name := "HMT CEX builder"

crossScalaVersions  := Seq("2.11.8", "2.12.4")
scalaVersion := (crossScalaVersions ).value.last

name := "hmtcexbuilder"
organization := "org.homermultitext"
version := "3.4.0"
licenses += ("GPL-3.0",url("https://opensource.org/licenses/gpl-3.0.html"))
libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "3.0.1" % "test",

  "edu.holycross.shot.cite" %% "xcite" % "4.0.2",
  "edu.holycross.shot" %% "ohco2" % "10.13.0",
  "org.homermultitext" %% "hmt-textmodel" % "6.0.1",

  "edu.holycross.shot" %% "cex" % "6.3.3",
  "edu.holycross.shot" %% "dse" % "4.6.0",
  "edu.holycross.shot" %% "scm" % "6.2.3",
  "edu.holycross.shot" %% "citerelations" % "2.4.1"
)

tutTargetDirectory := file("docs")
tutSourceDirectory := file("src/main/tut")


enablePlugins(TutPlugin)
