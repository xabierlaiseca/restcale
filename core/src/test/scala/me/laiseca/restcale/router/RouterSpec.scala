package me.laiseca.restcale.router

import scala.reflect.runtime.{universe => ru}
import scala.reflect.runtime.universe._
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.Mockito.when
import org.scalatest.BeforeAndAfter
import org.scalatest.FlatSpec
import org.scalatest.Matchers
import org.scalatest.mock.MockitoSugar
import me.laiseca.restcale.methodcall.MethodCaller
import me.laiseca.restcale.http.HttpRequest

class RouterSpec extends FlatSpec with Matchers with MockitoSugar {

  val restMethods = List(
      new RestMethod(null, null, "GET", null, List(new FixedRouteSegment("method1"))),
      new RestMethod(null, null, "POST", null, List(new FixedRouteSegment("method1"))),
      new RestMethod(null, null, "GET", null, List(new FixedRouteSegment("method1"), WildcardRouteSegment))
  )
  
  "when the enpoint with fixed segments is called the router" should "delegate the call to the caller" in {
    val caller = mock[MethodCaller]
    val request = mock[HttpRequest]
    
    when(request.method).thenReturn("POST")
    when(request.path).thenReturn("/method1")
    
    val router = new Router(restMethods, caller)
    router.route(request)
    
    verify(caller).call(restMethods(1), request)
  }
  
  "when the enpoint with some wildcard segments is called the router" should "delegate the call to the caller" in {
    val caller = mock[MethodCaller]
    val request = mock[HttpRequest]
    
    when(request.method).thenReturn("GET")
    when(request.path).thenReturn("/method1/10")
    
    val router = new Router(restMethods, caller)
    router.route(request)
    
    verify(caller).call(restMethods(2), request)
  }
  
  "when no method exists for the given http method the router" should "throws an exception" in {
    val caller = mock[MethodCaller]
    val request = mock[HttpRequest]
    
    when(request.method).thenReturn("DELETE")
    
    val router = new Router(restMethods, caller)
    intercept[RouteNotFoundException] {
    	router.route(request)
    }
  }
    
  "when no method exists for the given url the router" should "throws an exception" in {
    val caller = mock[MethodCaller]
    val request = mock[HttpRequest]
    
    when(request.method).thenReturn("GET")
    when(request.path).thenReturn("/method110")
    
    val router = new Router(restMethods, caller)
    intercept[RouteNotFoundException] {
    	router.route(request)
    }
  }
}
