package lectures.l3

sealed trait Animal
case class Cat(name: String, age: Int) extends Animal
case class Dog(name: String, age: Int, barking: Boolean) extends Animal
case class Fish(ocean: String) extends Animal
case class Dino() extends Animal
case class Turtle() extends Animal

/**
  * Необходимо реализовать функцию showAnimal, так, чтобы она возвращала результат, указанный в комментарии к вызову
  */
object PatternMatching2 extends App {
  def showAnimal(animal: Animal): String = {
    animal match {
      case Cat("CatDog", _) => "CatDog!"
      case Dog("CatDog", _, _) => "CatDog!"
      case Cat(name, _) => s"Hello, $name"
      case Dog(name, _, true) => s"$name dog, Bark-bark!"
      case Dog(name, _, false) => s"Good boy $name"
      case Fish(ocean) => s"Fish from $ocean ocean"
      case Dino() => "I don't know Dino"
      case Turtle() => "I don't know Turtle"
    }
  }

  showAnimal(Fish("Indian")) // Fish from Indian ocean

  showAnimal(Dog("Bobik", 3, true)) // Bobik dog, Bark-bark!
  showAnimal(Dog("Bobik", 1, false)) // Good boy Bobik

  showAnimal(Dog("CatDog", 2, false)) // CatDog!
  showAnimal(Cat("CatDog", 2)) // CatDog!

  showAnimal(Cat("Felix", 2)) // Hello, Felix
  showAnimal(Cat("Random cat", 2)) // Hello, Random cat

  showAnimal(Dino()) // I don't know Dino
  showAnimal(Turtle()) // I don't know Turtle
}
