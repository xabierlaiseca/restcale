import sbt._
import Keys._

object BuildSettings {
  val buildSettings = Defaults.defaultSettings ++ Seq(
    organization := "me.laiseca",
    version := "0.0.1-SNAPSHOT",
    scalaVersion := "2.10.3",
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

  lazy val macrosDeps = Seq(
    scalatest,
    mockito
  )

  lazy val sharedDeps = Seq(
    scalatest,
    mockito
  )

  lazy val root: Project = Project(
    "root",
    file("."),
    settings = buildSettings
  ) aggregate(macros, core, shared, example)

  lazy val shared: Project = Project(
    "shared",
    file("shared"),
    settings = buildSettings  ++ Seq( libraryDependencies ++= sharedDeps )
  )

  lazy val macros: Project = Project(
    "macros",
    file("macros"),
    settings = buildSettings ++ Seq(
      libraryDependencies <+= (scalaVersion)("org.scala-lang" % "scala-reflect" % _)) ++ Seq(
      libraryDependencies ++= macrosDeps) ++ Seq(
      libraryDependencies <+= (scalaVersion)("org.scala-lang" % "scala-compiler" % _))
  ) dependsOn(shared)

  lazy val core: Project = Project(
    "core",
    file("core"),
    settings = buildSettings ++ Seq( libraryDependencies ++= coreDeps )
  ) dependsOn(shared, macros)

  lazy val example: Project = Project(
    "example",
    file("example"),
    settings = buildSettings
  ) dependsOn(core)
}
