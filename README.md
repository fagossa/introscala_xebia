How to build and run the test?
=============

## - When you already have scala installed 

The simplest way to work with the exercises is to get to SBT's REPL:

    ./activator
    
Once inside, In order to execute the whole test set: 

    > test
    
If you want to run only one test, just type this:

    > testOnly fr.xebia.scala.StepXXX

-----

# How the exercises are organized?

## Intro level

### Step 1 : Basic

#### Currifying an existing function

Lets imagine that we have the following function

```scala
  val sum: (Int, Int) => Int = _ + _
    
  val result sum(1, 2) // result should be 3
```

But we split the parameters in order to reuse the first block. By using `curried` we can transform an existing 
function into a `curried function`. So, by doing this

```scala
  val sumCurried = sum.curried
  val result = sumCurried(1)(2) // result should be 3
```

### Step 2, 3, 4 : Classes, Traits and Objects

The following example show the relations beetween _classes_, _traits_ and _objects_

```scala
  trait SomeLikeAnInterfaceAndAbstractClass {
    def aSimpleOperation = println("I'm an implementation")
  }
    
  trait anotherTrait {
    def operation (param : Int) : Int
  }
    
  class MyClassLikeInJava (value: Int) extends SomeLikeAnInterfaceAndAbstractClass with anotherTrait {
    override def operation (param : Int) : Int = param * value
  }
    
  object MySingletonInstance extends anotherTrait {
    override def operation (param : Int) : Int = param * 2
  }
```

You can use like this

```scala
  new MyClassLikeInJava(2).operation(3) // should be 6
  MySingletonInstance.operation(3) // should be 6
```

## Intermediate level


### Step 5 : Collections

There are several ways to create list in scala.

* We can call the constructor directly

```scala
  val list = List(1, 2, 3)
```

* Or we can call the `::` function to chain the different elements 

```scala
  val list = 1 :: 2 :: 3 :: Nil
```

#### How to traverse a list?

Traversing a list means we need to handle at least two special cases:

* The end of the list specified by the keyword __Nil__

* The other cases, indicated by the pattern composed of a head and a tail, __head :: tail__ 

The easiest way to handle those cases is by using a pattern matching on the list.

```scala
    def sum(list: List[Int]): Int = list match {
      case Nil => 
        0 /* we arrived to the end of the list */
        
      case head :: tail =>
        /* get the value from 'head' and handle recursively the other cases */
        head + sum(tail) 
    }
```

### Step 6 : Optionals

Options atre strongly typed containers to represent a potentially "null" value. Can have any of the following values:
 
 - `Some(actualValue)`, for example `val num = Some(5)`
 
 - `None`, for example: `val num2 = Option.empty[Int]` or `val num3 = None`

You can verify its value by the following means:

#### Pattern matching: 

```scala
    num2 match {
      case Some(actualValue) => println(actualValue)
      case None => println("No value")
    }
```

#### Getting a default value

```scala
    val result1 = num2.getOrElse(0)
    val result2 = num2.get // may fail if value not present
    val result3 = num3.orElse(anotherOption).getOrElse(lastChanceValue)
```

### Step 7 : Data Validation
We included different examples that hopefully will help you to identify the advantages/disadvantages from the following
equivalent alternatives:

- _Either_ in Scala

- _Xor_ in Cats


### Step 8 : Futures

Scala's wrapper for futures operations

```scala
    import concurrent.Future
    import concurrent.ExecutionContext.Implicits.global //default thread pool
    
    val f: Future[String] = Future { "Hello world!" }

    // when the future is done:
    f.onComplete {
      case Success(value) =>
        println(s"this is the actual value!")
        
      case Failure(ex) =>
        println(s"Houston, we got a problem: ${ex.getMessage}")
    }
```
