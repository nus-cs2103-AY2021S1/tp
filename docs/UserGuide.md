---
layout: page
title: User Guide
---

Calo is a desktop app that is **designed for keeping track of calories burnt throughout the day**. It is optimized for use via a **Command Line Interface (CLI)** while still having Graphical User Interface (GUI). If you are a skilled typer, you can carry out various tasks such as adding new exercises and checking records for previous days much faster than the traditional GUI apps.

- Table of Contents
  {:toc}

---

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `Calo.jar`.

3. Copy the file to the folder you want to use as the _home folder_ for your Calo.

4. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.

5. Type the command in the command box and press Enter to execute it. For the details of each command, refer to the Features below.

---

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

- Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add e/EXERCISE c/CALORIES`, `NAME` is a parameter which can be used as `add e/Push Up c/1000`.

- Items in square brackets are optional.<br>
  e.g `e/EXERCISE c/CALORIES [at/DATE]` can be used as `e/Push Up c/1000 at/29-09-2020` or as `e/Push Up c/1000`.

- Parameters can be in any order.<br>
  e.g. if the command specifies `e/EXERCISE c/CALORIES`, `c/CALORIES e/EXERCISE` is also acceptable.

</div>

#### Add exercises : `add`

Add an exercise to the application, with calories burnt (optional).

Format: `add e/EXERCISE d/DESCRIPTION at/DATE [c/CALORIES]`

- The format for the DATE should be in the form of DD-MM-YYYY.

Examples:
- `add e/Push up d/10 at/14-09-2020 c/30`
- `add e/Sit up d/10 at/14-09-2020`

#### Update exercises : `update`

Update an existing exercise.

Format: `update INDEX [e/EXERCISE] [d/DESCRIPTION] [at/DATE] [c/CALORIES]`
- Edits the workout at the specified `INDEX`. The index refers to the index number shown in the displayed workout list. The index **must be a positive integer** 1, 2, 3, …​
- Existing values of the exercise will be updated to the input values.

Examples:
- `update 1 e/Push up d/30 at/09-07-2020 c/260` Updates the exercise, the description, the date and the calories burnt of the 1st exercise to be `push up`, `30`,  `07-09-2020`, `260` respectively.

#### Delete : `delete`
Deletes an exercise that a user has previously added.

Format: `delete INDEX`

- Deletes an exercise at the specified `INDEX`.
- The index refers to the index number shown in the displayed workout list.
- The index must be a positive integer: 1, 2, 3, …​

Example:
- `delete 2` Deletes the second exercise in the displayed list.

#### List: `list`
Lists out all the exercises that the user has keyed in

Format: `list`

#### Find exercises: `find`
Finds exercises whose name contain any of the given keywords.

Format: `find KEYWORD`
- The search is case-insensitive. e.g Squats will match squats.

Example:
- find Push up


#### Save
The application will save the data automatically to the default file path after any command that changes the data. There is no need to save the data manually.

#### Archive : `archive`
Archive the data into a different file location.

Format: `archive FILE_LOCATION`<br>
The file location takes reference from the home folder that the .jar file is located at.

Examples:<br>
archive data\file_name.txt If the file is located at C:\Users\Desktop\App, the archived file will be saved to  C:\Users\Desktop\App\data\file_name.txt.<br>

#### Add templates : `create`
Adds an exercise template.

Format: `create n/NAME d/DESCRIPTION c/CALORIES`  

Examples:<br>
- `create n/pushup d/half an hour c/100` Creates the exercise template with the name push up, description half an hour and calories 100.

#### Add an exercise using template : `addt`
Adds an exercise using template.

Format: `addt n/NAME at/DATE [c/CALORIES]`  
- The format for the DATE should be in the form of DD-MM-YYYY.     

- The user can input calorie value to overwrite the default calorie value defined by the template. If the user inputs no calories, then the exercise will have the default calorie value in the template.

Examples:<br>
- `addt n/pushup at/09-07-2020 c/260` Creates the exercise using the template called pushup with the date 09-07-2020 and calories 260.

- `addt n/pushup at/09-06-2020` Creates the exercise using the template called pushup with the date 09-06-2020 and default calories 100.
---
## FAQ

*Q*: How do I transfer my data to another Computer?<br>
*A*: Transfer the file “data” that is contained in the same file as your .jar file from your old computer to your new computer.

*Q*: How to load my archived file?<br>
*A*: For now, you can delete the `entry.txt` file in the `data` folder and rename the archived file of your choices to `entry.txt`. In subsequent updates, we will introduce a command to load archived files via Command Line Interface.

---

## Command summary

| Action     | Format, Examples                                                                                                                                                      |
| ---------- | --------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| *Add*    | `add e/EXERCISE d/DESCRIPTION at/DATE [c/CALORIES]` <br> e.g. `add e/Push up d/10 at/14-09-2020 c/30` |
| *Delete* | `delete INDEX`<br> e.g., `delete 2`                                                                                                                                   |
| *Update*   | `update INDEX [e/EXERCISE] [d/DESCRIPTION] [at/DATE] [c/CALORIES]​`<br> e.g.,`update 1 e/Push up d/30 at/09-07-2020 c/260`                                           |
| *Find*  | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`                                                                                                            |
| *List*   | `list`                                                                                                                                                                |
| *Archive*   | `archive FILE_LOCATION`    <br> e.g.,`archive data\file_name.txt`                                                                                                                                                                    |
