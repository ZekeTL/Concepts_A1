/**
 * Created by D. Tyla on 9/6/2018.
 */
public class for_statement implements Statement {

    private Iter it;
    private arithmetic_expression a_express;

    public for_statement(Iter it, arithmetic_expression a_express)
    {
        if (it == null)
            throw new IllegalArgumentException ("null iteration");
        if (a_express == null)
            throw new IllegalArgumentException ("null arthmetic expression");
        this.it = it;
        this.a_express = a_express;

    }

    //TODO: More?

    @Override
    public void execute() {

    }
}
