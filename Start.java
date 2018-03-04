/* CS 230 Final Project 
 * Fall 2016, Wellesley College
 * 
 * Project Name: "Raise Your Monsters" 
 * Group Members: Clare Frances Lee and Samantah Stewart
 * 
 * Modified Date: 12/20/2016
 * File Name: Start.java
 * 
 * Description: The starting panel, that has the start game button and the about
 * button. Start game button will call the storage panel to select, initialize the 
 * monster and play the game. The about button will display about panel, where the 
 * user manual and short description of the game can be found. 
 */

import java.awt.Dimension;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Start extends JPanel {
  
  /**************instance variables******************/
  
  //the start button
  private JButton button1;
  
  //the about button
  private JButton button2;
  
  //the main frame the components are getting added on
  private JFrame frame;
  
  //the storage panel
  private JPanel storage;
  
  //the about panel
  private JPanel about;
  
  //the title and author labels
  private JLabel title;
  private JLabel authors;
  
  /**
   * The constructor
   * @params frame, storagePanel, and aboutPanel 
   * @returns nothing
   * takes in the frame of which the components are getting added on, the
   * storagePanel and aboutPanel the start pane will allow navigation to
   */
  public Start(JFrame frame, StoragePane s, AboutPane a){
   
    //takes in the parameter and instatiates the instance variables
    this.frame = frame;
    this.storage = s;
    this.about = a;
    
    //sets background as lightgray 
    this.setBackground(Color.lightGray);
 
    //creates the starting image of an adult monster 
    ImageIcon icon = createImageIcon("Media/adult.png", "main menu image");
    
    //creates a label with the icon and the titel
    title = new JLabel("Raise your Monsters", icon, JLabel.CENTER);
    title.setVerticalTextPosition(JLabel.TOP);
    title.setHorizontalTextPosition(JLabel.CENTER);
    
    //creates the autho labels
    authors = new JLabel("By Clare Frances Lee and Samantha Stewart");
    
    //setting fonts for the label components
    title.setFont(new Font("Trebuchet",Font.PLAIN, 26));
    authors.setFont(new Font("Trebuchet",Font.PLAIN, 20));

      
    //add start game and about button
    button1 = new JButton("Start Game");
    button2 = new JButton("About");
    
    // adding font and action listeners to the button components
    button1.setFont(new Font("Trebuchet",Font.PLAIN, 16));
    button1.addActionListener(new ButtonListener());
    button2.setFont(new Font("Trebuchet",Font.PLAIN, 16));
    button2.addActionListener(new ButtonListener());
    
    //GroupLayOut
    GroupLayout layout = new GroupLayout(this);
    this.setLayout(layout);
    layout.setAutoCreateGaps(true);
    layout.setAutoCreateContainerGaps(true);
        //using the GroupLayout Manager to align the components
   layout.setHorizontalGroup(
      layout.createSequentialGroup()
      .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
           .addComponent(title)
           .addComponent(authors)
           .addGroup(layout.createSequentialGroup()                        
                    .addComponent(button1)
                    .addComponent(button2))
           )
    );
   
   layout.setVerticalGroup(
   layout.createSequentialGroup()
      .addComponent(title)  
      .addComponent(authors)
      .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
           .addComponent(button1)
           .addComponent(button2))
      
);
    
  }//end constructor
  
  
   /** 
   * Creates and returns an ImageIcon out of an image file.
   * @param path The path to the imagefile relevant to the current file.
   * @param description A short description to the image.
   * @return ImageIcon An ImageIcon, or null if the path was invalid. 
   */
  private ImageIcon createImageIcon(String path, String description) {
    java.net.URL imgURL = Start.class.getResource(path);
    if (imgURL != null) {
      return new ImageIcon(imgURL, description);
    } else {
      System.err.println("Couldn't find file: " + path);
      return null;
    }
  }
  
   private class ButtonListener implements ActionListener{
    
    public void actionPerformed (ActionEvent event){
      //if start button is clicked
      if (event.getSource() == button1) {
        //go on to the storage pane
        frame.getContentPane().removeAll();
        frame.getContentPane().add(storage);
        frame.pack();
        frame.setVisible(true);
        frame.validate();
        frame.repaint();
        //the repaint method allows the frame to be cleared out and the panel to be readded
      }
      
      //if about button is clicked
       if (event.getSource() == button2) {
        //go on to the about panel
        frame.getContentPane().removeAll();
        frame.getContentPane().add(about);
        frame.pack();
        frame.setVisible(true);
        frame.validate();
        frame.repaint();
        //the repaint method allows the frame to be cleared out and the panel to be readded
      }
    }
   }//end ButtonListener
   
}//end class