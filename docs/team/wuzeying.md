---
layout: page
title: Wu Zeying's Project Portfolio Page
---

## Project: DSAce (AddressBook Level 3)

DSAce is a desktop definition book application used for teaching Data Structures and Algorithms principles. The user interacts with it using a CLI, and it has a GUI created with JavaFX.
It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Priority
  * What it does: Allows users to assign priority of the flashcards. Users can sort the flashcards by priority.
  * Justification: This feature allows users to see which concept should be of their priority. Hence, easing their process of revision.
  * Highlights: This enhancement affects existing commands and commands to be added in future. It required an in-depth analysis of design alternatives. The implementation too was challenging as it required changes to existing commands such add and edit.

* **New Feature**: Added enter quiz and leave quiz command
  * What it does: Allows users to enter quiz and leave quiz interface
  * Justification: This feature allows users to enter a separate interface where flashcards are removed, quiz questions are shown and interface color scheme changes.

* **Bug fix**: Disabled invalid user input for all single word such as `Clear`. `Clear xx` is not supported now. (Pull requests [\#160]())

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=zeying99&sort=groupTitle&sortWithin=title&since=2020-08-14&until=2020-11-09&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)
* **Enhancements to existing features**:
  * Updated the GUI of priority (Pull requests [\#66]())
  * Connect quiz interface with logic and model
  * Enhance readability of exception alerts so that the users are more aware of the error.

* **Documentation**:
  * Developer Guide:
    * Added the `enter quiz` command sequence diagram.
  * User Guide:
    * Create an overall structure of User Guide.
  
* **Test cases**:
    * Added test cases for command and parser classes, such as `Clear` Command test and its parser.

* **Community**:
  * Reported bugs and suggestions for other teams in the class(https://github.com/zeying99/ped/issues/1#issue-732883559)
  

