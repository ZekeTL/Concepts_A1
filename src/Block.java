import java.util.ArrayList;
import java.util.Iterator;
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
        ar.add(statement);
    }

    public void process(){
        Iterator<Statement> iterate = this.ar.iterator();
        while(iterate.hasNext()){
            iterate.next().execute();
        }
    }
    public int size(){
        return this.ar.size();
    }
}