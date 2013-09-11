package me.laiseca.restcale.function

import me.laiseca.restcale.http.HttpMethod

class RestFunction22[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, R]
		(val f:(T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22) => R, method:HttpMethod, path:String)
		extends BaseRestFunction(method, path) 
		with ((T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22) => R) {
  
	def apply(t1:T1, t2:T2, t3:T3, t4:T4, t5:T5, t6:T6, t7:T7, t8:T8, t9:T9, t10:T10, t11:T11,
	    t12:T12, t13:T13, t14:T14, t15:T15, t16:T16, t17:T17, t18:T18, t19:T19, t20:T20, t21:T21, t22:T22):R = {
	  f.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15, t16, t17, t18, t19, t20, t21, t22)
	}

}