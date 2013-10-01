package me.laiseca.restcale.internal.function

import org.mockito.Mockito.verify
import org.scalatest.FlatSpec
import org.scalatest.Matchers
import org.scalatest.mock.MockitoSugar

import me.laiseca.restcale.http.HttpMethod

class RestFunction14Spec extends FlatSpec with Matchers with MockitoSugar {
  
  val method = HttpMethod.GET
  val path = "/some/path"
  
  "Function14 wrapper" should "call the wrapped function with same parameters" in {
	  val function = mock[Function14[AnyRef,AnyRef,AnyRef,AnyRef,AnyRef,AnyRef,AnyRef,AnyRef,AnyRef,
	    AnyRef,AnyRef,AnyRef,AnyRef,AnyRef,AnyRef]]
	  val p1 = mock[AnyRef]; val p2 = mock[AnyRef]; val p3 = mock[AnyRef]; val p4 = mock[AnyRef]
	  val p5 = mock[AnyRef]; val p6 = mock[AnyRef]; val p7 = mock[AnyRef]; val p8 = mock[AnyRef]
	  val p9 = mock[AnyRef]; val p10 = mock[AnyRef]; val p11 = mock[AnyRef]; val p12 = mock[AnyRef]
	  val p13 = mock[AnyRef]; val p14 = mock[AnyRef]
	  
	  val testObj = new RestFunction14(function, method, path)
	  testObj.apply(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14)
	  
	  verify(function).apply(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14)
  }
}