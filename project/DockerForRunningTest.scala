import java.io.PrintWriter

import sbt.Keys._
import sbt._

object DockerForRunningTest {

  def cleanly[A, B](resource: => A)(code: A => B)(cleanup: A => Unit): Option[B] = {
    try {
      val r = resource
      try {
        Some(code(r))
      }
      finally {
        cleanup(r)
      }
    } catch {
      case e: Exception => None
    }
  }

  val buildScalaSources = taskKey[Unit]("Build the example dockerfile")

  val buildScalaSourcesSettings = buildScalaSources := {

    val workdir = (sourceDirectory in Compile).value / "docker"

    s"docker build -t xebia/scala_exercises ${workdir.absolutePath}" !
  }

  val makeBashFileToTest = taskKey[Unit]("Run the example dockerfile")

  val makeBashFileToTestSettings = makeBashFileToTest := {

    val basedir = (baseDirectory in Compile).value

    val home = sys.env("HOME")

    val contents = List(
      "#!/bin/bash",
      s"docker run --name xebia_introscala -it -v $basedir:/tmp/scala_exercises -v /var/run/docker.sock:/var/run/docker.sock -v $home/.sbt/:/root/.sbt -v $home/.ivy2/:/root/.ivy2 xebia/scala_exercises /bin/sh -C /tmp/scala_exercises/; ./activator"
    )
    cleanly {
      new PrintWriter(new File("runTestInDocker.sh"))
    }(_.write(contents.mkString("\n")))(_.close)

    "chmod 555 runTestInDocker.sh" !
  }

}