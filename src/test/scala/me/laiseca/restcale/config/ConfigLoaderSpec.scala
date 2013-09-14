package me.laiseca.restcale.config

import org.scalatest.FlatSpec
import org.scalatest.Matchers

class ConfigLoaderSpec extends FlatSpec with Matchers {
	"the config loader" should "load the expected endpoint classes" in {
	  val config = new ConfigLoader().load("config/restcale.test.conf")
	  
	  assertResult(List[Class[_]](classOf[EndpointA], classOf[EndpointB], classOf[EndpointC])){
	    config.endpointClasses
	  }
	}
	
}

class EndpointA
class EndpointB
class EndpointC