package field

object Interpreter {
  def interpret(e: Expr)(implicit env: Map[String, Double]): Double = e match {
    case Add(l, r) => interpret(l) + interpret(r)
    case Sub(l, r) => interpret(l) - interpret(r)
    case Mul(l, r) => interpret(l) * interpret(r)
    case Div(l, r) => interpret(l) / interpret(r)
    case Var(x) => env(x)
    case LetBinding(x, e1, e2) =>
      val v1 = interpret(e1)
      val nenv = env + (x -> v1)
      interpret(e2)(nenv)
    case Num(n) => n
  }
}
