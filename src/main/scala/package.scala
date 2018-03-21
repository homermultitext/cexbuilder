package org.homermultitext
import java.io.File

package object cexbuilder {

  def filesInDir(dir: String, extension: String = "xml"): Set[File] = {
    val libraryDir = new File(dir)
    val fileVector = libraryDir.listFiles.filter(_.isFile).toVector
    val files = fileVector.filter(_.getName.endsWith(extension))
    files.toSet
    /*
    val allGroups = for (f <- files) yield {
      val root = XML.loadFile(f)
      val groups = root \ "text" \ "group" \ "text"
      groups
    }
    allGroups.flatten.map(_.attribute("n").get.text).toSet
    */
  }

}
