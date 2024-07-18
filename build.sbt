ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / scalaVersion := "2.13.12"
ThisBuild / organization := "com.example"

// sbt:ROOT> hello
lazy val hello = taskKey[Unit]("An example task")

val toolkitTest = "org.scala-lang" %% "toolkit-test" % "0.1.7"

lazy val sharedSettings = Seq(
  hello := { println("Hello SUBPROJECT!") }
)

lazy val dontTrackMe = project
  .in(file("dontTrackMe"))
  .settings(
    exportToInternal := TrackLevel.NoTracking
  )

lazy val util = project
  .in(file("util"))
  .settings(
    libraryDependencies ++= Seq(
      "org.scala-lang" %% "toolkit" % "0.1.7",
      toolkitTest % Test
    ),
    hello := { println("Hello UTIL!") }
  )

lazy val core = project
  .in(file("core"))
  .settings(
    name := "CORE",
    libraryDependencies ++= Seq(
      "org.scala-lang" %% "toolkit" % "0.1.7",
      toolkitTest % Test
    ),
    hello := { println("Hello CORE!") }
  )

lazy val root = project
  .in(file("."))
  .aggregate(util, core)
  .dependsOn(util, core)
  .enablePlugins(JavaAppPackaging)
  .settings(
    name := "ROOT",
    maintainer := "A scala dev!",
    libraryDependencies += toolkitTest % Test,
    hello := { println("Hello ROOT!") },
    hello / aggregate := false
  )
