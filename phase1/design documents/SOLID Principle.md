## Introduction
Our group mostly follows the SOLID design principle. They help a lot on decreasing the independecies between different part of program thus our teammate can
change one are without intepreting others. Also，they make our program easier to understand,maintain and extend. We will talk about what we do good or bad with
five solid principles in our program. Some future expectation are also included.
## Five SOLID Principles
*  Open-Closed Principle 
This principle has two of key part idea.
1).Open for extension, that need us to make extension always available when we want to make some new changes. A particular example is the builder design pattern we
use in our Controller class. We design a builder class such that we can always make new operation about the controller without modifying the original class. We make
many new game modes by this class.
2).Closed for modification, it means that once we set our source code, we should not change it. This idea is rather hard to implement but we have tried our best.
We always try to avoid the code we have already finished. While this may remain some of the dead code in our program. In future we will try to use these codes.
*  Liskov Substitution Principle **
This principle request us to ensure the functionality of child class should be the same as parent class. In our program we do not use a lot of inheritance, the only
one may be the Card class and its subclass NumberCard and FunctionCard. They all represent the card entity and will not scope the parent class. Actually we have 
thinked of making a subclass of player which is computer, while we give up this idea since it violates the Liskov Substitution Principle.
*  Interface Segregation Principle **
This princple asks the class to not implement the interface which has the unnecessary method in it. We have cosidered about this when we wirte the data reader class.
Then we decide to segregate the User Data and the Card Data. Although they are the similar class, but some of the methods must be unnecessary once we combine them.
*  Dependency Inversion Principle **
This principle is somewhat a way to decouplt the software. When one class have to depend on other class in the code, the best way is not to implement but to add an
interface between them. To make the UI class interact with the EachRound class in the controller level, we make a CardDisplayer interface which implement by UI but
depended by controller class. Then both of two methods will be more flexible to change.
*  Single Responsibility Principle **
This principle mains one class should have only one reason to change. Honestly we do not follow this principle very well. In PlayerManager and DeckManager we have
try to eliminate the dependecy within the same layer. While in the controller class it is still a problem for us. We have thinked several soultions about it but
they are not satisfying. Maybe we need some time to fix with it.
## Conclusion
It may cost a lot of time for our group to try to follow with the solid principles, while we find the reward is well worth it. We can always easily extend, maintain
and test our program. Also, these principles may not cover all the design issue in our project, but it is enough in most of cases.
