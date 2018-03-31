package org.homermultitext.hmtcexbuilder
import edu.holycross.shot.scm._
import edu.holycross.shot.cite._
import java.io.File
import java.io.PrintWriter

/** Class for creating overviews of a published release of HMT
* instantiated as a CiteLibrary.
*
* @param lib The published release to survey.
* @param baseDir Directory where subdirectory for reports will be created.
* @param releaseId Identifier for this release, used to create name
* of subdirectory where reports are written.
*/
case class ReleaseSurveyor(lib: CiteLibrary, baseDir: String, releaseId: String) {
  val imtIctBase = "http://www.homermultitext.org/ict2/"
  val hmtIipSrvBase = "http://www.homermultitext.org/iipsrv?OBJ=IIP,1.0&FIF=/project/homer/pyramidal/deepzoom/"


  /** Compose IIPSrv URL for an image. */
  def iipSrvUrl(img: Cite2Urn, baseUrl: String = hmtIipSrvBase, width: Int = 1000): String = {
    val trail = s"&WID=${width}&CVT=JPEG"
    val imageOnly = List(baseUrl, img.namespace, img.collection, img.version, img.dropExtensions.objectOption.get).mkString("/") + s".tif"

    img.objectExtensionOption match {
      case None => imageOnly +  trail
      case roi:  Some[String] =>imageOnly + "&RGN=" + roi.get + trail
    }

  }
//http://www.homermultitext.org/iipsrv?OBJ=IIP,1.0&FIF=/project/homer/pyramidal/deepzoom/hmt/vaimg/2017a/VA012RN_0013.tif&RGN=0.1107,0.3552,0.05651,0.04688&wID=5000&CVT=JPEG
  /** Map of topic label to subdirectory name. */
  val subdirForTopic = Map (
    "images" -> "images",
    "tbs" -> "codices-papyri",
    "texts" -> "texts",
    "dse" -> "dse"
  )


  // assemble complete suite of reports
  def overview = {
    val indexText = homePage
    val indexFile = new File(releaseDir, "index.md")
    println("write home page to " + indexFile)
    new PrintWriter(indexFile) {write(indexText); close; }
  }


  /** Compose a home page, in markdown format, for this report.
  */
  def homePage: String = {
    val hdr = "# Overview of HMT project release **" + releaseId +"**\n\n" +
    fileLayoutBoilerPlate  +
    "## Collection data models\n\n"

    val dm = for (dm <- lib.dataModels.get)  yield {
      "\n**" + dm.label + s"** (`${dm.model}`) applies to \n\n-   " + lib.collectionsForModel(dm.model).mkString("\n-   ")
    }

    val txtsHdr = "\n\n## Texts\n\nThe OHOC2 model of citable texts applies to \n\n"
    val textCatalog = lib.textRepository.get.catalog
    val exemplarList = for (txt <- textCatalog.labelledExemplars) yield {
      s"-   ${textCatalog.groupName(txt.urn)}, *${textCatalog.workTitle(txt.urn)}* (${txt.label}: `${txt.urn}`)"
    }
    val versionList =  for (txt <- textCatalog.labelledVersions) yield {
        s"-   ${textCatalog.groupName(txt.urn)}, *${textCatalog.workTitle(txt.urn)}* (${txt.label}: `${txt.urn})`"
      //s"-   ${txt.label} (${txt.urn})"
    }
    hdr + dm.mkString("\n") + txtsHdr+ exemplarList.mkString("\n") + versionList.mkString("\n")
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
    val subdirMap = for (topic <- subdirForTopic.keySet) yield {
        val subdir = new File(releaseDir, subdirForTopic(topic))
        subdir.mkdir
        require(subdir.exists, s"Did not create directory for topic ${topic}")
        (topic, subdir)
    }
    subdirMap.toMap
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

  /** Compose report on collections of images modelled as imagemodel objects.
  *
  * @param imageDir Directory where image reports should be written.
  */
  def imageOverview(imageDir: File) = {
    val binaryImageModel = Cite2Urn("urn:cite2:cite:datamodels.v1:imagemodel")
    val citeCatalog = lib.collectionRepository.get.catalog

    for (urn <- lib.collectionsForModel(binaryImageModel)) {
      val objects = lib.collectionRepository.get.objects ~~ urn
      println("COLLECTION: ")
      println("1.  " + citeCatalog.collection(urn).get.collectionLabel)
      println(s"2. size ${objects.size} images" )
      println("3.  Visuatiozze")
      for(k <- objects.objectMap.keySet) {

        println("\t" + k+"->"+objects.objectMap(k).label)
      }
    }

    ///lib.collectionRepository.get.catalog.collection(Cite2Urn("urn:cite2:hmt:vaimg.2017a:")).get.collectionLabel
/*    val dm = for (dm <- lib.dataModels.get)  yield {
      "\n**" + dm.label + s"** (`${dm.model}`) applies to \n\n-   " + lib.collectionsForModel(dm.model).mkString("\n-   ")
    }
*/
  }

  //  overview of text-bearing surfaces
  def tbsOverview(tbsDir: File) = {
    def tbsModel = Cite2Urn("urn:cite2:cite:datamodels.v1:tbsmodel")
  }

  // overview of DSE triangle
  def dseOverview(dseDir: File)= {
    val dseModel = Cite2Urn("urn:cite2:cite:datamodels.v1:dse")
  }



  /** Compose message about file layout. */
  def fileLayoutBoilerPlate: String =  {
    val folderList = for (topic <- subdirForTopic.keySet) yield {
      "-   `" + subdirForTopic(topic) + "`"
    }
    "Note: more details are provided for specific contents of this release in the associated folders:\n\n" + folderList.mkString("\n") + "\n\n"
  }

}
