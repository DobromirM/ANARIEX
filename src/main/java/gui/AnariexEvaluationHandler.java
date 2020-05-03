package gui;

import javax.swing.*;
import java.util.logging.LogRecord;
import java.util.logging.StreamHandler;

/**
 * @author DMarinov
 * Created on: 06/Feb/2019
 */
public class AnariexEvaluationHandler extends StreamHandler
{
    private JTextArea textArea;
    
    /**
     * Create custom stream handler that publishes to the GUI evaluation area.
     *
     * @param textArea - Text area for output.
     */
    public AnariexEvaluationHandler(JTextArea textArea)
    {
        this.textArea = textArea;
    }
    
    /**
     * Add output message to the evaluation area.
     *
     * @param record - Log record object holding the message.
     */
    @Override
    public void publish(LogRecord record)
    {
        super.publish(record);
        flush();
        
        if (textArea != null)
        {
            textArea.append(record.getMessage());
        }
    }
}
