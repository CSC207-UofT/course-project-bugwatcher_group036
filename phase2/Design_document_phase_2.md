## Specificaiton:
### 1 Brief Introduction of Our Project

Our java project aim at **simulating the Board-Game "UNO".** 

The Board-Game "UNO" is basically a card game. Each game contains 2 or more players. There are two different types of cards: the number card and function card. Each number card contains a number with a color and a function card contains a function and color. Each player will randomly receive 7 cards at the start of the game. Player that moves first in the game will choose a card from his/her own player's hand and play one of the card. The remaining players will then take turns to play a card from the player own player's hand. Each player can only play a card in a turn from its own hand with the play card has the same color, number, or is a function black card as the last card played by the previous player. Each player will draw a card from the card deck if there is no card available to play in the round. The last card play from a player needs to be a number card and cannot be a function card. When a player have no more cards in the player's hand, the player will become the winner of the game.

![This is an example](https://i.insider.com/5cd062dcf067174d3f7c4d72?width=1000&format=jpeg&auto=webp)

Our team developed two game modes: PVP and PVE. In PVP mode, you can play UNO together with up to 5 firends. In case you are playing alone, you can choose PVE mode and play with 1-4 computer players. Also, as we developed the login system, our program will count user statistics and transform them into relative level in exp system.

![Differnt modes and statistics](https://user-images.githubusercontent.com/56620120/144762755-0c277922-f1f4-494d-839c-d63a92b42a55.png)

### 2 What We've Done So Far (phase0 - phase2)
In phase 0, we determined the overall strucutre and realized the basic running of the game that only has number card with four colors.

Through phase 1, We accomplished great implementations on basic setting to enable function card play, implemented two design patterns, and created GUI to make it more user-friendly.

In phase 2, we focused on mainly three aspects and construct a comprehensive platform for UNO playing:
* **Aspect of Code Restructure** Our project structure in phase 1 contained several unnecessary designs that hindered furture expansion in GUI and login interaction. Thus, our team restructured the code to make it more efficient and concise. 
*  **Aspect of Login System and Interaction with gameRun** 
   * We added user database that allows new user to register and then login normally for further gamePlay.
   * We added user statistics collector and exp system that updates in each round, where user can access these information everytime before game starts.
   * We added "play again" option after each game play, and players can choose new game mode as they want.
* **Aspect of GUI** We developed a GUI and added necessary information all inside the GUI:
![image](https://user-images.githubusercontent.com/56620120/144762978-f1bd6935-1eb5-4a95-a9bb-e4ac7b3210ac.png)

### 3 Future Aspiration  

Our directions are mainly on these *three aspects*:
* **Optimizing online game play**, Players can use different computers and play together online
* **Implementing more rules**, like playing specific card would trigger special change, and adding extra function card into the deck.
* **Optimize code structure** With complexity of code increases, we will foucus on SOLID principles and clean architecture to make our codes more concise and effecitve with less potential bug.



## UML Diagram:
![Untitled Diagram](https://user-images.githubusercontent.com/90352316/144772448-6b24fefa-1310-4110-855e-c588ce49cc6d.jpg)


## Major Design Decisions We've Made:


## Clean Architecture:

The code structure of our project generally follows the clean architecture, we will introduce it from three aspects. Still, we'll perfect our design in the following phase 2

- We clearly seperate java classes into four layers: entity, use case, interface adapters and FrameWorks & drivers. All of them are clearly classified by packages
- We eliminate skip-layer method use and paid special attention to follow the layer by layer rule mentioned in clean architecture.
- For those complicated structure, like the places where we need to use base level methods, we utilize dependency inversion pattern to solve the conflict.
- Due to the complexicity of the structure in phase 1, we reconstruct the whole project to make it clearer and simpler.

1. In phase 2, since we reconstruct the project, we implemented the entities by CardHolder, Deck and Status. CardHolder contains ArrayLists of strings that represents the holder of cards (especially represents the hand cards of a player). Each string is a card. Deck stores two card decks, used and unused, by two ArrayLists of strings and can shuffle these cards. Status contains the status variables that are used in each round of the game.

   Compared to phase 1, the entities were Card, Deck, Player, with Card has two sub-classes which are NumberCard and FunctionCard. Card is similar to the real card in life that stores basic properties of a card. Deck is the thing that could store the cards and shuffle cards. Player is the player that involves in each game. We realize that we do not need to create Card class to represents card, instead using string to display a card is sufficient. Meanwhile, since the only work of Player is to hold ArrayList of cards, we use CardHolders instead in phase 2.

2. In the upper layer, the use cases are CardChecker, EachRound, GameBoard, GameCardHolders, GameDeck, GameRequest, GameResponse, GameRunner, GameStatus, IPresenter, IRequest, Readfile. IPresenter, IRequest, Readfile are interfaces and others are java classes. GameCardHolders contains an ArrayList of CardHolders where each CardHolder is the hand cards of a player. GameCardHolder, GameDeck and GameStatus are in charge of calling the methods in CardHolder, Deck and Status repectively. CardChecker check the function of the last card played and compares cards that the player wants to play. EachRound contains the logical process in each round of the project. GameBoard contains the operations follows the logical process. GameRequest receives information from the controller and GameResponse sends information to the presenter. GameRunner starts and runs the game. IPresenter and Readfile is used for dependency inversion and IRequest used for following Single Responsiblility.

   In phase 1, the use cases are DeckManager, PlayerManager, BasicOperations, Status, GameBoard. DeckManager is in charge of manage Card and Deck. PlayerManager is in charge of Player. Status stores the variables used in upper classes when running the game. BasicOperations stores the operations that each game someone plays a function card. GameBoard represents the circumstance on the table or screen, such as contains the card that the last player played.

3. In the controller layer, there are classes Presenter, Controller, Gateway. Controller receives instructions from the GUI, Presenter sends information to the GUI, Gateway read the cards data from txt files to create cards for the game.

   In phase 1, the structure is much more complex. There are classes Controller, ControllerBuilder, EachRound, Dealer, Cardreadfile, DeckManagerData, PlayerManagerData, BasicOperationsData, StatusData, GameBoardData. ControllerBuilder helps to build the complex structure of Controller, which is a design pattern. Dealer is in charge of deal and manage the cards in each round of the game. Cardreafile is the Gateway. DeckManagerData, PlayerManagerData, BasicOperationsData, StatusData, GameBoardData these are the classes that could call the methods in corresponding classes in lower layers. The left are the same as the classes in phase 2.

4. The top of the project is the UI that takes the responsibility to let players to operate.

In addition, we have another module of login system. 

1. The entities are User, UserList, UserReadWriter, UserStatistics, ReadWriter. User stands for each user. UserList is the list of users. UserReadWriter reads and writes the user information, which implements the interface ReadWriter. UserStatistics contains the statistics or information of each user. 

   In phase 1, we only have the user class for entity.

2. The use case level contains LoginUseCase and LoginInputBoundary. LoginUseCase uses the methods from the entity and contains the operations or process methods for login and register and LoginUseCase implements the interface LoginInputBoundary that contains methods login(), register(), getUsers(). 

   In phase 1, class in use case level is only UserData which operates the User and UserMap that provides the place to store the information of User such as the password in the current system.

3. In the controller level, there is only a LoginController class that receives the information from the client.

   But in phase 1, there are Controller and UserDataReader. The Controller is used for executing the system while UserDataReader is in charge of write in new users’ information in the database and read from the database.

4. On the top is the GUI for the login system.

Finally the two GUI are combined together to display the whole board game.



## SOLID Design Principle:


## Packaging Strategies:


## Design Patterns We Implemented:
We will list the design patterns we used and what we will further implement for phase 2:
### Patterns We Have Used
* **Iterator**: We use this pattern to iterate over handcards each player has directly, and players the playerManager has without accessing the inner arrayList. 
This reduce the occurance of bug and simplify iterating process for upper layer.
* **Builder**: We apply this pattern to standarize the initialization of game controller, which has multiple variables, each of them need to be constructed specifically. 
By using builder pattern, we simplify controller's complexity and make the process more extensible. 
### Patterns We may Implement in Future:
* **Facade**: We plan to use facade to make our code more conform to SOLID principle, and also make the structure more organized instead of being stranded together.
* **Strategy** We are thinking to implement a strategy design pattern for the computer game mode such as easy and hard version where the easy will just played the first card in the computer's hand and the hard will choose any avaliable card that he can play.


## Progress Report

