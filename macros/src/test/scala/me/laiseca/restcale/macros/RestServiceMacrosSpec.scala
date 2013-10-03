package me.laiseca.restcale.macros

import language.experimental.macros
import org.scalatest.Matchers
import org.scalatest.FlatSpec
import me.laiseca.restcale.internal.function.RestFunction0
import me.laiseca.restcale.internal.function.RestFunction1
import me.laiseca.restcale.internal.function.RestFunction2
import me.laiseca.restcale.internal.function.RestFunction3
import me.laiseca.restcale.internal.function.RestFunction4
import me.laiseca.restcale.internal.function.RestFunction5
import me.laiseca.restcale.internal.function.RestFunction6
import me.laiseca.restcale.internal.function.RestFunction7
import me.laiseca.restcale.internal.function.RestFunction8
import me.laiseca.restcale.internal.function.RestFunction9
import me.laiseca.restcale.internal.function.RestFunction10
import me.laiseca.restcale.internal.function.RestFunction11
import me.laiseca.restcale.internal.function.RestFunction12
import me.laiseca.restcale.internal.function.RestFunction13
import me.laiseca.restcale.internal.function.RestFunction14
import me.laiseca.restcale.internal.function.RestFunction15
import me.laiseca.restcale.internal.function.RestFunction16
import me.laiseca.restcale.internal.function.RestFunction17
import me.laiseca.restcale.internal.function.RestFunction18
import me.laiseca.restcale.internal.function.RestFunction19
import me.laiseca.restcale.internal.function.RestFunction20
import me.laiseca.restcale.internal.function.RestFunction21
import me.laiseca.restcale.internal.function.RestFunction22
import me.laiseca.restcale.http.HttpMethod
import scala.reflect.runtime.{universe => ru}
import scala.reflect.runtime.universe._

class RestServiceMacrosSpec extends FlatSpec with Matchers {
  private def delete[A](path:String)(f: => A) = macro RestServiceMacros.delete[A]
  private def get[A](path:String)(f: => A) = macro RestServiceMacros.get[A]
  private def patch[A](path:String)(f: => A) = macro RestServiceMacros.patch[A]
  private def post[A](path:String)(f: => A) = macro RestServiceMacros.post[A]
  private def put[A](path:String)(f: => A) = macro RestServiceMacros.put[A]
  
  "the macro with 0 parameters function" should "create a RestFunction0 with the given data" in {
    def method = get(RestServiceMacrosSpec.PATH) { () =>
      new R()
    }
    
    assertResult(classOf[RestFunction0[_]])(method.getClass())
    assertResult(RestServiceMacrosSpec.PATH)(method.path)
    assertResult(List(classOf[R]))(argsInfo(method))
  }
  
  "the macro with 1 parameter function" should "create a RestFunction1 with the given data" in {
    def method = get(RestServiceMacrosSpec.PATH) { (t1:T1) =>
      new R()
    }
    
    assertResult(classOf[RestFunction1[_,_]])(method.getClass())
    assertResult(RestServiceMacrosSpec.PATH)(method.path)
    assertResult(List(classOf[T1], classOf[R]))(argsInfo(method))
  }
    
  "the macro with 2 parameters function" should "create a RestFunction2 with the given data" in {
    def method = get(RestServiceMacrosSpec.PATH) { (t1:T1, t2:T2) =>
      new R()
    }
    
    assertResult(classOf[RestFunction2[_,_,_]])(method.getClass())
    assertResult(RestServiceMacrosSpec.PATH)(method.path)
    assertResult(List(classOf[T1], classOf[T2], classOf[R]))(argsInfo(method))
  }
  
  "the macro with 3 parameters function" should "create a RestFunction3 with the given data" in {
    def method = get(RestServiceMacrosSpec.PATH) { (t1:T1, t2:T2, t3:T3) =>
      new R()
    }
    
    assertResult(classOf[RestFunction3[_,_,_,_]])(method.getClass())
    assertResult(RestServiceMacrosSpec.PATH)(method.path)
    assertResult(List(classOf[T1], classOf[T2], classOf[T3], classOf[R]))(argsInfo(method))
  }
  
  "the macro with 4 parameters function" should "create a RestFunction4 with the given data" in {
    def method = get(RestServiceMacrosSpec.PATH) { (t1:T1, t2:T2, t3:T3, t4:T4) =>
      new R()
    }
    
    assertResult(classOf[RestFunction4[_,_,_,_,_]])(method.getClass())
    assertResult(RestServiceMacrosSpec.PATH)(method.path)
    assertResult(List(classOf[T1], classOf[T2], classOf[T3], classOf[T4], classOf[R]))(argsInfo(method))
  }
  
