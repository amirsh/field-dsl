package field

object Main {
  def main(args: Array[String]): Unit = {
  	val expr = FieldParser.parse("24 - 45 + 31")
  	println(expr)
  }
}
