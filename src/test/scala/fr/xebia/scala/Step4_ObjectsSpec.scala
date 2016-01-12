package fr.xebia.scala

import fr.xebia.scala.Step4_Objects.{Craftsman, RandomGreeter, _}
import org.scalatest.{FunSpec, Matchers}

class Step4_ObjectsSpec extends FunSpec with Matchers {

  describe("Step4_Objects") {

    it("should be completed with TODO 1") {
      greet().shouldBe("RandomGreeter:" + RandomGreeter.greet)
    }

    it("should be completed with TODO 2") {
      Craftsman.beginner().badges.shouldBe(0)
      Craftsman.check(new Craftsman(0)).shouldBe(false)
      Craftsman.check(new Craftsman(9)).shouldBe(false)
      Craftsman.check(new Craftsman(10)).shouldBe(false)
      Craftsman.check(new Craftsman(11)).shouldBe(true)
      Craftsman.check(new Craftsman(99)).shouldBe(true)
    }

  }

}
