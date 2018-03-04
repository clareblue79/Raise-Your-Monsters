/* CS 230 Final Project 
 * Fall 2016, Wellesley College
 * 
 * Project Name: "Raise Your Monsters" 
 * Group Members: Clare Frances Lee and Samantah Stewart
 * 
 * Modified Date: 12/20/2016
 * File Name: AboutPane.java
 * 
 * Description: The about panel, which has the return button to go back to the 
 * starting pane. The about panel displays the  user manual and short description
 * of the game.
 */

import java.awt.Dimension;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class AboutPane extends JPanel {
  
  /**************instance variables******************/
  
  //the return button
  private JButton button1;
  
  //the main JFrame the components to be added
  private JFrame frame;
  
  //a sub panel for the frame layout 
  private JPanel returnP;
  
  //a JLabel for the name of the panel
  private JLabel label1;

  
   /**
   * The constructor
   * @params frame
   * @returns nothing
   * takes in the frame of which the components are getting added on
   * instantiates the instance variables 
   */
  
  public AboutPane(JFrame frame){
    
    //takes in the frame paremeter and instantiates the frame
    this.frame = frame;
    
    //sets the background of the panel as light gray
    this.setBackground(Color.lightGray);
    
    //sets the font and text of the JLabel as the title of the panel
    label1 = new JLabel("About The Game");
    label1.setFont(new Font("Trebuchet",Font.PLAIN, 24));
      
    //adds the return button to navigate to the starting panel
    button1 = new JButton("Return");
    
    //adds the font and actionListener to the return button
    button1.setFont(new Font("Trebuchet",Font.PLAIN, 16));
    button1.addActionListener(new ButtonListener());
    
    //adding and setting the text of textArea
    JPanel center = new JPanel();
    JTextArea textArea = new JTextArea(5,10);
    JScrollPane scrollPane = new JScrollPane(textArea); 
    textArea.setEditable(false);
    textArea.append("This is a game that simulates Tamagotchi.\n" 
    + "\nYou will have four monsters that start as eggs. You will be able to name and care for them as they grow.\n" 
    + "\nIt is your job to make sure they don’t die by feeding, loving, cleaning, and watering them."
    + "These options change based on what level your monster is. They start as babies and grow into teens" 
    + "and become adults with unique traits. Have fun!\n"
    + "\nTHE GOAL OF THE GAME\n"
    + "Level up all four monsters to adult. Note that you will lose the game if you kill more than two monsters.");
    
    //for the scrollbar display
    scrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    
    //sets the size of the text area
    scrollPane.setPreferredSize(new Dimension(300, 300));
    
    //sets font and styel of the text area
    textArea.setFont(new Font("Trebuchet",Font.PLAIN, 16));
    textArea.setLineWrap(true);
    textArea.setWrapStyleWord(true);
    
    //GroupLayOut
    GroupLayout layout = new GroupLayout(this);
    this.setLayout(layout);
    layout.setAutoCreateGaps(true);
    layout.setAutoCreateContainerGaps(true);
    
   //using the GroupLayout Manager to align the components
   layout.setHorizontalGroup(
      layout.createSequentialGroup()
      .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
           .addComponent(label1)
           .addComponent(scrollPane)
           .addComponent(button1)
           )
    );
   
   layout.setVerticalGroup(
   layout.createSequentialGroup()
      .addComponent(label1)  
      .addComponent(scrollPane)
      .addComponent(button1)
      
);

  }//end constructor
  
  
   /*
   * @params Start class, the starting pane
   * @returns nothing
   * to set the returning panel as the Start Panel class
   */ 
  public void setReturn(Start s){
    this.returnP = s;
  }
  
  //Button listern method
   private class ButtonListener implements ActionListener{
    
    public void actionPerformed (ActionEvent event){
      
      //if clicked is the return button 
      if (event.getSource() == button1) {
        
        //remove current and add the Start panel
        frame.getContentPane().removeAll();
        frame.getContentPane().add(returnP);
        frame.pack();
        frame.setVisible(true);
        frame.repaint(); 
        //the repaint method allows the frame to be cleared out and the panel to be readded
      }
    }//end ActionPerformed()
   }//end ButtonListener
   
}//end class