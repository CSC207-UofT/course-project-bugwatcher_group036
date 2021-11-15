## Purpose of our project
In one sentence, we want to write a java project that **simulate Board-Game "UNO"**, and add a few innovative features into it.

The Board-Game "UNO" is basically a card game. Each game contains 2 or more players. There are two different types of cards: the number card and function card. Each number card contains a number with a color and a function card contains a function and color. Each player will randomly receive 7 cards at the start of the game. Player that moves first in the game will randomly chose a card from his/her own player's hand and play one of the card. The remaining players will then take turns to play a card from the player own player's hand. Each player can only play a card in a turn from its own hand with the play card has the same color, number, or is a function black card as the last card played by the previous player. Each player will draw a card from the card deck if there is no card available to play in the round. The last card play from a player needs to be a number card and cannot be a function card. When a player has only one card, the player must say "UNO!" to inform others that there is only one card in the player's hand, otherwise the player needs to draw an extra card if other player notice. When a player have no more cards in the player's hand, the player will become the winner of the game.

We want to demonstrate our understanding of clean architecture and design pattern, by implementing them in this project. We also intend to enter the field of GUI design, client platform, login system, URL,
+-and AI training alongside developing this project. More importantly, we want to create a personalized game that involves our own innovative features and rules, and we aim for perfect.

## Future TODO

Our directions are mainly on these *three aspects*:
* **Optimizing basic settings**, we will debug logical, style and fatal errors and implement login system and user info system using serialization. We may also dive into the field of client platform and try to implement online features. Furthermore, we may consider constructing android version as well as html web compatible with this game. Our GUI will definitely be optimized by adding more features, adjusting layouts, adding draw-shuffle-play card animation, and embellish with backgrounds and color blocks.
* **Implementing more rules**, like playing specific card would trigger special change, and adding extra function cards into the deck. We may also add playable characters as a pool of options for players, each with specific unique active/passive skills. And we may construct in-app purchasing system. We will add AI with different difficulty levels in the future, and it's solid that we are writing tutorial stage implemented with GUI.
* **Optimize code structure** With complexity of code increases, we will foucus on SOLID principles and clean architecture to make our codes more concise and effecitve with less potential bug. We will sniff for code smells and make relevant modifications in the future. Also, we will try to implement more design patterns into the code framework. 

## Reflection

## Our questions to TA

## Contribution

### Jason Zhu (zhuyuezx - yuezhexuan.zhu@mail.utoronto.ca)
* Wrote specification.md for phase 1
* Restructured Controller class
* wrote tests for PlayerManger and Player
* Modified PVE functionality for UNO with debugs

### Wise Chua (wisechua - wise.chua@mail.utoronto.ca)
* Contributed building and debugging / modification on GUI

### Allen Chen (Ez4Allen - challen.chen@mail.utoronto.ca)
* Contributed building the login system body
* Wrote docstring for Entity and UseCase classes
* Wrote tests for DeckManager

### Ben Wang (BenWXY - xyben.wang@mail.utoronto.ca)
* Modified Controller class, wrote several more classes under Controller branch with various design pattern applied
* Constructed PVE functionality for UNO with AI applied
* Wrote tests for CardReadFile and ReadFile classes

### York Chen (zhumengzhiren - york.chen@mail.utoronto.ca)
* Contributed building the login system entity
* Constructed the LoginController

### Yao Zhou (zhuyuezx - yaoo.zhou@mail.utoronto.ca)
* Contributed building the whole GUI framework, and modified several places in Controller class for compatibility

### Eli Gao (ElizaZoldyck - eliza.gao@mail.utoronto.ca)
* Wrote progress report.md for phase 1
* Wrote CardTest FunctionCardTest class
* Made minor debugs for the PVE functionality, modified EachRound and GameStarter class
* Marginally contributed in designing GUI