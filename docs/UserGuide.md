---
layout: page
title: User Guide
---

Police Investigation Virtual Organisational Tool (PIVOT) is a **desktop app to assist the police investigators in keeping track of their investigations and relevant information. It is optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI).
If you can type fast, PIVOT can manage your investigation cases faster than traditional GUI apps.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest release from [here](https://github.com/AY2021S1-CS2103-F09-2/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for PIVOT.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`list case`** : Lists all investigation cases.

   * **`add case`**` t:Kovan double murders` : Adds a case named `Kovan double murders` to PIVOT.
   
   * **`open case`**`1` : Opens the 1st case shown in the current list in the right panel with more details.
   
   * **`add victim`**` n:Joseph g:M` : Adds a victim in the current opened case.
   
   * **`return`** : Returns to the main page and closes the details on the right panel.

   * **`delete`**`case 3` : Deletes the 3rd case shown in the current list.

   * **`exit`** : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are parameters supplied by the user. Words in `[SQUARE_BRACKETS]`(with square brackets) optional parameters to be supplied by the user.<br>
  e.g. in `add case t:TITLE`, `TITLE` is a parameter which can be used as `add case t:Kovan double murders`.
</div>

### Main page
The main page of the application when the user first enters the app.

#### List all default investigation cases: `list case`
Lists all default investigation cases in PIVOT (unarchived cases).

Format: `list case`

#### List all archived investigation cases: `list archive`
Lists all archived investigation cases in PIVOT.

Format: `list archive`

#### Adding an investigation case: `add case t:TITLE [s:STATUS]`
Adds a new investigation case which has the active status by default, if not specified. The user can provide 3 status types:

1. `ACTIVE`

2. `CLOSED`

3. `COLD`

The case will be added to the DEFAULT/ARCHIVED section, depending on which section they are currently in. 

Format: `add case t:TITLE [s:STATUS]`
* The title must be alphanumeric and cannot be blank

Example: 
* `add case t:Kovan double murders` creates a new investigation case with the title “Kovan double murders”, the status initialized as an active case.
* `add case t:Kovan double murders s:Closed` creates a new investigation case with the title “Kovan double murders”, the status initialized as a closed case.

#### Delete an investigation case: `delete case CASE_NO`
Deletes the specified investigation case.

Format: `delete case CASE_NO`

Example: `list case` followed by `delete case 2` deletes the 2nd case in the investigation list.

#### Open an investigation case: `open case CASE_NO`
Enters the specified investigation case where users can add more information regarding the case
(see Investigation Case Page).

Format:  `open case CASE_NO`

Example: `list case` followed by `open case 1` opens the 1st case in the investigation list.

#### Archiving an investigation case in the DEFAULT section: `archive case CASE_NO`
Archives the specified investigation case in the DEFAULT section of Pivot.

Format:  `archive case CASE_NO`

Example: `list case` followed by `archive case 1` archives the 1st case in the investigation list.

#### Unarchiving an investigation case in the ARCHIVED section: `unarchive case CASE_NO`
Unarchives the specified investigation case in the ARCHIVED section of Pivot.

Format:  `unarchive case CASE_NO`

Example: `list archive` followed by `unarchive case 1` unarchives the 1st case in the investigation list.

#### Finding an investigation case: `find KEYWORD [MORE_KEYWORDS]`

Finds investigation cases whose details contains any of the given keywords.

* The search is case-insensitive. e.g keyword `hans` will match case containing `Hans` in its details
* The order of the keywords does not matter. e.g. keywords `Hans Bo` will match case containing `Bo Hans` in its details
* The search finds cases depending on which section they are in. If they are in the DEFAULT section, cases found are in DEFAULT section only.
 If they are in the ARCHIVED section, cases found are in the ARCHIVED section only. Note that on start-up, they are in the DEFAULT section,
 and can alternate between sections using [`list case`](#list-all-investigation-cases-list-case) or [`list archive`](#list-all-investigation-cases-list-archive)
* All details of all cases in the relevant section (ARCHIVED/DEFAULT) are searched, specifically: Title, Status, Description, 
Documents (file name and file reference that the users input on creation), Suspects/Witnesses/Victims (Name, Gender, Phone, Email, Address)
* Only full words will be matched e.g. keyword `Han` will not match cases containing `Hans` in their details
* Persons matching at least one keyword will be returned (i.e. `OR` search). e.g. keywords `Hans Bo` will return case 
containing `Hans Gruber`, `Bo Yang` in their details

Format:  `find KEYWORD [MORE_KEYWORDS]`

Example:
* `find Ang` return cases `ang` and `Ang Mo Kio Car Theft`, and cases containing `Ang` in their details
* `find dhoby bishan` return cases `Dhoby Ghaut Murder Case` and `Bishan Shopping Theft`, and cases containing `dhoby` or `bishan` in their details
* `find 91234567 bishan` return cases with suspect, victim or witness containing Phone number `91234567`, and cases containing `bishan` in their details

#### Exit application: `exit`
Exits the application.

### Investigation Case page
The page of the application when the user opens a specified investigation case.

#### Add investigation case description: `add desc d:DESC`
Adds the description of the investigation

Format: `add desc d:DESC`

Example: `add desc d:Kovan double murders of twins xxx and yyy` updates the description of this investigation case to “Kovan double murders of twins xxx and yyy”.

This command is flexible. If a description has been added, this command will overwrite the current description.
Tip: You can also use `add desc d:` to remove the current desc.

#### Adding a document related to the case: `add doc n:TITLE r:FILE_NAME`
Adds a new document that is related to the investigation case.

Format: `add doc n:TITLE r:FILE_NAME`

Example: `add doc n:Case Details r:case_details.pdf` adds a new document with title “Case Details” with the file name case_details.pdf to the investigation case.

This document must be manually added to the references folder provided before it can be added to the PIVOT system.

#### Adding a Suspect related to the case: `add suspect n:NAME g:GENDER [p:PHONE] [e:EMAIL] [a:ADDRESS]`

Adds a new suspect related to the investigation case.

Format: `add suspect n:NAME g:GENDER`

Example: `add suspect n:John Doe g:M`

Gender must either be `M` or `F`, not case-sensitive.

#### Adding a victim related to the case: `add victim n:NAME g:GENDER [p:PHONE] [e:EMAIL] [a:ADDRESS]`

Adds a new victim that is related to the investigation case.

Format: `add victim n:NAME g:GENDER`

Example: `add victim n:James Lee g:M`

Gender must either be `M` or `F`, not case-sensitive.

#### Add a witness related to the case: `add witness n:NAME g:GENDER [p:PHONE] [e:EMAIL] [a:ADDRESS]`

Adds a new witness that is related to the investigation case.

Format: `add witness n:NAME g:GENDER`

Example: `add witness n:John Doe g:M`

Gender must either be `M` or `F`, not case-sensitive.

#### Open document: `open doc DOC_NO`

Opens the specified document reference.

Format: `open doc DOC_NO`

Example: `open doc 1` opens the document in the list with index 1.

#### Edit investigation case title: `edit title t:TITLE`
Edits the tag of the investigation (tags: ACTIVE, COLD, CLOSED)

Format: `edit title t:TITLE`

#### Edit investigation case status: `edit status s:STATUS`
Edits the tag of the investigation (tags: ACTIVE, COLD, CLOSED)

Format: `edit status s:STATUS`

Example: `edit status s:CLOSED` updates the status of this investigation case to “CLOSED”.


#### Edit investigation case document: `edit doc DOC_NO [n:NAME] [r:REFERENCE]`
Edits the document of the current investigation case at the specified index of the list

Format: `edit doc DOC_NO [n:NAME] [r:REFERENCE]`

Example: `edit doc 2 n:Fire outbreak details r:newFireDoc.pdf` updates the second document of the current opened case with 
name "Fire outbreak details" and reference "newFireDoc.pdf".

This document "newFireDoc.pdf" must be manually added to the references folder provided and must be present before the document can be successfully updated.

#### Edit an existing suspect in the case: `edit suspect INDEX [n:NAME] [g:GENDER] [p:PHONE] [e:EMAIL] [a:ADDRESS]`

Edits the fields of the suspect specified with the index in the investigation case.
At least one of the fields must be specified to be edited.

Format: `edit suspect INDEX [n:NAME] [g:GENDER] [p:PHONE] [e:EMAIL] [a:ADDRESS]`

Example: `edit suspect 1 e:newEmail@mail.com a:New Road Crescent` edits the first suspect in the list with the email 
"newEmail@mail.com" and the address "New Road Crescent".

Gender must either be `M` or `F`, not case-sensitive.

#### Edit an existing victim in the case: `edit victim VICTIM_NO [n:NAME] [g:GENDER] [p:PHONE] [e:EMAIL] [a:ADDRESS]`

Edits the fields of the victim specified with the index in the investigation case.
At least one of the fields must be specified to be edited.

Format: `edit victim VICTIM_NO [n:NAME] [g:GENDER] [p:PHONE] [e:EMAIL] [a:ADDRESS]`

Example: `edit victim 1 e:newEmail@mail.com a:New Road Crescent` edits the first victim in the list with the email 
"newEmail@mail.com" and the address "New Road Crescent".

Gender must either be `M` or `F`, not case-sensitive.


#### Edit an existing witness in the case: `edit witness INDEX [n:NAME] [g:GENDER] [p:PHONE] [e:EMAIL] [a:ADDRESS]`

Edits the fields of the witness specified with the index in the investigation case. 
At least one of the fields is to be specified to make edits.

Format: `edit witness INDEX [n:NAME] [g:GENDER] [p:PHONE] [e:EMAIL] [a:ADDRESS]`

Example: `edit witness 1 e:newEmail@mail.com a:New Road Crescent` edits the first witness in the list with the email 
"newEmail@mail.com" and the address "New Road Crescent".

Gender must either be `M` or `F`, not case-sensitive.

#### Delete document: `delete doc DOC_NO `
Deletes the specified document reference.

Format: `delete doc DOC_NO`

Example: `delete doc 0`

#### Delete suspect: `delete suspect SUSPECT_NO`

Deletes the specified suspect from the list of suspects.

Format: `delete suspect SUSPECT_NO`

Example: `delete suspect 0`

#### Delete Victim: `delete victim VICTIM_NO`

Deletes the specified victim from the list of victims.

Format: `delete victim VICTIM_NO`

Example: `delete victim 0`


#### Delete witness: `delete witness WITNESS_NO`

Deletes the specified witness from the list of witnesses.

Format: `delete witness WITNESS_NO`

Example: `delete witness 0`

#### Return to main page: `return`

Returns to the application main page.

### Both pages

#### Undo: `undo`

Undoes the previous command.

Format: `undo`

#### Redo: `redo`

Redoes the command that was just undone. If another command that changes the data of PIVOT is used after an undo 
command, redo will not be able to be called.

Format: `redo`

#### Exit application: `exit`

Exits the application.

### Data Management

#### Loading of User Data

User data automatically loads when user opens the app

#### Saving of User Data

User data automatically saves when there is a change in data

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous AddressBook home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

#### Main Page Commands

|Command            | Format                                | Association   |
| ----------------- | ------------------------------------- | ------------- |
|**case**           | `add case t:TITLE [s:STATUS]` | Investigation |
|**list**           | `list case`                           | Investigation |
|**delete**         | `delete case CASE_NO`                 | Investigation |
|**open**           | `open case CASE_NO`                   | Investigation |
|**exit**           | `exit`                                | General       |

#### Investigation Page Commands

| Command           | Format                        | Association   |
| ----------------- | ----------------------------- | ------------- |
|**desc**           | `add desc d:DESC`             | Investigation |
|**document**       | `add doc t:TITLE r:FILE_NAME` | Document      |
|**delete doc**     | `delete doc DOC_NO`           | Document      |
|**open doc**       | `open doc DOC_NO`             | Document      |
|**suspect**        | `add suspect n:NAME`          | Suspect       |
|**delete suspect** | `delete suspect SUSPECT_NO`   | Suspect       |
|**victim**         | `add victim n:NAME`           | Victim        |
|**delete victim**  | `delete victim VICTIM_NO`     | Victim        |
|**witness**        | `add witness n:NAME`          | Witness       |
|**delete witness** | `delete witness WITNESS_NO`   | Witness       |
|**return**         | `return`                      | General       |
|**exit**           | `exit`                        | General       |
