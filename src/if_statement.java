/**
 * Created by D. Tyla on 9/6/2018.
 */
public class if_statement implements Statement{


    private Boolean_expression b_expr;
    private Block blk, blk2;


    public if_statement(Boolean_expression b_expr, Block blk, Block bl2)
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

    @Override
    public void execute()
    {
        if (b_expr.evaluate())
            blk.execute();
        else
            blk2.execute();
    }
}
