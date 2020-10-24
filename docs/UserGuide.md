---
layout: page
title: User Guide
---

* Table of Contents
{:toc}

## Introduction
ZooKeep is a desktop app for managing animals under a zookeeper’s care, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, ZooKeep can get your management tasks done faster than traditional GUI apps.

## Legend

<div markdown="block" class="alert alert-info">

**:information_source: Provides additional notes and constraints for certain information:**<br>

</div>

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
Provides tips while performing the feature stated.
</div>

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. You can download the latest `ZooKeep.jar` from [here](https://github.com/AY2021S1-CS2103T-W15-4/tp/releases).

1. Copy `ZooKeep.jar` to the folder you want to use as the _home folder_ for the app.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command you want in the command box and press Enter to execute it, e.g. typing **`help`** and pressing Enter will open the help window.
   <br> Here are some example commands you can try:

   * **`list`** : Lists all animals.
   * **`exit`** : Exits the app.

1. You can refer to the [Features](#features) below for details of each command.

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/Hershey`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [m/MEDICAL CONDITION]` can be used as `n/Hershey m/Healthy` or as `n/Hershey`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[f/FEEDING TIME]…​` can be used as ` ` (i.e. 0 times), `f/0600`, `f/0600 f/1800` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME s/SPECIES i/ID`, `n/NAME i/ID s/SPECIES` is also acceptable.

</div>

### Viewing help : `help`

Shows a message explaining how you can access the help page.

![help message](images/helpMessage.png)

Format: `help`

---

### Adding an animal : `add`

Adds an animal under the care of the user.

Format: `add n/NAME s/SPECIES i/ID [m/MEDICAL CONDITION]… [f/FEEDING TIME]…​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
An animal can have any number of medical conditions and feeding times (including 0).
</div>

Examples:
* `add n/Hershey s/Rufous Hummingbird i/193`
* `add n/Lonesome George s/Galapagos Tortoise i/117 m/Healthy f/1200`

---

### Listing all animals : `list`

Lists all animals under the care of the user.

Format: `list`

---

### Sorting animals : `sort`

Sorts the animals by the given category in a specific order and updates the list of animals accordingly.

Format: `sort CATEGORY`

Examples:
* `sort name` sorts all animals by name in alphabetical order.
* `sort id` sorts all animals by id in ascending order.
* `sort feedtime` sorts all animals by feeding time from the earliest to the latest.

---

### Replacing an animal's fields : `replace`

Replaces the information in the fields of the animal with the specified `ID`. `ID` refers to the id number shown in the displayed animal list.

Format: `replace ID [n/NAME] [s/SPECIES] [i/ID] [m/MEDICAL CONDITION]… [f/FEEDING TIME]…​`
<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
An animal can have any number of medical conditions and feeding times (including 0).
</div>

Examples:
* `replace 1307 i/2910` replaces the ID of animal 1307 with 2910.
* `replace 1307 i/2910 n/Jirachi` replaces the ID of animal 1307 with 2910 and the name with "Jirachi".

---

### Appending information to an animal's fields : `append`

Appends information to the fields of the animal with the specified `ID`. `ID` refers to the id number shown in the displayed animal list.

Format: `append ID [m/MEDICAL CONDITION]… [f/FEEDING TIME]…​`
<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
An animal can have any number of medical conditions and feeding times (including 0).
</div>

Examples:
* `append 1307 f/1900` appends a feeding time of 1900 to the animal identified by ID 1307.
* `append 1307 m/Healthy` append a medical condition "Healthy" to the animal identified by ID 1307.

---

### Deleting an animal : `delete`

Deletes the animal with the specified `ID`. `ID` refers to the id number shown in the displayed animal list.

Format: `delete ID`

Example:
* `delete 193` deletes the individual animal with id 193.

---

### Clearing all entries : `clear`

Clears all entries from the app.

Format: `clear`

---

### Undoing a command : `undo`

Undoes the most recently used command. No changes if no previous state exists.

Format: `undo`

---

### Redoing an undo : `redo`

Redoes the previous undo. No changes if no previous state exists.

Format: `redo`

---

### Saving a snapshot of animal data : `snap`

Creates a snapshot of the current zookeep book data, saved as a file with the user specified file name.

<div markdown="block" class="alert alert-primary">

**:information_source: Constraints:**<br>

* File name can only contain alphanumeric characters, hyphens `-` and underscores `_`.

* File name must be at least 1 character long and at most 100 characters long.

* File name must not already exist in the data folder.

</div>

Format: `snap FILE_NAME`

Example:
* `snap zookeepbook_19-10-2020` saves the current state of the zookeep book data as a file named 
`zookeepbook_19-10-2020.json`, located in the data folder.

---

### Exiting the program : `exit`

Exits the program.

Format: `exit`

---

#### Manually saving data

Animal data is saved in the hard disk with the preferred file name automatically after any command that changes the data. 
By default, the preferred file name is `zookeepbook.json`
There is no need to save manually, though you can create a copy of the current data with the `snap` command. 

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous ZooKeep home folder.

## Command summary

Action | Format, Examples
--------|------------------
**Help** | `help`
**Add** | `add n/NAME s/SPECIES i/ID [m/MEDICAL CONDITION]…​[f/FEEDING TIME]…` <br> e.g. `add n/Lonesome George s/Galapagos Tortoise i/117 m/Healthy f/1200`
**List** | `list`
**Sort** | `sort fieldname` <br> e.g. `sort name` 
**Replace** | `replace ID [n/NAME] [s/SPECIES] [i/ID] [m/MEDICAL CONDITION]… [f/FEEDING TIME]…` 
**Append** | `append ID [m/MEDICAL CONDITION]… [f/FEEDING TIME]…`
**Delete** | `delete ID` <br> e.g. `delete 193` 
**Clear** | `clear`
**Undo** | `undo`
**Redo** | `redo`
**Snap** | `snap FILE_NAME` <br> e.g. `snap zookeepbook_19-10-2020`
**Exit** | `exit`

