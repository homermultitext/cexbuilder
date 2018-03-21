package org.homermultitext.hmtcexbuilder
import java.io.File

object FileCollector {

  def filesInDir(dir: String, extension: String = "xml"): Set[File] = {
    val libraryDir = new File(dir)
    val fileVector = libraryDir.listFiles.filter(_.isFile).toVector
    val files = fileVector.filter(_.getName.endsWith(extension))
    files.toSet
  }

}
