package me.laiseca.restcale.example.rest

import me.laiseca.restcale.api.RestService

class UserResource extends RestService {
  
  override val path = "/user/"
	
  def get = GET("/:id") { id:Int =>
    path + id
  }
  
  def post = POST("/:a/:b") { (a:Float, b:Int) =>
    a + b
  }
}