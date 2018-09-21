/**
 * Created by D. Tyla on 9/20/2018.
 */
import java.io.FileNotFoundException;

/**
 * @author dgayler
 *
 * Parser class implements a recursive descent parsing algorithm
 * for the given grammar of a subset of Lua
 */
public class Parser
{

    private LexicalAnalyzer lex;

    /**
     * @param fileName cannot be null - checked in LexicalAnalyzer
     * @throws FileNotFoundException if file cannot be found
     * @throws LexicalException
     * postcondition: parser object has been created
     */
    public Parser (String fileName) throws FileNotFoundException, LexicalException
    {
        lex = new LexicalAnalyzer (fileName);
    }

    /**
     * @return Program object containing an intermediate representation of the program
     * @throws ParserException if a parsing error occurred
     * implements the production <program> → function id ( ) <block> end
     */
    public Program parse() throws ParserException
    {
        token tok = getNextToken();
        match (tok, tokentype.FUNCTION_TOK);
        Id functionName = getId();
        tok = getNextToken ();
        match (tok, tokentype.LEFT_PAREN_TOK);
        tok = getNextToken ();
        match (tok, tokentype.RIGHT_PAREN_TOK);
        Block blk = getBlock();
        tok = getNextToken ();
        match (tok, tokentype.end_tok);
        tok = getNextToken();
        if (tok.getTokType() != tokentype.EOS_TOK)
            throw new ParserException ("garbage at end of file");
        return new Program (blk);
    }

    /**
     * @return Block object
     * @throws ParserException if a parsing error occurred
     * implements the production <block> → <statement> | <statement> <block>
     */
    private Block getBlock() throws ParserException
    {
        Block blk = new Block();
        token tok = getNextToken(); //looking ahead
        while (isValidStartOfStatement (tok))
        {
            Statement stmt = getStatement();
            blk.add (stmt);
            tok = getNextToken(); //looking ahead
        }
        return blk;
    }

    /**
     * @return statement object
     * @throws ParserException if a parsing error occurred
     * implements the production <statement> → <if_statement> | <assignment_statement> | <while_statement> | <print_statement> | <repeat_statement>
     */
    private Statement getStatement() throws ParserException
    {
        Statement stmt;
        token tok = getNextToken(); //looking ahead
        if (tok.getTokType() == tokentype.if_tok)
            stmt = getIfStatement();
        else if (tok.getTokType() == tokentype.while_tok)
            stmt = getWhileStatement();
        else if (tok.getTokType() == tokentype.print_tok)
            stmt = getPrintStatement();
        else if (tok.getTokType() == tokentype.for_tok)
            stmt = getForStatement();
        else if (tok.getTokType() == tokentype.id)
            stmt = getAssignmentStatement();
        else
            throw new ParserException ("invalid statement at row " +
                    tok.getRowNumber()  + " and column " + tok.getColumnNumber());
        return stmt;
    }

    /**
     * @return assignment statement
     * @throws ParserException if a parsing error occurred
     * implements the production <assignment_statement> -> id <assignment_operator> <arithmetic_expression>
     */
    private Statement getAssignmentStatement() throws ParserException
    {
        Id var = getId();
        token tok = getNextToken();
        match (tok, tokentype.assignment_operator);
        arithmetic_expression expr = getArithmeticExpression();
        return new assignment_statement(var, expr);
    }

    /**
     * @return repeat statement
     * @throws ParserException if a parsing error occurred
     * implements the production <repeat_statement> -> repeat <block> until <boolean_expression>
     */
    private Statement getForStatement() throws ParserException
    {
        token tok = getNextToken();
        match (tok, tokentype.for_tok);
        Block blk = getBlock();
        tok = getNextToken();
        match (tok, tokentype.UNTIL_TOK); //TODO?
        Boolean_expression expr = getBooleanExpression();
        return new RepeatStatement (blk, expr);
    }