  "the macro with 5 parameters function" should "create a RestFunction5 with the given data" in {
    def method = get(RestServiceMacrosSpec.PATH) { (t1:T1, t2:T2, t3:T3, t4:T4, t5:T5) =>
      new R()
    }
    
    assertResult(classOf[RestFunction5[_,_,_,_,_,_]])(method.getClass())
    assertResult(RestServiceMacrosSpec.PATH)(method.path)
    assertResult(List(classOf[T1], classOf[T2], classOf[T3], classOf[T4], classOf[T5], classOf[R]))(
        argsInfo(method))
  }
  
  "the macro with 6 parameters function" should "create a RestFunction6 with the given data" in {
    def method = get(RestServiceMacrosSpec.PATH) { (t1:T1, t2:T2, t3:T3, t4:T4, t5:T5, t6:T6) =>
      new R()
    }
    
    assertResult(classOf[RestFunction6[_,_,_,_,_,_,_]])(method.getClass())
    assertResult(RestServiceMacrosSpec.PATH)(method.path)
    assertResult(List(classOf[T1], classOf[T2], classOf[T3], classOf[T4], classOf[T5], classOf[T6],
        classOf[R]))(argsInfo(method))
  }
  
  "the macro with 7 parameters function" should "create a RestFunction7 with the given data" in {
    def method = get(RestServiceMacrosSpec.PATH) { (t1:T1, t2:T2, t3:T3, t4:T4, t5:T5, t6:T6,
        t7:T7) =>
      new R()
    }
    
    assertResult(classOf[RestFunction7[_,_,_,_,_,_,_,_]])(method.getClass())
    assertResult(RestServiceMacrosSpec.PATH)(method.path)
    assertResult(List(classOf[T1], classOf[T2], classOf[T3], classOf[T4], classOf[T5], classOf[T6],
        classOf[T7], classOf[R]))(argsInfo(method))
  }
  
  "the macro with 8 parameters function" should "create a RestFunction8 with the given data" in {
    def method = get(RestServiceMacrosSpec.PATH) { (t1:T1, t2:T2, t3:T3, t4:T4, t5:T5, t6:T6,
        t7:T7, t8:T8) =>
      new R()
    }
    
    assertResult(classOf[RestFunction8[_,_,_,_,_,_,_,_,_]])(method.getClass())
    assertResult(RestServiceMacrosSpec.PATH)(method.path)
    assertResult(List(classOf[T1], classOf[T2], classOf[T3], classOf[T4], classOf[T5], classOf[T6],
        classOf[T7], classOf[T8], classOf[R]))(argsInfo(method))
  }
  
  "the macro with 9 parameters function" should "create a RestFunction9 with the given data" in {
    def method = get(RestServiceMacrosSpec.PATH) { (t1:T1, t2:T2, t3:T3, t4:T4, t5:T5, t6:T6,
        t7:T7, t8:T8, t9:T9) =>
      new R()
    }
    
    assertResult(classOf[RestFunction9[_,_,_,_,_,_,_,_,_,_]])(method.getClass())
    assertResult(RestServiceMacrosSpec.PATH)(method.path)
    assertResult(List(classOf[T1], classOf[T2], classOf[T3], classOf[T4], classOf[T5], classOf[T6],
        classOf[T7], classOf[T8], classOf[T9], classOf[R]))(argsInfo(method))
  }
  
  "the macro with 10 parameters function" should "create a RestFunction10 with the given data" in {
    def method = get(RestServiceMacrosSpec.PATH) { (t1:T1, t2:T2, t3:T3, t4:T4, t5:T5, t6:T6,
        t7:T7, t8:T8, t9:T9, t10:T10) =>
      new R()
    }
    
    assertResult(classOf[RestFunction10[_,_,_,_,_,_,_,_,_,_,_]])(method.getClass())
    assertResult(RestServiceMacrosSpec.PATH)(method.path)
    assertResult(List(classOf[T1], classOf[T2], classOf[T3], classOf[T4], classOf[T5], classOf[T6],
        classOf[T7], classOf[T8], classOf[T9], classOf[T10], classOf[R]))(argsInfo(method))
  }
  
  "the macro with 11 parameters function" should "create a RestFunction11 with the given data" in {
    def method = get(RestServiceMacrosSpec.PATH) { (t1:T1, t2:T2, t3:T3, t4:T4, t5:T5, t6:T6,
        t7:T7, t8:T8, t9:T9, t10:T10, t11:T11) =>
      new R()
    }
    
    assertResult(classOf[RestFunction11[_,_,_,_,_,_,_,_,_,_,_,_]])(method.getClass())
    assertResult(RestServiceMacrosSpec.PATH)(method.path)
    assertResult(List(classOf[T1], classOf[T2], classOf[T3], classOf[T4], classOf[T5], classOf[T6],
        classOf[T7], classOf[T8], classOf[T9], classOf[T10], classOf[T11], classOf[R]))(argsInfo(method))
  }
  
