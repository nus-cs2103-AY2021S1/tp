

Table of Content

Table of Content

\1. User Guide

\2. Quick Start

\3. Preface

3.1. Understanding the GUI

3.2. Reading this document

3.3. Prefix used

\4. Features

Adding a workout: add

Finding exercises with a keyword: find

Deleting exercises: delete

Updating exercises: update

Save

Archiving data files: archive

List: list

Setting Calorie burn goal: goal

Recalling the most recent exercise: recall

Adding an exercise template: create

Adding an exercise using template: addt

\5. Frequently Asked Questions (FAQ)

\6. Summary of Content





\1. User Guide

Calo is a **desktop app that is designed for keeping track of calories burnt**

**throughout the day. It is optimized for use via a Command Line Interface** (CLI)

while still having Graphical User Interface (GUI). If you are a skilled typer, you can carry

out various tasks such as adding new exercises and checking records for previous days

much faster than the traditional GUI apps.





\2. Quick Start

\1. Ensure you have Java 11 or above installed in your Computer.

\2. Download the latest Calo.jar from the following [GitHub Page](https://github.com/AY2021S1-CS2103T-W17-2/tp)


\3. Copy the file to the folder you want to use as the *home folder* for your Calo.

\4. Double-click the file to start the app. The GUI should appear in a few

seconds. [For the sample of GUI, refers to 3.1. Understanding the GUI ]

\5. Type the command in the command box and press **Enter** to execute it.

\6. For the full list of commands and their details, refer to the Features below.





\3. Preface

3.1. Understanding the GUI





3.2. Reading this document

This document makes use of the following syntax and styling to convey our message across

accurately and concisely. Let’s take a look at what they are and their meaning.

Syntax / Styling

Meaning

command

Represents inputs that you can key into Calo and test it out.

EXERCISE

n/EXERCISE

[at/MONTH]

Upper case words highlighted in grey are parameters for you to enter.

The slash and the letter(s) before it (in this case n/) is the prefix..

Prefix and Parameter in square bracket is optional. It is okay to exclude

them, but feel free to include them without the square bracket so that you

can track down more details of that exercise that you have done.

**Definition :**

**Parameter** is like a field in the form. Feel free to input the relevant details for the

exercise.

**Prefix** is token used by Calo to differentiate one parameter from another, and is

necessary for the application to work

3.3. Prefix used

Confused about what to enter after a prefix? Used this table as reference !!!

Prefix

What to include

n/

Name of the exercise. Calo only accepts names with letters, numbers

and space. Names with special characters will not be accepted.

c/

at/

d/

Calories burnt. It should be a positive number.

Date that you did the exercise.

Any other description or details that you want to note down about the

exercise.

f/

File path that you wish to point to.





\4. Features

**Adding a workout:** add

Adds a workout to the application, with calories burnt (optional). After which the daily

calorie burn goal is updated if it has been specified.

**Format:** add n/EXERCISE d/DESCRIPTION at/DATE c/CALORIES

● The format for the DATE should be in the form of DD-MM-YYYY.

**Examples:**

● add n/Push up d/10 at/14-09-2020 c/30

● add n/Sit up d/10 at/14-09-2020

**Finding exercises with a keyword:** find

Finds exercises whose names contain any of the given keywords.

**Format:** find KEYWORD

● The search is case-insensitive. e.g Squats will match with squats.

**Examples:**

● find Push up

**Deleting exercises:** delete

Deletes an exercise that a user has previously added.

**Format:** delete INDEX

● Deletes an exercise at the specified INDEX.

● The index refers to the index number shown in the displayed workout list.

● The index **must be a positive integer**: 1, 2, 3, …

**Example:**

● delete 2Deletes the second exercise in the displayed list.





**Updating exercises:** update

Updates an existing exercise.

**Format:** update INDEX [n/EXERCISE] [d/DESCRIPTION] [at/DATE] [c/CALORIES]

● Edits the workout at the specified INDEX. The index refers to the index number

shown in the displayed workout list. The index **must be a positive integer** 1, 2,

3, …

● Existing values of the exercise will be updated to the input values.

**Example:**

● update 1 n/Push up d/30 at/09-07-2020 c/260 Updates the exercise, the

description, the date and the calories burnt of the 1st exercise to be push up, 30,

07-09-2020, 260 respectively.

●

**Save**

The application will save the data automatically to the default file path after any

command that changes the data. There is no need to save the data manually.

**Archiving data files:** archive

Archive the data into a different file location.

**Format:** archive f/FILE\_LOCATION

The file location takes reference from the *home folder* that the .jar file is located at.

**Examples:**

● archive f/data\file\_name.txtIf the file is located at C:\Users\Desktop\App,

the archived file will be saved to C:\Users\Desktop\App\data\file\_name.txt.

**List:** list

List out all the exercises that the user has keyed in.

**Format:** list





**Setting Calorie burn goal:** goal

You can set a daily calorie burn goal for a specific date. You cannot insert another calorie burn

goal for a date that already has a calorie burn goal.

**Format:** goal c/Calories at/Date

● The format for the DATE should be in the form of DD-MM-YYYY.

**Example:**

● goal c/3000 at/09-07-2020 Creates a calorie burn goal of 3000 for the date

09-07-2020

**Recalling the most recent exercise:** recall

Finds the most recent exercise with the specified name.

**Format:** recall NAME

**Example:**

**●** recall push upFinds the most recent exercise with the name push up

**Adding an exercise template:** create

Adds an exercise template.

**Format:** create n/NAME d/DESCRIPTION c/CALORIES

**Example:**

**●** create n/pushup d/half an hour c/100 Creates the exercise template with the

name push up, description half an hour and calories 100.





**Adding an exercise using template:** addt

Adds an exercise using template.

**Format:** addt n/NAME at/DATE [c/CALORIES]

● The format for the DATE should be in the form of DD-MM-YYYY.

● The user can input calorie value to overwrite the default calorie value defined by the

template. If the user inputs no calories, then the exercise will have the default calorie

value in the template.

**Example:**

**●** addt n/pushup at/09-07-2020 c/260 Creates the exercise using the template

called pushup with the date 09-07-2020 and calories 260.

**●** addt n/pushup at/09-06-2020 Creates the exercise using the template called

pushup with the date 09-06-2020 and default calories 100.

\5. Frequently Asked Questions (FAQ)

**Q:** How do I transfer my data to another Computer?

**A:** Transfer the file “data” that is contained in the same file as your .jar file from your

old computer to your new computer.

**Q:** How to save my data?

**A:** The application will save the data automatically to the default file path after any

command that changes the data. There is no need to save the data manually. If

you want to save to a specific location, you can use archivecommand.

**Q:** How to load my archived file?

**A:** For now, you can delete the exerciseBook.json file in the data folder and

rename the archive file of your choices to exerciseBook.json. In subsequent

updates, we will introduce a command to load archived files via Command Line

Interface.





\6. Summary of Command

This page contains the summary of all the commands. For the details of each command, look

for respective parts in 4. Features.

**Action**

**Summary**

Add

add e/EXERCISE d/DESCRIPTION at/DATE [c/CALORIES]

e.g. add e/Push up d/10 at/14-09-2020 c/30

Add Template

create n/NAME d/DESCRIPTION c/CALORIES

e.g. create n/pushup d/half an hour c/100

Add Exercise

addt n/NAME at/DATE [c/CALORIES]

based on Template

e.g. addt n/pushup at/09-07-2020 c/260

Archive

Delete

archive f/FILE\_LOCATION

e.g. archive f/data\file\_name.txt

delete INDEX

e.g. delete 2

Find

find KEYWORD

e.g. find Push up

Goal Setting

goal c/Calories at/Date

e.g. goal c/3000 at/09-07-2020

List

list

Recall

recall NAME

e.g. recall push up

Update

update INDEX [e/EXERCISE] [d/DESCRIPTION] [at/DATE]

[c/CALORIES]





e.g. update 1 e/Push up d/30 at/09-07-2020 c/260

