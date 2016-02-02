package fr.xebia.scala

import fr.xebia.scala.model.Gender.{Female, Male, NotSpecified}
import fr.xebia.scala.model.{Film, Gender, User, UserRepository}

object Step6_Options {

  /** Using optionals **/

  /*
   * TODO 1: option
   * => getUserOrElse should return the first non empty option between first, second and third options (use if else expression)
   */
  def getUserOrElse(option1: Option[User], option2: Option[User], option3: Option[User]): Option[User] =
    if (option1.isDefined) option1 else if (option2.isDefined) option2 else option3

  /*
   * TODO 2: option and mapping
   * => getUserNameOrElse should return user's name if present, defaultName otherwise
   */
  def getUserNameOrElse(userOption: Option[User], defaultName: String): String =
    userOption.map(_.firstName).getOrElse(defaultName)

  /*
   * TODO 3: option filtering
   * => validUser should return an empty Option if given user does not match one of the following predicate:
   * - age <= 25
   * - lastName == "Lawrence"
   * - gender must be defined
   * note: use Option#filter
   */
  def validUser(user: User): Option[User] =
    Some(user)
      .filter(_.age <= 25)
      .filter(_.lastName == "Lawrence")
      .filter(_.gender.isDefined)

  /*
   * TODO 4: option matching
   * => translateGender should return:
   * - Female if user's gender is "F"
   * - Male if gender is "M"
   * - NotSpecified otherwise
   * note: user pattern matching
   */
  def translateGender(user: User): Gender = user.gender match {
    case Some("F") => Female
    case Some("M") => Male
    case _ => NotSpecified
  }

  /*
   * TODO 5: option of options
   * => getNaiveGenderFromUserId should return an Option[Option[String]] containing user's gender for the user corresponding to the given id
   * note: use UserRepository#findById to retrieve the user
   */
  def getNaiveGenderFromUserId(id: Int): Option[Option[String]] =
    UserRepository.findById(id).map(_.gender)

  /*
   * TODO 5: option flat map
   * => getBetterGenderFromUserId should return a gender Option for the user corresponding to the given id
   * note: use UserRepository#findById to retrieve the user
   */
  def getBetterGenderFromUserId(id: Int): Option[String] =
    UserRepository.findById(id).flatMap(_.gender)


  /** Syntax sugar with for-comprehension **/

  /*
   * TODO 5: option and for-comprehension (flat map sugar)
   * => getGenderFromUserIdSugared should return an Option containing user's gender
   * note: we can't actually test if you use for-comprehension or not, but for the sake of the exercise please use it :)
   * note: use UserRepository#findById to retrieve the user
   * note: for-comprehension can be used like this:
   *   for {
   *     result1 <- SomeContainer
   *     result2 <- SomeOtherContainer
   *   } yield result1 + result2
   */
  def getGenderFromUserIdSugared(id: Int): Option[String] =
    for {
      user <- UserRepository.findById(id)
      gender <- user.gender
    } yield gender

  /*
   * TODO 5: option, iterable and for-comprehension
   * => getAllGenders should return a Sequence of Option containing all users genders
   * note: use UserRepository#findAll to retrieve all users
   * note: use for-comprehension to flat map users' genders
   * note: use Traversable#toSeq to convert the computed iterable to a Seq[String]
   */
  def getAllGenders: Seq[String] =
    (
      for {
        users <- UserRepository.findAll
        genders <- users.gender
      } yield genders)
      .toSeq

  /*
   * TODO 6: option computation
   * => calculateTotalPrice should return an Option[Double] containing sum of film price * qty if films size == qty size, None otherwise
   * note: use Step5_Collections#zip to zip film and respective quantity together
   */
  def calculateTotalPrice(films: List[Film], qty: List[Int]): Option[Double] =
    if (films.size == qty.size) {
      Some(Step5_Collections.zip(films, qty)
        .map { case (film, amt) => film.price * amt }
        .sum)
    } else {
      None
    }

  /*
   * TODO 7: option computation again
   * => calculateTotalPriceWithIndex should return an Option containing a list of index and price * qty for each film
   * note: use Step5_Collections#zip and Step5_Collections#zipWithIndex to zip film with index and respective quantity together
   */
  def calculateTotalPriceWithIndex(films: List[Film], qty: List[Int]): Option[List[(Int, Double)]] =
    if (films.size == qty.size) {
      Some {
        Step5_Collections.zipWithIndex(Step5_Collections.zip(films, qty))
          .map { case ((f: Film, qty: Int), index) => (index, f.price * qty) }
      }
    } else {
      None
    }

}
