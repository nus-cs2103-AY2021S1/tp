---
layout: page
title: Lye Yi Xian's Project Portfolio Page
---

## Project: Wanderlust

WanderLust is a desktop travel-planning application that allows users to plan their trips in a structured and efficient
manner. It is optimised for CLI-proficient users, and comes with a GUI created with JavaFX. It is written in Java, and
has approximately 16 kLOC.

This application is part of my team project for an Introduction to Software Engineering (CS2103) module taken at NUS.

## Summary of Contributions

* **Code contributed**: [RepoSense](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=lyeyixian)

* **Major Enhancement**:
  * Added TravelPlanner and its relevant classes [\#56](https://github.com/AY2021S1-CS2103-T14-3/tp/pull/56)
    * What it does: The main class of Wanderlust which wraps all the other travel plan objects
  * Added Directory abstract class [\#159](https://github.com/AY2021S1-CS2103-T14-3/tp/pull/159)
    * What it does: Facilitate the switching of view between each TravelPlan and Wishlist
    * Highlight: Initially, encounter issue of needing to typecast everything as Wanderlust have different travel plan objects.
                 But we eventually figure out a way to work around it by utilising polymorphism and successfully remove
                 typecasting when using CRUD method on different travel plan objects.

* **Minor Enhancement**:
  * Helped in implementation of ObservableDirectory and Ui component [\#112](https://github.com/AY2021S1-CS2103-T14-3/tp/pull/112)
    * What it does: Allows the Ui to listen to changes in ObservableDirectory and updates to show the correct directory
  * Helped in implementation of showing total cost in Ui component [\#254](https://github.com/AY2021S1-CS2103-T14-3/tp/pull/254)
    * What it does: Binds the TextProperty to the StringProperty of Cost in ObservableDirectory, so it correctly updates when changes happened
    * Highlight: The problem we faced here is that we accidentally increased the coupling of classes in Ui, in particular TravelPlanPanel and TravelPlanObjectListPanel.
                 We managed to solve it by binding properties of JavaFX.

<div style="page-break-after: always;"></div>

* **Enhancements to existing features**:
  * Update Model and ModelManager to fit Wanderlust's implementations [\#75](https://github.com/AY2021S1-CS2103-T14-3/tp/pull/75)
  * Update the whole Storage package to fit Wanderlust's implementations [\#82](https://github.com/AY2021S1-CS2103-T14-3/tp/pull/82)

* **Testing, Bug Fixes, Code Quality Checking & Defensive Programming**
  * Added tests to TravelPlanner, ModelManager, UserPrefs to increase coverage from 40.74% to 57.18% [\#98](https://github.com/AY2021S1-CS2103-T14-3/tp/pull/98)
  * Added tests to the whole Storage package [\#130](https://github.com/AY2021S1-CS2103-T14-3/tp/pull/103)
  * Helped check the implementations and code quality of GoToCommand, SortCommand and their relevant classes and added some defensive code [\#176](https://github.com/AY2021S1-CS2103-T14-3/tp/pull/176), [\#185](https://github.com/AY2021S1-CS2103-T14-3/tp/pull/185)
  * Fixed bugs found in PE dry run [\#243](https://github.com/AY2021S1-CS2103-T14-3/tp/pull/243), [\#245](https://github.com/AY2021S1-CS2103-T14-3/tp/pull/245), [\#246](https://github.com/AY2021S1-CS2103-T14-3/tp/pull/246), [\#247](https://github.com/AY2021S1-CS2103-T14-3/tp/pull/247)

* **Project management**:
  * Lead the team
  * Assign roles and responsibilities to each team member
  * Manage weekly deadline and make sure the team is on track every week
  * Plan and delegate weekly tasks
  * Track team member's weekly progress
  * Maintain issue tracker and milestone
  * Schedule weekly meetings
  * Contribute to discussions
  * Define and enforce branch protection rules to master branch of team repo

* **Team-Based Tasks**:
  * Updated product website (renaming, updating links): [\#33](https://github.com/AY2021S1-CS2103-T14-3/tp/pull/33)
  * Morph AB3 into Wanderlust (remove ab3 code, rename packages) [\#136](https://github.com/AY2021S1-CS2103-T14-3/tp/pull/136)
  * In charge of integration, making sure everything works when merging into master branch

* **Documentation**:
  * User Guide:
    * Add documentation for the features `goto` and `find`: [\#20](https://github.com/AY2021S1-CS2103-T14-3/tp/pull/20), [\#174](https://github.com/AY2021S1-CS2103-T14-3/tp/pull/174)
  * Developer Guide:
    * Add 10 use cases [\#32](https://github.com/AY2021S1-CS2103-T14-3/tp/pull/32)
    * Add implementation details of the `find` feature [\#141](https://github.com/AY2021S1-CS2103-T14-3/tp/pull/141)
    * Update design section to reflect Wanderlust implementations (Class Diagrams, Sequence Diagrams) [\#140](https://github.com/AY2021S1-CS2103-T14-3/tp/pull/140)

* **Community**:
  * Total PRs reviewed: 57
  * PRs reviewed (with non-trivial review comments):
  [\#29](https://github.com/AY2021S1-CS2103-T14-3/tp/pull/29),
  [\#45](https://github.com/AY2021S1-CS2103-T14-3/tp/pull/45),
  [\#81](https://github.com/AY2021S1-CS2103-T14-3/tp/pull/81),
  [\#86](https://github.com/AY2021S1-CS2103-T14-3/tp/pull/86),
  [\#101](https://github.com/AY2021S1-CS2103-T14-3/tp/pull/101),
  [\#132](https://github.com/AY2021S1-CS2103-T14-3/tp/pull/132),
  [\#139](https://github.com/AY2021S1-CS2103-T14-3/tp/pull/139),
  [\#157](https://github.com/AY2021S1-CS2103-T14-3/tp/pull/157),
  [\#158](https://github.com/AY2021S1-CS2103-T14-3/tp/pull/158),
  [\#189](https://github.com/AY2021S1-CS2103-T14-3/tp/pull/189),
  [\#242](https://github.com/AY2021S1-CS2103-T14-3/tp/pull/242)
  * Reported bugs and suggestions for other teams in the class: [Reported 12 Bugs in PED](https://github.com/lyeyixian/ped/issues)
