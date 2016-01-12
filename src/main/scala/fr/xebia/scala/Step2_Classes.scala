package fr.xebia.scala

object Step2_Classes {

  /*
    classes are declared just as in Java:
      class Foo {}
    TODO 1 class
      => name should be initialized with "Bob"
      => greetings should return "Hello my name is $name"
  */
  class Greeter {

    val name: String = ???

    def greetings(): String = ???

  }

  /*
    class constructor is however defined just after class name:
      class Foo(x: Int) {}
    constructor argument can be declared as field using var or val keyword:
      class Foo(val x: Int) {}
      class Foo(var x: Int) {}
    TODO 2 constructor
      => greetings should return "Hello my name is $name" using name field defined in constructor
  */
  class UserWithName(val name: String) {

    def greetings(): String = ???

  }

  /*
    class inheritance is declared as follow:
      class Foo(x: Int) {}
      class Bar(x: Int) extends Foo(x) {}
    methods must be overridden using override keyword
    TODO 3 inheritance
      => greetings should return overridden method's return suffixed with ", I am $age years old"
      => notice that name argument in constructor is not declared as var or val, it's only an argument not a field
  */
  class UserWithNameAndAge(name: String, val age: Int) extends UserWithName(name) {

    override def greetings(): String = ???

  }

  /*
    class members visibility can be adjusted with private or protected keywords:
      class Foo(private var x: Int) {}
      class Foo { private var password: String = "tmp" }
    TODO 4 visibility
      => password field should be marked as private
  */
  class UserWithPassword(val password: String) {}

  /*
    case classes is a shortcut declaration for classes  powered with equals and toString using given fields:
      case class Foo(var age: Int, var name: String) {}
    constructor fields are automatically assigned as values (if not declared as var)
    TODO 5 case classes
      => string should return Item's case class string representation
      => compare should fully compare item1 and item2
      => fidelity should return (using pattern matching):
        - 2 points for Item "Beer" with price > 5
        - 1 point for Item with name matching "Beer(.*)" at any price
        - 0 points otherwise
  */
  class Item(name: String, price: Int) {}

  def itemAsString(item: Item): String = ???

  def compare(item1: Item, item2: Item): Boolean = ???

  def fidelity(item: Item): Int = ???

}
