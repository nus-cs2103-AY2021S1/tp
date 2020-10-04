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
    * added dependencies of it in and related collections;
    * added related test cases.

* **Model modification**: Update Participation class and create corresponding methods in Project. (Pull request: Pull request [\#71](https://github.com/AY2021S1-CS2103T-W10-3/tp/pull/71))
  * What it means: Participation class is updated after creation of Meeting class and Task class
  * What changes made: 
    * avoided cyclic dependencies between Participation class and Meeting class
      * Meeting class no longer keeps track of its assignees.
      * Added methods in Participation and Project to get assignees of a specific meeting. 
    * Added more methods in Participation.

* **New Feature**: 

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=T-Fang&tabRepo=AY2021S1-CS2103T-W10-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code)

* **Project management**:

* **Enhancements to existing features**:

* **Documentation**:
  * User Guide:
    * Added documentation for basic task-related features.
    * Came up with the first draft of feature list.
  * Developer Guide:
    * Wrote first draft of non-functional requirements. (Pull Request [\#54](https://github.com/AY2021S1-CS2103T-W10-3/tp/pull/54))

* **Community**:
  * Updated `README.md` for team repository.

* **Tools**:

* _{you can add/remove categories in the list above}_
