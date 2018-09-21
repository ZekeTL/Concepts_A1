/**
 * Created by D. Tyla on 9/20/2018.
 */
public class Id implements arithmetic_expression {

    private char id;

    public Id(char ltr){
        if (!LexicalAnalyzer.isValidIdentifier(ltr))
            throw new IllegalArgumentException ("character is not a valid identifier");
        this.id=ltr;
    }

    public int evaluate()
    {
        return Memory.fetch (id);
    }

    public char getChar ()
    {
        return id;
    }
}