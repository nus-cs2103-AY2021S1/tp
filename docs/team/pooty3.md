---
layout: page
title: Aloysius's Project Portfolio Page
---

## Project: HairStyleX

HairstyleX helps managers for budding neighborhood hair salons to manage clients, hairdressers, and appointments. It is optimized for CLI users so that frequent tasks can be done faster by typing in commands. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

### Summary of Contributions

#### Code contributed

Access my [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=pooty3) to view the code that I have contributed.

#### Enhancements implemented
        
* **Create shortcut features**: Designed and created `CommandShortcutSet`, `CommandShortcut` and `CommandWord` classes to be used in **HairStyleX** (Pull request [\#161](https://github.com/AY2021S1-CS2103T-T15-1/tp/pull/161))
    * What it does: Allows user to create their own custom defined shortcuts in **HairStyleX**
    * Justification:  This makes the user experience much more pleasant as now they can add shortcut to otherwise very long commands.
    * Highlights:
        * The CommandShortcutSet uses the singleton pattern as there is only one global instance throughout the application
        * CommandWords are enumerations as there are only a set values for them

        <div style="page-break-after: always;"></div>
* **Refactor Appointment, Hairdresser and Client classes**: Made the 3 classes implement a base `Entity` class and `Client` and `Hairdresser` to inherit from `Person` (Pull request [\#99](https://github.com/AY2021S1-CS2103T-T15-1/tp/pull/99))
    * What it does: Make Appointment, Hairdresser and Client inherit from Entity class. Also, UniqueEntityList served a similar purpose as well.
    * Justification: Originally, there are a lot of duplicate code within the 3 classes, and their associated UniqueList. As such, I made them inherit from a base class to cut down on duplicate code.
    This makes it more maintainable and easy to extend in the future.
    * Highlights: 
        * This is a major refactor as it affect the main model component of the program. As such, careful consideration and research had to be done before doing the refactor.
        * Not only was the 3 classes refactored, but many of it's related classes such as EntityPanels and EntityUniqueList was refactored to heavily reduce on duplicate code.
  
* **Refactor Command classes**: Refactor CRUD commands to make it extend from the base CRUD commands. (Pull request [\#136](https://github.com/AY2021S1-CS2103T-T15-1/tp/pull/136))
    * What it does: Make CRUD commands extend from the base CRUD commands, as well as its corresponding CommandParser classes.
    * Justification: Following the refactoring of the Appointment, Hairdresser and Client classes, it allowed the related CRUD commands to follow a similar pattern. This heavily reduces the duplicate code.
    * Highlights: 
        * This is another major refactor as the CRUD commands are central to the logic of the application. As such, similar to above, careful consideration must be carried out.
* **Testing**: Create tests for shortcuts and its related classes to increase coverage. (Pull request [\#262](https://github.com/AY2021S1-CS2103T-T15-1/tp/pull/262))

#### Contributions to the User Guide

* Provided a detailed guide supplemented with screenshots and examples for [shortcut](https://ay2021s1-cs2103t-t15-1.github.io/tp/UserGuide.html#45-shortcut-commands). [\#184](https://github.com/AY2021S1-CS2103T-T15-1/tp/pull/184)
    <div style="page-break-after: always;"></div>
#### Contributions to the Developer Guide

* Added implementation details of the [Shortcut](https://ay2021s1-cs2103t-t15-1.github.io/tp/DeveloperGuide.html#command-shortcut-feature) classes (Pull request [\#165](https://github.com/AY2021S1-CS2103T-T15-1/tp/pull/165))
* Recreated existing diagrams and updated implementation details for [ModelClassDiagram](https://ay2021s1-cs2103t-t15-1.github.io/tp/DeveloperGuide.html#model-component) (Pull request [\#165](https://github.com/AY2021S1-CS2103T-T15-1/tp/pull/165))
* Add [use cases](https://ay2021s1-cs2103t-t15-1.github.io/tp/DeveloperGuide.html#use-cases-1) (Pull request [\#257](https://github.com/AY2021S1-CS2103T-T15-1/tp/pull/257))

#### Contributions to the team-based tasks

* Came up with the general idea and architecture for the models in the application.
* Came up with the idea to implement a print feature

#### Review/mentoring contributions

* PRs reviewed: [\#290](https://github.com/AY2021S1-CS2103T-T15-1/tp/pull/290)

#### Contributions beyond the project team

* Reported [6 bugs](https://github.com/pooty3/ped/issues) in group T17-1's tP during mock PE
