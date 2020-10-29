---
layout: page
title: User Guide
---

FixMyAbs is a desktop app for managing exercises, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). If you are unmotivated for a workout, FixMyAbs will be your partner in helping you to change your life. 😎
* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `fixmyabs.jar`.

1. Copy the file to the folder you want to use as the home folder for your FixMyAbs.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/screenshots/v1.3homescreen.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`list`** : List the exercises

   * **`add e/situp r/10 c/this is a comment`** : Adds a sit-up exercise log of 10 reps, with a comment of "this is a comment", to the FitMyAbs record.

   * **`edit`**`4 c/no abs were hurt` : Edits the log at index 4, with a comment of no abs were hurt.

   * **`delete 3`** : Deletes the 3rd workout.

   * **`exit`** : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add e/EXERCISE`, `EXERCISE` is a parameter which can be used as `add e/push dow`.

* Items in square brackets are optional.<br>
  e.g `e/EXERCISE [c/COMMENTS]` can be used as `e/situp c/my abs hurt` or as `e/situp`.

* Parameters can be in any order.<br>
  e.g. if the command specifies `e/EXERCISE r/REP`, `r/REP`, `e/EXERCISE` is also acceptable.

</div>

### Viewing help : `help`

Shows a message explaning how to access the help page.

Format: `help`

![Ui](images/screenshots/v1.3help.png)

### Adding a log: `add`

Adds an exercise log.

Format: `add e/EXERCISE [c/COMMENT]`

Examples:
* `add e/situp r/50`
* `add e/situp r/1 c/my abs hurt :(`

![Ui](images/screenshots/v1.3add.png)

Success: 
![Ui](images/screenshots/v1.3add_success.png)

### Listing exercise logs : `list`

Shows a list of all exercise logs logged by the user in the application.

Format: `list`

![Ui](images/screenshots/v1.3list_success.png)

### Editing a exercise log : `edit`

Edits an existing log in the application.

Format: `edit INDEX [r/REPS] [c/COMMENT]`

* Edits the existing log at the specified `INDEX`. The index refers to the index number shown in the displayed log list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.

Examples:
*  `edit 1 c/no abs were hurt` Edits the log at index 1, with a comment of `no abs were hurt`.
*  `edit 1 r/20 c/no abs were hurt` Edits the log at index 1, with reps of `20` and a comment of `no abs were hurt`.

![Ui](images/screenshots/v1.3edit.png)

Success:
![Ui](images/screenshots/v1.3edit_success.png)

### Deleting a log : `delete`

Deletes the specified log.

Format: `delete INDEX`

* Deletes the log at the specified `INDEX`.
* The index refers to the index number shown in the list of logs.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2`deletes the 2nd log.

![Ui](images/screenshots/v1.3delete.png)

Success:
![Ui](images/screenshots/v1.3delete_success.png)

### Exiting the program : `exit`

Exits the program.

Format: `exit`

![Ui](images/screenshots/v1.3exit.png)

### Saving the data

Exercise log data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.


--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: Why can I only track sit-ups?<br>
**A**:  first iteration of FixMyAbs and it only supports sit-ups. In future versions, more types of exercises will be supported.

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous FixMyAbs home folder.
--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format | Examples
--------|------------------ | --------------
**Add** | `add e/EXERCISE r/REPS [c/COMMENTS]` <br> | e.g. `add e/situp r/30 c/Send help`
**Delete** | `delete INDEX` | <br> e.g. `delete 3`
**Edit** | `edit INDEX [r/REPS] [c/COMMENT]` | <br> e.g.`edit 1 r/20 c/no abs were hurt`
**List** | `list`
**Find** | `find` | <br> e.g.`find pushups`
**Exit** | `exit`
