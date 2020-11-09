---
layout: page
title: Developer Guide
---
* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## **Design**

### Architecture

<img src="images/ArchitectureDiagram.png" width="450" />

The ***Architecture Diagram*** given above explains the high-level design of the App. Given below is a quick overview of each component.

<div markdown="span" class="alert alert-primary">

:bulb: **Tip:** The `.puml` files used to create diagrams in this document can be found in the [diagrams](https://github.com/AY2021S1-CS2103-T16-2/tp/tree/master/docs/diagrams) folder. Refer to the [_PlantUML Tutorial_ at se-edu/guides](https://se-education.org/guides/tutorials/plantUml.html) to learn how to create and edit diagrams.

</div>

**`Main`** has two classes called [`Main`](https://github.com/AY2021S1-CS2103-T16-2/tp/blob/master/src/main/java/seedu/address/Main.java) and [`MainApp`](https://github.com/AY2021S1-CS2103-T16-2/tp/blob/master/src/main/java/seedu/address/MainApp.java). It is responsible for,
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
[`Ui.java`](https://github.com/AY2021S1-CS2103-T16-2/tp/blob/master/src/main/java/seedu/address/ui/Ui.java)

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `PersonListPanel`, `StatusBarFooter`, `ArchiveModeBox`, `HelpWindow`, `PersonCard`
 etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class.

The `UI` component uses JavaFx UI framework.
The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder.
For example, the layout of the [`MainWindow`](https://github.com/AY2021S1-CS2103-T16-2/tp/blob/master/src/main/java/seedu/address/ui/MainWindow.java) 
is specified in [`MainWindow.fxml`](https://github.com/AY2021S1-CS2103-T16-2/tp/blob/master/src/main/resources/view/MainWindow.fxml)

Several UI parts makes use of a [`DarkTheme.css`](https://github.com/AY2021S1-CS2103-T16-2/tp/blob/master/src/main/resources/view/DarkTheme.css) file to set the design theme. This file is found in the `src/main/resources/view` folder. 

The `UI` component,

* Executes user commands using the `Logic` component.
* Listens for changes to `Model` data so that the UI can be updated with the modified data.

### Logic component

![Structure of the Logic Component](images/LogicClassDiagram.png)

**API** :
[`Logic.java`](https://github.com/AY2021S1-CS2103-T16-2/tp/blob/master/src/main/java/seedu/address/logic/Logic.java)

1. `Logic` uses the `ClientListParser` class to parse the user command.
1. This results in a `Command` object which is executed by the `LogicManager`.
1. The command execution can affect the `Model` (e.g. adding a person).
1. The result of the command execution is encapsulated as a `CommandResult` object which is passed back to the `Ui`.
1. In addition, the `CommandResult` object can also instruct the `Ui` to perform certain actions, such as displaying help to the user.

Given below is the Sequence Diagram for interactions within the `Logic` component for the `execute("delete 1")` API call.

![Interactions Inside the Logic Component for the `delete 1` Command](images/DeleteSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `DeleteCommandParser` and `DeleteCommand` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>


### Model component

![Structure of the Model Component](images/ModelClassDiagram.png)

**API** : [`Model.java`](https://github.com/AY2021S1-CS2103-T16-2/tp/blob/master/src/main/java/seedu/address/model/Model.java)

The `Model`,

* stores a `UserPref` object that represents the user’s preferences.
* stores the client list data.
* stores the policy list data.
* exposes an unmodifiable `ObservableList<Person>` that can be 'observed' 
e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* does not depend on any of the other three components.

### Storage component

![Structure of the Storage Component](images/StorageClassDiagram.png)

**API** : [`Storage.java`](https://github.com/AY2021S1-CS2103-T16-2/tp/blob/master/src/main/java/seedu/address/storage/Storage.java)

The `Storage` component,
* can save `UserPref` objects in json format and read it back.
* can save the client list data in json format and read it back.
* can save the policy list data in json format and read it back.

### Common classes

Classes used by multiple components are in the seedu.address.commons package.

--------------------------------------------------------------------------------------------------------------------

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

### \[Proposed\] Priority Feature

#### Proposed Implementation

The proposed priority feature would involve the UI javafx feature as well as a newly implemented field for the Clientlist.This field for the Clientlist would affect the UI of the ClientList. 

There would be 4 settings for the priority feature: High, Medium, Low, Undefined. If a user never include any of the settings, it would default to undefined. The priority feature would change the entries in the Clientlist, changing the colors of the UI for each entry based on the priority assigned for the Client. 

![Proposed UI](images/UIPriority.png)

Firstly, an optional `priority` field would be added to the `Person` class. 

Given below is an example usage scenario and how the priority mechanism behaves when a user is added. Note that this addition of users via addcommand is the same command used when the user launches the application for the first time. 

Step 1. The user will add a client using the GUI and specifies the user to be added. The `AddCommandParser` will check if there was an empty string input for the priority field, if there is, we will set it as Undefined. If the user puts any other input other than the predefined inputs(H for high, M for medium, L for low), the user will get an error message. 

Step 2: The `AddCommandParser` object will parse the command into an `AddCommand` object in the logic package to check if the user is a duplicated user or not.  If the person is a valid person, the person would be wrapped into a `Model` and passed to the `UI`.

Step 3: In the UI, as it is an `AddCommand` object, the `MainWindow` will update the `PersonListPanel`, which will call the `updateItem` method based on the Person and the priority. This will go to the `PersonListViewCell`, and would attempt to create a person class. 

Step 4: To identify between the different levels of priority, I propose to use a to have a `PriortyIndicatorComponent` class to identify the type of person it is based on the archive status and the priority. The `PriorityIndicatorComponent` class will construct the indicator border and background using `Paint` elements from the `ColorPicker` class. The `PersonList` object will set the background and border based on the returned border and background. 

Step 5: This `PersonList` Object would be returned to the `PersonListViewCell`, updated on the `PersonListPanel` and finally shown in the `Mainwindow`. 

The following sequence diagram shows how the Priority operation works:

![Proposed Sequence Diagram](images/ImplementationPrioritySequence.png)

#### Design consideration:

##### Aspect: How Priority feature works.

* **Alternative 1 (current choice):** Create a parser to settle the image inside the UI class. 
  * Pros: Decreased coupling, easier to find bugs.

* **Alternative 2:** Do aside the parser, just put the switch cases inside the PersonListViewCell
  itself.
  * Pros: Will use less memory (No need to show the parser field).
  * Cons: Increased coupling

### Policy feature

#### Implementation

`Policy` is implemented as shown in this class diagram:

![Policy0](images/PolicyClassDiagram.png)

`PolicyName` and `PolicyDescription` are separate classes rather than string fields.
Implementing `Policy` this way conforms to the same structure as `Person` where string fields are
their own classes.
It also allows for abstraction of methods specified for each of the field classes such as 
checking for validity of each of the individual class's inputs.

Example:

* `PolicyName#isValidPolicyName` & `PolicyDescription#isValidPolicyDescription`

As shown in [**`Model`**](#model-component) above, each Person has an optional `Policy` field.
The `Policy` field can be added to a client using the `add` command, which is specified by the
PolicyName Prefix followed by a valid PolicyName. The PolicyName must be valid and a corresponding
`Policy` must already be in the `PolicyList` maintained by `ModelManager`. The specific `Policy` object
is then referenced from the `PolicyList` and maintained in the `Person` object.

Thus, Commands to add `Policy` objects into the `PolicyList` have been implemented.

Given below is the Sequence Diagram that shows how the Add Policy Command `addp` works.

![AddPolicyCommand](images/AddPolicySequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `AddPolicyCommandParser` and `AddPolicyCommand` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>

First, `ClientListParser` will parse if the correct command, `addp` in this case, is called. Then,
`AddPolicyCommandParser` will parse the parameters for  `Policy`'s name and description for their validity
and values. `AddPolicyCommandParser` then constructs an `AddPolicyCommand` with the given Policy using the
name and description. The Command object is returned to `LogicManager` which calls the execute 
method of the `Command`. Execute checks if the `Policy` already exists in the `PolicyList` stored
in Model and if it is not, then the `Policy` is added to the `PolicyList`.

A command used to clear the `PolicyList`, `clearp` was also implemented to give the user 
more control over the `PolicyList`. `clearp` clears the `PolicyList` as well as all the 
`Policy`s allocated to clients through interaction with `Model`'s `ClientList` and `PolicyList`.
The Command works similar to the Add Policy Command as illustrated in the sequence diagram
in terms of parsing and command creation, but works differently in `Model` as it clears all
`Policy` objects from the `PolicyList` as well as the `Policy` field of `Person` in
 the `ClientList`.

`PolicyList` is a List class stored in `Model`. It stores multiple `Policy` objects in a 
`HashMap`. A `HashMap` was used
because checking if a key is in the `HashMap` can be done quickly. Additionally, `HashMap` 
was chosen over `HashTable` because we do not require the Collection to be synchronized. 
Thus, `HashMap` is more apt due to its higher efficiency and speed.

Lastly, `PolicyList` is also stored in memory as `json` file. This requires json-adapted classes to be
created. The storage classes are shown in [**`Storage`**](#storage-component). They were implemented
using AB3's storage classes as template.

### Archive feature

#### Implementation

The archive mechanism is facilitated by `ModelManager` and `Person`. `Person` contains an archive status, stored internally as `isArchive`. 
`ModelManager` tracks the current viewing mode of the list of `Person`, which is either the active mode or archive mode, stored internally as `isArchiveMode`.
Additionally, `ModelManager` implements the following operations:

* `ModelManager#getIsArchiveMode()` — Gets the current archive mode.
* `ModelManager#setIsArchiveMode(...)` — Sets the current archive mode.

These operations are exposed in the `Model` interface as `Model#getIsArchiveMode()` and `Model#setIsArchiveMode(...)` respectively.

Given below is an example usage scenario and how the archive mechanism behaves at each step.

Step 1. The user launches the application for the first time. The `ModelManager` will be initialized with the active mode with `isArchiveMode` set to `false`.

For simplicity, we only show 2 example persons, `david` and `ben`, who are both not in the archive, i.e. their `isArchive` is `false`. 
The user can view both of these persons as he is in the active mode.

![Archive0](images/Archive0.png)

Step 2. The user executes `archive 2` command to archive the 2nd person (`ben`) in the client list. 
The `archive` command creates a new `Person` with `isArchive` set to `true`, then calls `Model#setPerson(...)` to update the model.

This is followed by `Model#updateFilteredPersonList(PREDICATE_SHOW_ALL_ACTIVE)` to view all the active persons, 
where `PREDICATE_SHOW_ALL_ACTIVE` is used to filter `Person`s with `isArchive` set to `false`. 

The 2nd person (`ben`) would be hidden from the user's current view, so he can only see `david`.

![Archive1](images/Archive1.png)

The following sequence diagram shows how the archive operation works:

![ArchiveSequenceDiagram](images/ArchiveSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `ArchiveCommandParser` and `ArchiveCommand` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>


Step 3. The user executes `list r/` to view the archive. 
The `list r/` command calls `ModelManager#setIsArchiveMode(true)` to set the viewing mode to the archive mode, 
followed by `Model#updateFilteredPersonList(PREDICATE_SHOW_ALL_ARCHIVE)` to view the archive.
`PREDICATE_SHOW_ALL_ARCHIVE` is used to filter `Person`s with `isArchive` set to `true`. 

This causes the user to only view archived persons, which is `ben` in this case.

![Archive2](images/Archive2.png)

The following activity diagram summarizes what happens when a user executes the archive command and list command:

![ArchiveActivityDiagram](images/ArchiveActivityDiagram.png)

#### Design consideration:

##### Potential issues with other commands and Resolutions

Since the archive is implemented using predicate filtering, it might potentially conflict with `FindCommand`, 
if it were to be implemented in future. However, this can be easily resolved using predicate composition with conjunction 
(AND) logic, a method already available in `Java`’s `Predicate`s. 

As an example, `predicate1` could be a filter for archive, and `predicate2` could be from the find command:

```java
Predicate<Person> composedPredicate = predicate1.and(predicate2);
```

##### Aspect: How archive executes

* **Alternative 1 (current choice):** Uses a variable in `Person` to track if he is in the archive.
  * Pros: Easy to implement.
  * Cons: With an additional variable in the `Person` class, we must ensure that the reading and updating of the variable is correct. 
  A `Person` would also “know” that he is archived or not, which may not be that ideal.

* **Alternative 2:** Have a separate storage for archive.
  * Pros: A more intuitive solution, and only `Storage`-related modules "knows" about the presence of an archive.
  * Cons: We must ensure that the implementation the reading and saving of the 2 different storages, and updating of the models are correct, which is time-consuming.

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

Insurance Agents - Financial Advisors

Insurance agents represent insurance companies, such as AIA and Prudential, as their sales person. They bring in 
clients and promote various insurance schemes. Their clients could be individuals or other businesses. Insurance agents’ 
mode of conducting their job generally involves talking directly to these clients in a face-to-face setting and giving 
their pitch. 

Insurance agents need to be familiar with their clients’ profile, such as their family status, financial needs and 
interests, to better promote their insurance schemes as it gives their pitch a personal touch. 

These users are likely used to typing notes and formulas to manage their clients into a tracker, for example in  Excel 
notebooks. They would add in attributes of users into the tracker, and add in additional functionalities using formulas. 
Thus, they are likely to be comfortable in using a command-line interface.

**Value proposition**: 

Remembering people like a good neighbour.

Successful Financial advisors can have more than a hundred clients. They may not be able to remember the personal 
details of all their clients at once. This product aims to provide a command-line interface for the financial agent to 
record the details of the client, from their scheme details, such as the policies they own, to their personal details, 
like their hobbies. As such, they would be better prepared for meetings and are able to provide a personal touch when 
giving their pitch.


### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a/an …                                  | I can …                        | So that I can…                                                         |
| -------- | ------------------------------------------ | ------------------------------ | ---------------------------------------------------------------------- |
| `* * *`  | regular user                               | add more clients               | handle more clients                                                    |
| `* * *`  | regular user                               | delete a client                | account for my clients who churned                                     |
|  `* *`   | first-time user                            | get some help on using the product | know how to use it                                                 |
| `* * *`  | first-time user                            | view all (active) clients      | get a general overview                                                 |
| `* * *`  | first-time user                            | load existing data that I have on user personal details |                                               |
| `* * *`  | regular user                               | save my client information     | save my edits for next time use                                        |
| `* *`    | expert user                                | compare the importance of each client | decide which client to pursue                                   |
| `* *`    | expert user                                | archive clients                | hide clients whom I am no longer working with                          |
| `* *`    | expert user                                | view all archived clients      | see the clients that I am no longer working with                       |
| `* *`    | expert user                                | unarchive clients              | display them when I start working with them again                      |
| `* *`    | regular user                               | clear all clients              | delete irrelevant client data when I move to a new company             |
| `* *`    | regular user                               | add existing policies          | keep track of policies my company offers and the policies my clients subscribe to|
| `* *`    | regular user                               | clear all policies             | delete irrelevant policy data when I move to a new company             |
| `* *`    | regular user                               | exit the app                   | start to relax                                                         | 




### Use cases

(For all use cases below, the **System** is `I4I` and the **Actor** is the `user`, unless specified otherwise)

**UC01 - User adds client**

**MSS**

1. User chooses to add new client with client details.
2. System gives success message.

    Use case ends.

**Extensions**

* 1a. User enters wrong add command format.

    * 1a1. System gives corresponding error message.

      Use case ends.

* 1b. User attempts to add clients in the archive mode.
   
    * 1b1. System gives corresponding error message.

      Use case ends.

* 1c. User attempts to add an existing client.
   
    * 1c1. System gives corresponding error message.

      Use case ends.
    
* 1d. User attempts to add a client with invalid fields.
   
    * 1d1. System gives corresponding error message.

      Use case ends.
      
* 1e. User attempts to add a client with policy name that corresponds to none of the policies in the policy list.
   
    * 1e1. System gives corresponding error message.

      Use case ends.
        
**UC02 - User deletes client**

**MSS**

1. User requests to <ins>list all active clients (UC03)</ins> or to <ins>list all archived clients (UC04)</ins>.
2. User chooses to delete particular client.
3. System gives success message.

    Use case ends.

**Extensions**

* 1a. The list is empty.

  Use case ends.

* 2a. User enters wrong delete command format.

    * 2a1. System gives corresponding error message.

      Use case ends.
      
* 2b. User enters invalid index.

    * 2b1. System gives corresponding error message.

      Use case ends.

**UC03 - User lists all active clients**

**MSS**

1. User chooses to list all active clients.
2. System shows all active clients.

    Use case ends.

**Extensions**

* 1a. User enters wrong list command format (for active clients).

    * 1a1. System gives corresponding error message.

      Use case ends.
      
**UC04 - User lists all archived clients**

**MSS**

1. User chooses to list all archived clients.
2. System shows all archived clients.

    Use case ends.

**Extensions**

* 1a. User enters wrong list command format (for archived clients).

    * 1a1. System gives corresponding error message.

      Use case ends.

**UC05 - User requests for help**

**MSS**

1. User requests for help on how to use the system.
2. System gives a guide to help user.

    Use case ends.

**Extensions**

* 1a. User enters wrong help command format.

    * 1a1. System gives corresponding error message.

      Use case ends.
      
**UC06 - User archives client**

**MSS**

1. User requests to <ins>list all active clients (UC03)</ins>.
2. User chooses to archive particular active client.
3. System gives success message.

    Use case ends.

**Extensions**

* 1a. The list is empty.

  Use case ends.

* 2a. User enters wrong archive command format.

    * 2a1. System gives corresponding error message.

      Use case ends.
      
* 2b. User enters invalid index.

    * 2b1. System gives corresponding error message.

      Use case ends.

**UC07 - User unarchives client**

**MSS**

1. User requests to <ins>list all archived clients (UC04)</ins>.
2. User chooses to unarchive particular archived client.
3. System gives success message.

    Use case ends.

**Extensions**

* 1a. The list is empty.

  Use case ends.

* 2a. User enters wrong unarchive command format.

    * 2a1. System gives corresponding error message.

      Use case ends.
      
* 2b. User enters invalid index.

    * 2b1. System gives corresponding error message.

      Use case ends.
      
**UC08 - User clears client list**

**MSS**

1. User chooses to clear the active client list and archived client list.
2. System gives success message.

    Use case ends.  
    
**Extensions**

* 1a. The list is empty.

  Use case ends.
      
**UC09 - User adds a new policy**    

**MSS**

1. User chooses to add a new policy with a name and description.
2. System gives success message.
    
    Use case ends.

**Extensions**

* 1a. User enters wrong add policy command format.

    * 1a1. System gives corresponding error message.
    
        Use case ends.
        
* 1b. User attempts to add an existing policy.

    * 1b1. System gives corresponding error message.
    
        Use case ends.

**UC10 - User clears the list of existing policy**    

**MSS**

1. User chooses to clear the policy list.
2. System gives success message.
    
    Use case ends.
    
**UC11 - User exits the app**

**MSS**

1. User chooses to exit the app.
2. System exits app and closes the interface.
  
  Use case ends.
      

### Non-Functional Requirements

1.  Should work on any _mainstream OS_ as long as it has Java `11` installed.
2.  Should be able to store up to 1000 clients.
3.  A user with above average typing speed for regular English text (i.e. not code, not system admin commands) 
should be able to accomplish most of the tasks faster using commands than using the mouse.

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, OS-X
* **Insurance4Insurance**: Name of the product.
* **I4I**: Short for Insurance4Insurance, the name of the product.
* **Active Client**: A client not in the archive.
* **Active List** / **Active Client List**: List of clients not in the archive.
* **Active Mode**: The mode which allows users to view the active list.
* **Archive Mode**: The mode which allows users to view the archive.
* **Priority Indicator**: The rectangle that shows the priority of the client in the list.

|Priority Type   |Syntax  |Picture(Unarchived)   |Picture(archived) |
|---|---|---|---|
|Undefined   |`l/undefined`,`l/u`, `l/U`   |![Undefined Priority](images/Priority Bar/UndefinedPriority.png) |![Undefined Priority](images/Priority Bar/UndefinedPriorityArchived.png) |
|Low   |`l/low`,`l/l`, `l/L`    |![Low Priority](images/Priority Bar/LowPriority.png)   |![Low Priority](images/Priority Bar/LowPriorityArchived.png) | 
|Medium   |`l/medium`,`l/m`, `l/M`    |![Medium Priority](images/Priority Bar/MediumPriority.png)   |  ![Medium Priority](images/Priority Bar/MediumPriorityArchived.png) |
|High   |`l/high`,`l/h`, `l/H`    |![High Priority](images/Priority Bar/HighPriority.png)   |![High Priority](images/Priority Bar/HighPriorityArchived.png) |
--------------------------------------------------------------------------------------------------------------------

## **Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.

<div markdown="span" class="alert alert-info">:information_source: **Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

</div>


### Launch and shutdown

1. Initial launch

   1. Download the jar file and copy into an empty folder.

   2. Launch the app by using the `java -jar` command (do not use double-clicking). Expected: Shows the GUI with a set of sample clients. The window size may not be optimum.

1. Saving window preferences

   1. Resize the window to an optimum size. Move the window to a different location. Close the window.

   2. Re-launch the app by using the `java -jar` command.<br>
      Expected: The most recent window size and location is retained.
        
 ### Listing all active clients
 
 1. Listing all active clients
 
    1. Prerequisites: At least one active client (for easier comparison).
 
    2. Test case: `list`<br>
       Expected: All active clients listed. Success message shown. "Active Client List" is shown below the success message.
       
       ![List All Active Clients](images/listAllActiveClients.png)
 
 
### Listing all archived clients
 
 1. Listing all archived clients
 
    1. Prerequisites: At least one archived client (for easier comparison). See [Archiving a Client](#archiving-a-client) for details on how to archive a client.
 
    2. Test case: `list r/`<br>
       Expected: All archived clients listed. Success message shown. "Archived Client List" is shown below the success message.
       The color of the priority indicator for each archived client is [darkened](#glossary). 
       ![List All Archived Clients](images/listAllArchivedClients.png)
       
       
### Adding a client

1. Adding a client while the active clients are being shown.
    
    1. Prerequisites: List all active clients (see [List Active Clients](#listing-all-active-clients)).
    The client is not already in the client list, i.e. a client that is different from those already in the client list should be added.
    2 clients are considered different if 
    - They have different names OR
    - They have the same name BUT have different email or phone number
    
    
    2. Test case: `add n/John Cena`<br>
    Expected: The client John Cena is added to the active list. Success message shown. Priority of the user is undefined (white rectangle)
    



### Archiving a client

1. Archiving a client while active clients are being shown

   1. Prerequisites: List all active clients (see [List Active Clients](#listing-all-active-clients)). At least one active client.

   2. Test case: `archive 1`<br>
      Expected: 
      First client is archived, and is no longer shown in the active list. Details of the archived client shown in the status message.
      Upon listing all archived clients (see [List Archived Clients](#listing-all-archived-clients)), the archived client can be seen.  
      The color of the archived client will be darkened to indicator that the client has been archived.     

   3. Test case: `archive 0`<br>
      Expected: No client is archived. Error details shown in the status message.

   4. Other incorrect archive commands to try: `archive`, `archive x`, `...` (where x is larger than the active list size)<br>
      Expected: Similar to previous.


### Unarchiving a client

1. Unarchiving a client while archived clients are being shown

   1. Prerequisites: List all archived clients (see [List Archived Clients](#listing-all-archived-clients)). At least one archived client.

   2. Test case: `unarchive 1`<br>
      Expected: 
      First client is unarchived, and is no longer shown in the archive. Details of the unarchived client shown in the status message.
      Upon listing all active clients (see [List Active Clients](#listing-all-active-clients)), the unarchived client can be seen.
      The color of the priority indicator is [lightened](#glossary) to show that the client has been unarchived.

   3. Test case: `unarchive 0`<br>
      Expected: No client is unarchived. Error details shown in the status message.

   4. Other incorrect unarchive commands to try: `unarchive`, `unarchive x`, `...` (where x is larger than the archive size)<br>
      Expected: Similar to previous.


### Deleting a client

1. Deleting a client while active clients are being shown

   1. Prerequisites: List all active clients (see [List Active Clients](#listing-all-active-clients)). At least one active client.

   2. Test case: `delete 1`<br>
      Expected: 
      First client is deleted, and is no longer shown in the active list. Details of the deleted client shown in the status message.
      
   3. Test case: `delete 0`<br>
      Expected: No client is deleted. Error details shown in the status message.

   4. Other incorrect delete commands to try: `delete`, `delete x`, `...` (where x is larger than the active list size)<br>
      Expected: Similar to previous.
   
   5. Another similar test to try: Deleting a client while archived clients are being shown

### Clearing the client list

1. Clearing the client list.

    1. Prerequisites: At least one client in active list, and another client in archive (for easier comparison).
    
    2. Test case: `clear` <br>
    Expected:
    Both the active list and the archived list are cleared. Status message will indicate that the client list has been cleared. 

### Displaying help
 
 1. Displaying help
 
    1. Prerequisites: Internet access.

    2. Test case: `help`<br>
       Expected: Success message shown. Also shows a popup explaining how to access the User Guide. The URL can be copied with "Copy URL".
       
       ![help message](images/helpMessage.png)
       
       Upon pasting the URL into the browser, the User Guide is displayed.
       
### Leaving the app
 
 1. Leaving the app
 
    1. Prerequisites: I4I must be open.

    2. Test case: `exit`<br>
       Expected: The app closes.

### Adding a policy

1. Adding a policy.
    
    1. Prerequisites: Policy to be added is not already in the policy list. 
    Two policies are considered different if they have different names.
    
    2. Test case: `addp pn/Medishield pd/Government Insurance`<br>
    Expected: The policy Medishield is added to the policy list. Success message shown. 
    
### Clearing the policy list

1. Clearing the policy list.

    1. Prerequisites: None.
    
    2. Test case: `clearp` <br>
    Expected:
    Status message will indicate that the policy list has been cleared. Policy fields of all clients are also cleared.
