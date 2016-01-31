package fr.xebia.scala

import fr.xebia.scala.model.Director.RandomDirector
import fr.xebia.scala.model.Genre.{Mistery, Thriller}
import fr.xebia.scala.model.{Film, FilmRepository, MockFilmData}
import org.scalatest.concurrent.{Eventually, Futures, ScalaFutures}
import org.scalatest.{FunSpec, Matchers}

class Step9_RefactoringSpec extends FunSpec with MockFilmData
with Matchers with Eventually with Futures {

  import scala.concurrent.ExecutionContext.Implicits.global

  describe("Some test on the repository") {

    it("should find an element of one List into another") {
      FilmRepository.containsAny(List(1, 2, 3, 4, 5), List(1)) shouldBe true
      FilmRepository.containsAny(List(1, 2, 3, 4, 5), List(1, 2)) shouldBe true
      FilmRepository.containsAny(List(1, 2, 3, 4, 5), List()) shouldBe false
      FilmRepository.containsAny(List(1, 2, 3, 4, 5), List(6)) shouldBe false
      FilmRepository.containsAny(List(1, 2, 3, 4, 5), List(6, 7)) shouldBe false
    }

    // You don't need to do nothing special with this test
    it("should get a related film of the same genre") {
      val filmId: Int = 1
      val film = FilmRepository.films(filmId)
      ScalaFutures.whenReady(FilmRepository.findRandomFilmByDirector(film.name, film.`type`)) { maybeFilm =>
        maybeFilm should be(defined)
        maybeFilm.get.name shouldBe "Paths of Glory"
      }
    }

    // You don't need to do nothing special with this test
    it("should NOT get a related film") {
      val filmId: Int = 3
      val film = FilmRepository.films(filmId)
      ScalaFutures.whenReady(FilmRepository.findRandomFilmByDirector(film.name, film.`type`)) { maybeFilm =>
        maybeFilm shouldNot be(defined)
      }
    }

  }

  describe("A renting system") {

    val aCustomer = Customer("Myself")

    it("should throw an exception when invalid price") {
      val film = Film("Memento", 2000, RandomDirector, List(Mistery, Thriller), -1)
      val thrown = intercept[IllegalArgumentException] {
        Step9_Refactoring.rent(aCustomer, film)
      }
      thrown.getMessage should include("Invalid price")
    }

    it("should find another film of the same genre") {
      val filmId: Int = 1
      ScalaFutures.whenReady(Step9_Refactoring.getRandomFilmBySameGenreOf(filmId)) { film =>
        film.name shouldBe "Paths of Glory"
      }
    }

    it("should throw an exception when the initial film does not exist") {
      val filmId: Int = 999
      ScalaFutures.whenReady(Step9_Refactoring.getRandomFilmBySameGenreOf(filmId).failed) { ex =>
        ex shouldBe an[IllegalArgumentException]
        ex should have message s"Film with Id <$filmId> not found"
      }
    }

    it("should throw an exception when there is no related film") {
      val filmId: Int = 3
      ScalaFutures.whenReady(Step9_Refactoring.getRandomFilmBySameGenreOf(filmId).failed) { ex =>
        ex shouldBe an[IllegalArgumentException]
        ex should have message "No film related"
      }
    }


  }

}
