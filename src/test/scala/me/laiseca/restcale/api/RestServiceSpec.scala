package me.laiseca.restcale.api

import org.scalatest.matchers.ShouldMatchers
import org.scalatest.mock.MockitoSugar
import org.scalatest.FlatSpec
import me.laiseca.restcale.http.GET_METHOD
import me.laiseca.restcale.function.BaseRestFunction
import me.laiseca.restcale.function.BaseRestFunction
import me.laiseca.restcale.http.POST_METHOD
import me.laiseca.restcale.http.PUT_METHOD
import me.laiseca.restcale.http.DELETE_METHOD

class RestServiceSpec extends FlatSpec with ShouldMatchers with MockitoSugar {
  val path = "/test/path"
  
  "The GET method" should "create a BaseRestFunction with the given information" in {
    val testObj = new TestClass with RestService {
      val getMethod = GET(path){ x:Int => x }
    }
    
    assert(testObj.getMethod.isInstanceOf[BaseRestFunction])
    
    val baseRestFunction = testObj.getMethod.asInstanceOf[BaseRestFunction]
    
    expect(GET_METHOD) {
      baseRestFunction.method
    }
    
    expect(path) {
      baseRestFunction.path
    }
  }
  
  "The POST method" should "create a BaseRestFunction with the given information" in {
    val testObj = new TestClass with RestService {
      val getMethod = POST(path){ x:Int => x }
    }
    
    assert(testObj.getMethod.isInstanceOf[BaseRestFunction])
    
    val baseRestFunction = testObj.getMethod.asInstanceOf[BaseRestFunction]
    
    expect(POST_METHOD) {
      baseRestFunction.method
    }
    
    expect(path) {
      baseRestFunction.path
    }
  }
  
  "The PUT method" should "create a BaseRestFunction with the given information" in {
    val testObj = new TestClass with RestService {
      val getMethod = PUT(path){ x:Int => x }
    }
    
    assert(testObj.getMethod.isInstanceOf[BaseRestFunction])
    
    val baseRestFunction = testObj.getMethod.asInstanceOf[BaseRestFunction]
    
    expect(PUT_METHOD) {
      baseRestFunction.method
    }
    
    expect(path) {
      baseRestFunction.path
    }
  }
  
  "The DELETE method" should "create a BaseRestFunction with the given information" in {
    val testObj = new TestClass with RestService {
      val getMethod = DELETE(path){ x:Int => x }
    }
    
    assert(testObj.getMethod.isInstanceOf[BaseRestFunction])
    
    val baseRestFunction = testObj.getMethod.asInstanceOf[BaseRestFunction]
    
    expect(DELETE_METHOD) {
      baseRestFunction.method
    }
    
    expect(path) {
      baseRestFunction.path
    }
  }
  
  class TestClass
}