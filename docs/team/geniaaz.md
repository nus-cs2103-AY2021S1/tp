---
layout: page
title: Wahab Niaaz's Project Portfolio Page
---

## Taskmania

Taskmania (based off AB3) is a **desktop app for a project leader to manage team members and tasks** to be finished in a
 software project, optimized for use via a Command Line Interface (CLI) while still having the benefits of a 
 Graphical User Interface (GUI). If you can type fast, Taskmania can allow you to manage your team faster than 
 a traditional point and click interface.
 
Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=GeNiaaz&tabRepo=AY2021S1-CS2103T-W10-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code)

* **Model modification**: Added the Person model and its attributes. (Pull requests [\#13](https://github
.com/AY2021S1-CS2103T-W10-3/tp/pull/13), [\#17](https://github.com/AY2021S1-CS2103T-W10-3/tp/pull/17))
  * What it means: persons that participates in projects.
  * What changes made: 
    * copied the original Person class in AB3;
    * adapted dependencies accordingly. 

* **Model modification** : Refactored some attributes for Person in AB3 to attributes for Project in Taskmania
. (Pull requests [\#66](https://github.com/AY2021S1-CS2103T-W10-3/tp/pull/66), [\#74](https://github.com/AY2021S1-CS2103T-W10-3/tp/pull/66), [\#80](https://github.com/AY2021S1-CS2103T-W10-3/tp/pull/80))
  * What it means: refactors person.Name -> project.ProjectName, person.Address -> project.ProjectDescription, person.Tags -> project.ProjectTags
  * What changes made: 
    * refactored based on attributes for Person in AB3;
    * changed all methods that has dependency on relevant attributes;
    * updated test cases accordingly.

* **New Feature** : Create new Command to add Teammates to a project. (Pull requests [\#98](https://github.com/AY2021S1-CS2103T-W10-3/tp/pull/98) )
  * What it means: Command allows teammates to be added to a project, with associations created between project and
   person
  * What changes made:
    * Created NewTeammateCommandParser class to parse command
    * Created NewTeammateCommand class to handle the logic
    * Created ParsePersonUtil class to handle the validating of variables passed in to NewTeammateCommand  
    
* **New Feature** : Create new Command to edit Teammates  (Pull requests [\#123](https://github.com/AY2021S1-CS2103T-W10-3/tp/pull/123))
  * Changes made: 
    * EditTeammate now can change attributes of Teammates

* **Enhancements to existing features** : Update NewTeammate to take in prefixes (Pull requests [\#123](https://github.com/AY2021S1-CS2103T-W10-3/tp/pull/123))
  * Changes made: 
    * AddTeammate now uses prefixes to allow user to add attributes in any order they wish with appropriate prefixes

* **Enhancements to existing features** : Update tests for Teammate Commands (Pull requests [\#141](https://github.com/AY2021S1-CS2103T-W10-3/tp/pull/123))
  * Changes made: 
    * Tests added to increase path coverage for Teammate classes

* **New Feature** : Create new Command to delete Teammates  (Pull requests [\#185](https://github.com/AY2021S1-CS2103T-W10-3/tp/pull/185))
  * Changes made: 
    * EditTeammate now can change attributes of Teammates
    
* **New Feature** : Create new Command to add an existing teammate to another project  (Pull requests
 [\#253](https://github.com/AY2021S1-CS2103T-W10-3/tp/pull/253))
  * Changes made: 
    * Existing teammates can now be added to projects.
    
* **New Feature** : Create new Command to delete a teammate's participation in another project (Pull requests
 [\#254](https://github.com/AY2021S1-CS2103T-W10-3/tp/pull/254))
  * Changes made: 
    * Teammate participation in a project can now be deleted.



* **Documentation**:
  * User Guide:
    * Came up with the content outline of the first draft of modified User Guide.
    * Added general introduction of the application (Pull request [\#27](https://github.com/AY2021S1-CS2103T-W10-3/tp/pull/27)).
    * Rewrite entire UG to improve clarity (Pull requests [\#171](https://github.com/AY2021S1-CS2103T-W10-3/tp/pull/171))
  * Developer Guide:
    * Adapted first draft for target user profile, value proposition, and user stories to the format that fit in Developer Guide.
    * Added glossaries in the first draft (Pull request [\#44](https://github.com/AY2021S1-CS2103T-W10-3/tp/pull/44)).
    * Modify Architecture and AddTeamamte sections in DG (Pull request [\#44](https://github.com/AY2021S1-CS2103T-W10-3/tp/pull/278)).

