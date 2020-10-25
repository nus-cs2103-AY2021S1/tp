# User Guide

Common Cents is your convenient at-hand expense-tracking tool, meant for anyone who runs a small-scale business. With just a few commands, Common Cents will keep track, categorise and calculate your income and expenditure for you!

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------
[comment]: <> (Copy the blocks below and edit your message)
# How to identify notations
These blocks are a few examples of notations that will be used in this document. Each block and icon 
has a different meaning so do look out for them under our features.

<div markdown="block" class="alert alert-info"> 

:information_source: **Note:** 

Important notes to read regarding the feature. 
</div>

<div markdown="block" class="alert alert-primary">

[comment]: <> (This only appears in Github CSS)

:bulb: **Tip:**

Ways to better your experience with Common Cents.
</div>

<div markdown="block" class="alert alert-success">

:heavy_check_mark: **Example:**

An example, or multiple examples to follow.
</div>

<div markdown="block" class="alert alert-danger">

:warning: **Warning:**

Important cautions that needs to be known before using the feature.
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



### Deleting entries: `delete`

Removes an entry (expense/revenue) from the tracker.

Format: `delete ENTRY_INDEX c/CATEGORY`

* Deletes the entry at the specified INDEX.
* The index refers to the index number shown in the displayed entry lists.
* The index must be a positive integer 1, 2, 3, and must be within the range of the number of entries (e.g. if there are 10 entries, the INDEX given cannot be > 10)

Example:
* `delete 1 c/expense`
* `delete 2 c/revenue`


### Editing an entry: `edit`

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

<br>![findCommand](images/commands/findCommand.png)
<p align="center"> <sub> <b>Figure</b>: Find command successfully executed </sub> </p>

<div markdown="block" class="alert alert-info"> 

:information_source: **Note:**

* The search is case-insensitive. e.g. `grocery` will match `grocery` and `Grocery`.
* Only the entry's description is searched.
* The search does not match partial words. e.g. `food` will match `food` and `fast food` but not `fastfood`.
* Entries matching at least one keyword will be returned (i.e. OR search). e.g. `Food` will return `Hawker food`, 
`Restaurant Food`.
* The category is optional.

</div>

<div markdown="block" class="alert alert-primary">

[comment]: <> (This only appears in Github CSS)

:bulb: **Tip:** When there are a lot of entries and you want to delete something, 
you can use `find` to search for it in the list and use `delete ENTRY_INDEX` to remove it from the updated
list, with the updated `ENTRY INDEX`.
</div>


### Listing all entries: `list`

Lists all entries in the current account.

**Format:** `list`


### Clearing all expenses or revenue: `clear`
If for whatever reason you would like to clear all entries from a particular category, 
you can use this command to do so.
 
Format: `clear`
 
<div markdown="block" class="alert alert-success">

:heavy_check_mark: **Example:**

* `clear c/expense`: clears all entries in expenses by setting the state to an empty one.

* `clear c/revenue`: clears all entries in revenues by setting the state to an empty one.

</div>

*(Add screenshots for before and after clear.)*

<div markdown="block" class="alert alert-info"> 

:information_source: **Note:**

The `clear` command will clear all entries in the stipulated category. If you would like to delete selected
entries, use the `delete` command instead.

</div>

<div markdown="block" class="alert alert-danger">


### Calculating net profits based on expenses and revenues: `profit` 
If you wish to calculate the profits you have currently, you may use this command to do so.
 
Format: `profit`
 
<div markdown="block" class="alert alert-success">

:heavy_check_mark: **Example:**

* `profit`: Returns the profits by taking the difference between the expenses and revenues.

</div>

*(Add screenshot showing the profit message)*

<div markdown="block" class="alert alert-info"> 

:information_source: **Note:**

The `profit` function will show deficits as a negative number should expenses be greater than revenue.
</div>


### Undoing Entry-level commands: `undo`
If you wish to undo the previous command, you can use this command to return to the state of entries prior to
previous command.
 
Format: `undo`
 
<div markdown="block" class="alert alert-success">

:heavy_check_mark: **Example:**

