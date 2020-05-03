package gui;

import anariex.Anariex;

import javax.swing.*;
import java.awt.*;
import java.io.PrintStream;

/**
 * @author DMarinov
 * Created on: 06/Feb/2019
 */
public class AnariexGUI extends JFrame
{
    static JFrame frame;
    static JTextArea inputArea;
    static JTextArea outputArea;
    public static JTextArea evaluationArea;
    static Anariex anariex;
    
    // Window Size
    private final Integer WINDOW_HEIGHT = 700;
    private final Integer WINDOW_WIDTH = 600;
    
    /**
     * Create Swing GUI for the application.
     */
    public AnariexGUI()
    {
        //Define JFrame
        frame = new JFrame("Anariex");
        frame.setLayout(new FlowLayout());
        frame.setResizable(false);
        
        //Define Input area.
        inputArea = new JTextArea(30, 53);
        inputArea.setLineWrap(true);
        JScrollPane inputScrollPane = new JScrollPane(inputArea);
        
        //Define output area.
        outputArea = new JTextArea(5, 36);
        outputArea.setLineWrap(true);
        outputArea.setEditable(false);
        PrintStream printStream = new PrintStream(new AnariexOutputStream(outputArea));
        System.setOut(printStream);
        System.setErr(printStream);
        JScrollPane outputScrollPane = new JScrollPane(outputArea);
        
        //Define evaluation area.
        evaluationArea = new JTextArea(5, 16);
        evaluationArea.setLineWrap(true);
        evaluationArea.setEditable(false);
        JScrollPane evaluationScrollPane = new JScrollPane(evaluationArea);
        
        //Define Menu bar.
        JMenuBar bar = new JMenuBar();
        JMenu menu = new JMenu("File");
        JMenuItem item1 = new JMenuItem("Open");
        JMenuItem item2 = new JMenuItem("Save");
        item1.addActionListener(new FileActionListener());
        item2.addActionListener(new FileActionListener());
        menu.add(item1);
        menu.add(item2);
        bar.add(menu);
        
        //Define GUI buttons.
        JButton buttonRun = new JButton("Run");
        buttonRun.addActionListener(new ExecutionActionListener());
        JButton buttonShowTree = new JButton("Show Tree");
        buttonShowTree.addActionListener(new ExecutionActionListener());
        
        //Add everything to the frame.
        anariex = new Anariex();
        frame.setJMenuBar(bar);
        frame.add(inputScrollPane);
        frame.add(outputScrollPane);
        frame.add(evaluationScrollPane);
        frame.add(buttonRun);
        frame.add(buttonShowTree);
        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}