---
layout: page
title: User Guide
---

Police Investigation Virtual Organisational Tool (PIVOT) is a **desktop app to assist the police investigators in keeping track of their investigations and relevant information. It is optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI).
If you can type fast, PIVOT can manage your investigation cases faster than traditional GUI apps.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start [To be updated]

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest release from [here](https://github.com/AY2021S1-CS2103-F09-2/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for PIVOT.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`list case`** : Lists all investigation cases.

   * **`add case`**`t:Kovan double murders` : Adds a case named `Kovan double murders` to PIVOT.

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

#### Adding an active investigation case: `add case t:TITLE [s:STATUS] [t/TAG]`
Adds a new investigation case which has the active status by default. The user can provide 3 status types:

1. `ACTIVE`

2. `CLOSED`

3. `COLD`

User can tag the case they add. Tags cannot contain whitespaces.

Format: `add case t:[TITLE] [s:STATUS] [t/TAG]`

Example: `add case t:Kovan double murders s:Closed t/SerialKiller` creates a new investigation case with the title “Kovan double murders”, the status initalized as a closed case, tagged as SerialKiller.

#### List all investigation cases: `list case`
Lists all available investigation cases in PIVOT.

Format: `list case`

#### Delete an investigation case: `delete case CASE_NO`
Deletes the specified investigation case.

Format: `delete case CASE_NO`

Example: `list case` followed by `delete case 2` deletes the 2nd case in the investigation list.

#### Open an investigation case: `open case CASE_NO`
Enters the specified investigation case where users can add more information regarding the case
(see Investigation Case Page).

Format:  `open case CASE_NO`

Example: `list case` followed by `open case 1` opens the 1st case in the investigation list.

### Investigation Case page
The page of the application when the user opens a specified investigation case.

#### Add investigation case description: `add desc d:DESC`
Adds the description of the investigation

Format: `add desc d:DESC`

Example: `add desc d:Kovan double murders of twins xxx and yyy` updates the description of this investigation case to “Kovan double murders of twins xxx and yyy”.

#### Edit investigation case tag: `tag STATUS`
Edits the tag of the investigation (tags: ACTIVE, COLD, CLOSED)

Format: `tag STATUS`

Example: `tag CLOSED` updates the tag status of this investigation case to “CLOSED”.

#### Adding a document related to the case: `add doc n:TITLE r:FILE_NAME`
Adds a new document that is related to the investigation case.

Format: `add doc n:TITLE r:FILE_NAME`

Example: `add doc n:Case Details r:case_details.pdf` adds a new document with title “Case Details” with the file name case_details.pdf to the investigation case.

This document must be manually added to the references folder provided before it can be added to the PIVOT system.

#### List all documents related to the case: `list doc`

Lists all added documents that are related to the investigation case.

Format: `list doc`

#### Delete document: `delete doc DOC_NO `
Deletes the specified document reference.

Format: `delete doc DOC_NO`

Example: `delete doc 0`

#### Open document: `open doc DOC_NO`

Opens the specified document reference.

Format: `open doc DOC_NO`

Example: `open doc 0`

#### Adding a suspect related to the case: `add suspect n:NAME`

Adds a new suspect related to the investigation case.

Format: `add suspect n:NAME`

Example: `add suspect n:John Doe`

#### Edit an existing suspect in the case: `edit suspect INDEX [n:NAME] [g:GENDER] [p:PHONE] [e:EMAIL] [a:ADDRESS]`

Edits the fields of the suspect specified with the index in the investigation case.
At least one of the fields must be specified to be edited.

Format: `edit suspect INDEX [n:NAME] [g:GENDER] [p:PHONE] [e:EMAIL] [a:ADDRESS]`

Example: `edit suspect 1 e:newEmail@mail.com a:New Road Crescent` edits the first suspect in the list with the email 
"newEmail@mail.com" and the address "New Road Crescent".

#### List all suspects related to the case: `list suspect`

Lists all added suspects that are related to the investigation case.

Format: `list suspect`

#### Delete suspect: `delete suspect SUSPECT_NO`

Deletes the specified suspect from the list of suspects.

Format: `delete suspect SUSPECT_NO`

Example: `delete suspect 0`

#### Adding a victim related to the case: `add victim n:NAME`

Adds a new victim that is related to the investigation case.

Format: `add victim n:NAME`

Example: `add victim n:James Lee`

#### Edit an existing victim in the case: `edit victim INDEX [n:NAME] [g:GENDER] [p:PHONE] [e:EMAIL] [a:ADDRESS]`

Edits the fields of the victim specified with the index in the investigation case.
At least one of the fields must be specified to be edited.

Format: `edit victim INDEX [n:NAME] [g:GENDER] [p:PHONE] [e:EMAIL] [a:ADDRESS]`

Example: `edit victim 1 e:newEmail@mail.com a:New Road Crescent` edits the first victim in the list with the email 
"newEmail@mail.com" and the address "New Road Crescent".

#### List all victims related to the case: `list victim`

Lists all added victims that are related to the investigation case.

Format: `list victim`

#### Delete victim: `delete victim VICTIM_NO`

Deletes the specified victim from the list of victims.

Format: `delete victim VICTIM_NO`

Example: `delete victim 0`

#### Add a witness related to the case: `add witness n:NAME`

Adds a new witness that is related to the investigation case.

Format: `add witness n:NAME`

Example: `add witness n:John Doe`

#### Edit an existing witness in the case: `edit witness INDEX [n:NAME] [g:GENDER] [p:PHONE] [e:EMAIL] [a:ADDRESS]`

Edits the fields of the witness specified with the index in the investigation case. 
At least one of the fields must be specified to be edited.

Format: `edit witness INDEX [n:NAME] [g:GENDER] [p:PHONE] [e:EMAIL] [a:ADDRESS]`

Example: `edit witness 1 e:newEmail@mail.com a:New Road Crescent` edits the first witness in the list with the email 
"newEmail@mail.com" and the address "New Road Crescent".

#### List all witness related to the case: `list witness`

Lists all added witnesses that are related to the investigation case.

Format: `list witness`

#### Delete suspect: `delete witness WITNESS_NO`

Deletes the specified witness from the list of witnesses.

Format: `delete witness WITNESS_NO`

Example: `delete witness 0`

#### Return to main page: `return`

Returns to the application main page.

### Both pages

#### Undo: `undo'

Undoes the previous command.

Format: `undo'

#### Redo: `redo'

Redoes the command that was just undone. If another command that changes the data of PIVOT is used after an undo 
command, redo will not be able to be called.

Format: `redo'

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
|**case**           | `add case t:TITLE [s:STATUS] [t/TAG]` | Investigation |
|**list**           | `list case`                           | Investigation |
|**delete**         | `delete case CASE_NO`                 | Investigation |
|**open**           | `open case CASE_NO`                   | Investigation |
|**exit**           | `exit`                                | General       |

#### Investigation Page Commands

| Command           | Format                        | Association   |
| ----------------- | ----------------------------- | ------------- |
|**desc**           | `add desc d:DESC`             | Investigation |
|**tag**            | `tag STATUS`                  | Investigation |
|**document**       | `add doc t:TITLE r:FILE_NAME` | Document      |
|**list doc**       | `list doc`                    | Document      |
|**delete doc**     | `delete doc DOC_NO`           | Document      |
|**open doc**       | `open doc DOC_NO`             | Document      |
|**suspect**        | `add suspect n:NAME`          | Suspect       |
|**list suspect**   | `list suspect`                | Suspect       |
|**delete suspect** | `delete suspect SUSPECT_NO`   | Suspect       |
|**victim**         | `add victim n:NAME`           | Victim        |
|**list victim**    | `list victim`                 | Victim        |
|**delete victim**  | `delete victim VICTIM_NO`     | Victim        |
|**witness**        | `add witness n:NAME`          | Witness       |
|**list witness**   | `list witness`                | Witness       |
|**delete witness** | `delete witness WITNESS_NO`   | Witness       |
|**return**         | `return`                      | General       |
|**exit**           | `exit`                        | General       |
