---
layout: page
title: Goh Tian Yong's Project Portfolio Page
---

## Project: WishfulShrinking

WishfulShrinking is a desktop diet manager refactor from an application called [AddressBook Level 3](https://github.com/nus-cs2103-AY2021S1/tp).
 It is an application used to helps on **managing the user's on-hand ingredients, organise personal recipes and track user's diet**.
 Wishful Shrinking facilitates a **healthier diet** in three main ways: 

1. Provide a **source of healthy, customizable recipes** 
1. **Recommend recipes** to improve ease of home cooking 
1. **Track daily food and calorie** intake<br><br>

Wishful Shrinking targets **office workers** who tend to discount healthy eating. Office workers are also more
 familiar with desktop applications and typing and correspondingly, Wishful Shrinking optimized for fast and efficient
 typers as it uses a Command Line Interface (CLI) and Graphical User Interface (GUI) created with JavaFX.
 Wishful Shrinking is available for the Linux, Unix, Windows and Mac OS operating systems.
 It is written in Java, and has about 10 kLoC. 
 

Given below are my contributions to the project.

* **New Feature**: Added the `Eat Recipe` command. (Pull Requests [\#51](https://github.com/AY2021S1-CS2103T-W10-2/tp/pull/51))
  * What it does: allows the user "eat" the recipe that is listed in the recipes' collection.
  * Justification: This feature improves the product as it is the base feature of Wishful Shrinking to help user to keep track of their daily consumption.
  * Highlights: This feature work similar to `delete` feature.
   It instead intakes a positive index as an argument to indicate the specific recipe.
   Get the recipe's data from Recipe List and then add the recipe's data to the Consumption List.
  * Credits: AddressBook Level 3 `delete` feature.

* **New Feature**: Added `List Consumption` feature. (Pull Requests [\#84](https://github.com/AY2021S1-CS2103T-W10-2/tp/pull/84), [\#87](https://github.com/AY2021S1-CS2103T-W10-2/tp/pull/87))
  * What it does: allows the user list the recipes in the Consumption List.
  * Justification: This feature improves the product as it sum up and show the user their calories intake besides from just listing all the recipe ate. 
  * Credits: AddressBook Level 3 `list` feature. 

* **New Feature**: Added `Clear Recipe` feature. (Pull Requests [\#170](https://github.com/AY2021S1-CS2103T-W10-2/tp/pull/170))
  * What it does: allows the user to clear all the recipe in Recipes List.
  * Justification: This feature improves the product because it allows user to start a new Recipe List with a single command instead of repeatedly using `deleteR` feature.
  
* **New Feature**: Added `Clear Ingredient` feature. (Pull Requests [\#170](https://github.com/AY2021S1-CS2103T-W10-2/tp/pull/170))
  * What it does: allows the user to clear all the ingredient in Fridge (Ingredient List).
  * Justification: This feature improves the product because it allows user to start a new "Fridge" with a single command instead of repeatedly using `deleteF` feature.

* **New Feature**: Added `Clear Consumption` feature. (Pull Requests [\#170](https://github.com/AY2021S1-CS2103T-W10-2/tp/pull/170))
  * What it does: allows the user to clear their daily consumption in Consumption List.
  * Justification: This feature improves the product because it allows user to start a new Consumption List with a single command instead of repeatedly using `deleteC` feature.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=tianyong-goh&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

* **Project management**:
  * Produce shadowJar file for test and release `v1.2`.
  * Setting up group meeting for discussion
  * Remind team members to approve members' PRs
  
* **Enhancements to existing features**:
  * Refactor `Search Recipe` feature from `find` feature in *AddressBook Level 3* (Pull Requests [\#37](https://github.com/AY2021S1-CS2103T-W10-2/tp/pull/37))
  * Added Calories field into Recipe for `Eat Recipe` feature (Pull Requests [\#80](https://github.com/AY2021S1-CS2103T-W10-2/tp/pull/80))
  * Updated the GUI to list out consumption (Pull Requests [\#88](https://github.com/AY2021S1-CS2103T-W10-2/tp/pull/88))
  * Refactor AddressBook Level 3 **Test Sample** to fit the current Wishful Shrinking. (Pull Requests [\#111](https://github.com/AY2021S1-CS2103T-W10-2/tp/pull/111))
  * Resolve most the tests that failed when we commented out while refactoring from *AddressBook Level 3*. (Pull Requests [\#111](https://github.com/AY2021S1-CS2103T-W10-2/tp/pull/111))
  * Added tests to `Search Recipe` feature and `Recommand` feature. (Pull Requests [\#116](https://github.com/AY2021S1-CS2103T-W10-2/tp/pull/116))

* **Documentation**:
  * [User Guide](https://ay2021s1-cs2103t-w10-2.github.io/tp/UserGuide.html):
    * Transfer the user guide's draft from google document to UserGuide.md. (Pull Requests [\#31](https://github.com/AY2021S1-CS2103T-W10-2/tp/pull/31))
    * Updated the `addRecipe` (Pull Requests [\#117](https://github.com/AY2021S1-CS2103T-W10-2/tp/pull/117))
    * Added documentation for the features `eatR`, `calories`, `clearR`, `clearF` and `clearC`  (Pull Requests [\#51](https://github.com/AY2021S1-CS2103T-W10-2/tp/pull/51), [\#117](https://github.com/AY2021S1-CS2103T-W10-2/tp/pull/117), [\#170](https://github.com/AY2021S1-CS2103T-W10-2/tp/pull/170))
  * [Developer Guide](https://ay2021s1-cs2103t-w10-2.github.io/tp/DeveloperGuide.html):
    * Added implementation details of the `eatRecipe`, `listConsumption`,  `selectRecipe` and `clear` feature. (Pull Requests [\#117](https://github.com/AY2021S1-CS2103T-W10-2/tp/pull/117))
    * Combined the implementation of the similar feature e.g. combine `listConsumption`, `listRecipe` and `listIngredient` to `list`. (Pull Requests [\#181](https://github.com/AY2021S1-CS2103T-W10-2/tp/pull/181))
    * Added the table of content in Developer Guide

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#172](https://github.com/AY2021S1-CS2103T-W10-2/tp/pull/172), [\#178](https://github.com/AY2021S1-CS2103T-W10-2/tp/pull/178), [\#24](https://github.com/AY2021S1-CS2103T-W10-2/tp/pull/24)
  * Reported bugs and suggestions for other teams in the class (examples: [1](https://github.com/TianYong-Goh/ped/issues/3), [2](https://github.com/TianYong-Goh/ped/issues/5), [3](https://github.com/TianYong-Goh/ped/issues/7))
