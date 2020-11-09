---
layout: page
title: Stephen Tan Hin Khai's Project Portfolio Page
---
![profileimage](../images/blackonyyx.png)

## Project: Inventoryinator

Inventoryinator is a desktop inventory management application that helps gamers quickly and easily manage
their in-game inventories. It supports any generic game, in contrast to community-created solutions,
which cater for specific games. Users interact with it via a CLI, and receive feedback via a GUI created
with JavaFX.

Given below are my contributions to the project.

* **New Feature**: Added the `deli` to delete items [\#40](https://github.com/AY2021S1-CS2103T-F13-1/tp/pull/40)
  * What it does: allows the user to delete items from the item list.
  * Justification: This feature improves the product significantly because a user can make mistakes in adding items and the app should provide a convenient way to delete them to prevent cluttering of the item list.
  * Highlights: This enhancement affects the deletion of recipes in existing items in the item list.
  * Credits: The implementation of `delr` command in editing the model was the inspiration of part of the execution of the command. 

* **New Feature**: Added `addt` to add item tag [\#86](https://github.com/AY2021S1-CS2103T-F13-1/tp/pull/85)
  * What it does: allows the user to edit items from the item
  * Justification: This feature improves the product in terms of User Experience, as it allowed people to edit items to add tags specifically, allowing users new options to perform additional fitering and searching.
   Thus the app should provide a fast and simple way for the user to do this. If the user used `edit` instead, they would have to edit all the tags and that the item already currently has, rather than just add new tags. 

* **New Feature**: Added `findt` to search by item tag [\#86](https://github.com/AY2021S1-CS2103T-F13-1/tp/pull/85)
  * What it does: allows the user to search for items by tags
  * Justification: This feature improves the product, by allowing the user to search for items in the item list from a user-defined given tag. 

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=blackonyyx&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

* **Project management**:
  * Code Review
  * Bug Reporting [\#51](https://github.com/AY2021S1-CS2103T-F13-1/tp/issues/51), [\#94](https://github.com/AY2021S1-CS2103T-F13-1/tp/issues/94), [\#77](https://github.com/AY2021S1-CS2103T-F13-1/tp/issues/77) 

* **Enhancements to existing features**:
  * Added optional parameters to `add item` command [\#26](https://github.com/AY2021S1-CS2103T-F13-1/tp/issues/26), and add recipe command [\#134](https://github.com/AY2021S1-CS2103T-F13-1/tp/issues/134)
  * What it does: Allow the user to set optional parameters, where default values are input if no prefix is given.
  
* **Documentation**:
  * User Guide: 
    * Setup the initial User Guide [\#12](https://github.com/AY2021S1-CS2103T-F13-1/tp/issues/12)
    * Added documentation for the features `delete item` [\#6](https://github.com/AY2021S1-CS2103T-F13-1/tp/issues/6)
    * Added documentation for the `add tag` and `search by item tag` [\#35](https://github.com/AY2021S1-CS2103T-F13-1/tp/issues/35)
    * Did cosmetic tweaks to existing documentation of features `clear`, `exit`: [\#74]()
  * Developer Guide:
    * Setup the initial Developer Guide [\#12](https://github.com/AY2021S1-CS2103T-F13-1/tp/issues/12)
    * Updated PlantUML diagrams of the architecture [\#12](https://github.com/AY2021S1-CS2103T-F13-1/tp/issues/12)
    * Added User Personas [\#12](https://github.com/AY2021S1-CS2103T-F13-1/tp/issues/12)
    * Added implementation details of the `delete item` feature.[\#60](https://github.com/AY2021S1-CS2103T-F13-1/tp/issues/60)

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#72](https://github.com/AY2021S1-CS2103T-F13-1/tp/pull/72), [\#91](https://github.com/AY2021S1-CS2103T-F13-1/tp/pull/91), [\#47](https://github.com/AY2021S1-CS2103T-F13-1/tp/pull/47), [\#29](https://github.com/AY2021S1-CS2103T-F13-1/tp/pull/29)

* **Testing**
  * Refactored the previous testing framework that was disrupted due to static variables, and refined certain testcases that required usage in future tests.
  * Wrote test cases for enhancements and implementations that was assigned to me. [\#51](https://github.com/AY2021S1-CS2103T-F13-1/tp/issues/51), [\#94](https://github.com/AY2021S1-CS2103T-F13-1/tp/issues/94), [\#77](https://github.com/AY2021S1-CS2103T-F13-1/tp/issues/77)
