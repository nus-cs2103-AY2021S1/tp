---
layout: page
title: Developer Guide
---
* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------
## **Introduction**

CAP5Buddy helps NUS SoC students to keep track of their module details efficiently. It helps them centralize key
module details and follows their study progress through a Command Line Interface (CLI) that allows efficient management
of module details. CAP5Buddy also functions as a scheduling system, todo list and contact list.

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

## **Design**

### Architecture

**How the architecture components interact with each other**
![Structure of the Overall Product](images/ArchitectureDiagram.png)

### Overall components

This is the overall design of our product. As we are using **GUI to help to display the information** and mainly focuses on
using **CLI to take in the required commands**, thus the product consists of **6 main major components**. The product starts
from the Launcher classes, that initiates based on our pre-set settings and then activates the MainApp class
the will run the GUI with these settings. MainApp will also start the _brain_ and -muscles_ of the program, which are the Logic, Storage,
Model and Ui components.

The role of the **Logic** component is to act as the _brain_ of the program, where all the parsing of information will be done, and the
execution of the commands will be carried out.

The role of the **Storage** component is to represent the _memory_ of the program, where the storing and tracking of the different items happens.
These items are saving locally in a json file, which can be imported and exported easily.

The role of the **Model** component is to represent all the items and their behaviours. Contains all the item classes and their support classes.

The role of the **Ui** component is to handle all the User interface related instructions, which includes the loading of GUI components, the updating
of these components and displaying the changes.

## Module Tracker

### UI component
![Structure of the UserInterface Component](images/UiClassDiagram.png)

The job of the UI component is to be the _face_ of the product, which the user directly interacts with.
It is in charge of containing the logic that **breaks down and executes the user input**, and displaying the **GUI** of the
product.

It composes of a few main classes, that serves as the focal point of this component. Such classes are **UiPart**,
**MainWindow**, **UiManager** and the respective panel displays, **(XYZListPanel)**. The rest of the classes are supporting
classes to help make the GUI.

The MainWindow is what the user actually sees, which has a **CommandBox**, **XYZListPanel**, **ResultDisplay** and **StatusBar**. These
components are stacking on top of one another using **stackPane** to ensure a smooth looking GUI. The order of the components
are as follows, **CommandBox**, **ResultDisplay**, **XYZListPanel** and **StatusBar**.

The **CommandBox** is just a textField component where the user can enter the commands. Upon pressing *Enter*, extracting of the
text occurs and is sent to the logic to be parsed and executed.

Next, after the executing is completed, a **CommandResult** object returns and is then passed to the **ResultDisplay** for the
relevant information to be shown in this component. This is being displayed in a TextArea component.

Lastly, the **XYZListPanel** is in charge of displaying all the modules, contacts, etc that is the product is tracking.
Each of these items are being displayed in a *cell* under their respective **XYZCard**, which will be displayed in the *ListCell*
of the **XYZListPanel**.


**API** :

### Logic component

![LogicClassDiagram](images/LogicClassDiagram.png)

**API** : `Logic.java`

1. Logic uses the `ParserManager` class to create the respective Parser classes: `ModuleListParser`, `ContactListParser`
 and `TodoListParser`. Depending on the user command, the user command will be parsed by the relevant Parser class.
2. This results in a `Command` object which is executed by `LogicManager`.
3. The command execution can affect the Model (e.g. adding a module).
4. The result of the command execution is encapsulated as a `CommandResult` object which is passed back to the `Ui`.
5. In addition, the `CommandResult` object can also instruct the `Ui` to perform certain actions, such as displaying
help to the user.

![Structure of the Storage Component](images/ModelClassDiagram.png)

### Model component

