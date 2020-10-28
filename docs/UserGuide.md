---
layout: page
title: User Guide
---

ScheDar is a **desktop app for managing tasks, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, ScheDar can get your task management done faster than traditional GUI apps.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `ScheDar.jar` from [here](coming soon).

1. Copy the file to the folder you want to use as the _home folder_ for your ScheDar.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`list`** : Lists out the tasks currently on the task list.

   * **`add`**`event n/project meeting d/2020-09-16 18:00 t/important` : Adds an event named `project meeting` to ScheDar.

   * **`delete`**`3` : Deletes the 3rd task shown in the current list.

   * **`find`** `cs2103 project`: Searches the current task list for the specified keyword `cs2103 project`.

   * **`exit`** : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/project meeting`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/project meeting t/important` or as `n/project meeting`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/important`, `t/important t/online` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME d/DATETIME`, `d/DATETIME n/NAME` is also acceptable.

</div>

### Viewing help : `help`

Lists out all the commands accepted by ScheDar. If the command name is specified, it will specify how to use that command.

Format: `help`


### Adding a task : `add`

Add a new task(todo/event/deadline) to the ScheDar program.

Format: `add TYPE n/NAME d/DATETIME [t/TAG]…​`
        TYPE refers to the type of task to be added.
        TYPE must be one of the following: deadline, event, todo
        Adds a new task of the given type, name, date and time, and tag if any, to the task list.
        The new task will be appended at the end of the existing task list.

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A task can have any number of tags (including 0)
</div>

Examples:
* `add event n/project meeting d/2020-09-16 18:00 t/important`

### Assigning priority to a task : `pri`

Assigns priority to an existing task on the task list.

Format: `pri LEVEL INDEX`
        LEVEL indicates the priority level
        The LEVEL must be one of the following: high, med, low
        Marks the task at the specified INDEX as the priority level indicated.
        The index refers to the index number shown on the task list when using the list command.
        The index must be a positive integer 1, 2, 3, …​

Example:
* `pri high 2`

### Listing all tasks : `list`

Lists out the tasks currently on the task list.

Format: `list`

### Listing deadlines chronologically : `dl`

Lists out deadline-type tasks currently stored on the task list, in chronological order of deadline. The earliest deadline would be at the top of the list.

Format: `dl`

### Marking task as done : `done`

Marks an existing task on the task list as completed.

Format: `done INDEX`
        Marks as completed the task at the specified INDEX.
        The index refers to the index number shown on the task list when using the list command.
        The index must be a positive integer 1, 2, 3, …​

Example: `done 2`

### Deleting a task : `delete`

Deletes the specified task from the current task list.

Format: `delete INDEX`
        Deletes the task at the specified INDEX.
        The index refers to the index number shown on the task list when using the list command.
        The index must be a positive integer 1, 2, 3, …​

Example: `delete 1`

### Editing a task : `editTodo`/`editEvent`/`editDeadline`

Add a new task(todo/event/deadline) to the ScheDar program.

Format: `editTodo n/NAME d/DATETIME [t/TAG]…​`/`editEvent n/NAME d/DATETIME [t/TAG]…​`
        /`editDeadline n/NAME d/DATETIME [t/TAG]…​`
        Edits the task with the given type, name, date and time, and tag if any, in the task list.
        The edited task will be placed in the same position as the original task list in the task list.

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A task can have any number of tags (including 0)
</div>

Examples:
* `edit event t/project meeting`

### Retrieve last-deleted task : `retrieve`

Restores the most recently deleted task.

Format: `retrieve`	


### Searching for tasks by keyword : `find`

Searches current shown tasklist for the specified keyword(s).

Format: `find KEYWORD1 [KEYWORD2]`
        Searches the task list shown on screen for the presence of the keyword(s) in titles and descriptions of tasks.
        Matching tasks are shown on the screen to the user.

Example: `find cs2103 project`

### Sorting tasks by date : `sort`

Sorts the whole tasklist according to their deadline or event data in ascending order. Todo tasks would be move to the bottom of the list.

Format: `sort`
        Sorts the whole tasklist according to their deadline or event data.
        Results would be shown on screen.

Example: `sort`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

ScheDar data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.


--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous ScheDar home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add** | `add TYPE n/NAME d/DATETIME [t/TAG]…​` <br> e.g., `add event n/project meeting d/2020-09-16 18:00 t/important`
**Deadline** | `dl`
**Delete** | `delete INDEX`<br> e.g., `delete 1`
**Done** | `done INDEX`<br> e.g., `done 1`
**Exit** | `exit`
**Find** | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find project meeting`
**Help** | `help`
**List** | `list`
**Priority** | `pri LEVEL INDEX`<br> e.g., `pri high 2`
**Retrieve** | `retrieve`

