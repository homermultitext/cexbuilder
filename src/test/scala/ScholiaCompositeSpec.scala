package org.homermultitext.cexbuilder

import org.scalatest.FlatSpec
import java.io.File
import scala.xml._

class ScholiaCompositeSpec extends FlatSpec {

  "The ScholiaComposite object" should "find unique scholia groups in a set of files" in {
    val scholiaSrc = "src/test/resources/scholia-xml"
    val expected = Set("msA",
    "msAextra", "msAext", "msAil", "msAint", "msAim", "msAlater")
    assert(ScholiaComposite.scholiaSet(scholiaSrc) == expected)
  }



  it should "extract text content for a given single document" in {
    val doc = "msAextra"
    val srcDir = "src/test/resources/scholia-xml"
    val xmlFiles = filesInDir(srcDir, "xml")
    val msAextraText = ScholiaComposite.compositeDocument(doc, xmlFiles)
    // should produce two scholia in book 1 and one in book 2:
    val root = XML.loadString("<root>" + msAextraText + "</root>")
    val books = root \ "div"
    assert(books.size == 2)
    val book1 = books(0)
    val scholia1 = book1 \ "div"
    assert (scholia1.size == 2)
    val scholia2 = books(1)
    assert (scholia2.size == 1)
  }
}
