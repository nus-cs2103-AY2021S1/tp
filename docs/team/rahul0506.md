---
layout: page
title: Gidijala Sai Rahul's Project Portfolio Page
---
![profileimage](../images/rahul0506.png)

## Project: Inventoryinator

Inventoryinator is a desktop inventory management application that helps gamers quickly and easily manage
their in-game inventories. It supports any generic game, in contrast to community-created solutions,
which cater for specific games. Users interact with it via a CLI, and receive feedback via a GUI created
with JavaFX.

Given below are my contributions to the project.

* **New Feature**: Added the `addr` command, as well as all the related structures required to add, store and load
    recipes.[\#23](https://github.com/AY2021S1-CS2103T-F13-1/tp/pull/23)
  * What it does: Allows the user to add a recipe to an existing item in the inventory, using existing items as
    ingredients.
  * Justification: This feature is core to Inventoryinator. It extends items to now be associated with each other as
    ingredients and products. This is used in other commands like `listr` and `view`, and enables other functionality
    such as `craft` and `check`.
  * Highlights: Built upon the framework laid by the item functionality. Initial plans to have separate item and recipe
    lists in the model were scrapped and a unified list to contain both was implemented. To prevent breaking abstraction
    in the multiple components involved, support classes like `ItemPrecursor` and `RecipePrecursor` were used as
    intermediates.
  * Credits: Existing AB3 code and Zhengdao's(@justacasul) item functionality was loosely referenced to create initial
    support for recipes and integrate them with items.

* **New Feature**: Added the `find` command that supports searching for items using their names.
    [\#42](https://github.com/AY2021S1-CS2103T-F13-1/tp/pull/42)
  * What it does: Allows the user query the items stored in the inventory using search keywords.
  * Justification: This feature improves the product by allowing users to search item names using keywords, instead of
    being forced to scroll through the list to find an item. It also allows partial matching of keyword to item name, so
    users can search more flexibly.
  * Highlights: The implementation is distinct from AB3's version of the command in how it searches the item list. While
    AB3 checked for a name containing a whole keyword, the current implementation uses regex to compile the keywords
    into patterns and match them to item names.
  * Credits: The `find` command in AB3 was referenced for base implementation.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=rahul0506&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

* **Project management**:
  * Code review
  * Bug reporting

* **Enhancements to existing features**:
  * Updated codebase that included item functionality, to have unified support for item, location and recipe entities.

* **Documentation**:
  * User Guide:
    * Expanded the introduction
    * Added undocumented commands and removed those that are not implemented
    * Various formatting and consistency fixes between different sections and commands
    * Added a command legend
    [\#79](https://github.com/AY2021S1-CS2103T-F13-1/tp/pull/79/)
  * Developer Guide:
    * Added implementation details of the `find` feature.
    * Added user stories and formatting/consistency fixes.
    [\#80](https://github.com/AY2021S1-CS2103T-F13-1/tp/pull/80)

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#12](), [\#32](), [\#19](), [\#42]()
  * Contributed to forum discussions (examples: [1](), [2](), [3](), [4]())
  * Reported bugs and suggestions for other teams in the class (examples: [1](), [2](), [3]())
  * Some parts of the history feature I added was adopted by several other class mates ([1](), [2]())

* **Testing**
  * Wrote test cases for find command and associated classes/methods: 
    [\#42](https://github.com/AY2021S1-CS2103T-F13-1/tp/pull/42)
  * Wrote additional tests for existing features like `undo`, `redo` and `view`:
    [\#90](https://github.com/AY2021S1-CS2103T-F13-1/tp/pull/90)
