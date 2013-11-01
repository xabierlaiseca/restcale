package me.laiseca.restcale.example

import me.laiseca.restcale.config.ConfigLoader
import me.laiseca.restcale.router.Router
import me.laiseca.restcale.methodcall.DefaultMethodCaller
import me.laiseca.restcale.router.RestMethodExtractor
import me.laiseca.restcale.api.RestService
import me.laiseca.restcale.http.server.HttpServer

object TestMain {

  def main(args: Array[String]): Unit = {
    val config = new ConfigLoader().load()
    val restServices = config.endpointClasses.map(endpointClass => Class.forName(endpointClass).newInstance().asInstanceOf[RestService])
    val methods = new RestMethodExtractor().extract(restServices)
    val router = new Router(methods, new DefaultMethodCaller())
    new HttpServer(router).run
  }

}