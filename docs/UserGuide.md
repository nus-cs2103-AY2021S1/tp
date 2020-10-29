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

   * **`list case`** : Lists all cases.

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

#### List all default cases: `list case`
Lists all default cases in PIVOT (unarchived cases).

Format: `list case`

#### List all archived cases: `list archive`
Lists all archived cases in PIVOT.

Format: `list archive`

#### Adding a case: `add case t:TITLE [s:STATUS]`
Adds a new case with the specified TITLE. The STATUS is active by default, if not specified. The user can provide 3 status types:

1. `ACTIVE`

2. `CLOSED`

3. `COLD`

The case will be added to the DEFAULT/ARCHIVED section, depending on which section they are currently in. 

Format: `add case t:TITLE [s:STATUS]`
* The title must be alphanumeric and cannot be blank.

Example: 
* `add case t:Kovan double murders` creates a new case with the title “Kovan double murders”, the status initialized as an active case.
* `add case t:Kovan double murders s:Closed` creates a new case with the title “Kovan double murders”, the status initialized as a closed case.

#### Delete a case: `delete case CASE_NO`
Deletes the specified case.

Format: `delete case CASE_NO`

Example: `list case` followed by `delete case 2` deletes the 2nd case in the list.

#### Open a case: `open case CASE_NO`
Enters the specified case where users can add more information regarding the case
(see Investigation Case Page).

Format:  `open case CASE_NO`

Example: `list case` followed by `open case 1` opens the 1st case in the list.

#### Archiving a case in the DEFAULT section: `archive case CASE_NO`
Archives the specified case in the DEFAULT section of PIVOT.

Format:  `archive case CASE_NO`

Example: `list case` followed by `archive case 1` archives the 1st case in the list.

#### Unarchiving a case in the ARCHIVED section: `unarchive case CASE_NO`
Unarchives the specified case in the ARCHIVED section of Pivot.

Format:  `unarchive case CASE_NO`

Example: `list archive` followed by `unarchive case 1` unarchives the 1st case in the list.

#### Finding a case: `find KEYWORD [MORE_KEYWORDS]`

Find cases whose details contain any of the given keywords.