* `undo`: Returns the state of expenses and revenues prior to the previous command. For example, if the 
[`add`](#adding-an-entry-add) command was used, using `undo` returns account to the state before the 
[`add`](#adding-an-entry-add) command was used as shown in the Figures below.

</div>

*(Add screenshots for before and after the undo, pointing to the entries)*

<div markdown="block" class="alert alert-info"> 

:information_source: **Note:**

Do note that the `undo` command can only undo  [`add`](#adding-an-entry-add), [`delete`](#deleting-entries-delete),
[`edit`](#editing-an-entry-edit) and [`clear`](#clearing-all-expenses-or-revenue-clear) commands at the entry-level. 
Account-level commands cannot be reverted using the `undo` command.

</div>

<div markdown="block" class="alert alert-danger">

:warning: **Warning:**

There is no `redo` command to revert your `undo` command. Do use the `undo` command with caution!

</div>

## Account-level Commands

### Add new Account: `newacc`
If you wish to separate your expenses and revenues for another purpose, you can create a new account with this
command. Once the account is created, you can check it via [`listacc`](#listing-accounts-you-have-listacc) 
or switch to it via [`switchacc`](#switching-accounts-switchacc). 

Format: `newacc n/NAME`

<div markdown="block" class="alert alert-success">

:heavy_check_mark: **Example:**

* `newacc n/Lim's Flower Shop`: adds a new account, `Lim's Flower Shop`, with no expenses or revenues.
* `newacc n/$uper $avers`: adds a new account, `$uper $avers`, with no expenses or revenues. 
</div>

*(Insert screenshot here for the second example, pointing to the feedback)*

### Deleting an account: `deleteacc`
If you wish to remove an account that you are not using anymore, you can delete the account using this command.

Format: `deleteacc INDEX`

<div markdown="block" class="alert alert-success">

:heavy_check_mark: **Example:**

* `deletacc 1`: Deletes first account based on list generated by [`listacc`](#listing-accounts-you-have-listacc).

</div>

*(Insert screenshot here, pointing to the feedback)*

<div markdown="block" class="alert alert-info"> 

:information_source: **Note:**

Do note that Common Cents prevents you from deleting the account if you are currently on that account
or if the account is your only account left.
</div>

<div markdown="block" class="alert alert-primary">

:bulb: **Tip:**

If you are unsure of the account index, use [`listacc`](#listing-accounts-you-have-listacc) to check the index!
</div>


<div markdown="block" class="alert alert-danger">

:warning: **Warning:**

Deleting your account means all the data on entries in the account is lost as well. Also, deleting is an 
irreversible action and cannot be undone. Do delete your account with caution!

</div>

### Editing the name current account: `editacc`
You can edit the name of the current account you are on using this command. 

Format: `editacc n/NAME`

<div markdown="block" class="alert alert-success">

:heavy_check_mark: **Example:**

* `editacc n/Bob's Bakery`: Replaces the name of the current account, `Josh's Bakery` to `Bob's Bakery` as shown in the Figure below

</div>

*(Insert screenshot here, point to the feedback and the account name)*

<div markdown="block" class="alert alert-info"> 

:information_source: **Note:**

Do note that you cannot edit account names other than the account you are currently on. To edit the name
of another account, you can use [`switchacc`](#switching-accounts-switchacc) to switch to the other account
and edit the name from there.

</div>

### Listing accounts you have: `listacc`
You can check the accounts you have in the app by using this command. Each account will be numbered with an index and 
displayed as their names.

Format: `listacc`

<div markdown="block" class="alert alert-success">

:heavy_check_mark: **Example:**

* `listacc`: Shows a numbered list of the account names as a message.

</div>

*(Insert screenshot here, pointing to the feedback)*

### Switching accounts: `switchacc`
If you wish to manage your expenses or revenues on another account, you can use this command to switch to the desired
account. 

Format: `switchacc INDEX`

<div markdown="block" class="alert alert-success">

:heavy_check_mark: **Example:**

* `switchacc 1`: Switches to the first account based on list generated by [`listacc`](#listing-accounts-you-have-listacc).

</div>

<div markdown="block" class="alert alert-primary">

:bulb: **Tip:**

If you are unsure of the account index, use [`listacc`](#listing-accounts-you-have-listacc) to check the index!
</div>

<div>

:warning: **Warning:**

Once you use the `switchacc` command, all the previous states for the [`undo`](#undoing-entry-level-commands-undo) will
be lost. This would mean that if you choose to switch back to the account, you cannot undo the previous entry-level
commands anymore. Do confirm your changes to the entries in the account before you switch account!
</div>


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
