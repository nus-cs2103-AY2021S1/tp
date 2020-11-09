---
layout: page
title: User Guide
---

TAsker is a **desktop app for Teaching Assistants (TAs) to manage student administration, optimized for use via a Command Line Interface (CLI)**. It still has the benefits of a Graphical User Interface (GUI). If you can type fast, TAsker can get your administrative tasks done faster than traditional GUI apps.

<!-- prettier-ignore -->
- Table of Contents
{:toc}

---

## Quick Start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `TAsker.jar` from [here](https://github.com/AY2021S1-CS2103T-F11-1/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your TAsker.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   - **`list`** : Lists all student.

   - **`add`**`n/John Doe p/98765432 e/johnd@example.com t/johndoe m/A0123456B tg/friends` : Adds a student named `John Doe` to the Address Book.

   - **`delete`**`3` : Deletes the 3rd student shown in the current list.

   - **`clear`** : Deletes all students.

   - **`exit`** : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

---

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

- Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

- Items in square brackets are optional.<br>
  e.g `n/NAME [tg/TAG]` can be used as `n/John Doe tg/friend` or as `n/John Doe`.

- Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[tg/TAG]…​` can be used as ` ` (i.e. 0 times), `tg/friend`, `tg/friend tg/family` etc.

- Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

- Any invalid parameter tags will be parsed as literals.<br>
  e.g. If we pass the `n/a/James` in as a parameter,
  since `a/` is an invalid parameter tag,
  it will attempt to interpret `a/James` as a `NAME`,
  rather than treating `a/` as a parameter tag and `James` as its field.

- Extra inputs to commands will be ignored. <br>
  e.g.

  - `list 1`, `1` will be ignored,

  - `exit abc`, `abc` will be ignored.

</div>

### Viewing help : `help`

Shows a message explaining how to access the help page.

Format: `help`

![result for 'help'](images/HelpCommand.png)

### Adding a student: `add`

Adds a student to the address book.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL t/TELEGRAM m/MATRIC_NUMBER [tg/TAG]…​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A student can have any number of tags (including 0). The rest of the parameters can **only** be inputted once.
</div>

Examples:

- `add n/John Doe p/98765432 e/johnd@example.com t/johndoe m/A0123456B`
- `add n/Betsy Crowe tg/friend e/betsycrowe@example.com t/betsy_crowe m/A0201234C p/1234567 tg/groupmate`

![result for 'add n/John Doe p/98765432 e/johnd@example.com t/johndoe m/A0123456B'](images/AddCommand.png)

### Listing all students : `list`

Shows a list of all students that the TA is in-charge of.

You may click on the Students tab to view the full list of students.

Format: `list`

![result for 'list'](images/ListCommand.png)

### Editing a student : `edit`

Edits an existing student's information.

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [t/TELEGRAM] [m/MATRIC_NUMBER] [tg/TAG]…​`

- Edits the student at the specified `INDEX`. The index refers to the index number shown in the displayed student list. The index **must be a positive integer** 1, 2, 3, …​
- At least one of the optional fields must be provided.
- If the index is a positive integer, the format for each optional field will be checked in the above order before the index's validity is verified.
- If all inputs are valid, the existing values will be updated to the input values.
- When editing tags, the existing tags of the student will be removed i.e adding of tags is not cumulative.
- You can remove all the student’s tags by typing `tg/` without
  specifying any tags after it.

Examples:

