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
  * [Add a deadline: `deadline`](#add-a-deadline--deadline) - Li Gangwei
  * [Add a event: `event`](#add-an-event--event) - Li Gangwei
  * [Add a lesson: `lesson`](#add-a-lesson--lesson) - Marcus Tan
  * [Delete a task : `delete`](#delete-a-task--delete) - Li Beining
  * [Mark a task as done: `done`](#mark-a-task-as-done--done) - Li Beining
  * [Find a task : `find`](#find-a-task-by-an-attribute--find) - Zhou Zijian
  * [Edit a task : `edit`](#edit-a-task--edit) - Gabriella Teh
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

* Items with `...` after them can be used multiple times including zero times.<br>
  e.g. `done INDEX...` can be used as (i.e. 0 times), `done 1 2 3`.

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


### Add a deadline : `deadline`

Adds a deadline to PlaNus.

Format: `deadline title:TITLE [desc:DESCRIPTION] [date:DATE_TIME] [tag:MODULE_CODE]`

* Adds a deadline to PlaNus.
  * The added deadline will be tracked for time analysis if it has a tag.

Examples:

* `deadline title:Return book datetime:02-01-2020 18:00`<br>
Adds a deadline with title "Return book", and a date and time of "02-01-2020 18:00" to PlaNus.
* `deadline title:Assignment 1 desc:CS3230 Assignment 1 date:01-11-2020 18:00 tag:CS3230`<br>
Adds a deadline with title “Assignment 1”, description “CS3230 Assignment 1”,
and a date and time of “01-01-2020 18:00” with tag "CS3230" to PlaNus.

### Add an event : `event`

Adds an event to PlaNus.

Format: `event title:TITLE date:DATE from:START_TIME to:END_TIME [desc:DESCRIPTION] [tag:MODULE_CODE]`

* Adds an event to PlaNus.
  * The added event will be tracked for time analysis if it has a tag.

Examples:

* `event title:Career Talk date:02-01-2020 from:09:00 to:12:30`<br>
Adds an event with title "Career Talk", and a date "02-01-2020" with start time of "09:00" and end time of "11:30"
to PlaNus.
* `event title:Consultation date:13-10-2020 from:19:00 to:20:30 desc:CS2105 consultation tag:CS2105`<br>
and a date "13-10-2020" with start time of "19:00" and end time of "20:30" with tag "CS2105" to PlaNus.

### Add a lesson : `lesson`

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

Format: `delete INDEX`

* Deletes the task(s) at the specified `INDEX`.
* The index refers to the index number shown in the displayed task list.
* The index **must be a positive integer** 1, 2, 3, ...

Examples:

* `list` followed by `delete 1 2` deletes the 1st task followed by the 2nd task in the results of the `list` command.
* `find title:homework` followed by `delete 1` deletes the 1st task in the results of the `find` command.

### Mark a task as done : `done`

Marks a specified task in PlaNus as done.

Format: `done INDEX...`

* Marks the task(s) at the specified `INDEX...` as done
* The index refers to the index number shown in the displayed task list
* The index **must be a positive integer** 1, 2, 3, ...

Examples:

* `list` followed by `done 2 3` marks the 2nd and the 3rd tasks in the results of the `list` command status to be done.
* `find title:homework` followed by `done 1` marks the 1st task in the results of the `find` command status to be done.

### Find a task by an attribute : `find`

Finds a task by a set of attributes given below.

Format: `find ATTRIBUTE_1:SEARCH_PHRASE ATTRIBUTE_2:SEARCH_PHRASE ...`

If you provide different attributes in the command, tasks that match all attributes will be displayed.
If you provide multiple search phrases of the same attribute, tasks that match any of the
search phrase of the same attribute will be displayed.

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


### Edit a task : `edit`

Edits a task by a set of defined attributes by the user.

Format: `edit INDEX ATTRIBUTE_1:NEW_VALUE ATTRIBUTE_2:NEW_VALUE ...`

If you provide different attributes in the command, multiple attributes of the specified task will be changed simultaneously.

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
| **Add**    | `add title:TITLE type:TYPE_OF_TASK [desc:DESCRIPTION] [date:DATE_TIME] [tag:MODULE_CODE]` <br> e.g. `add title:Read textbook type:todo tag:CS2103T` |
| **Lesson** | `title:TITLE tag:MODULE_CODE [desc:DESCRIPTION] day:DAY from:TIME to:TIME start:DATE end:DATE`<br>e.g.`lesson title:CS2103T Lecture tag:CS2103T desc:Most exciting lecture in NUS! day:Mon from:12:00 to:14:00 start:01-01-2020 end:01-05-2020` |
| **Delete** | `delete INDEX...` <br> e.g. `delete 3`                       |
| **Done**   | `done INDEX...`<br> e.g. `done 1 2 3`                        |
| **Find**   | `find ATTRIBUTE_1:SEARCH_PHRASE ATTRIBUTE_2:SEARCH_PHRASE ...` <br> e.g.`find title:dinner type:todo` |
| **Edit**   | `edit INDEX [title:TITLE] [date:DATE] [desc:DESCRIPTION] [type:TYPE] [tag:MODULE_CODE]`<br>e.g. `edit 1 date:02-02-2020 12:00 tag:CS2101` |
| **Exit**   | `exit`                                                       |
