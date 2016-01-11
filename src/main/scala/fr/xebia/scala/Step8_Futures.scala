package fr.xebia.scala

import fr.xebia.scala.model.{FilmRepository, Film, Genre}

import scala.concurrent.{ExecutionContext, Future}

object Step8_Futures {

  /*
   * Get the film name by the id specified
   *
   * Note:
   *  - Use FilmRepository#findById to retrieve the film
   */
  def getFilmNameById(id: Int)(implicit ex: ExecutionContext): Future[Option[String]] =
    FilmRepository.findById(id).map(_.map(_.name))

  /*
   *
   * Note:
   *  - call FilmRepository#findByIdOrFail inside the for-comprehension
   */
  def getEventualFilmById(id: Int)(implicit ex: ExecutionContext): Future[Film] =
    FilmRepository.findByIdOrFail(id)

  /*
   * Return a list containing 4 instances of 'Duration' constructed in different ways
   */

  import scala.concurrent.duration._

  def get4DurationsOfTenSeconds: List[Duration] = {
    val fromTimeUnit = Duration(10, SECONDS) // from Long and TimeUnit
    val fromLongAndString = Duration(10, "seconds") // from Long and String
    val fromImplicit = 10 seconds // implicitly from Long, Int or Double
    val fromString = Duration("10 s") // from String
    List(fromTimeUnit, fromLongAndString, fromImplicit, fromString)
  }

  /*
   * Sum the prices of all the films specified
   *
   * Note:
   *  - Use FilmRepository#findById to retrieve the film
   *  - Use Future#sequence for List[Future] to Future[List]
   */
  def sumFilmsPricesById(list: List[Int])(implicit ex: ExecutionContext): Future[Double] =
    Future.sequence(
      list map FilmRepository.findById
    ).map(_.flatten)
      .map(_.map(f => f.price).sum)

  /*
   * Sum the prices of all the films specified
   *
   * Note:
   *  - Use Future#fold to traverse the list of prices
   */
  def sumEventualPrices(prices: List[Future[Double]])(implicit ex: ExecutionContext): Future[Double] =
    Future.fold(prices)(0d)((acc, next) => acc + next)

  /*
   * Get the films having the ids specified of the genre specified
   *
   * Note:
   *  - Use FilmRepository#findById to retrieve each film
   *  - Use Future#sequence for List[Future] to Future[List]
   */
  def findMoviesByGenreAndId(genre: Genre, movies: List[Int])(implicit ex: ExecutionContext): Future[List[Film]] = {
    Future.sequence(
      movies map FilmRepository.findById
    ).map(_.flatten)
      .map(_.filter(_.`type`.contains(genre)))
  }

  /*
   * Return the sum of the prices having the Id specified
   * Note:
   *  - Use FilmRepository#findById to retrieve each film
   *  - IMPORTANT : all calls to FilmRepository#findById should be before the for-comprehension
   *  - sum the prices of the specified films
   */
  def eventualSumFromMovieIds(firstFilmId: Int, secondFilmId: Int, thirdFilmId: Int)(implicit ex: ExecutionContext): Future[Double] = {
    val ope1 = FilmRepository.findById(firstFilmId).map(_.map(_.price).getOrElse(0d))
    val ope2 = FilmRepository.findById(secondFilmId).map(_.map(_.price).getOrElse(0d))
    val ope3 = FilmRepository.findById(thirdFilmId).map(_.map(_.price).getOrElse(0d))
    for {
      v1 <- ope1
      v2 <- ope2
      v3 <- ope3
    } yield {
      v1 + v2 + v3
    }
  }

  /*
   * Return the sum of the prices having the Id specified
   *
   * Note:
   *  - Use for-comprehension
   *  - IMPORTANT : all calls to FilmRepository#findById should be inside the for-comprehension
   *  - sum the prices of the specified films
   *  - Question: What happens if we declare the futures before the for-comprehension??
   */
  def slowEventualSumFromMoviesIds(firstFilmId: Int, secondFilmId: Int, thirdFilmId: Int)(implicit ex: ExecutionContext): Future[Double] = {
    for {
      v1 <- FilmRepository.slowFindById(firstFilmId).map(_.map(_.price).getOrElse(0d))
      v2 <- FilmRepository.slowFindById(secondFilmId).map(_.map(_.price).getOrElse(0d))
      v3 <- FilmRepository.slowFindById(thirdFilmId).map(_.map(_.price).getOrElse(0d))
    } yield {
      v1 + v2 + v3
    }
  }

}
