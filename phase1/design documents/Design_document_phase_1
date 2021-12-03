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

