package field

sealed abstract class Expr
case class Add(t1: Expr, t2: Expr) extends Expr
case class Sub(t1: Expr, t2: Expr) extends Expr
case class Mul(t1: Expr, t2: Expr) extends Expr
case class Div(t1: Expr, t2: Expr) extends Expr
case class LetBinding(x: String, e1: Expr, e2: Expr) extends Expr
case class Var(name: String) extends Expr
case class Num(t: Double) extends Expr
