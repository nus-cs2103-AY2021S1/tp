---
layout: page
title: Cheak Han Wei's Project Portfolio Page
---

## Project: TR4CKER

TR4CKER is a desktop app that helps SoC students track their tasks, meet their deadlines, and stay productive. It is
optimized for Computing students familiar with CLI, who can manage their tasks efficiently by typing in commands.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=hanweic53)

* **Morphing of AddressBook:**
  * Refactor Person name to Task name. [\#39](https://github.com/AY2021S1-CS2103T-T10-2/tp/pull/39)

* **Change to existing feature**: Updated `add` command.
    * Change: Let Deadline be an optional parameter and set a default Deadline (Today, 2359) when users do not enter a Deadline.
    * Justification: Adds convenience to users especially when users want to create a Task that's due by Today to include in the Daily Tab.

* **New Feature**: Tasks are split into 3 Categories.
  * What it does: Displays tasks separately as 3 Task lists namely Pending Tasks, Expired Tasks and Completed Tasks, in the Home tab.
  * Justification: Makes the viewing of Tasks easier as compared to one long consolidated task list. 
  * Highlights: Allows users to manipulate tasks in different Categories separately.

* **New Feature**: Add `delete expired` and `edit expired` commands.
  * What it does: Allows users to edit the deadlines of expired tasks that are overdue but incomplete.
   It also gives users a chance to remove the tasks completely. 
  * Justification: Having overdue incomplete tasks is an area of concern to the users and users will want to either 
  continue working on them or remove them from TR4CKER completely. 
  * Highlights: Once users edit the Deadline of an expired task, they can only enter a future Deadline and the expired task
  will be shifted from the Expired Tasks panel to the Pending Tasks Panel immediately.
      
* **New Feature**: Support the use of Natural Deadlines and more than one DateTime format.
  * What it does: Allows users to use natural deadlines (E.g. Today, Monday, Sunday) 
  and other DateTime formats (E.g. 01-01-2020, 01-Jan-2020).
  * Justification: Adds convenience to users especially in cases whereby the users know that they have a tasks due next week, and they don't
  need to check the calendar for the exact date when they add the task into TR4CKER.
  * Highlights: Users can just enter the date without the time and TR4CKER will set 2359 as the default Deadline timing.
  This adds convenience to the users when they only have the deadline day in mind and a specific timing is not required. 

* **Enhancements**:
  * Updated UI appearance of Command Box and Home tab. [\#52](https://github.com/AY2021S1-CS2103T-T10-2/tp/pull/52), [\#82](https://github.com/AY2021S1-CS2103T-T10-2/tp/pull/82),
   [\#84](https://github.com/AY2021S1-CS2103T-T10-2/tp/pull/84)
  * Updated UI appearance of Task List Panel to 3 different Task List Panels: [\#98](https://github.com/AY2021S1-CS2103T-T10-2/tp/pull/98)
  
* **Documentation**:
  * User Guide:
    * Added Table of Contents and Summary of Commands table. [\#130](https://github.com/AY2021S1-CS2103T-T10-2/tp/pull/130)
    * Added Navigation guide for switching between tabs. [\#213](https://github.com/AY2021S1-CS2103T-T10-2/tp/pull/213)
    * Edited documentation for CRUD and other commands for tasks. [\#213](https://github.com/AY2021S1-CS2103T-T10-2/tp/pull/213)
    * Added documentation for the `edit expired` and `delete expired` commands. [\#213](https://github.com/AY2021S1-CS2103T-T10-2/tp/pull/213)
  * Developer Guide:
    * Added Use cases (UC01 - UC03) under Appendix: Requirements. [\#16](https://github.com/AY2021S1-CS2103T-T10-2/tp/pull/16)
    * Added documentation for the feature `Categorised Tasks Handling feature in Home tab`: [\#223](https://github.com/AY2021S1-CS2103T-T10-2/tp/pull/223)
    * Add instructions for manual testing: [\#223](https://github.com/AY2021S1-CS2103T-T10-2/tp/pull/223)

* **Team-based tasks**:
  * Project management:
    * Managed release `v1.2` on GitHub

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#44](https://github.com/AY2021S1-CS2103T-T10-2/tp/pull/44), [\#187](https://github.com/AY2021S1-CS2103T-T10-2/tp/pull/187)
  * Reported bugs and suggestions for other teams in the class (during Practical Dry Run): [Issues link](https://github.com/hanweic53/ped/issues)