  "the macro with 12 parameters function" should "create a RestFunction12 with the given data" in {
    def method = get(RestServiceMacrosSpec.PATH) { (t1:T1, t2:T2, t3:T3, t4:T4, t5:T5, t6:T6,
        t7:T7, t8:T8, t9:T9, t10:T10, t11:T11, t12:T12) =>
      new R()
    }
    
    assertResult(classOf[RestFunction12[_,_,_,_,_,_,_,_,_,_,_,_,_]])(method.getClass())
    assertResult(RestServiceMacrosSpec.PATH)(method.path)
    assertResult(List(classOf[T1], classOf[T2], classOf[T3], classOf[T4], classOf[T5], classOf[T6],
        classOf[T7], classOf[T8], classOf[T9], classOf[T10], classOf[T11], classOf[T12], classOf[R]))(
        argsInfo(method))
  }
  
  "the macro with 13 parameters function" should "create a RestFunction13 with the given data" in {
    def method = get(RestServiceMacrosSpec.PATH) { (t1:T1, t2:T2, t3:T3, t4:T4, t5:T5, t6:T6,
        t7:T7, t8:T8, t9:T9, t10:T10, t11:T11, t12:T12, t13:T13) =>
      new R()
    }
    
    assertResult(classOf[RestFunction13[_,_,_,_,_,_,_,_,_,_,_,_,_,_]])(method.getClass())
    assertResult(RestServiceMacrosSpec.PATH)(method.path)
    assertResult(List(classOf[T1], classOf[T2], classOf[T3], classOf[T4], classOf[T5], classOf[T6],
        classOf[T7], classOf[T8], classOf[T9], classOf[T10], classOf[T11], classOf[T12], classOf[T13],
        classOf[R]))(argsInfo(method))
  }
  
  "the macro with 14 parameters function" should "create a RestFunction14 with the given data" in {
    def method = get(RestServiceMacrosSpec.PATH) { (t1:T1, t2:T2, t3:T3, t4:T4, t5:T5, t6:T6,
        t7:T7, t8:T8, t9:T9, t10:T10, t11:T11, t12:T12, t13:T13, t14:T14) =>
      new R()
    }
    
    assertResult(classOf[RestFunction14[_,_,_,_,_,_,_,_,_,_,_,_,_,_,_]])(method.getClass())
    assertResult(RestServiceMacrosSpec.PATH)(method.path)
    assertResult(List(classOf[T1], classOf[T2], classOf[T3], classOf[T4], classOf[T5], classOf[T6],
        classOf[T7], classOf[T8], classOf[T9], classOf[T10], classOf[T11], classOf[T12], classOf[T13],
        classOf[T14], classOf[R]))(argsInfo(method))
  }

  "the macro with 15 parameters function" should "create a RestFunction15 with the given data" in {
    def method = get(RestServiceMacrosSpec.PATH) { (t1:T1, t2:T2, t3:T3, t4:T4, t5:T5, t6:T6,
        t7:T7, t8:T8, t9:T9, t10:T10, t11:T11, t12:T12, t13:T13, t14:T14, t15:T15) =>
      new R()
    }
    
    assertResult(classOf[RestFunction15[_,_,_,_,_,_,_,_,_,_,_,_,_,_,_,_]])(method.getClass())
    assertResult(RestServiceMacrosSpec.PATH)(method.path)
    assertResult(List(classOf[T1], classOf[T2], classOf[T3], classOf[T4], classOf[T5], classOf[T6],
        classOf[T7], classOf[T8], classOf[T9], classOf[T10], classOf[T11], classOf[T12], classOf[T13],
        classOf[T14], classOf[T15], classOf[R]))(argsInfo(method))
  }
  
  "the macro with 16 parameters function" should "create a RestFunction16 with the given data" in {
    def method = get(RestServiceMacrosSpec.PATH) { (t1:T1, t2:T2, t3:T3, t4:T4, t5:T5, t6:T6,
        t7:T7, t8:T8, t9:T9, t10:T10, t11:T11, t12:T12, t13:T13, t14:T14, t15:T15, t16:T16) =>
      new R()
    }
    
    assertResult(classOf[RestFunction16[_,_,_,_,_,_,_,_,_,_,_,_,_,_,_,_,_]])(method.getClass())
    assertResult(RestServiceMacrosSpec.PATH)(method.path)
    assertResult(List(classOf[T1], classOf[T2], classOf[T3], classOf[T4], classOf[T5], classOf[T6],
        classOf[T7], classOf[T8], classOf[T9], classOf[T10], classOf[T11], classOf[T12], classOf[T13],
        classOf[T14], classOf[T15], classOf[T16], classOf[R]))(argsInfo(method))
  }
  
