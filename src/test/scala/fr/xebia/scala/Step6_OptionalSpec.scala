package fr.xebia.scala

import fr.xebia.scala.control.PriceCalculations
import fr.xebia.scala.model.Gender.{Female, Male, NotSpecified}
import fr.xebia.scala.model.{User, Film, MockFilmData}
import org.scalatest.{FunSpec, Matchers}

class Step6_OptionalSpec extends FunSpec with MockFilmData with Matchers {

  describe("working with Optional") {

    it ("should use orElse with TODO 1") {
      val maybeUser1 = Some(User(1, "Akira", "Kurosawa", 50, Some("M")))
      val maybeUser2 = Some(User(2, "Akira", "Toriyama", 55, Some("M")))
      Step6_Options.getUserOrElse(maybeUser1, None, maybeUser2) shouldBe maybeUser1
      Step6_Options.getUserOrElse(None, maybeUser2, None) shouldBe maybeUser2
      Step6_Options.getUserOrElse(None, None, maybeUser2) shouldBe maybeUser2
      Step6_Options.getUserOrElse(None, None, None) shouldBe None
    }

    it ("should show the behaviour of getOrElse with TODO 2") {
      val maybeUser = Some(User(1, "Akira", "Kurosawa", 50, Some("M")))
      Step6_Options.getUserNameOrElse(maybeUser, defaultName = "Toto") shouldBe "Akira"
      Step6_Options.getUserNameOrElse(None, defaultName = "Toto") shouldBe "Toto"
    }

    it ("should use filter with TODO 3") {
      val user1 = User(2, "Christina", "Lawrence", 26, Some("F"))
      val user2 = User(2, "Angelina", "Jolie", 25, Some("F"))
      val user3 = User(2, "Jennifer", "Lawrence", 25, Some("F"))
      val user4 = User(2, "Scarlett", "Lawrence", 25, None)
      Step6_Options.validUser(user1) shouldBe None
      Step6_Options.validUser(user2) shouldBe None
      Step6_Options.validUser(user3) shouldBe Some(user3)
      Step6_Options.validUser(user4) shouldBe None
    }

    it ("should use pattern matching TODO 4") {
      val user1 = User(2, "Scarlett", "Johansson", 31, Some("F"))
      val user2 = User(2, "Michael", "Fassbender", 38, Some("M"))
      val user3 = User(2, "Titi", "Tata", 25, None)
      Step6_Options.translateGender(user1) shouldBe Female
      Step6_Options.translateGender(user2) shouldBe Male
      Step6_Options.translateGender(user3) shouldBe NotSpecified
    }

    it ("should use 'Option#map' and 'Option#flatMap' with TODO 5") {
      // Our first approach could be to simply map the result
      Step6_Options.getNaiveGenderFromUserId(1) shouldBe Some(Some("M"))
      // but as the result is also an Option then we are :(
      // So, lets try again :D
      Step6_Options.getBetterGenderFromUserId(1) shouldBe Some("M")
      // we could also use a for-comprehension
      Step6_Options.getGenderFromUserIdSugared(1) shouldBe Some("M")
      Step6_Options.getGenderFromUserIdSugared(2) shouldBe None
      Step6_Options.getAllGenders shouldBe Seq("M", "F")
    }

    it("should implement get total prices with index with TODO PriceCalculations_2") {
      PriceCalculations.calculateTotalPriceWithIndex(List.empty[Film], List(3, 5)) shouldBe None
      PriceCalculations.calculateTotalPriceWithIndex(kurosawaFilms, List.empty) shouldBe None
      PriceCalculations.calculateTotalPriceWithIndex(kurosawaFilms, List(2, 5)) shouldBe Some(List(
        (0, 4.6),
        (1, 15)
      ))
    }

  }

}
