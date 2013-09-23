package me.laiseca.restcale.function

import scala.reflect.runtime.{universe => ru}
import scala.reflect.runtime.universe._
import me.laiseca.restcale.http.HttpMethod

import scala.reflect.macros.Context
import scala.language.experimental.macros

abstract class BaseRestFunction(val method:HttpMethod, val path:String) {
  
//  Macros.init(this, method, path)

  
  
//  private def methodParameters() = {
//    val args = paramInfo
//    args.init
//  }
  
//  private def paramInfo[T:ru.TypeTag]() = {
//	def toClass(tpe: Type):Class[_] = {
//	  val m = runtimeMirror(getClass.getClassLoader)
//	  m.runtimeClass(tpe)
//	}
//    ru.typeOf[T] match { case ru.TypeRef(_, _, args) => args map toClass }
//  }
}
