package fr.xebia.scala

import fr.xebia.scala.FailureReason.{BoringFilm, NotOldEnough, TooExpensive}
import fr.xebia.scala.model.Film
import fr.xebia.scala.model.Genre.Crime

case class FailureReason(reason: String)

object FailureReason {

  object NotOldEnough extends FailureReason("Film is not old enough")

  object TooExpensive extends FailureReason("Film is too expensive")

  object BoringFilm extends FailureReason("boring film")

}

object ScalaOption {

  type FilmValidation = Option[Film] => Option[Film]

  /*
   * TODO 1_1: Validates if releaseYear <= 1980
   */
  private def validateReleaseDate: FilmValidation = (maybeFilm) => ???

  /*
   * TODO 1_2: Validates if price <= 3
   */
  private def validatePrice: FilmValidation = (maybeFilm) => ???

  /*
   * TODO 1_3: Validates if 'type' contains Crime
   */
  def validateGenre: FilmValidation = (maybeFilm) => ???

  /*
   * TODO 1:
   * call functions validateReleaseDate -> validatePrice -> validateGenre and
   * chain them using 'andThen'
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
   *  - Right, is the success case
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
   * TODO 2_1:
   * Validates if releaseYear <= 1980 otherwise NotOldEnough
   */
  private def validateReleaseDate(film: Film): Either[FailureReason, Film] = ???

  /*
   * TODO 2_2:
   * Validates if price <= 3 otherwise TooExpensive
   */
  private def validatePrice(film: Film): Either[FailureReason, Film] = ???

  /*
   * TODO 2_3:
   * Validates if 'type' contains Crime otherwise BoringFilm
   */
  def validateGenre(film: Film): Either[FailureReason, Film] = ???

  /*
   * TODO 2:
   * we validate error using a fail fast approach in a for-comprehension:
   *
   * validateReleaseDate -> validatePrice -> validateGenre
   *
   * Don't forget to call the 'right' function after each validation
   */
  def validateFilm(film: Film): Either[FailureReason, Film] = ???

}

object CatsXor {

  import cats.data.Xor

  /*
   * Xor is catz alternative to scala's Either
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
   * TODO 3_1: Validates if releaseYear <= 1980 otherwise NotOldEnough
   */
  private def validateReleaseDate(film: Film): Xor[FailureReason, Film] = ???

  /*
   * TODO 3_2: Validates if price <= 3 otherwise TooExpensive
   */
  private def validatePrice(film: Film): FailureReason Xor Film = ???

  /*
   * TODO 3_2: Validates if 'type' contains Crime otherwise BoringFilm
   */
  def validateGenre(film: Film): FailureReason Xor Film = ???

  /*
   * TODO 3: we validate error using a fail fast approach in a for-comprehension:
   *
   * validateReleaseDate -> validatePrice -> validateGenre
   *
   * Xor is right based, so there is no method 'right' to call after each validation
   */
  def validateFilm(film: Film): Xor[FailureReason, Film] = ???

}
