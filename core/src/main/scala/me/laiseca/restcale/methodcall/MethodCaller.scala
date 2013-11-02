package me.laiseca.restcale.methodcall

import me.laiseca.restcale.router.RestMethod
import me.laiseca.restcale.http.HttpRequest
import scala.reflect.runtime.{universe => ru}
import scala.reflect.runtime.universe._
import me.laiseca.restcale.api.RestService
import me.laiseca.restcale.internal.function.Argument

trait MethodCaller {
  def call(method: RestMethod, request:HttpRequest):Any
}

class DefaultMethodCaller(factory:ParamExtractorFactory) extends MethodCaller {
  
  def this() = this(new DefaultParamExtractorFactory)
  
  def call(method: RestMethod, request:HttpRequest):Any = {
    val extractor = factory.create(method.httpMethod, method.template)
    val function = call(method.obj, method.method)
    val functionMethod = extractFunctionMethod(function)
    val parameters = extractParameters(method.arguments, extractor, request)
    call(function, functionMethod, parameters:_*)
  }
  
  private def extractParameters(arguments: List[Argument],
      extractor:ParamExtractor, request:HttpRequest):List[Any] = {
    for(argument <- arguments) yield {
      extractor.extractParam(argument, request)
    }
  }
  
  private def call(obj:Any, method:MethodSymbol, parameters: Any*):Any = {
    val mirror = ru.runtimeMirror(obj.getClass.getClassLoader)
    val instanceMirror = mirror.reflect(obj)
    val methodMirror = instanceMirror.reflectMethod(method)
    methodMirror.apply(parameters:_*)
  }
  
  private def extractFunctionMethod(function:Any):MethodSymbol = {
    val mirror = ru.runtimeMirror(function.getClass.getClassLoader)
    val instanceMirror = mirror.reflect(function)
    instanceMirror.symbol.toType.member(newTermName("apply")).asMethod
  }
  
}