---
layout: page
title: ProductiveNUS
---

ProductiveNUS is a **desktop application for managing and scheduling your academic tasks, optimized for use via a Command Line Interface** (CLI) contained in a **Graphical User Interface** (GUI). ProductiveNUS allows you to keep track of all your lessons and assignments at hand and type in your assignments quickly so that no time is wasted.

--------------------------------------------------------------------------------------------------------------------

## Table of Contents
1. Getting started
2. Features
3. FAQ
4. Summary of commands supported

--------------------------------------------------------------------------------------------------------------------

## Getting started

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `productivenus.jar` from [here](https://github.com/AY2021S1-CS2103T-F11-3/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for ProductiveNUS.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>

   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>

   Some example commands you can try:

   * **`add`**`n/Lab report 3 d/23-04-2020 1230 mod/CS2100` : Adds an assignment named `Lab report 3` to your schedule.

   * **`delete`**`3` : Deletes the 3rd assignment shown in the current list.

   * **`import url/YOUR_NUSMODS_URL`** : Imports your timetable.

   * **`list`**`2` : Lists assignments with deadline 2 days from current date (48 hours from current date and time).
   
   * **`find`**`n/Lab` : Finds assignments with names that contain the word 'Lab'.

   * **`remind`**`3` : Sets reminders for the 3rd assignment which will be displayed in `Your Reminders` section.
   
   * **`unremind`**`3` : Removes the 3rd assignment from `Your Reminders` section.

   * **`prioritize`**`3 priority/HIGH` : Sets a "HIGH" priority tag for the 3rd assignment.
   
   * **`unprioritize`**`3` : Removes the priority tag for the 3rd assignment.
   
   * **`done`**`3` : Marks the 3rd assignment as done.
  
   * **`undone`**`3` : Marks the 3rd assignment as not done.
   
   * **`exit`** : Exits the application.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by you.<br>
  e.g. in `add n/NAME_OF_ASSIGNMENT`, `NAME_OF_ASSIGNMENT` is a parameter which can be used as `add n/Assignment 2`.

* Items in square brackets are optional.<br>
  e.g `n/NAME_OF_ASSIGNMENT [mod/MODULE_CODE]` can be used as `n/Assignment 2 mod/CS2100` or as `n/Assignment 2`.


* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME_OF_ASSIGNMENT d/DEADLINE`, `d/DEADLINE n/NAME_OF_ASSIGNMENT` is also acceptable.

</div>

### Adding an assignment: `add`

Adds an assignment into your schedule.

Format: `add n/NAME_OF_ASSIGNMENT d/DEADLINE_OF_ASSIGNMENT TIME_ASSIGNMENT_IS_DUE mod/MODULE​ [remind]`

**Tip:**
You can include the `remind` tag when adding the assignment instead of using the `remind` command after adding the assignment.

Examples:
* `add n/Lab report 3 d/23-04-2020 1230 mod/CS2100`
* `add n/Tutorial 2 d/29-06-2020 1400 mod/CS2100 remind`

### Deleting an assignment : `delete`

Deletes the specified assignment from the assignment list.

Format: `delete INDEX`

* Deletes the assignment at the specified `INDEX`.
* The index refers to the index number shown in the displayed assignment list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd assignment in the assignment list.

### Importing your timetable : `import`

Imports your NUSMods timetable data into your schedule.

Format: `import url/YOUR_NUSMODS_URL`

* Lesson data based on your NUSMods timetable will be added into your schedule.
* `YOUR_NUSMODS_URL` is obtained by clicking on the "Share/Sync" timetable icon at the NUSMods website.

Examples:
* `import url/https://nusmods.com/timetable/sem-1/share?CS2100=TUT:01,LAB:11,LEC:1&CS2101=&CS2103T=LEC:G16&CS2105=TUT:14,LEC:1&EC1301=TUT:S28,LEC:1&IS1103=` will 

### Listing assignments : `list`

Format: `list [NUMBER]`

- Shows a list of assignments in your schedule within next `NUMBER` 
days, starting from the current date (and current time).
- A day represents 24 hours. 
- `list` without `NUMBER` displays your entire list of assignments 
stored in ProductiveNUS.


Examples: 
- `list 2` lists all your assignments with deadline 2 days (48 hours) from the current date (and current time). 
- `list 3` lists all your assignments with deadline 3 days (72 hours) from the current date (and current time). 
- `list ` lists all your assignments.

### Finding assignments : `find`

Format: `find PREFIX/ KEYWORD [MORE KEYWORDS]`

Parameters are:
- n/ NAME_OF_ASSIGNMENT [MORE NAME_OF_ASSIGNMENTS] to find by name of assignment.
- d/ DATE_OR_TIME_OF_ASSIGNMENT [MORE DATE_OR_TIME_OF_ASSIGNMENT] to find by the deadline (date or time) of assignment.
- mod/ MODULE_CODE [MORE MODULE_CODE] to find by module code of assignment.
- priority/ PRIORITY_OF_ASSIGNMENT [MORE PRIORITY_OF_ASSIGNMENT] to find by priority of assignment.

- Finds assignments in your schedule by name, module code, deadline (date or time) or priority.
- DATE_OR_TIME_OF_ASSIGNMENT is in the format dd-MM-yyyy or HHmm to enable searching by time and date of assignment separately.
- Only one field can be searched at a time.
- You can find assignments with multiple keywords of the same field.


Examples: 
- `find n/Assignment Homework Lab` Finds all your assignments with names that has Assignment, Homework or Lab.
- `find mod/CS2100` Finds all your assignments from the module CS2100. 
- `find d/1200 24-10-2020` Finds all your assignments with due date of 24-10-2020 or with due time 1200.
- `find priority/HIGH` Finds all assignments with high priority.

### Setting reminders for assignments : `remind`

Format: `remind INDEX`

You can set reminders for specific assignments which will be displayed in `Your Reminders` section (Highlighted in red in the figure below).

   ![UserGuideYourReminders](images/UserGuideYourReminders.png)

You can use the `INDEX` of the assignment as shown in your assignment list to set reminders for that assignment. 

For example, `remind 1` will set reminders for the first assignment in your assignment list ("CS1231S Homework" as shown in the figure below) and adds it to `Your Reminders`.

      ---------------------------Figure of GUI with CS1231S Homework (Highlighted red) added into Your Reminders----------------------------

**Pointers to note:**
* At least one `INDEX` must be present. For example, `remind` will not work.
* `INDEX` **must be a positive integer** 1, 2, 3, …​
* The `INDEX` must be found in your assignment list.

### Removing reminders for assignments : `unremind`

Format: `unremind INDEX`

You can remove your reminded assignments from `Your Reminders` by specifying the `INDEX` of the assignment as shown in your **reminded assignments list**. 

For example, `unremind 1` will remove the first assignment in `Your Reminders` ("CS2106 Lab" as shown in the figure below).

      ---------------------------------Figure of GUI before CS2106 Lab is removed from Your Reminders---------------------------------

      ---------------------------------Figure of GUI after CS2106 Lab is removed from Your Reminders----------------------------------

**Pointers to note:**
* At least one `INDEX` must be present. For example, `unremind` will not work.
* `INDEX` **must be a positive integer** 1, 2, 3, …​
* The `INDEX` must be found in `Your Reminders`.

### Setting priority for assignments : `prioritize`

Sets a priority tag for the specified assignment.

Format: `prioritize INDEX priority/PRIORITY`

* Sets the priority to the assignment at the specified `INDEX`.
* Priority levels include LOW, MEDIUM and HIGH.
* The index refers to the index number shown in the displayed assignment list.
* The index **must be a positive integer** 1, 2, 3, …​
* If the assignment already has a priority tag, this command will replace the previous priority tag with the new one.

### Removing priority for assignments : `unprioritize`

Removes a priority tag for the specified assignment.

Format: `unprioritze INDEX`

* Removes the priority of the assignment at the specified `INDEX`.
* The index refers to the index number shown in the displayed assignment list.
* The index **must be a positive integer** 1, 2, 3, …​

### Marking assignments as done : `done`

Marks the specified assignment as done.

Format: `done INDEX`

* Marks the assignment at the specified `INDEX` as done.
* The index refers to the index number shown in the displayed assignment list.
* The index **must be a positive integer** 1, 2, 3, …​

### Marking assignments as not done : `undone`

Marks the specified assignment as not done.

Format: `undone INDEX`

**Tip:**
Assignments are marked as not done by default.

* Marks the assignment at the specified `INDEX` as not done.
* The index refers to the index number shown in the displayed assignment list.
* The index **must be a positive integer** 1, 2, 3, …​

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

ProductiveNUS data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: 
1. Install the app in your other computer and start the app.
2. Notice that a data file named `addressbook.json` is created under the `/data` folder.
3. Close the app in your other computer.
4. Overwrite the newly created data file with the data file from your previous computer.
5. All your existing data has been successfully transferred!

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**add** | `add n/NAME_OF_ASSIGNMENT d/DEADLINE_OF_ASSIGNMENT TIME_ASSIGNMENT_IS_DUE mod/MODULE​ [remind]` <br> e.g., `add n/Math tutorial d/21-03-2020 1100 mod/ST2334`
**delete** | `delete INDEX`<br> e.g., `delete 3`
**import** | `import url/NUSMODS_URL`
**list** | `list [NUMBER]` e.g., `list 2`, `list`
**find** | `find PREFIX/ KEYWORD [MORE KEYWORD]` e.g., `find mod/CS2103T CS2100`, `find priority/HIGH`
**remind** | `remind INDEX`
**unremind** | `unremind INDEX`
**prioritize** | `prioritize INDEX priority/PRIORITY`
**unprioritize** | `unprioritize INDEX`
**done** | `done INDEX`
**undone** | `undone INDEX`
