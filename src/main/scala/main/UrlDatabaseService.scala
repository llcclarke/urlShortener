package main

import org.mongodb.scala.{Document, MongoClient, MongoCollection, MongoDatabase}

class UrlDatabaseService {

  val mongoClient: MongoClient = MongoClient("mongodb://localhost")

  val database: MongoDatabase = mongoClient.getDatabase("urlshortener")

  val collection: MongoCollection[Document] = database.getCollection("urls")


}
