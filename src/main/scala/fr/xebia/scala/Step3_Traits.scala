package fr.xebia.scala

object Step3_Traits {

  /*
   * traits can carry fields and methods (not necessarily abstract):
   *   trait Point {
   *     var x: Int
   *     val y:Int = 42
   *     def draw()
   *     def translate(dx: Int) = x + dx
   *   }
   * TODO 1: trait declaration
   * => greetDecorated should return greet's return prefixed with "Decorated:"
   */
  trait Greeter {

    var name: String

    def greet(): String

    def greetDecorated(): String = ???

  }

  /*
   * traits can be inherited like this:
   *   trait Foo {}
   *   trait Bar {}
   *   class Qux extends Foo {}
   *   class Norf extends Foo with Bar {} // to inherit multiple traits
   * TODO 2: trait inheritance and usage
   * => name should be initialized
   * => greet should return "Hello my name is $name"
   */
  class GentleGreeter extends Greeter {

    override var name: String = ???

    override def greet(): String = ???
  }

}
