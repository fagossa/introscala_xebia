package fr.xebia.scala

import fr.xebia.scala.model.{FilmRepository, Film, Genre}

import scala.concurrent.{ExecutionContext, Future}

object Step8_Futures {

  /*
   * Get the film name by the id specified
   *
   * TODO 1:
   *  - Use FilmRepository#findById to retrieve the film
   */
  def getFilmNameById(id: Int)(implicit ex: ExecutionContext): Future[Option[String]] = ???

  /*
   *
   * TODO 2:
   *  - call FilmRepository#findByIdOrFail inside the for-comprehension
   */
  def getEventualFilmById(id: Int)(implicit ex: ExecutionContext): Future[Film] = ???

  /*
   * Sum the prices of all the films specified
   *
   * TODO 3:
   *  - Use FilmRepository#findById to retrieve the film
   *  - Use Future#sequence for List[Future] to Future[List]
   */
  def sumFilmsPricesById(list: List[Int])(implicit ex: ExecutionContext): Future[Double] = ???

  /*
   * Sum the prices of all the films specified
   *
   * TODO 4:
   *  - Use Future#fold to traverse the list of prices
   */
  def sumEventualPrices(prices: List[Future[Double]])(implicit ex: ExecutionContext): Future[Double] = ???

  /*
   * Get the films having the ids specified of the genre specified
   *
   * TODO 5:
   *  - Use FilmRepository#findById to retrieve each film
   *  - Use Future#sequence for List[Future] to Future[List]
   */
  def findMoviesByGenreAndId(genre: Genre, movies: List[Int])(implicit ex: ExecutionContext): Future[List[Film]] = ???
  /*
   * TODO 6:
   * create 4 instances of 'Duration' of 10 seconds using different constructors
   */
  import scala.concurrent.duration._

  def get4DurationsOfTenSeconds: List[Duration] = ???
  /*
   * Return the sum of the prices having the Id specified
   * TODO 7:
   *  - Use FilmRepository#findById to retrieve each film
   *  - IMPORTANT : all calls to FilmRepository#findById should be before the for-comprehension
   *  - sum the prices of the specified films
   */
  def eventualSumFromMovieIds(firstFilmId: Int, secondFilmId: Int, thirdFilmId: Int)(implicit ex: ExecutionContext): Future[Double] = ???
  /*
   * Return the sum of the prices having the Id specified
   *
   * TODO 8:
   *  - Use for-comprehension
   *  - IMPORTANT : all calls to FilmRepository#findById should be inside the for-comprehension
   *  - sum the prices of the specified films
   *  - Question: What happens if we declare the futures before the for-comprehension??
   */
  def slowEventualSumFromMoviesIds(firstFilmId: Int, secondFilmId: Int, thirdFilmId: Int)(implicit ex: ExecutionContext): Future[Double] = ???
}
