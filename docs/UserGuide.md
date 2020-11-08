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
  
* **Friendly syntax** is supported! For any command, typing the short-form of the command will already be recognized, unless there is any ambiguity. 

  * E.g. `help` only requires the user to type `h` to be recognized, while `sort` will require user to at least type `so` since typing `s` by itself will conflict with another command `submit` (The commands
  will be explained below. To minimize confusion, the whole command will be shown instead of the prefix.)
  
* Extra whitespaces in between arguments will be ignored.

  E.g. `add 1 3` is interpreted as the same command as `add   1        3`.

  E.g `tag 1 this  is    a      tag` is interpreted as the same command as `tag 1 this is a tag`.

</div>

The application is divided into two modes, vendor mode and menu mode. Vendor mode is when a vendor is not yet selected,
as seen by the section on the left displaying a list of vendors. In vendor mode, only vendor
related commands can be executed. In menu mode, vendor, menu and order commands can be executed.

## General

### Getting Started: `help`

Shows the user instructions on how to use the application.

Format: `help`

- Note that anything written after the `help` command will be ignored.

### Exit application: `exit`

See you next time! 

Note that any arguments written after the `exit` command will be ignored. 

- `exit` and `exit 1` is treated as the same command
- This also applies to all other commands with no arguments

Format: `exit`

- This command closes the jar file.

## Vendor related commands

### View/select vendor: `vendor`

Displays the list of all vendors or selects the specified vendor.

Format: `vendor [INDEX]`

* If no `INDEX` is specified, it displays the list of all vendors and returns to vendor mode.
* If an `INDEX` is specified, the corresponding vendor is selected, and its menu will be shown.
* If there is an existing supper order, it will be deleted.
* `INDEX` must be a positive integer and must not exceed the size of the vendor list.

Examples:
* `vendor`: Changes back to vendor mode.
* `vendor 2`: Selects the 2<sup>nd</sup> vendor in the list.

## Menu related commands

### Displaying supper menu: `menu`

Displays the default menu from the selected vendor.

- Can be used to display the menu after a `sort` / `find` / `price` command.
- Note that any arguments written after the `menu` command will be ignored, and menu will be listed as intended.

Format: `menu`

Example:

- `menu`: Displays the full menu of the selected vendor.


### Sorting the menu: `sort`

Sorts the menu by either price or name.

Format: `sort MODE [DIRECTION]`

* `MODE` dictates which mode it will sort by, with format:
  * `n`: sorts by name
  
  * `p`: sorts by price
  
    
* `DIRECTION` dictates which direction it will sort by, with format:
  
  * `a`: sort in ascending order
  * `d`: sort in descending order
  * `t`: toggles the direction, if previous sort was ascending, new direction is descending. If no direction was previously specified, sort in ascending order
* If `DIRECTION` is not specified, it will be treated as a toggle, and ascending direction will be sorted as descending order and vice versa

Examples:
* `sort n a`: sorts the menu by name in ascending direction.
* `sort p`: sorts the menu by price in opposite direction as last sorted.


### Find food item: `find`

Finds and lists all food items containing any of the specified keywords in their name.

Format: `find KEYWORD [MORE_KEYWORDS]`

- `KEYWORD` are NOT **case-sensitive**.
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
  * `>`: Strictly greater than
  * `>=`: Greater than or Equal to
* `PRICE` must be a non-negative real number.
* Note that decimal places of `PRICE` are taken into account when filtering. However, the message shown will only be to 2 decimal places.

Examples:
* `price < 3`: lists all food items with price less than $3.
* `price <= 2`: lists all food items with price less than or equals to $2.
* `price > 4`: lists all food items with price greater than $4

## Order related commands

### Adding a food item: `add`

Adds an order item for the user according to the index from the menu to the user's supper order.

Format: `add INDEX [QUANTITY]`

* The `INDEX` refers to the index number shown on the displayed menu list.
* `INDEX` must be a positive integer and must not exceed the size of the menu list.
* `QUANTITY` can be specified to indicate the number of item to be added. Otherwise, it adds one quantity of the item at the specified index.

Examples:
* `add 1 1`: add item at INDEX 1, of QUANTITY 1.
* `add 2 3`: add item at INDEX 2, of QUANTITY 3.
* `add 1`: add item at INDEX 1, of default QUANTITY 1.


### Removing an order item : `remove`

Removes the specified item from the supper order.

Format: `remove INDEX [QUANTITY]`

* `INDEX` refers to the index number shown in the displayed supper order list.
* `INDEX` must be a positive integer and must not exceed the size of the supper order list.
* `QUANTITY` can be specified to indicate the number of item to be deleted. Otherwise, it deletes all quantities of the item at the specified index.

