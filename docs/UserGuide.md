---
layout: page
title: User Guide
---

FixMyAbs is a desktop app for logging your exercises, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI).

Using FixMyAbs, you will be able to add your own custom exercises along with the calories burnt per rep. These exercises will be listed in the Exercise List. You will then be able to log your exercises, along with the number of reps you have done. FixMyAbs will automatically track the number of calories burnt, and provide you with a log of the exercises you have done. Tracking your progress has never been easier!

If you are unmotivated for a workout, FixMyAbs will be your partner in helping you to change your life. 😎

* Table of Contents
{:toc}

---

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `fma.jar`.

1. Copy the file to the folder you want to use as the home folder for your FixMyAbs.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/screenshots/v1.4homescreen.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   - **`list`** : List the exercises

   - **`add e/Sit ups r/10 c/this is a comment`** : Adds a "Sit ups" exercise log of 10 reps, with a comment of "this is a comment", to the FitMyAbs record.

   - **`edit`**`4 c/no abs were hurt` : Edits the log at index 4, with a comment of "no abs were hurt".

   - **`delete 3`** : Deletes the 3rd workout.

   - **`exit`** : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

---

## Features

<div markdown="block" class="alert alert-info" id="command-format">

**:information_source: Notes about the command format:**<br>

- Words in `<>` are the parameters to be supplied by the user.<br>
  e.g. in `add e/<exercise name>`, `exercise` is a parameter which can be used as `add e/Sit ups`.

- Items in square brackets are optional.<br>
  e.g `e/<exercise name> c/[comment]` can be used as `e/Sit ups c/my abs hurt` or as `e/Sit ups c/`.

- Parameters can be in any order.<br>
  e.g. if the command specifies `e/<exercise name> r/<rep>`, `r/<rep> e/<exercise name>` is also acceptable.

- Exercise names are case-insensitive and whitespace-insensitive.<br>
  e.g. `Sit ups`, `SITUPS`, and `S i T u P   s` are all recognised as the same exercise.
  
- If there are multiple parameters for a single tag e.g. `c/sad` and `c/happy` in `add e/Sit ups r/10 c/sad c/happy`,
the last one will take precedent, i.e. `c/happy`

</div>

### Viewing help : `help`

Shows a message explaining how to access the help page.

Format: `help`

![Ui](images/screenshots/v1.4help_success.png)

### Adding a log: `add`

Adds a log.

