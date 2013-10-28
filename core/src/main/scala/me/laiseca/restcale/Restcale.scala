package me.laiseca.restcale

import me.laiseca.restcale.http.server.HttpServer
import me.laiseca.restcale.methodcall.ParamExtractor

object Restcale {

  def main(args: Array[String]): Unit = {
//    new HttpServer().run()
    val extractor = ParamExtractor.defaultQueryParamExtractor
//    extractor.extractParam[List[Int]]("hola", null)
//    extractor.extractParam[Option[Int]]("hola", null)
//    extractor.extractParam[Int]("hola", null)
  }


}