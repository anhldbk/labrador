package com.bigsonata.tools.labrador

import java.io.{File, FileWriter, PrintWriter}

/**
 * Class that abstracts storages for retrived content
 * Created by anhld on 1/6/16.
 */
class Storage(outputPath: String) {
  private[this] var id = 0
  private[this] val locker = new Object
  new File(outputPath).mkdirs()

  /**
   * Store a new document (thread safe)
   * @param url The document's url
   * @param html  Its html code
   */
  def store(url: String, html: String) {
    locker synchronized {
      // append info
      new FileWriter(Utils.joinPath(outputPath, "site.info"), true) {
        write(url + "\n")
        close
      }

      // write file
      new PrintWriter(Utils.joinPath(outputPath, id.toString)) {
        write(html);
        close()
      }
      id += 1
    }
  }
}
