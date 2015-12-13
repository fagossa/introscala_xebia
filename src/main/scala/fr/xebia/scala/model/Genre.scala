package fr.xebia.scala.model

sealed trait Genre

object Genre {

  object Action extends Genre {
    override def toString: String = "Action"
  }

  object Drama extends Genre {
    override def toString: String = "Drama"
  }

  object Horror extends Genre {
    override def toString: String = "Horror"
  }

  object Thriller extends Genre {
    override def toString: String = "Thriller"
  }

  object Crime extends Genre {
    override def toString: String = "Crime"
  }

  object War extends Genre {
    override def toString: String = "War"
  }

  object Romance extends Genre {
    override def toString: String = "Romance"
  }

  object Mistery extends Genre {
    override def toString: String = "Mistery"
  }

  object Comedy extends Genre {
    override def toString: String = "Comedy"
  }

  object SciFi extends Genre {
    override def toString: String = "Sci-Fi"
  }

}