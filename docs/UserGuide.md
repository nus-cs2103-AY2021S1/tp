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

### Viewing help : `help`

Shows a message explaining how to access the help page.

Format: `help`

### Adding a contact: `add`

Adds a contact to FaculType.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL d/DEPARTMENT o/OFFICE [t/TAG]…​
​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A contact can have any number of tags (including 0)
</div>

Examples:
* `add n/John Doe p/98765432 e/johnd@example.com d/Computer Science o/B01-A3`
* `add n/Betsy Crowe p/98765431 e/betsycrowe@example.com d/Data Science o/COM1-02-03 t/lecturer t/friend`

### Listing all contacts : `clist`

Shows a list of all contacts in FaculType.

Format: `clist`

### Listing all modules : `mlist`

Shows a list of all modules in FaculType.

Format: `mlist`

### Editing a contact : `edit`

Edits an existing contact in FaculType.

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [d/DEPARTMENT] [o/OFFICE] [t/TAG]…​`

* Edits the contact at the specified `INDEX`. The index refers to the index number shown in the displayed contact list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the contact will be removed i.e adding of tags is not cumulative.
* You can remove all the contact’s tags by typing `t/` without
    specifying any tags after it.

Examples:
*  `edit 1 d/Computing o/COM2-01-02` edits the department and office of the 1st contact to be `Computing` and `COM2-01-02` respectively.
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

### Finding modules : `findmod`

Finds all modules whose codes or names contain the given keywords.

Format : `findmod KEYWORD [MORE_KEYWORDS]`

* The search is insensitive. e.g. `cs2103` will match `CS2103` in FaculType.
* Partial words will be matched. e.g. `database` will match `Database Systems` in FaculType.
* Modules matching at least one keyword will be returned (i.e. “OR” search)
  e.g. `CS210 algorithms` will return `CS2103`, `CS2100`, `Data Structures and Algorithms`.
* The order of the keyword does not matter. e.g. `Statistics and Probability` will match  `Probability and Statistics`.

Examples :

* `findmod programming` returns `Programming Methodology I`, `Programming Methodology II`
* `findmod CS11 security` returns `CS1101S`, `Computer Security`
* `findmod cs210` returns `CS2100`, `CS2102`, `CS2103`

### Assigning an instructor to modules : `assign`

Assigns an instructor to one or more modules.

Format: `assign INDEX m/MODULE_CODE [MORE MODULE_CODES]`
* Assigns the instructor at the specified `INDEX` to every `MODULE_CODE` specified. All of the `MODULE_CODE` **must exist** in FaculType in the first place.

Examples :
* `assign 1 m/CS3233` Assigns the existing module with code `CS3233` to contact at index 1
* `assign 2 m/CS2030S` Assigns the existing module with code `CS2030S` to contact at index 2
* `assign 3 m/CS2100 m/CS2106` Assigns the existing modules with code `CS2100` and `CS2106` to contact at index 3


### Reseting FaculType : `reset`

Resets FaculType to its initial state by clearing all entries of persons and modules.

Format: `reset`

### Clearing all contacts : `cclear`

Clears all entries of contacts in FaculType.

Format : `cclear`

### Clearing all modules : `mclear`

Clears all entries of modules in FaculType.

Format : `mclear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

FaculType data is saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous FaculType home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add** | `add n/NAME p/PHONE_NUMBER e/EMAIL d/DEPARTMENT o/OFFICE [t/TAG]…​` <br> e.g. `add n/Betsy Crowe p/98765431 e/betsycrowe@example.com d/Data Science t/senior lecturer t/friend`
**Reset** | `reset`
**Delete** | `delete INDEX`<br> e.g. `delete 3`
**Edit** | `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [d/DEPARTMENT] [o/OFFICE] [t/TAG]…​`<br> e.g. `edit 1 d/Computing b/COM2`
**Remark** | `remark INDEX [r/REMARK]`<br> e.g. `remark 1 r/Wears red glasses`
**Find** | `find KEYWORD [MORE_KEYWORDS]`<br> e.g. `find James Jake`
**Add modules** | `addmod m/MODULE_CODE n/MODULE_NAME`<br> e.g. `addmod m/CS2103 n/Software Engineering`
**Delete modules** | `delmod m/MODULE_CODE`<br> e.g. `delmod m/CS2103`
**Find modules** | `findmod KEYWORD [MORE_KEYWORDS]` <br> e.g. `findmod CS2103`
**List all contacts and modules** | `list`
**List all contacts** | `clist`
**List all modules** | `mlist`
**Assign** | `assign INDEX m/MODULE_CODE [MORE MODULE_CODES]` <br> e.g. `assign 3 m/CS2100 m/CS2106`
**Help** | `help`
**Exit** | `exit`
