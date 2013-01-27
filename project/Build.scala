import sbt._
import Keys._
import PlayProject._

object ApplicationBuild extends Build {

    val appName         = "bandwagon"
    val appVersion      = "1.0-SNAPSHOT"

    val appDependencies = Seq(
      "org.mongodb" %% "casbah" % "2.5.0"
    )

    val main = PlayProject(appName, appVersion, appDependencies, mainLang = SCALA).settings(
      // Add your own project settings here
      templatesImport ++= Seq("uk.co.chrisloy.bandwagon.model._")
    )

}
