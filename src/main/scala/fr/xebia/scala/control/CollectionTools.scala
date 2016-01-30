package fr.xebia.scala.control

object CollectionTools {

  /*
    * TODO 4:
    * remove elements from list if they don't pass the predicate specified
    */
  def filter[A](list: List[A], withFilter: A => Boolean): List[A] = ???

  /*
    * TODO 10:
    * creates a tuple from each pair of elements
    */
  def zip[A, B](first: List[A], second: List[B]): List[(A, B)] = ???

  /*
    * TODO PriceCalculations_2:
    * creates a tuple from each pair of elements keeping the index
    */
  def zipWithIndex[A](first: List[A]): List[(A, Int)] = ???

  /*
    * TODO 11:
    * Fill a list with the element specified
    */
  def fillList[A](qty: Int)(element: A): List[A] = ???
}
