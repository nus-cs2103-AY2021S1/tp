---
layout: page
title: Developer Guide

---

* Table of Contents
{:toc}



--------------------------------------------------------------------------------------------------------------------

## **Setting up, getting started**

For detailed instructions on how to set the application up, refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

## **Design**
This section describes the implementation of the design of the app.

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

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `TaskListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class.

The `UI` component uses JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* Executes user commands using the `Logic` component.
* Listens for changes to `Model` data so that the UI can be updated with the modified data.

### Logic component

![Structure of the Logic Component](images/LogicClassDiagram.png)

**API** :
[`Logic.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/logic/Logic.java)

1. `Logic` uses the `PlanusParser` class to parse the user command.
1. This results in a `Command` object which is executed by the `LogicManager`.
1. The command execution can affect the `Model` (e.g. adding a task).
1. The result of the command execution is encapsulated as a `CommandResult` object which is passed back to the `Ui`.
1. In addition, the `CommandResult` object can also instruct the `Ui` to perform certain actions, such as displaying help to the user.

Given below is the Sequence Diagram for interactions within the `Logic` component for the `execute("delete-task 1")` API call.

![Interactions Inside the Logic Component for the `delete 1` Command](images/DeleteSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `DeleteTaskCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>


### Model component

![Structure of the Model Component](images/ModelClassDiagram.png)

**API** : [`Model.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/model/Model.java)

The `Model`,

* stores a `UserPref` object that represents the user’s preferences.
* stores the PlaNus data.
* exposes an unmodifiable `ObservableList<Task>`, `ObservableList<Event>` and `ObservableList<Lesson>` that can be 'observed' e.g. the UI can be bound to these lists so that the UI automatically updates when the data in the list changes.
* does not depend on any of the other three components.


<div markdown="span" class="alert alert-info">:information_source: **Note:** An alternative (arguably, a more OOP) model is given below. It has a `Tag` list in the `PlaNus`, which `Task` references. This allows `PlaNus` to only require one `Tag` object per unique `Tag`, instead of each `Task` needing their own `Tag` object.<br>
![BetterModelClassDiagram](images/BetterModelClassDiagram.png)


</div>


### Storage component

![Structure of the Storage Component](images/StorageClassDiagram.png)

