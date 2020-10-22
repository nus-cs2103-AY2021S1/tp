---
layout: page
title: User Guide
---

## Introduction
![inventoryinator](images/inventoryinator.jpg)

Inventoryinator is a **desktop app for game inventories, optimized for use via a Command Line Interface** (CLI) 
while still having the benefits of a Graphical User Interface (GUI). If you can type fast, Inventoryinator can
get your inventory management tasks done faster than traditional GUI apps.

This User Guide will help you get started with the setup of Inventoryinator and provide a quick reference of
the features available and how to use them. You can use the table of contents below for easy access to sections
in this document. 

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `inventoryinator.jar` from [here](https://github.com/AY2021S1-CS2103T-F13-1/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your Inventoryinator.

1. Double-click the file to start the app. The GUI like below should appear in a few seconds. Note how the app contains some sample data.<br>
   
   ![Ui](images/Ui.png)

1. Type a command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   
   Some example commands you can try:

   * **`list`** : Lists all items stored in Inventoryinator.

   * **`addi`**`banana -q 44 -d edible banana -l Bob's Banana Farm` : Adds a item named `banana` to the Inventoryinator

   * **`add`**`banana -q 10` Add a quantity of 10 to the Bananas

   * **`deli`**`-n banana` : Deletes the banana item from the Inventoryinator

   * **`delr`**`-n banana`**`-r`**`1` : Deletes the first recipe from the item `bananas` 

   * **`exit`** : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

___________________________________________________________________

## Legend:

A legend for the styling used in this guide to describe the commands' format.

Key | Meaning
-------|-------------
term | Command/variable term<br>
**term** | Compulsory to be included in the command<br>
\<term\> | User Input Variable like recipe name or item name<br>
\[term, ...\] | Optional terms, "..." indicates multiple terms are accpeted
\[term1/ term2\] | Optional terms to specify parameters, only 1 of term1 or term2 _ should be input
-term | Option field to add to or an argument parameter
default | If no parameter is given as input, this will be the input parameter.

___________________________________________________________________

## Command summary

A summary of the commands for the features currently implemented.

Action | Format
-------|---------------------------------
**Add Item** | `addi` **-n \<item name\>** \[-q \<qty\>\] \[-d \<desc\>\] \[-l \<location1, location2, …\>\] 
**Add Recipe** | `addr` **-n \<product name\>** **-items \<item name\[quantity\], … >** \[-pc \<num>\] \[-d \<desc\>\]
**Add quantity to item** | `addq` **-n \<item name\>** **-q \<qty\>**
**List Items** | `listi`
**List Recipes** | `listr`
**Delete item** | `deli` **-n \<item name\>**
**Delete Recipe** | `delr` **-n \<item name\> -r index**
**Find** | `find` **\<search strings\>**
**View** | `view` **\<item name\>** \[-r / -c / -d (default)/ -all\]
**Help** | `help` \[command\]
**Exit** | `exit`


## Features

Inventoryinator's features and their descriptions, as of v1.2.

### Adding an item: `addi`

**NAME:**

- `addi` - adds a new item  

**SYNOPSIS:**
	 
- `addi` **-n \<item name\>** \[-q \<qty\>\] \[-d \<desc\>\] \[-l \<location1, location2, …\>\] 

**DESCRIPTION:**
- **item name:** name of the item to be added
- **q:** quantity to add (default: 1)
- **d:** description of item (default: “No description given.”)
- **l:** locations where item can be found in game 
- **t:** user defined **tag** for an item
- Adds an item to the inventory, with the given fields

**EXAMPLE:**
- `addi` -n <u>banana</u> -q 44 -d edible banana -l Bob’s banana farm -t delicious, consumable
- Adds new entry of 44 <u>banana</u>, with description edible <u>banana</u>, 
found at **location** <u>Bob’s banana farm</u> and tags delicious, consumable

### Adding a new Recipe: `addr`
**NAME:**
- `addr` - adds a new recipe

**SYNOPSIS:**
- `addr` **-n \<product name\>** **-items \<item name\[quantity\], … >** \[-pc \<num>\] \[-d \<desc\>\]

**DESCRIPTION:**
- **product name:**	name of the item created by the recipe
- **-items:** specify the list of material items used
- **-pc:** quantity of product produced in a craft (default: 1)
- **-d:** description of recipe (default: “No description given”)
- Adds a recipe to the inventory, with the given fields

**EXAMPLE:**
- `addr` -n <u>Bob’s anvil</u> **-items** <u>block of iron</u> \[3\], <u>iron ingot</u>\[4\]
- Adds a **recipe** to craft <u>Bob’s anvil</u>, which takes 3 <u>blocks of iron</u> and 4 <u>iron ingots</u>


### Adding quantity to an item: `addq`

**NAME:**
- `addq` - adds quantity to a single specified item

**SYNOPSIS:**
- `addq` **\<item name\>** **-q \<qty\>**

**DESCRIPTION:**
- **item name:** given name of the item in the system
- **-q:** amount of that item to add
- Adds the quantity to the item in the inventory

**EXAMPLE:**
- `addq` -n <u>Bob’s 6th regret</u> -q <u>8</u>
- Adds <u>8</u> more <u>Bob’s 6th regrets</u> to the inventory

### Listing all items : `listi`

**NAME:**
- `listi` - lists all items

**SYNOPSIS:**
- `listi`

**EXAMPLE:**
- `listi`
- Lists all items and their quantities

### Listing all items : `listr`

**NAME:**
- `listr` - lists all recipes

**SYNOPSIS:**
- `listr`

**EXAMPLE:**
- `listr` 
- Lists all recipes, outputs, descriptions and their ingredients

### Deleting an item : `deli`

**NAME:**
- `deli` - deletes an item

**SYNOPSIS:**
- `deli` **-n \<item name\>**

**DESCRIPTION:**
- **item name:** name of the item to be deleted
- Deletes the item in the inventory with the corresponding item name,
 and all recipes associated with the item

**EXAMPLE:**
- `deli` -n <u>Bob’s 28th finger</u>
- Deletes the **item** with the name of <u>Bob’s 28th finger</u>

### Deleting a Recipe : `delr`

**NAME:**
- `delr` - deletes a recipe

**SYNOPSIS:**
- `delr` **-n \<item name\>** **-r index**

**DESCRIPTION:**
- **item name:** name of the item associated with the recipe to be deleted
- **index:** deletes the <u>recipe</u> numbered <u>index</u>
- Deletes the recipe in the inventory with the corresponding recipe index in the given item

**EXAMPLE:**
- `delr` -n <u>Bob’s 28th finger</u> -r <u>1</u>
- Deletes the <u>first recipe</u> of the item <u>Bob’s 28th finger</u>

### Finding an item: `find`

**NAME:**
- `find` - finds items

**SYNOPSIS:**
- `find` **\<search strings\>**

**DESCRIPTION:**
- **search strings:** keywords to search by, space separated
- Displays items that match or contain, case-insensitive, any of the search keywords

**EXAMPLE:**
- `find` <u>bob alice</u>

- Returns the items whose names match/ contain bob or alice, like: 
  - Bob’s 9000th crush
  - Alice's sword
  - Little bob

### View item `view`
WIP as of v1.2

**NAME:**
- `view` - view more details on an item

**SYNOPSIS:**
- `view` **\<item name\>** \[-r / -c / -d (default)/ -all\]

**DESCRIPTION:**
- **item name:** item to view
- **-d:** 	returns item details
- **-c:** 	returns all recipes that create the item
- **-r:** 	returns all recipes that use the item
- **-all:**	returns everything above

**EXAMPLE:**
- `view` <u>Bob’s bitten fingernail clipping</u> -r 
- Returns all recipes that use <u>Bob’s bitten fingernail clipping</u>

### Viewing help : `help`
WIP as of v1.2

**NAME:** 
- `help` - lists all commands and how to use them (Current implementation)

**SYNOPSIS:**
- `help` \[command\]

**DESCRIPTION:**
- Displays a help sheet with brief descriptions of each command.
- Shows a message explaning how to access the help page.
- Will eventually be extended to get help about specific commands.

![help message](images/helpMessage.png)

### Exiting the program : `exit`

**NAME:**
- `exit` - closes the application

**SYNOPSIS:**
- `exit`

**DESCRIPTION:**
- Closes the application. 

### Saving the data

Inventoryinator data is saved in the hard disk automatically after any command that changes the data.
 There is no need to save manually. The format of save data is via `json file format`

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and run it. Then, overwrite the data files
 it creates with the files that contain the data of your previous Inventoryinator home folder.

--------------------------------------------------------------------------------------------------------------------

