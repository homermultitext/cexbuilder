package org.homermultitext.hmtcexbuilder
import scala.xml._
import java.io.File

object DataCollector {


  /** Recursively collect contents of all text-node
  * descendants of a given node.
  * @param n Node to collect from.
  * @param buff Buffer for collecting text contents.
  * @return A single String with all text from n.
  */
  def collectXmlText(n: xml.Node): String = {
    collectXmlText(n,"")
  }

  /** Recursively collect contents of all text-node
  * descendants of a given node.
  * @param n Node to collect from.
  * @param buff Buffer for collecting text contents.
  * @return A single String with all text from n.
  */
  def collectXmlText(n: xml.Node, s: String): String = {
    var buff = StringBuilder.newBuilder
    buff.append(s)
    n match {
      case t: xml.Text => {
        buff.append(t.text)
      }

      case e: xml.Elem => {
        for (ch <- e.child) {
          buff = new StringBuilder(collectXmlText(ch, buff.toString))
        }
      }
    }
    buff.toString
  }

  def collectXmlText(s: String): String = {
    val root = XML.loadString(s)
    collectXmlText(root, "")
  }

  def filesInDir(dir: String, extension: String = "xml"): Set[File] = {
    val libraryDir = new File(dir)
    val fileVector = libraryDir.listFiles.filter(_.isFile).toVector
    val files = fileVector.filter(_.getName.endsWith(extension))
    files.toSet
  }

}
