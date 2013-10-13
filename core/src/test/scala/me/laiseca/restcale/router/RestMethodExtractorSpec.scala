package me.laiseca.restcale.router

import scala.reflect.runtime.{universe => ru}
import org.scalatest.FlatSpec
import org.scalatest.Inside
import org.scalatest.Matchers
import me.laiseca.restcale.api.RestService

class RestMethodExtractorSpec extends FlatSpec with Matchers with Inside {
  
  "given an instance of a class with one def method, the rest method extractor" should
  		"extract one method" in {
    val service = new DefRestService
    
    val extractor = new RestMethodExtractor
    val methods = extractor.extract(List(service))
    
    assertResult(1)(methods.size)
    inside(methods(0)){ case RestMethod(service, method, httpMethod, route) =>
      service should be (service)
      method should be (ru.typeOf[DefRestService].declaration(ru.newTermName("restMethodDef")).asMethod)
      httpMethod should be ("GET")
      route should be (List(new FixedRouteSegment("urla"), new WildcardRouteSegment("a")))
    }
  }
  
  "given an instance of a class with one val method, the rest method extractor" should
  		"extract one method" in {
    val service = new ValRestService
    
    val extractor = new RestMethodExtractor
    val methods = extractor.extract(List(service))
    
    assertResult(1)(methods.size)
    inside(methods(0)){ case RestMethod(service, method, httpMethod, route) =>
      service should be (service)
      method should be (ru.typeOf[ValRestService].declaration(ru.newTermName("restMethodVal")).asMethod)
      httpMethod should be ("POST")
      route should be (List(new FixedRouteSegment("urlb"), new WildcardRouteSegment("b")))
    }
  }
  
}

private class DefRestService extends RestService {
  def restMethodDef = GET("/urla/:a") { () =>
    ""
  }
}

private class ValRestService extends RestService {
  val restMethodVal = POST("/urlb/:a") { () =>
    ""
  }
}
