---
layout: page
title: Zhou Zijian's Project Portfolio Page
---

## Introduction

This page serves to document my contributions to the project PlaNus under NUS module CS2103T in AY20/21 semester 1. 

## Project: PlaNus

PlaNus is a **task managing desktop application** for students in NUS with many projects and deadlines, 
optimized for use via a Command Line Interface (CLI) with the benefits of Graphical User Interface (GUI).
PlaNus reduces the time spent by students in task management as adding tasks and lessons is now simple and quick!


Given below are my contributions to the project.

* **New Features**: Added the ability to find tasks/lessons.
  * What it does: easy-to-use CLI commands to find a particular task or lesson according to a set of specified attributes.
  * Justifications: The `find-task` and `find-lesson` features are integral in managing a large amount of tasks and lessons.
  * Highlights: This feature requires deep integration with other features because the there are many attributes that a task/lesson
  can be searched for. Also, it requires some clever design to decide specifically what attributes to search so as to allow easy finding.

* **New Features**: Added the ability to edit/delete lesson.
  * What it does: allows user to edit or delete a lesson by a set of attributes. The calendar and data analysis will be updated as well.
  * Justifications: The ability to edit or delete a lesson is important in managing lessons. Users need to use this feature frequently to edit a lesson under
  many situations, for example the user makes a typo in the lesson title or the lesson is not relevant anymore.
  * Highlights: This feature requires a meticulous and creative design to ensure that the edited lesson is reflected on the calendar view
  as well as the data analysis as well.
 
* **New Features**: Refactor the `help` command from AB3.
  * What it does: refactor the help window when the user types `help` in the CLI. The new help window includes a set of command summary instead of
  simply providing a URL to the user guide as in the AB3.
  * Justifications: Allowing users to look up commands directly in PlaNus is much more user-friendly than asking users to navigate to the user guide for
  relevant information.
  * Highlights: The implementation requires good JavaFx skills to make sure the UI looks aesthetically appealing.
  
* **New Features**: Adds the ability to save lessons in JSON.
  * What it does: Allows lessons to be saved locally to a JSON.
  * Justifications: Similar to tasks, it is important that the lessons that a user adds to PlaNus is properly saved and can be referred next time
  PlaNus runs.
  * Highlights: As lesson is essentially a set of recurring tasks, a naive solution is to simply save all the recurring tasks to the JSON. However,
  this can results in too much data being saved locally and make PlaNus app too large, which contravenes with the objective of making PlaNus lightweight. So 
  instead of saving all recurring tasks, I decided to save only the lesson information and reconstruct all the recurring tasks based on the lessons read from
  the JSON file every time PlaNus runs.

* **Project management**:
  * Manage the milestones `v1.2`, `v1.3` and `v1.4` (including issues and deadlines) on GitHub. 

* **Code contributed**: 
  * My code contributions to PlaNus can be found via the [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=bobbyzhouzijian&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other).

* **Enhancement to existing features**:
  * Added test cases to many existing features and classes [\#274](https://github.com/AY2021S1-CS2103T-T12-3/tp/pull/274)
  * Refactored code to improve code quality [\#270](https://github.com/AY2021S1-CS2103T-T12-3/tp/pull/270), [\#267](https://github.com/AY2021S1-CS2103T-T12-3/tp/pull/267)

* **Documentation**:
  * User Guide:
    * Added documentation for the features `list` [\#59](https://github.com/AY2021S1-CS2103T-T12-3/tp/pull/59)
    * Added hyperlinks to Table of Content [\#74](https://github.com/AY2021S1-CS2103T-T12-3/tp/pull/74)
    * Add documentation to the `lesson`, `edit-lesson`, `find-lesson` and `delete-lesson` commands [\#186](https://github.com/AY2021S1-CS2103T-T12-3/tp/pull/186)
  * Developer Guide:
    * Updated NFRs and Glossaries [\#94](https://github.com/AY2021S1-CS2103T-T12-3/tp/pull/94)
    * Updated documentation for the section `Appendix:Requirements` [\#246](https://github.com/AY2021S1-CS2103T-T12-3/tp/pull/246)
    * Added `AddLessonSequenceDiagram`, `StatsClassDiagram`, `StatsDataSequenceDiagram` and `TimeAnalysisAcvitityDiagram` [\#275](https://github.com/AY2021S1-CS2103T-T12-3/tp/pull/275) 
    * Refactored existing UML diagrams in the `Implementation` section from ab3 to fit the design of PlaNus [\#275](https://github.com/AY2021S1-CS2103T-T12-3/tp/pull/275)

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#56](https://github.com/AY2021S1-CS2103T-T12-3/tp/pull/56),
  [\#61](https://github.com/AY2021S1-CS2103T-T12-3/tp/pull/61), [\#64](https://github.com/AY2021S1-CS2103T-T12-3/tp/pull/64),
  [\#65](https://github.com/AY2021S1-CS2103T-T12-3/tp/pull/65), [\#88](https://github.com/AY2021S1-CS2103T-T12-3/tp/pull/88),
  [\#107](https://github.com/AY2021S1-CS2103T-T12-3/tp/pull/107), [\#123](https://github.com/AY2021S1-CS2103T-T12-3/tp/pull/123),
  [\#127](https://github.com/AY2021S1-CS2103T-T12-3/tp/pull/127), [\#129](https://github.com/AY2021S1-CS2103T-T12-3/tp/pull/129),
  [\#135](https://github.com/AY2021S1-CS2103T-T12-3/tp/pull/135), [\#140](https://github.com/AY2021S1-CS2103T-T12-3/tp/pull/140),
  [\#142](https://github.com/AY2021S1-CS2103T-T12-3/tp/pull/142), [\#158](https://github.com/AY2021S1-CS2103T-T12-3/tp/pull/158),
  [\#165](https://github.com/AY2021S1-CS2103T-T12-3/tp/pull/165), [\#166](https://github.com/AY2021S1-CS2103T-T12-3/tp/pull/166),
  [\#173](https://github.com/AY2021S1-CS2103T-T12-3/tp/pull/173), [\#209](https://github.com/AY2021S1-CS2103T-T12-3/tp/pull/209)
  * Reported bugs and suggestions for other teams in the class via this [repo](https://github.com/BobbyZhouZijian/ped/issues)
