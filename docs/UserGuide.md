# Trackr - User Guide

1. [Introduction](#1-introduction)
2. [Quick Start](#2-quick-start)
3. [Features](#3-features)<br>
   3.1 [Command format](#31-command-format)<br>
   3.2 [View help: `help`](#32-view-help-help)<br>
   3.3 [Add an item: `add`](#33-add-an-item-add)<br>
   ---3.3.1 [Add a module: `add module`](#331-add-a-module-add-module)<br>
   ---3.3.2 [Add a student: `add student`](#332-add-a-student-add-student)<br>
   ---3.3.3 [Add a reminder: `add reminder`](#333-add-a-reminder-add-reminder)<br>
   3.4 [List items: `list`](#34-list-items-list)<br>
   ---3.4.1 [List all modules: `list modules`](#341-list-all-modules-list-modules)<br>
   ---3.4.2 [List all students within a module: `list students`](#342-list-all-students-within-a-module-list-students)<br>
   ---3.4.3 [List all reminders: `list reminders`](#343-list-all-reminders-list-reminders)<br>
   3.5 [Filter students: `filter`](#35-filter-students-filter)<br>
   3.6 [Delete an item: `delete`](#36-delete-an-item-delete)<br>
   ---3.6.1 [Delete a module: `delete module`](#361-delete-a-module-delete-module)<br>
   ---3.6.2 [Delete a student: `delete student`](#362-delete-a-student-delete-student)<br>
   ---3.6.3 [Delete a reminder: `delete reminder`](#363-delete-a-reminder-delete-reminder)<br>
   3.7 [Search for an item: `search`](#37-search-for-an-item-search)<br>
   ---3.7.1 [Search for a module: `search module`](#371-search-for-a-module-search-module)<br>
   ---3.7.2 [Search for a student: `search student`](#372-search-for-a-student-search-student)<br>
   3.8 [Mark a reminder as done: `done`](#38-mark-a-reminder-as-done-done)<br>
4. [FAQ](#4-faq)
5. [Command Summary](#5-command-summary)

## 1. Introduction

Trackr is suited for teaching assistants (TAs) who prefer to use a desktop application for managing their student records. It is optimized for Command Line Interface (CLI), while still retaining the benefits of a Graphical User Interface (GUI). If you are a TA with a fast typing speed, Trackr is the app for you. Head over to (Section 2, "Quick Start") and get started!

## 2. Quick Start

Get started by installing our app with the following steps:

1. Ensure you have Java `11` or above installed.
2. Download the latest trackr.jar [here](https://github.com/AY2021S1-CS2103T-W12-2/tp/releases).
3. Copy the file to a folder you wish to use as your home folder.
4. Double-click the file to start the app. The GUI should appear in a few seconds. Shown below is an example with some user commands and the app's responses.

(insert ui here)

5. Type your command in the command box and press **Enter** to execute it. e.g. typing `help` and pressing **Enter** will open the help window.

## 3. Features

### 3.1 Command format

-   Words in `UPPER_CASE` are the parameters to be supplied by the user.
    e.g. in add n/NAME, NAME is a parameter which can be used as add n/John Doe.
-   Items in square brackets are optional.
    e.g n/NAME [t/TAG] can be used as n/John Doe t/friend or as n/John Doe.
-   Items with …​ after them can be used multiple times including zero times.
    e.g. [t/TAG]…​ can be used as (i.e. 0 times), t/friend, t/friend t/family etc.
-   Parameters can be in any order.
    e.g. if the command specifies n/NAME p/PHONE_NUMBER, p/PHONE_NUMBER n/NAME is also acceptable.

### 3.2 View help: `help`

Shows a message explaining how to access the user guide.

Format: `help`

### 3.3 Add an item: `add`

Adds an item to the database.

#### 3.3.1 Add a module: `add module`

Adds a module to the database.

Format: `add module MODULE_CODE`

Example: `add module CS2103T`

#### 3.3.2 Add a student: `add student`

Adds a student to the database.

Format: `add student STUDENT_NAME to MODULE_CODE`

Example: `add student John Doe to CS2103T`

#### 3.3.3 Add a reminder: `add reminder`

Adds a reminder to the database.

Format: `add reminder REMINDER`

Example: `add reminder grade CS2103T user guides`

### 3.4 List items: `list`

Displays items of a specific type in a list

#### 3.4.1 List all modules: `list modules`

Shows a list of all modules in the database.

Format: `list modules`

#### 3.4.2 List all students within a module: `list students`

Shows a list of all students taking a particular module.

Format: `list students in MODULE_CODE`

Example: `list students in CS2103T`

#### 3.4.3 List all reminders: `list reminders`

Shows a list of all reminders for upcoming tasks.

Format: `list reminders`

### 3.5 Filter students: `filter`

Filters students in a module based on certain criteria.

Format: `filter KEYWORD`

List of keywords:

-   taskNotDone
-   trailingBehind
-   notParticipating

Example: `filter taskNotDone`

### 3.6 Delete an item: `delete`

Deletes an item from the database.

#### 3.6.1 Delete a module: `delete module`

Deletes a module from the database.

Format: `delete MODULE_CODE`

Example: `delete module CS2103T`

#### 3.6.2 Delete a student: `delete student`

Deletes a student from the database. Note deleting a student will also remove him from the module.

Format: `delete student STUDENT_NAME`

Example: `delete student John Doe`

#### 3.6.3 Delete a reminder: `delete reminder`

Deletes a reminder from the database.

Format: `delete reminder REMINDER_NUMBER`

Example: `delete reminder 2`

### 3.7 Search for an item: `search`

Searches for and retrieves an item from within the database.

#### 3.7.1 Search for a module: `search module`

Searches for and retrieves a module from within the database.

Format: `search module MODULE_CODE`

Example: `search module CS2103T`

#### 3.7.2 Search for a student: `search student`

Searches for and retrieves a student from within the database.

Format: `search student STUDENT_NAME`

Example: `search student John Doe`

### 3.8 Mark a reminder as done: `done`

Marks a reminder as done upon completion of the task.

Format: `done REMINDER_NUMBER`

Example: `done 2`

## 4. FAQ

## 5. Command Summary
