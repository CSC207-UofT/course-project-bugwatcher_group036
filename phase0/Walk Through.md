* initialization \newline
The controller gets the number of players in this game, and initializes one playerUseCase and one deckUseCase. The playerUseCase will create corresponding players inside playerManager. The deckUseCase will create a new deck with all cards initialized. Then, all players will draw 7 cards from the deck. After all players have drawn the card, the UNO game will start with choosing a random player to start.
* check what we can play to play cards \newline
When a certain player meets his/her round, the controller will show the player’s hand cards and automatically select the cards into a list that the player can play. The player is required to choose a card from the list and play the card by typing the card’s id. If the player types a valid card, the card will be successfully played and put into the unused cards deck. the controller will update the last card record and turn the round to the player in the next position and do the same process.
* no card playable and draw card \newline
For each player’s turn, the playerUseCase will first check whether there are playable cards in hand-card, if there is no playable card. The player will automatically draw a card and then pass the turn. Then, the controller passes the turn to the next player. 