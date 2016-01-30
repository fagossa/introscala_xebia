package fr.xebia.scala

import fr.xebia.scala.model.Genre.War
import fr.xebia.scala.model.{Film, FilmRepository, MockFilmData}
import org.scalatest.concurrent.{Eventually, Futures, ScalaFutures}
import org.scalatest.time.{Millis, Seconds, Span}
import org.scalatest.{FunSpec, Matchers}

import scala.language.postfixOps
import scala.util.{Failure, Success}

class Step8_FutureSpec extends FunSpec with MockFilmData
  with Matchers with Futures with Eventually {

  import scala.concurrent.ExecutionContext.Implicits.global

  //http://www.artima.com/docs-scalatest-2.0.M5/org/scalatest/concurrent/Futures.html
  implicit val defaultPatience =
    PatienceConfig(timeout = Span(1, Seconds), interval = Span(5, Millis))

  describe("several methods using futures") {

    import scala.concurrent._
    import scala.concurrent.duration._

    /*
     * Note: 'whenReady' is a simple way to wait until the future completes
     */
    it("should use basic futures with TODO 1") {
      ScalaFutures.whenReady(Step8_Futures.getFilmNameById(1)) { response =>
        response shouldBe Some("Saving Private Ryan")
      }
      ScalaFutures.whenReady(Step8_Futures.getFilmNameById(9)) { response =>
        response shouldBe None
      }
    }

    /*
     * Note: 'eventually' is loop that allow us to execute a pice of code several
     * times until we get a timeout
     */
    it("should handle the results of futures with TODO 2") {
      val successResponse = Step8_Futures.getEventualFilmById(1)
      eventually(timeout(scaled(1 seconds))) {
        successResponse.onSuccess {
          case film => film shouldBe FilmRepository.films.get(1).get
        }
        successResponse.onFailure {
          case error: Error => fail(error.getMessage)
        }
      }
      val failingResponse = Step8_Futures.getEventualFilmById(6)
      eventually(timeout(scaled(2 seconds))) {
        failingResponse.onComplete {
          case Success(film) => fail(s"this should not happen->$film")
          case Failure(error) => info(s"We got a '${error.getMessage}'. No problemo ;)")
        }
      }
    }

    it("should operates on values inside futures with TODO 3") {
      ScalaFutures.whenReady(Step8_Futures.sumFilmsPricesById(List(1, 2))) {
        _ shouldBe 6.8
      }
    }

    it("should use Future#fold with TODO 4") {
      val eventualValues = List(
        Future.successful(1.1),
        Future.successful(2.2),
        Future.successful(3.3)
      )
      ScalaFutures.whenReady(Step8_Futures.sumEventualPrices(eventualValues)) {
        _ shouldBe 6.6
      }
    }

    it("should use Future#sequence TODO 5") {
      // given
      val movieIds = List(1, 2, 5)
      val expectedFilms = List(
        FilmRepository.films.get(1),
        FilmRepository.films.get(2)
      ).flatten

      // when
      val films: Future[List[Film]] =
        Step8_Futures.findMoviesByGenreAndId(War, movieIds)

      ScalaFutures.whenReady(films) {
        _ should be(expectedFilms)
      }
    }

    it("should get 4 durations of 1 second created with different techniques with TODO 6") {
      Step8_Futures.get4DurationsOfTenSeconds shouldBe List.fill(4)(10 seconds)
    }

    /*
     * Note: 'Await.result' blocks the current execution thread until timeout
     */
    it("should execute the futures in a concurrent way with TODO 7") {
      // given
      val firstFilmId = 1
      val secondFilmId = 2
      val thirdFilmId = 3
      val duration = 1 second

      // when
      val eventualSum = Step8_Futures.eventualSumFromMovieIds(firstFilmId, secondFilmId, thirdFilmId)

      // then
      Await.result(eventualSum, duration) shouldBe 9.5
    }

    it("should execute the futures in a concurrent way handling timeouts with TODO 8") {
      // given
      val firstFilmId = 1
      val secondFilmId = 2
      val thirdFilmId = 3
      val duration = 2 seconds

      intercept[java.util.concurrent.TimeoutException] {
        // when
        val eventualSum = Step8_Futures.slowEventualSumFromMoviesIds(firstFilmId, secondFilmId, thirdFilmId)
        // then
        Await.result(eventualSum, duration) shouldBe 9.5
      }
    }

    it("should congratulate those who did all the exercises seriously !") {
      Step8_Futures.thatIsAllFolks() shouldBe "Congrats !"
    }

  }

}
