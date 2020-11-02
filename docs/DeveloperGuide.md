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

:bulb: **Tip:** The `.puml` files used to create diagrams in this document can be found in the [diagrams](https://github.com/AY2021S1-CS2103T-T13-4/tp/tree/master/docs/diagrams/) folder. Refer to the [_PlantUML Tutorial_ at se-edu/guides](https://se-education.org/guides/tutorials/plantUml.html) to learn how to create and edit diagrams.

</div>

**`Main`** has two classes called [`Main`](https://github.com/AY2021S1-CS2103T-T13-4/tp/tree/master/src/main/java/seedu/cc/Main.java) and [`MainApp`](https://github.com/AY2021S1-CS2103T-T13-4/tp/tree/master/src/main/java/seedu/cc/MainApp.java). It is responsible for,
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
[`Ui.java`](https://github.com/AY2021S1-CS2103T-T13-4/tp/tree/master/src/main/java/seedu/cc/ui/Ui.java)

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `AccountListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class.

The `UI` component uses JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/AY2021S1-CS2103T-T13-4/tp/tree/master/src/main/java/seedu/address/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/AY2021S1-CS2103T-T13-4/tp/tree/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* Executes user commands using the `Logic` component.
* Listens for changes to `Model` data so that the UI can be updated with the modified data.

### Logic component

![Structure of the Logic Component](images/LogicClassDiagram.png)

**API** :
[`Logic.java`](https://github.com/AY2021S1-CS2103T-T13-4/tp/tree/master/src/main/java/seedu/cc/logic/Logic.java)

1. `Logic` uses the `CommonCentsParser` class to parse the user command.
1. This results in a `Command` object which is executed by the `LogicManager`.
1. The command execution can either affect the `ActiveAccount` which in turn affects the `Model` (e.g. adding an expense), 
or affect the `Model` directly (e.g. adding an account).
1. Based on the changes the command execution made, the `CommandResultFactory` generates a `CommandResult` object which encapsulates
the result of the command execution and is passed back to the `Ui`,
1. In addition, the `CommandResult` object can also instruct the `Ui` to perform certain actions, such as displaying help to the user.

Given below is the Sequence Diagram for interactions within the `Logic` component for the `execute("delete 1")` API call.

![Interactions Inside the Logic Component for the `deleteacc 1` Command](images/DeleteSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `DeleteAccountCommandParser`, `DeleteAccountCommand` and `CommandResultFactory` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>

### Model component

![Structure of the Model Component](images/ModelClassDiagram.png)

**API** : [`Model.java`](https://github.com/AY2021S1-CS2103T-T13-4/tp/tree/master/src/main/java/seedu/cc/model/Model.java)

The `Model`,

* stores a `UserPref` object that represents the user’s preferences.
* stores the CommonCents data.
* stores an unmodifiable list of Accounts.
* does not depend on any of the other three components.


<div markdown="span" class="alert alert-info">:information_source: **Note:** An alternative (arguably, a more OOP) model is given below. It has a `Tag` list in the `CommonCents`, which `Entry` references. This allows `CommonCents` to only require one `Tag` object per unique `Tag`, instead of each `Entry` needing their own `Tag` object.<br>
![BetterModelClassDiagram](images/BetterModelClassDiagram.png)

</div>


### Storage component

![Structure of the Storage Component](images/StorageClassDiagram.png)

**API** : [`Storage.java`](https://github.com/AY2021S1-CS2103T-T13-4/tp/tree/master/src/main/java/seedu/cc/storage/Storage.java)

The `Storage` component,
* can save `UserPref` objects in json format and read it back.
* can save the CommonCents data in json format and read it back.

### Common classes

Classes used by multiple components are in the `seedu.cc.commons` package.

--------------------------------------------------------------------------------------------------------------------

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

### Edit account feature

#### Implementation
The proposed edit account mechanism is facilitated by `EditAccountCommand`. It extends `Command` and is identified by
`CommonCentsParser` and `EditAccountCommandParser`. The `EditAccountCommand` interacts with `Account` and the interaction
is managed by `ActiveAccount` as well as the `Model`. As such, it implements the following operation:

* `Account#setName(Name editedName)` — Sets the name of the account

This operation is exposed in the `ActiveAccount` interface as `ActiveAccount#setName()`.

Given below is an example usage scenario and how the edit account mechanism behaves at each step.

* Step 1: The user inputs the edit command to edit the current account in `ActiveAccount`. `CommonCentsParser` identifies
the command word and calls `EditCommandParser#parse(String args)` to parse the input into a valid `EditAccountCommand`

* Step 2: `EditAccountCommand` starts to be executed. In the execution, the current account, namely `previousAccount`
in `ActiveAccount` is retrieved.

* Step 3: `ActiveAccount#setName(Name editedName)` is called with the edited name to replace the name of the current
account in `ActiveAccount`.

* Step 4: The updated account, namely `newAccount`, in `ActiveAccount` is retrieved.

* Step 5: `Model#setAccount(Account target, Account editedAccount)` is called with `previousAccount` as `target`, and
`newAccount` as `editedAccount`. This is to replace the to-be-edited account in the Model with the edited account.

The following sequence diagram shows how an edit account operation works:

![EditAccountSequenceDiagram](images/EditAccountSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:**<br>
Some of the interactions with the utility classes, such as `CommandResult` and `Storage` are left out of the sequence diagram as their roles are not significant in the execution
of the edit account command. 
</div>

The following activity diagram summarizes what happens when a user executes a new command:

![EditAccountActivityDiagram](images/EditAccountActivityDiagram.png)

#### Design consideration:

##### Aspect: How edit account executes:

* **Alternative 1 (current choice):** Accounts can only be edited if they are active.
  * Pros: Easy to implement.
  * Cons: Less flexibility for user.

* **Alternative 2:** Accounts can be edited by retrieving them from the account list with an index input.
  * Pros: More flexibility for user.
  * Cons: It is difficult to implement and manage because we need to consider whether the account to be edited
  is active and add extra measures for that case. As such, we chose alternative one since it is a more elegant
  solution.
  
##### Aspect: Mutability of account
* **Choice:** Allowing name attribute of Account to be mutated by EditCommand.
  * Rationale: Initially, we implemented the name attribute of Account to be immutable. However, we realize that it
  is difficult to edit the name of the account if it is immutable. Hence, to overcome this obstacle, we decided
  to make the name attribute mutable.
  * Implications: Extra precaution needs to be implemented, for instance creating copies of account in methods that interacts
  with the accounts to prevent unnecessary changes to accounts in account list. Hence, it resulted in more defensive coding 
  which resulted in more lines of code.

### Find entries feature 

#### Implementation

The proposed Find entries feature is facilitated by `FindCommand`. It extends `Command` and 
is identified by `CommonCentsParser` and `FindCommandParser`. The FindCommand interacts 
with `Account` and the interactions are managed by `ActiveAccount`. As such, it implements the following
operations: 
* `Account#updateFilteredExpenseList(Predicate<Expense> predicate)` — Updates the expense 
list that has the given keywords as predicate.
* `Account#updateFilteredRevenueList(Predicate<Revenue> predicate)` — Updates the revenue 
list that has the given keywords as predicate. 

The operations are exposed in the `ActiveAccount` interface as `ActiveAccount` interface as 
`ActiveAccount#setName()`.

Given below is an example usage scenario and how the find entries mechanism behaves 
at each step.

* Step 1. The user inputs the find command to find the entries that have the occurrences of 
a list of specified keywords in the entries of `ActiveAccount`. `CommandParser` identifies the command word `find`
and calls `FindCommandParser#parse(String args)` to parse the input into a valid `FindCommand`.

* Step 2. `FindCommand` starts to be executed. In the execution, 
    * If there is a predicate for keywords in the expense list of `ActiveAccount`, the expense list is updated. 
    `ActiveAccount#updateFilteredExpenseList()` is called to update the current expense list with expenses 
    with descriptions matching the keywords. 
    * If there is a predicate for keywords in the revenue list of `ActiveAccount`, the revenue list is updated. 
    `ActiveAccount#updateFilteredExpenseList()` is called to update the current revenue list with revenues
    with descriptions matching the keywords.

The following sequence diagram shows how a find entry operation works:

![FindSequenceDiagram](images/FindSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** Some of the interactions with the utility classes,
such as `CommandResult` and `Storage` are left out of the sequence diagram as their roles are not significant in the execution
of the find entries command.
</div>

The following activity diagram summarizes what happens when a user executes a new command:

![FindActivityDiagram](images/FindActivityDiagram.png)

#### Design consideration:

##### Aspect: How find entries command is parsed
* **Choice:** User needs to use prefix "k/" to before the keywords.
    * Pros: 
        * Easy to implement as the arguments can be tokenized in the event of inputs with multiple arguments.
        * Able to handle multiple arguments as input for Category (with prefix "c/") is optional.
    * Cons: Less convenience for the user. 
     
### Get total revenue/expenses feature

#### Implementation
The get total expenses/revenues mechanism is facilitated by `GetTotalCommand`. It extends `Command` and is identified by `CommonCentsParser` and `GetTotalCommandParser`. The `GetTotalCommand` interacts with `Account` and the interaction is managed by `ActiveAccount`. As such, it implements the following operations:   

* `Account#getTotalExpenses()` — gets the total sum of all the expenses in the account
* `Account#getTotalRevenue()` — gets the total sum of all the revenues in the account

The operation is exposed in the `ActiveAccount` interface as `ActiveAccount#getTotalExpenses()`.

Given below is an example usage scenario and how the get total expenses/revenues mechanism behaves at each step.

* Step 1. The user inputs the total command to get the total expenses/revenues in the current account in ActiveAccount. `CommonCentsParser` identifies the command word and calls `GetTotalCommandParser#parse(String args)` to parse the input into a valid `GetTotalCommand`.

* Step 2. `GetTotalCommand` starts to be executed. In the execution, the total sum of the expenses/revenues is first initialised to 0.00.

* Step 3. If the input category is an `Expense`, `ActiveAccount#getTotalExpenses()` is called to get the total sum of all the expenses in the account. Otherwise, if the input category is a `Revenue`, `ActiveAccount#getTotalRevenue()` is called to get the total sum of all the revenues in the account.

* Step 4. The total sum is updated. 

The following sequence diagram shows how a get total expenses/revenues operation works: 

![GetTotalSequenceDiagram](images/GetTotalSequenceDiagram.png)

The following activity diagram summarizes what happens when a user executes a new command:

![GetTotalActivityDiagram](images/GetTotalActivityDiagram.png)

#### Design consideration

##### Aspect: How get total expenses/revenues executes:
* Alternative 1 (current choice): Calculates the total expenses/revenues in the by retrieving the expense/revenue list. 
    * Pros: Easy to implement.
 
### \[Proposed\] Undo/redo feature

#### Proposed Implementation

The proposed undo/redo mechanism is facilitated by `VersionedCommonCents`. It extends `CommonCents` with an undo/redo history, stored internally as an `commonCentsStateList` and `currentStatePointer`. Additionally, it implements the following operations:

* `VersionedCommonCents#commit()` — Saves the current CommonCents state in its history.
* `VersionedCommonCents#undo()` — Restores the previous CommonCents state from its history.
* `VersionedCommonCents#redo()` — Restores a previously undone CommonCents state from its history.

These operations are exposed in the `Model` interface as `Model#commitCommonCents()`, `Model#undoCommonCents()` and `Model#redoCommonCents()` respectively.

Given below is an example usage scenario and how the undo/redo mechanism behaves at each step.

Step 1. The user launches the application for the first time. The `VersionedCommonCents` will be initialized with the initial Common Cents state, and the `currentStatePointer` pointing to that single Common Cents state.

![UndoRedoState0](images/UndoRedoState0.png)

Step 2. The user executes `delete 5` command to delete the 5th person in the Common Cents. The `delete` command calls `Model#commitCommonCents()`, causing the modified state of the Common Cents after the `delete 5` command executes to be saved in the `commonCentsStateList`, and the `currentStatePointer` is shifted to the newly inserted Common Cents state.

![UndoRedoState1](images/UndoRedoState1.png)

Step 3. The user executes `add n/David …​` to add a new person. The `add` command also calls `Model#commitCommonCents()`, causing another modified Common Cents state to be saved into the `commonCentsStateList`.

![UndoRedoState2](images/UndoRedoState2.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** If a command fails its execution, it will not call `Model#commitCommonCents()`, so the Common Cents state will not be saved into the `commonCentsStateList`.

</div>

Step 4. The user now decides that adding the person was a mistake, and decides to undo that action by executing the `undo` command. The `undo` command will call `Model#undoCommonCents()`, which will shift the `currentStatePointer` once to the left, pointing it to the previous Common Cents state, and restores the Common Cents to that state.

![UndoRedoState3](images/UndoRedoState3.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** If the `currentStatePointer` is at index 0, pointing to the initial CommonCents state, then there are no previous CommonCents states to restore. The `undo` command uses `Model#canUndoCommonCents()` to check if this is the case. If so, it will return an error to the user rather
than attempting to perform the undo.

</div>

The following sequence diagram shows how the undo operation works:

![UndoSequenceDiagram](images/UndoSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `UndoCommand` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.

</div>

The `redo` command does the opposite — it calls `Model#redoCommonCents()`, which shifts the `currentStatePointer` once to the right, pointing to the previously undone state, and restores the Common Cents to that state.

<div markdown="span" class="alert alert-info">:information_source: **Note:** If the `currentStatePointer` is at index `commonCentsStateList.size() - 1`, pointing to the latest Common Cents state, then there are no undone CommonCents states to restore. The `redo` command uses `Model#canRedoCommonCents()` to check if this is the case. If so, it will return an error to the user rather than attempting to perform the redo.

</div>

Step 5. The user then decides to execute the command `list`. Commands that do not modify the Common Cents, such as `list`, will usually not call `Model#commitCommonCents()`, `Model#undoCommonCents()` or `Model#redoCommonCents()`. Thus, the `commonCentsStateList` remains unchanged.

![UndoRedoState4](images/UndoRedoState4.png)

Step 6. The user executes `clear`, which calls `Model#commitCommonCents()`. Since the `currentStatePointer` is not pointing at the end of the `commonCentsStateList`, all Common Cents states after the `currentStatePointer` will be purged. Reason: It no longer makes sense to redo the `add n/David …​` command. This is the behavior that most modern desktop applications follow.

![UndoRedoState5](images/UndoRedoState5.png)

The following activity diagram summarizes what happens when a user executes a new command:

![CommitActivityDiagram](images/CommitActivityDiagram.png)

#### Design consideration:

##### Aspect: How undo & redo executes

* **Alternative 1 (current choice):** Saves the entire Common Cents.
  * Pros: Easy to implement.
  * Cons: May have performance issues in terms of memory usage.

* **Alternative 2:** Individual command knows how to undo/redo by
  itself.
  * Pros: Will use less memory (e.g. for `delete`, just save the person being deleted).
  * Cons: We must ensure that the implementation of each individual command are correct.

_{more aspects and alternatives to be added}

### \[Proposed\] Data archiving

_{Explain here how the data archiving feature will be implemented}

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

* has a need to manage a significant number of business accounts, each with a significant number of financial entries
* prefer desktop apps over other types
* can type fast
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps

**Value proposition**: manage financial entries faster than a typical mouse/GUI driven app


### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​                                 | I want to …​                                   | So that I can…​                                                                 |
| -------- | ------------------------------------------ | ------------------------------------------------- | -------------------------------------------------------------------- |
| `* * *`  | user                                       | exit the app                                      |                                                                      |
| `* * *`  | user                                       | add an expense/revenue entry                       |                                                                      |
| `* * *`  | user                                       | delete an entry                                   | remove entries that I no longer need                                 |
| `* *`    | user                                       | have multiple accounts for different businesses   | keep expense/earning entries for the respective businesses separate  |

*{More to be added}*

### Use cases 
(Update the number once all the use cases are done) (Comment)

(For all use cases below, the **System** is the `CommonCents` and the **Actor** is the `user`, unless specified otherwise)

**Use case: UC01 - Add an expense entry**

**MSS**

1.  User requests to add an expense
2.  Common Cents adds the expense to expense list and displays success message.

    Use case ends.

**Extensions**

* 1a. The given command input is in invalid format.

    * 1a1. Common Cents shows an error message.

      Use case resumes at step 1.


**Use case: UC02 - Add a revenue**

**MSS**

1.  User requests to add revenue.
2.  Common Cents adds revenue to revenue list and displays success message.

    Use case ends.

**Extensions**

* 1a. The given command input is in invalid format.

    * 1a1. Common Cents shows an error message.

      Use case resumes at step 1.

**Use case: UC03 - Delete an expense**

**MSS**

1.  User requests to delete an expense.
2.  Common cents deletes the expense.

    Use case ends.

**Extensions**

* 1a. The given command input is in invalid format.

    * 1a1. Common cents shows an error message.

      Use case resumes at step 1.

**Use case: UC04 - Delete a revenue**

**MSS**

1.  User requests to delete an revenue.
2.  Common Cents removes the revenue from the revenue list and displays success message.

    Use case ends.

**Extensions**

* 1a. The given command input is in invalid format.

    * 1a1. Common cents shows an error message.

      Use case resumes at step 1.

**Use case: UC - Undoing an add command**

**MSS**

1.  User requests <u> add an expense (UC01)</u>.
2.  User requests to undo command.
3.  Common Cents returns to the state prior to the add command and displays success message.

**Use case: UC - Undoing a delete command**

**MSS**

1.  User requests <u> delete an expense (UC03)</u>.
2.  User requests to undo command.
3.  Common Cents returns to the state prior to the delete command and displays success message.

**Use case: UC - Undoing a edit command**

**MSS**

1.  User requests <u> edit an expense (UC)</u>.
2.  User requests to undo command.
3.  Common Cents returns to the state prior to the edit command and displays success message.


**Use case: UC - Add an account**

**MSS**

1. User request to add a new account.
2. Common Cents adds account to account list and displays success message

    Use case ends.

**Extensions**

* 1a. The given command input is in invalid format.

    * 1a1. Common Cents shows an error message.

      Use case resumes at step 1.
      
* 1b. The account to be added has the same name as an existing account in Common Cents.

    * 1b1. Common Cents shows an error message.

      Use case resumes at step 1.

**Use case: UC - Listing accounts**

**MSS**

1.  User requests to list all the accounts
2.  Common Cents displays the name of the accounts and their indices.

    Use case ends.

**Use case: UC - Delete a account**

**MSS**

1.  User requests <u> list all the account (UC)</u>.
2.  User requests to delete account.
3.  Common Cents removes the account from the account list and displays success message.

    Use case ends.

**Extensions**

* 2a. The given command input is in invalid format.

    * 2a1. Common cents shows an error message.

      Use case resumes at step 2.

* 2b. Common Cents only has an account.

    * 2b1. Common Cents shows an error message.
    
      Use case resumes at step 2.
    
* 2c. User is currently managing the account to be deleted.
    * 2c1. Common Cents shows an error message.
          
      Use case resumes at step 2.

**Use case: UC - Editing the account's name**

**MSS**

1.  User requests <u> list all the account (UC)</u>.
2.  User requests to edit the account's name.
3.  Common Cents edits the account name displays success message.

    Use case ends.

**Extensions**

* 2a. The given command input is in invalid format.

    * 2a1. Common cents shows an error message.

      Use case resumes at step 2.
      
* 2b. The new account name is the same as a name of an existing account.

    * 2b1. Common cents shows an error message.
    
      Use case resumes at step 2.
      
* 2c. The new account name is the same as the current name of the account.

    * 2c1. Common cents shows an error message.
    
      Use case resumes at step 2.

**Use case: UC - Switching to an account**

**MSS**

1.  User requests <u> list all the account (UC)</u>.
2.  User requests to switch to another account.
3.  Common Cents switches to another account and displays success message.

    Use case ends.

**Extensions**

* 2a. The given command input is in invalid format.

    * 2a1. Common cents shows an error message.

      Use case resumes at step 2.
      
* 2b. The user is already on the account to be switched.

    * 2b1. Common cents shows an error message.
    
      Use case resumes at step 2.
      
**Use case: UC - Exiting app**
    
**MSS**

1.  User requests to exit
2.  Common Cents responds with exit message and closes.

    Use case ends.



*{More to be added}*

### Non-Functional Requirements

1.  Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2.  Should be able to hold up to 1000 entries per account without a noticeable sluggishness in performance for typical
3.  A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
4.  Should be able to perform simple arithmetic with up to 1000 entries without a significant drop in performance
5.  Should be able to understand the layout of product without much reference to the user guide
6.  Should be able to hold up to 100 accounts without taking up excess memory
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


### Entry-level commands
1. Adding an entry while all entries are being shown
   1. Prerequisites: List all entries using the `list` command.
   
   1. Test case: `add c/expense d/buying paint a/6.45 t/arts`<br>
      Expected: Expense is added to the end of the expense list. Details of the expense added shown in the status message. 
      Pie chart and total expense value are updated.
      
   1. Test case: `add c/revenue d/selling paintings a/25 t/arts`<br>   
      Expected: Revenue  is added to the end of the revenue list. Details of the revenue added shown in the status message. 
      Pie chart and total revenue value are updated.
      
   1. Test case: `add c/wronginput d/buying paint a/6.45 t/arts`<br>
      Expected: No entry is added, Error details shown in the status message
      
   1. Other incorrect add commands to try: `add `, `add c/expense d/ a/6.45`, `add c/revenue d/selling paintings a/x`(where x is not a valid monetary value).<br>
      Expected: Similar behaviour with previous testcase. Note that error details may differ based on which parameters of the input that is in an incorrect format.   

1. Deleting a entry while all entries are being shown

   1. Prerequisites: List all entries using the `list` command. Multiple entries in the list.

   1. Test case: `delete 1 c/expense`<br>
      Expected: First expense is deleted from the expense list. Details of the deleted expense entry shown in the status message. 
      Pie chart and total expense value are updated.

   1. Test case: `delete 1 c/revenue`<br>
      Expected: First revenue is deleted from the expense list. Details of the deleted revenue shown in the status message. 
      Pie chart and total revenue value
      
   1. Test case: `delete 0 c/expense`<br>
      Expected: No expense entry is deleted. Error details shown in the status message. 

   1. Other incorrect delete commands to try: `delete 0 c/revenue`, `delete x` (where x is larger than the account list size or smaller than 1), `...`.<br>
      Expected: Similar behaviour with previous testcase. Note that error details may differ based on which parameters of the input that is in an incorrect format.   


1. Undoing an entry-level command

   1. Prerequisites: Use at least one add, edit or delete `command`.
   
   1. Test case: `undo`<br>
   Expected: Previous add, edit or delete command is reverted. Success message of the undo command will be shown in the status message. 
   Pie chart is reverted to the state prior to the previous command.
   
### Account-level commands
1. Adding a new unique account
   1. Prerequisite: Ensure no accounts in Common Cents has the name `New Account`.

   1. Test case: `newacc n/New Account`<br>
      Expected: New account is added to Common Cents. (use `listacc` command to check) First expense entry is deleted from the expense list. 
      Details of the added account is shown in the status message.
   
   1. Test case: `newacc n/`<br>
      Expected: No account is added. Error details shown in the status message.
      
   1. Other incorrect add account commands to try: `newacc`.<br>
      Expected: Similar behaviour with previous testcase. Note that error details may differ based on which parameters of the input that is in an incorrect format.   
         
1. Deleting an account
   1. Prerequisite: Have at least two account, and be on the first account. (use `listacc` command to check)
   
   1. Test case: `deleteacc 2`<br>
      Expected: Second account is deleted from the account list. (use `listacc` command to check) 
      Details of the deleted account is shown in the status message.
      
   1. Test case: `deleteacc 0`<br>
      Expected: No account is deleted. Error details shown in the status message.
      
   1. Test case: `deleteacc`, `delete x` (where x is larger than the account list size or smaller than 1)
      Expected: Similar behaviour with previous testcase. Note that error details may differ based on which parameters of the input that is in an incorrect format.   

### Saving data

1. Dealing with missing/corrupted data files

   1. Prerequisite: Remove commonCents.json in data folder in the home folder.
   1. Launch Common Cent via CLI
       1. Expected: CLI displays log stating that data file is not found and a sample data is loaded. Common Cents
       launches with two accounts, `Default Account 1` and `Default Account 2` and each account has sample expenses and revenues.
