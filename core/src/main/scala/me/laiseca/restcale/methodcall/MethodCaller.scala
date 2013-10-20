package me.laiseca.restcale.methodcall

import me.laiseca.restcale.router.RestMethod
import me.laiseca.restcale.http.HttpRequest

trait MethodCaller {
  def call(method: RestMethod, request:HttpRequest):Any
}
