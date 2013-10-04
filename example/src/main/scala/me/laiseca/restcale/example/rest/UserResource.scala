package me.laiseca.restcale.example.rest

import me.laiseca.restcale.api.RestService

class UserResource extends RestService {
  
  override val path = "/user"
	
  def get = GET("/:id") { id:Int =>
    id
  }
  
  def post = POST("/:id") { (a:Float, b:Int) =>
    a + b
  }
}