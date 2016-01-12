package fr.xebia.scala

import org.scalatest.{FunSpec, Matchers}

import scala.reflect.runtime.universe._

class Step1_BasicsSpec extends FunSpec with Matchers {

  describe("Step1_Basics") {

    it("should be completed with TODO 1") {
      Step1_Basics.variable.shouldBe(24)
      typeOf[Step1_Basics.type].decl(TermName("variable")).asTerm.accessed.asTerm.isVar.shouldBe(true)
    }

    it("should be completed with TODO 2") {
      Step1_Basics.value.shouldBe(42)
      typeOf[Step1_Basics.type].decl(TermName("value")).asTerm.accessed.asTerm.isVal.shouldBe(true)
    }

    it("should be completed with TODO 3") {
      Step1_Basics.square(2).shouldBe(4)
      Step1_Basics.square(3).shouldBe(9)
      Step1_Basics.square(8).shouldBe(64)
    }

    it("should be completed with TODO 4") {
      Step1_Basics.fxy(1, 2).shouldBe(6)
      Step1_Basics.fx(2)(1).shouldBe(6)

      Step1_Basics.fxy(3, 5).shouldBe(31)
      Step1_Basics.fx(5)(3).shouldBe(31)

      Step1_Basics.fxy(1, 2).shouldBe(6)
      Step1_Basics.fy(1)(2).shouldBe(6)

      Step1_Basics.fxy(3, 5).shouldBe(31)
      Step1_Basics.fy(3)(5).shouldBe(31)
    }

    it("should be completed with TODO 5") {
      Step1_Basics.gxy(1)(2).shouldBe(-2)
      Step1_Basics.gx(2)(1).shouldBe(-2)

      Step1_Basics.gxy(3)(5).shouldBe(-19)
      Step1_Basics.gx(5)(3).shouldBe(-19)

      Step1_Basics.gxy(1)(2).shouldBe(-2)
      Step1_Basics.gy(1)(2).shouldBe(-2)

      Step1_Basics.gxy(3)(5).shouldBe(-19)
      Step1_Basics.gy(3)(5).shouldBe(-19)
    }

    it("should be completed with TODO 6") {
      Step1_Basics.vararg(1).shouldBe(1)
      Step1_Basics.vararg(42, 8).shouldBe(50)
      Step1_Basics.vararg(1, 2, 3).shouldBe(6)
      Step1_Basics.vararg(75, 98, 89, 11).shouldBe(273)
    }

    it("should be completed with TODO 7") {
      Step1_Basics.expression(0).shouldBe("ok")
      Step1_Basics.expression(1).shouldBe("ko")
      Step1_Basics.expression(2).shouldBe("ko")
      Step1_Basics.expression(42).shouldBe("ko")
    }

  }

}
