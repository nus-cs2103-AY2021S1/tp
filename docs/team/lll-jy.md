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

* **New Feature**: 

* **Code contributed**: [RepoSense link]()

* **Project management**:
  * Managed all contributions of Model modifications.

* **Enhancements to existing features**:

* **Documentation**:
  * User Guide:
    * Added the teammate-related features' guide.
  * Developer Guide:
    * Wrote the Use Cases for first draft of Project Management System.

* **Community**:
  * Updated the first draft for `AboutUs.md`.

* **Tools**:

* _{you can add/remove categories in the list above}_
