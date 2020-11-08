---
layout: page
title: Olivia Johansen's Project Portfolio Page
---

## Project: Wishful Shrinking

Wishful Shrinking is a desktop application for managing your diet, keeping track of your on-hand ingredients, recipes, as well as the food you’ve eaten (along with their calories). While it has a GUI, most of the user interactions happen using a CLI (Command Line Interface).

Given below are my contributions to the project. These enhancements affects existing commands and commands to be
 added in future. they required an in-depth analysis of design alternatives. The implementation too was challenging
  as it required changes to existing commands.

* **New Feature**: Added the ability to list ingredients.
  * What it does: allows the user to list the ingredients they currently have in the fridge. 
  * Justification: This feature improves the product significantly because a user can easily view all the ingredients present in their fridge.
  * Credits: This feature was built off from the "list" feature in AddressBook Level 3.  
  
* **New Feature**: Added the ability to delete an ingredient in the fridge.
  * What it does: allows the user to delete an ingredient in their fridge.
  * Justification: This feature improves the product significantly because a user can now delete the ingredients to keep the fridge updated with their real-life fridge.
  * Credits: This feature was built off from the "delete contact" feature in AddressBook Level 3.  

* **New Feature**: Added the ability to edit ingredients in the user's fridge.
  * What it does: allows the user to edit ingredients to their fridge.
  * Justification: This feature improves the product significantly because a user can now edit ingredients in the fridge, saving them the hassle of having to delete then re-adding an ingredient.
  * Highlights: This enhancement affects existing commands and commands to be added in future. It required an in-depth analysis of design alternatives. The implementation too was challenging as it required changes to existing commands.
  * Credits: This feature was built off from the "edit" feature in AddressBook Level 3 and refactored to edit ingredients instead of contacts.

* **New Feature**: Added the ability to edit for a recipe in the recipe list.
  * What it does: allows the user to edit recipes to their recipe list.
  * Justification: This feature improves the product significantly because a user can now edit recipes in the recipe list, saving them the hassle of having to delete then re-adding an entire recipe.
  * Credits: This feature was built off from the "edit" feature in AddressBook Level 3 and refactored to edit ingredients instead of contacts.

* **New Feature**: Added the ability to directly modify an existing ingredient or recipe.
  * What it does: allows the user to easily edit ingredients or recipes in their fridge or recipe list
   respectively by directly modifying the existing item.
  * Justification: This feature improves the product significantly because a user can easily edit items without
   having to re-enter the entire fields of an item only to modify a portion of it.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=oliviajohansen&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

* **Project management**:
  * Managed release `v1.3.trial` (1 release) on GitHub
  * Updated the links and architecture sequence diagram in the Developer Guide to match Wishful Shrinking

* **Enhancements to existing features**:
  * Added support for quantity for ingredients (Pull requests [\#82](https://github.com/AY2021S1-CS2103T-W10-2/tp/pull/82))
    * Justification: This feature allow users to better keep track of quantity of ingredients in the fridge. The
     flexible format of quantity allows users to save and adjust ingredient quantities precisely.
      
* **Documentation**:
  * User Guide:
    * Added documentation for the features `deleteF`, `addF`, `get editR`, `editR`, `get editF` and `editF` [\#172](https://github.com/AY2021S1-CS2103T-W10-2/tp/pull/172)
    * Added Introduction and Glossary [\#172](https://github.com/AY2021S1-CS2103T-W10-2/tp/pull/172)
    * Did cosmetic tweaks to User Guide and ensured phrasing used was consistent and accurate: [\#184](https://github.com/AY2021S1-CS2103T-W10-2/tp/pull/184)
  * Developer Guide:
    * Added implementation details of the `deleteF`, `addF`, `get editR`, `editR`, `get editF` and `editF` feature: [\#122](https://github.com/AY2021S1-CS2103T-W10-2/tp/pull/122) [\#127](https://github.com/AY2021S1-CS2103T-W10-2/tp/pull/127)
    * Added overview and introduction [\#249](https://github.com/AY2021S1-CS2103T-W10-2/tp/pull/249)
    * Did cosmetic tweaks to Developer Guide and ensured phrasing used was consistent and accurate: [\#258](https://github.com/AY2021S1-CS2103T-W10-2/tp/pull/258) [\#257](https://github.com/AY2021S1-CS2103T-W10-2/tp/pull/257/files)

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#251](https://github.com/AY2021S1-CS2103T-W10-2/tp/pull/251)
