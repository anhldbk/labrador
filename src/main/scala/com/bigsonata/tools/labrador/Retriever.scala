package com.bigsonata.tools.labrador

import java.util.concurrent.ConcurrentHashMap

import edu.uci.ics.crawler4j.crawler.{Page, WebCrawler}
import edu.uci.ics.crawler4j.parser.HtmlParseData
import edu.uci.ics.crawler4j.url.WebURL

/**
 * Class to retrieve web pages
 */
class Retriever extends WebCrawler {
  val pathInspector = new PathInspector

  override def shouldVisit(referringPage: Page, url: WebURL): Boolean = {
    !pathInspector.isExcluded(url.getURL)
  }

  override def visit(page: Page) {
    println(s"Fetched ${page.getWebURL.getURL} from ${page.getWebURL.getAnchor}")
    page.getParseData match {
      case data: HtmlParseData =>
        val html = data.getHtml
        val url = page.getWebURL.getURL
        Retriever.store(url, html)
        getMyController.addSeed(url)
    }
  }
}

object Retriever {
  val siteStorage: ConcurrentHashMap[String, Storage] = new ConcurrentHashMap()
  var storageDir = Configuration.storageDir

  def setStorageDir(dir: String): Unit = {
    storageDir = dir
  }

  def getStorage(url: String): Storage = {
    val host = Utils.getHost(url)
    if (!siteStorage.containsKey(host)) {
      siteStorage.put(host, new Storage(Utils.joinPath(storageDir, host)))
    }
    siteStorage.get(host)
  }

  def store(url: String, html: String) = {
    getStorage(url).store(url, html)
  }
}
