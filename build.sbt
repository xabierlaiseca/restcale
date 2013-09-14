name := "restcale"

version:= "0.0.1-SNAPSHOT"

scalaVersion := "2.10.2"

scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature")

libraryDependencies += "io.netty" % "netty-all" % "4.0.7.Final"

libraryDependencies += "com.typesafe" % "config" % "1.0.2"

libraryDependencies ++= Seq(
	"org.scalatest" % "scalatest_2.10" % "2.0.M6" % "test",
	"org.mockito" % "mockito-core" % "1.9.5" % "test"
)
