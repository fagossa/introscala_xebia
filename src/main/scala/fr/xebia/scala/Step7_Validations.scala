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
   * TODO 1_1: validate releaseYear
   * => validateReleaseDate should return a defined Option if releaseYear <= 1980, empty Option otherwise
   */
  private def validateReleaseDate: FilmValidation = (maybeFilm) =>
    maybeFilm.filter(_.releaseYear <= 1980)

  /*
   * TODO 1_2: validate price
   * => validatePrice should filter return a defined Option if price <= 3, empty Option otherwise
   */
  private def validatePrice: FilmValidation = (maybeFilm) =>
    maybeFilm.filter(_.price <= 3)

  /*
   * TODO 1_3: validate genre
   * => validateGenre should filter return a defined Option if 'type' contains Crime, empty Option otherwise
   */
  private def validateGenre: FilmValidation = (maybeFilm) =>
    maybeFilm.filter(_.`type`.contains(Crime))

  /*
   * TODO 1: chaining validations
   * => validateFilm should return a defined Option if film passes all previous validations, empty Option otherwise
   * note: use Function1#andThen to chain calls
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
  private def validateReleaseDate(film: Film): Either[FailureReason, Film] =
    if (film.releaseYear <= 1980) Right(film) else Left(NotOldEnough)

  /*
   * TODO 2_2: validate price
   * => validatePrice should return a Right[Film] if price <= 3, Left[TooExpensive] otherwise
   */
  private def validatePrice(film: Film): Either[FailureReason, Film] =
    if (film.price <= 3) Right(film) else Left(TooExpensive)

  /*
   * TODO 2_3: validate genre
   * => validatePrice should return a Right[Film] if 'type' contains Crime, Left[BoringFilm] otherwise
   * Validates if 'type' contains Crime otherwise BoringFilm
   */
  private def validateGenre(film: Film): Either[FailureReason, Film] =
    if (film.`type`.contains(Crime)) Right(film) else Left(BoringFilm)

  /*
   * TODO 2: chaining validations with for-comprehension
   * => validateFilm should return a Right[Film] if film passes all previous validations, Left[FailureReason] otherwise
   * note: validate errors using a fail fast approach in a for-comprehension
   * note: don't forget to call the 'right' function after each validation
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
  private def validateReleaseDate(film: Film): Xor[FailureReason, Film] =
    if (film.releaseYear <= 1980) Xor.right(film) else Xor.left(NotOldEnough)

  /*
   * TODO 3_2: validate price
   * => validateReleaseDate should return a Xor.right[Film] if price <= 3, Xor.left[TooExpensive] otherwise
   */
  private def validatePrice(film: Film): FailureReason Xor Film =
    if (film.price <= 3) Xor.right(film) else Xor.left(TooExpensive)

  /*
   * TODO 3_2: validate genre
   * => validateReleaseDate should return a Xor.right[Film] if 'type' contains Crime, Xor.left[BoringFilm] otherwise
   */
  private def validateGenre(film: Film): FailureReason Xor Film =
    if (film.`type`.contains(Crime)) Xor.right(film) else Xor.left(BoringFilm)

  /*
   * TODO 3: validate film with for-comprehension
   * => validateFilm should return an Xor with film if film passes all previous validations, Xor with failure otherwise
   * note: validate errors using a fail fast approach in a for-comprehension
   * note: Xor is right based, so there is no method 'right' to call after each validation
   */
  def validateFilm(film: Film): Xor[FailureReason, Film] =
    for {
      oldEnoughFilm <- validateReleaseDate(film)
      cheapEnoughFilm <- validatePrice(film)
      thrillerFilm <- validateGenre(film)
    } yield thrillerFilm

}
