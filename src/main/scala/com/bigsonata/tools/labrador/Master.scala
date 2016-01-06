package com.bigsonata.tools.labrador

import java.io.File

import edu.uci.ics.crawler4j.crawler.{CrawlConfig, CrawlController}
import edu.uci.ics.crawler4j.fetcher.PageFetcher
import edu.uci.ics.crawler4j.robotstxt.{RobotstxtConfig, RobotstxtServer}

import scala.io.Source

/**
 * Main entry
 * Created by anhld on 1/7/16.
 */
object Master {
  def main(args: Array[String]): Unit = {
    println("Initializing SiteCrawler...")
    println(Configuration.toString)
    val crawlStorageFolder = "data/db"
    new File(crawlStorageFolder).mkdirs()
    val numberOfCrawlers = Configuration.threads


    val config = new CrawlConfig()
    config.setCrawlStorageFolder(crawlStorageFolder)
    config.setPolitenessDelay(Configuration.delay)
    config.setMaxDepthOfCrawling(Configuration.maxDepth)
    config.setResumableCrawling(true) // Enable as required

    val pageFetcher = new PageFetcher(config)
    val robotstxtConfig = new RobotstxtConfig()
    val robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher)
    val controller = new CrawlController(config, pageFetcher, robotstxtServer)

    // Read seeds file
    for (line <- Source.fromFile("data/config/seeds.txt").getLines()) {
      controller.addSeed(line)
    }

    controller.start(classOf[Retriever], numberOfCrawlers)
    println("Done")
  }
}
