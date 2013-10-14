package me.laiseca.restcale.router

abstract class RouteSegment(val segment:String) 

class FixedRouteSegment(segment:String) extends RouteSegment(segment) with Equals {
  def canEqual(other: Any) = {
    other.isInstanceOf[FixedRouteSegment]
  }
  
  override def equals(o:Any):Boolean = o match {
    case that: FixedRouteSegment => that.canEqual(FixedRouteSegment.this) && segment == that.segment
    case _ => false
  }
  
  override def hashCode:Int = segment.hashCode
}

class WildcardRouteSegment(segment:String) extends RouteSegment(segment) with Equals {
  def canEqual(other: Any) = {
    other.isInstanceOf[WildcardRouteSegment]
  }
  
  override def equals(o:Any): Boolean = o match {
    case that:WildcardRouteSegment => that.canEqual(WildcardRouteSegment.this)
    case _ => false
  }
  
  override def hashCode:Int = classOf[WildcardRouteSegment].hashCode
}