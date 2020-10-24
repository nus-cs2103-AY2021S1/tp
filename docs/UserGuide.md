---
layout: page
title: User Guide
---
* Table of Contents
{:toc}
--------------------------------------------------------------------------------------------------------------------
## 1. Introduction
OneShelf is a desktop application for you to manage restaurant inventories and pending deliveries.
It is easy to build and customise your inventories and deliveries by using
only Command Line Interface. If you are a fast-typist restaurant manager
who prefers to use the Command Line Interface and needs to keep
track of inventory items and pending deliveries, OneShelf is for you!
<br><br>
This User Guide document will provide a general overview of installation, existing features, coming-up features
as well as summary commands.



--------------------------------------------------------------------------------------------------------------------
## 2. Quick start

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `oneshelf.jar` from [here](https://github.com/AY2021S1-CS2103T-T12-1/tp/releases).

3. Copy the file to the folder you want to use as the _home folder_ for your Inventory Book.

4. Double-click the file to start the app. The similar GUI shown below (Figure 1) should appear in a few seconds. <br>
   *Note how the app contains some sample data but the installed version on your desktop might have a different data set.*<br>
   <br> ![Ui](images/Ui.png) <br>
   Figure 1: Introduction to OneShelf User Interface

5. Type the command `help start` in the command box and press Enter to execute it. <br>
   A new help window shown below (Figure 2) should appear on your desktop.
   <br> ![Help Window](images/UiHelpStart.png) <br>
   Figure 2: Help Start Window
   

6. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------
## 3. Features

OneShelf has 2 main features that it is able to store, namely are:
1. Inventory items
2. Pending deliveries

From here onwards, the term `item` and `delivery` are used specifically for inventory items and pending deliveries respectively.
*You may want to refer to Figure 1 above.*

<div markdown="block" class="alert alert-info">
**:information_source: Notes about the item command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add-i n/Chicken`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/Chicken t/Poultry` or as `n/Chicken`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME q/QUANTITY`, `q/QUANTITY n/NAME` is also acceptable.

*Note: The above notes are also applicable for `delivery`.*
</div>

--------------------------------------------------------------------------------------------------------------------
### 3.1 Command Features

#### 3.1.1 Viewing help : `help`

Format: `help start`
If you are a first time user, we strongly encourage you to enter `help start` and follow the guide to kick-start your journey in OneShelf.

Alternatives:
* Press `F1` at any point in the usage of the app
* GUI navigation menu at the top left

Format: `help summary`

We understand that even if you are not a first time user, it is not easy to remember all the commands within a single session.
Should you need a list of summary commands, you can enter `help summary` and a new window similar to Figure 3 should appear.

Alternatives:
* Press `F2` at any point in the usage of the app
* GUI navigation menu at the top left

![Help Summary Screenshot](images/HelpSummaryWindow.png) <br>
Figure 3: Help Summary Window



#### 3.1.2 Adding an item: `add-i` or `add-d`

Adds inventory item or pending delivery to OneShelf.

Format: `add-i n/NAME q/QUANTITY [s/SUPPLIER] [max/MAX_QUANTITY] [t/TAG]...​`
* Adds `QUANTITY` from the current quantity of an inventory item if the item already exist.
An item is uniquely identified by its NAME and SUPPLIER.

Examples: `add-i n/CHICKEN q/10 s/NTUC max/50`

Assuming that your inventory item is empty, entering the above command would add a new chicken item that was previously purchased from NTUC. <br>
By entering `add-i n/CHICKEN q/10 max/50`, a new inventory item would be added instead of increasing the quantity since the second chicken has no supplier. <br>
On the other hand, by entering `add-i n/CHICKEN q/10 s/NTUC` again would increase the quantity of Chicken from NTUC to be 20kg.


Format: `add-d n/NAME p/PHONE a/ADDRESS o/ORDER`

Examples: `add-d n/DAMITH p/91111111 a/Blk 251 Orchard Road o/Nasi goreng x1`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
* An item can have any number of tags (including 0)
* Unlike inventory item, pending delivery does not have a quantity
</div>



#### 3.1.3 Removing quantity from an item: `remove-i`

Removes a specified quantity of an existing item from OneShelf.

Format: `remove-i INDEX q/QUANTITY`
* Subtracts `QUANTITY` from the current quantity of an item at the specified `INDEX`. 
The index refers to the index number shown in the displayed item list. The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `remove-i 1 q/10`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
* There is no remove-d since a pending delivery does not have a quantity
</div>



#### 3.1.4 Editing an item : `edit-i` or `edit-d`

Edits an existing item in the Inventory book or an existing pending delivery in the Delivery book.

Format: `edit-i INDEX [n/NAME] [q/QUANTITY] [s/SUPPLIER] [max/MAX_QUANTITY] [t/TAG]…​`

* Edits the item at the specified `INDEX`. The index refers to the index number shown in the displayed item list. The index **must be a positive integer** 1, 2, 3, …​
* Updates ALL the components of an item, UNABLE to update a specific component of an item.
Ie if a user wants to update the quantity, he/ she needs to specify all attributes again: name, quantity, supplier, tag, if any.
* When editing tags, the existing tags of the item will be removed i.e adding of tags is not cumulative.
* You can remove all the item’s tags by typing `t/` without
    specifying any tags after it.

Examples:
*  `edit-i 1 n/Chicken q/50` <br>
Edits the name and quantity of the 1st item to be `CHICKEN` and `50` respectively.
*  `edit-i 2 n/Spinach t/` <br>
Edits the name of the 2nd item to be `Spinach` and clears all existing tags.


Format: `edit-d INDEX [n/NAME] [p/PHONE] [a/ADDRESS] [o/ORDER]`

Examples:
* `edit-d 1 n/AARON p/91111233` <br>
Edits the name and phone number of the 1st item to be `AARON` and `91111233` respectively.



#### 3.1.5 Locating items by keywords: `find-i` or `find-d`

Finds items or deliveries whose atributes contain any of the given keywords.

Format: `find-i PREFIX KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `chicken` will match `CHICKEN`
* The order of the keywords does not matter. e.g. `Chicken steak` will match `steak Chicken`
* Name, Supplier and Tag can be searched
* Only full words will be matched e.g. `chicke` will not match `chicken`
* Items matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `chicken steak` will return `chicken steak`, `steak beef`

Examples:
* `find-i n/Chicken` returns `chicken` and `chicken salad` items.


Format: `find-d PREFIX KEYWORD [MORE_KEYWORDS]`

Examples:
* `find-d n/John` returns `John Tay` and `John Lim`'s deliveries



#### 3.1.6 Listing all items : `list-i` or `list-d`

After entering `find-i` or `find-d`, the placeholder in your application will only show the items or deliveries 
that match your find KEYWORD. If you would like to show **all** the items and deliveries again, 
`list-i` or `list-d` command would be useful.

Format: `list-i` or `list-d`



#### 3.1.7 Deleting an item : `delete-i` or `delete-d`

Deletes an item or delivery from inventory book or delivery book respectively. Delete command is especially useful
for delivery as you would often need to delete a pending delivery once it has been delivered.

Format: `delete-i INDEX` or `delete-d INDEX`

* Deletes an item or delivery at the specified `INDEX`.
* The index refers to the index number shown in the displayed item/ delivery list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list-i` followed by `delete-i 2` deletes the 2nd item in the inventory book.
* `find-i Duck` followed by `delete-i 1` deletes the 1st item in the results of the `find-i` command.



#### 3.1.8 Clearing all entries : `clear-i` or `clear-d`

Clears all entries from the Inventory/ Delivery book.

Format: `clear-i` or `clear-d`



#### 3.1.9 Exiting the program : `exit`

Exits the program.

Format: `exit`



#### 3.1.10 Undo last command : `undo`

Undoes the previous command by reverting the current data displayed to the state it was in before the last command was executed.

Format: `undo`

* If there is a previous state available, the current state is reverted to that state
* If the current state is the earliest possible one, it shows a message informing the user that there is nothing more to undo



#### 3.1.11 Redo last command : `redo`

Redoes the last undone command by reverting the current data displayed to the state it was in before the last undo command was executed.

Format: `redo`

* If there is an undone state available, the current state is reverted to that state
* If the current state is the latest possible one, it shows a message informing the user that there is nothing more to redo
* After any command that changes the state of data (such as add, clear, delete, edit), the new state becomes the latest state
(i.e. the previous undo commands are "forgotten" and `redo` will have no effect)

--------------------------------------------------------------------------------------------------------------------
### 3.2 General Features
#### 3.2.1 Saving the data

OneShelf data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.



#### 3.2.2 Scroll through command history

OneShelf commands are traversable much like Window's command prompt with the arrow up key traversing into previous commands and arrow down key traversing into next commands.  



#### 3.2.3 Sorting items

* Inventory items are sorted based on percentage of quantity in ascending order. 
* If the maximum quantity does not exist for that particular item then it the item will be located at the end of the list.
* If 2 items have the same quantity, they are then sorted lexicographically.


--------------------------------------------------------------------------------------------------------------------
### 3.3 Coming Soon
#### 3.3.1 Countdown to deliver [`Coming Soon]`

Allows user to input a countdown timer when adding a new delivery so that the user will be able to keep track
the deliveries that needs to be delivered out based on their urgency

#### 3.3.2 Statistics `[Coming Soon]`

Prints the total amount of delivery and reservation for the day



#### 3.3.3 Scheduling `[Coming Soon]`

Allows user to know when to do restocking



#### 3.3.4 Prices of items `[Coming Soon]`

Look up prices on a 'supplier' database



#### 3.3.5 Notification `[Coming Soon]`

Notify the user if a certain stock is below threshold



--------------------------------------------------------------------------------------------------------------------

## 4. FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous Inventory/ DeliveryBook home folder.

--------------------------------------------------------------------------------------------------------------------



## 5. Command summary

#### 5.1 General commands summary

| Action    | Format, Examples                                                                                    |
|-----------|-----------------------------------------------------------------------------------------------------|
|**Get help to start off**    | `help start` or press `F1` or use GUI help menu at the top left |
|**Get help summary**    | `help summary` or press `F2` or use GUI help menu at the top left |                                                                                       |                                                                                             |
|**Undo last command**   | `undo`  |
|**Redo last undone command**   | `redo`  |
|**Exit command** | `exit` |

#### 5.2 Inventory summary

| Action    | Format, Examples                                                                                    |
|-----------|-----------------------------------------------------------------------------------------------------|
|**Add to Inventory**    | `add-i n/NAME q/QUANTITY [s/SUPPLIER] [max/MAX_QUANTITY] [t/TAG]...​` <br> e.g., `add n/Chicken q/3 s/ShengSiong t/Poultry` |
|**Clear from Inventory**  | `clear-i`                                                                                             |
|**Delete from Inventory** | `delete-i INDEX`<br> e.g., `delete 3`                                                                 |
|**Edit Inventory**   | `edit-i INDEX [n/NAME] [q/QUANTITY] [s/SUPPLIER] [max/MAX_QUANTITY] [t/TAG]…​`<br> e.g.,`edit 1 n/Chicken q/50`                |
|**Find in Inventory**   | `find-i PREFIX KEYWORD [MORE_KEYWORDS]`<br> e.g., `find-i n/Chicken Steak`                                       |
|**List Inventory**   | `list-i` |
|**Remove from Inventory** | `remove-i INDEX q/QUANTITY`                                                                                              |


#### 5.3 Delivery summary
| Action    | Format, Examples                                                                                    |
|-----------|-----------------------------------------------------------------------------------------------------|
|**Add to Delivery**    | `add-d` <br> e.g `add-d n/Alex Yeoh p/87438807 a/Blk 30 Geylang Street 29, #06-40 o/2x Chicken Rice, 1x Ice Milo` |
|**Clear from Delivery**  | `clear-d`                                                                                             |
|**Delete from Delivery** | `delete-d INDEX`<br> e.g., `delete 3`                                                                 |
|**Edit Delivery**   | `edit-d INDEX [n/NAME] [p/PHONE] [a/ADDRESS] [o/ORDER]`<br> e.g.,`edit 3 n/AARON p/91111233`                |
|**List Delivery**   | `list-d` |
|**Find in Delivery**  | `find-d PREFIX KEYWORD [MORE_KEYWORDS]` <br> e.g., `find-d n/Alex`           |
