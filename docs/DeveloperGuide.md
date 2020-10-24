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

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues the command `remove 1`.

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
1. The command execution can affect the `Model` (e.g. adding an order item).
1. The result of the command execution is encapsulated as a `CommandResult` object which is passed back to the `Ui`.
1. In addition, the `CommandResult` object can also instruct the `Ui` to perform certain actions, such as displaying help to the user.

Given below is the Sequence Diagram for interactions within the `Logic` component for the `execute("remove 1")` API call.

![Interactions Inside the Logic Component for the `delete 1` Command](images/DeleteSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `DeleteCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>

### Model component

![Structure of the Model Component](images/ModelClassDiagram.png)

**API** : [`Model.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/model/Model.java)

The `Model`,

* stores a `UserPref` object that represents the user’s preferences.
* contains the `AddressBook` and `MenuManager`, `OrderManager` components.
    * `AddressBook` stores the data for vendors.
    * `MenuManager` stores the data for food items.
    * `OrderManager` stores the data for order items.

* Each of these coponents exposes an unmodifiable `ObservableList` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
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

Step 2. The user executes `delete 5` command to delete the 5th person in the address book. The `delete` command calls `Model#commitAddressBook()`, causing the modified state of the address book after the `delete 5` command executes to be saved in the `addressBookStateList`, and the `currentStatePointer` is shifted to the newly inserted address book state.

![UndoRedoState1](images/UndoRedoState1.png)

Step 3. The user executes `add n/David …​` to add a new person. The `add` command also calls `Model#commitAddressBook()`, causing another modified address book state to be saved into the `addressBookStateList`.

