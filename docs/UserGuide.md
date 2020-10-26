# Trackr - User Guide

1. [Introduction](#1-introduction)
2. [Quick Start](#2-quick-start)
3. [Features](#3-features)<br>
   3.1 [Command format](#31-command-format)<br>
   3.2 [View help: `help`](#32-view-help-help)<br>
   3.3 [Module features](#33-module-features)<br>
   3.4 [Tutorial group features](#34-tutorial-group-features)<br>
   3.5 [Student features](#35-student-features)<br>
   --- 3.5.1 [Add a student: `addStudent`](#351-add-a-student-addstudent)<br>
   --- 3.5.2 [Delete a student: `deleteStudent`](#352-delete-a-student-deletestudent)<br>
   --- 3.5.3 [Find a student: `findStudent`](#353-find-a-student-findstudent)<br>
   --- 3.5.4 [Edit a student: `editStudent` [coming in v1.4]](#354-edit-a-student-editstudent-coming-in-v14)<br>
4. [FAQ](#4-faq)
5. [Command Summary](#5-command-summary)<br>
   5.1 [Module commands](#51-module-commands)<br>
   5.2 [Tutorial group commands](#52-tutorial-group-commands)<br>
   5.3 [Student commands](#53-student-commands)<br>

## 1. Introduction

Trackr is suited for teaching assistants (TAs) who prefer to use a desktop application for managing their student records. It is optimized for Command Line Interface (CLI), while still retaining the benefits of a Graphical User Interface (GUI). If you are a TA with a fast typing speed, Trackr is the app for you. Head over to (Section 2, "Quick Start") and get started!

## 2. Quick Start

Get started by installing our app with the following steps:

1. Ensure you have Java 11 or above installed.
2. Download the latest trackr.jar [here](https://github.com/AY2021S1-CS2103T-W12-2/tp/releases).
3. Copy the file to a folder you wish to use as your home folder.
4. Double-click the file to start the app. The GUI should appear in a few seconds. Shown below is an example with some user commands and the app's responses.

![Ui](images/Ui.png)

5. Type your command in the command box and press Enter to execute it. e.g. typing help and pressing Enter will open the help window.

## 3. Features

### 3.1 Command format

-   Words in UPPER_CASE are the parameters to be supplied by the user.
    e.g. in add n/NAME, NAME is a parameter which can be used as add n/John Doe.
-   Items in square brackets are optional.
    e.g n/NAME [t/TAG] can be used as n/John Doe t/friend or as n/John Doe.
-   Items with …  after them can be used multiple times including zero times.
    e.g. [t/TAG]…  can be used as (i.e. 0 times), t/friend, t/friend t/family etc.
-   Parameters can be in any order.
    e.g. if the command specifies n/NAME p/PHONE_NUMBER, p/PHONE_NUMBER n/NAME is also acceptable.

### 3.2 View help: `help`

Shows a message explaining how to access the user guide.

Format: `help`

### 3.3 Module features

### 3.4 Tutorial group features

### 3.5 Student features

Note: You should perform the following features while in the Student view.

#### 3.5.1 Add a student: `addStudent`

Adds a student with your provided details.

Format: `addStudent n/NAME p/PHONE_NUMBER e/EMAIL id/STUDENT_ID [t/TAG]...`

Note:

- Students should not share the same name within the same tutorial group.
- Phone numbers should only be 8 digits long.
- Student IDs begin and end with a **capital letter** and should have 7 digits (e.g. A1243567X).
- A student can have any number of tags, including 0.

Example:

- Adds a student called _John Tan_ with phone number _81234567_, email _johntan@u.nus.edu_, student id _A1234567X_
and tag _student_ to the current tutorial group in view.

    - `addStudent n/John Tan p/81234567 e/johntan@u.nus.edu id/A1234567X t/student`
    
Expected Outcome:

- From the example above, the result box will display the following message:

    New student added: {to be filled up}
    
{insert screenshot of addStudent with the above parameters}

#### 3.5.2 Delete a student: `deleteStudent`

Deletes a student based on the given `INDEX`.

Format: `deleteStudent INDEX`

Note:

- `INDEX` refers to the index number shown in the Student view.
- `INDEX` must be a positive integer starting from 1.
- Deleting a student is **irreversible**.

Example:

- Deletes the second student in the Student view.
    
    - `deleteStudent 2`
    
Expected Outcome:

- From the example given above, the result box will display the following message:

    Deleted student: {to be filled up}
    
{insert screenshot of deleteStudent with the above parameters}

#### 3.5.3 Find a student: `findStudent`

Finds and lists all students in the current Student view whose field contains any of the given keywords.

Format: `findStudent KEYWORD`

Note:

- `KEYWORD` is not case-sensitive (e.g. _john_ will match _John_).
- The search will look for matches in the student's name and student ID.
- If no student matching the keyword is found, the Student view will be empty.

Example:

- Finds a student with `KEYWORD` _a1234567x_.

    - `findStudent a1234567x`
    
Expected Outcome:

- From the example given above, the Student view will display the students matching the criteria:
    
{insert screenshot of findStudent with the above parameters}

#### 3.5.4 Edit a student: `editStudent` [coming in v1.4]

Edits a student with the provided details.

## 4. FAQ

Q: How to save data?<br>
A: Data will be saved automatically when Trackr is closed.

## 5. Command Summary

### 5.1 Module commands

### 5.2 Tutorial group commands

### 5.3 Student commands

Command | Summary
--------|--------
`addStudent n/NAME p/PHONE_NUMBER e/EMAIL id/STUDENT_ID [t/TAG]...` | Adds a new student to the current Student view
`deleteStudent INDEX` | Deletes a student from the current Student view
`findStudent KEYWORD` | Finds student(s) that contain the keyword in the current Student view