  "the macro with 17 parameters function" should "create a RestFunction17 with the given data" in {
    def method = get(RestServiceMacrosSpec.PATH) { (t1:T1, t2:T2, t3:T3, t4:T4, t5:T5, t6:T6,
        t7:T7, t8:T8, t9:T9, t10:T10, t11:T11, t12:T12, t13:T13, t14:T14, t15:T15, t16:T16,
        t17:T17) =>
      new R()
    }
    
    assertResult(classOf[RestFunction17[_,_,_,_,_,_,_,_,_,_,_,_,_,_,_,_,_,_]])(method.getClass())
    assertResult(RestServiceMacrosSpec.PATH)(method.path)
    assertResult(List(classOf[T1], classOf[T2], classOf[T3], classOf[T4], classOf[T5], classOf[T6],
        classOf[T7], classOf[T8], classOf[T9], classOf[T10], classOf[T11], classOf[T12], classOf[T13],
        classOf[T14], classOf[T15], classOf[T16], classOf[T17], classOf[R]))(argsInfo(method))
  }
  
  "the macro with 18 parameters function" should "create a RestFunction18 with the given data" in {
    def method = get(RestServiceMacrosSpec.PATH) { (t1:T1, t2:T2, t3:T3, t4:T4, t5:T5, t6:T6,
        t7:T7, t8:T8, t9:T9, t10:T10, t11:T11, t12:T12, t13:T13, t14:T14, t15:T15, t16:T16,
        t17:T17, t18:T18) =>
      new R()
    }
    
    assertResult(classOf[RestFunction18[_,_,_,_,_,_,_,_,_,_,_,_,_,_,_,_,_,_,_]])(method.getClass())
    assertResult(RestServiceMacrosSpec.PATH)(method.path)
    assertResult(List(classOf[T1], classOf[T2], classOf[T3], classOf[T4], classOf[T5], classOf[T6],
        classOf[T7], classOf[T8], classOf[T9], classOf[T10], classOf[T11], classOf[T12], classOf[T13],
        classOf[T14], classOf[T15], classOf[T16], classOf[T17], classOf[T18], classOf[R]))(
        argsInfo(method))
  }
  
  "the macro with 19 parameters function" should "create a RestFunction19 with the given data" in {
    def method = get(RestServiceMacrosSpec.PATH) { (t1:T1, t2:T2, t3:T3, t4:T4, t5:T5, t6:T6,
        t7:T7, t8:T8, t9:T9, t10:T10, t11:T11, t12:T12, t13:T13, t14:T14, t15:T15, t16:T16,
        t17:T17, t18:T18, t19:T19) =>
      new R()
    }
    
    assertResult(classOf[RestFunction19[_,_,_,_,_,_,_,_,_,_,_,_,_,_,_,_,_,_,_,_]])(method.getClass())
    assertResult(RestServiceMacrosSpec.PATH)(method.path)
    assertResult(List(classOf[T1], classOf[T2], classOf[T3], classOf[T4], classOf[T5], classOf[T6],
        classOf[T7], classOf[T8], classOf[T9], classOf[T10], classOf[T11], classOf[T12], classOf[T13],
        classOf[T14], classOf[T15], classOf[T16], classOf[T17], classOf[T18], classOf[T19], 
        classOf[R]))(argsInfo(method))
  }
  
  "the macro with 20 parameters function" should "create a RestFunction20 with the given data" in {
    def method = get(RestServiceMacrosSpec.PATH) { (t1:T1, t2:T2, t3:T3, t4:T4, t5:T5, t6:T6,
        t7:T7, t8:T8, t9:T9, t10:T10, t11:T11, t12:T12, t13:T13, t14:T14, t15:T15, t16:T16,
        t17:T17, t18:T18, t19:T19, t20:T20) =>
      new R()
    }
    
    assertResult(classOf[RestFunction20[_,_,_,_,_,_,_,_,_,_,_,_,_,_,_,_,_,_,_,_,_]])(
        method.getClass())
    assertResult(RestServiceMacrosSpec.PATH)(method.path)
    assertResult(List(classOf[T1], classOf[T2], classOf[T3], classOf[T4], classOf[T5], classOf[T6],
        classOf[T7], classOf[T8], classOf[T9], classOf[T10], classOf[T11], classOf[T12], classOf[T13],
        classOf[T14], classOf[T15], classOf[T16], classOf[T17], classOf[T18], classOf[T19], 
        classOf[T20], classOf[R]))(argsInfo(method))
  }
  
