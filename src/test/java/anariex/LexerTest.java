package anariex;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author DMarinov
 * Created on: 06/Feb/2019
 */
public class LexerTest
{
    @Test
    public void variableDefinitionTest()
    {
        //Given
        CharStream input = CharStreams.fromString("def foo;");
        List<String> expected = Arrays.asList("def", "foo", ";");
        
        //When
        AnariexLexer lexer = new AnariexLexer(input);
        lexer.addErrorListener(new AnariexLexerErrorListener());
        List<String> actual = Utils.getTokenNames(lexer);
        
        //Then
        Assert.assertEquals(expected, actual);
    }
    
    @Test
    public void variableAssignmentTest()
    {
        //Given
        CharStream input = CharStreams.fromString("foo = 5;");
        List<String> expected = Arrays.asList("foo", "=", "5", ";");
        
        //When
        AnariexLexer lexer = new AnariexLexer(input);
        lexer.addErrorListener(new AnariexLexerErrorListener());
        List<String> actual = Utils.getTokenNames(lexer);
        
        //Then
        Assert.assertEquals(expected, actual);
    }
    
    @Test
    public void printExpressionTest()
    {
        //Given
        CharStream input = CharStreams.fromString("print(23);");
        List<String> expected = Arrays.asList("print", "(", "23", ")", ";");
        
        //When
        AnariexLexer lexer = new AnariexLexer(input);
        lexer.addErrorListener(new AnariexLexerErrorListener());
        List<String> actual = Utils.getTokenNames(lexer);
        
        //Then
        Assert.assertEquals(expected, actual);
    }
    
    @Test
    public void floatNumberTest()
    {
        //Given
        CharStream input = CharStreams.fromString("print(34.32);");
        List<String> expected = Arrays.asList("print", "(", "34.32", ")", ";");
        
        //When
        AnariexLexer lexer = new AnariexLexer(input);
        lexer.addErrorListener(new AnariexLexerErrorListener());
        List<String> actual = Utils.getTokenNames(lexer);
        
        //Then
        Assert.assertEquals(expected, actual);
    }
    
    @Test
    public void variableNameTest()
    {
        //Given
        CharStream input = CharStreams.fromString("def AaaAaa43;");
        List<String> expected = Arrays.asList("def", "AaaAaa43", ";");
        
        //When
        AnariexLexer lexer = new AnariexLexer(input);
        lexer.addErrorListener(new AnariexLexerErrorListener());
        List<String> actual = Utils.getTokenNames(lexer);
        
        //Then
        Assert.assertEquals(expected, actual);
    }
    
    @Test
    public void commentTest()
    {
        //Given
        CharStream input = CharStreams.fromString("#This is a comment #");
        List<String> expected = Collections.emptyList();
        
        //When
        AnariexLexer lexer = new AnariexLexer(input);
        lexer.addErrorListener(new AnariexLexerErrorListener());
        List<String> actual = Utils.getTokenNames(lexer);
        
        //Then
        Assert.assertEquals(expected, actual);
    }
    
    @Test
    public void plusOperatorTest()
    {
        //Given
        CharStream input = CharStreams.fromString("5 + 3;");
        List<String> expected = Arrays.asList("5", "+", "3", ";");
        
        //When
        AnariexLexer lexer = new AnariexLexer(input);
        lexer.addErrorListener(new AnariexLexerErrorListener());
        List<String> actual = Utils.getTokenNames(lexer);
        
        //Then
        Assert.assertEquals(expected, actual);
    }
    
    @Test
    public void minusOperatorTest()
    {
        //Given
        CharStream input = CharStreams.fromString("5 - 3;");
        List<String> expected = Arrays.asList("5", "-", "3", ";");
        
        //When
        AnariexLexer lexer = new AnariexLexer(input);
        lexer.addErrorListener(new AnariexLexerErrorListener());
        List<String> actual = Utils.getTokenNames(lexer);
        
        //Then
        Assert.assertEquals(expected, actual);
    }
    
    @Test
    public void multiplicationOperatorTest()
    {
        //Given
        CharStream input = CharStreams.fromString("5 * 3;");
        List<String> expected = Arrays.asList("5", "*", "3", ";");
        
        //When
        AnariexLexer lexer = new AnariexLexer(input);
        lexer.addErrorListener(new AnariexLexerErrorListener());
        List<String> actual = Utils.getTokenNames(lexer);
        
        //Then
        Assert.assertEquals(expected, actual);
    }
    
