val akkaVersion = "2.4-20150420-230104"

name := "introscala"

organization := "com.ingenico.msh.marathonbridge"

version := "0.1"

scalaVersion := "2.11.6"


libraryDependencies ++= Seq(
  "com.typesafe.play" %% "play-json" % "2.3.4",
  "org.scalatest" % "scalatest_2.11" % "2.2.4" % "test"
)

resolvers += "typesafe_snapshots" at "http://repo.akka.io/snapshots"

resolvers += "Typesafe Repo" at "http://repo.typesafe.com/typesafe/releases/"

resolvers += "jboss repo" at "http://repository.jboss.org/nexus/content/groups/public-jboss/"
