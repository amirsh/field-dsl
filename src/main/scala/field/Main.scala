package field

object Main {
  def main(args: Array[String]): Unit = {
  	// val expr = FieldParser.parse("24 * 45 + 31")
  	// println(expr)
    // println(Interpreter.interpret(expr)(Map()))
    // println(CodeGenerator.generate(expr))
    // val expr2 = FieldParser.parse("let x = 24 * 45 in x + 31")
    // println(expr2)
    // println(Interpreter.interpret(expr2)(Map()))
    // println(CodeGenerator.generate(expr2))
    // val expr3 = FieldParser.parse("let x = 24 * 45 in x + 0")
    // println(CodeGenerator.generate(expr3))
    // val expr3o = Optimizer.optimize(Optimizer.optimize(expr3))
    // println(CodeGenerator.generate(expr3o))
    test()
  }

  def test(): Unit = {
    import EDSL._
    val expr = let(n(24) * n(45))(x =>
      let(x + n(31))(x =>
        x + n(64)
      )
    )
    println(expr)
    println(Interpreter.interpret(expr)(Map()))
    val expr1 = FieldParser.parse("let x = 24 * 45 in let x = x + 31 in x + 64")
    println(Interpreter.interpret(expr1)(Map()))
  }
}
