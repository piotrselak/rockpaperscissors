package gamelogic

import zio._

import weapons._

import exceptions.WrongInputException

object Logic {

  def playerChoice(input: String): IO[WrongInputException, Weapon] = {
    val lowerCaseInput = input.toLowerCase
    lowerCaseInput match {
      case "r" => ZIO.succeed(Rock)
      case "p" => ZIO.succeed(Paper)
      case "s" => ZIO.succeed(Scissors)
      case _ => ZIO.fail(WrongInputException())
    }
  }

  def computerChoice(): Task[Weapon] = {
    
  }

}