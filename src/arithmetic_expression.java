public interface arithmetic_expression {

    /*private Id id;
    private int val;
    private String op;

    private arithmetic_expression expr1;
    private arithmetic_expression expr2;

    public arithmetic_expression(Id id){
        this.id = id;
        op = "";
    }

    public arithmetic_expression(int val){
        this.val = val;
        op = "";
    }

    public arithmetic_expression(String op, arithmetic_expression expr1, arithmetic_expression expr2){
        this.op = op;
        this.expr1 = expr1;
        this.expr2 = expr2;
    }

    public int evaluate(){
        if(id == null && op.equals(""))
            return this.val;
        else if(op.equals(""))
            return Memory.fetch(id.getChar());
        else
            return this.calculate();
    }

    private int calculate(){
        if(op == "*")
            return expr1.evaluate() * expr2.evaluate();
        else if(op == "/")
            return expr1.evaluate() / expr2.evaluate();
        else if(op == "+")
            return expr1.evaluate() + expr2.evaluate();
        else if(op == "-")
            return expr1.evaluate() - expr2.evaluate();
        else
            throw new IllegalArgumentException("Invalid operator, You entered " + op);
    }*/

   public int evaluate();

}
