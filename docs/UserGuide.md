---
layout: page
title: User Guide
---

# User Guide for Taskmania

Taskmania (based off AB3) is a **desktop app for a project leader to manage team members and tasks** to be finished in a
 software project, optimized for use via a Command Line Interface (CLI) while still having the benefits of a 
 Graphical User Interface (GUI). If you can type fast, Taskmania can allow you to manage your team faster than 
 a traditional point and click interface.

## Contents

- Features before project initialisation
  - Get help `help` 
  - Features associated with initialising project 
    - Creating new project `new project `
    - Start working on an existing project `start `
  
- Features after project initialisation


--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure that you have Java `11` or above installed in your Computer.

1. Download the latest `Taskmania.jar` from [here](https://github.com/AY2021S1-CS2103T-W10-3/tp).

1. Copy the file to the folder you want to use as the _home folder_ for your TaskMania.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how
 the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will
 open the help window.<br>
   Some commands you can try:

   * **`list`** : Lists all projects.

   * **`exit`** : Exits the app.

1. Refer to the Features below for details of each command.

--------------------------------------------------------------------------------------------------------------------
<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [tg/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[tg/TAG]…​` can be used as ` ` (i.e. 0 times), `tg/friend`, `tg/friend tg/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

</div>

## **Features** before project initialisation 

### Viewing help : `help`

Shows a message explaning how to access the help page.

![help message](images/helpMessage.png)

Format: `help`

### Creating a new project `add project `
Adds a project to the project list.

Format: `add project n/NAME d/DUEDATE t/TEAM`
TEAM is any number of names separated by “ “ spaces.

Examples: `add project n/Taskmania d/2020-09-09 t/Niaaz Lucas Jiayu` Adds a new project with the name Taskmania, due
 date 9 Sep 2020 with team members, Niaaz, Lucas and Jiayu.

### Starting work on an existing project `start `
Initialises the project specified.

Format: `start INDEX`
- Initialises the project at the specified INDEX.
- The index refers to the index number shown in the displayed project list.
- The index must be a positive integer 1, 2, 3, …​

Examples: `start 2` Initialises the second project in the project list.

## **Features** after project initialisation

### Task-related features

#### Checking the project dashboard `dashboard `
Shows a summary of the important information regarding the project.

Format: `dashboard`

#### Viewing tasks allocated to a team member `view `
Displays a list of tasks allocated to the specified members.

Format: `view NAME`
- NAME refers to the name of the team member when it was first input during project creation.

Example: `view Niaaz` Displays a list of tasks allocated to Niaaz.

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that 
contains the data of your previous Taskmania home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Get Help** | `help`
**Add Project** | `add project n/NAME d/DUEDATE t/TEAM` <br> e.g., `add project n/Taskmania d/2020-09-09 t/Niaaz Lucas`
**Start** | `start INDEX`<br> e.g., `start 3`

