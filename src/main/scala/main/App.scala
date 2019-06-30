package main

import java.net.InetSocketAddress

import com.twitter.finagle.builder.ServerBuilder
import com.twitter.finagle.http.{Method, Request, Response}
import com.twitter.finagle.{Http, Service}
import com.twitter.util.{Await, Future}


object Server extends App {

  val urlGenerator = new UrlGenerator
  val db = new UrlDatabaseService
  val collection = db.collection


  val router = new Service[Request, Response] {

    override def apply(req: Request): Future[Response] = {
      (req.method, req.path) match {
        case (Method.Put, url) => urlGenerator.toTier(url)
      }
    }
  }

  val server = ServerBuilder()
    .stack(Http.server)
    .name("router")
    .bindTo(new InetSocketAddress(8080))
    .build(router)

  Await.ready(server)
}













