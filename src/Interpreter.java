/**
 * Created by D. Tyla on 9/13/2018.
 */
public class Interpreter {

    public static void main(String[] args)
    {
        try
        {
            Parser pars = new Parser ("test1.jl"); //TODO change this filename
            Program pro = pars.parse();
            pro.execute();
        }
        catch (ParserException e)
        {
            System.out.println (e.getMessage());
        }
        catch (LexicalException e)
        {
            System.out.println (e.getMessage());
        }
        catch (IllegalArgumentException e)
        {
            System.out.println (e.getMessage());
        }
        catch (FileNotFoundException e)
        {
            System.out.println ("The source file is not found");
        }
        catch (Exception e)
        {
            System.out.println ("An Unknown Error has occurred");
        }
    }


}
