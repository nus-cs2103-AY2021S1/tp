---
layout: page
title: Li Jiayu's Project Portfolio Page
---

## Taskmania

Taskmania (based off AB3) is a **desktop app for a project leader to manage team members and tasks** to be finished in a
 software project, optimized for use via a Command Line Interface (CLI) while still having the benefits of a 
 Graphical User Interface (GUI). If you can type fast, Taskmania can allow you to manage your team faster than 
 a traditional point and click interface.

Given below are my contributions to the project.

* **Model modification**: Came up with the overall structure for Taskmania and a guideline for adapting from AB3.

* **Model modification**: Refactored Person in AB3 as Project and AddressBook as MainCatalogue. (Pull request [\#2](https://github.com/AY2021S1-CS2103T-W10-3/tp/pull/2))
  * What it means: projects are the list of objects the application mainly holds, and main catalogue holds all projects.
  * What changes made: 
    * replaced all person with project;
    * replaced all addressBook with mainCatalogue. 

* **Model modification**: Created Task object and added basic dependencies. (Pull request: Pull request [\#21](https://github.com/AY2021S1-CS2103T-W10-3/tp/pull/21))
  * What it means: a task in a project that can be assigned to teammates.
  * What changes made: 
    * created the class from scratch;
    * added dependencies of it in Project, Participation, and related collections;
    * added related test cases.

* **UI modification**: Added another status for Project card. (Pull request: Pull request [\#63](https://github.com/AY2021S1-CS2103T-W10-3/tp/pull/63))
  * What changes made: 
    * added status field in relevant model and logic classes;
    * modified the ProjectCard to accommodate 2 different scope status;
    * allowed the GUI to output different status of ProjectCard;
    * added relevant test cases.

* **UI modification**: Created Person dashboard. (Pull request: Pull request [\#176](https://github.com/AY2021S1-CS2103T-W10-3/tp/pull/176))
  * What changes made:
    * created Person dashboard to display details of a person;
    * added relevant getters in some model classes.

* **New Feature**: Added navigation commands. (Pull request: Pull request [\#63](https://github.com/AY2021S1-CS2103T-W10-3/tp/pull/63), [\#120](https://github.com/AY2021S1-CS2103T-W10-3/tp/pull/120), [\#176](https://github.com/AY2021S1-CS2103T-W10-3/tp/pull/176))
  * What changes made: 
    * designed the implementation of navigation scoping features;
    * created StartProjectCommand, StartPersonCommand, ViewTaskCommand and LeaveCommand;
    * created relevant parsers;
    * allowed change of scope status and ProjectCard status by these commands;
    * added relevant test cases;
    * fixed bugs to accommodate later implementations.

* **New Feature**: Added assign command. (Pull request: Pull request [\#87](https://github.com/AY2021S1-CS2103T-W10-3/tp/pull/87))
  * What changes made: 
    * created AssignCommand and AssignCommandParser;
    * created relevant methods in MainCatalogueParser and model;
    * added relevant test cases.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=lll-jy&tabRepo=AY2021S1-CS2103T-W10-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other)

* **Project management**:
  * Managed all contributions of Model modifications.
  * Managed contributions of scoping-related features.

* **Enhancements to existing features**:

* **Documentation**:
  * User Guide:
    * Added the teammate-related features' guide. (Pull Request [\#46](https://github.com/AY2021S1-CS2103T-W10-3/tp/pull/46))
    * Added the guide for scoping commands. (Pull Request [\#89](https://github.com/AY2021S1-CS2103T-W10-3/tp/pull/89), [\#122](https://github.com/AY2021S1-CS2103T-W10-3/tp/pull/122), [#196](https://github.com/AY2021S1-CS2103T-W10-3/tp/pull/196))
  * Developer Guide:
    * Wrote the Use Cases for first draft of Project Management System. (Pull Request [\#49](https://github.com/AY2021S1-CS2103T-W10-3/tp/pull/49))
    * Wrote the design of `Model` component. (Pull Request [\#124](https://github.com/AY2021S1-CS2103T-W10-3/tp/pull/124))
    * Wrote the implentation of scoping features. (Pull Request [\#126](https://github.com/AY2021S1-CS2103T-W10-3/tp/pull/126))
    * Wrote the extension suggestions. (Pull Request [#255](https://github.com/AY2021S1-CS2103T-W10-3/tp/pull/255))

* **Community**:
  * Updated the first draft for `AboutUs.md`. (Pull Request [\#46](https://github.com/AY2021S1-CS2103T-W10-3/tp/pull/46))

* **Tools**:
