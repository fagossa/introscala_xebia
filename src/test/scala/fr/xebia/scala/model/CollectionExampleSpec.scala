package fr.xebia.scala.model

import fr.xebia.scala.model.Director._
import fr.xebia.scala.model.Genre._
import org.scalatest.{Matchers, FunSpec}
import org.scalatest.matchers.ShouldMatchers

class CollectionExampleSpec extends FunSpec with Matchers {

  val ran = Film("Ran", 1985, Kurosawa, List(Action, Drama, War), 2.3)
  val rashomon = Film("Rashomon", 1950, Kurosawa, List(Crime, Drama), 3.0)
  val psyco = Film("Psycho", 1960, Hitchcock, List(Horror, Mistery, Thriller), 2.7)
  val vertigo = Film("Vertigo", 1958, Hitchcock, List(Mistery, Romance, Thriller), 5.1)

  val completeList = List(ran, rashomon, psyco, vertigo)
  val hitchcockFilms = List(psyco, vertigo)
  val kurosawaFilms = List(ran, rashomon)

  describe("several utilitary methods in List") {

    it("should use List#filter") {
      // when
      val films = Film.getFilmsMadeBy(Hitchcock, completeList)
      // then
      films shouldBe hitchcockFilms
    }

    it("should use films List#filter and curring") {
      // when
      val filmFilter = Film.filterFilmsWithDirector(completeList) _
      // then
      filmFilter(Hitchcock) shouldBe hitchcockFilms
      filmFilter(Kurosawa) shouldBe kurosawaFilms
    }

    it("should use films List#filter and high order functions") {
      // when
      val filmFilter = Film.filterFilmsUsingFilter(completeList) _
      // then
      filmFilter(_.director == Hitchcock) shouldBe hitchcockFilms
      filmFilter(_.director == Kurosawa) shouldBe kurosawaFilms
    }

    it("should use films Collection#filter and high order functions") {
      // when
      val filmFilter = Film.filterFilmsUsingFilter2(completeList) _
      // then
      filmFilter(_.director == Hitchcock) shouldBe hitchcockFilms
      filmFilter(_.director == Kurosawa) shouldBe kurosawaFilms
    }

    it("should use films List#filter and several filters") {
      // when
      val filmFilter = Film.filterFilmsUsingMultipleFilter(completeList) _
      // then
      filmFilter(List(
        _.director == Hitchcock,
        _.price > 3
      )) shouldBe List(vertigo)
      filmFilter(List(
        _.director == Kurosawa,
        _.price < 2.4
      )) shouldBe List(ran)
    }

    it("should implement List#sum") {
      Film.sumPricesWithRecursion(hitchcockFilms) shouldBe 7.8
      Film.sumPricesWithRecursion(kurosawaFilms) shouldBe 5.3
    }

    it("should use folding to sum film prices") {
      Film.sumPricesWithFolding(hitchcockFilms) shouldBe 7.8
      Film.sumPricesWithFolding(kurosawaFilms) shouldBe 5.3
    }

    it("should use films List#zip") {
      Film.calculateTotalPrice(List.empty[Film], List(3, 5)) shouldBe None
      Film.calculateTotalPrice(kurosawaFilms, List.empty) shouldBe None
      Film.calculateTotalPrice(kurosawaFilms, List(3, 5)) shouldBe Some(21.9)
    }

    it("should implement films zip with List") {
      Collection.zip(List.empty[Film], List(3, 5)) shouldBe Nil
      Collection.zip(kurosawaFilms, List.empty) shouldBe Nil
      Collection.zip(kurosawaFilms, List(3, 5)) shouldBe List(
        (ran, 3),
        (rashomon, 5)
      )
    }

    it("should implement get total prices with index") {
      Film.calculateTotalPriceWithIndex(List.empty[Film], List(3, 5)) shouldBe None
      Film.calculateTotalPriceWithIndex(kurosawaFilms, List.empty) shouldBe None
      Film.calculateTotalPriceWithIndex(kurosawaFilms, List(2, 5)) shouldBe Some(List(
        (0, 4.6),
        (1, 15)
      ))
    }


    it("should implement fill method") {
      Collection.fillList(0)(ran) shouldBe Nil
      Collection.fillList(1)(ran) shouldBe List(ran)
      Collection.fillList(3)(ran) shouldBe List(ran, ran, ran)
    }

  }

  describe("working with Optional") {
    // optional can be described as a potential List of only one element

    it ("should show the behaviour of getOrElse") {
      val maybeUser = Some(User(1, "Akira", "Kurosawa", 50, Some("M")))
      User.getUserNameOrElse(maybeUser, defaultName = "Toto") shouldBe "Akira"
      User.getUserNameOrElse(None, defaultName = "Toto") shouldBe "Toto"
    }

    it ("should use orElse") {
      val maybeUser1 = Some(User(1, "Akira", "Kurosawa", 50, Some("M")))
      val maybeUser2 = Some(User(2, "Akira", "Toriyama", 55, Some("M")))
      User.getUserOrElse(maybeUser1, None, maybeUser2) shouldBe maybeUser1
      User.getUserOrElse(None, maybeUser2, None) shouldBe maybeUser2
      User.getUserOrElse(None, None, maybeUser2) shouldBe maybeUser2
      User.getUserOrElse(None, None, None) shouldBe None
    }

    it ("should use filter") {
      val user1 = User(2, "Christina", "Lawrence", 26, Some("F"))
      val user2 = User(2, "Angelina", "Jolie", 25, Some("F"))
      val user3 = User(2, "Jennifer", "Lawrence", 25, Some("F"))
      val user4 = User(2, "Scarlett", "Lawrence", 25, None)
      User.validUser(user1) shouldBe None
      User.validUser(user2) shouldBe None
      User.validUser(user3) shouldBe Some(user3)
      User.validUser(user4) shouldBe None
    }

    it ("should use pattern matching") {
      val user1 = User(2, "Scarlett", "Johansson", 31, Some("F"))
      val user2 = User(2, "Michael", "Fassbender", 38, Some("M"))
      val user3 = User(2, "Titi", "Tata", 25, None)
      User.translateGender(user1) shouldBe User.Female
      User.translateGender(user2) shouldBe User.Male
      User.translateGender(user3) shouldBe User.NotSpecified
    }

    it ("should use 'Option#map' and 'Option#flatMap'") {
      // Our first approach could be to simply map the result
      User.getNaiveGenderFromUserId(1) shouldBe Some(Some("M"))
      // but as the result is also an Option then we are :(
      // So, lets try again :D
      User.getBetterGenderFromUserId(1) shouldBe Some("M")
    }
  }
}
