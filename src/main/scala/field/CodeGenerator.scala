package field

object CodeGenerator {
  def generate(e: Expr): String = e match {
    case Add(e1, e2) => s"(${generate(e1)} + ${generate(e2)})" 
    case Sub(e1, e2) => s"(${generate(e1)} - ${generate(e2)})" 
    case Mul(e1, e2) => s"(${generate(e1)} * ${generate(e2)})" 
    case Div(e1, e2) => s"(${generate(e1)} / ${generate(e2)})" 
    case Num(v) => v.toString
    case Var(x) => x
    case LetBinding(x, e1, e2) => s"double ${x} = ${generate(e1)};\n${generate(e2)}"
  }
}