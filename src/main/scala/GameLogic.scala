package gamelogic


sealed trait Result {
  val name: String
}

final case object Win extends Result {
  override val name = "Win"
}

final case object Draw extends Result {
  override val name = "Draw"
}

final case object Lose extends Result {
  override val name = "Lose"
}


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
  override val weight = 3 
}

final case class WrongInput() {
  def get: String = "Wrong input"
}

sealed private case class RNG(seed: Long) {
  def getRand(max: Int = 1): (Long, RNG) = {
    val newSeed = ((seed * 17) & 321)
    val randomValue = (newSeed >> 3) % max 
    (randomValue, RNG(newSeed))
  }
}

object Logic {
  def parseUserChoice(input: String): Option[Weapon] = input.toLowerCase match {
    case "r" => Some(Rock)
    case "p" => Some(Paper)
    case "s" => Some(Scissors)
    case _ => None
  }

  def computerChoice(seed: Long): Weapon = {
    val randomValue = RNG(seed).getRand(3)._1
    randomValue match {
      case 0 => Rock
      case 1 => Paper
      case 2 => Scissors
    }
  }

  def evaluateGame(playerChoice: Option[Weapon], computerChoice: Weapon): Either[WrongInput, Result] = (playerChoice, computerChoice) match {
    case (wrong, x) if (wrong == None) => Left(WrongInput())
    case (Some(playerOption), computerOption) if (playerOption.weight == computerOption.weight + 1) => Right(Win)
    case (Some(playerOption), computerOption) if (playerOption.weight == computerOption.weight) => Right(Lose)
    case _ => Right(Draw)
  }
}