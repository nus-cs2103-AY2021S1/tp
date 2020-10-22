---
layout: page
title: Developer Guide
---
Table of Contents

---

## 1. **Setting up, getting started**

To get starte, please refer to the sertting up guide [here](SettingUp.md).

---

## 2. **Design**

### 2.1. Architecture


The architecture diagram given below explains the high-level design of the software NUStorage.

<img src="images/ArchitectureDiagram.png" width="450" />

<div markdown="span" class="alert alert-primary">

</div>

Given below is a quick overview of each of the components.

#### 2.1.1. [Main classes](#211-main-classes)

**`Main`** has two classes: [`Main`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/Main.java) and [`MainApp`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/MainApp.java). This component is responsible for:

* At app launch: Initializes the components in the correct sequence, and connects them up with each other.
* At shut down: Shuts down the components and invokes cleanup methods where necessary.

#### 2.1.2. Common classes

**`Commons`** represents a collection of classes used by multiple other components to support the software.

#### 2.1.3. Core classes

##### 2.1.3.1. UI

**`UI`** contains the user interface elements and is responsible for taking in user inputs and displaying the corresponding results and infomative feedback messages.

##### 2.1.3.2. Logic

**`Logic`** is responsible for executing the commands and ensuring the logic flow of the software.

##### 2.1.3.3. Model

**`Model`** represents a collection of model objects used by the software to hold the data of the application in memory while the app is running.

##### 2.1.3.4. Storage

**`Storage`** is responsible for:

* At app launch: Reading the data files and convert them into their corresponding `model` objects.
* When a command is executed: Converting the `model` objects into their corresponding serializable formats, then store these serializable objects into the data file.

Each of the four components in the **core classes**:

