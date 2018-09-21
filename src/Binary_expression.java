public class Binary_expression implements arithmetic_expression{

    private arithmetic_expression expr1, expr2;

    private arithmetic_operator op;

    public Binary_expression(arithmetic_operator op, arithmetic_expression expr1, arithmetic_expression expr2)
    {
        if (expr1 == null || expr2 == null)
            throw new IllegalArgumentException ("null expression argument");
        this.expr1 = expr1;
        this.expr2 = expr2;
        this.op = op;
    }

    //@Override
    public int evaluate()
    {
        int value;
        if (op == arithmetic_operator.add_operator)
            value = expr1.evaluate() + expr2.evaluate();
        else
            value = expr1.evaluate() * expr2.evaluate();
        return value;
    }
}
