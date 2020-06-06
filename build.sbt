name := "sbt-akka-http"

version := "0.2"

scalaVersion := "2.13.1"

libraryDependencies ++=
  Seq("com.typesafe.akka" %% "akka-http"   % "10.1.12",
    "com.typesafe.akka" %% "akka-stream" % "2.5.26",
    "org.scalatest" %% "scalatest" % "3.1.2" % "test",
    "com.typesafe.akka" %% "akka-stream-testkit" % "2.5.26" % "test",
    "com.typesafe.akka" %% "akka-http-testkit" % "10.1.12" % "test"
  )
