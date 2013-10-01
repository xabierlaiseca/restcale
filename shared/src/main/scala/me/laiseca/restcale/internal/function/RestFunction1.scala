package me.laiseca.restcale.internal.function

class RestFunction1[T, R](val f:(T) => R, method:String, path:String)
		extends BaseRestFunction(method, path) {
  
	def apply(t:T):R = {
	  f.apply(t)
	}
	
}