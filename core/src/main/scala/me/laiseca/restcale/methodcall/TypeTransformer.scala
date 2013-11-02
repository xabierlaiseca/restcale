package me.laiseca.restcale.methodcall

import scala.reflect.runtime.{universe => ru}
import scala.reflect.runtime.universe._

class TypeTransformer {
  def supports(tpe: Type):Boolean = TypeTransformer.TYPE_TRANSFORMERS.contains(tpe)
  
  def transform(stringValue: String, tpe: Type):Option[Any] =
    Option.apply(transformValue(stringValue, tpe))
    
  private def transformValue(stringValue: String, tpe: Type):Any = {
    val transformer = TypeTransformer.TYPE_TRANSFORMERS.get(tpe)
    
    if(transformer.isDefined) {
      try {
        (transformer.get)(stringValue)
      } catch {
        case e@(_:IllegalArgumentException | _:NumberFormatException)
        	=> throw new IllegalValueException(stringValue, e)
      }
    } else {
      null
    }
  }
}

private object TypeTransformer {
  val TYPE_TRANSFORMERS:Map[Type,(String) => Any] = Map(
    ru.typeTag[Byte].tpe -> ((value:String) => value.toByte),
    ru.typeTag[Short].tpe -> ((value:String) => value.toShort),
    ru.typeTag[Int].tpe -> ((value:String) => value.toInt),
    ru.typeTag[Long].tpe -> ((value:String) => value.toLong),
    ru.typeTag[Float].tpe -> ((value:String) => value.toFloat),
    ru.typeTag[Double].tpe -> ((value:String) => value.toDouble),
    ru.typeTag[Char].tpe -> ((value:String) => {
      if(value.length() == 1) {
    	  value.charAt(0)
      } else {
        throw new IllegalArgumentException
      }
    }),
    ru.typeTag[String].tpe -> ((value:String) => value),
    ru.typeTag[java.lang.String].tpe -> ((value:String) => value),
    ru.typeTag[Boolean].tpe -> ((value:String) => value.toBoolean)
  )
}