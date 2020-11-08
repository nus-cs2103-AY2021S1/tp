---
layout: page
title: Hwang Yong Kang's Project Portfolio Page
---

## Project: ProductiveNUS

ProductiveNUS is a desktop application targeted at Computing students of National University of Singapore (NUS) to help them manage and schedule their academic tasks efficiently. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the ability to import NUSMods timetable into ProductiveNUS.
  * What it does: Allows the user to import their NUSMods lesson information into ProductiveNUS.
  * Justification: This feature improves the product significantly because a user can conveniently refer to their timetable on ProductiveNUS instead of having to go to NUSMods. Other commands such as Schedule also works best if the user can import their weekly timetable.
  * Highlights: This enhancement retrieves data from the NUSMods API. A new model class Lesson is also implemented to support the import command.
  * Credits: Code implemented is adapted from [Baldeung link](https://www.baeldung.com/java-http-request) and [NUSMods API](https://api.nusmods.com/v2/).

* **New Feature**: Added the set/remove priorities in ProductiveNUS.
  * What it does: Allows the user to set the priority level of their assignments in ProductiveNUS, which is displayed as a coloured tag in the assignment card.
  * Justification: This feature improves the user's experience as it is easier for them to spot assignments that are of greater importance so as to better plan their schedule.
  * Highlights: This enhancement is compatible with the Find feature, where users are able to list assignments of a specific priority tag.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=hyngkng)

* **Project management**:
  * Managed releases `v1.2` - `v1.4` (3 releases) on GitHub

* **Enhancements to existing features**:
  * Updated the GUI color scheme (Pull requests [\#33](), [\#34]())
  * Wrote additional tests for existing features to increase coverage from 88% to 92% (Pull requests [\#36](), [\#38]())

* **Documentation**:
  * User Guide:
    * Added documentation for the features `delete` and `find` [\#72]()
    * Did cosmetic tweaks to existing documentation of features `clear`, `exit`: [\#74]()
  * Developer Guide:
    * Added implementation details of the `delete` feature.

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#12](), [\#32](), [\#19](), [\#42]()
  * Contributed to forum discussions (examples: [1](), [2](), [3](), [4]())
  * Reported bugs and suggestions for other teams in the class (examples: [1](), [2](), [3]())
  * Some parts of the history feature I added was adopted by several other class mates ([1](), [2]())

* **Tools**:
  * Integrated a third party library (Natty) to the project ([\#42]())
  * Integrated a new Github plugin (CircleCI) to the team repo

* _{you can add/remove categories in the list above}_
