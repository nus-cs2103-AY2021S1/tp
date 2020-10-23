---
layout: page
title: Developer Guide
---
* Table of Contents
{:toc}

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
1. The command execution can affect the `Model` (e.g. adding a student).
1. The result of the command execution is encapsulated as a `CommandResult` object which is passed back to the `Ui`.
1. In addition, the `CommandResult` object can also instruct the `Ui` to perform certain actions, such as displaying help to the user.

Given below is the Sequence Diagram for interactions within the `Logic` component for the `execute("delete 1")` API call.

![Interactions Inside the Logic Component for the `delete 1` Command](images/DeleteSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `DeleteCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>

### Model component

![Structure of the Model Component](images/ModelClassDiagram.png)

**API** : [`Model.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/model/Model.java)

The `Model`,

* stores a `UserPref` object that represents the user’s preferences.
* stores the address book data.
* exposes an unmodifiable `ObservableList<Person>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* does not depend on any of the other three components.


<div markdown="span" class="alert alert-info">:information_source: **Note:** An alternative (arguably, a more OOP) model is given below. It has a `Tag` list in the `AddressBook`, which `Person` references. This allows `AddressBook` to only require one `Tag` object per unique `Tag`, instead of each `Person` needing their own `Tag` object.<br>
![BetterModelClassDiagram](images/BetterModelClassDiagram.png)

</div>


### Storage component

![Structure of the Storage Component](images/StorageClassDiagram.png)

**API** : [`Storage.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/storage/Storage.java)

The `Storage` component,
* can save `UserPref` objects in json format and read it back.
* can save the address book data in json format and read it back.

### Common classes

Classes used by multiple components are in the `seedu.addressbook.commons` package.

--------------------------------------------------------------------------------------------------------------------

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

### 5.3 Overdue payment filter feature

The overdue payment filter feature allows the tutor to find all students who have not paid their tuition fees in the past month. It is handled by the `OverdueCommand`.

The following describes the flow of how `OverdueCommand` is executed.

1. Upon successfully parsing the user input, `OverdueCommand#execute(Model model)` is called to filter all students in Reeve whose last date of payment was more than a month ago.
2. `Model#updateFilteredStudentsList(Predicate<Student> predicate)` is called to find only students that match the above condition. A new `CommandResult` is returned with a successful message indicating the number of matching students.
3. The filtered student list replaces the displayed list on the GUI and a success message is shown in the result display.

The following sequence diagram shows how the question adding operation works.

![OverdueSequence](images/OverdueSequenceDiagram.png)

Figure \___. Sequence diagram for `OverdueCommand` execution

The following activity diagram summarises the flow of events when `OverdueCommand` is executed.

![OverdueActivity](images/OverdueActivityDiagram.png)

Figure \___. Activity diagram for `OverdueCommand` execution

**5.1.?** **Schedule Command**

This section describes the operations that `ScheduleCommand` performs.

**Step 1.** Upon successful parsing of the user input date into `LocalDate` , the `ScheduleCommand#execute(Model model)` method is called.

**Step 2.** The method `LocalDate#getDayOfWeek()` is then called on the `LocalDate` that is parsed from the user input to get the `dayOfWeek`.

**Step 3.** The `dayOfWeek` is then used to create a `Predicate<Student>` to check if the student has the same day as the date.

**Step 4.** Then the method `Model#updateFilteredPersonList(Predicate<Student>)` is then called to filter students based on predicate created in **Step 3**.

**Step 5.** The StudentListPanel is then populated with the students that have lesson on the day.

The following activity diagram summarizes the flow of events when the `ScheduleCommand` is being executed:

![ScheduleActivity](images/ScheduleActivityDiagram.png)

### \[Proposed\] Undo/redo feature

#### Proposed Implementation

The proposed undo/redo mechanism is facilitated by `VersionedAddressBook`. It extends `AddressBook` with an undo/redo history, stored internally as an `addressBookStateList` and `currentStatePointer`. Additionally, it implements the following operations:

* `VersionedAddressBook#commit()` — Saves the current address book state in its history.
* `VersionedAddressBook#undo()` — Restores the previous address book state from its history.
* `VersionedAddressBook#redo()` — Restores a previously undone address book state from its history.

These operations are exposed in the `Model` interface as `Model#commitAddressBook()`, `Model#undoAddressBook()` and `Model#redoAddressBook()` respectively.

Given below is an example usage scenario and how the undo/redo mechanism behaves at each step.

