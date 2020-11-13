---
layout: page
title: Ek Chin Hui's Project Portfolio Page
---

## Project: NUSave

NUSave is a desktop budgeting application used to manage an individual's budgets and expenses. 
NUSave can hold multiple budgets, of which each budget holds multiple expenditures. 
The user interacts with it using a command line interface (CLI), and it has a GUI created with JavaFX. 
It is written in Java, and has about 20,000 LoC.

Given below are my contributions to the project:
- **Feature**: Added ability to render both budgets and expenditures in the GUI of NUSave.
    - Justification: This is a core feature that allows the display of both budgets and expenditures
    using `open` and `close` commands. This builds upon the original implementation of AB3 using the
    ObservableList. The challenge in implementation was in encapsulating the `Budget` and `Expenditure`
    classes with little dependency on the UI classes.
    
- **Feature**: Change implementation of storage to store budgets and expenditures.
    - Justification: This feature is necessary to store budget and expenditure data
    to be accessed in subsequent use of the application. Since there is an additional
    layer of data compared to AB3, a deep understanding of the original implementation
    was necessary to implement this feature.
    
- **Feature**: Added ability to find and list expenditures in NUSave.
    - What it does: `find` command allows users to be able to filter specific expenditures
    in the current budget which matches the input search term. `list` command allows users
    to display all expenditures within the current budget.
    - Justification: This feature allows the user to be able to quickly retrieve
    information about an expenditure especially if there are many expenditure entries.
    
- **Code Contributed**: [Reposense Link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=ekchinhui)

- **Project Management**:
    - Created Issues in milestones v1.2 and v1.3
    - [PRs reviewed by me](https://github.com/AY2021S1-CS2103T-T11-4/tp/pulls?q=is%3Apr+reviewed-by%3A%40me+is%3Aclosed)

- **Contributions to Documentation**:
    - User Guide:
        - Added documentation for the commands `find` and `list`.
        - Added documentation for Frequently Asked Questions.
    - Developer Guide:
        - Added description of overall architecture.
        - Added implementation details for `Model` and `Commons` Component.
        - Added implementation details for `find` and `lost` commands.
        - Added non-functional requirements.
        - Added instructions for manual testing.

- **Community**:
    - Reported bugs and suggestions for other teams 
    (examples [1](https://github.com/AY2021S1-CS2103T-W15-2/tp/issues/184),
    [2](https://github.com/AY2021S1-CS2103T-W15-2/tp/issues/183),
    [3](https://github.com/AY2021S1-CS2103T-W15-2/tp/issues/182),
    [4](https://github.com/AY2021S1-CS2103T-W15-2/tp/issues/181))
    
    
**Contributions to User Guide (Extracts)**:

#### 5.2.4. Finding budgets: `find`
(Contributed by Chin Hui)

Format: `find KEYWORD`

 Prefix | Parameters | Requirement | Comments
 ------ | ---------- | ----------- | --------
 \-     |  `KEYWORD` | Required    | Keyword to be used for searching

You can use this command to find budgets whose names contain the given `KEYWORD`.

When you find budgets, the list view will filter to only display budgets whose names contains the given `KEYWORD`.

 > 📕 The filter is case-insensitive.
 >
 > 📕 If no budgets are displayed, it means that none of the budgets matches your search term. 
 > 
 > 📕 You can use the [list budgets command](#525-listing-budgets-list) to display all budgets again.

✏️ Example: `find Temasek Hall`

This will find all budgets with the keyword 'Temasek Hall' in the name of the budget as seen in Figure 5.2.4.1 below:

![Example of find budgets command](../images/CommandScreenShots/5_2_4_1_findBudgets.png)

Figure 5.2.4.1. Example of using the find budgets command.

**Contributions to Developer Guide (Extracts)**:

### 3.1. Architecture
<img src="../images/ArchitectureDiagram.png" width="450" />

Figure 3.1.1: Architecture Diagram of NUSave components.

The ***Architecture Diagram*** given above explains the high-level design of the application.

Given below is a quick overview of each component:

**`Main`** has two classes called [`Main`] and [`MainApp`].
It has two primary responsibilities:
* At launch: Initializes the components in the correct sequence, and connects them up with each other.
* At shut down: Shuts down the components and invokes cleanup methods where necessary.

[**`Commons`**](#common-classes) represents a collection of classes used by multiple other components.

The rest of the application consists of five components:

1. [**`UI`**](#ui-component): Handles the UI of the application.
1. [**`Logic`**](#logic-component): Executes the commands.
1. [**`Model`**](#model-component): Holds the data of the application in memory.
1. [**`Storage`**](#storage-component): Reads data from, and writes data to, the hard disk.
1. [**`State`**](#state-componenet): Remembers the current state of the application.

For each of the five components:

* Its API is defined in an `interface` with the same name as the Component.
* Its functionality is exposed using a concrete `{Component Name} Manager` class which implements the corresponding API
`interface` mentioned in the previous point.
    - For example, the `Logic` component (see the class diagram below) defines its API in the `Logic.java` interface and exposes its functionality
    using the `LogicManager.java` class which implements the `Logic` interface.

![Class Diagram of the Logic Component](../images/LogicClassDiagram.png)
Figure 3.1.2: Class Diagram of Logic Component.

<img src="../images/ArchitectureSequenceDiagram.png" width="574" />

Figure 3.1.3: Sequence Diagram of component interactions for `delete 1` command used to delete a budget.

The ***Sequence Diagram*** given above shows how the components interact with each other for the scenario where the user
issues the command `delete 1`.
