package me.laiseca.restcale.internal.function

import org.mockito.Mockito.verify
import org.scalatest.FlatSpec
import org.scalatest.Matchers
import org.scalatest.mock.MockitoSugar

import me.laiseca.restcale.http.HttpMethod

class RestFunction1Spec extends FlatSpec with Matchers with MockitoSugar {
  
  val method = HttpMethod.GET
  val path = "/some/path"
  
  "Function1 wrapper" should "call the wrapped function with same parameter" in {
	  val function = mock[Function1[AnyRef,AnyRef]]
	  val p1 = mock[AnyRef]
	  
	  val arg = new Argument(classOf[AnyRef].getName, "arg")
	  val testObj = new RestFunction1(function, method, path, List(arg))
	  testObj.apply(p1)
	  
	  verify(function).apply(p1)
  }
}