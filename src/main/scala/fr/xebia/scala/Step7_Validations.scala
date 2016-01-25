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
  private def validateReleaseDate: FilmValidation = (maybeFilm) =>
    maybeFilm.filter(_.releaseYear <= 1980)

  /*
   * TODO 1_2: Validates if price <= 3
   */
  private def validatePrice: FilmValidation = (maybeFilm) =>
    maybeFilm.filter(_.price <= 3)

  /*
   * TODO 1_3: Validates if 'type' contains Crime
   */
  def validateGenre: FilmValidation = (maybeFilm) =>
    maybeFilm.filter(_.`type`.contains(Crime))

  /*
   * TODO 1:
   * call functions validateReleaseDate -> validatePrice -> validateGenre and
   * chain them using 'andThen'
   */
  def validateFilm(film: Film): Option[Film] =
    validateReleaseDate.andThen(validatePrice).andThen(validateGenre)(Some(film))

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
  private def validateReleaseDate(film: Film): Either[FailureReason, Film] =
    if (film.releaseYear <= 1980) Right(film) else Left(NotOldEnough)

  /*
   * TODO 2_2:
   * Validates if price <= 3 otherwise TooExpensive
   */
  private def validatePrice(film: Film): Either[FailureReason, Film] =
    if (film.price <= 3) Right(film) else Left(TooExpensive)

  /*
   * TODO 2_3:
   * Validates if 'type' contains Crime otherwise BoringFilm
   */
  def validateGenre(film: Film): Either[FailureReason, Film] =
    if (film.`type`.contains(Crime)) Right(film) else Left(BoringFilm)

  /*
   * TODO 2:
   * we validate error using a fail fast approach in a for-comprehension:
   *
   * validateReleaseDate -> validatePrice -> validateGenre
   *
   * Don't forget to call the 'right' function after each validation
   */
  def validateFilm(film: Film): Either[FailureReason, Film] =
    for {
      oldEnoughFilm <- validateReleaseDate(film).right
      cheapEnoughFilm <- validatePrice(film).right
      thrillerFilm <- validateGenre(film).right
    } yield thrillerFilm

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
  private def validateReleaseDate(film: Film): Xor[FailureReason, Film] =
    if (film.releaseYear <= 1980) Xor.right(film) else Xor.left(NotOldEnough)

  /*
   * TODO 3_2: Validates if price <= 3 otherwise TooExpensive
   */
  private def validatePrice(film: Film): FailureReason Xor Film =
    if (film.price <= 3) Xor.right(film) else Xor.left(TooExpensive)

  /*
   * TODO 3_2: Validates if 'type' contains Crime otherwise BoringFilm
   */
  def validateGenre(film: Film): FailureReason Xor Film =
    if (film.`type`.contains(Crime)) Xor.right(film) else Xor.left(BoringFilm)

  /*
   * TODO 3: we validate error using a fail fast approach in a for-comprehension:
   *
   * validateReleaseDate -> validatePrice -> validateGenre
   *
   * Xor is right based, so there is no method 'right' to call after each validation
   */
  def validateFilm(film: Film): Xor[FailureReason, Film] =
    for {
      oldEnoughFilm <- validateReleaseDate(film)
      cheapEnoughFilm <- validatePrice(film)
      thrillerFilm <- validateGenre(film)
    } yield thrillerFilm

}
