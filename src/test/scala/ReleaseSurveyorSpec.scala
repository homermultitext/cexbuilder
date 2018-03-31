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


  def tidy(surveyor: ReleaseSurveyor) = {
    val directories =  surveyor.dirMap
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
    surveyor.releaseDir.delete
  }

  "A ReleaseSurveyor" should "set up a set of directories for reports" in {
    val surveyor = ReleaseSurveyor(lib,rootDir,releaseId)
    val directories =  surveyor.dirMap

    val expectedKeys = Set("texts","tbs", "images", "dse")
    assert(directories.keySet == expectedKeys)
    //tidy(surveyor)
  }
  it should "compose a home page for the report" in {
    val surveyor = ReleaseSurveyor(lib,rootDir,releaseId)
    surveyor.overview
  }
  it should "make it easy to tidy up after all these tests :-)" in {
    val surveyor = ReleaseSurveyor(lib,rootDir,releaseId)
    //tidy(surveyor)
  }


}
