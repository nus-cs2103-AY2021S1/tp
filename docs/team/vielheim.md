---
layout: page
title: Terence Ho Wei Yang's Project Portfolio Page
---

## Project: PIVOT (Police Investigation Virtual Organisational Tool)

PIVOT is a desktop application to assist the police investigators in keeping track of their investigations and relevant information. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 20 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the ability to undo/redo previous commands.
  * What it does: allows the user to undo all previous commands one at a time. Preceding undo commands can be reversed by using the redo command.
  * Justification: This feature improves the product significantly because a user can make mistakes in commands and the app should provide a convenient way to rectify them.
  * Highlights: This enhancement affects existing commands and commands to be added in future. It required an in-depth analysis of design alternatives. The implementation too was challenging as it required changes to existing commands.
  * Credits: *{mention here if you reused any code/ideas from elsewhere or if a third-party library is heavily used in the feature so that a reader can make a more accurate judgement of how much effort went into the feature}*

* **New Feature**: Added a history command that allows the user to navigate to previous commands using up/down keys.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&tabAuthor=Vielheim&tabRepo=AY2021S1-CS2103-F09-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code)

* **Project management**:
  * **Manage Team Progress and Tasks**:
    * Setting up Team Github Repo/Org: [Github Link](https://github.com/AY2021S1-CS2103-F09-2/tp)
    * Maintaining Issue Tracker and Milestones
    * Maintain existing documentation: README.md, AboutUs.md and SettingUp.md (Pull requests [\#44](https://github.com/AY2021S1-CS2103-F09-2/tp/pull/44), [\#51](https://github.com/AY2021S1-CS2103-F09-2/tp/pull/51), [\#71](https://github.com/AY2021S1-CS2103-F09-2/tp/pull/71))
    
  * **Code Quality and Standardisation**:
    * Ensured that the code follows the Coding Style/Principles taught in class (such as applying SLAP and having proper abstractions). (Pull request [\#138](https://github.com/AY2021S1-CS2103-F09-2/tp/pull/138))
    * Defensive Programming: Increased assertions, loggings and checks done in the code base to ensure that the code is defensive. (Pull requests [\#138](https://github.com/AY2021S1-CS2103-F09-2/tp/pull/138), [\#143](https://github.com/AY2021S1-CS2103-F09-2/tp/pull/143))
    
* **Enhancements to existing features**:
  * **Front-End Development:** Managed the GUI updates and implementations.
    * **Updated the GUI color scheme**: (Pull request [\#227](https://github.com/AY2021S1-CS2103-F09-2/tp/pull/227)) Implemented `BlueTheme.css` to serve as the main theme of the app while the original `DarkTheme.css` is converted for our `Archive` section.
    * **GUI Layout**: (Pull requests [\#111](https://github.com/AY2021S1-CS2103-F09-2/tp/pull/111), [\#227](https://github.com/AY2021S1-CS2103-F09-2/tp/pull/227)) Improved the GUI layout, such as implementing panels that are resizeable to improve readability of the content.
    * **Command Line Interaction**: (Pull requests [\#111](https://github.com/AY2021S1-CS2103-F09-2/tp/pull/111), [\#169](https://github.com/AY2021S1-CS2103-F09-2/tp/pull/169)) Integrated command updates in the GUI (such as switching of pages or tabs) when users enter the corresponding commands in the CLI. Users can thus change the GUI as they wish through the CLI instead of using a mouse.
    
  * **Testing:** 
    * Wrote additional tests for existing features to increase coverage from 54% to 66% (Pull request [\#158](https://github.com/AY2021S1-CS2103-F09-2/tp/pull/158))
    * Ensured that the tests for existing features in v1.3 sufficiently covers the code. (Pull request [\#158](https://github.com/AY2021S1-CS2103-F09-2/tp/pull/158))
    
* **Documentation**:
  * User Guide:
    * Adapted AB3 Introduction for PIVOT (Pull request [\#45](https://github.com/AY2021S1-CS2103-F09-2/tp/pull/45))
    * Updated Command Summary for PIVOT (Pull request [\#182](https://github.com/AY2021S1-CS2103-F09-2/tp/pull/182))
  * Developer Guide:
    * Updated Target User Profile, Value Proposition and User Stories. (Pull request [\#61](https://github.com/AY2021S1-CS2103-F09-2/tp/pull/61))
    * Updated Non-Functional Requirements and Glossary. (Pull request [\#64](https://github.com/AY2021S1-CS2103-F09-2/tp/pull/64))
    * Added Documentation for `State` and `Ui` Components. Included Class Diagrams for the Components for clarity (Pull request [\#149](https://github.com/AY2021S1-CS2103-F09-2/tp/pull/149))
    * Added `OpenCaseCommand` and `ReturnCommand`. Included Sequence Diagrams for the Commands for clarity (Pull request [\#149](https://github.com/AY2021S1-CS2103-F09-2/tp/pull/149))

* **Community**:
  * Reported bugs and suggestions for other teams in the class during [PE-Dry Run](https://github.com/Vielheim/ped/issues)

