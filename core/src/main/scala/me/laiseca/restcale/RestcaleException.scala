package me.laiseca.restcale

class RestcaleException(message:String, cause:Throwable, val code:Int) extends Exception(message, cause) {
  def this(message:String, code:Int) = this(message, null, code)
}