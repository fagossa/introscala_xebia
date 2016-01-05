package fr.xebia.scala.model

import fr.xebia.scala.control.EventualFuture
import fr.xebia.scala.model.Genre.War
import org.scalatest.concurrent.{Futures, ScalaFutures}
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

    /*
     * Note: 'whenReady' is a simple way to wait until the future completes
     */

    it("should use basic futures") {
      ScalaFutures.whenReady(EventualFuture.getFilmNameById(1)) { response =>
        response shouldBe Some("Saving Private Ryan")
      }
      ScalaFutures.whenReady(EventualFuture.getFilmNameById(9)) { response =>
        response shouldBe None
      }
    }

    it("should operates on values inside futures") {
      ScalaFutures.whenReady(EventualFuture.sumFilmsPricesById(List(1, 2))) {
        _ shouldBe 6.8
      }
    }

    it("should use Future#fold") {
      val eventualValues = List(
        Future.successful(1.1),
        Future.successful(2.2),
        Future.successful(3.3)
      )
      ScalaFutures.whenReady(EventualFuture.sumEventualPrices(eventualValues)) {
        _ shouldBe 6.6
      }
    }

    it("should use Future#sequence") {
      // given
      val movieIds = List(1, 2, 5)
      val expectedFilms = List(
        FilmRepository.films.get(1),
        FilmRepository.films.get(2)
      ).flatten

      // when
      val films: Future[List[Film]] =
        EventualFuture.findMoviesByGenreAndId(War, movieIds)

      ScalaFutures.whenReady(films) {
        _ should be(expectedFilms)
      }
    }

  }

}
