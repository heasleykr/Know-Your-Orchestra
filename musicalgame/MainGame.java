package musicalgame;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.sound.sampled.*;
import java.net.URL;
import java.io.*;

/**
 * Class that displays and houses main GUI for the package MusicalGame. User can interact
 * with program by clicking on JButtons and JRadioButtons to gain information regarding musical 
 * instruments and increase their score by choosing correct answers.
 * @author Katelynn Heasley
 */

public class MainGame extends JFrame {
    
///////////////////fields/////////////////////
    //Fields to initialze window dimensions
    private final int WINDOW_WIDTH = 700;
    private final int WINDOW_HEIGHT = 600;
    
    //Fields for game colors & text inset
    private Color backgroundColor = new Color(41,113,95);
    private Color mintColor = new Color(146,190,179);
    private Color fontWhite = Color.WHITE;
    private Insets center = new Insets(5,11,5,5);
    
    //Fields for border layout main panels
    private JPanel eastPanel;
    private JPanel centerPanel;
    private JPanel westPanel;
    private JPanel northPanel;
    private JPanel southPanel;
    
    //Fields for JRadioButtons
    private JRadioButton button1;
    private JRadioButton button2;
    private JRadioButton button3;
    private JRadioButton button4;
    private JRadioButton button5;
    private JRadioButton button6;
    private JRadioButton button7;
    private JRadioButton button8;
    
    //Fields for Music default Icon Images
    private ImageIcon violinV;
    private ImageIcon trumpetT;
    private ImageIcon tubaT;
    private ImageIcon snareS;
    private ImageIcon clarinetC;
    private ImageIcon fluteF;
    private ImageIcon harpH;
    private ImageIcon baseDrum;
    
    //Fields for Music selected Icon Images
    private ImageIcon trumpetG;
    private ImageIcon violinG;
    private ImageIcon harpG;
    private ImageIcon snareDrumG;
    private ImageIcon tubaG;
    private ImageIcon clarinetG;
    private ImageIcon fluteG;
    private ImageIcon baseDrumG;
    
    //Field for game logo and curtain images
    private ImageIcon logo1;
    private ImageIcon leftCurtain;
    private ImageIcon rightCurtain;
    private ImageIcon centerCurtain;
    
    //fields for audio and text counts
    private int count = 0;
    private int value = 0;
    
    //Fields for game messages & player's name
    private String playerName1;
    private String displayName;
    private String welcome = "Welcome! Click on your first instrument to began.";
    private String[] en = new String[8];
        
    //Fields for Music JRadioButtons;
    private JButton instrument1;
    private JButton instrument2;
    private JButton instrument3;
    private JButton instrument4;
    private JButton instrument5;
    private JButton instrument6;
    private JButton instrument7;
    private JButton instrument8;
    
    //Fields for Swing GUI label and text components
    private JLabel playerName;
    private JLabel scoreBoard;
    private JLabel curtainL;
    private JLabel curtainL1;
    private JLabel curtainR;
    private JLabel curtainR1;
    private JLabel curtainC;
    private JLabel logo;
    private JTextArea messageDisplay;

    //Field for Audio components
    private Clip trumpet;
    private AudioInputStream audioInputStream;
    private URL trumpetWav;
    
