package me.laiseca.restcale.methodcall

import me.laiseca.restcale.router.RestMethod
import io.netty.handler.codec.http.HttpRequest

trait MethodCaller {
  def call(method: RestMethod, request:HttpRequest):Any
}