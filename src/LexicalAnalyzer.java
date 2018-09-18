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
                tokType = TokenType.ID_TOK;
            else if (lexeme.equals("if"))
                tokType = TokenType.IF_TOK;
            else if (lexeme.equals("function"))
                tokType = TokenType.FUNCTION_TOK;
            else if (lexeme.equals("then"))
                tokType = TokenType.THEN_TOK;
            else if (lexeme.equals("end"))
                tokType = TokenType.END_TOK;
            else if (lexeme.equals("else"))
                tokType = TokenType.ELSE_TOK;
            else if (lexeme.equals("while"))
                tokType = TokenType.WHILE_TOK;
            else if (lexeme.equals("do"))
                tokType = TokenType.DO_TOK;
            else if (lexeme.equals("print"))
                tokType = TokenType.PRINT_TOK;
            else if (lexeme.equals("repeat"))
                tokType = TokenType.REPEAT_TOK;
            else if (lexeme.equals("until"))
                tokType = TokenType.UNTIL_TOK;
            else
                throw new LexicalException ("invalid lexeme "+ " at row " +
                        rowNumber  + " and column " + columnNumber);
        }
        else if (lexeme.equals("("))
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
        else if (lexeme.equals ("="))
            tokType = TokenType.assignment_operator;   //assignment_operator → =
        else
            throw new LexicalException ("invalid lexeme "+ " at row " +
                    rowNumber  + " and column " + columnNumber);
        return tokType;

        //TODO: Finish this list
        //id → letter
        //literal_integer → digit literal_integer | digit
        //assignment_operator → =
        //rev_div_operator → \

    }


    //TODO: Do we need all Digits
    //TODO: Do we need getLexeme

    private int skipWhiteSpace(String line, int index)
    {
        while (index < line.length() && Character.isWhitespace(line.charAt(index)))
            index++;
        return index;
    }

    //TODO: Do we need getLookaheadToken()

    public Token getNextToken() throws LexicalException
    {
        if (tokens.isEmpty())
            throw new LexicalException ("There aren't any more tokens");
        return tokens.remove(0);
    }

}
