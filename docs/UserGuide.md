![PlanusLogo](images/PlanusLogo.png)
---

User Guide v1.3 - Gabriella Teh
---

##Introduction

PlaNus is **task managing desktop application** for students in NUS, optimized for use via a Command Line Interface (CLI) with the benefits of Graphical User Interface (GUI). PlaNus allows you to keep track of all your deadlines, events and lessons easily.

##About
This user guide provides a detailed description of all the features available in the application.

## Quick start

* [Features](#features)
  * [Show all commands : `help`](#show-all-commands--help) - Gabriella Teh
  * [List all tasks : `list`](#list-all-tasks--list) - Marcus Tan
  * [Add a task: `add`](#add-a-task-add) - Li Gangwei
  * [Add a lesson: `lesson`](#add-a-lesson-lesson) - Marcus Tan
  * [Delete a task : `delete`](#delete-a-task--delete) - Li Beining
  * [Mark a deadline as done: `done`](#mark-a-deadline-as-done-done) - Li Beining
  * [Find a task : `find`](#find-a-task-by-attribute-find) - Zhou Zijian
  * [Edit a task : `edit`](#edit-a-task-edit`) - Gabriella Teh
  * [Exit the program : `exit`](#exit-the-program--exit) - Li Gangwei
* [FAQ](#faq) - Li Gangwei
* [Command summary](#command-summary) - Zhou Zijian

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">


**Notes about the command format**<br>

* Words in UPPER_CASE are the parameters to be supplied by the user.<br>
  e.g. in `add title:TITLE`, `TITLE` is a parameter which can be used as `title:homework 1`.

* Items in square brackets are optional input. e.g `desc:DESCRIPTION` <br>
  `[desc:DESCRIPTION]` can be used as `title:homework 1 desc:science project` or just as `title:homework 1`.

* Items with `...` after them can be used multiple times.<br>
  e.g. `done INDEX:TIME_TAKEN...` can be used as `done 1:20 2:120 3:50`.

* Parameters can be in any order.<br>
  e.g. if the command specifies `title:TITLE desc:DESCRIPTION`, `desc:DESCRIPTION title:TITLE` is also acceptable.

</div>

### Show all commands : `help`

Shows a message with a link to the user guide.

![help message](images/helpMessage.png)

Format: `help`


### List all tasks : `list`

Shows a list of all tasks in PlaNus.

![list tasks](images/ListTasks.png)

Format: `list`


### Add a task: `add`

Adds a task of the specified type to PlaNus.

Format: `add title:TITLE type:TYPE_OF_TASK [desc:DESCRIPTION] [date:DATE_TIME] [tag:MODULE_CODE]`

* Adds the task of the specified `type:TYPE_OF_TASK` to PlaNus.
* The type must be of the following types(case-sensitive):
  * deadline
  * event

Examples:

* `add title:Return book type:todo date:01-01-2020 18:00`
Adds a task with title "Return book", of type "deadline", and a date and time of "01-01-2020" to PlaNus.
* `add title:Birthday party type:deadline desc:Frank’s birthday party date:01-01-2020 18:00`
  Adds a task with title “Birthday party”, of type "event",
  description “Frank’s birthday party” and a date and time of “01-01-2020 18:00” to PlaNus.

### Add a lesson: `lesson`

Adds a lesson to PlaNus.

Format: `lesson title:TITLE tag:MODULE_CODE [desc:DESCRIPTION] day:DAY from:TIME to:TIME start:DATE end:DATE`

* Adds a lesson to PlaNus, starting from the date specified in `start:DATE` to the
 date specified in `end:DATE`, on the days specified in `day:DAY` from the time specified in `from:TIME` to the time
 specified in `to:TIME`.
* The format of day in `day:DAY` must be as follows (case-insensitive):
  * Mon - Sun
  * Monday - Sunday

Examples:

* `lesson title:CS2103T Lecture tag:CS2103T desc:Most exciting lecture in NUS! day:Mon from:12:00 to:14:00 start:01-01-2020 end:01-05-2020 ` Adds a lesson to PlaNus with a title "CS2103 Lecture", under the module "CS2103T", with a description "Most exciting lecture in NUS!", on all Mondays 12:00-14:00 in the date range 01-01-2020 to 01-05-2020.

### Delete a task : `delete`

Deletes the specified task from PlaNus.

Format: `delete INDEX...`

* Deletes the task(s) at the specified `INDEX`.
* The index refers to the index number shown in the displayed task list.
* The index **must be a positive integer** 1, 2, 3, ...
* User can provide more than 1 index at the same time, eg. delete 1 2 3, However, if one of the index is invalid, the whole command will not be executed and an error message will be shown.

Examples:

* `list` followed by `delete 1 2` deletes the 1st task followed by the 2nd task in the results of the `list` command.
* `find title:homework` followed by `delete 1` deletes the 1st task in the results of the `find` command.

### Mark a task as done: `done`

Marks a specified task in PlaNus as done.

Format: `done INDEX:TIME_TAKEN...`

* Marks the task(s) at the specified `INDEX` as done and record the time taken to complete the task.
* The index refers to the index number shown in the displayed task list.
* The time taken refers to the time in minutes that the user took to complete the specific task.
* The index and time taken **must be a positive integer** 1:20, 2:30, 3:120, ...
* Take note that there are two type of task, one is event, another one is deadline, only task of deadline can be marked as done through this command, an error message will be shown if user attempts to mark a event as done.
* After user marked a deadline as done, user cannot edit the deadline or undo the done command anymore.

In order to avoid typo from user, this command will not be executed successfully when (and a corresponding error message will be shown):
* one or more target indexes are event rather then deadline.
* one or more target deadline are already in complete status.
* same index appear more than one time in the command, eg. done 1:20 2:20 2:30 
* wrong INDEX/TIME_TAKEN value


Examples:

* `list` followed by `done 2:30 3:60` marks the 2nd and the 3rd tasks in the results of the `list` command status to be done, and record that user has spend 30 minutes to finish the 2nd task, and 60 minutes to finish the 3rd task.
* `find title:homework` followed by `done 1:20` marks the 1st task in the results of the `find` command status to be done and record the time taken to complete the deadline is 20 minutes.

### Find a task by an attribute: `find`

Finds a task by a set of attributes given below.

Format: `find ATTRIBUTE_1:SEARCH_PHRASE ATTRIBUTE_2:SEARCH_PHRASE ...`


If different attributes are provided in the command, tasks that match all attributes will be displayed.

If multiple search phrases of the same attribute are provided in the command, tasks that match any of the

Available attributes in v1.3 include:
* `title:` finds all tasks which contain the search phrase in the given title
* `desc:` finds all tasks which contain the search phrase in the given description
* `type:` finds all tasks of the given type
* `date:` finds all tasks with the given date and/or time
* `status:` finds all tasks with the given status
* `tag:` finds all tasks related with the given module tag

Examples:

* `find title:play games` will list all tasks with a title that includes the phrase `play games`
* `find type:todo` will list all tasks with the type `todo`
* `find title:dinner type:todo` will list all tasks with a title that includes `dinner` and is of the type `todo`
* `find date:01-01-2020 11:00` will list all tasks with the date 01-01-2020 11:00
* `find title:dinner title:lunch type:todo` will list all tasks with the type `todo` and with the title
   that includes either `dinner` or `lunch`


### Edit a task: `edit`

Edits a task by a set of attributes given below.

Format: `edit INDEX ATTRIBUTE_1:NEW_VALUE ATTRIBUTE_2:NEW_VALUE ...`

If different attributes are provided in the command, multiple attributes of the specified task will be changed simultaneously.

Available attributes in v1.3 include:
* `title` edits the title of the specified task
* `desc` edits the description of the specified task
* `type` edits the type of the specified task
* `date` edits the date and/or time of the specified task
* `tag` edits the tag of the specified task

Examples:

* `edit 1 title:play games type:todo` will edit the first task in the results of the `list` command, changing its title to `play games` and type of task to `todo`
* `find type:todo` followed by `edit 3 desc:This is very urgent!` will edit the first task of the third task in the results of the `find` command, changing its description to "This is very urgent!"
* `find title:homework` followed by`edit 2 desc:Homework is difficult date:01-01-2020` will edit the second task in the results of the `find` command, changing its date to 01-01-2020 and its description to "Homework is difficult"
* `edit 1 tag:CS2103T` will edit the first task in the results of the `list` command, changing its tag to "CS2103T"

### Exit the program : `exit`

Exits PlaNus.

Format: `exit`


--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I start the application?<br>
**A**: In command prompt, go to the folder the application resides in and type: java - jar planus.jar

--------------------------------------------------------------------------------------------------------------------

## Command summary

| Action     | Format, Examples                                             |
| ---------- | ------------------------------------------------------------ |
| **Help**   | `help`                                                       |
| **List**   | `list`                                                       |
| **Event**  | `event title:TITLE [desc:DESCRIPTION] date:DATE from:TIME to:TIME tag:MODULE_CODE` <br> e.g. `event title:CS2103T Group meeting date:23-10-2020 from:20:00 to:22:00 tag:CS2103T` |
| **Deadline**  | `deadline title:TITLE [desc:DESCRIPTION] [datetime:DATETIME] tag:MODULE_CODE` <br> e.g. `deadline title:Assignment2 submission datetime:23-10-2020 18:00 tag:CS2103T` |
| **Lesson** | `title:TITLE tag:MODULE_CODE [desc:DESCRIPTION] day:DAY from:TIME to:TIME start:DATE end:DATE`<br>e.g.`lesson title:CS2103T Lecture tag:CS2103T desc:Most exciting lecture in NUS! day:Mon from:12:00 to:14:00 start:01-01-2020 end:01-05-2020` |
| **Delete** | `delete INDEX...` <br> e.g. `delete 3`, `delete 3, 4, 5`                       |
| **Done**   | `done INDEX:TIME_TAKEN...`<br> e.g. `done 1:20`, `done 1:20 2:60 3:120`    |
| **Find**   | `find ATTRIBUTE_1:SEARCH_PHRASE ATTRIBUTE_2:SEARCH_PHRASE ...` <br> e.g.`find title:dinner type:todo` |
| **Edit**   | `edit INDEX [title:TITLE] [date:DATE] [desc:DESCRIPTION] [type:TYPE] [tag:MODULE_CODE]`<br>e.g. `edit 1 date:02-02-2020 12:00 tag:CS2101` |
| **Exit**   | `exit`                                                       |

## Input Format summary
All the keyword mentioned in command should follow the format stated below:

| keyword     | Format, Examples                                             |
| ---------- | ------------------------------------------------------------ |
| **date**   | `dd-MM-yyyy`  <br> e.g. 23-10-2020                                                    |
| **datetime**   | `dd-MM-yyyy HH:mm`                                                       |
| **day**  |  `Monday/Tuesday/Wednesday/Thursday/Friday/Saturday/Sunday` |
| **from, to, time** | `HH:mm` <br> 18:00      |
                                                     |
