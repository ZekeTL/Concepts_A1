/**
 * Created by D. Tyla on 9/6/2018.
 */
public class while_statement {


    private boolean_expression b_expr;
    private Block blk;

    public while_statement(boolean_expression b_expr, Block blk)
    {
        if (b_expr == null)
            throw new IllegalArgumentException ("null boolean expression");
        if (blk == null)
            throw new IllegalArgumentException ("null block ");
        this.b_expr = b_expr;
        this.blk = blk;

    }

    //TODO??
}
