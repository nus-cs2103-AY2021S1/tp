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
    
* **Model modification**: Showed the projects list during `viewteammate`. (Pull request [\#300](https://github.com/AY2021S1-CS2103T-W10-3/tp/pull/300))
   * What it means: makes the list panel on the left to show projects list instead of persons list during `viewteammate`
   * What changes made: 
     * added the scoping of `TEAMMATE` to the scoping list in Model where the project list is enabled.
     
 * **Model modification**: Refactored Deadline class. (Pull request [\#302](https://github.com/AY2021S1-CS2103T-W10-3/tp/pull/302))
    * What changes made: 
      * removed the function `convertIntoDateTime`;
      * added LocalDateTimeFormatter in Deadline class.  
      
  * **Model modification**: Edited execute in command classes.(Pull request [\#312](https://github.com/AY2021S1-CS2103T-W10-3/tp/pull/312)
    * What it means: synchronised the updates of the project, task, teammate, participation on dashboards
    * What changes made: 
      * if the instances mentioned above is edited or delete while they are displayed on dashboards, update the model class which holds the instances displayed;
      * created reset methods in model to reset the attributes.
      * updated test cases accordingly.    

* **GUI modification**: Enhanced the GUI for Milestone v1.2. (Pull request [\#101](https://github.com/AY2021S1-CS2103T-W10-3/tp/pull/101))
  * What it means: makes the GUI show the list of projects on the left half while the certain project profile is shown on the right half
  * What changes made: 
    * removed the status of the ProjectCard;
    * removed the extra information of a project to be shown on the ProjectCard;
    * added a new attribute in ModelManager which is the project that will be shown on the project dashboard;
    * updated methods in dependent classes to allow access to the new attribute;
    * created a ProjectDashboard class and the corresponding ProjectDashboard.fxml file;
    * created a EmptyProjectDashboard class and the corresponding EmptyProjectDashboard.fxml file;
    * added these two instances to the MainWindow class;
    * updated test cases of the MainWindow class.
    
* **GUI modification**: Enhanced the GUI for Milestone v1.3. (Pull request [\#150](https://github.com/AY2021S1-CS2103T-W10-3/tp/pull/150))
  * What it means: makes the GUI show the task, teammate on the rightmost dashboard
  * What changes made: 
    * added new stackpane on the Mainwindow.fxml;
    * added a new attributes in ModelManager which are task and teammate that will be shown on the rightmost attributes dashboard;
    * updated methods in dependent classes to allow access to the new attribute.
    * created a TaskDashboard class,TeammateDashboard class, the corresponding TaskDashboard.fxml file and the TeammateDashboard.fxml file;
    * refactor a EmptyProjectDashboard class to EmptyDashboard;
    * added these the rightAttributesDashboardPlaceHolder, TaskDashboard, TeammateDashboard to the MainWindow class;
    * updated test cases of the MainWindow class.
    
* **GUI modification**: Enhanced the GUI for Milestone v1.3. (Pull request [\#178](https://github.com/AY2021S1-CS2103T-W10-3/tp/pull/178))
  * What it means: makes the GUI show the list of all tasks assigned to the teammate which are from all projects that the teammate is participating on the teammate dashboard
  * What changes made: 
    * added flowpane named tasks in TeammateDashoboard.fxml;
 
 * **GUI modification**: Enhanced the GUI for Milestone v1.4. (Pull request [\#273](https://github.com/AY2021S1-CS2103T-W10-3/tp/pull/273)), [\#308](https://github.com/AY2021S1-CS2103T-W10-3/tp/pull/308))
   * What it means: changes the color of GUI and the resize the layout
   * What changes made: 
     * edited MainWindow.fxml;
     
 * **GUI modification**: Enhanced the GUI for Milestone v1.4. (Pull request [\#297](https://github.com/AY2021S1-CS2103T-W10-3/tp/pull/297))
   * What it means: makes the dashboards panel scrollable
   * What changes made: 
     * added scroll panes with a stack pane nested inside to MainWindow class;
     * removed the stack pane of dashboard classes which are PersonDashboard class, TaskDashboard class, TeammateDashboard class.
     
* **New Feature**:

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=TCQian&tabRepo=AY2021S1-CS2103T-W10-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code)

* **Project management**:
  * Set up GitHub organization of team repository

* **Enhancements to existing features**:

* **Documentation**:
  * User Guide: 
    * Added documentation for advanced task-related features. (Pull request [\#52](https://github.com/AY2021S1-CS2103T-W10-3/tp/pull/52))
    * Added documentation for `viewtask`, `viewteammate`, `leaveTaskView`, `leaveTeammateView`. (Pull request [\#167](https://github.com/AY2021S1-CS2103T-W10-3/tp/pull/167))
    * Edited documentation for Global scope features. (Pull request [\#322](https://github.com/AY2021S1-CS2103T-W10-3/tp/pull/322))
    * Update all images in UG (Pull request [\#327](https://github.com/AY2021S1-CS2103T-W10-3/tp/pull/327))
    * Eidted documentation for Project scope features(5th-8th) (Pull request [\#329](https://github.com/AY2021S1-CS2103T-W10-3/tp/pull/329))
  * Developer Guide:
    * Came up with the outline of Use Cases. (Pull request [\#53](https://github.com/AY2021S1-CS2103T-W10-3/tp/pull/53))
    * Wrote the Use Cases for first draft of ProjectProfile Tracking System. (Pull request [\#53](https://github.com/AY2021S1-CS2103T-W10-3/tp/pull/53))
    * Wrote the Use Cases for first draft of Team Members Tracking System. (Pull request [\#53](https://github.com/AY2021S1-CS2103T-W10-3/tp/pull/53))
    * Edit the Ui section (Pull request [\#169](https://github.com/AY2021S1-CS2103T-W10-3/tp/pull/169))
    * Wrote the Launch and Shutdown. (Pull request [\#169](https://github.com/AY2021S1-CS2103T-W10-3/tp/pull/169))

* **Community**:
  * Set up milestones in GitHub

* **Tools**:

* _{you can add/remove categories in the list above}_