    //Field for Score Count Object
    private ScoreCount sc = new ScoreCount();
       
//////////////constructor/////////////////////
/**One-Argument constructor. Initializes a MainGame object that
 * takes a name and uses this as player's name.
 * @param name the name to be used as player's name. 
*/     
    public MainGame(String name) {
        
        //Call the super class constructor
        super("Know Your Orchestra");
        
        //Set the size of the window
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        
        //Set application to exit when window is closed
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Set size of window to be unchangable 
        setResizable(true);
        
        //Create BorderLayout panels
        makeNorthPanel();
        makeSouthPanel();
        makeWestPanel();
        makeCenterPanel(name);
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
 * house curtain IconImages and will not interact
 * with user.  
 */ 
public void makeNorthPanel(){
    //Initialize panel & design
    northPanel = new JPanel();
    northPanel.setBackground(backgroundColor);
 
    //Initialize ImageIcons & set to labels
    leftCurtain = new ImageIcon("src/Images/LeftCurtain.png");
    centerCurtain = new ImageIcon("src/Images/CenterCurtain.png");
    rightCurtain = new ImageIcon("src/Images/RightCurtain.png");
    curtainL = new JLabel(leftCurtain);
    curtainL1 = new JLabel(centerCurtain);
    curtainC = new JLabel(centerCurtain);
    curtainR1 = new JLabel(centerCurtain);
    curtainR = new JLabel(rightCurtain);
       
    //Set layout & add components
    northPanel.setLayout(new GridLayout(1,5));
    northPanel.add(curtainL);
    northPanel.add(curtainL1);
    northPanel.add(curtainC);
    northPanel.add(curtainR1);
    northPanel.add(curtainR);    
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

/**Method to initialize East Panel. Panel will 
 * house JRadioButtons and will update score count,
 * display messages, and initialize Win GUI when user interacts
 * with components 
 */    
public void makeEastPanel(){
    //Initialize panel & design
    eastPanel = new JPanel();
    eastPanel.setBackground(mintColor);
        
    //Initialize JButton & add action listener
        button1 = new JRadioButton("Violin");
         button1.addActionListener(
           new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) { 
               //Update score and congratulate if score is under 7. Disable button
               if(instrument1.getIcon() == violinG && sc.getScore() < 7){
                  //Repaint JButton, update game message, disable button
                  button1.setContentAreaFilled(true);
                  messageDisplay.setText(encouragement());
                  button1.setEnabled(false);
                  
                  //update player Score
                  sc.addCount();
                  scoreBoard.setText(sc.getCount());
                  scoreBoard.repaint();
                  
                  //reset audio count
                  count = 0;
               
               //If player guesses correctly & score is 7, initialize ending win message
               }else if(instrument1.getIcon() == violinG && sc.getScore() == 7){
                  EndingFarewell ef = new EndingFarewell();
                  dispose();
                  
               //If player guesses incorrectly reset JButton, display message
               }else{
                  messageDisplay.setText("Whoops! Guess again!");
                  button1.setContentAreaFilled(false);
                  button1.repaint();
            }
        }      
    });
         
    //Initialize JButton & add action listener
        button2 = new JRadioButton("Harp");
         button2.addActionListener(
           new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
               //Update score and congratulate if score is under 7. Disable button
               if(instrument2.getIcon() == harpG && sc.getScore() < 7){
                  //Repaint JButton, update game message, disable button
                  button2.setContentAreaFilled(true);
                  messageDisplay.setText(encouragement());
                  button2.setEnabled(false);
                  
                  //update player Score
                  sc.addCount();
                  scoreBoard.setText(sc.getCount());
                  scoreBoard.repaint();
                  
                  //reset audio count
                  count = 0;
                  
               //If player guesses correctly & score is 7, initialize ending win message
               }else if(instrument2.getIcon() == harpG && sc.getScore() == 7){
                  EndingFarewell ef = new EndingFarewell();
                  dispose();
                  
               //If player guesses incorrectly reset JButton, display message
               }else{
                  messageDisplay.setText("Whoops! Guess again!");
                  button2.setContentAreaFilled(false);
                  button2.repaint();
            }
        }      
    });
         
    //Initialize JButton & add action listener
        button3 = new JRadioButton("Tuba");
         button3.addActionListener(
           new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                //Update score and congratulate if score is under 7. Disable button
                if(instrument3.getIcon() == tubaG && sc.getScore() < 7){
                  //Repaint JButton, update game message, disable button
                  button3.setContentAreaFilled(true);
                  messageDisplay.setText(encouragement());
                  button3.setEnabled(false);
                  
                  //update player Score
                  sc.addCount();
                  scoreBoard.setText(sc.getCount());
                  scoreBoard.repaint();
                  
                  //reset audio count
                  count = 0;
                  
               //If player guesses correctly & score is 7, initialize ending win message
               }else if(instrument3.getIcon() == tubaG && sc.getScore() == 7){
                  EndingFarewell ef = new EndingFarewell();
                  dispose();
                  
               //If player guesses incorrectly reset JButton, display message
               }else{
                  messageDisplay.setText("Whoops! Guess again!");
                  button3.setContentAreaFilled(false);
                  button3.repaint();
            }
        }      
    });
         
    //Initialize JButton & add action listener
        button4 = new JRadioButton("Snare Drum");
         button4.addActionListener(
           new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
               //Update score and congratulate if score is under 7. Disable button
               if(instrument4.getIcon() == snareDrumG && sc.getScore() < 7){
                  //Repaint JButton, update game message, disable button
                  button4.setContentAreaFilled(true);
                  messageDisplay.setText(encouragement());
                  button4.setEnabled(false);
                  
                  //update player Score
                  sc.addCount();
                  scoreBoard.setText(sc.getCount());
                  scoreBoard.repaint();
                  
                  //reset audio count
                  count = 0;
                  
               //If player guesses correctly & score is 7, initialize ending win message
               }else if(instrument4.getIcon() == snareDrumG && sc.getScore() == 7){
                  EndingFarewell ef = new EndingFarewell();
                  dispose();
                  
               //If player guesses incorrectly reset JButton, display message
               }else{
                  messageDisplay.setText("Whoops! Guess again!");
                  button4.setContentAreaFilled(false);
                  button4.repaint();
            }
        }      
    });
         
    //Initialize JButton & add action listener
        button5 = new JRadioButton("Trumpet");
          button5.addActionListener(
           new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                //Update score and congratulate if score is under 7. Disable button
                if(instrument5.getIcon() == trumpetG && sc.getScore() < 7){
                  //Repaint JButton, update game message, disable button
                  button5.setContentAreaFilled(true);
                  messageDisplay.setText(encouragement());
                  button5.setEnabled(false);
                  
                  //update player Score
                  sc.addCount();
                  scoreBoard.setText(sc.getCount());
                  scoreBoard.repaint();
                  
                  //reset audio count
                  count = 0;
                  
               //If player guesses correctly & score is 7, initialize ending win message
               }else if(instrument5.getIcon() == trumpetG && sc.getScore() == 7){
                  EndingFarewell ef = new EndingFarewell();
                  dispose();
                  
               //If player guesses incorrectly reset JButton, display message
               }else{
                  messageDisplay.setText("Whoops! Guess again!");
                  button5.setContentAreaFilled(false);
                  button5.repaint();
                  //button5.repaint();
            }
        }      
    });
          
    //Initialize JButton & add action listener
        button6 = new JRadioButton("Clarinet");
         button6.addActionListener(
           new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
               //Update score and congratulate if score is under 7. Disable button
                if(instrument6.getIcon() == clarinetG && sc.getScore() < 7){
                  //Repaint JButton, update game message, disable button
                  button6.setContentAreaFilled(true);
                  messageDisplay.setText(encouragement());
                  button6.setEnabled(false);
                  
                  //update player Score
                  sc.addCount();
                  scoreBoard.setText(sc.getCount());
                  scoreBoard.repaint();
                  
                  //reset audio count
                  count = 0;
               
               //If player guesses correctly & score is 7, initialize ending win message
               }else if(instrument6.getIcon() == clarinetG && sc.getScore() == 7){
                  EndingFarewell ef = new EndingFarewell();
                  dispose();
                  
               //If player guesses incorrectly reset JButton, display message
               }else{
                  messageDisplay.setText("Whoops! Guess again!");
                  button6.setContentAreaFilled(false);
                  button6.repaint();
            }
        }      
    });
         
    //Initialize JButton & add action listener
        button7 = new JRadioButton("Flute");
         button7.addActionListener(
           new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
               //Update score and congratulate if score is under 7. Disable button
                if(instrument7.getIcon() == fluteG && sc.getScore() < 7){
                  //Repaint JButton, update game message, disable button
                  button7.setContentAreaFilled(true);
                  messageDisplay.setText(encouragement());
                  button7.setEnabled(false);
                  
                  //update player Score
                  sc.addCount();
                  scoreBoard.setText(sc.getCount());
                  scoreBoard.repaint();
                  
                  //reset audio count
                  count = 0;
                  
               //If player guesses correctly & score is 7, initialize ending win message
               }else if(instrument7.getIcon() == fluteG && sc.getScore() == 7){
                  EndingFarewell ef = new EndingFarewell();
                  dispose();
                  
               //If player guesses incorrectly reset JButton, display message
               }else{
                  messageDisplay.setText("Whoops! Guess again!");
                  button7.setContentAreaFilled(false);
                  button7.repaint();
            }
        }      
    });
    
    //Initialize JButton & add action listener
        button8 = new JRadioButton("Base Drum");
         button8.addActionListener(
           new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
               //Update score and congratulate if score is under 7. Disable button
                if(instrument8.getIcon() == baseDrumG && sc.getScore() < 7){
                  //Repaint JButton, update game message, disable button
                  button8.setContentAreaFilled(true);
                  messageDisplay.setText(encouragement());
                  button8.setEnabled(false);
                  
                  //update player Score
                  sc.addCount();
                  scoreBoard.setText(sc.getCount());
                  scoreBoard.repaint();
                  
                  //reset audio count
                  count = 0;
                  
               //If player guesses correctly & score is 7, initialize ending win message
               }else if(instrument8.getIcon() == baseDrumG && sc.getScore() == 7){
                  EndingFarewell ef = new EndingFarewell();
                  dispose();
                  
               //If player guesses incorrectly reset JButton, display message
               }else{
                  messageDisplay.setText("Whoops! Guess again!");
                  button8.setContentAreaFilled(false);
                  button8.repaint();
            }
        }      
    });
         
    //Set layout & add components
    eastPanel.setLayout(new GridLayout(8,2));
    eastPanel.add(button1);
    eastPanel.add(button2);
    eastPanel.add(button3);
    eastPanel.add(button4);
    eastPanel.add(button5);
    eastPanel.add(button6);
    eastPanel.add(button7);
    eastPanel.add(button8);
}

