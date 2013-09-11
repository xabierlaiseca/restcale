package me.laiseca.scala.netty.app.function

import me.laiseca.scala.netty.app.http.HttpMethod

abstract class BaseRestFunction(val method:HttpMethod, val path:String)