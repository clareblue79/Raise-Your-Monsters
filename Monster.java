/**
 * CS 230 Final Project 
 * Fall 2016, Wellesley College
 * 
 * Project Name: "Raise Your Monsters" 
 * Group Members: Clare Frances Lee and Samantha Stewart
 * 
 * Modified Date: 12/20/2016
 * File Name: Monster.java
 * 
 * Primary Responsibility: Samantha Stewart
 * Additional Coding: Clare Frances Lee
 * 
 * Description: Monster class, abstract class to act as the framework for all monster
 * classes (Baby, Teen, and Adult) creates a Monster object that has health, level, and name
 * properties. It also keeps track of options for users and when it was visited. 
 * It has same methods that update its vitality, feed the monster, and check if it is alive.
 * It can check whether two monsters are the same and get its name.
 */

import javafoundations.BTNode; //in order to make the user options clear

public abstract class Monster{
  
  //name of the Monster, must be unique
  protected String name;
  
  //string of the image path for the monster; to be displayed in the GUI
  protected String imagePath;
  
  //string of the image path for the monster to be displayed in the Storage Pane
  protected String storageImagePath;
  
  //health of the monster (range from 0-50, 50 being healthiest)
  protected double vitality;
  
  //when this reaches specific number the monster will level up
  protected int level;
  
  //array of Binary Tree Nodes that represent the initial actions the user can do
  protected BTNode<String>[] actions;
  
  //whether the monster is currently living, true if yes
  protected boolean alive;
  
  //counts the number of moves ("time"), this stores the last move that the user
  //visited this monster
  protected int lastVisited;
  
  //counts the number of moves, this stores the current time of user visit
  protected int currentTime;
  
  //true if adult monster
  protected boolean adult = false;
  
  //number of poops at any time
  protected int numPoops;
  
  /**
   * Methods
   */
  
  
  /**
   * @param none
   * updates (subtracts) vitality, (love, clean, feed, and water) based on 
   * the time elapsed since the last visit
   * @returns nothing
   */
  public abstract void timeElapsed();
  
  
  /**
   * @param double change, represents how vitality status will change (+ or -)
   * changes the vitality status, if the change means it is greater than 50 then it is capped off
   * if the change puts the monster at zero or below then the monster dies
   * @returns nothing
   */
  protected void updateStatus(double change){
        
    //update vitality status
    vitality += change;
    
    //check that vitality is in range
    if(vitality > 50){
      vitality = 50;
    }
    
    else if(vitality <=0){
      //if there is no health left then the monster is dead
      alive = false;
    }
  }
  
  /**
   * @param none
   * updates vitality or speech
   * @returns nothing
   */
  public abstract void love();
  
  /**
   * @param Food object
   * this method updates vitality or talks based on the food inputted
   * @returns nothing
   */
  public abstract void feed(Food f);
  
  /**
   * @param none
   * updates vitality or speaks
   * @returns nothing
   */
  public abstract void water();
  
  /* @param none
   * cleans poop if there is some there
   * @returns nothing
   */
  public abstract void clean();
  
  /* @param none
   * @returns boolean of whether the monster is alive (true if yes)
   */
  public boolean isAlive(){
    return alive;
  }
  
  /**
   * @param none
   * @ retrunrs true if adult monster
   */
  public boolean isAdult(){
    return adult;
  }
  
  /**
   * @param
   * changes the current monster into an older monster, only if it 
   * meets the level up requirements. 
   * @returns the new monster (has the same name, creates a new type)
   *   returns null if it does not meet level up recquirements
   */
  public abstract Monster levelUp();
  
  
  /**
   * @param other Monster to compare to this one
   * checks that the monster is alive/exists
   * if the monster is living then it checks to see if they are the same
   * if their names are the same then they are the same monster
   * @returns true if the names are the same, false if different or doesn't exist
   */
  public boolean equals(Monster other){
        return (name.equals(other.getName()));
  }
  
  /********************************************************************************
   * Getter and Setter Methods
   ********************************************************************************
   */
  
  /**
   * @param none
   * @returns the String of the name of the monster
   */
  public String getName(){
    return name;
  }
  
  /**
   * @param none
   * @returns the String of the image path
   */
  public String getImagePath(){
    return imagePath;
  }
  
  /**
   * @param none
   * @returns the String of the storageImage path
   */
  public String getStorageImagePath(){
    return storageImagePath;
  }
  
  
  /**
   * @param none
   * @returns the double vitality (<=50), that represents the health of the monster
   */
  public double getVitality(){
    return vitality;
  }
  
  
  /**
   * @param double to replave the vitality (health) of the monster.
   * double must be between 0 and 50. if it isn't this doesn't do anything
   * @returns nothing
   */
  public void setVitality(double vitals){
    
    //check that the new number is in range
    if( (vitals>=0) && (vitals<=50)){
      vitality = vitals;
    }
  }
  
  
  /**
   * @param none
   * @returns (int) the level of the monster (monster should transform at a certain level)
   */
  public int getLevel(){
    return level;
  }
  
  /**
   * @param int lvl that represents the new level of the monster
   * if the lvl is in range (btwn 0 and 10) then the level is changed,
   * otherwise nothing happens
   * @returns nothing
   */
  public void setLevel(int lvl){
    
    //check that the level is in range
    if( (lvl>=0) && (lvl<= 10)){
      level = lvl;
    }
    
  }
  
  
  /**
   * @param boolean of whether the monster is alive (true if yes)
   * changes the variable to reflect the input. if input is false then the vitality (health) status goes to 0. 
   * @returns nothing
   */
  public void setAlive(boolean living){
    
    alive = living;
    
    //if we are changing the monster to dead then the health must be 0
    if(!living){
      vitality = 0;
    }
  }
  
  
  /**
   * @param none
   * returns the decisionTree for the monster. So all of the options of the actions that
   * the user can do to care for the monster
   * for baby the only option is love
   * @returns the array of string binary tree nodes of options
   */
  public BTNode<String>[] getDecisionTree(){
    return actions;
  }
  
  
  /**
   * @param none
   * gets the last visited time (number of moves) for the Monster
   * @returns int of last Visit
   */
  public int getLastVisit(){
    return lastVisited;
  }
  
  
    
  /**
   * @param
   * sets the last visited time (number of moves) for the Monster
   * @returns nothing
   */
  public void setTime(){
    lastVisited = currentTime;
  }
  
  /**
   * @param int time (total number of moves)
   * updatest the current time of the monster
   * @returns nothing
   */
  public void setCurrentTime(int time){
    currentTime = time;
  }
  
  /**
   * @param none
   * returns current time
   * @returns nothing
   */
  public int getCurrentTime(){
    return currentTime;
  }
  
  /**
   * @param none
   * @returns number of poops currently in existence
   */
  public int getNumPoops(){
    return numPoops;
  }
  
  /**
   * @param none
   * @overrides toString()
   * @returns name of the monster
   */
  public String toString(){
    
    return name;
  }
  
  
}//end of Monster.java class