package fr.xebia.scala.control

import fr.xebia.scala.model.{FilmRepository, Film, Genre}

import scala.concurrent.{ExecutionContext, Future}

object EventualFuture {

  /*
   * Get the films having the ids specified of the genre specified
   *
   * Note:
   *  - Use FilmRepository#findById to retrieve each film
   *  - Use Future.sequence for List[Future] to Future[List]
   */
  def findMoviesByGenreAndId(genre: Genre, movies: List[Int])(implicit ex: ExecutionContext): Future[List[Film]] = {
    Future.sequence(
      movies map FilmRepository.findById
    ).map(_.flatten)
     .map(_.filter(_.`type`.contains(genre)))
  }

}
