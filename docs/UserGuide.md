---
layout: page
title: ProductiveNUS User Guide
---
## What is ProductiveNUS?
ProductiveNUS is a desktop application for **managing and scheduling your academic tasks** via a **Graphical User Interface** (GUI). It uses a **Command Line Interface (CLI)**; this means that you operate the application by typing commands into a Command Box. 
You can **keep track of all your assignments and lessons efficiently** and **view them in this single application**. It offers a scheduling function for your assignments that you can use to **plan your personal timetable quickly**.
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
   
   * **`unremind`**`3` : Removes reminders set for the 3rd assignment (assignment will be removed from `Your Reminders` section).

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

### Deleting assignments : `delete`

Format: `delete INDEX [MORE INDEXES]`

You can delete assignments from your assignment list by specifying the assignment `INDEX` as shown in your list.

You can delete **one or more than one** assignment at a time. Here is an example:

1) You have completed some of your assignments ("Statistics tutorial" and "Biology lab report" as shown in the figure below), and would like to delete them from your assignment list.
2) You can simply enter `delete 1 3` into the command line as per their indexes as circled and labelled in the figure.
3) The assignments "Statistics tutorial" and "Biology lab report" are no longer displayed and are successfully deleted from your assignment list.

Example commands:
* `delete 1`
* `delete 4 5`
* `delete 2 3 1`

<div class="panel panel-info">
**Pointers to note:**
{: .panel-heading}
<div class="panel-body">

* At least one index must be **present**. For example, `delete` without any index will not work.
* The indexes **must be a positive integer** 1, 2, 3, …​
* The indexes **must be found in your assignment list**.
* The indexes **must not be duplicated**. For example, `delete 3 3` will not work.

</div>
</div>


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

You can use `NUMBER_OF_DAYS` index to quickly view assignments that you need to complete soon! (with deadlines nearing).
For example, `list 2` will show you assignments that are due 2 days (48 hours) from the current date (and current time).
{: .alert .alert-gitlab-purple}

Examples: 
- `list` lists all your assignments.
- `list 3` lists all your assignments with deadline 3 days (72 hours) from the current date (and current time). For example, If the current date and time is 24/10/2020 12:00 pm, all assignments due from this date and time to 27/10/2020 12:00PM will be displayed.
 

<div class="panel panel-info">
**Pointers to note:**
{: .panel-heading}
<div class="panel-body">

* `NUMBER_OF_DAYS` **must be a positive integer** 1, 2, 3, …​
* **Only one** number can be keyed in. For example, `list 1 2` will not work. 

</div>
</div>

### Finding assignments : `find`

You can find your assignments based on keywords (and prefixes) you enter. The keywords can be the name, module code, deadline and priority level of assignments.

Here is the table of prefixes that can be used:
<style type="text/css">
.tg  {border-collapse:collapse;border-spacing:0;}
.tg td{border-color:black;border-style:solid;border-width:1px;font-family:Arial, sans-serif;font-size:14px;
  overflow:hidden;padding:10px 5px;word-break:normal;}
.tg th{border-color:black;border-style:solid;border-width:1px;font-family:Arial, sans-serif;font-size:14px;
  font-weight:normal;overflow:hidden;padding:10px 5px;word-break:normal;}
.tg .tg-0lax{text-align:left;vertical-align:top}
</style>
<table class="tg">
<thead>
  <tr>
    <th class="tg-0lax">Prefix</th>
    <th class="tg-0lax">Type of keyword</th>
    <th class="tg-0lax">Syntax</th>
  </tr>
</thead>
<tbody>
  <tr>
    <td class="tg-0lax">n/</td>
    <td class="tg-0lax">Name (case insensitive)</td>
    <td class="tg-0lax">n/Tutorial<br>n/tutorial essay</td>
  </tr>
  <tr>
    <td class="tg-0lax">mod/</td>
    <td class="tg-0lax">Module code (case insensitive)</td>
    <td class="tg-0lax">mod/CS2100 cs2103t</td>
  </tr>
  <tr>
    <td class="tg-0lax">d/</td>
    <td class="tg-0lax">Date or time of deadline</td>
    <td class="tg-0lax">d/24-10-2020<br>d/1200<br>d/1300 25-11-2020</td>
  </tr>
  <tr>
    <td class="tg-0lax">p/</td>
    <td class="tg-0lax">Priority level</td>
    <td class="tg-0lax">p/HIGH<br>p/LOW</td>
  </tr>
</tbody>
</table>



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
