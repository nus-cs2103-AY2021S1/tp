---
layout: page
title: User Guide
---

NUStorage is a desktop application for __managing inventory, transaction records and accounts__.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Make sure you have Java 11 installed
1. Download the NUStorage.jar from <here>.
1. Copy the jar file to the folder you want to use as the home folder for your application.
1. Double-click on the jar file to start the app. The GUI should look something like this: <insert picture here>
1. Type the command in the command box and press enter to execute.
    - Some commands you can try are:
        - `list_inventory` to list inventory items
        - `list_finance` to list finance records
        - `add_inventory i/laptop q/2` to add 2 laptops to inventory
        - `add_finance amt/120.17` to add an outbound transaction of $120.17
        - `bye` to exit programme
    - Refer to the feature list below for details of each command.



--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>
* Words in `UPPER_CASE` are the __parameters__ to be supplied by the user.<br>
    e.g. in `add_inventory i/ITEM`, `ITEM` is a parameter which can be used as `add_inventory i/MacBook`.
* Items in square brackets are optional.<br>
  e.g `amt/AMOUNT [at/[DATE] [TIME]]` can be used as the possible formats: 
  * `amt/200 at/2020-04-10 18:00`
  * `amt/200 at/2020-04-10`
  * `amt/200 at/18:00`
  * `amt/200 at/2020-04-10`
</div>

#### Add items into inventory (add): `add_inventory`
Adds and stores items into the inventory

Format: `add_inventory i/ITEM_NAME Q/QUANTITY [c/ITEM_COST]`


### Sell items from inventory (remove): `delete_inventory`
Sells items from the inventory

Format: `delete_inventory ITEM_NUMBER`


### Edit item in inventory (remove): `edit_inventory`
Edits items in the inventory

Format: `edit_inventory ITEM_NUMBER [i/ITEM_NAME] [q/QUANTITY]`


#### List inventory records: `list_inventory`
Shows the items in the current Inventory

Format: `list_inventory`

#### List inventory records: `view_inventory_records`
Shows the movement of inventory

Format: `view_inventory_records`


#### Add finance records: `add_finance`
Adds and stores finance records into finance records

Format: `add_finance amt/AMOUNT [at/[DATE] [TIME]]`

Note: OPERATOR is either `in` or `out`


#### Delete finance records: `delete_finance`

Adds and stores finance records into finance records

Format: `delete_finance INDEX`


#### List finance records: `list_finance`

Adds and stores finance records into finance records

Format: `list_finance`


### Save the current data: `save`

Saves the current data locally into a data file

Format: `save`


#### Quit the programme: `exit`

Exits the programme and save the data locally in a data file

Format: `exit`

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
__Add inventory__ | `add_inventory i/ITEM n/NUMBER`<br> e.g. add_inventory i/MacBook n/200
__Sell inventory__ | `delete_inventory ITEM_NUMBER`<br> e.g. sell_inventory i/laptops n/100
__Edit inventory__ | `edit_inventory ITEM_NUMBER [i/ITEM_NAME] [q/QUANTITY]`<br> e.g. edit_inventory 3 i/laptops q/10
__List inventory__ | `list_inventory`
__Add finance__ | `add_finance amt/AMOUNT [at/[DATE] [TIME]]`<br> e.g. add_finance amt/420.69 at/2020-04-23
__Delete finance__ | `delete_finance INDEX`<br> e.g. delete_finance 0
__List finance__ | `list_finance`
__Save file__ | `save`
__Exit programme__ | `exit`
