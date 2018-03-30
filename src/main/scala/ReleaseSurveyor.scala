package org.homermultitext.hmtcexbuilder
import edu.holycross.shot.scm._
import java.io.File
import java.io.PrintWriter

// class for creating overviews of a published release of HMT
// instantiated as a CiteLibrary.
case class ReleaseSurveyor(lib: CiteLibrary, baseDir: String, releaseId: String) {



  def dirMap: Map[String, File] = {
    val rootDir = new File(baseDir)
    if (!rootDir.exists){
      rootDir.mkdir
    }

    val releaseDir = new File(rootDir, s"${releaseId}-summary")
    releaseDir.mkdir
    require(releaseDir.exists, "Did not create release directory")

    val textDir= new File(releaseDir, "texts")
    textDir.mkdir
    require(textDir.exists, "Did not create text report directory")

    val imageDir= new File(releaseDir, "images")
    imageDir.mkdir
    require(imageDir.exists, "Did not create image report directory")

    val tbsDir= new File(releaseDir, "codices-papyri")
    tbsDir.mkdir
    require(tbsDir.exists, "Did not create codices-papyri report directory")

    val dseDir= new File(releaseDir, "dse")
    dseDir.mkdir
    require(dseDir.exists, "Did not create DSE report directory")

    Map( "texts" -> textDir, "images" -> imageDir, "tbs" -> tbsDir, "dse" -> dseDir)
/*()
    val home = new File(releaseDir, "index.md")
    val homePage = "# Overview of HMT project release " + releaseId +"\n\n"
    new PrintWriter(home){ write(homePage); close;} */

  }

  // overview of ohco2 editions
  def textOverview(textDir: File) = {
/*
println("OHOC2 model applies to \n\t")
for (txt <- lib.textRepository.get.catalog.labelledExemplars) {
  println("\t"+ txt)
}
for (txt <- lib.textRepository.get.catalog.labelledVersions) {
  println(s"\t${txt.label} (${txt.urn})")
}
    */
  }

  // overview of images modelled as binaryimg objects
  def imageOverview(imageDir: File) = {}

  //  overview of text-bearing surfaces
  def tbsOverview(tbsDir: File) = {}

  // overview of DSE triangle
  def dseOverview(dseDir: File)= {}
}
