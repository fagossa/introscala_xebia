
# List

There are several ways to create list in scala.

* We can call the constructor directly
`val list = List(1, 2, 3)`

* Or we can call the `::` function to chain the different elements 
`val list = 1 :: 2 :: 3 :: Nil`


## How to traverse a list

Traversing a list means we need to handle at least two special cases:

* The end of the list specified by the keyword __Nil__

* The other cases, indicated by the pattern composed of a head and a tail, __head :: tail__ 

The easiest way to handle those cases is by using a pattern matching on the list.


    def sum(list: List[Int]): Int = list match {
      case Nil => 
        0 /* we arrived to the end of the list */
        
      case head :: tail =>
        /* get the value from 'head' and handle recursively the other cases */
        head + sum(tail) 
    }

# Currying


## Currifying an existing function

Lets imagine that we have the following function

    val sum: (Int, Int) => Int = _ + _
    
    val result sum(1, 2) // result should be 3

But we split the parameters in order to reuse the first block. By using `curried` we can transform an existing 
function into a `curried function`. So, by doing this

    val sumCurried = sum.curried
    val result = sumCurried(1)(2) // result should be 3


# Validating data

We included different examples that hopefully will help you to identify the advantages/disadvantages from the following
equivalent alternatives:

* Either

* Cats `Xor`

* ScalaZ `\/-` and `-\/