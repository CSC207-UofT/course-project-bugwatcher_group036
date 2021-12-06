## Specificaiton:



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

2. In the upper layer, the use cases are CardChecker, EachRound, GameBoard, GameCardHolders, GameDeck, GameRequest, GameResponse, GameRunner, GameStatus, IPresenter, IRequest, Readfile. IPresenter, IRequest, Readfile are interfaces and others are java classes. GameCardHolders, GameDeck, GameStatus are in charge of calling the methods in CardHolder, Deck and Status repectively. CardChecker check the function of the last card played and compares cards that the player wants to play. EachRound contains the logical process in each round of the project. GameBoard contains the operations follows the logical process. GameRequest receives information from the controller and GameResponse sends information to the presenter. GameRunner starts and runs the game. IPresenter and Readfile is used for dependency inversion and IRequest used for following Single Responsiblility.

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



## SOLID design principle:


## packaging strategies:


## Design Patterns We Implemented:


## Progress Report

