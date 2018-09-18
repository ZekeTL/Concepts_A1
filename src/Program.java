/**
 * Created by D. Tyla on 9/6/2018.
 */
public class Program {

    private Block blk;
    //function id??

    public Program(Block blk)
    {
        if(blk == null)
        {
            throw new IllegalArgumentException ("null block");
        }
        this.blk = blk;
    }

    //TODO??
}