/**Method to initialize West Panel. Panel will 
 * house JButtons and will play audio and update
 * default ImageIcons when user interacts with
 * components.
 */  
 public void makeWestPanel(){
    //Initialize panel & design
    westPanel = new JPanel();
    westPanel.setBackground(mintColor);
        
    //Initialize Jbuttons 
    instrument1 = new JButton();
    instrument2 = new JButton();
    instrument3 = new JButton();
    instrument4 = new JButton();
    instrument5 = new JButton();
    instrument6 = new JButton();
    instrument7 = new JButton();
    instrument8 = new JButton();
       
    //Initialize default instrument images
    violinV = new ImageIcon("src/Instrument Main Images/Violin.png");
    harpH = new ImageIcon("src/Instrument Main Images/harp.png");
    tubaT = new ImageIcon("src/Instrument Main Images/tuba.png");
    snareS = new ImageIcon("src/Instrument Main Images/SD.png");
    trumpetT = new ImageIcon("src/Instrument Main Images/trumpet.png");
    clarinetC = new ImageIcon("src/Instrument Main Images/Clarinet.png");
    fluteF = new ImageIcon("src/Instrument Main Images/Flute1.png");
    baseDrum = new ImageIcon("src/Instrument Main Images/baseDrum.png");
       
    //Initialize "selected" instrument images
    violinG = new ImageIcon("src/GreyedOut/ViolinG.png");
    harpG = new ImageIcon("src/GreyedOut/harpG.png");
    tubaG = new ImageIcon("src/GreyedOut/tubaG.png");
    snareDrumG = new ImageIcon("src/GreyedOut/SDG.png");
    trumpetG = new ImageIcon("src/GreyedOut/trumpetG.png");
    clarinetG = new ImageIcon("src/GreyedOut/ClarinetG.png");
    fluteG = new ImageIcon("src/GreyedOut/FluteG.png");
    baseDrumG = new ImageIcon("src/GreyedOut/baseDrumG.png");

    //Set default Iconimages on JButtons
    instrument1.setIcon(violinV);
    instrument2.setIcon(harpH);
    instrument3.setIcon(tubaT);
    instrument4.setIcon(snareS);
    instrument5.setIcon(trumpetT);
    instrument6.setIcon(clarinetC);
    instrument7.setIcon(fluteF);
    instrument8.setIcon(baseDrum);  
       
    //Add action listener to JButton
       instrument1.addActionListener(
        new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
              //If player has already guessed correctly, display message
              if(button1.isEnabled() == false){
                messageDisplay.setText("You've already named this instrument! Choose another!");  
              
              //If audio has already played & no was selection made, display message
              }else if(count == 1){
                messageDisplay.setText("Whoops! Choose the correct instrument name on the right to continue!");
            
              //If audio is reset and/or it's the player's first selection, play audio
              }else if(count == 0){
                  
               //set audio count so multiple clips cannot play at once
               count = 1;
               
               //play audio and repaint ImageIcon to "selected"
               playSound("/Audio Files/Violin.wav");
               instrument1.setIcon(violinG);
               instrument1.repaint();
         }}   
    });
       
    //Add action listener to JButton
       instrument2.addActionListener(
        new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
              //If player has already guessed correctly, display message
              if(button2.isEnabled() == false){
                messageDisplay.setText("You've already named this instrument! Choose another!");  
              
              //If audio has already played & no was selection made, display message
              }else if(count == 1){
                messageDisplay.setText("Whoops! Choose the correct instrument name on the right to continue!");
              
              //If audio is reset and/or it's the player's first selection, play audio
              }else if(count == 0){
               
               //set audio count so multiple clips cannot play at once
               count = 1;
               
               //play audio and repaint ImageIcon to "selected"
               playSound("/Audio Files/Harp.wav");
               instrument2.setIcon(harpG);
               instrument2.repaint();
         }}   
    });
       
    //Add action listener to JButton
       instrument3.addActionListener(
        new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
              //If player has already guessed correctly, display message
              if(button3.isEnabled() == false){
                messageDisplay.setText("You've already named this instrument! Choose another!");  
              
              //If audio has already played & no was selection made, display message
              }else if(count == 1){
                messageDisplay.setText("Whoops! Choose the correct instrument name on the right to continue!");
              
              //If audio is reset and/or it's the player's first selection, play audio
              }else if(count == 0){
               
               //set audio count so multiple clips cannot play at once   
               count = 1;
               
               //play audio and repaint ImageIcon to "selected"
               playSound("/Audio Files/tuba.wav");
               instrument3.setIcon(tubaG);
               instrument3.repaint();
         }}   
    });
       
    //Add action listener to JButton
       instrument4.addActionListener(
        new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
              //If player has already guessed correctly, display message
              if(button4.isEnabled() == false){
                messageDisplay.setText("You've already named this instrument! Choose another!");  
              
              //If audio has already played & no was selection made, display message
              }else if(count == 1){
                messageDisplay.setText("Whoops! Choose the correct instrument name on the right to continue!");
              
              //If audio is reset and/or it's the player's first selection, play audio
              }else if(count == 0){
               
               //set audio count so multiple clips cannot play at once   
               count = 1;
               
               //play audio and repaint ImageIcon to "selected"
               playSound("/Audio Files/snareDrum.wav");
               instrument4.setIcon(snareDrumG);
               instrument4.repaint();
         }}   
    });
       
    //Add action listener to JButton
       instrument5.addActionListener(
        new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
              //If player has already guessed correctly, display message
              if(button5.isEnabled() == false){
                messageDisplay.setText("You've already named this instrument! Choose another!");  
              
              //If audio has already played & no was selection made, display message
              }else if(count == 1){
                messageDisplay.setText("Whoops! Choose the correct instrument name on the right to continue!");
              
              //If audio is reset and/or it's the player's first selection, play audio
              }else if(count == 0){
               
               //set audio count so multiple clips cannot play at once   
               count = 1;
               
               //play audio and repaint ImageIcon to "selected"
               playSound("/Audio Files/Trumpet.wav");
               instrument5.setIcon(trumpetG);
               instrument5.repaint();
         }}   
    });
       
    //Add action listener to JButton
       instrument6.addActionListener(
        new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
              //If player has already guessed correctly, display message
              if(button6.isEnabled() == false){
                messageDisplay.setText("You've already named this instrument! Choose another!");  
              
              //If audio has already played & no was selection made, display message
              }else if(count == 1){
                messageDisplay.setText("Whoops! Choose the correct instrument name on the right to continue!");
              
              //If audio is reset and/or it's the player's first selection, play audio
              }else if(count == 0){
               
               //set audio count so multiple clips cannot play at once   
               count = 1;
               
               //play audio and repaint ImageIcon to "selected"
               playSound("/Audio Files/Clarinet.wav");
               instrument6.setIcon(clarinetG);
               instrument6.repaint();
         }}   
    });
       
    //Add action listener to JButton
       instrument7.addActionListener(
        new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
              //If player has already guessed correctly, display message
              if(button7.isEnabled() == false){
                messageDisplay.setText("You've already named this instrument! Choose another!");  
              
              //If audio has already played & no was selection made, display message
              }else if(count == 1){
                messageDisplay.setText("Whoops! Choose the correct instrument name on the right to continue!");
              
              //If audio is reset and/or it's the player's first selection, play audio
              }else if(count == 0){
               
               //set audio count so multiple clips cannot play at once   
               count = 1;
               
               //play audio and repaint ImageIcon to "selected"
               playSound("/Audio Files/Flute.wav");
               instrument7.setIcon(fluteG);
               instrument7.repaint();
         }}   
    });
       
    //Add action listener to JButton
       instrument8.addActionListener(
        new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
              //If player has already guessed correctly, display message
              if(button8.isEnabled() == false){
                messageDisplay.setText("You've already named this instrument! Choose another!");  
              
              //If audio has already played & no was selection made, display message
              }else if(count == 1){
                messageDisplay.setText("Whoops! Choose the correct instrument name on the right to continue!");
              
              //If audio is reset and/or it's the player's first selection, play audio
              }else if(count == 0){
               
               //set audio count so multiple clips cannot play at once   
               count = 1;
               
               //play audio and repaint ImageIcon to "selected"
               playSound("/Audio Files/baseDrum.wav");
               instrument8.setIcon(baseDrumG);
               instrument8.repaint();
         }}   
    });
       
    //Set layout & add components
    westPanel.setLayout(new GridLayout(4,2));
    westPanel.add(instrument1);
    westPanel.add(instrument2);
    westPanel.add(instrument3);
    westPanel.add(instrument4);
    westPanel.add(instrument5);
    westPanel.add(instrument6);
    westPanel.add(instrument7);
    westPanel.add(instrument8);    
}

