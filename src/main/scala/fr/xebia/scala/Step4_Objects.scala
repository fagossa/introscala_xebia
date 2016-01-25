package fr.xebia.scala

import java.util.UUID

object Step4_Objects {

  /*
    objects are pretty much singletons:
      object Foo {}
    objects are referenced with Java's static-like syntax:
      Foo.toString
    TODO 1 object
      => greet method should return RandomGreeter's greet field prefixed by "RandomGreeter:"
  */
  object RandomGreeter {

    val greet: String = UUID.randomUUID().toString

  }

  def greet(): String = "RandomGreeter:" + RandomGreeter.greet

  /*
    object can share the same name with a class, it is then named companion object:
      class Foo {}
      object Foo {} // this is our companion object
    TODO 2 companion object
      => beginner should return a new Craftsman with 0 badges
      => check should return true if given craftsman's badges are strictly greater than 10, false otherwise
  */
  class Craftsman(val badges: Int) {}

  object Craftsman {

    def beginner(): Craftsman = new Craftsman(0)

    def check(craftsman: Craftsman): Boolean = craftsman.badges > 10

  }

  /*
    classes or objects can have an apply function:
    object Foo {
      def apply() = ???
    }
    this function can be called without naming it:
    Foo()
    TODO 3 apply methods
      => Scorer's apply should increment Scorer's count on each call
      => DoubleScorer's apply should call Scorer's apply method twice on each call
  */
  object Scorer {
    var count = 0

    def apply() = count += 1
  }

  object DoubleScorer {
    def apply() = {
      Scorer()
      Scorer()
    }
  }

}
