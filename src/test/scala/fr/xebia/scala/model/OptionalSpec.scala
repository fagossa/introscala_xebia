package fr.xebia.scala.model

import fr.xebia.scala.control.PriceCalculations
import fr.xebia.scala.model.Gender.{NotSpecified, Male, Female}
import org.scalatest.{Matchers, FunSpec}

class OptionalSpec extends FunSpec with MockFilmData with Matchers {

  describe("working with Optional") {

    it("should implement get total prices with index") {
      PriceCalculations.calculateTotalPriceWithIndex(List.empty[Film], List(3, 5)) shouldBe None
      PriceCalculations.calculateTotalPriceWithIndex(kurosawaFilms, List.empty) shouldBe None
      PriceCalculations.calculateTotalPriceWithIndex(kurosawaFilms, List(2, 5)) shouldBe Some(List(
        (0, 4.6),
        (1, 15)
      ))
    }

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
      User.translateGender(user1) shouldBe Female
      User.translateGender(user2) shouldBe Male
      User.translateGender(user3) shouldBe NotSpecified
    }

    it ("should use 'Option#map' and 'Option#flatMap'") {
      // Our first approach could be to simply map the result
      User.getNaiveGenderFromUserId(1) shouldBe Some(Some("M"))
      // but as the result is also an Option then we are :(
      // So, lets try again :D
      User.getBetterGenderFromUserId(1) shouldBe Some("M")
      // we could also use a for-comprehension
      User.getGenderFromUserIdSugared(1) shouldBe Some("M")
      User.getGenderFromUserIdSugared(2) shouldBe None
      User.getAllGenders shouldBe Seq("M", "F")
    }
  }


}
