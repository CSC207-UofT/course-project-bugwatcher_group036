## 1 Brief Introduction of our Project

We want to write a java project that **simulate Board-Game "UNO".** 

The Board-Game "UNO" is basically a card game. Each game contains 2 or more players. There are two different types of cards: the number card and function card. Each number card contains a number with a color and a function card contains a function and color. Each player will randomly receive 7 cards at the start of the game. Player that moves first in the game will choose a card from his/her own player's hand and play one of the card. The remaining players will then take turns to play a card from the player own player's hand. Each player can only play a card in a turn from its own hand with the play card has the same color, number, or is a function black card as the last card played by the previous player. Each player will draw a card from the card deck if there is no card available to play in the round. The last card play from a player needs to be a number card and cannot be a function card. When a player has only one card, the player must say "UNO!" to inform others that there is only one card in the player's hand, otherwise the player needs to draw an extra card if other player notice. When a player have no more cards in the player's hand, the player will become the winner of the game.

There are 5 different color card: Yellow, Blue, Green, Red, Black

There are 5 different function card: Reverse, Skip, Switch Color, Plus Two Cards, Plus Four Cards

![This is an example](https://i.insider.com/5cd062dcf067174d3f7c4d72?width=1000&format=jpeg&auto=webp)

## 2 Our Current Progress

As we implmented basic UNO setting that has only number cards and can be played normally. During phase 1, We accomplished great implementations on several aspects:
* **Aspect of Basic Setting** We added function card to the deck and enabled function card interactions. Then, we enabled fiexible player-count that allows player to decide. We implemented PVE mode - one player and multiple computer players, with hidden card details. Finally, we added a txt file and reader class to store deck informations in txt. In this case, we can devise customized decks without changing the code.
*  **Aspect of design pattern** We added iterator pattern to easier access handcards in player and all players in sequence
We added builder pattern to standardize the initialization process and reduce controller complexity.
We added facade pattern to maintain the SOLID principle and improve extensiblility.
* **Aspect of GUI** We developed a GUI, which can be further perfected, and we'll do that in phase 2. Here is our developed GUI:
![139bb29f73dbd099cf2b4aeae5d7d88](https://user-images.githubusercontent.com/56620120/141606380-23530b3b-21fb-45ce-b96d-4233d28aee6d.png)

## 3 Future Aspiration & What We Will Implement in Phase 2 

Our directions are mainly on these *three aspects*:
* **Optimizing basic settings**, we will debug logical errors and implement login system and user info system using serialization.
* **Implementing more rules**, like playing specific card would trigger special change, and adding extra function card into the deck.
* **Optimize code structure** With complexity of code increases, we will foucus on SOLID principles and clean architecture to make our codes more concise and effecitve with less potential bug.
