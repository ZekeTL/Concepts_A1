/**
 * Created by D. Tyla on 9/6/2018.
 */
public class for_statement implements Statement {

    private assignment_statement stat;
    private Boolean_expression bool;
    private Block blk;

    public for_statement(assignment_statement stat, Boolean_expression bool, Block blk)
    {
        if (stat == null)
            throw new IllegalArgumentException ("null assignment statement");
        else if (bool == null)
            throw new IllegalArgumentException ("null boolean expression");
        else if (blk == null)
            throw new IllegalArgumentException("null black");
        this.stat = stat;
        this.bool = bool;
        this.blk = blk;

    }

    //TODO: More?

    @Override
    public void execute() {
        stat.execute();
        while(bool.eval()){
            blk.process();
        }
    }
}