Step 1. The user launches the application for the first time. The `VersionedAddressBook` will be initialized with the initial address book state, and the `currentStatePointer` pointing to that single address book state.

![UndoRedoState0](images/UndoRedoState0.png)

Step 2. The user executes `delete 5` command to delete the 5th student in the address book. The `delete` command calls `Model#commitAddressBook()`, causing the modified state of the address book after the `delete 5` command executes to be saved in the `addressBookStateList`, and the `currentStatePointer` is shifted to the newly inserted address book state.

![UndoRedoState1](images/UndoRedoState1.png)

Step 3. The user executes `add n/David …​` to add a new student. The `add` command also calls `Model#commitAddressBook()`, causing another modified address book state to be saved into the `addressBookStateList`.

![UndoRedoState2](images/UndoRedoState2.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** If a command fails its execution, it will not call `Model#commitAddressBook()`, so the address book state will not be saved into the `addressBookStateList`.

</div>

Step 4. The user now decides that adding the student was a mistake, and decides to undo that action by executing the `undo` command. The `undo` command will call `Model#undoAddressBook()`, which will shift the `currentStatePointer` once to the left, pointing it to the previous address book state, and restores the address book to that state.

![UndoRedoState3](images/UndoRedoState3.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** If the `currentStatePointer` is at index 0, pointing to the initial AddressBook state, then there are no previous AddressBook states to restore. The `undo` command uses `Model#canUndoAddressBook()` to check if this is the case. If so, it will return an error to the user rather
than attempting to perform the undo.

</div>

The following sequence diagram shows how the undo operation works:

![UndoSequenceDiagram](images/UndoSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `UndoCommand` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.

</div>

The `redo` command does the opposite — it calls `Model#redoAddressBook()`, which shifts the `currentStatePointer` once to the right, pointing to the previously undone state, and restores the address book to that state.

<div markdown="span" class="alert alert-info">:information_source: **Note:** If the `currentStatePointer` is at index `addressBookStateList.size() - 1`, pointing to the latest address book state, then there are no undone AddressBook states to restore. The `redo` command uses `Model#canRedoAddressBook()` to check if this is the case. If so, it will return an error to the user rather than attempting to perform the redo.

</div>

Step 5. The user then decides to execute the command `list`. Commands that do not modify the address book, such as `list`, will usually not call `Model#commitAddressBook()`, `Model#undoAddressBook()` or `Model#redoAddressBook()`. Thus, the `addressBookStateList` remains unchanged.

![UndoRedoState4](images/UndoRedoState4.png)

Step 6. The user executes `clear`, which calls `Model#commitAddressBook()`. Since the `currentStatePointer` is not pointing at the end of the `addressBookStateList`, all address book states after the `currentStatePointer` will be purged. Reason: It no longer makes sense to redo the `add n/David …​` command. This is the behavior that most modern desktop applications follow.

![UndoRedoState5](images/UndoRedoState5.png)

The following activity diagram summarizes what happens when a user executes a new command:

![CommitActivityDiagram](images/CommitActivityDiagram.png)

#### Design consideration:

##### Aspect: How undo & redo executes

* **Alternative 1 (current choice):** Saves the entire address book.
  * Pros: Easy to implement.
  * Cons: May have performance issues in terms of memory usage.

* **Alternative 2:** Individual command knows how to undo/redo by
  itself.
  * Pros: Will use less memory (e.g. for `delete`, just save the student being deleted).
  * Cons: We must ensure that the implementation of each individual command are correct.

_{more aspects and alternatives to be added}_

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

* is a Singapore primary/secondary/junior college private tutors
* has a need to manage a significant number of student contacts
* has a need to manage administrative details of students
* has a need to manage administrative details of lessons
* prefer desktop apps over other types
* can type fast
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps

**Value proposition**: Helps tutors organise administrative details of their students and lesson with ease and manage student needs better.


### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​                   | I want to …​                                          | So that I can…​                                                                     |
| -------- | --------------------------| ----------------------------------------------------- | ----------------------------------------------------------------------              |
| `* * *`  | user ready to use Reeve   | input my student’s details (Admin)                    | refer to instructions when I forget how to use the App                              |
| `* * *`  | novice user               | be able to save meeting links for online lessons      | keep track of meeting links when my student cannot make it for a physical lesson    |                                                                       |
| `* * *`  | expert user               | view my students' preferred tutoring location         | figure out how to get that location                                                 |
| `* * *`  | expert user               | edit my students' personal details                    | get rid of outdated data                                                            |
| `* * *`  | expert user               | view my student's details                             | refer to them when needed                                                           |
| `* * *`  | expert user               | add additional details to each student                | add other miscellaneous details which can allow me to better cater to student needs |
| `* * *`  | long-time user            | delete students' data                                 | remove irrelevant data of students who are no longer my tutees                      |
| `* * *`  | expert user               | find a student's record                               | retrieve students' data with ease                                                   |

*{More to be added}*

### Use cases

(For all use cases below, the **System** is `Reeve` and the **Actor** is the `Tutor (User)`, unless specified otherwise)

**UC01: Adding a student**

**MSS**

1.  User enters a command to add a student with student details.
2.  Reeve saves student data into the students list and displays a success message.

    Use case ends.

**Extensions**

* 1a. User provides input with missing compulsory fields.
    * 1a1. Reeve requests for appropriate input.

      Use case resumes from step 1.
        	
* 1b. User provides input with invalid format.
    * 1b1. Reeve requests for appropriate input with valid format.
      
      Use case resumes from step 1.
        	
**UC02: Searching for a student**

**MSS**

1.  User enters a command to find all students that match the given search parameter (name, school, year or subject).
2.  Reeve displays all students matching the criteria. 
    
    Use case ends.

**Extensions**

* 1a. User provides input with invalid data into the search parameter.
    * 1a1. Reeve displays erroneous field and expected format.       
      
      Use case resumes at step 1.      
* 1b. User provides input without a search parameter.
    * 1a1. Reeve displays a message indicating a search parameter was not provided.       
      
      Use case resumes at step 1.
* 1c. No students match the given criteria.
    * 1c1. Reeve displays a message indicating no match found.    
      
      Use case ends.

**UC03: Editing a student's details**

**MSS**

1.  User enters a command to list all students.
2.  Reeve shows the list of students.
3.  User enters command to edit a specific student in the list and provides needed parameters.
4.  Reeve updates the specified student with the input parameters and displays a success message.

    Use case ends.

**Extensions**

* 2a. The list is empty.
  Use case ends.

* 3a. User provides input with invalid index.
    * 3a1. Reeve requests for input with valid index.

      Use case resumes at step 2.
* 3b. User provides input without any parameters.
    * 3b1. Reeve requests for input with parameters.
      
      Use case resumes at step 2.
* 3c. User provides input with invalid format.
	* 3c1. Reeve requests for input with valid format.
	
	  Use case resumes at step 2.

**UC04: Deleting a student**

**MSS**

1.  User enters a command to list students.
2.  Reeve displays a list of students.
3.  User enters a command to delete a specific student in the list.
4.  Reeve deletes the student and displays a success message.

    Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. User provides input with invalid index.
    * 3a1. Reeve displays an error message.

      Use case resumes at step 2.
    
**UC05: Listing all students** 

**MSS**

1. User enters command to display students list.
2. Reeve displays the students list with student details.

   Use case ends.
 

**UC06: Clearing all student records**

**MSS**

1. User enters a command to clear the students list.
2. Reeve displays a success message.
   
   Use case ends.


**UC08: Finding all students with overdue tuition fees**

**MSS**

1. User enter command to filter all students by those who have not paid their fees in the past month.
2. Reeve displays all students that match the above criteria.

   Use case ends.
   
**Extensions**

* 1a. The list is empty.

  Use case ends.

* 1b. All students have paid their fees in the past month.

  * 1b1. Reeve displays an empty list.

    Use case ends.

### Non-Functional Requirements

1.  Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2.  The response to any use action should become visible within 5 seconds.
3.  The program should be able to handle at least 100 students.
4.  The graphical user interface should be easy to use for non-IT savvy users.

*{More to be added}*

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, OS-X
* **Private contact detail**: A contact detail that is not meant to be shared with others

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

### Deleting a student

1. Deleting a student while all students are being shown

   1. Prerequisites: List all students using the `list` command. Multiple students in the list.

   1. Test case: `delete 1`<br>
      Expected: First contact is deleted from the list. Details of the deleted contact shown in the status message. Timestamp in the status bar is updated.

   1. Test case: `delete 0`<br>
      Expected: No student is deleted. Error details shown in the status message. Status bar remains the same.

   1. Other incorrect delete commands to try: `delete`, `delete x`, `...` (where x is larger than the list size)<br>
      Expected: Similar to previous.

1. _{ more test cases …​ }_

### Saving data

1. Dealing with missing/corrupted data files

   1. _{explain how to simulate a missing/corrupted file, and the expected behavior}_

1. _{ more test cases …​ }_
