/**
 * CS 230 Final Project 
 * Fall 2016, Wellesley College
 * 
 * Project Name: "Raise Your Monsters" 
 * Group Members: Clare Frances Lee and Samantah Stewart
 * 
 * Modified Date: 12/20/2016
 * File Name: MonsterPane.java
 * 
 * Primary Responsibility: Clare Frances Lee
 * Additional Coding: Samantha Stewart
 * 
 * Description: The monster panel, where the users can interact with 
 * the selected monster. The user can name and initialize the monster 
 * baby when first visited, and depending on the level/status, the users
 * can interact with the monsters by feeding, loving, cleaning, and etc. 
 */

import java.util.LinkedList;
import javafoundations.*;
import java.awt.Dimension;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MonsterPane extends JPanel {
  
  /**************instance variables******************/
  //all the different buttons and panels for the layout 
  //JPanel components......
  private JButton returnButton;
  private JButton sustenance;
  private JButton treat;
  private JLabel speech;
  private JLabel poop;
  private JFrame frame;
  private JLabel vitality;
  private JPanel monsterBlock;
  private JPanel foodOptions;
  private JPanel sustenanceOptions;
  private JPanel treatOptions;
  private JPanel food; 
  private JPanel selections;
  
  //Different classes and variables needed to keep track of the
  //selected monster and updating its information in the storage
  //as user interacts with it 
  private Storage storage;
  private int index;
  private LinkedList<Monster> monsters;
  private Monster m;
  private int time;
  
  /**
   * The constructor
   * @params frame, storage, index
   * @returns nothing
   * takes in the frame of which the components are getting added on, the
   * storage which monster information is stored, and the index of the monster 
   * within the storage, the panel can be populated, and the interaction can update
   * with the selected monster from the storagePane. 
   * 
   */
  public MonsterPane(JFrame frame, Storage s, int i){
    //takes in the parameter and instatiates the necessary instance variables
    this.frame = frame;
    this.setBackground(Color.lightGray);
    this.storage = s;
    this.monsters = storage.getMonsters();
    this.index = i;
    this.m = monsters.get(index);
    
    //total number of moves the user inputs this round
    //stored to calculate the time elapsed for other monsters
    time=0;
    
    //add start game and about button
    returnButton = new JButton("Return to Storage");
    vitality = new JLabel("Vitality: " + m.getVitality());
    
    // adding non-default font to button 
    returnButton.setFont(new Font("Trebuchet",Font.PLAIN, 20));
    returnButton.addActionListener(new ButtonListener());
    
    //calls Creatblock function 
    monsterBlock = createBlock(m);
    this.add(monsterBlock);
    this.add(createOptions());
    
    //add the food button options and hides it 
    food = new JPanel();
    sustenance = new JButton("Sustenance");
    sustenance.addActionListener(new ButtonListener());
    treat = new JButton("Treat");
    treat.addActionListener(new ButtonListener());
    food.add(sustenance);
    food.add(treat);
    food.setVisible(false);
    foodOptions.add(food);
    
    //adds the sustenance options and hides it until user clicks it
    sustenanceOptions = new JPanel();
    sustenanceOptions.setLayout(new GridLayout(2,1));
    JButton bread = new JButton("Bread");
    bread.addActionListener(new ButtonListener());
    JButton meat = new JButton ("Meat");
    meat.addActionListener(new ButtonListener());
    
    sustenanceOptions.add(meat);
    sustenanceOptions.add(bread);
    sustenanceOptions.setVisible(false);
    selections.add(sustenanceOptions);
    
    //adds the treatOption and hides it until user clicks it
    treatOptions = new JPanel();
    treatOptions.setLayout(new GridLayout(2,1));
    JButton candy = new JButton("Candy");
    candy.addActionListener(new ButtonListener());
    JButton soda = new JButton ("Soda");
    soda.addActionListener(new ButtonListener());
    
    treatOptions.add(candy);
    treatOptions.add(soda);
    treatOptions.setVisible(false);
    selections.add(treatOptions);
    
    //generate poops and populates the panel are if the probablity is bigger than 1
    if (m.getNumPoops() > 0){
      ImageIcon icon = createImageIcon("Media/poop.png", "poop image");
      poop = new JLabel(icon);
      frame.add(poop);
      poop.setVisible(true);
    }
    
  }//end constructor
  
  /**
   * @params none
   * @returns JPanel
   * looks through the monster's decision tree and populate the interaction 
   * option depending the monster's level. returns the populated JPanel
   */
  private JPanel createOptions(){
    JPanel optionPanel = new JPanel();
    
    //checks the different interaction option by looking at the decision tree
    BTNode<String>[] options = m.getDecisionTree();
    
    //two extra rows for the hidden food options
    optionPanel.setLayout(new GridLayout(options.length+2, 1));
    
    //populates and creates the button in the JPanel
    for (int i=0; i < options.length; i++){
      String buttonName = options[i].getElement();
      JButton button1 = new JButton(buttonName);
      optionPanel.add(button1);
      button1.setFont(new Font("Trebuchet",Font.PLAIN, 16));
      button1.addActionListener(new ButtonListener());
    }
    
    //add the hidden JPanels the bottom two rows
    foodOptions = new JPanel();
    selections = new JPanel();
    
    optionPanel.add(foodOptions);
    optionPanel.add(selections);
    
    //returns the optionPanel
    return optionPanel;
    
  }//end createOptions
  
  /**
   * @params monster
   * @returns JPanel
   * takes in the Monster and creates a block with the selected monster's information
   */
  private JPanel createBlock(Monster m1){
    //the parameters imagepath, monster name, and button;
    JPanel block1 = new JPanel();
    
    ImageIcon icon = createImageIcon(m1.getImagePath(), "monster image");
    JLabel name = new JLabel(m1.getName(), icon, JLabel.CENTER);
    name.setVerticalTextPosition(JLabel.BOTTOM);
    name.setHorizontalTextPosition(JLabel.CENTER);
    name.setFont(new Font("Trebuchet",Font.PLAIN, 20));
    speech = new JLabel("");
    speech.setFont(new Font("Trebuchet",Font.PLAIN, 14));
    
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
                                            .addComponent(speech)
                                            .addComponent(returnButton)
                                            .addComponent(vitality)
                                         )
                             );
    
    layout.setVerticalGroup(
                            layout.createSequentialGroup()
                              .addComponent(name)
                              .addComponent(speech)
                              .addComponent(returnButton) 
                              .addComponent(vitality)
                           );
    
    return block1;
    
  }//end createBlocks
  
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
      //this is saved since some button's text need to be checked
      JButton clicked = (JButton) event.getSource();
      
      
      if (clicked == returnButton) {
        //if the return storage button is clicked
        // call setTime for this Monster
        m.setTime(); //sets the lastVisited time for that monster
        
        //go on to the storage panel:
        frame.getContentPane().removeAll();
        StoragePane storagePane = new StoragePane(frame, storage);
        // storagePane.updateGrid(storage);
        frame.getContentPane().add(storagePane);
        frame.pack();
        frame.setVisible(true);
        frame.repaint();
      }
      
      //For different interaction option buttons, it will call the 
      //relevate method for the monster of the action and updates its
      //vitality or love 
      if(clicked.getText().equals("Love")){
        
        //call monster's love method
        m.love();
        
        //update vitality 
        vitality.setText("Vitality: " + m.getVitality());
        
        //add time
        time++;
        
        //call hasleveledup to check
        hasLeveledUp(m);
        
        //call speak for the case of adult monsters
        speak(m);
        
      }
      
      //the following options have the same mechanism as the love button
      if(clicked.getText().equals("Water")){
        m.water();
        vitality.setText("Vitality: " + m.getVitality());
        time++;
        
        speak(m);
        hasLeveledUp(m);
      }
      
      if(clicked.getText().equals("Clean")){
        m.clean();
        vitality.setText("Vitality: " + m.getVitality());
        time++;
        speak(m);
        hasLeveledUp(m);
        if (m.getNumPoops() == 0) poop.setVisible(false);
      }
      
      //if food button is clicked sustenance and treat options are shows
      if(clicked.getText().equals("Feed")){
        
        food.setVisible(true);
        
      }
      
      //if sustenance button is clicked meat and bread options are shown
      if(clicked == sustenance){
        System.out.println("Sustenance button clicked");
        sustenanceOptions.setVisible(true);
        
      }
      
      //if treat button is clicked candy and soda options are shown
      if(clicked == treat){
        System.out.println("Treat button clicked");
        treatOptions.setVisible(true);
      }
      
      //again, same mechanism as the love button, updating the selected
      //monster's vitality by using feed method and the food object
      if(clicked.getText().equals("Bread")){
        System.out.println("Bread Button is Clicked");
        Food bread2 = new Food("Bread", 0.5, 7, 0);
        m.feed(bread2);
        vitality.setText("Vitality: " + m.getVitality());
        time++;
        
        speak(m);
        hasLeveledUp(m);
      }
      if(clicked.getText().equals("Meat")){
        Food meat2 = new Food("Meat", .4, 7, 0);
        m.feed(meat2);
        
        vitality.setText("Vitality: " + m.getVitality());
        time++;
        
        speak(m);
        hasLeveledUp(m);
      }
      if(clicked.getText().equals("Candy")){
        Food candy2 = new Food("Candy", .01, 3, 2);
        m.feed(candy2);
        vitality.setText("Vitality: " + m.getVitality());
        time++;
        
        speak(m);
        hasLeveledUp(m);
      }
      if(clicked.getText().equals("Soda")){
        Food soda2 = new Food("Soda", .1, 3, 1); 
        m.feed(soda2);
        vitality.setText("Vitality: " + m.getVitality());
        time++;
        
        speak(m);
        hasLeveledUp(m);
      }
      
    }//end actionPerformed
  }//end ButtonListener
  
  /**
   * @params monster
   * @returns nothing
   * takes in the selected monster and determines if we level up
   */
  private void hasLeveledUp(Monster m1){
    
    storage.setCurrentTime(1);
    Monster result = m1.levelUp();
    
    if(result != null){
      //add the new monster to storage when we level up
      //storage.setCurrentTime(time);
      storage.levelUp(m1, result);
      
      if (storage.weWon()){
        //check if the player has won the game, 
        //if so launch the finish Panel
        frame.getContentPane().removeAll();
        FinishPane won = new FinishPane(frame);
        frame.setPreferredSize(new Dimension(1500, 1200));
        frame.getContentPane().add(won);
        frame.pack();
        frame.setVisible(true);
        frame.repaint();
        
      }
      else{
        //update GUI if the game is not won with the monster pane
        frame.getContentPane().removeAll();
        MonsterPane monsterPane = new MonsterPane(frame, storage, index);
        frame.getContentPane().add(monsterPane);
        frame.pack();
        frame.setVisible(true);
        frame.repaint();
      }
    }
  }//end hasLeveledUp()
  
  /**
   * @params monster
   * @returns nothing
   * takes in the selected monster and determines if it is an adult, 
   * if so, update the speech label as the user interacts with the monster
   */
  private void speak(Monster m2){
    
    //if the selected monster is an adult
    if(m2.isAdult()){
      Adult a = (Adult) m2;
      //update speech label with appropriate text
      speech.setText(a.getSpeech());
    }
  }//end speak()
  
}//end class