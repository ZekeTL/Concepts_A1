/**
 * Created by D. Tyla on 9/6/2018.
 */
public class if_statement {


    private boolean_expression b_expr;
    private Block blk, blk2;


    public if_statement(boolean_express b_expr, Block blk, Block bl2)
    {
        if (b_expr == null)
        {
            throw new IllegalArgumentException ("null boolean expression");
        }
        if(blk == null || blk2 == null)
        {
            throw new IllegalArgumentException ("null block");
        }
        this.b_expr = b_expr;
        this.blk = blk;
        this.blk2 = blk2;
    }

    //TODO:Anything else??
}
