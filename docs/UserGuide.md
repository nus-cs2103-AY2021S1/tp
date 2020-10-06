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


### Removing expenses : `remove`

Removes an expense from the tracker.

Format: `remove ENTRY_INDEX`

* Deletes the expense at the specified INDEX.
* The index refers to the index number shown in the displayed expense list.
* The index must be a positive integer 1, 2, 3, and must be within the range of the number of entries (e.g. if there are 10 entries, the INDEX given cannot be > 10)

Example: 
* `remove 1`: Removes the first expense in the tracker


### Editing an expense : `edit`

Edits an entry in the tracker.

* Edits an entry that matches the expense name and changes the initial amount with the either entry in a/MONEY_AMT (if any), the initial name to the entry in n/EXPENSE_NAME (if any) or both if both entries are keyed in
* The tags n/ and a/ have to be used to indicate which field specifies EXPENSE_NAME and which field specifies MONEY_AMT
* Order of whether `[n/EXPENSE_NAME]` or `[a/MONEY_AMT]` would not affect the edit command so long as the `n/` and `a/` tags are used, e.g. `edit 2 n/Cash paid at Zouk a/200` would have the same effect as `edit 2 a/200 and n/Cash paid at Zouk`.
* The index must be a positive integer 1, 2, 3, and must be within the range of the number of entries (e.g. if there are 10 entries, the `INDEX` given cannot be > 10)

Format: 
* `edit ENTRY_NUM [n/EXPENSE_NAME] [a/MONEY_AMT]`


Example: 
* `edit 2 n/eating McSpicy a/8.60` changes the name and the amount of entry 2 to `eating McSpicy` and `$8.60` respectively
* `edit 2 n/eating McNuggets` changes the name of entry 2 to `eating McNuggets` 
* `edit 2 a/5.50` changes the amount of entry 2 to `$5.50`


### Locating expenses by name: `find`

Finds expenses that have the given keyword in their names.

* The search is case-sensitive. e.g `grocery` will match `grocery` but not `Grocery`.
* Only the expense name is searched.
* Expenses matching at least one keyword will be returned (i.e. OR search). e.g. `Food` will return `Hawker Food`, `Restaurant Food`

Format: 
* `find KEYWORD [MORE_KEYWORDS]`

Example:
* `find food`: Finds expenses with the keyword `food`.


### Listing out expenses : `list`

Lists out all expenses in a category. 

* The search is case-sensitive. e.g `food` will match `food` but not `Food`.
* All expenses belonging to that category will be shown. 

Format: 
* `list CATEGORY`

Example:
* `list earning`: Lists expenses that belong to the `earnings` category. 

### Exiting the program : `exit`

Exits the program.

Format: `exit`

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Help** | `help`
**Add** | `add c/CATEGORY  n/EXPENSE_NAME a/EXPENSE_AMT`
**Remove** | `remove ENTRY_NUM`<br>`remove n/EXPENSE_NAME`  
**Edit** | `edit ENTRY_NUM n/EXPENSE_NAME a/MONEY_AMT`
**Find** | `find KEYWORD [MORE_KEYWORDS]`
**List** | `list CATEGORY`
**Exit** | `exit`
