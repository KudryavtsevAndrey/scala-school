package homework.l3

case class Boook(title: String, author: String, genre: String, height: Int, publisher: String)

object TextParser {
  def parse(text: String): Seq[Boook] = {

    val lines = text.split("\n")
    val bookRegex = """(.+),"(.+)",(.+),(\d+),(.+)""".r
    var books = Seq[Boook]()
    for (line <- lines) {
      line match {
        case bookRegex(title, author, genre, height, publisher) =>
          books = books :+ Boook(title, author, genre, height.toInt, publisher)
        case _ =>
      }
    }
    books
  }
}
