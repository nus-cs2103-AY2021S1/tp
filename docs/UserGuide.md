# User Guide

Common Cents is your convenient at-hand expense-tracking tool, meant for anyone who runs a small-scale business. With just a few commands, Common Cents will keep track, categorise and calculate your income and expenditure for you!

* Quick Start
* Features
  <!--* Viewing help: `help`-->
  * Adding an entry: `add`
  * Deleting an entry: `delete`
  * Editing an entry: `edit`
  * Finding entries by a keyword: `find`
  * Exiting the program: `exit`
* Command Summary

--------------------------------------------------------------------------------------------------------------------
[comment]: <> (Copy the blocks below and edit your message)

<div markdown="block" class="alert alert-info"> 

:information_source: **Note:**

Explains the rationale behind our design. 

</div>

<div markdown="block" class="alert alert-primary">

[comment]: <> (This only appears in Github CSS)

:bulb: **Tip:**

Good to learn, but not necessary to know to use FitEgo. 
</div>


<div markdown="block" class="alert alert-warning">

:star: **Feature:**

Important to know.
</div>

<div markdown="block" class="alert alert-success">

:heavy_check_mark: **Example:**

An example to follow. 

</div>

<div markdown="block" class="alert alert-danger">

:warning: **Warning:**

May have irreversible effect when used. Backup and caution is recommended.
</div>


--------------------------------------------------------------------------------------------------------------------

# Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `commoncents.jar`.

1. Copy the file to the folder you want to use as the _home folder_ for CommonCents.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample expenses and revenues.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * `add c/expense d/buy lunch a/5.20 t/food` : Adds an expense named "buy lunch" to the expense list.
   * `delete 1 c/expense` : Deletes the 1st entry in the expense list.
   * `exit` : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

# Features

<div markdown="block" class="alert alert-info">

**Notes about the command format:**<br>

* Words in UPPERCASE are the parameters to be supplied by the user.<br>
  e.g. in `add c/CATEGORY...`, `CATEGORY` is a parameter which can be used as `add c/REVENUE...`

* Items in square brackets are optional.<br>
  e.g `...a/AMOUNT [t/TAG]` can be used as `...a/50.10 t/Supplies` or as `...a/50.10`.

* Parameters can be in any order.<br>
  e.g. if the command specifies `c/CATEGORY d/DESCRIPTION...`, `d/DESCRIPTION c/CATEGORY...` is also acceptable.


</div>

## App-Level Commands

### Viewing help : `help`

Shows a message explaining how to access the help page (Figure). You can click on the `Copy URL ` button to copy
the link to clipboard and paste it 
<br>![Ui](images/helpMessage.png)
<p align="center"> <i>Figure: Help message</i> </p>

Format: `help`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

## Entry-Level Commands

### Adding an entry: `add`

Adds an entry (expense/revenue) to the tracker.

Format: `add c/CATEGORY d/DESCRIPTION a/AMOUNT [t/TAG]`

* Adds an entry to either category.
* The category refers to the classification of the entry in the entry lists.
* The category must be either 'expense' or 'revenue'.

Examples:
* `add c/expense d/buying supplies a/10.10 t/blogshop t/eCommerce`
* `add c/revenue d/sale of clothes a/200 t/blogshop t/eCommerce`



### Deleting entries : `delete`

Removes an entry (expense/revenue) from the tracker.

Format: `delete ENTRY_INDEX c/CATEGORY`

* Deletes the entry at the specified INDEX.
* The index refers to the index number shown in the displayed entry lists.
* The index must be a positive integer 1, 2, 3, and must be within the range of the number of entries (e.g. if there are 10 entries, the INDEX given cannot be > 10)

Example:
* `delete 1 c/expense`
* `delete 2 c/revenue`


### Editing an entry (expense/revenue) : `edit`

Edits an entry in the tracker.

* Edits an entry that matches the entry name and changes the parameters of an entry depending on which are specified
* The tags d/ and a/ have to be used to indicate which field specifies DESCRIPTION and which field specifies AMOUNT
* The index must be a positive integer 1, 2, 3, and must be within the range of the number of entries (e.g. if there are 10 entries, the `INDEX` given cannot be > 10)

Formats:
* `edit 1 c/CATEGORY d/DESCRIPTION a/AMOUNT t/TAG`
* `edit 2 c/CATEGORY d/DESCRIPTION`
* `edit 3 c/CATEGORY a/AMOUNT`

Example:
* `edit 2 c/expense n/buy McSpicy a/8.60` changes the name and the amount of entry 2 to `eating McSpicy` and `$8.60` respectively
* `edit 2 c/revenue n/sell McNuggets` changes the name of entry 2 to `sell McNuggets`
* `edit 2 c/expense a/5.50` changes the amount of entry 2 to `$5.50`


### Locating entries by description: `find`

You can use this command to list expenses and/or revenues that have the given keyword(s) in their description. If there
is no expense or revenue found, the respective list will be empty.

**Format:** `find [c/CATEGORY] k/KEYWORDS`

<div markdown="block" class="alert alert-success">

:heavy_check_mark: **Example:** 

`find c/expense k/watercolours canvas` : finds all expenses with keywords `watercolours` and/or `canvas` in their 
description.

`find c/revenue k/phone` : finds all revenues with keyword `phone` in their 
description.

`find k/canvas earrings` : finds all expenses and revenues with keywords `canvas` and/or `earrings` in their 
description (Figure).
 
</div>

<br>![findCommand](images/command/findCommand.png)
<p align="center"> <sub> <b>Figure</b>: Find command successfully executed </sub> </p>

<div markdown="block" class="alert alert-info"> 

:information_source: **Note:**

* The search is case-insensitive. e.g. `grocery` will match `grocery` and `Grocery`.
* Only the entry's description is searched.
* The search does not match partial words. e.g. `food` will match `food` and `fast food` but not `fastfood`.
* Entries matching at least one keyword will be returned (i.e. OR search). e.g. `Food` will return `Hawker food`, 
`Restaurant Food`.
* The category is optional.

<div markdown="block" class="alert alert-primary">

[comment]: <> (This only appears in Github CSS)

:bulb: **Tip:**

Good to learn, but not necessary to know to use FitEgo. 
</div>

</div>

Format:
* `find k/KEYWORD [MORE_KEYWORDS]`

Example:
* `find k/food`: Finds expenses with the keyword `food`.

### Listing all entries: `list`

### Clearing all expenses or revenue: `clear`

### Calculating net profits based on expenses and revenues: `profit` 

## Account-level Commands

### Add new Account: `newacc`

### Deleting an account: `deleteacc`

### Editing the current account: `editacc`

### Listing accounts you have: `listacc`

### Switching accounts: `switchacc`

--------------------------------------------------------------------------------------------------------------------

# Command summary

Action | Format, Examples
--------|------------------
**Help** | `help`
**Exit** | `exit`
**Add** | `add c/CATEGORY d/DESCRIPTION a/AMOUNT [t/TAG]`
**Delete** | `delete ENTRY_INDEX c/CATEGORY`
**Edit** | `edit 1 c/CATEGORY [d/DESCRIPTION] [a/AMOUNT] [t/TAG]`
**Find** | `find k/KEYWORD [MORE_KEYWORDS]`
**List** |
**Clear** |
**Profit** |
**New Account** |
**Delete Account** |
**Edit Account** |
**List Account** |
**Switch Account** |

<!-- **Edit** | `edit ENTRY_NUM n/EXPENSE_NAME a/MONEY_AMT` -->
<!-- **Find** | `find KEYWORD [MORE_KEYWORDS]` -->
