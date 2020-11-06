---
layout: page
title: Michael Setia Atmaja's Project Portfolio Page
---

## Project: CAP 5 Buddy

### Project Overview

Cap 5 Buddy is a project developed for a software engineering module (CS2103T) at the National University of Singapore.
It has a GUI created using JavaFX, and it is written in Java.

### Summary of Contributions

Given below are my contributions to the project.

* **New Feature**: Added the ability to undo/redo previous commands.
  * What it does: allows the user to undo all previous commands one at a time. Preceding undo commands can be reversed by using the redo command.
  * Justification: This feature improves the product significantly because a user can make mistakes in commands and the app should provide a convenient way to rectify them.
  * Highlights: This enhancement affects existing commands and commands to be added in future. It required an in-depth analysis of design alternatives. The implementation too was challenging as it required changes to existing commands.
  * Credits: *{mention here if you reused any code/ideas from elsewhere or if a third-party library is heavily used in the feature so that a reader can make a more accurate judgement of how much effort went into the feature}*

* **New Feature**: Added TodoList feature as a task manager
  * Added base implementation for TodoList.
  * Implemented some of TodoList commands.
  * Added a non-command feature to view statistics related to the TodoList.
  * Updated GUI for TodoList (Tasks tab)
  * The following are some notable commands that the TodoList have,
   
   (Some big feature will be mentioned below)
   
* **Notable Feature**: Added sorting feature for TodoList
  * Added implementation for sorting a TodoList based on a criterion.
  * Updated implementation of `Model` to support this feature
  
* **Notable Feature**: Enhanced task managing of TodoList by updating the GUI
  * Added indicators for representing the state of a task
  * Added a pie chart to show the completion ration of the tasks
  * Added a bar chart to show the incoming tasks within a week

* **Notable Feature**: Contributed in implementing mechanism to store zoom links in a module
  * Implemented the base code for storing zoom links in the memory or disk
  * This implementation is used by zoom-links related commands

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=michael-setia&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

* **Project management**:
  * Approved PRs.

* **Enhancements to existing features**:
  * Updated the GUI color scheme (Pull requests [\#33](), [\#34]())
  * Updated the card used in the GUI to display the items in the list.
  * Wrote additional tests for existing features to increase coverage from 88% to 92% (Pull requests [\#36](), [\#38]())

* **Documentation**:
  * User Guide:
    * Added documentation for the features `delete` and `find` [\#72]()
    * Did cosmetic tweaks to existing documentation of features `clear`, `exit`: [\#74]()
    * Added documentation for `Task`.
    * Added documentation for `TodoList` features (except for find command).
    * Added documentation for sorting contacts command.
  * Developer Guide:
    * Added implementation details of the `delete` feature.
    * Added diagram and implementation details for `Model`
    * Added diagram and implementation details for `Task` under `TodoList`
    * Added implementation details for TodoList related commands.

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#316](), [\#381](), [\#383](), [\#156]() (these PR are just randomly chosen, will finalize later)
  * Contributed to forum discussions (examples: [1](), [2](), [3](), [4]())
  * Reported bugs and suggestions for other teams in the class (examples: [1](), [2](), [3]())
  * Some parts of the history feature I added was adopted by several other class mates ([1](), [2]())

* **Tools**:
  * Integrated a third party library (Natty) to the project ([\#42]())
  * Integrated a new Github plugin (CircleCI) to the team repo

* _{you can add/remove categories in the list above}_

### Contributions to the Developer Guide (Extracts)

(Some screenshots and diagrams will be added)

### Contributions to the User Guide (Extracts)

(Some screenshots and diagrams will be added)
