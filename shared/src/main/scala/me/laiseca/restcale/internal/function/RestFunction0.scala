package me.laiseca.restcale.internal.function

class RestFunction0[R](val f:() => R, method:String, path:String)
		extends BaseRestFunction(method, path) {
  
	def apply(): R = {
	  f.apply()
	}

}