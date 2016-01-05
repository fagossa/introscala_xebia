package fr.xebia.scala.control

import scala.annotation.tailrec

object CollectionTools {

  /**
    * remove elements from list if they don't pass the predicate specified
    */
  def filter[A](list: List[A], withFilter: A => Boolean): List[A] = list match {
    case Nil => Nil
    case h :: t if withFilter(h) => List(h) ++ filter(t, withFilter)
    case h :: t => filter(t, withFilter)
  }

  /**
    * creates a tuple from each pair of elements
    */
  def zip[A, B](first: List[A], second: List[B]): List[(A, B)] = {
    (first, second) match {
      case (Nil, Nil) => Nil
      case (h1 :: t1, h2 :: t2) => List((h1, h2)) ++ zip(t1, t2)
      case (_, _) => Nil
    }
  }

  /**
    * creates a tuple from each pair of elements keeping the index
    */
  def zipWithIndex[A](first: List[A]): List[(A, Int)] = {
    def go(elements: List[A], index: Int): List[(A, Int)] = {
      elements match {
        case (Nil) => Nil
        case (h1 :: t1) => List((h1, index)) ++ go(t1, index + 1)
      }
    }
    go(first, 0)
  }


  /**
    * Fill a list with the element specified
    */
  def fillList[A](qty: Int)(element: A): List[A] = {
    @tailrec
    def go(list: List[A], current: Int): List[A] = {
      list match {
        case h :: t if qty == 0 => Nil
        case h :: t if current < qty => go(list ++ List(element), current + 1)
        case h :: t => list
      }
    }
    go(List(element), 1)
  }

}
