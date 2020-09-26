---
layout: page
title: User Guide
---

Cap 5.0 Buddy helps NUS SoC students to keep track of their module details efficiently. It helps them centralize key module details and follows their study progress through a Command Line Interface (CLI) that allows efficient management of module details.

1. [Quick Start](#quick-start)
2. [Features](#features)
   1. [Viewing a module](#viewing-a-module)
   2. [Deleting a module](#deleting-a-module)
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

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.


</div>

### Adding a new module: `add module`

Creates and add a new module to be stored in the system.

  Format: `add module` **_`[MODULE_NAME]`_** **_`[ZOOM_LINK]`_**

   * Leaving the **_ZOOM_LINK_** part empty will create a empty module.

   Examples:
   * `add module CS2103T https:\\link` creates and add the module called CS2103T
   with the specified link into the system.
   * `add module CS2103T` creates and add the module CS2103T with no zoom link.

### Viewing a module: `view`

Views a module stored in the system

 Format: `view` **_`[MODULE_NAME]`_**

  * Using the keyword all in place of specified module name will display all module details


  Examples:
  * `view cs2103t` views the specified module
  * `view all` views all the modules stored

### Adding a zoom link to a module: `add zoom`

  Adds a zoom link to an existing module.

  Format: `add zoom` **_`[MODULE_NAME]`_** **_`[ZOOM_LINK]`_**

  * Adds a zoom link [ZOOM_LINK] to a module named **_`[MODULE_NAME]`_**

  Example of usage:
  `add zoom cs2103T https://sample.zoom.us` adds a zoom link `https://sample.zoom.us` to the module named `cs2103T`


### Deleting a module: `delete`

Deletes the specified module from the system

 Format: `delete` **_`[MODULE_NAME]`_**

  Examples:
  * `delete cs2103t` deletes the specified module named `cs2103t`


### Editing a module : `edit zoom`

* Edits an existing module in the module list.

Format: `edit zoom` **_`[MODULE_NAME]`_** **_`[ZOOM_LINK]`_**

* Edits the zoom link of a module named **_`[MODULE_NAME]`_** to **_`[ZOOM_LINK]`_**

Examples:
* `edit zoom CS2030 https://sample.zoom.us` edits the zoom link for a module named `CS2030`
  to `https://sample.zoom.us`

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

**Delete** | `delete KEYWORD `<br> e.g., `delete cs2101`

**Edit** | `edit zoom MODULE_NAME ZOOM_LINK`<br> e.g., `edit zoom CS2103T https://sample.zoom.us`

**Add Zoom** | `add zoom MODULE_NAME ZOOM_LINK` <br> e.g., `add zoom cs2103T https://sample.zoom.us`
