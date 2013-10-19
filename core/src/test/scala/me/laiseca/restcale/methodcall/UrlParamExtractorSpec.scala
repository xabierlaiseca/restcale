package me.laiseca.restcale.methodcall

import scala.reflect.runtime.{universe => ru}
import org.mockito.Mockito.when
import org.scalatest.FlatSpec
import org.scalatest.Matchers
import org.scalatest.BeforeAndAfter
import io.netty.handler.codec.http.HttpRequest
import org.scalatest.mock.MockitoSugar

class UrlParamExtractorSpec extends FlatSpec with Matchers  with MockitoSugar with BeforeAndAfter {
  val PARAM_NAME = "value"
  var testObj:UrlParamExtractor = _

  before {
    testObj = new UrlParamExtractor("/methond/:" + PARAM_NAME)
  }
  
  "given a method byte parameter the url extractor" should "return the byte value given in the url path" in {
    val request:HttpRequest = mock[HttpRequest]
    when(request.getUri()).thenReturn("/method/10")
    
    assertResult(10.toByte){
	  testObj.extractParam(PARAM_NAME, ru.typeTag[Byte], request).get
    }
  }
  
  "given a method short parameter the url extractor" should "return the short value given in the url path" in {
    val request:HttpRequest = mock[HttpRequest]
    when(request.getUri()).thenReturn("/method/10")
    
    assertResult(10.toShort){
	  testObj.extractParam(PARAM_NAME, ru.typeTag[Short], request).get
    }
  }

  "given a method int parameter the url extractor" should "return the int value given in the url path" in {
    val request:HttpRequest = mock[HttpRequest]
    when(request.getUri()).thenReturn("/method/10")
    
    assertResult(10){
	  testObj.extractParam(PARAM_NAME, ru.typeTag[Int], request).get
    }
  }
  
  "given a method long parameter the url extractor" should "return the long value given in the url path" in {
    val request:HttpRequest = mock[HttpRequest]
    when(request.getUri()).thenReturn("/method/10")
    
    assertResult(10.toLong){
	  testObj.extractParam(PARAM_NAME, ru.typeTag[Long], request).get
    }
  }
  
  "given a method float parameter the url extractor" should "return the float value given in the url path" in {
    val request:HttpRequest = mock[HttpRequest]
    when(request.getUri()).thenReturn("/method/10")
    
    assertResult(10.0.toFloat){
	  testObj.extractParam(PARAM_NAME, ru.typeTag[Float], request).get
    }
  }
  
  "given a method double parameter the url extractor" should "return the double value given in the url path" in {
    val request:HttpRequest = mock[HttpRequest]
    when(request.getUri()).thenReturn("/method/10")
    
    assertResult(10.0){
	  testObj.extractParam(PARAM_NAME, ru.typeTag[Double], request).get
    }
  }
  
  "given a method char parameter the url extractor" should "return the char value given in the url path" in {
    val request:HttpRequest = mock[HttpRequest]
    when(request.getUri()).thenReturn("/method/c")
    
    assertResult('c'){
	  testObj.extractParam(PARAM_NAME, ru.typeTag[Char], request).get
    }
  }
  
  "given a method string parameter the url extractor" should "return the string value given in the url path" in {
    val request:HttpRequest = mock[HttpRequest]
    when(request.getUri()).thenReturn("/method/something")
    
    assertResult("something"){
	  testObj.extractParam(PARAM_NAME, ru.typeTag[String], request).get
    }
  }
  
  "given a method boolean parameter the url extractor" should "return the boolean value given in the url path" in {
    val request:HttpRequest = mock[HttpRequest]
    when(request.getUri()).thenReturn("/method/true")
    
    assertResult(true){
	  testObj.extractParam(PARAM_NAME, ru.typeTag[Boolean], request).get
    }
  }
}