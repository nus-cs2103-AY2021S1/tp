---
layout: page
title: Wee Yee Hong's Project Portfolio Page
---

## Project: Covigent

Covigent is a desktop application that is used to aid hotel staff handle the Covid-19 situation better. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the ability to add a task to a room.
  * What it does: allows the user to create a new task with description and due date in any room in Covigent.
  * Justification: This feature improves the product significantly because a user may want to keep track of time-critical tasks related to patients in each of the rooms.

* **New Feature**: Added the ability to delete a task from a room.
  * What it does: allows the user remove any tasks that she has completed or does not need from a room.
  * Justification: This feature improves the product significantly because too many tasks in a room can clutter the user interface and make it more difficult for the user to organize her tasks.

* **New Feature**: Added the ability to edit the information of a task in a room.
   * What it does: allows the user to change the description or due date after a task has been added to a room in Covigent.
   * Justification: This feature improves the product significantly because a user can make mistakes in typing in the details of a task and the application should provide a convenient way to rectify them.

* **New Feature**: Added the ability to view a list of tasks in the user interface.
  * What it does: allows the user to see in which rooms the tasks can be found and get an overview of all tasks that are in Covigent, without needing to know the room number of the room in which the task has been added.
  * Justification: This feature improves the product significantly because a user may forget which room she has added a task to and wants a quick way to look up the task.

* **New Feature**: Allowed commands to support different date-time formats.
  * What it does: allows the user to select their preferred date-time formats when using commands that have a date or time component.
  * Justification: This feature improves the product because it allows the user to comfortably use a common and familiar date-time format (e.g. dd/MM/YYYY) in her country without memorizing a specific format.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=w-yeehong)

* **Enhancements to existing features**:
  * Updated GUI color scheme and separate CSS into multiple files based on the names of the views for easier lookup (Pull request [#101](https://github.com/AY2021S1-CS2103T-W12-1/tp/pull/101))
  * Refactored and repackaged the codebase for better organization of files based on the model type handled (Pull requests [#22](https://github.com/AY2021S1-CS2103T-W12-1/tp/pull/22), [#107](https://github.com/AY2021S1-CS2103T-W12-1/tp/pull/107))
  * Reduced code duplication (Pull request [#154](https://github.com/AY2021S1-CS2103T-W12-1/tp/pull/154))

* **Testing**:
  * Added unit tests to test and validate that the description and due date of a task should conform to a specified format.
  * Added unit tests for the `addtask`, `deletetask`, and `edittask` features.

* **Documentation**:
  * User Guide:
    * Added documentation for the features `addtask`, `deletetask`, and `edittask` [\#153](https://github.com/AY2021S1-CS2103T-W12-1/tp/pull/153)
    * Improved "Getting Started" section for Covigent` [[\#153](https://github.com/AY2021S1-CS2103T-W12-1/tp/pull/153)
  * Developer Guide:
    * Added the implementation details for `Task` and its CRUD operations
    * Added the implementation details for the user interface of `Task`
    * Updated to reflect the high-level view of the architecture of Covigent
    * Updated the testing guide to include additional instructions for testing Covigent

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#10](https://github.com/AY2021S1-CS2103T-W12-1/tp/pull/10),
   [\#12](https://github.com/AY2021S1-CS2103T-W12-1/tp/pull/12), [\#26](https://github.com/AY2021S1-CS2103T-W12-1/tp/pull/26),
   [\#60](https://github.com/AY2021S1-CS2103T-W12-1/tp/pull/60), [\#64](https://github.com/AY2021S1-CS2103T-W12-1/tp/pull/64),
   [\#72](https://github.com/AY2021S1-CS2103T-W12-1/tp/pull/72), [\#100](https://github.com/AY2021S1-CS2103T-W12-1/tp/pull/100),
   [\#105](https://github.com/AY2021S1-CS2103T-W12-1/tp/pull/105)
  * Reported bugs and provided suggestions for Jarvis, a project by another teams in the class: [link](https://github.com/w-yeehong/ped/issues)

* **Tools**:
  * Integrated a third party library (Mockito) to the project ([\#104](https://github.com/AY2021S1-CS2103T-W12-1/tp/pull/104))
