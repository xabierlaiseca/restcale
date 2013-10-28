package me.laiseca.restcale.router

import scala.reflect.runtime.universe.MethodSymbol

import me.laiseca.restcale.api.RestService

case class RestMethod(val obj:RestService, val method:MethodSymbol, 
    val httpMethod:String, val template:String, val route:List[RouteSegment])