package org.github.api

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route

import scala.concurrent.Future

trait PingPongApi {

  implicit val system: ActorSystem
  implicit val materializer: ActorMaterializer

  lazy val routes: Route = pingRoute

  private val pingRoute: Route = path("ping") {
    get {
      onComplete(Future.successful(HttpEntity(ContentTypes.`text/html(UTF-8)`, "<h1>Pong!!</h1>"))) {
        case scala.util.Success(s) => complete(s)
        case scala.util.Failure(_) => complete("Something went wrong !!!!!")
      }
    }

  }

}
