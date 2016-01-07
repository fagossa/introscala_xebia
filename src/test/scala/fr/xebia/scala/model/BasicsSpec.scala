package fr.xebia.scala.model

import fr.xebia.scala.basics.DistanceConverter
import org.scalatest.{FunSpec, Matchers}

class BasicsSpec extends FunSpec with Matchers {

  describe("some basic examples") {

    it("should cal a function from a companion object") {
        DistanceConverter.fromKmToMile(10) shouldBe 16.09
    }

    it("should use partially applied functions") {
        DistanceConverter.fromKmToMiles(10, 20, 50) shouldBe Seq(16.09, 32.18, 80.45)
    }

  }

}
