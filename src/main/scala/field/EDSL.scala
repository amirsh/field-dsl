package field

object EDSL {
  def n(x: Double): Expr = Num(x)

  implicit class ExprOps(self: Expr) {
    def *(o: Expr): Expr = Mul(self, o)
    def +(o: Expr): Expr = Add(self, o)
  }

  def let(e1: Expr)(f: Expr => Expr): Expr = {
    val x = freshName()
    LetBinding(x, e1, f(Var(x)))
  }

  var globalId = 0
  def freshName(): String = {
    globalId += 1
    s"x${globalId}"
  }
}