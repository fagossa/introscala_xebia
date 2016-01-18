package fr.xebia.scala.control

object OptionTools {

  /*
   * TODO 1 : return the first parameter if present, else the second
   */
  def orElse[A](firstOption: Option[A], secondOption: Option[A]): Option[A] =
    if (firstOption.isDefined) {
      firstOption
    } else {
      secondOption
    }

  /*
    * TODO 5: apply the mapper function to the option specified and wrap the result
    */
  def map[A, B](maybe: Option[A])(mapper: A => B): Option[B] = {
    if (maybe.isDefined) {
      Some(mapper(maybe.get))
    } else {
      None
    }
  }

  /*
    *
    * TODO 5: apply the mapper function to the option specified
    */
  def flatMap[A, B](maybe: Option[A])(mapper: A => Option[B]): Option[B] = {
    if (maybe.isDefined) {
      mapper(maybe.get)
    } else {
      None
    }
  }

}
