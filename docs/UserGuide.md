---
layout: page
title: User Guide
---
**Supper Strikers is a desktop application for managing your supper orders.** It is targeted at students living in NUS for ordering delivery from supper stretch.  While it has a GUI (Graphical User Interface), most of the user interactions happen using a CLI (Command Line Interface).

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

1. Download the latest `supperstrikers.jar` from [here](https://github.com/AY2021S1-CS2103-T16-1/tp/releases).

1. Copy the jar to the folder you want to use as the _home folder_ for your SupperStrikers.

1. Open a command window. Run the java -version command to ensure you are using Java 11. If not, please install Java 11 to ensure you are able to safely launch the jar file.

1. Launch the jar file using the java -jar supperstrikers.jar command (do not use double-clicking). The GUI similar to the one below should appear in a few seconds.<br>
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
  E.g. in `find KEYWORD`, `KEYWORD` is a parameter which can be used as `find spicy`.
* Items in square brackets are optional.<br>
  E.g `add INDEX [QUANTITY]` can be used as `add 3 2` or as `add 3`.
* Friendly syntax is supported! For any command, typing the prefix of the command will already be recognized, unless there is any ambiguity. 
* E.g. `help` only requires the user to type `h` to be recognized, while `sort` will require user to at least type `so` since typing `s` by itself will conflict with another command `submit` (The commands
will be explained below. To minimize confusion, the whole command will be shown instead of the prefix.)

</div>

The application is divided into two modes, vendor mode and menu mode. Vendor mode is when a vendor is not yet selected,
as seen by the section on the left displaying a list of vendors. In vendor mode, only vendor
related commands can be executed. In menu mode, both vendor and menu commands can be executed.

## General

### Getting Started: `help`

Shows the user instructions on how to use the application.

Format: `help`

- Note that anything written after the `help` command will be ignored.

### Exit application: `exit`

See you next time! 

Format: `exit`

- Note that anything written after the `exit` command will be ignored.

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

Displays the menu from the selected vendor.

Format: `menu`

- Can be used to display the menu after a `sort` / `find` / `price` command.
- Note that anything written after the `menu` command will be ignored, and menu will be listed as intended.

Example:

- `menu`: Displays the menu of the selected vendor.

### Sorting the menu: `sort`

Sorts the menu by either price or name.

Format: `sort MODE [DIRECTION]`

* `MODE` dictates which mode it will sort by, with format:
  * `name`: sorts by name.
  * `price`: sorts by price.
* `DIRECTION` dictates which direction it will sort by, with format:
  * `ascending`: sort in ascending order.
  * `descending`: sort in descending order.
* If `DIRECTION` is not specified, it will be treated as a toggle, and ascending direction will be sorted as descending order and vice versa.

Examples:
* `sort name ascending`: sorts the menu by name in ascending direction.
* `sort price`: sorts the menu by price in opposite direction as last sorted.


### Find food item: `find`

Finds and lists all food items containing any of the specified keywords in their name.

Format: `find KEYWORD [MORE_KEYWORDS]`

- `KEYWORD` are NOT Case-Sensitive
- `KEYWORD` filters tags as well.

Examples:
* `find milo`: lists all food items containing the word 'milo' in their name.
* `find milo dinosaur`: lists all food items containing the word 'milo' or 'dinosaur' in their name.


### Filter food item by price: `price`

Filters all food item within a specified price range.

Format: `price INEQUALITY PRICE`

* `INEQUALITY` is an inequality sign, of the below formats:
  * `<`: Strictly less than
  * `<=`: Less than or Equal to
  * `>`: Greater than
  * `>=`: Greater than or Equal to
* `PRICE` must be a non-negative real number.

Examples:
* `price < 3`: lists all food item with price less than $3.
* `price >= 2`: lists all food item with price from $2.

## Order related commands

### Adding a food item: `add`

Adds an OrderItem for the user according to the index from the menu to the OrderManager.

Format: `add INDEX [QUANTITY]`

* The `INDEX` refers to the index number shown on the displayed menu list.
* `INDEX` must be a positive integer and must not exceed the size of the menu list.
* `Quantity` can be specified to indicate the number of item to be added. Otherwise, it adds one quantity of the item at the specified index.

Examples:
* `add 1 1`: add item at INDEX 1, of QUANTITY 1.
* `add 2 3`: add item at INDEX 2, of QUANTITY 3.
* `add 1`: add item at INDEX 1, of default QUANTITY 1.


### Removing an item : `remove`

Removes the specified item from the supper order.

Format: `remove INDEX [QUANTITY]`

- `INDEX` refers to the index number shown in the displayed supper order list.

* `INDEX` must be a positive integer and must not exceed the size of the supper order list.
* `QUANTITY` can be specified to indicate the number of item to be deleted. Otherwise, it deletes all quantities of the item at the specified index.

Examples:
* `remove 2`: remove item at INDEX 2.
* `remove 1 2`: remove item at INDEX 1, of quantity 2.


### Clearing the order: `clear`

Removes everything from the order.

Format: `clear`

Example:

- `clear`: clears all items on current order.


### Undo changes to order: `undo`

Undoes last change to the order.

Format: `undo`

- Note that anything written after the `undo` command will be ignored.

Example:

- `undo`: undoes previous command and returns order back to its previous state.

### Calculate total: `total`

Displays the total cost of the order currently.

Format: `total`

- Note that anything written after the `total` command will be ignored.

Example:

- `total`: returns the total cost of all items from current order.


### Generate order text: `submit`

Displays a copy-paste-able text of the order.

Format: `submit`

- Text obtained is automatically copied to clipboard.

Example:

- `submit`: returns a text form of the order

### Preset supper orders: `preset`

Saves or Loads a preset of the user's supper order.

Format: `preset MODE [NAME]`

* `MODE` dictates what the system will perform for the user's supper orders, represented by the formats:
  * `save`: Used to save a preset. (If used without a `NAME`, will save with a default preset name of 'MyPreset')
  * `load`: Used to load a preset. (If used without a `NAME`, will list all saved presets)
* `NAME` is the preset name which the system will save the preset as, or tries to load the given preset by the given name.
  * if `NAME` already exists, the new preset will overwrite the existing preset.
  * `NAME` is **Case-Sensitive**.
  * `NAME` is Vendor Specific, and is unique to each vendor.

Examples:
* `preset save`: saves the user's supper order with the default preset name.
* `preset load MyPreset`: loads the current default preset if it exists.
* `preset save vegan`: save the user's supper order with a preset name of 'vegan'.
* `preset load vegan`: loads the preset supper order with the preset name 'vegan'.



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
