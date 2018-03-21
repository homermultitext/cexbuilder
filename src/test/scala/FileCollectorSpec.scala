package org.homermultitext.hmtcexbuilder
import org.scalatest.FlatSpec
import java.io.File

class FileCollectorSpec extends FlatSpec {

  "The package object" should "list files in a directory" in  {
    val srcDir = "src/test/resources/scholia-xml"
    val xmlFiles = FileCollector.filesInDir(srcDir, "xml")
    val expected = Set(
      new File("src/test/resources/scholia-xml/VenetusA-Scholia-01.xml"),
      new File("src/test/resources/scholia-xml/VenetusA-Scholia-02.xml")
    )
    assert(xmlFiles == expected)
  }
}
