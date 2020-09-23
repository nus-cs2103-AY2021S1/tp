---
layout: page
title: User Guide
---

AddressBook Level 3 (AB3) is a **desktop app for managing contacts, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, AB3 can get your contact management tasks done faster than traditional GUI apps.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `addressbook.jar` from [here](https://github.com/se-edu/addressbook-level3/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your AddressBook.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`list`** : Lists all contacts.

   * **`add`**`n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` : Adds a contact named `John Doe` to the Address Book.

   * **`delete`**`3` : Deletes the 3rd contact shown in the current list.

   * **`clear`** : Deletes all contacts.

   * **`exit`** : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

### Investigation page
The page of the application when the user opens a specified investigation case.

#### Add investigation case description: `desc [DESC]`
Adds the description of the investigation 

Format: `desc [DESC]`

Example: `desc Kovan double murders of twins xxx and yyy` updates the description of this investigation case to “Kovan double murders of twins xxx and yyy”.

#### Edit investigation case tag: `tag [STATUS]`
Edits the tag of the investigation (tags: ACTIVE, COLD, CLOSED)

Format: `tag [STATUS]`

Example: `tag CLOSED` updates the tag status of this investigation case to “CLOSED”.

#### Adding a document related to the case: `doc [TITLE]`
Adds a new document that is related to the investigation case.

Format: `doc [TITLE]`

Example: `doc Case Details` adds a new document with title “Case Details” to the investigation case.

#### List all documents related to the case: `list doc`

Lists all added documents that are related to the investigation case.

Format: `list doc`

#### Delete document: `delete doc [DOC_NO] `
Deletes the specified document reference.

Format: `delete doc [DOC_NO]`

Example: `delete doc 0`

#### Open document: `open doc [DOC_NO]`

Opens the specified document reference.

Format: `open doc [DOC_NO]`

Example: `open doc 0`

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous AddressBook home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add** | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​` <br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 t/friend t/colleague`
**Clear** | `clear`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Edit** | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`
**Find** | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`
**List** | `list`
**Help** | `help`
