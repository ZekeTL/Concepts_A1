/**
 * Created by D. Tyla on 9/20/2018.
 */
public class Id{
    char id;

    public Id(char ltr){
        if (!LexicalAnalyzer.isValidIdentifier(ltr))
            throw new IllegalArgumentException ("character is not a valid identifier");
        this.id=ltr;
    }

    @Override
    public int evaluate()
    {
        return Memory.fetch (id);
    }

    public char getId ()
    {
        return id;
    }
}