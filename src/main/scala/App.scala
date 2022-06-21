package App

import zio.Console._
import zio._

object MyApp extends ZIOAppDefault {
  def run = myAppLogic

  val myAppLogic =
    for {
      _ <- printLine("Hello! Rock - r, paper - p, scissors - s. What's your choice?")
      
    } yield ()
}