/**Method to initialize Center Panel. Panel will 
 * display player's name, player's score, & game
 * messages for user. Panel will not interact with user.  
 * @param name1 the name to be used for player's name
 */
public void makeCenterPanel(String name1){
    //Initialize panel
    centerPanel = new JPanel();
        
    //Initialize player's name, set to JLabel & set design
    playerName1 = name1;      
    displayName = "Conductor: " + playerName1;
    playerName = new JLabel(displayName, 0);
    playerName.setForeground(fontWhite);
    playerName.setSize(200,60);
     
    //Initialize & design score
    scoreBoard = new JLabel(sc.getCount(), 0);
    scoreBoard.setForeground(fontWhite);
        
    //Set restrictions & look for game text
    messageDisplay = new JTextArea(6,5);
    messageDisplay.setText(welcome);
    messageDisplay.setWrapStyleWord(true);
    messageDisplay.setLineWrap(true);
    messageDisplay.setOpaque(false);
    messageDisplay.setEditable(false);
    messageDisplay.setFocusable(false);
    messageDisplay.setMargin(center);
    messageDisplay.setForeground(fontWhite);
        
    //Initialize logo & set to JLabel
    logo1 = new ImageIcon("src/Images/LogoSmall1.png");
    logo = new JLabel(logo1);
        
    //Set layout & add components
    centerPanel.setLayout(new GridLayout(4,1));
    centerPanel.add(logo);
    centerPanel.add(playerName);
    centerPanel.add(scoreBoard);
    centerPanel.add(messageDisplay);
    centerPanel.setBackground(backgroundColor);  
}
   
