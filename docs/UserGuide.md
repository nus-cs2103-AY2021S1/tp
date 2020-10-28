---
layout: page
title: User Guide
---
**Supper Strikers is a desktop application for managing your supper orders.** While it has a GUI (Graphical User Interface), most of the user interactions happen using a CLI (Command Line Interface).

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

1. Download the latest `supperstrikers.jar` from [here](https://github.com/AY2021S1-CS2103-T16-1/tp/releases).

1. Copy the jar to the folder you want to use as the _home folder_ for your SupperStrikers.

1. Open a command window. Run the java -version command to ensure you are using Java 11. If not, please install Java 11 to ensure you are able to safely launch the jar file.

1. Launch the jar file using the java -jar command (do not use double-clicking). The GUI similar to the one below should appear in a few seconds.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`vendor`** : List all the vendors available.
   * **`vendor 1`** : Select the first vendor to make a supper order for.
   * **`add 1 2`** : Adds two quantity of the item at the 1st index from the vendor's menu to your supper order.
   * **`remove 1 1`** : Removes 1 quantity of the item at the 1st index from your order.
   * **`preset save My First Preset`** : Saves your current order items locally as a preset with the name "My First Preset", which you can call it back easily by:
   * **`preset load My First Preset`** : Loads the preset with the name "My First Preset" to your supper strikers app!
   * **`exit`** : Exits the app.
   
1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `find KEYWORD`, `KEYWORD` is a parameter which can be used as `find spicy`.

* Items in square brackets are optional.<br>
  e.g `add INDEX [QUANTITY]` can be used as `add 3 2` or as `add 3`.
  
* Friendly syntax is supported! For any command, typing the prefix of the command will already be recognized, unless
there is any ambiguity. For example, `help` only requires the user to type `h` to be recognized, while `sort` will
require user to at least type `so` since typing `s` by itself will conflict with another command `submit` (The commands
will be explained below. For the sake of clarity, the whole command will be shown instead of the prefix.)

</div>

The application is divided into two modes, vendor mode and menu mode. Vendor mode is when a vendor is not yet selected,
as seen by the section on the left displaying a list of vendors. In vendor mode, only vendor
related commands can be executed. In menu mode, both vendor and menu commands can be executed.

## General

### Getting Started: `help`

Shows the user instructions on how to use the application.


### Exit application: `exit`

See you next time! 

## Vendor related commands

### View/select vendor: `vendor`

Displays the list of all vendors or selects the specified vendor.

Format: `vendor [INDEX]`

* If no `INDEX` is specified, it displays the list of all vendors and returns to vendor mode.
* If an `INDEX` is specified, the corresponding vendor is selected, and its menu will be shown.
* If there is an existing supper order, it will be deleted.
* `INDEX` must be a positive integer and must not exceed the size of the vendor list.

Examples:
* `vendor` Change back to vendor mode.
* `vendor 2` Selects the 2<sup>nd</sup> vendor in the list.

## Menu related commands

### Displaying supper menu: `menu`

Shows the menu from the currently selected vendor.

Format: `menu`


### Sorting the menu: `sort`

Sorts the menu by either price or name.

Format: `sort MODE [DIRECTION]`

* `MODE` must be either 'name' or 'price', which dictates which mode it will sort by.
* `DIRECTION` is either 'ascending' or 'descending', which dictates which direction it will sort by.
* If `DIRECTION` is not specified, it will be treated as a toggle, and ascending direction will be sorted as descending 
order and vice versa.

Examples:
* `sort name ascending` sorts the menu by name in ascending direction.
* `sort price` sorts the menu by price in opposite direction as last sorted.


### Find food item: `find`

Finds and lists all food items containing any of the specified keywords in their name. Note that the keywords are 
case-sensitive.

Format: `find KEYWORD [MORE_KEYWORDS]`

Examples:
* `find milo dinosaur` lists all food items containing the word 'milo' or 'dinosaur' in their name.


### Filter food item by price: `price`

Finds all food item within a specified price range.

Format: `price INEQUALITY PRICE`

* `INEQUALITY` is an inequality sign, and must be either '<' (strictly less than), '<=' (less than or equal to),
'>' (greater than), or '>=' (greater than or equal to).
* `PRICE` must be a non-negative real number.

Examples:
* `price < 3` lists all food item with price less than $3.
* `price >= 2` lists all food item with price from $2.

## Order related commands

### Adding a food item: `add`

Adds a new food item for the user according to the index from the menu.

Format: `add INDEX [QUANTITY]`

* The `INDEX` refers to the index number shown in the displayed menu list.
* `INDEX` must be a positive integer and must not exceed the size of the menu list.
* Quantity can be specified to indicate the number of item to be added. Otherwise, it adds one quantity of the item at the specified index.

Examples:
* `add 1 1` add 1 quantity of item at index 1.
* `add 2 3` add 3 quantity of item at index 2.
* `add 1` add 1 quantity of item at index 1.


### Removing an item : `remove`

Removes the specified item from the supper order.

Format: `remove INDEX [QUANTITY]`

* The `INDEX` refers to the index number shown in the displayed supper order list.
* `INDEX` must be a positive integer and must not exceed the size of the supper order list.
* Quantity can be specified to indicate the number of item to be deleted. Otherwise, it deletes all quantities of the item at the specified index.

Examples:
* `remove 2` deletes the 2<sup>nd</sup> item in the supper order.
* `remove 1 2` deletes 2 instances of the 1<sup>st</sup> item in the supper order.


### Clearing the order: `clear`

Removes everything from the order.

Format: `clear`


### Undo changes to order: `undo`

Undoes last change to the order.

Format: `undo`


### Calculate total: `total`

Displays the total cost of the order currently.

Format: `total`


### Generate order text: `submit`

Displays a copy-paste-able text of the order.

Format: `submit`


### Preset supper orders: `preset`

Saves or Loads a preset of the user's supper order.

Format: `preset MODE [NAME]`

* `MODE` must be either 'save' or 'load', which dictates what the system will perform for the user's supper orders.
* `NAME` is the preset name which the system will save the preset as, or tries to load the given preset by the given name.
* If `NAME` is not specified, for save mode, it will save the preset with a default preset name. Meanwhile, for load mode,
it will display all the saved presets under the current vendor to the user.

Examples:
* `preset save` saves the user's supper order with the default preset name.
* `preset load vegan` loads the preset supper order with the preset name "vegan".



--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Overwrite your current data file with your old data file.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format
--------|------------------
**Help**   | `help`
**Exit** | `exit`
**Vendor** | `vendor [INDEX]`
**Menu**   | `menu`
**Sort** | `sort MODE [DIRECTION]`
**Find** | `find KEYWORD [MORE_KEYWORDS]`
**Price** | `price INEQUALITY PRICE`
**Add** | `add INDEX [QUANTITY]`
**Remove** | `remove INDEX [QUANTITY]`
**Clear** | `clear`
**Undo** | `undo`
**Total** | `total`
**Submit** | `submit`
**preset** | `preset MODE [NAME]`