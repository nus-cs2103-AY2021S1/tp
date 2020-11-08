---
layout: page
title: User Guide
---

DSAce is a **desktop app for creating flashcards and attempting quiz questions for CS2040s, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, DSAce can get your revision tasks done faster than traditional GUI apps.

## Table of Contents
* [Quick start](#quick-start)
* [Features](#features)
   * [**`help`** : Viewing help.](#viewing-help--help)
   * [**`add`** : Adding a flashcard.](#adding-a-flashcard--add)
   * [**`list`** : Listing all flashcards.](#listing-all-flashcards--list)
   * [**`sort`** : Sorting all flashcards.](#sorting-all-flashcards--sort)
   * [**`edit`** : Editing a flashcard.](#editing-a-flashcard--edit)
   * [**`find`** : Locating flashcards by name/tag/priority.](#locating-flashcards-by-nametagpriority-find)
   * [**`delete`** : Deleting a flashcard.](#deleting-a-flashcard--delete)
   * [**`flip`** : Flipping a flashcard.](#flipping-a-flashcard--flip)
   * [**`clear`** : Clearing all flashcards.](#clearing-all-entries--clear)
   * [**`enter quiz`** : Entering Quiz mode.](#entering-quiz-mode--enter-quiz)
   * [**`leave quiz`** : Leaving Quiz mode.](#leaving-quiz-mode--leave-quiz)
   * [**`exit`** : Exiting the program.](#exiting-the-program--exit)
   * [**`performance`** : Opening performance interface.](#checking-performance--performance)
   * [**`view`** : Viewing previous an attempt result.](#viewing-a-specific-historical-attempt--view)
   * [**`list`** : List historical attempts.](#listing-historical-attempts-results--list)
   * [Saving the data.](#saving-the-data)
* [FAQ](#faq)
* [Command Summary](#command-summary)


--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `dsace.jar` from [here](https://github.com/AY2021S1-CS2103-T14-2/tp/releases).

3. Copy the file to the folder you want to use as the _home folder_ for your DSAce.

4. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

5. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`list`** : Lists all flashcards.

   * **`add`**`n/Insertion Sort d/Worse case: O(n^2)` : Adds a flashcard named `Insertion Sort` to the DSAce folder.

   * **`edit`**`1 n/BubbleSort d/Average case: O(n^2)` : Edits the name and definition of the 1st flashcard in
    current list to be `BubbleSort` and `Average case: O(n^2)` respectively.

   * **`sort`**`desc` : Sorts all flashcards by priority in descending order.

   * **`find`**`n/Heap` : Finds flashcards with names containing the keyword `Heap`

   * **`delete`**`3` : Deletes the 3rd flashcard shown in the current list.

   * **`flip`**`2` : Flips the 2nd flashcard shown in the current list.

   * **`clear`** : Deletes all flashcards.

   * **`enter quiz`** : Enters Quiz mode. (Enter quiz interface)

      * **`start attempt`** : Start a proper attempt where answers will be recorded. (Only workable in quiz interface)

      * **`answer`** `1 a/true` : Answer the first quiz question, type the question index and user's answer in this format `a/ANSWER`. (Only workable in quiz interface)

      * **`end attempt`**: End the curret attempt and results can be seemed in performance. (Only workable in quiz interface)

   * **`leave quiz`** : Leaves Quiz mode. (Enter flashcard interface)

   * **`performance`** : Check historical quiz attempts results. (Enter performance interface)

     * **`view`** `1` : View the first attempt to check the wrong and correct answers. (Only workable in performance interface)

     * **`list`** : Show the list of historical attempts. (Only workable in performance interface)

   * **`exit`** : Exits the app.

6. Refer to the three features below for details of each command.

7. All sample data and flashcards created by the user will be stored in the `DSAce` folder.


--------------------------------------------------------------------------------------------------------------------


<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>
* There are three sets of instructions for Flashcard, Quiz and Performance interfaces. The sets of instructions are not mutual.
 <br> eg: add n/name d/definition cannot be used in quiz and performance interface. <br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user. <br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/Sorting`.

* Parameters can be in any order. <br>
  e.g. if the command specifies `n/NAME d/DEFINITION`, `d/DEFINITION n/NAME` is also acceptable.

</div>

## Feature 1 - Flashcard mode
<div markdown="block" class="alert alert-info">
* Command lines below are only workable under flashcard interface.
</div>

### Viewing help : `help`

Shows a message explaining the features of the app, and the format of the command associated with each feature.

![help message](images/helpMessage.png)

Format: `help`


### Adding a flashcard : `add`

Adds a flashcard to the default DSAce folder.

Format: `add n/NAME d/DEFINITION [t/TAG] [p/PRIORITY]`

Examples:
* `add n/Bellman Ford Search d/runtime: O(VE) p/high`
* `add n/Bubble Sort d/runtime: O(n^2) t/sorting t/midterm`

Note:
- `n/NAME` and `d/DEFINITION` are compulsory user's inputs. `[t/TAG]` and `[p/PRIORITY]` are optional inputs.
- Priority will default to low when unspecified.
- To add multiple tags, each of them requires its own label e.g `t/sorting t/midterm`
- If there are multiple instances of name and/or definition, the one that appears last is taken
e.g second is the name of `n/first n/second`

### Listing all flashcards : `list`

Shows a list of all flashcards in the DSAce folder.

Format: `list`

### Sorting all flashcards : `sort`

Sorts all flashcards in the DSAce folder by priority.

Format: `sort [ORDER]`

* Order is specified as either `asc` or `desc`.
* The order is case-insensitive. e.g `ASC` and `AsC` will both sort by ascending.
* If no order is specified, default order is ascending.

Examples:
* `sort ASC` Sorts all flashcards by priority in ascending order.
* `sort desc` Sorts all flashcards by priority in descending order.

### Editing a flashcard : `edit`

Edits an existing flashcard in the DSAce folder.

Format: `edit INDEX [n/NAME] [d/DEFINITION] [t/TAG] [p/PRIORITY]`

* Edits the flashcard at the specified `INDEX`.
* The index refers to the index number associated with the edited flashcard, as shown in the displayed flashcard list.
* The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the flashcard will be removed i.e adding of tags is not cumulative.
* You can remove all the flashcard’s tags by typing t/ without specifying any tags after it.

Examples:
* `list` followed by `edit 1 n/BubbleSort d/Average case: O(n^2)` Edits the name and definition of the 1st flashcard to
 be `BubbleSort
` and `Average case: O(n^2)` respectively.
* `list` followed by `edit 2 n/SelectionSort t/` Edits the name of the 2nd flashcard to be `SelectionSort` and clears
 all existing tags.
* `list` followed by `edit 3 p/high` Edits the priority of the 3rd flashcard to be `high`.

Note:
- To edit multiple tags, each of them requires its own label e.g `t/sorting t/midterm`
- If there are multiple instances of name and/or definition, the one that appears last is taken
  e.g second will be the name of `n/first n/second`

### Locating flashcards by name/tag/priority: `find`

Finds flashcards with names, tags or priorities containing any of the given keywords.

Format: find `[n/KEYWORD]​` `[t/KEYWORD]​` `[p/KEYWORD]​`

* All `find` operations are done on the original flashcards list which contains all flashcards.
* The search is case-insensitive. e.g `sort` will match `Sort`
* The order of the keywords does not matter. e.g. `runtime sort` will match `sort runtime`
* Names, tags or priorities will be searched according to input prefixes.
* Only full words will be matched e.g. `sort` will not match `sorting`
* Only flashcards matching all keywords will be returned (i.e. `AND` search).

Examples:

* `find n/Quicksort` returns `Quicksort`
* `find n/Chaining t/hashing` returns `Chaining`
* `find n/Heap p/medium` returns `Heaps`
* `find n/Heap p/low` or `find n/Chaining t/metal` returns no flashcards because not all conditions are satisfied

### Deleting a flashcard : `delete`

Deletes the specified flashcard from DSAce folder.

Format: `delete INDEX`

* Deletes the flashcard at the specified `INDEX`.
* The index refers to the index number associated with the deleted flashcard, as shown in the displayed flashcard list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd flashcard in the folder.

### Flipping a flashcard : `flip`

Flips the specified flashcard from DSAce folder.

Format: `flip INDEX`

* Flips the flashcard at the specified `INDEX`.
* The index refers to the index number associated with the flipped flashcard, as shown in the displayed flashcard list.
* The index **must be a positive integer** 1, 2, 3, …​
* The flashcard will not stay flipped upon user exiting and re-entering the app.

Examples:
* `list` followed by `flip 2` flips the 2nd flashcard in the folder.

### Clearing all entries : `clear`

Clears all entries from the flashcards folder.

Format: `clear`

### Entering Quiz mode : `enter quiz`

Enters quiz mode and disables all commands in flashcard mode.

Format: `enter quiz`

### Checking performance : `performance`

Opens a new window of performance where historical attempts are stored.

Format: `performance`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

## Feature 2 - Quiz mode

<div markdown="block" class="alert alert-info">
* Command line below are only workable under quiz interface.
</div>

### Starting an attempt : `start attempt`

Starts an attempt of the set of questions.

Format: `start attempt`

### Answering quiz questions : `answer`

Answers the specific indexed quiz question.

Format: `answer INDEX a/ANSWER`

* For True/False questions, answer in true/false. (case-insensitive)
* For MCQ questions, answer in positive integer as labelled in the quiz list.

Examples:
For True/False questions, type e.g `answer 1 a/true` or `answer 1 a/TrUe` or `answer 1 a/false`
For MCQ questions, type e.g `answer 2 a/1` for option 1 or `answer 2 a/2` for option 2. Invalid out of bounce index will not be recorded.
### Ending an attempt : `end attempt`

Ends the current attempt and store the result into Performance.

### Checking performance : `performance`

Opens a new window of performance where historical attempts are stored.

Format: `performance`

### Viewing help : `help`

Shows a message explaining the features of the app, and the format of the command associated with each feature.

![help message](images/helpMessage.png)

Format: `help`

### Leaving Quiz mode : `leave quiz`

Leaves quiz mode and disables all commands in quiz mode.

Format: `leave quiz`

### Exiting application : `exit`

Exits the programme.

Format: `exit`

## Feature 3 - Performance mode

<div markdown="block" class="alert alert-info">
* Command line below are only workable under perfomance interface.
</div>

### Listing historical attempts results: `list`

Shows a list of previous attempts and result statistic.

Format: `list`

### Viewing a specific historical attempt : `view`

Shows the quiz questions attempted. Red options indicate wrong answer input and green options indicate correct answer/input.

Format: `view INDEX`

Example: Index input must start from 1. Out of bounce index will not be recorded. e.g `view 1`

![view message](images/view_attempt.png)

### Viewing help : `help`

Shows a message explaining the features of the app, and the format of the command associated with each feature.

![help message](images/helpMessage.png)

Format: `help`

### Exiting application : `exit`

Exits the programme.

Format: `exit`


### Saving the data
DSAce data is saved in the DSAce folder automatically after any command that changes the data is entered. There is no need to save the data manually.

### FAQ
Q: How do I transfer my data to another Computer? <br>
A: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous DSAce home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add** | `add n/NAME d/DEFINITION [t/TAG] [p/PRIORITY]` <br> e.g., `add n/Bellman-Ford Search d/runtime: O(VE)`
**Clear** | `clear`
**Sort** | `sort [ORDER]` <br> e.g., `sort ASC`
**Delete** | `delete INDEX` <br> e.g., `delete 3`
**Flip** | `flip INDEX` <br> e.g., `flip 2`
**Edit** | `edit INDEX [n/NAME] [d/DEFINITION] [t/TAGS] [p/PRIORITY]` <br> e.g., `edit 1 n/BubbleSort d/Average case: O(n^2) p/low`
**Find** | `find [n/KEYWORDS] [t/KEYWORD​S] [p/KEYWORD​S]​` <br> e.g., `find n/BellmanFord Search`
**List** | `list` (flashcard interface)
**Help** | `help`
**Exit** | `exit`
**Enter Quiz** | `enter quiz`
**Start attempt**  | `start attempt`
**Answer** | `answer INDEX a/ANSWER` <br> e.g., `ansewer 1 a/true` for True/False questions and `ansewer 2 a/1` for MCQ questions
**End attempt**  | `end attempt`
**Leave Quiz** | `leave quiz`
**Performance** | `performance`
**List** | `list` (performance interface)
**View** | `view INDEX` <br> e.g., `view 1`
