package field

object Optimizer {
  def optimize(e: Expr): Expr = e match {
    // case Add(e1, Num(0)) => optimize(e1)
    // case Add(Num(0), e2) => optimize(e2)
    case Add0(e1) => optimize(e1)
    case Add(e1, e2) => Add(optimize(e1), optimize(e2))
    case Sub(e1, e2) => Sub(optimize(e1), optimize(e2))
    case Mul(e1, e2) => Mul(optimize(e1), optimize(e2))
    case Div(e1, e2) => Div(optimize(e1), optimize(e2))
    case Var(x) => Var(x)
    case Num(n) => Num(n)
    case LetBinding(x, e1, Var(x2)) if x == x2 => optimize(e1)
    case LetBinding(x, e1, e2) => LetBinding(x, optimize(e1), optimize(e2))
  }

  object Add0 {
    def unapply(e: Expr): Option[Expr] = e match {
      case Add(e1, Num(0)) => Some(e1)
      case Add(Num(0), e2) => Some(e2)
      case _ => None
    }
  }
}