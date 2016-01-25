package fr.xebia.scala

import fr.xebia.scala.model.{FilmRepository, Film, Genre}

import scala.concurrent.{ExecutionContext, Future}

import scala.concurrent.duration._
import scala.language.postfixOps

object Step8_Futures {

  /*
   * Get the film name by the id specified
   *
   * TODO 1:
   *  - Use FilmRepository#findById to retrieve the film
   */
  def getFilmNameById(id: Int)(implicit ex: ExecutionContext): Future[Option[String]] =
    FilmRepository.findById(id).map(_.map(_.name))

  /*
   *
   * TODO 2:
   *  - call FilmRepository#findByIdOrFail inside the for-comprehension
   */
  def getEventualFilmById(id: Int)(implicit ex: ExecutionContext): Future[Film] =
    FilmRepository.findByIdOrFail(id)

  /*
   * Sum the prices of all the films specified
   *
   * TODO 3:
   *  - Use FilmRepository#findById to retrieve the film
   *  - Use Future#sequence for List[Future] to Future[List]
   */
  def sumFilmsPricesById(list: List[Int])(implicit ex: ExecutionContext): Future[Double] =
    Future.sequence(list.map(FilmRepository.findById))
      .map(_.flatten)
      .map(_.map(_.price).sum)

  /*
   * Sum the prices of all the films specified
   *
   * TODO 4:
   *  - Use Future#fold to traverse the list of prices
   */
  def sumEventualPrices(prices: List[Future[Double]])(implicit ex: ExecutionContext): Future[Double] =
    Future.fold(prices)(0d)((acc, next) => acc + next)

  /*
   * Get the films having the ids specified of the genre specified
   *
   * TODO 5:
   *  - Use FilmRepository#findById to retrieve each film
   *  - Use Future#sequence for List[Future] to Future[List]
   */
  def findMoviesByGenreAndId(genre: Genre, movies: List[Int])(implicit ex: ExecutionContext): Future[List[Film]] =
    Future.sequence(movies.map(FilmRepository.findById))
      .map(_.flatten)
      .map(_.filter(_.`type`.contains(genre)))

  /*
   * TODO 6:
   * create 4 instances of 'Duration' of 10 seconds using different constructors
   */
  def get4DurationsOfTenSeconds: List[Duration] = List(
    Duration(10, SECONDS), // from Long and TimeUnit
    Duration(10, "seconds"), // from Long and String
    10 seconds, // implicitly from Long, Int or Double
    Duration("10 s") // from String
  )

  /*
   * Return the sum of the prices having the Id specified
   * TODO 7:
   *  - Use FilmRepository#findById to retrieve each film
   *  - IMPORTANT : all calls to FilmRepository#findById should be before the for-comprehension
   *  - sum the prices of the specified films
   */
  def eventualSumFromMovieIds(firstFilmId: Int, secondFilmId: Int, thirdFilmId: Int)(implicit ex: ExecutionContext): Future[Double] = {
    val price1 = FilmRepository.findById(firstFilmId).map(_.map(_.price).getOrElse(0d))
    val price2 = FilmRepository.findById(secondFilmId).map(_.map(_.price).getOrElse(0d))
    val price3 = FilmRepository.findById(thirdFilmId).map(_.map(_.price).getOrElse(0d))
    for {
      p1 <- price1
      p2 <- price2
      p3 <- price3
    } yield p1 + p2 + p3
  }

  /*
   * Return the sum of the prices having the Id specified
   *
   * TODO 8:
   *  - Use for-comprehension
   *  - IMPORTANT : all calls to FilmRepository#slowFindById should be inside the for-comprehension
   *  - sum the prices of the specified films
   *  - Question: What happens if we declare the futures before the for-comprehension??
   */
  def slowEventualSumFromMoviesIds(firstFilmId: Int, secondFilmId: Int, thirdFilmId: Int)(implicit ex: ExecutionContext): Future[Double] = for {
    p1 <- FilmRepository.slowFindById(firstFilmId).map(_.map(_.price).getOrElse(0d))
    p2 <- FilmRepository.slowFindById(secondFilmId).map(_.map(_.price).getOrElse(0d))
    p3 <- FilmRepository.slowFindById(thirdFilmId).map(_.map(_.price).getOrElse(0d))
  } yield p1 + p2 + p3
}
