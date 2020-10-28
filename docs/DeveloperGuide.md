---
layout: page
title: Developer Guide
---
* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## **Introduction**

### **Purpose**
This document specified architecture, software design decisions and features for the application, ProductiveNUS. It will provide you with the essential information on its development process. 

### **Scope**
The intended audience of this document are developers, designers, and software testers.

#### **About ProductiveNUS**
ProductiveNUS is a desktop application targeted at Computing students of National University of Singapore (NUS) to help them manage and schedule their academic tasks efficiently.


--------------------------------------------------------------------------------------------------------------------

--------------------------------------------------------------------------------------------------------------------

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

## **Design**

### Architecture

<img src="images/ArchitectureDiagram.png" width="450" />

The ***Architecture Diagram*** given above explains the high-level design of the App. Given below is a quick overview of each component.

<div markdown="span" class="alert alert-primary">

:bulb: **Tip:** The `.puml` files used to create diagrams in this document can be found in the [diagrams](https://github.com/se-edu/addressbook-level3/tree/master/docs/diagrams/) folder. Refer to the [_PlantUML Tutorial_ at se-edu/guides](https://se-education.org/guides/tutorials/plantUml.html) to learn how to create and edit diagrams.

</div>

**`Main`** has two classes called [`Main`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/Main.java) and [`MainApp`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/MainApp.java). It is responsible for,
* At app launch: Initializes the components in the correct sequence, and connects them up with each other.
* At shut down: Shuts down the components and invokes cleanup methods where necessary.

[**`Commons`**](#common-classes) represents a collection of classes used by multiple other components.

The rest of the App consists of four components.

* [**`UI`**](#ui-component): The UI of the App.
* [**`Logic`**](#logic-component): The command executor.
* [**`Model`**](#model-component): Holds the data of the App in memory.
* [**`Storage`**](#storage-component): Reads data from, and writes data to, the hard disk.

Each of the four components,

* defines its *API* in an `interface` with the same name as the Component.
* exposes its functionality using a concrete `{Component Name}Manager` class (which implements the corresponding API `interface` mentioned in the previous point.

For example, the `Logic` component (see the class diagram given below) defines its API in the `Logic.java` interface and exposes its functionality using the `LogicManager.java` class which implements the `Logic` interface.

![Class Diagram of the Logic Component](images/LogicClassDiagram.png)

**How the architecture components interact with each other**

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues the command `delete 1`.

<img src="images/ArchitectureSequenceDiagram.png" width="574" />

The sections below give more details of each component.

### UI component

![Structure of the UI Component](images/UiClassDiagram.png)

**API** :
[`Ui.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/Ui.java)

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `PersonListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class.

The `UI` component uses JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* Executes user commands using the `Logic` component.
* Listens for changes to `Model` data so that the UI can be updated with the modified data.

### Logic component

![Structure of the Logic Component](images/LogicClassDiagram.png)

**API** :
[`Logic.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/logic/Logic.java)

1. `Logic` uses the `AddressBookParser` class to parse the user command.
1. This results in a `Command` object which is executed by the `LogicManager`.
1. The command execution can affect the `Model` (e.g. adding an assignment).
1. The result of the command execution is encapsulated as a `CommandResult` object which is passed back to the `UI`.
1. In addition, the `CommandResult` object can also instruct the `UI` to perform certain actions, such as displaying help to the user.

Given below is the Sequence Diagram for interactions within the `Logic` component for the `execute("delete 1")` API call.

![Interactions Inside the Logic Component for the `delete 1` Command](images/DeleteSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `DeleteCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>

### Model component

![Structure of the Model Component](images/ModelClassDiagram.png)

**API** : [`Model.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/model/Model.java)

The `Model`,

* stores a `UserPref` object that represents the user’s preferences.
* stores the data in ProductiveNUS.
* exposes an unmodifiable `ObservableList<Assignment>` and an unmodifiable `ObservableList<Task>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.

### Storage component

![Structure of the Storage Component](images/StorageClassDiagram.png)

**API** : [`Storage.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/storage/Storage.java)

The `Storage` component,
* can save `UserPref` objects in json format and read it back.
* can save assignment and lesson data in json format and read it back.

### Common classes

Classes used by multiple components are in the `seedu.addressbook.commons` package.

--------------------------------------------------------------------------------------------------------------------

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

### \[Implemented\] Import timetable feature
The user can import information about their lessons into ProductiveNUS using their NUSMods timetable URL.

#### Reasons for implementation:
Users may find it inconvenient to constantly refer to their NUSMods timetable whenever they want to check if they are
available on a specific date and time. By giving users the option to add their lesson information into ProductiveNUS,
it will help increase the user's convenience as all their academic related schedule can be found in one place.

ProdutiveNUS can also better schedule the user's work with their timetable information available, avoiding any clashes
in schedule.

#### Current implementation:
- The import command is a typical command used in ProductiveNUS. It extends `Command` and overrides the method `execute`
in `CommandResult`. `ImportCommandParser` implements `Parser<ImportCommand>` and it parses the user's input to return an
`ImportCommand` object. The constructor of `ImportCommand` takes in the prefix url/ and the user's NUSMods timetable
url.

- A call to `TimetableRetriever` will be made. `TimetableRetriever` takes the user's timetable data which was parsed by
`ImportCommandParser` and makes a HTTP GET request to NUSMods API. NUSMods sends `TimetableRetriever` the relevant JSON
data. The data is parsed and returns as a list of `Lessons`.

The following sequence diagram shows the sequence when LogicManager executes `import` command.
![Interactions Inside the Logic Component for the `import url/URL` Command](images/ImportSequenceDiagram.png)

1. The `execute` method of `LogicManager` is called when a user keys in an input into the application and `execute`
takes in the input.
2. The `parseCommand` method of `ProductiveNusParser` parses the user input and returns an initialized
`ImportCommandParser` object and further calls the `parse` method of this object to identify the URL in the user input.
3. It calls the `TimetableUrlParser` with the URL and it returns a `TimetableData` object.
4. `ImportCommandParser` returns an `ImportCommand` object.
5. There is return call to `LogicManager` which then calls the overridden `execute` method of `ImportCommand`.
6. The `execute` method of `ImportCommand` will call the `retrieveLessons` method from `TimetableRetriever`, which
 returns a list of lessons to be added.
7. The `execute` method returns a `CommandResult` object.

### \[Implemented\] Schedule an assignment

The user can input a deadline and expected time for an assignment to get a suggested start time and end time to work on the assignment.
The suggested time will be within working hours from 6am to 11pm local time.
The expected hours for an assignment ranges from 1 to 5 hours.
The suggested time will not class with any of the suggested time for other assignments and lessons.

#### Reasons for Implementation

User may find it convenient to be suggested a time slot where they can do their assignment before a specific date and at a
specific time which he is free from all lessons and other assignment.

#### Current Implementation
- The schedule command is a typical command used in ProductiveNUS. It extends `Command` and overrides the method `execute` in `CommandResult`.

- `ScheduleCommandParser` implements `Parser<ScheduleCommand>` and it parses the user's input to return a `ScheduleCommand` object.

- The constructor of `ScheduleCommand` takes in (`Index`, `ExpectedHours`, `DoBefore`) where `Index` is a zero-based index
with the prefix (expected/, dobefore/) in the user's input. 
 
- The suggested schedule will be display in the assignment card shown in list.
 
It implements the following operations:
* `schedule 3 expected/2 dobefore/01-01-2001 0101` - Suggest schedule for the 3rd assignment in the displayed assignment list
with expected hours of 2 and need to be done before 01:01 01-01-2001.
* `schedule 2 expected/5 dobefore/02-02-2002 0202` - Suggest schedule for the 2nd assignment in the displayed assignment 
with expected hours of 5 and need to be done before 02:02 02-02-2002.

#### Usage Scenario

A usage scenario would be when a user wants to schedule an assignment.

1. The `execute` method of `LogicManager` is called when a user keys in an input into the application and `execute` takes in the input.
2. The `parseCommand` method of `ProductiveNusParser` parses the user input and returns an initialized `ScheduleCommandParser` object and further calls the `parse` method of this object to identify keywords and prefixes in the user input.
3. If user input is valid, it returns a `ScheduleCommand` object, which takes in a predicate. (`ExpectedHours` in this example user input)
4. There is return call to `LogicManager` which then calls the overridden `execute` method of `ScheduleCommand`.
6. The `execute` method returns a `ScheduleResult` object.

### Find by specific fields feature

The user can find assignments by providing keywords of the following fields:
- Name of assignment
- Module code of assignment
- Due date or time of assignment
- Priority of assignment

The user can find assignments with single or multiple keywords of the same type of field.

#### Reasons for Implementation

If the user can search by only one field, it would restrict the user's process of viewing assignments.
As a student user, the following scenarios are likely:
- The user wants to search for assignments with the highest priority, so that he knows what assignments to complete first.
- The user wants to search for assignments due on a particular date or time, so that he can complete it and submit his assignment on time.
- The user wants to view the details of one particular assignment with a specific name.
- The user wants to complete all assignments under a certain module first, before moving on with his next task.

We thus concluded that finding by specific fields would be beneficial for users, and it would make it easier and more convenient for them to view assignments based on their needs.

#### Current Implementation

##### Prefixes used in identifying keywords
The use of prefixes before keywords allows for validation of keywords in the user's input, with Regular Expressions.

The following prefixes are used to identify the fields:
- /n for Name
- /mod for Module code
- /d for Due date or time
- /p for Priority

##### Predicate classes 
The following Predicate classes implements `Predicate<Assignment>` and are used when the user inputs keywords of its assigned field:
- NameContainsKeywordsPredicate
- ModuleCodeContainsKeywordsPredicate
- DeadlineContainsKeywordsPredicate
- PriorityContainsKeywordsPredicate

Given below is the class diagram of these Predicate classes:

------ CLASS DIAGRAM---------



##### FindCommand Class
- `FindCommand` extends abstract class `Command` and overrides the method `execute` in `CommandResult`.

- The constructor of `FindCommand` takes in a Predicate depending on the prefix or keywords in the user's input. 

##### FindCommandParser Class
- The `FindCommandParser` class contains private methods to parse each type of keyword field, and to check for valid input format.
- `FindCommandParser` implements `Parser<FindCommand>` and it parses the user's input to return a `FindCommand` object.

Given below is the class diagram of `FindCommandParser` class:






------ CLASS DIAGRAM---------










#### Usage Scenario

The following is a usage scenario of when a user wants to find assignments with the name 'Lab'.

Given below is the sequence diagram for the interactions within `LogicManager` for the `execute(find n/Lab)` API call.
![Interactions Inside the Logic Component for the `find n/Lab` Command](images/FindSequenceDiagram.png)

1. The `execute` method of `LogicManager` is called when a user keys in an input into the application and `execute` takes in the input.
2. The `parseCommand` method of `ProductiveNusParser` parses the user input and returns an initialized `FindCommandParser` object and further calls the `parse` method of this object to identify keywords and prefixes in the user input.
3. If user input is valid, it returns a `FindCommand` object, which takes in `NameContainsKeywordsPredicate` with the list of keywords.
4. There is return call to `LogicManager` which then calls the overridden `execute` method of `FindCommand`.
5. The `execute` method of `FindCommand` will call the `updateFilteredAssignmentList` method and then the `getFilteredAssignmentListMethod` of the `Model` object.
6. The `execute` method returns a `CommandResult` object.



### \[Implemented\] Remind assignments feature
The user can set reminders for assignments.
Reminded assignments will be displayed in the `Your Reminders` section in ProductiveNUS for easy referral.

#### Reasons for Implementation
It is likely that the user will want to receive reminders for assignments with deadlines that are far away, so that he will not forget to complete those assignments. It is also likely that the user will want to receive reminders for assignments that require more attention, so that he will know which assignments to focus on and plan his time accordingly.

Displaying reminded assignments in a list separate from the main assignment list allows for easy referral and is hence more convenient for the user.

#### Current Implementation
- The remind command is a typical command used in ProductiveNUS.
- It extends `Command` and overrides the method `execute` in `CommandResult`.
- `RemindCommandParser` implements `Parser<RemindCommand>` and it parses the user's input to return a `RemindCommand` object.
- The constructor of `RemindCommand` takes in an `Index` which is parsed from the zero based index of the user's input.

It implements the following operations:
* `remind 3` - Sets reminders for the 3rd assignment in the displayed assignment list.
* `remind 2` - Sets reminders for the 2nd assignment in the displayed assignment list.



### \[Implemented\] List by days feature

The user can list all his assignments (`list` without a subsequent argument index), or list assignments with deadlines 
within a number of days from the current date (and time), with the number being the user input after `list`. 

#### Reasons for Implementation
It is likely that the user will want to view assignments that are due within days (soon) from the current date, so that he will know which assignments to complete first in order to meet the deadlines.
It is different from the `find` command as users can list all assignments with deadlines within a period of time (from the current date and time to a number of days later, depending on the index he keys in).
`find` by deadline (date or time) will only display assignments due on this particular day or time.

It also provides a more intuitive approach for users to view assignments that are more urgent to complete.

#### Current Implementation
- The list command is a typical command used in ProductiveNUS. 
- It extends `Command` and overrides the method `execute` in `CommandResult`.
- `ListCommandParser` implements `Parser<ListCommand>` and it parses the user's input to return a `ListCommand` object.
- The constructor of `ListCommand` takes in an `Index` which is parsed from the zero based index of the user's input.

It implements the following operations:
* `list` — Lists all assignments stored in ProductiveNUS.
* `list 3` — Lists assignments with deadline 3 days (72 hours) from the current date. (and current time)
* `list 2` — Lists assignments with deadline 2 days (48 hours) from the current date. (and current time)

### \[Coming up\] Delete multiple assignments feature
The user can delete multiple assignments at a time, when more than one index is keyed in.

#### Reasons for Implementation
It will provide convenience to users who want to delete more than one assignment at a time, and it makes the deleting process faster.


#### Current Implementation
- The `delete` command is a typical command used in ProductiveNUS. 
- It extends `Command` and overrides the method `execute` in `CommandResult`.
- `DeleteCommandParser` implements `Parser<DeleteCommand>` and it parses the user's input (index of the assignment as a positive integer)) to return a `DeleteCommand` object.
- The constructor of `DeleteCommand` takes in an `Index` which is parsed from the one based index of the user's input.
 
It can implement the following operations:
* `delete 1 3` — Deletes the assignment at the first and third index in list.
* `delete 1` — Deletes the assignment at the first index in list.

### \[Coming up\] Help feature

### \[Implemented\] Undo

The user can undo the most recent command that changes the data of the assignments or lessons.

#### Reasons for Implementation
It is likely that the user might type in command mistakenly will want to go the previous state.
Instead of using a combination of adding, deleting, editting, ..., a single undo command will 
help solving the problem easily.

#### Current Implementation
- The undo command is a typical command used in ProductiveNUS. 
- It extends `Command` and overrides the method `execute` in `CommandResult`.

It implements the following operations:
* `Undo` — Undo the most recent command that changes the data of the assignments or lessons.

#### Usage Scenario

A usage scenario would be when a user wants to undo the most recent command that changes the data of the assignments

1. The `execute` method of `LogicManager` is called when a user keys in an input into the application and `execute` takes in the input.
2. The `execute` calls the `UndoCommand`.
4. There is return call to `LogicManager` which then calls the overridden `execute` method of `UndoCommand`.
5. The `execute` method of `UndoCommand` will call the `getPreviousModel` of the `Model` object and reassign `Model`.
6. The `execute` method returns a `CommandResult` object.

### \[Coming up\] Delete multiple assignments feature
The user can delete multiple assignments at a time, when more than one index is keyed in.

#### Reasons for Implementation
It will provide convenience to users who want to delete more than one assignment at a time, and it makes the deleting process faster.


#### Current Implementation
- The `delete` command is a typical command used in ProductiveNUS. 
- It extends `Command` and overrides the method `execute` in `CommandResult`.
- `DeleteCommandParser` implements `Parser<DeleteCommand>` and it parses the user's input (index of the assignment as a positive integer)) to return a `DeleteCommand` object.
- The constructor of `DeleteCommand` takes in an `Index` which is parsed from the one based index of the user's input.
 
It can implement the following operations:
* `delete 1 3` — Deletes the assignment at the first and third index in list.
* `delete 1` — Deletes the assignment at the first index in list.

### \[Proposed\] Data archiving

_{Explain here how the data archiving feature will be implemented}_


--------------------------------------------------------------------------------------------------------------------

## **Documentation, logging, testing, configuration, dev-ops**

* [Documentation guide](Documentation.md)
* [Testing guide](Testing.md)
* [Logging guide](Logging.md)
* [Configuration guide](Configuration.md)
* [DevOps guide](DevOps.md)

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Requirements**

### Product scope

**Target user profile**:
* NUS Computing Students with poor time management skills who have difficulties managing their weekly academic schedule
* prefer desktop apps over other types
* can type fast and prefer typing to mouse interactions
* is reasonably comfortable using CLI apps

**Value proposition**:
* More convenient than typical apps as lessons and assignments are managed in just one app so there is no need to switch between different ones.
* Faster than typical mouse/GUI driven apps as most features are accomplished by typing simple commands.
* Easier to manage schedule than typical scheduling apps as assignments are automatically scheduled.

### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​                                    | I want to …​                     | So that I can…​                                                        |
| -------- | ------------------------------------------ | ------------------------------ | ---------------------------------------------------------------------- |
| `* * *`  | student                                    | import my timetable         | better schedule my assignments with my lesson timings taken into account              |
| `* * *`  | forgetful student                                       | receive reminders for my lessons and assignments               |  avoid forgetting to attend lessons or do my work                                                                      |
| `* * *`  | poor time manager                                       | add and schedule assignments                |  keep track of what needs to be done                                 |
| `* * *`  | poor time manager                                       | delete assignments          | remove assignments that i have completed or added wrongly |
| `* * *`    | student                                       | view lessons and assignments together   | view all assignments i have to complete amidst my lessons                |
| `* * *`      | particular student | use a scheduler with a user-friendly interface           | use the application easily and enjoyably                                                 |
| `* * *`      | new user | navigate the UI easily           | use the application efficiently                                                 |                                      |
| `* * *`      | slow/confused student | i can access and view my academic duties easily           | quickly find out what i need to do for the week
| `* * *`      | experienced vim-user | use my keyboard to key in assignments           | save time tracking down my assignments                                                 ||
| `* * `      | beginner user | view a tutorial           | benefit from the features of ProductiveNUS                                                 ||
| `* * `      | experienced vim-user | use shortcuts in my commands            | access my academic schedule more quickly                                              ||
*{More to be added}*

### Use cases

(For all use cases below, the **System** is the `Academic Schedule Manager` and the **Actor** is the `user`, unless specified otherwise)

**Use case: UC01 - Add an assignment**

**MSS**

1.  User requests to add an assignment.
2.  Academic Schedule Manager adds the assignment.
3.  Academic Schedule Manager shows a success message with details of the assignment added.

    Use case ends.

**Extensions**

* 1a. The user did not supply all required parameters.

    * 1a1. Academic Schedule Manager shows an error message.

      Use case ends.


* 1b. The given DEADLINE_OF_ASSIGNMENT parameter is in the wrong format.

    * 1b1. Academic Schedule Manager shows an error message.

      Use case ends.


**Use case: UC02 - Delete an assignment**

**MSS**

1.  User <ins>requests to list assignments and lessons (UC05)</ins>.
2.  User requests to delete a specific assignment in the list.
3.  Academic Schedule Manager shows a success message with details of the assignment deleted.

    Use case ends.

**Extensions**

* 2a. The given index is invalid (index is referring to a lesson or index is out of range).

    * 2a1. Academic Schedule Manager shows an error message.

      Use case resumes at step 2.


**Use case: UC03 - Import timetable**

**MSS**

1.  User retrieves NUSMods timetable URL from the NUSMods website.
2.  User requests to import NUSMods timetable using their NUSMods timetable URL.
3.  Academic Schedule Manager adds all the lessons according to the data retrieved.
4.  Academic Schedule Manager shows a success message with details of the lessons added.

    Use case ends.

**Extensions**

* 2a. The given URL is invalid (not a valid NUSMods timetable URL).

    * 2a1. Academic Schedule Manager shows an error message.

      Use case resumes at step 2.

* 2b. User already has a timetable imported before.

    * 2b1. Academic Schedule Manager informs user that previously imported lesson will be deleted.
    
      Use case resumes at step 3.


**Use case: UC04 - Remind**

**MSS**

1.  User <ins>requests to list assignments and lessons (UC05)</ins>.
2.  User requests to set reminder for a specific assignment in the list.
3.  Academic Schedule Manager adds the assignment to the remind list.
4.  Academic Schedule Manager shows a success message with details of the assignment set as reminder.

    Use case ends.

**Extensions**

* 2a. The given index is invalid (index is referring to a lesson or index is out of range).

    * 2a1. Academic Schedule Manager shows an error message.

      Use case resumes at step 2.


**Use case: UC05 - List assignments and lessons**

**MSS**

1.  User requests to list assignments and lessons.
2.  Academic Schedule Manager shows a list of all assignments and lessons.

    Use case ends.

**Extensions**

* 1a. User requests to list XX day(s) of assignments and lessons.

    * 1a1. Academic Schedule Manager shows a filtered list of assignments and lessons.

        Use case ends.
      
* 2a. The list is empty.

        Use case ends.
    
### Non-Functional Requirements

1.  Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2.  Should be able to hold up to `1000 lessons and assignments` without a noticeable sluggishness in performance for typical usage.
3.  A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
4.  The system should respond within two seconds.
5.  The system should save a user's data `after every user command`.
6.  The system should be usable by a novice who has never used the app before.

*{More to be added}*

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, OS-X
* **User**: NUS Computing student

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.

<div markdown="span" class="alert alert-info">:information_source: **Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

</div>

### Launch and shutdown

1. Initial launch

   1. Download the jar file and copy into an empty folder

   1. Double-click the jar file Expected: Shows the GUI with a set of sample contacts. The window size may not be optimum.

1. Saving window preferences

   1. Resize the window to an optimum size. Move the window to a different location. Close the window.

   1. Re-launch the app by double-clicking the jar file.<br>
       Expected: The most recent window size and location is retained.

1. _{ more test cases …​ }_

### Deleting a person

1. Deleting a person while all persons are being shown

   1. Prerequisites: List all persons using the `list` command. Multiple persons in the list.

   1. Test case: `delete 1`<br>
      Expected: First contact is deleted from the list. Details of the deleted contact shown in the status message. Timestamp in the status bar is updated.

   1. Test case: `delete 0`<br>
      Expected: No person is deleted. Error details shown in the status message. Status bar remains the same.

   1. Other incorrect delete commands to try: `delete`, `delete x`, `...` (where x is larger than the list size)<br>
      Expected: Similar to previous.

1. _{ more test cases …​ }_

### Saving data

1. Dealing with missing/corrupted data files

   1. _{explain how to simulate a missing/corrupted file, and the expected behavior}_

1. _{ more test cases …​ }_
