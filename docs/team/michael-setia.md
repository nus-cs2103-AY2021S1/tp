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

* **New Feature**: Added TodoList feature as a task manager
  * Added base implementation for TodoList.
  * Implemented some of TodoList commands.
  * Added a non-command feature to view statistics related to the TodoList.
  * Updated GUI for TodoList (Tasks tab)
  * The following are some notable commands that the TodoList have,
   
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
  * Approved and reviewed PRs with non-trivial comments.

* **Enhancements to existing features**:
  * Updated the card used to display each entry in the GUI.
  * Updated the card used in the GUI to display the items in the list along with a progress bar.

* **Documentation**:
  * User Guide:
    * Added documentation for `Task`.
    * Added documentation for `TodoList` features (except for find command).
    * Added documentation for `sortcontact` contacts command.
  * Developer Guide:
    * Added all implementation details for `Task`
    * Added all implementation details for `TodoList` and all of its command (except `findcommand`)
    * Added implementation details for `sortcontact` feature
    * Added diagram and implementation details for `Model`

* **Testing**:
  * Did testing for most of the `TodoList` part (4000+ LoC)
  * Did testing for basic commands for `ModuleTracker` (500+ LoC)

* **Community**:
  * Reviewed and approved PRs e.g PR #316, PR #257 (more can be checked in GitHub)
  * Added 50+ comments througout the project (can be checked in the module dashboard)
  * Added comments as discussion e.g. PR #244 (more can be checked in GitHub)
