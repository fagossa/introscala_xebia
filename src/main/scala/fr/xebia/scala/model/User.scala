package fr.xebia.scala.model

import fr.xebia.scala.model.UserOptions._

case class User(
                 id: Int,
                 firstName: String,
                 lastName: String,
                 age: Int,
                 gender: Option[String])

object User {
  def getUserOrElse(firstOption: Option[User], secondOption: Option[User], thirdOption: Option[User]) =
    orElse(orElse(firstOption, secondOption), thirdOption)

  def getUserNameOrElse(someUser: Option[User], defaultName: String) =
    someUser.map(_.firstName).getOrElse(defaultName)

}

object UserOptions {

  def orElse[A](firstOption: Option[A], secondOption: Option[A]): Option[A] =
    if (firstOption.isDefined) {
      firstOption
    } else {
      secondOption
    }

}

object UserRepository {
  private val users = Map(1 -> User(1, "John", "Doe", 32, Some("male")),
    2 -> User(2, "Johanna", "Doe", 30, None))

  def findById(id: Int): Option[User] = users.get(id)

  def findAll = users.values
}
