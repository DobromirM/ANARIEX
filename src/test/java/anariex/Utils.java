package anariex;

import org.antlr.v4.runtime.Token;

import java.util.ArrayList;
import java.util.List;

/**
 * @author DMarinov
 * Created on: 06/Feb/2019
 */
class Utils
{
    /**
     * Return token names as list from a lexer
     *
     * @param lexer - Lexer containing tokens
     *
     * @return - List of token names
     */
    static List<String> getTokenNames(AnariexLexer lexer)
    {
        Token token = lexer.nextToken();
        List<String> tokenNames = new ArrayList<>();
        
        while (token.getType() != -1)
        {
            tokenNames.add(token.getText());
            token = lexer.nextToken();
        }
        
        return tokenNames;
    }
}
