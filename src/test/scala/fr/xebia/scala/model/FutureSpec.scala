package fr.xebia.scala.model

import fr.xebia.scala.control.EventualFuture
import fr.xebia.scala.model.Genre.War
import org.scalatest.concurrent.{ScalaFutures, Futures, Eventually}
import org.scalatest.concurrent.PatienceConfiguration.{Interval, Timeout}
import org.scalatest.time.{Millis, Seconds, Span}
import org.scalatest.{FunSpec, Matchers}

import scala.concurrent.Future

class FutureSpec extends FunSpec with MockFilmData
with Matchers with Futures {

  import scala.concurrent.ExecutionContext.Implicits.global

  //http://www.artima.com/docs-scalatest-2.0.M5/org/scalatest/concurrent/Futures.html
  implicit val defaultPatience =
    PatienceConfig(timeout = Span(1, Seconds), interval = Span(5, Millis))

  describe("several methods using futures") {

    it("should use Future#sequence") {
      // when
      val movieIds = List(1, 2, 5)
      val films: Future[List[Film]] = EventualFuture.findMoviesByGenreAndId(War, movieIds)

      val expectedFilms = List(
        FilmRepository.films.get(1),
        FilmRepository.films.get(2)
      ).flatten

      /*
       * whenReady is a simple way to wait until the future completes
       */
      ScalaFutures.whenReady(films) { actualList =>
        // run assertions against the object returned in the future
        actualList should be(expectedFilms)
      }
    }

  }

}
