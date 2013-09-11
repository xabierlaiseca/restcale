package me.laiseca.scala.netty.app.function

import me.laiseca.scala.netty.app.http.HttpMethod

class RestFunction1[T, R](val f:(T) => R, method:HttpMethod, path:String)
		extends BaseRestFunction(method, path) with ((T) => R) {
  
	def apply(t:T):R = {
	  f.apply(t)
	}
	
}