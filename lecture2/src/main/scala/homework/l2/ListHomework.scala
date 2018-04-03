package homework.l2


/**
  * Вам нужно реализовать функции sumOfSquares и multiplicationOfCubes
  * при помощи ListFunctions.fold, композиции и частичного применения функций, sum, multiply и pow
  * Можно добавлять промежуточные функции.
  * Также вам может понадобится функция Function.uncurry,
  * которая из карированной функции делает функцию с несколькими аргументами
  */
object ListHomework {

  val sum = (a: Int, b: Int) => a + b

  val multiply = (a: Int, b: Int) => a * b

  def pow(a: Int, p: Int): Int = if(p <= 0) 1 else a * pow(a, p - 1)

  /**
    * Сумма квадратов чисел в списке
    */
  lazy val sumOfSquares: List[Int] => Int = {
    val squareOfNum: Int => Int = pow(_ ,2)
    val func = (acc: Int, elem: Int) => sum(acc, squareOfNum(elem))
    ListFunctions.fold(0, _)(func)
  }

  /**
    * Произведение кубов чисел в списке
    */
  lazy val multiplicationOfCubes: List[Int] => Int = {
    val cubeOfNum: Int => Int = pow(_, 3)
    val func = (acc: Int, elem: Int) => multiply(acc, cubeOfNum(elem))
    ListFunctions.fold(1, _)(func)
  }
}
