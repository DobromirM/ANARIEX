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

import static org.junit.Assert.fail;

/**
 * @author DMarinov
 * Created on: 06/Feb/2019
 */
public class ParserTest
{
    
    private final ByteArrayOutputStream output = new ByteArrayOutputStream();
    private final PrintStream sysOut = System.out;
    
    @Before
    public void setUpStreams()
    {
        System.setOut(new PrintStream(output));
    }
    
    @After
    public void restoreStreams()
    {
        System.setOut(sysOut);
    }
    
    @Test
    public void variableTest() throws IOException
    {
        //Given
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("valid/variableTest.txt");
        CharStream input = CharStreams.fromStream(inputStream);
        AnariexLexer lexer = new AnariexLexer(input);
        AnariexParser parser = new AnariexParser(new CommonTokenStream(lexer));
        ParseTree tree = parser.file();
        AnariexParseVisitor visitor = new AnariexParseVisitor();
        
        try
        {
            //When
            visitor.visit(tree);
            
            //Then
            Assert.assertEquals("4\r\n", output.toString());
        }
        catch (Exception e)
        {
            fail("Parser exception occurred: " + e.getMessage());
        }
    }
    
    @Test
    public void floatNumberTest() throws IOException
    {
        //Given
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("valid/floatNumberTest.txt");
        CharStream input = CharStreams.fromStream(inputStream);
        AnariexLexer lexer = new AnariexLexer(input);
        AnariexParser parser = new AnariexParser(new CommonTokenStream(lexer));
        ParseTree tree = parser.file();
        AnariexParseVisitor visitor = new AnariexParseVisitor();
        
        try
        {
            //When
            visitor.visit(tree);
            
            //Then
            Assert.assertEquals("4.23\r\n", output.toString());
        }
        catch (Exception e)
        {
            fail("Parser exception occurred: " + e.getMessage());
        }
    }
    
    @Test
    public void roundingTest() throws IOException
    {
        //Given
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("valid/roundingTest.txt");
        CharStream input = CharStreams.fromStream(inputStream);
        AnariexLexer lexer = new AnariexLexer(input);
        AnariexParser parser = new AnariexParser(new CommonTokenStream(lexer));
        ParseTree tree = parser.file();
        AnariexParseVisitor visitor = new AnariexParseVisitor();
        
        try
        {
            //When
            visitor.visit(tree);
            
            //Then
            Assert.assertEquals("4\r\n", output.toString());
        }
        catch (Exception e)
        {
            fail("Parser exception occurred: " + e.getMessage());
        }
    }
    
    @Test
    public void commentTest() throws IOException
    {
        //Given
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("valid/commentTest.txt");
        CharStream input = CharStreams.fromStream(inputStream);
        AnariexLexer lexer = new AnariexLexer(input);
        AnariexParser parser = new AnariexParser(new CommonTokenStream(lexer));
        ParseTree tree = parser.file();
        AnariexParseVisitor visitor = new AnariexParseVisitor();
        
        try
        {
            //When
            visitor.visit(tree);
            
            //Then
            Assert.assertEquals("5\r\n", output.toString());
        }
        catch (Exception e)
        {
            fail("Parser exception occurred: " + e.getMessage());
        }
    }
    
    @Test
    public void additionTest() throws IOException
    {
        //Given
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("valid/additionTest.txt");
        CharStream input = CharStreams.fromStream(inputStream);
        AnariexLexer lexer = new AnariexLexer(input);
        AnariexParser parser = new AnariexParser(new CommonTokenStream(lexer));
        ParseTree tree = parser.file();
        AnariexParseVisitor visitor = new AnariexParseVisitor();
        
        try
        {
            //When
            visitor.visit(tree);
            
            //Then
            Assert.assertEquals("112\r\n", output.toString());
        }
        catch (Exception e)
        {
            fail("Parser exception occurred: " + e.getMessage());
        }
    }
    
