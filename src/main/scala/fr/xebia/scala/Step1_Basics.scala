package fr.xebia.scala

object Step1_Basics {

  /*
    variables are declared and initialized like this:
      var name: Type = Initializer
    TODO 1 variables => initialize this variable with a value of 24... is it that simple ?
  */
  var variable: Int = 0

  /*
    values are declared like variables but with val keyword
    TODO 2 values => initialize this value with a value of 42... keeping it simple
  */
  val value: Int = 0

  /*
    functions are declared like this:
      def name(param: Type): ReturnType = Initializer
    TODO 3 functions => make square function return the square value of the given x argument
  */
  def square(x: Int): Int = 0

  /*
    functions can be partially applied like this:
      fxy(9, _) or fxy(_, 9)
    both previous calls return functions of type: Int => Int
    TODO 4 partial application => make fx and fy return partial functions using given fxy function and respective y and x arguments
  */
  def fxy(x: Int, y: Int): Int = (2 * x) + (y * y)

  def fx(y: Int): Int => Int = (_: Int) => 0 // stub implementation

  def fy(x: Int): Int => Int = (_: Int) => 0 // stub implementation

  /*
    functions can be curried like this:
      def foo(x: Int)(y: Int): Int = ???
    this syntax forces users to pass arguments in distinct calls, thus using an intermediate function
    TODO 5 curried function => make gx and gy return partial functions using given gxy function and respective y and x arguments
  */
  def gxy(x: Int)(y: Int): Int = (2 * x) - (y * y)

  def gx(y: Int): Int => Int = (_: Int) => 0 // stub implementation

  def gy(x: Int): Int => Int = (_: Int) => 0 // stub implementation

  /*
    functions can have variable length arguments:
      def foo(x: Int*): Int = ???
    TODO 6 variable length arguments => make vararg return sum of x variable length argument
  */
  def vararg(x: Int*): Int = 0

  /*
    if/else expressions can be used to return values like this:
      def metal(x: String): Int = if (x == "ok") 0 else 1
    TODO 7 function with expression => return "ok" if x argument is 0, "ko" otherwise using if/else expression
  */
  def expression(x: Int): String = ""

}
