---
layout: page
title: User Guide
---

SWEe! is a  **desktop app for CS2103T students to manage their learning progress mainly through flashcards**. It is optimized for CLI users so that frequent tasks can be done faster by typing in commands. If you can type fast, SWEe! can create your CS2103T flashcards faster than traditional GUI apps.


* Table of Contents
    - Quick start
    - Features
        - Adding a flashcard: `add`
        - Deleting a flashcard: `delete`
        - Editing a flashcard: `edit`
        - Favourite a flashcard : `fav`
        - Unfavourite a flashcard: `unfav`
        - Find flashcards: `find`
        - Listing all flashcards: `list`
        - Review all flashcards : `review`
        - Sort all flashcards: `sort`
        - Exiting the program: `exit`
        - View a flashcard: `view`
        - Saving the data
    - FAQ
    - Command summary
        

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `swee.jar` from [here](https://github.com/AY2021S1-CS2103T-T17-2/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for SWEe!.

1. Type the command in the command box and press Enter to execute it.<br>
   Some example commands you can try:

   * **`add q/What does OOP stand for? a/Object Oriented Programming c/General`** : Adds a flashcard with a question and answer into the General category.

   * **`delete 3`**: Deletes the 3rd flashcard in the current list.
   
   * **`edit 2 q/What is a revision control software? a/It is the software tool that automate the process of Revision Control`**: Edits the 2nd flashcard in the current list with the specified attributes.

   * **`fav 1`** : Favourite the 1st flashcard in the current list.
     
   * **`unfav 1`** : Unfavourite the 1st flashcard in the current list.
   
   * **`find general`**: Find all flashcards containing general

   * **`list`** : Lists all flashcards.

   * **`review`** : Reviews the current list of flashcards.
   
   * **`sort reviewed -d`** : Sorts the list of flashcards according to review frequency in descending order.
   
   * **`view 1`** : Views the 1st flashcard in the current list.

   * **`exit`** : Exits the app.
  

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in UPPER_CASE are the parameters to be supplied by the user.<br>
  e.g. in add `q/QUESTION`, `QUESTION` is a parameter which can be used as `add q/`What is my name?

* Words in lower_case are to be specified exactly. e.g. in `sort <success|reviewed>`, `success` and `reviewed` 
must be specified exactly.

* Items in square brackets are optional.<br>
  e.g `q/QUESTION [c/CATEGORY]` can be used as `q/What is my name? c/topic 1` or as `q/What is my name?`

* Parameters can be in any order.<br>
  e.g. if the command specifies `q/QUESTION a/ANSWER, a/ANSWER q/QUESTION` is also acceptable.
  
* Items in angle brackets are either/or options. Each option is delineated by a | . 
  e.g <success|reviewed> can be used as success or reviewed but not both.

</div>

### Adding a flashcard : `add`

Adds a flashcard.

Format: `add q/QUESTION a/ANSWER [c/CATEGORY] [r/RATING] [n/NOTE] [d/DIAGRAM]`

* `RATING` must be a number between 1 and 5 inclusive.
* `DIAGRAM` can be defined by a valid relative or absolute path.

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
If the category is not specified, the flashcard will have the `General` category.
</div>

Examples:
* `add q/What does OOP stand for? a/Object Oriented Programming`
* `add q/What does OOP stand for? a/Object Oriented Programming r/3`
* `add q/What does OOP stand for? a/Object Oriented Programming c/Super Important n/Important question!`
* `add q/What does OOP stand for? a/Object Oriented Programming d/images/diagram.png`
* `add q/What does OOP stand for? a/Object Oriented Programming c/UML n/Important question! d/images/diagram.png`


### Deleting a flashcard  : `delete`

Deletes the specified flashcard.

Format: `delete INDEX`

* Deletes the flashcard at the specified INDEX.
* The index refers to the index number shown in the displayed flashcard list.
* The index **must be a positive integer** 1, 2, 3, …

Examples:
*  `list` followed by `delete 2` deletes the 2nd flashcard in the flashcard list.

### Editing a flashcard  : `edit`

Edits a flashcard.

Format: `edit INDEX [q/QUESTION] [a/ANSWER] [c/CATEGORY] [n/NOTE] [r/RATING] [d/DIAGRAM]`

* Edits the flashcard at the specified INDEX.
* The index refers to the index number shown in the displayed flashcard list.
* The index **must be a positive integer** 1, 2, 3, …
* Although all fields are optional, a minimum of one field has to be given.
* Specifying empty values to note or rating eg. `r/` will remove the corresponding field in the flashcard.

Examples:
* `edit 3 q/What does OOP stand for? a/Object Oriented Programming c/General`
* `edit 3 q/What does OOP stand for? a/Object Oriented Programming`
* `edit 3 n/Important question! r/`

### Filtering out flashcards  : `filter`

Filters the specified flashcard based on category input(s) by user.

Format: `filter c/CATEGORY`

* Filters the specified flashcard based on category.
* Supports filtering of one or more categories. For example:
* `filter c/SDLC c/Revision History`

Examples:
*  `filter` followed by `c/SDLC` filters and lists all flashcards belonging n the SDLC category.

### Favourite a flashcard  : `fav`

Favourite the specified flashcard.

Format: `fav INDEX`

* Favourite the flashcard at the specified INDEX.
* The index refers to the index number shown in the displayed flashcard list.
* The index **must be a positive integer** 1, 2, 3, …

Examples: 
* `list` followed by `fav 2` favourite the 2nd flashcard in the displayed list.

### Unfavourite a flashcard  : `unfav`

Unfavourite the specified flashcard.

Format: `unfav INDEX`

* Unfavourite the flashcard at the specified INDEX.
* The index refers to the index number shown in the displayed flashcard list.
* The index **must be a positive integer** 1, 2, 3, …

Examples: 
* `list` followed by `unfav 2` unfavourite the 2nd flashcard in the displayed list.

### Find flashcards : `find`

Search for all flashcards matching any of the search keywords.

Format: `find KEYWORD...`
* Finds all flashcards containing any of the keywords
* The keywords are **case insensitive**
* Keywords will match as long as they are contained within any flashcard’s question/answer/category/note/tags. Eg. `UML` keyword will match a flashcard with a `category` called `UML-Diagram`

Examples: 
* `find general` 
* `find general important` 
* `find GENERAL object`


### Listing all flashcards : `list`

Shows a list of all flashcards.

Format: `list`

### Reviewing all flashcards: `review`

Review the current list of flashcards. This puts the user in review mode and the user can no 
longer input commands to the textbox.

Upon entering review mode, the following user input will be recognised:
* `↓ key` shows answer and notes of the current flashcard  
* `↑ key` hides answer and notes of the current flashcard  
* `→ arrow` key moves on to the next flashcard (if there is a next flashcard)
* `← key` moves to the previous flashcard (if there is a previous flashcard)
* `q key` quits review mode

Format: `review`

<div markdown="span" class="alert alert-primary">:memo: **Note:**
The review and success frequency of a flashcard is not affected by review mode.
</div>
    
### Sort all flashcards : `sort`

Sorts a list of all flashcards according to the criteria given.

Format: `sort <success|reviewed> <-a|-d>`

Examples: 
* sort followed by:
    - `reviewed -a` shows a list of all flashcards sorted according to review frequency in ascending order
    - `reviewed -d` shows a list of all flashcards sorted according to review frequency in descending order
    - `success -a` shows a list of all flashcards sorted according to success rate in ascending order
    - `success -d` shows a list of all flashcards sorted according to success rate in descending order

<div markdown="span" class="alert alert-primary">:memo: Note: The review and success frequency of a flashcard is only noted during quiz mode.
</div>
    
### Views a flashcard  : `view`

View the specified flashcard. A "snapshot" of the flashcard is taken and displayed in the view pane to the user.  

<div markdown="span" class="alert alert-primary">:memo: Note: If the viewed flashcard is changed (eg. edited or deleted), the view shown remains unchanged.
</div>

Format: `view INDEX [a/]`

* View the flashcard at the specified INDEX.
* The index refers to the index number shown in the displayed flashcard list.
* The index **must be a positive integer** 1, 2, 3, …
*  If `a/` is specified, the answer and notes of the flashcard will be shown too.

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

Flashcards data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add** | `add q/QUESTION a/ANSWER [c/CATEGORY] [n/NOTE] [r/RATING] [d/DIAGRAM]` <br> e.g, `add q/What does OOP stand for? a/Object Oriented Programming c/General n/Important question! d/images/diagram.jpeg`
**Delete** | `delete INDEX` <br> e.g. `delete 3`
**Edit** | `edit INDEX [q/QUESTION] [a/ANSWER] [c/CATEGORY] [n/NOTE] [r/RATING] [d/DIAGRAM]` <br> e.g. `edit 3 q/What does OOP stand for? a/Object Oriented Programming`
**Fav** | `fav INDEX` <br> e.g. `fav 1`
**Unfav** | `unfav INDEX` <br> e.g. `unfav 1`
**Find** | `find keyword...` <br>  e.g. `find general important`
**List** | `list`
**Review** | `review`
**Sort** | <code>sort <success&#124;reviewed> <-a&#124;-d></code> <br> e.g. `sort success -a`
**View** | `view INDEX` <br> e.g. `view 1`
**Exit** | `exit`
