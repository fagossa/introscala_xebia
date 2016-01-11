package fr.xebia.scala.model

import fr.xebia.scala.control.OptionTools
import fr.xebia.scala.model.Gender.{NotSpecified, Female, Male}
import OptionTools._

case class User(
                 id: Int,
                 firstName: String,
                 lastName: String,
                 age: Int,
                 gender: Option[String])

sealed trait Gender

object Gender {

  object Female extends Gender

  object Male extends Gender

  object NotSpecified extends Gender
}


object UserRepository {

  private val users = Map(
    1 -> User(1, "John", "Doe", 32, Some("M")),
    2 -> User(2, "Johanna", "Doe", 30, None),
    3 -> User(2, "Johanna", "Janitor", 27, Some("F"))
  )

  def findById(id: Int): Option[User] = users.get(id)

  def findAll = users.values

}
