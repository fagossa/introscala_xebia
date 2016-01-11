package fr.xebia.scala

import fr.xebia.scala.FailureReason.{BoringFilm, TooExpensive, NotOldEnough}
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
   * Validates if releaseYear <= 1980
   */
  private def validateReleaseDate: FilmValidation = (maybeFilm) =>
    maybeFilm.filter(_.releaseYear <= 1980)

  /*
   * Validates if price <= 3
   */
  private def validatePrice: FilmValidation = (maybeFilm) =>
    maybeFilm.filter(_.price <= 3)

  /*
   * Validates if 'type' contains Crime
   */
  def validateGenre: FilmValidation = (maybeFilm) =>
    maybeFilm.filter(_.`type`.contains(Crime))

  /*
   * validateReleaseDate -> validatePrice -> validateGenre
   */
  def validateFilm(film: Film): Option[Film] =
    (validateReleaseDate andThen validatePrice andThen validateGenre)(Some(film))

}

object ScalaEither {

  /*
   * Validates if releaseYear <= 1980 otherwise NotOldEnough
   */
  private def validateReleaseDate(film: Film): Either[FailureReason, Film] =
    if (film.releaseYear <= 1980) {
      Right(film)
    } else {
      Left(NotOldEnough)
    }

  /*
   * Validates if price <= 3 otherwise TooExpensive
   */
  private def validatePrice(film: Film): Either[FailureReason, Film] =
    if (film.price <= 3) {
      Right(film)
    } else {
      Left(TooExpensive)
    }

  /*
   * Validates if 'type' contains Crime otherwise BoringFilm
   */
  def validateGenre(film: Film): Either[FailureReason, Film] =
    if (film.`type`.contains(Crime)) {
      Right(film)
    } else {
      Left(BoringFilm)
    }

  /*
   * Note: we validate error using a fail fast approach in a for-comprehension:
   *
   * validateReleaseDate -> validatePrice -> validateGenre
   */
  def validateFilm(film: Film): Either[FailureReason, Film] =
    for {
      e1 <- validateReleaseDate(film).right
      e2 <- validatePrice(e1).right
      e3 <- validateGenre(e2).right
    } yield e3

}

object CatsXor {

  import cats.data.Xor

  /*
   * Validates if releaseYear <= 1980 otherwise NotOldEnough
   */
  private def validateReleaseDate(film: Film): Xor[FailureReason, Film] =
    if (film.releaseYear <= 1980) {
      Xor.right(film)
    } else {
      Xor.left(NotOldEnough)
    }

  /*
   * Validates if price <= 3 otherwise TooExpensive
   */
  private def validatePrice(film: Film): FailureReason Xor Film =
    if (film.price <= 3) {
      Xor.right(film)
    } else {
      Xor.left(TooExpensive)
    }

  /*
   * Validates if 'type' contains Crime otherwise BoringFilm
   */
  def validateGenre(film: Film): FailureReason Xor Film =
    if (film.`type`.contains(Crime)) {
      Xor.right(film)
    } else {
      Xor.left(BoringFilm)
    }

  /*
   * Note: we validate error using a fail fast approach in a for-comprehension:
   *
   * validateReleaseDate -> validatePrice -> validateGenre
   */
  def validateFilm(film: Film): Xor[FailureReason, Film] = {
    for {
      e1 <- validateReleaseDate(film)
      e2 <- validatePrice(e1)
      e3 <- validateGenre(e2)
    } yield e3
  }

}
