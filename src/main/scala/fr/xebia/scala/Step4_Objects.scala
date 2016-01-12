package fr.xebia.scala

import java.util.UUID

object Step4_Objects {

  /*
    objects are pretty much singletons:
      object Foo {}
    objects are referenced with Java's static-like syntax:
      Foo.toString
    TODO 1 object => make greet method return CrazyLegs's greet field prefixed by "CrazyLegs says:"
  */
  object CrazyLegs {

    val greet: String = UUID.randomUUID().toString

  }

  def greet(): String = ???

  /*
    object can share the same name with a class, it is then named companion object:
      class Foo {}
      object Foo {} // this is our companion object
    TODO 2 companion object:
      => implement beginner method (it is just a factory of Craftsman with 0 badges)
      => make check method return true if Craftsman's badges are strictly greater than 10, false otherwise
  */
  class Craftsman(val badges: Int) {}

  object Craftsman {

    def beginner(): Craftsman = ???

    def check(craftsman: Craftsman): Boolean = ???

  }

}
