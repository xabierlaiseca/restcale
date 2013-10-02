package me.laiseca.restcale.api

import language.experimental.macros
import me.laiseca.restcale.macros.Macros

trait RestService {
  
  def DELETE[R](path:String)(f: => R) = macro Macros.delete[R]
  def GET[R](path:String)(f: => R) = macro Macros.get[R]
  def PATCH[R](path:String)(f: => R) = macro Macros.patch[R]
  def POST[R](path:String)(f: => R) = macro Macros.post[R]
  def PUT[R](path:String)(f: => R) = macro Macros.put[R]
  
}
