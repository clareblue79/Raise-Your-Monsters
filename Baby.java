/**
 * CS 230 Final Project 
 * Fall 2016, Wellesley College
 * 
 * Project Name: "Raise Your Monsters" 
 * Group Members: Clare Frances Lee and Samantha Stewart
 * 
 * Modified Date: 12/20/2016
 * File Name: Baby.java
 * 
 * Primary Responsibility: Samantha Stewart
 * 
 * Description: Baby class, inherits from the abstract Monster class to create a Monster 
 * object that has health, level, and name properties. It also keeps track of options for
 * users (only love) and when it was visited. 
 * It has methods that update its vitality and check if it is alive.
 * It can check whether two monsters are the same and get its name.
 */

import javafoundations.BTNode; //tree implementation for decisionTree options for food

public class Baby extends Monster{
  
  
  //rate at which love is lost (1.5 vitality lost per turn)
  private final double LOSE_LOVE = 1.5;

  
  
  /**
   * @param integer current time (time created)
   * constructor
   * creates a new Baby object with name Null
   * full health, and level 0
   * @returns nothing
   */
  public Baby(int time){
    
    vitality = 50.0;
    level = 0;
    name = null;
    alive = true;
    lastVisited = time;
    currentTime = time;
    numPoops = 0;
    
    actions = new BTNode[1];
    actions[0] = new BTNode("Love");
    imagePath = "Media/egg.png";
    storageImagePath = "Media/storageEgg.png";
  }
  
  
  

  /**
   * @param
   * changes this monster into a Teen monster if level is over 10;
   * @returns the new Teen monster (has the same name)
   */
  public Teen levelUp(){
    
    if(level>10){
      return new Teen(name, currentTime);
    }
    return null;
  }
  
  
  
  /**
   * @param none
   * updates (subtracts) vitality, (love) based on the time elapsed since the last visit
   * @returns nothing
   */
  public void timeElapsed(){
    
    int timePassed = (currentTime - lastVisited);
    
    //calculate how the time impacts the change in vitality
    double change = LOSE_LOVE*(timePassed);
    
    //change the vitality
    updateStatus(-change);
    
    //update last visited
    lastVisited = currentTime;
    
  }
  
  
  
  
  /**
   * @param String toName, will become the name of the monster
   * sets the input to be the name of the monster, only if the Monster has no name
   * @returns nothing
   */
  public void setName(String toName){
    
    //checks that the monster has no name
    if(name == null)
      name = toName;
  }
  
  
  
  /**
   * @param
   * gives the egg love, this updates the vitality and level statuses
   * @returns nothing
   */
  public void love(){
    
    //calls updateStatus to update the vitality
    updateStatus(5);
    
    level++;
  }
  
  
  
  
  /**
   * @param Food object
   * this method updates vitality or talks based on the food inputted
   * baby cannot be fed
   * @returns nothing
   */
  public void feed(Food f){
    
  }

  
  
  
  /**
   * @param none
   * updates vitality or speaks
   * baby cannot be watered
   * @returns nothing
   */
  public void water(){
    
  }
  
  
  
  
  /**
   * @param none
   * updates vitality or speaks
   * baby cannot be cleaned
   * @returns nothing
   */
  public void clean(){
    
  }

} //end Baby.java class
