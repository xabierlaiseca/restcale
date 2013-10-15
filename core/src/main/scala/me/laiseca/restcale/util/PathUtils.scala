package me.laiseca.restcale.util

object PathUtils {
  private val SEGMENT_SEPARATOR = "/"
  
  def split(path:String):List[String] = {
    path.split(SEGMENT_SEPARATOR).filterNot(_.isEmpty).toList
  }
}