    /**
     * @return print statement
     * @throws ParserException if a parsing error occurred
     * implements the production <print_statement> → print ( <arithmetic_expression> )
     */
    private Statement getPrintStatement() throws ParserException
    {
        token tok = getNextToken();
        match (tok, tokentype.print_tok);
        tok = getNextToken ();
        match (tok, tokentype.LEFT_PAREN_TOK);
        arithmetic_expression expr = getArithmeticExpression();
        tok = getNextToken ();
        match (tok, tokentype.RIGHT_PAREN_TOK);
        return new Print_statement (expr);
    }

    /**
     * @return while statement
     * @throws ParserException if a parsing error occurred
     * implements the production <while_statement> → while <boolean_expression> do <block> end
     */
    private Statement getWhileStatement() throws ParserException
    {
        token tok = getNextToken ();
        match (tok, tokentype.while_tok);
        Boolean_expression expr = getBooleanExpression();
        tok = getNextToken ();
        match (tok, tokentype.DO_TOK);
        Block blk = getBlock();
        tok = getNextToken();
        match (tok, tokentype.end_tok);
        return new while_statement (expr, blk);
    }

    /**
     * @return if statement
     * @throws ParserException if a parsing error occurred
     * implements the production <if_statement> → if <boolean_expression> then <block> else <block> end
     */
    private Statement getIfStatement() throws ParserException
    {
        token tok = getNextToken ();
        match (tok, tokentype.if_tok);
        Boolean_expression expr = getBooleanExpression();
        tok = getNextToken ();
        match (tok, tokentype.THEN_TOK);
        Block blk1 = getBlock();
        tok = getNextToken ();
        match (tok, tokentype.else_tok);
        Block blk2 = getBlock();
        tok = getNextToken();
        match (tok, tokentype.end_tok);
        return new if_statement(expr, blk1, blk2);
    }

    /**
     * @param tok cannot be null - checked with assertion
     * @return whether tok can be the start of a statement
     */
    private boolean isValidStartOfStatement(token tok)
    {
        assert (tok != null);
        return tok.getTokType() == tokentype.id ||
                tok.getTokType() == tokentype.if_tok||
                tok.getTokType() == tokentype.while_tok ||
                tok.getTokType() == tokentype.print_tok||
                tok.getTokType() == tokentype.for_tok;
    }

    /**
     * @return arithmetic expression
     * @throws ParserException if a parsing error occurred
     * implements the production <arithmetic_expression> → <id> | <literal_integer> | <arithmetic_op> <arithmetic_expression> <arithmetic_expression>
     */
    private arithmetic_expression getArithmeticExpression() throws ParserException
    {
        arithmetic_expression expr;
        token tok = getNextToken();
        if (tok.getTokType() == tokentype.id)
            expr = getId();
        else if (tok.getTokType() == tokentype.literal_integer)
            expr = getLiteralInteger();
        else
            expr = getBinaryExpression();
        return expr;
    }

    /**
     * @return binary expression
     * @throws ParserException if a parsing error occurred
     * implements the grammar expression <arithmetic_op> <arithmetic_expression> <arithmetic_expression>
     */
    private Binary_expression getBinaryExpression() throws ParserException
    {
        Binary_expression.arithmetic_operator op = getArithmeticOperator();
        arithmetic_expression expr1 = getArithmeticExpression();
        arithmetic_expression expr2 = getArithmeticExpression();
        return new Binary_expression (op, expr1, expr2);
    }

    /**
     * @return arithmetic operator
     * @throws ParserException if a parsing error occurred
     * implements the production <arithmetic_op> → add_operator | sub_operator | mul_operator | div_operator
     */
    private Binary_expression.arithmetic_operator getArithmeticOperator() throws ParserException
    {
        Binary_expression.arithmetic_operator op;
        token tok = getNextToken();
        if (tok.getTokType() == tokentype.ADD_TOK)
            op = Binary_expression.arithmetic_operator.ADD_OP;
        else if (tok.getTokType() == tokentype.SUB_TOK)
            op = Binary_expression.ArithmeticOperator.SUB_OP;
        else if (tok.getTokType() == tokentype.MUL_TOK)
            op = Binary_expression.ArithmeticOperator.MUL_OP;
        else if (tok.getTokType() == tokentype.DIV_TOK)
            op = Binary_expression.ArithmeticOperator.DIV_OP;
        else
            throw new ParserException ("arithmetic operator expected at row " +
                    tok.getRowNumber()  + " and column " + tok.getColumnNumber());
        return op;
    }

