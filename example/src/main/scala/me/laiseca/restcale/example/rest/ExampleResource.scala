package me.laiseca.restcale.example.rest

import me.laiseca.restcale.api.RestService

class ExampleResource extends RestService {
  
  override val path = "/example"
	
  def echo = GET("/echo/:word") { word:String =>
    word
  }
  
  def hello = GET("/hello") { name:Option[String] => 
    if(name.isDefined) {
      "Hello " + name.get + "!"
    } else {
      "Hello World!"
    }
  }
  
  def sum = GET("/sum") { nums:List[Int] =>
    nums.foldLeft(0)(_ + _)
  }
}