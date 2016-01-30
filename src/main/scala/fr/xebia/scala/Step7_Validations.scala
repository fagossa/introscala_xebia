package fr.xebia.scala

import fr.xebia.scala.model.Film

case class FailureReason(reason: String)

object FailureReason {

  object NotOldEnough extends FailureReason("Film is not old enough")

  object TooExpensive extends FailureReason("Film is too expensive")

  object BoringFilm extends FailureReason("boring film")

}

object ScalaOption {

  type FilmValidation = Option[Film] => Option[Film]

  /*
   * TODO 1_1: validate releaseYear
   * => validateReleaseDate should return a defined Option if releaseYear <= 1980, empty Option otherwise
   */
  private def validateReleaseDate: FilmValidation = (maybeFilm) => ???

  /*
   * TODO 1_2: validate price
   * => validatePrice should filter return a defined Option if price <= 3, empty Option otherwise
   */
  private def validatePrice: FilmValidation = (maybeFilm) => ???

  /*
   * TODO 1_3: validate genre
   * => validateGenre should filter return a defined Option if 'type' contains Crime, empty Option otherwise
   */
  private def validateGenre: FilmValidation = (maybeFilm) => ???

  /*
   * TODO 1: chaining validations
   * => validateFilm should return a defined Option if film passes all previous validations, empty Option otherwise
   * note: use Option#andThen to chain calls
   */
  def validateFilm(film: Film): Option[Film] = ???

}

object ScalaEither {

  /*
   * Either can also be used to validate data. As with options we have two values:
   * 'Left' and 'Right'
   *
   * Where:
   *  - Left, allows to represent the erroneous case
   *  - Right, is the success case, cf. http://goo.gl/kufxDA
   *
   * The advantage over options is that the Left case can handle the error cause.
   *
   * You can create both cases just by calling the constructor, like this:
   * - Right(Person("Marie", 17))
   * - Left("Age must be over 18")
   *
   * Pattern matching works similar to Options.
   *
   */

  /*
   * TODO 2_1: validate releaseYear
   * => validateReleaseDate should return a Right[Film] if releaseYear <= 1980, Left[NotOldEnough] otherwise
   */
  private def validateReleaseDate(film: Film): Either[FailureReason, Film] = ???

  /*
   * TODO 2_2: validate price
   * => validatePrice should return a Right[Film] if price <= 3, Left[TooExpensive] otherwise
   */
  private def validatePrice(film: Film): Either[FailureReason, Film] = ???

  /*
   * TODO 2_3: validate genre
   * => validatePrice should return a Right[Film] if 'type' contains Crime, Left[BoringFilm] otherwise
   * Validates if 'type' contains Crime otherwise BoringFilm
   */
  private def validateGenre(film: Film): Either[FailureReason, Film] = ???

  /*
   * TODO 2: chaining validations with for-comprehension
   * => validateFilm should return a Right[Film] if film passes all previous validations, Left[FailureReason] otherwise
   * note: validate errors using a fail fast approach in a for-comprehension
   * note: don't forget to call the 'right' function after each validation
   */
  def validateFilm(film: Film): Either[FailureReason, Film] = ???

}

object CatsXor {

  import cats.data.Xor

  /*
   * Xor is catz alternative to scala's Either (to learn more about Xor http://goo.gl/FTjwME)
   *
   * Where:
   *  - Xor.left, allows to represent the erroneous case
   *  - Xor.right, is the success case
   *
   * You can create both cases just by calling the constructor, like this:
   * - Xor.right(Person("Marie", 17))
   * - Xor.left("Age must be over 18")
   */

  /*
   * TODO 3_1: validate releaseYear
   * => validateReleaseDate should return a Xor.right[Film] if releaseYear <= 1980, Xor.left[NotOldEnough] otherwise
   */
  private def validateReleaseDate(film: Film): Xor[FailureReason, Film] = ???

  /*
   * TODO 3_2: validate price
   * => validateReleaseDate should return a Xor.right[Film] if price <= 3, Xor.left[TooExpensive] otherwise
   */
  private def validatePrice(film: Film): FailureReason Xor Film = ???

  /*
   * TODO 3_2: validate genre
   * => validateReleaseDate should return a Xor.right[Film] if 'type' contains Crime, Xor.left[BoringFilm] otherwise
   */
  private def validateGenre(film: Film): FailureReason Xor Film = ???

  /*
   * TODO 3: validate film with for-comprehension
   * => validateFilm should return an Xor with film if film passes all previous validations, Xor with failure otherwise
   * note: validate errors using a fail fast approach in a for-comprehension
   * note: Xor is right based, so there is no method 'right' to call after each validation
   */
  def validateFilm(film: Film): Xor[FailureReason, Film] = ???

}
