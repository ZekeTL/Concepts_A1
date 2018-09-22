/**
 * Created by Ezekiel Lawal on 9/12/18
 */
public class Print_statement implements Statement
{

    private arithmetic_expression expr;

    public Print_statement(arithmetic_expression expr){
        if(expr == null){
            throw new IllegalArgumentException("Expression cannot be empty");
        }
        this.expr = expr;

    }
    @Override
    public void execute()
    {
        System.out.println (expr);

    }

}
