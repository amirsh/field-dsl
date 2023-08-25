package field

object EDSL {
  def _var(name: String) = Var(name)
  def num(v: Double) = Num(v)

  implicit class ExprOps(self: Expr) {
    def *(o: Expr): Expr = Mul(self, o)
    def +(o: Expr): Expr = Add(self, o)
  }
}