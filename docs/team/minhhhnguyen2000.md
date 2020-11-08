---
layout: page
title: Nguyen Hoang Hai Minh's Project Portfolio Page
---

## Project: ProductiveNUS

ProductiveNUS is a desktop application targeted at Computing students of National University of Singapore (NUS) to help them manage and schedule their academic tasks efficiently. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the ability to suggest a possible start time and end time to work assignments.
    * What it does: allows the user to provide the estimated time to complete assignments and the period that the user want to work on them.
    A possible suggested time slot within the period will be generated for the user.
    * Justification: This feature improves the product significantly because a user may have many assignments and can rely on the app to generate a possible working schedule.
    * Highlights: This enhancement and its implementation require an in-depth analysis of scheduling algorithm.
    
* **New Feature**: Added the ability to undo previous commands.
    * What it does: allows the user to undo all previous commands one at a time.
    * Justification: This feature improves the product significantly because a user can make mistakes in commands and the app should provide a convenient way to rectify them.
    * Highlights: This enhancement affects existing commands and commands to be added in future. It required an in-depth analysis of design alternatives.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=minhhhnguyen2000)

* **Project management**:
  * Managed releases `v1.2` - `v1.4` (3 releases) on GitHub

* **Enhancements to existing features**:
  * Updated the GUI to support all new features
  * Wrote additional tests for existing features to increase coverage

* **Documentation**:
  * User Guide:
    * Added documentation for the features `schedule`, `unschedule` and `undo`
    * Added documentation for `FAQ`
  * Developer Guide:
    * Added implementation details of the `schedule`, `unschedule` and `undo` features.
    * Added design consideration of the `GUI`

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#12](), [\#32](), [\#19](), [\#42]()
  * Contributed to forum discussions (examples: [1](), [2](), [3](), [4]())
  * Reported bugs and suggestions for other teams in the class (examples: [1](), [2](), [3]())

* **Tools**:
  * Integrated a third party library (Natty) to the project ([\#42]())
  * Integrated a new Github plugin (CircleCI) to the team repo

* _{you can add/remove categories in the list above}_
