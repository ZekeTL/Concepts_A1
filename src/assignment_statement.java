/**
 * Created by D. Tyla on 9/6/2018.
 */
public class assignment_statement {

    private arithmetic_expression a_express;
    private assignment_operator a_op;
    //TODO: Id var;

    public assignment_statement(arithmetic_expression a_express, assignment_operator a_op)
    {
        if (a_express == null)
            throw new IllegalArgumentException ("null Expression");

        //TODO:should I check the assignment operator or something?

        this.a_express = a_express;

    }

    //TODO: do anything else?

}
