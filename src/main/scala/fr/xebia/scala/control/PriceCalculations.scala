package fr.xebia.scala.control

import fr.xebia.scala.model.Film

object PriceCalculations {

  /*
   * TODO PriceCalculations_1: use CollectionTools#zip[A,B](List[A], List[B]):(A,B)
   * Calculate the totalPrice of the list specified ONLY if the film list size IS EQUAL to the prices list
   */
  def calculateTotalPrice(films: List[Film], qty: List[Int]): Option[Double] = ???

  /*
   * TODO PriceCalculations_2 : use CollectionTools#zipWithIndex[A](List[A]):(A, Int)
   * Return the total price and the index on the ranking ONLY if the film list size IS EQUAL to the prices list
   */
  def calculateTotalPriceWithIndex(films: List[Film], qty: List[Int]): Option[List[(Int, Double)]] = ???
}
