The code structure of our project generally follows the clean architecture, we will introduce it from three aspects. Still, we'll perfect our design in the following phase 2
* We clearly seperate java classes into four layers: entity, use case, interface adapters and FrameWorks & drivers. All of them are clearly classified by packages
* We eliminate skip-layer method use and paid special attention to follow the layer by layer rule mentioned in clean architecture.
* For those complicated structure, like the places where we need to use base level methods, we utilize dependency injection pattern to solve the conflict or create facade pattern.
