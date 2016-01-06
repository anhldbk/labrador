package com.bigsonata.tools.labrador

import java.util.Properties

/**
 * Created by anhld on 1/6/16.
 */
class PropertiesReader extends Properties{
  /**
   * Get an Int property
   * @param key Ihe property's key
   * @param defaultValue  the default value to return if there's no such key
   * @return  The associated value
   */
  def getIntProperty(key: String, defaultValue: Int): Int = {
    val value = this.getProperty(key)
    if (value == null){
      return defaultValue
    }
    value.toInt
  }
}
