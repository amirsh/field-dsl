package field

object Optimizer {
  def optimize(e: Expr): Expr = e match {
    case Add(t1, Num(0)) => optimize(t1)
    case Add(Num(0), t1) => optimize(t1)
    case Add(t1, t2) => Add(optimize(t1), optimize(t2))
    case Sub(t1, t2) => Sub(optimize(t1), optimize(t2))
    case Mul(t1, t2) => Mul(optimize(t1), optimize(t2))
    case Div(t1, t2) => Div(optimize(t1), optimize(t2))
    case Num(t) => Num(t)
    case LetBinding(x, e1, e2) =>
      LetBinding(x, optimize(e1), optimize(e2))
    case Var(x) => Var(x)
  }
}
