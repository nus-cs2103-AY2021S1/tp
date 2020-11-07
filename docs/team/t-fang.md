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
  * What it means: basic management of Participation.
  * What changes made: 
    * Added dependencies of it in and related collections;
    * Added related test cases.
* **Model modification**: Update Participation class and create corresponding methods in Project. (Pull request: Pull request [\#71](https://github.com/AY2021S1-CS2103T-W10-3/tp/pull/71))
  * What it means: Participation class is updated after creation of the Task class
  * What changes made:
    * Added more methods in Participation.
* **New Feature**: Added task filter command. 
    (Pull request: Pull request [\#96](https://github.com/AY2021S1-CS2103T-W10-3/tp/pull/96))
    * What changes made: 
    * Created TaskFilterCommand
    * Created relevant parsers
    * Allowed user to filter tasks by assignee, deadline, or task name, progress, done status
    * Added relevant test cases.
* **New Feature**: Added a command to show all the tasks
    (Pull Request [\#138](https://github.com/AY2021S1-CS2103T-W10-3/tp/pull/138))
  * What changes made: 
    * Created AllTasksCommand
    * Updated MainCatalogueParser
* **New Feature**: Added a command to sort the task list
    (Pull Request [\#188](https://github.com/AY2021S1-CS2103T-W10-3/tp/pull/188))
  * What changes made: 
    * Created TaskSorterCommand and the relevant parser
    * Created several comparators in TaskComparators class
    * Created ArgumentMultimapUtil class to faciliate checking prefixes inside a Argument Multimap.
    * Updated MainCatalogueParser
    * Allowed user to sort the task list by task name, deadline, progress, and done status
    * Added relevant test cases
* **New Feature**: Added a command to delete a task from the task list
    (Pull Request [\#271](https://github.com/AY2021S1-CS2103T-W10-3/tp/pull/271))
  * What changes made: 
    * Created DeleteTaskCommand and the relevant parser
    * Updated MainCatalogueParser
    * Allowed user to delete a task from the task list
    * Added relevant test cases
* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=T-Fang&tabRepo=AY2021S1-CS2103T-W10-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code)
* **Enhancements to existing features**: Update task filter to support filtering by progress/isDone 
    (Pull Request [\#138](https://github.com/AY2021S1-CS2103T-W10-3/tp/pull/138))
    * What changes made: 
        * Added more prefixes
        * Updated TaskFilterCommandParser
        * Updated MainCatalogueParser
* **Enhancements to existing features**: Add Deadline support to AddTaskCommand and task filter
    (Pull Request [\#139](https://github.com/AY2021S1-CS2103T-W10-3/tp/pull/139))
    * What changes made: 
        * Allowed user to create tasks with deadlines (optional)
        * Fully supported filter tasks by deadline
* **Enhancements to existing features**: Update task filter to support filtering tasks within a time range 
    (Pull Request [\#188](https://github.com/AY2021S1-CS2103T-W10-3/tp/pull/188))
    * What changes made: 
        * Creates Date class
        * Updated relevant parsers
        * Allowed user to find tasks within a time period


* **Documentation**:
  * User Guide:
    * Added documentation for basic task-related features.
    * Came up with the first draft of feature list.
    * Added feature guide for `filter`
    * Added feature guide for `alltasks`  (Pull Request [\#149](https://github.com/AY2021S1-CS2103T-W10-3/tp/pull/149))
    * Added feature guide for `sort` (Pull Request [\#188](https://github.com/AY2021S1-CS2103T-W10-3/tp/pull/188))
    * Added feature guide for `deletetask` (Pull Request [\#271](https://github.com/AY2021S1-CS2103T-W10-3/tp/pull/271))
  * Developer Guide:
    * Wrote first draft of non-functional requirements. (Pull Request [\#54](https://github.com/AY2021S1-CS2103T-W10-3/tp/pull/54))
    * Added and updated Use Cases related to `filter`
    * Wrote the design of the `Logic` component. (Pull Request [\#131](https://github.com/AY2021S1-CS2103T-W10-3/tp/pull/131))
    * Wrote the implentation of filtering features. (Pull Request [\#146](https://github.com/AY2021S1-CS2103T-W10-3/tp/pull/146))
* **Community**:
  * Updated `README.md` for team repository.
* **Tools**:
