package lectures.l8

trait Eq[A] {
  def eq(a1: A, a2: A): Boolean
}

object Eq extends LowPriorityEq {

  implicit def eqOpt[A](implicit e: Eq[A]): Eq[Option[A]] = new Eq[Option[A]] {
    def eq(a1: Option[A], a2: Option[A]): Boolean = {
      (a1, a2) match {
        case (None, None) => true
        case (Some(a1), Some(a2)) if a1 == a2 => true
        case _ => false
      }
    }
  }

  implicit def eqSeq[A](implicit e: Eq[A]): Eq[Seq[A]] = new Eq[Seq[A]] {
    def eq(a1: Seq[A], a2: Seq[A]): Boolean = a1.sameElements(a2)
  }

  // a1(key) can throw an exception if there is no such value
  implicit def eqMap[A](implicit e: Eq[A]): Eq[Map[String, A]] = new Eq[Map[String, A]] {
    def eq(a1: Map[String, A], a2: Map[String, A]): Boolean = {
      if (a1.keys.sameElements(a2.keys))
        return false
      else
        for (key <- a1.keys) {
          if (a1(key) != a2(key))
            return false
        }
        return true
    }
  }
}

trait LowPriorityEq {
  implicit def simpleEq[A]: Eq[A] = new Eq[A] {
    def eq(a1: A, a2: A): Boolean = a1 == a2
  }
}

object Equals {
  object syntax {
    implicit class EqualitySyntax[A](val a1: A) {
      def ===(a2: A)(implicit e: Eq[A]): Boolean = e.eq(a1, a2)
      def !==(a2: A)(implicit e: Eq[A]): Boolean = !(e.eq(a1, a2))
    }
  }
}

import Equals.syntax._
object Main extends App {
  // compiler happy
  Option(1) === Option(1)
  "str" !== "str1"
  true == false

  // compiler sad
//  Option(1) === Option("1")
//  "str" !== false
}

