---
layout: page
title: User Guide
---

Cap 5.0 Buddy helps NUS SoC students to keep track of their module details efficiently. It helps them centralize key module details and follows their study progress through a Command Line Interface (CLI) that allows efficient management of module details.

1. [Quick Start](#quick-start)
2. [Features](#features)
    1. Module Tracker
        1. [Adding a module](#adding-a-new-module-add-module) : `addmodule`
        2. [Viewing a module](#viewing-a-module-view) : `viewmodule`
        3. [Adding a zoom link to a module](#adding-a-zoom-link-to-a-module-add-zoom) : `addzoomlink`
        4. [Deleting a module](#deleting-a-module-delete) : `deletemodule`
        5. [Editing a module](#editing-a-module--edit-zoom) : `editmodule`
    2. Contact List
    3. Todo List
        1. [Adding a task](#adding-a-task) : `addtask`
        2. [Deleting a task](#deleting-a-task) : `deletetask`
        3. [Editing a task](#editing-a-task) : `edittask`
        4. [Finding a task](#finding-a-task) : `findtask`
        5. [Marking a task as completed](#marking-a-task-as-completed) : `complete`
        6. [Resetting a task](#resetting-a-task) : `resettask`
        6. [Sorting tasks](#sorting-tasks) : `sorttask`
        7. [Filtering tasks](#filtering-tasks) : `filtertask`
        8. [Archiving a task](#archiving-a-task) : `archivetask`
        9. [Clearing the list](#clearing-the-list) : `clear`
    4. Scheduler
3. [FAQ](#faq)
4. [Command Summary](#command-summary)

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `cap5buddy.jar` from [here](https://github.com/AY2021S1-CS2103T-F12-3/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ Cap 5 Buddy application.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/OriginalImages/Ui.png)

--------------------------------------------------------------------------------------------------------------------

## Features

### Module Tracker

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.


</div>

#### Adding a new module: `add module`

Creates and add a new module to be stored in the system.

  Format: `add module` **_`[MODULE_NAME]`_** **_`[ZOOM_LINK]`_**

   * Leaving the **_ZOOM_LINK_** part empty will create a empty module.

   Examples:
   * `add module CS2103T https:\\link` creates and add the module called CS2103T
   with the specified link into the system.
   * `add module CS2103T` creates and add the module CS2103T with no zoom link.

#### Viewing a module: `view`

Views a module stored in the system

 Format: `view` **_`[MODULE_NAME]`_**

  * Using the keyword all in place of specified module name will display all module details


  Examples:
  * `view cs2103t` views the specified module
  * `view all` views all the modules stored

#### Adding a zoom link to a module: `add zoom`

  Adds a zoom link to an existing module.

  Format: `add zoom` **_`[MODULE_NAME]`_** **_`[ZOOM_LINK]`_**

  * Adds a zoom link [ZOOM_LINK] to a module named **_`[MODULE_NAME]`_**

  Example of usage:
  `add zoom cs2103T https://sample.zoom.us` adds a zoom link `https://sample.zoom.us` to the module named `cs2103T`


#### Deleting a module: `delete`

Deletes the module at the specified position from the system

 Format: `delete` **_`[MODULE_POSITION]`_**

  Examples:
  * `delete 1` deletes the module at position `1`


#### Editing a module: `edit zoom`

* Edits an existing module in the module list.

Format: `edit zoom` **_`[MODULE_NAME]`_** **_`[ZOOM_LINK]`_**

* Edits the zoom link of a module named **_`[MODULE_NAME]`_** to **_`[ZOOM_LINK]`_**

Examples:
* `edit zoom CS2030 https://sample.zoom.us` edits the zoom link for a module named `CS2030`
  to `https://sample.zoom.us`

### Contact List

### Todo List

#### Adding a task: `addtask`

#### Deleting a task: `deletetask`

#### Editing a task: `edittask`

#### Finding a task: `findtask`

#### Marking a task as completed: `completetask`

#### Resetting a task: `resettask`

#### Sorting tasks: `sorttask`

#### Filtering tasks: `filtertask`

#### Archiving a task: `archivetask`

#### Clearing the list: `clear`

### Scheduler

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
**Add** | `add module KEYWORD`<br> e.g., `add module CS2103T [link]`, `add module CS2103T`
**View** | `view KEYWORD `<br> e.g., `view cs2101` , `view all`
**Delete** | `delete KEYWORD `<br> e.g., `delete 3`
**Edit** | `edit zoom MODULE_NAME ZOOM_LINK`<br> e.g., `edit zoom CS2103T https://sample.zoom.us`
**Add Zoom** | `add zoom MODULE_NAME ZOOM_LINK` <br> e.g., `add zoom cs2103T https://sample.zoom.us`
