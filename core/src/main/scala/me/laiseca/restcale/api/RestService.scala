package me.laiseca.restcale.api

import language.experimental.macros
import me.laiseca.restcale.macros.Macros

trait RestService {
  
  def GET[R](path:String)(f: => R) = macro Macros.get[R]
  
  def POST[R](path:String)(f: => R) = macro Macros.post[R]
  
  def PUT[R](path:String)(f: => R) = macro Macros.put[R]
  
  def DELETE[R](path:String)(f: => R) = macro Macros.delete[R]
  
}
