---
layout: page
title: User Guide
---

Cap 5.0 Buddy helps NUS SoC students to keep track of their module details efficiently. 
It helps them to centralize key module details, contacts and information while following their study progress through a Command Line Interface (CLI).

* Table of Contents
{:toc}


--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `cap5buddy.jar` from [here](https://github.com/AY2021S1-CS2103T-F12-3/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ Cap 5 Buddy application.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/OriginalImages/Ui.png)

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `addtask n/NAME`, `NAME` is a parameter which can be used as `addtask n/Week 11 quiz`.

* Items in square brackets are optional. Items without square brackets are compulsory fields. <br>
  e.g. `addtask n/NAME [p/PRIORITY]` can be used as `addtask n/Week 11 quiz p/highest` or as `addtask n/Week 11 quiz`
  
* Items with `...` after them can be used multiple times, including zero times. <br>
  e.g. `[t/TAG]...` can be used as ` ` (i.e. 0 times), `t/easy`, `t/friend t/important` etc.
  
* Argument parameters can be provided in any order.
  e.g. If the command specifies `addtask n/NAME [p/PRIORITY]`, `addtask [p/PRIORITY] n/NAME` is also acceptable.  

</div>


### Module Features

#### Adding a new module: `add module`

Creates and add a new module to be stored in the system.

  Format: `add module` **_`[MODULE_NAME]`_** **_`[ZOOM_LINK]`_**

   * Leaving the **_ZOOM_LINK_** part empty will create a empty module.

   Examples:
   * `add module CS2103T https:\\link` creates and add the module called CS2103T
   with the specified link into the system.
   * `add module CS2103T` creates and add the module CS2103T with no zoom link.


#### Viewing a module: `viewmodule`

Views a module stored in the system

 Format: `viewmodule` **_`n/[MODULE_NAME]`_**

  * Views information for a module named **_`[MODULE_NAME]`_**

  Examples:
   * `viewmodule n/cs2103t` views the specified module

#### Adding a zoom link to a module: `add zoom`

  Adds a zoom link to an existing module.

  Format: `add zoom` **_`[MODULE_NAME]`_** **_`[ZOOM_LINK]`_**

  * Adds a zoom link [ZOOM_LINK] to a module named **_`[MODULE_NAME]`_**

  Example of usage:
  `add zoom cs2103T https://sample.zoom.us` adds a zoom link `https://sample.zoom.us` to the module named `cs2103T`

#### Deleting a module: `deletemodule`

Deletes the module at the specified position in the module list.

 Format: `delete` **_`[MODULE_POSITION]`_**

  Examples:
  * `deletemodule 1` deletes the module at position `1`

#### Editing a module : `edit zoom`

* Edits an existing module in the module list.

Format: `edit zoom` **_`[MODULE_NAME]`_** **_`[ZOOM_LINK]`_**

* Edits the zoom link of a module named **_`[MODULE_NAME]`_** to **_`[ZOOM_LINK]`_**

Examples:
* `edit zoom CS2030 https://sample.zoom.us` edits the zoom link for a module named `CS2030`
  to `https://sample.zoom.us`

#### Adding assignment to a module: `addassignment`

  Adds an assignment that takes up a percentage of the grade and has a result from 0.00 to 1.00 to an existing module.

  Format: `addassignment` **_`n/[MODULE_NAME]`_** **_`a/[ASSIGNMENT_NAME]`_**
  **_`%/[ASSIGNMENT_PERCENTAGE]`_** **_`r/[ASSIGNMENT_RESULT]`_**

  * Adds an assignment **_`[ASSIGNMENT_NAME]`_** that takes up **_`[ASSIGNMENT_PERCENTAGE]`_**
  of the grade with a result of **_`[ASSIGMENT_RESULT]`_** to a module named **_`[MODULE_NAME]`_**

  Example of usage:
  `addassignment n/CS2100 a/Quiz 1 %/5 r/0.80` adds an assignment called `Quiz 1` that takes up `5`% of the
  grade with a result of `0.80` to the module named `CS2100`


### Todo List Features

#### Adding a task: `addtask`

Adds a task to the list.

Format: `addtask` **_`[n/TASK_NAME]`_** **_`[t/TAG]`_** **_`[p/PRIORITY]`_** **_`[d/DATE]`_**

* All fields except the name of the task are optional.
* Name of the task should not be longer than 30 characters.
* You can provide more than one tag.
* Date must be in the form of YYYY-MM-DD.

Examples: 
* `addtask n/read book t/DAILY HOBBY p/low d/2020-10-10` adds the specified task.

#### Deleting a task: `deletetask`

Deletes a task from the list.

Format: `deletetask` **_`INDEX`_**

* See index from the list.
* Index must be a positive integer.

Examples:
* `deletetask 1` deletes the first task in the list.

#### Editing a task: `edittask`

Edits a task in the list.

Format: `edittask` `INDEX` **_`[n/TASK_NAME]`_** **_`[t/TAG]`_** **_`[p/PRIORITY]`_** **_`[d/DATE]`_**

* See index from the list.
* Index must be a positive integer.
* At least one field must not be empty.

Examples:
* `edittask 1 n/read chapter 5 p/HIGH` edits the first task name to `read chapter 5` and
and priority to `HIGH`.

#### Finding a task: `findtask`

Finds a task based on keywords.

Format: `findtask` **_`[KEYWORD]`_**

Examples:
* `findtask` **_`[k/KEYWORD]`_** list all the task that matches the keyword.

#### Marking a task as completed: `completetask`

Labels a task as COMPLETED.

Format: `completetask` **_`INDEX`_**

* See index from the list.
* Index must be a positive integer.

Examples:
* `completetask 1` label first task in the list as completed.

#### Resetting a task: `resettask`

Reset the status of a task back to NOT COMPLETED.

Format: `resettask` **_`INDEX`_**

* See index from the list.
* Index must be a positive integer.

Examples:
* `resettask 2` reset the second task in the list.

#### Sorting tasks: `sorttask`

Sorts the list based on a criterion.

Format: `sorttask` **_`[REVERSED]`_** **_`[CRITERION]`_**

* **_`[REVERSED]`_** is a signle character 'r'.
* Add **_`[REVERSED]`_** to reverse the ordering of the list.
* **_`[CRITERION]`_** is pre-defined i.e. choose from `NAME`, `PRIORITY`, `DATE`.
* **_`[CRITERION]`_** is not case-sensitive.

Examples:
* `sorrtask priority` sorts the task from lowest to highest priority.
* `sorrtask r priority` sorts the task from the highest to the lowest.

#### Filtering tasks: `filtertask`

Filters the list based on a criterion.

Format: `filtertask` + **_`[CRITERION]`_** + **_`[KEYWORD]`_**

Examples:
* `filtertask priority high` shows only tasks that has high priority.

#### Archiving a task: `archivetask`

Archives a task from the list.

Format: `archivetask` **_`INDEX`_**

* See index from the list.
* Index must be a positive integer.

Examples: `archivetask 1` archive the first task.

#### Clearing the list: `cleartask`

Clears all tasks in the list.

Format: `cleartask`


### Contact Features

#### Add a contact: `addcontact`

Adds a new contact into the contact list if it does not already exist.

Format: `addcontact n/NAME [e/EMAIL] [te/TELEGRAM] [t/TAG]`

<div markdown="span" class="alert alert-info">:information_source: **Note:** 
Users are required to provide at least one other contact detail apart from **Name**.
</div>

Examples of Usage:
`addcontact n/john e/john@gmail.com te/@john123`

#### Delete a contact: `deletecontact`

Deletes an existing contact from the contact list

Format: `deletecontact CONTACT_INDEX`

* Contact index refers to the index number of the contact in the contact list displayed in the application

Examples of Usage:
`deletecontact 1`

#### Edit a contact: `editcontact`

Edits an existing contact in the contact list.

Format: `editcontact CONTACT_INDEX [n/NAME] [e/EMAIL] [te/TELEGRAM]`

* This feature edits the contact at the specified contact index. Contact index refers to the index number of the contact in the contact list displayed in the application
* At least one of the contact fields must be provided
* The edit contact must not already exist in the contact list

Examples of Usage:
`editcontact 1 n/john e/john@gmail.com`: Edits the name and email of the contact at index 1 to `john` and `john@gmail.com` respectively

#### Calculating Cumulative Average Point(CAP): `calculatecap`

Calculates the user's CAP based on completed modules

 Format: `calculatecap`

  Examples:
  * `calculatecap` calculate the user's cap

### Scheduler Features

#### Adding an Event to the Scheduler: `addevent`

Creates and add a new Event with the specified information from the user input

  Format: `addevent n/[event name] d/[date]`
  
   Examples:
    * `addevent n/CS2103T exams d/12-12-2020` adds an Event called CS2103T into the Scheduler with the date 12-12-2020.


### General Features

#### Undo previous user command: `undo`

Undoes the previous user command

 Format: `undo`

  Examples:
  * `undo`

#### Redo previous user command: `redo`

Redoes the previously undone user command

 Format: `redo`

  Examples:
  * `redo`

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do you add a module into the program?<br>
**A**: Run the program and wait for the terminal to start up. Next, type in : “add module [CS2103T]” to add a module called CS2103T.

**Q**: How do you view the zoom links of a particular module?<br>
**A**: When the program has started running, enter the following in the terminal : “view [CS2103T]” to view the zoom link for the module called CS2103T.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add** | `addmodule n/MODULE_NAME l/ZOOM_LINK`<br> e.g. `addmodule n/CS2103T l/https://sample.zoom.us`
**View** | `viewmodule n/MODULE_NAME`<br> e.g. `viewmodule n/cs2101`
**Delete** | `deletemodule MODULE_POSITION `<br> e.g. `deletemodule 3`
**Edit** | `editmodule n/MODULE_NAME e/NEW_NAME l/NEW_LINK`<br> e.g. `editmodule n/CS2103T e/CS2100 l/https://sample.zoom.us`
**Add Zoom** | `addzoomlink n/MODULE_NAME l/ZOOM_LINK` <br> e.g. `addzoomlink n/cs2103T l/https://sample.zoom.us`
**Add Assignment** | `addassignment n/MODULE_NAME a/ASSIGNMENT_NAME %/ASSIGNMENT_PERCENTAGE r/ASSIGNMENT_RESULT` <br> e.g. `addassignment n/CS2100 a/Quiz 1 %/5 r/0.80`
**Calculate CAP** | `calculatecap` <br> e.g. `calculatecap`
**Add Event** | `addevent n/EVENT_NAME d/DATE` <br> e.g. `addevent n/CS2103T d/12-12-2020`
**Undo** | `undo` <br> e.g. `undo`
**Redo** | `redo` <br> e.g. `redo`
