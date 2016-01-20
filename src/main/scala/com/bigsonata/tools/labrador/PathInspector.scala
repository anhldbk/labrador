package com.bigsonata.tools.labrador

import java.util.regex.Pattern

import scala.collection.mutable
import scala.io.Source

/**
 * Class to validate Urls
 * Created by anhld on 1/6/16.
 */
class PathInspector {
  def getPatterns(filePath: String): mutable.MutableList[Pattern] = {
    var res = new mutable.MutableList[Pattern]()
    for (line <- Source.fromFile(filePath).getLines()) {
      if (line.length != 0) {
        if (!line.startsWith("#")) {
          res += Pattern.compile(line.toLowerCase)
        }
      }
    }
    res
  }

  def isMatched(patterns: mutable.MutableList[Pattern], input: String): Boolean = {
    patterns.foreach(pattern => {
      if (pattern.matcher(input).matches()) {
        return true
      }
    })
    return false
  }

  val excluded = getPatterns("data/config/exclude.txt")
  val domains = getPatterns("data/config/domains.txt")

  /**
   * Check if a URL need to be excluded
   * @param url The url
   * @return If the url's configured to be exclued, returns true.
   *         Otherwise, returns false
   */
  def isExcluded(url: String): Boolean = {
    val lowerUrl = url.toLowerCase
    if (isMatched(excluded, lowerUrl)) {
      return true
    }
    val host = Utils.getHost(lowerUrl)
    if (isMatched(domains, host)) {
      return false
    }

    true
  }
}
