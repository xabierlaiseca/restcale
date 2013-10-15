package me.laiseca.restcale.router

import scala.collection.mutable
import io.netty.handler.codec.http.HttpRequest
import me.laiseca.restcale.methodcall.MethodCaller
import me.laiseca.restcale.util.PathUtils

class Router(private val restMethods:List[RestMethod], private val caller: MethodCaller) {
  private val routes: Map[String, RouteMap] = initRoutes
  
  private def initRoutes() = {
    def addRoute(routeMap: MutableRouteMap, restMethod: RestMethod):Unit = {
      def addRoute(routeMap: MutableRouteMap, route:List[RouteSegment]):Unit = {
        if(route.size == 0) {
          routeMap.method = Option.apply(restMethod)
        } else {
          val currentRouteMap = routeMap.getOrElseUpdate(route(0), new MutableRouteMap)
          addRoute(currentRouteMap, route.drop(1))
        }
      }
      
      addRoute(routeMap, restMethod.route)
    }
    
    def toImmutableRoutes(mutableRoutes:mutable.Map[String, MutableRouteMap]):Map[String, RouteMap] = {
      mutableRoutes.toMap.flatMap(entry => List(entry._1 -> entry._2.toImmutable))
    }
    
    val routeMap = new mutable.HashMap[String, MutableRouteMap]
    for(restMethod <- restMethods) {
      val httpMethodRoutes = routeMap.getOrElseUpdate(restMethod.httpMethod, new MutableRouteMap)
      addRoute(httpMethodRoutes, restMethod)
    }
    
    toImmutableRoutes(routeMap)
  }
  
  def route(request:HttpRequest):Any = {
	val httpMethod = request.getMethod.name.toUpperCase
    val path = request.getUri
    
    def throwRouteNotFoundException() = throw new RouteNotFoundException(String.format("No route found for %s %s", httpMethod, path))
			
    def httpMethodRoutes(httpMethod:String):RouteMap = {
      val subroutes = routes get httpMethod
      if(subroutes.isDefined) {
        subroutes.get
      } else {
        throwRouteNotFoundException
      }
    }
    
    def subroutesForSegment(routes:RouteMap, segment:String) = {
      val routeSegment = new FixedRouteSegment(segment)
      val subroutes = routes.routes get routeSegment
      if(subroutes.isDefined) {
        subroutes
      } else {
        routes.routes get Router.WILDCARD
      }
    }
    
    def methodToCall(currentRoutes: RouteMap, segments: List[String]):Option[RestMethod] = {
      if(segments.isEmpty) {
        currentRoutes.method
      } else {
        val subroutes = subroutesForSegment(currentRoutes, segments(0))
        if(subroutes.isDefined) {
          methodToCall(subroutes.get, segments.drop(1))
        } else {
          Option.empty
        }
      }
    }
    
    val routesForHttpMethod = httpMethodRoutes(httpMethod)
    val segments = PathUtils.split(path)
    val method = methodToCall(routesForHttpMethod, segments)
    if(method.isDefined) {
      caller.call(method.get, request)
    } else {
      throwRouteNotFoundException
    }
  }
  
  private class MutableRouteMap extends mutable.HashMap[RouteSegment, MutableRouteMap] {
    var method: Option[RestMethod] = Option.empty

    def toImmutable():RouteMap = new RouteMap(method, this.toMap.flatMap(entry => List(entry._1 -> entry._2.toImmutable)))
  }
}

class RouteMap(val method:Option[RestMethod], val routes: Map[RouteSegment, RouteMap])

object Router {
  val WILDCARD = new WildcardRouteSegment(null)
}

