package lectures.l4

import scala.annotation.tailrec
import scala.util.Random

object Collection3 extends App {

  // Задан некоторый список пассажиров М и Ж в слуйчайно количестве.
  // Необходимо распределить их по купэ, при этом не должно быть пустых и в каждом не больше 4х человек
  // В результате посчитать сколько купе какого типа получилось

  sealed trait Person

  case object Male extends Person

  case object Female extends Person

  case object AttackHelicopter extends Person

  sealed trait StateroomType

  case object MaleOnlyStateroom extends StateroomType

  case object FemaleOnlyStateroom extends StateroomType

  case object MixedStateroom extends StateroomType

  case class Stateroom(passengers: Seq[Person]) {
    require(passengers.length <= 4, "Stateroom overflow")
    require(passengers.nonEmpty, "Stateroom is empty")
  }

  def sort(list: Seq[Person]): Seq[Stateroom] = {
    val malesCount = list.count(_ == Male)
    val femalesCount = list.count(_ == Female)
    val helisCount = list.count(_ == AttackHelicopter)
    val leftOver = (Seq.fill(malesCount % 4)(Male) ++ Seq.fill(femalesCount % 4)(Female)).splitAt(4)

    Seq.fill[Stateroom](malesCount / 4)(Stateroom(Seq.fill(4)(Male))) ++
      Seq.fill[Stateroom](femalesCount / 4)(Stateroom(Seq.fill(4)(Female))) ++
      Seq.fill[Stateroom](helisCount / 4)(Stateroom(Seq.fill(4)(AttackHelicopter))) ++
      Seq(Stateroom(leftOver._1), Stateroom(leftOver._2),
        Stateroom(Seq.fill(helisCount % 4)(AttackHelicopter))).filterNot(_.passengers.isEmpty)
  }

  def getType(room: Stateroom): StateroomType = {
    room.passengers match {
      case p if p.forall(_ == Male) => MaleOnlyStateroom
      case p if p.forall(_ == Female) => FemaleOnlyStateroom
      case _ => MixedStateroom
    }
  }

  def count(list: Seq[Stateroom]): Map[StateroomType, Int] = {
    list.map(x => getType(x) -> list.count(_ == x)).toMap
  }

  val males = Seq.fill(Random.nextInt(20))(Male)
  val females = Seq.fill(Random.nextInt(20))(Female)
  val helicopters = Seq.fill(Random.nextInt(6))(AttackHelicopter)
  val passengers = Random.shuffle(males ++ females ++ helicopters)

  println(count(sort(passengers)))

}
