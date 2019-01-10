package org.homermultitext.hmtcexbuilder
import org.scalatest.FlatSpec
import scala.xml._
import java.io.File
import edu.holycross.shot.cex._

class DataCollectorSpec extends FlatSpec {

  "The DataCollector object" should "list files in a directory" in  {
    val srcDir = "src/test/resources/scholia-xml"
    val xmlFiles = DataCollector.filesInDir(srcDir, "xml")
    val expected = Vector(
      new File("src/test/resources/scholia-xml/VenetusA-Scholia-01.xml"),
      new File("src/test/resources/scholia-xml/VenetusA-Scholia-02.xml")
    )
    assert(xmlFiles == expected)
  }

  it should "collect text content from CEX files" in {
    val srcDir = "src/test/resources/cex"
    val cexString = DataCollector.compositeFiles(srcDir, "cex")
    val cex = CexParser(cexString)

    val expectedLabels = Set("ctscatalog", "ctsdata", "cexversion", "citelibrary")
    assert(cex.blockLabels == expectedLabels)
  }


  it should "drop headers from collected files as specified" in {
    val srcDir = "src/test/resources/withHeaders"
    val dataPlusHeader = DataCollector.compositeFiles(srcDir, "cex")
    assert(dataPlusHeader.split("\n").size == 3)
    val dataOnly  = DataCollector.compositeFiles(srcDir, "cex", 1)
    assert(dataOnly.split("\n").size == 2)
  }
}
