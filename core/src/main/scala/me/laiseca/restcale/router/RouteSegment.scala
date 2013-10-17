package me.laiseca.restcale.router

abstract class RouteSegment 

class FixedRouteSegment(val routeSegment:String) extends RouteSegment with Equals {
  def canEqual(other: Any) = {
    other.isInstanceOf[FixedRouteSegment]
  }
  
  override def equals(o:Any):Boolean = o match {
    case that: FixedRouteSegment => that.canEqual(FixedRouteSegment.this) && routeSegment == that.routeSegment
    case _ => false
  }
  
  override def hashCode:Int = routeSegment.hashCode
}

object WildcardRouteSegment extends RouteSegment