package homework.l3

import scala.annotation.tailrec

sealed trait Box
case class PlayStationBox() extends Box
case class GuitarBox() extends Box
case class EaselBox() extends Box
case class BasicBox() extends Box {
  val size: Int = 4
}
case class BigBox() extends Box {
  val size: Int = 10
}

trait Stuff
case class PlayStation() extends Stuff
case class Guitar() extends Stuff
case class TV(size: Int) extends Stuff
case class Easel() extends Stuff
case class Book() extends Stuff
case class Cat() extends Stuff
case class Uculele() extends Stuff
case class Dish() extends Stuff
case class Shoes() extends Stuff

object BoxPlan {
  def plan(stuff: Seq[Stuff]): Seq[Box] = {
    @tailrec
    def planImpl(stuff: Seq[Stuff], stored: Seq[Box], spacing: Seq[Int]): Seq[Box] = {
      stuff match {
        case Seq() => stored
        case h +: t => h match {
          case PlayStation() => planImpl(t, stored :+ PlayStationBox(), spacing)
          case Guitar() => planImpl(t, stored :+ GuitarBox(), spacing)
          case Easel() => planImpl(t, stored :+ EaselBox(), spacing)
          case tv@TV(_) =>
            if (tv.size > spacing.max && tv.size > 4) {
              planImpl(t, stored :+ BigBox(), spacing :+ (10 - tv.size))
            }
            else if (tv.size > spacing.max && tv.size <= 4) {
              planImpl(t, stored :+ BasicBox(), spacing :+ (4 - tv.size))
            }
            else {
              val maxIdx = spacing.indexOf(spacing.max)
              planImpl(t, stored, spacing.updated(maxIdx, spacing(maxIdx) - tv.size))
            }
          case _: Stuff =>
            if (spacing.max > 0) {
              val canAddIdx = spacing.indexWhere(x => x > 0)
              planImpl(t, stored, spacing.updated(canAddIdx, spacing(canAddIdx) - 1))
            }
            else {
              planImpl(t, stored :+ BasicBox(), spacing :+ 3)
            }
        }
      }
    }

    planImpl(stuff, Seq[Box](), Seq(0))
  }

}