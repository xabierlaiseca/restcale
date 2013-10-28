package me.laiseca.restcale.methodcall

trait ParamExtractorsFactory {
  def create(httpMethod: String, pathTemplate: String):List[ParamExtractor]
}

class DefaultParamExtractorsFactory extends ParamExtractorsFactory {
  def create(httpMethod: String, pathTemplate: String):List[ParamExtractor] = {
    val typeTransformer = new TypeTransformer
    List(new UrlParamExtractor(pathTemplate, typeTransformer), 
        new QueryParamExtractor(typeTransformer))
  }
}