---
layout: page
title: User Guide
---

<img align="right" height="170" src="images/inventoryinator.jpg">

## Introduction

**Inventoryinator** is a **desktop app for managing game inventories**, and supports
**any generic game** where you need to sort and collate resources. If you ever find
yourself wondering how many resources you have back at home base, or are unsure whether
you can craft your next upgrade, then this is the app for you!

Our app is optimized for use via the **typing** of commands, while your inventory is
shown on our **Graphical User Interface (GUI)**. If you can type fast, Inventoryinator can
get your inventory management tasks done faster than traditional GUI apps.

This User Guide will help you get started with the setup of Inventoryinator and provide a
quick reference of the features available and how to use them. You can use the table of
contents below for easy access to sections in this document. 

<div style="page-break-after: always;"></div>

* Table of Contents
{:toc}

<div style="page-break-after: always;"></div>

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `inventoryinator.jar` from [here](https://github.com/AY2021S1-CS2103T-F13-1/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your Inventoryinator.

1. Double-click the file to start the app. The GUI like below should appear in a few seconds. Note how the app contains some sample data.<br>
   
   ![Ui](images/Ui.png)

1. Type a command in the command box and press `Enter` to execute it. e.g. typing **`help`** and pressing `Enter` will open the help window.<br>
   
   Some example commands you can try:

   * **`listi`** : This shows you all items stored in your inventory.

   * **`addi`**`-n banana -q 44 -d edible banana -l Bobs Banana Farm` : Adds an item named `banana` to your inventory.

   * **`addq`**`-n banana -q 10` Adds a quantity of 10 to the number of `banana` you own.

   * **`deli`**`-n banana` : Deletes the `banana` item from your inventory.

   * **`delr`**`-n banana -i 1` : Deletes the first recipe from the `banana` item of your inventory.

   * **`exit`** : Exits the application.

1. Refer to the [Features](#features) below for details of each command.

___________________________________________________________________

<div style="page-break-after: always;"></div>

## Legend:

You can refer to this section to reference the syntax used in this guide to format
the commands.

Key | Meaning
-------|-------------
term | Standard command/variable term<br>
-term | Terms preceded by a dash (-) are prefixes to denote the parameter after it (eg. -q for quantity)
**compulsory_term** | Bolded terms are compulsory for the command to be executed<br>
\<input_variable\> | Terms in <> brackets are user input variables like recipe names or item names<br>
\<term, ...\> | "..." indicates that the command can accept multiple terms for this parameter
\[optional_input_variable\] | Terms in [] brackets are optional
\[optional_term, ...\] | "..." indicates that the command can accept zero or more terms for this parameter
default | If no parameter is given as input, this will be the input parameter

___________________________________________________________________

<div style="page-break-after: always;"></div>

## Command summary

A summary of the commands for the features currently implemented.

Action | Format
-------|---------------------------------
**Add Item** | `addi` **-n \<item name\>** \[-q \<qty\>\] \[-d \<desc\>\] \[-l \<location, …\>\] \[-t \<tag, …\>\]
**Add Recipe** | `addr` **-n \<product name\>** **-items \<item name\[quantity\], … >** \[-pc \<num>\] \[-d \<desc\>\]
**Add Quantity to Item** | `addq` **-n \<item name\>** **-q \<qty\>**
**Add Tag to Item** | `addt` **-n \<item name\>** **-t \<tag, …\>**
**Craft Item** | `craft` **-n \<item name\>** **-q \<quantity\>** \[-i \<index\>\]
**Check craft** | `check` **-n \<item name\>** **-q \<quantity\>** 
**Delete Item** | `deli` **-n \<item name\>**
**Delete Recipe** | `delr` **-n \<item name\> -i \<index\>**
**Edit Item** | `edit` **-o \<item name\>** \[-n \<name\>\] \[-q \<qty\>\] \[-d \<desc\>\] \[-t \<tag, …\>\]
**Find Item by Name** | `find` **\<string, ...\>**
**Find Item by Tag** | `findt` **\<string, ...\>**
**List Items** | `listi`
**List Recipes** | `listr`

<div style="page-break-after: always;"></div>

**View Detailed View of Item** | `view` **\<item name\>**
**Clear Items** | `cleari`
**Clear Recipes** | `clearr`
**Undo** | `undo`
**Redo** | `redo`
**Help** | `help` 
**Exit** | `exit`

<div style="page-break-after: always;"></div>

## Features
In this section, you can find a comprehensive list of Inventoryinator's commands and their descriptions, as of v1.4.
For each command, you will find a written description of the command's functions. For experienced users, you can
refer to the more succinct name, synopsis, description and example sections to directly access the information you need.

```
NOTE:
We have removed all apostrophes (') from our example commands, 
but that does not mean you can't use them.
We have only done so because github (or something else) formats apostrophes
to be different from the ones on our keyboard, which affects our 
Inventoryinator's case-sensitive commands.
Now, you can copy and paste our example commands without worry.
:)
```

### Adding an item: `addi`
(Implemented by: Zhengdao)

This command allows you to add a new item to your inventory. Adding an item will cause Inventoryinator to start
tracking your item. You will need to provide an item name. You can optionally provide the initial item quantity,
description, location and tags. If you do not provide these fields, they will be set to default values (except for
location and tags). You will be able to edit these fields later on.

**NAME:**

- `addi` - adds a new item to your inventory 

**SYNOPSIS:**
	 
- `addi` **-n \<item name\>** \[-q \<qty\>\] \[-d \<desc\>\] \[-l \<location, …\>\] \[-t \<tag, …\>\]

**DESCRIPTION:**
- **n:** name of the item to be added
- **q:** quantity to add (default: 0)
- **d:** description of item (default: “None.”)
- **l:** locations where item can be found in game (default: "None")
- **t:** user defined tag for an item
- Adds an item to your inventory, with the given input.

**EXAMPLE:**
- `addi` -n <u>banana</u> -q 44 -d edible banana -l Bobs banana farm -t delicious, consumable

<img height="400" src="images/UG images/UG addi.png"/>

This command adds a new entry of 44 <u>banana</u>, with description <u>edible banana</u>, 
found at location <u>Bobs banana farm</u> and tags <u>delicious</u>, <u>consumable</u> to your inventory.

```
TIPS:
Quantity should be an integer between 0 and 2,147,483,647.

Item names are case-sensitive.

Tags must be alphanumerical and cannot contain spaces.
```

<div style="page-break-after: always;"></div>

### Adding a new Recipe: `addr`
(Implemented by: Rahul)

This command allows you to add a new recipe to your inventory. Adding a recipe will cause Inventoryinator to link
the recipe to its product item. This will allow you to use this recipe to craft the item using the `craft` command
you will see later. You will need to provide a product name as well as names and quantities of the items used to
create this product. You can optionally provide the product quantity (if the recipe produces more than 1 of the product)
and a description for the recipe. If you do not provide these fields, they will be set to default values.

**NAME:**
- `addr` - adds a new recipe to your inventory

**SYNOPSIS:**
- `addr` **-n \<product name\>** **-items \<item name\[quantity\], … >** \[-pc \<num\>\] \[-d \<desc\>\]

**DESCRIPTION:**
- **n:**	name of the item created by the recipe
- **items:** specify the list of material items used
- **pc:** quantity of product produced in a craft (default: 1)
- **d:** description of recipe (default: “None”)
- Adds a recipe to your inventory, with the given input,
 do note that all items mentioned in the command must exist in the item listing.

**EXAMPLE:**
- `addr` -n <u>Bobs anvil</u> **-items** <u>block of iron</u>\[3\], <u>iron ingot</u>\[4\]

<img height="400" src="images/UG images/UG addr.png"/>

This command adds a recipe to craft <u>Bobs anvil</u>, which takes 3 <u>blocks of iron</u> and 4 <u>iron ingots</u>.

```
TIPS:
Item names are case-sensitive.

You cannot edit recipes! Please be careful when typing in your quantities.

To add a recipe that creates an item not yet in your inventory, 
you will need to add the item first.

You CAN have 0 as a quantity for an item used, 
we like to call these items 'catalysts'.
```

<div style="page-break-after: always;"></div>

### Adding quantity to an item: `addq`
(Implemented by: Jing Lin)

This command allows you to increase or decrease the quantity of an item in your inventory by a specified amount.
You cannot reduce the quantity of an item to below 0. You will need to provide the item name of the item you wish
to increase or decrease the quantity to, as well as the quantity you wish to increase or decrease. To decrease the
quantity, provide a negative number.

**NAME:**
- `addq` - adds quantity to a single specified item

**SYNOPSIS:**
- `addq` **-n \<item name\>** **-q \<qty\>**

**DESCRIPTION:**
- **n:** given name of the item in the system
- **q:** amount of that item to add
- Adds the quantity to the item in your inventory.

**EXAMPLE:**
- `addq` -n <u>Bobs 6th regret</u> -q <u>8</u>

<img height="400" src="images/UG images/UG addq.png"/>

Adds <u>8</u> more <u>Bobs 6th regrets</u> to your inventory.

```
TIPS:
Total quantity cannot exceed 2,147,483,647.
```

<div style="page-break-after: always;"></div>

### Adding tags to an item: `addt`
(Implemented by: Stephen)

This command allows you to add tags to an item. You will need to provide the item name of the item you wish to add
tags to, and the tags that you wish to add.

**NAME:**
- `addt` - adds tags to a single specified item

**SYNOPSIS:**
- `addt` **-n \<item name\>** **-t \<tag, …\>**

**DESCRIPTION:**
- **n:** given name of the item in the system
- **t:** tags to be added to the item
- Adds the tags to the item in your inventory.

**EXAMPLE:**
- `addt` -n <u>Bobs 9th horse</u> -t <u>consumable</u>

<div style="page-break-after: always;"></div>

<img height="400" src="images/UG images/UG addt.png"/>

Adds the <u>consumable</u> tag to the item <u>Bobs 9th horse</u> in your inventory.

```
TIPS:
Tags must be alphanumerical and cannot contain spaces.
Your input must contain at least one tag that the item does not currently have.
```

<div style="page-break-after: always;"></div>

### Listing all items: `listi`
(Implemented by: Kheng Hun)

This command displays all items in your inventory. You will be able to view the item names, their quantities,
descriptions and tags.

**NAME:**
- `listi` - lists all items

**SYNOPSIS:**
- `listi`

**DESCRIPTION:**
Lists all items that are in your inventory, and their quantities, descriptions and tags.

<img height="400" src="images/UG images/UG listi.png"/>

<div style="page-break-after: always;"></div>

### Listing all recipes: `listr`
(Implemented by: Kheng Hun)

This command displays all recipes in your inventory. You will be able to view the product names, amount of product
produced by the recipe, and the items and their associated quantities required to craft the product. You
will also see the recipe description.

**NAME:**
- `listr` - lists all recipes

**SYNOPSIS:**
- `listr`

**DESCRIPTION:**
Lists all recipes, products, descriptions and their ingredients.

<img height="400" src="images/UG images/UG listr.png"/>

<div style="page-break-after: always;"></div>

### Deleting an item: `deli`
(Implemented by: Stephen)

This command allows you to delete an item in your inventory. This removes the item as well as all recipes that use
or produce the item from your inventory. You will need to provide the item name of the item you wish to delete.

**NAME:**
- `deli` - deletes an item

**SYNOPSIS:**
- `deli` **-n \<item name\>**

**DESCRIPTION:**
- **n:** name of the item to be deleted
- Deletes the item in your inventory with the corresponding item name,
 and all recipes associated with the item

**EXAMPLE:**
- `deli` -n <u>Bobs 28th finger</u>

<img height="400" src="images/UG images/UG deli.png"/>

Deletes the item with the name <u>Bobs 28th finger</u> from your inventory.

```
TIPS:
If you accidentally delete an item, you can always undo it!
```

<div style="page-break-after: always;"></div>

### Deleting a Recipe: `delr`
(Implemented by: Kheng Hun)

This command allows you to delete a recipe in your inventory. This removes the recipe from your inventory, and it
will no longer be associated with any of its ingredients or product. You will need to provide the product name of the
recipe and the index of the recipe in the product item.

**NAME:**
- `delr` - deletes a recipe

**SYNOPSIS:**
- `delr` **-n \<item name\>** **-i \<index\>**

**DESCRIPTION:**
- **n:** name of the product item of the recipe to be deleted
- **r:** deletes the <u>recipe</u> numbered <u>index</u>
- Deletes the recipe in your inventory with the corresponding recipe index in the given item.

**EXAMPLE:**
- `delr` -n <u>Bobs 28th finger</u> -i <u>1</u>

<img height="400" src="images/UG images/UG delr.png"/>

Deletes the <u>first recipe</u> of the item <u>Bobs 28th finger</u> from your inventory.

```
TIPS:
To find the index of the recipe you wish to delete, 
use the view command on the product item.

The input recipe index should not exceed 2,147,483,647.
```

<div style="page-break-after: always;"></div>

### Finding an item: `find`
(Implemented by: Rahul)

This command allows you to search for items in your inventory by item name. Entering multiple names will allow you
to search for items that have an item name that matches or contains any string. Note that this command is
case-insensitive.

**NAME:**
- `find` - finds items

**SYNOPSIS:**
- `find` **\<string, ...\>**

**DESCRIPTION:**
- **string:** keywords to search by, comma separated.
- Displays items in your inventory that match or contain, case-insensitive, any of the search keywords.

**EXAMPLE:**
- `find` <u>bob</u>, <u>alice</u>

<img height="400" src="images/UG images/UG find.png"/>

- Returns the items whose names match/ contain <u>bob</u> or <u>alice</u>, like: 
  - Bobs 9000th crush
  - Alices sword
  - Little bob

```
TIPS:
You can use `find .` to find all items in your inventory.
```

<div style="page-break-after: always;"></div>

### Finding items by tags: `findt`
(Implemented by: Stephen)

This command allows you to search for items in your inventory by tag. Entering multiple tags will allow you
to search for items that have any tags in your search parameters.

**NAME:**
- `findt` - finds items tagged with matching tag strings

**SYNOPSIS:**
- `findt` **\<string, ...\>**

**DESCRIPTION:**
- **string:** keyword tags to search by, comma separated.
- Displays items that match the tags, case-insensitive, any of the given search keywords.

**EXAMPLE:**
- `findt` <u>delic</u>, <u>yummy</u>

<div style="page-break-after: always;"></div>

<img height="400" src="images/UG images/UG findt.png"/>

- Returns the items whose tags match/contain "delic" or "yummy", like: 
  - Bobs Banana tags: [<u>tuturu</u>, <u>yummy</u>]
  - Alices Apple tags: [<u>delicate</u>]
  - Kims Kiwi tags: [<u>yummy</u>, <u>delicious</u>]

```
TIPS:
Search parameters must be alphanumerical and cannot contain spaces.
```

<div style="page-break-after: always;"></div>

### View item `view`
(Implemented by: Zhengdao)

This command allows you to view more details of an item in your inventory, and the recipes that can craft this item.
You will need to provide the item name of the item you wish to view.

**NAME:**
- `view` - view more details on an item

**SYNOPSIS:**
- `view` **\<item name\>** 

**DESCRIPTION:**
- **item name:** item to view

**EXAMPLE:**
- `view` <u>Bobs bitten fingernail clipping</u>

<div style="page-break-after: always;"></div>

<img height="400" src="images/UG images/UG view.png"/>

View all details of the item with the name <u>Bobs bitten fingernail clipping</u>.

```
TIPS:
This command will only show an item that matches 
the search parameter exactly. (case-sensitive)
```

<div style="page-break-after: always;"></div>

### Edit an item: `edit`
(Implemented by: Kheng Hun)

This command allows you to edit an item in your inventory. You will need to provide the item name of the item you wish
to edit as well as the fields you wish to edit.

**NAME:**
- `edit` - edit 1 or more fields of an item

**SYNOPSIS:**
- `edit` **-o \<item name\>** \[-n \<name\>\] \[-q \<qty\>\] \[-d \<desc\>\] \[-t \<tag, …\>\]

**DESCRIPTION:**
- **o:** item name of the item to be edited
- **n:** new name of edited item
- **q:** new quantity of edited item
- **d:** new description of edited item
- **t:** new tags of edited item

**EXAMPLE:**
- `edit` -o <u> Iron Ore</u> -q <u>20</u>

Edits the item named <u>Iron Ore</u> to have quantity of <u>20</u>.

- `edit` -o <u> Iron Ore</u> -q <u>20</u> -d <u>mined</u>  

<img height="400" src="images/UG images/UG edit.png"/>

Edits the item named <u>Iron Ore</u> to have quantity of <u>20</u> and description of <u>mined</u>.

<div style="page-break-after: always;"></div>

### Craft an item: `craft`
(Implemented by: Kheng Hun)

This command allows you to craft an item using a recipe in your inventory. You will need to provide the item name
of the item being crafted, the quantity of the item you wish to craft, and optionally, the index of the recipe you wish
to use. If you do not provide the index, the first recipe will be used. The ingredients of the recipe will have their
quantities reduced by the amount required to craft the desired quantity of the product.

**NAME:**
- `craft` - crafts a quantity of an item using materials in your inventory

**SYNOPSIS:**
- `craft` **-n \<item name\>** **-q \<quantity\>** \[-i \<index\>\]

**DESCRIPTION:**
- **n:** name of the item to craft
- **q:** desired quantity of the item to obtain  
- **i:** uses the <u>recipe</u> numbered <u>index</u> for crafting (default: 1)
Crafts quantity of the specified item using the specified recipe.

**EXAMPLE:**
- `craft` -n <u>Iron Sword</u> -q <u>3</u> -i <u>2</u>

<img height="400" src="images/UG images/UG craft.png"/>

Crafts 3 <u>Iron Swords</u> using the second recipe in the list which may craft it. 3 <u>Iron Swords</u> will be
added to your inventory.

```
TIPS:
This command may craft more of the desired item than your input, 
if the recipe used creates 2 or more of the product.
```

<div style="page-break-after: always;"></div>

### Check if crafting an item is possible: `check`
(Implemented by: Kheng Hun)

This command allows you to check if you have sufficient items in your inventory to craft a specified quantity of a
desired item. You will need to provide the item name and quantity of the item you wish to check if you can craft.

**NAME:**
- `check` - check if crafting an item with one recipe is possible based on the current inventory

**SYNOPSIS:**
- `check` **-n \<item name\>** **-q \<quantity\>** 

**DESCRIPTION:**
- **n:** name of the item to check
- **q:** desired quantity of the item to obtain from crafting 
Checks if it is possible for you to craft a quantity of the specified item using any recipe in your inventory.

**EXAMPLE:**
- `check` -n <u>Iron Sword</u> -q <u>5</u>  

<img height="400" src="images/UG images/UG check.png"/>

Checks and displays if any recipe in the recipe list can craft 5 (or more) <u>Iron Swords</u>
based on your current inventory.

<div style="page-break-after: always;"></div>

### Clear all items: `cleari`
(Implemented by: Zhengdao)

This command allows you to clear all items and recipes in your inventory.

**NAME:**
- `cleari` - Deletes all items and recipes

**SYNOPSIS:**
- `cleari`

**DESCRIPTION:**
Deletes all items and recipes. Your inventory will be empty after executing this command.

**EXAMPLE:**
- `cleari` followed by `listi`
    - `listi` will indicate "You have no items in your inventory now."

<img height="400" src="images/UG images/UG cleari.png"/>

<div style="page-break-after: always;"></div>

### Clear all recipes: `clearr`
(Implemented by: Zhengdao)

This command allows you to clear all recipes in your inventory.

**NAME:**
- `clearr` - Deletes all recipes

**SYNOPSIS:**
- `clearr`

**DESCRIPTION:**
Deletes all recipes.

**EXAMPLE:**
- `clearr` followed by `listr`
    - `listr` will indicate "You have no recipes in your inventory now."

<img height="400" src="images/UG images/UG clearr.png"/>

<div style="page-break-after: always;"></div>

### Undo a command: `undo`
(Implemented by: Jing Lin)

This command allows you to undo the last command that affected any item or recipe. Your inventory will be reverted
to how it was before you executed the command. You should use this if you accidentally executed a command you did
not intend to.

**NAME:**
- `undo` - Reverses the effect of the previous command

**SYNOPSIS:**
- `undo`

**DESCRIPTION:**
Reverses the effect of the previous command. This only
considers commands that adds, removes, or changes an item or recipe.

<div style="page-break-after: always;"></div>

**EXAMPLE:**
- `addq -n Apple -q 10` followed by `undo`
    - First command adds 10 <u>Apples</u>, `undo` removes the 10 <u>Apples</u>.

<img height="400" src="images/UG images/UG undo.png"/>

<div style="page-break-after: always;"></div>

### Redo a command: `redo`
(Implemented by: Jing Lin)

This command allows you to redo the last command that was undone. Your inventory will be set to how it was after
you executed the command. You should use this if you accidentally undid a command you actually wanted to execute.

**NAME:**
- `redo` - Reverses the effect of the previous undone command

**SYNOPSIS:**
- `redo`

**DESCRIPTION:**
Reverses the effect of the previous undo command.

<div style="page-break-after: always;"></div>

**EXAMPLE:**
- `addq -n Apple -q 10` followed by `undo` followed by `redo`
    - First command adds 10 <u>Apples</u>, `undo` removes the 10 <u>Apples</u>,
      `redo` re-adds the 10 <u>Apples</u>.

<img height="400" src="images/UG images/UG redo.png"/>

<div style="page-break-after: always;"></div>

### Viewing help: `help`
This command opens a dialog box with a link to this user guide. If you're here, you don't need this command! :)

**NAME:** 
- `help` - lists all commands and how to use them (current implementation)

**SYNOPSIS:**
- `help`

**DESCRIPTION:**
Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

### Exiting the program: `exit`
This command allows you to close the application.

**NAME:**
- `exit` - closes the application

**SYNOPSIS:**
- `exit`

**DESCRIPTION:**
Closes the application. 

<div style="page-break-after: always;"></div>

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and run it. Then, overwrite the data files
 it creates with the files that contain the data of your previous Inventoryinator home folder.

**Q**: Do I need to save my data manually in the application?
**A**: Inventoryinator data is saved in the hard disk automatically after any command that changes the data.
        There is no need to save manually. The format of save data is via `json file format`

## Glossary
* Mainstream OS: Windows, Linux, Unix, OS-X
* GUI: The graphical user interface is a form of user interface that allows users
 to interact with electronic devices through graphical icons and audio indicator such as primary notation.
* Parameter: a user input to be used by the application
* Item: An item represents an object you obtain in a game. Eg a <u>Rock</u>
* Recipe: A recipe is associated with multiple items, and represents the consumption of items in the input,
 to produce an item of the output. Eg: 3 <u>Sticks</u> -> <u>Staff</u>
* Location: The place where an item can be found in game. Eg: <u>Sleepywood</u>
* Inventory: The entire state of the Inventoryinator, including recipes, items, locations etc.

--------------------------------------------------------------------------------------------------------------------
