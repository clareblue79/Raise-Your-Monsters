/**
 * CS 230 Final Project 
 * Fall 2016, Wellesley College
 * 
 * Project Name: "Raise Your Monsters" 
 * Group Members: Clare Frances Lee and Samantah Stewart
 * 
 * Modified Date: 12/20/2016
 * File Name: Home.java
 * 
 * Primary Responsibility: Clare Frances Lee
 * 
 * Description: The main GUI driver class that sets up the multi-pane GUI 
 * structure of the Game. Home constructs the necessary panel classes 
 * - StartPane, AboutPane, and StoragePane - for launching the game GUI. Home also 
 * instantiates the Storage class needed for the gameplay. By running, it launches the
 * starting panel, which links to aboutPane and storagePane. 
 * 
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Home extends JFrame{
  
  public static void main (String[] args){
    
    //creates the main JFrame to add different components and panels
    JFrame frame = new JFrame("Raise Your Monsters");
    
    //for exiting when clicking the X button
    frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
    
    //creates new Storage class
    Storage storage = new Storage();

    //this is the storage panel, launches when the user first clicks start
    StoragePane storagePane = new StoragePane(frame, storage);
       
    //this is the about panel, launches when the user clicks about
    //the about panel displays information about the game and the user manual
    AboutPane about = new AboutPane(frame);
    
    //this is the starting panel, launches when home's main method is called
    //buttons are displayed to link to storagePane and aboutPane
    Start starting = new Start(frame, storagePane, about);
    
    //this is a helper method to set the returning page of about as startPane 
    about.setReturn(starting);
                           
    /**********setting initinal layout and look of the frame****************/
     
    //sets the frame layout as GridBagLayout, which centers the JPanel, relative
    //to the frame size
    frame.setLayout(new GridBagLayout()); 
    
    //sets the color of the background as light gray
    frame.setBackground(Color.lightGray);
    
    //adds the startPane to the frame
    frame.getContentPane().add(starting);
    
    //sets dimensions of the frame
    frame.setPreferredSize(new Dimension(650, 650));
    
    /******adding the components together.....********/
    frame.pack();
    frame.setVisible(true); 
    
    //the repaint method allows the frame to be cleared out and the panel to be readded
    frame.repaint();   
    
  }//end main
}//end class