public class Boolean_expression {

   // public enum RelOperator {EQ_OP, NE_OP, GT_OP, GE_OP, LT_OP, LE_OP};
   //TODO: Call the Relative_Operator Class?
    private RelOperator op;
    private arithmetic_expression expr1, expr2;

    public Boolean_expression(RelOperator op, arithmetic_expression expr1,
                             arithmetic_expression expr2)
    {
        if (op == null)
            throw new IllegalArgumentException ("Relational operator argument cannot be null!");
        if (expr1 == null || expr2 == null)
            throw new IllegalArgumentException ("Arithmetic expression argument cannot be null!");
        this.op = op;
        this.expr1 = expr1;
        this.expr2 = expr2;
    }

    public boolean eval ()
    {
        boolean result = false;
        switch (op)
        {
            case EQ_OP:
                result = expr1.evaluate(expr1.getString()) == expr2.evaluate(expr2.getString());
                break;
            case NE_OP:
                result = expr1.evaluate(expr1.getString()) != expr2.evaluate(expr2.getString());
                break;
            case LT_OP:
                result = expr1.evaluate(expr1.getString()) < expr2.evaluate(expr2.getString());
                break;
            case LE_OP:
                result = expr1.evaluate(expr1.getString()) <= expr2.evaluate(expr2.getString());
                break;
            case GT_OP:
                result = expr1.evaluate(expr1.getString()) > expr2.evaluate(expr2.getString());
                break;
            case GE_OP:
                result = expr1.evaluate(expr1.getString()) >= expr2.evaluate(expr2.getString());
                break;
        }
        return result;
    }
}
