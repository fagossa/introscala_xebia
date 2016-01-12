package fr.xebia.scala

object Step2_Classes {

  /*
    classes are declared just as in Java:
      class Foo {}
    TODO 1 class => initialize name with "Bob" and make greetings return "Hello my name is $name"
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
    TODO 2 constructor => make greetings return "Hello my name is $name" using name field defined in constructor
  */
  class UserWithName(val name: String) {

    def greetings(): String = ???

  }

  /*
    class inheritance is declared as follow:
      class Foo(x: Int) {}
      class Bar(x: Int) extends Foo(x) {}
    methods must be overridden using override keyword
    TODO 3 inheritance => override greetings and make it return overridden method's return suffixed with ", I am $age years old"
    notice name argument in constructor is not declared as var or val, it's only an argument not a field
  */
  class UserWithNameAndAge(name: String, val age: Int) extends UserWithName(name) {

    override def greetings(): String = ???

  }

  /*
    class members visibility can be adjusted with private or protected keywords:
      class Foo(private var x: Int) {}
      class Foo { private var password: String = "tmp" }
    TODO 4 visibility => make password field private
  */
  class UserWithPassword(val password: String) {

  }

}
