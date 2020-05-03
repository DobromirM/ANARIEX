package anariex;

import org.antlr.v4.runtime.ParserRuleContext;

import java.util.HashMap;
import java.util.Map;

/**
 * @author DMarinov
 * Created on: 06/Feb/2019
 */
public class AnariexParseVisitor extends AnariexParserBaseVisitor<Double>
{
    private Map<String, Double> memory = new HashMap<>();
    
    /**
     * Line visitor. If the context contains statement evaluate it and display it.
     *
     * @param context - Line context.
     *
     * @return - Result of the statement or null.
     */
    @Override
    public Double visitLine(AnariexParser.LineContext context)
    {
        if (context.statement() != null)
        {
            Double result = this.visit(context.statement());
            displayLine();
            return result;
        }
        else
        {
            return null;
        }
    }
    
    /**
     * Statement Declaration visitor.
     *
     * @param context - Declaration statement context.
     *
     * @return - null.
     */
    @Override
    public Double visitDeclarationStatement(AnariexParser.DeclarationStatementContext context)
    {
        return this.visit(context.declaration());
    }
    
    /**
     * Declaration visitor. Add a variable to the memory.
     *
     * @param context - Declaration context.
     *
     * @return null.
     */
    @Override
    public Double visitDeclaration(AnariexParser.DeclarationContext context)
    {
        displayInfo(context.name.getText());
        displayInfo("def");
        
        memory.put(context.name.getText(), null);
        return null;
    }
    
    /**
     * Assignment Statement visitor.
     *
     * @param context - Assignment statement context.
     *
     * @return null.
     */
    @Override
    public Double visitAssignmentStatement(AnariexParser.AssignmentStatementContext context)
    {
        return this.visit(context.assignment());
    }
    
    /**
     * Assignment visitor. Assign a value to a variable in the memory.
     *
     * @param context Assignment context.
     *
     * @return - null.
     *
     * @throws RuntimeException - Custom runtime error for a non defined variables.
     */
    @Override
    public Double visitAssignment(AnariexParser.AssignmentContext context)
    {
        
        String variable = context.name.getText();
        
        if (memory.containsKey(variable))
        {
            displayInfo(variable);
            Double value = this.visit(context.value);
            displayInfo("=");
            
            memory.replace(context.name.getText(), value);
            
            return null;
        }
        else
        {
            String location = location(context);
            throw new RuntimeException("Runtime error!\n'" + variable + "' is not a defined variable!\nLocation " + location);
        }
    }
    
    /**
     * Evaluation statement visitor.
     *
     * @param context - Evaluation statement context.
     *
     * @return - Double - Result of the evaluation.
     */
    @Override
    public Double visitEvaluationStatement(AnariexParser.EvaluationStatementContext context)
    {
        return this.visit(context.evaluation());
    }
    
    /**
     * Evaluation visitor. Evaluate the child expression and format the result by removing trailing zeroes.
     *
     * @param context - Evaluation context.
     *
     * @return - Formatted result of the evaluation.
     */
    @Override
    public Double visitEvaluation(AnariexParser.EvaluationContext context)
    {
        Double result = this.visit(context.expression());
        
        if (result != null)
        {
            System.out.println(result.toString().endsWith(".0") ? String.valueOf(Math.round(result)) : result.toString());
        }
        
        displayInfo(context.op.getText());
        
        return result;
    }
    
    /**
     * Binary operation visitor. Apply a binary operation on the two operands.
     * Supported operations: ^, *, /, +, -
     *
     * @param context - Binary operation context.
     *
     * @return - Result of applying a binary operation.
     */
    @Override
    public Double visitBinaryOperation(AnariexParser.BinaryOperationContext context)
    {
        
        Double left = this.visit(context.left);
        Double right = this.visit(context.right);
        String operator = context.operator.getText();
        
        displayInfo(operator);
        
        switch (operator.charAt(0))
        {
            case '^':
                return Math.pow(left, right);
            case '*':
                return left * right;
            case '/':
                if (right == 0)
                {
                    String location = location(context);
                    throw new ArithmeticException("Arithmetic exception!\nDivision by zero!\n" + "Location:" + location);
                }
                else
                {
                    return left / right;
                }
            case '+':
                return left + right;
            case '-':
                return left - right;
            default:
                throw new IllegalArgumentException("Unknown operator " + operator);
        }
    }
    
    /**
     * Unary operation visitor. Apply unary operation on the operand.
     * Supported operations: cos, sin, tan, log, - (negation)
     *
     * @param context - Unary operation context.
     *
     * @return - Result of applying a unary operation.
     */
    @Override
    public Double visitUnaryOperation(AnariexParser.UnaryOperationContext context)
    {
        Double argument = this.visit(context.argument);
        String operator = context.operator.getText();
        
        displayInfo(operator);
        
        switch (operator)
        {
            case "cos":
                return Math.cos(argument);
            case "sin":
                return Math.sin(argument);
            case "tan":
                return Math.tan(argument);
            case "log":
                return Math.log10(argument);
            case "-":
                return argument * -1;
            default:
                throw new IllegalArgumentException("Unknown function " + operator);
        }
    }
    
    /**
     * Grouping operation visitor.
     *
     * @param context - Grouping operation context.
     *
     * @return - Result of the grouping expression.
     */
    @Override
    public Double visitGroupingOperation(AnariexParser.GroupingOperationContext context)
    {
        return this.visit(context.grouping());
    }
    
    /**
     * Grouping visitor.
     *
     * @param context - Grouping context.
     *
     * @return - Result of the grouping expression.
     */
    @Override
    public Double visitGrouping(AnariexParser.GroupingContext context)
    {
        return this.visit(context.expression());
    }
    
    /**
     * Number visitor. Converts the value of the number from String to Double.
     *
     * @param context - Number context.
     *
     * @return - Double - The converted number.
     */
    @Override
    public Double visitNumber(AnariexParser.NumberContext context)
    {
        displayInfo(context.getText());
        return Double.valueOf(context.getText());
    }
    
    /**
     * Variable visitor. Reads a variable from memory and returns its value.
     *
     * @param context - Variable context.
     *
     * @return - The value of the variable.
     *
     * @throws AnariexException - Custom error for a non initialized variables.
     */
    @Override
    public Double visitVariable(AnariexParser.VariableContext context)
    {
        String variable = context.getText();
        
        if (memory.containsKey(variable))
        {
            Double value = memory.get(variable);
            
            if (value != null)
            {
                displayInfo(variable);
                
                return value;
            }
            else
            {
                String location = location(context);
                throw new AnariexException("Initialization error!\n'" + variable + "' has not been initialized yet!\nLocation: " + location);
            }
        }
        else
        {
            String location = location(context);
            throw new AnariexException("Initialization error!\n'" + variable + "' has not been initialized yet!\nLocation: " + location);
        }
    }
    
    /**
     * Find the location of an element from the context.
     *
     * @param context - Context of the element to locate.
     *
     * @return - String - Line and position description.
     */
    private String location(ParserRuleContext context)
    {
        String line = String.valueOf(context.start.getLine());
        String position = String.valueOf(context.start.getCharPositionInLine());
        return "line " + line + ":" + position;
    }
    
    /**
     * Display a message using the specified logger.
     *
     * @param message - Message to display.
     */
    private void displayInfo(String message)
    {
        Anariex.logger.loggerWriteInfo(message + " ");
    }
    
    /**
     * Display a blank line using the specified logger.
     */
    private void displayLine()
    {
        Anariex.logger.loggerWriteInfo("\n");
    }
}
