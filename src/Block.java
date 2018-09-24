import java.util.ArrayList;
/**
 * Created by D. Tyla on 9/6/2018.
 */
public class Block{
    ArrayList<Statement> ar;

    public Block()
    { ar = new ArrayList<Statement>(); }

    public Block(Statement statement){
        ar.add(statement);
    }

    public void add(Statement statement){
        if (ar == null)
            throw new IllegalArgumentException ("null statement argument");
        ar.add(statement);
    }

    public void process(){
        for (int i = 0; i < ar.size(); i++)
            ar.get(i).execute();
    }
    public int size(){
        return this.ar.size();
    }
}