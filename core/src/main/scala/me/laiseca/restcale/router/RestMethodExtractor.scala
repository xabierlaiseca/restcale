package me.laiseca.restcale.router

import scala.reflect.runtime.{universe => ru}
import scala.reflect.runtime.universe._
import me.laiseca.restcale.internal.function.BaseRestFunction
import me.laiseca.restcale.api.RestService
import me.laiseca.restcale.util.PathUtils

class RestMethodExtractor {
  private val m = ru.runtimeMirror(this.getClass().getClassLoader)
  
  private def getMethodSymbols(clazz:ClassSymbol): Iterable[MethodSymbol] = {
    val baseRestFunctionClassSymbol = m.staticClass(classOf[BaseRestFunction].getName())
    
    for {
      decl <- clazz.toType.declarations
      if decl.isMethod
      method = decl.asMethod
      if method.returnType.baseClasses.contains(baseRestFunctionClassSymbol)
    } yield method
  }
  
  private def buildRoute(pathString: String):List[RouteSegment] = {
    for {
      segmentString <- PathUtils.split(pathString)
    } yield {
      if(segmentString startsWith ":") {
        WildcardRouteSegment
      } else {
        new FixedRouteSegment(segmentString)
      }
    }
  }
  
  def extract(restServices:List[RestService]):List[RestMethod] = {
    for {
      restService <- restServices
      restServiceClassName = restService.getClass().getName()
      classSymbol = m.staticClass(restServiceClassName)
      methodSymbol <- getMethodSymbols(classSymbol)
    } yield {
      val serviceReflect = m.reflect(restService)
      val methodReflect = serviceReflect.reflectMethod(methodSymbol)
      val applied = methodReflect.apply()
      val restFunction = applied.asInstanceOf[BaseRestFunction]
      new RestMethod(restService, methodSymbol, restFunction.httpMethod, buildRoute(restFunction.path))
    }
  }
}
