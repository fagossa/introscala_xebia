package fr.xebia.scala

import fr.xebia.scala.model.{Film, Genre}

import scala.concurrent.duration._
import scala.concurrent.{ExecutionContext, Future}
import scala.language.postfixOps

object Step8_Futures {

  /** these are not the implicits you are looking for **/

  /*
   * TODO 1: future option
   * => getFilmNameById should return a future option name for the given film id
   * note: use FilmRepository#findById to retrieve the film by its id
   */
  def getFilmNameById(id: Int)(implicit ex: ExecutionContext): Future[Option[String]] = ???

  /*
   *
   * TODO 2: back to the future
   * => getEventualFilmById should return a future film for the given film id
   * note: use FilmRepository#findByIdOrFail to retrieve the film
   */
  def getEventualFilmById(id: Int)(implicit ex: ExecutionContext): Future[Film] = ???

  /*
   * TODO 3: future, one more time
   * => sumFilmsPricesById should return a list of future prices of films for given ids
   * note: use FilmRepository#findById to retrieve each film by its id
   * note: use Future#sequence to convert List[Future] to Future[List]
   */
  def sumFilmsPricesById(list: List[Int])(implicit ex: ExecutionContext): Future[Double] = ???

  /*
   * TODO 4: future folding
   * => sumEventualPrices should return a future price sum using given list of future prices
   * note: use Future#fold to traverse list of prices
   */
  def sumEventualPrices(prices: List[Future[Double]])(implicit ex: ExecutionContext): Future[Double] = ???

  /*
   * Get the films having the ids specified of the genre specified
   *
   * TODO 5: future is now
   * => findMoviesByGenreAndId should return a future list of films of the given genre for the given ids
   * note: use FilmRepository#findById to retrieve each film
   * note: use Future#sequence to convert List[Future] to Future[List]
   */
  def findMoviesByGenreAndId(genre: Genre, ids: List[Int])(implicit ex: ExecutionContext): Future[List[Film]] = ???
  /*
   * TODO 6: durations
   * => get4DurationsOfTenSeconds should return a list of four durations of ten seconds using different constructors
   */
  def get4DurationsOfTenSeconds: List[Duration] = ???
  /*
   * TODO 7: future and for-comprehension
   * => eventualSumFromMovieIds should return a future price sum of the three movies corresponding to the given ids
   * note: use FilmRepository#findById to retrieve each film
   * IMPORTANT: all calls to FilmRepository#findById should be before the for-comprehension
   * note: sum the prices of the specified films
   */
  def eventualSumFromMovieIds(id1: Int, id2: Int, id3: Int)(implicit ex: ExecutionContext): Future[Double] = ???
  /*
   * TODO 8: future and for-comprehension
   * => slowEventualSumFromMoviesIds should return a future price sum of the three movies corresponding to the given ids
   * IMPORTANT: all calls to FilmRepository#slowFindById should be inside the for-comprehension
   * question: what happens if we declare the futures before the for-comprehension ??
   */
  def slowEventualSumFromMoviesIds(id1: Int, id2: Int, id3: Int)(implicit ex: ExecutionContext): Future[Double] = ???

  /*
   * TODO 9: That's All Folks!
   * => you should congratulate yourself
   */
  def thatIsAllFolks(): String = ???

}
