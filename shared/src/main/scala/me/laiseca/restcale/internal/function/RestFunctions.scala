package me.laiseca.restcale.internal.function

case class Argument(val tpe:String, name:String)

abstract class BaseRestFunction(val httpMethod:String, val path:String, val args:List[Argument])

class RestFunction0[R](val f:() => R, method:String, path:String, args:List[Argument])
    extends BaseRestFunction(method, path, args) {
  def apply(): R = {
    f.apply()
  }
}

class RestFunction1[T, R](val f:(T) => R, method:String, path:String, args:List[Argument])
    extends BaseRestFunction(method, path, args) {
  def apply(t:T):R = {
    f.apply(t)
  }
}

class RestFunction2[T1, T2, R](val f:(T1, T2) => R, method:String, path:String, args:List[Argument]) 
    extends BaseRestFunction(method, path, args) {
  def apply(t1:T1, t2:T2):R = {
    f.apply(t1, t2)
  }
}

class RestFunction3[T1, T2, T3, R](val f:(T1, T2, T3) => R, method:String, path:String, args:List[Argument]) 
    extends BaseRestFunction(method, path, args) {
  def apply(t1:T1, t2:T2, t3:T3):R = {
    f.apply(t1, t2, t3)
  }
}

class RestFunction4[T1, T2, T3, T4, R](val f:(T1, T2, T3, T4) => R, method:String, path:String, args:List[Argument]) 
    extends BaseRestFunction(method, path, args) {
  def apply(t1:T1, t2:T2, t3:T3, t4:T4):R = {
    f.apply(t1, t2, t3, t4)
  }
}

class RestFunction5[T1, T2, T3, T4, T5, R](val f:(T1, T2, T3, T4, T5) => R, method:String, path:String, args:List[Argument]) 
    extends BaseRestFunction(method, path, args) {
  def apply(t1:T1, t2:T2, t3:T3, t4:T4, t5:T5):R = {
    f.apply(t1, t2, t3, t4, t5)
  }
}

class RestFunction6[T1, T2, T3, T4, T5, T6, R](val f:(T1, T2, T3, T4, T5, T6) => R, method:String, path:String, args:List[Argument]) 
    extends BaseRestFunction(method, path, args) {
  def apply(t1:T1, t2:T2, t3:T3, t4:T4, t5:T5, t6:T6):R = {
    f.apply(t1, t2, t3, t4, t5, t6)
  }
}

class RestFunction7[T1, T2, T3, T4, T5, T6, T7, R](val f:(T1, T2, T3, T4, T5, T6, T7) => R,
      method:String, path:String, args:List[Argument]) extends BaseRestFunction(method, path, args) {
  def apply(t1:T1, t2:T2, t3:T3, t4:T4, t5:T5, t6:T6, t7:T7):R = {
    f.apply(t1, t2, t3, t4, t5, t6, t7)
  }
}

class RestFunction8[T1, T2, T3, T4, T5, T6, T7, T8, R](val f:(T1, T2, T3, T4, T5, T6, T7, T8) => R,
      method:String, path:String, args:List[Argument]) extends BaseRestFunction(method, path, args) {
  def apply(t1:T1, t2:T2, t3:T3, t4:T4, t5:T5, t6:T6, t7:T7, t8:T8):R = {
    f.apply(t1, t2, t3, t4, t5, t6, t7, t8)
  }
}

class RestFunction9[T1, T2, T3, T4, T5, T6, T7, T8, T9, R](val f:(T1, T2, T3, T4, T5, T6, T7, T8, T9) => R,
      method:String, path:String, args:List[Argument]) extends BaseRestFunction(method, path, args) {
  def apply(t1:T1, t2:T2, t3:T3, t4:T4, t5:T5, t6:T6, t7:T7, t8:T8, t9:T9):R = {
    f.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9)
  }
}

class RestFunction10[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, R](val f:(T1, T2, T3, T4, T5, T6, T7, T8, T9, T10) => R,
      method:String, path:String, args:List[Argument]) extends BaseRestFunction(method, path, args) {
  def apply(t1:T1, t2:T2, t3:T3, t4:T4, t5:T5, t6:T6, t7:T7, t8:T8, t9:T9, t10:T10):R = {
    f.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10)
  }
}

class RestFunction11[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, R]
    (val f:(T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11) => R, method:String, path:String, args:List[Argument])
    extends BaseRestFunction(method, path, args)  {
  def apply(t1:T1, t2:T2, t3:T3, t4:T4, t5:T5, t6:T6, t7:T7, t8:T8, t9:T9, t10:T10, t11:T11):R = {
    f.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11)
  }
}

class RestFunction12[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, R]
    (val f:(T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12) => R, method:String, path:String, args:List[Argument])
    extends BaseRestFunction(method, path, args) {
  def apply(t1:T1, t2:T2, t3:T3, t4:T4, t5:T5, t6:T6, t7:T7, t8:T8, t9:T9, t10:T10, t11:T11,
      t12:T12):R = {
    f.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12)
  }
}

