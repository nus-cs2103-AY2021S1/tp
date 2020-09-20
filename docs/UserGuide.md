---
layout: page
title: User Guide
---

Modduke is a **desktop app for managing contacts, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, Modduke can get your module management tasks done faster than traditional GUI apps.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `modduke.jar` from [here](https://github.com/se-edu/addressbook-level3/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your AddressBook.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`contact list`** : Lists all contacts.

   * **`contact add`**`n/John Doe p/98765432 e/johnd@example.com` : Adds a contact named `John Doe` to the Address Book.

   * **`contact delete John Doe`** : Deletes `John Doe` from the contact list.

   * **`contact clear`** : Deletes all contacts.

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



### Adding a contact: `contact add`

Adds a contact to Modduke.

Format: `contact add n/NAME p/PHONE_NUMBER e/EMAIL`

Note: All fields are required. No duplicate names.

Example:
* `contact add n/John Doe p/98765432 e/johnd@example.com`

### Deleting a contact : `contact delete`

Deletes the specified contact from Modduke.

Format: `contact delete CONTACT_NAME`

* Deletes the contact with the specified name.

Examples:
* `contact delete Roy` deletes `Roy` contact from Modduke.

### Editing a contact : `contact edit`

Edits an existing contact in Modduke.

Format: `contact edit CONTACT_NAME [n/NEW_NAME] [p/PHONE] [e/EMAIL] [m/MODULE]`

Note: At least one optional field must be provided

Examples:
*  `contact edit John Doe p/91234567 e/johndoe@example.com` Edits the phone number and email address of John Doe to be `91234567` and `johndoe@example.com` respectively.
*  `contact edit Roy Chan n/Betsy Crower` Edits the name of Roy Chan to be `Betsy Crower` and clears all existing tags.

### Viewing all contacts : `contact list`

Shows a list of all persons in the address book.

Format: `contact list`

### Clearing all entries : `clear`

Clears all entries from the address book.

Format: `clear`

### Add a tag to a user: `label add`

Adds a label to a contact

Format: ` label add [c/CONTACT_NAME][t/TAG_NAMES]`

* Only 1 contact name can be used at a time but multiple tags can be added.
* Tag names are to be separated by a ",".

Examples:
* `label add c/Jay t/2103, teamproject`

### Schedule a meeting : `meeting add`

Schedules a meeting at a given date and time with specified members and a provided meeting name

Format: `meeting add [n/MEETING_NAME] [d/MEETING_DATE]  [t/MEETING_TIME]  [m/MEMBERS]`

* Creates a meeting with the provided meeting name
* All the fields must be provided
* Date is in the YYYY-MM-dd format and time is in the HH:mm format
* There can be multiple members separated by a ",".

Examples:
*  `meeting add n/CS2103 weekly meeting d/2020-09-20 t/10:00 m/Jay, Roy, Jerryl, Yeeloon, Ekam`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

AddressBook data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Archiving data files `[coming in v2.0]`

_{explain the feature here}_

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous AddressBook home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add Contact** | `contact add [n/NAME] [p/PHONE_NUMBER] [e/EMAIL]` <br> e.g., `contact add n/Jay p/22224444 e/jay@example.com`
**Clear Contacts** | `contact clear`
**Delete Contact** | `contact delete CONTACT_NAME`<br> e.g., `delete Jay`
**Edit Contact** | `contact edit CONTACT_NAME [n/NEW_NAME] [p/PHONE] [e/EMAIL]` <br> e.g.,`contact edit Jay n/Roy e/roy@example.com`
**List Contacts** | `contact list`
**Add Module** | `module add [n/MODULE_NAME] [m/MEMBER_NAMES]`<br> e.g., `module add n/CS2103 m/Jay, Roy`
**List Modules** | `module list  [n/MODULE_NAME]`<br> e.g., `module list n/CS2103`
