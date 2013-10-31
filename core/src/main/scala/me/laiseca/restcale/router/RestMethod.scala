package me.laiseca.restcale.router

import scala.reflect.runtime.universe.MethodSymbol
import me.laiseca.restcale.api.RestService
import me.laiseca.restcale.internal.function.Argument

case class RestMethod(val obj:RestService, val method:MethodSymbol, val arguments:List[Argument],
    val httpMethod:String, val template:String, val route:List[RouteSegment])