name := "Soccer league"
version := "0.1.0"
scalaVersion := "2.11.6"

resolvers += "Akka Snapshot Repository" at "http://repo.akka.io/snapshots/"

libraryDependencies += "junit" % "junit" % "4.10" % "test"
libraryDependencies += "org.scalatest" % "scalatest_2.11" % "2.2.4" % "test"
libraryDependencies += "com.typesafe.akka" %% "akka-actor" % "2.5-SNAPSHOT"