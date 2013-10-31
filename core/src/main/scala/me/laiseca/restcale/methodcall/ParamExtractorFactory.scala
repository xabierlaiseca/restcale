package me.laiseca.restcale.methodcall

trait ParamExtractorFactory {
  def create(httpMethod: String, pathTemplate: String):ParamExtractor
}

class DefaultParamExtractorFactory extends ParamExtractorFactory {
  def create(httpMethod: String, pathTemplate: String):ParamExtractor = {
    new DefaultParamExtractor(httpMethod, pathTemplate)
  }
}