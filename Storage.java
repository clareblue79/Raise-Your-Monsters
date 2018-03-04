/**
 * CS 230 Final Project 
 * Fall 2016, Wellesley College
 * 
 * Project Name: "Raise Your Monsters" 
 * Group Members: Clare Frances Lee and Samantha Stewart
 * 
 * Modified Date: 12/20/2016
 * File Name: Storage.java
 * 
 * Primary Responsibility: Samantha Stewart
 * 
 * Description: Storage class, creates a storage space that holds 4 Monster Objects.
 * It creates four babies and keeps track of how many have died. When one dies a new baby
 * is created as long as there have been fewer than 3 deaths. It can also check if the game 
 * is over (3 deaths), and whether a Monster is alive or currently in storage. It can kill
 * a monster and get or set the monsters included.
 */

import java.util.LinkedList;

public class Storage{
  
  //instance variables
  private int num_deaths; //number of monsters that have died
  private LinkedList<Monster> monsters; //linked list that holds the monsters
  private int currentTime; //number of moves played in the game
  
  
  /**
   * Constructor
   * creates a linked list with four new monsters (baby/egg)
   * number of deaths is set at zero
   * @returns nothing
   */
  public Storage(){
    
    num_deaths = 0;
    monsters = new LinkedList<Monster>();
    
    //populates the linked list with four baby monsters to start
    for(int i=0; i<4; i++){
      monsters.add(new Baby(0));
    }
    
    currentTime = 0;
  }
  
  
  
  /**
   * checks if the game is over. the game is over if there are more than two deaths
   * @returns true if there are more than 2 deaths (game is over)
   * @returns false if otherwise
   */
  public boolean isOver(){
    
    return (num_deaths > 2);
  }
  
  
  
  /**
   * @param takes in a Monster object
   * checks that the monster is in the collection, if it is then it is alive
   * if the monster is not in the collection then it has died or it did not ever exist
   * @returns true if monster is not in the list
   * @returns false if monster is in the list
   */
  public boolean isDead(Monster m){
    
    //calls contains method from the java LinkedList class
    return !(monsters.contains(m));
  }
  
  
  
  /**
   * @param takes in a Monster object
   * checks to see if the specified monster is in the storage
   * @returns true if the monster is in the list
   * @returns false if not
   */
  public boolean contains(Monster m){
    
    //calls contains method from java linkedList class
    return monsters.contains(m);
  }
  
  
  
  /**
   * @param takes in a Monster object
   * checks to see that the monster is in the list, if it is then it removes it and adds a new baby
   * death count is incremented
   * if the monster is not in the list then this does nothing
   * @returns nothing
   */
  public void died(Monster m){
    
    //checks to see that the monster is in the list
    if(contains(m)){
      
      //get index of the monster that will die
      int index = monsters.indexOf(m);
      
      //add a new baby monster in the position of the dead monster
      monsters.set(index, new Baby(currentTime));
      
      //increment the death count
      num_deaths++;
      
    }
  }
  
  /**
   * @param takes in 2 Monster objects, the old and the one that is new level
   * replaces the old monster with the new one
   * @returns nothing
   */
  public void levelUp(Monster old, Monster new1){

      //get index of the monster that will die
      int index = monsters.indexOf(old);
      
      //add a new  monster in the position of the old monster
      monsters.set(index, new1);
      
  }
  
  
  
  /**
   * @param none
   * @returns the (integer) number of deaths
   */
  public int getNumDeaths(){
    
    return num_deaths;
  }
  
  
  
  /**
   * @param integer i
   * sets the number of deaths to the integer given, unless the integer doesn't make sense (less than 0)
   * @returns nothing
   */
  public void setNumDeaths(int i){
    
    //check that number of deaths makes sense (not less than 0, and not greater than 3)
    if((i>=0) && (i<=3)){
      
      //set the number of deaths to be the input
      num_deaths = i;
    }
  }
  
  
  
  /**
   * @param none
   * @returns a LinkedList of all the monsters
   */
  public LinkedList<Monster> getMonsters(){
    
    return monsters;
  }
  
  
  
  
  /**
   * @param LinkedList<Monsters>
   * checks that the LinkedList contains 4 monsters
   * sets the linkedList to the input unless there is some other number of monsters
   * @returns nothing
   */
  public void setMonsters(LinkedList<Monster> mons){
    
    if(mons.size()==4){
      monsters = mons;
    }
  }

  
  /**
   * @param none
   * @returns current time (num moves made by the user)
   */
  public int getCurrentTime(){
    return currentTime;
  }
  
  
  /**
   * @param int timeThisTurn
   * @returns nothing
   */
  public void setCurrentTime(int time){
    //add the number of moves from this turn to the currentTime
    currentTime+=time;
    
    //update current time of all monsters
        
    //check each monster
    for(int i=0; i<4; i++){
      Monster current = monsters.get(i);
      //if a monster has this name then we can't name one this.
      current.setCurrentTime(currentTime);
    }
  }
  
  /**
   * @param String that user is trying to name a baby
   * @returns true if no other monster has this name
   *   false if another monster does have it
   */
  public boolean canName(String name){
    
    //check each monster
    for(int i=0; i<4; i++){
      Monster current = monsters.get(i);
      //if a monster has this name then we can't name one this.
      if(name.equals(current.getName()))
        return false;
    }
    
    //if we have gone through all monsters and none has this name then we can name it
    return true;
    
  }
  
  /**
   * @param none
   * checks to see if we won, 
   * @returns true if all four monsters are adults
   */
  public boolean weWon(){
    
    //check each monster
    for(int i=0; i<4; i++){
      Monster current = monsters.get(i);
      //if a monster has this name then we can't name one this.
      if(!(current.isAdult()))
        return false;
    }
    
    //if we have gone through all monsters and none has this name then we can name it
    return true;
    
  }
  
  
  /**
   * @param none
   * @overrides toString()
   * @returns string representation of storage. returns each monsters name and location
   * and total number of deaths
   */
  public String toString(){
    
    String s = "This Storage has "+num_deaths+" deaths and contains: \n";
    
    for(int i=0; i<4; i++){
      Monster current = monsters.get(i);
      s+= "\t"+current.getName()+" is at: "+i+"\n";
    }
    
    s+="end Storage\n";
    return s;
  }
  
  
  
}//end Storage.java class
