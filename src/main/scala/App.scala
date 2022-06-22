package App

import zio.Console._
import zio._

import java.io.IOException

import gamelogic._


object MyApp extends ZIOAppDefault {
  override def run = myAppLogic

  def getUserInput: IO[IOException, String] = Console.readLine

  def getResult(input: String, seed: Long): String = 
    Logic.evaluateGame(
      Logic.parseUserChoice(input),
      Logic.computerChoice(seed)
    ).name

  val myAppLogic : IO[IOException, Unit] =
    for {
      _ <- printLine("Hello, seed?")
      seed <- getUserInput
      _ <- printLine("Rock - r, paper - p, scissors - s. What's your choice?")
      input <- getUserInput
      _ <- printLine(getResult(input, seed.toLong))
    } yield ()
}
