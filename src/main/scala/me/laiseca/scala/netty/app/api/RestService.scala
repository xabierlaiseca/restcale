package me.laiseca.scala.netty.app.api

import me.laiseca.scala.netty.app.function.RestFunction0
import me.laiseca.scala.netty.app.function.RestFunction1
import me.laiseca.scala.netty.app.function.RestFunction10
import me.laiseca.scala.netty.app.function.RestFunction11
import me.laiseca.scala.netty.app.function.RestFunction12
import me.laiseca.scala.netty.app.function.RestFunction13
import me.laiseca.scala.netty.app.function.RestFunction14
import me.laiseca.scala.netty.app.function.RestFunction15
import me.laiseca.scala.netty.app.function.RestFunction16
import me.laiseca.scala.netty.app.function.RestFunction17
import me.laiseca.scala.netty.app.function.RestFunction18
import me.laiseca.scala.netty.app.function.RestFunction19
import me.laiseca.scala.netty.app.function.RestFunction2
import me.laiseca.scala.netty.app.function.RestFunction20
import me.laiseca.scala.netty.app.function.RestFunction21
import me.laiseca.scala.netty.app.function.RestFunction22
import me.laiseca.scala.netty.app.function.RestFunction3
import me.laiseca.scala.netty.app.function.RestFunction4
import me.laiseca.scala.netty.app.function.RestFunction5
import me.laiseca.scala.netty.app.function.RestFunction6
import me.laiseca.scala.netty.app.function.RestFunction7
import me.laiseca.scala.netty.app.function.RestFunction8
import me.laiseca.scala.netty.app.function.RestFunction9
import me.laiseca.scala.netty.app.http.DELETE_METHOD
import me.laiseca.scala.netty.app.http.GET_METHOD
import me.laiseca.scala.netty.app.http.HttpMethod
import me.laiseca.scala.netty.app.http.POST_METHOD
import me.laiseca.scala.netty.app.http.PUT_METHOD

trait RestService {
  def GET[R](path:String)(f: => R):R = {
    wrap(f, GET_METHOD, path)
  }
  
  def POST[R](path:String)(f: => R):R = {
    wrap(f, POST_METHOD, path)
  }
  
  def PUT[R](path:String)(f: => R):R = {
    wrap(f, PUT_METHOD, path)
  }
  
  def DELETE[R](path:String)(f: => R):R = {
    wrap(f, DELETE_METHOD, path)
  }
  
