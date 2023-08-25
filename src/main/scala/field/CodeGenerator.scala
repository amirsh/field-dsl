package field

object CodeGenerator {
  def generate(t: Expr): String = t match {
    case Add(t1, t2) => s"(${generate(t1)} + ${generate(t2)})"
    case Sub(t1, t2) => s"(${generate(t1)} - ${generate(t2)})"
    case Mul(t1, t2) => s"(${generate(t1)} * ${generate(t2)})"
    case Div(t1, t2) => s"(${generate(t1)} / ${generate(t2)})"
    case Num(t) => t.toString
    case LetBinding(x, e1, e2) =>
      s"double $x = ${generate(e1)};\n${generate(e2)}"
    case Var(x) =>
      x
  }
}
