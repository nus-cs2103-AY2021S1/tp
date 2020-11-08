---
layout: page
title: Lucas Tai's Project Portfolio Page
---

## Project: Taskmania

Taskmania (based off AB3) is a **desktop app for a project leader to manage team members and tasks** to be finished in a
 software project, optimized for use via a Command Line Interface (CLI) while still having the benefits of a 
 Graphical User Interface (GUI). If you can type fast, Taskmania can allow you to manage your team faster than 
 a traditional point and click interface.
 
Given below are my contributions to the project.

* **Model modification**: Created and managed Participation object. (Pull request: [\#18](https://github.com/AY2021S1-CS2103T-W10-3/tp/pull/18))
  * What it means: An association class keeping track of who participates in what project.
  * What changes made: 
    * created the class from scratch.
    * made relevant changes when needed to improve the model.
        
* **Storage modification**: Allowed the application to store data for future reference(Pull request: [\#182](https://github.com/AY2021S1-CS2103T-W10-3/tp/pull/182))
  * What it means: Allowed the application to save all data as a Json file.
  * What changes made:
    * Created JsonAdaptedPerson, JsonAdaptedParticipation, JsonAdaptedTask and their related classes.(Pull request: [\#151](https://github.com/AY2021S1-CS2103T-W10-3/tp/pull/151))
    * Modified the rest of the classes under Storage Package to fit with the new data and classes.
    * Implemented a new design for storing of data compared to AB3. (Pull request: [\#182](https://github.com/AY2021S1-CS2103T-W10-3/tp/pull/182))
    * Edited related test cases to fit the new storage design
    * Added additional relevant test cases
  
* **New Feature**: Created new command to add a new task (Pull request: [\#100](https://github.com/AY2021S1-CS2103T-W10-3/tp/pull/100))
    * What it does: allows the user to create a new task within a project.
    * What changes made:
      * Created AddTaskCommand and its relevant parser
      * Added relevant test cases
    
* **New Feature**: Created new command to edit an existing task (Pull request: [\#100](https://github.com/AY2021S1-CS2103T-W10-3/tp/pull/100))
    * What it does: allows the user to edit the properties of an existing task.
    * What changes made:
      * Created EditTaskCommand and its relevant parser
      * Added relevant test cases

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=lucastai98&tabRepo=AY2021S1-CS2103T-W10-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code)

* **Documentation**:
  * User Guide:
    * Came up with the content outline of the first draft of modified User Guide.
    * Added user guide for dashboard-related features, including project dashboard and task dashboard.
    * Added the glossary of terms (Pull request: [\#272](https://github.com/AY2021S1-CS2103T-W10-3/tp/pull/272))
  * Developer Guide:
    * Wrote the implementation of the design of the storage.
    * Wrote the implementation of task feature. (Pull request: [\#181](https://github.com/AY2021S1-CS2103T-W10-3/tp/pull/181))
    * Added the glossary of terms (Pull request: [\#272](https://github.com/AY2021S1-CS2103T-W10-3/tp/pull/272))

* **Community**:
  * Added all user stories of first draft to issue tracker
