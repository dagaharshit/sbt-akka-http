package org.github.api

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer

import scala.concurrent.ExecutionContextExecutor
import scala.io.StdIn

object MainApp extends App {

  implicit val mainActorSystem: ActorSystem = ActorSystem("my-actor-system")
  implicit val mainSystemMaterializer: ActorMaterializer = ActorMaterializer()

  object PingPongApiObject extends PingPongApi {
    override implicit val system: ActorSystem = mainActorSystem
    override implicit val materializer: ActorMaterializer = mainSystemMaterializer
  }

  implicit val ec: scala.concurrent.ExecutionContext = scala.concurrent.ExecutionContext.global
  val bindingFuture = Http().bindAndHandle(PingPongApiObject.routes, "localhost", 9000)

  println(s"Server online at http://localhost:9000/\nPress RETURN to stop...")
  StdIn.readLine() // let it run until user presses return

  implicit val dispatcher: ExecutionContextExecutor = mainActorSystem.dispatcher

  bindingFuture.flatMap(_.unbind()).onComplete(_ => mainActorSystem.terminate())

}
