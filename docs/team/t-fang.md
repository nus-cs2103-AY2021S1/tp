---
layout: page
title: Tian Fang's Project Portfolio Page
---

## Project: Taskmania

Taskmania (based off AB3) is a **desktop app for a project leader to manage team members and tasks** to be finished in a
 software project, optimized for use via a Command Line Interface (CLI) while still having the benefits of a 
 Graphical User Interface (GUI). If you can type fast, Taskmania can allow you to manage your team faster than 
 a traditional point and click interface.

Given below are my contributions to the project.

* **Model modification**: Added basic dependencies of Participation.
  * What it means: created attribute and methods related to Participation class in Project and Person class.
* **Model modification**: Updated Participation class after the creation of the Task class (Pull request [\#71](https://github.com/AY2021S1-CS2103T-W10-3/tp/pull/71))
  * What it means: added more and updated methods in Participation and create corresponding methods in Project.
* **New Feature**: Added `TaskFilterCommand` to filter the task list (Pull request [\#96](https://github.com/AY2021S1-CS2103T-W10-3/tp/pull/96))
    * What it does: allows the user to filter the task list by task's assignee, deadline, task name
    * Justification: This feature is one of the core features that we promised to deliever as this command allows our target users to find desired tasks effortlessly, which is crucial to their project management.
* **New Feature**: Added `AllTasks` command to show all the tasks (Pull Request [\#138](https://github.com/AY2021S1-CS2103T-W10-3/tp/pull/138))
    * What is does: allows the user to clear the task filter and view all the tasks in the project.
* **New Feature**: Added `TaskSorterCommand` to sort the task list (Pull Request [\#188](https://github.com/AY2021S1-CS2103T-W10-3/tp/pull/188))
  * What is does: allows the user to sort the task list by task name, deadline, progress, and done status
  * Justification: Users may want to change the way the task list is presented so that the tasks are sorted in a desired order. This can provide more insights into the status of different tasks (e.g. sort by deadline shows tasks that are due soon at the top of the task list).
* **New Feature**: Added `DeleteTaskCommand` to delete a task from the task list (Pull Request [\#271](https://github.com/AY2021S1-CS2103T-W10-3/tp/pull/271))
    * What is does: allows the user to delete a task from the task list and remove all information related to that task.
* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=T-Fang&tabRepo=AY2021S1-CS2103T-W10-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code). Note: we initially had a Meeting class, but we decided to remove it due to time constraints. Therefore, some of my codes written for the Meeting class may not be reflected on the RepoSense such as Meeting Filter (Pull Request [\#135](https://github.com/AY2021S1-CS2103T-W10-3/tp/pull/135)) and AllMettingsCommand (Pull Request [\#138](https://github.com/AY2021S1-CS2103T-W10-3/tp/pull/138))
* **Enhancements to existing features**: 
    * Updated the task filter to support filtering by task's progress, or done status (Pull Request [\#138](https://github.com/AY2021S1-CS2103T-W10-3/tp/pull/138))
    * Updated `AddTaskCommand` to enable creating tasks with deadlines (Pull Request [\#139](https://github.com/AY2021S1-CS2103T-W10-3/tp/pull/139))
    * Created `Date` and `ArgumentMultimapUtil` classes, and updated the task filter to support finding tasks within a time range (Pull Request [\#188](https://github.com/AY2021S1-CS2103T-W10-3/tp/pull/188))


* **Documentation**:
  * User Guide:
    * Added documentation for the features `filter`, `alltasks`, `sort`, and `deletetask` (Pull Requests [\#149](https://github.com/AY2021S1-CS2103T-W10-3/tp/pull/149) [\#188](https://github.com/AY2021S1-CS2103T-W10-3/tp/pull/188) [\#271](https://github.com/AY2021S1-CS2103T-W10-3/tp/pull/271))
    * Fixed some command format and term inconsistency (Pull Request [\#323](https://github.com/AY2021S1-CS2103T-W10-3/tp/pull/323))
  * Developer Guide:
    * Wrote first draft of non-functional requirements (Pull Request [\#54](https://github.com/AY2021S1-CS2103T-W10-3/tp/pull/54))
    * Added and updated Use Cases related to `filter`
    * Modified the `Logic` component section in DG. (Pull Request [\#131](https://github.com/AY2021S1-CS2103T-W10-3/tp/pull/131))
    * Added the implentation of the `filter` feature (Pull Request [\#146](https://github.com/AY2021S1-CS2103T-W10-3/tp/pull/146))
* **Testing**: 

  * Wrote test cases for commands that I created: (Pull Requests [\#188](https://github.com/AY2021S1-CS2103T-W10-3/tp/pull/188) [\#271](https://github.com/AY2021S1-CS2103T-W10-3/tp/pull/271))
