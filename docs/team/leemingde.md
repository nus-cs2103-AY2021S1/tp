---
layout: page
title: Lee Ming De's Project Portfolio Page
---

## Project: Covigent

Covigent is a desktop application that is used to aid hotel staff handle the Covid-19 situation better. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the ability to edit a patient's information.
  * What it does: allows the user to change a patient's information (eg, name, temperature, age) after a patient has been added to Covigent.
  * Justification: This feature improves the product significantly because a user can make mistakes in typing in the details of the patient and the application should provide a convenient way to rectify them.

* **New Feature**: Added the ability for a user to allocate a patient to a room.
  * What it does: allows the user to allocate a patient to a room or remove a patient from a room.
  * Justification: This feature improves the product because it allows the user to keep track of the room the patient is in which fulfills the functional requirements of the application.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=leemingde)

* **Enhancements to existing features**:
  * Updated the GUI color scheme
  * Improved the GUI by adding in a panel to display information
  * Added CSS file for the details panel which was later reused by others.
  
* **Testing**:
  * Added unit tests for the `allocateRoomParser`, `editPatientCommand`, and `editPatientCommandParser` features.
  * Added integration tests for `allocateRoomCommand`, `editPatientCommand`.

* **Documentation**:
  * User Guide:
    * Added documentation for the features `editpatient` and `allocateroom` [\#44](https://github.com/AY2021S1-CS2103T-W12-1/tp/pull/44)
    * Added introduction for Covigent [\#44](https://github.com/AY2021S1-CS2103T-W12-1/tp/pull/44)
  * Developer Guide:
    * Added implementation details of the `allocateroom` and `logging` features
    * Added implementation details of GUI
    * Added/updated UML diagrams for `Logic Component`, `allocateroom`, `UI` implementation
    * Helped with formatting for the entire developer guide

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#10](https://github.com/AY2021S1-CS2103T-W12-1/tp/pull/10),
  [\#26](https://github.com/AY2021S1-CS2103T-W12-1/tp/pull/26), [\#27](https://github.com/AY2021S1-CS2103T-W12-1/tp/pull/27),
  [\#69](https://github.com/AY2021S1-CS2103T-W12-1/tp/pull/69), [\#80](https://github.com/AY2021S1-CS2103T-W12-1/tp/pull/80),
  [\#84](https://github.com/AY2021S1-CS2103T-W12-1/tp/pull/84), [\#99](https://github.com/AY2021S1-CS2103T-W12-1/tp/pull/99)
  * Reported bugs and provided suggestions for StonksBook, a project by another teams in the class: [link](https://github.com/leemingde/ped/issues)
