package me.laiseca.restcale.example.rest

import me.laiseca.restcale.api.RestService

class UserResource extends RestService {
	
  def get = GET("/user/:id") { id:Int =>
    id
  }
  
  def get2 = GET("/user/:id") { (a:Float, b:Int) =>
    a + b
  }
}