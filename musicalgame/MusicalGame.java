package musicalgame;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Class that displays a welcome GUI for the package MusicalGame. User is oriented
 * to the game and corresponding rules. User can input their name and click
 * JButton "Let's Play" to initialize Main Game and hide welcome GUI.
 * @author Katelynn Heasley
 */

public class MusicalGame extends JFrame {
    
///////////////////fields/////////////////////
    //Fields to initialze window dimensions
    private final int WINDOW_WIDTH = 600;
    private final int WINDOW_HEIGHT = 300;
    
    //Fields for game colors & text inset
    private Color backgroundColor = new Color(41,113,95);
    private Color mintColor = new Color(146,190,179);
    private Color fontWhite = Color.WHITE;
    private Insets center = new Insets(5,5,5,5);
    
    //Fields for border layout main panels
    private JPanel westPanel;
    private JPanel centerPanel;
    private JPanel southPanel;
    private JPanel northPanel;
    private JPanel eastPanel;

    //Fields for Icon Images
    private ImageIcon logo;
    private ImageIcon eastPane;
   
    //Fields for game messages
    private String rules1 = "How to Play: Match the musical instrument "
            + "on the left with it's name on the right!";
    private String rules2 = "Type in your name, choose your level of difficulty, "
            + "and lets play!";
    private String nameFinal;
    
    //Fields for Error & Main Game GUIs
    private ErrorNameWindow enw;
    private MainGame mainGo;
    
    //Fields Java GUI Swing components
    private JButton letsPlay = new JButton("Let's Play!");
    private JLabel welcomeHeader = new JLabel("Welcome to Know Your Orchestra!", 0);
    private JLabel name = new JLabel("Name");
    private JLabel logo1;
    private JLabel eastPane1;
    private JTextField enterName = new JTextField("Enter name here", 3);
    private JTextArea welcomeRules1 = new JTextArea(6,0);
    private JTextArea welcomeRules2 = new JTextArea(6,0);
 
//////////////constructor/////////////////////
/**No-Argument constructor. Initializes a welcome GUI object.
* Displays game rules. Name is initialized to a default and 
* must be updated by user in order to run main game file. 
*/ 
    public MusicalGame(){
        
        //Call the super class constructor
        super("Know Your Orchestra");
        
        //Set the size of the window
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        
        //Set application to exit when window is closed
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Set size of window to be unchangable 
        setResizable(false);
        
         //Create BorderLayout panels
        makeNorthPanel();
        makeSouthPanel();
        makeWestPanel();
        makeCenterPanel();
        makeEastPanel();
      
        //Set layout and add panels
        setLayout(new BorderLayout());
        add(northPanel, BorderLayout.NORTH);
        add(southPanel, BorderLayout.SOUTH);
        add(westPanel, BorderLayout.WEST);
        add(eastPanel, BorderLayout.EAST);
        add(centerPanel, BorderLayout.CENTER);
        
        //Make window visible
        setVisible(true);
    }
    
////////////////methods///////////////////////
    
/**Method to initialize North Panel. Panel will 
 * house space, color & dimension only and 
 * will not interact with user.  
 */   
public void makeNorthPanel(){
    //Initialize panel & design
    northPanel = new JPanel(); 
    northPanel.setBackground(backgroundColor);     
}

/**Method to initialize South Panel. Panel will 
 * house space, color & dimension only and 
 * will not interact with user.  
 */
public void makeSouthPanel(){
    //Initialize panel & design
    southPanel = new JPanel();
    southPanel.setBackground(backgroundColor);    
}
    
/**Method to initialize West Panel. Panel will 
 * house an ImageIcon & dimension only and 
 * will not interact with user.  
 */
public void makeWestPanel(){
    //Initialize panel
    westPanel = new JPanel();
    
    //Initialize logo, set design, & set to JLabel
    logo = new ImageIcon("src/Images/LogoSmall.png");
    logo1 = new JLabel();
    logo1.setIcon(logo);
    logo1.setForeground(mintColor);
    
    //Add components & set design
    westPanel.add(logo1);
    westPanel.setBackground(backgroundColor);
}

/**Method to initialize East Panel. Panel will 
 * house game Logo, color & dimension only and 
 * will not interact with user.  
 */
public void makeEastPanel(){
    //Initialize panel
    eastPanel = new JPanel();
    
    //Initialize ImageIcon & add to Jlabel
    eastPane = new ImageIcon("src/Images/EastPane.png");
    eastPane1 = new JLabel();
    eastPane1.setIcon(eastPane);
    
    //Add components & set design
    eastPanel.add(eastPane1);
    eastPanel.setBackground(backgroundColor);
}
    
public void makeCenterPanel(){
    //Initialize panel & set layout
    centerPanel = new JPanel();
    centerPanel.setLayout(new GridLayout(5,1));

    //Set restrictions & look for game instruction text 1
    welcomeRules1.setText(rules1);
    welcomeRules1.setWrapStyleWord(true);
    welcomeRules1.setLineWrap(true);
    welcomeRules1.setOpaque(false);
    welcomeRules1.setEditable(false);     
    welcomeRules1.setFocusable(false);
    welcomeRules1.setMargin(center);
     
    //Set restrictions & look for game instruction text 2
    welcomeRules2.setText(rules2);
    welcomeRules2.setWrapStyleWord(true);
    welcomeRules2.setLineWrap(true);
    welcomeRules2.setOpaque(false);
    welcomeRules2.setEditable(false);
    welcomeRules2.setFocusable(false);
    welcomeRules2.setMargin(center);
     
    //Add Event Handling
     letsPlay.addActionListener(
        new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                
               //If name field is default, initialze error window
               if("Enter name here".equals(enterName.getText()))
               {
                 enw = new ErrorNameWindow(); 
               }
               
               //If name field is null, initialze error window
               else if("".equals(enterName.getText()))
               {
                 enw = new ErrorNameWindow();  
               }
               
               //If name field text has been updated, initialze main game
               else
               {
                 //Initialize game and hide welcome GUI
                 nameFinal = enterName.getText();
                 mainGo = new MainGame(nameFinal);
                 setVisible(false);                          
               }           
              }
            });
     
    //Add components & set design
    centerPanel.add(welcomeHeader);
    centerPanel.add(welcomeRules1);
    centerPanel.add(welcomeRules2);
    centerPanel.add(enterName);
    centerPanel.add(letsPlay);
    centerPanel.setBackground(mintColor);
    centerPanel.setForeground(mintColor);
}
 

/**Method to obtain the player's name.
 * @return the player's name.
 */    
public String getName(){
      return enterName.getText();
    }
     
/**Main method to initialize a MusicalGame object
* @param args the command line arguments
*/
public static void main(String[] args) {
    MusicalGame mg = new MusicalGame();
  }  
}
