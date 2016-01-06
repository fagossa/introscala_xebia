package fr.xebia.scala.control

import fr.xebia.scala.model.{Film, FilmRepository, Genre}

import scala.concurrent.{ExecutionContext, Future}

object EventualFuture {

  /*
   * Get the film name by the id specified
   *
   * Note:
   *  - Use FilmRepository#findById to retrieve the film
   */
  def getFilmNameById(id: Int)(implicit ex: ExecutionContext): Future[Option[String]] =
    FilmRepository.findById(id).map(_.map(_.name))

  /*
   * Sum the prices of all the films specified
   *
   * Note:
   *  - Use FilmRepository#findById to retrieve the film
   *  - Use Future.sequence for List[Future] to Future[List]
   */
  def sumFilmsPricesById(list: List[Int])(implicit ex: ExecutionContext): Future[Double] =
    Future.sequence(
      list.map(FilmRepository.findById)
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
   *  - Use Future.sequence for List[Future] to Future[List]
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
   *  - IMPORTANT : call FilmRepository#findById before the for-comprehension
   *  - sum the prices
   */
  def eventualSumFromMoviesIds(firstFilmId: Int, secondFilmId: Int, thirdFilmId: Int)(implicit ex: ExecutionContext): Future[Double] = {
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
   *  - IMPORTANT : call FilmRepository#findById inside the for-comprehension
   *  - sum the prices
   *  - Question: What happens if we declare the futures before?
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
