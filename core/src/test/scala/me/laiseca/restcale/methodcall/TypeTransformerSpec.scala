package me.laiseca.restcale.methodcall

import org.scalatest.FlatSpec
import org.scalatest.Matchers

class TypeTransformerSpec extends FlatSpec with Matchers {
  
  "given a supported type, supports" should "return true" in {
    val testObj = new TypeTransformer
    
    assert{
      testObj.supports[Int]
    }
  }
  
  "given a not supported type, supports" should "return false" in {
    val testObj = new TypeTransformer
    
    assertResult(false){
      testObj.supports[NotSupportedType]
    }
  }
  
  "given a String containing a Byte, transform" should "return the Byte value" in {
    val testObj = new TypeTransformer
    
    assertResult(10.toByte) {
      testObj.transform[Byte]("10").get
    }
  }
  
  "given a String not containing a Byte, transform to Byte" should "throw an exception" in {
    val testObj = new TypeTransformer

    intercept[IllegalValueException] {
      testObj.transform[Byte]("10a")
    }
  }
  
  "given a String containing a Short, transform" should "return the Short value" in {
    val testObj = new TypeTransformer
    
    assertResult(10.toShort) {
      testObj.transform[Short]("10").get
    }
  }
  
  "given a String not containing a Short, transform to Short" should "throw an exception" in {
    val testObj = new TypeTransformer

    intercept[IllegalValueException] {
      testObj.transform[Short]("10a")
    }
  }
  
  "given a String containing a Int, transform" should "return the Int value" in {
    val testObj = new TypeTransformer
    
    assertResult(10) {
      testObj.transform[Int]("10").get
    }
  }
  
  "given a String not containing a Int, transform to Int" should "throw an exception" in {
    val testObj = new TypeTransformer

    intercept[IllegalValueException] {
      testObj.transform[Int]("10a")
    }
  }
  
  "given a String containing a Long, transform" should "return the Long value" in {
    val testObj = new TypeTransformer
    
    assertResult(10.toLong) {
      testObj.transform[Long]("10").get
    }
  }
  
  "given a String not containing a Long, transform to Long" should "throw an exception" in {
    val testObj = new TypeTransformer

    intercept[IllegalValueException] {
      testObj.transform[Long]("10a")
    }
  }

  "given a String containing a Float, transform" should "return the Float value" in {
    val testObj = new TypeTransformer
    
    assertResult(10.2.toFloat) {
      testObj.transform[Float]("10.2").get
    }
  }
  
  "given a String not containing a Float, transform to Float" should "throw an exception" in {
    val testObj = new TypeTransformer

    intercept[IllegalValueException] {
      testObj.transform[Float]("10.2a")
    }
  }
  
  "given a String containing a Double, transform" should "return the Double value" in {
    val testObj = new TypeTransformer
    
    assertResult(10.2) {
      testObj.transform[Double]("10.2").get
    }
  }
  
  "given a String not containing a Double, transform to Double" should "throw an exception" in {
    val testObj = new TypeTransformer

    intercept[IllegalValueException] {
      testObj.transform[Double]("10.2a")
    }
  }
  
  "given a String containing a Char, transform" should "return the Char value" in {
    val testObj = new TypeTransformer
    
    assertResult('c') {
      testObj.transform[Char]("c").get
    }
  }
  
  "given a String not containing a Char, transform to Char" should "throw an exception" in {
    val testObj = new TypeTransformer

    intercept[IllegalValueException] {
      testObj.transform[Char]("cc")
    }
  }
  
  "given a String containing a Boolean, transform" should "return the Boolean value" in {
    val testObj = new TypeTransformer
    
    assertResult(true) {
      testObj.transform[Boolean]("true").get
    }
  }
  
  "given a String not containing a Boolean, transform to Boolean" should "throw an exception" in {
    val testObj = new TypeTransformer

    intercept[IllegalValueException] {
      testObj.transform[Boolean]("something")
    }
  }
  
  "given a String, transform to String" should "return the same value" in {
    val testObj = new TypeTransformer
    
    assertResult("something") {
      testObj.transform[String]("something").get
    }
  }
  
  "given a not supported type, transform" should "return an empty option" in {
    val testObj = new TypeTransformer
    
    assertResult(Option.empty) {
      testObj.transform[NotSupportedType](null)
    }
  }
}