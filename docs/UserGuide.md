---
layout: page
title: User Guide
---

AddressBook Level 3 (AB3) is a **desktop app for managing contacts, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, AB3 can get your contact management tasks done faster than traditional GUI apps.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `inventoryinator.jar` from [here](https://github.com/AY2021S1-CS2103T-F13-1/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your AddressBook.

1. Double-click the file to start the app. The GUI like below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`list`** : Lists all items stored in Inventoryinator.

   * **`addi`**`banana -q 44 -d edible banana -l Bob's Banana Farm` : Adds a item named `banana` to the Inventoryinator

   * **`add`** `banana -q 10` Add a quantity of 10 to the Bananas

   * **`del`**`banana` : Deletes the banana item from the Inventoryinator

   * **`del`**`banana` **`-r`** `1` : Deletes the first recipe from the item `bananas` 

   * **`clear`** : Deletes all contacts.

   * **`exit`** : Exits the app.

1. Refer to the [Features](#features) below for details of each command.
___________________________________________________________________
## Convention:
- term:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Command/variable term<br>
- **term**:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Compulsory to be included in the command<br>
- \<term\>:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;User Input Variable like recipe name or item name<br>
- \[term1/ term2\]:&nbsp;&nbsp;&nbsp;&nbsp;Optional terms to specify parameters, only 1 of term1 or term2 _ should be input
- -term: Option field to add to or a argument parameter
- default:&nbsp;&nbsp;&nbsp;&nbsp;If no parameter is given as input, this will be the input parameter.
___________________________________________________________________

## Command summary

Action | Format
--------|------------------
**Add Item** | `addi` <**item name**> \[-q \<qty\>\] \[-d \<desc\>\] \[-l \<location1, location2…\>\] 
**Add Recipe** | `addr` \<**product name**\> **-items** **\<item name\[quantity\]**, … > \[-pc \<num>\] \[-d \<desc\>\]
**Add quantity to item** | `add` <**item name**> -q <**qty**>
**List** | `list` \[-i (default) / -r\]
**Delete entry** | `del` \<item name\> \[-r index\]
**Find** | `find` \<search string\>
**View** | `view` <item name> \[-r / -c / -d (default)/ -all\]
**Help** | `help` \[command\]
**Bye** | `bye`


## Features

### Viewing help : `help`
**NAME:** 

- `help` - lists all commands and how to use them (Current implementation)

**SYNOPSIS:**

- `help` \[command\]

**DESCRIPTION:**

- Displays a help sheet with brief descriptions of each command.

- Shows a message explaning how to access the help page.
Will eventually be extended to get help about specific commands.
![help message](images/helpMessage.png)

### Adding a item: `addi`

**NAME:**

- `addi` - adds a new item  

**SYNOPSIS:**
	 
- `addi` <**item name**> \[-q \<qty\>\] \[-d \<desc\>\] \[-l \<location1, location2…\>\] 

**DESCRIPTION:**

- **q:**	qty indicates quantity to add (default: 1)
- **d:**	desc indicates description of item (default: “No description given.”)
- **l:**	locations indicate where item is found (default: none)

**EXAMPLE:**
- `addi` <u>banana</u> -q 44 -d edible banana -l Bob’s banana farm
- Adds new entry of 44 <u>banana</u>, with description edible <u>banana</u>, found at **location** <u>Bob’s banana farm</u>

### Adding a new Recipe: `addr`
**NAME:**
- `addr` - adds a new recipe

**SYNOPSIS:**
- `addr` \<**product name**\> **-items** **\<item name\[quantity\]**, … > \[-pc \<num>\]
\[-d \<desc\>\]

**DESCRIPTION:**
- **product name:**	name of the item created by the recipe
- **-items:** specify the list of material items used
- **-pc:** quantity of product produced in a craft (default: 1)
- **-d:** description of recipe (default: “No description given”)

**EXAMPLE:**
- `addr` <u>Bob’s anvil</u> **-items** <u>block of iron</u> \[3\], <u>iron ingot</u>\[4\]
- Adds a **recipe** to craft <u>Bob’s anvil</u>, which takes 3 <u>blocks of iron</u> and 4 <u>iron ingots</u>


### Adding a item: `add`

**NAME:**

- `add` - add a quantity to a single specified item

**SYNOPSIS:**

- `add` <**item name**> -q <**qty**>

**DESCRIPTION:**
- **item name:** given name of the item in the system
- **-q:** amount of that item to add to the accumulated value

**EXAMPLE:**
- `add` <u>Bob’s 6th regret</u> -q <u>8</u>
- Adds <u>8</u> more <u>Bob’s 6th regrets</u> to the `Inventoryinator`

### Listing all persons : `list`
**NAME:**
	
- `list` - lists all items or recipes the user has entered

**SYNOPSIS:**

- `list` \[-i (default) / -r\]

**DESCRIPTION:**

- **i:** Displays list of items
(showing item name and quantity)
- **r:** Displays list of recipes
(showing item, required items and their quantities, and the product items and their quantities)

**EXAMPLE:**
- `list` -i
- Lists all items and their quantities


### Deleting a entry : `del`
**NAME:**
- `del` - delete an item or recipe

**SYNOPSIS:**
- `del` \<item name\> \[-r index\]

**DESCRIPTION:**
- **deletes** the item in the inventory with the corresponding item name
- **index:** 	if provided, deletes the <u>recipe</u> numbered <u>index</u>, 

**EXAMPLE:**
- `del` <u>Bob’s 28th finger</u>
- Deletes the **item** with the name of <u>Bob’s 28th finger</u>

### Finding an Entry: `find`
**NAME:**
- `find` - find an item

**SYNOPSIS:**
- `find` \<search string\>

**DESCRIPTION:**
- Displays items that match the search string, via the **description**.

**EXAMPLE:**
- `find` -i <u>Bob’s 9000th crush</u>
- Returns the item that match the item description: 
<u>Bob’s 9000th crush</u>

	(supports regex, eg. find -i <u>Bob’s ([1-9][0-9])(st\*nd\*rd\*th) crush</u>)


### View item `view`
**NAME:**
- `view` - view more details on an item

**SYNOPSIS:**
- `view` <item name> \[-r / -c / -d (default)/ -all\]

**DESCRIPTION:**
- **-d:** 	returns item details
- **-c:** 	returns all recipes that create the item
- **-r:** 	returns all recipes that use the item
- **-all:**	returns everything above

**EXAMPLE:**
- `view` <u>Bob’s bitten fingernail clipping</u> -r 
- Returns all recipes that use <u>Bob’s bitten fingernail clipping</u>


### Exiting the program : `bye`

**NAME:**
- `bye` - closes the application

**SYNOPSIS:**
- `bye`

**DESCRIPTION:**
- Closes the application. 

### Saving the data

Inventoryinator data is saved in the hard disk automatically after any command that changes the data.
 There is no need to save manually. The format of save data is via `json file format`

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous AddressBook home folder.

--------------------------------------------------------------------------------------------------------------------

