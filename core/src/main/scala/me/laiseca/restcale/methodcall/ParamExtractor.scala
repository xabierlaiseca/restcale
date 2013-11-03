package me.laiseca.restcale.methodcall

import scala.reflect.runtime.{universe => ru}
import scala.reflect.runtime.universe._
import me.laiseca.restcale.util.PathUtils
import me.laiseca.restcale.http.HttpRequest
import me.laiseca.restcale.internal.function.Argument
import me.laiseca.restcale.internal.function.TypeParameter

trait ParamExtractor {
  def extractParam(arg:Argument, request:HttpRequest):Any
}

class DefaultParamExtractor(httpMethod: String, pathTemplate: String) extends ParamExtractor {
  private val typeTransformer = new TypeTransformer
  private val extractors = List(new UrlParamExtractor(pathTemplate, typeTransformer), 
        new QueryParamExtractor(typeTransformer))
  private val mirror = ru.runtimeMirror(getClass.getClassLoader)
 
  private def buildType(arg:Argument):Type = {
    def typeOf(tpeName:String):Type = {
      val clazz = Class.forName(tpeName)
      val classSymbol = mirror.classSymbol(clazz)
      classSymbol.toType
    }
    
    def buildTypeParam(typeParam: TypeParameter):Type = {
      val TypeRef(p, s, _) = typeOf(typeParam.tpe)
      TypeRef(p, s, typeParam.typeParams.map(subTypeParam => buildTypeParam(subTypeParam)))
    }
    
    val TypeRef(pre, sym, _) = typeOf(arg.tpe)
    val sub = arg.tpeParams.map(tpeParam => buildTypeParam(tpeParam))
    TypeRef(pre, sym, sub)
  }
  
  override def extractParam(arg:Argument, request:HttpRequest):Any = {
    val tpe = buildType(arg)
      
    for(extractor <- extractors) {
      val value = extractor.extractParam(tpe, arg.name, request)
      if(value.isDefined) {
        return value.get
      }
    }
    
    if(tpe.typeSymbol == typeOf[Option[_]].typeSymbol) {
      Option.empty
    } else {
    	throw new RuntimeException("Replace me!")
    }
    
  }
}

trait InternalParamExtractor {
  def extractParam(tpe:Type, paramName:String, request:HttpRequest):Option[Any]
}

class UrlParamExtractor(val pathTemplate:String, typeTransformer:TypeTransformer) extends InternalParamExtractor {
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

class QueryParamExtractor(typeTransformer:TypeTransformer) extends InternalParamExtractor {
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
    println(showRaw(values))
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

//class BodyParamExtractor extends InternalParamExtractor {
//  import org.json4s._
//  import org.json4s.JsonDSL._
//  import org.json4s.native.JsonMethods._
//
//  override def extractParam(tpe: Type, paramName:String, request:HttpRequest):Option[Any] = {
//    request.method match {
//      case "PATCH" | "POST" | "PUT" => extractParam(request) 
//      case _ => Option.empty
//    }
//  }
//  
//  private def extractParam(request:HttpRequest): Option[Any] = {
//    val parsed = parse(request.body)
//    native.Serialization.
//    Option.empty
//  }
//}