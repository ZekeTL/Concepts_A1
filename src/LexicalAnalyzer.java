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

    public LexicalAnalyzer(String fileName) throws FileNotFoundException, LexicalException
    {
        if (fileName == null)
            throw new IllegalArgumentException ("null file name argument");
        tokenList = new ArrayList<Token>();
        Scanner input = new Scanner (new File(fileName));
        int lineNumber = 0;
        while (input.hasNext())
        {
            String line = input.nextLine();
            lineNumber++;
            processLine (line, lineNumber);
        }
        input.close();
        tokenList.add(new Token (TokenType.EOS_TOK, "EOS", lineNumber, 1));
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
            TokenType tokType = getTokenType (lexeme, lineNumber, index + 1);
            tokenList.add(new Token (tokType, lexeme, lineNumber, index + 1));
            index += lexeme.length();
            index = skipWhiteSpace(line, index);
        }
    }

    private Tokentype getTokenType()
    {
        if (lexeme == null || lexeme.length() == 0)
            throw new IllegalArgumentException ("invalid string argument");
        TokenType tokType = TokenType.EOS_TOK;
        if (Character.isDigit(lexeme.charAt(0)))
            if (allDigits (lexeme))
                tokType = TokenType.LITERAL_INTEGER_TOK;
            else
                throw new LexicalException ("literal integer expecated "+ " at row " +
                        rowNumber  + " and column " + columnNumber);
        else if (Character.isLetter(lexeme.charAt(0)))
        {
            /*if (lexeme.length() == 1)
                tokType = TokenType.ID_TOK;*/
            else if (lexeme.equals("function"))
                tokType = TokenType.function_tok;
            else if (lexeme.equals("end"))
                tokType = TokenType.end_tok;
            else if (lexeme.equals("if"))
                tokType = TokenType.if_tok;
            else if (lexeme.equals("else"))
                tokType = TokenType.else_tok;
            else if (lexeme.equals("print"))
                tokType = TokenType.print_tok;
            else if (lexeme.equals("while"))
                tokType = TokenType.while_tok;
            else
                throw new LexicalException ("invalid lexeme "+ " at row " +
                        rowNumber  + " and column " + columnNumber);
        }
        /*else if (lexeme.equals("("))
            tokType = TokenType.LEFT_PAREN_TOK;
        else if (lexeme.equals(")"))
            tokType = TokenType.RIGHT_PAREN_TOK;*/
        else if (lexeme.equals(">="))
            tokType = TokenType.ge_operator;        //ge_operator → >=
        else if (lexeme.equals(">"))
            tokType = TokenType.gt_operator;         //gt_operator → >
        else if (lexeme.equals("<="))
            tokType = TokenType.le_operator;         //le_operator → <=
        else if (lexeme.equals("<"))
            tokType = TokenType.lt_operator;        ///lt_operator → <
        else if (lexeme.equals("!="))
            tokType = TokenType.ne_operator;         //ne_operator → <=
        else if (lexeme.equals("=="))
            tokType = TokenType.eq_operator;        //eq_operator → = =
        else if (lexeme.equals("%"))
            tokType = TokenType.mod_operator;        //mod_operator → = =
        else if (lexeme.equals("^"))
            tokType = TokenType.exp_operator;         //exp_operator → ^
        else if (lexeme.equals("+"))
            tokType = TokenType.add_operator;         //add_operator → +
        else if (lexeme.equals("-"))
            tokType = TokenType.sub_operator;         //sub_operator → -
        else if (lexeme.equals("*"))
            tokType = TokenType.mul_operator;         //mul_operator → *
        else if (lexeme.equals("/"))
            tokType = TokenType.div_operator;         //div_operator → /
        else if (lexeme.equals ("\"))
            tokType = TokenType.rev_div_operator;     //rev_div_operator → \
        else if (lexeme.equals ("="))
            tokType = TokenType.assignment_operator;   //assignment_operator → =
        else
            throw new LexicalException ("invalid lexeme "+ " at row " +
                    rowNumber  + " and column " + columnNumber);
        return tokType;

        //TODO: Finish this list
        //id → letter
        //literal_integer → digit literal_integer | digit
        //rev_div_operator → \

    }

    private boolean digits(String lexeme)
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

    public Token getNextToken() throws LexicalException
    {
        if (tokenList.isEmpty())
            throw new LexicalException ("no more tokens");
        return tokenList.get(0);
    }

    public Token removeNextToken() throws LexicalException
    {
        if (tokens.isEmpty())
            throw new LexicalException ("There aren't any more tokens");
        return tokens.remove(0);
    }

}
