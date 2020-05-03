package anariex;

import org.antlr.v4.gui.TreeViewer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.Arrays;

/**
 * @author DMarinov
 * Created on: 06/Feb/2019
 */
public class Anariex
{
    private ParseTree tree;
    private AnariexLexer lexer;
    private AnariexParser parser;
    static AnariexLogger logger = new AnariexLogger("AnariexLogger");
    
    /**
     * Parse a string of code using the custom Anariex lexer and parser.
     *
     * @param code - Code to be parsed.
     *
     * @return - Boolean - true if the parsing was done without any errors, false otherwise.
     */
    public Boolean parse(String code)
    {
        CharStream input = CharStreams.fromString(code);
        try
        {
            lexer = new AnariexLexer(input);
            lexer.removeErrorListeners();
            lexer.addErrorListener(new AnariexLexerErrorListener());
            parser = new AnariexParser(new CommonTokenStream(lexer));
            parser.setErrorHandler(new AnariexParserErrorStrategy());
            tree = parser.file();
            return true;
        }
        catch (Exception e)
        {
            tree = null;
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    /**
     * Evaluate the expression in the parser.
     */
    public void evaluate()
    {
        try
        {
            AnariexParseVisitor visitor = new AnariexParseVisitor();
            visitor.visit(tree);
        }
        catch (Exception e)
        {
            tree = null;
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * Display the parse tree if it is available.
     */
    public void showTree()
    {
        if (tree != null)
        {
            TreeViewer viewer = new TreeViewer(Arrays.asList(parser.getRuleNames()), tree);
            viewer.open();
        }
    }
}
