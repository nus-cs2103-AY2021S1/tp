## User Guide

**Welcome to Reeve!**

Reeve is a desktop application for **private tutors to to better manage both administrative and academic details of their students**, optimised for use via a
**Command Line Interface (CLI)** for receiving inputs while still having the benefits of a **Graphical User Interface (GUI)** for displaying information.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `jar` file from [here](https://github.com/AY2021S1-CS2103T-W15-2/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for Reeve.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`list`** : Lists all contacts.

   * **`add`**`add n/Alex p/93211234 s/Commonwealth Secondary School y/Primary 6 v/Blk 33 West Coast Rd #21-214
   t/1 1430-1630 f/25 d/12/12/2020` : Adds a student named `Alex` to Reeve.

   * **`delete`**`3` : Deletes the 3rd contact shown in the current list.

   * **`clear`** : Deletes all contacts.

   * **`exit`** : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

</div>

### Viewing help : `help`

Shows a message explaning how to access the help page.

![help message](images/helpMessage.png)

Format: `help`

### Adding a student: `add`

Adds a student to Reeve. (Written by: Alex and Hogan)

Format: `add n/NAME p/PHONE s/SCHOOL y/YEAR v/CLASS_VENUE t/CLASS_TIME f/FEE d/LAST_PAYMENT_DATE [a/ADDITIONAL_DETAILS]`

<div markdown="block" class="alert alert-info">

:information_source: The format of TIME is {int: day_of_week} {int: start_time}-{int: end_time}<br>

</div>

Example:
* `add n/Alex p/93211234 s/Commonwealth Secondary School y/Primary 6 v/Blk 33 West Coast Rd #21-214
t/1 1430-1630 f/25 d/12/12/2020`
* `add n/John Doe p/98765432 s/Woodlands Secondary School y/Secondary 2 v/347 Woodlands Ave 3, Singapore 730347
t/1 1200-1400 f/30 d/24/09/2020 a/Likes chocolates a/Needs help with Algebra`

### Listing all students : `list`

Shows a list of all students in Reeve.

Format: `list`

### Editing a student : `edit`

Edits an existing student in Reeve (Written by: Vaishak).

Format: `edit INDEX [n/NAME] [p/PHONE] [s/SCHOOL] [y/YEAR] [v/CLASS_VENUE] [t/CLASS_TIME] [f/FEE] [d/PAYMENT_DATE] `

<div markdown="block" class="alert alert-info">

:information_source: The format of TIME is {int: day_of_week} {int: start_time}-{int: end_time}<br>

</div>

* Edits the student at the specified `INDEX`. The index refers to the index number shown in the displayed student list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* Start time has to be before end time.

Examples:
*  `edit 1 n/Alex p/99999999 s/Meridian Junior College` Edits the name, phone number and school of the 1st student to be `Alex`, `99999999` and `Meridian Junior College` respectively.
*  `edit 3 sb/Mathematics v/Blk 33 West Coast Rd #21-214 t/1 1430-1630` Edits the subject, venue and time of the third student to be `Mathematics`, `Blk 33 West Coast Rd #21-214` and `1 1430-1630` respectively.

### Locating students: `find`

Finds students who satisfy the given search criteria. (Written by: Choon Siong)

Format: `find [n/NAME] [s/SCHOOL] [y/YEAR]`

* The search is case-insensitive. e.g `hans` will match `Hans`
* At least one of the optional fields must be provided.
* The order of the optional fields do not matter. e.g `n/Hans s/River Valley` will match `s/River Valley n/Hans`
* Only full words will be matched. e.g `han` will not match `hans`.
* For the name, students with a name that matches any whole keyword specified for the name will be considered to match for the name.
* For the school, students with a school that contains any keyword specified for the school will be considered to match for the school.
* For the year, students with a year that contains any keywords specified for the year will be considered to match for the year.
* Only students matching all criteria specified will be returned (i.e `AND` search).

Examples:
* `find n/Alex david` matches `Alex David`, `alex david` and `Alex david`.
* `find n/Alex david` does not match `Alexis Davinder`.
* `find s/yishun sec` matches `Yishun Secondary School` and `Yishun Town Secondary School`.
* `find s/yishun secondary` does not match `Yishun Sec`
* `find y/sec 3` matches `sec 3`, `Secondary 3`
* `find y/sec 3` matches `sec 4`
* `find n/alex s/yishun y/sec 3` searches for all students who match all of `n/alex`, `s/yishun` and `y/sec 3`.

### Finding students with overdue fees: `overdue`

Finds students whose date of last payment is more than a month ago. (Written by: Ying Gao)

Format: `unpaid`

### Recording questions from a student: `question`

Adds, resolves or remove questions from a specified student. (written by: Ying Gao)

Format: `question INDEX [a/QUESTION_ADD] [s/QUESTION_INDEX SOLUTION] [d/QUESTION_INDEX]`

* Exactly one of the optional fields must be present.
* The index and question index **must be positive integers** 1, 2, 3, …​
* The `a/` field adds a new unanswered question to the student at the specified `INDEX`.
* The `s/` field marks an unanswered question, of the student at the specified `INDEX`, at the specified
`QUESTION_INDEX` as solved with the given `SOLUTION`.
* The `d/` field deletes the question at the specified `QUESTION_INDEX` from the student at the specified `INDEX`.

Examples:
* `question 1 a/How do birds fly?` records "How do birds fly?" as a new question from the 1st student in Reeve.
* `question 2 s/3` marks the 3rd question of the 2nd student in Reeve as answered.
* `question 1 d/2` removes the 2nd question from the 1st student in Reeve.

### Listing lessons schedule on a particular date: `schedule`

List the students that the user has class with on the given date. (written by: Alex Chua)

Format: `schedule DATE`

* Date must be in the format of **dd/mm/yyyy**.

Examples:
* `schedule 20/11/2020` outputs a list of students who has lessons with the user on that date

### Managing additional details for a student: `detail`

Adds, edits or deletes an additional detail for a specified student. (written by: Vaishak)

Format: `detail [add] [edit] [delete] STUDENT_INDEX [i/DETAIL_INDEX] [d/DETAIL_TEXT]`

* Exactly one of the following fields must be present: `[add]`, `[edit]` or `[delete]`
* The student index and detail index **must be positive integers** 1, 2, 3, …​
* `detail add` adds the given additional detail to the student at the specified `STUDENT_INDEX`.
* `detail add` requires the following optional field: `[d/DETAIL_TEXT]`.
* `detail edit` edits the additional detail at the specified `DETAIL_INDEX`, for the student at the specified `STUDENT_INDEX`
* `detail edit` requires the following optional fields: `[i/DETAIL_INDEX] [d/DETAIL_TEXT]`.
* `detail delete` deletes the additional detail at the specified `DETAIL_INDEX`, for the student at the specified `STUDENT_INDEX`
* `detail delete` requires the following optional field: `[i/DETAIL_INDEX]`.

Examples:
* `detail add 1 d/Smart` adds the "Smart" detail to the 1st student in Reeve.
* `detail edit 1 i/2 d/Handsome` edits the 2nd detail for the 1st student in Reeve, to "Hansome".
* `detail delete 1 i/3` deletes the 3rd detail for the 1st student in Reeve.

### Deleting a student : `delete`

Deletes the specified student from Reeve.

Format: `delete INDEX`

* Deletes the student at the specified `INDEX`.
* The index refers to the index number shown in the displayed students list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd student in Reeve.
* `find n/Betsy` followed by `delete 1` deletes the 1st student in the results of the `find` command.

### Clearing all entries : `clear`

Clears all entries from Reeve.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

Reeve data is saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous Reeve home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add** | `add n/NAME p/PHONE s/SCHOOL y/YEAR v/CLASS_VENUE t/CLASS_TIME f/FEE d/LAST_PAYMENT_DATE [a/ADDITIONAL_DETAILS]​` <br> e.g., `add n/John Doe p/98765432 s/Woodlands Secondary School y/Secondary 2 v/347 Woodlands Ave 3, Singapore 730347 t/1 1200-1400 f/30 d/24/09/2020 a/Likes chocolates a/Needs help with Algebra`
**Clear** | `clear`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Edit** | `edit INDEX [n/NAME] [p/PHONE] [n/NAME] [p/PHONE] [v/CLASS_VENUE] [s/SCHOOL] [sb/SUBJECT] [y/YEAR] [t/CLASS_TIME] [a/ADDITIONAL_DETAILS]`<br> e.g.,`edit 1 n/Alex p/99999999 s/Meridian Junior College`
**Find** | `find [n/NAME] [s/SCHOOL] [y/YEAR] [sb/SUBJECT]`<br> e.g., `find n/alex s/yishun`
**List** | `list`
**Help** | `help`
