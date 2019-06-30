package main


import com.twitter.util.Await
import org.specs2.mock.Mockito
import org.specs2.mutable.Specification
import org.specs2.specification.Scope


class UrlControllerSpec extends Specification with Mockito {

  trait Context extends Scope {
    val db = mock[UrlDatabaseService]
    val urlController = new UrlController(db)
    val inserted = Await.result(urlController.toTier("url.com"))
  }

  "when generating a shortened Url " >> {

    "returns a 200 status code" in new Context {
      val result = Await.result(urlController.toTier("url.com"))
      inserted.statusCode === 200
    }
    "returns a new url" in new Context {
      val result = Await.result(urlController.toTier("url.com"))

      inserted.contentString === "tier.app/dXJsLmNvbQ=="
    }

  }

}
