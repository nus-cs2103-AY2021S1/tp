---
layout: page
title: User Guide
---

Eva is a simple and lightweight application that **handles HR related administrative tasks**, 
like managing staff performance and recruitment of applicants, faster than a typical mouse/GUI driven app.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `eva.jar` from [here](https://github.com/AY2021S1-CS2103T-W13-1/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for Eva.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`list`** : Lists all staffs and applicants.

   * **`addstaff`**`n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` : Adds a staff named `John Doe` to the application.

   * **`addapplicant`**`n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` : Adds a staff named `John Doe` to the application.

   * **`delstaff`**`3` : Deletes the 3rd staff shown in the staff list.
   
   * **`addleave`**`2 l/d:08/10/2020 d:10/10/2020 l/d:20/10/2020` : Adds two leave records with dates `08/10/2020 to 10/10/2020` and `20/10/2020` to the 2nd staff shown in the current list.

   * **`deleteleave`**`1 10/10/2020` : Deletes the leave record containing the date `10/10/2020` from the 1st person in the current list.

   * **`clear`** : Clears the database.

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

## List of commands you can execute <br>
Here are all the commands you can execute with Eva.
- [Add a staff](#adding-a-staff) <br>
- [Add a applicant](#adding-an-applicant) <br>
- [Record a leave for a staff](#record-leave-taken-by-staff-addleave)


### Viewing help : `help`

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help`

## Centralised Add (Experienced users): `add`

##### Add a staff to application: `add s-`

Format: `add s- n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG] [c/COMMENT] [l/LEAVE]`

##### Add a applicant to application: `add a-`

Format: `add a- n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG] [c/COMMENT] [id/INTERVIEW_DATE]`

##### Add a comment to staff in application: `add <index> s- c-`

Format: `add <index_of_staff> s- c- t:<TITLE> d:<DATE IN DD/MM/YYYY> desc:<DESCRIPTION>`

##### Add a comment to applicant in application: `add <index> a- c-`

Format: `add <index_of_applicant> a- c- t:<TITLE> d:<DATE IN DD/MM/YYYY> desc:<DESCRIPTION>`

##### Add leave to staff in application: `add <index> l/ `

Format: `add <index_of_staff> l/ d:<DATE IN DD/MM/YYYY>`

## Centralised Delete (Experienced Users): `delete`

##### Delete a staff from application: `delete <index_of_staff> s-`

Format: `delete <index_of_staff> s-`

##### Delete a applicant from application: `delete <index_of_applicant> a-`

Format: `delete <index_of_applicant> a-`

##### Delete a comment from staff in application: `delete <index> s- c-`

Format: `delete <index_of_staff> s- c- t:<TITLE>`

##### Delete a comment from applicant in application: `delete <index> a- c-`

Format: `delete <index_of_applicant> a- c- t:<TITLE>`

##### Delete leave from staff in application: `delete <index> l/ `

Format: `delete <index_of_staff> l/ d:<DATE IN DD/MM/YYYY>`



## Staff commands
<a name="adding-a-staff">
<H3> Adding a staff: <code>addstaff</code></H3> 
</a> 

Adds a staff to the application.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​[c/COMMENTS]…`

 - A staff can have any number of tags (including 0) <br>
 - A staff can have any number of comments (including 0) <br><br>
 

Examples:
* `addstaff n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01`
* `addstaff n/Betsy Crowe t/friend e/betsycrowe@example.com a/Betsy street, block 123, #01-01 p/1234567 t/Role: Developer`

### Record leave taken by staff: `addleave`

Records leave taken by a staff that is in the eva database.
Format: `addleave INDEX l/d:DATE [d:DATE]`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
Addition of multiple leaves using the same command is supported. A leave can have either one (single day) or two dates (start and end inclusive).
</div>

Examples:
* `list` followed by `addleave 2 l/d:20/10/2020` adds the leave record with the given date(s) to the 2nd person in the shown list.
* `find Betsy` followed by `addleave 1 l/d:20/10/2020` adds the leave to the 1st person in the results of the `find` command.
* `addleave 1 l/d:08/10/2020 d:10/10/2020 l/d:20/10/2020`
* `addleave 2 l/d:10/10/2020 d:08/10/2020 l/d:09/09/2020`

### Delete leave taken by staff: `deleteleave`

Removes record of leave taken by staff.
Format: `deleteleave INDEX d:DATE`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
Addition of multiple leaves using the same command is supported. A leave can have either one (single day) or two dates (start and end inclusive).
</div>

Examples:
* `list` followed by `deleteleave 2 d:09/09/2020` deletes the leave record of which the given date coincides with from the 2nd person in shown list.
* `find Betsy` followed by `delete 1` deletes the leave from the 1st person in the results of the `find` command.
* `deleteleave 2 d:09/09/2020`

## Applicant commands
<a name="adding-an-applicant">
<H3> Adding an applicant: <code>addapplicant</code></H3> 
</a> 

Adds an applicant to the Eva.

Format: `addapplicant n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [id/INTERVIEW_DATE] [t/TAG]…​[c/COMMENTS]…`

 - An applicant can have any number of tags (including 0) <br>
 - An applicant can have any number of comments (including 0) <br>
 - The interview date has to be in DD/MM/YYYY format <br>
 - :bulb: **Tip** The interview date is optional. If an interview date is not fixed yet, you can leave it and set it later. <br>
<br>


Examples:
* `addapplicant n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01 id/22/11/2020`
* `addapplicant n/Betsy Crowe t/friend e/betsycrowe@example.com a/Betsy street, block 123, #01-01 p/1234567 t/Role: Developer`

<a name="adding-an-application">
<H3> Adding an application: <code>addapplication</code></H3> 
</a> 

Adds an application to an applicant under Eva.

Format: `addapplication INDEX [filepath]`

 - An applicant should be created prior to the addition of it's application.

Examples:
* `addapplication 1 C:\Users\Public\Downloads\resume.txt`

## General commands
### Listing all persons : `list`

Shows a list of all persons in the application.

Format: `list`

### Clearing all entries : `clear`

Clears all entries from the application.

Format: `clear`

### Exiting the program : `exit`

Exits the program. 

Format: `exit`

### Saving the data

Once any change is made to the data from the commands above, the data is saved in the hard disk. 
There is no need to save manually.

### Archiving data files `[coming in v2.0]`

_{explain the feature here}_

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous Eva home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**AddStaff** | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​` <br> e.g., `addstaff n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01`
**AddLeave** | `addleave INDEX l/d:DATE [d:DATE]` <br> e.g., `addleave 2 l/d:08/10/2020 d:10/10/2020 l/d:20/10/2020`
**Clear** | `clear`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**DeleteLeave** | `deleteleave INDEX d:DATE`<br> e.g., `deleteleave 1 10/10/2020`
**Edit** | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`
**Find** | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`
**List** | `list`
**Help** | `help`
