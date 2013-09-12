package me.laiseca.restcale.function

import org.mockito.Mockito.verify
import org.scalatest.FlatSpec
import org.scalatest.mock.MockitoSugar
import org.scalatest.matchers.ShouldMatchers
import me.laiseca.restcale.http.GET_METHOD

class RestFunction0Spec extends FlatSpec with ShouldMatchers with MockitoSugar {
  
  val method = GET_METHOD
  val path = "/some/path"
  
  "Function0 wrapper" should "call the wrapped function" in {
	  val function = mock[Function0[AnyRef]]
	  
	  val testObj = new RestFunction0(function, method, path)
	  testObj.apply()
	  
	  verify(function).apply()
  }
}