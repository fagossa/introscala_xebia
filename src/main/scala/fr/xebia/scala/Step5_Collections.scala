package fr.xebia.scala

import fr.xebia.scala.control.CollectionTools
import fr.xebia.scala.model.{Film, Director}

import scala.annotation.tailrec

object Step5_Collections {

  def getFilmsMadeBy(director: Director, films: List[Film]): List[Film] =
    films.filter(f => f.director == director)

  /*
   * Get the films directed only by the director specified
   * Note: Now we have two blocks of parameters. This is called currying. Refer to the documentation for further
   * information.
   */
  def filterFilmsWithDirector(films: List[Film])(director: Director): List[Film] =
    films.filter(f => f.director == director)

  /*
   * Get the films using the filter specified
   * Note:
   */
  def filterFilmsUsingFilter(films: List[Film])(withCustomFilter: Film => Boolean): List[Film] =
    films.filter(withCustomFilter)

  /*
   * Note: use/implement CollectionTools#filter
   */
  def filterFilmsUsingFilter2(films: List[Film])(withCustomFilter: Film => Boolean): List[Film] =
    CollectionTools.filter(films, withCustomFilter)

  /*
   * Get the films by applying the filter list specified
   * Note: use pattern matching and recursion
   */
  def filterFilmsUsingMultipleFilter(films: List[Film])(withCustomFilters: List[Film => Boolean]): List[Film] =
    withCustomFilters match {
      case Nil => films
      case h :: t => filterFilmsUsingMultipleFilter(films.filter(h))(t)
    }

  // Note: use recursion
  def sumPricesWithRecursion(films: List[Film]): Double = {
    @tailrec
    def go(films: List[Film], sum: Double): Double = {
      films match {
        case Nil => sum
        case h :: tail => go(tail, h.price + sum)
      }
    }
    go(films, 0)
  }

  /*
   * Apply discounts for all films following these rules:
   * - 35% reduction if price is only multiple of 3
   * - 40% reduction if price is only multiple of 5
   * - 50% reduction if price is both multiple of 5 and 3
   * - 0% reduction otherwise
   *
   * Note: you can use pattern matching on tuples
   */
  def discounts(films: List[Film]): List[Double] = films match {
    case Nil => Nil
    case h :: t => (h.price % 3 == 0, h.price % 5 == 0) match {
      case (true, false) => List(h.price * 0.35) ++ discounts(t)
      case (false, true) => List(h.price * 0.4) ++ discounts(t)
      case (true, true) => List(h.price * 0.5) ++ discounts(t)
      case (_, _) => List(h.price) ++ discounts(t)
    }
  }

  // Note: you could use 'foldLeft' which in fact is a curried function
  def sumPricesWithFolding(films: List[Film]): Double =
    films.foldLeft(0d)((r, c) => r + c.price)

  /*
   * Delete duplicate consecutive numbers
   */
  def deleteConsecutiveDuplicates(allFilms: List[Film]): List[Film] =
    allFilms.foldLeft(List.empty[Film])((acc, next) =>
      if (acc.contains(next)) {
        acc
      } else {
        acc ++ List(next)
      }
    )

}



