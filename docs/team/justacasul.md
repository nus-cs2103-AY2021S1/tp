---
layout: page
title: Liu Zheng Dao's Project Portfolio Page
---
<img src="../images/justacasul.png" height=200>

## Project: Inventoryinator

Inventoryinator is a desktop inventory management application that helps gamers quickly and easily manage
their in-game inventories. It supports any generic game, in contrast to community-created solutions,
which cater for specific games. Users interact with it via a CLI, and receive feedback via a GUI created
with JavaFX.

Given below are my contributions to the project.

* **New Feature**: Added the `addi` command, as well as all related structures required to add, store and load items 
    and locations. [\#17](https://github.com/AY2021S1-CS2103T-F13-1/tp/pull/17)
  * What it does: Adds an item to the inventory.
  * Justification: This feature is the core of Inventoryinator. It is needed for displaying items, adding recipes,
    using items in recipes for crafting, etc.
  * Highlights: Adding this feature lays the groundwork required for breaking off from AB3 and creating our own product.
  * Credits: Parts of the `add` command, and `Model`, `Storage`, `Logic`, `Ui` packages from AB3 were heavily refactored
    in the creation of this feature.
    
* **New Feature**: Added the `listi` command that lists all items in the inventory. 
    [\#17](https://github.com/AY2021S1-CS2103T-F13-1/tp/pull/17) (yes I did do them all in one PR)
  * What it does: Displays all items in the inventory on the GUI.
  * Justification: This feature is required for users to know which items they have, and what quantities they are at.
  * Highlights: `listi` lays the groundwork for the future implementation of `listr`, a command to list all recipes.
  * Credits: Almost entirely refactored from `list` command of AB3.
    
* **New Feature**: Added the `cleari` command that clears all items in the inventory. 
    [\#17](https://github.com/AY2021S1-CS2103T-F13-1/tp/pull/17) 
    [\#76](https://github.com/AY2021S1-CS2103T-F13-1/tp/pull/76) (update for clearing related recipes) 
  * What it does: Removes all items and related recipes from the inventory.
  * Justification: This feature is for users who want to start afresh, on a new game or account.
  * Highlights: Adding this feature lays the groundwork required for breaking off from AB3 and creating our own product.
  * Credits: Initially refactored from `clear` command of AB3, then changed to accomodate deleting related recipes.
    
* **New Feature**: Added the `view` command to view the detailed information of a single item. 
    [\#44](https://github.com/AY2021S1-CS2103T-F13-1/tp/pull/44)
  * What it does: Displays an item's details, quantity, tags and recipes to craft the item.
  * Justification: This is for users who wish to view detailed information of an item that's usually truncated in list view.
  * Highlights: Implementing this feature resulted in the change of `CommandResult` to allow for changing parts of the 
    GUI while the program is still running.
  * Credits: Rahul(@Rahul0506) suggested looking at how `executeCommand` handled the `help` command.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=justacasul&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

* **Project management**:
  * Code Review
  * Bug Reporting [\#43](https://github.com/AY2021S1-CS2103T-F13-1/tp/issues/43), 
    [\#82](https://github.com/AY2021S1-CS2103T-F13-1/tp/issues/82), 
    [\#145](https://github.com/AY2021S1-CS2103T-F13-1/tp/issues/145),
    [\#170](https://github.com/AY2021S1-CS2103T-F13-1/tp/issues/170)

* **Enhancements to existing features**:
  * Update `deli` and `delr` commands to delete the item completely. 
  [\#63](https://github.com/AY2021S1-CS2103T-F13-1/tp/pull/63),
  [\#66](https://github.com/AY2021S1-CS2103T-F13-1/tp/pull/66)
  * Update `addq` command to show an error messages and handle non-integers and overflows.
  [\#142](https://github.com/AY2021S1-CS2103T-F13-1/tp/pull/142),
  [\#146](https://github.com/AY2021S1-CS2103T-F13-1/tp/pull/146)
  
* **Documentation**:
  * User Guide: 
    * Updated the introduction. [\#103](https://github.com/AY2021S1-CS2103T-F13-1/tp/pull/103)
    * Added pictures for all example commands. [\#158](https://github.com/AY2021S1-CS2103T-F13-1/tp/pull/158)
  * Developer Guide:
    * Add writeup and sequence diagram of `view command`. [\#75](https://github.com/AY2021S1-CS2103T-F13-1/tp/pull/75)

* **Community**:
  * PRs reviewed (with non-trivial review comments): 
  [\#22](https://github.com/AY2021S1-CS2103T-F13-1/tp/pull/22), 
  [\#27](https://github.com/AY2021S1-CS2103T-F13-1/tp/pull/27), 
  [\#155](https://github.com/AY2021S1-CS2103T-F13-1/tp/pull/155)

* **Testing**
  * Wrote test cases for commands I've implemented and edited: 
  [\#17](https://github.com/AY2021S1-CS2103T-F13-1/tp/issues/17),
  [\#44](https://github.com/AY2021S1-CS2103T-F13-1/tp/pull/44), 
  [\#76](https://github.com/AY2021S1-CS2103T-F13-1/tp/pull/76), 
  [\#146](https://github.com/AY2021S1-CS2103T-F13-1/tp/pull/146)
