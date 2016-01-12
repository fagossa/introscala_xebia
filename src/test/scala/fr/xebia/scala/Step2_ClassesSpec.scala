package fr.xebia.scala

import fr.xebia.scala.Step2_Classes.{Item, UserWithName, UserWithNameAndAge, UserWithPassword}
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

    it("should be completed with TODO 5") {
      val item = new Item("Chocolate", 5)
      Step2_Classes.itemAsString(item).shouldBe("Item(Chocolate,5)")
      Step2_Classes.compare(item, new Item("Chocolate", 5)).shouldBe(true)
      Step2_Classes.compare(item, new Item("Chocolate", 0)).shouldBe(false)
      Step2_Classes.compare(item, new Item("Sugar", 5)).shouldBe(false)
      Step2_Classes.compare(item, new Item("Sugar", 0)).shouldBe(false)
    }

    it("should be completed with TODO 6") {
      Step2_Classes.fidelity(new Item("Beer", 6)).shouldBe(2)
      Step2_Classes.fidelity(new Item("Beer", 10)).shouldBe(2)
      Step2_Classes.fidelity(new Item("Beer", 5)).shouldBe(1)
      Step2_Classes.fidelity(new Item("BeerA", 6)).shouldBe(1)
      Step2_Classes.fidelity(new Item("BeerB", 5)).shouldBe(1)
      Step2_Classes.fidelity(new Item("BeerB", 0)).shouldBe(1)
      Step2_Classes.fidelity(new Item("Hot Chocolate", 0)).shouldBe(0)
      Step2_Classes.fidelity(new Item("Hot Chocolate", 5)).shouldBe(0)
    }

  }

}
