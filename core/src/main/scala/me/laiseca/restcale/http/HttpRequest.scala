package me.laiseca.restcale.http

import io.netty.handler.codec.http.QueryStringDecoder

class HttpRequest(request: io.netty.handler.codec.http.HttpRequest) {
  private val decoder = new QueryStringDecoder(request.getUri())
    
  def headers = request.headers
  def method = request.getMethod.name.toUpperCase
  def path = decoder.path
  def parameters = decoder.parameters
}
