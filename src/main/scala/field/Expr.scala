package field

sealed abstract class Expr
case class Add(t1: Expr, t2: Expr) extends Expr
case class Sub(t1: Expr, t2: Expr) extends Expr
case class Num(t: Double) extends Expr
