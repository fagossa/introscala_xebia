package fr.xebia.scala

import fr.xebia.scala.Step2_Classes.{UserWithName, UserWithNameAndAge, UserWithPassword}
import org.scalatest.{FunSpec, Matchers}

import scala.reflect.runtime.universe._

class Step2_ClassesSpec extends FunSpec with Matchers {

  describe("Step2_Classes") {

    it("should be completed with TODO 1") {
      val user: Step2_Classes.Greeter = new Step2_Classes.Greeter()
      user.name.shouldBe("Bob")
      user.greetings().shouldBe("Hello my name is Bob")
    }

    it("should be completed with TODO 2") {
      val user = new UserWithName("Alice")
      user.name.shouldBe("Alice")
      user.greetings().shouldBe("Hello my name is Alice")
    }

    it("should be completed with TODO 3") {
      val user = new UserWithNameAndAge("Yoda", 999)
      user.name.shouldBe("Yoda")
      user.greetings().shouldBe("Hello my name is Yoda, I am 999 years old")
    }

    it("should be completed with TODO 4") {
      typeOf[UserWithPassword].decl(TermName("password")).isPrivate.shouldBe(true)
    }

  }

}