/**Method build an array of game messages
 * to be displayed for user.
 * @return message to be displayed to user
 */ 
private String encouragement(){
    
    //Set String array messages 
    en[0] = "Great Job! Keeping Going!";
    en[1] = "You're going to make a great conductor!";
    en[2] = "Bravo!";
    en[3] = "Encore!";
    en[4] = "Magnificent performance!";
    en[5] = "Almost there! Keep going!";
    en[6] = "This last one is obvious, right?";
    en[7] = "CONGRATULATIONS!!! You've named all the instruments!";
       
    //Set current array message to screen display
    welcome = en[value]; 
    
    //Increment to next component in array
    value++;
    
    //Return message of current array component
    return welcome;
}
    
/**Method to set up an audio input stream file and
 * load it into a clip
 * @param url the .wav file to be played
 */  
private void soundEffect(URL url) {  
    try {
        
    //Get audio file, convert to clip & open
    audioInputStream = AudioSystem.getAudioInputStream(url);
    trumpet = AudioSystem.getClip();
    trumpet.open(audioInputStream);
        
    //Exception handling for audio loading errors   
    } catch (UnsupportedAudioFileException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    } catch (LineUnavailableException e) {
        e.printStackTrace();
    }
}

/**Method to play an audio clip
 * @param url1 the audio clip to be played
 */ 
public void playSound(String url1) {
    
    //Get open audio clip
    trumpetWav = getClass().getResource(url1); 
    
    //Internal Method to load the sound
    soundEffect(trumpetWav);
    
    //Play audio clip
    trumpet.start();
}


/**Main method to initialize a MainGame object
* @param args the command line arguments
*/
public static void main(String[] args) {
    MainGame mg = new MainGame("test");
  }
}
