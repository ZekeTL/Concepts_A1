/**
 * Created by Deja T. Jackson on 9/21/2018.
 */
public class literal_integer implements arithmetic_expression{

    public int value;

    public literal_integer(int value)
    {
        this.value = value;
    }
    //@Override
    public int evaluate()
    {
        return value;
    }
}