  private def wrap[R](f: => R, method: HttpMethod, path:String):R = {
    val wrapper = f match {
      case f0:Function0[r] => new RestFunction0[r](f0, method, path)
      case f1:Function1[t,r] => new RestFunction1[t,r](f1, method, path)
      case f2:Function2[t1,t2,r] => new RestFunction2[t1,t2,r](f2, method, path)
      case f3:Function3[t1,t2,t3,r] => new RestFunction3[t1,t2,t3,r](f3, method, path)
      case f4:Function4[t1,t2,t3,t4,r] => new RestFunction4[t1,t2,t3,t4,r](f4, method, path)
      case f5:Function5[t1,t2,t3,t4,t5,r] => new RestFunction5[t1,t2,t3,t4,t5,r](f5, method, path)
      case f6:Function6[t1,t2,t3,t4,t5,t6,r] =>
        new RestFunction6[t1,t2,t3,t4,t5,t6,r](f6, method, path)
      case f7:Function7[t1,t2,t3,t4,t5,t6,t7,r] =>
        new RestFunction7[t1,t2,t3,t4,t5,t6,t7,r](f7, method, path)
      case f8:Function8[t1,t2,t3,t4,t5,t6,t7,t8,r] => 
        new RestFunction8[t1,t2,t3,t4,t5,t6,t7,t8,r](f8, method, path)
      case f9:Function9[t1,t2,t3,t4,t5,t6,t7,t8,t9,r] =>
        new RestFunction9[t1,t2,t3,t4,t5,t6,t7,t8,t9,r](f9, method, path)
      case f10:Function10[t1,t2,t3,t4,t5,t6,t7,t8,t9,t10,r] =>
        new RestFunction10[t1,t2,t3,t4,t5,t6,t7,t8,t9,t10,r](f10, method, path)
      case f11:Function11[t1,t2,t3,t4,t5,t6,t7,t8,t9,t10,t11,r] =>
        new RestFunction11[t1,t2,t3,t4,t5,t6,t7,t8,t9,t10,t11,r](f11, method, path)
      case f12:Function12[t1,t2,t3,t4,t5,t6,t7,t8,t9,t10,t11,t12,r] =>
        new RestFunction12[t1,t2,t3,t4,t5,t6,t7,t8,t9,t10,t11,t12,r](f12, method, path)
      case f13:Function13[t1,t2,t3,t4,t5,t6,t7,t8,t9,t10,t11,t12,t13,r] =>
        new RestFunction13[t1,t2,t3,t4,t5,t6,t7,t8,t9,t10,t11,t12,t13,r](f13, method, path)
      case f14:Function14[t1,t2,t3,t4,t5,t6,t7,t8,t9,t10,t11,t12,t13,t14,r] =>
        new RestFunction14[t1,t2,t3,t4,t5,t6,t7,t8,t9,t10,t11,t12,t13,t14,r](f14, method, path)
      case f15:Function15[t1,t2,t3,t4,t5,t6,t7,t8,t9,t10,t11,t12,t13,t14,t15,r] =>
        new RestFunction15[t1,t2,t3,t4,t5,t6,t7,t8,t9,t10,t11,t12,t13,t14,t15,r](f15, method, path)
      case f16:Function16[t1,t2,t3,t4,t5,t6,t7,t8,t9,t10,t11,t12,t13,t14,t15,t16,r] =>
        new RestFunction16[t1,t2,t3,t4,t5,t6,t7,t8,t9,t10,t11,t12,t13,t14,t15,t16,r](f16, method, path)
      case f17:Function17[t1,t2,t3,t4,t5,t6,t7,t8,t9,t10,t11,t12,t13,t14,t15,t16,t17,r] =>
        new RestFunction17[t1,t2,t3,t4,t5,t6,t7,t8,t9,t10,t11,t12,t13,t14,t15,t16,t17,r](f17, method, path)
      case f18:Function18[t1,t2,t3,t4,t5,t6,t7,t8,t9,t10,t11,t12,t13,t14,t15,t16,t17,t18,r] =>
        new RestFunction18[t1,t2,t3,t4,t5,t6,t7,t8,t9,t10,t11,t12,t13,t14,t15,t16,t17,t18,r](f18, method, path)
      case f19:Function19[t1,t2,t3,t4,t5,t6,t7,t8,t9,t10,t11,t12,t13,t14,t15,t16,t17,t18,t19,r] =>
        new RestFunction19[t1,t2,t3,t4,t5,t6,t7,t8,t9,t10,t11,t12,t13,t14,t15,t16,t17,t18,t19,r](f19, method, path)
      case f20:Function20[t1,t2,t3,t4,t5,t6,t7,t8,t9,t10,t11,t12,t13,t14,t15,t16,t17,t18,t19,t20,r] =>
        new RestFunction20[t1,t2,t3,t4,t5,t6,t7,t8,t9,t10,t11,t12,t13,t14,t15,t16,t17,t18,t19,t20,r](f20, method, path)
      case f21:Function21[t1,t2,t3,t4,t5,t6,t7,t8,t9,t10,t11,t12,t13,t14,t15,t16,t17,t18,t19,t20,t21,r] =>
        new RestFunction21[t1,t2,t3,t4,t5,t6,t7,t8,t9,t10,t11,t12,t13,t14,t15,t16,t17,t18,t19,t20,t21,r](f21, method, path)
      case f22:Function22[t1,t2,t3,t4,t5,t6,t7,t8,t9,t10,t11,t12,t13,t14,t15,t16,t17,t18,t19,t20,t21,t22,r] =>
        new RestFunction22[t1,t2,t3,t4,t5,t6,t7,t8,t9,t10,t11,t12,t13,t14,t15,t16,t17,t18,t19,t20,t21,t22,r](f22, method, path)
    }
    
    wrapper.asInstanceOf[R]
  }
}