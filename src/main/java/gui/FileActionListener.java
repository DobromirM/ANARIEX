package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

/**
 * @author DMarinov
 * Created on: 06/Feb/2019
 */
public class FileActionListener implements ActionListener
{
    /**
     * Define actions for the menu bar buttons.
     * <p>
     * Open - Creates a dialog window for selecting a file. The selected file is then loaded into the input area.
     * Save - Creates a dialog window for specifying a file. The input area is then saved to that file.
     *
     * @param event - Action to be performed.
     */
    @Override
    public void actionPerformed(ActionEvent event)
    {
        String action = event.getActionCommand();
        
        JFileChooser fileChooser = new JFileChooser();
        int response = fileChooser.showOpenDialog(null);
        
        if (response == JFileChooser.APPROVE_OPTION)
        {
            File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
            
            if (action.equals("Open"))
            {
                try
                {
                    String content;
                    
                    FileReader fileReader = new FileReader(file);
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    StringBuilder stringBuilder = new StringBuilder(bufferedReader.readLine());
                    
                    while ((content = bufferedReader.readLine()) != null)
                    {
                        stringBuilder.append("\n").append(content);
                    }
                    
                    AnariexGUI.inputArea.setText(stringBuilder.toString());
                }
                catch (Exception evt)
                {
                    JOptionPane.showMessageDialog(AnariexGUI.frame, evt.getMessage());
                }
            }
            
            if (action.equals("Save"))
            {
                try
                {
                    FileWriter fileWriter = new FileWriter(file, false);
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    
                    bufferedWriter.write(AnariexGUI.inputArea.getText());
                    bufferedWriter.flush();
                    bufferedWriter.close();
                }
                catch (Exception evt)
                {
                    JOptionPane.showMessageDialog(AnariexGUI.frame, evt.getMessage());
                }
            }
        }
    }
}
