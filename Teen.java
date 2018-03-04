/**
 * CS 230 Final Project 
 * Fall 2016, Wellesley College
 * 
 * Project Name: "Raise Your Monsters" 
 * Group Members: Clare Frances Lee and Samantha Stewart
 * 
 * Modified Date: 12/20/2016
 * File Name: Teen.java
 *  
 * Primary Responsibility: Samantha Stewart
 * 
 * Description: Teen class, inherits from abstract Monster class and is created from
 * leveled up Baby object. This Monster object that has health, level, and name
 * properties. It also keeps track of options for users and when it was last visited. 
 * It has same methods that update its vitality, which depend on how long since it has been visited
 * and can gain health through user choices. How much health is dependent on internal needs
 * and current fullfilment. The user can feed and care for the monster, and check if it is alive.
 * It can check whether two monsters are the same and get its name. When it levels up it creates
 * an Adult Monster.
 */

import javafoundations.BTNode; //to make options for user to interact with monster
import javafoundations.ArrayQueue; //array queue to hold poop
import java.util.Random; //to select which type of Adult monster will be formed

public class Teen extends Monster{
  
  //instance variables
  private ArrayQueue<Food> poopQueue; //queue that holds food object and determines if the monster will poop
  
  
  //following doubles represent the rate at which the monster loses health based on the 
  //following characteristics over time
  final private double LOSE_LOVE = .3; //rate health is lost from not being loved
  final private double LOSE_FOOD = .7; //rate health is lost from not being fed
  final private double LOSE_WATER = .5; //rate water health is lost
  final private double LOSE_CLEAN = .7; //rate health is lost from being around poop
  
  
  //following doubles represent max health possible from each action
  final private double MAX_LOVE = 10;
  final private double MAX_FOOD = 30;
  final private double MAX_WATER = 15;
  final private double MAX_CLEAN = 15;
  
  //following represent the current health from each option
  private double currentLove;
  private double currentFood;
  private double currentWater;
  private double currentClean;
  
  //to level up to an adult the monster must have at least each of the following
  //(each number represents a choice from one turn)
  private final int MIN_FOOD = 10;
  private final int MIN_LOVE = 1;
  private final int MIN_WATER = 2;
  private final int MIN_CLEAN = 3;
  private final int MIN_LEVEL = 20;
  
  //the following keep track of the choices that the user has made in taking care of 
  //the monster. aka current levels. These must exceed the mins above to level up
  private int foodLevel;
  private int waterLevel;
  private int cleanLevel;
  private int loveLevel;
  
 
  
