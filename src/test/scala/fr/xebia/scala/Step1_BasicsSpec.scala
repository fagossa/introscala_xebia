package fr.xebia.scala

import java.util.UUID

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
      Step1_Basics.plus21(() => 2).shouldBe(23)
      Step1_Basics.plus21(() => 3).shouldBe(24)
      Step1_Basics.plus21(() => 8).shouldBe(29)
    }

    it("should be completed with TODO 5") {
      Step1_Basics.product(2)(8).shouldBe(16)
      Step1_Basics.product(3)(-3).shouldBe(-9)
      Step1_Basics.product(8)(25).shouldBe(200)
    }

    it("should be completed with TODO 6") {
      Step1_Basics.fxy(1, 2).shouldBe(6)
      Step1_Basics.fx(2)(1).shouldBe(6)

      Step1_Basics.fxy(3, 5).shouldBe(31)
      Step1_Basics.fx(5)(3).shouldBe(31)

      Step1_Basics.fxy(1, 2).shouldBe(6)
      Step1_Basics.fy(1)(2).shouldBe(6)

      Step1_Basics.fxy(3, 5).shouldBe(31)
      Step1_Basics.fy(3)(5).shouldBe(31)
    }

    it("should be completed with TODO 7") {
      Step1_Basics.gxy(1)(2).shouldBe(-2)
      Step1_Basics.gx(2)(1).shouldBe(-2)

      Step1_Basics.gxy(3)(5).shouldBe(-19)
      Step1_Basics.gx(5)(3).shouldBe(-19)

      Step1_Basics.gxy(1)(2).shouldBe(-2)
      Step1_Basics.gy(1)(2).shouldBe(-2)

      Step1_Basics.gxy(3)(5).shouldBe(-19)
      Step1_Basics.gy(3)(5).shouldBe(-19)
    }

    it("should be completed with TODO 8") {
      Step1_Basics.vararg(1).shouldBe(1)
      Step1_Basics.vararg(42, 8).shouldBe(50)
      Step1_Basics.vararg(1, 2, 3).shouldBe(6)
      Step1_Basics.vararg(75, 98, 89, 11).shouldBe(273)
    }

    it("should be completed with TODO 9") {
      Step1_Basics.expression(0).shouldBe("ok")
      Step1_Basics.expression(1).shouldBe("ko")
      Step1_Basics.expression(2).shouldBe("ko")
      Step1_Basics.expression(42).shouldBe("ko")
    }

    it("should be completed with TODO 10") {
      Step1_Basics.oddEven(-2).shouldBe("even")
      Step1_Basics.oddEven(-1).shouldBe("odd")
      Step1_Basics.oddEven(0).shouldBe("even")
      Step1_Basics.oddEven(1).shouldBe("odd")
      Step1_Basics.oddEven(99).shouldBe("odd")
      Step1_Basics.oddEven(1024).shouldBe("even")
    }

    it("should be completed with TODO 11") {
      val random = UUID.randomUUID().toString
      val unsafeRuntimeException = () => throw new RuntimeException("bad runtime exception:" + random)
      val unsafeException = () => throw new Exception("bad checked exception:" + random)
      Step1_Basics.safeHandler(unsafeRuntimeException).shouldBe("Runtime:" + "bad runtime exception:" + random + ":Finally")
      Step1_Basics.safeHandler(unsafeException).shouldBe("bad checked exception:" + random + ":Finally")
    }

  }

}
