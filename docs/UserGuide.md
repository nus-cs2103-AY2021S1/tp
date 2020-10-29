---
layout: page
title: User Guide
---
Welcome to the User Guide for Eva!

- [1. Introduction](#1-introduction)
- [2. Getting Started](#2-getting-started)
- [3. Features](#3-features)
  * [3.1. Common Commands](#31-common-commands)
    + [3.1.1. View](#311-view--view)
    + [3.1.2. List all persons : `list`](#312-list-all-persons--list)
    + [3.1.3. Find a person](#313-find-a-person--find--find_type)
  * [3.2. General Commands](#32-general-commands)
    + [3.2.1. Clear all entries : `clear`](#321-clear-all-entries--clear)
    + [3.2.2. Find help : `help`](#322-find-help--help)
    + [3.2.3. Exit the program : `exit`](#323-exit-the-program--exit)
  * [3.3. Staff commands](#33-staff-commands)
    + [3.3.1. Add a staff: `addstaff`](#331-add-a-staff-addstaff)
    + [3.3.2. Delete a staff: `delstaff`](#332-delete-a-staff-delstaff)
    + [3.3.3. Edit a staff: `editstaff`](#333-edit-a-staff-editstaff)
    + [3.3.4. Record leave taken by staff: `addleave`](#334-record-leave-taken-by-staff-addleave)
    + [3.3.5. Delete leave taken by staff: `deleteleave`](#335-delete-leave-taken-by-staff-deleteleave)
    + [3.3.6. Edit leave taken by staff: `editleave`](#336-edit-leave-taken-by-staff-editleave)
  * [3.4. Applicant commands](#34-applicant-commands)
    + [3.4.1 Add an applicant: `addapplicant`](#341-add-an-applicant-addapplicant)
    + [3.4.2. Delete an applicant: `delapplicant`](#342-delete-an-applicant-delapplicant)
    + [3.4.3. Edit an applicant: `editapplicant`](#343-edit-an-applicant-editapplicant)
    + [3.4.4. Add an application: `addapplication`](#344-add-an-application-addapplication)
    + [3.4.5. Delete an application: `deleteapplication`](#345-delete-an-application-deleteapplication)
    + [3.4.6. Set application status: `setappstatus`](#346-set-application-status-setappstatus)
  * [3.5. Comment commands](#35-comment-commands)
    + [3.5.1 Add comment to a staff: `addcomment INDEX s-`](#351-add-comment-to-a-staff-addcomment-index-s-)
    + [3.5.2 Add comment to an applicant: `addcomment INDEX a-`](#352-add-comment-to-an-applicant-addcomment-index-a-)
    + [3.5.3 Delete comment from a staff: `deletecomment INDEX s-`](#353-delete-comment-from-a-staff-deletecomment-index-s-)
    + [3.5.4 Delete comment from an applicant: `deletecomment INDEX a-`](#354-delete-comment-from-an-applicant-deletecomment-index-a-)
    + [3.5.5 Edit comment on a staff: `editcomment INDEX s-`](#355-edit-comment-on-a-staff-editcomment-index-s-)
    + [3.5.6 Edit comment on an applicant: `editcomment INDEX a-`](#356-edit-comment-on-an-applicant-editcomment-index-a-)
  * [3.6. Centralised Add (Experienced users): `add`](#36-quick-add-experienced-users-add)
    + [3.6.1. Add a staff to application: `add s-`](#361-add-a-staff-to-eva-add-s-)
    + [3.6.2. Add a applicant to application: `add a-`](#362-add-a-applicant-to-eva-add-a-)
    + [3.6.3. Add a comment to staff in application: `add <index> s- c/`](#363-add-a-comment-to-staff-in-eva-add-index-s--c)
    + [3.6.4. Add a comment to applicant in application: `add <index> a- c/`](#364-add-a-comment-to-applicant-in-eva-add-index-a--c)
    + [3.6.5. Add leave to staff in application: `add <index> l/ `](#365-add-leave-to-staff-in-eva-add-index-l-)
  * [3.7. Centralised Delete (Experienced Users): `delete`](#37-quick-delete-experienced-users-delete)
    + [3.7.1. Delete a staff from application: `delete <index_of_staff> s-`](#371-delete-a-staff-from-eva-delete-index_of_staff-s-)
    + [3.7.2. Delete a applicant from application: `delete <index_of_applicant> a-`](#372-delete-a-applicant-from-eva-delete-index_of_applicant-a-)
    + [3.7.3. Delete a comment from staff in application: `delete <index> s- c/`](#373-delete-a-comment-from-staff-in-eva-delete-index-s--c)
    + [3.7.4. Delete a comment from applicant in application: `delete <index> a- c/`](#374-delete-a-comment-from-applicant-in-eva-delete-index-a--c)
    + [3.7.5. Delete leave from staff in application: `delete <index> l/ `](#375-delete-leave-from-staff-in-eva-delete-index-l-)
  * [3.8. Quick Edit (Experienced users): `edit`](#38-quick-edit-experienced-users-edit)
    + [3.8.1. Edit a staff](#381-edit-a-staff)
      + [3.8.1.1 Edit general information of staff : `edit INDEX s-`](#3811-edit-general-information-of-staff--edit-index-s-)
      + [3.8.1.2 Edit comment of a staff: `edit INDEX s- c/`](#3812-edit-comment-of-a-staff-edit-index-s--c)
    + [3.8.2 Edit an applicant](#382-edit-an-applicant)
      + [3.8.2.1 Edit general information of staff : `edit INDEX a-`](#3821-edit-general-information-of-applicant--edit-index-a-)
      + [3.8.2.2 Edit comment of an applicant: `edit INDEX a- c/`](#3822-edit-comment-of-an-applicant-edit-index-a--c)
      + [3.8.2.3 Edit Interview Date of an applicant: `edit INDEX a- id/`](#3823-edit-interview-date-of-an-applicant-edit-index-a--id)
  * [3.9. Archive data files `[coming in v2.0]`](#39-archive-data-files-coming-in-v20)
- [4. FAQ](#4-faq)
- [5. Command summary](#5-command-summary)
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
   Eva launches into the Staff List Panel.
   
   </div>
   
   Some example commands you can try:

   * **`list -applicant`** : Lists all applicants.
   
   * **`addapplicant`**`n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` : Adds a staff named `John Doe` to the application.
   
   * **`list -staff`** : Lists all staffs.

   * **`addstaff`**`n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` : Adds a staff named `John Doe` to the application.

   * **`delstaff`**`3` : Deletes the 3rd staff shown in the staff list.
   
   * **`addleave`**`2 l/d/08/10/2020 d/10/10/2020 l/d/20/10/2020` : Adds two leave records with dates `08/10/2020 to 10/10/2020` and `20/10/2020` to the 2nd staff shown in the current list.

   * **`deleteleave`**`1 10/10/2020` : Deletes the leave record containing the date `10/10/2020` from the 1st person in the current list.

   * **`find`**`-s Doe` : Finds the staff whose name contains "Doe".
   
   * **`clear`** : Clears the database.

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


Note: Once any change is made to the data from the commands below, the data is saved in the hard disk. 
There is no need to save manually. <br>
### 3.1. Common Commands
#### 3.1.1. View : `view`

Brings you to the profile panel of the staff, if on the staff list, or applicant, if on the applicant list, at the specified index. 

Format: `view INDEX`

Example:
* `view 1`

#### 3.1.2. List all persons : `list`

Shows a list of all persons in the application.

Format: `list -LIST_TYPE`

Examples:
* `list -staff`
* `list -applicant`

#### 3.1.3. Find a person : `find -FIND_TYPE`

Shows a list of the persons whose name contains one of the given names.

Format `find -FIND_TYPE`

Examples:
* `find -staff Doe`
* `find -applicant John`

### 3.2. General Commands

#### 3.2.1. Clear all entries : `clear`

Clears all entries from Eva.

Format: `clear`

#### 3.2.2. Find help : `help`

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help`

#### 3.2.3. Exit the program : `exit`

Exits the program. 

Format: `exit`

### 3.3. Staff commands

#### 3.3.1. Add a staff: `addstaff`

Adds a staff to Eva.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​[c/COMMENTS]…`

 - A staff can have any number of tags (including 0) <br>
 - A staff can have any number of comments (including 0) <br>
 
Examples:
* `addstaff n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01`
* `addstaff n/Betsy Crowe t/friend e/betsycrowe@example.com a/Betsy street, block 123, #01-01 p/1234567 t/Role: Developer`

#### 3.3.2. Delete a staff: `delstaff`

Deletes a staff from Eva. 

Format: `delstaff INDEX`

Example:
* `delstaff 1`

#### 3.3.3 Edit a staff: `editstaff`

Edits general details of a staff from eva (excluding leave taken)

Format: `editstaff INDEX [n/NAME] [p/PHONE_NUMBER] [a/ADDRESS] [e/EMAIL] [c/COMMENT]`

<div markdown="span" class="alert alert-primary">

:bulb: **Tip:**

* Edit of comment needs to be in required format: `c/ ti/TITLE_OF_COMMENT_TO_CHANGE d/DATE_OF_COMMENT_TO_CHANGE DESC/NEW_DESCRIPTION`

</div>

Example:
* `editstaff 1 n/NEW_NAME p/99999999 a/NEW_ADDRESS e/NEW@example.com`
* `editstaff 1 c/ ti/title d/10/10/2010 desc/new_description`


#### 3.3.4. Record leave taken by staff: `addleave`

Records leave taken by a staff that is in Eva. <br>
Format: `addleave INDEX l/d/DATE [d/DATE]`

<div markdown="span" class="alert alert-primary">

:bulb: **Tip:**

* Addition of multiple leaves using the same command is supported. A leave can have either one (single day) or two dates (start and end inclusive).
* Dates can be input in any order. Eva will sort the leaves and dates according to which date comes first.

</div>

Examples:
* `list` followed by `addleave 2 l/d/20/10/2020` adds the leave record with the given date(s) to the 2nd person in the shown list.
* `find Betsy` followed by `addleave 1 l/d/20/10/2020` adds the leave to the 1st person in the results of the `find` command.
* `addleave 1 l/d/08/10/2020 d/10/10/2020 l/d/20/10/2020`
* `addleave 2 l/d/10/10/2020 d/08/10/2020 l/d/09/09/2020`

#### 3.3.5. Delete leave taken by staff: `deleteleave`

Removes record of leave taken by staff. <br>
Format: `deleteleave INDEX d/DATE`

Examples:
* `list` followed by `deleteleave 2 d/09/09/2020` deletes the leave record of which the given date coincides with from the 2nd person in shown list.
* `find Betsy` followed by `deleteleave 1 d/09/09/2020` deletes the leave from the 1st person in the results of the `find` command.
* `deleteleave 2 d/09/09/2020`

#### 3.3.6. Edit leave taken by staff: `editleave`
[This feature is coming soon]

### 3.4. Applicant commands

#### 3.4.1 Add an applicant: `addapplicant`

Adds an applicant to Eva.

Format: `addapplicant n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [id/INTERVIEW_DATE] [t/TAG]…​[c/COMMENTS]…`

 - An applicant can have any number of tags (including 0) <br>
 - An applicant can have any number of comments (including 0) <br>
 - The interview date has to be in DD/MM/YYYY format <br>
 - Once you add an applicant the status would be automatically set as received, if you wish to change it, refer to the feature [setting 
 of application status]() below
 - :bulb: **Tip** The interview date is optional. If an interview date is not fixed yet, you can leave it and set it later. <br>
<br>

Examples:
* `addapplicant n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01 id/22/11/2020`
* `addapplicant n/Betsy Crowe t/friend e/betsycrowe@example.com a/Betsy street, block 123, #01-01 p/1234567 t/Role: Developer`

#### 3.4.2. Delete an applicant: `delapplicant`

Deletes an applicant with the specified index on the displayed list in Eva. 

Format: `delapplicant INDEX`

Example:
* `delapplicant 1`

#### 3.4.3 Edit an applicant: `editapplicant`

Edits general details of an applicant from eva (excluding application status)

Format: `editapplication INDEX [n/NAME] [p/PHONE_NUMBER] [a/ADDRESS] [e/EMAIL] [c/COMMENT] [id/INTERVIEW_DATE]`

<div markdown="span" class="alert alert-primary">

:bulb: **Tip:**

* Edit of comment needs to be in required format: `c/ ti/TITLE_OF_COMMENT_TO_CHANGE d/DATE_OF_COMMENT_TO_CHANGE DESC/NEW_DESCRIPTION`

</div>

Example:
* `editapplicant 1 n/NEW_NAME p/99999999 a/NEW_ADDRESS e/NEW@example.com`
* `editapplicant 1 c/ ti/title d/10/10/2010 desc/new_description`
* `editapplicant 1 id/ 10/10/2010`


#### 3.4.4. Add an application: `addapplication`

Adds an application to an applicant under Eva.

Format: `addapplication INDEX [filepath]`

 - An applicant should be created prior to the addition of its application.
 - You may use the sample resume by keying in `sample` as filepath.

Example:
* `addapplication 1 C:\Users\Public\Downloads\resume.txt`
* `addapplication 1 sample`


#### 3.4.5. Delete an application: `deleteapplication`

Deletes an application from an applicant under Eva.

Format: `deleteapplication INDEX`

Example:
* `deleteapplication 1`

#### 3.4.6. Set application status: `setappstatus`
[This feature is coming soon] <br>
Sets the application status of an applicant with the specified index on the displayed list in Eva.

Format: `setappstatus INDEX NEW_APPLICATION_STATUS`

- Application status can only be any one from the following: 
  - received
  - processing
  - accepted
  - rejected
  
Example:
* `setappstatus 1 received`

### 3.5. Comment Commands

Commands to add, delete and edit comments on staff or applicants
 
#### 3.5.1 Add comment to a staff: `addcomment INDEX s-`

Adds a comment to a staff under eva

Format: `addcomment 1 s- ti/TITLE_OF_COMMENT d/DATE_OF_COMMENT desc/DESCRIPTIONS`

Example:
* `addcomment 1 s- ti/title d/10/10/2010 desc/description`

#### 3.5.2 Add comment to an applicant: `addcomment INDEX a-`

Adds a comment to an applicant under eva

Format: `addcomment 1 a- ti/TITLE_OF_COMMENT d/DATE_OF_COMMENT desc/DESCRIPTIONS`

Example:
* `addcomment 1 a- ti/title d/10/10/2010 desc/description`

#### 3.5.3 Delete comment from a staff: `deletecomment INDEX s-`

Deletes a comment from a staff under eva

Format: `deletecomment 1 s- ti/TITLE_OF_COMMENT_TO_DELETE`

Example:
* `deletecomment 1 s- ti/title`

#### 3.5.4 Delete comment from an applicant: `deletecomment INDEX a-`

Deletes a comment from an applicant under eva

Format: `deletecomment 1 a- ti/TITLE_OF_COMMENT_TO_DELETE`

Example:
* `deletecomment 1 a- ti/title`

#### 3.5.5 Edit comment on a staff: `editcomment INDEX s-`

Edits the description of an existing comment on a staff under eva

Format: `editcomment 1 s- ti/TITLE_OF_COMMENT_TO_EDIT d/DATE_OF_COMMENT_TO_EDIT desc/NEW_DESCRIPTION`

Example: 
* `editcomment 1 s- ti/title d/10/10/2010 desc/new_description`

#### 3.5.6 Edit comment on an applicant: `editcomment INDEX a-`

Edits the description of an existing comment on an applicant under eva

Format: `editcomment 1 a- ti/TITLE_OF_COMMENT_TO_EDIT d/DATE_OF_COMMENT_TO_EDIT desc/NEW_DESCRIPTION`

Example: 
* `editcomment 1 a- ti/title d/10/10/2010 desc/new_description`

### 3.6. Quick Add (Experienced Users): `add`

These are some shortcuts you can use for the commands above.

#### 3.6.1. Add a staff to Eva: `add s-`

Format: `add s- n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG] [c/COMMENT]`

Example:
* `add s- n/Alex p/99999999 e/hi@example.com a/address`

#### 3.6.2. Add a applicant to Eva: `add a-`

Format: `add a- n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG] [c/COMMENT] [id/INTERVIEW_DATE]`

Example: 
* `add a- n/Alex p/99999999 e/hi@example.com a/address`

#### 3.6.3. Add a comment to staff in Eva: `add INDEX s- c/`

Format: `add INDEX_OF_STAFF s- c/ ti/TITLE d/DATE (IN DD/MM/YYYY) desc/DESCRIPTION`

Example:
* `add 1 s- c/ ti/title d/10/10/2010 desc/new_description`

#### 3.6.4. Add a comment to applicant in Eva: `add INDEX a- c/`

Format: `add INDEX_OF_APPLICANT a- c/ ti/TITLE d/DATE (IN DD/MM/YYYY) desc/DESCRIPTION`

Example:
* `add 1 a- c/ ti/title d/10/10/2010 desc/new_description`

#### 3.6.5. Add leave to staff in Eva: `add INDEX l/ `

Format: `add INDEX_OF_STAFF l/ d/DATE (IN DD/MM/YYYY)`

Example:
* `add 1 l/ d/10/10/2010`

### 3.7. Quick Delete (Experienced Users): `delete`

#### 3.7.1. Delete a staff from Eva: `delete INDEX_OF_STAFF s-`

Format: `delete INDEX_OF_STAFF s-`

Example:
* `delete 1 s-`

#### 3.7.2. Delete a applicant from Eva: `delete INDEX_OF_APPLICANT a-`

Format: `delete INDEX_OF_APPLICANT a-`

Example:
* `delete 1 a-`

#### 3.7.3. Delete a comment from staff in Eva: `delete INDEX s- c/`

Format: `delete INDEX_OF_STAFF s- c/ ti/TITLE`

Example: 
* `delete 1 s- c/ ti/title`

#### 3.7.4. Delete a comment from applicant in Eva: `delete INDEX a- c/`

Format: `delete INDEX_OF_APPLICANT a- c/ ti/TITLE`

Example:
* `delete 1 a- c/ ti/title`

#### 3.7.5. Delete leave from staff in Eva: `delete INDEX l/ `

Format: `delete INDEX_OF_STAFF l/ d/DATE (IN DD/MM/YYYY/)`

Example:
* `delete 1 l/ d/10/10/2010`

### 3.8. Quick Edit (Experienced Users): `edit`

#### 3.8.1. Edit a staff

Edits specified fields of a staff

##### 3.8.1.1 Edit general information of staff : `edit INDEX s-`

*Fields: Name, Address, Email, Phone, Tag*

 - Can edit the name, phone number, email, address and tag of a staff
 - Must change at least one field of a staff

Format: `edit INDEX s- [n/NAME] [a/ADDRESS] [e/EMAIL] [p/PHONE] [t/TAG]`

Example:
* `edit 1 s- n/example a/new_address`

##### 3.8.1.2 Edit comment of a staff: `edit INDEX s- c/`

 - Can edit descriptions of specific comments
 - Comment to be changed is identified by title and date

*Fields: Comments*

Format: `edit INDEX s- c/ ti/TITLE_OF_COMMENT d/DATE_OF_COMMENT desc/NEW_DESCRIPTION`

Example:
* `edit 1 s- c/ ti/title d/10/10/2010 desc/new_description`

#### 3.8.2 Edit an applicant 

Edits specified fields of applicant

##### 3.8.2.1 Edit general information of applicant : `edit INDEX a-`

*Fields: Name, Address, Email, Phone, Tag*

 - Can edit the name, phone number, email, address and tag of a applicant
 - Must change at least one field of an applicant

Format: `edit INDEX a- [n/NAME] [a/ADDRESS] [e/EMAIL] [p/PHONE] [t/TAG]`

##### 3.8.2.2 Edit comment of an applicant: `edit INDEX a- c/`

 - Can edit descriptions of specific comments
 - Comment to be changed is identified by title and date

*Fields: Comments*

Format: `edit INDEX a- c/ ti/TITLE_OF_COMMENT d/DATE_OF_COMMENT desc/NEW_DESCRIPTION`

Example:
* `edit 1 a- n/example a/new_address`

##### 3.8.2.3 Edit Interview Date of an applicant: `edit INDEX a- id/`

 - Can edit interview date of an applicant
 
*Fields: Interview Date*
 
Format: `edit INDEX a- id/ NEW_DATE_OF_INTERVIEW`

Example: 
* `edit 1 a- id/10/10/2010`

### 3.9. Archive data files `[coming in v2.0]`

##

_{explain the feature here}_

--------------------------------------------------------------------------------------------------------------------

## 4. FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous Eva home folder.

--------------------------------------------------------------------------------------------------------------------

## 5. Command summary

### 5.1. Common

| Action   | Format, Examples                                                                                                           |
|----------|----------------------------------------------------------------------------------------------------------------------------|
| **Edit** | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com` |
| **Find** | `find [-applicant ⎮ -staff] KEYWORD [MORE_KEYWORDS]`<br> e.g., `find -applicant James Jake`                                                                 |
| **List** | `list`<br> e.g., `list -staff`                                                                                             |
| **View** | `view INDEX`<br> e.g., `view 2`                                                                                            |

### 5.2. General

| Action    | Format, Examples   |
|-----------|--------------------|
| **Clear** | `clear`            |
| **Help**  | `help`             |
| **Exit**  | `exit`             |

### 5.3. Staff

| Action          | Format, Examples                                                                                                                                        |
|-----------------|---------------------------------------------------------------------------------------------------------------------------------------------------------|
| **AddStaff**    | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​` <br> e.g., `addstaff n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` |
| **DeleteStaff** | `delstaff INDEX`<br> e.g., `delstaff 1`                                                                                                                 |
| **AddLeave**    | `addleave INDEX l/d/DATE [d/DATE]` <br> e.g., `addleave 2 l/d/08/10/2020 d/10/10/2020 l/d/20/10/2020`                                                   |
| **DeleteLeave** | `deleteleave INDEX d/DATE`<br> e.g., `deleteleave 1 d/10/10/2020`                                                                                       |

### 5.4. Applicant

| Action                | Format, Examples                                                                                                 |
|-----------------------|------------------------------------------------------------------------------------------------------------------|
| **AddApplicant**      | `addapplicant` <br> e.g., `addapplicant n/John Doe p/98765432 e/jd@example.com a/John street, block 123, #01-01` |
| **DeleteApplicant**   | `delapplicant` <br> e.g., `delapplicant 1`                                                                       |
| **AddApplication**    | `addapplication INDEX [filepath]` <br> e.g., `addapplication 1 C:\Users\Public\Downloads\resume.txt`             |
| **DeleteApplication** | `deleteapplication INDEX` <br> e.g., `deleteapplication 1`                                                       |
| **SetAppStatus**      | `setappstatus INDEX NEW_APPLICATION_STATUS` <br> e.g., `setappstatus 1 processing`                               |


