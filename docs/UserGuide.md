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
  - Teammate related features 
    - Create new teammate `new teammate `
    - Add existing teammates of other projects to participate in this project `involves `
    - Update teammate details (person portfolio) `updatetm teammate `
    - Update participation details (tasks and project-specific information) `updatept participation `
    - Remove a teammate in the project `remove teammate `
    - View tasks allocated to a particular teammate `task participants `
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

### **Teammate**-related features
#### Create new teammate `new teammate `
Adds a new teammate to a project

Format: `new NAME p/PHONE_NUMBER e/EMAIL`
- `NAME` refers to the name of the teammate
- `PHONE_NUMBER` refers to the teammate's contact number
- `EMAIL` refers to the teammate's email

Example: `new Lucas p/94311421 e/lucastai98@gmail.com` Instantiates teammate Lucas with the specified contact number and email

#### Add existing teammates of other projects to participate in this project `involves `
Involves an existing teammate in other projects to the current project

Format: `involves NAME`
- `NAME` refers to the name of the teammate; by this, we should guarantee that teammates of all projects have different names if they are different people

Example: `involves Lucas` for `Lucas` that participates in another existing project

#### Update teammate details (person portfolio) `updatetm teammate `
Updates person particulars of person portfolio of a teammate

Format: `updatetm NAME [n/NAME] [p/PHONE_NUMBER] [e/EMAIL]`

Example: `updatetm Lucas p/12345678` Resets the contact number of `Lucas` to `12345678`

#### Update participation details (tasks and project-specific information) `updatept participation `
Updates the participation details of the teammate, such as his/her role

Format: `updatept NAME [r/ROLE]`
- `ROLE` refers to the role of the teammate in the project, i.e., team member or leader

Example: `updatept Lucas r/LEADER` Sets the existing teammate in the project, `Lucas`, as the leader of the project.

#### Remove a teammate in the project `remove teammate `
Removes an existing teammate in the project

Format: `removetm NAME`

Example: `removetm Lucas` Removes the existing teammate in the project, `Lucas`, from the current project

#### View teammates participated in a particular task `task participants `
Views the information of all teammates participating in a particular task

Format: `task participants TASK_NUMBER`
- `TASK_NUMBER` refers to the task number in the project

Example: `task participants 1` Displays the teammates that are assigned to do task 1

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that 
contains the data of your previous Taskmania home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples | Scope
--------|------------------|-------
**Get Help** | `help` | any scope
**Add Project** | `add project n/NAME d/DUEDATE t/TEAM` <br> e.g., `add project n/Taskmania d/2020-09-09 t/Niaaz Lucas` | global scope
**Start** | `start INDEX`<br> e.g., `start 3` | global scope
**New Teammate** | `new NAME p/PHONE_NUMBER e/EMAIL` <br> e.g., `new Lucas p/94311421 e/lucastai98@gmail.com` | project-specific scope
**Involve Teammate** | `involves NAME` <br> e.g., `involves Lucase` | project-specific scope
**Update Teammate** | `updatetm NAME [n/NAME] [p/PHONE_NUMBER] [e/EMAIL]` <br> e.g., `updatetm Lucas p/12345678` | project-specific scope
**Update Participation** | `updatept NAME [r/ROLE]` <br> e.g., `updatept Lucas r/LEADER` | project-specific scope
**Remove Teammate** | `removetm NAME` <br> e.g., `removetm Lucas` | project-specific scope
**View Teammates of Task** | `task participants TASK_NUMBER` <br> e.g., `task participants 1` | project-specific scope

