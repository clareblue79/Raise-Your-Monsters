/**
 * CS 230 Final Project 
 * Fall 2016, Wellesley College
 * 
 * Project Name: "Raise Your Monsters" 
 * Group Members: Clare Frances Lee and Samantah Stewart
 * 
 * Modified Date: 12/20/2016
 * File Name: FinishPane.java
 * 
 * Primary Responisibilty: Clare Frances Lee
 * 
 * Description: A simple JPanel class that displays the winning message 
 */

import java.util.LinkedList;
import java.awt.Dimension;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class FinishPane extends JPanel {
  
  /**************instance variables******************/
  
   //the main JFrame the components are getting added to
  private JFrame frame;

  //a JLabel that displays the winning message
  private JLabel message;
  
  
   /**
   * The constructor
   * @params frame
   * @returns nothing
   * takes in the frame of which the components are getting added on
   * instantiates the instance variables 
   */
  public FinishPane(JFrame frame){
     
    //takes in the frame paremeter and instantiates the frame 
    this.frame = frame;
    
    //sets the background of the Panel as light gray
    this.setBackground(Color.lightGray);
    
    //sets text of the game over message
    this.message = new JLabel("You are done! Congrats, you successfully raised four monster babies.");
    
    //sets font of the message
    message.setFont(new Font("Trebuchet",Font.PLAIN, 26));
    
    //sets the X and Y alignment of the message to be center of the frame
    message.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
    message.setAlignmentY(java.awt.Component.CENTER_ALIGNMENT);
    
    //adds the JLabel of gameover message to the frame
    this.add(message);

  }//end constructor
  
}//end class