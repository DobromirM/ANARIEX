package anariex;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

/**
 * @author DMarinov
 * Created on: 06/Feb/2019
 */
public class ErrorsTest
{
    private final ByteArrayOutputStream error = new ByteArrayOutputStream();
    private final PrintStream sysErr = System.err;
    
    @Before
    public void setUpStreams()
    {
        System.setErr(new PrintStream(error));
    }
    
    @After
    public void restoreStreams()
    {
        System.setErr(sysErr);
    }
    
    @Test
    public void notDefinedErrorTest() throws IOException
    {
        //Given
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("invalid/notDefinedErrorTest.txt");
        CharStream input = CharStreams.fromStream(inputStream);
        AnariexLexer lexer = new AnariexLexer(input);
        lexer.addErrorListener(new AnariexLexerErrorListener());
        AnariexParser parser = new AnariexParser(new CommonTokenStream(lexer));
        parser.setErrorHandler(new AnariexParserErrorStrategy());
        AnariexParseVisitor visitor = new AnariexParseVisitor();
        
        try
        {
            //When
            ParseTree tree = parser.file();
            visitor.visit(tree);
        }
        catch (Exception e)
        {
            //Then
            String actual = "Runtime error!\n" + "'a' is not a defined variable!\n" + "Location line 1:0";
            Assert.assertEquals(actual, e.getMessage());
        }
    }
    
    @Test
    public void initErrorTest() throws IOException
    {
        //Given
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("invalid/initErrorTest.txt");
        CharStream input = CharStreams.fromStream(inputStream);
        AnariexLexer lexer = new AnariexLexer(input);
        lexer.addErrorListener(new AnariexLexerErrorListener());
        AnariexParser parser = new AnariexParser(new CommonTokenStream(lexer));
        parser.setErrorHandler(new AnariexParserErrorStrategy());
        AnariexParseVisitor visitor = new AnariexParseVisitor();
        
        try
        {
            //When
            ParseTree tree = parser.file();
            visitor.visit(tree);
        }
        catch (Exception e)
        {
            //Then
            String actual = "Initialization error!\n" + "'a' has not been initialized yet!\n" + "Location: line 1:6";
            Assert.assertEquals(actual, e.getMessage());
        }
    }
    
    @Test
    public void divisionByZeroErrorTest() throws IOException
    {
        //Given
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("invalid/divisionByZeroErrorTest.txt");
        CharStream input = CharStreams.fromStream(inputStream);
        AnariexLexer lexer = new AnariexLexer(input);
        lexer.addErrorListener(new AnariexLexerErrorListener());
        AnariexParser parser = new AnariexParser(new CommonTokenStream(lexer));
        parser.setErrorHandler(new AnariexParserErrorStrategy());
        AnariexParseVisitor visitor = new AnariexParseVisitor();
        
        try
        {
            //When
            ParseTree tree = parser.file();
            visitor.visit(tree);
        }
        catch (Exception e)
        {
            //Then
            String actual = "Arithmetic exception!\n" + "Division by zero!\n" + "Location:line 1:6";
            Assert.assertEquals(actual, e.getMessage());
        }
    }
    
    @Test
    public void unrecognizedCharErrorTest() throws IOException
    {
        //Given
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("invalid/unrecognizedCharErrorTest.txt");
        CharStream input = CharStreams.fromStream(inputStream);
        AnariexLexer lexer = new AnariexLexer(input);
        lexer.addErrorListener(new AnariexLexerErrorListener());
        AnariexParser parser = new AnariexParser(new CommonTokenStream(lexer));
        parser.setErrorHandler(new AnariexParserErrorStrategy());
        AnariexParseVisitor visitor = new AnariexParseVisitor();
        
        try
        {
            //When
            ParseTree tree = parser.file();
            visitor.visit(tree);
        }
        catch (Exception e)
        {
            //Then
            String actual = "Unrecognized character error!\n" + "token recognition error at: '$'\n" + "Location 1:4";
            Assert.assertEquals(actual, e.getMessage());
        }
    }
    
    @Test
    public void extraneousInputErrorTest() throws IOException
    {
        //Given
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("invalid/extraneousInputErrorTest.txt");
        CharStream input = CharStreams.fromStream(inputStream);
        AnariexLexer lexer = new AnariexLexer(input);
        lexer.addErrorListener(new AnariexLexerErrorListener());
        AnariexParser parser = new AnariexParser(new CommonTokenStream(lexer));
        parser.setErrorHandler(new AnariexParserErrorStrategy());
        AnariexParseVisitor visitor = new AnariexParseVisitor();
        
        try
        {
            //When
            ParseTree tree = parser.file();
            visitor.visit(tree);
        }
        catch (Exception e)
        {
            //Then
            String actual = "Extraneous input error!\n" + "Got a '</>' but expected {'(', '-', 'cos', 'sin', 'tan', 'log', NUMBER, ID}!\n" + "Location: line 2:4";
            Assert.assertEquals(actual, e.getMessage());
        }
    }
    
    @Test
    public void inputMismatchErrorTest() throws IOException
    {
        //Given
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("invalid/inputMismatchErrorTest.txt");
        CharStream input = CharStreams.fromStream(inputStream);
        AnariexLexer lexer = new AnariexLexer(input);
        lexer.addErrorListener(new AnariexLexerErrorListener());
        AnariexParser parser = new AnariexParser(new CommonTokenStream(lexer));
        parser.setErrorHandler(new AnariexParserErrorStrategy());
        AnariexParseVisitor visitor = new AnariexParseVisitor();
        
        try
        {
            //When
            ParseTree tree = parser.file();
            visitor.visit(tree);
        }
        catch (Exception e)
        {
            //Then
            String actual = "Input mismatch error!\n" + "Got a '<+>' but expected '='!\n" + "Location: line 2:2";
            Assert.assertEquals(actual, e.getMessage());
        }
    }
}