class RestFunction13[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, R]
    (val f:(T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13) => R, method:String, path:String, args:List[Argument])
    extends BaseRestFunction(method, path, args) {
  def apply(t1:T1, t2:T2, t3:T3, t4:T4, t5:T5, t6:T6, t7:T7, t8:T8, t9:T9, t10:T10, t11:T11,
      t12:T12, t13:T13):R = {
    f.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13)
  }
}

class RestFunction14[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, R]
    (val f:(T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14) => R, method:String, path:String, args:List[Argument])
    extends BaseRestFunction(method, path, args) {
  def apply(t1:T1, t2:T2, t3:T3, t4:T4, t5:T5, t6:T6, t7:T7, t8:T8, t9:T9, t10:T10, t11:T11,
      t12:T12, t13:T13, t14:T14):R = {
    f.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14)
  }
}

class RestFunction15[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, R]
    (val f:(T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15) => R, method:String, path:String, args:List[Argument])
    extends BaseRestFunction(method, path, args) {
  def apply(t1:T1, t2:T2, t3:T3, t4:T4, t5:T5, t6:T6, t7:T7, t8:T8, t9:T9, t10:T10, t11:T11,
      t12:T12, t13:T13, t14:T14, t15:T15):R = {
    f.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15)
  }
}

class RestFunction16[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, R]
    (val f:(T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16) => R, method:String, path:String, args:List[Argument])
    extends BaseRestFunction(method, path, args) {
  def apply(t1:T1, t2:T2, t3:T3, t4:T4, t5:T5, t6:T6, t7:T7, t8:T8, t9:T9, t10:T10, t11:T11,
      t12:T12, t13:T13, t14:T14, t15:T15, t16:T16):R = {
    f.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15, t16)
  }
}

class RestFunction17[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, R]
    (val f:(T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17) => R, method:String, path:String, args:List[Argument])
    extends BaseRestFunction(method, path, args) {
  def apply(t1:T1, t2:T2, t3:T3, t4:T4, t5:T5, t6:T6, t7:T7, t8:T8, t9:T9, t10:T10, t11:T11,
      t12:T12, t13:T13, t14:T14, t15:T15, t16:T16, t17:T17):R = {
    f.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15, t16, t17)
  }
}

class RestFunction18[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, R]
    (val f:(T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18) => R, method:String, path:String, args:List[Argument])
    extends BaseRestFunction(method, path, args) {
  def apply(t1:T1, t2:T2, t3:T3, t4:T4, t5:T5, t6:T6, t7:T7, t8:T8, t9:T9, t10:T10, t11:T11,
      t12:T12, t13:T13, t14:T14, t15:T15, t16:T16, t17:T17, t18:T18):R = {
    f.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15, t16, t17, t18)
  }
}

class RestFunction19[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, R]
    (val f:(T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19) => R, method:String, path:String, args:List[Argument])
    extends BaseRestFunction(method, path, args) {
  def apply(t1:T1, t2:T2, t3:T3, t4:T4, t5:T5, t6:T6, t7:T7, t8:T8, t9:T9, t10:T10, t11:T11,
      t12:T12, t13:T13, t14:T14, t15:T15, t16:T16, t17:T17, t18:T18, t19:T19):R = {
    f.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15, t16, t17, t18, t19)
  }
}

class RestFunction20[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, R]
    (val f:(T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20) => R, method:String, path:String, args:List[Argument])
    extends BaseRestFunction(method, path, args) {
  def apply(t1:T1, t2:T2, t3:T3, t4:T4, t5:T5, t6:T6, t7:T7, t8:T8, t9:T9, t10:T10, t11:T11,
      t12:T12, t13:T13, t14:T14, t15:T15, t16:T16, t17:T17, t18:T18, t19:T19, t20:T20):R = {
    f.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15, t16, t17, t18, t19, t20)
  }
}

class RestFunction21[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, R]
    (val f:(T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21) => R, method:String, path:String, args:List[Argument])
    extends BaseRestFunction(method, path, args) {
  def apply(t1:T1, t2:T2, t3:T3, t4:T4, t5:T5, t6:T6, t7:T7, t8:T8, t9:T9, t10:T10, t11:T11,
      t12:T12, t13:T13, t14:T14, t15:T15, t16:T16, t17:T17, t18:T18, t19:T19, t20:T20, t21:T21):R = {
    f.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15, t16, t17, t18, t19, t20, t21)
  }
}

class RestFunction22[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, R]
    (val f:(T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22) => R, method:String, path:String, args:List[Argument])
    extends BaseRestFunction(method, path, args) {
  def apply(t1:T1, t2:T2, t3:T3, t4:T4, t5:T5, t6:T6, t7:T7, t8:T8, t9:T9, t10:T10, t11:T11,
      t12:T12, t13:T13, t14:T14, t15:T15, t16:T16, t17:T17, t18:T18, t19:T19, t20:T20, t21:T21, t22:T22):R = {
    f.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15, t16, t17, t18, t19, t20, t21, t22)
  }
}