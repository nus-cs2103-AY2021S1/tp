---
layout: page
title: Goh Tian Yong's Project Portfolio Page
---

## Project: WishfulShrinking

### Overview
WishfulShrinking is a desktop diet manager refactor from an application called [AddressBook-Level 3(AB3)](https://github.com/nus-cs2103-AY2021S1/tp).
 It is an application used to helps on **managing the user's on-hand ingredients, organise personal recipes and track user's diet**.
 Wishful Shrinking facilitates a **healthier diet** in three main ways: 

1. Provide a **source of healthy**, **customizable recipes** 
1. **Recommend recipes** to improve ease of home cooking 
1. **Track daily food and calorie** intake<br><br>

Wishful Shrinking targets **office workers** who tend to neglect healthy eating. Office workers are also more
 familiar with desktop applications and typing and correspondingly, Wishful Shrinking optimized for fast and efficient
 typers as it uses a Command Line Interface (CLI) and Graphical User Interface (GUI) created with JavaFX.
 Wishful Shrinking is available for the Linux, Unix, Windows and Mac OS operating systems.
 It is written in Java, and has about 10 kLoC. 
 
 
Given below are my contributions to the project:


* **New Feature 1**: Added the `Eat Recipe` command. (Pull Requests [\#51](https://github.com/AY2021S1-CS2103T-W10-2/tp/pull/51))
  * What it does: allows the user "eat" the recipe that is listed in the recipes' collection.
  * Justification: This feature improves the product as it is the base feature of Wishful Shrinking to help user to keep track of their daily consumption.
  * Highlights: This feature work similar to `delete` feature. Instead of removing,
   it intakes a positive index as an argument to indicate the specific recipe in the Recipe List and add the recipe into Consumption List.
  * Credits: AddressBook Level 3 `delete` feature.
<br>
* **New Feature 2**: Added `List Consumption` feature. (Pull Requests [\#84](https://github.com/AY2021S1-CS2103T-W10-2/tp/pull/84), [\#87](https://github.com/AY2021S1-CS2103T-W10-2/tp/pull/87))
  * What it does: allows the user list the recipes in the Consumption List.
  * Justification: This feature improves the product as it sum up and show the user their calories intake besides from just listing all the recipe ate. 
  * Credits: AddressBook Level 3 `list` feature. 
<br>
* **New Feature 3**: Added `Clear Recipe` feature. (Pull Requests [\#170](https://github.com/AY2021S1-CS2103T-W10-2/tp/pull/170))
  * What it does: allows the user to clear all the recipe in Recipes List.
  * Justification: This feature improves the product because it allows user to start a new Recipe List with a single command instead of repeatedly using `deleteR` feature.
<br>
* **New Feature 4**: Added `Clear Ingredient` feature. (Pull Requests [\#170](https://github.com/AY2021S1-CS2103T-W10-2/tp/pull/170))
  * What it does: allows the user to clear all the ingredient in Fridge (Ingredient List).
  * Justification: This feature improves the product because it allows user to start a new "Fridge" with a single command instead of repeatedly using `deleteF` feature.
<br>
* **New Feature 5**: Added `Clear Consumption` feature. (Pull Requests [\#170](https://github.com/AY2021S1-CS2103T-W10-2/tp/pull/170))
  * What it does: allows the user to clear their daily consumption in Consumption List.
  * Justification: This feature improves the product because it allows user to start a new Consumption List with a single command instead of repeatedly using `deleteC` feature.
<br>
* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=tianyong-goh&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)
<br>
* **Project management**:
  * Produce shadowJar file for test and release `v1.2`.
  * Setting up group meeting for discussion
  * Remind team members to approve members' PRs
<br>
* **Enhancements to existing features**:
  * Refactored `Search Recipe` feature from `find` feature in *AddressBook Level 3* (Pull Requests [\#37](https://github.com/AY2021S1-CS2103T-W10-2/tp/pull/37))
  * Added Calories field into Recipe for `Eat Recipe` feature (Pull Requests [\#80](https://github.com/AY2021S1-CS2103T-W10-2/tp/pull/80))
  * Updated the GUI to list out consumption (Pull Requests [\#88](https://github.com/AY2021S1-CS2103T-W10-2/tp/pull/88))
  * Refactored *Sample Test* in AddressBook Level 3 to fit the current Wishful Shrinking. (Pull Requests [\#111](https://github.com/AY2021S1-CS2103T-W10-2/tp/pull/111))
  * Resolved most the tests that failed when we commented out while refactoring feature from *AddressBook Level 3*. (Pull Requests [\#111](https://github.com/AY2021S1-CS2103T-W10-2/tp/pull/111))
  * Added tests for `Search Recipe` feature and `Recommand` feature. (Pull Requests [\#116](https://github.com/AY2021S1-CS2103T-W10-2/tp/pull/116))
<br>
* **Documentation**:
  * [User Guide](https://ay2021s1-cs2103t-w10-2.github.io/tp/UserGuide.html):
    * Transfer the user guide's draft from google document to UserGuide.md. (Pull Requests [\#31](https://github.com/AY2021S1-CS2103T-W10-2/tp/pull/31))
    * Updated the `addRecipe` (Pull Requests [\#117](https://github.com/AY2021S1-CS2103T-W10-2/tp/pull/117))
    
    <div style="page-break-after: always;"></div>
    
    * Added documentation for the features `eatR`, `calories`, `clearR`, `clearF` and `clearC`  (Pull Requests [\#51](https://github.com/AY2021S1-CS2103T-W10-2/tp/pull/51), [\#117](https://github.com/AY2021S1-CS2103T-W10-2/tp/pull/117), [\#170](https://github.com/AY2021S1-CS2103T-W10-2/tp/pull/170))
    * Added break page for pdf converting purpose. (Pull Requests [#294](https://github.com/AY2021S1-CS2103T-W10-2/tp/pull/294))
  * [Developer Guide](https://ay2021s1-cs2103t-w10-2.github.io/tp/DeveloperGuide.html):
    * Added implementation details and sequence diagrams of `eatRecipe`, `listConsumption`,  `selectRecipe` and `clear` feature. (Pull Requests [\#117](https://github.com/AY2021S1-CS2103T-W10-2/tp/pull/117))
    * Combined the implementation of the similar feature e.g. combine `listConsumption`, `listRecipe` and `listIngredient` to `list`. (Pull Requests [\#181](https://github.com/AY2021S1-CS2103T-W10-2/tp/pull/181))
    * Added the table of content in Developer Guide. (Pull Requests [#245](https://github.com/AY2021S1-CS2103T-W10-2/tp/pull/245))
    * Added activity diagram for `clear` and `eat Recipe` features. (Pull Requests [#261](https://github.com/AY2021S1-CS2103T-W10-2/tp/pull/261))
    * Updated Class Diagrams(Model Component). (Pull Requests [#252](https://github.com/AY2021S1-CS2103T-W10-2/tp/pull/252))
    * Added break page for pdf converting purpose. (Pull Requests [#294](https://github.com/AY2021S1-CS2103T-W10-2/tp/pull/294))
<br>
* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#172](https://github.com/AY2021S1-CS2103T-W10-2/tp/pull/172), [\#178](https://github.com/AY2021S1-CS2103T-W10-2/tp/pull/178), [\#24](https://github.com/AY2021S1-CS2103T-W10-2/tp/pull/24)
  * Reported bugs and suggestions for other teams in the class (examples: [1](https://github.com/TianYong-Goh/ped/issues/3), [2](https://github.com/TianYong-Goh/ped/issues/5), [3](https://github.com/TianYong-Goh/ped/issues/7))
