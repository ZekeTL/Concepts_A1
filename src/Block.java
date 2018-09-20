import java.util.ArrayList;
import java.util.Iterator;
/**
 * Created by D. Tyla on 9/6/2018.
 */
public class Block{
    ArrayList<Statement> ar;
    public Block(Statement statement){
        ar = new ArrayList<Statement>();
        ar.add(statement);
    }
    public void add(Statement statement){
        ar.add(statement);
    }
    public void process(){
        Iterator<Statement> iterate = this.ar.iterator(); //TODO: Should this be Iter not Iterator for the Iter Class
        while(iterate.hasNext()){
            iterate.next().evaluate();
        }
    }
    public int size(){
        return this.ar.size();
    }
}