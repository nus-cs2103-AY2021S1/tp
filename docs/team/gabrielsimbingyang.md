---
layout: page
title: Gabriel Sim's Project Portfolio Page
---

## Project: Green Tea

Green Tea is a desktop app for learning a new language, optimized for use via a Command Line Interface (CLI)
while still having the benefits of a Graphical User Interface (GUI) created with JavaFX.
It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Implemented the basic quizzing system including new commands such as the PlayCommand, StopCommand and AnswerCommand.
(Pull requests [\#106](https://github.com/AY2021S1-CS2103T-T09-4/tp/pull/106) [\#185](https://github.com/AY2021S1-CS2103T-T09-4/tp/pull/185))
  * What it does: Allows the user to quiz themselves on the different entries they have added to the Green Tea.
  * Justification: This feature is essentially the core of the application. The entire application is built around this compulsory feature including other features such as
  statistics and switching tabs.
  * Highlights: This implementation was incredibly difficult to implement because it utilised portions of logic, model and ui. Implementing this feature allowed
  me to understand the inner working of AddressBook and allowed me to greater help my teammates with their work by communicating to them what is and is not technically
  feasible in this product. Since this is a core feature, this implementation also affected what code can be added to the future and what existing code can be removed. Modifying the implementation to
  allow the program to switch between play mode and command mode also required deep analysis of design alternatives.
  * Credits: I wrote this by myself but I utilized the existing commands created by my teammate, Melanie.
  Additionally, code with the title `WordBank` mostly originated from the same `AddressBook` classes from AB3.

* **New Feature**: Split the UI to hold both deck list and entry list. (Pull requests [\#67](https://github.com/AY2021S1-CS2103T-T09-4/tp/pull/67))
  * What it does: Allows the user to observe both the deck list and the entry list which is essential for Green Tea to be an intuitive application.
  * Justification: This feature allowed the user to observe a more intuitive user interface. It also allowed the team to develop  the tabbing feature which meant
  that more data/features can be stored in each of the tabs
  * Highlights: This implementation was difficult to implement because it required knowledge it utilised portions of logic, model and UI. Specifically, understanding how
  obervablelist was used and modifying it was a challenging task.
  * Credits: I wrote this by myself but I utilized the existing commands created by my teammate, Melanie.
  Additionally, code with the title `WordBank` mostly originated from the same `AddressBook` classes from AB3.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&zFR=false&tabType=authorship&tabAuthor=gabrielsimbingyang&tabRepo=AY2021S1-CS2103T-T09-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other)

* **Project management**:
  * Team leader
  * Helped other team members with their task and contributions
  * Kept the team on schedule by monitoring dashboard, issues , milestones and datelines
  * Ensured team members focused on goals during weekly group meetings
  * Gave meaningful updates on my own progress to help teammates better understand the current state of the application
    (Pull requests [\#72](https://github.com/AY2021S1-CS2103T-T09-4/tp/pull/72))
  * Reported bugs and gave meaningful feedback to the team
    (Examples: [1](https://github.com/AY2021S1-CS2103T-T09-4/tp/issues/183) [2](https://github.com/AY2021S1-CS2103T-T09-4/tp/issues/40) [3](https://github.com/AY2021S1-CS2103T-T09-4/tp/issues/38))
  * Managed releases `v1.3` - `v1.4` (3 releases) on GitHub

* **Enhancements to existing features**:
  * Wrote additional tests for existing features such as PlayCommand, StopCommand, TestCommand and Leitner.java
  (Pull requests [\#188](https://github.com/AY2021S1-CS2103T-T09-4/tp/pull/188) [\#185](https://github.com/AY2021S1-CS2103T-T09-4/tp/pull/185)
  * Updated the GUI color scheme
  (Pull requests [\#61](https://github.com/AY2021S1-CS2103T-T09-4/tp/pull/61) [\#44](https://github.com/AY2021S1-CS2103T-T09-4/tp/pull/44))
  * Fix several critical bugs such as the Select Command bug and the Answer Command Bug
  (Pull requests [\#180](https://github.com/AY2021S1-CS2103T-T09-4/tp/pull/180) [\#72](https://github.com/AY2021S1-CS2103T-T09-4/tp/pull/72))

* **Documentation**:
  * User Guide:
    * Added documentation for Chapter 1, What is Green Tea
    (Pull requests [\#126](https://github.com/AY2021S1-CS2103T-T09-4/tp/pull/126))
    * Added documentation for Chapter 2, About
    (Pull requests [\#126](https://github.com/AY2021S1-CS2103T-T09-4/tp/pull/126))
    * Added documentation for Chapter 3, Getting Started
    (Pull requests [\#126](https://github.com/AY2021S1-CS2103T-T09-4/tp/pull/126)
    [\#189](https://github.com/AY2021S1-CS2103T-T09-4/tp/pull/189))
    * Added documentation for Chapter 8, FAQ
    (Pull requests [\#126](https://github.com/AY2021S1-CS2103T-T09-4/tp/pull/126))
    * Added documentation for Chapter 9, Command Summary
    (Pull requests [\#126](https://github.com/AY2021S1-CS2103T-T09-4/tp/pull/126))
    * Did tweaks to existing documentation of Chapter 4, Using this Guide
    (Pull requests [\#189](https://github.com/AY2021S1-CS2103T-T09-4/tp/pull/189))
  * Developer Guide:
    * Wrote the puml code for all the new diagram in the DG
    (Pull requests [\#209](https://github.com/AY2021S1-CS2103T-T09-4/tp/pull/209)
    [\#204](https://github.com/AY2021S1-CS2103T-T09-4/tp/pull/204)
    [\#201](https://github.com/AY2021S1-CS2103T-T09-4/tp/pull/201))
    * Modified Chapter 3 of the DG
    (Pull requests [\#243](https://github.com/AY2021S1-CS2103T-T09-4/tp/pull/243)
    [\#240](https://github.com/AY2021S1-CS2103T-T09-4/tp/pull/240)
    [\#247](https://github.com/AY2021S1-CS2103T-T09-4/tp/pull/247))
    * Added the Flashcard portion of chapter 4
    (Pull requests [\#243](https://github.com/AY2021S1-CS2103T-T09-4/tp/pull/243)
    [\#218](https://github.com/AY2021S1-CS2103T-T09-4/tp/pull/218)
    [\#104](https://github.com/AY2021S1-CS2103T-T09-4/tp/pull/104))
  * Others:
    * Added the Leitner System psuedo code for team reference
    (Pull requests [\#65](https://github.com/AY2021S1-CS2103T-T09-4/tp/pull/65))
  * Credit:
    * Developer Guides and User Guides that were referenced can be found in our [README](https://github.com/AY2021S1-CS2103T-T09-4/tp/blob/master/README.md) (under coded bases referenced).

* **Community**:
  * Contributed to forum discussions (Examples: [1](https://github.com/nus-cs2103-AY2021S1/forum/issues/291) [2](https://github.com/nus-cs2103-AY2021S1/forum/issues/137) [3](https://github.com/nus-cs2103-AY2021S1/forum/issues/384))
