package me.laiseca.scala.netty.app.function

import scala.runtime.AbstractFunction0
import me.laiseca.scala.netty.app.http.HttpMethod

class RestFunction0[R](val f:() => R, method:HttpMethod, path:String)
		extends BaseRestFunction(method, path) with (() => R) {
  
	def apply(): R = {
	  f.apply()
	}

}