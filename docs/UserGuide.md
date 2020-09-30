---
layout: page
title: User Guide
---

FaculType is a **desktop app** for managing **faculty members and their modules**, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI).

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

### Adding a contact: `add`

Adds a contact to FaculType.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL d/DEPARTMENT b/BUILDING [t/TAG]…​
​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A contact can have any number of tags (including 0)
</div>

Examples:
* `add n/John Doe p/98765432 e/johnd@example.com d/Computer Science b/B01`
* `add n/Betsy Crowe p/98765431 e/betsycrowe@example.com d/Data Science t/senior lecturer t/friend`

### Listing all contacts : `list`

Shows a list of all contacts in FaculType.

Format: `list`

### Editing a contact : `edit`

Edits an existing contact in FaculType.

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [d/DEPARTMENT] [b/BUILDING] [t/TAG]…​`

* Edits the contact at the specified `INDEX`. The index refers to the index number shown in the displayed contact list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the contact will be removed i.e adding of tags is not cumulative.
* You can remove all the contact’s tags by typing `t/` without
    specifying any tags after it.

Examples:
*  `edit 1 d/Computing b/COM2` edits the department and building of the 1st contact to be `Computing` and `COM2` respectively.
*  `edit 2 n/Betsy Crower t/` edits the name of the 2nd contact to be `Betsy Crower` and clears all existing tags.

### Adding or updating a remark : `remark`

Adds or updates the remark of an existing contact in FaculType.

Format: `remark INDEX [r/REMARK]`

* Adds a remark to the contact at the specified `INDEX`. The index refers to the index number shown in the displayed contact list. The index **must be a positive integer** 1, 2, 3, …​
* You can remove the contact’s remark by typing `r/` without specifying any remark after it.
    
Examples:
*  `remark 1 r/Wears red glasses` adds the remark “Wears red glasses” to the 1st contact in the list.
*  `remark 2 r/` erases the remark of the 2nd contact in the list.

### Locating contacts by name: `find`

Finds contacts whose names contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`
* Contacts matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:
* `find John` returns `john` and `John Doe`
* `find alex david` returns `Alex Yeoh`, `David Li`<br>
  ![result for 'find alex david'](images/findAlexDavidResult.png)

### Deleting a contact : `delete`

Deletes the specified contact from FaculType.

Format: `delete INDEX`

* Deletes the contact at the specified `INDEX`.
* The index refers to the index number shown in the displayed contact list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd contact in FaculType.
* `find Betsy` followed by `delete 1` deletes the 1st contact in the results of the `find` command.

### Adding a module: `addmod`

Adds a new module to FaculType.

Format : `addmod m/MODULE_CODE n/MODULE_NAME`

* `MODULE_CODE` must be unique.

Examples:
* `addmod m/CS2103 n/Software Engineering` adds a module `Software Engineering` with code `CS2103` to FaculType.
* `addmod m/CS2102 n/Database System` adds a module `Database System` with code `CS2102` to FaculType.

### Deleting a module: `delmod`

Deletes a module from FaculType.

Format: `delmod m/MODULE_CODE`

* Deletes the `MODULE_CODE` specified from FaculType. The `MODULE_CODE` **must exist** in FaculType in the first place.

Examples:
* `delmod m/CS2103` deletes the existing module with code `CS2103` from FaculType.
* `delmod m/CS2102` deletes the existing module with code `CS2102` from FaculType.

### Clearing all entries : `clear`

Clears all entries from the FaculType.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

FaculType data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous FaculType home folder.

