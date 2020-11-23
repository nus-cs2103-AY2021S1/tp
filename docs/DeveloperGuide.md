---
layout: page
title: Developer Guide
---

1. [Setting Up and Getting Started](#1-setting-up-and-getting-started)
2. [Design](#2-design)
    * 2.1. [Architecture](#21-architecture)
    * 2.2. [UI Component](#22-ui-component)
    * 2.3. [Logic Component](#23-logic-component)
    * 2.4. [Model Component](#24-model-component)
    * 2.5. [Storage Component](#25-storage-component)
    * 2.6. [Common Classes](#26-common-classes)
3. [Implementation](#3-implementation)
    * 3.1. [Categorised Tasks Handling feature in Home tab (Han Wei)](#31-categorised-tasks-handling-feature-in-home-tab-han-wei)
        * 3.1.1. [Feature Introduction](#311-feature-introduction)
        * 3.1.2. [Implementation Details](#312-implementation-details)
        * 3.1.3. [Design Considerations](#313-design-considerations)
            * 3.1.3.1. [Aspect 1](#3131-aspect-1-how-users-can-easily-view-and-control-all-the-tasks)
    * 3.2. [Daily Feature (Yingqi)](#32-daily-feature-yingqi)
        * 3.2.1. [Implementation](#321-implementation)
        * 3.2.2. [Design Considerations](#322-design-considerations)
            * 3.2.2.1. [Aspect 1](#3221-aspect-1-how-users-can-plan-for-the-things-heshe-wants-to-complete-for-the-day)
            * 3.2.2.2. [Aspect 2](#3222-aspect-2-how-users-indicate-that-they-have-completed-a-daily-todo-task)
    * 3.3. [Modules Feature (Ethan)](#33-module-feature-ethan)
        * 3.3.1. [Implementation](#331-implementation)
        * 3.3.2. [Design Considerations](#332-design-considerations)
            * 3.3.2.1. [Aspect 1](#3321-aspect-1-how-modules-are-deleted-and-added-to-tasks)
    * 3.4. [Countdown Feature (Wen Ling)](#34-countdown-feature-wen-ling)
        * 3.4.1. [Implementation](#341-implementation)
        * 3.4.2. [Design Considerations](#342-design-considerations)
            * 3.4.2.1. [Aspect 1](#3421-aspect-1-viewing-events-in-countdowns)
            * 3.4.2.2. [Aspect 2](#3422-aspect-2-adding-an-event-to-countdowns)
    * 3.5. [Planner Feature (Rui Ling)](#35-planner-feature-rui-ling)
        * 3.5.1. [Implementation](#351-implementation)
        * 3.5.2. [Design Considerations](#352-design-considerations)
            * 3.5.2.1. [Aspect 1](#3521-aspect-1-how-to-represent-tasks-list-for-planner-tab)
            * 3.5.2.2. [Aspect 2](#3522-aspect-2-how-users-can-easily-navigate-to-todaystomorrows-tasks-list)
4. [Documentation](#4-documentation)
5. [Testing](#5-testing)
6. [Logging](#6-logging)
    <div style="page-break-after: always;"></div>
7. [Configuration](#7-configuration)
8. [Dev-ops](#8-dev-ops)
9. Appendix A: [Requirements](#appendix-a-requirements)
    * A.1. [Product Scope](#a1-product-scope)
    * A.2. [User Stories](#a2-user-stories)
    * A.3. [Use Cases](#a3-use-cases)
    * A.4. [Non-Functional Requirements](#a4-non-functional-requirements)
    * A.5. [Glossary](#a5-glossary)
10. Appendix B: [Instructions for Manual Testing](#appendix-b-instructions-for-manual-testing)
    * B.1. [Launch and Shutdown](#b1-launch-and-shutdown)
    * B.2. [Features in Home tab](#b2-features-in-home-tab)
    * B.3. [Daily Feature](#b3-daily-feature)
    * B.4. [Module Feature](#b4-module-feature)
    * B.5. [Countdown Feature](#b5-countdown-feature)
    * B.6. [Planner Feature](#b6-planner-feature)
    * B.7. [Saving Data](#b7-saving-data)
    
--------------------------------------------------------------------------------------------------------------------

# 1. Setting up and getting started

You can refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

# 2. Design

## 2.1. Architecture

<img src="images/ArchitectureDiagram.png" width="450" />

The ***Architecture Diagram*** given above explains the high-level design of TR4CKER. Given below is a quick overview
of each component.

<div markdown="span" class="alert alert-primary">

:bulb: **Tip:** The `.puml` files used to create diagrams in this document can be found in the
[diagrams](https://github.com/AY2021S1-CS2103T-T10-2/tp/tree/master/docs/diagrams/) folder. Refer to the
[_PlantUML Tutorial_ at se-edu/guides](https://se-education.org/guides/tutorials/plantUml.html) to learn how to create
and edit diagrams.

</div>

**`Main`** has two classes called [`Main`](https://github.com/AY2021S1-CS2103T-T10-2/tp/tree/master/src/main/java/seedu/tr4cker/Main.java)
and [`MainApp`](https://github.com/AY2021S1-CS2103T-T10-2/tp/tree/master/src/main/java/seedu/tr4cker/MainApp.java).
It is responsible for,
* At TR4CKER launch: Initializes the components in the correct sequence, and connects them up with each other.
* At shut down: Shuts down the components and invokes cleanup methods where necessary.

[**`Commons`**](#common-classes) represents a collection of classes used by multiple other components.

The rest of TR4CKER consists of four components.

* [**`UI`**](#ui-component): The UI of TR4CKER.
* [**`Logic`**](#logic-component): The command executor.
* [**`Model`**](#model-component): Holds the data of TR4CKER in memory.
* [**`Storage`**](#storage-component): Reads data from, and writes data to, the hard disk.

Each of the four components,

* defines its *API* in an `interface` with the same name as the Component.
* exposes its functionality using a concrete `{Component Name}Manager` class (which implements the corresponding API
`interface` mentioned in the previous point.

For example, the `Logic` component (see the class diagram given below) defines its API in the `Logic.java` interface and
exposes its functionality using the `LogicManager.java` class which implements the `Logic` interface.

![Class Diagram of the Logic Component](images/LogicClassDiagram.png)

**How the architecture components interact with each other**

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues
the command `delete 1`.

<img src="images/ArchitectureSequenceDiagram.png" width="574" />

The sections below give more details of each component.

## 2.2. UI component (Han Wei)

![Structure of the UI Component](images/UiClassDiagram.png)

**API** :
[`Ui.java`](https://github.com/AY2021S1-CS2103T-T10-2/tp/tree/master/src/main/java/seedu/tr4cker/ui/Ui.java)

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `TaskListPanel`,
`StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class.

The `UI` component uses JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are
in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/AY2021S1-CS2103T-T10-2/tp/tree/master/src/main/java/seedu/tr4cker/ui/MainWindow.java)
is specified in [`MainWindow.fxml`](https://github.com/AY2021S1-CS2103T-T10-2/tp/tree/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* Executes user commands using the `Logic` component.
* Listens for changes to `Model` data so that the UI can be updated with the modified data.

## 2.3. Logic component

![Structure of the Logic Component](images/LogicClassDiagram.png)

**API** :
[`Logic.java`](https://github.com/AY2021S1-CS2103T-T10-2/tp/tree/master/src/main/java/seedu/tr4cker/logic/Logic.java)

1. `Logic` uses the `Tr4ckerParser` class to parse the user command.
1. This results in a `Command` object which is executed by the `LogicManager`.
1. The command execution can affect the `Model` (e.g. adding a Task).
1. The result of the command execution is encapsulated as a `CommandResult` object which is passed back to the `Ui`.
1. In addition, the `CommandResult` object can also instruct the `Ui` to perform certain actions, such as displaying help to the user.

Given below is the Sequence Diagram for interactions within the `Logic` component for the `execute("delete 1")` API call.

![Interactions Inside the Logic Component for the `delete 1` Command](images/DeleteSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `DeleteCommandParser` should
end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>

## 2.4. Model component

![Structure of the Model Component](images/ModelClassDiagram.png)

**API** : [`Model.java`](https://github.com/AY2021S1-CS2103T-T10-2/tp/tree/master/src/main/java/seedu/tr4cker/model/Model.java)

The `Model`,

* stores a `UserPref` object that represents the user’s preferences.
* stores the TR4CKER data.
* exposes an unmodifiable `ObservableList<Task>` that can be 'observed' e.g. the UI can be bound to this list so that
the UI automatically updates when the data in the list change.
* does not depend on any of the other three components.

<div markdown="span" class="alert alert-info">:information_source: **Note:** An alternative (arguably, a more OOP) model
is given below. It has a `Tag` list in the `Tr4cker`, which `Task` references. This allows `Tr4cker` to only require one
`Tag` object per unique `Tag`, instead of each `Task` needing their own `Tag` object.<br>
![BetterModelClassDiagram](images/BetterModelClassDiagram.png)

</div>

## 2.5. Storage component

![Structure of the Storage Component](images/StorageClassDiagram.png)

**API** : [`Storage.java`](https://github.com/AY2021S1-CS2103T-T10-2/tp/tree/master/src/main/java/seedu/tr4cker/storage/Storage.java)

The `Storage` component:
* can save `UserPref` objects in json format and read it back
* can save TR4CKER data in json format and read it back

## 2.6. Common classes

Classes used by multiple components are in the `seedu.tr4cker.commons` package.

--------------------------------------------------------------------------------------------------------------------

# 3. Implementation

This section describes some noteworthy details on how certain features are implemented.

## 3.1. Categorised Tasks Handling feature in Home tab (Han Wei)

### 3.1.1. Feature introduction
This feature allows users to view and handle tasks under 3 categories separately.

### 3.1.2. Implementation details
To implement the UI of this feature, there will be a `PendingTaskListPanel`, `ExpiredTaskListPanel` and `CompletedTaskListPanel` classes
in the `ui` package, modified from the `TaskListPanel` class. To implement the commands of
this feature, there are `EditExpiredCommnad` and `DeleteExpiredCommand` classes that extends from the `EditCommand` and `DeleteCommand` in the `logic` package respectively.

This feature comes in the form of a Task List panel, which is made up of three sub-panels:
1. Pending tasks (Incomplete tasks that are not overdue)
2. Expired tasks (Incomplete tasks that are overdue)
3. Completed tasks (Archived tasks)

Upon starting TR4CKER or after using the `list` command, tasks which deadlines have passed will automatically appear under Expired tasks panel.
Users can modify tasks displayed in the Expired task panel using the Edit command.

The following diagram shows the sequence flow of a `EditExpiredCommand` which modifies the deadline of a task in the Expired task list:
![EditExpiredActivityDiagram](images/EditExpiredActivityDiagram.png)

Figure 1: Edit deadline of expired task Activity Diagram

Once the user marks a task as complete, it will automatically appear under Completed tasks.

### 3.1.3. Design considerations:

#### 3.1.3.1. Aspect 1: How users can easily view and control all the tasks

* **Current Choice:** Splits the tasks into 3 panels according to their completion status and deadlines.
  * Pros: Able to support commands that specifically deal with each type of tasks.
  * Pros: Allows users to view their tasks separately instead of browsing through a long task list.
  * Cons: UI looks cramp when there are too many panels laid out horizontally.

* **Alternative 1:** Use a single task list.
  * Pros: Task list would have a large horizontal space to display long task descriptions and task names instead of having to wrap text.
  * Cons: Less user-friendly because users would have to manually compare the deadlines of the tasks with today's date to find out which are the expired tasks.

**Justification for current choice:** To the users, expired tasks that are overdue but not completed deserve more attention than pending tasks.
 Users would want to either remove the expired task or continue working on it. Therefore, we felt a need to display them separately and create commands that specifically handle Expired tasks.
 In addition, since tasks are sorted in the chronological order of their deadlines, having a centralised task list would mean that both completed and incomplete tasks would be mixed together,
 which does not allow users to focus on their incomplete tasks.

## 3.2. Daily feature (Yingqi)
Tracker has a daily feature that allows users to add current tasks to a todo list for the day.
This feature allows users to plan for what they want to do for a particular day.

The 3 main functions of the Daily feature are to:
1. Add tasks from existing pending task list to a todo list
2. Display all tasks that the user wants to do for the day
3. Delete a daily todo task when it is done for the day

### 3.2.1. Implementation
The UI of the Daily feature is facilitated by the `DailyPanel` and `TodoCard` classes which will show users all daily todo tasks as a list in the Daily tab.
To implement the commands of this Daily feature, there are `TodoCommand`, `TodoCommandParser` , `DailyCommand` and `DailyCommandParser` classes in the `logic` package.

The following diagram shows the sequence flow when a task gets added into the `DailyPanel`:
![DailySequenceDiagram](images/DailySequenceDiagram.png)
Figure 2: Sequence Diagram for Daily tab

### 3.2.2. Design Considerations

#### 3.2.2.1. Aspect 1: How users can plan for the things he/she wants to complete for the day

* **Current Choice:** When users use list command to view all tasks, only task names are shown.
  * Pros: A neater list is being shown.
  * Pros: Users can look through the list quickly and decide what to add to daily todo list.
  * Cons: Important details such and deadline and descriptions are omitted, users may not know which task is more urgent to do.

* **Alternative 1:** When users decides to add a particular task to daily todo list, details of that task is shown.
  * Pros: Users have clearer idea of the details of the tasks that he/she wants to complete for the day.
  * Cons: Details are shown only when the tasks is already added, if the user do not want to do it for the day, he/she has to delete it from todo list.

**Justification for current choice:** Users would already have details of the tasks in mind and therefore they will tend
to add the tasks they feel that are more urgent to daily todo list. Hence details can be ommited when users select tasks
to add into daily todo list.

#### 3.2.2.2. Aspect 2: How users indicate that they have completed a daily todo task
* **Current Choice:** 
There are 3 ways for users to remove a todo from daily todo list:
    1. Delete the pending task from home page, the corresponding task in daily todo list will be removed.
    2. Delete directly from daily todo list, the corresponding pending task in home page is still present.
    3. Indicate that they have completed a pending task, the corresponding todo task will be removed from daily todo list.
    
  * Pros: Users can remove a daily todo task under different scenarios.
  * Cons: May delete a daily todo task unexpectedly and have to add it back again.

* **Alternative 1:** When users want to delete a todo task, prompt a message to double check with the user.
  * Pros: Prevent users from deleting tasks unexpectedly.
  * Cons: Users have to enter more commands for deleting
  
* **Justification for current choice:** Users would have decided what to remove from their list before deciding to delete.
It is not necessary to prompt the user on whether to delete a task.
  
## 3.3. Module feature (Ethan)
TR4CKER has a module tab which provides users with an alternate view of tasks. Tasks are arranged in shorter lists categorized by modules.
This feature is to allow users to have a more focused overview of tasks, and know how many tasks are
pending for each module as opposed to the most-urgent-task-first organization in  main task list.

The 2 main functions of the Module feature are to:
1. Display all modules taken by the user.
2. Display the list of yet to be completed tasks under each module.

### 3.3.1. Implementation
To implement the UI of this Module feature, there will be a `ModuleCard` & `ModuleListPanel` classes
in the `ui` package, modified from the `TaskCard` and `TaskListPanel` classes. To implement the commands of
this module feature, there are `ModuleCommnad` and `ModuleCommandParser` classes in the `logic` package.

A module in the Modules tab has its own `ModuleListPanel`, updated whenever new tasks are added with the module or are
edited to be associated under the module.

The following diagram shows the sequence flow when a task gets added to the `ModuleListPanel` of a module:
![TaskWithModuleActivityDiagram](images/TaskWithModuleActivityDiagram.png)
Figure 3: Adding task to `ModuleTaskListPanel` of a module.

During the initialization of TR4CKER, a `ModuleListPanel` will be created with a copy of the filtered Module list obtained
from executing `Logic#getFilteredModuleList` to fill the list of modules in the Module tab. Each module consists of
a main panel, and within the main panel is a list of tasks. `ModuleCard` is used as the main panel, containing a list view
of tasks stored each in a `SmallTaskCard`. 

When users add new modules, the `ModuleListPanel` will be updated to contain the new `ModuleCard`. Similarly, assigning tasks
to a module will create a `SmallTaskCard` to appear, in the task list of a specific `ModuleCard`. The Module tab will update
whenever a user switches to it.

The following sequence diagram (Figure 4) shows the process of adding a module,
following execution of the command `modules n/Software Engineering m/CS2103T`:

![ModuleSequenceDiagram](images/ModuleSequenceDiagram.png)
Figure 4: Module Sequence Diagram during execution of `modules n/Software Engineering m/CS2103T`

Whenever a user executes a `ModuleCommand`, like `modules n/Software Engineering m/CS2103T`, `MainWindow` will be called to
execute the command. It will then call `LogicManager` to execute, followed by parsing of command in `Tr4ckerParser`.
`Tr4ckerParser` will create a new instance of `ModuleCommandParser` to parse the user's input. After parsing and checking
the validity of user's input, a new `ModuleCommand` instance is created. This new instance `moduleCommand` will be passed
back to `LogicManager` to execute on the `Model` in `ModuleCommand`. After executing, a new instance `CommandResult` `commandResult`
is created. `commandResult` will be passed back to `MainWindow`, which then proceeds to create a new `ModuleCard` once
the user switches to the Modules tab.

### 3.3.2. Design considerations:

#### 3.3.2.1. Aspect 1: How modules are deleted and added to tasks

* **Current Choice:** Modules cannot be deleted if there are existing tasks tagged with the module. Tasks can only be
tagged with an existing module.
  * Pros: Safer, will not have stray tasks with non-existent modules.
  * Pros: User has safeguard against deleting modules that still has pending tasks.
  * Cons: Less flexible, extra steps for the user to create modules before assigning tasks, and delete tasks before
  deleting a module.

* **Alternative 1:** Allow both modules to be deleted and tasks to be tagged regardless of the others' existence.
  * Pros: More convenient for user to use without restrictions.
  * Cons: Prone to error, user may assign incorrectly with typos.

**Justification for current choice:** Better reliability of the feature by reducing possible errors by the user. As errors
will cause more time wasted for the user to fix them anyway, it seems that it is better to incur some overhead to
prevent making a mess altogether.

## 3.4. Countdown feature (Wen Ling)
TR4CKER has a Countdown tab which allows users to add important events that they would like TR4CKER to countdown to.
This feature allows users to isolate the most important time sensitive events and deadlines, and tells the user exactly
how many days do they have to a certain event, which enhances the tracking experience.

These are the main functions of the Countdown feature:
1. Display a list of all events
2. Display the number of days left to an event and the date of an event
3. Add an event
4. Delete an event
5. Count the total number of events in a specified number of days from current date

### 3.4.1. Implementation
The UI of the Countdown feature is facilitated by the `countdown` package in `model` and `ui` packages. `CountdownCommand`
and `CountdownCommandParser` classes in the `logic` package implements this feature.
The class diagram (Figure 5) shown below summarises the implementation of the UI of the countdown feature:

![CountdownClassDiagram](images/CountdownClassDiagram.png)

Figure 5: Countdown Class Diagram

During the initialisation of TR4CKER, `CountdownTabWindow` will be initialised, together with `CountdownEventListPanel`.
`CountdownTabWindow` will execute `Logic#getFilteredEventList` to update the events list shown in Countdown tab.
The list of events will be sorted before it is displayed in the `CountdownEventListPanel`, and the events which has
passed will be placed at the end of the list, behind all the upcoming events.

When users execute countdown commands, mainly the commands to add or delete an event, the events in the
`CountdownEventListPanel` will be updated to reflect the new list of events, and the sorted order will be maintained.
This is done by initialising new `CountdownEventCard` objects to be placed in the `CountdownEventListPanel`, or by
removing those that are deleted.

The following sequence diagram (Figure 6) shows the execution of `countdown days/7`:

![CountdownSequenceDiagram](images/CountdownSequenceDiagram.png)
Figure 6: Countdown Sequence Diagram

When a user executes a `CountdownCommand`, `countdown days/7` to count the number of events in the upcoming 7 days from
from today, `MainWindow` begins the execution of the command. `LogicManager` is then called to execute the
command. The command is then parsed in `Tr4ckerParser`, and `Tr4ckerParser` will create a new instance of
`CountdownCommandParser` to parse the user's input. After parsing and checking the validity of user's input, a
new `CountdownCommand` instance is created. This new instance `countdownCommand` will be passed back to `LogicManager`
to execute it on `Model` in the `CountdownCommand`. After executing, a new instance `CommandResult`, `commandResult` is
created. `commandResult` will be passed back to `MainWindow`, then it will be displayed in the results display box in
`CountdownTabWindow`.

Within the execution of `countdownCommand`, the list in `Model#getfilteredEventList`, to used to get a list of events
for `countdownCommand` to work on.

The following activity diagram summarises the flow of executing the various functions of `CountdownCommand`:
![CountdownCommandActivityDiagram](images/CountdownActivityDiagram.png)
Figure 7: Countdown Activity Diagram

The activity diagram shows all the possible paths TR4CKER can take when a user executes a `CountdownCommand`, which is
identified by the use of the command word, `countdown`. After a command is entered, it's arguments are parsed, to know
which function the user wants to use:
1. Add an event
2. Delete an event
3. Count the number of events in a certain number of upcoming days
4. Switch to Countdown tab

The result of the command will be displayed accordingly, either in the events list box, or the result display box at
near the bottom of the GUI.

### 3.4.2. Design considerations:

#### 3.4.2.1. Aspect 1: Viewing events in Countdowns

* **Current Choice:** Display all events in the `upcoming events` list in the Countdown tab, where users can add or
delete an event and have it be directly reflected in the list.
  * Pros: Users can see an overview of all events.
  * Pros: Users will be able to spot any mistakes in adding an event easily.
  * Cons: Less visually attractive as it is just a list.

* **Alternative 1:** Display only the earliest upcoming event prominently, and allow users to navigate to view
subsequent or previous events using `countdown next` and `countdown previous` commands.
  * Pros: More visually attractive, less cluttered, as users only see one event at a time.
  * Cons: May be difficult to navigate if there is a long list of events.
  * Cons: User will not know how many events they have in total.

**Justification for current choice:** We consider how users who are using the countdown feature will prefer to be able
to have a bird's eve view of all their events, and be able to gauge at a glance how many upcoming events they have.
(For example, how many exams they have and what is the range of dates that they fall on.) The first implementation
is also less prone to user errors as they can easily see whether the event is present in the list.

#### 3.4.2.2. Aspect 2: Adding an event to Countdowns

* **Current Choice:** Add an event using the `countdown n/NAME d/DATE` command, to add directly to the list of events.
  * Pros: Command is short and sweet and is easy for users to remember.
  * Pros: Not reliant on the main TaskList in TR4CKER.
  * Cons: Users have to use a separate command if they want to track the same item as a task and as an event.

* **Alternative 1:** Allow users to add an event to events list, based on an existing task in task list, through a
command to convert a task to an event.
  * Pros: Users can type a shorter command if they want to add a task that is already in tasks list.
  * Cons: An event has lesser fields than a task, so using tasks to add an event is more complicated than necessary.

**Justification for current choice:** We recognise that an item in tasks lists is often very different from an item in
events list, (a user might add an exam or a festival to events list, whereas a task might be an assignment or a piece
of homework) so it would be better to have separate commands to reduce dependency between different types of things
that are being tracked, as well as to reduce confusion as to which tab to use.

## 3.5. Planner feature (Rui Ling)
TR4CKER has a planner feature which provides users to view the calendar side-by-side with the tasks that are due on
specified day. This feature is to allow users to have a clearer view of their schedules and allow them to plan their
time ahead, and hence increasing productivity.

The 2 main functions of Planner command are to:
1. Switch to planner tab
2. Display an overview of tasks for today/tomorrow and on specified date/month

### 3.5.1. Implementation
To implement the UI of this planner feature, there is a `planner` package in both `model` and `ui` packages. To implement
the commands of this planner feature, there are `PlannerCommand` and `PlannerCommandParser` classes in `logic` package.
The following class diagram (Figure 8) summarises how the UI aspect of this planner feature is being implemented:
![PlannerClassDiagram](images/PlannerClassDiagram.png)
Figure 8: Planner Class Diagram

During the initialisation of TR4CKER, `PlannerTabWindow` will be initialised, together with `PlannerCalendarPanel` and
`PlannerTaskListPanel`. `PlannerTabWindow` will execute `Logic#getPlannerFilteredTaskList` to get the current date's filtered
task list. Subsequent updates to filtered task list will be updated through the execution of `Logic#updatePlannerFilteredTaskList`.
The month and year of the calendar will be set in `PlannerCalendarPanel`. There are multiple `PlannerDayCard`
in 1 `PlannerCalendarPanel`. `PlannerDayCard` serves to store the details of each `PlannerDay`, which contains the date
of each grid in the `PlannerCalendarPanel`, the circled date (today) and the indicators for each date. When users
execute planner commands, the month and year of the calendar will be updated in `PlannerCalendarPanel`. Existing details
of the calendar will also be cleared through `PlannerCalendarPanel#clearCalendar` and `PlannerDayCard#clear`. After
clearing the calendar, new indicators would be set through `PlannerTabWindow#updateIndicator`, `PlannerCalendarPanel#updateIndicator`
and `PlannerDayCard#updateIndicator`. The title of the tasks list will be updated through `PlannerTaskListPanel#updateTitle`
and the tasks list will also be updated.

The following sequence diagram (Figure 9) shows how the planner feature works when a user executes `planner goto/today`:
![PlannerSequenceDiagram](images/PlannerSequenceDiagram.png)
Figure 9: Planner Sequence Diagram during execution of `planner goto/today`

<div markdown="span" class="alert alert-info">:information_source: 

**Note:** The lifelines for all the classes except `MainWindow` should
end at the destroy markers _(X)_ but due to the limitation of PlantUML, the lifeline reaches the end of diagram.

</div>

When a user executes a `PlannerCommand` of `planner goto/today`, `MainWindow` will be called to execute the command. It will
then call `LogicManager` to execute, followed by parsing of command in `Tr4ckerParser`. `Tr4ckerParser` will create a new
instance of `PlannerCommandParser` to parse the user's input. After parsing and checking the validity of user's input, a
new `PlannerCommand` instance is created. This new instance `plannerCommand` will be passed back to `LogicManager` to execute
on the `Model` in `PlannerCommand`. After executing, a new instance `CommandResult` `commandResult` is created. `commandResult`
will be passed back to `MainWindow`, then it will be checked in `PlannerTabWindow` if the user wants to switch to Planner
tab, or to view a specific date/month. In this case, user wants to go to today which is a specific date. After that, the
calendar and tasks list are updated in Planner tab, and user can now see the results.

The following activity diagram (Figure 10) summarises what happens when a user executes the 2 main functions of
`PlannerCommand`:
![PlannerActivityDiagram](images/PlannerActivityDiagram.png)
Figure 10: Planner Activity Diagram

This activity diagram shows all the possible paths TR4CKER can take when a user executes a `PlannerCommand`. After
inputting a command, the command is parsed. By checking the arguments provided by the user, it can either mean the
user wants to:
1. switch to Planner tab
2. display an overview of tasks for today/tomorrow and on a specified date/month.

If user wants to switch to Planner tab, TR4CKER will display the planner, and a feedback will be provided to the user. If
user wants to display an overview of tasks, the validity of input will be checked. The input can take these 5 different paths:
1. today
2. tomorrow
3. specified date
4. specified month
5. error (not shown here).

Depending on the inputs, the calendar view and tasks list will be updated accordingly. Planner tab is then displayed to
the user and a feedback is provided.

### 3.5.2. Design considerations:

#### 3.5.2.1. Aspect 1: How to represent tasks list for Planner tab

* **Current Choice:** Have a separate tasks list for Planner tab, which is the `PlannerTaskListPanel`class. This class
is different from the `TaskListPanel` class which is mainly used for the TR4CKER tab.
  * Pros: Obey Single Responsibility Principle whereby each class should only have one responsibility.
  * Pros: Developers using `PlannerTaskListPanel` class would not need to change the functionality of `TaskListPanel`
  class to suit what functionalities they want for the tasks list in Planner tab.
  * Cons: Additional coding and time would be required to create another class to cater to only tasks list in Planner tab.

* **Alternative 1:** Use the existing `TaskListPanel` class for the tasks list in Planner tab.
  * Pros: Do not need to code for another class and more time could have been spent on other features.
  * Cons: Do not obey Single Responsibility Principle as now a class would need to have 2 types of functionalities for
  different purposes.
  * Cons: Some functionalities for tasks list in TR4CKER tab and Planner tab differ, so accommodating for both functionalities
  in a single class is quite difficult.

**Justification for current choice:** After thinking about how having a separate class for the tasks list in Planner tab
would require more time and effort, I still choose to implement the current choice. This is because there are varying
functionalities to the tasks list in TR4CKER tab and Planner tab. For example, tasks list in TR4CKER tab only shows non-expired
tasks and shows every task on the list. However, tasks list in Planner tab shows any date that the user wants to go to
and can even show expired tasks if user chooses to go to a date in the past. Therefore, although it required more time
and effort, I think that having a separate class would be better since the tasks list in both tabs act differently.

#### 3.5.2.2. Aspect 2: How users can easily navigate to today's/tomorrow's tasks list

* **Current Choice:** Use the same `planner` command to navigate to today's/tomorrow's tasks list. For example,
`planner goto/today` would navigate users to today's tasks list and `planner goto/tomorrow` would navigate users to
tomorrow's tasks list. Short forms are also provided such as `tdy` and `tmr`.
  * Pros: User-friendly as users would only need to know 1 command.
  * Pros: Users can use short forms, which increase convenience.
  * Cons: Users may not utilise this feature as they do not know the existence of it.

* **Alternative 1:** Separate commands to allow users to navigate to today's/tomorrow's tasks list.
  * Pros: Clearer error messages to prompt users that the input does not conform to standard.
  * Cons: Need to ensure that the implementation of each individual command is correct.
  * Cons: Not as user-friendly as users would need to know multiple commands now.

**Justification for current choice:** After thinking about how different commands would also have their own advantages,
I chose to implement the current choice. The current implementation would allow users to only know 1 command, which would
fairly be more user-friendly, especially after considering how TR4CKER also has many other commands available.
The problem of users not knowing the existence of this command could be solved by documenting this feature clearly in the
User Guide of TR4CKER.

--------------------------------------------------------------------------------------------------------------------

# 4. Documentation
To understand more about the documentation used in TR4CKER, you can read up the [Documentation guide](Documentation.md).

# 5. Testing
To understand more about the testing used in TR4CKER, you can read up the [Testing guide](Testing.md).

# 6. Logging
To understand more about the logging used in TR4CKER, you can read up the [Logging guide](Logging.md).

# 7. Configuration
To understand more about the configuration used in TR4CKER, you can read up the [Configuration guide](Configuration.md).

# 8. Dev-Ops
To understand more about the Dev-Ops used in TR4CKER, you can read up the [DevOps guide](DevOps.md).

--------------------------------------------------------------------------------------------------------------------

# Appendix A: Requirements

## A.1. Product scope

**Target user profile**:

* busy SoC students
* has a need to manage a significant number of tasks, such as assignments
* prefer desktop applications over other types
* can type fast
* prefers typing to mouse interactions
* is reasonably comfortable using CLI applications

**Value proposition**: TR4CKER is a desktop application that helps SoC students track their tasks, meet their deadlines,
and stay productive. It is optimized for Computing students familiar with CLI, who can manage their tasks efficiently
by typing in commands.

## A.2. User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​                                | I want to …​                                          | So that I can…​                                                     |
| -------- | ----------------------------------------- | -------------------------------------------------------- | ---------------------------------------------------------------------- |
| `* * *`  | student                                   | add a new task                                           |                                                                        |
| `* * *`  | student                                   | delete a task                                            | remove tasks that I have completed or no longer need                   |
| `* * *`  | student                                   | edit a task                                              | change the tasks that I have entered wrongly without deleting it       |
| `* * *`  | organised student                         | view all pending tasks                                   | have an overview of all tasks that I currently have                    |
| `* * *`  | forgetful student                         | view all expired tasks                                   | know which tasks I have not yet completed but are already expired      |
| `* * *`  | goal-oriented student                     | view all completed tasks                                 | know how productive I have been                                        |
| `* * *`  | student                                   | find a task                                              | find the tasks that match the keywords that I want to find             |
| `* * *`  | student                                   | mark a task as completed                                 | when I complete a task, I want to mark it as completed                 |
| `* * *`  | forgetful student                         | view my tasks in chronological order                     | know which tasks are due soon                                          |
| `* * *`  | user                                      | exit from TR4CKER                                        | indicate that I am done with what I wanted to do                       |
| `* * *`  | task-oriented student                     | add my tasks to daily to-dos                             | set a goal for each day                                                |
| `* * *`  | student who wants to plan future tasks    | tag my assignments and tasks with module codes           | I can have an overview of the tasks I need to complete for each module |
| `* * *`  | student                                   | view the tasks tagged to each module                     | know how much time is required for each module                         |
| `* * *`  | unmotivated student                       | add countdowns                                           | countdown to special events to motivate me                             |
| `* * *`  | student                                   | delete countdowns                                        | delete unwanted countdowns                                             |
| `* * *`  | visual student                            | view my tasks in a calendar view                         | know how busy I will be for that particular month                      |
| `* * *`  | visual student                            | view my tasks due on certain date                        | know how busy I will be for that day                                   |
| `* * *`  | organised student                         | know how many tasks are due on a certain day             | plan ahead of my schedule                                              |
| `* *`    | potential user                            | see the sample data                                      | I know what TR4CKER can do when I am using it in the future            |
| `* *`    | user who is ready to use TR4CKER          | purge all sample data                                    | I can input my own data and explore the usage of it                    |
| `* *`    | forgetful student                         | see the upcoming task deadlines                          | I do not miss my deadlines                                             |
| `* *`    | student who wants to finish tasks on time | set my own target deadlines                              | I can complete them before the actual deadlines to plan future tasks   |
| `* *`    | lazy user                                 | use shortcuts for some features that are used frequently | it is more convenient to use TR4CKER                                   |
| `*`      | beginner in using TR4CKER                 | key in one module I am currently taking                  | I can know how TR4CKER works                                           |
| `*`      | student with high workload                | track the modules I am currently taking                  | I do not forgot what modules I am taking                               |
| `*`      | student                                   | track my assignment weightage                            | I can know how much I need to score to get my desired grade            |
| `*`      | clumsy student                            | undo some accidental edits                               | I do not need to type the same inputs again                            |
| `*`      | busy student                              | use TR4CKER quickly                                      | I can get back to my tasks                                             |
| `*`      | animal lover                              | feed a cat by completing my tasks                        | to motivate myself to complete tasks on time                           |
| `*`      | forgetful student                         | receive reminders on upcoming tasks                      | I will not miss out any assignments                                    |
| `*`      | student who wants to know his time usage  | view my weekly progress reports                          | I can plan my future time well                                         |
| `*`      | professional user                         | utilise all the features that are available to me        | I can maximise my efficiency                                           |
| `*`      | student                                   | keep short notes of my modules                           | remind myself what is important                                        |

## A.3. Use cases

(For all use cases below, the **System** is the `TR4CKER`: application and the **Actor** is the `user`, unless specified otherwise)

**Use case: UC01 - Add a new task**

**MSS**

1.  User requests to add a new task.
2.  TR4CKER requests for task details.
3.  User provides task details.
4.  TR4CKER inserts the new task to user's Pending Tasks list.

    Use case ends.

**Extensions**

* 3a. TR4CKER detects an error in entered task details.
    * 3a1. TR4CKER requests for the correct task details.
    * 3a2. User enters the new task details.

    Steps 3a1-3a2 are repeated until the details entered are correct.

    Use case resumes at step 4.

**Use case: UC02 - Edit a Pending task**

**MSS**

1.  User requests to <u>list Pending tasks (UC08)</u>.
2.  User requests to edit a specific task in the Pending Tasks list.
3.  TR4CKER asks for the task index and edited fields.
4.  User provides the task index and the new edited task field.
5.  TR4CKER updates the selected field accordingly.

    Use case ends.

**Extensions**

* 4a. TR4CKER detects an error in the entered task index or edited task field.
    * 4a1. TR4CKER shows an error message.

    Use case end.

**Use case: UC03 - Edit an Expired task**

**MSS**

1.  User requests to edit a specific task in the Expired Tasks list.
2.  TR4CKER asks for the task index and edited fields.
3.  User provides the task index and the new edited task field.
4.  TR4CKER updates the selected field accordingly.

    Use case ends.

**Extensions**

* 3a. TR4CKER detects an error in the entered task index or edited task field.
    * 3a1. TR4CKER shows an error message.

    Use case end.

**Use case: UC04 - Delete a Pending task**

**MSS**

1.  User requests to <u>list Pending tasks (UC08)</u>.
2.  User requests to delete a specific task in the Pending Tasks list.
3.  TR4CKER asks for the task index.
4.  User provides index.
5.  TR4CKER deletes the task from the list.

    Use case ends.

**Extensions**

* 4a. TR4CKER detects an error in entered task index.
    * 4a1. TR4CKER shows an error message.

    Use case ends.

**Use case: UC05 - Delete an Expired task**

**MSS**

1.  User requests to delete a specific task from the Expired Tasks list.
2.  TR4CKER asks for the task index.
3.  User provides index.
4.  TR4CKER deletes the expired task from the Expired Tasks list.

    Use case ends.

**Extensions**

* 3a. TR4CKER detects an error in entered task index.
    * 3a1. TR4CKER shows an error message.

    Use case ends.

**Use case: UC06 - Mark a Pending task as done**

**MSS**

1.  User requests to <u>list Pending tasks (UC08)</u>.
2.  User requests to mark a specific task in the list as done.
3.  TR4CKER marks the task as done and display task under Completed Tasks list.

    Use case ends.

**Extensions**

* 2a. TR4CKER detects an error in entered task index.

    * 2a1. TR4CKER shows an error message.

     Use case ends.

**Use case: UC07 - Find Pending tasks by keywords**

1.  User requests to find tasks using specified keyword(s).
2.  TR4CKER updates the Pending Tasks list with the Pending tasks that match any of the given keyword(s).

    Use case ends.

**Extensions**

* 2a. No tasks match the specified keyword(s).

    * 2a1. TR4CKER shows a 'no matches' error message.

    Use case ends.

**Use case: UC08 - List Pending tasks**

**MSS**

1.  User requests to list tasks.

2.  TR4CKER shows a list of all Pending tasks.

    Use case ends.

**Extensions**

* 2a. TR4CKER's Pending Tasks list is empty.

    * 2a1. TR4CKER shows an empty list.

    Use case ends.

**Use case: UC09 - Switch to Daily tab**
   
**MSS**

1. User requests to go to Daily tab.
2. TR4CKER switches tab to Daily tab.

    Use case ends

**Extensions**

* 1a. User entered the wrong command.
    
    * 1a1. An error message is shown.
    
    Use case ends.

**Use case: UC10 - Add a todo task to Daily tab**

1.  User requests to <u>list Pending tasks (UC08)</u>.
2.  User requests to add a specific pending task in the daily todo list.
3.  TR4CKER adds the task in daily todo list.

    Use case ends

**Extensions**

* 2a. TR4CKER detects an error in entered task index.

    * 2a1. An error message is shown.
    
    Use case ends.

**Use case: UC11 - Delete a todo task from Daily tab**

1. User <u>switches to Daily tab(UC09)</u>.
2. User requests to delete a specific todo task in the daily todo list.
3. TR4CKER deletes the todo task from daily todo list.

    Use case ends

**Extensions**

* 2a. TR4CKER detects an error in entered task index.

    * 2a1. An error message is shown.
    
    Use case ends.
    
**Use case: UC12 - Switching to Module tab**

**MSS**

1.  User requests to go to Module tab.

2.  TR4CKER switches tab to Modules tab.

**Extentions**

* 1a. User entered the wrong command.

    * 1a1. An error message is shown.

    Use case ends.

**Use case: UC13 - Add a module to Module tab**

**MSS**

1.  User requests to <u>switch to Module tab (UC12)</u> to view current list of modules.

2.  TR4CKER shows current list of modules.

3.  User enters command to add a new module.

4.  TR4CKER adds the new module to the list of modules.

    Use case ends.

**Extentions**

* 1a. User entered the wrong command.

    * 1a1. An error message is shown.

    Use case ends.

**Use case: UC14 - Delete a module from Module tab**

**MSS**

1.  User requests to <u>switch to Module tab (UC12)</u> to view current list of modules.

2.  TR4CKER shows current list of modules.

3.  User enters command to delete a module.

4.  TR4CKER deletes module from the list of modules.

    Use case ends.

**Extensions**

* 3a. User enters an invalid module index or uses the wrong format.

    * 3a1. TR4CKER shows an error message.

    Use case ends.

* 3b. Module selected still has pending tasks

    * 3b1. TR4CKER shows an error message.

    Use case ends.

**Use case: UC15 - Assigning a task to a module**

1.  User requests to <u>list Pending tasks (UC08)</u>.

2.  TR4CKER shows current list of Pending tasks.

3.  User requests to <u>edit a Pending task (UC02)</u> with a module code.

4.  TR4CKER assigns task to the module.

**Extensions**

* 3a. User enters an invalid module code or uses the wrong format.

    * 3a1. TR4CKER shows an error message.

    Use case ends.

**Use case: UC16 - Un-assigning a task to a module**

1.  User requests to <u>list Pending tasks (UC08)</u>.

2.  TR4CKER shows current list of Pending tasks.

3.  User requests to <u>edit a Pending task (UC02)</u> to remove its module code.

4.  TR4CKER un-assigns task from the module.

**Extensions**

* 3a. User tries to remove code from task that already has no module code, without
changing other task fields.

    * 3a1. TR4CKER shows an error message.

    Use case ends.

**Use case: UC17 - Switching to Countdown tab**

**MSS**

1.  User requests to go to Countdown tab.

2.  TR4CKER switches tab to Countdown tab.

    Use case ends.

**Extensions**

* 1a. User entered the wrong command.

    * 1a1. An error message is shown.

    Use case ends.

**Use case: UC18 - Add an event to Countdown tab**

**MSS**

1.  User requests to <u>switch to Countdown tab (UC17)</u> to view current list of events.

2.  TR4CKER shows current list of events.

3.  User enters command to add a new event.

4. TR4CKER adds new event to the list of events.

    Use case ends.

**Extensions**

* 3a. User enters an invalid name or date or uses an invalid format.

    * 3a1. An error message is shown.

    Use case ends.

**Use case: UC19 - Delete an event from Countdown tab**

**MSS**

1.  User requests to <u>switch to Countdown tab (UC17)</u> to view current list of events.

2.  TR4CKER shows current list of events.

3.  User enters command to delete an event.

4.  TR4CKER deletes event from the list of events.

    Use case ends.

**Extensions**

* 3a. User enters an invalid event index or uses the wrong format.

    * 3a1. TR4CKER shows an error message.

    Use case ends.

**Use case: UC20 - Count the number of events in the next `x` days**

**MSS**

1.  User requests to <u>switch to Countdown tab (UC17)</u> to view current list of events.

2.  TR4CKER shows current list of events.

3.  User enters command to count the number of events in the next `x` days.

4.  TR4CKER counts and shows the events in the next `x` days.

    Use case ends.

**Extensions**

* 3a. User enters an invalid number of days or uses the wrong format.

    * 3a1. TR4CKER shows an error message.

    Use case ends.

**Use case: UC21 - Switching to Planner tab**

**MSS**

1.  User requests to go to Planner tab.
2.  TR4CKER switches tab to Planner tab.

    Use case ends.

**Extensions**

* 1a. User entered the wrong command.

    * 1a1. An error message is shown.

    Use case ends.

**Use case: UC22 - Goto specific date in Planner tab**

**MSS**

1.  User requests to view calendar view of a specific date and tasks list.
2.  TR4CKER shows calendar view of user's input and tasks list.

    Use case ends.

**Extensions**

* 1a. User entered invalid date or invalid format.

    * 1a1. An error message is shown.

    Use case ends.

**Use case: UC23 - Goto specific month in Planner tab**

**MSS**

1.  User requests to view calendar view of a specific month and tasks list.
2.  TR4CKER shows calendar view of user's input and tasks list.

    Use case ends.

**Extensions**

* 1a. User entered invalid month or invalid format.

    * 1a1. An error message is shown.

    Use case ends.

## A.4. Non-Functional Requirements

1.  TR4CKER should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2.  TR4CKER should be able to hold up to **1000 tasks** without any noticeable sluggishness in performance for typical usage.
3.  Users should be able to use TR4CKER without Internet connection, but accessing of User Guide or Developer Guide would require Internet.
4.  Users with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to
    accomplish most of the tasks faster using commands than using the mouse.
5.  Users should be able to retain their information when they switch laptops if they have the `data folder` (created on first load)
    in the same directory as the `JAR file` on the other laptop.
6.  The user interface should be intuitive enough for users who are not tech-savvy.
7.  The response to any inputted commands should become visible within 5 seconds.
8.  The source code should be open source.

## A.5. Glossary

* **Mainstream OS**: Windows, Linux, Unix, OS-X
* **Task**: An item to be completed by a certain deadline.
* **Todo**: An item planned to be completed in a day.
* **Event**: An item representing an event which occurs on a certain date.
* **CLI**: Command-Line Interface, a method of interacting with computer programs by typing lines of text.

--------------------------------------------------------------------------------------------------------------------

# Appendix B: Instructions for manual testing

Given below are instructions to test TR4CKER manually.

<div markdown="span" class="alert alert-info">:information_source: **Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

</div>

## B.1. Launch and shutdown

1. Initial launch

   1.1. Download the jar file and copy into an empty folder.

   1.2. Double-click the jar file.<br>
   Expected: Shows the GUI with a set of sample tasks. The window size may not be optimum.

2. Saving window preferences

   2.1. Resize the window to an optimum size. Move the window to a different location. Close the window.

   2.2. Re-launch TR4CKER by double-clicking the jar file.<br>
   Expected: The most recent window size and location is retained.

## B.2. Features in Home tab
1. Adding a task in Home tab.<br>

    1.1. Prerequisite: Must not have another task with the same name and same deadline. (Task names can be the same as long they have different deadline due to the recurring nature of some tasks, e.g CS2103T tutorial)

    1.2. Test case: `add n/CS2103T tutorial dl/12-12-2020 des/last tutorial m/CS2103T t/sad`<br>
    Expected: TR4CKER adds the event to Countdown tab, with a result message saying
    `New task added: CS2103T tutorial Deadline: 12-Dec-2020 2359 CompletionStatus: 0% Description: last tutorial ModuleCode: CS2103T Tags: [sad]`.

    1.3. Incorrect countdown commands to try:`add n/TR@CKER dl/12-12-2020 des/hahaha`<br>
    Expected: TR4CKER shows an `Names should only contain alphanumeric characters and spaces, and it should not be blank` error message since `NAME` is not alphanumeric.
        
2. Editing the deadline of a Pending task.<br>

    2.1. Prerequisite: Pending task exists in Pending Tasks list.

    2.2. Test case: `edit 1 dl/15-12-2020 2200`
    Expected: TR4CKER edits the pending task at index 1, with a result message saying `Edited task: ...`.

    2.3. Incorrect countdown commands to try:`edit 1`<br>
    Expected: TR4CKER shows an `At least one field to edit must be provided.` error message since no fields were specified in the command.


3. Deleting a Pending task.<br>

   3.1. Prerequisites: List all Pending tasks using the `list` command. Multiple tasks in the list.

   3.2. Test case: `delete 1`<br>
   Expected: First task deleted from the displayed Pending Tasks list, with a result message saying `Deleted task: ...`.

   3.3. Incorrect delete commands to try: `delete task 1`<br>
   Expected: TR4CKER shows an `Invalid command format! ...` error message since the syntax of the Delete commmand is wrong.


## B.3. Daily feature
1. Switching to Daily tab.<br>

    1.1 Prerequisite: Current tab must not be Daily tab.<br>
    
    1.2 Test case: `daily`<br>
    
    Expected: TR4CKER switches to Daily tab with a result message saying `Switched to Daily tab!`. Daily tab will be
                  empty if there are no todos.

2. Adding a todo to Daily tab.<br>

    1.1 Prerequisite: List all pending task using the `list` Command. Names of all pending tasks are shown.<br>
    
    1.2 Test case: `todo 1`<br>
    
    Expected: First pending task is added to Daily tab. TR4CKER shows details of the added task.
    
    1.3 Test case: `todo 0`<br>
    
    Expected: No todo is added. TR4CKER shows an error message as the index entered is invalid.
    
    1.4 Other incorrect todo commands to try: `todo`, `todo x`, `...` (where `x` is larger than the pending list size)<br>
    
    Expected: Similar to previous.

3. Deleting a todo in Daily tab.<br>
    
    1.1 Prerequisite: There is at least 1 todo in Daily tab to be deleted.<br>
    
    1.2 Test case: `daily del/1`<br>
    
    Expected: First todo in Daily tab is deleted. TR4CKER shows details of the deleted todo.
    
    1.3 Test case: `daily del/0`<br>
    
    Expected: No todo is deleted. TR4CKER shows an error message as the index entered is invalid.
    
    1.4 Other incorrect delete todo commands to try: `daily del/`, `daily del/x`, `...` (where `x` is larger than the daily todo list size)<br>
    
    Expected: Similar to previous.

## B.4. Module feature
1. Switching to Module tab.<br>

    1.1. Prerequisite: Current tab must not be Module tab.<br>

    1.2. Test case: `modules`<br>

    Expected: TR4CKER switches to Module tab with a result message saying `Switched to Module tab!`. Module tab will be
    empty if there are no modules.

2. Deleting a module while switched to Module tab.<br>

    2.1. Prerequisite: Module must not have any tasks listed under it.

    2.2. Test case: `modules del/INDEX` (where `INDEX` is the number of the module in the list)<br>
        Expected: Module is deleted from the list with result message saying `Deleted module: [ CODE | NAME ]`
        (where `CODE` and `NAME` are the module code and name of the module)

    2.3. Test case: `modules del/x` (where `x` is the number of a module that still has tasks)<br>
        Expected: Module is not deleted. Error details shown in status message.

    2.4. Test case: `modules del/0`<br>
        Expected: No module is deleted. Error details shown in status message.

    2.5. Other incorrect delete commands to try: `modules del/`, `delete del/x`, `...` (where `x` is larger than the list size)<br>
        Expected: Similar to previous.

3. Assigning a task to a module.<br>

    3.1. Prerequisites: Have at least one created module and task. List all tasks using the `list` command.<br>

    3.2. Test case: `edit 1 m/MODULECODE` (where `MODULECODE` is the code of an existing module)<br>
        Expected: First task is tagged with that module code. Task appears under in the Module tab under the respective module.

    3.3. Test case: `edit 1 m/invalid code`
        Expected: TR4CKER shows an error message. First task not assigned to any module. Error details shown in status message.
        
## B.5. Countdown feature
1. Switching to Countdown tab<br>

    1.1. Prerequisite: Current tab must not be Countdown tab.<br>

    1.2. Test case: `countdown`<br>

    Expected: TR4CKER switches to Countdown tab with a result message saying `Switched to Countdown tab!`. Upcoming
    events are sorted in chronologial order, with expired events listed at the bottom.

    1.3. Incorrect countdown commands to try:<br>
        * `countdowns`
        * `countdown tab`

2. Adding an event to Countdown tab<br>

    2.1. Prerequisite: Must not have another event with the same name. (Tasks in the Home tab with the same name are fine.)

    2.2. Test case: `countdown n/Internship Begins d/01-06-2021`<br>
    Expected: TR4CKER adds the event to Countdown tab, with a result message saying `New event added to Countdowns
    added: Internship Begins Date: 01-Jun-2021`.

    2.3. Incorrect countdown commands to try:`countdown n/TR@CKER d/13-11-2020`<br>
    Expected: TR4CKER shows an `Invalid command format!` error message since `NAME` is not alphanumeric.

3. Deleting an event from Countdown tab<br>

    3.1. Prerequisite: Event is present in events list.

    3.2. Test case: `countdown del/1`
    Expected: TR4CKER deletes the event at index 1, with a result message saying `Deleted event from Countdowns: ...`.

    3.3. Incorrect countdown commands to try:`countdown del/0`<br>
    Expected: TR4CKER shows an `Invalid command format!` error message since `INDEX` is not a valid index.

4. Counting the number of events in specified number of days

    4.1. Test case: `countdown days/10`
    Expected: TR4CKER counts the events in the next 10 days , with a result message saying `There are x event(s) in 10 days :...`.

    4.2. Incorrect countdown commands to try:`countdown days/-10`<br>
    Expected: TR4CKER shows an `Invalid command format!` error message since `DAYS` is not a valid number of days.

## B.6. Planner feature
1. Switching to Planner tab<br>

    1.1. Prerequisite: Current tab must not be Planner tab.<br>
    
    1.2. Test case: `planner`<br>
    
    Expected: TR4CKER switches to Planner tab with a result message saying `Switched to Planner tab!`. Current date is
    circled on calendar and tasks list shows tasks due today.
    
2. Navigating to today's calendar view and tasks list<br>

    2.1. Prerequisite: Current date is not selected.<br>
    
    2.2. Test case: `planner goto/today`<br>
    
    Expected: TR4CKER switches to Planner tab with a result message saying `Showed tasks on: TODAYS_DATE (TODAY)`. 
    TR4CKER switches to the monthly calendar view of today's date and tasks list shows tasks due today.
    
    2.3. Test case: `planner goto/tdy`<br>
    
    Expected: TR4CKER switches to Planner tab with a result message saying `Showed tasks on: TODAYS_DATE (TODAY)`. 
    TR4CKER switches to the monthly calendar view of today's date and tasks list shows tasks due today.
    
    2.4. Incorrect planner goto commands to try: `planner goto/todayy`, `planner goto/tdyy`<br>
    
    Expected: TR4CKER shows an error message. No switching to planner tab, calendar view and tasks list.
    
3. Navigating to tomorrow's calendar view and tasks list<br>

    3.1. Prerequisite: Tomorrow's date is not selected.<br>
    
    3.2. Test case: `planner goto/tomorrow`<br>
    
    Expected: TR4CKER switches to Planner tab with a result message saying `Showed tasks on: TOMORROWS_DATE (TOMORROW)`. 
    TR4CKER switches to the monthly calendar view of tomorrow's date and tasks list shows tasks due tomorrow.
    
    3.3. Test case: `planner goto/tmr`<br>
    
    Expected: TR4CKER switches to Planner tab with a result message saying `Showed tasks on: TOMORROWS_DATE (TOMORROW)`. 
    TR4CKER switches to the monthly calendar view of tomorrow's date and tasks list shows tasks due tomorrow.
    
    3.4. Incorrect planner goto commands to try: `planner goto/tomorr`, `planner goto/tmrr`<br>
    
    Expected: TR4CKER shows an error message. No switching to planner tab, calendar view and tasks list.
    
4. Navigating to a specific date's calendar view and tasks list<br>

    4.1. Prerequisite: The date to input is not selected.<br>
    
    4.2. Test case: `planner goto/19-10-2020`<br>
    
    Expected: TR4CKER switches to Planner tab with a result message saying `Showed tasks on: 19-Oct-2020`. 
    TR4CKER switches to the monthly calendar view of the date and tasks list shows tasks due on that day.
    
    4.3. Test case: `planner goto/19-Oct-2020`<br>
    
    Expected: TR4CKER switches to Planner tab with a result message saying `Showed tasks on: 19-Oct-2020`. 
    TR4CKER switches to the monthly calendar view of the date and tasks list shows tasks due on that day.
    
    4.4. Incorrect planner goto commands to try: `planner goto/29-02-2021`<br>
    
    Expected: TR4CKER shows an error message. No switching to planner tab, calendar view and tasks list.
    
5. Navigating to a specific month's calendar view and tasks list<br>

    5.1. Prerequisite: The month to input is not selected.<br>
    
    5.2. Test case: `planner goto/10-2020`<br>
    
    Expected: TR4CKER switches to Planner tab with a result message saying `Showed tasks on: 01-Oct-2020`. 
    TR4CKER switches to the monthly calendar view of the month and tasks list shows tasks due on first day of the month.
    
    5.3. Test case: `planner goto/Oct-2020`<br>
    
    Expected: TR4CKER switches to Planner tab with a result message saying `Showed tasks on: 01-Oct-2020`.
    TR4CKER switches to the monthly calendar view of the month and tasks list shows tasks due on first day of the month.
    
    5.4. Incorrect planner goto commands to try: `planner goto/dec-2020`<br>
    
    Expected: TR4CKER shows an error message. No switching to planner tab, calendar view and tasks list.

## B.7. Saving data

1. Dealing with missing/corrupted data files

   1.1. Open the `/data/` folder and delete all `.json` files in that folder.
   
   1.2. Launch TR4CKER by double-clicking the `tr4cker.jar` file.<br>
   Expected Outcome: TR4CKER starts up with sample data in the GUI. Sample tasks should show up.