![UndoRedoState2](images/UndoRedoState2.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** If a command fails its execution, it will not call `Model#commitAddressBook()`, so the address book state will not be saved into the `addressBookStateList`.

</div>

Step 4. The user now decides that adding the person was a mistake, and decides to undo that action by executing the `undo` command. The `undo` command will call `Model#undoAddressBook()`, which will shift the `currentStatePointer` once to the left, pointing it to the previous address book state, and restores the address book to that state.

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
  * Pros: Will use less memory (e.g. for `delete`, just save the person being deleted).
  * Cons: We must ensure that the implementation of each individual command are correct.

_{more aspects and alternatives to be added}_

### \[Proposed\] Data archiving

_{Explain here how the data archiving feature will be implemented}_

### Friendly Syntax

The friendly syntax allows users to type in just the prefix of a command to execute it.

- If there is no command with the given prefix, an `Parse Exception` will be thrown.
- If the inputted prefix exists for more than 1 command, an `Parse Exception` will be thrown.
- If there is a command which is a prefix for another command, it can no longer be executed.

The following diagram summarizes the sequence when the RemoveCommand is executed from the user input of `r 1`.

![Friendly_Syntax_Architecture_Diagram](images/Friendly_Syntax_Architecture_Diagram.png)

Given below is an example usage scenario and how the friendly syntax behaves at the parse stage.

Step 1: The user launches the application for the first time and enters the vendor command `vendor 1`.

Step 2: SupperStrikers loads the menu of the 1<sup>st</sup> vendor into the GUI.

Step 3: The user enters the add command `add 1 3` 

Step 4:  SupperStrikers adds 3 of the 1<sup>st</sup> item into the order

Step 5: The user enters the command `r 1 1`.

Step 6: The `LogicManager#execute` is executed to call the `AddressBookParser#parseCommand` method.

Step 7: `AddressBookParser#parseCommand` checks if the inputted command word is a prefix of one and only one of the valid commands by filtering the list of valid commands based on whether they start with the user inputted prefix. The `r` in this case maps to the `remove` keyword.

Step 8: The `RemoveCommand` is executed and one quantity of the first item in the order is removed.  

### Vendor Command

* The Vendor Command allows the user to select a vendor from the `AddressBook` to order from.
* If the vendor does not exist, a `Command Exception will be thrown`
* If the vendor selected is different from the current vendor, the model will clear the current order.

The following diagram summarises the sequence when the VendorCommmand is executed.![VendorSequendeDiagram](images/VendorCommandSequenceDiagram.png)


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

* has a need to order supper frequently
* stays on campus
* prefer desktop apps over other types
* can type fast
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps

**Value proposition**: manage ordering supper faster than a typical mouse/GUI driven app


### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​                                    | I want to …​                     | So that I can…​                                                        |
| -------- | ------------------------------------------ | ------------------------------ | ---------------------------------------------------------------------- |
| `* * *`  | new user                                   | see usage instructions         | refer to instructions when I forget how to use the App                 |
| `* * *`  | NUS resident                           | add a food item to my supper order |                                                                        |
| `* * *`  | NUS resident | remove a food item from my supper order |                                    |
| `* * *`  | NUS resident | see the menu | view all the items currently ordered by me |
| `* * *` | NUS resident | see the vendor list and select vendor | confirm which vendor to order from |
| `* * ` | NUS resident | see the total price of my current order | decide whether I want to order more |
| `*` | NUS resident | confirm order | finalize my supper selection |
| `* * *` | NUS resident | undo my commands | fix any mistakes made while ordering |
| `* * *` | NUS resident | submit my order |  |
| `* *` | NUS resident | clear the current order | start a new order |
*{More to be added}*

### Use cases

(For all use cases below, the **System** is the `SupperStrikers` and the **Actor** is the `user`, unless specified otherwise)

**Use case: Showing and selecting vendor**

**MSS**

1. User requests to view the list of vendors
2. SupperStrikers displays the list of vendors
3. User requests to choose a specified vendor
4. SupperStrikers displays the menu of the selected vendor
5. SupperStrikers creates a new empty order of the selected vendor

**Extensions**

- 3a. The given index is invalid.

  - 3a1. SupperStrikers displays an error message.

    Use Case resumes at step 2
- 3b. The user has already selected a different vendor.
  
  - 3b1. SupperStrikers clears the order of the current vendor.
  
  Use case resumes at step 4.

**Use case: Viewing current order**

**MSS**

1. User requests to list all the items in the current order
2. SupperStrikers displays the current order to the user

**Use case: Viewing total**

**MSS**

1. User requests to see the total price of the current order
2. SupperStrikers displays the total price of the current order to the user

**Use case: Clearing current order**

**MSS**

1. User requests to clear the current order
2. SupperStrikers clears the current order
3. SupperStrikers creates a new empty order of the selected order  

**Use case: Submit order**

**MSS**

1. User requests to submit the current order
2. SupperStrikers displays a copy of the order in a submittable format to the user

**Use case: Add an item**

**MSS**

1. User requests to add a specified quantity of an item listed in the vendor menu
2. SupperStrikers adds the item along with the quantity specified into the current order
4. SupperStrikers displays the updated order with the newly added item

**Extensions**

- 1a. The given index is invalid.

  - 1a1. SupperStrikers shows an error message.

    Use Case resumes at step 1

- 1b. The given quantity is negative.

  - 1b1. SupperStrikers shows an error message.

    Use Case resumes at step 1
  
- 1c. The quantity is not specified

  - 1c1. SupperStrikers adds the quantity of the item by 1.

    Use case resumes at step 1.

**Use case: Remove an item**

**MSS**

1.  User requests to remove a quantity of a specific item in the current order
2.  SupperStrikers decreases the quantity of the item by the quantity provided.

    Use case ends.

**Extensions**

* 1a. The list is empty.

  Use case ends.

* 1b. The given index is invalid.

    * 1b1. SupperStrikers shows an error message.

      Use case ends.
    
* 1c. The given quantity is invalid.
  
     * 1c1. SupperStrikers shows an error message.
     
      Use case ends.
     
* 1d. The given quantity is larger or equal to the quantity to the order item.

     * 1d1. SupperStrikers removes the order item at the specified index.

     Use case ends.

* 1e. The quantity is not specified.

     - 1e1. SupperStrikers removes the order item at the specified index.

     Use case ends.

*{More to be added}*

### Non-Functional Requirements

1.  Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2.  Should be able to hold up to 1000 items without a noticeable sluggishness in performance for typical usage.
3.  A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.

*{More to be added}*

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, OS-X

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.

<div markdown="span" class="alert alert-info">:information_source: **Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.
</div>

### Launch and shutdown

1. Initial launch

   1. Download the jar file and copy into an empty folder

   2. Double-click the jar file Expected: Shows the GUI with a set of sample contacts. The window size may not be optimum.

1. Saving window preferences

   1. Resize the window to an optimum size. Move the window to a different location. Close the window.

   2. Re-launch the app by double-clicking the jar file.<br>
       Expected: The most recent window size and location is retained.

2. _{ more test cases …​ }_

### Selecting a vendor

1. Selecting a vendor while all the vendors are being shown

   1. Prerequisites: List all vendors using the `vendor` command.

   2. Test case: `vendor 1`<br>
      Expected: First vendor is selected. The menu from the selected vendor is displayed.

   3. Test case: `vendor`<br>
      Expected: No vendor is selected. The list of vendors is displayed again.

   4. Test case: `vendor 0`<br>

      Expected: No vendor is selected. Error details shown in status message. Status bar remains the same.

   5. Other incorrect delete commands to try: `vendor x`, `...` (where x is larger than the list size)<br>
      Expected: Similar to previous.

2. _{ more test cases …​ }_

### Adding an item

1. Adding an item while an order is currently active

   1. Prerequisites: Create a supper order using the `view` command.

   2. Test case: `add 1 1`<br>
      Expected: 1 order of the first item from the menu is added into the order. Details of the added order shown in the status message.

   3. Test case: `add 1 0`<br>
      Expected: No item is added. Error details shown in the status message.

   4. Test case: `add 0 1`<br>

      Expected: No item is added. Error details shown in the status message.

   5. Other incorrect add commands to try: `add`, `add -1 -1`, `add x y`, `...` (where x is larger than the menu size)<br>
      Expected: Similar to previous.

2. _{ more test cases …​ }_

### Removing an item

1. Removing an item while an order is currently active

   1. Prerequisites: List all items using the `view` command. There is at least one item in the order.

   2. Test case: `remove 1`<br>
      Expected: All the quantity of the first item is removed from the order. Details of the removed order shown in the status message.

   3. Test case: `remove 1 1`<br>
      Expected: 1 order of the first item from the user's order is removed from the order. Details of the removed order shown in the status message.

   4. Test case: `remove 0`<br>
      Expected: No item is deleted. Error details shown in the status message.

   5. Test case: `remove 1 0`<br>
      Expected: No item is deleted. Error details shown in the status message.

   6. Other incorrect delete commands to try: `remove`, `remove -1 -1`,`remove x y`, `...` (where x is larger than the list size or y is larger than the user's order quantity amount)<br>
      Expected: Similar to previous.
2. _{ more test cases …​ }

### Saving data

1. Dealing with missing/corrupted data files

   1. _{explain how to simulate a missing/corrupted file, and the expected behavior}_

2. _{ more test cases …​ }_
