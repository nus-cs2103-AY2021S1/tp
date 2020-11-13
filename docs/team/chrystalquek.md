---
layout: page
title: Chrystal Quek's Project Portfolio Page
---

### Project: Productiv

Productiv is a desktop application for product managers to organise their product-related information. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 20 kLoC.

Given below are my contributions to the project.

**Code contributed**: [RepoSense](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=chrystalquek)

##### New features and Enhancements

* **New Feature**: Added the ability to switch modes.
  * What it does: Allows the user to `switch` to the different modes of the app. The user can also click on the tabs in the navigation bar. Subsequent commands are executed with respect to the current mode.
  * Justification: This feature improves the user experience significantly because the user can just choose to see only deliverable, meeting or contact related information. This makes the application less cluttered and more organised.

    The user can also remember less commands, e.g. the `add` command word can be used to add a deliverable, meeting or contact, depending on the mode the user is currently in.
  * Highlights: This feature was very difficult to implement and required significant restructuring of the entire application. I had to make the UI change whenever there was a switch in mode and ensure that the subsequent commands were passed to the correct `LogicManager`s for execution.

* **Major enhancement**: Updated Person to user-facing Contact that is more useful for product-management.
  * What it does: Allows the user to CRUD contacts in Productiv.
  * Justification: Helps users keep track of the developers and stakeholders that are involved in the development of the product.
  * Highlights: Refactored Person to user-facing Contact. A Contact can have a `Role` (developer or stakeholder) and also a `Description`.
   After careful consideration, decided to remove attributes that would not be important for product-management such as `Address` and `Tag`.

* **Major enhancement**: Improved list UI. Created a table format for list UI and made sure to only display fields that are more important to the user.

* **Enhancements to existing features**:
  * Updated the GUI color scheme: [\#48](https://github.com/AY2021S1-CS2103T-F11-2/tp/pull/48)
  * Edited GUI for overall cohesiveness: [\#119](https://github.com/AY2021S1-CS2103T-F11-2/tp/pull/119)


##### Project management
  * Organised and lead some weekly team meetings.
  * Facilitated task delegation.
  * Ensured that deadlines were met.
    
##### Contributions to team-based tasks
  * Created skeleton for switching modes: [\#32](https://github.com/AY2021S1-CS2103T-F11-2/tp/pull/32), [\#106](https://github.com/AY2021S1-CS2103T-F11-2/tp/pull/106)
  * Necessary general code enhancements
      * Changed product icon and name and renamed jar file: [\#119](https://github.com/AY2021S1-CS2103T-F11-2/tp/pull/119)
      * Changed log file name: [\#188](https://github.com/AY2021S1-CS2103T-F11-2/tp/pull/188)
      * Updated Person to user-facing Contact that is more useful for product-management: [\#37](https://github.com/AY2021S1-CS2103T-F11-2/tp/pull/37), [\#67](https://github.com/AY2021S1-CS2103T-F11-2/tp/pull/67)
  * Enabled assertions in Gradle: [\#94](https://github.com/AY2021S1-CS2103T-F11-2/tp/pull/94)
  * Clarified and followed up with reviewers of PE-D on behalf of team: [\#4](https://github.com/khoodehui/ped/issues/4), [\#3](https://github.com/zhaohuanqdcn/ped/issues/3)
  * Standardized App feedback messages: [\#124](https://github.com/AY2021S1-CS2103T-F11-2/tp/pull/124)

##### Community
  * PRs reviewed (with non-trivial review comments): [\#105](https://github.com/AY2021S1-CS2103T-F11-2/tp/pull/105), [\#70](https://github.com/AY2021S1-CS2103T-F11-2/tp/pull/70), [#34](https://github.com/AY2021S1-CS2103T-F11-2/tp/pull/34)
  * Total PRs reviewed: 20+, Total comments given: 80+
  * Reported bugs and suggestions for other teams (during PE-D): [#11](https://github.com/chrystalquek/ped/issues/11), [#7](https://github.com/chrystalquek/ped/issues/7)

##### Documentation
  * User Guide:
    * Updated documentation for contact feature: [\#124](https://github.com/AY2021S1-CS2103T-F11-2/tp/pull/124)
    * Added documentation for dashboard feature : [\#216](https://github.com/AY2021S1-CS2103T-F11-2/tp/pull/216)
  * Developer Guide:
    * Added implementation details of the `switch` feature. Included UML diagrams `SwitchModeSequenceDiagram`, `SwitchModeActivityDiagram` and `SwitchModeMouseInputSequenceDiagram`: [\#241](https://github.com/AY2021S1-CS2103T-F11-2/tp/pull/241)
    * Updated almost the entire Appendix, including Instructions for Manual Testing and Effort. Also introduced a neater structure to the Appendix: [\#229](https://github.com/AY2021S1-CS2103T-F11-2/tp/pull/229)
