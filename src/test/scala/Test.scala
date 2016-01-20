import java.util.regex.Pattern

import com.bigsonata.tools.labrador.PathInspector

import scala.io.Source

/**
 * Created by anhld on 1/21/16.
 */
object Test extends App {
  val inspector = new PathInspector
  var url = "http://vnexpress.net"
  for (line <- Source.fromFile("data/test.txt").getLines()) {
    if (line.length != 0) {
      println(s"url = ${line} => excluded = ${inspector.isExcluded(line)}")
    }
  }
  println("Done")
}
