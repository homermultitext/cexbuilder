package org.homermultitext.hmtcexbuilder
import edu.holycross.shot.scm._
import java.io.File
import java.io.PrintWriter

/** Class for creating overviews of a published release of HMT
* instantiated as a CiteLibrary.
*
* @param lib The published release to survey.
* @param baseDir Directory where reports are to be written.
* @param releaseId Identifier this release, used to create name
* of subdirectory where reports are written.
*/
case class ReleaseSurveyor(lib: CiteLibrary, baseDir: String, releaseId: String) {


  def overview = {

    /*
    for (dm <- lib.dataModels.get)  {
      println("CITE data model:  " + dm.model + " applies to \n\t" + lib.collectionsForModel(dm.model).mkString("\n\t"))
    }
    println("OHOC2 model applies to \n\t")
    for (txt <- lib.textRepository.get.catalog.labelledExemplars) {
      println("\t"+ txt)
    }
    for (txt <- lib.textRepository.get.catalog.labelledVersions) {
      println(s"\t${txt.label} (${txt.urn})")
    }
    */
  }


  /** Compose a home page, in markdown format, for this report.
  */
  def homePage: String = {
    ""
  }

  /** Find root directory as a File object,
  * ensuring that it has been created.
  */
  def rootDir : File = {
    val root = new File(baseDir)
    if (!root.exists){
      root.mkdir
    }
    root
  }

  /** Find directory for reports on this release, as a File object,
  * ensuring that it has been created.
  */
  def releaseDir : File = {
    val reportDir = new File(rootDir, s"${releaseId}-summary")
    if (!reportDir.exists){
      reportDir.mkdir
    }
    reportDir
  }

  /** Construct map of required subdirectories.
  */
  def dirMap: Map[String, File] = {
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
