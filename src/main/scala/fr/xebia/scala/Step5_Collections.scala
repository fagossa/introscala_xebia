package fr.xebia.scala

import fr.xebia.scala.control.CollectionTools
import fr.xebia.scala.model.{Film, Director}

import scala.annotation.tailrec

object Step5_Collections {

  /*
   * TODO 1: basic filter
   */
  def getFilmsMadeBy(director: Director, films: List[Film]): List[Film] =
    films.filter(_.director == director)

  /*
   * Get the films directed only by the director specified
   * TODO 2:
   * Now we have two blocks of parameters. This is called currying. Refer to the documentation for further
   * information.
   */
  def filterFilmsWithDirector(films: List[Film])(director: Director): List[Film] =
    films.filter(_.director == director)

  /*
   * Get the films using the filter specified
   * TODO 3: This time we have the filter specified as a parameter. So, we we have a 'high order function'
   */
  def filterFilmsUsingFilter(films: List[Film])(withCustomFilter: Film => Boolean): List[Film] =
    films.filter(withCustomFilter)

  /*
   * TODO 4: use/implement CollectionTools#filter
   */
  def filterFilmsUsingAnotherFilter(films: List[Film])(withCustomFilter: Film => Boolean): List[Film] =
    CollectionTools.filter(films, withCustomFilter)

  /*
   * Get the films by applying the filter list specified
   * TODO 5: use pattern matching and recursion
   * note: you can match a collection using "case head :: tail => ..."
   */
  def filterFilmsUsingMultipleFilter(films: List[Film])(withCustomFilters: List[Film => Boolean]): List[Film] =
    withCustomFilters match {
      case Nil => films
      case head :: tail => filterFilmsUsingMultipleFilter(films.filter(head))(tail)
    }

  /*
   * TODO 6: Sum the prices of the films specified using recursion
   */
  def sumPricesWithRecursion(films: List[Film]): Double = {
    @tailrec
    def sumPrices(films: List[Film], sum: Double): Double = {
      films match {
        case Nil => sum
        case head :: tail => sumPrices(tail, head.price + sum)
      }
    }
    sumPrices(films, 0)
  }

  /*
   * TODO 7: Use pattern matching on tuples to execute the following rules:
   *
   * Apply discounts for all films following these rules:
   * - 35% reduction if price is only multiple of 3
   * - 40% reduction if price is only multiple of 5
   * - 50% reduction if price is both multiple of 5 and 3
   * - 0% reduction otherwise
   */
  def discounts(films: List[Film]): List[Double] = films.map(_.price).map(_ match {
    case p if p % 3 == 0 && p % 5 == 0 => 0.5 * p
    case p if p % 3 == 0 => 0.35 * p
    case p if p % 5 == 0 => 0.4 * p
    case p => p
  })

  /*
   * TODO 8: use 'foldLeft' (which in fact is a curried function) to get the sum of prices
   */
  def sumPricesWithFolding(films: List[Film]): Double =
    films.foldLeft(0d)((total, film) => total + film.price)

  /*
   * TODO 9: Delete duplicate consecutive numbers using foldLeft
   */
  def deleteConsecutiveDuplicates(allFilms: List[Film]): List[Film] =
    allFilms.foldLeft(List.empty[Film])((filmList, film) =>
      if (filmList.contains(film)) {
        filmList
      } else {
        filmList ++ List(film)
      }
    )

}



