---
layout: page
title: User Guide
---

QuickCache is a **desktop app for managing flashcards, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, QuickCache can get your flashcard management tasks done faster than traditional GUI apps.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start
Here is a quick start on how you can start using our app in your own computer. 

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `quickcache.jar` from [here](https://github.com/AY2021S1-CS2103T-T13-2/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your QuickCache.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:
   
   * **`add`**`q/Plants give out ___ when they photosynthesise? ans/Oxygen t/Biology` :  Adds an open ended question `Plants give out ___ when they photosynthesise?` with answer `Oxygen` and tagged to `Biology`. 
      
   * **`addmcq`**`q/Plants give out ___ when they photosynthesise? ans/1 c/Oxygen c/Carbon c/Carbon dioxide` :  Adds a multiple choice question `Plants give out ___ when they photosynthesise?` with 3 options `Oxygen`, `Carbon`, `Carbon dioxide` and with answer `Oxygen`.

   * **`open`**`1` : Opens the 1st question shown in the current list.
   
   * **`edit`**`1 ans/Edited answer` : Edit the answer of the first flashcard on the list to become `Edited answer`.
   
   * **`list`** : Lists all FlashCards.
   
   * **`find`** `find t/MCQ q/What CS2103T q/is t/GoodQuestion` : Finds all Flashcards tagged to the tag `MCQ` and `GoodQuestion` and has keywords `What`, `CS2103T` and `is` in question.
   
   * **`delete`**`3` : Deletes the 3rd flashcard shown in the current list.
   
   * **`clear`** : Deletes all FlashCards.
   
   * **`test`**`1 ans/Example answer` : Tests the 1st question shown in the current list with `Example answer` as the answer.
   
   * **`stats`**`1` : Show stats of the 1st question shown in the current list.
   
   * **`clearstats`**`1` : Clears the statistics of the 1st flashcard shown in the current list.
   
   * **`export`**`science-questions.json` : Exports current list of flashcard to a file named `science-questions.json` in the `export` folder.
   
   * **`import`**`science-questions.json` : Import all flashcards from a file named `science-questions.json` in the `import` folder.

   * **`exit`** : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/ANSWER`, `ANSWER` is a parameter which can be used as `add n/Oxygen`.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/QUESTION p/ANSWER`, `p/ANSWER n/QUESTION` is also acceptable.

</div>

### Viewing help : `help`

Shows a message explaning how to access the help page.

Format: `help`

![help message](images/helpMessage.png)

### Creating a new flashcard
You can add a new flashcard to the list.
#### Creating a flashcard with open ended question: `add q/QUESTION ans/ANSWER`
You can create a flashcard that contains an open ended question which will be added to the list.

1. You can use the add command to add a flashcard with an open ended question which will be added to the list.
For example, you can enter`add q/Sample Question ans/Sample Answer`
![addOpenEnded](images/addOpenEnded.png)
2. You have successfully created a flashcard with an open ended question and it will be saved immediately in the list.
![addOpenEnded2](images/addOpenEnded2.png)

<div class="alert alert-danger">
You cannot add a flashcard with empty question and empty answer.
</div>

#### Creating a flashcard with multiple choice question: `addmcq q/QUESTION ans/ANSWER c/FIRST_CHOICE c/SECOND_CHOICE ..`
You can create a flashcard that contains a multiple choice question which will be added to the list.

1. You can use the addmcq command to add a flashcard with a multiple choice question which will be added to the list.
For example, you can enter `addmcq q/Sample Question ans/1  c/Sample Choice c/Sample Choice 2`
![addMCQ](images/addMCQ.png)
2. You have successfully created a flashcard with a multiple choice question and it will be saved immediately in the list.
![addMCQ2](images/addMCQ2.png)

<div class="alert alert-danger">
You cannot add flashcard with missing question or missing answer or missing choice.
<br>
You should have at least two choices.
</div>

### Opening a flashcard: `open INDEX`

To view a flashcard you can open it by its index.

<div markdown="block" class="alert alert-info">

:information_source: The INDEX refers to the the index number shown on the last displayed flashcard list and it <strong>must be a positive integer</strong>.

</div>

1. Use the `list` command to first list all the flashcards. You can also use the `find` command to filter for a list of flashcards.

	![OpenIndexStep1](./images/OpenIndexStep1.png)
	
2. Using the indices of the displayed list, enter the open command followed by the index of the flashcard you want to open. For example, if you want to open the 1st flashcard in the displayed list, you can enter `open 1`.

	![OpenIndexStep2](./images/OpenIndexStep2.png)
	
3. Press enter and QuickCache will open the flashcard specified by the index.

	![OpenIndexStep3](./images/OpenIndexStep3.png)
	
You have successfully opened a flashcard!

### Editing a flashcard: `edit INDEX q/QUESTION ans/ANSWER c/FIRST_CHOICE c/SECOND_CHOICE ..`

You can edit a flashcard that you have created previously.

1. Use the `list` command to first list all the flashcards. You can also use the `find` command to filter for a list of flashcards.
![edit](images/Edit.png)
2. Using the indices of the displayed list, enter the edit command followed by the index of the flashcard you want to edit. 
For example, if you want to edit the 3rd flashcard in the displayed list, you can enter `edit 3 q/Updated Question`.
![edit2](images/Edit2.png)
3. Press enter and QuickCache will edit the flashcard specified by the index.
![edit3](images/Edit3.png)

You have successfully edited a flashcard.

<div class="alert alert-danger">
You must have at least one edited field which is different from the previous flashcard.
</div>

### Listing all flashcards : `list`

Shows a list of all flashcards currently created.

Format: `list`

### Finding Flashcards

To find flashcards, you can find them by specifying keywords within their questions and/or specifying their tags.

<div markdown="block" class="alert alert-info">

:information_source: The .. refers to any number of tags and/or keywords

</div>

#### Finding by question: `find q/KEYWORD1 q/KEYWORD2 ..`
	
<div markdown="block" class="alert alert-info">

:information_source:<br>
- Words in spaced keywords will be treated as individual keywords. Example, the keyword `what is` will be treated as two keywords: `what` and `is`.<br>
- Keywords do not need to match exact word. Example, the keyword `Wha` will pick up questions containing `What` as a word.

</div>

1. Use the `list` command to first list all the flashcards.

	![FindQuestionStep1](./images/FindQuestionStep1.png)
	
2. Enter the find command followed by `q/` and the keyword in the question of the flashcards you want to find. For example, if you want to find flashcards with the keyword `CS2103T` in their question in the displayed list, you can enter `find q/CS2103T`.

	![FindQuestionStep2](./images/FindQuestionStep2.png)
	
3. Press enter and QuickCache will display all the flashcards with the keyword `CS2103T` in their question.

	![FindQuestionStep3](./images/FindQuestionStep3.png)

#### Finding by tags: `find t/TAG1 t/TAG2 ..`
	
<div markdown="block" class="alert alert-info">

:information_source: Tags are case-sensitive.

</div>

1. Use the `list` command to first list all the flashcards.

	![FindTagsStep1](./images/FindTagsStep1.png)
	
2. Enter the find command followed by `t/` and the tag of the flashcards you want to find. For example, if you want to find flashcards with the tag `Assembly` in the displayed list, you can enter `find t/Assembly`.

	![FindTagsStep2](./images/FindTagsStep2.png)
	
3. Press enter and QuickCache will display all the flashcards with the tag `Assembly`.

	![FindTagsStep3](./images/FindTagsStep3.png)
	
#### Finding by tags and keywords: `find t/TAG1 t/TAG2 .. q/KEYWORD1 q/KEYWORD2 ..`

<div markdown="block" class="alert alert-info">

:information_source:<br>
- Words in spaced keywords will be treated as individual keywords. Example, the keyword `what is` will be treated as two keywords: `what` and `is`.<br>
- Keywords do not need to match exact word. Example, the keyword `Wha` will pick up questions containing `What` as a word.<br>
- Tags are case-sensitive.

</div>

1. Use the `list` command to first list all the flashcards.

	![FindQuestionAndTagsStep1](./images/FindQuestionAndTagsStep1.png)
	
2. Enter the find command followed by `t/` and the tag of the flashcards you want to find. Proceed to include `q/` and the keyword in the question of the flashcards you want to find. For example, if you want to find flashcards with the tag `TCPforLife` in the displayed list and the keyword `What`, you can enter `find t/TCPforLife q/What`.

	![FindQuestionAndTagsStep2](./images/FindQuestionAndTagsStep2.png)
	
3. Press enter and QuickCache will display all the flashcards with the tag `TCPforLife ` and the keyword `What` in their question.

	![FindQuestionAndTagsStep3](./images/FindQuestionAndTagsStep3.png)

### Deleting a flashcard

To delete a flashcard, you can either delete it by its index or based off its tags.

<div markdown="block" class="alert alert-danger">

:exclamation: You can only delete based on index or based on tags but not both!

</div>

#### Deleting by index : `delete INDEX`

You can delete a flashcard based on the index shown in the last displayed list.


<div markdown="block" class="alert alert-info">

:information_source: The INDEX refers to the index number shown on the last displayed flashcard list and it <strong>must be a positive integer</strong>.

</div>

1. Use the `list` command to first list all the flashcards. You can also use the `find` command to filter for a list of flashcards.

	![DeleteIndexStep1](./images/DeleteIndexStep1.png)
	
2. Using the indices of the displayed list, enter the delete command followed by the index of the flashcard you want to delete. For example, if you want to delete the 4th flashcard in the displayed list, you can enter `delete 4`.

	![DeleteIndexStep2](./images/DeleteIndexStep2.png)
	
3. Press enter and QuickCache will delete the flashcard specified by the index. 

	![DeleteIndexStep3](./images/DeleteIndexStep3.png)
	
You have successfully deleted a flashcard!

#### Delete by tags : `delete t/TAG1`

You can delete a group of flashcards based on a specified tag. All flashcards that have this specified tag will be deleted.

<div markdown="block" class="alert alert-info">

:bulb: You can specify more than one tag to be used when deleting flashcards. Any flashcard with **at least one** of these specified tags will be deleted.

</div>

1. In the user input box, enter in the delete command together with the tags that you want to use as the criteria. For example, if you want to delete all flashcards with the tag `SolarSystem`, type `delete t/SolarSystem`.

	![DeleteTagStep1](./images/DeleteTagStep1.png)

2. Press enter and QuickCache will delete the flashcards accordingly.

	![DeleteTagStep2](./images/DeleteTagStep2.png)

All flashcards with your specified tag(s) are now deleted!

#### Clearing all entries : `clear`
Clears all entries from QuickCache.

Format: `clear`

### Testing a flashcard : `test`

Tests a specified flashcard from the list.

#### Containing an open-ended question

Format: `test INDEX ans/ANSWER`

* Tests the flashcard at the specified `INDEX`
* The index refers to the index number shown in the displayed flashcard list.
* The index **must be a positive integer**  1, 2, 3, …
* The `ANSWER` is case-insensitive.

Examples:
* `list` followed by `test 1 a/Example answer` tests the 1st flashcard in the list with `Example answer` as the answer.

#### Containing a multiple choice question

Format: `test INDEX o/OPTION` 

* Tests the flashcard at the specified `INDEX`
* The index refers to the index number shown in the displayed flashcard list.
* The index **must be a positive integer**  1, 2, 3, …
* `CHOICE`(s) are displayed in the displayed choices list of the flashcard after `open INDEX` command is performed.
* The `OPTION` refers to the index number of the specified `CHOICE`.
* The `OPTION` **must be a positive integer** 1, 2, 3, …

Examples:
* `list` followed by `test 1 o/2` tests the 1st flashcard in the list with `OPTION 2` corresponding to the 2nd choice in the choices of the multiple choice question as the answer.

### Displaying statistics for a Flashcard: `stats INDEX`

To view the statistics of a flashcard you can use the stats command.

<div markdown="block" class="alert alert-info">

:information_source: The INDEX refers to the the index number shown on the last displayed flashcard list and it <strong>must be a positive integer</strong>.

</div>

<div markdown="block" class="alert alert-dark">

Statistics include:<br>

- The number of times and the percentage the user answers the question associated with the flashcard correctly.<br>
- The number of times and the percentage the user answers the question associated with the flashcard incorrectly.

</div>

1. Use the `list` command to first list all the flashcards. You can also use the `find` command to filter for a list of flashcards.

	![StatsIndexStep1](./images/StatsIndexStep1.png)
	
2. Using the indices of the displayed list, enter the stats command followed by the index of the flashcard you want to view the statistics of. For example, if you want to view the statistics of the first flashcard in the displayed list, you can enter `stats 1`.

	![StatsIndexStep2](./images/StatsIndexStep2.png)
	
3. Press enter and QuickCache will display the statistics of the flashcard specified by the index.

	![StatsIndexStep3](./images/StatsIndexStep3.png)
	
You have successfully displayed the statistics of a flashcard!

### Clearing a flashcard's statistics : `clearstats INDEX`

To clear the statistics of a flashcard you can use the clearstats command.

<div markdown="block" class="alert alert-info">

:information_source: The INDEX refers to the the index number shown on the last displayed flashcard list and it <strong>must be a positive integer</strong>.

</div>

1. Use the `list` command to first list all the flashcards. You can also use the `find` command to filter for a list of flashcards.

	![ClearstatsIndexStep1](./images/ClearstatsIndexStep1.png)
	
	<div markdown="block" class="alert alert-dark">
	<Strong>Optional Step:</Strong>
	You can first check the statistics of the flashcard you want to clear the statistics of.
	
	<img src="./images/ClearstatsIndexStepOptional1.png">
		
	</div>
	
2. Using the indices of the displayed list, enter the clearstats command followed by the index of the flashcard you want to clear the statistics of. For example, if you want to clear the statistics of the first flashcard in the displayed list, you can enter `clearstats 1`.

	![ClearstatsIndexStep2](./images/ClearstatsIndexStep2.png)
	
3. Press enter and QuickCache will clear the statistics of the flashcard specified by the index.

	![ClearstatsIndexStep3](./images/ClearstatsIndexStep3.png)
	
	<div markdown="block" class="alert alert-dark">
	<Strong>Optional Step:</Strong>
	You can check that the statistics of the flashcard has been cleared.
	
	<img src="./images/ClearstatsIndexStepOptional2.png">
	
	</div>
	
You have successfully cleared the statistics of a flashcard!

### Importing a set of flashcards : `import`

Imports the flashcards from a specified file into your local QuickCache.

Format: `import FILE_NAME`

* Imports the flashcards from the specified file.
* Duplicate flashcards will be ignored.
* The input file follows the name specified in `FILE_NAME`.
* The input file should be placed within the `import` folder for it to be detected.


### Exporting a set of flashcards : `export`

Exports the current list of flashcard into a file.

Format: `export FILE_NAME`

* Exports the previously shown list of flashcards.
* The output file follows the name specified in `FILE_NAME`.
* The output file can be found in the `export` folder.

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

QuickCache data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous QuickCache home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

<table>
    <thead>
        <tr>
            <th>Action</th>
            <th>Command</th>
            <th>Format</th>
            <th>Example</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td rowspan=2>
            	<Strong>Creating a flashcard</Strong>
            </td>
            <td rowspan=1>add</td>
            <td rowspan=1>
            	<code>add q/QUESTION ans/ANSWER</code>
            </td>
            <td rowspan=1>
            	<code>add q/Sample Question? ans/A</code>
            </td>
        </tr>
        <tr>
            <td rowspan=1>addmcq</td>
            <td rowspan=1>
            	<code>addmcq q/QUESTION ans/ANSWER <br>
            	c/FIRST_CHOICE c/SECOND_CHOICE ..</code>
            </td>
            <td rowspan=1>
            	<code>addmcq q/Sample Question? ans/1 c/A c/B c/C</code>
            </td>
        </tr>
        <tr>
            <td rowspan=1>
            	<Strong>Opening a flashcard</Strong></td>
            <td>open</td>
            <td rowspan=1>
            	<code>open INDEX</code>
            </td>
            <td rowspan=1>
            	<code>open 1</code>
            </td>
        </tr>
        <tr>
            <td rowspan=1>
            	<Strong>Editing a flashcard</Strong></td>
            <td>edit</td>
            <td rowspan=1>
            	<code>edit INDEX q/QUESTION ans/ANSWER <br>
            	c/FIRST_CHOICE c/SECOND_CHOICE ..</code>
            </td>
            <td rowspan=1>
            	<code>edit 1 q/Sample Question? ans/2 c/A c/C c/B</code>
            </td>
        </tr>
        <tr>
            <td rowspan=1>
            	<Strong>Listing flashcards</Strong></td>
            <td>list</td>
            <td rowspan=1>
            	<code>list</code>
            </td>
            <td rowspan=1>
            	<code>list</code>
            </td>
        </tr>
        <tr>
            <td rowspan=3>
            	<Strong>Finding flashcards</Strong></td>
            <td rowspan=3>find</td>
            <td rowspan=1>
            	<code>find q/KEYWORD1 q/KEYWORD2 ..</code>
            </td>
            <td rowspan=1>
            	<code>find q/CS2103T q/What q/is</code>
            </td>
        </tr>
        <tr>
        	<td rowspan=1>
        		<code>find t/TAG1 t/TAG2 ..</code>
        	</td>
          <td rowspan=1>
            	<code>find t/Assembly t/MCQ</code>
         	</td>
        </tr>
        <tr>
        	<td rowspan=1>
        		<code>find t/TAG1 t/TAG2 .. <br>
        		q/KEYWORD1 q/KEYWORD2 ..</code>
        	</td>
          <td rowspan=1>
            	<code>find t/Assembly t/MCQ q/CS2100 q/What</code>
         	</td>
        </tr>
        <tr>
            <td rowspan=3>
            	<Strong>Deleting flashcard(s)</Strong></td>
            <td rowspan=2>delete</td>
            <td rowspan=1>
            	<code>delete INDEX</code>
            </td>
            <td rowspan=1>
            	<code>delete 1</code>
            </td>
        </tr>
        <tr>
        	<td rowspan=1>
        		<code>delete t/TAG1</code>
        	</td>
          <td rowspan=1>
            	<code>delete t/Assembly</code>
         	</td>
        </tr>
        <tr>
        	<td rowspan=1>clear</td>
          <td rowspan=1>
            	<code>clear</code>
         	</td>
          <td rowspan=1>
            	<code>clear</code>
         	</td>
        </tr>
        <tr>
        	<td rowspan=2>
        		<Strong>Testing a flashcard<Strong>
        	</td>
        	<td rowspan=2>test</td>
        	<td rowspan=1>
        		Open Ended: <code>test INDEX a/ANSWER</code>
        	</td>
        	<td rowspan=1>
        		<code>test 2 a/lorem ipsum</code>
        	</td>
        </tr>
        <tr>
        	<td rowspan=1>
        		MCQ: <code>test INDEX o/OPTION</code>
        	</td>
        	<td rowspan=1>
        		<code>test 3 o/1</code>
        	</td>
        </tr>
        <tr>
        	<td rowspan=2>
        		<Strong>Flashcard Statistics<Strong>
        	</td>
        	<td rowspan=1>stats</td>
        	<td rowspan=1>
        		Display statistics:<code>stats INDEX </code>
        	</td>
        	<td rowspan=1>
        		<code>stats 1</code>
        	</td>
        </tr>
        <tr>
        	<td rowspan=1>clearstats</td>
        	<td rowspan=1>
        		Clear statistics:<code>clearstats INDEX</code>
        	</td>
        	<td rowspan=1>
        		<code>clearstats 1</code>
        	</td>
        </tr>
        <tr>
        	<td rowspan=2>
        		<Strong>Import and Export<Strong>
        	</td>
        	<td rowspan=1>import</td>
        	<td rowspan=1>
        		<code>import FILE_NAME</code>
        	</td>
        	<td rowspan=1>
        		<code>import science-questions.json</code>
        	</td>
        </tr>
        <tr>
        	<td rowspan=1>export</td>
        	<td rowspan=1>
        		<code>export FILE_NAME</code>
        	</td>
        	<td rowspan=1>
        		<code>export science-questions.json</code>
        	</td>
        </tr>
        <tr>
        	<td rowspan=1>
        		<Strong>Get user guide URL</Strong>
        	</td>
        	<td>help</td>
        	<td>
        		<code>help</code>
        	</td>
        	<td>
        		<code>help</code>
        	</td>
        </tr>
        <tr>
        	<td rowspan=1>
        		<Strong>Exit QuickCache</Strong>
        	</td>
        	<td>exit</td>
        	<td>
        		<code>exit</code>
        	</td>
        	<td>
        		<code>exit</code>
        	</td>
        </tr>
    </tbody>
</table>
