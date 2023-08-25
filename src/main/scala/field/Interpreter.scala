package field

object Interpreter {
  def interpret(t: Expr)(env: Map[String, Double]): Double = t match {
    case Add(t1, t2) => interpret(t1)(env) + interpret(t2)(env)
    case Sub(t1, t2) => interpret(t1)(env) - interpret(t2)(env)
    case Mul(t1, t2) => interpret(t1)(env) * interpret(t2)(env)
    case Div(t1, t2) => interpret(t1)(env) / interpret(t2)(env)
    case Num(t) => t
    case LetBinding(x, e1, e2) =>
      val xv = interpret(e1)(env)
      val nenv = env + (x -> xv)
      interpret(e2)(nenv)
    case Var(x) =>
      env(x)
  }
}
