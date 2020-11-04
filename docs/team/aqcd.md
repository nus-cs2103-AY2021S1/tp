---
layout: page
title: Shi Jing Lin's Project Portfolio Page
---
![profileimage](../images/aqcd.png)

## Project: Inventoryinator

Inventoryinator is a desktop inventory management application that helps gamers quickly and easily manage
their in-game inventories. It supports any generic game, in contrast to community-created solutions,
which cater for specific games. Users interact with it via a CLI, and receive feedback via a GUI created
with JavaFX.

Given below are my contributions to the project.

* **New Feature**: Added the `undo` and `redo` commands to undo/redo previous commands.
  * What it does: Allows the user to undo all previous commands that affected inventory state one at a time.
                  Preceding undo commands can be reversed by using the redo command.
  * Justification: This feature improves the product significantly because a user can make mistakes in executing
                   commands. In particular, `craft` commands affect many items at once, and it will be difficult
                   for users to undo such commands manually. Thus, the app should provide a convenient way to
                   rectify them.
  * Highlights: This enhancement affects existing commands and commands to be added in the future. The final
                implementation's design differed from the original plan after an in-depth analysis of the tradeoff
                between increased effort to implement future commands and increased time and space requirements
                concluded that the former choice was less acceptable.
  * Credits: AB3's proposed undo/redo command implementation inspired the final design choice.

* **New Feature**: Added the `addq` command that provides the user a faster way to add a quantity of an item to their
                   inventory.
  * Justification: Adding a specific quantity to an item is something a user will do very often. Thus, the app
                 should provide a fast and simple way for the user to do this. If the user used `edit` instead,
                 they would have to check the original quantity of the item and do the addition/subtraction
                 of the quantity change manually.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=aqcd&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

* **Project management**:
  * Managed `v1.1` - `v1.4` (6 milestones, 4 releases) on GitHub
  * Managed workload assignment and timetabling
  * Primary code reviewer

* **Testing**:
  * Refactored the previous test framework to support items, recipes and locations and their precursors.
    This includes setting up the test objects to be used in future tests, and the tests for items, recipes
    and locations. (Pull requests [\#29](https://github.com/AY2021S1-CS2103T-F13-1/tp/pull/29), [\#39](https://github.com/AY2021S1-CS2103T-F13-1/tp/pull/39))
  * Wrote tests for `addi`, `addr`, `addq` and their parsers. (Pull request [\#39](https://github.com/AY2021S1-CS2103T-F13-1/tp/pull/39))

* **Documentation**:
  * User Guide:
    * Added feature write-ups. (Pull request [\#147](https://github.com/AY2021S1-CS2103T-F13-1/tp/pull/147))
    * Added documentation for the features `cleari`, `clearr`, `undo`, `redo`. (Pull request [\#99](https://github.com/AY2021S1-CS2103T-F13-1/tp/pull/99))
  * Developer Guide:
    * Added implementation details for the features `addq`, `undo`, `redo`. (Pull requests
    [\#72](https://github.com/AY2021S1-CS2103T-F13-1/tp/pull/72), [\#84](https://github.com/AY2021S1-CS2103T-F13-1/tp/pull/84))

* **Community**:
  * Sample of PRs reviewed (with non-trivial review comments): [\#17](https://github.com/AY2021S1-CS2103T-F13-1/tp/pull/17),
  [\#22](https://github.com/AY2021S1-CS2103T-F13-1/tp/pull/22), [\#23](https://github.com/AY2021S1-CS2103T-F13-1/tp/pull/23),
  [\#40](https://github.com/AY2021S1-CS2103T-F13-1/tp/pull/40), [\#85](https://github.com/AY2021S1-CS2103T-F13-1/tp/pull/85)
