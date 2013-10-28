package me.laiseca.restcale.methodcall

import scala.reflect.runtime.{universe => ru}
import scala.reflect.runtime.universe._
import me.laiseca.restcale.util.PathUtils
import me.laiseca.restcale.http.HttpRequest

trait ParamExtractor {
  def extractParam(tpe:Type, paramName:String, request:HttpRequest):Option[Any]
}

class UrlParamExtractor(val pathTemplate:String, typeTransformer:TypeTransformer) extends ParamExtractor {
  override def extractParam(tpe: Type, paramName:String, request:HttpRequest):Option[Any] = {
    val paramStringValue = extractParamStringValue(request.path, paramName)
    if(paramStringValue.isDefined) {
      if(typeTransformer.supports(tpe)) {
        return typeTransformer.transform(paramStringValue.get, tpe)
      } else {
        throw new IllegalParameterTypeException(paramStringValue.get)
      }
    } else {
    	Option.empty
    }
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
  
  override def extractParam(tpe: Type, paramName:String, request:HttpRequest):Option[Any] = {
    val values = request.parameters.get(paramName)
    if(values.isDefined) {
      if(isIterable(tpe)) {
        extractIterable(tpe, values.get)
      } else if (isOption(tpe)) {
        extractOption(tpe, values.get)
      } else {
        extractOther(tpe, values.get)
      }
    } else {
      Option.empty
    }
  }
  
  private def isIterable(tpe:Type) = {
    tpe.baseClasses.contains(iterableSymbol)
  }
  
  private def isOption(tpe:Type) = {
    tpe.typeSymbol == optionSymbol
  }
  
  private def extractIterable(tpe:Type, values:List[String]):Option[Any] = {
    val arg = extractFirstArg(tpe)
    if(typeTransformer.supports(arg)) {
      Option.apply(values.map(typeTransformer.transform(_, arg).get))
    } else {
      throw new IllegalParameterTypeException(values.toString)
    }
  }
  
  private def extractOption(tpe:Type, values:List[String]):Option[Any] = {
    validateOnlyOneValue(values)
    val arg = extractFirstArg(tpe)
    if(typeTransformer.supports(arg)) {
      Option.apply(typeTransformer.transform(values(0), arg))
    } else {
    	throw new IllegalParameterTypeException(values(0))
    }
  }
  
  private def extractOther(tpe:Type, values:List[String]):Option[Any] = {
    validateOnlyOneValue(values)
    if(typeTransformer.supports(tpe)) {
      typeTransformer.transform(values(0), tpe)
    } else {
    	throw new IllegalParameterTypeException(values(0))
    }
  }
  
  private def extractFirstArg(tpe:Type) = {
    val TypeRef(_,_,args) = tpe
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