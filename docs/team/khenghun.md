---
layout: page
title: Siew Kheng Hun's Project Portfolio Page
---
<img src="../images/khenghun.png" height=200>

## Project: Inventoryinator

Inventoryinator is a desktop inventory management application that helps gamers quickly and easily manage
their in-game inventories. It supports any generic game, in contrast to community-created solutions,
which cater for specific games. Users interact with it via a CLI, and receive feedback via a GUI created
with JavaFX.

Given below are my contributions to the project.
* **New Feature**: Added the `delr` command which deletes a recipe.
[\#27](https://github.com/AY2021S1-CS2103T-F13-1/tp/pull/27)
  * What it does: Allows the user to delete a recipe they no longer need.
  * Justification: This feature improves the User Experience, as it reduces the clutter in the existing recipe list,
    so that the user can see all useful and relevant recipes.
  * Highlights: This supports the `deli` command, as deleting an item will delete all affected recipes.

* **New Feature**: Added the `listr` command that lists all recipes in the inventory. 
[\#47](https://github.com/AY2021S1-CS2103T-F13-1/tp/pull/47)
  * What it does: Displays all recipes in the inventory on the GUI.
  * Justification: This feature is required for users to view their recipes.
  * Credits: Adapted from the framework of `listi`. 
  
* **New Feature**: Added the `edit` command which edits an item in the inventory. 
[\#53](https://github.com/AY2021S1-CS2103T-F13-1/tp/pull/53)
  * What it does: Allows the user to edit the name, description and/or quantity of an item.
  * Justification: This feature improves the User Experience, as it allows the user to amend or update any fields required.
  Sets the groundwork for modifying of quantity in `addq`. 
  * Credits: Referenced from the original implementation in AB3.
  
* **New Feature**: Added the `craft` and `check` command.
[\#81](https://github.com/AY2021S1-CS2103T-F13-1/tp/pull/81)
[\#91](https://github.com/AY2021S1-CS2103T-F13-1/tp/pull/91)
  * What it does: `craft` allows the user to use a recipe to craft an item and reflects the outcome in the inventory.
    `check` allows the user to check which recipes may be used for the crafting process.
  * Justification: These features allow the user to check if there are sufficient materials for crafting, and simulate
  the crafting process in the inventory with a simple command.
  * Credits: Usage of `addq` command to update the relevant quantities of items. 

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=khenghun&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

* **Project management**:
  * Feature Developer
  * Code Review
  * Bug Reporting [\#98](https://github.com/AY2021S1-CS2103T-F13-1/tp/pull/98), 
  [\#141](https://github.com/AY2021S1-CS2103T-F13-1/tp/pull/141)
  
* **Enhancements to existing features**:
  * Update `view` command to display the recipes which can craft the item. 
  [\#155](https://github.com/AY2021S1-CS2103T-F13-1/tp/pull/155)
  * This enables the user to easily choose which
  recipe to use for crafting, and helps in identifying the right recipe index to delete when using `delr`.

* **Testing**:
  * Wrote test cases for all features I implemented.
  * Wrote unit tests for Quantity, UniqueItemList and InvParserUtil 
  [\#21](https://github.com/AY2021S1-CS2103T-F13-1/tp/pull/21)                        
  
* **Documentation**:
  * User Guide:
    * Added documentation for the features of `edit`, `craft`, and `check`.
[\#100](https://github.com/AY2021S1-CS2103T-F13-1/tp/pull/100)
  * Developer Guide:
    * Added documentation and implementation for the features `listi` and `listr`
          [\#74](https://github.com/AY2021S1-CS2103T-F13-1/tp/pull/74)
    
* **Community**:
  * Sample of PRs reviewed (with non-trivial review comments): [\#23](https://github.com/AY2021S1-CS2103T-F13-1/tp/pull/23)
  [\#44](https://github.com/AY2021S1-CS2103T-F13-1/tp/pull/44)