  /**
   * @param String name, int time
   * constructor
   * creates a new Teen object with the name as the input, time as current time
   * full health, and level 0
   * @returns nothing
   */
  public Teen(String toName, int time){
    
    //set old statistics to same as when Baby was initialized
    vitality = 50.0;
    level = 0;
    name = toName;
    alive = true;
    imagePath = "Media/teen.png";
    storageImagePath = "Media/storageTeen.png";
    
    //set the current and visit time to time of creation
    currentTime = time;
    lastVisited = time;
    
    //create an empty poop queue
    poopQueue = new ArrayQueue();
    numPoops = 0;
    
    //set current health breakdown to natural
    currentLove = 5.0;
    currentFood = 25.0;
    currentWater = 10.0;
    currentClean = 10.0;
    
    //set all levels to 0
    foodLevel = 0;
    loveLevel = 0;
    waterLevel = 0;
    cleanLevel = 0;
    
    //create the decision tree
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
   * @param
   * changes this monster into an Adult monster, only if it meets the level up requirements
   * must have current levels in each category (food, clean, love, water) above minimum levels
   * and must have total level over 20
   * @returns the new Adult monster (has the same name, creates a new type)
   */
  public Adult levelUp(){
    
    //check that the total level is over the requirement (20)
    if(level >= 20){
      
      //check that each category is above min
      if((foodLevel>=MIN_FOOD)&&(waterLevel>=MIN_WATER)&&(cleanLevel>=MIN_CLEAN)&&(loveLevel>MIN_LOVE)){
        
        //if conditions met then make new adult of random type
        Random rand = new Random();
        int fave_type = rand.nextInt(4);
        
        return new Adult(name, currentTime, fave_type);
      }
    }
    
    return null;
  }
  
  
  
  /**
   * @param Food object
   * this method updates the vitality and level of the monster
   * @returns nothing
   */
  public void feed(Food f){
    
    //create a counter to determine how much this food impacts vitality
    double impact;
    
    //add the food to the poop queue. when it is dequeued there is a probability 
    //of it turning into a poop
    poopQueue.enqueue(f);
    
    //see how much the food impacts vitality (through food and love)
    
    //get the impact on vitality through food
    double foodChange = f.getVitalityImpact();
    
    //check that all of the food impact can be added 
    if( (foodChange + currentFood)<=MAX_FOOD){
      
      //if it can be then add it
      impact = foodChange;
      
      //update currentFood level
      currentFood += foodChange;
    }
    
    
    //if all of it can't be added then add what can be
    else{
      impact = (MAX_FOOD-currentFood);
      //update current food
      currentFood = MAX_FOOD;
    }
    
    
    //get the impact on vitality through love
    double loveChange = f.getLoveImpact();
    
    //check that all of the love impact can be added 
    if( (loveChange + currentLove)<=MAX_LOVE){
      
      //if it can be then add it
      impact += loveChange;
      
      //update currentLove level
      currentLove += loveChange;
    }
    
    
    //if all of it can't be added then add what can be
    else{
      impact += (MAX_LOVE-currentLove);
      //update current love
      currentLove = MAX_LOVE;
    }
    
    //update the vitality status bar 
    updateStatus(impact);
    
    
    //increment the level
    level++;
    foodLevel++;
  }
  
  
  
  /**
   * @param none
   * gives the teen love, this updates the vitality and level statuses
   * @returns nothing
   */
  public void love(){
    
    //checks that we can add all the love to the vitality
    if((currentLove+2)<MAX_LOVE){
      //calls updateStatus to update the vitality
      updateStatus(2);
      currentLove +=2;
    }
    //if we can't add all two then add what we can
    else{
      updateStatus((MAX_LOVE-currentLove));
      currentLove = MAX_LOVE;
    }
    
    
    //update levels
    level++;
    loveLevel++;
    
  }
  
  
  
  
  /**
   * @param none
   * if there is a poop then clean will remove one and update the vitality and level 
   * status to reflect that. If there is no poop then it does nothing but the poop level
   * increases
   * @returns nothing
   */
  public void clean(){
    
    //checks that there is a poop to clean
    if(numPoops>0){
      
      //remove the poop
      numPoops--;
      
      //check that we can increase clean vitality
      if( (currentClean+4)<MAX_CLEAN){
        //update the vitality
        updateStatus(4);
        
        //update the current clean value
        currentClean += 4;
      }
      //if we cant increase the clean vitality all the way add what we can
      else{
        updateStatus((MAX_CLEAN-currentClean));
        currentClean = MAX_CLEAN;
      }
    }
    
    //update levels
    level++;
    cleanLevel++;
    
  }
  
  
  
  /**
   * @param none
   * waters the monster if it can gain health from getting water
   * increases level regardless
   * @returns nothing
   */
  public void water(){
    
    //check that we can increase water vitality
    if( (currentWater+4)<MAX_WATER){
      //update the vitality
      updateStatus(4);
      
      //update the current water value
      currentWater += 4;
    }
    //if we cant increase the water vitality all the way add what we can
    else{
      updateStatus((MAX_WATER-currentWater));
      currentWater = MAX_WATER;
    }
    
    //update levels
    level++;
    waterLevel++;
    
  }
  
  
  
  /**
   * @param int time
   * updates (subtracts) vitality, (love, clean, feed, and water) based on the
   * time elapsed since the last visit
   * @returns nothing
   */
  public void timeElapsed(){

    int timePassed = (currentTime - lastVisited);
    
    //find out how many poops we have, for every 3 turns it is possible that the Monster poops
    for(int t=0; t<timePassed; t+=3){
      //if there is food in the poopQueue then check to see if it turns to poop
      if(!poopQueue.isEmpty()){
        //dequeue from poopQueue to see if monster pooped then call doesPoop on food object
        Food removed = poopQueue.dequeue();
        
        //if the Monster does poop then add the poop to the count of num poops
        if(removed.doesPoop()){
          numPoops++;
        }
      }
    }
    double cleanChange =0;
    //calculate how the time impacts the change in vitality
    double loveChange = (-1)*LOSE_LOVE*(timePassed);
    double foodChange = (-1)*LOSE_FOOD*(timePassed);
    double waterChange = (-1)*LOSE_WATER*(timePassed);
    if(numPoops>0){
      cleanChange = (-1)*LOSE_CLEAN*(timePassed)*numPoops;
    }
    else{
      cleanChange = (-1)*LOSE_CLEAN*timePassed;
    }
     
    //change the vitality
    double totalChange = loveChange+foodChange+waterChange+cleanChange;
    updateStatus(totalChange);
    
    //update the current health status of each sub section
    currentLove += loveChange;
    currentFood += foodChange;
    currentWater += waterChange;
    currentClean += cleanChange;
    
    //update last visited
    lastVisited = currentTime;
    
  }
  
  
}//end Teen.java class
