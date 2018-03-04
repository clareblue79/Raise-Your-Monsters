/**
 * CS 230 Final Project 
 * Fall 2016, Wellesley College
 * 
 * Project Name: "Raise Your Monsters" 
 * Group Members: Clare Frances Lee and Samantha Stewart
 * 
 * Modified Date: 12/20/2016
 * File Name: Food.java
 * 
 * Primary Responsibility: Clare Frances Lee
 * 
 * Description: Food class, which constructs different food objects. Each
 * food object includes its name, the probably of it generating poop, and 
 * how it will impact the love and vitality status of the monster. 
 */

public class Food{
  
  /**************instance variables******************/
  
  //name of the food
  private String name; 
  
  //probably of the object generating poop
  private double poop; 
  
  //how much the food impacts vitality
  private double vitalityImpact; 
  
  //how much the food impacts love
  private double loveImpact; 
  
  //constructor
  public Food(String n, double p, int v, int l){
    name = n; 
    poop = p; 
    vitalityImpact = v;
    loveImpact = l;
  }
  
  /******************Getter and setter methods*********************/
  
   /*
   * @params none
   * @returns String, name of the food
   */
  public String getName(){
    return name;
  }
  
   /*
   * @params none
   * @returns double, probability of the object generating poop
   */
  public double getPoop(){
    return poop;
  }
  
  /*
   * @params none
   * @returns double, how much the food impacts vitality
   */
  public double getVitalityImpact(){
    return vitalityImpact;
  }
  
   /*
   * @params none
   * @returns double, how much the food impacts love
   */
  public double getLoveImpact(){
    return loveImpact;
  }
  
   /*
   * @params String name
   * updates the name of the food object
   * @returns nothing
   */
  public void setName(String n2){
    name = n2;
  }
  
    /*
   * @params double probability
   * updates the probability of the object generating poop
   * @returns nothing
   */
  public void setPoop(double p2){
    poop = p2;
  }
  
  /*
   * @params double probability
   * updates how much the food impacts vitality
   * @returns nothing
   */
  public void setVitalityImpact(double v2){
    vitalityImpact = v2;
  }
  
    /*
   * @params double probability
   * updates how much the food impacts love
   * @returns nothing
   */
  public void setLoveImpact(double l2){
    loveImpact = l2;
  }
  
  //****************end of getter and setter methods*******************
 
   /**
     * @params none
     * @ returns a boolean
     * a predicate method to determine if poop needs to be generated. 
     * The method generates a random number and compares it to the
     * poop probability of the food object
     */ 
  
  public boolean doesPoop(){
    double randNum = Math.random();
    if (poop < randNum) return true;
    else return false;
  }
  
}//end class