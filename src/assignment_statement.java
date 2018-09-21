/**
 * Created by D. Tyla on 9/6/2018.
 */
public class assignment_statement implements Statement{

    private arithmetic_expression a_express;
    Id var;

    public assignment_statement(Id var, arithmetic_expression a_express)
    {
        if (a_express == null)
            throw new IllegalArgumentException ("null Expression");

        //TODO:should I check the assignment operator or something?
        this.var = var;
        this.a_express = a_express;

    }

    @Override
    public void execute()
    {
        Memory.store (var.getChar(), a_express.evaluate());
    }

}


