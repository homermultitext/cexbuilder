name := "HMT CEX builder"

crossScalaVersions  := Seq("2.11.8", "2.12.4")
scalaVersion := (crossScalaVersions ).value.last

name := "hmtcexbuilder"
organization := "org.homermultitext"
version := "3.2.0"
licenses += ("GPL-3.0",url("https://opensource.org/licenses/gpl-3.0.html"))
libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "3.0.1" % "test",

  "edu.holycross.shot.cite" %% "xcite" % "3.5.0",
  "edu.holycross.shot" %% "ohco2" % "10.9.0",
  "org.homermultitext" %% "hmt-textmodel" % "3.0.0",
  "edu.holycross.shot" %% "cex" % "6.2.1",
  "edu.holycross.shot" %% "dse" % "3.2.0",
  "edu.holycross.shot" %% "scm" % "6.1.1",
  "edu.holycross.shot" %% "citerelations" % "2.3.0"
)

tutTargetDirectory := file("docs")
tutSourceDirectory := file("src/main/tut")


enablePlugins(TutPlugin)
