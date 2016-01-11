package fr.xebia.scala.model

import fr.xebia.scala.model.Director._
import fr.xebia.scala.model.Genre._

import scala.concurrent.{ExecutionContext, Future}

object FilmRepository {

  val films =
    Map(
      1 -> Film("Saving Private Ryan", 1998, Spielberg, List(Action, Drama, War), 3.8),
      2 -> Film("Paths of Glory", 1950, Kubrick, List(Drama, War), 3.0),
      3 -> Film("The Princess Bride", 1987, RandomDirector, List(Comedy), 2.7)
    )

  def findById(id: Int): Future[Option[Film]] =
    Future.successful(films.get(id))

  def slowFindById(id: Int)(implicit ex: ExecutionContext): Future[Option[Film]] =
    Future {
      Thread.sleep(1000)
      films.get(id)
    }

  def findByIdOrFail(id: Int)(implicit ex: ExecutionContext): Future[Film] =
    Future {
      films.getOrElse(id, throw new IllegalArgumentException("Error: Unknown film"))
    }

  def findAll: Future[List[Film]] =
    Future.successful(films.values.toList)

}
