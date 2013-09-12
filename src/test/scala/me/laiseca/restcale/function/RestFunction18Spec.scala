package me.laiseca.restcale.function

import org.mockito.Mockito.verify
import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.mock.MockitoSugar

import me.laiseca.restcale.http.GET_METHOD

class RestFunction18Spec extends FlatSpec with ShouldMatchers with MockitoSugar {
  
  val method = GET_METHOD
  val path = "/some/path"
  
  "Function18 wrapper" should "call the wrapped function with same parameters" in {
	  val function = mock[Function18[AnyRef,AnyRef,AnyRef,AnyRef,AnyRef,AnyRef,AnyRef,AnyRef,AnyRef,
	    AnyRef,AnyRef,AnyRef,AnyRef,AnyRef,AnyRef,AnyRef,AnyRef,AnyRef,AnyRef]]
	  val p1 = mock[AnyRef]; val p2 = mock[AnyRef]; val p3 = mock[AnyRef]; val p4 = mock[AnyRef]
	  val p5 = mock[AnyRef]; val p6 = mock[AnyRef]; val p7 = mock[AnyRef]; val p8 = mock[AnyRef]
	  val p9 = mock[AnyRef]; val p10 = mock[AnyRef]; val p11 = mock[AnyRef]; val p12 = mock[AnyRef]
	  val p13 = mock[AnyRef]; val p14 = mock[AnyRef]; val p15 = mock[AnyRef]; val p16 = mock[AnyRef]
	  val p17 = mock[AnyRef]; val p18 = mock[AnyRef]
	  
	  val testObj = new RestFunction18(function, method, path)
	  testObj.apply(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18)
	  
	  verify(function).apply(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16,
	      p17, p18)
  }
}