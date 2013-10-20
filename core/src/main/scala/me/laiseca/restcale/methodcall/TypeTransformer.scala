package me.laiseca.restcale.methodcall

import scala.reflect.runtime.{universe => ru}
import scala.reflect.runtime.universe._

class TypeTransformer {
  def supports[T](implicit ttag: TypeTag[T]) = {
    TypeTransformer.TYPE_TRANSFORMERS.contains(ttag)
  }
  
  def transform[T: ru.TypeTag](stringValue: String)(implicit ttag:TypeTag[T]):Option[T] = {
    val transformer = TypeTransformer.TYPE_TRANSFORMERS.get(ttag)
    
    if(transformer.isDefined) {
      try {
        val value = (transformer.get)(stringValue)
        Option.apply(value.asInstanceOf[T])
      } catch {
        case e@(_:IllegalArgumentException | _:NumberFormatException)
        	=> throw new IllegalValueException(stringValue, e)
      }
    } else {
      Option.empty
    }
  }
}

private object TypeTransformer {
  val TYPE_TRANSFORMERS:Map[TypeTag[_],(String) => Any] = Map(
    ru.typeTag[Byte] -> ((value:String) => value.toByte),
    ru.typeTag[Short] -> ((value:String) => value.toShort),
    ru.typeTag[Int] -> ((value:String) => value.toInt),
    ru.typeTag[Long] -> ((value:String) => value.toLong),
    ru.typeTag[Float] -> ((value:String) => value.toFloat),
    ru.typeTag[Double] -> ((value:String) => value.toDouble),
    ru.typeTag[Char] -> ((value:String) => {
      if(value.length() == 1) {
    	  value.charAt(0)
      } else {
        throw new IllegalArgumentException
      }
    }),
    ru.typeTag[String] -> ((value:String) => value),
    ru.typeTag[Boolean] -> ((value:String) => value.toBoolean)
  )
}