/**
 * CS 230 Final Project 
 * Fall 2016, Wellesley College
 * 
 * Project Name: "Raise Your Monsters" 
 * Group Members: Clare Frances Lee and Samantah Stewart
 * 
 * Modified Date: 12/20/2016
 * File Name: NamePane.java
 * 
 * Primary Responsibility: Clare Frances Lee
 * 
 * Description: The name panel, where the user can name and initialize 
 * the monster baby when first visited. The user must name the baby with 
 * a unique name.
 */

import java.util.LinkedList;
import java.awt.Dimension;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class NamePane extends JPanel {
  
  /**************instance variables******************/
  
  //the main JFrame for the components to be added
  private JFrame frame;
  
  //the submit button
  private JButton inputButton;
  
  //a textfield for the user input
  private JTextField editTextArea;
  
  //a short instruction message label
  private JLabel instruction;
  
  //saving the user input as mystring
  private String myString;
  
  //the index of the monster in the storage
  private int i; 
  
  //the storage class 
  private Storage storage;
  
  
  
  /**
   * The constructor
   * @params frame, storage, and index of the monster within the storage
   * @returns nothing
   * takes in the frame of which the components are getting added on, the
   * storage and which monster within the storage was clicked, so the information
   * can be updated within the storage.instantiates the instance variables 
   */
  public NamePane(JFrame frame, Storage s, int index){
    
    //takes in the parameter and instatiates the frame
    this.frame = frame;
    
    //sets background color as light gray
    this.setBackground(Color.lightGray);
    
    //takes in the parameter and instatiates the storage
    this.storage = s;
    
    //creates the Submit button
    this.inputButton = new JButton("Submit");
    
    //sets instruction text within the text field
    this.editTextArea = new JTextField("Name your Baby!");
    
    //takes in the parameter and instatiates the index of the monster
    //within the storage
    this.i = index;
    
    //sets text for instruction label
    instruction = new JLabel("Your baby's name must be unique");
    
    //sets fonts for all the components
    instruction.setFont(new Font("Trebuchet",Font.PLAIN, 20));
    editTextArea.setFont(new Font("Trebuchet",Font.PLAIN, 16));
    inputButton.setFont(new Font("Trebuchet",Font.PLAIN, 16));
    
    //adding action listern for the submit button
    inputButton.addActionListener(new ButtonListener());
    
    //GroupLayOut
    GroupLayout layout = new GroupLayout(this);
    this.setLayout(layout);
    layout.setAutoCreateGaps(true);
    layout.setAutoCreateContainerGaps(true);
    //using the GroupLayout Manager to align the components
    layout.setHorizontalGroup(
                              layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                            .addComponent(instruction)
                                            .addGroup(layout.createSequentialGroup()                        
                                                        .addComponent(editTextArea)
                                                        .addComponent(inputButton))
                                         )
                             );
    
    layout.setVerticalGroup(
                            layout.createSequentialGroup()
                              .addComponent(instruction)  
                              .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                          .addComponent(editTextArea)
                                          .addComponent(inputButton))
                              
                           );
    
  }//end constructor
  
  private class ButtonListener implements ActionListener{
    
    public void actionPerformed (ActionEvent event){
      
      //if clicked is the submit button
      if (event.getSource() == inputButton) {
        
        //takes in the user input and saves as myString
        myString = editTextArea.getText();
        
        //checks if the name is unique
        if (storage.canName(myString)){
          
          //if so, select the naming monster from the storage
          LinkedList<Monster> monsters = storage.getMonsters();
          Baby m1 = (Baby) monsters.get(i);
          
          //set name as user input
          m1.setName(myString);
          
          //go on to next panel, which is the monster pane of the selected monster
          frame.getContentPane().removeAll();
          MonsterPane monster = new MonsterPane(frame, storage, i);
          frame.setPreferredSize(new Dimension(800, 800));
          frame.getContentPane().add(monster);
          frame.pack();
          frame.setVisible(true);
          frame.repaint();
          
        } else {
          //the baby name is not unique. displays message. 
          instruction.setText("You have already named your baby " + myString + ".");
          editTextArea.setText("Pick another name");
        }
      }
      
    }//end actionPerformed()
  }//end ButtonListener
  
}//end class