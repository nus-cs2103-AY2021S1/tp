---
layout: page
title: Terence Ho Wei Yang's Project Portfolio Page
---


## Project: PIVOT (Police Investigation Virtual Organisational Tool)

PIVOT is a desktop application to assist the police investigators in keeping track of their investigations and relevant information. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 20 kLoC.

Given below are my contributions to the project.  

* **New Feature**: Implemented `Suspect` class in PIVOT. (Pull request [\#78](https://github.com/AY2021S1-CS2103-F09-2/tp/pull/78))
  * What was done: Set up the base class `Suspect` and refactored `Case` class to accommodate for the `Suspect`.
  * What it does: The classes will then further be used by the team to develop the `Case` class, a major component in PIVOT.

* **New Feature**: Implemented `Description` and `Alphanumeric` class in PIVOT. (Pull request [\#81](https://github.com/AY2021S1-CS2103-F09-2/tp/pull/81))
  * What was done: Set up the base class `Description` and refactored `Case` class to accommodate for the `Description`.
  * Abstracted most of the common functionalities of `Title` and `Name` into a parent `Alphanumeric` class.
  * What it does: The classes will then further be used by the team to develop the `Case` class, a major component in PIVOT.

* **New Feature**: Implemented `AddDescriptionCommand` in PIVOT. (Pull request [\#126](https://github.com/AY2021S1-CS2103-F09-2/tp/pull/126))
  * What was done: Set up the command class as well as its `Parser` to parse the command in PIVOT.
  * What it does: The user can now add a `Description` to a `Case` by calling `add desc d:[description]`.

These enhancements affects existing and future commands. It was carefully designed to integrate with the existing architecture, keeping in mind of future extensions.  

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&tabAuthor=Vielheim&tabRepo=AY2021S1-CS2103-F09-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code)  
<div style="page-break-after: always;"></div>

* **Project management**:
  * **Manage Team Progress and Tasks**:
    * Setting up Team Github Repo/Org, Issue Tracker and Milestones: [Github Link](https://github.com/AY2021S1-CS2103-F09-2/tp)
    * Maintain existing documentation: README.md, AboutUs.md and SettingUp.md (Pull requests [\#44](https://github.com/AY2021S1-CS2103-F09-2/tp/pull/44), [\#51](https://github.com/AY2021S1-CS2103-F09-2/tp/pull/51), [\#71](https://github.com/AY2021S1-CS2103-F09-2/tp/pull/71))
    
  * **Code Quality and Standardisation**:
    * Ensured that the code follows the Coding Style/Principles taught in class (applying SLAP and having proper abstractions). (Pull request [\#138](https://github.com/AY2021S1-CS2103-F09-2/tp/pull/138))
    * Defensive Programming: Increased assertions, loggings and checks done in the code base to ensure that the code is defensive. (Pull requests [\#138](https://github.com/AY2021S1-CS2103-F09-2/tp/pull/138), [\#143](https://github.com/AY2021S1-CS2103-F09-2/tp/pull/143))  

* **Enhancements to existing features**:
  * **Front-End Development:** Managed the GUI updates and implementations.
    * **Updated the GUI color scheme**: (Pull request [\#227](https://github.com/AY2021S1-CS2103-F09-2/tp/pull/227)) Implemented `BlueTheme.css` as the main theme of the app. The original `DarkTheme.css` is converted for our `Archive` section.
    * **GUI Layout**: (Pull requests [\#111](https://github.com/AY2021S1-CS2103-F09-2/tp/pull/111), [\#227](https://github.com/AY2021S1-CS2103-F09-2/tp/pull/227)) Improved the GUI layout, such as implementing resizeable panels to improve readability of the content.
    * **Command Line Interaction**: (Pull requests [\#111](https://github.com/AY2021S1-CS2103-F09-2/tp/pull/111), [\#169](https://github.com/AY2021S1-CS2103-F09-2/tp/pull/169)) Integrated updates in the GUI when users enter the corresponding commands in the CLI.
    
  * **Testing:** 
    * Wrote additional tests for existing features to increase coverage from 54% to 66%, ensuring the code is covered sufficiently for v1.3 (Pull request [\#158](https://github.com/AY2021S1-CS2103-F09-2/tp/pull/158))

* **Documentation**:
  * User Guide:
    * Adapted AB3 Introduction for PIVOT (Pull request [\#45](https://github.com/AY2021S1-CS2103-F09-2/tp/pull/45))
    * Updated Quick Start into different sections (Pull request [\#242](https://github.com/AY2021S1-CS2103-F09-2/tp/pull/242))
    * Updated Command Summary for PIVOT (Pull request [\#182](https://github.com/AY2021S1-CS2103-F09-2/tp/pull/182))
  * Developer Guide:
    * Updated Target User Profile, Value Proposition and User Stories. (Pull request [\#61](https://github.com/AY2021S1-CS2103-F09-2/tp/pull/61))
    * Updated Non-Functional Requirements and Glossary. (Pull request [\#64](https://github.com/AY2021S1-CS2103-F09-2/tp/pull/64))
    * Added Documentation for `State` and `Ui` Components. Included Class Diagrams for the Components for clarity (Pull request [\#149](https://github.com/AY2021S1-CS2103-F09-2/tp/pull/149))
    * Added `OpenCaseCommand` and `ReturnCommand`. Included Sequence Diagrams for the Commands for clarity (Pull request [\#149](https://github.com/AY2021S1-CS2103-F09-2/tp/pull/149))  


* **Community**:
  * Reported bugs and suggestions for other teams in the class during [PE-Dry Run](https://github.com/Vielheim/ped/issues)