- `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st student to be `91234567` and `johndoe@example.com` respectively.
- `edit 2 n/Betsy Crower tg/` Edits the name of the 2nd students to be `Betsy Crower` and clears all existing tags.

  *Before editing*
  ![Before running 'edit 2 n/Betsy Crower tg/'](images/edit2before.png)
  
  *After editing*
  ![result for 'edit 2 n/Betsy Crower tg/'](images/edit2after.png)
  
### Locating students by name: `find`

Finds students whose names contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

- The search is case-insensitive. e.g `hans` will match `Hans`
- The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
- Only the name is searched.
- Only full words will be matched e.g. `Han` will not match `Hans`
- Students matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:

- `find John` returns `john` and `John Doe`
- `find alex david` returns `Alex Yeoh`, `David Li`<br>

  ![result for 'find alex david'](images/findAlexDavidResult.png)

### Deleting a student : `delete`

Deletes the specified student from the address book.

Format: `delete INDEX`

- Deletes the student at the specified `INDEX`.
- The index refers to the index number shown in the displayed student list.
- The index **must be a positive integer** 1, 2, 3, …​

Examples:

- `list` followed by `delete 2` deletes the 2nd student in the address book.
- `find Betsy` followed by `delete 1` deletes the 1st student in the results of the `find` command.

### Clearing all students : `clear`

Clears all entries from the list of students.

Format: `clear`

### Show Attendance: `showatt`

Shows the attendance for tutorial on a specified date.

Format: `showatt INDEX d/DATE`

Examples:

- `showatt 1 d/12/12/2020` checks if the first student
  in the list attended class on 12th December, 2020.

  For example if "John" was our first student,
  we would get the following response if he was present:
  ```
  John was present on 2020-12-12
  ```

  If he was absent we would get this instead:
  ```
  John was absent on 2020-12-12
  ```

### Marking Attendance : `attend`

Marks the student as present for tutorial on a specified date.

Format: `attend INDEX d/DATE`

![Attend](images/AddAttendance.png)

For: `attend 1 d/12/12/2000` 

Examples:

- `attend 1 d/12/12/2000` Marks the student at index 1 as present on 12 December 2000.
- `attend 2 d/12/01/2002` Marks the student at index 2 as present on 12 January 2002.

### Unmarking Attendance : `unattend`

Marks the student as absent (default) for tutorial on a specified date.

Format: `unattend INDEX d/DATE`

![Unattend](images/RemoveAttendance.png)

For: `unattend 1 d/12/12/2000` (This **particular** illustration requires `attend 1 d/12/12/2000` to be ran first)

Examples:
- `unattend 1 d/12/12/2000` Marks the student at index 1 as absent on 12 December 2000.
- `unattend 2 d/12/01/2002` Marks the student at index 2 as absent on 12 January 2002.

### Adding a consultation: `add-consult`

Adds a consultation to the address book.

Format: `add-consult n/NAME d/DATE tm/TIME a/LOCATION ty/TYPE`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
There are only **2 types** of consultations, Personal or Group. They can inputted in any case too!
</div>

Examples:

- `add-consult n/John Doe d/10/10/2020 tm/18:00 a/SOC Basement ty/personal`
- `add-consult n/Ann Lim d/30/05/2020 tm/11:00 a/Tembusu College ty/group `

![result for add consult command](images/addConsultResult.png)

### Deleting a consultation: `del-consult`

Deletes a consultation from the address book.

Use this to indicate a consultation has been completed or remove incorrect consultation slots.

Format: `del-consult INDEX`

Examples:

- `del-consult 1`

![result for delete consult command](images/delConsultResult.png)

### Listing all consultations: `list-consult`

Shows a list of all consultations that has been arranged with your students.

You may click on the Consultations tab to view the full list of consultations.

Format: `list-consult`

![result for list consult command](images/listConsultResult.png)

### Archiving data files : `archive`

Creates a copy of all current data, tagged with the current date and time.

Running this command will create a new json file containing all your saved data, in your `/data` folder.

Format: `archive`

Example:

- `archive` creates a json file called `tasker_2020-11-09_2113.json` as shown below.

  ![result for 'archive'](images/archiveResult.png)

### Exporting attendance : `exportatt`

Exports attendance to a csv file in `data/attendance_YYYY-MM-DD_HHmm.csv`

Running this command will create a new csv file in your `/data` folder. Each column corresponds to a student and each row corresponds to a date. Every cell is marked with either `ATTENDED` or `ABSENT` depending on whether the student has attended that day.

Format: `exportatt`

Example:

- `exportatt` creates a csv file called `attendance_2020-11-09_2055.csv` as shown below.

  ![result for 'exportatt'](images/exportattResult.png)

### Exiting the program : `exit`

Exits the program.

Format: `exit`

---

## FAQ

**Q**: How do I start using TAsker?
<br>
**A**: Refer to our [Quick Start Guide](https://ay2021s1-cs2103t-f11-1.github.io/tp/UserGuide.html#quick-start) here.
<br>

**Q**: How do I transfer my data to another Computer?
<br>
**A**: Install the app in the other computer and overwrite the `data` folder that TAsker creates with the `data` folder from your previous TAsker installation. The `data` folder should be in the folder where TAsker is installed to.
<br>

**Q**: Can I import existing spreadsheets of my students?
<br>
**A**: This is not supported at the moment.
<br>

**Q**: How many students can I store?
<br>
**A**: More than you will ever teach in a lifetime.
<br>

**Q**: How do I save my data?
<br>
**A**: TAsker data is automatically saved in the hard disk after the execution of any command that changes the data. There is no need to save manually.
<br>

**Q**: How do I submit a bug report?
<br>
**A**: [Open an issue on our github repository](https://github.com/AY2021S1-CS2103T-F11-1/tp/issues).
<br>

---

## Command Summary

| Action                   | Format, Examples                                                                                                                                                                                                           |
| ------------------------ | -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| **Add Student**          | `add n/NAME p/PHONE_NUMBER e/EMAIL t/TELEGRAM m/MATRIC_NUMBER [tg/TAG]…​`<br> e.g., `add n/James Ho p/98765432 e/jamesho@example.com t/james-ho m/A0202345B tg/F11`                                                         |
| **Edit Student**         | `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [t/TELEGRAM] [m/MATRIC_NUMBER] [tg/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`                                                                                    |
| **Find Student**         | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`                                                                                                                                                                 |
| **List Students**        | `list`                                                                                                                                                                                                                     |
| **Delete Student**       | `delete INDEX`<br> e.g., `delete 3`                                                                                                                                                                                        |
| **Clear All Students**   | `clear`                                                                                                                                                                                                                    |
| **Show Attendance**      | `showatt INDEX d/DATE`<br> e.g., `showatt 1 d/12/12/2020`                                                                                                                                                                  |
| **Marking Attendance**   | `attend INDEX d/DATE`<br> e.g., `attend 1 d/12/12/2000`                                                                                                                                                                    |
| **Unmarking Attendance** | `unattend INDEX d/DATE`<br> e.g., `unattend 1 d/12/12/2000`                                                                                                                                                                |
| **Add Consultation**     | `add-consult n/NAME d/DATE tm/TIME a/LOCATION ty/TYPE`<br> e.g., `add-consult n/John Doe d/10/10/2020 tm/18:00 a/SOC Basement ty/personal`                                                                                 |
| **Delete Consultation**  | `del-consult INDEX`<br> e.g., `del-consult 1`                                                                                                                                                                              |
| **List Consultation**    | `list-consult`                                                                                                                                                                                                             |
| **Archive Data Files**   | `archive`                                                                                                                                                                                                                  |
| **Export Attendance**    | `exportatt`                                                                                                                                                                                                                |
| **Help**                 | `help`                                                                                                                                                                                                                     |
| **Exit**                 | `exit`                                                                                                                                                                                                                     |
