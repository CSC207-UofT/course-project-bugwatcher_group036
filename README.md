## How to Download and Play the Game

* Download the program by clicking the **Code** button on upper right corner. You can either download by using the http address by git or directly download the zip document and then unzip it.
* Using java IDE equipped with JDK to open the program, and locate **GameStarterTerminal** at **src/main/java** (Our development environment is JDK11)
* Run the main program, and then you can see the login interface & play UNO game freely

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
![image](https://user-images.githubusercontent.com/90352316/145323740-72202909-e532-43c0-81ab-a55b4d1c8c28.png)


Source of the uno card picture: https://github.com/bennuttall/uno/tree/master/images
## 3 Future Aspiration  

Our directions are mainly on these *three aspects*:
* **Optimizing online game play**, Players can use different computers and play together online
* **Implementing more rules**, like playing specific card would trigger special change, and adding extra function card into the deck.
* **Optimize code structure** With complexity of code increases, we will foucus on SOLID principles and clean architecture to make our codes more concise and effecitve with less potential bug.
