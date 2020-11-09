---
layout: page
title: Developer Guide
---

* Table of Contents
{:toc}

---

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

---

## **Design**

### Architecture

<img src="images/ArchitectureDiagram.png" width="450" />

The **_Architecture Diagram_** given above explains the high-level design of the App. Given below is a quick overview of each component.

<div markdown="span" class="alert alert-primary">

:bulb: **Tip:** The `.puml` files used to create diagrams in this document can be found in the [diagrams](https://github.com/AY2021S1-CS2103-F10-3/tp/tree/master/docs/diagrams/) folder. Refer to the [_PlantUML Tutorial_ at se-edu/guides](https://se-education.org/guides/tutorials/plantUml.html) to learn how to create and edit diagrams.

</div>

**`Main`** has two classes called [`Main`](https://github.com/AY2021S1-CS2103-F10-3/tp/tree/master/src/main/java/seedu/fma/Main.java) and [`MainApp`](https://github.com/AY2021S1-CS2103-F10-3/tp/tree/master/src/main/java/seedu/fma/MainApp.java). It is responsible for,

- At app launch: Initializes the components in the correct sequence, and connects them up with each other.
- At shut down: Shuts down the components and invokes cleanup methods where necessary.

[**`Commons`**](#common-classes) represents a collection of classes used by multiple other components.

The rest of the App consists of four components.

- [**`UI`**](#ui-component): The UI of the App.
- [**`Logic`**](#logic-component): The command executor.
- [**`Model`**](#model-component): Holds the data of the App in memory.
- [**`Storage`**](#storage-component): Reads data from, and writes data to, the hard disk.

Each of the four components,

- defines its _API_ in an `interface` with the same name as the Component.
- exposes its functionality using a concrete `{Component Name}Manager` class (which implements the corresponding API `interface` mentioned in the previous point.

For example, the `Logic` component (see the class diagram given below) defines its API in the `Logic.java` interface and exposes its functionality using the `LogicManager.java` class which implements the `Logic` interface.

![Class Diagram of the Logic Component](images/LogicClassDiagram.png)

**How the architecture components interact with each other**

The _Sequence Diagram_ below shows how the components interact with each other for the scenario where the user issues the command `delete 1`.

<img src="images/ArchitectureSequenceDiagram.png" width="574" />

The sections below give more details of each component.

### UI component

![Structure of the UI Component](images/UiClassDiagram.png)

**API** :
[`Ui.java`](https://github.com/AY2021S1-CS2103-F10-3/tp/tree/master/src/main/java/seedu/fma/ui/Ui.java)

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `LogListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class.

The `UI` component uses JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/se-edu/AY2021S1-CS2103-F10-3/tp/tree/master/src/main/java/seedu/fma/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/AY2021S1-CS2103-F10-3/tp/tree/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

- Executes user commands using the `Logic` component.
- Listens for changes to `Model` data so that the UI can be updated with the modified data.

### Logic component

![Structure of the Logic Component](images/LogicClassDiagram.png)

**API** :
[`Logic.java`](https://github.com/AY2021S1-CS2103-F10-3/tp/tree/master/src/main/java/seedu/fma/logic/Logic.java)

1. `Logic` uses the `FixMyAbsParser` class to parse the user command.
1. This results in a `Command` object which is executed by the `LogicManager`.
1. The command execution can affect the `Model` (e.g. adding a log).
1. The result of the command execution is encapsulated as a `CommandResult` object which is passed back to the `Ui`.
1. In addition, the `CommandResult` object can also instruct the `Ui` to perform certain actions, such as displaying help to the user.

Given below is the Sequence Diagram for interactions within the `Logic` component for the `execute("delete 1")` API call.

![Interactions Inside the Logic Component for the `delete 1` Command](images/DeleteSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `DeleteCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>

### Model component

![Structure of the Model Component](images/ModelClassDiagram.png)

**API** : [`Model.java`](https://github.com/AY2021S1-CS2103-F10-3/tp/tree/master/src/main/java/seedu/fma/model/Model.java)

The `Model` component,

- stores a `UserPref` object that represents the user’s preferences.
- stores the logbook data.
- exposes unmodifiable lists `ObservableList<Log>` and `ObservableList<Exercise>` that can be 'observed' e.g. the UI can be bound to these lists so that the UI automatically updates when the data in these lists change.
- does not depend on any of the other three components.

### Storage component

![Structure of the Storage Component](images/StorageClassDiagram.png)

**API** : [`Storage.java`](https://github.com/AY2021S1-CS2103-F10-3/tp/tree/master/src/main/java/seedu/fma/storage/Storage.java)

The `Storage` component,

- can save `UserPref` objects in json format and read it back.
- can save the logbook data in json format and read it back.

### Common classes

Classes used by multiple components are in the `seedu.fma.commons` package.

---

## **Implementation**

This section describes some noteworthy details on how certain features were implemented.

### Add Exercise feature
The adding of Exercises is one of the core functionalities of FixMyAbs. Apart from the generic exercises provided when users first start the app, users are also able to add their own exercises, which makes FixMyAbs customizable and more suited to each user's specific needs.  

The mechanism is facilitated by an `Exercise` class. An `Exercise` class has `Name` and `Calories`. Calories represent the number of calories burnt per rep of the exercise.

![AddExClassDiagram](images/AddExerciseClassDiagram.png)

A user can add an `Exercise`to the `LogBook` by executing the `addex` command.

#### Example usage scenario
Given below is an example usage scenario and how the `add exercise` mechanism behaves at each step after launching the application.

Step 1. The user executes the command `addex e/Jumping kicks c/2`. `FixMyAbsParser` creates a new `AddExCommandParser` and calls the `AddExCommandParser#parse()` method.

Step 2. The user input is passed into the `AddExCommandParser#parse()` method and instances of `Name` and `Calories` are created using the `ParserUtil` class, from user input. These new instances are passed as parameters to the `Exercise` constructor, and a new `Exercise` object is created as a result.

![AddExClassDiagram](images/AddExerciseStep2.png)

Step 3. A new `AddExCommand` is returned with the created `Exercise` object as an attribute. The `Exercise` object is then added to the `Model`.

![AddExClassDiagram](images/AddExerciseStep3.png)

The following sequence diagram shows how the `Add Exercise` feature works:

![AddExClassDiagram](images/AddExerciseSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `AddExCommandParser` 
should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>

### Add Log feature
The adding of Logs are the core to the functionality of FixMyAbs. Users are able to add information related to that 
exercise log, which will then allow them to track their fitness progress and details regarding their workout.  

The mechanism is facilitated by a `Log` class. A `Log` class has `Exercise`, `Rep`, `Comment` and `LocalDateTime`.

![AddLogClassDiagram](images/AddLogClassDiagram.png)

A user can add a `Log`to the `LogBook` by executing the `add` command.

#### Example usage scenario
Given below is an example usage scenario and how the `add log` mechanism behaves at each step after launching the application.

Step 1. The user executes the command `add e/Pushups r/50 c/Managed to increase to 50 reps today!`. `FixMyAbsParser` creates a new `AddCommandParser` and calls the `AddCommandParser#parse()` method.

Step 2. The user input is passed into the `AddCommandParser#parse()` method and instances of `Exercise`, `Rep` and `Comment` are created using the `ParserUtil` class, from user input. These new instances are passed as parameters to the `Log` constructor, and a new `Log` object is created as a result. Each `Log` instantiates with a new `dateTime` field, which calls the `LocalDateTime#now()` method alongside the current system clock of the user's computer.`

Step 3. A new `AddCommand` is returned with the created `Log` object as an attribute. The `Log` object is then added to the `Model`.

The following sequence diagram shows how the `Add Log` feature works:

![AddLogClassDiagram](images/AddLogSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `AddCommandParser` 
should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.

</div>

![](images/DeleteExDiagram.png)


### Find Log feature
To find specific logs, users are able to search for logs based on certain keywords.
This will allow them to easily find logs with a certain exercise, as well as any of its details.

![FindLogClassDiagram](images/NameContainsKeywordsPredicate.png) 

`NameContainsKeywordsPredicate` supports this command.
`NameContainsKeywordsPredicate#test()` checks if a Log contains all the keywords in the user input.

A user can thus find a `Log` by executing the `find` command.

#### Example usage scenario
Given below is an example usage scenario and how the `find` command behaves at each step after launching the application.

Step 1. The user executes the command `find Push ups`. `FixMyAbsParser` creates a new `FindCommandParser` and calls the `FindCommandParser#parse()` method.

Step 2. The user input is passed into the `FindCommandParser#parse()` method, which then creates a new `NameContainsKeywordsPredicate` object, with the user input as the parameter. This object checks if there is a match between the keyword and the tested `Log`. 

Step 3. As a result of the `FindCommandParser#parse()` method, a new `FindCommand` is returned with the created `NameContainsKeywordsPredicate` object as an attribute. Upon calling `FindCommand#execute()`, the model is updated through `Model#updateFilteredLogList()`, and the new filtered log list is displayed to the user.

The following sequence diagram shows how the `find` feature works:

![FindLogClassDiagram](images/FindLogSequenceDiagram.png) 

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `FindCommandParser` 
should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>



### Autocomplete  feature

Autocomplete feature uses following classes:

- `ResultDisplay` - Displays user input.
- `CommandBox` - CommandBox which consists of `commandTextField`
- `LogicManager` - Implements logic between models and user interface

Below is how it works:

Step 1. User enters command `add`.

Step 2. `commandTextField` in `CommandBox` detects changes in the user input and parse the input into
 `showAutoCompleteResult()` method in `ResultDisplay` 

Step 3: `ResultDisplay` executes method `setText` which executes `getAutoCompleteResult` method.

Step 4: `getAutoCompleteResult` computes the result to display to the user and returns the String `result`.

Step 5: `setText` method in `ResultDisplay` receives the `result` as a parameter and display the `result` for the user.

![AutocompleteSequenceDiagram](images/AutocompleteSequenceDiagram.png) 

### Better user interface

We changed and improve the user interface to support exercise by adding `ExerciseListCard` and `ExerciseListPanel
` which is similar to `LogListCard` and `LogListPanel` to the
`MainWindow`.

The `UI` component works the same

- Executes user commands using the `Logic` component.
- Listens for changes to `Model` data so that the UI can be updated with the modified data.

The design looks like:

<img src="images/Ui.png" width="500px">

---

## **Documentation, logging, testing, configuration, dev-ops**

- [Documentation guide](Documentation.md)
- [Testing guide](Testing.md)
- [Logging guide](Logging.md)
- [Configuration guide](Configuration.md)
- [DevOps guide](DevOps.md)

---

## **Appendix: Requirements**

### Product scope

**Target user profile**:

- has a need to keep track of exercises done
- has a need to keep track of calories burnt over time
- prefer desktop apps over other types
- can type fast
- prefers typing to mouse interactions
- is reasonably comfortable using CLI apps

**Value proposition**: simple and quick way to monitor exercises and quantify fitness progress

### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​                   | I want to …​                                     | So that I can…​                                                |
| -------- | ------------------------- | ------------------------------------------------ | -------------------------------------------------------------- |
| `* * *`  | user                      | see usage instructions                           | refer to instructions when I forget how to use the App         |
| `* * *`  | user                      | add an exercise                                  | track what I have done in the app                              |
| `* * *`  | user                      | delete a log                                     | remove exercises that I no longer want to track                |
| `* * *`  | user                      | update an exercise                               | edit what the app is tracking                                  |
| `* * *`  | user                      | list exercises                                   | view what the app is tracking                                  |
| `* *`    | impatient user            | have quick app response times                    | not waste time waiting for it to load                          |
| `* *`    | new user                  | see the app populated with sample data           | view what the app will looks like when there is input          |
| `* *`    | user ready to use the app | reset all current data                           | begin using the app for real without any sample data           |
| `* *`    | busy user                 | use my keyboard to log exercises                 | log exercises as quickly as possible                           |
| `* *`    | forgetful user            | have easy to remember input commands             | use the app quickly without constantly referring to the manual |
| `* *`    | indecisive user           | view a list of exercises I can do                | start exercising without having to think of what to do         |
| `*`      | user                      | have a graph showing my calories burnt over time | see if I am making progress                                    |
| `*`      | user                      | view the calories I have burnt over time         | feel a sense of accomplishment                                 |
| `* `     | tech savvy user           | import my own list of exercises                  | view and track it in the application                           |
| `* `     | unmotivated user          | have reminders of the last time I exercised      | feel more motivated to start exercising again                  |
| `* `     | careless user             | be able to undo actions                          | fix any mistakes I may have done regarding input               |

### Use cases

(For all use cases below, the **System** is `FixMyAbs` and the **Actor** is the `user`, unless specified otherwise)

**Use case: UC01 - Create a log**

**MSS**

1. User requests to create a specific log
2. FixMyAbs creates the log
3. FixMyAbs shows a success message

   Use case ends.

**Extensions**

- 1a. The given exercise does not exist.

  - 1a1. FixMyAbs shows an error message.

    Use case resumes at step 1.
    
- 1b. The given reps is invalid.

  - 1b1. FixMyAbs shows an error message.

    Use case resumes at step 1.

**Use case: UC-02 - Edit a log**

**MSS**

1. User requests to list all logs
2. FixMyAbs shows a list of all recorded logs
3. User requests to edit a specific log in the list
4. FixMyAbs edits the log according to user specifications
5. FixMyAbs shows a success message

   Use case ends.

**Extensions**

- 2a. The list of logs is empty.

  - 2a1. FixMyAbs shows an error message.

    Use case ends.

- 3a. The given index is invalid.

  - 3a1. FixMyAbs shows an error message.

    Use case resumes at step 2.

**Use case: UC03 - Delete a log**

**MSS**

1. User requests to list all logs
2. FixMyAbs shows a list of all recorded logs
3. User requests to delete a specific log in the list
4. FixMyAbs deletes the log
5. FixMyAbs shows a success message

   Use case ends.

**Extensions**

- 2a. The list of logs is empty.

  - 2a1. FixMyAbs shows an error message.
  
    Use case ends.

- 3a. The given index is invalid.

  - 3a1. FixMyAbs shows an error message.

    Use case resumes at step 2.
    
**Use case: UC04 - Find logs**

**MSS**

1. User requests to find all logs that contain specific keywords
2. FixMyAbs shows a list of all recorded logs that contain all the specified keywords

   Use case ends.

**Extensions**

- 1a. No keywords are given.

  - 1a1. FixMyAbs shows an error message.

    Use case resumes at step 1.
    
**Use case: UC05 - Create an exercise**

**MSS**

1. User requests to create a specific exercise
2. FixMyAbs creates the exercise
3. FixMyAbs shows a success message

   Use case ends.

**Extensions**

- 1a. The given exercise already exists.

  - 1a1. FixMyAbs shows an error message.

    Use case resumes at step 1.
    
- 1b. The given calories per rep is invalid.

  - 1b1. FixMyAbs shows an error message.

    Use case resumes at step 1.
   
**Use case: UC06 - Edit an exercise**

**MSS** 

1. User requests to edit the calories per rep of an existing exercise.
2. FixMyAbs edits the indicated exercise
3. FixMyAbs shows a success message
    
   Use case ends.

**Extensions**

- 1a. The given new exercise name exists.

    - 1a1. FixMyAbs shows an error message.
        
        Use case resumes at step 1.
        
- 1b. The given calories per rep is invalid.

    - 1b1. FixMyAbs shows an error message.
    
        Use case resumes at step 1.
        
    
### Non-Functional Requirements

1.  Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2.  Should be able to hold up to 1000 exercises without a noticeable sluggishness in performance for typical usage.
3.  A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
4.  Response time and processing time for user input should be under 0.1s.
5.  Startup and showdown times should be under 1.0s.

### Glossary

- **Mainstream OS**: Windows, Linux, Unix, OS-X

---

## **Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.

<div markdown="span" class="alert alert-info">:information_source: **Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

</div>

### Launch and shutdown

1. Initial launch

   1. Download the jar file and copy into an empty folder.

   1. Double-click the jar file.<br>
      Expected: Shows the GUI with a set of sample logs and exercises. The window size may not be optimum.

1. Saving window preferences

   1. Resize the window to an optimum size. Move the window to a different location. Close the window.

   1. Re-launch the app by double-clicking the jar file.<br>
      Expected: The most recent window size and location is retained.

### Creating a log

1. Creating a log

   1. Prerequisites: At least one exercise in the exercise list.

   1. Test case: `add e/Sit ups r/1 c/my abs hurt :(`<br>
      Expected: A log with the specified details is created and displayed in the log list. Details of the created log shown in the status message.

   1. Test case: `add e/Sit ups`<br>
      Expected: No log is created. Error details shown in the status message.

   1. Other incorrect add commands to try: `add`, `add e/x r/1 c/my abs hurt :(` (where x is an exercise that does not exist)<br>
      Expected: Similar to previous.

### Editing a log

1. Editing a log while all logs are being shown

   1. Prerequisites: List all logs using the `list` command. Multiple logs in the list.

   1. Test case: `edit 1 c/no abs were hurt`<br>
      Expected: Edits the log at index 1, with a comment of `no abs were hurt`. Details of the edited log shown in the status message.

   1. Test case: `edit 1 r/20 c/no abs were hurt`<br>
      Expected: Edits the log at index 1, with reps of `20` and a comment of `no abs were hurt`. Details of the edited log shown in the status message.

   1. Test case: `edit 1`<br>
      Expected: No log is edited. Error details shown in the status message.

   1. Other incorrect edit commands to try: `edit`, `edit x ...` (where x is an integer larger than the list size or less than 1)<br>
      Expected: Similar to previous.
      
### Deleting a log

1. Deleting a log while all logs are being shown

   1. Prerequisites: List all logs using the `list` command. Multiple logs in the list.

   1. Test case: `delete 1`<br>
      Expected: First log is deleted from the list. Details of the deleted log shown in the status message.

   1. Test case: `delete 0`<br>
      Expected: No log is deleted. Error details shown in the status message.

   1. Other incorrect delete commands to try: `delete`, `delete x` (where x is an integer larger than the list size or less than 1)<br>
      Expected: Similar to previous.
      
### Adding an exercise

1. Adding an exercise in the shown exercises list.

    1. Prerequisites: No prerequisites, you can add an exercise in any state of FixMyAbs.
    
    2. Test cases:  `addex e/High jump c/100`<br>.
       Expected: The exercise with name as `High jump` and calories per rep as 100 is added to the end of the exercise list.
       
    3. Test case:  `addex e/  c/ `<br>
        Expected: No exercise is added. Error details shown in the status message.
    
    4. Other incorrect addex commands to try: `addex e/High jump c/0`, `addex c/123`. 
    
### Deleting an exercise

1. Deleting an exercise in the shown exercises list.

   1. Prerequisites: No prerequisites, you can delete an exercise in any state of FixMyAbs.

   1. Test case: `delete 1`<br>
      Expected: First exercise is deleted from the list. Details of the deleted exercise shown in the status message.

   1. Test case: `delete 0`<br>
      Expected: No exercise is deleted. Error details shown in the status message.

   1. Other incorrect delete commands to try: `delete`, `delete x` (where x is larger than the list size or less than 1)<br>
      Expected: Similar to previous.
      
### Editing an exercise

1. Editing an exercise

   1. Prerequisites: No prerequisites, you can delete an exercise in any state of FixMyAbs.

   1. Test case: `editex 1 e/High jump`<br>
      Expected: Edits the exercise at index 1, with a comment of `High jump`. Details of the edited exercise shown in the status message.

   1. Test case: `editex 1 c/123 e/High jump`<br>
      Expected: Edits the exercise at index 1, with name of `High jump` and calories per rep of `123`. Details of the edited exercise shown in the status message.

   1. Test case: `edit 1`<br>
      Expected: No exercise is edited. Error details shown in the status message.

   1. Other incorrect edit commands to try: `edit`, `edit x ...` (where x is larger than the list size or less than 1)<br>
      Expected: Similar to previous.

### Saving data

1. Saving data upon shutdown

   1. Make some changes in the app.

   1. Close the window or use the `exit` command.
    
   1. Re-launch the app by double-clicking the jar file.<br>
      Expected: All changes should be saved.
    
1. Dealing with missing/corrupted data files

   1. Locate the local data file `.\data\logbook.json`.
   
   1. Test case: Delete the file. Launch the app by double-clicking the jar file.<br>
      Expected: A new data file will be created with a set of sample logs and exercises.
   
   1. Test case: Corrupt the file (e.g. delete an attribute of a log). Launch the app by double-clicking the jar file.<br>
      Expected: The app will startup without any data.
