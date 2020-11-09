---
layout: page
title: Ong Li Jin's Project Portfolio Page
---

## Project: Ace CS2103/T

Ace CS2103/T is a desktop task manager application used for keeping track of tasks for CS2103/T Software Engineering. The user interacts with it using a CLI, and it has a GUI created with JavaFX.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense_link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=onglijin&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

* **Refactoring**: Refactored the code to introduce new classes for all components of Task under Model.
    * What it does: Allows each component of task to have its own class
    * Justification: This allows test code to be written in a way that each component can be tested independently and for the behaviour of the component to be contained within the class and be unknown to the public.
    * Highlights: This enhancement affects the Task class and all existing commands and commands to be added in the future. It required an in-depth analysis of the existing code. The implementation was challenging as it required changes to most of the exisitng files.

* **UI**: Added the UI components to display the week number as well as command feedback [\#86](https://github.com/AY2021S1-CS2103-T14-4/tp/pull/86)
    * What it does: Allows the logic to display the week number as well as command feedback in the components
    * Justification: The UI components are needed for the application to looked as planed

* **Documentation**:
  * User Guide:
    * Updated quick start of the documentation [\#5](https://github.com/AY2021S1-CS2103-T14-4/tp/pull/5)
    * Added documentation for the features `delete` and `exit` [\#5](https://github.com/AY2021S1-CS2103-T14-4/tp/pull/5)
    * Updated FAQ section of the documentation and the command summary [\#5](https://github.com/AY2021S1-CS2103-T14-4/tp/pull/5), [\#46](https://github.com/AY2021S1-CS2103-T14-4/tp/pull/46)
    * Update user guide to fix inconsistencies and errors [\#213](https://github.com/AY2021S1-CS2103-T14-4/tp/pull/213), [\#214](https://github.com/AY2021S1-CS2103-T14-4/tp/pull/214), [\#215](https://github.com/AY2021S1-CS2103-T14-4/tp/pull/215)

  * Developer Guide:
    * Updated the UML diagram for UI component and the description under UI.java [\#127](https://github.com/AY2021S1-CS2103-T14-4/tp/pull/127)
    * Updated the user stories [\#50](https://github.com/AY2021S1-CS2103-T14-4/tp/pull/50), [\#153](https://github.com/AY2021S1-CS2103-T14-4/tp/pull/153)
    * Contributed to the instruction for manual testing [\#320](https://github.com/AY2021S1-CS2103-T14-4/tp/pull/320)

* **Contributions to team-based tasks**:
  * Necessary general code enhancement:
    * Renaming the product [\#5](https://github.com/AY2021S1-CS2103-T14-4/tp/pull/5), [\#47](https://github.com/AY2021S1-CS2103-T14-4/tp/pull/47)
    * Changing the product icon [\#86](https://github.com/AY2021S1-CS2103-T14-4/tp/pull/86)
  * Enabled assertions in team repo [\#118](https://github.com/AY2021S1-CS2103-T14-4/tp/pull/118)
  * Fixed CI errors [\#275](https://github.com/AY2021S1-CS2103-T14-4/tp/pull/275)

* **Community**:
  * PRs reviewed : [\#7](https://github.com/AY2021S1-CS2103-T14-4/tp/pull/7), [\#103](https://github.com/AY2021S1-CS2103-T14-4/tp/pull/103), [\#131](https://github.com/AY2021S1-CS2103-T14-4/tp/pull/131)
