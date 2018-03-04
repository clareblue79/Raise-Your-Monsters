# Raise Your Monsters Game
## Wellesley CS230 Data Structures Final Project

**By Clare Lee and Samantha Stewart**

**_Game Description_**

This is a game that simulates Tamagotchi. You will have four monsters that start as eggs. You will be able to name and care for them as they grow. It is your job to make sure they don’t die by feeding, loving, cleaning, and watering them. These options change based on what level your monster is. They start as babies and grow into teens and become adults with unique sayings. Have fun!

**_Goal_**: Level up all four monsters to adult. Note that you will lose the game if you kill more than two monsters.

#### How to Run the Program
The program is run by running Home.java. This will take you through the game smoothly as long as you hit the button options and do not close the window. When the window is closed or the game finishes, you have quit the game and should restart by running the program again.

#### How to Play
Start by pressing “Start Game” or read about how to play the game by clicking “About.” The storage place holds four monsters. You can interact with a monster by clicking “Visit” below its image. When you first visit a monster you will have the option to name it. Note that the names must be unique. Names will appear below the monster or egg after you have chosen a name. When you select a monster it takes you to a page where you can interact with your monster. The number at the bottom of the monster shows the health of your monster. If that hits zero, your monster dies. As you level up you will have more interaction options. For instance, you can Feed, Water, and Clean teen monsters in addition to giving Love. Teen monsters have the ability to poop. If there is poop, then you will need to clean your monster or it will lose health. When you have finished interacting with the monster you can select the “Return to Storage” button. Note that as time passes your monsters will lose health. Different level monsters have different needs. It is your responsibility to determine what your monsters need in order to be healthy and level up. When the last teen is leveled up to an adult you will win. You will be notified when a monster dies. (Try to avoid seeing this message!) Note that you cannot kill an adult, although you can still interact with it (see what they say!).There are two outcomes to this game. Success….Or failure….Have fun and good luck leveling up your monsters!

#### Technical Report
###### ADTS
- LinkedList to Manage the babies: Keeps track of the number of deaths. We could use different data structures ( arrays) but we like the flexibility of the LinkedList so that when a monster is killed it is easy to remove it and generate another baby without bothering with indices.
- Queue to keep the number of user’s feed input: Everytime a user feeds the monster a “poop” class will be enqueued. After the queue reaches its capacity or after a certain amount time elapses we will dequeue one poop object to populate the pane area with poop.
Different food will have different probability of poop appearing in the panel. Since it logically makes sense to “poop” the first thing you ate first, we are using the first-in-first-out nature of the queue to determine the appearance of poop in the GUI.

- Decision Tree for all the different User Options: We wanted to use the decision tree to prompt the user to interact with the monsters. This decision tree would only be for Teens and Adults (babies can only be loved). The decision trees will be Feed/Love/Water/Clean. The Feed option then has multiple foods for the monster (sustenance/treat) and those depend on the monster types. Responses to this decision tree update the vitality status of the monsters (and it will update internal variables within in the class) and determine how and when they level up.

###### Classes and Actions:
1. Monster class: abstract. Gives the base options (feed, status, life, die) for the all of the tamagotchi monsters
   - Instance variables: String name, vitality(int to keep track of how alive the monster is) , alive (boolean value to determine whether the monster is alive or not), decisionTree (since depending on the status or type, the decision tree will have different options),
   - Methods:feed(), updateStatus(), setName(User input String), predicate method dead() to determine the monster’s survival status and set the alive value to false
   
2. Extends Monster: 
   - Baby class: young tamagotchi, needs more love (int value)
     - Its decisionTree will include:  love()
     - Level Up(): when the love status reaches a certain number, this method will be invoked to return a teen class, and replaces the baby class in the storage
   - Teen class: needs more food, more food options (bread, meat, acorn, treat: candy, chocolate)
     - Variables: love (int), certain skills (int), etc, Poop queue (to keep track of the food’s poop input it received)
     - Its decisionTree will include: feed(), love(), water(), clean()
     - The feed branch will have different types of food class that updates different variables of the teen class, which will later be used to determine what adult it will grow up as 
     - LevelUp(): when certain variables in teen class reaches a certain number, this method will be invoked to return a type of an adult class 
   - Adult class: adult tamagotchi, needs less food/water => return String message (talks to you, return a random string from a collection of messages)
     - decisionTree(): talk(); feed() (a special kind of food depending on the monster type)
     - Type1: Lake Waban (Loch Ness) monster
     - Type2: Fat Squirrel monster (feed acorn to get this)
     - Type3: Wellesley Whale
     - Type 4: sbog frog And other monsters….

3. Food Class 
   - Variables: String name, Double poopProbability
   - Methods: poop( enqueue probability of pooping  to the poop queue), updateVitatlity() and updateSkill() will add numbers to certain variables of the teen monster class (use setters)

4. Home: Driver class that calls all the method and keeps track 
   - Instance variables:, LinkedList of Tamagotchi objects
   - Calls decisionTree() on tamagotchi instances

5. GUI class
   - Time tracker: The user needs to constantly care (feed, clean, love, water) the monster within a set amount of time. Unless, the vitality decreases and may lead to the death of the monster.
