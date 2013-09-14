package me.laiseca.restcale.api

import scala.language.reflectiveCalls

import org.scalatest.FlatSpec
import org.scalatest.Matchers

import me.laiseca.restcale.function.BaseRestFunction
import me.laiseca.restcale.http.DELETE_METHOD
import me.laiseca.restcale.http.GET_METHOD
import me.laiseca.restcale.http.POST_METHOD
import me.laiseca.restcale.http.PUT_METHOD

class RestServiceSpec extends FlatSpec with Matchers {
  val path = "/test/path"
  
  "The GET method" should "create a BaseRestFunction with the given information" in {
    val testObj = new TestClass with RestService {
      val getMethod = GET(path){ x:Int => x }
    }
    
    assert(testObj.getMethod.isInstanceOf[BaseRestFunction])
    
    val baseRestFunction = testObj.getMethod.asInstanceOf[BaseRestFunction]
    
    assertResult(GET_METHOD) {
      baseRestFunction.method
    }
    
    assertResult(path) {
      baseRestFunction.path
    }
  }
  
  "The POST method" should "create a BaseRestFunction with the given information" in {
    val testObj = new TestClass with RestService {
      val getMethod = POST(path){ x:Int => x }
    }
    
    assert(testObj.getMethod.isInstanceOf[BaseRestFunction])
    
    val baseRestFunction = testObj.getMethod.asInstanceOf[BaseRestFunction]
    
    assertResult(POST_METHOD) {
      baseRestFunction.method
    }
    
    assertResult(path) {
      baseRestFunction.path
    }
  }
  
  "The PUT method" should "create a BaseRestFunction with the given information" in {
    val testObj = new TestClass with RestService {
      val getMethod = PUT(path){ x:Int => x }
    }
    
    assert(testObj.getMethod.isInstanceOf[BaseRestFunction])
    
    val baseRestFunction = testObj.getMethod.asInstanceOf[BaseRestFunction]
    
    assertResult(PUT_METHOD) {
      baseRestFunction.method
    }
    
    assertResult(path) {
      baseRestFunction.path
    }
  }
  
  "The DELETE method" should "create a BaseRestFunction with the given information" in {
    val testObj = new TestClass with RestService {
      val getMethod = DELETE(path){ x:Int => x }
    }
    
    assert(testObj.getMethod.isInstanceOf[BaseRestFunction])
    
    val baseRestFunction = testObj.getMethod.asInstanceOf[BaseRestFunction]
    
    assertResult(DELETE_METHOD) {
      baseRestFunction.method
    }
    
    assertResult(path) {
      baseRestFunction.path
    }
  }
  
  class TestClass
}