---
layout: page
title: CliniCal User Guide
---

## 1. Introduction 

Clinic Calendar (CliniCal) is a **desktop app that allows doctors to manage patient records and schedule upcoming appointments**. With CliniCal, you can enhance your daily workflow through the effective scheduling of medical appointments. You can also have access to a digital database that safely stores all your patient records. 

Furthermore, CliniCal is optimized for use via a Command Line Interface (CLI) and even retains the benefits of a Graphical User Interface (GUI). If you can type fast, CliniCal can get your work done faster than traditional GUI apps.

Interested to know more? Take a look at our Quick Start guide. Enjoy!

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## 2. Quick Start

Welcome to CliniCal! If you are new to our software, this quick start guide will get you up and running in no time! It equips you with the basics tools you need to use CliniCal appropriately.

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `CliniCal.jar` from [here](https://github.com/AY2021S1-CS2103T-W11-4/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for CliniCal.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press <kbd>Enter</kbd> to execute it. e.g. typing **`help`** and pressing <kbd>Enter</kbd> will open the help window.<br>
   Some example commands you can try:

   * **`list`** : Lists all patients.

   * **`add`**`n/John Doe p/98765432 i/S2561932A a/Pickle street, Block 123, #01-01 e/johnd@example.com` : Adds a patient named `John Doe` to patient database.

   * **`delete`**`3` : Deletes the 3rd patient shown in the patient list.

   * **`clear`** : Deletes all patients.

   * **`exit`** : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## 3. Features

This section highlights the commands that CliniCal supports. These include details about the format of the command and example scenarios of the command.

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [g/ALLERGY]` can be used as `n/John Doe g/penicillin` or as `n/John Doe`.

* Items with `…` after them can be used multiple times including zero times.<br>
  e.g. `[g/ALLERGY]…` can be used as ` ` (i.e. 0 times), `g/penicillin`, `g/sulfa g/aspirin` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

</div>

### 3.1. General Commands

General commands are commands that enhances general user experience when you are using the app.

#### 3.1.1. Exiting the program : `exit`

Exits the program.

Format: `exit`

#### 3.1.2. Viewing help : `help`

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help`

#### 3.1.3. Retrieve past commands using command line interface: `history`

Retrieves a list of past commands that the user had used during the session.

Format: `history`

#### 3.1.4. Clearing command history : `clearhistory`

Clears command history from the patient database.

Format: `clearhistory`

### 3.2. Patient Commands

Patient commands are commands that you can utilise to make changes to your list of patients.

#### 3.2.1. Adding a patient: `add`

Adds a patient to the patient database.

Format: `add n/NAME p/PHONE_NUMBER i/NRIC [a/ADDRESS] [e/EMAIL] [s/SEX] [b/BLOOD_TYPE] [ct/COLOR_TAG] [g/ALLERGY]…`

<div markdown="span" class="alert alert-primary">

:bulb: **Tips:**<br>

* A patient can have any number of allergies (including 0)

* The color tag can be any standard HTML color name, such as red, green or orange.<br>
  Check out [this link](https://www.w3schools.com/colors/colors_names.asp) for an extensive list.

</div>

Examples:
* `add n/John Doe p/98765432 i/S3857462J e/johnd@example.com a/Pickle street, block 123, #01-01`
* `add n/Betsy Crowe i/G7667353B e/betsycrowe@example.com a/Newgate Prison p/1234567 g/penicillin`

#### 3.2.2 Adding profile picture using command line interface: `addpicture`

Adds a profile picture to the patient’s profile by specifying filepath to desired profile picture.

All patient profiles are preloaded with a stock profile picture.

Format: `addpicture INDEX f/FILE_PATH`

Examples:
*  `addpicture 1 f/data/profile_picture.png` Replaces existing profile picture with 'profile_picture.png' for the 1st patient
*  `addpicture 2 f/downloads/profile_picture.png` Replaces existing profile picture with 'profile_picture.png' found in
                                                  'downloads' folder for the 2nd patient

#### 3.2.3 Adding profile picture using drag and drop

Adds a profile picture to the patient’s profile by using drag and drop with your mouse.

1. Select the desired profile picture and drag it onto the space reserved for patient profile picture in ClinCal.
2. Release mouse button and patient's profile picture would be updated with your desired picture.

#### 3.2.4. Clearing all entries : `clear`

Clears all patient entries from the patient database.

Format: `clear`

### 3.2.5. Color coding patients

When a patient is tagged with a color tag, the background of the patient will be styled to show that color. The color tags can be used for a variety of purposes; for example, a doctor may color code patients at risk of terminal diseases as red, for easier reference.

![example of color coded patient](images/Ui.png)

#### 3.2.6. Deleting a patient: `delete`

Deletes the specified patient from the patient database.

Format: `delete INDEX`

* Deletes the patient at the specified `INDEX`.
* The index refers to the index number shown in the displayed patient list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd patient in the patient database.
* `find Betsy` followed by `delete 1` deletes the 1st patient in the results of the `find` command.

#### 3.2.7. Editing a patient: `edit`

Edits an existing patient in the patient database.

Format: `edit INDEX [n/NAME] [p/PHONE_NUMBER] [i/NRIC] [a/ADDRESS] [e/EMAIL] [s/SEX] [b/BLOOD_TYPE] [ct/COLOR_TAG] [g/ALLERGY]…`

* Edits the patient at the specified `INDEX`. The index refers to the index number shown in the displayed patient list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.

* You can remove all the patient’s allergies by typing `g/` without specifying any allergies after it. The same applies for color tags of a patient.

Examples:
*  `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st patient to be `91234567` and `johndoe@example.com` respectively.
*  `edit 2 n/Betsy Crower g/` Edits the name of the 2nd patient to be `Betsy Crower` and clears all existing allergies.
* When editing allergies (or color tag), the existing allergies (or color tag) of the patient will be removed i.e adding of allergies (or color tag) is not cumulative.
* You can remove all the patient’s allergies by typing `g/` without specifying any allergies after it.

Examples:
*  `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st patient to be `91234567` and `johndoe@example.com` respectively.
*  `edit 2 n/Betsy Crower t/` Edits the name of the 2nd patient to be `Betsy Crower` and clears all existing allergies.
*  `edit 3 ct/red` Clears the existing color tag and edits the color tag of the 3rd patient to be `red`.

#### 3.2.8 Listing all patients: `list`

Shows a list of all patients in the patient database.

Format: `list`

#### 3.2.9. Redoing a command : `redo`

Redoes the most recent command that the user has undone.

Format: `redo`

#### 3.2.10. Retrieve and reuse past commands using arrow keys

Reuse past commands using the <kbd>↑</kbd> arrow and <kbd>↓</kbd> arrow keys on the keyboard.
1. Click on the text field of the command box.
2. Press the <kbd>↑</kbd> arrow key to display the next recent past command.
3. Press the <kbd>↓</kbd> arrow key to display the previously shown past command.
4. Press <kbd>Enter</kbd>/<kbd>Return</kbd> key to reuse the command.

### 3.2.11. Saving the data

CliniCal data is saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

#### 3.2.12. Undoing the previous command: `undo`

Reverts the previous command given by the user.

Format: `undo`

--------------------------------------------------------------------------------------------------------------------

## 4. FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous CliniCal home folder.

--------------------------------------------------------------------------------------------------------------------

## 5. Command summary

Action | Format, Examples
--------|------------------
**Add** | `add n/NAME p/PHONE_NUMBER i/NRIC [a/ADDRESS] [e/EMAIL] [s/SEX] [b/BLOOD_TYPE] [ct/COLOR_TAG] [g/ALLERGY]…​` <br> e.g., `add n/James Ho p/22224444 i/S2686887R e/jamesho@example.com a/123, Clementi Rd, 1234665 s/M b/B+ g/sulfa g/aspirin`
**Add profile picture** | `addpicture 1 f/data/profile_picture.png`
**Clear** | `clear`
**Clear command history** | `clearhistory`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Edit** | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [i/NRIC] [a/ADDRESS] [e/EMAIL] [s/SEX] [b/BLOOD_TYPE] [ct/COLOR_TAG] [g/ALLERGY]…`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com `
**Find** | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`
**List** | `list`
**Help** | `help`
**Undo** | `undo`
**Redo** | `redo`
**Retrieve past commands** | `history`
