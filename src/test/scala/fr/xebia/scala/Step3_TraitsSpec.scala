package fr.xebia.scala

import fr.xebia.scala.Step3_Traits.{Greeter, GentleGreeter}
import org.scalatest.{FunSpec, Matchers}

import scala.reflect.runtime.universe._

class Step3_TraitsSpec extends FunSpec with Matchers {

  describe("Step3_Traits") {

    it("should be completed with TODO 1") {
      new Greeter {

        override var name: String = "Gandalf"

        override def greet(): String = "you shall not pass"

      }.greetDecorated().shouldBe("Decorated:you shall not pass")
    }

    it("should be completed with TODO 2") {
      val greeter: GentleGreeter = new GentleGreeter()
      greeter.name = "Bob"
      greeter.greet().shouldBe("Hello my name is Bob")
      greeter.greetDecorated().shouldBe("Decorated:Hello my name is Bob")
    }

  }

}
