## Specificaiton:
## 1 Brief Introduction of Our Project

Our java project aim at **simulating the Board-Game "UNO".** 

The Board-Game "UNO" is basically a card game. Each game contains 2 or more players. There are two different types of cards: the number card and function card. Each number card contains a number with a color and a function card contains a function and color. Each player will randomly receive 7 cards at the start of the game. Player that moves first in the game will choose a card from his/her own player's hand and play one of the card. The remaining players will then take turns to play a card from the player own player's hand. Each player can only play a card in a turn from its own hand with the play card has the same color, number, or is a function black card as the last card played by the previous player. Each player will draw a card from the card deck if there is no card available to play in the round. The last card play from a player needs to be a number card and cannot be a function card. When a player have no more cards in the player's hand, the player will become the winner of the game.

![This is an example](https://i.insider.com/5cd062dcf067174d3f7c4d72?width=1000&format=jpeg&auto=webp)
Source of the picture: https://i.insider.com/5cd062dcf067174d3f7c4d72?width=1000&format=jpeg&auto=webp

Our team developed two game modes: PVP and PVE. In PVP mode, you can play UNO together with up to 5 firends. In case you are playing alone, you can choose PVE mode and play with 1-4 computer players. Also, as we developed the login system, our program will count user statistics and transform them into relative level in exp system.

![Differnt modes and statistics](https://user-images.githubusercontent.com/56620120/144762755-0c277922-f1f4-494d-839c-d63a92b42a55.png)

## 2 What We've Done So Far (phase0 - phase2)
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
![image](https://user-images.githubusercontent.com/90352316/145322606-e37a153d-508a-4644-a31e-d08ec1ff8315.png)


Source of the uno card picture: https://github.com/bennuttall/uno/tree/master/images
## 3 Future Aspiration  

Our directions are mainly on these *three aspects*:
* **Optimizing online game play**, Players can use different computers and play together online
* **Implementing more rules**, like playing specific card would trigger special change, and adding extra function card into the deck.
* **Optimize code structure** With complexity of code increases, we will foucus on SOLID principles and clean architecture to make our codes more concise and effecitve with less potential bug.



## UML Diagram for phase 2:
![Untitled Diagram](https://user-images.githubusercontent.com/90352316/145121942-20ef2712-5de6-4b85-8a4d-3378e848a22e.jpg)



## Major Design Decisions We've Made:

* Restructure: Use the Presenter, move class from controller to UseCase, Change the structure of our data(card). This decision helps us to implement future functions with ease, and reduce the coupling between classes in the same level.
* Use serializable to construct log in system, and store player-specific statistics inside database. By doing so, we've brought real funtionality to login system instead of simply letting user to log in.
* The GUI: Added bgm and more info chunks to inform other players' card count, history of card playing, and last card's image. These extensions make the game much more attractive and user friendly.
* Design Pattern: we implemented iterator pattern inside cardHolder class to interate over cards easily, and as we have two modes avaliable for players, we use the builder pattern to standardize the initialization also enable further addition of special game mode.


## Clean Architecture:

The code structure of our project generally follows the clean architecture, we will introduce it from three aspects. Still, we'll perfect our design in the following phase 2

- We clearly seperate java classes into four layers: entity, use case, interface adapters and FrameWorks & drivers. All of them are clearly classified by packages
- We eliminate skip-layer method use and paid special attention to follow the layer by layer rule mentioned in clean architecture.
- For those complicated structure, like the places where we need to use base level methods, we utilize dependency inversion pattern to solve the conflict.
- Due to the complexicity of the structure in phase 1, we reconstruct the whole project to make it clearer and simpler.

1. In phase 2, since we reconstruct the project, we implemented the entities by CardHolder, Deck and Status. CardHolder contains ArrayLists of strings that represents the holder of cards (especially represents the hand cards of a player). Each string is a card. Deck stores two card decks, used and unused, by two ArrayLists of strings and can shuffle these cards. Status contains the status variables that are used in each round of the game.

   In phase 1, the entities were Card, Deck, Player, with Card has two sub-classes which are NumberCard and FunctionCard. Card is similar to the real card in life that stores basic properties of a card. Deck is the thing that could store the cards and shuffle cards. Player is the player that involves in each game. We realize that we do not need to create Card class to represents card, instead using string to display a card is sufficient. Meanwhile, since the only work of Player is to hold ArrayList of cards, we use CardHolders instead in phase 2.

2. In the upper layer, the use cases are CardChecker, EachRound, GameBoard, GameCardHolders, GameDeck, GameRequest, GameResponse, GameRunner, GameStatus, IPresenter, IRequest, Readfile. IPresenter, IRequest, Readfile are interfaces and others are java classes. GameCardHolders contains an ArrayList of CardHolders where each CardHolder is the hand cards of a player. GameCardHolder, GameDeck and GameStatus are in charge of calling the methods in CardHolder, Deck and Status repectively. CardChecker check the function of the last card played and compares cards that the player wants to play. EachRound contains the logical process in each round of the project. GameBoard contains the operations follows the logical process. GameRequest receives information from the controller and GameResponse sends information to the presenter. GameRunner starts and runs the game. IPresenter and Readfile is used for dependency inversion and IRequest used for following Single Responsiblility.

   In phase 1, the use cases are DeckManager, PlayerManager, BasicOperations, Status, GameBoard. DeckManager is in charge of manage Card and Deck. PlayerManager is in charge of Player. Status stores the variables used in upper classes when running the game. BasicOperations stores the operations that each game someone plays a function card. GameBoard represents the circumstance on the table or screen, such as contains the card that the last player played.

3. In the controller layer, there are classes Presenter, Controller, Gateway. Controller receives instructions from the GUI to construct the game, including use cases and entities, Presenter sends information to the GUI, Gateway read the cards data from txt files to create cards for the game.

   In phase 1, the structure is much more complex. There are classes Controller, ControllerBuilder, EachRound, Dealer, Cardreadfile, DeckManagerData, PlayerManagerData, BasicOperationsData, StatusData, GameBoardData. ControllerBuilder helps to build the complex structure of Controller, which is a design pattern. Dealer is in charge of deal and manage the cards in each round of the game. Cardreafile is the Gateway. DeckManagerData, PlayerManagerData, BasicOperationsData, StatusData, GameBoardData these are the classes that could call the methods in corresponding classes in lower layers. The left are the same as the classes in phase 2.

4. The top of the project is the UI that takes the responsibility to let players to operate. There are two builder classes, PVEBuilder and PVPBuilder, and an builder Interface, ModeBuilder in the UI layer in order to build the complex structure of UI.

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
Our group mostly follows the SOLID design principle. They help a lot on decreasing the independecies between different part of program thus our teammate can change one are without intepreting others. Also，they make our program easier to understand,maintain and extend. We will talk about what we do good or bad with five solid principles in our program. Some future expectation are also included.
### Open-Closed Principle 
This principle has two of key part idea. 1).Open for extension, that need us to make extension always available when we want to make some new changes. A particular example is the builder design pattern we use in our Controller class. We design a builder class such that we can always make new operation about the controller without modifying the original class. We make many new game modes by this class. 2).Closed for modification, it means that once we set our source code, we should not change it. This idea is rather hard to implement but we have tried our best. We always try to avoid the code we have already finished. While this may remain some of the dead code in our program. In future we will try to use these codes.
### Liskov Substitution Principle 
This principle request us to ensure the functionality of child class should be the same as parent class. In our program we do not use a lot of inheritance, the only one may be the Card class and its subclass NumberCard and FunctionCard. They all represent the card entity and will not scope the parent class. Actually we have thinked of making a subclass of player which is computer, while we give up this idea since it violates the Liskov Substitution Principle.
### Interface Segregation Principle
This princple asks the class to not implement the interface which has the unnecessary method in it. We have cosidered about this when we wirte the data reader class. Then we decide to segregate the User Data and the Card Data. Although they are the similar class, but some of the methods must be unnecessary once we combine them.
### Dependency Inversion Principle 
This principle is somewhat a way to decouplt the software. When one class have to depend on other class in the code, the best way is not to implement but to add an interface between them. To make the UI class interact with the EachRound class in the controller level, we make a CardDisplayer interface which implement by UI but depended by controller class. Then both of two methods will be more flexible to change.
### Single Responsibility Principle 
This principle mains one class should have only one reason to change. Honestly we do not follow this principle very well. In PlayerManager and DeckManager we have try to eliminate the dependecy within the same layer. While in the controller class it is still a problem for us. We have thinked several soultions about it but they are not satisfying. Maybe we need some time to fix with it.
### Conclusion
It may cost a lot of time for our group to try to follow with the solid principles, while we find the reward is well worth it. We can always easily extend, maintain and test our program. We create many new structures and functions in phase 2 while we found they are easy to implement. Nevertheless, these principles may not cover all the design issue in our project, but it is enough in most of cases.


## Packaging Strategies:
Our project can be divided into two parts according to its function.(Uno part and login part). We did not spend so mush time on deciding our packaging strategy(Layers strategy). From the very beginning we noticed that Clean Architecture is a very significant part of this course, so we choose layers strategy for both parts whose layout of files is straight forward. It makes us easier to implement the Clean Architecture and checkout the correctness afterwards.

## Design Patterns We Implemented:
### Implemented:
1. CardHolder Class - Iterator
2. PVE/PVP Frame Class - Builder
### Change From Phase 1:
1. Due to Restructure from Command UI to GUI and PVE mode
2. All Design Pattern in Phase 1 was redesigned
3. Builder Class build PVP/PVE Frame
### Possible implementation in Future:
Memento - Undo Game State
Strategy - PVE Mode

We originally have Iterator for our Card Class and Player Class. Due to the Restructure in Phase 2, we decided to reconsider all our design pattern whether it is useful or not. Furthermore, We changed our Card Class as previously it was an parent class with two child class, function card and normal card. As now our card all transformed into CardHolder Class which can be consider as the player's hand in real life. We would want to iterate through the player's hand to get different card, so we use the iterator design pattern. Previously, We implemented builder class but the feedback in Phase 1 indicated that our builder class did not correctly implement . In the new builder class, we build different frame and depending on the user, we will choose to build different frame. As our restructure in Phase 2 was big from Command UI to GUI, we did not implement new design pattern. We do want to implement Memento for reversing back to different game state or restoring from a pause game. We do also want to implement strategy design pattern for the different game mode in PVE.


## Progress Report

### Purpose of our project
In one sentence, we want to write a java project that **simulate Board-Game "UNO"**, and add a few innovative features into it.

The Board-Game "UNO" is basically a card game. Each game contains 2 or more players. There are two different types of cards: the number card and function card. Each number card contains a number with a color and a function card contains a function and color. Each player will randomly receive 7 cards at the start of the game. Player that moves first in the game will randomly chose a card from his/her own player's hand and play one of the card. The remaining players will then take turns to play a card from the player own player's hand. Each player can only play a card in a turn from its own hand with the play card has the same color, number, or is a function black card as the last card played by the previous player. Each player will draw a card from the card deck if there is no card available to play in the round. The last card play from a player needs to be a number card and cannot be a function card. When a player has only one card, the player must say "UNO!" to inform others that there is only one card in the player's hand, otherwise the player needs to draw an extra card if other player notice. When a player have no more cards in the player's hand, the player will become the winner of the game.

We want to demonstrate our understanding of clean architecture and design pattern, by implementing them in this project. We also intend to enter the field of GUI design, client platform, login system, URL,
+-and AI training alongside developing this project. More importantly, we want to create a personalized game that involves our own innovative features and rules, and we aim for perfect.

### Future Plan
**Optimizing online game mode**
* We may create an online platform and relative client for players to connect and play this UNO game online, instead of local multiplayer mode like the current one.

**Make the game more customizable**
* We may make this game more customizable by adding features to manually change the background, bgm, profile picture and etc. We may add character and skill system as well to make this UNO game more personalized.

**Create android app**
* We may aim to create an android app for this game, broaden its current platform restriction from pc only to pc and android mobile.
### Reflection
In phase 0, we determined the overall structure and realized the basic run of the game that only has number card with four colors.

Through phase 1, We accomplished great implementations on basic setting to enable function card play, implemented two design patterns, and created GUI to make it more user-friendly.

In phase 2, we focused on mainly three aspects and construct a comprehensive platform for UNO playing:
* **Aspect of Code Restructure** Our project structure in phase 1 contained several unnecessary designs that hindered future expansion in GUI and login interaction. Thus, our team restructured the code to make it more efficient and concise. It went out to be a thorough overhaul that, we totally denied the previous UML structure and based on Clean architecture and SOLID principles, we re-framed the whole code body to the current stage.
* **Aspect of Login System and Interaction with gameRun**
    * We added user database that allows new user to register and then login normally for further gamePlay. We made this feature functions that, now our game can record and store user login intel to local ser. documents, and is able to perform what a login system ought to do. 
    * We added user statistics collector and exp system that updates in each round, where user can access this information everytime before game starts. We inputted exp accumulator and win/lose rate statistics feature to one extra frame that can be called from the main page. The scarcity is we are unable to call it in-game at the moment, but could be implemented in the future for definite.
    * We added "play again" option after each game play, and players can choose new game mode as they want (currently just pve and pvp).
* **Aspect of GUI** We developed a GUI and added necessary information all inside the GUI. And we added background music for this game.

### Contribution

#### Jason Zhu (zhuyuezx - yuezhexuan.zhu@mail.utoronto.ca)
* Wrote specification.md for phase 2
* Restructured the whole project with regard on rigorous clean Architecture
* Added executable GUI features
* Contributed writing UseCase test classes

#### Wise Chua (wisechua - wise.chua@mail.utoronto.ca)
* Completed GUI for the whole game after re-framing
* Transformed from command line UI to GUI
* Implemented all GUI frames
* Wrote testcases for UseCase and Entity

#### Allen Chen (Ez4Allen - challen.chen@mail.utoronto.ca)
* Checked Login classes
* Wrote tests for UseClass and Login
* Made minor debugs for Controller and UseCase class

#### Ben Wang (BenWXY - xyben.wang@mail.utoronto.ca)
* Revised the structure to follow SOLID and Clean Architecture
* Created UML diagram for phase 2
* Made last check to SOLID and Clean Architecture

#### York Chen (zhumengzhiren - york.chen@mail.utoronto.ca)
* Completed GUI for login system, created relevant frames
* Wrote docstring for controller, entity and login system classes
* Wrote tests for entity and controller classes

#### Yao Zhou (zhouyao9 - yaoo.zhou@mail.utoronto.ca)
* Wrote the accessibility report

#### Eli Gao (ElizaZoldyck - eliza.gao@mail.utoronto.ca)
* Wrote progress report.md for phase 2
* Marginally contributed in writing UseCase test
* Made minor debugs for the UseCase class structure
* Modified BGM function and sound related features in UI class