* Defines its *API* in an `interface` with the same name as the Component.
* Exposes its functionality using a concrete `{Component Name}Manager` class (which implements the corresponding API `interface` mentioned in the previous point.

For example, the `Logic` component (see the class diagram given below) defines its API in the `Logic.java` interface and exposes its functionality using the `LogicManager.java` class which implements the `Logic` interface.

![Class Diagram of the Logic Component](images/LogicClassDiagram.png)

---

### 2.2. Interactions between architectural components

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues the command `delete 1`.

![Architecture sequence diagram](images/ArchitectureSequenceDiagram.png)

Section 2.3 elaborates on each of the components in detail.

### 2.3. 2.3 Details of Architectural Components

#### 2.3.1. UI component

Given below is the class diagram of the UI component of NUStorage.

![Structure of the UI Component](images/UiComponent.png)

**API** :
[`Ui.java`](https://github.com/AY2021S1-CS2103T-T11-3/tp/blob/master/src/main/java/nustorage/ui/Ui.java)

The UI component consists of a `MainWindow` that is made up of `CommandBox`, `ResultDisplay`, `TabPane`, `StatusBarFooter` and `HelpWindow`. The `TabPane` holds multiple `Tab` objects such as `Inventory` and `Finance`.

Every component in UI, including the `MainWindow`, inherits from the abstract `UiPart` class.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in the corresponding `.fxml` files with the same name, located in the `src/main/resources/view` directory. For example, the layout of the [`MainWindow`](https://github.com/AY2021S1-CS2103T-T11-3/tp/blob/master/src/main/java/nustorage/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/AY2021S1-CS2103T-T11-3/tp/blob/master/src/main/resources/view/MainWindow.fxml)

The `UI` component:

* Executes user commands using the `Logic` component.
* Updates itself with modified data whenever there are changes made by the user.

#### 2.3.2. Logic component

![Structure of the Logic Component](images/LogicClassDiagram.png)

**API**:
[`Logic.java`](https://github.com/AY2021S1-CS2103T-T11-3/tp/blob/master/src/main/java/nustorage/logic/Logic.java)

1. `Logic` uses the `NuStorageParser` class to parse the user command.
2. This results in a `Command` object which is executed by the `LogicManager`.
3. The command execution can affect the `Model` (e.g. adding a person).
4. The result of the command execution is encapsulated as a `CommandResult` object which is passed back to the `Ui`.
5. In addition, the `CommandResult` object can also instruct the `Ui` to perform certain actions, such as displaying help to the user.

Given below is the Sequence Diagram for interactions within the `Logic` component for the `execute("delete_finance 1")` API call.

![Interactions Inside the Logic Component for the `delete_finance 1` Command](images/DeleteFinanceSequenceDiagram.png)

#### 2.3.3. Model component

![Structure of the Model Component](images/ModelClassDiagram.png)

**API** : [`Model.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/model/Model.java)

The `Model`,

* Stores a `UserPref` object that represents the user’s preferences.
* Stores Inventory and financeAccount data.
* Exposes unmodifiable `ObservableList<InventoryRecord>` and `ObservableList<FinanceRecord>` that can be 'observed' e.g. the UI can be bound to the lists so that the UI automatically updates when the data in the lists change.
* Does __not__ depend on any of the other three components (UI, Logic and Storage).

#### 2.3.4. Storage component

This section shows the structure and logic of the storage component of NUStorage. All storage-related classes are stored in the package `nustorage.storage`.

Given below is the class diagram of the `storage` component.

![Structure of the Storage Component](images/StorageClassDiagram.png)

The storage component comprises three different sections:

1. The `UserPrefStorage` section:
   * loads the user preferences and settings on start up.
   * stores the user preferences and settings upon exiting .

2. The `FinanceAccountStorage` section:
   * loads the previously saved finance records on start up.
   * stores new finance records as they are added / logged into the software.

3. The `InventoryStorage` section:
   * loads the previously saved inventory records on start up.
   * stores new inventory records as they are added / logged into the software.

**API** : [`Storage.java`](https://github.com/AY2021S1-CS2103T-T11-3/tp/blob/master/src/main/java/nustorage/storage/Storage.java)

* Saving inventory:

  * Given below is the **activity** diagram for saving the inventory. Saving finance account works similarly.
  This diagram shows the decision pathways of the storage component when saving inventory.

      ![Activity Diagram for saving inventory](images/SavingInventoryActivityDiagram.png)

  * The following is the **sequence** diagram for saving the inventory. Saving finance account works similarly.
  This diagram shows the function calls between classes when saving inventory.

      ![Sequence Diagram for loading finance](images/SaveInventorySequenceDiagram.png)

* Loading finance account:

  * Given below is the **activity** diagram for loading the finance account on start up. Loading inventory works similarly.
  This diagram shows the decision pathways of the storage component when loading finance account.

      ![Activity Diagram for loading finance account](images/LoadingFinanceActivityDiagram.png)

  * The following is the **sequence** diagram for loading the finance account. Loading inventory works similarly.
  This diagram shows the function calls between classes when loading finance account.

      ![Sequence Diagram for loading finance](images/LoadFinanceSequenceDiagram.png)

#### 2.3.5. Common classes

Classes used by multiple components are in the `nustorage.commons` package.

---

## 3. **Implementation**

This section describes some noteworthy details on how certain features are implemented.

### 3.1. [Proposed] Undo/redo feature

#### 3.1.1. Proposed Implementation

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

#### 3.1.2. Design considerations

##### 3.1.2.1. Aspect: How undo & redo executes

* **Alternative 1 (current choice):** Saves the entire address book.
  * Pros: Easy to implement.
  * Cons: May have performance issues in terms of memory usage.

* **Alternative 2:** Individual command knows how to undo/redo by
  itself.
  * Pros: Will use less memory (e.g. for `delete`, just save the person being deleted).
  * Cons: We must ensure that the implementation of each individual command are correct.

_{more aspects and alternatives to be added}_

### 3.2. \[Proposed\] Data archiving

_{Explain here how the data archiving feature will be implemented}_


---

## 4. **Documentation, logging, testing, configuration, dev-ops**

* [Documentation guide](Documentation.md)
* [Testing guide](Testing.md)
* [Logging guide](Logging.md)
* [Configuration guide](Configuration.md)
* [DevOps guide](DevOps.md)

---

## 5. **Appendix: Requirements**

### 5.1. Product scope

**Target user profile**:

* has a need to manage a significant number of varying inventories, as well as their finances
* prefer desktop apps over other traditional modes of managing inventories/finances, such as pen and paper
* can type fast
* is reasonably comfortable using CLI apps
* Proficient in english to allow them to check the user guide when they are having trouble with the app

**Value proposition**: manage inventories and finances faster than the typical paper and pen

### 5.2. User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​                                    | I want to …​                   | So that I can…​                                                        |
| -------- | ------------------------------------------ | ------------------------------ | ---------------------------------------------------------------------- |
| `* * *`  | inventory keeper                           | view inventory records         | keep track of the amount of products I have left                       |
| `* * *`  | small business owner                       | view finance records           | plan the next steps of my business                                     |
| `* * *`  | forgetful business owner                   | save my records                | track my financial records more easily                                 |
| `* * *`  | small business owner                       | add and remove inventories     | update my inventory status                                             |
| `* *`    | accountant                                 | edit my financial records      | update my financial status                                             |
| `*`      | user                                       | be able to exit the app safely |                                                                        |

*{More to be added}*

### 5.3. Use cases

For all use cases (unless specified otherwise):

* The **System** is `NUStorage`
* The **Actor** is `User`

**Use case: Add an inventory item**

**MSS**

1. User requests to list inventory
2. NUStorage shows the inventory list
3. User requests to add a specific inventory item into the list
4. NUStorage adds the item to the list

    Use case ends.

**Use case: Remove an inventory item**

**MSS**

1. User requests to list inventory
2. NUStorage shows the inventory list
3. User requests to remove a specific inventory item into the list
4. NUStorage removes the item from the list at the specified index

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. The given index is invalid.

    * 3a1. NUStorage shows an error message.

      Use case resumes at step 2.

**Use case: Add a finance record**

**MSS**

1. User requests to list finances
2. NUStorage shows the finance record list
3. User requests to add a specific finance record into the list
4. NUStorage adds the record to the list

    Use case ends.

**Use case: Remove a finance record**

**MSS**

1. User requests to list finances
2. NUStorage shows the finance record list
3. User requests to remove a specific financial record into the list
4. NUStorage removes the record from the list at the specified index

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. The given index is invalid.

    * 3a1. NUStorage shows an error message.

      Use case resumes at step 2.

**Use case: List finance/inventory records**

**MSS**

1. User requests to list finances / inventories
2. NUStorage shows the finance record / inventory list

* 2a. The list is empty.

  Use case ends.

**Use case: Save finance / inventory records**

**MSS**

1. User requests to save the current data stored
2. NUStorage saves both finance and inventory records and shows a success message

**Use case: Exiting the application**

**MSS**

1. User requests to exit NUStorage
2. NUStorage saves both finance and inventory records and shows a goodbye message
3. NUStorage terminates after 1.5 seconds



### 5.4. Non-Functional Requirements

1. Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2. Should be able to hold up to 100 financial records and 100 inventory items without a noticeable sluggishness in performance for typical usage.
3. A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.

*{More to be added}*

### 5.5. Glossary

* **Mainstream OS**: Windows, Linux, Unix, OS-X
* **Inventory**: An item that a user wishes to record. An inventory item can refer to any existing object
* **Finances**: A record that allows a user to monitor his earnings and spending.

---

## 6. **Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.

<div markdown="span" class="alert alert-info">:information_source: **Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

</div>

### 6.1. Launch and shutdown

1. Initial launch

   1. If your system does not already have Java JDK 11 or above, head to [OpenJDK](https://openjdk.java.net/projects/jdk) and install Java JDK 11 or higher.

   1. Download the jar file and copy into an empty folder

   1. Double-click the jar file<br>
      Expected: Shows the GUI with an introductory message. The window size may not be optimum.

      **NOTE**: If double-clicking the jar file does not work, open up your terminal and navigate to the directory that contains the jar file. Then, enter the following command:<br>
      `java -jar nustorage.jar`

1. Saving window preferences

   1. Resize the window to an optimum size. Move the window to a different location. Close the window.

   1. Re-launch the app by double-clicking the jar file.<br>
       Expected: The most recent window size and location is retained.

1. Saving and Shutting Down

   1. You may shut down NUStorage by entering the `exit` command or simply clicking the close button on the top-right corner of the application.

   1. NUStorage automatically saves any changes made to records while the application is in use. Closing and re-opening the application will not result in any data loss.

### 6.2. Adding a record

1. Adding a financial/inventory record.

    1. Prerequisites: None

    1. Test case: `add_inventory i/MacBook q/10`<br>
        Expected: An inventory item 'MacBook' is added with the quantity of 10. Details of the added record shown in the status message.

    1. Test case: `add_finance amt/1000 at/2020-09-09`
        Expected: A finance record of an increase by $1000.00 is added, time-stamped at 09 SEP 2020. Details of the added record shown in status message.
        
    1. Test case: `add_inventory i/Iphone q/100 c/10`
        Expected: An inventory item 'Iphone' is added with the quantity of 10, along with a financial record depicting the record with the cost of '10' for each 'Iphone'. Details of the added record shown in status message.

    1. Other incorrect add commands to try: `add`, `add_record`, `add_inventory i/MacBook` `add_finance amt/1000 at/2020-13-13` <br>
        Expected: No record is added. Error details shown in the status message.

### 6.3. Deleting a record

1. Deleting a record while all inventory/financial records are being shown.

   1. Prerequisites: List all inventory/financial records using the `list_inventory` or `list_finance` commands respectively. Multiple records in the list.

   1. Test case: `delete_finance 1`<br>
      Expected: First finance record is deleted from the list. Details of the deleted record shown in the status message.
      
   1. Test case: `delete_inventory 2`<br>
      Expected: Second inventory record is deleted from the list. Details for the deleted record shown in the status message.   
      
   1. Test case: `delete_inventory 0`<br>
      Expected: No record is deleted. Error details shown in the status message.

   1. Other incorrect delete commands to try: `delete_finance `, `delete_inventory x`, `delete 1`<br>
      Expected: No record is added. Error details shown in the status message.
      
      
### 6.4. Editing a record

1. Editing a record while all inventory/financial records are being shown.

   1. Prerequisites: List all inventory/financial records using the `list_inventory` or `list_finance` commands respectively. Multiple records in the list.
   
   1. Test case: `edit_inventory 1 i/Pasta q/100`<br>
      Expected: The first inventory record's item and quantity are changed to 'Pasta' and '100' respectively. Details for the edited record shown in the status message.
      
   1. Test case: `edit_finance 2 amt/3000 at/2021-01-02`<br>
      Expected: The second finance record's amount and date are changed to '3000' and '02 January 2021' respectively. Details for the edited record shown in the status message.
   
   1. Test case: `edit 1 i/Pasta q/100`<br>
      Expected: No record is edited. Error details shown in the status message.
      
   1. Other incorrect edit commands to try:<br>
      `edit_finance 0 amt/3000 at/2021-01-02`, `edit_inventory x i/Pasta q/100`(where x is larger than the number of inventory records in the list)<br>
      Expected: No records are edited. Error details shown in the status message.
   
### 6.5. Listing records

1. Listing all inventory/finance records

   1. Prerequisites: None. In order for the application to display record cards, ensure that you already have some inventory/finance records saved.
   
   1. Test case: `list_inventory`<br>
      Expected: All saved inventory records are listed horizontally below the status bar.
      
   1. Test case: `list_finance`<br>
      Expected: All saved finance records are listed horizontally below the status bar.
   
   1. Test case: `list`<br>
      Expected: No records are listed. Error details shown in the status message.
   
   1. Other incorrect list commands to try:<br>
      `list-finance`, `list inventory`, `list `.<br>
      Expected: No records are listed. Error details shown in the status message.
      
1. Finding certain inventory/finance records based on a certain keyword.

   1. Prerequisites: Have at least 1 or more inventory/finance records saved.
   
   1. Test case: `find_inventory phone`<br>
      Expected: All saved inventory records with the word 'phone' in its description/id are listed horizontally below the status bar. If there are no such records, then there will be no records listed.
      
   1. Test case: `find_finance 1000`<br>
      Expected: All saved finance records with the number '1000' in its amount/id are listed horizontally below the status bar. If there are no such records, then there will be no records listed.
      
   1. Test case: `find finance 1000`<br>
      Expected: No records will be displayed. Error details shown in the status message.
      
   1. Other incorrect find commands to try: <br>
      `find phone`, `find_inventory `, `find-finance 1000`.
      Expected: No records will be displayed. Error details shown in the status message.
       
## 7. **Appendix: Proposed features for future implementation**

1. Customizable Commands<br>
   1. We understand that not everybody that uses NUStorage would be comfortable with the current names of commands. Therefore, we plan to implement a feature that allows users to rename the commands to better suit their liking.
   
1. Graphical Depiction<br>
   1. Currently, NUStorage only displays inventory and finance records as a static listing. We plan to include a new feature that displays the data in a graphical manner.
   
   1. Expanding on this, we could include a number of graphical options, such as pie charts, bar graphs and line graphs.
   
1. Saving inventory as items instead of records<br>
   1. Currently, NUStorage saves inventory as records. For business that constantly deals with the same set of items daily, it might seem tiresome to constantly have to add inventory records of the same item. Thus, we plan to implement a feature that allows users to 'save' a certain inventory item for reuse, allowing them to only have to type the quantity when adding records.
