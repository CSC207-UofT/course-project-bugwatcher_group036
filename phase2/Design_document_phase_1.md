## 1 Brief Introduction of Our Project

We want to write a java project that **simulate Board-Game "UNO".** 

The Board-Game "UNO" is basically a card game. Each game contains 2 or more players. There are two different types of cards: the number card and function card. Each number card contains a number with a color and a function card contains a function and color. Each player will randomly receive 7 cards at the start of the game. Player that moves first in the game will choose a card from his/her own player's hand and play one of the card. The remaining players will then take turns to play a card from the player own player's hand. Each player can only play a card in a turn from its own hand with the play card has the same color, number, or is a function black card as the last card played by the previous player. Each player will draw a card from the card deck if there is no card available to play in the round. The last card play from a player needs to be a number card and cannot be a function card. When a player has only one card, the player must say "UNO!" to inform others that there is only one card in the player's hand, otherwise the player needs to draw an extra card if other player notice. When a player have no more cards in the player's hand, the player will become the winner of the game.

There are 5 different color card: Yellow, Blue, Green, Red, Black

There are 5 different function card: Reverse, Skip, Switch Color, Plus Two Cards, Plus Four Cards

![This is an example](https://i.insider.com/5cd062dcf067174d3f7c4d72?width=1000&format=jpeg&auto=webp)

## 2 Our Current Progress & What We've Done So Far
In phase 0, we determined the overall strucutre and realized the basic running of the game that only has number card with four colors.
After phase 1, We accomplished great implementations on three aspects:
* **Aspect of Basic Setting** We added function card to the deck and enabled function card interactions. Then, we enabled fiexible player-count that allows player to decide. We also implemented PVE mode - one player and multiple computer players, with hidden card details. Finally, we added a txt file and reader class to store deck informations in txt so that we can devise customized decks without altering the code.
*  **Aspect of design pattern** 
   * We added iterator pattern to easier access handcards in player and all players in sequence.
   * We added builder pattern to standardize the initialization process and reduce controller complexity.
   * We added facade pattern to maintain the SOLID principle and improve extensiblility.
* **Aspect of GUI** We developed a GUI, which can be further perfected, and we'll do that in phase 2. Here is our current GUI:
![139bb29f73dbd099cf2b4aeae5d7d88](https://user-images.githubusercontent.com/56620120/141606380-23530b3b-21fb-45ce-b96d-4233d28aee6d.png)

## 3 Future Aspiration & What We Will Implement in Phase 2 

Our directions are mainly on these *three aspects*:
* **Optimizing basic settings**, we will debug logical errors and implement login system and user info system using serialization.
* **Implementing more rules**, like playing specific card would trigger special change, and adding extra function card into the deck.
* **Optimize code structure** With complexity of code increases, we will foucus on SOLID principles and clean architecture to make our codes more concise and effecitve with less potential bug.



## UML Diagram

![uml drawio](https://user-images.githubusercontent.com/90352316/144681601-03ae515f-3b0c-40a5-9b1a-b2dfbb82f68b.png)




## Major Design Decisions We've Made
* **Reduce the size of controller** We realize that our original controller is too complex and would lead to greater problem for future implementation. 
  Thus, We decided to reduce complexity of the controller - mainly in three aspects. Two of our members focused on this part.
    * extract codes and form external methods
    * put base level code into use case level
    * split classes to store methods with similarity
* **Choice of Log in System** As our game serve for players, we decided to create a login system to make it more like a "game platform". This also helps us to setup 
the user database. Two od our members focused on this part.
* **Function Card and external txt** By the end of Phase 0, we only realized the interaction between number cards - basic settings are not fully implemented. Thus, in the first week of phase 1, 
all of us worked together and added function cards to out deck. Moreover, we enabled txt reading so that we can modify deck settings by changing the external txt file then let programs 
read txt files and initialize cards according to info given.
* **UI Design** During the reading week, two of our members focused on developing graphical user interface - we consider this necessary since operating by typing in java terminal is 
not user-friendly at all. We want to make our game have a easier control. Till the end of phase1, we developed a basic GUI, and we decide to further polish it at Phase 2.
* **Design pattern** Our group dicussed which pattern and how we would implement those patterns. We take serious discussion and tried on various aspects. Then, we picked the ones 
that can really make changes and improve code structure then take a closer look at how to make them fit better. We've made multiple decisions throughout the course 
of the latter half of phase 1.



## Clean Architecture

The code structure of our project generally follows the clean architecture, we will introduce it from three aspects. Still, we'll perfect our design in the following phase 2
* We clearly seperate java classes into four layers: entity, use case, interface adapters and FrameWorks & drivers. All of them are clearly classified by packages
* We eliminate skip-layer method use and paid special attention to follow the layer by layer rule mentioned in clean architecture.
* For those complicated structure, like the places where we need to use base level methods, we utilize dependency injection pattern to solve the conflict or create facade pattern.

Start from the construction of CRC model, we build our project in the way of clean architecture. Since our project is a card board game, the entities are Card, Deck, Player, with Card has two sub-classes which are NumberCard and FunctionCard. Card is similar to the real card in life that stores basic properties of a card. Deck is the thing that could store the cards and shuffle cards. Player is the player that involves in each game.

In the upper layer, the use cases are DeckManager, PlayerManager, BasicOperations, Status, GameBoard. DeckManager is in charge of manage Card and Deck. PlayerManager is in charge of Player. Status stores the variables used in upper classes when running the game. BasicOperations stores the operations that each game someone plays a function card. GameBoard represents the circumstance on the table or screen, such as contains the card that the last player played. 

In the controller layer, there are classes Controller, ControllerBuilder, EachRound, Dealer, Cardreadfile, DeckManagerData, PlayerManagerData, BasicOperationsData, StatusData, GameBoardData. The Controller is in charge of receiving the instructions from the users to start to run the game. ControllerBuilder helps to build the complex structure of Controller, which is a design pattern. EachRound contains the methods that push the process of each round of game. Dealer is in charge of deal and manage the cards in each round of the game. Cardreafile could read the txt file that contains the information of the database of cards and create these cards. DeckManagerData, PlayerManagerData, BasicOperationsData, StatusData, GameBoardData these are the classes that could call the methods in corresponding classes in lower layers. 

The top of the project is the UI that takes the responsibility to let players to operate. 

In addition, we have another module of login system. The entity is User which stands for each user. The use case level contains UserData which operates the User and UserMap that provides the place to store the information of User such as the password in the current system. In the top layer are the Controller and UserDataReader. The Controller is used for executing the system while UserDataReader is in charge of write in new users’ information in the database and read from the database.



## SOLID design principle
Our group mostly follows the SOLID design principle. They help a lot on decreasing the independecies between different part of program thus our teammate can
change one are without intepreting others. Also，they make our program easier to understand,maintain and extend. We will talk about what we do good or bad with
five solid principles in our program. Some future expectation are also included.

## Five SOLID Principles
*  Open-Closed Principle 
This principle has two of key part idea.
1).Open for extension, that need us to make extension always available when we want to make some new changes. A particular example is the builder design pattern we
use in our Controller class. We design a builder class such that we can always make new operation about the controller without modifying the original class. We make
many new game modes by this class.
2).Closed for modification, it means that once we set our source code, we should not change it. This idea is rather hard to implement but we have tried our best.
We always try to avoid the code we have already finished. While this may remain some of the dead code in our program. In future we will try to use these codes.
*  Liskov Substitution Principle **
This principle request us to ensure the functionality of child class should be the same as parent class. In our program we do not use a lot of inheritance, the only
one may be the Card class and its subclass NumberCard and FunctionCard. They all represent the card entity and will not scope the parent class. Actually we have 
thinked of making a subclass of player which is computer, while we give up this idea since it violates the Liskov Substitution Principle.
*  Interface Segregation Principle **
This princple asks the class to not implement the interface which has the unnecessary method in it. We have cosidered about this when we wirte the data reader class.
Then we decide to segregate the User Data and the Card Data. Although they are the similar class, but some of the methods must be unnecessary once we combine them.
*  Dependency Inversion Principle **
This principle is somewhat a way to decouplt the software. When one class have to depend on other class in the code, the best way is not to implement but to add an
interface between them. To make the UI class interact with the EachRound class in the controller level, we make a CardDisplayer interface which implement by UI but
depended by controller class. Then both of two methods will be more flexible to change.
*  Single Responsibility Principle **
This principle mains one class should have only one reason to change. Honestly we do not follow this principle very well. In PlayerManager and DeckManager we have
try to eliminate the dependecy within the same layer. While in the controller class it is still a problem for us. We have thinked several soultions about it but
they are not satisfying. Maybe we need some time to fix with it.
## Conclusion
It may cost a lot of time for our group to try to follow with the solid principles, while we find the reward is well worth it. We can always easily extend, maintain
and test our program. Also, these principles may not cover all the design issue in our project, but it is enough in most of cases.



## packaging strategies
Our project can be divided into two parts according to its function.(Uno part and login part).
We did not spend so mush time on deciding our packaging strategy(Layers strategy).
From the very beginning we noticed that Clean Architecture is a very significant part of this course,
so we choose layers strategy for both parts whose layout of files is straight forward.
It makes us easier to implement the Clean Architecture and checkout the correctness afterwards.



## Design Patterns We Implemented

We will list the design patterns we used and what we will further implement for phase 2:
### Patterns We Have Used
* **Iterator**: We use this pattern to iterate over handcards each player has directly, and players the playerManager has without accessing the inner arrayList. 
This reduce the occurance of bug and simplify iterating process for upper layer.
* **Builder**: We apply this pattern to standarize the initialization of game controller, which has multiple variables, each of them need to be constructed specifically. 
By using builder pattern, we simplify controller's complexity and make the process more extensible. 
* **Dependency Injection** To ensure the clean architecture of UI class, we use dependency injection pattern that create interface to let lower class that operates on UI class 
operating on its related interface instead of the upper layer UI.
### Patterns We may Implement in Phase 2
* **Facade**: We plan to use facade to make our code more conform to SOLID principle, and also make the structure more organized instead of being stranded together.
* **Strategy** We are thinking to implement a strategy design pattern for the computer game mode such as easy and hard version where the easy will just played the first card in the computer's hand and the hard will choose any avaliable card that he can play.
### Others Methods Implementing in Phase 2
* **Serialization** We are going to implement a login system with user database at phase 2. Thus, we'll use serialization to encrypt the user data and access them.



## Purpose of our project
In one sentence, we want to write a java project that **simulate Board-Game "UNO"**, and add a few innovative features into it.

The Board-Game "UNO" is basically a card game. Each game contains 2 or more players. There are two different types of cards: the number card and function card. Each number card contains a number with a color and a function card contains a function and color. Each player will randomly receive 7 cards at the start of the game. Player that moves first in the game will randomly chose a card from his/her own player's hand and play one of the card. The remaining players will then take turns to play a card from the player own player's hand. Each player can only play a card in a turn from its own hand with the play card has the same color, number, or is a function black card as the last card played by the previous player. Each player will draw a card from the card deck if there is no card available to play in the round. The last card play from a player needs to be a number card and cannot be a function card. When a player has only one card, the player must say "UNO!" to inform others that there is only one card in the player's hand, otherwise the player needs to draw an extra card if other player notice. When a player have no more cards in the player's hand, the player will become the winner of the game.

We want to demonstrate our understanding of clean architecture and design pattern, by implementing them in this project. We also intend to enter the field of GUI design, client platform, login system, URL,
+-and AI training alongside developing this project. More importantly, we want to create a personalized game that involves our own innovative features and rules, and we aim for perfect.

## Future TODO

Our directions are mainly on these *three aspects*:
* **Optimizing basic settings**
  * we will debug logical, style and fatal errors and implement login system and user info system using serialization. We may also dive into the field of client platform and try to implement online features. Furthermore, we may consider constructing android version as well as html web compatible with this game. Our GUI will definitely be optimized by adding more features, adjusting layouts, adding draw-shuffle-play card animation, and embellish with backgrounds and color blocks.
* **Implementing more rules**
  * like playing specific card would trigger special change, and adding extra function cards into the deck. We may also add playable characters as a pool of options for players, each with specific unique active/passive skills. And we may construct in-app purchasing system. We will add AI with different difficulty levels in the future, and it's solid that we are writing tutorial stage implemented with GUI.
* **Optimize code structure** 
  * With complexity of code increases, we will focus on SOLID principles and clean architecture to make our codes more concise and effecitve with less potential bug. We will sniff for code smells and make relevant modifications in the future. Also, we will try to implement more design patterns into the code framework. 

## Reflection

In phase 0, we determined the overall structure and realized the basic running of the game that only has number card with four colors.
After phase 1, We accomplished great implementations on three aspects:
* **Aspect of Basic Setting** 
    * We added function card to the deck and enabled function card interactions. Then, we enabled flexible player-count that allows player to decide. We also implemented PVE mode - one player and multiple computer players, with hidden card details. Finally, we added a txt file and reader class to store deck information in txt so that we can devise customized decks without altering the code. 
    * To reflect, we made great progresses on modifying basic setting and main body of the game UNO, we utilized Serialization, and we basically re-wrote the Controller system, and compared to Phase0, the game now owns almost all features as the original. 
    * But we do need to pay attention to the code smell, clean architecture and SOLID principles. We still need to balance the size of Controller packages with other branches. Meanwhile, we will need to add more innovative personalized features to the game.
* **Aspect of design pattern**
    * We added iterator pattern to easier access HandCards in player and all players in sequence.
    * We added builder pattern to standardize the initialization process and reduce controller complexity.
    * We added facade pattern to maintain the SOLID principle and improve extensibility.
* **Aspect of GUI** 
    * We developed a GUI, which can be further perfected, and we'll do that in phase 2.
    * We have so far made this GUI at an extreme low quality of visual perception without loss on the game features. We will focus more on the GUI by Phase 2 by adding login page, background, draw-n-play animations, etc.

## Our questions to TA

* Are we still on the right track?
* To what extent should we put effort on the GUI?
* Is it preferable to have such a huge Controller folder? Shall we switch and re-allocate the content in Controller to other places?
* Is there a better alternative to build GUI?
* Shall we implement gson/json to generate cards instead of a huge deck class with several sub-classes?

## Contribution

### Jason Zhu (zhuyuezx - yuezhexuan.zhu@mail.utoronto.ca)
* Wrote specification.md for phase 1
* Restructured Controller class
* wrote tests for PlayerManger and Player
* Modified PVE functionality for UNO with debugs

### Wise Chua (wisechua - wise.chua@mail.utoronto.ca)
* Contributed building and debugging / modification on GUI
* Changed command line to GUI and implemented updates of GUI for each played hand
* Implemented ReadFile class for reading card file

### Allen Chen (Ez4Allen - challen.chen@mail.utoronto.ca)
* Contributed building the login system body
* Wrote docstring for Entity and UseCase classes
* Wrote tests for DeckManager

### Ben Wang (BenWXY - xyben.wang@mail.utoronto.ca)
* Modified Controller class, wrote several more classes under Controller branch with various design pattern applied
* Constructed PVE functionality for UNO with Serialization applied
* Wrote tests for CardReadFile and ReadFile classes
* Checked and fixed SOLID and clean architecture
* Made UML diagram

### York Chen (zhumengzhiren - york.chen@mail.utoronto.ca)
* Contributed building the login system entity
* Constructed the LoginUI System interact with file
* wrote tests for Entity

### Yao Zhou (zhuyuezx - yaoo.zhou@mail.utoronto.ca)
* Contributed building the whole GUI framework, and modified several places in Controller class for compatibility

### Eli Gao (ElizaZoldyck - eliza.gao@mail.utoronto.ca)
* Wrote progress report.md for phase 1
* Wrote CardTest FunctionCardTest class
* Made minor debugs for the PVE functionality, modified EachRound and GameStarter class
* Marginally contributed in designing GUI
