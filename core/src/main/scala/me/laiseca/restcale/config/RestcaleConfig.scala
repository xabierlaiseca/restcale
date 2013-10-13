package me.laiseca.restcale.config

import scala.collection.JavaConversions._
import scala.collection.immutable.List

import com.typesafe.config.ConfigFactory

class RestcaleConfig(val endpointClasses:List[String])

class ConfigLoader() {
  def load(file:String = "restcale.conf"):RestcaleConfig = {
    val config = ConfigFactory.load(file)
    new RestcaleConfig(config.getStringList("restcale.endpoints").toList)
  }
}