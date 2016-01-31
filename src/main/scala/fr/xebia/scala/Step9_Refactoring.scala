package fr.xebia.scala

import fr.xebia.scala.model.{Director, FilmRepository, Film}

import scala.concurrent.{ExecutionContext, Future}

case class Customer(name: String)

case class Rent(customer: Customer, film: Film)

object Verifications {

  def verifyFilm(film: Film): Unit = {
    if (film.price < 0) {
      throw new IllegalArgumentException("Invalid price")
    }
  }

}

trait Step9_Refactoring {

  /*
   * The function Verifications#verifyFilm is kind of weird!
   * What can we do?
   *
   * Note:
   *  - you could not change the signature of this method!
   */
  def rent(customer: Customer, film: Film): Rent = {
    Verifications.verifyFilm(film)
    Rent(customer, film)
  }

  /*
   * This becomes a mess when we have several cascading flatMaps BUT we still need
   * to throw exceptions on missing data.
   *
   * So, what can we do to improve readability?
   *
   */
  def getRandomFilmBySameGenreOf(id: Int)(implicit ex: ExecutionContext): Future[Film] = {
    FilmRepository.findById(id).flatMap {
      case Some(actualFilm1) =>
        FilmRepository.findRandomFilmByDirector(actualFilm1.name, actualFilm1.`type`).flatMap {
          case Some(actualFilm2) =>
            Future.successful(actualFilm2)
          case None =>
            throw new IllegalArgumentException(s"No film related")
        }
      case None =>
        throw new IllegalArgumentException(s"Film with Id <$id> not found")
    }
  }

}

object Step9_Refactoring extends Step9_Refactoring