    @Test
    public void subtractionTest() throws IOException
    {
        //Given
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("valid/subtractionTest.txt");
        CharStream input = CharStreams.fromStream(inputStream);
        AnariexLexer lexer = new AnariexLexer(input);
        AnariexParser parser = new AnariexParser(new CommonTokenStream(lexer));
        ParseTree tree = parser.file();
        AnariexParseVisitor visitor = new AnariexParseVisitor();
        
        try
        {
            //When
            visitor.visit(tree);
            
            //Then
            Assert.assertEquals("-52\r\n", output.toString());
        }
        catch (Exception e)
        {
            fail("Parser exception occurred: " + e.getMessage());
        }
    }
    
    @Test
    public void multiplicationTest() throws IOException
    {
        //Given
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("valid/multiplicationTest.txt");
        CharStream input = CharStreams.fromStream(inputStream);
        AnariexLexer lexer = new AnariexLexer(input);
        AnariexParser parser = new AnariexParser(new CommonTokenStream(lexer));
        ParseTree tree = parser.file();
        AnariexParseVisitor visitor = new AnariexParseVisitor();
        
        try
        {
            //When
            visitor.visit(tree);
            
            //Then
            Assert.assertEquals("120\r\n", output.toString());
        }
        catch (Exception e)
        {
            fail("Parser exception occurred: " + e.getMessage());
        }
    }
    
    @Test
    public void divisionTest() throws IOException
    {
        //Given
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("valid/divisionTest.txt");
        CharStream input = CharStreams.fromStream(inputStream);
        AnariexLexer lexer = new AnariexLexer(input);
        AnariexParser parser = new AnariexParser(new CommonTokenStream(lexer));
        ParseTree tree = parser.file();
        AnariexParseVisitor visitor = new AnariexParseVisitor();
        
        try
        {
            //When
            visitor.visit(tree);
            
            //Then
            Assert.assertEquals("1\r\n", output.toString());
        }
        catch (Exception e)
        {
            fail("Parser exception occurred: " + e.getMessage());
        }
    }
    
    @Test
    public void exponentsTest() throws IOException
    {
        //Given
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("valid/exponentTest.txt");
        CharStream input = CharStreams.fromStream(inputStream);
        AnariexLexer lexer = new AnariexLexer(input);
        AnariexParser parser = new AnariexParser(new CommonTokenStream(lexer));
        ParseTree tree = parser.file();
        AnariexParseVisitor visitor = new AnariexParseVisitor();
        
        try
        {
            //When
            visitor.visit(tree);
            
            //Then
            Assert.assertEquals("59049\r\n", output.toString());
        }
        catch (Exception e)
        {
            fail("Parser exception occurred: " + e.getMessage());
        }
    }
    
    @Test
    public void cosTest() throws IOException
    {
        //Given
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("valid/cosTest.txt");
        CharStream input = CharStreams.fromStream(inputStream);
        AnariexLexer lexer = new AnariexLexer(input);
        AnariexParser parser = new AnariexParser(new CommonTokenStream(lexer));
        ParseTree tree = parser.file();
        AnariexParseVisitor visitor = new AnariexParseVisitor();
        
        try
        {
            //When
            visitor.visit(tree);
            
            //Then
            Assert.assertEquals("0.9074467814501962\r\n", output.toString());
        }
        catch (Exception e)
        {
            fail("Parser exception occurred: " + e.getMessage());
        }
    }
    
    @Test
    public void sinTest() throws IOException
    {
        //Given
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("valid/sinTest.txt");
        CharStream input = CharStreams.fromStream(inputStream);
        AnariexLexer lexer = new AnariexLexer(input);
        AnariexParser parser = new AnariexParser(new CommonTokenStream(lexer));
        ParseTree tree = parser.file();
        AnariexParseVisitor visitor = new AnariexParseVisitor();
        
        try
        {
            //When
            visitor.visit(tree);
            
            //Then
            Assert.assertEquals("0.4201670368266409\r\n", output.toString());
        }
        catch (Exception e)
        {
            fail("Parser exception occurred: " + e.getMessage());
        }
    }
    
