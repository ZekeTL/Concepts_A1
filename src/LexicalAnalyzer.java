import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by D. Tyla on 9/11/2018.
 */
public class LexicalAnalyzer {

    private List<token> tokens;

    public LexicalAnalyzer(String fileName) throws FileNotFoundException, LexicalException
    {
        if (fileName == null)
            throw new IllegalArgumentException ("null file name argument");
        tokens = new ArrayList<>();
        Scanner input = new Scanner (new File(fileName));
        int lineNumber = 0;
        while (input.hasNext())
        {
            String line = input.nextLine();
            lineNumber++;
            processLine (line, lineNumber);
        }
        input.close();
        tokens.add(new token (tokentype.EOS_TOK, "EOS", lineNumber, 1));
    }

    private void processLine(String line, int lineNumber) throws LexicalException
    {
        if (line == null)
            throw new IllegalArgumentException ("null line argument");
        if (lineNumber <= 0)
            throw new IllegalArgumentException ("invalid line number argument");
        int index = skipWhiteSpace(line, 0);
        while (index < line.length())
        {
            String lexeme = getLexeme (line, index);
            tokentype tokType = getTokenType (lexeme, lineNumber, index + 1);
            tokens.add(new token (tokType, lexeme, lineNumber, index + 1));
            index += lexeme.length();
            index = skipWhiteSpace(line, index);
        }
    }

    private tokentype getTokenType(String lexeme, int rowNumber, int columnNumber) throws LexicalException
    {
        if (lexeme == null || lexeme.length() == 0)
            throw new IllegalArgumentException ("invalid string argument");
        tokentype tokType = tokentype.EOS_TOK;
        if (Character.isDigit(lexeme.charAt(0)))
            if (allDigits (lexeme))
                tokType = tokentype.literal_integer;  //literal_integer → digit literal_integer | digit
            else
                throw new LexicalException ("literal integer expecated "+ " at row " +
                        rowNumber  + " and column " + columnNumber);
        else if (Character.isLetter(lexeme.charAt(0)))
        {
            /*if (lexeme.length() == 1)
                tokType = tokentype.ID_TOK;*/
            if (lexeme.equals("function"))
                tokType = tokentype.function_tok;
            else if (lexeme.equals("end"))
                tokType = tokentype.end_tok;
            else if (lexeme.equals("if"))
                tokType = tokentype.if_tok;
            else if (lexeme.equals("else"))
                tokType = tokentype.else_tok;
            else if (lexeme.equals("print"))
                tokType = tokentype.print_tok;
            else if (lexeme.equals("while"))
                tokType = tokentype.while_tok;
            else
                throw new LexicalException ("invalid lexeme "+ " at row " +
                        rowNumber  + " and column " + columnNumber);
        }
        else if(isValidIdentifier(lexeme.charAt(0)))
            tokType = tokentype.id;                  //id → letter
        else if (lexeme.equals(">="))
            tokType = tokentype.ge_operator;        //ge_operator → >=
        else if (lexeme.equals(">"))
            tokType = tokentype.gt_operator;         //gt_operator → >
        else if (lexeme.equals("<="))
            tokType = tokentype.le_operator;         //le_operator → <=
        else if (lexeme.equals("<"))
            tokType = tokentype.lt_operator;        ///lt_operator → <
        else if (lexeme.equals("!="))
            tokType = tokentype.ne_operator;         //ne_operator → <=
        else if (lexeme.equals("=="))
            tokType = tokentype.eq_operator;        //eq_operator → = =
        else if (lexeme.equals("%"))
            tokType = tokentype.mod_operator;        //mod_operator → = =
        else if (lexeme.equals("^"))
            tokType = tokentype.exp_operator;         //exp_operator → ^
        else if (lexeme.equals("+"))
            tokType = tokentype.add_operator;         //add_operator → +
        else if (lexeme.equals("-"))
            tokType = tokentype.sub_operator;         //sub_operator → -
        else if (lexeme.equals("*"))
            tokType = tokentype.mul_operator;         //mul_operator → *
        else if (lexeme.equals("/"))
            tokType = tokentype.div_operator;         //div_operator → /
        /*else if (lexeme.equals ("\"))
            tokType = tokentype.rev_div_operator;     //rev_div_operator → \ */
        else if (lexeme.equals ("="))
            tokType = tokentype.assignment_operator;   //assignment_operator → =
        else
            throw new LexicalException ("invalid lexeme "+ " at row " +
                    rowNumber  + " and column " + columnNumber);
        return tokType;



    }

    private boolean allDigits(String lexeme)
    {
        if (lexeme == null)
            throw new IllegalArgumentException ("null string argument");
        int i = 0;
        while (i < lexeme.length() && Character.isDigit(lexeme.charAt(i)))
            i++;
        return i == lexeme.length();
    }

    private String getLexeme(String line, int index)
    {
        if (line == null)
            throw new IllegalArgumentException ("null string argument");
        if (index < 0)
            throw new IllegalArgumentException ("invalid index argument");
        int i = index;
        while (i < line.length() && !Character.isWhitespace(line.charAt(i)))
            i++;
        return line.substring(index, i);
    }

    private int skipWhiteSpace(String line, int index)
    {
        while (index < line.length() && Character.isWhitespace(line.charAt(index)))
            index++;
        return index;
    }

    public token getNextToken() throws LexicalException //looking ahead
    {
        if (tokens.isEmpty())
            throw new LexicalException ("no more tokens");
        return tokens.get(0);
    }

    public token removeNexttoken() throws LexicalException
    {
        if (tokens.isEmpty())
            throw new LexicalException ("There aren't any more tokens");
        return tokens.remove(0);
    }

    public static boolean isValidIdentifier(char ch){
        if(Character.isLetter(ch) && Character.isLowerCase(ch)) return true;
        else return false;
    }

}