  "the macro with 21 parameters function" should "create a RestFunction21 with the given data" in {
    def method = get(RestServiceMacrosSpec.PATH) { (t1:T1, t2:T2, t3:T3, t4:T4, t5:T5, t6:T6,
        t7:T7, t8:T8, t9:T9, t10:T10, t11:T11, t12:T12, t13:T13, t14:T14, t15:T15, t16:T16,
        t17:T17, t18:T18, t19:T19, t20:T20, t21:T21) =>
      new R()
    }
    
    assertResult(classOf[RestFunction21[_,_,_,_,_,_,_,_,_,_,_,_,_,_,_,_,_,_,_,_,_,_]])(
        method.getClass())
    assertResult(RestServiceMacrosSpec.PATH)(method.path)
    assertResult(List(classOf[T1], classOf[T2], classOf[T3], classOf[T4], classOf[T5], classOf[T6],
        classOf[T7], classOf[T8], classOf[T9], classOf[T10], classOf[T11], classOf[T12], classOf[T13],
        classOf[T14], classOf[T15], classOf[T16], classOf[T17], classOf[T18], classOf[T19], 
        classOf[T20], classOf[T21], classOf[R]))(argsInfo(method))
  }
  
  "the macro with 22 parameters function" should "create a RestFunction22 with the given data" in {
    def method = get(RestServiceMacrosSpec.PATH) { (t1:T1, t2:T2, t3:T3, t4:T4, t5:T5, t6:T6,
        t7:T7, t8:T8, t9:T9, t10:T10, t11:T11, t12:T12, t13:T13, t14:T14, t15:T15, t16:T16,
        t17:T17, t18:T18, t19:T19, t20:T20, t21:T21, t22:T22) =>
      new R()
    }
    
    assertResult(classOf[RestFunction22[_,_,_,_,_,_,_,_,_,_,_,_,_,_,_,_,_,_,_,_,_,_,_]])(
        method.getClass())
    assertResult(RestServiceMacrosSpec.PATH)(method.path)
    assertResult(List(classOf[T1], classOf[T2], classOf[T3], classOf[T4], classOf[T5], classOf[T6],
        classOf[T7], classOf[T8], classOf[T9], classOf[T10], classOf[T11], classOf[T12], classOf[T13],
        classOf[T14], classOf[T15], classOf[T16], classOf[T17], classOf[T18], classOf[T19], 
        classOf[T20], classOf[T21], classOf[T22], classOf[R]))(argsInfo(method))
  }
  
  "the DELETE method macro" should "return DELETE as HTTP method" in {
    def method = delete(RestServiceMacrosSpec.PATH) { () =>
      new R()
    }
    
    assertResult(HttpMethod.DELETE)(method.httpMethod)
  }
  
  "the GET method macro" should "return GET as HTTP method" in {
    def method = get(RestServiceMacrosSpec.PATH) { () =>
      new R()
    }
    
    assertResult(HttpMethod.GET)(method.httpMethod)
  }
  
  "the PATCH method macro" should "return PATCH as HTTP method" in {
    def method = patch(RestServiceMacrosSpec.PATH) { () =>
      new R()
    }
    
    assertResult(HttpMethod.PATCH)(method.httpMethod)
  }
  
  "the POST method macro" should "return POST as HTTP method" in {
    def method = post(RestServiceMacrosSpec.PATH) { () =>
      new R()
    }
    
    assertResult(HttpMethod.POST)(method.httpMethod)
  }
  
  "the PUT method macro" should "return PUT as HTTP method" in {
    def method = put(RestServiceMacrosSpec.PATH) { () =>
      new R()
    }
    
    assertResult(HttpMethod.PUT)(method.httpMethod)
  }
  
  private def argsInfo[T:ru.TypeTag](x:T) = {
    def toClass(tpe: Type):Class[_] = {
      val m = runtimeMirror(getClass.getClassLoader)
      m.runtimeClass(tpe)
    }
    ru.typeOf[T] match { case ru.TypeRef(_, _, args) => args map toClass }
  }
}

class T1; class T2; class T3; class T4; class T5; class T6; class T7; class T8; class T9; class T10
class T11; class T12; class T13; class T14; class T15; class T16; class T17; class T18; class T19; class T20
class T21; class T22

class R

object RestServiceMacrosSpec {
  val PATH = "/path"
}