## Major Design Decisions We've Made
* **Reduce the size of controller** We realize that our original controller is too complex and would lead to greater problem for future implementation. 
Thus, We decided to reduce complexity of the controller - mainly in three aspects. Two of our members focused on this part.
    * extract codes and form external methods
    * put base level code into use case level
    * split classes to store methods with similarity
* **Choice of Log in System** As our game serve for players, we decided to create a login system to make it more like a "game platform". This also helps us to setup 
the user database. Two od our members focused on this part.
* **Function Card and external txt** By the end of Phase 0, we only realized the interaction between number cards - basic settings are not fully implemented. Thus, in the first week of phase 1, 
all of us worked together and added function cards to out deck. Moreover, we enabled txt reading so that we can modify deck settings by changing the external txt file then let programs 
read txt files and initialize cards according to info given.
* **UI Design** During the reading week, two of our members focused on developing graphical user interface - we consider this necessary since operating by typing in java terminal is 
not user-friendly at all. We want to make our game have a easier control. Till the end of phase1, we developed a basic GUI, and we decide to further polish it at Phase 2.
* **Design pattern** Our group dicussed which pattern and how we would implement those patterns. We take serious discussion and tried on various aspects. Then, we picked the ones 
that can really make changes and improve code structure then take a closer look at how to make them fit better. We've made multiple decisions throughout the course 
of the latter half of phase 1.
