package fr.xebia.scala.control

object OptionTools {

  /**
    * apply the mapper function to the option specified and wrap the
    * result
    */
  def map[A, B](maybe: Option[A])(mapper: A => B): Option[B] = {
    if (maybe.isDefined) {
      Some(mapper(maybe.get))
    } else {
      None
    }
  }

  /**
    * apply the mapper function to the option specified
    */
  def flatMap[A, B](maybe: Option[A])(mapper: A => Option[B]): Option[B] = {
    if (maybe.isDefined) {
      mapper(maybe.get)
    } else {
      None
    }
  }


  /**
    * if the first parameter is None return the second
    */
  def orElse[A](firstOption: Option[A], secondOption: Option[A]): Option[A] =
    if (firstOption.isDefined) {
      firstOption
    } else {
      secondOption
    }

}
