package org.homermultitext.hmtcexbuilder
import org.scalatest.FlatSpec
import scala.xml._

class XmlCollectorSpec extends FlatSpec {

  "The XmlCollector object" should "collect text content of a parsed node" in  {
    val tinyNode = XML.loadString("<root>Text message</root>")
    assert (XmlCollector.collectText(tinyNode) == "Text message")
  }

  it should "collect text content from a node with multiple levels of markup" in {
    val twoTier = "<root>Text <subElement>message</subElement></root>"
      val tinyNode = XML.loadString(twoTier)
      assert (XmlCollector.collectText(tinyNode) == "Text message")

  }

  it should "collect XML text content from a well-formed text fragment" in {
      val twoTier = "<root>Text <subElement>message</subElement></root>"
      assert (XmlCollector.collectText(twoTier)  == "Text message")
  }
}
