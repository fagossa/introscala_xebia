package fr.xebia.scala.control

import scala.annotation.tailrec

object CollectionTools {

  /*
    * TODO 4:
    * remove elements from list if they don't pass the predicate specified
    */
  def filter[A](list: List[A], withFilter: A => Boolean): List[A] = list match {
    case Nil => Nil
    case head :: tail if withFilter(head) => head :: filter(tail, withFilter)
    case head :: tail => filter(tail, withFilter)
  }

  /*
    * TODO 10:
    * creates a tuple from each pair of elements
    */
  def zip[A, B](first: List[A], second: List[B]): List[(A, B)] = (first, second) match {
    case (Nil, Nil) => Nil
    case (head1 :: tail1, head2 :: tail2) => (head1, head2) :: zip(tail1, tail2)
    case (_, _) => Nil
  }

  /*
    * TODO PriceCalculations_2:
    * creates a tuple from each pair of elements keeping the index
    */
  def zipWithIndex[A](first: List[A]): List[(A, Int)] = {
    def zipWithIndexRec(first: List[A], n: Int): List[(A, Int)] = first match {
      case Nil => Nil
      case head :: tail => (head, n) :: zipWithIndexRec(tail, n + 1)
    }
    zipWithIndexRec(first, 0)
  }

  /*
    * TODO 11:
    * Fill a list with the element specified
    */
  def fillList[A](qty: Int)(element: A): List[A] = {
    @tailrec
    def fillListRec(list: List[A], current: Int): List[A] = list match {
      case h :: t if qty == 0 => Nil
      case h :: t if current < qty => fillListRec(list ++ List(element), current + 1)
      case h :: t => list
    }
    fillListRec(List(element), 1)
  }

}
