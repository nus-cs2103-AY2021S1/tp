---
layout: page
title: User Guide
---
<pre>
                                    *Brings food over*
            
             ----------------  /'--.._ `'-="""=-'` _..--'\
             | my lunch? :D |  |   ~. )  _     _  ( .~   |
             ----------------   \  '~/   a  _  a   \~'  /
                             \   \  `|     / \     |`  /
                              \___`'--\    \_/    /--'`
                                      .'._  J__.-'.
                                      / /  '-/_ `-  \
                                     / -"-'-.  '-.__/
                                     \__,-.\/     | `\
                                     /  ;---.  .--'   |
                                    |     /\'-'      /
                                    '.___.\   _.--;'`)
                                    
            
            Welcome to ZooKeep! We know your zookeeping duties are hard,
            so we're here to make your life easier with some automation!
            
            
            ASCII art by Joan G. Stark.
</pre>

If this is your first time using `ZooKeep`, this guide will serve to provide a reference for you to get familiar with the application.

<div class="toc-no-bullet-points">
<!-- Referenced from https://github.com/AY2021S1-CS2103T-W16-3/tp/pull/179/commits/aec461182c194c9ca2c67d7c407fcabb376191ff -->
  * Table of Contents
  {:toc}
</div>

## 1. Introduction
This section gives you a quick overview about ZooKeep.

`ZooKeep` is a desktop app for managing animals under a zookeeper’s care, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, `ZooKeep` can get your management tasks done faster than traditional GUI apps.

## 2. Legend
The following are symbols you should look out for when using this guide.

<div markdown="span" class="alert alert-info">

**:information_source: Provides additional information.**

</div>

<div markdown="span" class="alert alert-primary">

**:bulb: Provides tips while performing the feature stated.**

</div>

<div markdown="span" class="alert alert-warning">

**:exclamation: Provides input constraints for the command.**

</div>

## 3. Quick start
You can follow the steps below to get started with ZooKeep.

1. Ensure you have Java `11` or above installed in your Computer.

1. You can download the latest `ZooKeep.jar` from [here](https://github.com/AY2021S1-CS2103T-W15-4/tp/releases).

1. Copy `ZooKeep.jar` to the folder you want to use as the _home folder_ for the app.

1. Double-click the file to start the app. The GUI similar to *Figure 1* below should appear in a few seconds. Note how the app contains some sample data.<br>

    <p align="center"><img src="images/Ui.png"/></p>

    <p align="center"><i>Figure 1: GUI shown upon starting ZooKeep</i></p>

1. Type the command you want in the command box and press Enter to execute it, e.g. typing **`help`** and pressing Enter will open the help window.
   <br> Here are some example commands you can try:

   * **`list`** : Lists all animals.
   * **`exit`** : Exits the app.

1. You can refer to the features below for the details of each command. For the convenience of new users, the [Basic features](#basic-features) are listed first, followed by additional [Advanced features](#advanced-features) which may be useful for the user.

## 4. Basic features
This section provides you with the most basic features to get you started with ZooKeep.

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters that you will supply when entering the command.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/Hershey`.

* You can choose not to include the fields in square brackets.<br>
  e.g `n/NAME [m/MEDICAL_CONDITION]` can be used as `n/Hershey m/Flu` or as `n/Hershey`.

* Fields with `…`​ after them indicate that you can enter them multiple times or omit them if unneeded.<br>
  e.g. `[f/FEED_TIME]…​` can be used as ` ` (i.e. 0 times), `f/0600`, `f/0600 f/1800` etc.

* You can enter the parameters in any order you like.<br>
  e.g. if the command specifies `n/NAME s/SPECIES i/ID`, `n/NAME i/ID s/SPECIES` is also acceptable.

</div>

### 4.1. Viewing help: `help`

If you ever need any help on how to use `ZooKeep` or want to see what features it supports, type `help` into the
input box, and `ZooKeep` will show a message as shown in *Figure 2* below explaining how you can access the help page.

<p align="center"><img src="images/helpMessage.png"/></p>

<p align="center"><i>Figure 2: Help message shown upon entering command</i></p>

Format: `help`

Expected Outcome: 
```
Opened help window.
```
A message is displayed containing a link to this User Guide.

---

### 4.2. Exiting the program: `exit`

After you finish using `ZooKeep`, you can use this command to close it.

Format: `exit`

Expected Outcome:

Your `ZooKeep` application closes.

---

### 4.3. Listing all animals: `list`

This command is used when you need to display all the animals stored in your `ZooKeep` book, such
as when you have finished filtering animals using the `find` command.

Format: `list`

Expected Outcome:
```
Listed all animals
```
All animals in your `ZooKeep` book are displayed.

---

### 4.4. Clearing all entries: `clear`

You can use this to clear all the entries of your `ZooKeep` book and start the application with a clean slate.

Format: `clear`

Expected Outcome:
```
ZooKeepBook has been cleared!
```
All animals in your `ZooKeep` book are cleared.

---

### 4.5. Adding an animal: `add`

When you get new animals assigned to you, you can use `add` to make new entries for them to keep track of any
important information. Compulsory entries you need to add are the animals' names, species and ID number.
You can choose to add optional fields like medical conditions and feed times if necessary. 

Format: `add n/NAME s/SPECIES i/ID [m/MEDICAL_CONDITION]… [f/FEED_TIME]…​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
An animal can have any number of medical conditions and feed times (including 0).
</div>

<div markdown="block" class="alert alert-warning">
**:exclamation: Constraints:**<br>

* If you enter an `ID` with leading zeroes, those zeroes will be trimmed during command execution.

* `ID` should be a number from 100 to 999999, inclusive.

* `ID` of animal to add must not already exist in your `ZooKeep` book.

* `FEED_TIME` must be a valid time in 24 hour format *(HHmm)*.
</div>

Examples:
* `add n/Hershey s/Rufous Hummingbird i/193`
* `add n/Lonesome George s/Galapagos Tortoise i/117 m/Flu f/1200`

Example Usage:
```
add n/Kai Kai s/Giant Panda i/200 m/Sunstroke f/1230 f/1400
```

<p align="center"><img src="images/user-guide/addinganimal.png"/></p>

<p align="center"><i>Figure 3: User entering add command</i></p>

Expected Outcome:
```
New animal added
Name: Kai Kai ID: 200 Species: Giant Panda Medical conditions: [Sunstroke] Feed times: [1230][1400]
```

<p align="center"><img src="images/user-guide/addedanimal.png"/></p>

<p align="center"><i>Figure 4: User executing add command</i></p>

As illustrated in *Figure 4* above, an animal with the name `Kai Kai`, ID `200`, species `Giant Panda`, medical condition `Sunstroke`
and feed times of `1230 hrs` and `1400 hrs` is added to your `ZooKeep` book.

---

### 4.6. Deleting an animal: `delete`

When a particular animal in your `ZooKeep` book is no longer under your care, you can use this command to 
delete that animal by entering its `ID`. `ID` refers to the id number shown in the displayed animal list.

Format: `delete ID`

<div markdown="block" class="alert alert-warning">
**:exclamation: Constraints:**<br>

* If you enter an `ID` with leading zeroes, those zeroes will be trimmed during command execution.

* `ID` should be a number from 100 to 999999, inclusive.

* `ID` of animal to delete must exist in your `ZooKeep` book.
</div>

Example Usage:
```
delete 200
```

<p align="center"><img src="images/user-guide/deletinganimal.png"/></p>

<p align="center"><i>Figure 5: User entering delete command</i></p>

Expected Outcome:
```
Deleted Animal
Name: Kai Kai ID: 200 Species: Giant Panda Medical conditions: [Sunstroke] Feed times: [1230][1400]
```

<p align="center"><img src="images/user-guide/deletedanimal.png"/></p>

<p align="center"><i>Figure 6: User executing delete command</i></p>

The animal with ID `200` is deleted from your `ZooKeep` book, as shown in *Figure 6* above.

---

### 4.7. Undoing a command: `undo`

When you make a mistake in your `ZooKeep` book, such as an accidental `delete`, you can perform this operation which
undoes the most recently used command. No changes will be made if no previous state exists, meaning that you cannot
undo if you just started up the application.

Format: `undo`

Example usage:
Refer to the scenario in the `delete` section above as illustrated in *Figure 5 & 6*, where you just deleted
Kai Kai from your `ZooKeep` book. Now you decide to use undo to bring Kai Kai back into your book by typing `undo`
as seen in *Figure 7* below:

<p align="center"><img src="images/user-guide/undoingcommand.png"/></p>

<p align="center"><i>Figure 7: User entering undo command</i></p>

Expected Outcome:
```
Undo successful
```

<p align="center"><img src="images/user-guide/undonecommand.png"/></p>

<p align="center"><i>Figure 8: User executing undo command</i></p>

The previous command is undone, and as seen in *Figure 8* above, Kai Kai is now back in your `ZooKeep` book!

---

### 4.8. Redoing an undo: `redo`

You can use this command to quickly redo a command that was just undone by undo. For example, after you `undo` an
accidental `delete`, you can use `redo` to perform the delete again. You can only use redo if `undo`
was used before and no edit to your `ZooKeep` book was made in between, otherwise this command will do nothing.

Format: `redo`

Example usage:
Refer to *Figures 7 & 8* above where you just executed the undo command. You decide to delete Kai Kai from your
`ZooKeep` book after all, and type redo into the interface as shown in *Figure 9*:

<p align="center"><img src="images/user-guide/redoingcommand.png"/></p>

<p align="center"><i>Figure 9: User entering redo command</i></p>

Expected Outcome:
```
Redo successful
```

<p align="center"><img src="images/user-guide/redidcommand.png"/></p>

<p align="center"><i>Figure 10: User executing redo command</i></p>

The undone command is redone. It will show Kai Kai deleted from your `ZooKeep` book as seen in *Figure 10*.

---

## 5. Advanced features

When you are comfortable with the basic features, you can try the follow advanced features to achieve more.

### 5.1. Appending information to an animal's fields: `append`

If you forgot to add the medical conditions and/or feed times for a particular animal, you can use this feature to
append the missing information to the respective fields of the animal with the specified `ID` instead of deleting and adding
that same animal again. `ID` here refers to the id number shown in the displayed animal list.

Format: `append ID [m/MEDICAL_CONDITION]… [f/FEED_TIME]…​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
An animal can have any number of medical conditions and feed times (including 0).
</div>

<div markdown="block" class="alert alert-warning">
**:exclamation: Constraints:**<br>

* At least one medical condition or feed time must be specified.

* If you enter an `ID` with leading zeroes, those zeroes will be trimmed during command execution.

* `ID` should be a number from 100 to 999999, inclusive.

* `ID` of animal must exist in your `ZooKeep` book.

* `FEED_TIME` must be a valid time in 24 hour format *(HHmm)*.
</div>

Examples:
* `append 200 f/1900`
* `append 200 m/Flu`

Example Usage:
```
append 200 m/Flu
```

<p align="center"><img src="images/user-guide/appendingmedical.png"/></p>

<p align="center"><i>Figure 11: User entering append command</i></p>

Expected Outcome:
```
Appended Animal Details
Name: Kai Kai ID: 200 Species: Giant Panda Medical conditions: [Sunstroke][Flu] Feed times: [1230][1400]
```

<p align="center"><img src="images/user-guide/appendedmedical.png"/></p>

<p align="center"><i>Figure 12: User executing append command</i></p>

The medical condition "Flu" is appended to the medical conditions of the animal with `ID` 200. *Figure 12* above
reflects what you will see in your `ZooKeep` book.

---

### 5.2. Replacing an animal's fields: `replace`

If you made a mistake while entering the information of an animal in your `ZooKeep` book, instead of deleting the animal
and entering all the information again, you can use this command to replace only the incorrect fields
of the animal after specifying its `ID`. `ID` refers to the id number shown in the displayed animal list. Note that
if this command is used on an animal's `MEDICAL_CONDITION`s or `FEED_TIME`s, all of its previous 
`MEDICAL_CONDITION`s or `FEED_TIME`s will be replaced.

Format: `replace ID [n/NAME] [s/SPECIES] [i/ID] [m/MEDICAL_CONDITION]… [f/FEED_TIME]…​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
An animal can have any number of medical conditions and feed times (including 0).
</div>

<div markdown="block" class="alert alert-warning">
**:exclamation: Constraints:**<br>

* At least one field to replace must be specified.

* If you enter an `ID` with leading zeroes, those zeroes will be trimmed during command execution.

* `ID` should be a number from 100 to 999999, inclusive.

* `ID` of animal must exist in your `ZooKeep` book.

* `FEED_TIME` must be a valid time in 24 hour format *(HHmm)*.
</div>

Examples:
* `replace 200 i/2910`
* `replace 200 i/2910 n/Jirachi`

Example Usage:
```
replace 200 i/2910 n/Jirachi
```

<p align="center"><img src="images/user-guide/replacingfields.png"/></p>

<p align="center"><i>Figure 13: User entering replace command</i></p>

Expected Outcome:
```
Replaced Animal Details
Name: Jirachi ID: 2910 Species: Giant Panda Medical conditions: [Sunstroke][Flu] Feed times: [1230][1400]
```

<p align="center"><img src="images/user-guide/replacedfields.png"/></p>

<p align="center"><i>Figure 14: User executing replace command</i></p>

*Figure 14* above shows the animal with ID `200` having its ID replaced with `2910` and its name 
replaced with `Jirachi`.

---

### 5.3. Finding animals by their fields: `find`

You may find yourself in situations where you need to retrieve a specific group of animals from your `ZooKeep` book. For
example, you may need to find animals with the same feed times or similar medical conditions.
You can use this command to find and list all animals in your `ZooKeep` book whose fields contain any of the specified 
argument keywords (at least 1).

Format: `find KEYWORD [MORE KEYWORDS]…`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
Keyword matching is case insensitive.
</div>

Examples:
* `find Ahmeng Buttercup Coco` finds all animals with the fields (name) containing any of the specified keywords.
* `find 1200` finds all animals with the field (id or feed time) containing the specified keyword.

Example Usage:
```
find Kai Kai
```

<p align="center"><img src="images/user-guide/findinganimal.png"/></p>

<p align="center"><i>Figure 15: User entering find command</i></p>

Example Outcome:
```
1 animals listed!
```

<p align="center"><img src="images/user-guide/foundanimal.png"/></p>

<p align="center"><i>Figure 16: User executing find command</i></p>

The animal with name `Kai Kai` is displayed as seen in *Figure 16* above.

---

### 5.4. Sorting animals: `sort`

If you need to create some sort of ordering in your `ZooKeep` book, such as a chronological order by feed times to organise
your feeding schedule, this feature helps to sort the animals by the given category and updates the list of animals accordingly.

Format: `sort CATEGORY`

These are the category options you can sort the animals by:

* `name`: Sorts all animals by their name in alphabetical order (case-insensitive).
* `id`: Sorts all animals by their id in ascending order.
* `feedtime`: Sorts all animals by their earliest feed time in chronological order.
* `medical`: Sorts all animals by their number of medical conditions in ascending order.

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
The category input is case insensitive.
</div>

<div markdown="block" class="alert alert-warning">
**:exclamation: Constraints:**<br>

* The `CATEGORY` must be a valid sorting category as specified above.

* Only one sorting category can be specified at a time.

</div>

Example:
* `sort name`
* `sort id`

Example Usage:
```
sort id
```

<p align="center"><img src="images/user-guide/sortinglist.png"/></p>

<p align="center"><i>Figure 17: User entering sort command</i></p>

Example Outcome:
```
Sorted all animals by id
```

<p align="center"><img src="images/user-guide/sortedlist.png"/></p>

<p align="center"><i>Figure 18: User executing sort command</i></p>

The animals in your `ZooKeep` book are sorted by their ID, in ascending order. *Figure 18* above should correspond
with what you see.

---

### 5.5. Saving a snapshot of animal data: `snap`

This command is useful for storing important archives in the `data/snapshots` folder of your `ZooKeep` application, in case you need
to refer to the information of animals that have been deleted long ago. When executed, this command will
create a snapshot of the current `ZooKeep` book data, saved as a file with the user specified file name.

Please refer to the [FAQ](#faq) to find out how to load a snapshot that you have saved.

Format: `snap FILE_NAME`

<div markdown="block" class="alert alert-warning">
**:exclamation: Constraints:**<br>

* `FILE_NAME` can only contain alphanumeric characters, hyphens `-` and underscores `_`.

* `FILE_NAME` must be at least 1 character long and at most 100 characters long.

* `FILE_NAME` must not already exist in the `data/snapshots` folder.
</div>

Example:
* `snap zookeepbook_19-10-2020` 
* `snap archive_1`

Example Usage:
```
snap zookeepbook_19-10-2020
```

<p align="center"><img src="images/user-guide/beforesnap.png"/></p>

<p align="center"><i>Figure 19: User entering snap command</i></p>

Expected Outcome:
```
Current ZooKeepBook saved as zookeepbook_19-10-2020.json
```

<p align="center"><img src="images/user-guide/aftersnap.png"/></p>

<p align="center"><i>Figure 20: User executing snap command</i></p>

<p align="center"><img src="images/user-guide/snapresult.png"/></p>

<p align="center"><i>Figure 21: The directory where the archived file is located</i></p>

A file named `zookeepbook_19-10-2020.json` is created in the `data/snapshots` directory as seen in *Figure 21*.
This file contains the state of your `ZooKeep` book data at the point in time when the command
was executed.

---

## 6. FAQ
This section lists some of the frequently asked questions.

**Q**: How do I transfer my data to a new computer?<br>
**A**: First, install the app on your new computer. Next, copy over the `zookeepbook.json` file
       from your old computer and paste it into the `data` folder of your new computer.

**Q**: How can I manually save new data that I enter into the application?<br>
**A**: You do not need to manually save data as animal data is saved in the hard disk 
       automatically as a file named `zookeepbook.json` after any command that changes the data. 
       Please do not manually edit this file as it may result in loss of data or unexpected 
       behaviour when running the application.

**Q**: How can I load a snapshot that I created using the `snap` command?<br>
**A**: First, go to your `data` folder and rename your `zookeepbook.json` file into something else,
       such as `zookeepbook_current.json`. Next, copy the snapshot you wish to load, paste it into
       the `data` folder and rename this snapshot to `zookeepbook.json`. The next time you launch
       `ZooKeep`, your snapshot will be loaded.

**Q**: Can I record the feed times of a specific animal in any order I prefer?<br>
**A**: The feed time will be arranged in chronological order regardless of the order entered for easier reference.

**Q**: Can I search for animals based on a certain alphabet or half specified keywords?<br>
**A**: The find feature will only list animals with the exact specified keyword provided.

## 7. Command summary
If you need a quick reference guide, the following tables should help.

**Basic commands**

Action | Format, Examples
--------|------------------
**Help** | `help`
**Exit** | `exit`
**List** | `list`
**Clear** | `clear`
**Add** | `add n/NAME s/SPECIES i/ID [m/MEDICAL_CONDITION]… [f/FEED_TIME]…` <br> e.g. `add n/Lonesome George s/Galapagos Tortoise i/117 m/Flu f/1200`
**Delete** | `delete ID` <br> e.g. `delete 193`
**Undo** | `undo`
**Redo** | `redo`

**Advanced commands**

Action | Format, Examples
--------|------------------
**Append** | `append ID [m/MEDICAL_CONDITION]… [f/FEED_TIME]…` <br> e.g. `append 1307 f/1900`
**Replace** | `replace ID [n/NAME] [s/SPECIES] [i/ID] [m/MEDICAL_CONDITION]… [f/FEED_TIME]…` <br> e.g. `replace 1307 i/2910 n/Jirachi`
**Find** | `find KEYWORD [MORE KEYWORDS]…` <br> e.g. `find Ahmeng Buttercup Coco`
**Sort** | `sort CATEGORY` <br> e.g. `sort name` 
**Snap** | `snap FILE_NAME` <br> e.g. `snap zookeepbook_19-10-2020`
