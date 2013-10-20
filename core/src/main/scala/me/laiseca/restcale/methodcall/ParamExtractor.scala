package me.laiseca.restcale.methodcall

import scala.reflect.runtime.{universe => ru}
import scala.reflect.runtime.universe._
import me.laiseca.restcale.util.PathUtils
import me.laiseca.restcale.http.HttpRequest

trait ParamExtractor {
  def extractParam[T: TypeTag](paramName:String, request:HttpRequest):Option[T]
}

class UrlParamExtractor(val pathTemplate:String, typeTransformer:TypeTransformer) extends ParamExtractor {
  override def extractParam[T: TypeTag](paramName:String, request:HttpRequest):Option[T] = {
    if(typeTransformer.supports[T]) {
      val paramStringValue = extractParamStringValue(request.path, paramName)
      if(paramStringValue.isDefined) {
        return typeTransformer.transform(paramStringValue.get)
      }
    }
    Option.empty
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
}

object ParamExtractor {
  def defaultUrlParamExtractor(pathTemplate:String): ParamExtractor =
    new UrlParamExtractor(pathTemplate, new TypeTransformer)
}