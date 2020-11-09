---
layout: page
title: Li Beining's Project Portfolio Page
---

## Introduction

This page serves to document my contributions to the project PlaNus under NUS module CS2103T in AY20/21 semester 1. 

## Project: PlaNus

PlaNus is a **task managing desktop application** for students in NUS with many projects and deadlines, 
optimized for use via a Command Line Interface (CLI) with the benefits of Graphical User Interface (GUI).
PlaNus reduces the time spent by students in task management as adding tasks and lessons is now simple and quick!

Given below are my contributions to the project.

* **New Features**: Added the ability to add deadlines and events.

  * What it does: Easy-to-use CLI commands to add a particular deadline according to a set of specified attributes.
  * Justifications: The `deadline` and `event` command are integral in managing a students tasks.
  * Highlights: This feature requires use of inheritance and polymorphism as both deadline and event are considered a task in PlaNus. 
 <br>
* **New Features**: Added the ability to list task in certain sequence.

   * What it does: Easy-to-use CLI commands list the current task in PlaNus.
   * Highlights: Custom compareTo method is used for event and deadline class to provide more logical sorting sequence for the deadline and event when they are add in the task list. (passed event and done deadline will be placed in the bottom of the list, while earlier deadline will be place in the top of the list)

<div style="page-break-after: always;"></div>

* **New Features**: Added the ability to mark a deadline as done.
  
  * What it does: allows user to change the status of deadline to complete.
  * Justifications: The ability to mark a deadline as done is important, as it allows user to input the time taken for the task, and it also records the time that user has finished the task. These data will be recorded and used for data analysis.
  * Highlights: This feature requires design to ensure that it accepts varargs. The a user can mark more than one task as done at a single command, and it will also prevent user from trying to mark an event as done.
<br>
* **New Features**: Adds the ability to save tasks(deadlines and events) in JSON.
 
  * What it does: Allows tasks to be saved locally to a JSON.
  * Justifications: tasks that a user adds to PlaNus is properly saved and can be referred next time when PlaNus runs. 
  * Highlights: deadline and events will be save in two different array in json for the ease of reading for the data analysis.
<br>
* **Project management**:

  * Managed the milestones `v1.2`, `v1.3` and `v1.4` (including related issues and deadlines) on GitHub. 
<br>
* **Code contributed**: 

  * My code contributions to PlaNus can be found via the [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=dearvae&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other).
<br>
* **Documentation**:
<br>
* User Guide:
    * Added documentation for the features `deadline title:TITLE [desc:DESCRIPTION] datetime:DATE_TIME [tag:MODULE_CODE]`
    * Added documentation for the features `event title:TITLE date:DATE from:START_TIME to:END_TIME [desc:DESCRIPTION] [tag:MODULE_CODE]`
    * Added documentation for the features `done INDEX:TIME_TAKEN...`
    * Added documentation for the features `list-task`
<br>
* Developer Guide:
    * add Appendix: Instructions for manual testing 
<br>
* **Community**:
  * Important PRs reviewed (with non-trivial review comments):
    * [\#129](https://github.com/AY2021S1-CS2103T-T12-3/tp/pull/129),
      [\#161](https://github.com/AY2021S1-CS2103T-T12-3/tp/pull/161), 
    * The full list of PRs reviewed and approved can be found [here](https://github.com/AY2021S1-CS2103T-T12-3/tp/pulls?q=is%3Apr+is%3Aclosed+reviewed-by%3Adearvae)
  * Reported bugs and suggestions for other teams in the class via this [repo](https://github.com/dearvae/ped/issues)
