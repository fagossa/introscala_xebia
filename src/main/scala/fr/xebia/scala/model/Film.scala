package fr.xebia.scala.model

import fr.xebia.scala.control.CollectionTools

import scala.annotation.tailrec

/*
 * A sealed trait can be extended only in the same file as its declaration.
 */
sealed trait Director

/*
 * This is the companion-object of the trait Director. This is also a common way to handle enumerated values in scala
 */
object Director {

  object Kurosawa extends Director {
    override def toString: String = "Kurosawa"
  }

  object Hitchcock extends Director {
    override def toString: String = "Hitchcock"
  }

  object Spielberg extends Director {
    override def toString: String = "Spielberg"
  }

  object Kubrick extends Director {
    override def toString: String = "Kubrick"
  }

  object RandomDirector extends Director {
    override def toString: String = "does_not_matter"
  }

}

/*
 * This is a class that only holds data. They are the equivalent to 'Value Objects'.
 *
 * The 'case' keyword allow us to define correct 'hashCode', 'equals' and 'toString'. Somehow similar to some utilities
 * in java-commons or java.util.Objects. It also avoids to write 'new' when creating new instances.
 *
 * Attributes can have default values. In this class if 'price' is not specified it will hold the value 0
 */
case class Film(name: String, releaseYear: Int, director: Director, `type`: List[Genre], price: Double = 0)
