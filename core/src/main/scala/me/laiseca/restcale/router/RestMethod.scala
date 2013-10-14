package me.laiseca.restcale.router

import scala.reflect.runtime.universe.MethodSymbol

import me.laiseca.restcale.api.RestService

case class RestMethod(obj:RestService, method:MethodSymbol, 
    httpMethod:String, route:List[RouteSegment])