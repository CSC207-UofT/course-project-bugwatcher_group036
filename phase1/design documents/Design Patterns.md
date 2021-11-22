## Design Patterns We Implemented
We will list the design patterns we used and what we will further implement for phase 2:
### Patterns We Have Used
* **Iterator**: We use this pattern to iterate over handcards each player has directly, and players the playerManager has without accessing the inner arrayList. 
This reduce the occurance of bug and simplify iterating process for upper layer.
* **Builder**: We apply this pattern to standarize the initialization of game controller, which has multiple variables, each of them need to be constructed specifically. 
By using builder pattern, we simplify controller's complexity and make the process more extensible. 
* **Dependency Injection** To ensure the clean architecture of UI class, we use dependency injection pattern that create interface to let lower class that operates on UI class 
operating on its related interface instead of the upper layer UI.
### Patterns We may Implement in Phase 2
* **Facade**: We plan to use facade to make our code more conform to SOLID principle, and also make the structure more organized instead of being stranded together.
* **Strategy** We are thinking to implement a strategy design pattern for the computer game mode such as easy and hard version where the easy will just played the first card in the computer's hand and the hard will choose any avaliable card that he can play.
### Others Methods Implementing in Phase 2
* **Serialization** We are going to implement a login system with user database at phase 2. Thus, we'll use serialization to encrypt the user data and access them.