Examples:
* `remove 2`: remove all quantities of item at INDEX 2.
* `remove 1 2`: remove item at INDEX 1, of quantity 2.


### Tag an order item: `tag`

Tags an order item with remark.

Format: `tag INDEX REMARK`

- The `INDEX` refers to the index number of the order item.
- `INDEX` must be positive number and must not exceed the size of the order list.
- `REMARK` is any non-empty string.
- If the `REMARK` being added to the order item already exists, it would not be added.
- Unlimited tags can be added, and is left up to the user's discretion.

Examples:
* `tag 5 2 no egg`: tags the order item at INDEX 5 with the REMARK '2 no egg'.


### Untag an order item: `untag`

Clears all tags of the specified order item.

Format: `untag INDEX`

- The `INDEX` refers to the index number of the order item.
- `INDEX` must be positive number and must not exceed the size of the order list.

Examples:

- `untag 1`: clears all tags for the order item at INDEX 1.


### Clearing the order: `clear`

Removes everything from the order.

Format: `clear`

- Note that any arguments written after the `clear` command will be ignored.

Example:

- `clear`: clears all items on current order.


### Undo changes to order: `undo`

Undoes last change to the order. Note that it does not affect commands unrelated to order.

Format: `undo`

- Note that any arguments written after the `undo` command will be ignored.
- Note that an error message will be returned to the user if there are no changes left to undo.

Example:

- `undo`: undoes previous command and returns order back to its previous state.

### Calculate total: `total`

Displays the total cost of the order currently.

Format: `total`

- Note that any arguments written after the `total` command will be ignored.
- Note that an error message will be returned to the user if the order is empty.

Example:

- `total`: returns the total cost of all items from current order.



### Profile Details: `profile`

Add delivery address and phone number for submission.

Format: `profile PHONE ADDRESS`

- `PHONE` represents your contact number and must be a valid phone number (First digit must start with a 6/8/9 and must be 8 digits long).

- `ADDRESS` represents your delivery address.

Examples:

- `profile 92030888 25 Lower Kent Ridge Rd, Singapore 119081`: Saves your PHONE number as '92030888' and your ADDRESS as '25 Lower Kent Ridge Rd, Singapore 119081'.


### Generate order text: `submit`

Displays a copy-paste-able text of profile with order. A profile must be set up before using `submit`.

Format: `submit`

- Text obtained will be copied to clipboard if possible.
- Note that an error message will be returned to the user if the order is empty.
- Note that any arguments written after the `submit` command will be ignored.

Example:

- `submit`: returns a text form of the order

### Preset supper orders: `preset`

Saves or Loads a preset of the user's supper order.

Format: `preset MODE [NAME]`

* `MODE` dictates what the system will perform for the user's supper orders, represented by the formats:
  * `save`: Used to save a preset. (If used without a `NAME`, will save with a default preset name of 'MyPreset')
  * `load`: Used to load a preset. (If used without a `NAME`, will list all saved presets for the current vendor)
  * `delete`: Used to delete a preset. (`NAME` must be specified)
* `NAME` is the preset name which the system will save the preset as, or tries to load the given preset by the given name.
  * if `NAME` already exists and preset is in save mode, the new preset will overwrite the existing preset.
  * `NAME` is **case-sensitive** and supports space characters.
  * if `NAME` does not exist and preset is in delete mode, an error message will be returned to the user.
* Presets are split by vendors, therefore running `preset save PresetName` for two different vendors will not affect one
another. Similar for `preset load`.

Examples:
* `preset save`: saves the user's supper order with the default preset name.
* `preset load MyPreset`: loads the current default preset if it exists.
* `preset save vegan`: save the user's supper order with a preset NAME of 'vegan'.
* `preset load vegan`: loads the preset supper order with the preset NAME 'vegan'.
* `preset delete vegan`: deletes the preset supper order with the preset NAME "vegan".



--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?
**A**: Overwrite your current data file with your old data file.

**Q**: Why are some pictures missing on the menu?
**A**: These are intended as an example of what is shown if vendors lack a picture for a food item.

**Q**: Why am I not allowed to add vendors or menu items?
**A**: We realised that allowing users to add vendors or menu items from the command line is unfeasible, as each vendor will have many menu items and it would be extremely time consuming for the user. However, the user is able to manually add vendors or menu items in the json file, but the user must ensure that all details are included correctly.


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
**Tag** | `tag INDEX REMARK`
**Untag** | `untag INDEX`
**Clear** | `clear`
**Undo** | `undo`
**Total** | `total`
**Profile** | `profile PHONE ADDRESS`
**Submit** | `submit`
**Preset** | `preset MODE [NAME]`

