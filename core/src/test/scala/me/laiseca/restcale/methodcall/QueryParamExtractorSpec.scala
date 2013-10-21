package me.laiseca.restcale.methodcall

import scala.reflect.runtime.{universe => ru}
import org.mockito.Mockito.when
import org.scalatest.Matchers
import org.scalatest.FlatSpec
import org.scalatest.BeforeAndAfter
import org.scalatest.mock.MockitoSugar
import me.laiseca.restcale.http.HttpRequest

class QueryParamExtractorSpec extends FlatSpec with Matchers with MockitoSugar with BeforeAndAfter {
  val LIST_PARAM_NAME = "listparam"
  val SINGLE_PARAM_NAME = "singleparam"
  val NOT_EXISTING_PARAM = "notexisting"
  val PARAM_VALUE_1 = "value1"
  val PARAM_VALUE_2 = "value2"
    
  var testObj:QueryParamExtractor = _
  var request:HttpRequest = _
  
  before {
    val typeTransformer = mock[TypeTransformer]
    when(typeTransformer.supports(ru.typeOf[String])).thenReturn(true)
    when(typeTransformer.supports[String]).thenReturn(true)
    when(typeTransformer.transform[String](PARAM_VALUE_1)).thenReturn(Option.apply(PARAM_VALUE_1))
    when(typeTransformer.transform(PARAM_VALUE_1, ru.typeOf[String])).thenReturn(Option.apply(PARAM_VALUE_1))
    when(typeTransformer.transform[String](PARAM_VALUE_2)).thenReturn(Option.apply(PARAM_VALUE_2))
    when(typeTransformer.transform(PARAM_VALUE_2, ru.typeOf[String])).thenReturn(Option.apply(PARAM_VALUE_2))
    
    request = mock[HttpRequest]
    when(request.parameters).thenReturn(Map(
    		LIST_PARAM_NAME -> List(PARAM_VALUE_1, PARAM_VALUE_2),
    		SINGLE_PARAM_NAME -> List(PARAM_VALUE_1)
        ))
    
    testObj = new QueryParamExtractor(typeTransformer)
  }
  
  "given a not existing parameter name" should "return an empty value" in {
    assertResult(Option.empty) {
      testObj.extractParam(NOT_EXISTING_PARAM, request)
    }
  }
  
  "when a list parameter of a supported type is extracted" should "return the transformed list" in {
    assertResult(List(PARAM_VALUE_1, PARAM_VALUE_2)) {
      testObj.extractParam[List[String]](LIST_PARAM_NAME, request).get
    }
  }
  
  "when a list parameter of a not supported type is extracted" should "throw an illegal value exception" in {
    intercept[IllegalValueException] {
      testObj.extractParam[List[Int]](LIST_PARAM_NAME, request)
    }
  }
  
  "when an option parameter of a supported type is extracted" should "return the transformed value" in {
    assertResult(Option.apply(PARAM_VALUE_1)) {
      testObj.extractParam[Option[String]](SINGLE_PARAM_NAME, request).get
    }
  }
  
  "when an option parameter of a not supported type is extracted" should "throw an illegal value exception" in {
    intercept[IllegalValueException] {
      testObj.extractParam[Option[Int]](SINGLE_PARAM_NAME, request)
    }
  }
  
  "when an option parameter is extracted from a list parameter" should "throw an illegal value exception" in {
    intercept[IllegalValueException] {
      testObj.extractParam[Option[String]](LIST_PARAM_NAME, request)
    }
  }
  
  "when a parameter of a supported type is extracted" should "return the transformed value" in {
    assertResult(PARAM_VALUE_1) {
      testObj.extractParam[String](SINGLE_PARAM_NAME, request).get
    }
  }
  
  "when a parameter of a not supported type is extracted" should "throw an illegal value exception" in {
    intercept[IllegalValueException] {
      testObj.extractParam[Int](SINGLE_PARAM_NAME, request)
    }
  }
  
  "when a parameter is extracted from a list parameter" should "throw an illegal value exception" in {
    intercept[IllegalValueException] {
      testObj.extractParam[String](LIST_PARAM_NAME, request)
    }
  }
}