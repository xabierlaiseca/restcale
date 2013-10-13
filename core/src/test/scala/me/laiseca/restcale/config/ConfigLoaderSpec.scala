package me.laiseca.restcale.config

import org.scalatest.FlatSpec
import org.scalatest.Matchers

class ConfigLoaderSpec extends FlatSpec with Matchers {
	"the config loader" should "load the expected endpoint classes" in {
	  val config = new ConfigLoader().load("config/restcale.test")
	  
	  assertResult(List(classOf[EndpointA].getName(), classOf[EndpointB].getName(), classOf[EndpointC].getName())){
	    config.endpointClasses
	  }
	}
	
}

class EndpointA
class EndpointB
class EndpointC