    @Test
    public void tanTest() throws IOException
    {
        //Given
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("valid/tanTest.txt");
        CharStream input = CharStreams.fromStream(inputStream);
        AnariexLexer lexer = new AnariexLexer(input);
        AnariexParser parser = new AnariexParser(new CommonTokenStream(lexer));
        ParseTree tree = parser.file();
        AnariexParseVisitor visitor = new AnariexParseVisitor();
        
        try
        {
            //When
            visitor.visit(tree);
            
            //Then
            Assert.assertEquals("0.4630211329364896\r\n", output.toString());
        }
        catch (Exception e)
        {
            fail("Parser exception occurred: " + e.getMessage());
        }
    }
    
    @Test
    public void logTest() throws IOException
    {
        //Given
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("valid/logTest.txt");
        CharStream input = CharStreams.fromStream(inputStream);
        AnariexLexer lexer = new AnariexLexer(input);
        AnariexParser parser = new AnariexParser(new CommonTokenStream(lexer));
        ParseTree tree = parser.file();
        AnariexParseVisitor visitor = new AnariexParseVisitor();
        
        try
        {
            //When
            visitor.visit(tree);
            
            //Then
            Assert.assertEquals("1.1139433523068367\r\n", output.toString());
        }
        catch (Exception e)
        {
            fail("Parser exception occurred: " + e.getMessage());
        }
    }
    
    @Test
    public void negationTest() throws IOException
    {
        //Given
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("valid/negationTest.txt");
        CharStream input = CharStreams.fromStream(inputStream);
        AnariexLexer lexer = new AnariexLexer(input);
        AnariexParser parser = new AnariexParser(new CommonTokenStream(lexer));
        ParseTree tree = parser.file();
        AnariexParseVisitor visitor = new AnariexParseVisitor();
        
        try
        {
            //When
            visitor.visit(tree);
            
            //Then
            Assert.assertEquals("-5\r\n", output.toString());
        }
        catch (Exception e)
        {
            fail("Parser exception occurred: " + e.getMessage());
        }
    }
    
    @Test
    public void groupingTest() throws IOException
    {
        //Given
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("valid/groupingTest.txt");
        CharStream input = CharStreams.fromStream(inputStream);
        AnariexLexer lexer = new AnariexLexer(input);
        AnariexParser parser = new AnariexParser(new CommonTokenStream(lexer));
        ParseTree tree = parser.file();
        AnariexParseVisitor visitor = new AnariexParseVisitor();
        
        try
        {
            //When
            visitor.visit(tree);
            
            //Then
            Assert.assertEquals("46\r\n", output.toString());
        }
        catch (Exception e)
        {
            fail("Parser exception occurred: " + e.getMessage());
        }
    }
    
    @Test
    public void simpleProgram() throws IOException
    {
        //Given
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("valid/simpleProgram.txt");
        CharStream input = CharStreams.fromStream(inputStream);
        AnariexLexer lexer = new AnariexLexer(input);
        AnariexParser parser = new AnariexParser(new CommonTokenStream(lexer));
        ParseTree tree = parser.file();
        AnariexParseVisitor visitor = new AnariexParseVisitor();
        
        try
        {
            //When
            visitor.visit(tree);
            
            //Then
            Assert.assertEquals("169\r\n", output.toString());
        }
        catch (Exception e)
        {
            fail("Parser exception occurred: " + e.getMessage());
        }
    }
    
    @Test
    public void complexProgram() throws IOException
    {
        //Given
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("valid/complexProgram.txt");
        CharStream input = CharStreams.fromStream(inputStream);
        AnariexLexer lexer = new AnariexLexer(input);
        AnariexParser parser = new AnariexParser(new CommonTokenStream(lexer));
        ParseTree tree = parser.file();
        AnariexParseVisitor visitor = new AnariexParseVisitor();
        
        try
        {
            //When
            visitor.visit(tree);
            
            //Then
            Assert.assertEquals("104\r\n30\r\n", output.toString());
        }
        catch (Exception e)
        {
            fail("Parser exception occurred: " + e.getMessage());
        }
    }
}
