package App

import zio.Console._
import zio._

import java.io.IOException

import gamelogic.Logic


object MyApp extends ZIOAppDefault {
  def run = myAppLogic

  def getUserInput: IO[IOException, String] = Console.readLine

  val myAppLogic : IO[IOException, Unit] =
    for {
      _ <- printLine("Hello! Rock - r, paper - p, scissors - s. What's your choice?")
      input <- getUserInput      
    } yield ()
}
