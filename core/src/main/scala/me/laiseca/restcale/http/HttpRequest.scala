package me.laiseca.restcale.http

import scala.collection.JavaConversions._
import io.netty.handler.codec.http.QueryStringDecoder
import io.netty.handler.codec.http.HttpHeaders

class HttpRequest(request: io.netty.handler.codec.http.HttpRequest) {
  private val decoder = new QueryStringDecoder(request.getUri())
    
  val headers = toImmutableHeaders(request.headers)
  val method = request.getMethod.name.toUpperCase
  val path = decoder.path
  val parameters = toImmutableParameters(decoder.parameters)
  
  private def toImmutableHeaders(headers: HttpHeaders) = {
    headers.map(entry => (entry.getKey(), entry.getValue())).toMap
  }
  
  private def toImmutableParameters(parameters: java.util.Map[String, java.util.List[String]]) = {
    parameters.map(entry => (entry._1, entry._2.toList)).toMap
  }
}