**API** : [`Storage.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/storage/Storage.java)

The `Storage` component,

* can save `UserPref` objects in json format and read it back.
* can save the PlaNus data in json format and read it back.

### Common classes

Classes used by multiple components are in the `seedu.addressbook.commons` package.

--------------------------------------------------------------------------------------------------------------------

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

### Adding a `Lesson`

#### Implementation

The classes involved in creating a lesson are `Lesson` and`LessonCommand`. The user inputs the `dayOfTheWeek` , `startTime` and `endTime` of the lesson as well as the `startDate` and `endDate`. The lesson information is then stored in planus.json.

`Lesson` implements the following operations:

* `Lesson#createRecurringTasks` — Creates a list of recurring event tasks based on the lesson's details.

The created tasks are then added to the `Planus#UniqueTaskList` via the `Planus#addTask` method. Together with the tasks, the calendar is also
updated and the new calendar tasks are added to `Planus#Clendar` via the `Planus#addTaskToCalendar` method. Lastly, the newly added lesson is added to
`Planus#UniqueLessonList` which finishes the state update. 

  <br>

The following sequence diagram describes what happens when `Planus` updates a newly added `lesson`:

![AddLessonSequenceDiagram](images/AddLessonSequenceDiagram.png)

#### Design consideration:

##### Aspect: How to store lessons in PlaNus

* **Alternative 1 (current choice):** Save lesson information in a lesson json object.
  * Pros: Saves space as lessons that occur over a long period of time are not stored as many events that contain similar attributes.
  * Cons: PlaNus will be slower on start up as lessons have to be turned into events and added to PlaNus.

* **Alternative 2:** Save lessons as multiple recurring events.
  * Pros: PlaNus will be faster on start up as lessons are already stored as events.
  * Cons: Uses more space by storing more json objects.

### Data analysis feature

#### Implementation

The data analysis feature is facilitated by `Statistics` and `StatisticsData`. 

![StatsClassDiagram](images/StatsClassDiagram.png)

The time taken to complete each task is stored internally in planus.json when the `DoneCommand` is executed.
The `Statistics` class facilitates updating `StatisticsData` from `ObservableList` in UI.

`Statistics` implements the following operations:

* `Statistics#generateStatistics(startDate, endDate)` — Computes total duration of time spent on all tasks and lessons for the specified time period and stores it in a data structure.

  <br>

The `StatisticsData` class contains the methods to store and retrieve information from the custom data structure. The custom data structure comprises of a HashMap where the **`tag` containing the module code** is the **key** and the **value** is a size two integer array with the **total time spent on `task`** being stored in the first index and the **total time spent on `lesson`** in the second index.

`StatisticsData` implements the following operations to read and update data stored in this custom data structure:

* `StatisticsData#addTag(tag)` — Stores the module tag in the data structure.

* `StatisticsData#addTaskTime(tag, value)` — Adds the time taken for tasks with the specified module tag.

* `StatisticsData#addLessonTime(tag, value)` — Adds the time taken for lessons with the specified module tag.

* `StatisticsData#getTotalTime(tag)` — Retrieves total time spent on both lessons and tasks associated with module tag from data structure.

  <br>

The following activity diagram summarises when the data analysis chart and data is being updated in the UI:



The following sequence diagram describes what happens when the user keys in the `done` command:



#### Design consideration:

##### Aspect: How to track time spent on each task or lesson

* **Alternative 1 (current choice)**: Allow user to input how much time he has spent on each deadline  with the `done` command and automatically calculating the time spent on each `event`/`lesson` by assuming the user has spent the amount of time between the start and end time of the `event`/`lesson`.

The user enters the command `done index:duration` where `index` refers to the inedx of the deadline to be marked as done and `duration` refers to the time used to complete the deadline.

  * Pros: 
    * User does not have to key in as many commands as he inputs the time spent on each task when he completed the task. 
    * User does not have to worry about time spent on all `event` and `lesson`.
  * Cons: 
    * User must manually keep track of the time spent on the deadline.
    * User is not able to indicate actual time spent on `event` and `lesson`.

* **Alternative 2:** Use a `start` and `stop` command to indicate when the user is spending time on a `deadline`/`event`/`lesson`, automatically recording the time spent.
  * Pros: User will be able to more accurately track time spent on each `task`/`lesson`.
  * Cons: Keying in multiple `start` and `stop` commands may be tedious for the user.



### \[Proposed\] Undo/redo feature

#### Proposed Implementation

The proposed undo/redo mechanism is facilitated by `VersionedPlanus`. It extends `Planus` with an undo/redo history, stored internally as an `planusStateList` and `currentStatePointer`. Additionally, it implements the following operations:

* `VersionedPlanus#commit()` — Saves the current PlaNus state in its history.
* `VersionedPlanus#undo()` — Restores the previous PlaNus state from its history.
* `VersionedPlanus#redo()` — Restores a previously undone PlaNus state from its history.

These operations are exposed in the `Model` interface as `Model#commitPlanus()`, `Model#undoPlanus()` and `Model#redoPlanus()` respectively.

Given below is an example usage scenario and how the undo/redo mechanism behaves at each step.

Step 1. The user launches the application for the first time. The `VersionedPlanus` will be initialized with the initial PlaNus state, and the `currentStatePointer` pointing to that single PlaNus state.

![UndoRedoState0](images/UndoRedoState0.png)

Step 2. The user executes `delete-task 5` command to delete the 5th task in Planus. The `delete-task` command calls `Model#commitPlanus()`, causing the modified state of the PlaNus after the `delete-task 5` command executes to be saved in the `planusStateList`, and the `currentStatePointer` is shifted to the newly inserted PlaNus state.

![UndoRedoState1](images/UndoRedoState1.png)

Step 3. The user executes `event title:project …` to add a new event. The `event` command also calls `Model#commitPlanus()`, causing another modified PlaNus state to be saved into the `planusStateList`.

![UndoRedoState2](images/UndoRedoState2.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** If a command fails its execution, it will not call `Model#commitPlanus()`, so the PlaNus state will not be saved into the `planusStateList`.


</div>

Step 4. The user now decides that adding the event was a mistake, and decides to undo that action by executing the `undo` command. The `undo` command will call `Model#undoPlanus()`, which will shift the `currentStatePointer` once to the left, pointing it to the previous PlaNus state, and restores the PlaNus to that state.

![UndoRedoState3](images/UndoRedoState3.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** If the `currentStatePointer` is at index 0, pointing to the initial Planus state, then there are no previous Planus states to restore. The `undo` command uses `Model#canUndoPlanus()` to check if this is the case. If so, it will return an error to the user rather
than attempting to perform the undo.


</div>

The following sequence diagram shows how the undo operation works:

![UndoSequenceDiagram](images/UndoSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `UndoCommand` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.


</div>

The `redo` command does the opposite — it calls `Model#redoPlanus()`, which shifts the `currentStatePointer` once to the right, pointing to the previously undone state, and restores the PlaNus to that state.

<div markdown="span" class="alert alert-info">:information_source: **Note:** If the `currentStatePointer` is at index `planusStateList.size() - 1`, pointing to the latest PlaNus state, then there are no undone Planus states to restore. The `redo` command uses `Model#canRedoPlanus()` to check if this is the case. If so, it will return an error to the user rather than attempting to perform the redo.


</div>

Step 5. The user then decides to execute the command `list-lesson`. Commands that do not modify the PlaNus, such as `list-lesson`, will usually not call `Model#commitPlanus()`, `Model#undoPlanus()` or `Model#redoPlanus()`. Thus, the `planusStateList` remains unchanged.

![UndoRedoState4](images/UndoRedoState4.png)

Step 6. The user executes `clear`, which calls `Model#commitPlanus()`. Since the `currentStatePointer` is not pointing at the end of the `planusStateList`, all PlaNus states after the `currentStatePointer` will be purged. Reason: It no longer makes sense to redo the `event title:project …` command. This is the behavior that most modern desktop applications follow.

![UndoRedoState5](images/UndoRedoState5.png)

The following activity diagram summarizes what happens when a user executes a new command:

![CommitActivityDiagram](images/CommitActivityDiagram.png)

#### Design consideration:

##### Aspect: How undo & redo executes

* **Alternative 1 (current choice):** Saves the entire PlaNus.
  * Pros: Easy to implement.
  * Cons: May have performance issues in terms of memory usage.

* **Alternative 2:** Individual command knows how to undo/redo by
  itself.
  * Pros: Will use less memory (e.g. for `delete-task`, just save the task being deleted).
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

* NUS Student
* has a lot of projects and modules
* can type fast
* wants to track productivity and workload

**Value proposition**: Allows NUS students to manage their tasks and visualise 
them on a calendar as well as time spent on tasks and lessons so as to improve their productivity.



### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …                  | I want to …                                | So that I can…                      |
| -------- | ----------------------- | ------------------------------------------ | ----------------------------------- |
| `* * *`  | new user                | have a help command                        | easily access the user guide.       |
| `* * *`  | user                    | add new task to my list                    |                                     |
| `* * *`  | student                 | know when my tasks are due                 |                                     |
| `* * *`  | user                    | maintain a list of tasks I currently have  |                                     |
| `* * *`  | user with a lot of tasks| delete my task after it is not relevant   | focus on only the unfinished tasks.  |
| `* * *`  | user                    | mark my task as done after it is completed | track the status of the task.       |
| `* *  `  | user                    | find tasks by date                         | easily know what events or deadlines are on that date. |
| `* * *`  | user                    | find tasks by title                        |                                     |
| `* *  `  | user                    | find tasks by description                  |                                     |
| `* *  `  | user                    | find tasks by module it is associated      |                                     |
| `* *  `  | user                    | find completed and uncompleted deadlines   | have a quick glance of what are to be completed. |
| `* * *`  | user                    | edit my tasks                              |                                     |
| `* * *`  | user                    | maintain a list of lessons I currently have  | track  my lessons.                |
| `* * *`  | user with a lot of lessons| delete my lesson after it is not relevant   | focus on only the lessons I currently take. |
| `* *  `  | user                    | find lesson by module code                 | easily know what lessons are associated with a particular module |
| `* *  `  | user                    | find lesson by date                        | easily know what lessons I have on a particular date.            |
| `* *  `  | user                    | find lesson by time                        | easily know what lessons I have on a particular time.            |
| `* * *`  | user                    | find tasks by title                        |                                     |
| `* * *`  | user                    | edit my lessons                            |                                     |
| `* * *`  | user who is interested in tracking my productivity | have a visualisation of my workload in the past week | have a sense of how productive I was in the past week |
| `* * *`  | user who likes visualisations much better | have a calendar view of my tasks/lessons   | have a clear view of what I need to do. |

*{More to be added}*

### Use cases

<div markdown="block" class="alert alert-info">

**:information_source: Notes**<br>

* For all use cases below, the **System** is `PlaNus` and the **Actor** is the `user`, unless specified otherwise.

* A **task** is either an **event** or a **deadline**.

</div>


**Use case: UC01 - Add a deadline**

**Guarantees**

* A deadline will be added only if there is no duplicate deadline existing in PlaNus.

**MSS**

1. User requests to add a deadline to the list.

2. PlaNus adds the deadline to the list.
    
    Use case ends.
    
**Extensions**

- 1a. The given input is invalid.
    
    - 1a1. PlaNus shows an error message.
    
    Use case resumes at step 1.
    
- 1b. The deadline already exists in the list.

    - 1b1. PlaNus shows an error message.
    
    Use case resumes at step 1.
    
<br>

**Use case: UC02 - Add an event**

**Guarantees**

* A deadline will be added only if there is no duplicate deadline existing in PlaNus and it does not
overlap with any other lesson or event in PlaNus.

**MSS**

1. User requests to add an event to the list.

2. PlaNus adds the event to the list.
    
    Use case ends.
    
**Extensions**

- 1a. The given input is invalid.
    
    - 1a1. PlaNus shows an error message.
    
    Use case resumes at step 1.
    
- 1b. The event already exists in the list.

    - 1b1. PlaNus shows an error message.
    
    Use case resumes at step 1.
    
<br>



**Use case: UC03 - List all tasks**

**MSS**

1. User requests to list tasks.

2. PlaNus shows a list of tasks.

  Use case ends.

**Extensions**

  - 2a. The list is empty.

    Use case ends.

<br>

**Use case: UC04 - Mark deadlines as done**

**MSS**

1. User requests to list tasks.

2. PlaNus shows a list of tasks.

3. User requests to mark certain deadlines in the list as done.

4. PlaNus marks the deadline as done.

  Use case ends.

**Extensions**

- 3a. The given index is invalid.

  - 3a1. PlaNus shows an error message.

    Use case resumes at step 3.

- 3b. Some indexes given are not of a deadline.

  - 3b1. PlaNus shows an error message.

    Use case resumes at step 3.

- 3c. Deadlines represented by some indexes are already marked as completed.

  - 3c.1 PlaNus shows an error message.

    Use case resumes at step 3.

<br>

**Use case: UC05 - Find tasks**

**MSS**

1. User <ins>lists a set of tasks (UC03 or UC05)</ins>

2. User requests to find tasks with specified search phrase(s) in specified attribute(s).

3. PlaNus shows a list of tasks that match the specified search phrase(s) in the specified attribute(s).

  Use case ends.

**Extensions**

- 2a. The search phrase is empty or consists of only white spaces.

  - 2a1. PlaNus shows an error message.

    Use case ends.

- 2b. The search phrase is in invalid format or includes invalid characters.

    - 2b1. PlaNus shows an error message.

    Use case ends.

- 3a. The list is empty.

  Use case ends.

<br>

**Use case: UC06 - Edit a task**

**Guarantees**

* A task will be edited only if the attributes of the edited task are all valid and the edited task
does not overlap with any other lesson or event in PlaNus.

**MSS**

1. User <ins>lists a set of tasks (UC03 or UC05)</ins>

2. User requests to edit values of specified attribute(s) of a task.

3. PlaNus shows task with updated attribute(s).

  Use case ends.

**Extensions**
- 2a. The given index is invalid.

  - 2a1. PlaNus shows an error message.

    Use case resumes at step 1.

- 2b. The given value of an attribute is invalid.

  - 2b1. PlaNus shows an error message.

    Use case resumes at step 1.

<br>

**Use case: UC07 - Delete tasks**

**MSS**

1. User <ins>lists a set of tasks (UC03 or UC05)</ins>

2. User requests to delete certain tasks from the list.

3. PlaNus deletes the tasks.

  Use case ends.

**Extensions**

- 1a. The list is empty.

  Use case ends.

- 2a. Some indexes given are invalid.

  - 3a1. PlaNus shows an error message.

    Use case resumes at step 3.

<br>

**Use case: UC08 - Add a lesson**

**Guarantees**

* A new lesson will be added only there is no duplicate lesson already existing in PlaNus and it does not 
overlap with any other lesson or event in PlaNus.

**MSS**

1. User requests to add a lesson to the list.

2. PlaNus adds the lessons as recurring tasks.

  Use case ends.


**Extensions**

- 1a. The given input is invalid.

  - 1a1. PlaNus shows an error message.

    Use case resumes at step 1.

- 1b. The lesson already exists in the list.

  - 1b1. PlaNus shows an error message.

    Use case resumes at step 1.

<br>

**Use case: UC09 - Find lessons**

**MSS**

1. User <ins>lists a set of lessons (UC08 or UC09)</ins>

2. User requests to find lessons with specified search phrase(s) in specified attribute(s).

3. PlaNus shows a list of lessons that match the specified search phrase(s) in the specified attribute(s).

  Use case ends.

**Extensions**

- 2a. The search phrase is empty or consists of only white spaces.

  - 2a1. PlaNus shows an error message.

    Use case ends.

- 2b. The search phrase is in invalid format or includes invalid characters.

    - 2b1. PlaNus shows an error message.

    Use case ends.

- 3a. The list is empty.

  Use case ends.

<br>

**Use case: UC10 - Edit a lesson**

**Guarantees**

* A lesson will be edited only if the attributes of the edited lesson are all valid and the edited lesson
does not overlap with any other lesson or event in PlaNus.

**MSS**

1. User <ins>lists a set of lessons (UC08 or UC09)</ins>

2. User requests to edit values of specified attribute(s) of a lesson.

3. PlaNus shows task with updated attribute(s).

  Use case ends.

**Extensions**
- 2a. The given index is invalid.

  - 2a1. PlaNus shows an error message.

    Use case resumes at step 1.

- 2b. The given value of an attribute is invalid.

  - 2b1. PlaNus shows an error message.

    Use case resumes at step 1.

<br>

**Use case: UC11 - Delete lessons**

**MSS**

1. User <ins>lists a set of lessons (UC08 or UC09)</ins>

2. User requests to delete certain lessons from the list.

3. PlaNus deletes the lessons.

  Use case ends.

**Extensions**

- 1a. The list is empty.

  Use case ends.

- 2a. Some indexes given are invalid.

  - 3a1. PlaNus shows an error message.

    Use case resumes at step 3.

<br>

**Use case: UC12 - Request help**

**MSS**

1. User requests help.

2. PlaNus shows available commands.

  Use case ends.

<br>

**Use case: UC13 - Exit application**

**MSS**

1. User requests to exit application.

2. PlaNus exits application.

  Use case ends.


*{More to be added}*

### Non-Functional Requirements

1. Should work on any _mainstream OS_ as long as it has Java `11` or above installed.

2. Should be able to hold up to 1000 tasks (including recurring lessons) without a noticeable sluggishness in performance for typical usage.

3. A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using a mouse.

4. The product should be easy to use by a novice with no experience of using a task management application.

5. The product should be lightweight and should not exceed 20mb in size.

6. Documentation should be easy to read and user-centric with proper callouts and markdowns.

7. The source code should be open source.

*{More to be added}*

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, OS-X
* **Task**: A task is a collection of details about a specific task that needs to be done. A task can either be an **event**
or a **deadline**.
* **lesson**: A lesson is a collection of details of a specific lesson which happens in a recurring
manner with a specified start and end date and a start and end time.
* **tag**: A tag is an attribute that can be associated with a task or lesson to represent a module code. PlaNus' time analysis
feature is based on the tags of the tasks and lessons.
* **description**: A description is one or two sentences used to explain or add remarks to a task or lesson. It is optional.

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

1. _{ more test cases … }_

### Deleting a task

1. Deleting a task while all tasks are being shown

   1. Prerequisites: List all tasks using the `list` command. Multiple tasks in the list.

   1. Test case: `delete 1`<br>
      Expected: First contact is deleted from the list. Details of the deleted contact shown in the status message. Timestamp in the status bar is updated.

   1. Test case: `delete 0`<br>
      Expected: No task is deleted. Error details shown in the status message. Status bar remains the same.

   1. Other incorrect delete commands to try: `delete`, `delete x`, `...` (where x is larger than the list size)<br>
      Expected: Similar to previous.

1. _{ more test cases … }_

### Saving data

1. Dealing with missing/corrupted data files

   1. _{explain how to simulate a missing/corrupted file, and the expected behavior}_

1. _{ more test cases … }_

|      |      |
| ---- | ---- |
|      |      |
|      |      |
|      |      |
|      |      |
|      |      |
|      |      |
|      |      |
