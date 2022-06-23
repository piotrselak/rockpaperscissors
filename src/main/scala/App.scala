package App

import zio.Console._
import zio._

import java.io.IOException

import gamelogic._


object MyApp extends ZIOAppDefault {
  override def run = myAppLogic

  def getUserInput: IO[IOException, String] = Console.readLine

  def getResult(input: String, seed: Long): String = {
    val result = Logic.evaluateGame(
      Logic.parseUserChoice(input),
      Logic.computerChoice(seed)
    )

    result match {
      case Right(x) => x.name
      case Left(error) => error.get
    }
  }

  val myAppLogic : IO[IOException, Unit] =
    for {
      _ <- printLine("Hello, seed?")
      seed <- getUserInput
      _ <- printLine("Rock - r, paper - p, scissors - s. What's your choice?")
      input <- getUserInput
      _ <- printLine(getResult(input, seed.toLong))
    } yield ()
}
