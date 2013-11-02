package me.laiseca.restcale.example.rest

import me.laiseca.restcale.api.RestService

class UserResource extends RestService {
  
  override val path = "/user"
	
  def get = GET("/:id") { id:Int =>
    path + id
  }
  
  def get2 = GET("/:a/:b") { (a:Float, b:Int) =>
    a + b
  }
  
  def get3 = GET("/hello") { name:Option[String] => 
    if(name.isDefined) {
    	"Hello " + name.get + "!"
    } else {
      "Hello World!"
    }
  }
}