public class Boolean_expression {

    private Relative_op op; //OR Should this just be a string?
    private arithmetic_expression expr1, expr2;

    public Boolean_expression(Relative_op op, arithmetic_expression expr1,
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
            case eq_operator:
                result = expr1.evaluate(expr1.getString()) == expr2.evaluate(expr2.getString());
                break;
            case ne_operator:
                result = expr1.evaluate(expr1.getString()) != expr2.evaluate(expr2.getString());
                break;
            case lt_operator:
                result = expr1.evaluate(expr1.getString()) < expr2.evaluate(expr2.getString());
                break;
            case le_operator:
                result = expr1.evaluate(expr1.getString()) <= expr2.evaluate(expr2.getString());
                break;
            case gt_operator:
                result = expr1.evaluate(expr1.getString()) > expr2.evaluate(expr2.getString());
                break;
            case ge_operator:
                result = expr1.evaluate(expr1.getString()) >= expr2.evaluate(expr2.getString());
                break;
        }
        return result;
    }
}
