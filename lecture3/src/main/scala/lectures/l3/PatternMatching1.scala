package lectures.l3

/**
  * Необходимо реализовать функции f1 и f2, чтобы они выводили результат на экран так,
  * как указано в комментарии к использованию
  */
object PatternMatching1 extends App {
  def f1(xs: Seq[Int]): Unit = {
    xs.foreach { x =>
      x match {
        case num if num % 3 == 0 => println(num)
        case _ =>
      }
    }
  }
  def f2(xs: Seq[Any]): Unit = {
    xs.foreach { x =>
      x match {
        case s: String => println(s"Got string: $s")
        case neg: Int if neg < 0 => println("Got minus! WOW!")
        case mult10: Int if mult10 % 10 == 0 => println(s"Got $mult10 that divided by 10")
        case d: Double => println(s"Got double: $d")
        case some => println(s"Unexpected value: $some")
        case _ =>
      }
    }
  }

  /**
    * Вывести только те числа, у которых остаток от деления на 3 равен 0
    */
  f1(Seq(1, 2, 3, 4, 5, 6, 7, 8, 9, 10))
  // 3
  // 6
  // 9

  f2(Seq("A", -10, 0.33, 10, 'c', 100))
  // Got string: A
  // Got minus! WOW!
  // Got double: 0.33
  // Got 10 that divided by 10
  // Unexpected value: c
  // Got 100 that divided by 10
}
