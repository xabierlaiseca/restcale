package me.laiseca.restcale.internal.function

import org.mockito.Mockito.verify
import org.scalatest.FlatSpec
import org.scalatest.Matchers
import org.scalatest.mock.MockitoSugar

import me.laiseca.restcale.http.HttpMethod

class RestFunction10Spec extends FlatSpec with Matchers with MockitoSugar {
  
  val method = HttpMethod.GET
  val path = "/some/path"
  
  "Function10 wrapper" should "call the wrapped function with same parameters" in {
	  val function = mock[Function10[AnyRef,AnyRef,AnyRef,AnyRef,AnyRef,AnyRef,AnyRef,AnyRef,AnyRef,
	    AnyRef,AnyRef]]
	  val p1 = mock[AnyRef]; val p2 = mock[AnyRef]; val p3 = mock[AnyRef]; val p4 = mock[AnyRef]
	  val p5 = mock[AnyRef]; val p6 = mock[AnyRef]; val p7 = mock[AnyRef]; val p8 = mock[AnyRef]
	  val p9 = mock[AnyRef]; val p10 = mock[AnyRef]
	  
	  val typeParam = new TypeParameter(classOf[String].getName(), List())
	  val arg = new Argument(classOf[AnyRef].getName, List(typeParam), "arg")
	  val testObj = new RestFunction10(function, method, path, List(arg, arg, arg, arg, arg,
	      arg, arg, arg, arg, arg))
	  testObj.apply(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10)
	  
	  verify(function).apply(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10)
  }
}