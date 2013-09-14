package me.laiseca.restcale.config

import scala.collection.JavaConversions._
import scala.collection.immutable.List
import com.typesafe.config.ConfigFactory
import com.typesafe.config.Config

class RestcaleConfig(val endpointClasses:List[Class[_]])

class ConfigLoader() {
  def load(file:String = "restcale"):RestcaleConfig = {
    val config = ConfigFactory.load(file)
    val endpointClasses = config.getStringList("restcale.endpoints")
    	.toList.map(Class.forName(_))
    
    new RestcaleConfig(endpointClasses)
  }
}