* The search is case-insensitive. e.g keyword `hans` will match case containing `Hans` in its details
* The order of the keywords does not matter. e.g. keywords `Hans Bo` will match case containing `Bo Hans` in its details
* The search finds cases depending on which section they are in. If they are in the DEFAULT section, cases found are in DEFAULT section only.
 If they are in the ARCHIVED section, cases found are in the ARCHIVED section only. Note that on start-up, they are in the DEFAULT section,
 and can alternate between sections using [`list case`](#list-all-investigation-cases-list-case) or [`list archive`](#list-all-investigation-cases-list-archive)
* All details of all cases in the relevant section (ARCHIVED/DEFAULT) are searched, specifically: Title, Status, Description, 
Documents (file name and file reference that the users input on creation), Suspects/Witnesses/Victims (Name, Gender, Phone, Email, Address)
* Only full words will be matched e.g. keyword `Han` will not match cases containing `Hans` in their details
* Persons matching at least one keyword will be returned (i.e. `OR` search). e.g. keywords `Hans Bo` will return case 
containing `Hans Gruber`, `Bo Yang` in their details.

Format:  `find KEYWORD [MORE_KEYWORDS]`

Example:
* `find Ang` return cases `ang` and `Ang Mo Kio Car Theft`, and cases containing `Ang` in their details
* `find dhoby bishan` return cases `Dhoby Ghaut Murder Case` and `Bishan Shopping Theft`, and cases containing `dhoby` or `bishan` in their details
* `find 91234567 bishan` return cases with suspect, victim or witness containing Phone number `91234567`, and cases containing `bishan` in their details

#### Exit application: `exit`
Exits the application.

### Investigation Case page
The page of the application when the user opens a specified case.

#### Add description to the current case: `add desc d:DESC`
Adds the description of the case.

Format: `add desc d:DESC`

Example: `add desc d:Kovan double murders of twins xxx and yyy` updates the description of this case to “Kovan double murders of twins xxx and yyy”.

This command is flexible. If a description has been added, this command will overwrite the current description.
Tip: You can also use `add desc d:` to remove the current desc.

#### Add a document to the current case: `add doc n:TITLE r:FILE_NAME`
Adds a new document to the current case with the specified `TITLE` and `FILE_NAME`.

Format: `add doc n:TITLE r:FILE_NAME`

Example: `add doc n:Case Details r:case_details.pdf` adds a new document with title “Case Details” with the file name case_details.pdf to the investigation case.

This document must be manually added to the references folder provided before it can be added to the PIVOT system.

#### Add a Suspect to the current case: `add suspect n:NAME g:GENDER [p:PHONE] [e:EMAIL] [a:ADDRESS]`

Adds a new suspect to the current case with the specified `NAME` and `GENDER`. The other fields are optional.

Format: `add suspect n:NAME g:GENDER`

Example: `add suspect n:John Doe g:M`

Gender must either be `M` or `F`, not case-sensitive.

#### Add a victim to the current case: `add victim n:NAME g:GENDER [p:PHONE] [e:EMAIL] [a:ADDRESS]`

Adds a new victim to the current case with the specified `NAME` and `GENDER`. The other fields are optional.

Format: `add victim n:NAME g:GENDER`

Example: `add victim n:James Lee g:M`

#### Add a witness to the current case: `add witness n:NAME g:GENDER [p:PHONE] [e:EMAIL] [a:ADDRESS]`

Gender must either be `M` or `F`, not case-sensitive.


Adds a new witness to the current case with the specified `NAME` and `GENDER`. The other fields are optional.

Format: `add witness n:NAME g:GENDER`

Example: `add witness n:John Doe g:M`

Gender must either be `M` or `F`, not case-sensitive.

#### Opens a document in the current case: `open doc DOC_NO`

Opens the specified document at index `DOC_NO` in the list.

Format: `open doc DOC_NO`

Example: `open doc 1` opens the document in the list with index 1.

#### Edit title in the current case: `edit title t:TITLE`
Edits the title of the case with the specified `TITLE`.

Format: `edit title t:TITLE`

Example: `edit title t:Murder case 29` updates the title of this case to “Murder case 29”.

#### Edit status in the current case: `edit status s:STATUS`

Edits the status (ACTIVE, COLD, CLOSED) of the case with the specified `STATUS`.

Format: `edit status s:STATUS`

Example: `edit status s:CLOSED` updates the status of this case to “CLOSED”.


#### Edit an existing document in the current case: `edit doc DOC_NO [n:NAME] [r:REFERENCE]`

Edits the document of the current case at the specified `DOC_NO` of the list. There must be at least one field indicated.

Format: `edit doc DOC_NO [n:NAME] [r:REFERENCE]`

Example: `edit doc 2 n:Fire outbreak details r:newFireDoc.pdf` updates the second document of the current opened case with 
name `Fire outbreak details` and reference `newFireDoc.pdf`.

This document `newFireDoc.pdf` must be manually added to the references folder provided and must be present before the document can be successfully updated.

#### Edit an existing suspect in the current case: `edit suspect INDEX [n:NAME] [g:GENDER] [p:PHONE] [e:EMAIL] [a:ADDRESS]`

Edits the fields of the suspect specified with the index in the case.
At least one of the fields is to be specified to make edits.

Format: `edit suspect INDEX [n:NAME] [g:GENDER] [p:PHONE] [e:EMAIL] [a:ADDRESS]`

Example: `edit suspect 1 e:newEmail@mail.com a:New Road Crescent` edits the first suspect in the list with the email 
`newEmail@mail.com` and the address `New Road Crescent`.

Gender must either be `M` or `F`, not case-sensitive.

#### Edit an existing victim in the current case: `edit victim INDEX [n:NAME] [g:GENDER] [p:PHONE] [e:EMAIL] [a:ADDRESS]`

Edits the fields of the victim specified with the index in the case.
At least one of the fields is to be specified to make edits.

Format: `edit victim VICTIM_NO [n:NAME] [g:GENDER] [p:PHONE] [e:EMAIL] [a:ADDRESS]`

Example: `edit victim 1 e:newEmail@mail.com a:New Road Crescent` edits the first victim in the list with the email 
`newEmail@mail.com` and the address `New Road Crescent`.

Gender must either be `M` or `F`, not case-sensitive.


#### Edit an existing witness in the current case: `edit witness INDEX [n:NAME] [g:GENDER] [p:PHONE] [e:EMAIL] [a:ADDRESS]`

Edits the fields of the witness specified with the index in the case. 
At least one of the fields is to be specified to make edits.

Format: `edit witness INDEX [n:NAME] [g:GENDER] [p:PHONE] [e:EMAIL] [a:ADDRESS]`

Example: `edit witness 1 e:newEmail@mail.com a:New Road Crescent` edits the first witness in the list with the email 
`newEmail@mail.com` and the address `New Road Crescent`.

Gender must either be `M` or `F`, not case-sensitive.

#### Delete document: `delete doc DOC_NO `
Deletes the document specified with `DOC_NO` from the list of documents.

Format: `delete doc DOC_NO`

Example: `delete doc 0`

#### Delete suspect: `delete suspect SUSPECT_NO`

Deletes the suspect specified with `SUSPECT_NO` from the list of suspects.

Format: `delete suspect SUSPECT_NO`

Example: `delete suspect 0`

#### Delete Victim: `delete victim VICTIM_NO`

Deletes the victim specified with `VICTIM_NO` from the list of victims.

Format: `delete victim VICTIM_NO`

Example: `delete victim 0`


#### Delete witness: `delete witness WITNESS_NO`

Deletes the witness specified with `WITNESS_NO` from the list of witnesses.

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

User data automatically loads when user opens the app.

#### Saving of User Data

User data automatically saves when there is a change in data.

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous AddressBook home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

#### Main Page Commands

|Command            | Format                                |
| ----------------- | ------------------------------------- |
|**list case**      | `list case`                           |
|**list archive**   | `list archive`                        |
|**add case**       | `add case t:TITLE [s:STATUS]`         |
|**open case**      | `open case CASE_NO`                   |
|**delete case**    | `delete case CASE_NO`                 |
|**archive**        | `archive case CASE_NO`                |
|**unarchive**      | `unarchive case CASE_NO`              |
|**find**           | `find KEYWORD [MORE KEYWORDS]`        |
|**exit**           | `exit`                                |

#### Investigation Page Commands

| Command             | Format                                                                            |
| ------------------- | ----------------------------------------------------------------------------------|
|**list document**    | `list doc`                                                                        |
|**list suspect**     | `list suspect`                                                                    |
|**list victim**      | `list victim`                                                                     |
|**list witness**     | `list witness`                                                                    |
|**add description**  | `add desc d:DESC`                                                                 |
|**add document**     | `add doc n:TITLE r:FILE_NAME`                                                     |
|**add suspect**      | `add suspect n:NAME g:GENDER [p:PHONE] [e:EMAIL] [a:ADDRESS]`                     |
|**add victim**       | `add victim n:NAME g:GENDER [p:PHONE] [e:EMAIL] [a:ADDRESS]`                      |
|**add witness**      | `add witness n:NAME g:GENDER [p:PHONE] [e:EMAIL] [a:ADDRESS]`                     |
|**open doc**         | `open doc DOC_NO`                                                                 |
|**edit title**       | `edit title t:TITLE`                                                              |
|**edit status**      | `edit status s:STATUS`                                                            |
|**edit document**    | `edit doc DOC_NO [n:TITLE] [r:FILE_NAME]`                                         |
|**edit suspect**     | `edit suspect SUSPECT_NO [n:NAME] [g:GENDER] [p:PHONE] [e:EMAIL] [a:ADDRESS]`     |
|**edit victim**      | `edit victim VICTIM_NO [n:NAME] [g:GENDER] [p:PHONE] [e:EMAIL] [a:ADDRESS]`       |
|**edit witness**     | `edit witness WITNESS_NO [n:NAME] [g:GENDER] [p:PHONE] [e:EMAIL] [a:ADDRESS]`     |
|**delete doc**       | `delete doc DOC_NO`                                                               |
|**delete suspect**   | `delete suspect SUSPECT_NO`                                                       |
|**delete victim**    | `delete victim VICTIM_NO`                                                         |
|**delete witness**   | `delete witness WITNESS_NO`                                                       |
|**return**           | `return`                                                                          |
|**exit**             | `exit`                                                                            |

#### Both Pages

|Command       | Format                        |
| -------------| ------------------------------|
|**undo**      | `undo`                        |
|**redo**      | `redo`                        |
|**exit**      | `exit`                        |
