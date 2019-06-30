package main

import com.twitter.finagle.http.Response
import com.twitter.util.Future
import scalaj.http.Base64

import scala.concurrent.Await
import scala.concurrent.duration._


class UrlController(urlDatabaseService: UrlDatabaseService) {

  def toTier(url: String): Future[Response] = {
    val urlToShorten = urlWithoutSlash(url)
    val hashedUrl = Base64.encodeString(urlToShorten)
    val shortenUrl = "tier.app/" + hashedUrl

    urlDatabaseService.insertUrl(url, shortenUrl)
    respondWith(shortenUrl)
  }

  def toUrl(url: String): Future[Response] = {
    val fullUrl = urlDatabaseService.retrieveFullUrl(urlWithoutSlash(url))
    val result = Await.result(fullUrl, 3 seconds)

    respondWith(result)
  }

  private def getHashOf(url: String) = Base64.encodeString(url)

  private def urlWithoutSlash(url: String) = url.stripPrefix("/")

  private def respondWith(url: String): Future[Response] = {
    val response = Response()
    response.setContentString(url)
    Future.value(response)
  }

}