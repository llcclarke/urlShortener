package main

import org.mongodb.scala.model.Filters.equal
import org.mongodb.scala.{Completed, Document, MongoClient, MongoCollection, MongoDatabase, Observer}

import scala.concurrent.ExecutionContext.Implicits._

class UrlDatabaseService {

  val mongoClient: MongoClient = MongoClient("mongodb://localhost")

  val database: MongoDatabase = mongoClient.getDatabase("urlshortener")

  val collection: MongoCollection[Document] = database.getCollection("urls")

  def insertUrl(longUrl: String, shortUrl: String) = collection.insertOne(Document("url" -> longUrl, "tinyUrl" -> shortUrl)).subscribe(new Observer[Completed] {
    override def onNext(result: Completed): Unit = println("Inserted")

    override def onError(e: Throwable): Unit = println(" \n\nFailed " + e + "\n\n")

    override def onComplete(): Unit = println("Completed")
  })

  def retrieveFullUrl(shortUrl: String) =
    collection.find(equal("tinyUrl", shortUrl))
    .first()
    .toFuture()
    .map(doc => doc.getString("url"))


}
