name := "HMT CEX builder"


scalaVersion := "2.12.4"


name := "hmtcexbuilder"
organization := "org.homermultitext"
version := "0.1.0"
licenses += ("GPL-3.0",url("https://opensource.org/licenses/gpl-3.0.html"))
libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "3.0.1" % "test",

  "edu.holycross.shot.cite" %% "xcite" % "3.2.2",
  "edu.holycross.shot" %% "ohco2" % "10.5.3",
  "org.homermultitext" %% "hmt-textmodel" % "2.2.0"
)

tutTargetDirectory := file("docs")
tutSourceDirectory := file("src/main/tut")


enablePlugins(TutPlugin)
