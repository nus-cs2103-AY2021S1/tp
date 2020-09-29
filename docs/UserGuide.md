---
layout: page
title: User Guide
---
* Table of Contents
{:toc}
--------------------------------------------------------------------------------------------------------------------

## Introduction
 OneShelf is a desktop application for you to manage all of restaurant inventories,
 table reservations and pending deliveries. 
 It is easy to build and customise your inventories by using 
 only Command Line Interface. If you are a busy restaurant manager 
 who prefers to use the Command Line Interface and needs to keep 
 track of multiple items, OneShelf is for you!

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `addressbook.jar` from [here](https://github.com/se-edu/addressbook-level3/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your Inventory Book.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`list`** : Lists all contacts.

   * **`add`**`add n/Chicken q/3 s/ShengSiong t/Poultry` : Adds a item named `Chicken` to OneShelf.

   * **`delete`**`3` : Deletes the 3rd contact shown in the current list.

   * **`clear`** : Deletes all contacts.

   * **`exit`** : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/Chicken`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/Chicken t/Poultry` or as `n/Chicken`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME q/QUANTITY`, `q/QUANTITY n/NAME` is also acceptable.

</div>

### Viewing help : `help`

Shows a message explaning how to access the help page.

![help message](images/helpMessage.png)

Format: `help`


### Adding a item: `add`

Adds item to OneShelf, if there's already an item inside with the same name and supplier, it adds on to existing
quantity of existing item.

Format: `add n/NAME q/QUANTITY s/SUPPLIER [t/TAG]...​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A item can have any number of tags (including 0)
</div>

Examples:
* `add n/Chicken q/3 s/ShengSiong t/Poultry`
* `add n/Chicken q/3 s/NTUC`

### Listing all items : `list`

Shows a list of all items in the Inventory book.

Format: `list`

### Editing an item : `edit`

Edits an existing item in the Inventory book.

Format: ` edit INDEX n/NAME q/QUANTITY s/SUPPLIER [t/TAG]…​`

* Edits the item at the specified `INDEX`. The index refers to the index number shown in the displayed item list. The index **must be a positive integer** 1, 2, 3, …​
* Updates ALL the components of an item, UNABLE to update a specific component of an item.
Ie if a user wants to update the quantity, he/ she needs to specify all attributes again: name, quantity, supplier, tag, if any.
* When editing tags, the existing tags of the item will be removed i.e adding of tags is not cumulative.
* You can remove all the item’s tags by typing `t/` without
    specifying any tags after it.

Examples:
*  `edit 1 n/Chicken q/50` Edits the name and quantity of the 1st item to be `CHICKEN` and `50` respectively.
*  `edit 2 n/Spinach t/` Edits the name of the 2nd item to be `Spinach` and clears all existing tags.

### Locating items by name: `find`

Finds items whose names contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `chicken` will match `CHICKEN`
* The order of the keywords does not matter. e.g. `Chicken steak` will match `steak Chicken`
* Only the name is searched.
* Only full words will be matched e.g. `chicke` will not match `chicken`
* Items matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `chicken steak` will return `chicken steak`, `steak beef`

Examples:
* `find Chicken` returns `chicken` and `CHICKEN`

### Deleting an item : `delete`

Deletes the specified item from the inventory book.

Format: `delete INDEX`

* Deletes the item at the specified `INDEX`.
* The index refers to the index number shown in the displayed item list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd item in the inventory book.
* `find Duck` followed by `delete 1` deletes the 1st item in the results of the `find` command.

### Clearing all entries : `clear`

Clears all entries from the Inventory book.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

InventoryBook data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Undo `[Coming Soon]`

Undo previous command

### Sorting items`[Coming Soon]`

Implicit sorting done. Can be explicitly called for lexicographical sorting

### Statistics `[Coming Soon]`

Prints the total amount of delivery and reservation for the day

### Scheduling `[Coming Soon]`

Allows user to know when to do restocking

### Prices of items `[Coming Soon]`

Look up prices on a 'supplier' database

### Notification `[Coming Soon]`

Notify the user if a certain stock is below threshold

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous InventoryBook home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

| Action    | Format, Examples                                                                                    |
|-----------|-----------------------------------------------------------------------------------------------------|
|**Add**    | `add n/NAME q/QUANTITY s/SUPPLIER [t/TAG]...​` <br> e.g., `add n/Chicken q/3 s/ShengSiong t/Poultry` |
|**Clear**  | `clear`                                                                                             |
|**Delete** | `delete INDEX`<br> e.g., `delete 3`                                                                 |
|**Edit**   | ` edit INDEX n/NAME q/QUANTITY s/SUPPLIER [t/TAG]…​`<br> e.g.,`edit 1 n/Chicken q/50`                |
|**Find**   | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find Chicken Steak`                                       |
|**List**   | `list`                                                                                              |
|**Help**   | `help`                                                                                              |
