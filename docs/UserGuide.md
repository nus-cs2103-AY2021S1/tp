# User Guide

Common Cents is your convenient at-hand expense-tracking tool, meant for Singaporean University students with side businesses. With just a few commands, Common Cents will keep track, categorise and calculate your income and expenditure for you!

* Quick Start
* Features
  * Viewing help: `help`
  * Adding an expense: `add`
  * Removing an expense: `remove`
  * Editing an expense: `edit`
  * Finding expenses by keyword: `find`
  * Listing out expenses: `list`
  * Exiting the program: `exit`
* Command Summary

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `commoncents.jar`.

1. Copy the file to the folder you want to use as the _home folder_ for CommonCents.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample expenses and income.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`add`**`n/Food costs a/6.90` : Adds an expense named "Food costs" to the expense list.
   * **`delete`**`3` : Deletes the 3rd expense entry shown in the current list.
   * **`clear`** : Deletes all expenses.
   * **`exit`** : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in UPPERCASE are the parameters to be supplied by the user.<br>
  e.g. in `add n/EXPENSE_NAME`, `EXPENSE_NAME` is a parameter which can be used as `add n/Buying cups`

* Items in square brackets are optional.<br>
  e.g `n/EXPENSE_NAME [t/TAG]` can be used as `n/Buying cups t/Supplies` or as `n/Buying cups`.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/EXPENSE_NAME a/EXPENSE_AMOUNT`, `a/EXPENSE_AMOUNT n/EXPENSE_NAME` is also acceptable.


</div>

### Viewing help : `help`

Shows a message explaning how to access the help page.

![help message](images/helpMessage.png)

Format: `help`


### Adding an expense: `add`

Adds an expense to the tracker.

Format: `add c/CATEGORY n/EXPENSE_NAME a/EXPENSE_AMT`

Examples:
* `add c/expenses n/buying cups a/5.50` 
* `add c/earnings n/selling cookies a/10.10`


### Listing all persons : `remove`

Removes an expense from the tracker.

Format: `remove ENTRY_INDEX`

* Deletes the expense at the specified INDEX.
* The index refers to the index number shown in the displayed expense list.
* The index must be a positive integer 1, 2, 3, and must be within the range of the number of entries (e.g. if there are 10 entries, the INDEX given cannot be > 10)

Example: 
* `remove 1`: Removes the first expense in the tracker


### Editing a person : `edit`

Edits an existing person in the address book.

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`

* Edits the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the person will be removed i.e adding of tags is not cumulative.
* You can remove all the person’s tags by typing `t/` without
    specifying any tags after it.

Examples:
*  `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st person to be `91234567` and `johndoe@example.com` respectively.
*  `edit 2 n/Betsy Crower t/` Edits the name of the 2nd person to be `Betsy Crower` and clears all existing tags.

### Locating persons by name: `find`

Finds persons whose names contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`
* Persons matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:
* `find John` returns `john` and `John Doe`
* `find alex david` returns `Alex Yeoh`, `David Li`<br>
  ![result for 'find alex david'](images/findAlexDavidResult.png)

### Deleting a person : `delete`

Deletes the specified person from the address book.

Format: `delete INDEX`

* Deletes the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd person in the address book.
* `find Betsy` followed by `delete 1` deletes the 1st person in the results of the `find` command.

### Clearing all entries : `clear`

Clears all entries from the address book.

Format: `clear`

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
**Add** | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​` <br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 t/friend t/colleague`
**Clear** | `clear`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Edit** | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`
**Find** | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`
**List** | `list`
**Help** | `help`
