package fr.xebia.scala.model

import fr.xebia.scala.model.UserOptions._

case class User(
                 id: Int,
                 firstName: String,
                 lastName: String,
                 age: Int,
                 gender: Option[String])

object User {

  sealed trait Gender

  object Female extends Gender

  object Male extends Gender

  object NotSpecified extends Gender

  /**
   * Return the first Option not empty from the list specified using the function
   * UserOptions#orElse
   */
  def getUserOrElse(firstOption: Option[User], secondOption: Option[User], thirdOption: Option[User]): Option[User] =
    orElse(orElse(firstOption, secondOption), thirdOption)

  def getUserNameOrElse(someUser: Option[User], defaultName: String) =
    someUser.map(_.firstName).getOrElse(defaultName)

  // Note:
  // Use Option#filter
  // valid user means the following criteria:
  //   - age <= 25
  //   - called Lawrence
  //   - with a specified gender
  def validUser(user: User): Option[User] = {
    Some(user)
      .filter(_.age <= 25)
      .filter(_.lastName == "Lawrence")
      .filter(_.gender.isDefined)
  }

  // Note: user pattern matching
  def translateGender(user: User): Gender = user.gender match {
    case Some(gender) if gender == "F" => Female
    case Some(gender) if gender == "M" => Male
    case None => NotSpecified
  }

  // Note:
  // - use UserRepository#findById
  // - use UserOptions#map
  def getNaiveGenderFromUserId(id: Int): Option[Option[String]] =
    map(UserRepository.findById(id))((u) => u.gender)

  // Note:
  // - use UserRepository#findById
  // - use UserOptions#flatMap
  def getBetterGenderFromUserId(id: Int): Option[String] =
    flatMap(UserRepository.findById(id))((u) => u.gender)

}

object UserOptions {

  /**
   * apply the mapper function to the option specified and wrap the
   * result
   */
  def map[A, B](maybe: Option[A])(mapper: A => B): Option[B] = {
    if (maybe.isDefined) {
      Some(mapper(maybe.get))
    } else {
      None
    }
  }

  /**
   * apply the mapper function to the option specified
   */
  def flatMap[A, B](maybe: Option[A])(mapper: A => Option[B]): Option[B] = {
    if (maybe.isDefined) {
      mapper(maybe.get)
    } else {
      None
    }
  }


  /**
   * if the first parameter is None return the second
   */
  def orElse[A](firstOption: Option[A], secondOption: Option[A]): Option[A] =
    if (firstOption.isDefined) {
      firstOption
    } else {
      secondOption
    }

}

object UserRepository {
  private val users = Map(1 -> User(1, "John", "Doe", 32, Some("M")),
    2 -> User(2, "Johanna", "Doe", 30, None))

  def findById(id: Int): Option[User] = users.get(id)

  def findAll = users.values
}
