name := "UrlShortener"

scalaVersion := "2.12.6"

version := "1.0"

libraryDependencies += "com.twitter" %% "finagle-http" % "19.6.0"
libraryDependencies += "io.github.finagle" %% "finagle-postgres" % "0.11.0"

libraryDependencies += "org.specs2" %% "specs2-core" % "4.3.4" % "test"
libraryDependencies += "org.specs2" %% "specs2-mock" % "4.3.4" % "test"
libraryDependencies +=  "org.scalaj" %% "scalaj-http" % "2.4.2"

libraryDependencies += "org.mongodb.scala" %% "mongo-scala-driver" % "2.6.0"

