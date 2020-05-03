package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author DMarinov
 * Created on: 06/Feb/2019
 */
public class ExecutionActionListener implements ActionListener
{
    /**
     * Define actions for GUI buttons.
     * <p>
     * Run - Execute the code from the input area.
     * Show Tree - Display the parse tree after the code has been run.
     *
     * @param event - Action to be performed.
     */
    @Override
    public void actionPerformed(ActionEvent event)
    {
        String action = event.getActionCommand();
        
        if (action.equals("Run"))
        {
            AnariexGUI.evaluationArea.setText("");
            AnariexGUI.outputArea.setText("");
            String code = AnariexGUI.inputArea.getText();
            Boolean success = AnariexGUI.anariex.parse(code);
            
            if (success)
            {
                AnariexGUI.anariex.evaluate();
            }
        }
        
        if (action.equals("Show Tree"))
        {
            AnariexGUI.anariex.showTree();
        }
    }
}
