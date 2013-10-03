package me.laiseca.restcale.macros

import scala.collection.immutable.List
import scala.reflect.macros.Context
import me.laiseca.restcale.internal.function.BaseRestFunction
import me.laiseca.restcale.http.HttpMethod
import scala.collection.mutable.HashSet
import scala.collection.mutable.Set
import scala.collection.immutable.HashMap

object RestServiceMacros {
  
  private val pathPatternsByMethod = HashMap[String, Set[String]](
      HttpMethod.DELETE -> HashSet(),
      HttpMethod.GET -> HashSet(),
      HttpMethod.PATCH -> HashSet(),
      HttpMethod.POST -> HashSet(),
      HttpMethod.PUT -> HashSet())
  
  val BASE_CLASS_NAME = classOf[BaseRestFunction].getPackage().getName() + ".RestFunction"
  
  def delete[R](c: Context)(path:c.Expr[String])(f:c.Expr[R]) = {
    buildFunction(c)(f, path, HttpMethod.DELETE)
  }
  def get[R](c: Context)(path:c.Expr[String])(f:c.Expr[R]) = {
    buildFunction(c)(f, path, HttpMethod.GET)
  }
  
  def patch[R](c: Context)(path:c.Expr[String])(f:c.Expr[R]) = {
    buildFunction(c)(f, path, HttpMethod.PATCH)
  }
  
  def post[R: c.WeakTypeTag](c: Context)
  	  (path:c.Expr[String])(f:c.Expr[R]) = {
    buildFunction(c)(f, path, HttpMethod.POST)
  }
  
  def put[R: c.WeakTypeTag](c: Context)
  	  (path:c.Expr[String])(f:c.Expr[R]) = {
    buildFunction(c)(f, path, HttpMethod.PUT)
  }
  
  private def buildFunction[R](c: Context)(f:c.Expr[R], path:c.Expr[String], method: String) = {
    import c.universe._
    
    def validateUrl(pathExpr:c.Expr[String], method:String) = {
      val Literal(Constant(path:String)) = pathExpr.tree
      val pathPatterns = pathPatternsByMethod.getOrElse(method, null)
      val paramPattern = ":[^/]+".r
      val pathPattern = paramPattern.replaceAllIn(path, "<wildcard>")
      
      if(pathPatterns contains pathPattern) {
        c.abort(c.enclosingPosition, "url pattern already defined")
      } else {
        pathPatterns add pathPattern
      }
    }
    
    validateUrl(path, method)
    
    val TypeRef(thisType, _, args) = f.tree.tpe
    val clazz = c.mirror.staticClass(BASE_CLASS_NAME + (args.size - 1) )
    val clazzType = TypeRef(thisType, clazz , args)
    
    c.Expr[BaseRestFunction](Apply(
      Select(
        New(TypeTree().setType(clazzType)),
        newTermName(nme.CONSTRUCTOR.decoded)),
      List(f.tree, Literal(Constant(method)), path.tree)))
  }
}