- Exercise must already be present in the Exercise list. [Exercise names are case-insensitive and whitespace-insensitive.](#command-format)
- The comment may be left blank. (`c/` is compulsory)

Format: `add e/<exercise name> r/<reps> c/[comment]`

Constraints:
- reps must be within range 1-1000 inclusive

Examples:
- `add e/Sit ups r/1 c/` Adds a log with an existing exercise `Sit ups`, 1 rep and no comment.
- `add e/Sit ups r/1 c/my abs hurt :(` Adds a log with an existing exercise `Sit ups`, 1 rep and a comment of `no abs
 were hurt`.
- `add e/sit ups r/20 c/my abs hurt :(` Adds a log an existing exercise `sit ups`, 20 reps and a comment of `no
 abs were hurt`.


![Ui](images/screenshots/v1.4add.png)

Success:
![Ui](images/screenshots/v1.4add_success.png)

### Listing all logs : `list`

Shows a list of all logs logged by the user in the application.

Format: `list`

Example: `list`


<div markdown="block" class="alert alert-info" id="command-format">

**:information_source: Notes about long comments or exercise name:**<br>

- To view information of comments or exercise names that are too long such as `1. Sits up, push up ...`, you can
 hover over the `exercise name` of `log` or `exercise` to view more information.
- The text of log / exercise will appear after 2 seconds.
  
 Example:
 ![Ui](images/screenshots/v1.4hover.png)
 
</div>

![Ui](images/screenshots/v1.4list_success.png)

### Editing a log : `edit`

Edits an existing log in the application.

Format: `edit <index> [r/reps] [c/comment]`

Constraints:
- reps must be within range 1-1000 inclusive

- Edits the existing log at the specified `<index>`. The index refers to the index number shown in the displayed log list. The index **must be a positive integer** 1, 2, 3, …​
- At least one of the optional fields must be provided.
- Existing values will be updated to the input values.

Examples:
- `edit 1 r/20` Edits the log at index 1, with 20 reps.
- `edit 2 c/no abs were hurt` Edits the log at index 2, with a comment of `no abs were hurt`.
- `edit 1 r/20 c/no abs were hurt` Edits the log at index 1, with reps of `20` and a comment of `no abs were hurt`.

![Ui](images/screenshots/v1.4edit.png)

Success:
![Ui](images/screenshots/v1.4edit_success.png)

### Deleting a log : `delete`

Deletes the specified log.

Format: `delete <index>`

- Deletes the log at the specified `<index>`.
- The index refers to the index number shown in the current list of logs.
- The index **must be a positive integer** 1, 2, 3, …

Examples:
- `delete 2` Deletes the 2nd log.

![Ui](images/screenshots/v1.4delete.png)

Success:
![Ui](images/screenshots/v1.4delete_success.png)

### Finding a log : `find`

Finds all logs that contain ALL keywords (case-insensitive) anywhere in the log.

Format: `find <keywords in logs>`

Examples:
- `find abs` Find log(s) which contain(s) the word `abs`.
- `find Oct` Find log(s) which contain(s) the word `Oct`.

![Ui](images/screenshots/v1.4find.png)

Success:
![Ui](images/screenshots/v1.4find_success.png)

### Adding an exercise: `addex`

Adds an exercise.

Format: `addex e/<exercise name> c/calories per rep`

- The exercise must not already exist. [Exercise names are case-insensitive and whitespace-insensitive.](#command-format)
- Calories per rep would be used to calculate the calories burnt for each log.

Constraints:
- calories must be within range 1-1000 inclusive

Examples:
- `addex e/Sit ups c/20` Adds an exercise with name`Sit ups` and 20 calories per rep.
- `addex e/Jumping jacks c/50` Adds an exercise with name`Jumping jacks`and 50 calories per rep.

![Ui](images/screenshots/v1.4addex.png)

Success:
![Ui](images/screenshots/v1.4addex_success.png)

### Editing an exercise: `editex`

Edits an existing exercise in the application, either with a new name, or new calories per rep.

Format: `editex <index> [e/exercise] [c/calories per rep]`

* Edits the existing exercise at the specified `index`. The index refers to the index number shown in the displayed exercise list. The index **must be a positive integer** 1, 2, 3, …
* At least one of the optional fields must be provided.
* The new exercise must not already exist. [Exercise names are case-insensitive and whitespace-insensitive.](#command-format)
* Existing values will be updated to the input values.

Constraints:
- Calories must be within range 1-1000 inclusive

Additional information
- Details of logs that use the edited exercise will be edited as well to match the new exercise
- This ensures that there is consistency within FixMyAbs

Examples:
- `editex 2 e/Sit ups` Edits an exercise at index `2` with name `Sit ups`.
- `editex 3 c/50` Edits an exercise at index `3` with 50 calories per rep.
- `editex 3 e/Sit ups c/50` Edits an exercise at index `3` with name `Sit ups` and 50 calories per rep.


![Ui](images/screenshots/v1.4editex.png)

Success:
![Ui](images/screenshots/v1.4editex_success.png)

### Deleting an exercise : `deleteex`

Deletes the specified exercise.

Format: `deleteex <index>`

- Deletes the log at the specified `<index>`.
- The index refers to the index number shown in the list of exercises.
- The index **must be a positive integer** 1, 2, 3, …

Additional information

- Logs that use the deleted exercise will be deleted as well
- This ensures that there is consistency within FixMyAbs

Examples:
- `deleteex 7` deletes the 7th exercise in the exercise list.

![Ui](images/screenshots/v1.4deleteex.png)

Success:
![Ui](images/screenshots/v1.4deleteex_success.png)


### Exiting the program : `exit`

Exits the program.

Format: `exit`

![Ui](images/screenshots/v1.4exit.png)

### Clearing FixMyAbs : `clear` 

Clears all the data including all logs and all exercises stored in FixMyAbs.

Format: `clear`

![Ui](images/screenshots/v1.4clear.png)

Success:
![Ui](images/screenshots/v1.4clear_success.png)  

### Saving the data

Exercise log data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

---

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous FixMyAbs home folder.

---

## Command summary

| Action     | Format                                    | Examples                                  |
| ---------- | ----------------------------------------- | ----------------------------------------- |
| **Add Log**    | `add e/<exercise name> r/<reps> c/[comment]`  | e.g. `add e/Sit ups r/30 c/Send help`, `add e/Sit ups r/30 c/`       |
| **Delete Log** | `delete <index>`                          | e.g. `delete 3`                      |
| **Edit Log**   | `edit <index> [r/reps] [c/comment]`       | e.g.`edit 1 r/20 c/no abs were hurt`, `edit 1 r/20`, `edit 1 c/no abs were hurt` |
| **List Logs**   | `list`                                    | e.g. `list`
| **Add exercise**   | `addex e/<exercise name> c/<calories per rep>` | e.g. `addex e/Lunges c/5` |
| **Edit exercise**   | `editex <index> [e/exercise name] [c/calories per rep]` | e.g. `editex 1 e/One-legged Lunges c/6`, `editex 1 e/One-legged Lunges`, `editex 1 c/6` |
| **Delete exercise**   | `deleteex <index>` | e.g. `deleteex 1` |
| **Clear**  | `clear`                                   | e.g. `clear`                   |
| **Find**   | `find <keywords in logs>`                         | e.g. `find pushups`                   |
| **Help**   | `help`                         | e.g. `help`                   |
| **Exit**   | `exit`                                    | e.g. `exit`

