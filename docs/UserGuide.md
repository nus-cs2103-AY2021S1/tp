---
layout: page
title: User Guide
---

**ProductiveNUS is a desktop application** made for you, a School of Computing (SoC) student in National University of Singapore (NUS), to manage and schedule your academic tasks more effectively**. It makes use of a **Graphical User Interface (GUI)**, which provides you with an intuitive interface and immediate visual feedback. ProductiveNUS uses a **Command Line Interface (CLI)**; this means that you operate the application by typing commands into a [Command Box](#gui-terminologies). If you are fast at typing, you can manage your academic tasks more efficiently via the [Command Box](#gui-terminologies). 

As a **student from the SoC in NUS**, you tend to have a heavy workload. ProductiveNUS helps improve your productivity by enhancing your organisational skills. Apart from simply keeping track of your tasks, ProductiveNUS is capable of scheduling them for you so you will never miss any deadlines. ProductiveNUS is also compatible with NUSMods, meaning all your timetable information can be imported easily into the application so all your academic tasks can be found in just one application.


--------------------------------------------------------------------------------------------------------------------

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

### GUI terminologies

### Icon usages
Wondering what each icon is used for? You can refer to the table below to find out.

| Icon        | Icon usage                                               | Box color                                                     |
|-------------|----------------------------------------------------------|---------------------------------------------------------------|
| :clipboard: | - Notes about the command format <br> - Pointers to note | <div markdown="span" class="alert alert-info"> Green </div>   |
| :bulb:      | - Tip                                                    | <div markdown="span" class="alert alert-success"> Blue </div> |

### Command syntax and terminologies

All commands and their examples are demarcated with `markups`. `Markups` appear as a grey box as shown.

You can find out more about the command terminologies here:

* Prefix: An indicator to identify your input.

--------------------------------------------------------------------------------------------------------------------

## Getting started

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `productivenus.jar` from [here](https://github.com/AY2021S1-CS2103T-F11-3/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for ProductiveNUS.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>

   ![Ui](images/Ui.png)

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:notebook_with_decorative_cover: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by you.<br>
  e.g. in `add n/NAME_OF_ASSIGNMENT`, `NAME_OF_ASSIGNMENT` is a parameter which can be used as `add n/Assignment 2`.

* Items in square brackets are optional.<br>
  e.g `n/NAME_OF_ASSIGNMENT [mod/MODULE_CODE]` can be used as `n/Assignment 2 mod/CS2100` or as `n/Assignment 2`.


* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME_OF_ASSIGNMENT d/DEADLINE`, `d/DEADLINE n/NAME_OF_ASSIGNMENT` is also acceptable.
  
* All instances of `INDEX` **must be a positive integer**.<br>
  e.g. 1, 2, 3, …​

</div>

### Adding an assignment: `add`

Adds an assignment into your schedule.

Format: `add n/NAME_OF_ASSIGNMENT d/DEADLINE_OF_ASSIGNMENT TIME_ASSIGNMENT_IS_DUE mod/MODULE​ [remind]`

<div markdown="span" class="alert alert-success">
   
**:bulb: Tip:**
You can include `remind` when adding an assignment instead of using the `remind` command to set reminders after adding an assignment.
</div>

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

Format: `remind INDEX [MORE_INDEXES]`

You can set reminders for specific assignments which will be displayed in `Your Reminders` (Highlighted in red in the figure below) for your easy referral.

   ![UserGuideYourReminders](images/UserGuideYourReminders.png)
   *Figure 1: `Your Reminders` highlighted in red*

You can use the `INDEX` of the assignment as shown in your assignment list to set reminders for that assignment. 

For example, `remind 1` will set reminders for the first assignment in your assignment list ("CS1231S Homework" as shown in the figure below) and adds it to `Your Reminders`.

      ---------------------------Figure of GUI with CS1231S Homework (Highlighted red) added into Your Reminders----------------------------

You can set reminders for **more than one** assignments at a time as well. 

For example, `remind 2 4` will set reminders for the second and fourth assignment in your assignment list and adds both assignments to `Your Reminders`.


      ---------------------------Figure of GUI with second and fourth assignment (Highlighted red) added into Your Reminders----------------------------


<div markdown="block" class="alert alert-primary">
  
**:clipboard: Pointers to note:**<br>
* At least one `INDEX` must be present. For example, `remind` will not work.
* `INDEX` **must be a positive integer** 1, 2, 3, …​
* The `INDEX` must be found in your assignment list.

</div>

### Removing reminders for assignments : `unremind`

Format: `unremind INDEX`

You can remove your reminded assignments from `Your Reminders` by specifying the `INDEX` of the assignment as shown in your **reminded assignments list**. 

For example, `unremind 1` will remove the first assignment in `Your Reminders` ("CS2106 Lab" as shown in the figure below).

      ---------------------------------Figure of GUI before CS2106 Lab is removed from Your Reminders---------------------------------

      ---------------------------------Figure of GUI after CS2106 Lab is removed from Your Reminders----------------------------------

<div markdown="block" class="alert alert-primary">
  
**:clipboard: Pointers to note:**<br>
* At least one `INDEX` must be present. For example, `unremind` will not work.
* `INDEX` **must be a positive integer** 1, 2, 3, …​
* The `INDEX` must be found in `Your Reminders`.

</div>

### Setting priority for assignments : `prioritize`

Sets a priority for the specified assignment.

Format: `prioritize INDEX priority/PRIORITY`

* Sets the priority to the assignment at the specified `INDEX`.
* Priority levels include LOW, MEDIUM and HIGH.
* The index refers to the index number shown in the displayed assignment list.
* The index **must be a positive integer** 1, 2, 3, …​
* If the assignment already has a priority tag, this command will replace the previous priority tag with the new one.

### Removing priority for assignments : `unprioritize`

Removes a priority for the specified assignment.

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

## Command summary
| Action           | Format                                                                                         | Examples                                                            |
|------------------|------------------------------------------------------------------------------------------------|---------------------------------------------------------------------|
| **add**          | `add n/NAME_OF_ASSIGNMENT d/DEADLINE_OF_ASSIGNMENT TIME_ASSIGNMENT_IS_DUE mod/MODULE [remind]` | `add n/Math tutorial d/21-03-2020 1100 mod/ST2334`                  |
| **delete**       | `delete INDEX [MORE_INDEXES]`                                                                  | `delete 3`<br>`delete 2 3 4`                                        |
| **import**       | `import url/NUSMODS_URL`                                                                       | `import url/https://nusmods.com/timetable/sem-2/share?CS2108=LEC:1` |
| **list**         | `list [NUMBER_OF_DAYS]`                                                                        | `list 2`<br>`list`                                                  |
| **find**         | `find PREFIX/ KEYWORD [MORE_KEYWORD]`                                                          | `find mod/CS2103T CS2100`<br>`find p/HIGH`                          |
| **remind**       | `remind INDEX [MORE_INDEXES]`                                                                  | `remind 5`<br>`remind 2 4 5`                                        |
| **unremind**     | `unremind INDEX`                                                                               | `unremind 2`                                                        |
| **prioritize**   | `prioritize INDEX p/PRIORITY`                                                                  | `prioritize 3 p/HIGH`<br>`prioritize 1 p/LOW`                       |
| **unprioritize** | `unprioritize INDEX`                                                                           | `unprioritize 1`                                                    |
| **done**         | `done INDEX`                                                                                   | `done 4`                                                            |
| **undone**       | `undone INDEX`                                                                                 | `undone 2`                                                          |
| **help**         | `help`                                                                                         | `help`                                                              |
| **exit**         | `exit`                                                                                         | `exit`                                                              |

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: 
1. Install the app in your other computer and start the app.
2. Notice that a data file named `addressbook.json` is created under the `/data` folder.
3. Close the app in your other computer.
4. Overwrite the newly created data file with the data file from your previous computer.
5. All your existing data has been successfully transferred!
