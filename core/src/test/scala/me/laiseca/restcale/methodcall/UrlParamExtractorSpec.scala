package me.laiseca.restcale.methodcall

import scala.reflect.runtime.{universe => ru}
import org.mockito.Mockito.when
import org.scalatest.FlatSpec
import org.scalatest.Matchers
import org.scalatest.BeforeAndAfter
import org.scalatest.mock.MockitoSugar
import me.laiseca.restcale.http.HttpRequest

class UrlParamExtractorSpec extends FlatSpec with Matchers  with MockitoSugar with BeforeAndAfter {
  val PARAM_NAME = "value"
  val OTHER_PARAM_NAME = "other"
  var testObj:UrlParamExtractor = _

  before {
    val extractor = mock[TypeTransformer]
    when(extractor.supports(ru.typeOf[NotSupportedType])).thenReturn(false)
    when(extractor.supports(ru.typeOf[Int])).thenReturn(true)
    when(extractor.transform("10", ru.typeOf[Int])).thenReturn(Option.apply(10))
    
    testObj = new UrlParamExtractor("/method/:" + PARAM_NAME, extractor)
  }
  
  "given a supported type parameter, the url extractor" should "return the value given in the url path" in {
    val request:HttpRequest = mock[HttpRequest]
    when(request.path).thenReturn("/method/10")
    
    assertResult(10) {
	  testObj.extractParam(ru.typeOf[Int], PARAM_NAME, request).get
    }
  }
  
  "given a not supported type parameter, the url extractor" should "throw a illegal type exception" in {
    val request:HttpRequest = mock[HttpRequest]
    when(request.path).thenReturn("/method/10")
    
    intercept[IllegalParameterTypeException] {
	  testObj.extractParam(ru.typeOf[NotSupportedType], PARAM_NAME, request)
    }
  }

  "given a not existing param name in url, the url extractor" should "return an empty return value" in {
    val request:HttpRequest = mock[HttpRequest]
    when(request.path).thenReturn("/method/10")
    
    assertResult(Option.empty) {
	  testObj.extractParam(ru.typeOf[Int], OTHER_PARAM_NAME, request)
    }
  }
}