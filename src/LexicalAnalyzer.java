import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by D. Tyla on 9/11/2018.
 */
public class LexicalAnalyzer {

    private List<Token> tokens;

    public LexicalAnalyzer();

    public void lineProcessor();

    private Tokentype getTokenType()
    {
        //id → letter
        //literal_integer → digit literal_integer | digit
        //assignment_operator → =
        //le_operator → <=
        ///lt_operator → <
        //ge_operator → >=
        //gt_operator → >
        //eq_operator → = =
        //ne_operator → !=
        //add_operator → +
        //sub_operator → -
        //mul_operator → *
        //div_operator → /
        //mod_operator → %
        //rev_div_operator → \
        //exp_operator → ^

    }
    public Token getNextToken() throws LexicalException
    {
        if (tokens.isEmpty())
            throw new LexicalException ("There aren't any more tokens");
        return tokens.remove(0);
    }

}
