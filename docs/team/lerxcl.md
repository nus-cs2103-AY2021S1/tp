---
layout: page
title: Chua Chen Ler's Project Portfolio Page
---

## Project: Lifebook

Lifebook is a desktop task management application used for teaching Software Engineering principles. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 13 kLoC.

Given below are my contributions to the project.

* **Added the ability to filter tasks due at/before a specified date/time**
  * What it does: It allows users to search todos/meetings due at/before a certain date/time.
  * Justification: This feature improves the product functionality because a user may have a long task-list and it could be hard to search for specific deadlines.
  * Highlights: The implementation of this feature requires accessing the task-list and each task's deadline.

* **Added ability to view tasks that are due soon (within 1 week)**
  * What it does: At the bottom right-hand corner of Lifebook, a list of tasks is shown that are due 1 week from the current date/time.
  * Justification: This feature improves the product usefulness as the user will be more inclined to use Lifebook for the ease of looking at tasks that are due soon.
  * Highlights: It is a little challenging to incorporate the "Due by" panel into Lifebook, and I have to ensure that the "Due soon" panel is updated dynamically each time the user does a task operation.

* **Add a common tag to both a contact and a task**
  * What it does: With a single command, you can add the same tag(s) to a particular contact and task.
  * Justification: This feature helps to speed up the process of adding a tag to a contact and a task if the user decides to have a commonn tag.
  * Highlights: Instead of writing all new code, I have used the "tagging" feature for a contact and task (individually) to reduce chance of bugs and overlaps.

* **Added ability to add a recurring task**
  * What it does: A recurring task is created when a user includes the optional `recurring/` field in adding a new todo/event.
                  When a recurring task is marked as done, it will automatically generate another recurring task with the new deadline based on the recurrence field. (while the remaining details of the task remains the same)
  <div style="page-break-after: always;"></div>
  * Justification: This feature improves the product functionality as the user would not have to manually add recurring tasks every time.
  * Highlights: I have to ensure that the recurring task is generated properly after it is marked as done. We have considered whether should the "done" recurrence task be deleted automatically too, but we decide to leave it in case the user wants to trace back their done tasks.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=f12&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&tabAuthor=lerxcl&tabRepo=AY2021S1-CS2103T-F12-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other)

