package lectures.l3

case class Address(zipCode: Int, city: String)
case class Person(name: String, age: Int, address: Address)

/**
  * В этом задании необходимо дописать функции RussianPerson.unapply и fromRussia.
  * fromRussia должен использовать RussianPerson для проверки адреса Person.
  * Если проверка выполняется, то fromRussia должен возвращать true.
  */
object PatternMatching3 extends App {
  object RussianPerson {
    def unapply(person: Person): Option[String] = {
      person match {
        case p if Seq("Moscow", "Saint Petersburg").contains(p.address.city) => Some("Russian")
        case _ => None
      }
    }
  }

  def fromRussia(person: Person): Boolean = {
    person match {
      case RussianPerson("Russian") => true
      case _ => false
    }
  }

  val cities = Seq(
    "London",
    "Moscow",
    "Saint Petersburg",
    "New York"
  )

  fromRussia(
    Person("Random Name", 32, Address(190000, "Saint Petersburg"))
  ) // true

  fromRussia(
    Person("Random Name", 32, Address(190000, "New York"))
  ) // false
}
