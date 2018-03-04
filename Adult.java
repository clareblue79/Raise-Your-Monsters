/**
 * CS 230 Final Project 
 * Fall 2016, Wellesley College
 * 
 * Project Name: "Raise Your Monsters" 
 * Group Members: Clare Frances Lee and Samantha Stewart
 * 
 * Modified Date: 12/20/2016
 * File Name: Adult.java
 * 
 * Primary Responsibility: Samantha Stewart
 * 
 * Description: Adult class, is created from Teen object and extends Monster.
 * This monster has a name and keeps track of options for users.
 * This monster cannot die, but it can and does speak.
 * It can check whether two monsters are the same and get its name.
 */


import javafoundations.ArrayQueue; //array queue to hold speaking options
import javafoundations.BTNode; //to hold user interaction options



public class Adult extends Monster{
  
  
  private int type; //the type of monster, an int between 0-4
  private String[][] speak; //the speak options based on the type of monster and action
  private String speech; //current message to display to user
  
  
  
  
  
  /**
   * @param String name, integer current time (time created), integer type
   * constructor
   * creates a new Adult object with name of predecessor
   * full health, and level 0
   * @returns nothing
   */
  public Adult(String names, int time, int this_type){
    
    vitality = 50.0;
    
    name = names;

    type = this_type;
    level = 0;
    alive = true;
    lastVisited = time;
    currentTime = time;
    numPoops = 0;
    
    imagePath = "Media/adult.png";
    storageImagePath = "Media/storageAdult.png";
    
    speak = new String[4][4];
    speech = "";
    
    adult = true;
    
    //add all the speak option to the array
    //[type of monster][action]: actions are 0:food(not love heavy=sustenance), 
    //1:food(love heavy), 2:water, 3:love
    speak[0][0] = "Oh, am I energized!";
    speak[0][1] = "Wow, that's sweet!";
    speak[0][2] = "Aaah, refreshing!";
    speak[0][3] = "Aw, I love you too!";
    speak[1][0] = "Chomp. chomp. chomp..";
    speak[1][1] = "mmmm....";
    speak[1][2] = "Slurppp..";
    speak[1][3] = "aah mm..";
    speak[2][0] = "I don't need that!";
    speak[2][1] = "Gross, too sweet!";
    speak[2][2] = "Are you trying to drown me?!";
    speak[2][3] = "That was unnecessary!!";
    speak[3][0] = "Mmm, hmm, good stuff!";
    speak[3][1] = "I haven't had that in ages!";
    speak[3][2] = "Nothing better on a hot day.";
    speak[3][3] = "You're a sweet one";
    
    
    //create the decision tree of options for user interaction
    actions = new BTNode[4];
    actions[0] = new BTNode<String>("Love");
    BTNode<String> root = new BTNode<String>("Feed");
    BTNode<String> left = new BTNode<String>("Sustenance");
    BTNode<String> right = new BTNode<String>("Treat");
    BTNode<String> food1 = new BTNode<String>("Bread");
    BTNode<String> food2 = new BTNode<String>("Meat");
    BTNode<String> treat1 = new BTNode<String>("Candy");
    BTNode<String> treat2 = new BTNode<String>("Soda");
    left.setLeft(food1);
    left.setRight(food2);
    right.setLeft(treat1);
    right.setRight(treat2);
    root.setLeft(left);
    root.setRight(right);
    actions[1] = root;
    actions[2] = new BTNode<String>("Water");
    actions[3] = new BTNode<String>("Clean");
  }
  
  
  
  
  /**
   * @param Food object
   * this method updates vitality or talks based on the food inputted
   * @returns nothing
   */
  public void feed(Food f){
    
    //checks to see if the food was a treat (had love benefits)
    if(f.getLoveImpact()>0){
      speech = speak[type][1];
    }
    else
      speech = speak[type][0];
  }
  
  
  
  /**
   * @param
   * Adult cannot level up 
   * @returns null
   */
  public Monster levelUp(){
    return null;
  }
  
  
  
  
  /**
   * @param none
   * sets speech string to message based on the type of monster
   * @returns nothing
   */
  public void love(){
    speech = speak[type][3];
  }
  
  
  
  /**
   * @param none
   * updates speech to message based on type
   * @returns nothing
   */
  public void water(){
    speech = speak[type][2];
  }
  
  
  
  /**
   * @param none
   * cannot be cleaned. updates speech
   * @returns nothing
   */
  public void clean(){
    speech = "I *am* clean!";
  }
  
  
  
  /**
   * @param none
   * adult does not update
   * @returns nothing
   */
  public void timeElapsed(){
    //time does not matter to an adult, becuase adult vitality does not change
  }
  
  public String getSpeech(){
    return speech;
  }
  
}//end of Adult.java class