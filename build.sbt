name := "labrador"

version := "1.0"

scalaVersion := "2.11.7"

libraryDependencies += "edu.uci.ics" % "crawler4j" % "4.2"

mainClass in assembly := Some("com.bigsonata.com.tools.labrador.Master")

assemblyMergeStrategy in assembly := {
  case PathList("META-INF", "MANIFEST.MF") => MergeStrategy.discard
  case PathList("META-INF", "ECLIPSEF.RSA") => MergeStrategy.discard
  case PathList("META-INF", "ECLIPSEF.SF") => MergeStrategy.discard
  case PathList("META-INF", "BCKEY.SF") => MergeStrategy.discard
  case PathList("META-INF", "BCKEY.DSA") => MergeStrategy.discard
  case _ => MergeStrategy.last
}