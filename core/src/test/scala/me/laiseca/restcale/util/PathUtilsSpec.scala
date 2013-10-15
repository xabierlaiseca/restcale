package me.laiseca.restcale.util

import org.scalatest.Matchers
import org.scalatest.FlatSpec

class PathUtilsSpec extends FlatSpec with Matchers {
  "given a path which starts with / split" should "create a list of the path segments" in {
    val path = "/some/path"
    
    assertResult(List("some", "path")){
      PathUtils.split(path)
    }
  }
  
  "given a path which starts with a letter split" should "create a list of the path segments" in {
    val path = "some/path"
    
    assertResult(List("some", "path")){
      PathUtils.split(path)
    }
  }
}