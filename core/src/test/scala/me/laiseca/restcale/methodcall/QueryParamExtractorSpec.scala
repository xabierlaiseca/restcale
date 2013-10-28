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
  
  val stringListType = ru.typeOf[List[String]]
  val intListType = ru.typeOf[List[Int]]
  val stringOptionType = ru.typeOf[Option[String]]
  val intOptionType = ru.typeOf[Option[Int]]
  val stringType = ru.typeOf[String]
  val intType = ru.typeOf[Int]
  
  before {
    val typeTransformer = mock[TypeTransformer]
    when(typeTransformer.supports(ru.typeOf[String])).thenReturn(true)
    when(typeTransformer.transform(PARAM_VALUE_1, ru.typeOf[String])).thenReturn(Option.apply(PARAM_VALUE_1))
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
      testObj.extractParam(null, NOT_EXISTING_PARAM, request)
    }
  }
  
  "when a list parameter of a supported type is extracted" should "return the transformed list" in {
    assertResult(List(PARAM_VALUE_1, PARAM_VALUE_2)) {
      testObj.extractParam(stringListType, LIST_PARAM_NAME, request).get
    }
  }
  
  "when a list parameter of a not supported type is extracted" should "throw an illegal type exception" in {
    intercept[IllegalParameterTypeException] {
      testObj.extractParam(intListType, LIST_PARAM_NAME, request)
    }
  }
  
  "when an option parameter of a supported type is extracted" should "return the transformed value" in {
    assertResult(Option.apply(PARAM_VALUE_1)) {
      testObj.extractParam(stringOptionType, SINGLE_PARAM_NAME, request).get
    }
  }
  
  "when an option parameter of a not supported type is extracted" should "throw an illegal type exception" in {
    intercept[IllegalParameterTypeException] {
      testObj.extractParam(intOptionType, SINGLE_PARAM_NAME, request)
    }
  }
  
  "when an option parameter is extracted from a list parameter" should "throw an illegal value exception" in {
    intercept[IllegalValueException] {
      testObj.extractParam(stringOptionType, LIST_PARAM_NAME, request)
    }
  }
  
  "when a parameter of a supported type is extracted" should "return the transformed value" in {
    assertResult(PARAM_VALUE_1) {
      testObj.extractParam(stringType, SINGLE_PARAM_NAME, request).get
    }
  }
  
  "when a parameter of a not supported type is extracted" should "throw an illegal type exception" in {
    intercept[IllegalParameterTypeException] {
      testObj.extractParam(intType, SINGLE_PARAM_NAME, request)
    }
  }
  
  "when a parameter is extracted from a list parameter" should "throw an illegal value exception" in {
    intercept[IllegalValueException] {
      testObj.extractParam(stringType, LIST_PARAM_NAME, request)
    }
  }
}