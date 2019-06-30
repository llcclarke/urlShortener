package main

import com.twitter.finagle.http.Response
import com.twitter.util.Future
import org.mongodb.scala.model.Filters._
import org.mongodb.scala.{Completed, Document, Observer}
import scalaj.http.Base64

import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits._
import scala.concurrent.duration._


class UrlController {
  val db = new UrlDatabaseService
  val collection = db.collection

  def toTier(url: String) = {

    val urlToShorten = urlWithoutSlash(url)

    val shortenUrl = "tier.app/" + Base64.encodeString(urlToShorten)

    collection.insertOne(Document("url" -> urlToShorten, "tinyUrl" -> shortenUrl)).subscribe(new Observer[Completed] {
      override def onNext(result: Completed): Unit = println("Inserted")

      override def onError(e: Throwable): Unit = println(" \n\nFailed " + e + "\n\n")

      override def onComplete(): Unit = println("Completed")
    })
    respondWith(shortenUrl)

  }

  def toUrl(url: String) = {

    val documentForUrl = collection.find(equal("tinyUrl", urlWithoutSlash(url)))
      .first()
      .toFuture()
      .map(doc => doc.getString("url"))

    val result = Await.result(documentForUrl, 2 seconds)

    respondWith(result)
  }

  private def getHashOf(url: String) = Base64.encodeString(url)

  private def urlWithoutSlash(url: String) = url.stripPrefix("/")

  private def respondWith(url: String) = {
    val response = Response()

    response.setContentString(url)

    Future.value(response)
  }

}