---
layout: page
title: New Jun Jie (Jet)'s Project Portfolio Page
---

## Project: ResiReg

ResiReg is a desktop residential management application used for managing a residential college such as Cinnamon College. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java and has about 20kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the ability allocate/ deallocate/ reallocate rooms to students (Allocation Feature).

  * What it does: allows the user, an admin, to allocate, deallocate or reallocate a room to a student to manage housing in ResiReg.
  * Justification: This feature improves the product significantly because an admin can now keep track of rooms allocated to students, in order to not make blunders, such as duplicate allocations.
  * Highlights: This enhancement affects existing and future rooms and students. It required an in-depth analysis of design alternatives. The implementation too was challenging as it required changes to existing commands.
  * Considerations: Trialed implementations of "Room contains Student" and "Student contains Room" with the aim of maximising simplicity of implementation, and eventually arrived at the decision of an `Allocation` association class that refers to a Room allocated to a Student, reducing duplicates in storage and space.

* **Feature Consideration**: Trialed the ability to view statistics about allocated and unallocated rooms using a visually appealing pie chart (Piechart Feature).
  * Justification: This feature improves the product significantly because an admin can now quickly determine the percentage usage of rooms in ResiReg, potentially opening plans for better usage of rooms over time.
  * Highlights: This enhancement improves the visual appeal of ResiReg and the data literacy of the admin user. The admin can easily communicate statistics about usage of rooms in ResiReg to other admins.
  * Conclusion: After much discussion with the development team, the team agreed that the statistics piechart, in consideration of the numerous other features of ResiReg, is unfortunately not well packaged as a single product with a single core principled functionality based on product design considerations, and is purged ([\#171](https://github.com/AY2021S1-CS2103-T16-3/tp/pull/171)).

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#search=jetnew&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=zoom&zA=jetnew&zR=AY2021S1-CS2103-T16-3%2Ftp%5Bmaster%5D&zACS=361.3333333333333&zS=2020-08-14&zFS=jetnew&zU=2020-10-29&zMG=false&zFTF=commit&zFGS=groupByRepos&zFR=false)

* **Project management**:
  * Contributed to releases `v1.2` - `v1.4` (3 releases) on GitHub.

* **Documentation**:
  * User Guide:
    * Added documentation for the Allocation Feature: [\#124](https://github.com/AY2021S1-CS2103-T16-3/tp/pull/124)
    * Added screenshot examples in the User Guide for the Allocation Feature: [\#131](https://github.com/AY2021S1-CS2103-T16-3/tp/pull/131)
  * Developer Guide:
    * Added documentation for the Allocation Feature: [\#124](https://github.com/AY2021S1-CS2103-T16-3/tp/pull/124)
    * Added implementation considerations, sequence diagrams and execution steps for the Allocation Feature: [\#130](https://github.com/AY2021S1-CS2103-T16-3/tp/pull/130)

* **Community**:
  * Feature Increment PRs pushed: [\#78](https://github.com/AY2021S1-CS2103-T16-3/tp/pull/78), [\#84](https://github.com/AY2021S1-CS2103-T16-3/tp/pull/84), [\#85](https://github.com/AY2021S1-CS2103-T16-3/tp/pull/85), [\#122](https://github.com/AY2021S1-CS2103-T16-3/tp/pull/121)
  * Unit Testing PRs pushed: [\#106](https://github.com/AY2021S1-CS2103-T16-3/tp/pull/106), [\#121](https://github.com/AY2021S1-CS2103-T16-3/tp/pull/121), [\#179](https://github.com/AY2021S1-CS2103-T16-3/tp/pull/179)
  
