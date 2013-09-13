package me.laiseca.restcale

import me.laiseca.restcale.http.server.HttpServer

object Main {

  def main(args: Array[String]): Unit = {
    new HttpServer().run()
  }


}