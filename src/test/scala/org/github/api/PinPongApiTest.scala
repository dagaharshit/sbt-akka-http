package org.github.api

import akka.actor.ActorSystem
import akka.http.scaladsl.testkit.ScalatestRouteTest
import akka.stream.ActorMaterializer
import akka.testkit.TestKit
import org.scalatest.BeforeAndAfterAll
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpecLike

class PinPongApiTest extends AnyWordSpecLike with Matchers with BeforeAndAfterAll with ScalatestRouteTest {
  test =>

  val pingApiObj: PingPongApi = new PingPongApi {
    override implicit val system: ActorSystem = test.system
    override implicit val materializer: ActorMaterializer = ActorMaterializer()
  }

  "The PingPongApi" should {
    "return a 'PONG!' response for GET requests to /ping" in {
      Get("/ping") ~> pingApiObj.routes ~> check {
        responseAs[String] shouldEqual "<h1>Pong!!</h1>"
      }
    }
  }

  override def afterAll: Unit = {
    TestKit.shutdownActorSystem(test.system)
  }

}
