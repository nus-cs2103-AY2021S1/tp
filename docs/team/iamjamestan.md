---
layout: page
title: Tan Yu Li, James's Project Portfolio Page
---

## Project: HairStyleX

HairstyleX helps managers for budding neighborhood hair salons to manage clients, hairdressers, and appointments. It is optimized for CLI users so that frequent tasks can be done faster by typing in commands. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 20 kLoC.

Given below are my contributions to the project.

### Summary of Contributions

#### Code contributed

Access my [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=iamjamestan&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other) to view the code that I have contributed.

#### Enhancements implemented
        
* **Create Client and Hairdresser class**: Designed and created `Client` and `Hairdresser` classes to be used in **HairStyleX** (Pull request [\#79](https://github.com/AY2021S1-CS2103T-T15-1/tp/pull/79))
    * What it does: Defines the blueprint and rules of creating clients and hairdressers in **HairStyleX**
    * Justification: These classes are fundamental to the entire project, since they define how clients and hairdressers are to be stored, and what attributes they should have.
    * Highlights:
        * This was the first change made from the original AddressBook-3, so there was a great importance of these classes as the work of my other team members depended heavily on these.
        * Careful consideration of good OOP design was made when creating these classes. Since `Client`s and `Hairdresser`s are both `Person`s, the `Person` class was made to be an abstract class, with `Client` and `Hairdresser` inheriting from `Person`. `Person` encapsulated fields which will be essential to both entities, while `Client`s and `Hairdresser`s encapsulated other fields which would be important for their own classes.
        * The methods used to define equality was also carefully considered, since they will be used to prevent creation of duplicate entities in **HairStyleX**.
        * In addition, all fields were carefully designed to include regex tests for the most appropriate strings to be accepted.
        
* **Create CRUD command for Appointments**: Designed and created `Add`, `Edit`, `Delete` and `List` commands for `Appointments` (Pull request [\#117](https://github.com/AY2021S1-CS2103T-T15-1/tp/pull/117), [\#119](https://github.com/AY2021S1-CS2103T-T15-1/tp/pull/119))
    * What it does: Encapsulates the logic that makes changes to the `Model` and `Storage` classes whenever appointments are manipulated in **HairStyleX**.
    * Justification: These commands are essential to ensuring that appointments are stored and saved correctly in **HairStyleX** whenever a user manipulates them.
    * Highlights:
        * The creation of these commands entailed creating and designing many more appointment-related classes and methods in the `model` and `storage` packages to ensure that the data will be handled in the correct manner.
        * This was the first version of `Appointment` commands that allowed us to complete a minimum viable product by v1.2. As such, it was a very basic implementation that did not account for appointment clashes.
        * Nonetheless, this basic implementation of appointment commands allowed my fellow teammates [Nicholas](https://ay2021s1-cs2103t-t15-1.github.io/tp/team/nicktohzyu.html), [YiFan](https://ay2021s1-cs2103t-t15-1.github.io/tp/team/theyifan.html) and [Yao Yuan](https://ay2021s1-cs2103t-t15-1.github.io/tp/team/ya0-yuan.html) to implement more advanced features that aids users in scheduling and manipulating appointments.
        
* **Refactor Index to Id**: Changed all user operations to work on `Id` instead of the default `Index` (Pull request [\#150](https://github.com/AY2021S1-CS2103T-T15-1/tp/pull/150))
    * What it does: enforces the user to identify `Client`s, `Hairdresser`s and `Appointment`s by their unique `Id` instead of the default list index that is shown in *AddressBook-3*.
    * Justification: Initially, we intended to follow the original AddressBook-3, where each `Person` would be identified by its shown index in the list shown in the GUI. However, we came to the realisation this approach would confuse the user during this scenario:
        * When the user searches for an entity with filters, the filtered list would show indices that are not corresponding to the original list. As such, the user might erroneously key in the wrong index for his/her selected entity. To reduce potential confusion for users, we decided that each entity should be identified by a unique ID within its own class, and that this should be displayed to the user and be the primary way of identifying entities for the purposes of editing, deleting, etc.
    * Highlights: 
        * This refactor affected all existing commands that previously used `Index`. Furthermore, since every entity now has an `Id` field, it required changes to the existing `Storage` classes in order for each entity to record its own `Id`, and it also required a global counter for the respective `Id`s so that the next `Id` can be generated without collisions.
        * In order for each entity to have a unique `Id` upon instantiation, a final `IdCounter` class was created to keep track of each entity's current `Id` and increment it whenever an `Id` is generated. This class is a singleton, and only one instance can exist at any point in time.
        
* **Testing**: Create tests for client related commands to increase coverage from 48% to 56% (Pull request [\#280](https://github.com/AY2021S1-CS2103T-T15-1/tp/pull/280))

#### Contributions to the User Guide

* Provided a detailed guide supplemented with screenshots and examples for [general commands](https://ay2021s1-cs2103t-t15-1.github.io/tp/UserGuide.html#41-general-commands) such as `help`, `clear`, `exit` and `print`. (Pull request [\#265](https://github.com/AY2021S1-CS2103T-T15-1/tp/pull/265))
* Contributed to [Introduction](https://ay2021s1-cs2103t-t15-1.github.io/tp/UserGuide.html#1-introduction), [Quick Start](https://ay2021s1-cs2103t-t15-1.github.io/tp/UserGuide.html#2-quick-start), and [About](https://ay2021s1-cs2103t-t15-1.github.io/tp/UserGuide.html#3-about) sections to give an overview about **HairStyleX** and guide new users on how to interpret the User Guide. (Pull request [\#192](https://github.com/AY2021S1-CS2103T-T15-1/tp/pull/192))
* Added FAQ and Command Summary Sections to address common questions and give a quick overview of all command syntax. (Pull requests [\#192](https://github.com/AY2021S1-CS2103T-T15-1/tp/pull/192), [\#43](https://github.com/AY2021S1-CS2103T-T15-1/tp/pull/43))

#### Contributions to the Developer Guide

* Added implementation details of the [Id and IdCounter](https://ay2021s1-cs2103t-t15-1.github.io/tp/DeveloperGuide.html#id-and-id-counter) classes (Pull request [\#170](https://github.com/AY2021S1-CS2103T-T15-1/tp/pull/170))
* Recreated existing diagrams and updated implementation details for [Logic Component](https://ay2021s1-cs2103t-t15-1.github.io/tp/DeveloperGuide.html#logic-component) (Pull request [\#170](https://github.com/AY2021S1-CS2103T-T15-1/tp/pull/170))
* Update [product scope](https://ay2021s1-cs2103t-t15-1.github.io/tp/DeveloperGuide.html#product-scope) (Pull request [\#42](https://github.com/AY2021S1-CS2103T-T15-1/tp/pull/42))
* Add [use cases](https://ay2021s1-cs2103t-t15-1.github.io/tp/DeveloperGuide.html#use-cases-1) (Pull request [\#76](https://github.com/AY2021S1-CS2103T-T15-1/tp/pull/76))

#### Contributions to the team-based tasks

* Set up github organisation and repo
* Set up README section on github (Pull request [\#55](https://github.com/AY2021S1-CS2103T-T15-1/tp/pull/55))
* Managed and allocate tasks for v1.1
* Refactored all instances of `AddressBook` to `HairStyleX` (Pull request [\#255](https://github.com/AY2021S1-CS2103T-T15-1/tp/pull/255))
* Upload screenshots for v1.3 demo

#### Review/mentoring contributions

* PRs reviewed (with non-trivial review comments): [\#184](https://github.com/AY2021S1-CS2103T-T15-1/tp/pull/184)

#### Contributions beyond the project team

* Reported [6 bugs](https://github.com/iamjamestan/ped/issues) in group F13-4's tP during mock PE
