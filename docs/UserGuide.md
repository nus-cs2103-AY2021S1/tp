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
        - Listing all flashcards: `list`
        - Review all flashcards : `review`
        - Exiting the program: `exit`
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

   * **`add`**`add q/What does OOP stand for? a/Object Oriented Programming c/General` : Adds a flashcard with a question and answer into the General category.

   * **`delete`**: Deletes the 3rd flashcard in the current list.

   * **`list`** : Lists all flashcards.

   * **`review`** : Reviews a list of all flashcards.

   * **`exit`** : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in UPPER_CASE are the parameters to be supplied by the user.<br>
  e.g. in add `q/QUESTION`, `QUESTION` is a parameter which can be used as `add q/`What is my name?

* Items in square brackets are optional.<br>
  e.g `q/QUESTION [c/CATEGORY]` can be used as `q/What is my name? c/topic 1` or as `q/What is my name?`

* Parameters can be in any order.<br>
  e.g. if the command specifies `q/QUESTION a/ANSWER, a/ANSWER q/QUESTION` is also acceptable.

</div>

### Adding a flashcard : `add`

Adds a flashcard.

Format: `add q/QUESTION a/ANSWER [c/CATEGORY]`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
If the category does not exist, it will be created.
</div>

Examples:
* `add q/What does OOP stand for? a/Object Oriented Programming c/General`
* `add q/What does OOP stand for? a/Object Oriented Programming`

### Deleting a flashcard  : `delete`

Deletes the specified flashcard.

Format: `delete INDEX`

* Deletes the flashcard at the specified INDEX.
* The index refers to the index number shown in the displayed flashcard list.
* The index **must be a positive integer** 1, 2, 3, …

Examples:
*  `list` followed by `delete 2` deletes the 2nd flashcard in the flashcard list.

### Filtering out flashcards  : `filter`

Filters the specified flashcard based on category input(s) by user.

Format: `filter c/CATEGORY`

* Filters the specified flashcard based on category.
* Supports filtering of one or more categories. For example:
* `filter c/SDLC c/Revision History`

Examples:
*  `filter` followed by `c/SDLC` filters and lists all flashcards belonging n the SDLC category.

### Listing all flashcards : `list`

Shows a list of all flashcards.

Format: `list`

### Reviewing all flashcards: `review`

Reviews a list of all flashcards.

Format: `review`

Examples:
* review followed by:
    - `[down key]` shows answer to the current flashcard
    - `[up key]` hides answer to the current flashcard
    - `[right key]` skips the current flashcard and moves on to the next flashcard
    - `[left key]` returns the previous flashcard
    - `q` quits review function

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

Flashcards data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add** | `add q/QUESTION a/ANSWER [c/CATEGORY]` <br> e.g., `add q/What does OOP stand for? a/Object Oriented Programming c/General`
**Delete** | `delete INDEX` <br> e.g., `delete 3`
**List** | `list`
**Review** | `review`
**Exit** | `exit`
