package org.homermultitext.hmtcexbuilder
import org.scalatest.FlatSpec
import scala.xml._
import java.io.File

class DataCollectorSpec extends FlatSpec {

  "The DataCollector object" should "collect text content of a parsed xml node" in  {
    val tinyNode = XML.loadString("<root>Text message</root>")
    assert (DataCollector.collectXmlText(tinyNode) == "Text message")
  }

  it should "collect text content from a node with multiple levels of markup" in {
    val twoTier = "<root>Text <subElement>message</subElement></root>"
      val tinyNode = XML.loadString(twoTier)
      assert (DataCollector.collectXmlText(tinyNode) == "Text message")

  }

  it should "collect XML text content from a well-formed text fragment" in {
      val twoTier = "<root>Text <subElement>message</subElement></root>"
      assert (DataCollector.collectXmlText(twoTier)  == "Text message")
  }

  it should "list files in a directory" in  {
    val srcDir = "src/test/resources/scholia-xml"
    val xmlFiles = DataCollector.filesInDir(srcDir, "xml")
    val expected = Set(
      new File("src/test/resources/scholia-xml/VenetusA-Scholia-01.xml"),
      new File("src/test/resources/scholia-xml/VenetusA-Scholia-02.xml")
    )
    assert(xmlFiles == expected)
  }
}
