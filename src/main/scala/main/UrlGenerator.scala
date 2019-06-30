package main

import com.twitter.finagle.http.{Response, Status, Version}
import com.twitter.io.Buf.Utf8
import com.twitter.io.Reader
import com.twitter.util.Future
import main.Server.collection
import org.mongodb.scala.{Completed, Document, Observer}
import scalaj.http.Base64

class UrlGenerator {

  def toTier(url: String) = {
    val base = "tier.app/"
    val shortenUrl =  getHashOf(url)

    collection.insertOne(Document("url" -> url, "tinyUrl" -> shortenUrl)).subscribe(new Observer[Completed] {
      override def onNext(result: Completed): Unit = println("Inserted")

      override def onError(e: Throwable): Unit = println(" \n\nFailed " + e + "\n\n")

      override def onComplete(): Unit = println("Completed")
    })

    Future(Response(Version.Http11,Status.Ok, Reader.fromBuf(Utf8(s"${base + shortenUrl}"))))
  }

  private def getHashOf(url: String) = Base64.encodeString(url)

}