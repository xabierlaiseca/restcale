import sbt._
import Keys._

object BuildSettings {
  val buildSettings = Defaults.defaultSettings ++ Seq(
    organization := "me.laiseca",
    version := "0.0.1-SNAPSHOT",
    scalaVersion := "2.10.0",
    scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature")
  )
}

object RestcaleBuild extends Build {
  import BuildSettings._
  
  val netty = "io.netty" % "netty-all" % "4.0.7.Final"
  val config = "com.typesafe" % "config" % "1.0.2"
  val scalatest = "org.scalatest" % "scalatest_2.10" % "2.0.M6" % "test"
  val mockito = "org.mockito" % "mockito-core" % "1.9.5" % "test"
  
  lazy val coreDeps = Seq(
    netty,
    config,
    scalatest,
    mockito
  )

  lazy val root: Project = Project(
    "root",
    file("."),
    settings = buildSettings
  ) aggregate(macros, core)

  lazy val macros: Project = Project(
    "macros",
    file("macros"),
    settings = buildSettings ++ Seq(
      libraryDependencies <+= (scalaVersion)("org.scala-lang" % "scala-reflect" % _))
  )

  lazy val core: Project = Project(
    "core",
    file("core"),
    settings = buildSettings ++ Seq( libraryDependencies ++= coreDeps )
  ) dependsOn(macros)
}