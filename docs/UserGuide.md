---
layout: page
title: User Guide
---

SWEe! is a  **desktop app for CS2103T students to manage their learning progress mainly through flashcards**. It is optimized for CLI users so that frequent tasks can be done faster by typing in commands. If you can type fast, SWEe! can create your CS2103T flashcards faster than traditional GUI apps.


* Table of Contents
    - Quick start
    - Features
        - Adding a flashcard: `add`
        - Clear all flashcards: `clear`
        - Deleting a flashcard: `delete`
        - Editing a flashcard: `edit`
        - Favourite a flashcard : `fav`
        - Unfavourite a flashcard: `unfav`
        - Find flashcards: `find`
        - Viewing help: `help`
        - Listing all flashcards: `list`
        - Review all flashcards : `review`
        - Quiz flashcards: `quiz`
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

   * **`filter c/OOP`** : Filters out flashcard(s) belonging to the OOP category. 
   
   * **`fav 1`** : Favourite the 1st flashcard in the current list.
     
   * **`unfav 1`** : Unfavourite the 1st flashcard in the current list.
   
   * **`find general`**: Find all flashcards containing general.

   * **`list`** : Lists all flashcards.

   * **`review`** : Reviews the current list of flashcards.
   
   * **`quiz`** : Quiz the current list of flashcards.
   
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
  
* Items in angle brackets `<>` are either/or options. Each option is delineated by a `|` . 
  e.g `<success|reviewed>` can be used as `success` or `reviewed` but not both.

</div>

### Adding a flashcard : `add`

Adds a flashcard.

Format: `add q/QUESTION a/ANSWER [c/CATEGORY] [r/RATING] [n/NOTE] [d/DIAGRAM]`

* `RATING` must be a number between 1 and 5 inclusive.
* `DIAGRAM` can be defined by a valid relative or absolute path.

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
If the category is not specified, the flashcard will have the <b>General</b> category.
</div>

Examples:
* `add q/What does OOP stand for? a/Object Oriented Programming`
* `add q/What does OOP stand for? a/Object Oriented Programming r/3`
* `add q/What does OOP stand for? a/Object Oriented Programming c/Super Important n/Important question!`
* `add q/What does OOP stand for? a/Object Oriented Programming d/images/diagram.png`
* `add q/What does OOP stand for? a/Object Oriented Programming c/UML n/Important question! d/images/diagram.png`

### Clearing all flashcards : `clear` 

Clears all flashcard data from the program.

Format: `clear`


### Deleting a flashcard  : `delete`

Deletes the specified flashcard.

Format: `delete INDEX`

* Deletes the flashcard at the specified INDEX.
* The index refers to the index number shown in the displayed flashcard list.
* The index **must be a positive integer greater than 0** 1, 2, 3, …

Examples:
*  `list` followed by `delete 2` deletes the 2nd flashcard in the flashcard list.

### Editing a flashcard  : `edit`

Edits a flashcard.

Format: `edit INDEX [q/QUESTION] [a/ANSWER] [c/CATEGORY] [n/NOTE] [r/RATING] [d/DIAGRAM]`

* Edits the flashcard at the specified INDEX.
* The index refers to the index number shown in the displayed flashcard list.
* The index **must be a positive integer greater than 0** 1, 2, 3, …
* Although all fields are optional, a minimum of one field has to be given.
* Specifying empty values to note, rating, tag or diagram eg. `r/` will remove the corresponding field in the flashcard.
* Although question, answer and category are optional values, you are not allowed to specify an empty value to those attributes once the prefix is declared e.g. `c/` is not allowed and will not remove category

Examples:
* `edit 3 q/What does OOP stand for? a/Object Oriented Programming c/General`
* `edit 3 q/What does OOP stand for? a/Object Oriented Programming`
* `edit 3 n/Important question! r/`

### Filtering out flashcards  : `filter`

Filters for specific flashcard(s) based on the field input(s) by the user. 
This will return all the flashcards whose fields match all the fields specified by the user.

Format: `filter [c/CATEGORY] [r/RATING] [f/<yes|no>] [t/TAG]...`

* Filters the specified flashcard based on category, rating, favourite status or tags.
* Supports filtering of one or more different fields. For example:
    - `filter c/SDLC r/5` will filter out flashcards belonging to the SDLC category with a rating of 5.
* Although all fields are optional, a minimum of one field has to be given.

Examples:
*  `filter c/SDLC` filters and lists all flashcards belonging n the SDLC category.
*  `filter t/examinable t/study` filters and lists all flashcards that have both “examinable” and “study”.
*  `filter r/3 f/yes` filters and lists all favourited flashcards that have a rating of 3.


### Favourite a flashcard  : `fav`

Favourite the specified flashcard.

Format: `fav INDEX`

