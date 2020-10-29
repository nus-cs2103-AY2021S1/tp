# Trackr - User Guide

1. [Introduction](#1-introduction)<br>
2. [About This Document](#2-about-this-document)<br>
    2.1 [Glossary](#21-glossary)<br>
	2.2 [Formatting](#22-formatting)<br>
	2.3 [Command format](#23-command-format)<br>
3. [Getting Started](#3-getting-started)<br>
    3.1 [Setting Up](#31-setting-up)<br>
    3.2 [Using Trackr](#32-using-trackr)<br>
    3.3 [Switching Views](#33-switching-views)<br>
4. [Features](#4-features)<br>
   4.1 [View help: `help`](#41-view-help-help)<br>
   4.2 [Module features](#42-module-features)<br>
   --- 4.2.1 [View all modules: `viewMod`](#421-view-all-modules-viewmod)<br>
   --- 4.2.2 [Add a module: `addMod`](#422-add-a-module-addmod)<br>
   --- 4.2.3 [Delete a module: `deleteMod`](#423-delete-a-module-deletemod)<br>
   --- 4.2.4 [Find a module: `findMod`](#424-find-a-module-findmod)<br>
   --- 4.2.5 [Edit a module: `editMod` [coming in v1.4]](#425-edit-a-module-editmod-coming-in-v14)<br>
   4.3 [Tutorial group features](#43-tutorial-group-features)<br>
   --- 4.3.1 [View all tutorial groups in a module: `viewTG`](#431-view-all-tutorial-groups-in-a-module-viewtg)<br>
   --- 4.3.2 [Add a tutorial group to a module: `addTG`](#432-add-a-tutorial-group-to-a-module-addtg)<br>
   --- 4.3.3 [Find a tutorial group: `findTG`](#433-find-a-tutorial-group-findtg)<br>
   --- 4.3.4 [Delete a tutorial group from a module: `deleteTG`](#434-delete-a-tutorial-group-from-a-module-deletetg)<br>
   4.4 [Student features](#44-student-features)<br>
   --- 4.4.1 [View all students in a tutorial group: `viewStudent`](#441-view-all-students-in-a-tutorial-group-viewstudent)<br>
   --- 4.4.2 [Add a student: `addStudent`](#442-add-a-student-addstudent)<br>
   --- 4.4.3 [Delete a student: `deleteStudent`](#443-delete-a-student-deletestudent)<br>
   --- 4.4.4 [Find a student: `findStudent`](#444-find-a-student-findstudent)<br>
   --- 4.4.5 [Edit a student: `editStudent` [coming in v1.4]](#445-edit-a-student-editstudent-coming-in-v14)<br>
5. [FAQ](#5-faq)<br>
6. [Command Summary](#6-command-summary)<br>
   6.1 [Module commands](#61-module-commands)<br>
   6.2 [Tutorial group commands](#62-tutorial-group-commands)<br>
   6.3 [Student commands](#63-student-commands)<br>

## 1. Introduction

__Trackr__ is an application for teaching assistants (TAs) who prefer to use a desktop application for managing their 
student records. It is uses a Command Line Interface (CLI), while still retaining the benefits of a Graphical User 
Interface (GUI). If you are a TA with a fast typing speed, __Trackr__ is the app for you! You can start by familiarising 
yourself with the information regarding how this document is designed in 
[Section 2, "About this document"](#2-about-this-document).



## 2. About this document

Welcome to the __Trackr__ User Guide! In this document you will be able to learn the features that __Trackr__ can 
provide to make your TA experience less troublesome. This section will provide information regarding the use of this 
document, namely how to navigate through this document, what the technical terms mean and how to interpret the 
formatting used.

### 2.1 Glossary

The table below explains certain terms used in the document to describe different parts or aspects of the application.

| Term                           | Meaning                                                      |
| ------------------------------ | ------------------------------------------------------------ |
| Graphical User Interface (GUI) | An interface through which users interact with electronic devices via visual indicator representations. |
| Command Line Interface (CLI)   | An interface that processes commands to a computer program in the form of lines of text. |
| Command                        | An instruction typed by you, the user for **Trackr** to perform. |
| Field                          | A segment of a command that requires information to be filled into. |
| Parameter                      | The information filled by you, the user, into the field of a command. |
| Prefix                         | A short word that is typed right before you, the user, writes the parameter into the command. It always starts with a slash (/) and typically is an abbreviation of the name of the field. |
| Tutorial Group                 | The specific class being taught under a given module.        |
| Student                        | An individual who is enrolled in a given module.             |

### 2.2 Formatting

This document is written in a manner where formatting is applied to text in order to convey different meanings. The table below will explain what these formatting uses are, what they look like, and what they mean in the context of the document.

| Formatting    | Meaning                                                      |
| ------------- | ------------------------------------------------------------ |
| `code`        | Words in 'code' format refer to commands (or fields in commands) that can be typed into the command line. |
| *Italics*     | Words in italics refer to example parameters, which is what you, the user could type as the parameter that needs to be provided. |
| [Underline]() | Words that are underlined refer to hyperlinks. Hyperlinks are clickable, and bring you either to different parts of this document or to external websites, for the convenience of navigating quickly to different parts of the document or the web. |
| **Bold**      | The name of the application **Trackr** is written in bold to refer to what the application does or how it responds to user input. |

### 2.3 Command Format

Commands you provide in the command line follow specific rules. These are the rules you have to follow when typing commands for **Trackr** to read:

-   Words in UPPER_CASE are the parameters to be supplied by the user.
    e.g. in add n/NAME, NAME is a parameter which can be used as add n/John Doe.
-   Items in square brackets are optional.
    e.g n/NAME [t/TAG] can be used as n/John Doe t/friend or as n/John Doe.
-   Items with …  after them can be used multiple times including zero times.
    e.g. [t/TAG]…  can be used as (i.e. 0 times), t/friend, t/friend t/family etc.
-   Parameters can be in any order.
    e.g. if the command specifies n/NAME p/PHONE_NUMBER, p/PHONE_NUMBER n/NAME is also acceptable.



## 3. Getting Started

### 3.1 Setting Up
Get started by installing __Trackr__ with the following steps:

1. Ensure you have Java 11 or above installed. The latest version of Java can be found [here](https://www.java.com/en/download/).
2. Download the latest version of __Trackr__ [here](https://github.com/AY2021S1-CS2103T-W12-2/tp/releases). Look for the file trackr.jar.
3. Copy the file to a folder you wish to use as your home folder for __Trackr__
4. Double-click trackr.jar to start the app. The GUI (Figure 3.1) should appear in a few seconds.

![Ui](images/Ui.png) <br>
Figure 3.1 Trackr GUI

### 3.2 Using Trackr
__Trackr__ boasts many useful features to make using the app seamless and smooth. These include
commands to add or delete modules, tutorial groups, or students you are teaching. To use a feature, simply
type the command into the command box (see Figure 3.2) and press _Enter_ to execute it. Further explanations of 
all the commands are listed in [Section 4, "Features"](#4-features).

![CommandExample](images/CommandExample.png) <br>
Figure 3.2 Command example

### 3.3 Switching Views
__Trackr__ allows you to switch the current view from `Modules` (Figure 3.31) to `Tutorial Groups` (Figure 3.32) 
to `Students` (Figure 3.33) depending on your current need. This is done using the commands `viewMod`, `viewTG`, and `viewStudent`. 
The correct syntax for these commands are shown in [Section 4, "Features"](#4-features).

![ModuleView](images/ModuleView.png) <br>
Figure 3.31 Module View

![TutorialGroupView](images/TutorialGroupView.png) <br>
Figure 3.32 Tutorial Group View

![StudentView](images/StudentView.png) <br>
Figure 3.33 Student view


## 4. Features

### 4.1 View help: `help`

Shows a message explaining how to access the user guide.

Format: `help`

### 4.2 Module features

#### 4.2.1 View all modules: `viewMod`

Shows all the modules you have added in the Module view.

Format: `viewModule`

> You should perform the following features while in the Module view.

#### 4.2.2 Add a module: `addMod`

Adds a module to the module list.

Format: `addMod MODULE_CODE`

Note: 

- Modules should not share the same name.
- It is recommended to use the exact code of the module.

Example:

- Adds a module _CS2100_ to the module list.
    - `addMod CS2100`

Expected Outcome:

- From the example above, the result box will display the following message:

    New module added: CS2100
    
#### 4.2.3 Delete a module: `deleteMod`

Deletes a module based on the given `INDEX`

Format: `deleteMod INDEX`

Note: 

- `INDEX` refers to the index number shown in the Module view.
- `INDEX` must be a positive integer starting from 1.
- Deleting a module is irreversible.

Example:

- Deletes a module at index _2_ in the module list.
    - `deleteMod 2`

Expected Outcome:

- From the example above, the result box will display the following message:

    Module deleted: module at index 2

#### 4.2.4 Find a module: `findMod`

Finds and lists all modules in the current Module view whose field contains any of the given keywords.

Format: `findMod KEYWORD`

Note:

- `KEYWORD` is not case-sensitive (e.g. _cs2100_ will match _CS2100_).
- The search will look for matches in the module's code.
- If no module matching the keyword is found, the Module view will be empty.

Example:

- Finds a module with `KEYWORD` _cs2100_.

    - `findMod cs2100`

Expected Outcome:

- From the example given above, the Module view will display the modules matching the criteria:

#### 4.2.5 Edit a module: `editMod` [coming in v1.4]

Edits a module with the provided details.

### 4.3 Tutorial Group features

#### 4.3.1 View all Tutorial Groups in a Module: `viewTG`
You can display all Tutorial Groups in a Module with this command. You can indicate which Module's Tutorial Groups to view by entering its index in the current list.

Format: `viewTG MODULE_INDEX`
Where MODULE_INDEX is the target Module's index in the currently displayed Module List.

Example: `viewTG 1`

You can also use this command to return to the view of all Tutorial Groups in a Module whilst you are viewing all Students in a Tutorial Group by entering the command without an index.

Format: `viewTG`

#### 4.3.2 Add a Tutorial Group to a Module: `addTG`
You can add a Tutorial Group to the Module you are viewing using this command.

> You can only use this command when you are viewing all Tutorial Groups of the Module you intend to add the Tutorial Group to.
> Refer to 4.2.1 - View all Tutorial Groups in a Module

Format: `addTG tg/TG_CODE`

Example: `addTG tg/T03`

#### 4.3.3 Find a Tutorial Group: `findTG`
If you need to quickly find a Tutorial from a list of Tutorials, use this command.

> You can only use this command when you are viewing the Module which you are looking for the target Tutorial Group in. 
> Refer to 4.2.1 - View all Tutorial Groups in a Module

Format: `findTG TARGET_TG_CODE`
Where TARGET_TG_CODE is the code of the Tutorial Group you want to search for.

Example: `findTG B014`
To search for a Tutorial Group with code B014

#### 4.3.4 Delete a Tutorial Group from a Module: `deleteTG`
You can use this command to delete a Tutorial Group you have previously added to a Module.
> You can only use this command when you are viewing the Module which you are looking for the target Tutorial Group in. 
> Refer to 4.2.1 - View all Tutorial Groups in a Module

Format: `deleteTG TG_INDEX`
Where TG_INDEX is the index of the Tutorial Group you want to delete.

Example: `deleteTG 2`
To delete Tutorial Group with the index 2 in the currently displayed list.

These are the commands you can use to manage the different `Tutorial Groups` you teach. Now we look at how you can manage your `Students`.

### 4.4 Student features

#### 4.4.1 View all students in a tutorial group: `viewStudent`

Shows all students within the given tutorial group.

Format: `viewStudent INDEX`

Note:

- `INDEX` refers to the index number shown in the Tutorial Group view.
- `INDEX` must be a positive integer starting from 1.

Example:

- Views all students of tutorial group _T03_.
    - `viewStudent T03`

> You should perform the following features while in the Student view.

#### 4.4.2 Add a student: `addStudent`

Adds a student with your provided details.

Format: `addStudent n/NAME p/PHONE_NUMBER e/EMAIL id/STUDENT_ID [t/TAG]...`

Note:

- Students should not share the same name within the same tutorial group.
- Phone numbers should only be 8 digits long.
- Student IDs begin and end with a fcapital letter and should have 7 digits (e.g. A1243567X).
- A student can have any number of tags, including 0.

Example:

- Adds a student called _John Tan_ with phone number _81234567_, email _johntan@u.nus.edu_, student id _A1234567X_
and tag _student_ to the current tutorial group in view.
  - `addStudent n/John Tan p/81234567 e/johntan@u.nus.edu id/A1234567X t/student`
  
Expected Outcome:

- From the example above, the result box will display the following message:

    New student added: New student added: John Tan Phone: 81234567 Email: johntan@u.nus.edu Student ID: A1234567X 
    Tags: [student]
    
#### 4.4.3 Delete a student: `deleteStudent`

Deletes a student based on the given `INDEX`.

Format: `deleteStudent INDEX`

Note:

- `INDEX` refers to the index number shown in the Student view.
- `INDEX` must be a positive integer starting from 1.
- Deleting a student is irreversible.

Example:

- Deletes the second student in the Student view.
  
    - `deleteStudent 2`
    
Expected Outcome:

- From the example given above, the result box will display the following message:

    Deleted student: {to be implemented by this week}
    
#### 4.4.4 Find a student: `findStudent`

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
  
#### 4.4.5 Edit a student: `editStudent` [coming in v1.4]

Edits a student with the provided details.

## 5. FAQ
__Q:__ Why did the command I input change colour from white to red? <br> 
__A:__ This happens when the input command is of the wrong syntax or does not exist. You may go to the [Command Format](#23-command-format) to 
view the list of commands and their accepted formats.

__Q:__ How do I save my data? <br>
__A:__ When you issue a new command, __Trackr__ automatically updates the local save file immediately. This ensures that
no data will be lost when you close the app or even in the event of a crash.

__Q:__ How do I exit the app safely? <br>
__A:__ To close the app, simply click the red X on the top right for Windows users, on the top left
for MacOS and Linux users.

## 6. Command Summary

### 6.1 Module commands

Command | Summary
--------|--------
`viewMod` | Views all modules in __Trackr__.
`addMod MODULE_CODE` | Adds a new module to the current Module view. 
`deleteMod INDEX` | Deletes a module from the current Module view. 
`findMod KEYWORD` | Finds module(s) that contain the keyword in the current Module view. 

### 6.2 Tutorial Group commands

Command | Summary
--------|--------
`viewTG MODULE_INDEX` | Views all Tutorial Groups in a Module. 
`addTG tg/TG_CODE` | Adds a Tutorial Group to a Module. 
`findTG TARGET_TG_CODE` | Finds a Tutorial Group. 
`deleteTG TG_INDEX` | Deletes a Tutorial Group from a Module. 

### 6.3 Student commands

Command | Summary
--------|--------
`viewStudent INDEX` | Views all students in the given tutorial group.
`addStudent n/NAME p/PHONE_NUMBER e/EMAIL id/STUDENT_ID [t/TAG]...` | Adds a new student to the current Student view. 
`deleteStudent INDEX` | Deletes a student from the current Student view. 
`findStudent KEYWORD` | Finds student(s) that contain the keyword in the current Student view. 
