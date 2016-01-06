package com.bigsonata.tools.labrador

import java.util.regex.Pattern

import scala.collection.mutable
import scala.io.Source

/**
 * Class to validate Urls
 * Created by anhld on 1/6/16.
 */
class PathInspector {
  var excluded = mutable.MutableList[Pattern]()
  var domains = mutable.MutableList[String]()

  for (line <- Source.fromFile("data/config/exclude.txt").getLines()) {
    if (line.length != 0) {
      excluded += Pattern.compile(line.toLowerCase)
    }
  }
  for (line <- Source.fromFile("data/config/domains.txt").getLines()) {
    if (line.length != 0) {
      domains += line.toLowerCase
    }
  }

  /**
   * Check if a URL need to be excluded
   * @param url The url
   * @return If the url's configured to be exclued, returns true.
   *         Otherwise, returns false
   */
  def isExcluded(url: String): Boolean = {
    excluded.foreach(pattern => {
      if (pattern.matcher(url).matches()) {
        return true
      }
    })

    val host = Utils.getHost(url)
    if (domains.contains(host.toLowerCase)) {
      return false;
    }

    true
  }
}