* **Project management**:
  * Created release `v1.3(trial)` on [GitHub](https://github.com/AY2021S1-CS2103T-F12-4/tp/releases/tag/v1.3a)
  * Created release `v1.4` on [GitHub](https://github.com/AY2021S1-CS2103T-F12-4/tp/releases/tag/v1.4)

* **Enhancements to existing features**:
  * Wrote tests for the basic task operations to increase coverage from 49.89% to 56.75% (Pull requests [\#69](https://github.com/AY2021S1-CS2103T-F12-4/tp/pull/69))
  * Wrote tests for:
    - Filter function [\#71](https://github.com/AY2021S1-CS2103T-F12-4/tp/pull/71)
    - Recurring function [\#98](https://github.com/AY2021S1-CS2103T-F12-4/tp/pull/98)
    - Common tag for contact and task [\#132](https://github.com/AY2021S1-CS2103T-F12-4/tp/pull/132)
    - Due soon [\#257](https://github.com/AY2021S1-CS2103T-F12-4/tp/pull/257)

  * Modify GUI to show "Recurring task" [\#125](https://github.com/AY2021S1-CS2103T-F12-4/tp/pull/125)
  * Modify GUI to show "Due soon" panel [\#126](https://github.com/AY2021S1-CS2103T-F12-4/tp/pull/126)

  * Modify delete task command [\#129](https://github.com/AY2021S1-CS2103T-F12-4/tp/pull/129)
  * Improve validation of date and time input when adding a task [\#194](https://github.com/AY2021S1-CS2103T-F12-4/tp/pull/194)

* **Documentation**:
  * User Guide:
    * Edited initial documentation to match Lifebook description. [\#13](https://github.com/AY2021S1-CS2103T-F12-4/tp/pull/13)
    * Fix commands syntax (Pull requests [\#78](https://github.com/AY2021S1-CS2103T-F12-4/tp/pull/78), [\#88](https://github.com/AY2021S1-CS2103T-F12-4/tp/pull/88))
    * Added documentation for the features:
        - Filter function [\#16](https://github.com/AY2021S1-CS2103T-F12-4/tp/pull/16)
        - Recurring function (Pull requests [\#98](https://github.com/AY2021S1-CS2103T-F12-4/tp/pull/98), [\#125](https://github.com/AY2021S1-CS2103T-F12-4/tp/pull/125))
        - Common tag for contact and task [\#132](https://github.com/AY2021S1-CS2103T-F12-4/tp/pull/132)
    * Did cosmetic tweaks to command summary : [\#138](https://github.com/AY2021S1-CS2103T-F12-4/tp/pull/138)
    * Update release link for v1.3a [\#121](https://github.com/AY2021S1-CS2103T-F12-4/tp/pull/121)
    * Update release link for v1.4 [\#274](https://github.com/AY2021S1-CS2103T-F12-4/tp/pull/274)
    * Added more notices and warnings (Pull requests [\#243](https://github.com/AY2021S1-CS2103T-F12-4/tp/pull/243), [\#271](https://github.com/AY2021S1-CS2103T-F12-4/tp/pull/271), [\#266](https://github.com/AY2021S1-CS2103T-F12-4/tp/pull/266))
    * Resize some images [\#273](https://github.com/AY2021S1-CS2103T-F12-4/tp/pull/273)
  * Developer Guide:
    * Added MSS + use case for `filter`, `recurring task` and `contactTaskTag` (Pull requests [\#19](https://github.com/AY2021S1-CS2103T-F12-4/tp/pull/19), [\#113](https://github.com/AY2021S1-CS2103T-F12-4/tp/pull/113), [\#239](https://github.com/AY2021S1-CS2103T-F12-4/tp/pull/239))
    * Added implementation details of the `filter` feature (Pull requests [\#113](https://github.com/AY2021S1-CS2103T-F12-4/tp/pull/113), [\#114](https://github.com/AY2021S1-CS2103T-F12-4/tp/pull/114), [\#115](https://github.com/AY2021S1-CS2103T-F12-4/tp/pull/115))
    * Added implementation details of the `contactTaskTag` feature [\#239](https://github.com/AY2021S1-CS2103T-F12-4/tp/pull/239) 
    * Added manual testing instructions for `adding task`, `filter`, `due soon` and `contactTaskTag` (Pull requests [\#239](https://github.com/AY2021S1-CS2103T-F12-4/tp/pull/239), [\#271](https://github.com/AY2021S1-CS2103T-F12-4/tp/pull/271))
    * Standardize UML diagram colors [\#254](https://github.com/AY2021S1-CS2103T-F12-4/tp/pull/242)
    * Added brief "Effort" section [\#271](https://github.com/AY2021S1-CS2103T-F12-4/tp/pull/271)

* **Community**:
  * PRs reviewed with non-trivial comments: (Pull requests [\#270](https://github.com/AY2021S1-CS2103T-F12-4/tp/pull/270), [\#240](https://github.com/AY2021S1-CS2103T-F12-4/tp/pull/240), [\#232](https://github.com/AY2021S1-CS2103T-F12-4/tp/pull/232))
  * Contributed to forum discussions:
    - Sharing tips: [Testing your application using Windows Sandbox](https://github.com/nus-cs2103-AY2021S1/forum/issues/227), [iP .jar tip](https://github.com/nus-cs2103-AY2021S1/forum/issues/174)
    - Asking for help: [Unable to display ✓ and ✘](https://github.com/nus-cs2103-AY2021S1/forum/issues/64)

* **Other contributions**:
  * Contributed to the creation of Lifebook's demonstration video. (Script and voice-over)
