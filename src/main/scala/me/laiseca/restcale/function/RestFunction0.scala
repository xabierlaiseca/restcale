package me.laiseca.restcale.function

import me.laiseca.restcale.http.HttpMethod

class RestFunction0[R](val f:() => R, method:HttpMethod, path:String)
		extends BaseRestFunction(method, path) with (() => R) {
  
	def apply(): R = {
	  f.apply()
	}

}