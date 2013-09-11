package me.laiseca.scala.netty.app.function

import me.laiseca.scala.netty.app.http.HttpMethod

class RestFunction7[T1, T2, T3, T4, T5, T6, T7, R](val f:(T1, T2, T3, T4, T5, T6, T7) => R,
    	method:HttpMethod, path:String) extends BaseRestFunction(method, path) 
		with ((T1, T2, T3, T4, T5, T6, T7) => R) {
  
	def apply(t1:T1, t2:T2, t3:T3, t4:T4, t5:T5, t6:T6, t7:T7):R = {
	  f.apply(t1, t2, t3, t4, t5, t6, t7)
	}

}