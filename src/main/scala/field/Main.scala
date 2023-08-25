package field

object Main {
  def main(args: Array[String]): Unit = {
  	val expr = FieldParser.parse("24 * 45 + 31")
  	println(expr)
  	println(Interpreter.interpret(expr)(Map()))
  	println(CodeGenerator.generate(expr))
  	val expr2 = FieldParser.parse("let x = 24 * 45 in x + 31")
  	println(expr2)
  	println(Interpreter.interpret(expr2)(Map()))
  	println(CodeGenerator.generate(expr2))
    val expr3 = FieldParser.parse("let x = 24 * 45 in x + 0")
    println(expr3)
    val expr3o = Optimizer.optimize(expr3)
    println(CodeGenerator.generate(expr3o))
    val expr4 = FieldParser.parse("let x = 24 * 45 in 0 + 0 + x")
    println(expr4)
    val expr4o = Optimizer.optimize(expr4)
    println(CodeGenerator.generate(expr4o))

    test2()
  }

  def test2(): Unit = {
    import EDSL._
    val expr = num(24) * num(45) + num(31)
    println(expr)
  }
}