    @Test
    public void divisionOperatorTest()
    {
        //Given
        CharStream input = CharStreams.fromString("5 / 3;");
        List<String> expected = Arrays.asList("5", "/", "3", ";");
        
        //When
        AnariexLexer lexer = new AnariexLexer(input);
        lexer.addErrorListener(new AnariexLexerErrorListener());
        List<String> actual = Utils.getTokenNames(lexer);
        
        //Then
        Assert.assertEquals(expected, actual);
    }
    
    @Test
    public void powerOperatorTest()
    {
        //Given
        CharStream input = CharStreams.fromString("5 ^ 3;");
        List<String> expected = Arrays.asList("5", "^", "3", ";");
        
        //When
        AnariexLexer lexer = new AnariexLexer(input);
        lexer.addErrorListener(new AnariexLexerErrorListener());
        List<String> actual = Utils.getTokenNames(lexer);
        
        //Then
        Assert.assertEquals(expected, actual);
    }
    
    @Test
    public void cosFunctionTest()
    {
        //Given
        CharStream input = CharStreams.fromString("cos(90);");
        List<String> expected = Arrays.asList("cos", "(", "90", ")", ";");
        
        //When
        AnariexLexer lexer = new AnariexLexer(input);
        lexer.addErrorListener(new AnariexLexerErrorListener());
        List<String> actual = Utils.getTokenNames(lexer);
        
        //Then
        Assert.assertEquals(expected, actual);
    }
    
    @Test
    public void sinFunctionTest()
    {
        //Given
        CharStream input = CharStreams.fromString("sin(90);");
        List<String> expected = Arrays.asList("sin", "(", "90", ")", ";");
        
        //When
        AnariexLexer lexer = new AnariexLexer(input);
        lexer.addErrorListener(new AnariexLexerErrorListener());
        List<String> actual = Utils.getTokenNames(lexer);
        
        //Then
        Assert.assertEquals(expected, actual);
    }
    
    @Test
    public void tanFunctionTest()
    {
        //Given
        CharStream input = CharStreams.fromString("tan(90);");
        List<String> expected = Arrays.asList("tan", "(", "90", ")", ";");
        
        //When
        AnariexLexer lexer = new AnariexLexer(input);
        lexer.addErrorListener(new AnariexLexerErrorListener());
        List<String> actual = Utils.getTokenNames(lexer);
        
        //Then
        Assert.assertEquals(expected, actual);
    }
    
    @Test
    public void logFunctionTest()
    {
        //Given
        CharStream input = CharStreams.fromString("log(90);");
        List<String> expected = Arrays.asList("log", "(", "90", ")", ";");
        
        //When
        AnariexLexer lexer = new AnariexLexer(input);
        lexer.addErrorListener(new AnariexLexerErrorListener());
        List<String> actual = Utils.getTokenNames(lexer);
        
        //Then
        Assert.assertEquals(expected, actual);
    }
    
    @Test
    public void simpleExpressionTest()
    {
        //Given
        CharStream input = CharStreams.fromString("cos(90) / sin(30) + 1;");
        List<String> expected = Arrays.asList("cos", "(", "90", ")", "/", "sin", "(", "30", ")", "+", "1", ";");
        
        //When
        AnariexLexer lexer = new AnariexLexer(input);
        lexer.addErrorListener(new AnariexLexerErrorListener());
        List<String> actual = Utils.getTokenNames(lexer);
        
        //Then
        Assert.assertEquals(expected, actual);
    }
    
    @Test
    public void complexExpressionTest()
    {
        //Given
        CharStream input = CharStreams.fromString("((2^3 + 5) * (2^3 + 5));");
        List<String> expected = Arrays.asList("(", "(", "2", "^", "3", "+", "5", ")", "*", "(", "2", "^", "3", "+", "5", ")", ")", ";");
        
        //When
        AnariexLexer lexer = new AnariexLexer(input);
        lexer.addErrorListener(new AnariexLexerErrorListener());
        List<String> actual = Utils.getTokenNames(lexer);
        
        //Then
        Assert.assertEquals(expected, actual);
    }
}
