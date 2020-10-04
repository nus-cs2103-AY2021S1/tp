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
        - `add_inventory i/laptop n/2` to add 2 laptops to inventory
        - `add_finance op/out amt/1.17` to add an outbound transaction of $1.17
        - `bye` to exit programme
    - Refer to the feature list below for details of each command.



--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>
* Words in `UPPER_CASE` are the __parameters__ to be supplied by the user.<br>
    e.g. in `add_inventory i/ITEM`, `ITEM` is a parameter which can be used as `add_inventory i/MacBook`.

</div>

### Add items into inventory (add): `add_inventory`
Adds and stores items into the inventory

Format: `add_inventory i/ITEM n/NUMBER`


### Sell items from inventory (remove): `sell_inventory`
Sells items from the inventory

Format: `sell_inventory i/ITEM n/NUMBER`


### List inventory records: `list_inventory`
Shows the items in the current Inventory

Format: `list_inventory`

### List inventory records: `view_inventory_records`
Shows the movement of inventory

Format: `view_inventory_records`


### Add finance records: `add_finance`
Adds and stores finance records into finance records

Format: `add_finance op/OPERATOR amt/AMOUNT`

Note: OPERATOR is either `in` or `out`


### Delete finance records: `delete_finance`

Adds and stores finance records into finance records

Format: `delete_finance i/INDEX`


### List finance records: `list_finance`

Adds and stores finance records into finance records

Format: `list_finance`


### Save the current data: `save`

Saves the current data locally into a data file

Format: `save`


### Quit the programme: `exit`

Exits the programme and save the data locally in a data file

Format: `exit`

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
__Add inventory__ | `add_inventory i/ITEM n/NUMBER`<br> e.g. add_inventory i/MacBook n/200
__Sell inventory__ | `sell_inventory i/ITEM n/NUMBER`<br> e.g. sell_inventory i/laptops n/100
__List inventory__ | `list_inventory`
__Add finance__ | `add_finance op/OPERATOR amt/AMOUNT`<br> e.g. add_finance op/in amt/420.69
__Delete finance__ | `delete_finance i/INDEX`<br> e.g. delete_finance i/0
__List finance__ | `list_finance`
__Save file__ | `save`
__Exit programme__ | `exit`