    /**
     * @return literal integer
     * @throws ParserException if a parsing error occurred
     */
    private LiteralInteger getLiteralInteger() throws ParserException
    {
        token tok = getNextToken ();
        if (tok.getTokType() != TokenType.LITERAL_INTEGER_TOK)
            throw new ParserException ("literal integer expected at row " +
                    tok.getRowNumber()  + " and column " + tok.getColumnNumber());
        int value = Integer.parseInt(tok.getLexeme());
        return new LiteralInteger (value);
    }

    /**
     * @return an id
     * @throws ParserException if a parsing error occurred
     */
    private Id getId() throws ParserException
    {
        token tok = getNextToken();
        if (tok.getTokType() != tokentype.ID_TOK)
            throw new ParserException ("identifier expected at row " +
                    tok.getRowNumber()  + " and column " + tok.getColumnNumber());
        return new Id (tok.getLexeme().charAt(0));
    }

    /**
     * @return boolean expression
     * @throws ParserException if a parsing error occurred
     * implements the production <boolean_expression> → <relative_op> <arithmetic_expression> <arithmetic_expression>
     */
    private Boolean_expression getBooleanExpression() throws ParserException
    {
        Boolean_expression.Relative_op op = getRelationalOperator();
        arithmetic_expression expr1 = getArithmeticExpression();
        arithmetic_expression expr2 = getArithmeticExpression ();
        return new Boolean_expression(op, expr1, expr2);
    }

    /**
     * @return relative operator
     * @throws ParserException if a parsing error occurred
     * implements the production <relative_op> → le_operator | lt_operator | ge_operator | gt_operator | eq_operator | ne_operator
     */
    private Boolean_expression.Relative_op getRelationalOperator() throws ParserException
    {
        Boolean_expression.Relative_op op;
        token tok = getNextToken();
        if (tok.getTokType() == tokentype.eq_operator)
            op = Boolean_expression.Relative_op.eq_operator;
        else if (tok.getTokType() == tokentype.ne_operator)
            op = Boolean_expression.Relative_op.NE_OP;
        else if (tok.getTokType() == tokentype.gt_operator)
            op = Boolean_expression.Relative_op.GT_OP;
        else if (tok.getTokType() == tokentype.ge_operator)
            op = Boolean_expression.Relative_op.GE_OP;
        else if (tok.getTokType() == tokentype.lt_operator)
            op = Boolean_expression.Relative_op.LT_OP;
        else if (tok.getTokType() == tokentype.le_operator)
            op = Boolean_expression.Relative_op.LE_OP;
        else
            throw new ParserException ("relational operator expected at row " +
                    tok.getRowNumber()  + " and column " + tok.getColumnNumber());
        return op;
    }

    /**
     * @param tok cannot be null - checked by assertion
     * @param tokType cannot be null - checked by assertion
     * @throws ParserException if type of tok is not tokType
     */
    private void match(token tok, tokentype tokType) throws ParserException
    {
        assert (tok != null);
        assert (tokType != null);
        if (tok.getTokType() != tokType)
            throw new ParserException (tokType + " expected at row " +
                    tok.getRowNumber()  + " and column " + tok.getColumnNumber());
    }

    /**
     * @return copy of next token
     * @throws ParserException if there are no more tokens
     */
    private token getNextToken() throws ParserException
    {
        token tok = null;
        try
        {
            tok = lex.getNextToken();//looking ahead
        }
        catch (LexicalException e)
        {
            throw new ParserException ("no more tokens");
        }
        return tok;
    }

    /**
     * @return next token
     * @throws ParserException if there are no more tokens
     */
    private token removeNextToken() throws ParserException
    {
        token tok = null;
        try
        {
            tok = lex.getNextToken();
        }
        catch (LexicalException e)
        {
            throw new ParserException ("no more tokens");
        }
        return tok;
    }
}

