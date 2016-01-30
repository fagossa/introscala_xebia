package fr.xebia.scala

import fr.xebia.scala.model.{Film, FilmRepository, Genre}

import scala.concurrent.duration._
import scala.concurrent.{ExecutionContext, Future}
import scala.language.postfixOps

object Step8_Futures {

  /** these are not the implicits you are looking for **/

  /*
   * TODO 1: future option
   * => getFilmNameById should return a future option name for the given film id
   * not: use FilmRepository#findById to retrieve the film by its id
   */
  def getFilmNameById(id: Int)(implicit ex: ExecutionContext): Future[Option[String]] =
    FilmRepository.findById(id).map(_.map(_.name))

  /*
   *
   * TODO 2: back to the future
   * => getEventualFilmById should return a future film for the given film id
   * note: use FilmRepository#findByIdOrFail to retrieve the film
   */
  def getEventualFilmById(id: Int)(implicit ex: ExecutionContext): Future[Film] =
    FilmRepository.findByIdOrFail(id)

  /*
   * TODO 3: future, one more time
   * => sumFilmsPricesById should return a list of future prices of films for given ids
   * note: use FilmRepository#findById to retrieve each film by its id
   * note: use Future#sequence to convert List[Future] to Future[List]
   */
  def sumFilmsPricesById(list: List[Int])(implicit ex: ExecutionContext): Future[Double] =
    Future.sequence(list.map(FilmRepository.findById))
      .map(_.flatten)
      .map(_.map(_.price).sum)

  /*
   * TODO 4: future folding
   * => sumEventualPrices should return a future price sum using given list of future prices
   * note: use Future#fold to traverse list of prices
   */
  def sumEventualPrices(prices: List[Future[Double]])(implicit ex: ExecutionContext): Future[Double] =
    Future.fold(prices)(0d)((total, price) => total + price)

  /*
   * Get the films having the ids specified of the genre specified
   *
   * TODO 5: future is now
   * => findMoviesByGenreAndId should return a future list of films of the given genre for the given ids
   * note: use FilmRepository#findById to retrieve each film
   * note: use Future#sequence to convert List[Future] to Future[List]
   */
  def findMoviesByGenreAndId(genre: Genre, ids: List[Int])(implicit ex: ExecutionContext): Future[List[Film]] =
    Future.sequence(ids.map(FilmRepository.findById))
      .map(_.flatten)
      .map(_.filter(_.`type`.contains(genre)))

  /*
   * TODO 6: durations
   * => get4DurationsOfTenSeconds should return a list of four durations of ten seconds using different constructors
   */
  def get4DurationsOfTenSeconds: List[Duration] = List(
    Duration(10, SECONDS), // from Long and TimeUnit
    Duration(10, "seconds"), // from Long and String
    10 seconds, // implicitly from Long, Int or Double
    Duration("10 s") // from String
  )

  /*
   * TODO 7: future and for-comprehension
   * => eventualSumFromMovieIds should return a future price sum of the three movies corresponding to the given ids
   * note: use FilmRepository#findById to retrieve each film
   * IMPORTANT: all calls to FilmRepository#findById should be before the for-comprehension
   * note: sum the prices of the specified films
   */
  def eventualSumFromMovieIds(id1: Int, id2: Int, id3: Int)(implicit ex: ExecutionContext): Future[Double] = {
    val price1 = FilmRepository.findById(id1).map(_.map(_.price).getOrElse(0d))
    val price2 = FilmRepository.findById(id2).map(_.map(_.price).getOrElse(0d))
    val price3 = FilmRepository.findById(id3).map(_.map(_.price).getOrElse(0d))
    for {
      p1 <- price1
      p2 <- price2
      p3 <- price3
    } yield p1 + p2 + p3
  }

  /*
   * TODO 8: future and for-comprehension
   * => slowEventualSumFromMoviesIds should return a future price sum of the three movies corresponding to the given ids
   * IMPORTANT: all calls to FilmRepository#slowFindById should be inside the for-comprehension
   * question: what happens if we declare the futures before the for-comprehension ??
   */
  def slowEventualSumFromMoviesIds(id1: Int, id2: Int, id3: Int)(implicit ex: ExecutionContext): Future[Double] = for {
    p1 <- FilmRepository.slowFindById(id1).map(_.map(_.price).getOrElse(0d))
    p2 <- FilmRepository.slowFindById(id2).map(_.map(_.price).getOrElse(0d))
    p3 <- FilmRepository.slowFindById(id3).map(_.map(_.price).getOrElse(0d))
  } yield p1 + p2 + p3

  /*
   * TODO 9: That's All Folks!
   * => you should congratulate yourself
   */
  def thatIsAllFolks(): String = "Congrats !"

}
