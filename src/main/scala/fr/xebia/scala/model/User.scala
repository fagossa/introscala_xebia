package fr.xebia.scala.model

import fr.xebia.scala.model.Gender.{NotSpecified, Female, Male}
import fr.xebia.scala.control.UserOptions._

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

object User {

  /**************************************
    * Using optionals
    **************************************/

  /*
   * Return the first Option not empty from the list specified using the function
   * UserOptions#orElse
   */
  def getUserOrElse(firstOption: Option[User], secondOption: Option[User], thirdOption: Option[User]): Option[User] =
    orElse(
      orElse(firstOption, secondOption),
      thirdOption)

  /*
   * If the user specified is present the his/her name, otherwise use the default value
   */
  def getUserNameOrElse(someUser: Option[User], defaultName: String) =
    someUser
      .map(_.firstName)
      .getOrElse(defaultName)

  /*
   * valid user means the following criteria:
   *   - age <= 25
   *   - called "Lawrence"
   *   - with a specified gender
   * Note:
   *   Use Option#filter
   */
  def validUser(user: User): Option[User] = {
    Some(user)
      .filter(_.age <= 25)
      .filter(_.lastName == "Lawrence")
      .filter(_.gender.isDefined)
  }

  /*
   * Follow this instructions to translate the gender:
   *  - Female if gender is "F"
   *  - Male if gender is "M"
   *  - NotSpecified otherwise
   * Note: user pattern matching
   */
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



  /**************************************
   * Syntax sugar with for-comprehension
   **************************************/

  /*
   * Note: Use for-comprehension; we can't actually test if you
   * use it or not, but for the sake of the exercise please use it :)
   */
  def getGenderFromUserIdSugared(id: Int): Option[String] =
    for {
      user <- UserRepository.findById(id)
      gender <- user.gender
    } yield gender

  /*
   * Get genders from UserRepository#findAll
   * Note: for-comprehension + Traversable#toSeq
   */
  def getAllGenders: Seq[String] =
    (for {
      users <- UserRepository.findAll
      genders <- users.gender
    } yield genders).toSeq

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
