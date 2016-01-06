package com.bigsonata.tools.labrador

import java.io.{File, FileInputStream}

/**
 * Class to load configurations
 * Created by anhld on 1/6/16.
 */
object Configuration {
  private[this] val properties = new PropertiesReader()
  properties.load(new FileInputStream("data/config/crawler.properties"))
  val delay = properties.getIntProperty("crawler.delay", 100)
  val maxDepth = properties.getIntProperty("crawler.max_depth", 10)
  val threads = properties.getIntProperty("crawler.threads", 8)
  val sessionId = java.util.UUID.randomUUID().toString
  val storageDir = Utils.joinPath("data/storage", sessionId)

  // create storage directory on the fly
  new File(storageDir).mkdir()

  override def toString(): String = {
    s"SiteCrawler configurations:\n" +
      s"Session Id: ${sessionId}\n" +
      s"Storage directory: ${storageDir}\n" +
      s"Threads: ${threads}\n" +
      s"Delay: ${delay}ms\n" +
      s"Max Depth: ${maxDepth}ms\n"
  }
}
