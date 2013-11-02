package me.laiseca.restcale.macros

import scala.collection.immutable.List
import scala.reflect.macros.Context
import me.laiseca.restcale.internal.function.BaseRestFunction
import me.laiseca.restcale.http.HttpMethod
import scala.collection.mutable.HashSet
import scala.collection.mutable.Set
import scala.collection.immutable.HashMap
import me.laiseca.restcale.internal.function.Argument
import me.laiseca.restcale.internal.function.TypeParameter
import me.laiseca.restcale.internal.function.TypeParameter

object RestServiceMacros {
  
  private val pathPatternsByMethod = HashMap[String, Set[String]](
      HttpMethod.DELETE -> HashSet[String](),
      HttpMethod.GET -> HashSet[String](),
      HttpMethod.PATCH -> HashSet[String](),
      HttpMethod.POST -> HashSet[String](),
      HttpMethod.PUT -> HashSet[String]())
  
  val BASE_CLASS_NAME = classOf[BaseRestFunction].getPackage().getName() + ".RestFunction"
  
  def delete[R](c: Context)(path:c.Expr[String])(f:c.Expr[R]) =
    buildFunction(c)(f, path, HttpMethod.DELETE)

  def get[R](c: Context)(path:c.Expr[String])(f:c.Expr[R]) =
    buildFunction(c)(f, path, HttpMethod.GET)
  
  def patch[R](c: Context)(path:c.Expr[String])(f:c.Expr[R]) =
    buildFunction(c)(f, path, HttpMethod.PATCH)
  
  def post[R: c.WeakTypeTag](c: Context) (path:c.Expr[String])(f:c.Expr[R]) = 
    buildFunction(c)(f, path, HttpMethod.POST)
  
  def put[R: c.WeakTypeTag](c: Context)(path:c.Expr[String])(f:c.Expr[R]) =
    buildFunction(c)(f, path, HttpMethod.PUT)
  
  private def buildFunction[R](c: Context)(expr:c.Expr[R], pathExpr:c.Expr[String], method: String) = {
    import c.universe._

    def validateIsFunction(expr:c.Expr[R]): Unit = {
      expr match {
        case Expr(Function(_)) => 
        case _ => c.abort(c.enclosingPosition, "Parameter is not a function")
      }
    }
    
    def buildParams(expr:c.Expr[R]) = {
      def buildList[T:TypeTag](elems:List[Tree]) = {
        Apply(
          TypeApply(
            Select(
              Ident(c.mirror.staticModule(classOf[List[_]].getName)),
              newTermName("apply")),
            List(Ident(typeOf[T].typeSymbol))), elems
        )
      }
      
      def buildTypeParameter(typeParam: Type):Tree = {
        val TypeRef(_,_,subTypeParams) = typeParam
        Apply(
          Select(
            New(TypeTree().setType(typeOf[TypeParameter])),
            newTermName(nme.CONSTRUCTOR.decoded)),
          List(
            Literal(Constant(typeParam.typeSymbol.fullName)),
            buildList[TypeParameter](subTypeParams.map(subTypeParam => buildTypeParameter(subTypeParam)))
          )
        )
      }
      
      def buildParam(valDef:ValDef):Tree = {
        val ValDef(a1, name, tpeTree, a2) = valDef

        val TypeRef(_,_,typeParams) = tpeTree.tpe
        
        Apply(
          Select(
            New(TypeTree().setType(typeOf[Argument])),
            newTermName(nme.CONSTRUCTOR.decoded)),
          List(
            Literal(Constant(tpeTree.symbol.fullName)),
            buildList[TypeParameter](typeParams.map(typeParam => buildTypeParameter(typeParam))),
            Literal(Constant(name.decoded))
          )
        )
      }
      
      val Expr(Function(params,_)) = expr
      
      buildList[Argument](params.map(elem => buildParam(elem)))
    }
    
    val Literal(Constant(relativePath:String)) = pathExpr.tree
    
    validateIsFunction(expr)
    
    val TypeRef(thisType, _, args) = expr.tree.tpe
    val clazz = c.mirror.staticClass(BASE_CLASS_NAME + (args.size - 1) )
    val clazzType = TypeRef(thisType, clazz , args)
    
    c.Expr[BaseRestFunction](
      Apply(
        Select(
          New(TypeTree().setType(clazzType)),
          newTermName(nme.CONSTRUCTOR.decoded)),
        List(expr.tree,
          Literal(Constant(method)),
          Apply(
            Select(
              Select(This(tpnme.EMPTY), newTermName("path")),
              newTermName("$plus")
            ),
            List(pathExpr.tree)
          ),
          buildParams(expr)
        )
      )
    )
  }
}

