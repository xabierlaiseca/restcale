package me.laiseca.restcale.api

import language.experimental.macros
import me.laiseca.restcale.macros.RestServiceMacros

trait RestService {
  
  def DELETE[R](path:String)(f: => R) = macro RestServiceMacros.delete[R]
  def GET[R](path:String)(f: => R) = macro RestServiceMacros.get[R]
  def PATCH[R](path:String)(f: => R) = macro RestServiceMacros.patch[R]
  def POST[R](path:String)(f: => R) = macro RestServiceMacros.post[R]
  def PUT[R](path:String)(f: => R) = macro RestServiceMacros.put[R]
  
}
