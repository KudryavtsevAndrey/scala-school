package lectures.l1
/*

import org.scalatest.{FlatSpec, Matchers}

object CountRoundToLower {
  def undefined() = throw new IllegalArgumentException("*.5")

  def toHigh(v: Double) = v * 10 % 10

  def counter(toUp: Double) = {
    val lastDigit = toUp.toInt
    if (lastDigit == 5)
      undefined()
    else if (lastDigit < 5)
      1
    else
      0
  }

  def count(list: Seq[Double]) = list.map(v => counter(toHigh(v))).sum
}

class CountRoundToLowerTest extends FlatSpec with Matchers {

  import CountRoundToLower.count

  it should "count numbers" in {
    count(Seq(1, 2l, 1.1f)) shouldBe 3
    count(Seq(1.3f, 1.6f)) shouldBe 1
  }

  it should "throw error" in {
    assertThrows[IllegalArgumentException](
      count(Seq(1, 1.6f, 1.5f))
    )
  }

  it should "support big long number" in {
    count(Seq(1000000000l)) shouldBe 1
  }
}

*/