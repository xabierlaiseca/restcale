package me.laiseca.restcale.internal.function

import me.laiseca.restcale.http.HttpMethod

class RestFunction2[T1, T2, R](val f:(T1, T2) => R, method:String, path:String) 
		extends BaseRestFunction(method, path) {
  
	def apply(t1:T1, t2:T2):R = {
	  f.apply(t1, t2)
	}

}