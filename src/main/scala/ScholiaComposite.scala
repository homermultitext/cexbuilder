package org.homermultitext.cexbuilder


import scala.xml._
import java.io.File
import java.io.PrintWriter



/** Factory for creating CEX composite texts of scholia from
* multiple XML source files in a given directory.
*
* @param srcDir Source directory with archival XML files..
*/
object ScholiaComposite {


  /** Find set of text groups represented in XML
  * source files in a given directory.
  *
  * @param srcDir Directory containing XML editions of scholia.
  */
  def scholiaSet(srcDir: String): Set[String]  = {
    val xmlSource = filesInDir(srcDir, "xml")
    val allGroups = for (f <- xmlSource) yield {
      val root = XML.loadFile(f)
      val groups = root \ "text" \ "group" \ "text"
      groups
    }
    allGroups.flatten.map(_.attribute("n").get.text).toSet
  }


  /** Extract a given scholia document from a set of XML
  * source files and create a single composite text (as a String).
  *
  * @param document Document identifier for scholia document (work ID in the document's CTS URN).
  * @param files XML files to extract content from.
  */
  def compositeDocument(document : String, files: Set[File]): String = {
    val lines = for (f <- files) yield {
      val root = XML.loadFile(f)
      val teiGroup = root \ "text" \ "group"
      val bkNode = teiGroup(0)
      val book = bkNode.attribute("n").get
      val bookOpen = s"""<div n="${book}" type="book">"""

      val allDocs = bkNode \ "text"
      val relevant = allDocs.filter(_.attribute("n").get.text == document)
      if (relevant.size > 0) {
        val doc = relevant(0)
        val scholia = doc \ "body" \ "div"
        println("\tIn " + document + "book " + book  +", " + scholia.size + " scholia.")
        val scholStrings = scholia.map(_.toString)
        bookOpen + scholStrings.mkString("\n") + "</div>"
      } else { "" }
    }
    lines.mkString("\n")
  }

}
