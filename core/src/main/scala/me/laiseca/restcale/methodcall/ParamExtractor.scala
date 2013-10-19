package me.laiseca.restcale.methodcall

import scala.reflect.runtime.{universe => ru}
import scala.reflect.runtime.universe._
import io.netty.handler.codec.http.HttpRequest
import me.laiseca.restcale.util.PathUtils

trait ParamExtractor {
  def extractParam[T](paramName:String, paramType:TypeTag[T], request:HttpRequest):Option[T]
}

class UrlParamExtractor(val pathTemplate:String) extends ParamExtractor {
  override def extractParam[T](paramName:String, paramType:TypeTag[T], request:HttpRequest):Option[T] = {
    if(isSupportedParamType(paramType)) {
      val paramStringValue = extractParamStringValue(request.getUri(), paramName)
      if(paramStringValue.isDefined) {
        Option.apply(paramAs(paramStringValue.get, paramType))
      } else {
        Option.empty
      }
    } else {
      Option.empty
    }
  }
  
  private def isSupportedParamType[T](paramType:TypeTag[T]):Boolean = {
    UrlParamExtractor.TYPE_TRANSFORMERS.contains(paramType)
  }
  
  private def extractParamStringValue(path:String, paramName:String):Option[String] = {
    val segments = PathUtils.split(pathTemplate)
    val index = segments.indexOf(":" + paramName)
    if(index > 0) {
      Option.apply(PathUtils.split(path)(index))
    } else {
      Option.empty
    }
  }
  
  private def paramAs[T](paramStringValue:String, paramType:TypeTag[T]):T = {
    val value = (UrlParamExtractor.TYPE_TRANSFORMERS.get(paramType).get)(paramStringValue)
    value.asInstanceOf[T]
  }
}

object UrlParamExtractor {
  val TYPE_TRANSFORMERS:Map[TypeTag[_],(String) => Any] = Map(
        ru.typeTag[Byte] -> ((value:String) => value.toByte),
        ru.typeTag[Short] -> ((value:String) => value.toShort),
        ru.typeTag[Int] -> ((value:String) => value.toInt),
        ru.typeTag[Long] -> ((value:String) => value.toLong),
        ru.typeTag[Float] -> ((value:String) => value.toFloat),
        ru.typeTag[Double] -> ((value:String) => value.toDouble),
        ru.typeTag[Char] -> ((value:String) => value.charAt(0)),
        ru.typeTag[String] -> ((value:String) => value),
        ru.typeTag[Boolean] -> ((value:String) => value.toBoolean)
      )
}