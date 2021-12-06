## Purpose of our project
In one sentence, we want to write a java project that **simulate Board-Game "UNO"**, and add a few innovative features into it.

The Board-Game "UNO" is basically a card game. Each game contains 2 or more players. There are two different types of cards: the number card and function card. Each number card contains a number with a color and a function card contains a function and color. Each player will randomly receive 7 cards at the start of the game. Player that moves first in the game will randomly chose a card from his/her own player's hand and play one of the card. The remaining players will then take turns to play a card from the player own player's hand. Each player can only play a card in a turn from its own hand with the play card has the same color, number, or is a function black card as the last card played by the previous player. Each player will draw a card from the card deck if there is no card available to play in the round. The last card play from a player needs to be a number card and cannot be a function card. When a player has only one card, the player must say "UNO!" to inform others that there is only one card in the player's hand, otherwise the player needs to draw an extra card if other player notice. When a player have no more cards in the player's hand, the player will become the winner of the game.

We want to demonstrate our understanding of clean architecture and design pattern, by implementing them in this project. We also intend to enter the field of GUI design, client platform, login system, URL,
+-and AI training alongside developing this project. More importantly, we want to create a personalized game that involves our own innovative features and rules, and we aim for perfect.

## Future Plan
**Optimizing online game mode**
* We may create an online platform and relative client for players to connect and play this UNO game online, instead of local multiplayer mode like the current one.

**Make the game more customizable**
* We may make this game more customizable by adding features to manually change the background, bgm, profile picture and etc. We may add character and skill system as well to make this UNO game more personalized.

**Create android app**
* We may aim to create an android app for this game, broaden its current platform restriction from pc only to pc and android mobile.
## Reflection
In phase 0, we determined the overall structure and realized the basic run of the game that only has number card with four colors.

Through phase 1, We accomplished great implementations on basic setting to enable function card play, implemented two design patterns, and created GUI to make it more user-friendly.

In phase 2, we focused on mainly three aspects and construct a comprehensive platform for UNO playing:
* **Aspect of Code Restructure** Our project structure in phase 1 contained several unnecessary designs that hindered future expansion in GUI and login interaction. Thus, our team restructured the code to make it more efficient and concise. It went out to be a thorough overhaul that, we totally denied the previous UML structure and based on Clean architecture and SOLID principles, we re-framed the whole code body to the current stage.
* **Aspect of Login System and Interaction with gameRun**
    * We added user database that allows new user to register and then login normally for further gamePlay. We made this feature functions that, now our game can record and store user login intel to local ser. documents, and is able to perform what a login system ought to do. 
    * We added user statistics collector and exp system that updates in each round, where user can access this information everytime before game starts. We inputted exp accumulator and win/lose rate statistics feature to one extra frame that can be called from the main page. The scarcity is we are unable to call it in-game at the moment, but could be implemented in the future for definite.
    * We added "play again" option after each game play, and players can choose new game mode as they want (currently just pve and pvp).
* **Aspect of GUI** We developed a GUI and added necessary information all inside the GUI. And we added background music for this game.

## Contribution

### Jason Zhu (zhuyuezx - yuezhexuan.zhu@mail.utoronto.ca)
* Wrote specification.md for phase 2
* Restructured the whole project with regard on rigorous clean Architecture
* Added executable GUI features
* Contributed writing UseCase test classes

### Wise Chua (wisechua - wise.chua@mail.utoronto.ca)
* Completed GUI for the whole game after re-framing
* Transformed from command line UI to GUI
* Implemented all GUI frames
* Wrote testcases for UseCase and Entity

### Allen Chen (Ez4Allen - challen.chen@mail.utoronto.ca)
* Checked Login classes
* Wrote tests for UseClass and Login
* Made minor debugs for Controller and UseCase class

### Ben Wang (BenWXY - xyben.wang@mail.utoronto.ca)
* Revised the structure to follow SOLID and Clean Architecture
* Created UML diagram for phase 2
* Made last check to SOLID and Clean Architecture

### York Chen (zhumengzhiren - york.chen@mail.utoronto.ca)
* Completed GUI for login system, created relevant frames
* Wrote docstring for controller, entity and login system classes
* Wrote tests for entity and controller classes

### Yao Zhou (zhouyao9 - yaoo.zhou@mail.utoronto.ca)
* Wrote the accessibility report

### Eli Gao (ElizaZoldyck - eliza.gao@mail.utoronto.ca)
* Wrote progress report.md for phase 2
* Marginally contributed in writing UseCase test
* Made minor debugs for the UseCase class structure
* Modified BGM function and sound related features in UI class