* Favourite the flashcard at the specified INDEX.
* The index refers to the index number shown in the displayed flashcard list.
* The index **must be a positive integer greater than 0** 1, 2, 3, …

Examples: 
* `list` followed by `fav 2` favourite the 2nd flashcard in the displayed list.

### Unfavourite a flashcard  : `unfav`

Unfavourite the specified flashcard.

Format: `unfav INDEX`

* Unfavourite the flashcard at the specified INDEX.
* The index refers to the index number shown in the displayed flashcard list.
* The index **must be a positive integer greater than 0** 1, 2, 3, …

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

### Viewing help : `help`

Opens a window with a link that directs you to our user guide.

Format: `help`

### Listing all flashcards : `list`

Shows a list of all flashcards. This is useful for removing any `filter` or `find` you have done on the flashcard list.

Format: `list`

* All additional input after the command word `list` will be ignored. E.g. `list` and `list 123` will have the same effect.

### Reviewing all flashcards: `review`

Reviews the current list of flashcards. This puts the user in review mode and the user can no 
longer input commands to the textbox.

Upon entering review mode, the following user input will be recognised:
* `↓ key` shows answer and notes of the current flashcard  
* `↑ key` hides answer and notes of the current flashcard  
* `→ key` moves on to the next flashcard (if there is a next flashcard)
* `← key` moves to the previous flashcard (if there is a previous flashcard)
* `q` quits review mode

Format: `review`

<div markdown="span" class="alert alert-primary">:memo: Note:
The review and success frequency of a flashcard is not affected by review mode.
</div>

### Quizzing flashcards: `quiz`

Quiz the current list of flashcards. This puts the user in quiz mode and the user can no longer input commands to the textbox.

Upon entering quiz mode, the following user input will be recognised:
* `↓ key` shows answer and notes of the current flashcard  
* `q` quits quiz mode
* `y` This input will only be recognised after the `↓ key` is pressed. `y` is a feedback to indicate a correct answer. 
* `n` This input will only be recognised after the `↓ key` is pressed. `n` is a feedback to indicate an incorrect answer. 

Upon pressing the `↓ key`, the user will be prompted if they got the answer correct. The user can then press 
`y` to feedback that they got the correct answer or `n` to feedback that they got an incorrect answer.  
Once you give a feedback (pressing either `y` or `n`), the review and success frequency for that flashcard will be updated even if you
quit the quiz prematurely.

Format: `quiz`

<div markdown="span" class="alert alert-primary">:memo: Note: Once the user presses `y` or `n`, the review and success frequency of the flashcard is updated accordingly.
</div>

### Sort all flashcards : `sort`

Sorts a list of all flashcards according to the criteria given.

Format: `sort <success|reviewed> <-a|-d>`

Examples: 
* `sort reviewed -a` shows a list of all flashcards sorted according to review frequency in ascending order
* `sort reviewed -d` shows a list of all flashcards sorted according to review frequency in descending order
* `sort success -a` shows a list of all flashcards sorted according to success rate in ascending order
* `sort success -d` shows a list of all flashcards sorted according to success rate in descending order

<div markdown="span" class="alert alert-primary">:memo: Note: The review and success frequencies of a flashcard are only affected by quiz mode.
</div>
    
### Views a flashcard  : `view`

View the specified flashcard. A "snapshot" of the flashcard is taken and displayed in the view pane to the user.

Format: `view INDEX [-a]`

* View the flashcard at the specified INDEX.
* The index refers to the index number shown in the displayed flashcard list.
* The index **must be a positive integer greater than 0** 1, 2, 3, …
*  If `-a` is specified, the answer and notes of the flashcard will be shown too.

<div markdown="span" class="alert alert-primary">:memo: Note: Once another command is executed, the view pane will be returned to a blank state even if the shown
flashcard was not modified/deleted.
</div>

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
**Clear** | `clear`
**Delete** | `delete INDEX` <br> e.g. `delete 3`
**Edit** | `edit INDEX [q/QUESTION] [a/ANSWER] [c/CATEGORY] [n/NOTE] [r/RATING] [d/DIAGRAM]` <br> e.g. `edit 3 q/What does OOP stand for? a/Object Oriented Programming`
**Filter** | `filter [c/CATEGORY] [r/RATING] [f/FAVOURITE] [t/TAG]...` <br> e.g. `filter t/examinable r/3`
**Fav** | `fav INDEX` <br> e.g. `fav 1`
**Unfav** | `unfav INDEX` <br> e.g. `unfav 1`
**Find** | `find KEYWORD...` <br>  e.g. `find general important`
**Help** | `help`
**List** | `list`
**Review** | `review`
**Quiz** | `quiz`
**Sort** | <code>sort <success&#124;reviewed> <-a&#124;-d></code> <br> e.g. `sort success -a`
**View** | `view INDEX [-a]` <br> e.g. `view 1`
**Exit** | `exit`
