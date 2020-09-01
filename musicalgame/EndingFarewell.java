package musicalgame;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Class that displays an ending GUI for the package MusicalGame. Once the total game
 * points are achieved, file displays text congratulations to player. You can end 
 * package MusicalGame by JButton "End Game".
 * @author Katelynn Heasley
 */

public class EndingFarewell extends JFrame {
    
///////////////////fields/////////////////////
    //Fields to initialze window dimensions
    private final int WINDOW_WIDTH = 600;
    private final int WINDOW_HEIGHT = 300;
    
    //Field for text insets
    private Insets center = new Insets(5,5,5,5);
    
    //Fields for border layout main panels
    private JPanel westPanel = new JPanel();
    private JPanel centerPanel = new JPanel();
    private JPanel southPanel = new JPanel();
    private JPanel eastPanel = new JPanel();
    
    //Fields for Icon Images
    private ImageIcon curtainL = new ImageIcon("src/Images/CurtainL.png");
    private ImageIcon curtainR = new ImageIcon("src/Images/CurtainR.png");
    private ImageIcon curtainC = new ImageIcon("src/Images/CurtainC.png");
   
    //Field for display message
    private String ending1 = "CONGRATULATIONS!!! You've completed the game!";
    
    //Fields Java GUI Swing components
    private JButton endGame = new JButton("End Game");
    private JLabel Lcurtain = new JLabel(curtainL);
    private JLabel Ccurtain = new JLabel(curtainC);
    private JLabel Rcurtain = new JLabel(curtainR);
    private JTextArea endingNotes1 = new JTextArea(6,1);
    private JTextArea endingNotes2 = new JTextArea(6,1);

//////////////constructor/////////////////////
/**No-Argument constructor. Initializes an EndingFarewell GUI object.
* Ending Game message displays. File and Musical Game package ends when 
* user chooses JButton "End Game".
*/   
    public EndingFarewell(){
        
        //Call the super class constructor
        super("Know Your Orchestra");
        
        //Set the size of the window
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        
        //Set application to exit when window is closed
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Set size of window to be unchangable 
        setResizable(false);
        
        //Build west and east panels 
        westPanel.add(Lcurtain);
        eastPanel.add(Rcurtain);
        
        //Center panel build & set layout
        centerPanel.setLayout(new GridLayout(3,1));
            //Set restrictions & look for ending text
              endingNotes1.setText(ending1);
              endingNotes1.setWrapStyleWord(true);
              endingNotes1.setLineWrap(true);
              endingNotes1.setOpaque(false);
              endingNotes1.setEditable(false);
              endingNotes1.setFocusable(false);
              endingNotes1.setMargin(center);
            endGame.addActionListener(
             new ActionListener(){
             @Override
             public void actionPerformed(ActionEvent e) {
                 System.exit(0);    
            }
        }); 
        
        //Center panel add components
        centerPanel.add(Ccurtain);
        centerPanel.add(endingNotes1);
        centerPanel.add(endGame);
        
        //Set layout and add panels
        setLayout(new BorderLayout());
        add(southPanel, BorderLayout.SOUTH);
        add(westPanel, BorderLayout.WEST);
        add(eastPanel, BorderLayout.EAST);
        add(centerPanel, BorderLayout.CENTER);
        
        //Make window visible
        setVisible(true);
    }
    
/**Main method to initialize an EndingFarewell object
* @param args the command line arguments
*/
public static void main(String[] args) {
    EndingFarewell ef = new EndingFarewell();
  }
}

