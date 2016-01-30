package fr.xebia.scala

import fr.xebia.scala.control.OptionTools
import fr.xebia.scala.model.Gender.{Female, Male, NotSpecified}
import fr.xebia.scala.model.{Gender, User, UserRepository}

object Step6_Options {

  /** Using optionals **/

  /*
   * TODO 1:
   * Return the first Option not empty from the list specified using the function
   * UserOptions#orElse
   */
  def getUserOrElse(firstOption: Option[User], secondOption: Option[User], thirdOption: Option[User]): Option[User] =
    OptionTools.orElse(OptionTools.orElse(firstOption, secondOption), thirdOption)

  /*
   * TODO 2:
   * If the user specified is present the firstname name, otherwise use the default value
   */
  def getUserNameOrElse(someUser: Option[User], defaultName: String) =
    someUser
      .map(_.firstName)
      .getOrElse(defaultName)

  /*
   * TODO 3:
    *  valid user means the following criteria:
   *   - age <= 25
   *   - with lastName "Lawrence"
   *   - with a specified gender
   * Note:
   *   Use Option#filter
   */
  def validUser(user: User): Option[User] =
    Some(user)
      .filter(_.age <= 25)
      .filter(_.lastName == "Lawrence")
      .filter(_.gender.isDefined)

  /*
   * TODO 4:
   * Follow this instructions to translate the gender:
   *  - Female if gender is "F"
   *  - Male if gender is "M"
   *  - NotSpecified otherwise
   * Note: user pattern matching
   */
  def translateGender(user: User): Gender = user.gender match {
    case Some("F") => Female
    case Some("M") => Male
    case _ => NotSpecified
  }

  /*
   * TODO 5:
   * - use UserRepository#findById
   * - use UserOptions#map
   */
  def getNaiveGenderFromUserId(id: Int): Option[Option[String]] =
    OptionTools.map(UserRepository.findById(id))(_.gender)

  /*
   * TODO 5:
   * - use UserRepository#findById
   * - use UserOptions#flatMap
   */
  def getBetterGenderFromUserId(id: Int): Option[String] =
    OptionTools.flatMap(UserRepository.findById(id))(_.gender)


  /** Syntax sugar with for-comprehension **/

  /*
   * TODO 5:
   * Use for-comprehension; we can't actually test if you
   * use it or not, but for the sake of the exercise please use it :)
   * - call UserRepository#findById(id) and return the gender
   */
  def getGenderFromUserIdSugared(id: Int): Option[String] =
    for {
      user <- UserRepository.findById(id)
      gender <- user.gender
    } yield gender

  /*
   * TODO 5:
   * Get genders from UserRepository#findAll
   * Note: for-comprehension + Traversable#toSeq
   */
  def getAllGenders: Seq[String] =
    (
      for {
        users <- UserRepository.findAll
        genders <- users.gender
      } yield genders)
      .toSeq

}
