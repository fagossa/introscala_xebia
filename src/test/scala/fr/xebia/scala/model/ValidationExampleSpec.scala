package fr.xebia.scala.model

import cats.data.Xor
import fr.xebia.scala.control._
import fr.xebia.scala.model.Director._
import FailureReason.{BoringFilm, TooExpensive, NotOldEnough}
import fr.xebia.scala.model.Genre._
import org.scalatest.{FunSpec, Matchers}

import scalaz.{-\/, \/-}

class ValidationExampleSpec extends FunSpec with Matchers {

  val ran = Film("Ran", 1985, Kurosawa, List(Action, Drama, War), 2.3)
  val rashomon = Film("Rashomon", 1950, Kurosawa, List(Crime, Drama), 3.0)
  val psyco = Film("Psycho", 1960, Hitchcock, List(Horror, Mistery, Thriller), 2.7)
  val vertigo = Film("Vertigo", 1958, Hitchcock, List(Mistery, Romance, Thriller), 5.1)

  val completeList = List(ran, rashomon, psyco, vertigo)
  val hitchcockFilms = List(psyco, vertigo)
  val kurosawaFilms = List(ran, rashomon)

  describe("several utilitary methods in List") {

    it("should show how to chain Option filters") {
      ScalaOption.validateFilm(rashomon) shouldBe Some(rashomon)
      ScalaOption.validateFilm(ran) shouldBe None
      ScalaOption.validateFilm(vertigo) shouldBe None
      ScalaOption.validateFilm(psyco) shouldBe None
    }

    it("should show how to use 'Either.left' and 'Either.right'") {
      ScalaEither.validateFilm(rashomon) shouldBe Right(rashomon)
      ScalaEither.validateFilm(ran) shouldBe Left(NotOldEnough)
      ScalaEither.validateFilm(vertigo) shouldBe Left(TooExpensive)
      ScalaEither.validateFilm(psyco) shouldBe Left(BoringFilm)
    }

    it("should show how to use 'Xor.left' and 'Xor.right'") {
      CatsXor.validateFilm(rashomon) shouldBe Xor.Right(rashomon)
      CatsXor.validateFilm(ran) shouldBe Xor.Left(NotOldEnough)
      CatsXor.validateFilm(vertigo) shouldBe Xor.Left(TooExpensive)
      CatsXor.validateFilm(psyco) shouldBe Xor.Left(BoringFilm)
    }

    it("""should show how to use '-\/' and '\/-' """) {
      ScalaZEither.validateFilm(rashomon) shouldBe \/-(rashomon)
      ScalaZEither.validateFilm(ran) shouldBe -\/(NotOldEnough)
      ScalaZEither.validateFilm(vertigo) shouldBe -\/(TooExpensive)
      ScalaZEither.validateFilm(psyco) shouldBe -\/(BoringFilm)
    }

  }
}