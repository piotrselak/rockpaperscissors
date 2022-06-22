package gamelogic

/*
 * Probably the greatest choice of word in the whole universe
*/
sealed trait Weapon

final case object Rock extends Weapon
final case object Paper extends Weapon
final case object Scissors extends Weapon

final case class WrongInput(name: String = "Wrong input!") extends Exception

sealed private case class RNG(seed: Long) {
  def getRand() : (Int, RNG) = {
    val randomValue = ???
    val newSeed = ???

    (randomValue, RNG(newSeed))
  }
}

object Logic {
  def parseUserChoice(input: String): Either[WrongInput, Weapon] = input.toLowerCase match {
    case "r" => Right(Rock)
    case "p" => Right(Paper)
    case "s" => Right(Scissors)
    case _ => Left(WrongInput())
  }


}