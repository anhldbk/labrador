package com.bigsonata.tools.labrador

import java.io.File
import java.net.URL

import scala.util.Try

/**
 * Created by anhld on 1/6/16.
 */
object Utils {
  /**
   * Get the host part from a url
   * @param url The url
   * @return The host (if valid). Otherwise, returns null.
   */
  def getHost(url: String): String = {
    val res = Try {
      new URL(url)
    }
    if (res == null) {
      return null
    }
    return res.get.getHost
  }

  def joinPath(dir: String, file: String): String = {
    new File(dir, file).toString
  }
}
