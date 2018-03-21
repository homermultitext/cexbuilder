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

}
