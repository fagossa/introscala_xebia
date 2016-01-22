package fr.xebia.scala

import fr.xebia.scala.control.OptionTools._
import fr.xebia.scala.model.Gender.{Female, Male, NotSpecified}
import fr.xebia.scala.model.{Gender, User, UserRepository}

object Step6_Options {

  /** Using optionals **/

  /*
   * TODO 1:
   * Return the first Option not empty from the list specified using the function
   * UserOptions#orElse
   */
  def getUserOrElse(firstOption: Option[User], secondOption: Option[User], thirdOption: Option[User]): Option[User] = ???

  /*
   * TODO 2:
   * If the user specified is present the his/her name, otherwise use the default value
   */
  def getUserNameOrElse(someUser: Option[User], defaultName: String) : String = ???

  /*
   * TODO 3:
    *  valid user means the following criteria:
   *   - age <= 25
   *   - called "Lawrence"
   *   - with a specified gender
   * Note:
   *   Use Option#filter
   */
  def validUser(user: User): Option[User] = ???

  /*
   * TODO 4:
   * Follow this instructions to translate the gender:
   *  - Female if gender is "F"
   *  - Male if gender is "M"
   *  - NotSpecified otherwise
   * Note: user pattern matching
   */
  def translateGender(user: User): Gender = ???

  /*
   * TODO 5:
   * - use UserRepository#findById
   * - use UserOptions#map
   */
  def getNaiveGenderFromUserId(id: Int): Option[Option[String]] = ???

  /*
   * TODO 5:
   * - use UserRepository#findById
   * - use UserOptions#flatMap
   */
  def getBetterGenderFromUserId(id: Int): Option[String] = ???


  /** Syntax sugar with for-comprehension **/

  /*
   * TODO 5:
   * Use for-comprehension; we can't actually test if you
   * use it or not, but for the sake of the exercise please use it :)
   * - call UserRepository#findById(id) and return the gender
   */
  def getGenderFromUserIdSugared(id: Int): Option[String] = ???

  /*
   * TODO 5:
   * Get genders from UserRepository#findAll
   * Note: for-comprehension + Traversable#toSeq
   */
  def getAllGenders: Seq[String] = ???

}
