package org.homermultitext.cexbuilder

import org.scalatest.FlatSpec
import java.io.File

class ScholiaCompositeSpec extends FlatSpec {

  "The ScholiaComposite object" should "find unique scholia groups in a set of files" in {
    val scholiaSrc = "src/test/resources/scholia-xml"
    val expected = Set("msA",
    "msAextra", "msAext", "msAil", "msAint", "msAim", "msAlater")
    assert(ScholiaComposite.scholiaSet(scholiaSrc) == expected)
  }
}
