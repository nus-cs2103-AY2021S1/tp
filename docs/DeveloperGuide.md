---
layout: page
title: Developer Guide
---
* Table of Contents
{:toc}

<div style="page-break-after: always;"></div>
--------------------------------------------------------------------------------------------------------------------

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

## **Design**

### Architecture

<img src="images/ArchitectureDiagram.png" width="450" />

The ***Architecture Diagram*** given above explains the high-level design of the App. Given below is a quick overview of each component.

<div style="page-break-after: always;"></div>
<div markdown="span" class="alert alert-primary">

:bulb: **Tip:** The `.puml` files used to create diagrams in this document can be found in the [diagrams](https://github.com/AY2021S1-CS2103-F09-1/tp/tree/master/docs/diagrams/) folder. Refer to the [_PlantUML Tutorial_ at se-edu/guides](https://se-education.org/guides/tutorials/plantUml.html) to learn how to create and edit diagrams.

</div>

**`Main`** has two classes called [`Main`](https://github.com/AY2021S1-CS2103-F09-1/tp/tree/master/src/main/java/seedu/address/Main.java) and [`MainApp`](https://github.com/AY2021S1-CS2103-F09-1/tp/tree/master/src/main/java/seedu/address/MainApp.java). It is responsible for,
* At app launch: Initializes the components in the correct sequence, and connects them up with each other.
* At shut down: Shuts down the components and invokes cleanup methods where necessary.

[**`Commons`**](#common-classes) represents a collection of classes used by multiple other components.

The rest of the App consists of four components.

* [**`UI`**](#ui-component): The UI of the App.
* [**`Logic`**](#logic-component): The command executor.
* [**`Model`**](#model-component): Holds the data of the App in memory.
* [**`Storage`**](#storage-component): Reads data from, and writes data to, the hard disk.

Each of the four components

* defines its *API* in an `interface` with the same name as the Component.
* exposes its functionality using a concrete `{Component Name}Manager` class (which implements the corresponding API `interface` mentioned in the previous point.

For example, the `Logic` component (see the class diagram given below) defines its API in the `Logic.java` interface and exposes its functionality using the `LogicManager.java` class which implements the `Logic` interface.

![Class Diagram of the Logic Component](images/LogicClassDiagram.png)

**How the architecture components interact with each other**

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues the command `delete 1`.

<img src="images/ArchitectureSequenceDiagram.png" width="574" />

The sections below give more details of each component.

<div style="page-break-after: always;"></div>
### UI component

![Structure of the UI Component](images/UiClassDiagram.png)

**API** :
[`Ui.java`](https://github.com/AY2021S1-CS2103-F09-1/tp/tree/master/src/main/java/seedu/address/ui/Ui.java)

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `StudentListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class.

The `UI` component uses JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/AY2021S1-CS2103-F09-1/tp/tree/master/src/main/java/seedu/address/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/AY2021S1-CS2103-F09-1/tp/tree/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* Executes user commands using the `Logic` component.
* Listens for changes to `Model` data so that the UI can be updated with the modified data.

<div style="page-break-after: always;"></div>

### Logic component

![Structure of the Logic Component](images/LogicClassDiagram.png)

**API** :
[`Logic.java`](https://github.com/AY2021S1-CS2103-F09-1/tp/tree/master/src/main/java/seedu/address/logic/Logic.java)

1. `Logic` uses the `TaskmasterParser` class to parse the user command.
1. This results in a `Command` object which is executed by the `LogicManager`.
1. The command execution can affect the `Model` (e.g. adding a student).
1. The result of the command execution is encapsulated as a `CommandResult` object which is passed back to the `Ui`.
1. In addition, the `CommandResult` object can also instruct the `Ui` to perform certain actions, such as displaying help to the user.

Given below is the Sequence Diagram for interactions within the `Logic` component for the `execute("delete 1")` API call.

![Interactions Inside the Logic Component for the `delete 1` Command](images/DeleteSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `DeleteCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of the diagram.
</div>

<div style="page-break-after: always;"></div>

### Model component

![Structure of the Model Component](images/ModelClassDiagram.png)

**API** : [`Model.java`](https://github.com/AY2021S1-CS2103-F09-1/tp/tree/master/src/main/java/seedu/address/model/Model.java)

The `Model`,

* stores a `UserPref` object that represents the user’s preferences.
* stores the student list data as a `Taskmaster` object.
<div style="page-break-after: always;"></div>
* exposes an unmodifiable `ObservableList<Student>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* also exposes an unmodifiable `ObservableList<Session>` that is similarly used by the UI.
* does not depend on any of the other three components.


<div markdown="span" class="alert alert-info">:information_source: **Note:** An alternative (arguably, a more OOP) model is given below. It has a `Tag` list in the `AddressBook`, which `Student` references. This allows `AddressBook` to only require one `Tag` object per unique `Tag`, instead of each `Student` needing their own `Tag` object.<br>
![BetterModelClassDiagram](images/BetterModelClassDiagram.png)

</div>


### Storage component

![Structure of the Storage Component](images/StorageClassDiagram.png)

**API** : [`Storage.java`](https://github.com/AY2021S1-CS2103-F09-1/tp/tree/master/src/main/java/seedu/address/storage/Storage.java)

The `Storage` component,
* can save `UserPref` objects in json format and read it back.
* can save the student list data in json format and read it back.
* can save the SessionList data in json format and read it back.

<div style="page-break-after: always;"></div>
### Common classes

Classes used by multiple components are in the `seedu.taskmaster.commons` package.

--------------------------------------------------------------------------------------------------------------------

<div style="page-break-after: always;"></div>

## **Additional Features Implemented**

### SessionList

Author: **Joshua Chew**
* Implement the class `SessionList` that encapsulates a list of tutorial sessions.
* Implement the class `Session` that represents a tutorial session conducted by a teaching assistant.
* Implement the commands in which a session is created and deleted.
* Implement the command to select the current session.

![Structure of the SessionList Component](images/SessionListClassDiagram.png)

The `SessionList`,

* is stored by the Taskmaster.
* encapsulates a list of zero, one or more Session objects.
* can provide a list of the names of all Session objects that it contains (to pass to the UI).

The `Session`,

* stores a SessionName.
* stores a StudentRecordList.
* can mark the attendance of a particular student in the StudentRecordList.
* can mark the attendance of all students in the StudentRecordList with a single Command.

The current running Session, `currentSession`, is also stored as an attribute in the Taskmaster.

<div style="page-break-after: always;"></div>

Given below is the Sequence Diagram for interactions within the `Session` component when `Taskmaster#markStudentWithNusnetId(nusnetId, attendanceType)` is called.

![Interactions iside the Session class for the `markStudentAttendance'` method call](images/MarkStudentAttendanceSequenceDiagram.png)

**Design Considerations**

The following considerations were taken into account in the process of implementing a `Session`.

* The ability to name a session is meant to contribute to the ease of use of TAskmaster for a Teaching Assistant.
This gives the Teaching Assistant the freedom to either name sessions as index numbers, or as the topic that was covered during the session.
With modules typically having up to 24 sessions per semester, this would help the Teaching Assistant navigate and search through a large number of sessions.
* Sessions are selected by name and deleted by name, to simplify the Teaching Assistant's process of managing multiple sessions. As a result,
a Teaching Assistant is not allowed to name two sessions with the same name. The class `SessionList` hence supports operations to check if a 
session with a specified name already exist inside the session list.

**Design Alternatives**

* Make `Session` store an `AttendanceList` and a `ClassParticipationList`.

    Initially, the attendance and class participation data was meant to be stored as separate data structures. However, this
    approach was abandoned in favour of allowing the `Session` class to store the interface `StudentRecordList` instead. This would
    support the Open-Closed Principle. It would allow greater ease of future extensions in the functionality of TAskmaster. For
    example, if we wished to allow a session store data on student submissions, the original approach would necessitate the modification
    of the code for `Session` to store another data structure which encapsulate the student submission data. On the other hand,
    storing a `StudentRecordList` can allow `Session` store such additional data without modifying its code at all, as all student-related
    data is encapsulated in a `StudentRecord`.
    
<div style="page-break-after: always;"></div>

**Future Expansion**

Beyond v1.4, additional features can be implemented for **Session**:

* Allow `Session` to contain a `SessionRemark`.

    We have considered that Teaching Assistants might feel the need to write down a short memo about every Session. Such memos
    may consist of little reminders to collect assignments from students, or to note down what was not covered during the session due
    to time constraints. Hence, it would be beneficial to give Teaching Assistants the option of adding remarks for every Session.

* Allow users to edit the name, date and time of a `Session`.

    Currently, users are given the option to delete a `Session` if he has made a typo when keying in inputs for 
    the `SessionName` or `SessionDateTime`. In the future, it would be good to implement an `EditSessionCommand`
    to allow users to change the name, date and time of a session while still keeping the session's student record data on TAskmaster.
<br>

<div style="page-break-after: always;"></div>
### StudentRecordList

Author: **Goh Siau Chiak**

* Implemented the classes that encapsulate a list of student records.

![Structure of StudentRecordList Component](images/StudentRecordDiagram.png)

<div style="page-break-after: always;"></div>
The `StudentRecordList`,

![Student Record List Class](images/StudentRecordListDiagram.png)

* is stored by a `Session`.
* encapsulates a list of zero or more `StudentRecord` objects.
* can provide a list of the `StudentRecord` objects that it contains (to pass to the UI).
* can mark the attendance of a student in the `Session`.
* can award class participation score to a student in the `Session`.

The `StudentRecord`,
* represents an immutable record of a student enrolled in a particular `Session`.
  * When the user wants to update the record, a new `StudentRecord` object will be created. 
* contains
  * an `NusnetId` that indicates which student the record represents.
  * the `Name` of the student represented.
  * the `AttendanceType` of the student for the `Session`.
  * a `ClassParticipation` representing the score awarded to the student for participating in the `Session`.

<div style="page-break-after: always;"></div>

Given below is the Sequence Diagram for interactions within the `StudentRecordListManager` component when `StudentRecordListManager#markStudentAttendance(nusnetId, attendanceType)` is called.

![Interactions inside the StudentRecordListManager class for the `markStudentAttendance'` method call](images/StudentRecordListAttendanceSequenceDiagram.png)

**Considerations for `markStudentAttendance`**
    
On one hand, the mark command uses an index to identify the record, for ease of usage for the user, as compared to 
having to type out the record's NUSNET ID. On the other hand, the mark attendance method within the `Model` identifies 
the record to mark using its NUSNET ID, to ensure correctness. Furthermore, the student records have to be stored in a 
JavaFX `ObservableList` to be easily displayable on the GUI. 

In the end, I decided to find the record to mark by 
iterating through the record list and comparing NUSNET IDs, since each student's NUSNET ID must be unique. The `O(N)`
time complexity of this method does not incur significant time cost because we expect there to be no more than 1000
students recorded in any session created by TAs using TAskmaster.

**Design alternatives**

* Make `StudentRecord` mutable.

    This was the original design of `StudentRecord` which made sense at the time because a student's record
    would be frequently updated for marking of attendance and awarding of class participation scores. However,
    the JavaFX `ObservableList` interface is **not notified when contained objects are modified**, only when 
    objects were added or deleted. This resulted in a bug where the GUI will not show the changes made when a
    student record was updated. In the end, it was decided that `StudentRecord` should be immutable to fix this bug.

<div style="page-break-after: always;"></div>
* Make each `StudentRecordList` be dependent on and backed by the `StudentList` maintained by TAskmaster.

    This alternative was eventually rejected because it did not make sense from a design perspective. Each
    student record list represents the record of students **for that particular session only**. If we 
    implemented this alternative, edits to the student list will result in changes to all the student record
    lists, which does not follow the design of the student record list. For example:
    * Suppose a TA is currently teaching three students, `A, B, C` who are all present for the first session.
    * When the TA creates a new session `S1` , it will contain three corresponding student records.
    * Subsequently after the session is over, student `A` informs the TA that he is going to drop the module
    * When the TA deletes student `A` from the student list, the student record list of `S1` will update and
    no longer reflect that `A` was enrolled in the module when `S1` occurred
    * However, this does not change the fact that student `A` was present at session `S1`, and so the records of `S1`
    should reflect that `A` was present.

* Have `StudentRecord` contain the whole `Student` object, not just its `Name` and `NusnetId`

    This was considered and ultimately rejected because of the design considerations in the previous 
    alternative. Each student record, once created, is supposed to be independent of the student in the
    student list. This implies that subsequent changes to the student should not be known by the student
    record. Moreover, each student record only needs to know the name and NUSNET ID of the student at the moment 
    it was created and there is no need for it to know the rest of the student's information. 
    
    Furthermore, having
    each student record contain a student object will incur significant memory usage, especially since for most
    TAs, the same set of students will be enrolled in multiple sessions, each with a student record list. 
    Following the **Law of Demeter**, it was decided to restrict the student record to only have the name and NUSNET ID.
    Since editing of `NusnetId` is not allowed, there will be no issues with syncing of data. For example, even if the
    name of a particular student is edited after his `StudentRecord` was saved, we can find that student using their
    `NusnetId`.
<div style="page-break-after: always;"></div>
**Future Expansions**

Beyond 1.4, there are several features that can be added to improve TAskmaster

* Add a centralised view to see attendance and class participation score over all sessions.

    This would allow the user to, at a glance, observe the attendances and class participation scores of his students. 
    This could be done by reading through the student record lists of all sessions and displaying the data in a table 
    form whenever the centralised view is selected by the user. A potential problem is the fact that different sessions 
    can have different students enrolled, which means that the table will not be complete, i.e. some cells may be empty.

* Add statistics tracking attendance and class participation score to each student

    This would allow the user to, at a glance, analyse how often a student is attending lessons and how much they are
    participating in each session. This could be done by finding their record in each of the student record lists of
    all the sessions and displaying the aggregated data in another view for data visualisation, for e.g. with line 
    graphs and pie charts.

<br>
<div style="page-break-after: always;"></div>
### Storage

Author: **Jaya Rengam**
* Change/Add classes in the Storage package to store data related to Sessions.

![Structure of the Storage Component](images/NewStorageClassDiagram.png)

<div style="page-break-after: always;"></div>

**Implementation details**

A new method `JsonTaskmasterStorage#saveSessionList` will store the `SessionList` in the running `Taskmaster` to a separate .json file.
* The SessionList will be represented by a `JsonSerializableSessionList`, which contains a `List<JsonSerializableSession>`
* Each `JsonSerializableSession` will contain a `List<JsonAdaptedStudentRecord>`, along with two fields for its name and date-time.
* The respective fields of each class are converted into Json values using the existing `toString()` methods available.
* For the conversion of each JSON serializable class back to its model type:
    * `StudentRecord`: Check for null (empty) values, then pass the field to its constructor.(The `Double` value for `ClassParticipation` is parsed first.)
    * `Session`: 
        `SessionName` is created directly from the stored string, while the corresponding `SessionDateTime` JSON field needs to first be parsed to a `LocalDateTime` before it can be converted.
        For the `StudentRecords`, maintain a list of NusnetIds in addition to a list of StudentRecords. 
        Loop through the list of `JsonAdaptedStudentRecords`, converting them to `StudentRecord`, and check for duplicate `NusnetId` before adding them to the list.
    * `SessionList`:
        Create a new `SessionList`, then loop through the list of `JsonSerializableSessions`; after each `Session` is converted, 
        check that the `SessionList` does not already have a session with the same name before adding it.

**To-Do:**
* Update `TaskmasterStorage` interface (add `saveSessionList(ReadOnlyTaskmaster taskmaster)` method)
* Implement `saveSessionList` method in `JsonTaskmasterStorage`
* Create the new `JsonSerializable..`/`JsonAdapted..` classes
<br>

<div style="page-break-after: always;"></div>

**_Notes from developer:_**
* This implementation of the feature uses Jackson libraries/formatting that is used in existing AB3 Storage classes
<br>

**Alternative implementations:**
* Store the `SessionList` as a JSON field in the existing `Taskmaster` file
    * Doing it this way would mean that the file would be repeatedly overwritten and any format errors will invalidate the whole file, including the list of students.
    
* Store each `Session` in a separate file, like what was done with the old `AttendanceList`
    * A possible implementation method would be to have the relevant `Session` file is updated when the taskmaster is in a particular session. This would require some kind of additional field in `Taskmaster` 
    which would be responsible for keeping track of what sessions have been created that is accessed to load the require file when the user requests to change the session.
    
    * Advantage: Session files can be viewed, transferred or even edited individually, and formatting errors in one Session file will not affect other data. 
    
    * Disadvantages:
        - The primary disadvantage to this implementation would be that it would no longer make sense for Sessions not in use to be loaded in memory, 
        unless session-related commands are rewritted to trigger a storage operation when the implementation is changed. This would require a refactoring of the code in a way that would increase coupling, 
        and fundamentally change the nature of `CommandResult`, as it would now have to contain information about the session being modified, 
        so that `LogicManager` is able to tell `Storage` which session file to update.
        - Not having Sessions not loaded in memory would affect future features of the product regarding `StudentRecord`. 
        For example, a command that calculates the average class participation/attendance rate of a particular student across all `Sessions` that they have attended,
        or a command to view all absences over the course of the semester. <br>
        To implement these commands, it would make sense for all Sessions to already be loaded into `Taskmaster` so that they are easily searchable.
        - This might also result in the user having to manage a lot of files.     
            
            <div style="page-break-after: always;"></div>
            
**Future Expansion**

Refactoring Storage: <br>
An improvement that could be done to the code would be to abstract out the `Storage` methods to a new abstract class or interface, following the Command pattern.
Subclasses of this abstract class (`StorageInstruction`) can then be used to save/load different `Model` classes. <br>
These storage instructions can then be created and run within `Command#execute`, instead of within `LogicManager#execute`.
This would require that `Commands` take in `Storage` as an additional parameter.
 
* This would then allow for the addition of storage-related features, such as:
    * saving all attendance records of a particular student to a file for viewing
        * This would allow for TAs to print the attendance in an easy-to-read format that they can then use to mark student's attendance on the official platform for their module.
    * saving student records from one particular Session to a file for viewing
    * saving the current state of the Taskmaster to a new file for backup purposes (that file can then be loaded with a new `CreateBackupCommand`)
        * This could even be done in the background, to prevent accidental deletion of data from the `clear` command or otherwise.

<br>

<div style="page-break-after: always;"></div>
### Class Participation Score

Author: **Theodore Leebrant**
* Implement the class in charge of representing the class participation of each student in a session.
* Implement the commands in which class participation would be scored.

The `ClassParticipation` is made such that it:
* Stores a `score`
    * of the `double` type.
    * between 0 and 10 inclusive.
    * with detail up to 2 decimal places.
* Supports equality whenever scores are the same
* Has a method to return the `String` representation of itself.
* Will be used by `StudentRecord`.

To support the `ClassParticipation` class, TAskmaster needs to be able to set a student's score in a session.  
In setting the student's score, TAskmaster needs to comply with the following specifications:
* Supports checking of the session (i.e. throws an error in case of no sessions made, or no sessions selected)
* Able to identify the NUSNET ID from the index of student in the session.
* Has input validation for the score (between 0 and 10 inclusive)
* Able to truncate score to 2 decimal places (to follow with the `ClassParticipation` class)
* (Extra goal) Able to score all students who are present.

Based on the specification above, the following is the activity diagram when someone were to score a student's participation score:
![Activity Diagram for Class Participation](images/ScoreActivityDiagram.png)

**Design Considerations**

The specifications above have undergone several revisions, with the following considerations taken into account:
* Session checking needs to be done, as the scoring depends on the session the user is currently on.
* Support for scoring from the index is for ease of use for the user. 
Internally, it uses NUSNET ID to see which student needs to be scored as the NUSNET ID is unique for each student.
Therefore, there needs to be some lookup needed to bridge between these two.
* Input validation is needed to ensure that the score inputted is valid.
<div style="page-break-after: always;"></div>
* The choice of 0-10 `double` score is to support the granularity of score detail 
(most TA would use at most 1 decimal place, therefore we added support for 2 decimal places for meticulous TAs), while
still maintaining good display for the sake of UX. Negative scores are not supported as a negative score
does not make sense in this case.
* Marking all students will be a feature that supports the user experience of TAs using TAskmaster. Oftentimes there
is a baseline on what TAs consider basic participation marks - and many students barring the exceptional ones will get
similar, if not the same score. The `mark all` command comes from our observation of this fact.

**Design Alternatives**

* Make `ClassParticipation` a field in `StudentRecord`.  
This was the initial plan for `StudentRecord`. However, there are advantages to having ClassParticipation as its own class.
This would support data abstraction as we abstract away lower-level data items, as well as help with decoupling. 
The implementer of `StudentRecord` does not need to know the internal details of the implementation of `ClassParticipation`, whether is it an enum (of, let's say
`GREAT, GOOD, AVERAGE, BAD`) or a `double` score as we have chosen to implement. Having a `ClassParticipation` class would
also support future expansion in the scoring of class participation without going through the internal details of `StudentRecord`.

* Make `ClassParticipation` an enum or use `int` values for the score.  
This was initially considered but ultimately scrapped to increase flexibility as well as attention to detail. 
Having an enum as the scoring system would work, but in effect, it would reduce the detail of the score given to a student.
This may cause some TAs to not be satisfied with the scoring system. While having integer is a better solution, we
decided on using `double` due to the fact that 0-10 is easily scalable (e.g. to get the percentage, the TA can just glance
at the score) and provides more detail. An alternative that may also be considered for future iteration is to use
`float` instead of `double` as we do not necessarily need double-precision decimal representation with only two decimal
places.

<div style="page-break-after: always;"></div>
**Future expansion**

Beyond 1.4, there are several improvements that can be done:
* Set a maximum score for the session.  
This would include one extra command to change the maximum value of the score. In this case, we need to implement
a field in `ClassParticipation` for the maximum score, and see from this value for input validation. A problem that we
foresee in implementing this is the default choice of the maximum score, validation of current score (what happens if
there is a student with a score of 5.5 but we set the maximum score to 5), as well as possible coupling due to the command
needing to see the maximum value of the class participation score.
* Have alternatives for scoring class participation.  
We have thought about the possibility that the TA does not need the granularity of a `double` value to 2 decimal places.
In the future, it would be good to support a command that changes the type of the score, e.g. to discrete values given
by enum. Some considerations would be about existing scores, implementation of classes beyond `ClassParticipation`,
as well as command handling.


<br>

<div style="page-break-after: always;"></div>
### UI

Author: **Lim Jin Feng**
* Change/Add classes in the UI package to display newly implemented functionality such as ClassRecords and Sessions.
The following is my implementation of the Ui.

![Structure of the UI Component](images/UpdatedUiClassDiagram.png)

<div style="page-break-after: always;"></div>

* New classes `StudentRecordListPanel` and `StudentRecordCard` was added to support the display of Student Records in
`MainWindow`.
* A new class, `SessionListPanel` was added to support the display of Sessions.
* In `MainWindow`, `studentListPanel` was refactored to `mainListPanel` as it could be filled with either students or sessions.
* `MainWindow#fillInnerParts` was refactored to take in a boolean `fillWithStudents`, which determines whether
  `mainListPanel` should be filled with the Student List or Session List.
* A new method `MainWindow#fillMainList` was introduced to reduce code duplication as filling with Students versus filling
  with Sessions fills the rest of TAskmaster with its defaults.

**Design considerations/specifications:**
* Supported displaying of Sessions.
    * Displaying of Session is an integral part of TAskmaster as with any user interface, the user would have
    difficulties in viewing, editing, adding and managing sessions.
    * Improvements in the displaying Sessions include:
        * Adding a header to show the user which Session he is currently on.
        * Adding the display of date and time in the header.
* Implemented alternative changing of session via GUI buttons.
    * Although TAskmaster is optimised for CLI, we felt that it would be a great quality of life improvement to allow the
    user an alternative way to change sessions via a click. This would also complement the sidebar implemented for
    TAskmaster, hence improving the aesthetics and usability as a whole.
* Implemented alternative changing to the student list via GUI buttons.
    * Similarly, this is a quality of life feature.
* Must be able to accommodate future implementations.
    * For v2.0, any expansions made to TAskmaster would require an update to UI. Hence, it is imperative that current
    implementations of UI must be able to support those expansions.

Alternative implementations considered:
* Use FXML's tab feature to display class records: Does not fit the overall theme of TAskmaster and does not support
indefinite number of sessions.

<div style="page-break-after: always;"></div>

--------------------------------------------------------------------------------------------------------------------

## **Documentation, logging, testing, configuration, dev-ops**

* [Documentation guide](Documentation.md)
* [Testing guide](Testing.md)
* [Logging guide](Logging.md)
* [Configuration guide](Configuration.md)
* [DevOps guide](DevOps.md)

--------------------------------------------------------------------------------------------------------------------

<div style="page-break-after: always;"></div>

## **Appendix: Requirements**

### Product scope

**Target user profile**:

* is an Undergraduate CS Teaching Assistant
* has a need to manage their students:
    * attendance
    * participation
* can type fast
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps

**Value proposition**:
* manage students faster than a typical mouse/GUI driven app
* view student details at a glance
* add and remove students easily
* create student records for each individual class conducted
* mark and view attendance and class participation in these classes quickly

<div style="page-break-after: always;"></div>

### User stories

Priorities: 
- High (must have) - `* * *`
- Medium (nice to have) - `* *`
- Low (unlikely to have) - `*`

| Priority | As a …​                                    | I want to …​                     | So that I can…​                                             |
| -------- | ------------------------------------------ | --------------------------------- | -------------------------------------------------------------- |
| `* * *`  | user                                       | add a new student                 |                                                                |
| `* * *`  | user                                       | view details of a student         | have quick access to them                                      |
| `* * *`  | user                                       | view details of all my students   | have quick access to them                                      |
| `* * *`  | user                                       | mark a student's attendance       |                                                                |
| `* *`    | user                                       | mark all students' attendance     | update all attendance records quickly and focus on teaching    |
| `* * *`  | user                                       | score a student's participation   |                                                                |
| `* *`    | user                                       | score all students; participation | update all participation records quickly and focus on teaching |
| `* * *`  | user                                       | delete a student                  |                                                                |
| `* * *`  | user                                       | add a new session                 |                                                                |
| `* * *`  | user                                       | view details of a session         | have quick access to it                                        |
| `* * *`  | user                                       | delete a session                  |                                                                |


*{More to be added}*

<div style="page-break-after: always;"></div>

### Use cases

(For all use cases below, the **System** is `Taskmaster` and the **Actor** is the `user`, unless specified otherwise)
<br>


**Use case: Add a student**

**MSS**

1.  User requests to add a new student
2.  System shows the added student

Use case ends.

**Extensions**

* 1a. The given input is invalid.
    * 1a1. System shows an error message.
      Use case resumes at step 1.
<br>


**Use case: View a student's details**

**MSS**

1.  User requests to view a student's details
2.  System shows the student's details

Use case ends.

**Extensions**

* 1a. The given input is invalid.
    * 1a1. System shows an error message.
      Use case resumes at step 1.
<br>


**Use Case: View all students' details**

**MSS**
1. User requests to view all students' details
2. System shows all students' details.

Use case ends.
<br>


<div style="page-break-after: always;"></div>

**Use Case: Delete a student**

**MSS**
1. User requests to list students
2. System shows a list of students
3. User requests to delete a specific student in the list
4. System deletes student

Use case ends.

Extensions
* 2a. The list is empty. <br>
    Use case ends.
    
* 3a. The given input is invalid.
    * 3a1. System shows an error message.
        Use case resumes at step 2.
<br>


**Use Case: Add a session**

**MSS**
1. User requests to add a session
2. System adds the session and shows the student records for that session with default fields

Extensions
* 1a. The given input is invalid.
    * 1a1. System shows an error message.
        Use case resumes at step 1.
        
* 1b. The name of the session given by the user is already in use.
    * 1b1. System shows an error message.
        Use case resumes at step 1.

<br>

<div style="page-break-after: always;"></div>

**Use Case: Change view to a session**

**MSS**
1. User requests to view information (student records) of a session
2. System displays student records of that session

Extensions
* 1a. The given input is invalid.
    * 1a1. System shows an error message.
        Use case resumes at step 1.
        
<br>


**Use Case: Delete a session**

**MSS**
1. User requests to delete a session
2. System deletes the session

Extensions
* 1a. The given input is invalid.
    * 1a1. System shows an error message.
        Use case resumes at step 1.

<br>

> Note: The following use cases **all** have the precondition that the user is in a session view.

**Use Case: Mark a student's attendance**

**MSS**
1. User requests to mark student attendance.
2. System marks the student's attendance within the context of that session.

Extensions
* 1a. System is not within the context of a session.
    * 1a1. System shows an error message.
    Use case ends.
    
* 1b. The given input is invalid.
    * 1b1. System shows an error message.
        Use case resumes at step 1.

<br>

<div style="page-break-after: always;"></div>

**Use Case: Mark all students' attendances**

**MSS**
1. User requests to mark all students' attendance.
2. System marks all student attendances within the context of that session.

Extensions
* 1a. System is not within the context of a session.
    * 1a1. System shows an error message.
    Use case ends.
    
* 1b. The given input is invalid.
    * 1b1. System shows an error message.
        Use case resumes at step 1.

<br>


**Use Case: Score student's participation**

**MSS**
1. User requests to score participation.
2. System scores the student's participation within the context of that session.

Extensions
* 1a. System is not within the context of a session.
    * 1a1. System shows an error message.
    Use case ends.
    
* 1b. The given input is invalid.
    * 1b1. System shows an error message.
        Use case resumes at step 1.

<br>

<div style="page-break-after: always;"></div>

**Use Case: Score all students' participation**

**MSS**
1. User requests to score all students' participation.
2. System scores all student participation within the context of that session.

Extensions
* 1a. System is not within the context of a session.
    * 1a1. System shows an error message.
    Use case ends.
    
* 1b. The given input is invalid.
    * 1b1. System shows an error message.
        Use case resumes at step 1.

<br>

**Use Case: View all students with the lowest participation score**

**MSS**
1. User requests to view all students with the lowest participation score.
2. System shows all students with the lowest participation score.

Extensions
* 1a. System is not within the context of a session.
    * 1a1. System shows an error message.
    Use case ends.
    
* 1b. The given input is invalid.
    * 1b1. System shows an error message.
        Use case resumes at step 1.

* 1c. There are no students present.
    * 1b1. System shows an error message.
       
<br>

<div style="page-break-after: always;"></div>

**Use case: Get a random student which is present**

**MSS**
1. User requests to view a random student.
2. System shows a student picked at random who is present

Extensions
* 1a. System is not within the context of a session.
    * 1a1. System shows an error message.
    Use case ends.
    
* 1b. The given input is invalid.
    * 1b1. System shows an error message.
        Use case resumes at step 1.
        
* 1c. There are no students present.
    * 1b1. System shows an error message.
    Use case ends.
    
<br>

### Non-Functional Requirements

1.  Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2.  Should work without requiring an installer, the installation of any additional software, or any external connections.
3.  Should only be for a single user.
4.  Should be able to hold up to 1000 students without a noticeable sluggishness in performance for typical usage.
5.  A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
6.  Should store data locally _only_, in a human editable text file
7.  Total file size should not exceed 100MB.

*{More to be added}*

<div style="page-break-after: always;"></div>

### Glossary

* **Mainstream OS**: Windows, Linux (Ubuntu), Unix, macOS
* **Private identification detail**: A personal particular that is not meant to be shared with others (e.g. NUSNET ID)

<div style="page-break-after: always;"></div>

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.

<div markdown="span" class="alert alert-info">:information_source: **Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

</div>

### Launch and shutdown

1. Initial launch

   1. Download the jar file and copy into an empty folder

   1. Double-click the jar file Expected: Shows the GUI with a set of sample students. The window size may not be optimal.

1. Saving window preferences

   1. Resize the window to an optimum size. Move the window to a different location. Close the window.

   1. Re-launch the app by double-clicking the jar file.<br>
       Expected: The most recent window size and location is retained.

<div style="page-break-after: always;"></div>

### Adding a student

1. Adding a student while all students are being shown

   1. Prerequisites: List all students using the `list-students` command. Multiple students in the list.

   1. Test case: `add-student n/John Tan u/johntan98 e/johntan98@gmail.com i/e0012345 t/tardy`<br>
      Expected: A student named John Tan is added to the list. Details of the added student shown in the status message.

   1. Test case: `add-student n/John Tan u/johntan98 e/johntan98@gmail.com i/e0012345 t/tardy`<br>
      Expected: No student is added because a student with the same details already exists in the list. Status message informs user that student already exists.

   1. Test case: `add-student n/Gandalf`<br>
      Expected: No student is added. Error details shown in the status message.

   1. Other incorrect add commands to try: `add-student`, `add-student Gandalf`, `...`<br>
      Expected: Similar to previous.
      
### Finding a student

1. Finding a student whose name exists in the student list

   1. Prerequisites: List all students using the `list-students` command. Multiple students in the list.

   1. Test case: `find-students john`<br>
      Expected: A list of students whose names match the keyword `john` will be shown.

   1. Test case: `find-students`<br>
      Expected: No change to the student list view. Error details shown in the status message.
      
### Editing a student

1. Editing a student while all students are being shown

   1. Prerequisites: List all students using the `list-students` command. Multiple students in the list.

   1. Test case: `edit-student 1 u/johntan68 e/johntan68@gmail.com`<br>
      Expected: First student's Telegram handle will be changed to `johntan68`, and his email to `johntan68@gmail.com`.

   1. Test case: `edit-student 0 u/johntan98 e/johntan98@gmail.com`<br>
      Expected: No student is edited. Error details shown in the status message.
   
   1. Other incorrect delete commands to try: `edit-student`, `edit-student x`, `...` (where x is larger than the list size)<br>
      Expected: Similar to previous.

<div style="page-break-after: always;"></div>

### Deleting a student

1. Deleting a student while all students are being shown

   1. Prerequisites: List all students using the `list-students` command. Multiple students in the list.

   1. Test case: `delete-student 1`<br>
      Expected: First student is deleted from the list. Details of the deleted student shown in the status message.

   1. Test case: `delete-student 0`<br>
      Expected: No student is deleted. Error details shown in the status message.

   1. Other incorrect delete commands to try: `delete-student`, `delete-student x`, `...` (where x is larger than the list size)<br>
      Expected: Similar to previous.


### Adding a session

1. Adding a new session 

   1. Test case: `add-session s/First Session dt/23-10-2020 0900`<br>
      Expected: A session named First Session is added to the session list. Details of the added session shown in the status message.

   1. Test case: `add-session s/First Session dt/24-10-2020 0900`<br>
      Expected: No session is added as a session with the same name is already found in the session list. Status message informs user that session already exists.

   1. Test case: `add-session`<br>
      Expected: No session is added. Error details shown in the status message.

   1. Other incorrect add commands to try: `add-session First Session`, `...`<br>
      Expected: Similar to previous.
   
   <div style="page-break-after: always;"></div>

### Changing the current session view

1. Changing the current session view

   1. Prerequisites: Add a session to the session list by running the command `add-session s/First Session dt/23-10-2020 0900`.

   1. Test case: `goto s/First Session`<br>
      Expected: The student records of the session named First Session will be displayed.

   1. Test case: `goto s/Session that does not exist`<br>
      Expected: Current view does not change. Status message states that the session does not exist.
   
   1. Test case: `goto`<br>
      Expected: Current view does not change. Error details shown in the status message.

   1. Other incorrect add commands to try: `goto First Session`, `...`<br>
      Expected: Similar to previous.

### Marking a student's attendance

1. Marking a student's attendance within a session

    1. Prerequisites: 
        - Add a session to the session list by running the command `add-session s/First Session dt/23-10-2020 0900`.

    1. Test case: `mark 1 a/present`<br>
       Expected: First student in the student record list of First Session is marked as present.

    1. Test case: `mark a/present`<br>
       Expected: Current view does not change. Error details shown in the status message.

    1. Test case: `random-student` followed by `mark 1 a/absent`<br>
       Expected: Random student selected is marked as absent.

    1. Other incorrect add commands to try: `mark`, `mark 1`, `mark x`, `...` (where x is larger than the list size)<br>
       Expected: Similar to previous.

2. Marking a student's attendance when no session is selected

    1. Prerequisites: List all students using the `list-students` command. Multiple students in the list.

    1. Test case: `mark 1 a/present`<br>
       Expected: Current view does not change. Status message informs user to select a session first.

<div style="page-break-after: always;"></div>

### Marking all students attendances

1. Marking all students attendances within a session

    1. Prerequisites: 
        - Add a session to the session list by running the command `add-session s/First Session dt/23-10-2020 0900`.

    1. Test case: `mark all a/present`<br>
       Expected: All students in the student record list of First Session are marked as present.

    1. Test case: `mark all`<br>
       Expected: Current view does not change. Error details shown in the status message.

    1. Test case: `random-student` followed by `mark all a/absent`<br>
       Expected: All students in the student record list, **not just the random student**, are marked absent.
       UI updates to shows all student records.
       
    1. Test case: `lowest-score` followed by `mark all a/absent`<br>
       Expected: All students in the student record list, **not just those with the lowest score**, are marked absent.
       UI updates to shows all student records.
       
    1. Other incorrect add commands to try: `mark all a/x` (where x is not a valid attendance type)<br>
       Expected: Similar to previous.

2. Marking a student's attendance when no session is selected

    1. Prerequisites: List all students using the `list-students` command. Multiple students in the list.

    1. Test case: `mark all a/present`<br>
       Expected: Current view does not change. Status message informs user to select a session first.

<div style="page-break-after: always;"></div>

### Scoring a student
The below testcases assume that you are in a session and have 7 students inside it.

* Scoring a student
    1. Test case: `score 1 cp/5.3`  
    Expected: The student with index 1 in the session will be scored 5.3.
    2. Test case: `score 8 cp/3.23`  
    Expected: No student's score will be changed. An error shows that the student index provided is invalid.
    3. Test case: `score 1 cp/-1`  
    Expected: No student's score will be changed. An error shows that the input is invalid as it is negative.
    4. Test case: `score 1 cp/11.4`  
    Expected: No student's score will be changed. An error shows that the input is invalid as it greater than 10.

* Scoring multiple students (all students who are present)
    1. Test case: `score all cp/8.2`  
    Expected:  
        * All students who are present are scored 8.2.
        * All students who are not present have their scores unchanged.
    2. Test case: `score all cp/-0.52`  
    Expected: No scores changed, an error shows that the input is invalid as it is negative.
    3. Test case: `score all cp/10.52`  
    Expected: No scores changed, an error shows that the input is invalid as it is greater than 10.
    4. Test case: `random-student` followed by `score all a/8`<br>
       Expected: All students in the student record list, **not just the random student**, have their scores updated. 
       UI updates to show all student records.
    5. Test case: `lowest-score` followed by `mark all a/absent`<br>
       Expected: All students in the student record list, **not just those with the lowest score**, have their scores updated. 
       UI updates to shows all student records.

<div style="page-break-after: always;"></div>

### Showing a student with the lowest score

1. Getting the present student(s) with the lowest score
    1. Prerequisite: A newly-created session with at least 3 students.
    
    1. Test case: Enter `mark all a/present`, `score all cp/5.0`, `mark 1 a/absent`, then enter `lowest-score` <br>
    Expected: Only the students marked as present appear in the list
   
    1. Test case: Enter `mark all a/present`, `score all cp/5.0`, `mark 1 a/absent`, `score 1 cp/0`, then enter `lowest-score` <br>
    Expected: Only the students marked as present (with score 5.0) appear in the list.
    
    1. Test case: Enter `lowest-score` (all student attendance should be `NO RECORD`) <br>
    Expected: No students in the student record list are shown, an error shows that there are no present students in the session.

### Showing a random student who is present

1. Getting a random student who is present
    1. Prerequisite: A newly-created session with at least 3 students.
    
    1. Test case: Enter `mark all a/present`, then enter `random-student` a few times. <br>
    Expected: Different students, one at a time, should appear in no particular order. 
   
    1. Test case: Enter `mark 1 a/present`, then enter `random-student` a few times. <br>
    Expected: Only the first student should appear each time.
    
    1. Test case: Enter `random-student` (all student attendance should be `NO RECORD`) <br>
    Expected: No students in the student record list are shown, an error shows that there are no present students in the session.

### Clearing contents of student and session list

1. Clearing contents of student and session list

   1. Test case: `clear`<br>
      Expected: The contents of the student and session list will be emptied.

<div style="page-break-after: always;"></div>

### Deleting a session

1. Deleting a session

   1. Prerequisites: Add a session to the session list by running the command `add-session s/First Session dt/23-10-2020 0900`.

   1. Test case: `delete-session s/First Session`<br>
      Expected: First session is deleted from the list.

   1. Test case: `delete-session s/Session that does not exist`<br>
      Expected: No session is deleted. Status message informs user that there are no sessions in the session list with that name.

   1. Other incorrect delete commands to try: `delete-session`, `delete-session First Session`<br>
      Expected: Error details shown in the status message.


### Exiting the program

1. Exiting the program

   1. Test case: `exit`<br>
      Expected: The window of the program will close.

<div style="page-break-after: always;"></div>

### Saving data

1. Dealing with missing/corrupted data files

   1. If there is no file at the default location `./data/taskmaster.json`, sample data will be loaded into the `Taskmaster` upon startup. 
      This sample data can be found in [`model/util/SampleDataUtil.java`](https://github.com/AY2021S1-CS2103-F09-1/tp/blob/master/src/main/java/seedu/taskmaster/model/util/SampleDataUtil.java)
   1. If either the `Taskmaster` or `SessionList` storage files have invalid formatting/values, the application loads with an empty Taskmaster.
   For testing, invalid data can easily be created by starting a new TAskmaster and then editing the fields/formatting in the created JSON files.

2. Updating storage files during the running of the program
   
   1. Prerequisite: Start with a new TAskmaster
   
   1. Test case: Add a student `add-student n/John Tan u/johntan98 e/johntan98@gmail.com i/e0012345 t/tardy`, then close and restart the application <br>
   Expected: In `data/taskmaster.json`, there should be a new JSON field representing the student added and his details. <br>
   When restarted, the UI should show one student named 'John Tan' with the details as shown above.
   
   1. Test case: Add a student `add-student n/John Tan u/johntan98 e/johntan98@gmail.com i/e0012345 t/tardy`, then add a session `add-session s/First Session dt/23-10-2020 0900`, 
   then close and restart the application. <br>
   Expected: In `data/session_list.json`, there should be a new JSON field representing the session and its details. In the `records` field, 
   the student's name and nusnetId should be filled in correctly, and his class participation and attendance type should be set to their default values.<br>
   When restarted, the UI should show one session which shows the corresponding student record when the view is changed.

<div style="page-break-after: always;"></div>
--------------------------------------------------------------------------------------------------------------------

## **Appendix: Effort**

As compared to AB3, this project was tougher and requires more effort to build. First, we consider the behaviour of TAskmaster within each session. The difficulty of implementing this is on par with AB3, since we are having similar functionality to AB3 - just with regards to marking attendance and scoring participation score as compared to AB3's operations on the list of people. This is coupled with a lot of design considerations and iterative changes made to accommodate UI integration and unexpected UI behaviours as will be discussed below. Second, we shall look into the session command itself - this requires a significant update with regards to the UI, adding the sidebar and adjusting sizes to make the user experience better. Additionally, this also entails more commands, which is the creation and deletion of the sessions. On top of that, there needs to be integration with the changed AB3 feature, in which the students from the student list (the morphed AB3) needs to be taken to build the session. Third, we should see the refactoring of AB3 into TAskmaster, changing the input validation and fields as necessary. Overall, we can see that this project has a higher difficulty than AB3, and we are happy to have delivered v1.4 of this product. The following is an elaboration on the challenges we found along the way:

1. Integration Issues
    
    As each of us worked on features that were closely linked to each other, our team had to ensure that each of us followed sound
    software engineering principles, especially the Open-Closed Principle. At every team meeting, we ensured that all of us agreed
    on a particular design pattern before implementing each of our assigned features, and that our design pattern has sufficient
    functional and data abstraction.
    
    The features had to be done sequentially, which made the integration much more challenging. For example, `StudentRecord` and `StudentRecordList`
    had to be completed before `Session` and `SessionList` was implemented. Only after the feature for adding sessions have been created, could the
    features for storing session data be implemented. Finally, as the GUI depended on the backend logic of `StudentRecord` and `Session` to be complete,
    incremental additions to GUI had to always be completed last in the milestone.
    
    As a result, the design choices agreed upon at the start of every milestone had to be absolutely sound, as changing design patterns in the middle 
    of a milestone could potentially have cascading repercussions down the chain of workflow. Between milestones, we have had changes in the design patterns,
    and this has caused us to change, rewrite, or otherwise refactor our code. We believe that it is a group achievement to have maintained cohesiveness 
    and be steadfast in our development, not letting these issues come to interfere with our releases.
<div style="page-break-after: always;"></div>
2. Integrating model with UI

   Another major challenge we faced was in integrating the model with the UI.
   
   1. Updating StudentRecords with Immutability.  
   It was found out that without sticking to immutability, the UI would not update properly, causing a number of refactoring to be needed for this project. 
   As of v1.4, this issue has been successfully fixed - as a vital part of our product, we have ensured that this part will work as expected.
   
   2. Ensuring that the list of student records is updated properly in session view <br>
   When implementing the `random-student` and `lowest-score` commands, we ran into various difficulties as a result of how the existing application was coded.
   We had to modify how the list of student records from the running session was stored in `Model`, to allow for the student record lists of different sessions to be loaded.
   In the absence of UI tests, we also had to debug the running of the program extensively to identify how and when the student record list was being updated in the UI, and 
   adjust our code accordingly. In this process, we found ourselves having to refactor the code several times until we settled on a solution that was working properly.

3. Difficulty of writing automated tests

    Some features, especially the GUI, had difficulties implementing unit tests. As a result, testing for the GUI had to be done manually and through the
    debugger. A lot of effort had to be made on Jin Feng's part to ensure that the testing was comprehensive. This is an achievement in and of itself, as manual testing
    is something that is not easy to do and a lot of elaborate efforts have been done to warrant the correctness of the GUI behaviour.
