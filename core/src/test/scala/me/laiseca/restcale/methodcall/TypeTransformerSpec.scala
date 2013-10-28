package me.laiseca.restcale.methodcall

import scala.reflect.runtime.{universe => ru}
import org.scalatest.FlatSpec
import org.scalatest.Matchers

class TypeTransformerSpec extends FlatSpec with Matchers {
  
  "given a supported type, supports" should "return true" in {
    val testObj = new TypeTransformer
    
    assert{
      testObj.supports(ru.typeOf[Int])
    }
  }
  
  "given a not supported type, supports" should "return false" in {
    val testObj = new TypeTransformer
    
    assertResult(false){
      testObj.supports(ru.typeOf[NotSupportedType])
    }
  }
  
  "given a String containing a Byte, transform with type" should "return the Byte value" in {
    val testObj = new TypeTransformer
    
    assertResult(10.toByte) {
      testObj.transform("10", ru.typeOf[Byte]).get
    }
  }
  
  "given a String not containing a Byte, transform to Byte" should "throw an exception" in {
    val testObj = new TypeTransformer

    intercept[IllegalValueException] {
      testObj.transform("10a", ru.typeOf[Byte])
    }
  }
  
  "given a String containing a Short, transform with type" should "return the Short value" in {
    val testObj = new TypeTransformer
    
    assertResult(10.toShort) {
      testObj.transform("10", ru.typeOf[Short]).get
    }
  }
  
  "given a String not containing a Short, transform to Short" should "throw an exception" in {
    val testObj = new TypeTransformer

    intercept[IllegalValueException] {
      testObj.transform("10a", ru.typeOf[Short])
    }
  }
  
  "given a String containing a Int, transform with type" should "return the Int value" in {
    val testObj = new TypeTransformer
    
    assertResult(10) {
      testObj.transform("10", ru.typeOf[Int]).get
    }
  }
  
  "given a String not containing a Int, transform to Int" should "throw an exception" in {
    val testObj = new TypeTransformer

    intercept[IllegalValueException] {
      testObj.transform("10a", ru.typeOf[Int])
    }
  }
  
  "given a String containing a Long, transform with type" should "return the Long value" in {
    val testObj = new TypeTransformer
    
    assertResult(10.toLong) {
      testObj.transform("10", ru.typeOf[Long]).get
    }
  }
  
  "given a String not containing a Long, transform to Long" should "throw an exception" in {
    val testObj = new TypeTransformer

    intercept[IllegalValueException] {
      testObj.transform("10a", ru.typeOf[Long])
    }
  }

  "given a String containing a Float, transform with type" should "return the Float value" in {
    val testObj = new TypeTransformer
    
    assertResult(10.2.toFloat) {
      testObj.transform("10.2", ru.typeOf[Float]).get
    }
  }
  
  "given a String not containing a Float, transform to Float" should "throw an exception" in {
    val testObj = new TypeTransformer

    intercept[IllegalValueException] {
      testObj.transform("10.2a", ru.typeOf[Float])
    }
  }
  
  "given a String containing a Double, transform with type" should "return the Double value" in {
    val testObj = new TypeTransformer
    
    assertResult(10.2) {
      testObj.transform("10.2", ru.typeOf[Double]).get
    }
  }
  
  "given a String not containing a Double, transform to Double" should "throw an exception" in {
    val testObj = new TypeTransformer

    intercept[IllegalValueException] {
      testObj.transform("10.2a", ru.typeOf[Double])
    }
  }
  
  "given a String containing a Char, transform with type" should "return the Char value" in {
    val testObj = new TypeTransformer
    
    assertResult('c') {
      testObj.transform("c", ru.typeOf[Char]).get
    }
  }
  
  "given a String not containing a Char, transform to Char" should "throw an exception" in {
    val testObj = new TypeTransformer

    intercept[IllegalValueException] {
      testObj.transform("cc", ru.typeOf[Char])
    }
  }
  
  "given a String containing a Boolean, transform with type" should "return the Boolean value" in {
    val testObj = new TypeTransformer
    
    assertResult(true) {
      testObj.transform("true", ru.typeOf[Boolean]).get
    }
  }
  
  "given a String not containing a Boolean, transform to Boolean" should "throw an exception" in {
    val testObj = new TypeTransformer

    intercept[IllegalValueException] {
      testObj.transform("something", ru.typeOf[Boolean])
    }
  }
  
  "given a String, transform to String with runtime type" should "return the same value" in {
    val testObj = new TypeTransformer
    
    assertResult("something") {
      testObj.transform("something", ru.typeOf[String]).get
    }
  }
  
  "given a not supported type, transform" should "return an empty option" in {
    val testObj = new TypeTransformer
    
    assertResult(Option.empty) {
      testObj.transform(null, ru.typeOf[NotSupportedType])
    }
  }
}