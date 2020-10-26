---
layout: page
title: User Guide
---
- [1. Introduction](#1-introduction)
- [2. Getting Started](#2-getting-started)
- [3. Features](#3-features)
  * [3.1. Common Commands](#31-common-commands)
    + [3.1.1. View](#311-view)
    + [3.1.2. Listing all persons : `list`](#312-listing-all-persons----list-)
    + [3.1.3. Find](#313-find)
  * [3.2. General Commands](#32-general-commands)
    + [3.2.1. Clearing all entries : `clear`](#321-clearing-all-entries----clear-)
    + [3.2.2. Finding help : `help`](#322-finding-help----help-)
    + [3.2.3. Exiting the program : `exit`](#323-exiting-the-program----exit-)
  * [3.3. Staff commands](#33-staff-commands)
    + [3.3.1. Adding a staff: `addstaff`](#331-adding-a-staff---addstaff-)
    + [3.3.2. Deleting a staff: `delstaff`](#332-deleting-a-staff---delstaff-)
    + [3.3.3. Record leave taken by staff: `addleave`](#333-record-leave-taken-by-staff---addleave-)
    + [3.3.4. Delete leave taken by staff: `deleteleave`](#334-delete-leave-taken-by-staff---deleteleave-)
    + [3.3.5. Edit leave taken by staff: `editleave`](#335-edit-leave-taken-by-staff---editleave-)
  * [3.4. Applicant commands](#34-applicant-commands)
    + [3.4.1 Adding an applicant: `addapplicant`](#341-adding-an-applicant---addapplicant-)
    + [3.4.2. Deleting an applicant:](#342-deleting-an-applicant-)
    + [3.4.3. Adding an application: `addapplication`](#343-adding-an-application---addapplication-)
    + [3.4.4. Deleting an application: `deleteapplication`](#344-deleting-an-application---deleteapplication-)
  * [3.5. Centralised Add (Experienced users): `add`](#35-centralised-add--experienced-users----add-)
    + [3.5.1. Add a staff to application: `add s-`](#351-add-a-staff-to-application---add-s--)
    + [3.5.2. Add a applicant to application: `add a-`](#352-add-a-applicant-to-application---add-a--)
    + [3.5.3. Add a comment to staff in application: `add <index> s- c-`](#353-add-a-comment-to-staff-in-application---add--index--s--c--)
    + [3.5.4. Add a comment to applicant in application: `add <index> a- c-`](#354-add-a-comment-to-applicant-in-application---add--index--a--c--)
    + [3.5.5. Add leave to staff in application: `add <index> l/ `](#355-add-leave-to-staff-in-application---add--index--l---)
  * [3.6. Centralised Delete (Experienced Users): `delete`](#36-centralised-delete--experienced-users----delete-)
    + [3.6.1. Delete a staff from application: `delete <index_of_staff> s-`](#361-delete-a-staff-from-application---delete--index-of-staff--s--)
    + [3.6.2. Delete a applicant from application: `delete <index_of_applicant> a-`](#362-delete-a-applicant-from-application---delete--index-of-applicant--a--)
    + [3.6.3. Delete a comment from staff in application: `delete <index> s- c-`](#363-delete-a-comment-from-staff-in-application---delete--index--s--c--)
    + [3.6.4. Delete a comment from applicant in application: `delete <index> a- c-`](#364-delete-a-comment-from-applicant-in-application---delete--index--a--c--)
    + [3.6.5. Delete leave from staff in application: `delete <index> l/ `](#365-delete-leave-from-staff-in-application---delete--index--l---)
  * [3.7. Quick Edit (Experienced users): `edit`](#37-quick-edit-experienced-users-edit)
    + [3.7.1. Edit a staff](#371-edit-a-staff)
      + [3.7.1.1 Edit general information of staff : `edit INDEX s-`](#3711-edit-general-information-of-staff--edit-index-a-)
      + [3.7.1.2 Edit comment of a staff: `edit INDEX s- c/`](#3712-edit-comment-of-a-staff-edit-index-s--c)
    + [3.7.2 Edit an applicant](#372-edit-an-applicant)
      + [3.7.2.1 Edit general information of staff : `edit INDEX a-`](#3721-edit-general-information-of-staff--edit-index-a-)
      + [3.7.2.2 Edit comment of an applicant: `edit INDEX a- c/`](#3722-edit-comment-of-an-applicant-edit-index-a--c)
      + [3.7.2.3 Edit Interview Date of an applicant: `edit INDEX a- id/`](#3723-edit-interview-date-of-an-applicant-edit-index-a--id)
  * [3.8. Archiving data files `[coming in v2.0]`](#37-archiving-data-files---coming-in-v20--)
- [4. FAQ](#4-faq)
- [5. Command summary](#5-command-summary)
  * [5.1. Common](#51-common)
  * [5.2. General](#52-general)
  * [5.3. Staff](#53-staff)
  * [5.4. Applicant](#54-applicant)

## 1. Introduction

Eva is a simple and lightweight application that **handles HR related administrative tasks**, 
like managing staff performance and recruitment of applicants, faster than a typical mouse/GUI driven app.

This user guide takes you through the basics of Eva and helps you get moving straightaway.

## 2. Getting Started

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `eva.jar` from [here](https://github.com/AY2021S1-CS2103T-W13-1/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for Eva.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:
   <div markdown="block" class="alert alert-info">
   
   **:information_source: Note:**
   Eva launches into the Staff List Panel.
   </div>

   * **`list -applicant`** : Lists all applicants.
   
   * **`addapplicant`**`n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` : Adds a staff named `John Doe` to the application.
   
   * **`list -staff`** : Lists all staffs.

   * **`addstaff`**`n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` : Adds a staff named `John Doe` to the application.

   * **`delstaff`**`3` : Deletes the 3rd staff shown in the staff list.
   
   * **`addleave`**`2 l/d:08/10/2020 d:10/10/2020 l/d:20/10/2020` : Adds two leave records with dates `08/10/2020 to 10/10/2020` and `20/10/2020` to the 2nd staff shown in the current list.

   * **`deleteleave`**`1 10/10/2020` : Deletes the leave record containing the date `10/10/2020` from the 1st person in the current list.

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
There is no need to save manually.
### 3.1. Common Commands

#### 3.1.1. View : `view`

Brings user to the profile panel of the staff, if on the staff list, or applicant, if on the applicant list, at the specified index. 

Format: `view INDEX`

Examples:
* `view 1`

#### 3.1.2. Listing all persons : `list`

Shows a list of all persons in the application.

Format: `list -LIST_TYPE`

Examples:
* `list -staff`
* `list -applicant`

#### 3.1.3. Find

### 3.2. General Commands

#### 3.2.1. Clearing all entries : `clear`

Clears all entries from the application.

Format: `clear`

#### 3.2.2. Finding help : `help`

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help`

#### 3.2.3. Exiting the program : `exit`

Exits the program. 

Format: `exit`

### 3.3. Staff commands
#### 3.3.1. Adding a staff: `addstaff`

Adds a staff to the application.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​[c/COMMENTS]…`

 - A staff can have any number of tags (including 0) <br>
 - A staff can have any number of comments (including 0) <br><br>
 
Examples:
* `addstaff n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01`
* `addstaff n/Betsy Crowe t/friend e/betsycrowe@example.com a/Betsy street, block 123, #01-01 p/1234567 t/Role: Developer`

#### 3.3.2. Deleting a staff: `delstaff`

Deletes a staff from the application. 

Format: `delstaff INDEX`

Examples:
* `delstaff 1`

#### 3.3.3. Record leave taken by staff: `addleave`

Records leave taken by a staff that is in the eva database.
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

#### 3.3.4. Delete leave taken by staff: `deleteleave`

Removes record of leave taken by staff.
Format: `deleteleave INDEX d/DATE`

Examples:
* `list` followed by `deleteleave 2 d/09/09/2020` deletes the leave record of which the given date coincides with from the 2nd person in shown list.
* `find Betsy` followed by `deleteleave 1 d/09/09/2020` deletes the leave from the 1st person in the results of the `find` command.
* `deleteleave 2 d/09/09/2020`

#### 3.3.5. Edit leave taken by staff: `editleave`

### 3.4. Applicant commands

#### 3.4.1 Adding an applicant: `addapplicant`

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

#### 3.4.2. Deleting an applicant:

#### 3.4.3. Adding an application: `addapplication`

Adds an application to an applicant under Eva.

Format: `addapplication INDEX [filepath]`

 - An applicant should be created prior to the addition of its application.

Examples:
* `addapplication 1 C:\Users\Public\Downloads\resume.txt`

#### 3.4.4. Deleting an application: `deleteapplication`

Deletes an application from an applicant under Eva.

Format: `deleteapplication INDEX`

Examples:
* `deleteapplication 1`

### 3.5. Quick Add (Experienced users): `add`

#### 3.5.1. Add a staff to application: `add s-`

Format: `add s- n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG] [c/COMMENT]`

#### 3.5.2. Add a applicant to application: `add a-`

Format: `add a- n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG] [c/COMMENT] [id/INTERVIEW_DATE]`

#### 3.5.3. Add a comment to staff in application: `add <index> s- c/`

Format: `add <index_of_staff> s- c/ ti/TITLE d/DATE (IN DD/MM/YYYY) desc/DESCRIPTION`

#### 3.5.4. Add a comment to applicant in application: `add <index> a- c/`

Format: `add <index_of_applicant> a- c/ ti/TITLE d/DATE (IN DD/MM/YYYY) desc/DESCRIPTION`

#### 3.5.5. Add leave to staff in application: `add <index> l/ `

Format: `add <index_of_staff> l/ d/DATE (IN DD/MM/YYYY)`

### 3.6. Quick Delete (Experienced Users): `delete`

#### 3.6.1. Delete a staff from application: `delete <index_of_staff> s-`

Format: `delete <index_of_staff> s-`

#### 3.6.2. Delete a applicant from application: `delete <index_of_applicant> a-`

Format: `delete <index_of_applicant> a-`

#### 3.6.3. Delete a comment from staff in application: `delete <index> s- c/`

Format: `delete <index_of_staff> s- c/ ti/TITLE`

#### 3.6.4. Delete a comment from applicant in application: `delete <index> a- c/`

Format: `delete <index_of_applicant> a- c/ ti/TITLE`

#### 3.6.5. Delete leave from staff in application: `delete <index> l/ `

Format: `delete <index_of_staff> l/ d/DATE (IN DD/MM/YYYY/)`

### 3.7. Quick Edit (Experienced users): `edit`

#### 3.7.1. Edit a staff

Edits specified fields of a staff

##### 3.7.1.1 Edit general information of staff : `edit INDEX s-`

*Fields: Name, Address, Email, Phone, Tag*

 - Can edit the name, phone number, email, address and tag of a staff
 - Must change at least one field of a staff

Format: `edit INDEX s- [n/NAME] [a/ADDRESS] [e/EMAIL] [p/PHONE] [t/TAG]`

##### 3.7.1.2 Edit comment of a staff: `edit INDEX s- c/`

 - Can edit descriptions of specific comments
 - Comment to be changed is identified by title and date

*Fields: Comments*

Format: `edit INDEX s- c/ ti/TITLE_OF_COMMENT d/DATE_OF_COMMENT desc/NEW_DESCRIPTION`

#### 3.7.2 Edit an applicant

Edits specified fields of applicant

##### 3.7.2.1 Edit general information of staff : `edit INDEX a-`

*Fields: Name, Address, Email, Phone, Tag*

 - Can edit the name, phone number, email, address and tag of a applicant
 - Must change at least one field of an applicant

Format: `edit INDEX a- [n/NAME] [a/ADDRESS] [e/EMAIL] [p/PHONE] [t/TAG]`

##### 3.7.2.2 Edit comment of an applicant: `edit INDEX a- c/`

 - Can edit descriptions of specific comments
 - Comment to be changed is identified by title and date

*Fields: Comments*

Format: `edit INDEX a- c/ ti/TITLE_OF_COMMENT d/DATE_OF_COMMENT desc/NEW_DESCRIPTION`

##### 3.7.2.3 Edit Interview Date of an applicant: `edit INDEX a- id/`

 - Can edit interview date of an applicant
 
*Fields: Interview Date*
 
Format: `edit INDEX a- id/ NEW_DATE_OF_INTERVIEW`


### 3.8. Archiving data files `[coming in v2.0]`

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
| **Find** | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`                                                                 |
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
| **AddApplicant**      | `to be updated` <br> e.g., `addstaff n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` |
| **DeleteApplicant**   | `to be updated` <br> e.g., `addleave 2 l/d:08/10/2020 d:10/10/2020 l/d:20/10/2020`                               |
| **AddApplication**    | `addapplication INDEX [filepath]` <br> e.g., `addapplication 1 C:\Users\Public\Downloads\resume.txt`             |
| **DeleteApplication** | `deleteapplication INDEX` <br> e.g., `deleteapplication 1`                                                       |


