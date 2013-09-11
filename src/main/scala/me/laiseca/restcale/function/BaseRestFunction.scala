package me.laiseca.restcale.function

import me.laiseca.restcale.http.HttpMethod

abstract class BaseRestFunction(val method:HttpMethod, val path:String)