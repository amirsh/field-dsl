package field

import scala.util.parsing.combinator.syntactical.StandardTokenParsers

object FieldParser extends StandardTokenParsers {
  lexical.reserved   ++= List("let", "in")
  lexical.delimiters += ("*", "+", "-", "/", "=")

  def parse(statement: String): Expr = {
  	phrase(expr)(new lexical.Scanner(statement)) match {
      case Success(r, q) => r
      case failure       => throw new Exception("Unable to parse the arithmetic expression!\n" + failure)
    }
  }

  def exprList(t: Expr, p: List[String ~ Expr]): Expr = {
    // imperative style
    /*
    var res = t
    for(i <- 0 until p.length) {
      val elem = p(i)
      elem match {
        case "+" ~ t2 => res = Add(res, t2)
        case "-" ~ t2 => res = Sub(res, t2)
      }
    }
    res
    */
    // functional style
    p match {
    	case Nil      => t
    	case hd :: tl => hd match {
    	  case "+" ~ t2 => exprList(Add(t, t2), tl)
    	  case "-" ~ t2 => exprList(Sub(t, t2), tl)
    	}
    }
  }

  lazy val expr: Parser[Expr] = term ~ rep(("+" | "-") ~ term) ^^ {
    case t ~ ts => exprList(t, ts)
  }

  lazy val term = factor ~ rep(("*" | "/") ~ factor) ^^ {
    case t ~ ts => 
      // yet another functional style, which is more concise than the one used in `exprList`.
      ts.foldLeft(t) {
        case (t1, "*" ~ t2) => Mul(t1, t2)
        case (t1, "/" ~ t2) => Div(t1, t2)
      }
  } | (("let" ~> ident ~ ("=" ~> expr)) ~ ("in" ~> expr)) ^^ {
    case x ~ e1 ~ e2 => LetBinding(x, e1, e2)
  } | (ident ^^ { case x => Var(x) })

  lazy val factor = 
    "(" ~> expr <~ ")" | 
    num

  lazy val num = numericLit ^^ { t => Num(t.toDouble) }
}
