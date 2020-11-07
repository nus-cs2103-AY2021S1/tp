---
layout: page
title: User Guide
---
Welcome to the User Guide for Eva!

- [**1. Introduction**](#1-introduction)
- [**2. Getting Started**](#2-getting-started)
- [**3. Features**](#3-features)
  * [3.1. Common Commands](#31-common-commands)
    + [3.1.1. View](#311-view--view)
    + [3.1.2. List all persons : `list`](#312-list-all-persons--list)
    + [3.1.3. Find a person](#313-find-a-person--find-find_type-)
  * [3.2. General Commands](#32-general-commands)
    + [3.2.1. Clear all entries : `clear`](#321-clear-all-entries--clear)
    + [3.2.2. Find help : `help`](#322-find-help--help)
    + [3.2.3. Exit the program : `exit`](#323-exit-the-program--exit)
  * [3.3. Staff commands](#33-staff-commands)
    + [3.3.1. Add a staff: `adds`](#331-add-a-staff-adds)
    + [3.3.2. Delete a staff: `dels`](#332-delete-a-staff-dels)
    + [3.3.3. Edit a staff: `edits`](#333-edit-a-staff-edits)
    + [3.3.4. Record leave taken by staff: `addl`](#334-record-leave-taken-by-staff-addl)
    + [3.3.5. Delete leave taken by staff: `dell`](#335-delete-leave-taken-by-staff-dell)
  * [3.4. Applicant commands](#34-applicant-commands)
    + [3.4.1 Add an applicant: `adda`](#341-add-an-applicant-adda)
    + [3.4.2. Delete an applicant: `dela`](#342-delete-an-applicant-dela)
    + [3.4.3. Edit an applicant: `edita`](#343-edit-an-applicant-edita)
    + [3.4.4. Add an application: `addapp`](#344-add-an-application-addapp)
    + [3.4.5. Delete an application: `delapp`](#345-delete-an-application-delapp)
    + [3.4.6. Set application status: `setas`](#346-set-application-status-setas)
  * [3.5. Comment Commands](#35-comment-commands)
    + [3.5.1 Add comment to a staff/applicant: `addc INDEX c/ ti/TITLE d/Date desc/DESCRIPTION`](#351-add-comment-to-a-staffapplicant-addc-index-c-tititle-ddate-descdescription)
    + [3.5.2 Delete comment from a staff/applicant: `delc INDEX c/ ti/TITLE_TO_DELETE`](#352-delete-comment-from-a-staffapplicant-delc-index-c-tititle_to_delete)
    + [3.5.3 Edit comment on an applicant: `editc INDEX c/ ti/TITLE_TO_EDIT d/DATE_OF_TITLE_TO_EDIT desc/ NEW_DESC`](#353-edit-comment-on-an-applicant-editc-index-c-tititle_to_edit-ddate_of_title_to_edit-desc-new_desc)
  * [3.6. Archive data files `[coming in v2.0]`](#36-archive-data-files-coming-in-v20)
- [**4. FAQ**](#4-faq)
- [**5. Command summary**](#5-command-summary)
  * [5.1. Common](#51-common)
  * [5.2. General](#52-general)
  * [5.3. Staff](#53-staff)
  * [5.4. Applicant](#54-applicant)

## 1. Introduction

Eva is a simple and lightweight application that **handles HR related administrative tasks**, 
like managing staff performance and recruitment of applicants, faster than a typical mouse/GUI driven app. 
This application is better suited for start-ups with a size of about 5-30 staff. 

This user guide takes you through the basics of Eva and helps you get moving straightaway.

------------------------------------------------------------------------------------------------------------------------

## 2. Getting Started

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `eva.jar` from [here](https://github.com/AY2021S1-CS2103T-W13-1/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for Eva.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. 
Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type a command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   
   <div markdown="block" class="alert alert-info">
   
   **:information_source: Note:**
   Eva launches into the `Staff List Panel` by default.
   However, Eva remembers which list you last looked at and will open at `Applicant List Panel` if you exited the app from there!
   
   </div>
   
   Some example commands you can try:

   * **`list a-`** : Lists all applicants, changes the panel to display the applicant list.
   
   * **`adda`**` n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` : Adds a staff named `John Doe` to the application.
   
   * **`list s-`** : Lists all staffs, changes the panel to display the staff list.

   * **`adds`**` n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` : Adds a staff named `John Doe` to the application.

   * **`dels`**` 3` : Deletes the 3rd staff shown in the staff list.
   
   * **`addl`**` 2 l/d/08/10/2020 d/10/10/2020 l/d/20/10/2020` : Adds two leave records with dates `08/10/2020 to 10/10/2020` and `20/10/2020` to the 2nd staff shown in the current list.

   * **`dell`**` 1 10/10/2020` : Deletes the leave record containing the date `10/10/2020` from the 1st person in the current list.

   * **`find s-`**` Doe` : Finds the staff whose name contains "Doe".
   
   * **`clear s-`** : Clears the staff database.

   * **`exit`** : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## 3. Features
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

<div>
:bulb: **Tip:**

Eva automatically saves any changes made to the records in your hard disk when using the commands below!
There is no need to save manually! <br>
You can also find the records in the `data` folder where the `eva.jar` file is located.

</div>

### 3.1. Common Commands
#### 3.1.1. View : `view`

Brings you to the profile panel of the staff, if on the staff list, or applicant, if on the applicant list, at the specified index. 

Format: `view INDEX`

Example:
* `view 1`

#### 3.1.2. List all persons : `list`

Shows a list of all persons in the application.

Format: `list LIST_TYPE-`

Examples:
* `list s-` : lists all staffs
* `list a-` : lists all applicants

#### 3.1.3. Find a person : `find FIND_TYPE-`

Shows a list of the persons whose name contains one of the given names.

Format `find FIND_TYPE- KEYWORD`

Examples:
* `find s- Doe`
* `find a- John`

### 3.2. General Commands

#### 3.2.1. Clear all entries : `clear`

Clears all entries from Eva.

Format: `clear DATABASE_TYPE-`

Examples:
* `clear s-`
* `clear a-`

#### 3.2.2. Find help : `help`

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help`

#### 3.2.3. Exit the program : `exit`

Exits the program. 

Format: `exit`

### 3.3. Staff commands

<div markdown="span" class="alert alert-primary">

:bulb: **Tip:**<br>

* All commands under staff can only be done on either staff list or staff profile panel except `adds`, which can be done on any panel
 
* While in staff profile, only details of the profile that is being viewed can be changed.

</div>

#### 3.3.1. Add a staff: `adds`

Adds a staff to Eva.

Format: `adds n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​[c/COMMENTS]…`

 - A staff can have any number of tags (including 0) <br>
 - A staff can have any number of comments (including 0) <br>
 
Examples:
* `adds n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01`
* `adds n/Betsy Crowe t/friend e/betsycrowe@example.com a/Betsy street, block 123, #01-01 p/12345678 t/Developer`

#### 3.3.2. Delete a staff: `dels`

Deletes a staff from Eva. 

Format: `dels INDEX`

Example:
* `dels 1`

#### 3.3.3 Edit a staff: `edits`

Edits general details of a staff from eva (excluding leave taken)

Format: `edits INDEX [n/NAME] [p/PHONE_NUMBER] [a/ADDRESS] [e/EMAIL] [c/COMMENT]`

Example:
* `edits 1 n/John Doe p/99999999 a/John Street e/NEW@example.com`


#### 3.3.4. Record leave taken by staff: `addl`

Records leave taken by a staff that is in Eva. <br>
Format: `addl INDEX l/d/DATE [d/DATE] [l/d/DATE [d/DATE]]…​`

<div markdown="span" class="alert alert-primary">

:bulb: **Tip:**

* Addition of multiple leaves using the same command is supported. A leave can have either one (single day) or two dates (start and end inclusive).
* Dates can be input in any order. Eva will sort the leaves and dates according to which date comes first.

</div>

Examples:
* `list s-` followed by `adll 2 l/d/20/10/2020` adds the leave record with the given date(s) to the 2nd person in the shown list.
* `find s- Betsy` followed by `adll 1 l/d/20/10/2020` adds the leave to the 1st person in the results of the `find s-` command.
* `addl 1 l/d/08/10/2020 d/10/10/2020 l/d/20/10/2020`
* `addl 2 l/d/10/10/2020 d/08/10/2020 l/d/09/09/2020`

#### 3.3.5. Delete leave taken by staff: `dell`

Removes record of leave taken by staff. <br>
Format: `dell INDEX d/DATE`

Examples:
* `list s-` followed by `dell 2 d/09/10/2020` deletes the leave record of which the given date coincides with from the 2nd person in shown list.
* `find s- Betsy` followed by `dell 1 d/09/10/2020` deletes the leave from the 1st person in the results of the `find s-` command.
* `dell 2 d/09/10/2020`

### 3.4. Applicant commands

<div>

:bulb: **Tip:**
- All commands under applicant can only be done on either applicant list or applicant profile except `adda`, 
 which can be done on anywhere
- While in profiles, only details of the profile that is being viewed can be changed.

</div>

#### 3.4.1 Add an applicant: `adda`

Adds an applicant to Eva.

Format: `adda n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [id/INTERVIEW_DATE] [t/TAG]…​[c/COMMENTS]…`

 - An applicant can have any number of tags (including 0) <br>
 - An applicant can have any number of comments (including 0) <br>
 - The interview date has to be in DD/MM/YYYY format <br>
 - Once you add an applicant the status would be automatically set as received, if you wish to change it, refer to the feature [setting 
 of application status]() below
 - :bulb: **Tip** The interview date is optional. If an interview date is not fixed yet, you can leave it and set it later. <br>
<br>

Examples:
* `adda n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01 id/22/11/2020`
* `adda n/Betsy Crowe t/friend e/betsycrowe@example.com a/Betsy street, block 123, #01-01 p/1234567 t/Role: Developer`

#### 3.4.2. Delete an applicant: `dela`

Deletes an applicant with the specified index on the displayed list in Eva. 

Format: `dela INDEX`

Example:
* `dela 1`

#### 3.4.3 Edit an applicant: `edita`

Edits general details of an applicant from eva (excluding application status)

Format: `edita INDEX [n/NAME] [p/PHONE_NUMBER] [a/ADDRESS] [e/EMAIL] [c/COMMENT] [id/INTERVIEW_DATE]`

<div markdown="span" class="alert alert-primary">

:bulb: **Tip:**

* Edit of comment needs to be in required format: `c/ ti/TITLE_OF_COMMENT_TO_CHANGE d/DATE_OF_COMMENT_TO_CHANGE DESC/NEW_DESCRIPTION`


</div>

Example:
* `edita 1 n/NEW_NAME p/99999999 a/NEW_ADDRESS e/NEW@example.com`
* `edita 1 id/ 10/10/2010`


#### 3.4.4. Add an application: `addapp`

Adds an application to an applicant under Eva.

Format: `addapp INDEX [filepath]`

 - An applicant should be created prior to the addition of its application.
 - You may use the sample resume by keying in `sample` as filepath.

Example:
* `addapp 1 data\resume.txt`
* `addapp 1 sample`


#### 3.4.5. Delete an application: `delapp`

Deletes an application from an applicant under Eva.

Format: `delapp INDEX`

Example:
* `delapp 1`

#### 3.4.6. Set application status: `setas`
[This feature is coming soon] <br>
Sets the application status of an applicant with the specified index on the displayed list in Eva.

Format: `setas INDEX NEW_APPLICATION_STATUS`

- Application status can only be any one from the following: 
  - received
  - processing
  - accepted
  - rejected
  
Example:
* `setas 1 received`

### 3.5. Comment Commands

Commands to add, delete and edit comments on staff or applicants

<div markdown="span" class="alert alert-primary">

:bulb: **Important:**

* Comment Commands take index reference from which type of person user is viewing. 
    * If user is viewing staff list or profile, comment commands takes index reference from staff list.
    * If user is viewing applicant list or profile, comment commands takes index reference from applicant list.
    * Comments are arranged according to date, then alphabetically if same date.
    * Comments do not support the input `|`.
    * Comment description can only be seen in profiles.

</div>
 
#### 3.5.1 Add comment to a staff/applicant: `addc INDEX c/ `

Adds a comment to a staff/applicant under eva depending on which panel you are in

Format: `addc INDEX c/ ti/TITLE_OF_COMMENT d/DATE_OF_COMMENT desc/DESCRIPTIONS`

Example:
* `addc 1 c/ ti/Working Ethics d/10/10/2010 desc/Good`

#### 3.5.2 Delete comment from a staff/applicant: `delc INDEX c/ `

Deletes a comment from a staff/applicant under eva depending on which panel you are in

Format: `delc INDEX c/ ti/TITLE_OF_COMMENT_TO_DELETE`

Example Scenario:
* Comment to delete has Title: Working Ethics, Date: 10/10/2010, Description:Good, Staff index is 1
* Command: `delc 1 c/ ti/Working Ethics`

#### 3.5.3. Edit comment on a staff/applicant: `editc INDEX c/`

Edits only the description of a comment on a staff. 

Format: `editc INDEX c/ ti/TITLE_OF_COMMENT_TO_CHANGE d/DATE_OF_COMMENT_TO_CHANGE desc/NEW_DESCRIPTION`

Example Scenario:
* Comment to change has Title: Working Ethics, Date: 10/10/2010, Description: Good, and staff index is 1
* Command: `editc 1 c/ ti/Working Ethics d/10/10/2010 desc/Quite Bad`


### 3.6. Archive data files `[coming in v2.0]`

##

_{explain the feature here}_

--------------------------------------------------------------------------------------------------------------------

## 4. FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous Eva home folder.

**Q**: I can't run the app by double clicking! What do I do?<br>
**A**: Open terminal and traverse to the directory the `eva.jar` file is in. Then type the command `java -jar eva.jar`.
--------------------------------------------------------------------------------------------------------------------

## 5. Command summary

### 5.1. Common

| Action   | Format, Examples                                                                                                           |
|----------|----------------------------------------------------------------------------------------------------------------------------|
| **Edit** | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com` |
| **Find** | `find [s- ⎮ a-] KEYWORD [MORE_KEYWORDS]`<br> e.g., `find a-James Jake`                                                                 |
| **List** | `list [s- ⎮ a-]`<br> e.g., `list s-`   
| **Clear** | `clear [s- ⎮ a-]` <br> e.g., `clear s-`           |
| **View** | `view INDEX`<br> e.g., `view 2`                                                                                            |

### 5.2. General

| Action    | Format, Examples   |
|-----------|--------------------|
| **Help**  | `help`             |
| **Exit**  | `exit`             |

### 5.3. Staff

| Action          | Format, Examples                                                                                                                                        |
|-----------------|---------------------------------------------------------------------------------------------------------------------------------------------------------|
| **AddStaff**    | `adds n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​` <br> e.g., `addstaff n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` |
| **DeleteStaff** | `dels INDEX`<br> e.g., `delstaff 1`                                                                                                                 |
| **AddLeave**    | `addl INDEX l/d/DATE [d/DATE]` <br> e.g., `addl 2 l/d/08/10/2020 d/10/10/2020 l/d/20/10/2020`                                                   |
| **DeleteLeave** | `dell INDEX d/DATE`<br> e.g., `dell 1 d/10/10/2020`                                                                                       |

### 5.4. Applicant

| Action                | Format, Examples                                                                                                 |
|-----------------------|------------------------------------------------------------------------------------------------------------------|
| **AddApplicant**      | `adda` <br> e.g., `adda n/John Doe p/98765432 e/jd@example.com a/John street, block 123, #01-01` |
| **DeleteApplicant**   | `dela` <br> e.g., `dela 1`                                                                       |
| **AddApplication**    | `addapp INDEX [filepath]` <br> e.g., `addapp 1 C:\Users\Public\Downloads\resume.txt`             |
| **DeleteApplication** | `delapp INDEX` <br> e.g., `delapp 1`                                                       |
| **SetAppStatus**      | `setas INDEX NEW_APPLICATION_STATUS` <br> e.g., `setas 1 processing`                               |


