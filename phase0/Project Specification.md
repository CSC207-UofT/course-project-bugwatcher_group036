In one sentence, we want to write a java project that **simulate Board-Game "UNO".** 

The Board-Game "UNO" is basically a card game. Each game contains 2 or more players. The cards have two types: The card with a number and a card with a function. Most of the cards has a certain color with while a few function cards do not have a certain color. When the game starts, every player will randomly receive 7 cards. The first player could be randomly chosen to play the first card or depends on some algorithms. After the first player randomly play a card, each player in the later rounds could only play a card that has the same color, or number, or function as the last card played by the previous player, except that the last card is some special function card. If a player do not have any valid card, he/she needs to draw a card from the deck. When a player has only one card, he/she must say "UNO!" to tell others that he/she has only one card, otherwise draw an extra card if others notice. When a player plays all his/her own hand cards, he/she becomes the winner.

Starting from implementing the UNO with basic setting and person-to-person on one computer, which is what we've accomplished throughout phase 0, 
we will gradually add more modes and settings to the game.

Our directions are mainly on these *three aspects*:
* **Optimizing basic settings**, including refining program structure and developing graphical User Interface to make it more user-friendly.
* **Implementing more rules**, like playing specific card would trigger special change, and adding extra function card into the deck.
* **Developing more game mode**, like UNO AI and player-to-computer mode, where player can choose number of computers. Moreover, we may develope remote game-mode (In the case that we have extra time).
