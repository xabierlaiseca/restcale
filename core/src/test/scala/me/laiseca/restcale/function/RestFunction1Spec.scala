package me.laiseca.restcale.function

import org.mockito.Mockito.verify
import org.scalatest.FlatSpec
import org.scalatest.Matchers
import org.scalatest.mock.MockitoSugar

import me.laiseca.restcale.http.GET_METHOD

class RestFunction1Spec extends FlatSpec with Matchers with MockitoSugar {
  
  val method = GET_METHOD
  val path = "/some/path"
  
  "Function1 wrapper" should "call the wrapped function with same parameter" in {
	  val function = mock[Function1[AnyRef,AnyRef]]
	  val p1 = mock[AnyRef]
	  
	  val testObj = new RestFunction1(function, method, path)
	  testObj.apply(p1)
	  
	  verify(function).apply(p1)
  }
}