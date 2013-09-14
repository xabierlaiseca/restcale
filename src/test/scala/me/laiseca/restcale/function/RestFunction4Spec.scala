package me.laiseca.restcale.function

import org.mockito.Mockito.verify
import org.scalatest.FlatSpec
import org.scalatest.Matchers
import org.scalatest.mock.MockitoSugar

import me.laiseca.restcale.http.GET_METHOD

class RestFunction4Spec extends FlatSpec with Matchers with MockitoSugar {
  
  val method = GET_METHOD
  val path = "/some/path"
  
  "Function4 wrapper" should "call the wrapped function with same parameters" in {
	  val function = mock[Function4[AnyRef,AnyRef,AnyRef,AnyRef,AnyRef]]
	  val p1 = mock[AnyRef]; val p2 = mock[AnyRef]; val p3 = mock[AnyRef]; val p4 = mock[AnyRef]
	  
	  val testObj = new RestFunction4(function, method, path)
	  testObj.apply(p1, p2, p3, p4)
	  
	  verify(function).apply(p1, p2, p3, p4)
  }
}