package gamelogic


sealed trait Result

final case object Win extends Result
final case object Draw extends Result
final case object Lose extends Result

/*
 * Probably the greatest choice of word in the whole universe
*/
sealed trait Weapon {
  val weight: Int
}

final case object Rock extends Weapon {
  override val weight = 1  
}

final case object Paper extends Weapon {
  override val weight = 2
}

final case object Scissors extends Weapon {
  override val weight = 1  
}

final case class WrongInput(name: String = "Invalid input.") extends Exception

sealed private case class RNG(seed: Long) {
  def getRand(max: Int = 1): (Long, RNG) = {
    val newSeed = ((seed * 17) & 321)
    val randomValue = (newSeed >> 3) % max 
    (randomValue, RNG(newSeed))
  }
}

object Logic {
  private def parseUserChoice(input: String): Either[WrongInput, Weapon] = input.toLowerCase match {
    case "r" => Right(Rock)
    case "p" => Right(Paper)
    case "s" => Right(Scissors)
    case _ => Left(WrongInput())
  }

  private def computerChoice(seed: Long): Weapon = {
    val randomValue = RNG(seed).getRand(3)._1
    randomValue match {
      case 0 => Rock
      case 1 => Paper
      case 2 => Scissors
    }
  }

  def evaluateGame(playerChoice: Weapon, computerChoice: Weapon): Result = (playerChoice, computerChoice) match {
    case (Rock, Paper) => Lose
    case (Rock, Scissors) => Win
    case (Paper, Scissors) => Lose
    case (Paper, Rock) => Win
    case (Scissors, Rock) => Lose
    case (Scissors, Paper) => Win
    case _ => Draw
  }
}