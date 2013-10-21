package me.laiseca.restcale.methodcall

import scala.reflect.runtime.{universe => ru}
import scala.reflect.runtime.universe._
import me.laiseca.restcale.util.PathUtils
import me.laiseca.restcale.http.HttpRequest

trait ParamExtractor {
  def extractParam[T:TypeTag](paramName:String, request:HttpRequest):Option[T]
}

class UrlParamExtractor(val pathTemplate:String, typeTransformer:TypeTransformer) extends ParamExtractor {
  override def extractParam[T:TypeTag](paramName:String, request:HttpRequest):Option[T] = {
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

class QueryParamExtractor(typeTransformer:TypeTransformer) extends ParamExtractor {
  val iterableSymbol = ru.typeOf[Iterable[_]].typeSymbol
  val optionSymbol = ru.typeOf[Option[_]].typeSymbol
  
  override def extractParam[T:TypeTag](paramName:String, request:HttpRequest):Option[T] = {
    val values = request.parameters.get(paramName)
    if(values.isDefined) {
      if(isIterable[T]) {
        extractIterable(values.get)
      } else if (isOption[T]) {
        extractOption(values.get)
      } else {
        extractOther(values.get)
      }
    } else {
      Option.empty
    }
  }
  
  private def isIterable[T:TypeTag](implicit ttag:TypeTag[T]) = {
    ttag.tpe.baseClasses.contains(iterableSymbol)
  }
  
  private def isOption[T:TypeTag](implicit ttag:TypeTag[T]) = {
    ttag.tpe.typeSymbol == optionSymbol
  }
  
  private def extractIterable[T:TypeTag](values:List[String]):Option[T] = {
    val arg = extractFirstArg[T]
    if(typeTransformer.supports(arg)) {
      Option.apply(values.map(typeTransformer.transform(_, arg).get).asInstanceOf[T])
    } else {
      throw new IllegalValueException(values.toString)
    }
  }
  
  private def extractOption[T:TypeTag](values:List[String]):Option[T] = {
    validateOnlyOneValue(values)
    val arg = extractFirstArg[T]
    if(typeTransformer.supports(arg)) {
      Option.apply(typeTransformer.transform(values(0), arg).asInstanceOf[T])
    } else {
    	throw new IllegalValueException(values(0))
    }
  }
  
  private def extractOther[T:TypeTag](values:List[String]):Option[T] = {
    validateOnlyOneValue(values)
    if(typeTransformer.supports[T]) {
      typeTransformer.transform[T](values(0))
    } else {
    	throw new IllegalValueException(values(0))
    }
  }
  
  private def extractFirstArg[T](implicit ttag: TypeTag[T]) = {
    val TypeRef(_,_,args) = ttag.tpe
    args(0)
  }
  
  private def validateOnlyOneValue(values:List[String]):Unit = {
    if(values.length != 1) {
      throw new IllegalValueException(values.toString)
    }
  }
  
}

object ParamExtractor {
  def defaultUrlParamExtractor(pathTemplate:String): ParamExtractor =
    new UrlParamExtractor(pathTemplate, new TypeTransformer)
  
  def defaultQueryParamExtractor(): ParamExtractor = new QueryParamExtractor(new TypeTransformer)
}