/**
 * Created by D. Tyla on 9/18/2018.
 */
public class token {


    private tokentype tokType;
    private String lexeme;
    private int rowNumber;
    private int columnNumber;


    public token(tokentype tokType, String lexeme, int rowNumber,
                 int columnNumber)
    {
        if (tokType == null)
            throw new IllegalArgumentException ("null TokenType argument");
        if (lexeme == null || lexeme.length() == 0)
            throw new IllegalArgumentException ("invalid lexeme argument");
        if (rowNumber <= 0)
            throw new IllegalArgumentException ("invalid row number argument");
        if (columnNumber <= 0)
            throw new IllegalArgumentException ("invalid column number argument");
        this.tokType = tokType;
        this.lexeme = lexeme;
        this.rowNumber = rowNumber;
        this.columnNumber = columnNumber;
    }

    public tokentype getTokType()
    {
        return tokType;
    }

    public String getLexeme()
    {
        return lexeme;
    }

    public int getRowNumber()
    {
        return rowNumber;
    }

    public int getColumnNumber()
    {
        return columnNumber;
    }

}
