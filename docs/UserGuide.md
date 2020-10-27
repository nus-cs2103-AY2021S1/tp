---
layout: page
title: ProductiveNUS User Guide
---
## Table of Contents
{toc:}

--------------------------------------------------------------------------------------------------------------------
## Introduction
**ProductiveNUS is a desktop application** made for you, a School of Computing (SoC) student in National University of Singapore (NUS), to manage and schedule your academic tasks more effectively**. It makes use of a **Graphical User Interface (GUI)**, which provides you with an intuitive interface and immediate visual feedback. ProductiveNUS uses a **Command Line Interface (CLI)**; this means that you operate the application by typing commands into a Command Box. If you are fast at typing, you can manage your academic tasks more efficiently via the Command Box (put a hyperlink to terminologies related to GUI).

As a **student from the SoC in NUS**, you tend to have a **heavy workload**. ProductiveNUS helps **improve your productivity** by **enhancing your organisational skills**. Apart from simply **keeping track of your tasks**, ProductiveNUS is capable of **scheduling** them for you so you will never **miss any deadlines**. ProductiveNUS is also compatible with NUSMods, meaning all your **timetable information can be imported easily** into the application so all your academic tasks can be found in just one application.

--------------------------------------------------------------------------------------------------------------------
## About
This user guide provides you with the necessary information on how to become an expert user of ProductiveNUS. 
Before moving on to the next section, [Getting started](#getting-started), you can familiarize yourself with the terminologies, syntax and icons used in this user guide by reading the following subsections.  

### GUI Terminologies
The figure below shows the GUI of ProductiveNUS, with its components labelled. 


--------------------------------------------------------------------------------------------------------------------
## Getting started

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `productivenus.jar` from [here](https://github.com/AY2021S1-CS2103T-F11-3/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for ProductiveNUS.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>

   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>

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

### Deleting assignments : `delete`

Format: `delete INDEX [MORE_INDEXES]`

You can delete assignments from your assignment list by specifying the assignment `INDEX` as shown in your list.

You can delete **one or more than one** assignment at a time. Here is an example with steps to follow:

1) To delete assignments with the name "Statistics tutorial" and "Biology lab report" as shown in the figure below, you can simply enter `delete 1 3` into the command line as per their indexes that are circled and labelled in the figure.
2) The two assignments are no longer displayed and are successfully deleted from your assignment list. 
3) A "Deleted assignments" message that includes the information of your deleted assignments will be displayed in the Command Box.

More examples:
* `delete 1`
* `delete 2 3 1`

**Pointers to note:**
* At least one index must be **present**. For example, `delete` without any index will not work.
* The indexes **must be a positive integer** 1, 2, 3, …​
* The indexes **must be found in your assignment list**.
* The indexes **must not be duplicated**. For example, `delete 3 3` will not work.


### Importing your timetable : `import`

Imports your NUSMods timetable data into your schedule.

Format: `import url/YOUR_NUSMODS_URL`

* Lesson data based on your NUSMods timetable will be added into your schedule.
* `YOUR_NUSMODS_URL` is obtained by clicking on the "Share/Sync" timetable icon at the NUSMods website.

Examples:
* `import url/https://nusmods.com/timetable/sem-1/share?CS2100=TUT:01,LAB:11,LEC:1&CS2101=&CS2103T=LEC:G16&CS2105=TUT:14,LEC:1&EC1301=TUT:S28,LEC:1&IS1103=` will 

### Listing assignments : `list`

Format: `list [NUMBER_OF_DAYS]`

You can list your assignments altogether with just typing `list`. Alternatively, you can type `list` followed by an index `NUMBER_OF_DAYS` to list your assignments with deadlines that are within the current date (and time) and `NUMBER_OF_DAYS` later.

Tip:
You can key in a `NUMBER_OF_DAYS` index to quickly view assignments that you need to complete soon!
For example, `list 2` will show you assignments that are due 2 days (48 hours) from the current date (and current time).

Examples: 
- `list` lists all your assignments.
- `list 3` lists all your assignments with deadline 3 days (72 hours) from the current date (and current time). For example, If the current date and time is 24/10/2020 12:00 pm, all assignments due from this date and time to 27/10/2020 12:00PM will be displayed.
 
**Pointers to note:**
* `NUMBER_OF_DAYS` **must be a positive integer** 1, 2, 3, …​
* **Only one** number can be keyed in. For example, `list 1 2` will not work. 


### Finding assignments : `find`

Format: `find PREFIX/ KEYWORD [MORE_KEYWORDS]`

You can find your assignments based on keywords (and prefixes) you enter. The types of keywords are the name, module code, deadline and priority level of assignments. 
You can find assignments with multiple keywords of the same type to speed up your search!

Here is the table of prefixes used:
| Prefix | Syntax with examples                                            | Examples                             | Remarks                                                                                                                                                                                                                                        |
|--------|-----------------------------------------------------------------|--------------------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| n/     | n/NAME_OF_ASSIGNMENT  [MORE NAME_OF_ASSIGNMENT]                 | n/Tutorial n/tutorial essay          | Case insensitive                                                                                                                                                                                                                               |
| mod/   | mod/MODULE_CODE  [MORE MODULE_CODE]                             | mod/ST2334 mod/CS2100 cs2103t        | Case insensitive                                                                                                                                                                                                                               |
| d/     | d/DATE_OR_TIME_OF_ASSIGNMENT  [MORE DATE_OR_TIME_OF_ASSIGNMENT] | d/24-10-2020 d/1200d/1300 25-11-2020 | Date keywords are irrespective of time  and time keywords are irrespective of date. For example, `find d/1300 25-11-2020` will list assignments with due date of 25-11-2020 (regardless of time) or with due time of 1300 (regardless of date).  |
| p/     | p/PRIORITY_OF_ASSIGNMENT  [MORE PRIORITY_OF_ASSIGNMENT]         | p/high p/LOW                         | Case insensitive                                                                                                                                                                                                                               |


Here is an example of a usage scenario:
1) You want to find assignments from the modules CS2100 and ST2334.
2) You can simply key in `find mod/CS2100 ST2334` to view these assignments quickly.
3) Assignments from the modules CS2100 and ST2334 will appear in the assignment list.
4) A "listed your assignments" message will be displayed in the command box.

**Points to note:**
* `DATE_OR_TIME_OF_ASSIGNMENT` must have dates in the format **dd-MM-yyyy** or times in the format **HHmm** (24 hour).
* You can only **find assignments with keywords of the same type**. For example, `find n/Assignment d/23-10-2020` will not work.


### Setting reminders for assignments : `remind`

Sets reminders for the specified assignment which will be displayed in `Your Reminders` section.

Format: `remind INDEX`

* Sets reminders for the assignment at the specified `INDEX`.
* The index refers to the index number shown in the displayed assignment list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `remind 2` sets reminders for the 2nd assignment in the assignment list.

### Removing reminders for assignments : `unremind`

Removes reminders set for the specified assignment (assignment will be removed from `Your Reminders` section)

Format: `unremind INDEX`

* Removes reminders set for the assignment at the specified `INDEX`.
* The index refers to the index number shown in the displayed assignment list.
* The index **must be a positive integer** 1, 2, 3, …​

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
