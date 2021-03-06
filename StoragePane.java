/**
 * CS 230 Final Project 
 * Fall 2016, Wellesley College
 * 
 * Project Name: "Raise Your Monsters" 
 * Group Members: Clare Frances Lee and Samantah Stewart
 * 
 * Modified Date: 12/20/2016
 * File Name: StoragePane.java
 * 
 *  
 * Primary Responsibility: Clare Frances Lee
 * 
 * Description: The storage panel, where the user can select which 
 * monster they prefer to visit. When the storage is visited for the first
 * time, the user will initialize the monster when clicking visit, and
 * be allowed to name the baby by navigating to the name pane. After naming
 * the baby, the user will be able to click visit and go to monsterPane, where
 * the user will be allowed to interact with the monsters (feeding, loving, cleaning, etc). 
 */

import java.util.LinkedList;
import java.awt.Dimension;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class StoragePane extends JPanel {
  
  /**************instance variables******************/
  
  //visit buttons
  private JButton selectM1; //select monster 1
  private JButton selectM2; //select monster 2
  private JButton selectM3; //select monster 3
  private JButton selectM4; //select monster 4
  
  //the main frame the components to be added to 
  private JFrame frame;
  
  //static variable for it to be accessed and updated
  public static Storage s;
  
  //list of the monsters obtained from the storage
  private LinkedList<Monster> m;
  
  
  
  /**
   * The constructor
   * @params frame, storage
   * @returns nothing
   * takes in the frame of which the components are getting added on, the
   * storage which monster information is stored. This will be used to populate
   * the storage pane and instantiate the variables. 
   * 
   */
  public StoragePane(JFrame frame, Storage s1){
    
    //instatiates the instance variables
    this.frame = frame;
    
    //sets background color as lightGray
    this.setBackground(Color.lightGray);
    this.s = s1;
    this.m = s.getMonsters();
    
    
    //adding the visit buttons
    selectM1 = new JButton("Visit");
    selectM2 = new JButton("Visit");
    selectM3 = new JButton("Visit");
    selectM4 = new JButton("Visit");
    
    //adding actionListers
    selectM1.addActionListener(new ButtonListener());
    selectM2.addActionListener(new ButtonListener());
    selectM3.addActionListener(new ButtonListener());
    selectM4.addActionListener(new ButtonListener());
    
    //setting fonts
    selectM1.setFont(new Font("Trebuchet",Font.PLAIN, 20));
    selectM2.setFont(new Font("Trebuchet",Font.PLAIN, 20));
    selectM3.setFont(new Font("Trebuchet",Font.PLAIN, 20));
    selectM4.setFont(new Font("Trebuchet",Font.PLAIN, 20));
    
    //call createGrid method to populate storage panel with monsters
    this.add(createGrid());
    
  }//end Constructor
  
  /**
   * @params imagePath, monsterName, button
   * @returns JPanel
   * takes in the imagePath, monsterName, and the button to create one block of 
   * monster in the storage. Returns the components in JPanel
   */
  private JPanel createBlock(String imagePath, String monsterName, JButton button){
    
    //creates new JPanel for the components to be added
    JPanel block1 = new JPanel();
    
    //create image icon of the monster and add the name of the monster
    ImageIcon icon = createImageIcon(imagePath, "monster image");
    JLabel name = new JLabel(monsterName, icon, JLabel.CENTER);
    name.setVerticalTextPosition(JLabel.BOTTOM);
    name.setHorizontalTextPosition(JLabel.CENTER);
    name.setFont(new Font("Trebuchet",Font.PLAIN, 16));
    
    //GroupLayOut
    GroupLayout layout = new GroupLayout(block1);
    block1.setLayout(layout);
    layout.setAutoCreateGaps(true);
    layout.setAutoCreateContainerGaps(true);
    
    //using the GroupLayout Manager to align the components
    layout.setHorizontalGroup(
                              layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                            .addComponent(name)
                                            .addComponent(button)
                                         )
                             );
    
    layout.setVerticalGroup(
                            layout.createSequentialGroup()
                              .addComponent(name)  
                              .addComponent(button)     
                           );
    
    //return the JPanel
    return block1;
    
  }//end createBlock()
  
  /*
   * @params none
   * @returns JPanel
   * populates the Panel with monsters with appropriate status and name by 
   * looping through the storage and calling the createBlock method. Then 
   * returns the JPanel
   */
  
  private JPanel createGrid(){
    //creates the JPanel for the blocks to be added on 
    JPanel grid = new JPanel();
    
    //sets the background color and the layout of the panel 
    //as Grid
    grid.setBackground(Color.lightGray);
    grid.setLayout(new GridLayout(2,2, 20, 20));
    
    //a list of all the visit JButtons, so each can be added for 
    //each four monster
    JButton[] buttons = {selectM1, selectM2, selectM3, selectM4};
    
    //looping through the storage to create each monster block
    for (int i = 0; i < 4; i++){
      
      //get monster from the storage
      Monster m1 = m.get(i);
      
      //call createBlock
      JPanel block1 = createBlock(m1.getStorageImagePath(), m1.getName(), buttons[i]);
      
      //add to grid
      grid.add(block1);
    }
    
    //return the populated JPanel
    return grid;
    
  }//end createGrid
  
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
      //for each visit button for each monster.....
      if (event.getSource() == selectM1) {
        
        //remove all the current components
        frame.getContentPane().removeAll();
        
        //get monster info
        Monster m1 = m.get(0);
        
        if (m1.getName() == null){
          //first time visit
          //if the baby has not been named
          //navigate to the name Panel
          NamePane namePane = new NamePane(frame, s, 0);
          frame.getContentPane().add(namePane);
          frame.pack();
          frame.setVisible(true);
          frame.repaint();
          
        } else {
          //if the baby has been named
          
          //subtracts vitality status based on how much time has elapsed
          //since the player's last visit
          m1.timeElapsed();
          
          //checks if the baby is still alive
          if(m1.isAlive()){
            //if alive create the monsterPane with the selected monster's information 
            MonsterPane monsterPane = new MonsterPane(frame, s, 0);
            frame.setPreferredSize(new Dimension(900, 900));
            //setTimeElapsed
            frame.getContentPane().add(monsterPane);
            frame.pack();
            frame.setVisible(true);
            frame.repaint();
            
          } else {
            //if the baby is dead
            
            //update the storage by declaring the dead monster
            s.died(m1);
            
            //checks if the game is over
            if(s.isOver()){
              
              //if over, launch the game over Pane
              GameOverPane gameOverPane = new GameOverPane(frame);
              frame.setPreferredSize(new Dimension(1200, 1200));
              frame.getContentPane().add(gameOverPane);
              frame.pack();
              frame.setVisible(true);
              frame.repaint(); 
              
            } else {
              
              //if the game is not over, alert the user with a popup
              String alert = m1.getName() + " died. :(";
              JOptionPane.showMessageDialog(frame, alert);
              
              //launch MonsterPane, populated with the selected monster's info
              MonsterPane monsterPane = new MonsterPane(frame, s, 0);
              //setTimeElapsed
              frame.setPreferredSize(new Dimension(900, 900));
              frame.getContentPane().add(monsterPane);
              frame.pack();
              frame.setVisible(true);
              frame.repaint();
            }
          }
        }
      }
      
      //same mechanism as above for all the rest of the visit buttons
      if (event.getSource() == selectM2) {
        frame.getContentPane().removeAll();
        Monster m2 = m.get(1);
        
        if (m2.getName() == null){
          NamePane namePane = new NamePane(frame, s, 1);
          frame.getContentPane().add(namePane);
          frame.pack();
          frame.setVisible(true);
          frame.repaint();
        } else {
          m2.timeElapsed();
          if(m2.isAlive()){
            MonsterPane monsterPane = new MonsterPane(frame, s, 1);
            frame.setPreferredSize(new Dimension(900, 900));
            frame.getContentPane().add(monsterPane);
            frame.pack();
            frame.setVisible(true);
            frame.repaint();
          } else {
            s.died(m2);
            if(s.isOver()){
              GameOverPane gameOverPane = new GameOverPane(frame);
              frame.setPreferredSize(new Dimension(1200, 1200));
              frame.getContentPane().add(gameOverPane);
              frame.pack();
              frame.setVisible(true);
              frame.repaint(); 
            } else {
              String alert = m2.getName() + " died. :(";
              JOptionPane.showMessageDialog(frame, alert);
              MonsterPane monsterPane = new MonsterPane(frame, s, 1);
              frame.setPreferredSize(new Dimension(900, 900));
              frame.getContentPane().add(monsterPane);
              frame.pack();
              frame.setVisible(true);
              frame.repaint();
            }
          }
        }
      }
      
      if (event.getSource() == selectM3) {
        frame.getContentPane().removeAll();
        Monster m3 = m.get(2);
        
        if (m3.getName() == null){
          NamePane namePane = new NamePane(frame, s, 2);
          frame.getContentPane().add(namePane);
          frame.pack();
          frame.setVisible(true);
          frame.repaint();
        } else {
          m3.timeElapsed();
          if(m3.isAlive()){
            MonsterPane monsterPane = new MonsterPane(frame, s, 2);
            frame.setPreferredSize(new Dimension(900, 900));
            frame.getContentPane().add(monsterPane);
            frame.pack();
            frame.setVisible(true);
            frame.repaint();
          }else {
            s.died(m3);
            if(s.isOver()){
              GameOverPane gameOverPane = new GameOverPane(frame);
              frame.setPreferredSize(new Dimension(1200, 1200));
              frame.getContentPane().add(gameOverPane);
              frame.pack();
              frame.setVisible(true);
              frame.repaint(); 
              
            } else {
              
              String alert = m3.getName() + " died. :(";
              JOptionPane.showMessageDialog(frame, alert);
              MonsterPane monsterPane = new MonsterPane(frame, s, 2);
              frame.setPreferredSize(new Dimension(900, 900));
              frame.getContentPane().add(monsterPane);
              frame.pack();
              frame.setVisible(true);
              frame.repaint();
            }
          }
        }
      }
      
      if (event.getSource() == selectM4) {
        frame.getContentPane().removeAll();
        Monster m4 = m.get(3);
        
        if (m4.getName() == null){
          NamePane namePane = new NamePane(frame, s, 3);
          frame.getContentPane().add(namePane);
          frame.pack();
          frame.setVisible(true);
          frame.repaint();
        } else {
          m4.timeElapsed();
          if(m4.isAlive()){
            MonsterPane monsterPane = new MonsterPane(frame, s, 3);
            frame.setPreferredSize(new Dimension(900, 900));
            frame.getContentPane().add(monsterPane);
            frame.pack();
            frame.setVisible(true);
            frame.repaint();
          }else {
            s.died(m4);
            if(s.isOver()){
              GameOverPane gameOverPane = new GameOverPane(frame);
              frame.setPreferredSize(new Dimension(1200, 1200));
              frame.getContentPane().add(gameOverPane);
              frame.pack();
              frame.setVisible(true);
              frame.repaint();             
            } else {
              String alert = m4.getName() + " died. :(";
              JOptionPane.showMessageDialog(frame, alert);
              MonsterPane monsterPane = new MonsterPane(frame, s, 3);
              frame.setPreferredSize(new Dimension(900, 900));
              frame.getContentPane().add(monsterPane);
              frame.pack();
              frame.setVisible(true);
              frame.repaint();
            }
          }
        }
      }
    }
    
  }//end ButtonListener
  
}//end class