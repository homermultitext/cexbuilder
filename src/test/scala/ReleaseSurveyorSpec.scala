package org.homermultitext.hmtcexbuilder
import org.scalatest.FlatSpec
import scala.xml._
import java.io.File

import edu.holycross.shot.scm._

class ReleaseSurveyorSpec extends FlatSpec {

  val tinyCex = "src/test/resources/hmt-tiny.cex"
  val rootDir = "src/test/resources"
  val releaseId = "test-release"
  val lib = CiteLibrarySource.fromFile(tinyCex)

  "A ReleaseSurveyor" should "set up a set of directories" in {
    val surveyor = ReleaseSurveyor(lib,rootDir,releaseId)
    val directories =  surveyor.dirMap

    val expectedKeys = Set("texts","tbs", "images", "dse")
    assert(directories.keySet == expectedKeys)

    for (d <- directories.keySet) {
      val subdir = directories(d)
      assert(subdir.exists)
      println(s"${subdir} exists.")
      // and tidy up
      for (f <- DataCollector.filesInDir(subdir, "md")) {
        f.delete
      }
      subdir.delete

    }
    // text
    // img
    // tbs
    // dse
  }


}
