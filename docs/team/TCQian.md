---
layout: page
title: Tan Chia Qian's Project Portfolio Page
---

## Project: Taskmania

Taskmania (based off AB3) is a **desktop app for a project leader to manage team members and tasks** to be finished in a
 software project, optimized for use via a Command Line Interface (CLI) while still having the benefits of a 
 Graphical User Interface (GUI). If you can type fast, Taskmania can allow you to manage your team faster than 
 a traditional point and click interface.
 
Given below are my contributions to the project.

* **Model modification**: Refactored some attributes for Person in AB3 to attributes for Project in Taskmania.(Pull request [\#72](https://github.com/AY2021S1-CS2103T-W10-3/tp/pull/72), [\#76](https://github.com/AY2021S1-CS2103T-W10-3/tp/pull/76))
  * What it means: refactors person.Phone -> project.Deadline, person.Email -> project.RepoUrl
  * What changes made: 
    * refactored based on attributes for Person in AB3;
    * changed all methods that has dependency on relevant attributes;
    * updated test cases accordingly.

* **GUI modification**: Enhanced the GUI for Milestone v1.2. (Pull request [\#101](https://github.com/AY2021S1-CS2103T-W10-3/tp/pull/101))
  * What it means: makes the GUI show the list of projects on the left half while the certain project profile is shown on the right half
  * What changes made: 
    * removed the status of the ProjectCard;
    * removed the extra information of a project to be shown on the ProjectCard.
    * added a new attribute in ModelManager which is the project that will be shown on the project dashboard.
    * updated methods in dependent classes to allow access to the new attribute.
    * created a ProjectDashboard class and the corresponding ProjectDashboard.fxml file;
    * created a EmptyProjectDashboard class and the corresponding EmptyProjectDashboard.fxml file;
    * added these two instances to the MainWindow class;
    * updated test cases of the MainWindow class.

* **New Feature**: 

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=TCQian&tabRepo=AY2021S1-CS2103T-W10-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code)

* **Project management**:
  * Set up GitHub organization of team repository

* **Enhancements to existing features**:

* **Documentation**:
  * User Guide: (Pull request [\#52](https://github.com/AY2021S1-CS2103T-W10-3/tp/pull/52))
    * Added documentation for advanced task-related features.
  * Developer Guide: (Pull request [\#53](https://github.com/AY2021S1-CS2103T-W10-3/tp/pull/53))
    * Came up with the outline of Use Cases.
    * Wrote the Use Cases for first draft of ProjectProfile Tracking System.
    * Wrote the Use Cases for first draft of Team Members Tracking System.

* **Community**:
  * Set up milestones in GitHub

* **Tools**:

* _{you can add/remove categories in the list above}_