**API** : [`Model.java`](https://github.com/AY2021S1-CS2103T-F12-3/tp/blob/master/src/main/java/seedu/address/model/Model.java)

The `Model`,

* stores a `UserPref` object that represents the user’s preferences.
* stores the data for these 3 types of list:
  * module tracker
  * contact list
  * todo list
* exposes an unmodifiable `ObservableList<T>` for all types of list as mentioned above which can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* does not depend on any of the other three components

### Storage component

![Structure of the Storage Component](images/StorageClassDiagram.png)

**API** : [`Storage.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/storage/Storage.java)

The `Storage` component facilitates the storage of CAP5BUDDY data in the hard drive. When the program attempts to save
data, the `Storage` component converts java data objects such as `ModuleList` and `ContactList` into a json format to store
at a specified file location. When the program is started, it will attempt to read existing user data and the `Storage`
component will be converting data in json format into java objects.

* can save `UserPref` objects in json format and read it back.
* can save the module list data in json format and read it back.
* can save the contact list data in json format and read it back.
* can save the todo list data in json format and read it back.

### Common classes

Classes used by multiple components are in the `seedu.addressbook.commons` package.
### Common classes

**API** :

## Module List
![Structure of the Module List Component](images/ModuleListDiagram.png)

**Module package** : [`seedu.address.model.module`](https://github.com/AY2021S1-CS2103T-F12-3/tp/tree/master/src/main/java/seedu/address/model/module)

* Module is a container class that stores :
  * Name of a module
  * Zoom link of a module
  * GradeTracker of a module
* GradeTracker is a container class that stores:
  * Grade for a module
  * Assignments for a module

#### ModuleList class
**ModuleList class** : [`ModuleList.java`](https://github.com/AY2021S1-CS2103T-F12-3/tp/blob/master/src/main/java/seedu/address/model/ModuleList.java)

* Wraps all data i.e. Modules at the module list level
* Stores Modules in memory
* Stores a UniqueModuleList
* Duplicate Modules are not allowed

## CAP Calculator

## Scheduler

## Contact List

![Structure of the Contact List Component](images/ContactListDiagram.png)

#### Contact class

**Contact package** : [`seedu.address.model.contact`](https://github.com/AY2021S1-CS2103T-F12-3/tp/tree/master/src/main/java/seedu/address/model/contact)

* Contact is a container class that stores :
  * Name of a contact
  * Email of a contact
  * Telegram of a contact
#### ContactList class
**ContactList class** : [`ContactList.java`](https://github.com/AY2021S1-CS2103T-F12-3/tp/blob/master/src/main/java/seedu/address/model/ContactList.java)

* Wraps all data i.e. Contacts at the contact list level
* Stores Contacts in memory
* Stores a UniqueContactList
* Duplicate Contacts are not allowed

## Todo List

![Structure of the Todo List Component](images/TodoList/TodoListClassDiagram.png)

#### Task class

**Task package** : [`seedu.address.model.task`](https://github.com/AY2021S1-CS2103T-F12-3/tp/tree/master/src/main/java/seedu/address/model/task)

* Task is a container class that stores :
  * Name of a task
  * Tags of a task
  * Priority of a task
  * Date or deadline of a task
  * Status of a task<br/>
  Only name is compulsory when creating a new Task.

#### TodoList class

**TodoList class** : [`TodoList.java`](https://github.com/AY2021S1-CS2103T-F12-3/tp/blob/master/src/main/java/seedu/address/model/TodoList.java)

* Wraps all data i.e. Tasks at the Todo List level
* Stores Tasks in memory
* Stores a UniqueTodoList
* Duplicate Task objects are now allowed

TodoList will be explained more comprehensively in the [TodoList feature](#33-todolist-feature) Section

--------------------------------------------------------------------------------------------------------------------

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

<<<<<<< HEAD
### \[Proposed\] Add Event feature
![Structure of the Add Event command](images/AddEventSequenceDiagram.png)
#### Proposed Implementation
The idea of this feature is to be able to allow the user to keep track of his/her current events that
will be happening. Events can be either a one time event like an exam for a particular module, or a recurring
event like a weekly tutorial class.

How we are currently implementing this feature is by following the same implementation as the AB3. We have an event
object under the Model package. Two classes called EventName and EventTime act as information containers to store
the respective data and help support the Event class.
=======

## 3.1 Module list management features

### Basic Module Tracker features

#### Add Module feature

This feature creates and adds a new `Module` into the `ModuleList` if the contact does not already exist. 

This feature is facilitated by the following classes:

 * `AddModuleParser`:
   * It implements `AddModuleParser#parse()` to parse and validate the user arguments to create a new `Module`.

 * `AddModuleCommand`:
   * It implements `AddModuleCommand#execute()` which executes the addition of the new `Module` into `Model`.

Given below is an example usage scenario and how the mechanism for adding module behaves at each step:
Step 1. `LogicManager` receives the user input `addmodule n/CS2100 mc/4.0 t/Coremodule ` from `Ui`
Step 2. `LogicManager` calls `ModuleListParser#parseCommand()` to create an `AddModuleParser`
Step 3. Additionally, `ModuleListParser` will call the `AddModuleParser#parse()` method to parse the command arguments
Step 4. This creates an `AddModuleCommand` and `AddModuleCommand#execute()` will be invoked by `LogicManager` to execute the command to add the `Module`
Step 5. The `Model#addModule()` operation exposed in the `Model` interface is invoked to add the new `Module`
Step 6. A `CommandResult` from the command execution is returned to `LogicManager`

Given below is the sequence diagram of how the operation to add a `Module` works:
![AddModuleSequenceDiagram](images/Module/AddModuleSequenceDiagram.png)
Figure 3.1.1.1 Sequence diagram for the execution of `AddModuleCommand`

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `AddModuleCommand` and `AddModuleParser` should end 
at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>

The add completed module feature serves a very similar function — it calls `Model#addModule()` also but creates a `Module` that contains the "completed" tag by default instead.

#### Delete Module Feature

The delete module feature deletes a pre-existing `module` using the index of the `Module` on the displayed `ModuleList`.
This feature is facilitated by the following classes: 

  * `DeleteModuleParser`:
    * It implements `DeleteModuleParser#parse()` to parse and validate the `Module` ID

  * `DeleteModuleCommand`:
    * It implements `DeleteModuleCommand#execute()` to delete the `Module` from `Model`

After the user input has been parsed by `DeleteModuleParser`, `LogicManager` will execute the delete operation by invoking
`DeleteModuleCommand#execute()`. This deletes the target `Module` by invoking the `Model#deleteModule()` method exposed in the `Model` interface.

Given below is the sequence diagram of how the operation to delete a `Module` works:
![DeleteModuleSequenceDiagram](images/Module/DeleteModuleCommandSequenceDiagram.png)

#### Design consideration:

##### Aspect: Method to delete module

* **Alternative 1 (current choice):** Delete a `Module` based on its index in the displayed `ModuleList`
  * Pros: Using the `Module` index allows us to uniquely identify the target `Module` to delete, reducing the room for possible error
  * Cons: The target `Module` to be deleted might not be displayed on the `ModuleList` and hence the `Module` index might not be
          readily available. This can inconvenience users who have to search for the `Module` to retrieve the `Module` index.

* **Alternative 2:** Delete a `Module` based on the `Module` name
  * Pros: It can make the deletion process simpler for **users** who can provide the name of the `Module` without having to execute more commands.
  * Cons: This is more difficult to implement.

Alternative 1 was chosen since it is easier to implement and it makes the command simpler for users to input.


#### Edit Module Feature

The edit module feature edits a pre-existing `Module` in the `ModuleList` using `Module` details provided by the user.
This feature is facilitated by the following classes:

  * `EditModuleParser`: 
    * It implements `EditModuleParser#parse()` to parse and validate the provided `Module` details and `Module` index

  * `EditModuleDescriptor`:
    * It stores the `Module` details which will be used to edit the target `Module`

  * `EditModuleCommand`:
    * It implements `EditModuleCommand#execute()` to edit the contact in `Model`


Given below is an example usage scenario and how the mechanism for editing a `Module` behaves at each step:
Step 1. `LogicManager` receives the user input `editmodule 1 n/CS2100 mc/4.0 gp/5.0 t/Coremodule ` from `Ui`
Step 2. `LogicManager` calls `ModuleListParser#parseCommand()` to create an `EditModuleParser`
Step 3. Additionally, `ModuleListParser` will call the `EditModuleParser#parse()` method to parse the command arguments
Step 4. This creates an `EditModuleCommand` and `EditModuleCommand#execute()` will be invoked by `LogicManager` to edit the target `Module`
Step 5. The `Model#setModule()` operation exposed in the `Model` interface is invoked to replace the target `Module` with the edited `Module`
Step 6. A `CommandResult` from the command execution is returned to `LogicManager`

Given below is the sequence diagram of how the operation to edit a `Module` works:
![EditModuleSequenceDiagram](images/Module/EditModuleCommandSequenceDiagram.png)


#### Design consideration:

##### Aspect: Implementation of `EditModuleCommand`

* **Alternative 1 (current choice):** 
  * Pros: Reduces coupling between the command classes and `EditModuleCommand` can be implemented without restrictions,
          or a need to consider how it might affect the other command classes
  * Cons: Additional methods have to be implemented to replace the target module with the edited module

* **Alternative 2:** Reuse `DeleteModuleCommand` to delete the target `Module` and `AddModuleCommand` to add the edited contact
  * Pros: Reusing other commands would make the implementation of `EditModuleCommand` simpler and easier
  * Cons: It increases coupling between the 3 commands and this can cause issues in `EditModuleCommand` if either 
          `DeleteModuleCommand` or `AddModuleCommand` developed bugs or errors. Also, it might affect performance since 
          executing `EditModuleCommand` will execute 2 other commands.

Alternative 1 was chosen since it gave more freedom with regard to the implementation of `EditModuleCommand` since
we were not restricted to reusing other commands. Less coupling between the classes meant that changes in one class would 
less likely require changes to other classes.


#### Find Module Feature

The find `Module` feature is important since sieving through all modules to search for a specific `Module` can be 
tedious and not user-friendly.

The find `Module` feature searches for modules using the `Module` name.
For each search parameter, modules have to match at least one keyword to fulfil the search criteria.

This feature is facilitated by the following classes:

  * `FindModuleParser`:
    * It implements `FindModuleParser#parse()` to parse and validate the user input
    * It creates `NameContainsKeywordsPredicate` objects using the command arguments
   
  * `FindContactCommand`:
    * It implements `FindModuleCommand#execute()` to find all matching modules by updating the 
      filtered displayed module list in `Model` using the `NameContainsKeywordsPredicate` from `FindModuleParser`

Given below is an example usage scenario and how the mechanism for finding `Module` behaves at each step:
Step 1. `LogicManager` receives the user input `findcontact n/CS2100` from `Ui`
Step 2. `LogicManager` calls `ModuleListParser#parseCommand()` to create a `FindModuleParser`
Step 3. Additionally, `ModuleListParser` will call the `FindModuleParser#parse()` method to parse the command arguments
Step 4. This creates a `NameContainsKeywordsPredicate` that will be used to obtain the filtered displayed `ModuleList`
Step 4. Additionally, a `FindModuleCommand` is created and `FindModuleCommand#execute()` will be invoked by `LogicManager` to find matching modules
Step 5. The `Model#updateFilteredModuleList()` operation exposed in the `Model` interface is invoked to update the displayed `ModuleList`
        using `NameContainsKeywordsPredicate`
Step 6. A `CommandResult` from the command execution is returned to `LogicManager`

Given below is the sequence diagram of how the operation to find modules works:
![FindModuleCommandSequenceDiagram](images/Module/FindModuleCommandSequenceDiagram.png)
Fig ??

#### Module list data archiving

##### Implementation

The module list data archiving function is facilitated by `ModelManager`. It keeps track of a additional `ModuleList` which stores archived modules as
compared the the current `ModuleList` that stores currently relevant modules. Additionally, it implements the following operations:

* `ModelManager#archiveModule()` - Archives a module by removing it from the current `ModuleList` and placing it in the archived `ModuleList`.

* `ModelManager#unarchiveModule()` - Un-archives a module by removing it from the archived `ModuleList` and placing it in the current `ModuleList`.

The following sequence diagram shows how the archive module operation works:
![ArchiveModuleSequenceDiagram](images/Module/ArchiveModuleSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `ArchiveModuleCommand`
should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>

The `unarchivemodule` command does the opposite — it calls `Model#unarchiveModule()`, which removes the specified module  from the archived `ModuleList` and placing it in the current `ModuleList`.


### Module Assignment 
In order for CAP 5 Buddy to properly support the students study, information of the students grades assignments and results should be kept.
This would allow the student to adequately assess the current grades that he or she currently has. With knowledge of the grades already achieved for the module
CAP 5 Buddy can calculate the current percentage and results for the student so that the student can understand how close he or she
is to their next grade.

The section below provides details on the implementation of each assignment related function and design considerations of these features.

### Details of implementation

The model below shows the implementation of the `GradeTracker` that is stored under the `Module` class.
Each `Module` can only have one `GradeTracker` which manages the assignments under that module.
The `GradeTracker` stores a `UniqueAssignmentList` that ensures assignments within the list are not duplicates of each other.
Each `Assignment` contains the following three fields: an `AssignmentName`, `AssignmentPercentage` and `AssignmentResult`.

![Structure of the Grade Tracker Component](images/GradeTrackerDiagram.png)

The list of all `GradeTracker` related features are:
1. Add an Assignment: Adds a new assignment to the `GradeTracker`.
2. Edit an Assignment: Edits a pre-existing assignment in the `GradeTracker`.
3. Delete an Assignment: Deletes a pre-existing assignment in the `GradeTracker`.
4. Add a Grade: Adds a grade for the overall module.

#### Add Assignment Feature

This feature creates and adds a new `Assignment` to the `GradeTracker` of a `Module`. This action
is only allowed if the `Assignment` does not already exist in the `GradeTracker`.

This feature is facilitated by the following classes:

* `AddAssignmentParser`:
  * It implements `AddAssignmentParser#parse()` to validate and parse the module name and assignment details.
* `AddAssignmentCommand`:
  * It implements `AddAssignmentCommand#execute()` which executes the creation of the `Assignment` and adds the
  assignment to the module identified by the `ModuleName` that was parsed.

When an `assignment` is added, it follows the sequence diagram as shown below. The sequence flows similarly 
to the rest of the project as the command is parsed and then executed.

![Sequence Diagram of the Add Assignment Command](images/AddAssignmentSequenceDiagram.png)

Given below is an example usage scenario and how the mechanism for adding an `Assignment` behaves at each step:

Step 1. `LogicManager` receives the user input `addassignment n/CS2100 a/Quiz 1 %/20 r/85` from `Ui`

Step 2. `LogicManager` calls `GradeTrackerParser#parseCommand()` to create a `AddAssignmentParser`

Step 3. Additionally, `AddAssignmentParser` will call the `AddAssignmentParser#parse()` method to parse the command arguments

Step 4. An `AddAssignmentCommand` is created and the command arguments are passed to it.

Step 5.  `AddAssignmentCommand#execute()` will be evoked by `LogicManager` to creates an `Assignment` using the parsed inputs, `Quiz 1` for `AssignmentName`, `20` for `AssignmentPercentage`
and `85` for `AssignmentResult`. A `ModuleName` is also created using the input `CS2100`.

Step 6. The `Module` is searched for through the `Model#getFilteredModuleList()` and when it is found, the
`Module#addAssignment()` is executed with the `Assignment`, adding the assignment to the module's `GradeTracker`.

Step 7. A `CommandResult` from the command execution is returned to `LogicManager`

#### Design consideration:

##### Aspect: Whether to directly store the assignments under module
* Alternative 1 : Module stores assignments directly without any association class.
    * Pros : Less work to be done.
    * Cons : Less OOP.
    
* Alternative 2 (current choice): Module stores a separate class that then stores the assignments
    * Pros : More OOP and the assignments are less coupled to the Module.
    * Cons : Takes more effort and complexity to recreate the unique object list within another layer(`Module`).
    
We implemented the second option despite its difficulty and complexity, taking more time to carry out as we felt
that this feature was major enough to warrant the time and depth to implement.

####Edit Assignment Feature

This feature allows `assignments` within a `GradeTracker` to be edited. The fields that can be edited are the
`AssignmentName`, `AssignmentPercentage` and its `AssignmentResult`. The grade tracker of the module to act on must
currently have a valid assignment to target.

This feature requires the following classes:

* `EditAssignmentDescriptor`:
  * It represents and encapsulates the edited assignment and stores the fields to replace the current ones.
* `EditAssignmentParser`:
  * It implements `EditAssignmentParser#parse()` to validate and parse the assignment `Index`, module name and assignment
  edited details, creating an `EditAssignmentDescriptor` object with the edited details.
* `EditAssignmentCommand`:
  * It implements `EditAssignmentCommand#execute()` which will execute the editing of the assignment at the corresponding
  assignment `Index` in the corresponding `Module` identified by the parsed module name.

Given below is an example usage scenario and how the mechanism for editing an `Assignment` behaves at each step:

Step 1. `LogicManager` receives the user input `editassignment 1 n/CS2100 a/Quiz 1` from `Ui`

Step 2. `LogicManager` calls `GradeTrackerParser#parseCommand()` to create a `EditAssignmentParser`

Step 3. Additionally, `EditAssignmentParser` will call the `EditAssignmentParser#parse()` method to parse the command arguments

Step 4. An `EditAssignmentCommand` is created and the command arguments are passed to it.

Step 5. `EditAssignmentCommand#execute()` will be evoked by `LogicManager` to creates an `EditAssignmentDescriptor`
using the parsed inputs, `Quiz 1` for `AssignmentName`. A `ModuleName` is also created using the input `CS2100`.

Step 6. The `Module` is searched for through the `Model#getFilteredModuleList()` and when it is found, the
`Module#setAssignment()` is executed with the `Assignment`, adding the assignment to the module's `GradeTracker`.

Step 7. A `CommandResult` from the command execution is returned to `LogicManager`

#### Design consideration:

##### Aspect: Whether to directly store the assignments under module
* Alternative 1 : Module stores assignments directly without any association class.
    * Pros : Less work to be done.
    * Cons : Less OOP.
    
* Alternative 2 (current choice): Module stores a separate class that then stores the assignments
    * Pros : More OOP and the assignments are less coupled to the Module.
    * Cons : Takes more effort and complexity to recreate the unique object list within another layer(`Module`).
    
We implemented the second option despite its difficulty and complexity, taking more time to carry out as we felt
that this feature was major enough to warrant the time and depth to implement.

### Cap Calculator

#### Calculate CAP Feature

The calculate CAP function is facilitated by `CalculateCapCommand`. It extends Command with a counter for total
grade points and modular credits, both stored internally `gradePoints` and `modularCredits` respectively. Additionally, it implements the following operations:

* `CalculateCapCommand#calculateCap()` - Calculates CAP using data from modules tagged as completed in current `ModuleList` and archived `ModuleList`.
>>>>>>> 15461e0e8d7b806ec82290e22af42376c52be749

We also make sure in the Logic package, there are personal sub-parsers for each of the existing Event
related commands, and an overall Parser known as SchedulerParser that is in charge of managing all of the
sub-parsers of the Scheduler.

Each of the commands of the Scheduler will always return a CommandResult class, that is basically an information
container that stores all the relevant data of the results. This CommandResult object is then passes back up to the
UiManager, where it is then passed to the GUI components for it to be displayed.

#### Design consideration:

##### Aspect: Whether to create a new Parser for Scheduler.
Option 1 **(Current implementation)**: A custom Parser in charge of all **Scheduler** related commands **only**.
Pros:
- More OOP orientated.
- More defensive programming.
Cons:
- More Parsers to handle by the ParserManager

Option 2: Place the Scheduler related parser together with the rest of the other parsers for other features, like module list, etc.
Pros:
- Faster to implement.
- Less effort needed, simply add on to the existing Parser.
Cons:
- Mess and less readible, hard to distinguish between differnt commands.
- Higher chance of errors, as we are mixing all the different parsers for every feature into a single Parser.
- LONG methods.


### \[Proposed\] Data archiving

### 1.1 Contact List Management

As a module tracking system, Cap 5 Buddy allows users to manage a list of module-related contacts with ease.

The section below provides details of the implementation of each Contact List function and design considerations
of the contact list feature.

#### 1.1.1 Contact List Commands

Below is a list of all `Contact` related features:

1. Add a contact: Adds a new contact into the contact list
2. Delete a contact: Deletes a pre-existing contact from the contact list
3. Edit a contact: Edits a pre-existing contact in the contact list
4. View all contacts: Lists out all contacts in the contact list

Given below is the class diagram of the `Contact` class:

![ContactClassDiagram](images/Contact/ContactClassDiagram.png)

Figure ?.? Class Diagram for Contact class

#### 1.1.2 Details of implementation

Given below is an example usage scenario and how the mechanism for adding contact behaves at each step:
1. `LogicManager` receives the user input `addcontact n/John e/john@gmail.com te/@johndoe` from `Ui`
2. `LogicManager` calls `ContactListParser#parseCommand()` to create `AddContactParser`
3. `ContactListParser` will call the respective `AddContactParser#parse()` method to parse the command arguments
4. This creates a `AddContactCommand` and `AddContactCommand#execute` will be invoked by `LogicManager`
5. The `Model#addContact()` operation exposed in the `Model` interface is used to add the new contact
6. A `CommandResult` from the command execution is returned to `LogicManager`

Given below is the sequence diagram of how the operation to add a contact works:
![AddContactSequenceDiagram](images/Contact/AddContactSequenceDiagram.png)
Figure ?.? Sequence diagram for the execution of `AddContactCommand`

The section below describes the implementation details of each Contact List feature.

####Add Contact Feature
* This feature creates and adds a new `Contact` using the contact details provided by users
* `ContactListParser` invokes `AddContactParser#parse()` to parse and validate the command arguments
* `AddContactCommand#execute()` will be called to add the new `Contact` if the contact does not already exist
* The mechanism to add a contact is facilitated by `Contactlist` which implements `ContactList#addContact()`
* This operation is exposed in the `Model` interface as `Model#addContact()`

The following activity diagram summarizes what happens when a user executes the `AddContactCommand`:
![AddContactCommandActivityDiagram](images/Contact/AddContactCommandActivityDiagram.png)
Figure ?.? Activity diagram representing the execution of `AddContactCommand`

#### Delete Contact Feature
* This feature deletes a pre-existing `Contact` using the contact ID provided by users
* `ContactListParser` invokes `DeleteContactParser#parse()` to parse and validate the contact ID
* `DeleteContactCommand#execute()` will be called to delete the `Contact`
* The mechanism to delete a contact is facilitated by `ContactList` which implements `ContactList#removeContact()`
* This operation is exposed in the `Model` interface as `Model#deleteContact()`

#### Edit Contact Feature
* This feature edits a pre-existing `Contact` using the contact details provided by users.
* `ContactListParser` invokes `EditContactParser#parse()` to parse and validate the contact ID and command arguments
* `EditContactCommand#execute()` will be called to create the edited `Contact` and replace the old contact with the edited contact,
   if the edited contact does not already exist
* The mechanism to edit a contact is facilitated by `ContactList` which implements `ContactList#setContact()`
* This operation is exposed in the `Model` interface as `Model#setContact()`

#### View Contact Feature


#### 1.1.2 Design Considerations <br>
##### Aspect: Data structure to support Contact related functions
* Alternative 1: Use a `HashMap` to store contacts
  * Pros: Will be more efficient to retrieve contacts from a HashMap.
  * Cons: Requires additional memory to support the HashMap. This would worsen as the number of contacts stored increases.
* Alternative 2: Use an `ArrayList` to store contacts


### \[Proposed\] Calculate CAP feature

#### Proposed Implementation

The proposed calculate CAP function is facilitated by `CalculateCapCommand`. It extends Command with a counter for total
grade points and modular credits, both stored internally `gradePoints` and `modularCredits` respectively. Additionally, it implements the following operations:

* `CalculateCapCommand#accumulate(ModuleList)` - Loops through a given `ModuleList` and updates the grade points and
modular credits count accordingly.

* `CalculateCapCommand#calculateCap()` - Calculates CAP based the grade points and modular credits counter.

The following sequence diagram shows how the calculate cap operation works:
![CalculateCapSequenceDiagram](images/CalculateCapSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `CalculateCapCommand`
should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>

#### Design consideration:

##### Aspect: Information used to calculate cap
* Alternative 1 (current choice): Calculates based on academic information on mods tagged as completed.
    * Pros : Easy to implement
    * Cons : User has to manually input every module taken

* Alternative 2 : Prompts user for academic information used for last calculated cap and stores it.
    * Pros :
     * User does not need to input unnecessary modules.
     * Will use less memory.(e.g Modules that the user is not currently taking does not need to be added by user).
    * Cons : Will require additional storage.

### TodoList feature

#### Implementation

The TodoList feature has two main component :

* **Containee component** (Task-related classes)
  * `Class Task` - container class to store information about a task
  * `Class TaskName` - wrapper class to store the name of a task
  * `Class Date` - wrapper class to store the date/deadline of a task
  * `Enum Priority` - enum class to represent priority of a task
  * `Enum Status` - enum class to represent the progress status of a task

* **Container component** (List-like classes)
  * Class `UniqueTodoList` - container class for storing tasks
  * Class `TodoList` - wrapper class for UniqueTodoList
  * Interface `ReadOnlyTodoList` - interface for displaying the list on the GUI

##### Containee Component

The Task class mainly functions as a class to store all the informations related to a task i.e. name, tag, priority,
date, and status. It does not have any subclasses.

The Task class supports the following operations :

* Setters for all the field
* Getters for all the field
* `Task#isSameTask()` - checks if two tasks are the same i.e. have the same name
(weaker than Task#equals() which requires all the fields to be the same)
* `Task#hasSameTag()` - checks if the task has the specified tag
* `Task#hasSamePriority()` - checks if the task has the specified priority
* `Task#hasSameDate()` - checks if the task has the specified date

##### Container Component

The TodoList class is facilitated by UniqueTodoList. The UniqueTodoList is stored internally inside
the TodoList class which act like a wrapper class.

The TodoList class supports the following operations :

* `TodoList#resetData()` - replaces all data in TodoList with new data.
* `TodoList#hasTask()` - checks if the specified task exist in the list.
* `TodoList#addTask()` - adds a task to the list.
* `TodoList#setTask()` - replaces a task with the specified task.
* `TodoList#removeTask()` - removes the specified task from the list.

The operations above are exposed in the Model interface as :

* `Model#hasTask()`
* `Model#addTask()`
* `Model#setTask()`
* `Model#deleteTask()`

TodoList implements ReadOnlyTodoList which require the following operation :

* `ReadOnlyTodoList#getTodoList()` - returns an ObservableList with type Task that is immutable, and we cannot
  modify the elements.

#### Design Consideration

##### Aspect: Task type

* Alternative 1 (current): <br/>
  Use one concrete class i.e. Task without inheritance involved. The type of the task
  is represented by the Tag field instead.

  Pros :
  * Easier to implement
  * Types are not pre-defined i.e. can simply add a different tag to represent different type of task

  Cons :
  * All type of task have the same pre-defined field

* Alternative 2 : <br/>
  Use one abstract class i.e. Task with inheritance. Each subclasses represent a type of a Task.

  Pros :
  * Difference between type are clear and standardized
  * Can be considered more OOP

  Cons :
  * Types must be pre-defined i.e. cannot add new type of classes without adding codes

  Alternative 1 is chosen since we prioritize user freedom to create custom type for the task.


### \[Proposed\] GradeTracker feature

#### Proposed Implementation
<<<<<<< HEAD
=======
The idea of this feature is to be able to allow the user to keep track of his/her current events that
will be happening. Events can be either a one time event like an exam for a particular module, or a recurring
event like a weekly tutorial class.

How we are currently implementing this feature is by following the same implementation as the AB3. We have an event
object under the Model package. Two classes called EventName and EventTime act as information containers to store
the respective data and help support the Event class.

We also make sure in the Logic package, there are personal sub-parsers for each of the existing Event
related commands, and an overall Parser known as SchedulerParser that is in charge of managing all of the
sub-parsers of the Scheduler. 

Each of the commands of the Scheduler will always return a CommandResult class, that is basically an information
container that stores all the relevant data of the results. This CommandResult object is then passes back up to the
UiManager, where it is then passed to the GUI components for it to be displayed.

#### Design consideration:

##### Aspect: Whether to create a new Parser for Scheduler.
Option 1 **(Current implementation)**: A custom Parser in charge of all **Scheduler** related commands **only**.
Pros: 
- More OOP orientated.
- More defensive programming.
Cons:
- More Parsers to handle by the ParserManager

Option 2: Place the Scheduler related parser together with the rest of the other parsers for other features, like module list, etc.
Pros:
- Faster to implement.
- Less effort needed, simply add on to the existing Parser.
Cons:
- Mess and less readable, hard to distinguish between different commands.
- Higher chance of errors, as we are mixing all the different parsers for every feature into a single Parser.
- LONG methods.

### Add Event Feature

#### Implementation
The way this feature is currently implemented is similar to that of AB3. In the `Logic` component, we are using a specialised parser called
`SchedulerParser` that is currently set to handle all event related commands called by the user. This parser will activate the *_AddEventParser_*
that works similarly to AB3, returning the `AddEventCommand` that is executed in the `LogicManager`. Similar to AB3 and Module Tracker, the addevent
command will create the event based on the given user input and add that to the `EventList` to be stored.

This command is being facilitated by two supporting classes, `EventName` and `EventTime`. `EventName` is a logic container that contains the name of the
event to be created, and `EventTime` is the logic container for holding the date and time set by the user, as a LocalDateTime object. These two
classes are used in the creation of an `Event`, as the `Event` Object will take in a `EventName` and `EventTime`.

This is how Add Event works:<br>
Step 1. `LogicManager` takes the user input from the `UiManager` and checks the command word to decide which parser to pass onto.<br>
Step 2. `ParserManager` then selects `SchedulerParser` based on the command word.<br>
Step 3. `SchedulerParser` takes the user input and separate the command word from the arguments.<br>
Step 4. Based on the command word, the switch case selects the `AddEventParser` and passes the arguments into the parser.<br>
Step 5. `AddEventParser` then uses the `ArgumentTokenizer` to break down the arguments by the Prefixes and returns them in a `ArgumentMultiMap`.<br>
Step 6. Information under the name, date and tag prefixes are pulled out and checked for any invalid and null values,
if any are present, an `ParseException` is thrown.<br>
Step 7. Once all the relevant information is parsed, the respective supporting objects are created such as `EventName`, `EventTime` and `Tag`.<br>
Step 8. The supporting objects are used to create the new `Event` that passes to the `AddEventCommand` constructor.<br>
Step 9. `LogicManager` receives the newly created `AddEventCommand` and executes it.<br>
Step 10. The execute method of `AddEventCommand` takes in the current model and adds the `Event` to the eventlist of the model.<br>

### Delete Event Feature

#### Implementation
The implementation is similar to that of AB3 and of Add Event as mentioned previously. The main difference is the arguments that
delete event takes in. Delete event will take in just one parameter, which is the index of the event based on the eventlist shown in the
GUI.

This is how Delete Event works:<br>
Step 1. `LogicManager` takes the user input from the `UiManager` and checks the command word to decide which parser to pass onto.<br>
Step 2. `ParserManager` then selects `SchedulerParser` based on the command word.<br>
Step 3. `SchedulerParser` takes the user input and separate the command word from the arguments.<br>
Step 4. Based on the command word, the switch case selects the `DeleteEventParser` and passes the arguments into the parser.<br>
Step 5. `DeleteEventParser` then uses the `ArgumentTokenizer` to break down the arguments by the Prefixes and returns them in a `ArgumentMultiMap`.<br>
Step 6. The value of the index is pulled out and checked for any invalid (non-positive integers) and null values,
if any are present, an `ParseException` is thrown.<br>
Step 7. The value parsed is used to create an `Index` that represents the value.<br>
Step 8. The `Index` passes to the `DeleteEventCommand` constructor.<br>
Step 9. `LogicManager` receives the newly created `DeleteEventCommand` and executes it.<br>
Step 10. The execute method of `DeleteEventCommand` takes in the current model and removes the `Event` from the eventlist of the model.<br>

### Edit Event Feature

#### Implementation
Just like in AB3, this feature is supported by a static inner class `EditEventDescriptor` which serves as a logic container to
hold all the information fields that needs to be changed in the target event.

`EditEventDescriptor` is used to inform the later command of what needs to be changed and what will remain tha same. It is designed
using `Optional` where if the user did not input a new value for a parameter, then it will be an empty optinal object. Later on
in the execution of the command, when the value in the descriptor is empty, then it will take the original value in the target event.

The `EditEventCommand` also has a method called `createEditedEvent` which will help to make the newly update `Event`. It takes in the target
event object and the descriptor that holds the changes to be made. How this works is that it will check for each field in event, if there exist
a new value for that field. If there is, then the new field will be used to create the `Event`. For instance, if there is a new name for the event,
then a new `EventName` will be created based on the new name and the new `Event` will be created with thi new `EventName`.

This is how Edit Event works:<br>
Step 1. `LogicManager` takes the user input from the `UiManager` and checks the command word to decide which parser to pass onto.<br>
Step 2. `ParserManager` then selects `SchedulerParser` based on the command word.<br>
Step 3. `SchedulerParser` takes the user input and separate the command word from the arguments.<br>
Step 4. Based on the command word, the switch case selects the `EditEventParser` and passes the arguments into the parser.<br>
Step 5. `EditEventParser` then uses the `ArgumentTokenizer` to break down the arguments by the Prefixes and returns them in a `ArgumentMultiMap`.<br>
Step 6. The values under the preamble, name, date and tag prefixes are extracted out and checked for any invalid (non-positive integers) and null values,
if any are present, an `ParseException` is thrown.<br>
Step 7. The values parsed are used to create the supporting classes, `Index`, `EventName`, `EventTime` and `Tag`.<br>
Step 8. These supporting classes will be passed into the descriptor object using the respective `set` methods.<br>
Step 9. The descriptor and index passes along to the `EditEventCommand` constructor.<br>
Step 10. `LogicManager` receives the newly created `EditEventCommand` and executes it.<br>
Step 11. The execute method of `EditEventCommand` takes in the current model and calls the `createEditedEvent` methods as mentioned above, and
replaces the target event with the new `Event` that is created.<br>

### Find Event Feature

#### Implementation
Same as the previously mentioned features, the Find Event feature is similar as well. The key difference is that all of the
parameters are optional but at least one must be present. The Find Event feature will search through the EventList and return a
new filtered list that contains all the events that matches the given keywords.

This feature is supported by four classes:
 * `FindEventCritera` is used as a logic container to hold all the predicates that are going to be entered into the find command.
 * `EventNameContainsKeyWordsPredicate` is used to create the predicate that returns true for all events that contains any of the keywords in their `EventName` field.
 * `EventContainsDatePredicate` is used to create the predicate that returns true for all events that have the exact same date and time as entered by the user.
 * `EventContainsTagPredicate` is used to create the predicate that returns true for all events that have the same `Tag` under them.

This is how Find Event works:<br>
Step 1. Similar to the other features mentioned previously.<br>
Step 2. Similar to the other features mentioned previously.<br>
Step 3. Similar to the other features mentioned previously.<br>
Step 4. Similar to the other features mentioned previously.<br>
Step 5. Similar to the other features mentioned previously.<br>
Step 6. The values parsed by the `ArgumentTokenizer` are extracted out and used to create the supporting classes, `EventNameContainsKeyWordsPredicate`,
`EventNameContainsKeyWordsPredicate`, `EventContainsDatePredicate` and `EventContainsTagPredicate`.<br>
Step 7. `FindEventCriteria` is created and all the predicates will be added to it using the add method.<br>
Step 8. The `FindEventCriteria` is then passed to the `FindEventCommand` constructor.<br>
Step 9. The `LogicManager` receives the `FindEventCommand` and executes it.<br>
Step 10. The execute method of `FindEventCommand` will pass all the predicates to the filtered list.<br>
Step 11. The filtered list will take in the predicates and filters out the events that passes the predicates and update the
`FilteredList<Event>`, which will be displayed in the GUI.<br>


### Calendar GUI Feature
![Calendar](images/CalendarView.png)
#### Implementation  
The Calendar is added to help reflect the Eventlist in the model. It generates an accurate monthly Calendar based on the current date
and time of the user. This Calendar feature has 2 main parts, the FXML design and the GUI-component class, `Calendar`.

The design of the Calender mainly consists of 2 Gridpanes, one for the header and the other is used to form the days in each month. The header Gridpane
is a 1 x 1 grid, that contains a Label for the month and year, and two buttons for the users to cycle between the months in the Calender.
The second Gridpane is a 7 x 7 grid. The first row is reserved for headers for the different days of the week, while the rest are used
for filling up the days based on the month and year.

The logic of the Calender is located in the `Calendar` under the Ui component. It contains methods to load the month based on the date of the header, a method to scan
through the eventlist in model to identify the days of the events in the eventlist, and the methods for the buttons to switch between the next month and previous month.
The constructor of the Calendar takes in the `ReadOnlyEventList` from model, so that it can check for the dates that need to be marked out. The constructor also
loads in the values for `headerMonth`, `headerYear` and `now`. The `headerMonth` and `headerYear` is used to track which month and year to display in the Calendar, while the `now` tracks the
current month and year to start the Calendar in each time it loads up.

Each time the user presses any of the two buttons, depending on which, the method for handling the buttons will either add or minus a month from the `headerMonth`. This updates the
Calendar on which month to display. Also, the method will check if the current is either the start or end on the year, so that it knows when to change the values of the year.

Next, the method that loads the Calendar will pull the values of `headerMonth` and `headerYear`. It will check if the month is Feburary, and if so, it will check if the
year is a leap year. Then, it will return the appropriate number of days for the `headerMonth`. Then, it checks what is the day of the week that the first day of
this month is on, then it starts to fill up the grid starting from the first day of the week. A VBox is created for each grid cell, and a Label is created and added
to that VBox with the correct value of the dates. If there is an event that falls on the date, it the colour of the VBox will be set to a different color
to indicate an existing event.
  
##General Features

### Undo/redo feature

#### Implementation
The undo/redo mechanism is facilitated by the respective versioned lists of each list type. For example
`VersionedModuleList` for a `ModuleList` type. We will use `VersionedModuleList` to demonstrate the implementation of undo/redo mechanism. It extends `ModuleList` with an undo/redo history, stored internally as an `moduleListStateList` and `currentStatePointer`. Additionally, it implements the following operations:

* `VersionedModuleList#commit()` — Saves the current `ModuleList` state in its history.
* `VersionedModuleList#undo()` — Restores the previous `ModuleList` state from its history.
* `VersionedModuleList#redo()` — Restores a previously undone `ModuleList` state from its history.

These operations are exposed in the `Model` interface as `Model#commitModuleList()`, `Model#undoModuleList()` and `Model#ModuleList()` respectively.

Given below is an example usage scenario and how the undo/redo mechanism behaves at each step.
>>>>>>> 15461e0e8d7b806ec82290e22af42376c52be749

The proposed grade tracker feature is an association class used to store additional information for the module.
The `Assignments` each store their own `assignment name`, `percentage of final grade` and `result`.

![Structure of the Module List Component](images/GradeTrackerDiagram.png)

When an `assignment` is added, it follows the sequence diagram as shown below. The sequence flows similarly
to the rest of the project as the command is parsed and then executed.

![Sequence Diagram of the Add Assignment Command](images/AddAssignmentSequenceDiagram.png)

#### Design consideration:

##### Aspect: Format to store the grade for a module
* Alternative 1 : Grade stores CAP.
    * Pros : Easier to integrate with Cap Calculator
    * Cons : User has to manually input CAP and does not know the average from the assignments accumulated

* Alternative 2 (current choice): Grade stores the raw score calculated from assignment
    * Pros : Grade can be automatically calculated from the assignment overall percentage for user to view
    * Cons : Requires separate CAP to be stored for Cap Calculator to access

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

* has a need to manage a number of classes
* prefer desktop apps over other types
* can type fast
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps

**Value proposition**:
* Users can keep track of all module details efficiently.
* Centralize key module details.
* Keep track of study progress effectively.
* Everything can be done in a single app.

### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​                                    | I want to …​                     | So that I can…​                                                        |
| -------- | ------------------------------------------ | ------------------------------ | ------------------------------------------------------ |
| `* * *`  | new user                                   | see usage instructions         | refer to instructions when I forget how to use the App |
| `* * *`  | user                                       | add a new module               | keep track of the module information easily            |
| `* * *`  | user                                       | delete a module                | remove modules that are completed                      |
| `* *`    | user                                       | find a module by name          | locate details of a module without having to go through the entire list |
| `* *`    | user                                       | add a zoom link to a module    | keep track and retrieve it easily                      |
| `* *`    | user                                       | calculate my cumulative average point   | plan my academic progress for the future      |
| `* *`    | user                                       | add graded assignments       | add the information of the assignments that contributed to my grade      |
| `* *`    | user                                       | edit my graded assignments     | update the information of the assignments I have completed     |
| `* *`    | user                                       | delete graded assignments      | remove the assignments that are do not contribute to my grade anymore|
| `*`      | user who is overloading                    | sort modules by name           | locate a module easily                                 |
| `* * *`  | user                                       | add a task                     | keep track of the tasks that I must complete           |
| `* * *`  | user                                       | delete a task                  | remove a task that has been done                       |
| `* * *`  | user                                       | edit a task                    | make necessary changes to a task                       |
| `* *`    | user                                       | label a task as completed      |                                                        |
| `* *`    | user                                       | find a task                    | find a task easily without looking at the entire list  |
| `* *`    | user                                       | sort tasks based on criteria   | easily manage the tasks by order                       |
| `* *`    | user                                       | filter tasks based on criteria | easily manage the tasks by group                       |
| `*`      | user                                       | reset the status of a task     | change a task from labeled as completed to not completed |
| `*`      | user                                       | archive a task                 | hide irrelevant tasks that might still be useful for future purposes |

*{More to be added}*

### Use cases

(For all use cases below, the **System** is the `CAP5BUDDY` and the **Actor** is the `user`, unless specified otherwise)

**Use case: Add a new Module**

**MSS**

1. User wants to add a new module to track.
2. User enters the command for adding a module: `add [module name/code]`
3. CAP5BUDDY adds the module into storage and display the success message.

*{More to be added}*

**Use case: Delete a module**

**MSS**

1. User requests to show all the existing modules.
2. CAP5BUDDY displays a list of the modules.
3. User wants to delete a specific module from the list.
4. User enters the command for module deletion: `delete [module name/code]`
4. CAP5BUDDY deletes that module from the list.

**Extensions**
* 2a. The list is empty.

  Use case ends here.

* 3a. The given index is invalid or does not exist.

  * 3a1. CAP5BUDDY displays an error message.

    Use case resumes at step 2.

*{More to be added}*

**Use case: Edit a module**

**MSS**

1. User requests to show all the existing modules.
2. CAP5BUDDY displays all the modules in a list.
3. User wants to edit a specific module from the list.
4. User enters the new information for that module.
5. CAP5BUDDY overwrites the existing information of that module.

**Extensions**
* 2a. The list is empty.

  Use case ends here.

* 3a. The given index is invalid or does not exist.

  * 3a1. CAP5BUDDY displays an error message.

    Use case resumes at step 2.

* 4a. The new information is empty

  * 4a1. CAP5BUDDY display an error message.

  Use case resumes at step 2.

  *{More to be added}*

**Use case: View a module**

**MSS**

1. User requests to view the list of modules.
2. CAP5BUDDY displays all the modules in a list.
3. User requests to view the information of a specific module from the list.
4. CAP5BUDDY displays all information related to the specified module.

**Extensions**

* 2a. The list is empty.

  Use case ends here.

* 3a. The given index is invalid or null.

  * 3a1. CAP5BUDDY displays an error message.

  Use case resumes at step 2.

**Use case: Input module Cumulative Average Point(CAP) details**

**MSS**

1. User requests to input CAP details(Grade point and Credit) for a module.
2. CAP5BUDDY saves CAP details under the specified module.

Use case ends.

**Extensions**

* 2a. Input contains invalid CAP details.

  * 2a1. CAP5BUDDY displays an error message.
  * 2a2. User enters the correct data.
  Use case resumes at step 1.

**Use case: Calculate Cumulative Average Point(CAP)**

**MSS**

1. User requests to calculate CAP
2. CAP5BUDDY calculates and displays CAP to user.

Use case ends.

**Extensions**

* 2a. One or more modules do not contain details of grade point or credits.

  * 2a1. CAP5BUDDY displays an error message.

  * 2a2. User enters required data.

  Steps 2a1-2a2 are repeated until the data requirements are fulfilled.<br>
  Use case resumes at step 2.

* 3a. One or more modules contain invalid details of grade point or credits.

  * 3a1. CAP5BUDDY displays an error message.

  * 3a2. User enters valid data.

  Steps 2a1-2a2 are repeated until the data requirements are fulfilled.<br>
  Use case resumes at step 2.

  *{More to be added}*

**Use case: Add a task to todo list**

**MSS**

1. User wants to add a task to the todo list.
2. User chooses the type of task to be added.
3. User enters the information regarding the task.
4. CAP5BUDDY saves the given task in the todo list.

Use case ends.

**Extensions**

* 3a. Format for information about the task is invalid.

  * 3a1. CAP5BUDDY displays an error message and ask the user to use the correct format.
  * 3a2. User enters the information with the correct format.

  Use case resumes at step 4

  *{More to be added}*

**Use case: Delete a task in the todo list**

**MSS**

1. User wants to delete a task in the todo list.
2. User chooses the task to be deleted.
3. CAP5BUDDY shows a prompt message asking if the user really wants to delete the task.
4. User clicks the "YES" button.
5. CAP5BUDDY deletes the task from the todo list.

Use case ends.

**Extensions**

* 4a. User accidentally clicked the "NO" button.

  * 4a1. The prompt message disappears and CAP5BUDDY does not delete the task.

  Use case ends.

  *{More to be added}*

**Use case: Sort task in the todo list**

**MSS**

1. User wants to sort the tasks in the todo list.
2. User chooses the basis for sorting the tasks.
3. CAP5BUDDY displays the sorted tasks based on the chosen basis.

Use case ends.

  *{More to be added}*

**Use case: Find a task in the todo list**

**MSS**

1. User wants to find a task in the todo list.
2. User chooses whether to find task based on date or keyword.
3. User enters the date or keyword.
4. CAP5BUDDY displays all task based on the user input from step 3.

Use case ends.

**Extensions**

* 3a. User inputs the date with an incorrect format.

  * 3a1. CAP5BUDDY displays an error message and ask the user to use the correct format.
  * 3a2. User enters the date with the correct format.

  Use case resumes at step 4

  *{More to be added}*

**Use case: Archive a task in the todo list**

**MSS**

1. User wants to archive a task in the todo list.
2. CAP5BUDDY shows a prompt message asking if the user is sure to archive the task.
3. User clicks the "YES" button.
4. CAP5BUDDY archives the task.

Use case ends.

**Extensions**

* 3a. User accidentally clicked the "NO" button.

  * 3a1. The prompt message disappears and CAP5BUDDY does not archive the task.

  Use case ends.

  *{More to be added}*

**Use Case: View all contact details of a lecturer**

  **MSS**
   1. User requests to view all contact details of a lecturer.
   2. User provides the name of the lecturer.
   3. CAP5BUDDY searches for the specified lecturer from storage.
   4. CAP5BUDDY retrieves all contact details of the lecturer from storage.
   5. CAP5BUDDY displays the desired contact details.

  **Extensions**

   * 3a. The specified lecturer name does not exist.

     * CAP5BUDDY displays an error message.

     Use case ends.

  **Use Case: View the email of a Lecturer**

  **MSS**
  1. User requests to view the email of a lecturer.
  2. User provides the name of the lecturer.
  3. CAP5BUDDY searches for the specified lecturer from storage.
  4. CAP5BUDDY retrieves the email of the lecturer from storage.
  4. CAP5BUDDY displays the desired email address.

  **Extensions**

  * 3a. The specified lecturer name does not exist.

    * CAP5BUDDY displays an error message.

    Use case ends.

  **Use Case: View the hand phone contact of a peer**

  **MSS**
  1. User requests to view the hand phone number of a peer.
  2. User provides the name of the peer.
  3. CAP5BUDDY searches for the specified peer from storage.
  4. CAP5BUDDY retrieves the hand phone contact of the peer from storage.
  4. CAP5BUDDY displays the desired hand phone contact.

  **Extensions**

* 3a. The specified peer name does not exist.

  * CAP5BUDDY displays an error message.

  Use case ends.

* 4a. The specified peer does not have a hand phone contact saved.

  * CAP5BUDDY displays an error message.

  Use case ends.

**Use Case: Edit the email of a TA**

  **MSS**
  1. User requests to edit the email of a TA.
  2. User provides the name of the TA.
  3. CAP5BUDDY searches for the specified TA from storage.
  4. User provides the new email to replace the existing email.
  5. CAP5BUDDY replaces the email of the TA with the user provided email.
  6. CAP5BUDDY displays the success message.

  **Extensions**

  * 3a. The specified TA name does not exist.

    * CAP5BUDDY displays an error message.

    Use case ends.

  * 4a. The provided email address is empty or null.

    * CAP5BUDDY displays an error message.

    Use case ends.

    *{More to be added}*

**Use Case: Add assignment to CAP5BUDDY**

  **MSS**
   1. User requests to add an assignment to a module in CAP5BUDDY.
   2. CAP5BUDDY retrieves module from module list.
   3. CAP5BUDDY creates and adds assignment to the gradetracker in the module retrieved.
   4. CAP5BUDDY updates module in module list.
   5. CAP5BUDDY displays success message.

  **Extensions**

 * 2a. The module to add to is invalid.

    * CAP5BUDDY displays an error message.

      Use case ends.

 * 3a. The given grade is invalid.

    * CAP5BUDDY displays an error message.

      Use case ends.

**Use Case: View grades for a module**

  **MSS**
  1. User requests to view grades for a module.
  2. CAP5BUDDY retrieves current grades.
  3. CAP5BUDDY displays current grades.

  **Extensions**

  * 3a. The current list of grades is empty.

    * CAP5BUDDY displays an error message.

      Use case ends.


**Use Case: Edit assignment in CAP5BUDDY**

  **MSS**
  1. User requests to edit an assignment in a module in CAP5BUDDY.
  2. CAP5BUDDY retrieves the module.
  3. CAP5BUDDY retrieves the assignment requested from the grade tracker in the module.
  4. User requests to edit the assignment retrieved.
  5. CAP5BUDDY edits the assignment.
  6. CAP5BUDDY saves the edited assignment in the module.
  7. CAP5BUDDY displays success message.

  **Extensions**

  * 2a. The given module is invalid.

    * CAP5BUDDY displays an error message.

      Use case ends.

  * 3a. The given assignment is invalid.

    * CAP5BUDDY displays an error message.

      Use case ends.

  *{More to be added}*

**Use case: Delete an assignment**

   **MSS**
   1. User requests to delete an assignment in a module in CAP5BUDDY.
   2. CAP5BUDDY retrieves the module.
   3. CAP5BUDDY retrieves the assignment requested from the grade tracker in the module.
   4. CAP5BUDDY deletes the assignment.
   5. CAP5BUDDY updates the grade tracker in the module.
   4. CAP5BUDDY displays success message.

   **Extensions**

   * 3a. The provided assignment is invalid.

      * CAP5BUDDY displays an error message.

        Use case ends.

   *{More to be added}*

**Use case: Add an event**

  **MSS**
  1. User request to create and add a new event into the tracker.
  2. CAP5BUDDY creates and add the event into the list.

  **Extensions**
  * 1a. The provide event information is invalid, missing date and time.

       * CAP5BUDDY displays an error message.

          Use case resumes at step 1.

**Use case: Edit an event**

  **MSS**
  1. User requests to view all existing events.
  2. CAP5BUDDY displays the list of all events.
  3. User requests to modify an existing event.
  4. CAP5BUDDY displays the newly modified event.

  **Extensions**
  * 3a. The newly inputted information is invalid format or empty.

    * CAP5BUDDY displays an error message.

      Use case resumes at step 2.

  * 3b. The requested event does not exist.

    * CAP5BUDDY displays an error message.

      Use case resumes at step 2.

**Use case: Delete an event**

  **MSS**
  1. User requests to view all existing events in the list.
  2. CAP5BUDDY displays all the events.
  3. User requests to remove a specific event from the list.
  4. CAP5BUDDY shows a success message and shows the new list.

  **Extensions**
  * 3a. The event to be removed does not exist.

    * CAP5BUDDY displays an error message.

      Use case resumes at step 2.

**Use case: Mark an event as completed**

  **MSS**
  1. User requests to show all events.
  2. CAP5BUDDY displays all tracked events.
  3. User requests to mark a specific event as completed.
  4. CAP5BUDDY shows the event as completed.

  **Extensions**

  * 3a. The event requested does not exist.

    * CAP5BUDDY shows an error message.

      Use case resumes at step 2.

  * 3b. The event requested is already marked as completed/

    * CAP5BUDDY does nothing.

      Use case resumes at step 2.

**Use case: Push the event back**

  **MSS**
  1. User requests to show all events.
  2. CAP5BUDDY displays all currently tracked events.
  3. User requests to push the event back.
  4. CAP5BUDDY shows the new date and time of the event.

  **Extensions**

  * 3a. The requested event does not exist.

    * CAP5BUDDY shows an error message.

      Use case resumes at step 2.

  * 3b. The new date and time is invalid.

    * CAP5BUDDY shows an error message.

      Use case resumes at step 2.

### Non-Functional Requirements

1.  Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2.  A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
3.  A user with not be able to access and modify other users' data files.
4.  Should be usable by any novice that has never used this program before.
5.  The name of the modules should not exceed 40 characters.
6.  Should not contain more than 100 modules at 1 time.

*{More to be added}*

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, OS-X
* **Module List**: A list of all modules currently being tracked and stored in the CAP5BUDDY.

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

### Saving data

1. Dealing with missing/corrupted data files

   1. _{explain how to simulate a missing/corrupted file, and the expected behavior}_

1. _{ more test cases …​ }_
