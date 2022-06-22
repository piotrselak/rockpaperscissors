package exceptions

final case class WrongInputException(private val name: String = "Wrong user input")
