---
layout: page
title: Audrey's Project Portfolio Page
---
### Project: Warenager

Warenager is an **inventory management application** to help warehouse managers
of small scale companies keep track of stocks in their warehouse.
It **optimizes inventory management tasks** for warehouse managers including but not
exhaustive of **updating, searching and sorting stocks** via Command Line Interface (CLI),
while still having the benefits of a Graphical User Interface (GUI).

Given below are my contributions to the project.

* **Code Contribution**
  * Here is the link to [my code](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=AudreyFelicio&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&tabAuthor=AudreyFelicio&tabRepo=AY2021S1-CS2103T-T15-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other)
  on the tP Code Dashboard.

* **Features and Enhancements Implemented**
  * **New Feature**: Added the ability to update existing stocks in the inventory. (Pull requests
  [\#101](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/101),
  [\#157](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/157),
  [\#214](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/214),
  [\#222](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/222),
  [\#258](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/258))
    * What it does: Allows the user to update the details of the desired stocks.
    * Justification: This feature allows the user to update stocks without the need to remove first and then add back
    the stocks with new details.
    * Highlights: Multiple stocks are able to be updated in one step. User can update the name, source, low quantity
    threshold, quantity, and location details of a stock. The implementation was challenging as the implementation need
    to preserve data integrity if an error occurs while updating.

  * **New Feature**: Added the ability to sort existing stocks in the inventory. (Pull request [\#151](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/151))
    * What it does: Allows the user to sort the stocks in the inventory by the field the user wants.
    * Justification: This feature allows the user to sort stocks according to the user's preference for easier viewing.
    * Highlights: User can sort by name, source, quantity, serial number, or location and in both ascending
    and descending order. The implementation was challenging since it requires comparators to sort the stocks.
  
  * **New Feature**: Added the ability to suggest correct commands to the user. (Pull requests
  [\#133](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/133),
  [\#187](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/187),
  [\#261](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/261))
    * What it does: Suggests a correct command format to the user if the command the user entered is invalid.
    * Justification: This feature allows the user to view and type the correct command form easily after entering an
    invalid command.
    * Highlights: This feature uses the minimum edit distance heuristic to calculate the closest correct command word.
    The suggestion for the fields and prefixes is generated based on the user input and default parameters.
    The implementation was the most challenging since a lot of research on the algorithm for string comparison heuristic
    was needed.

* **Git & Repository**:
  * Created and set up project repository.
  * Used GitHub Projects feature to create kanban boards to track user stories. ([Link](https://github.com/AY2021S1-CS2103T-T15-3/tp/projects/1))
  * Setup codecov for the team to track coverage for tests (Pull requests
  [\#69](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/69),
  [\#301](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/301))
  * Managed the issue tracker: [Link to issues created](https://github.com/AY2021S1-CS2103T-T15-3/tp/issues?q=is%3Aissue+is%3Aclosed+author%3Aaudreyfelicio)

* **Reviews & Merging**:
  * PRs reviewed (with non-trivial review comments): 
  [\#62](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/62),
  [\#64](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/64),
  [\#156](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/156),
  [\#213](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/213),
  [\#256](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/256)
  * All PRs reviewed: [Link to PRs reviewed](https://github.com/AY2021S1-CS2103T-T15-3/tp/pulls?page=2&q=is%3Apr+reviewed-by%3Aaudreyfelicio+is%3Aclosed)

* **Bugs & Forums**:
  * Fixed bugs in `update` and `suggestion` features (Pull requests
  [\#222](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/222),
  [\#258](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/258),
  [\#261](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/261))
  * Reported bugs and suggestions for other teams in the class (examples:
  [1](https://github.com/AudreyFelicio/ped/issues/1),
  [2](https://github.com/AudreyFelicio/ped/issues/2),
  [3](https://github.com/AudreyFelicio/ped/issues/3),
  [4](https://github.com/AudreyFelicio/ped/issues/4),
  [5](https://github.com/AudreyFelicio/ped/issues/5),
  [6](https://github.com/AudreyFelicio/ped/issues/6),
  [7](https://github.com/AudreyFelicio/ped/issues/7),
  [8](https://github.com/AudreyFelicio/ped/issues/8),
  [9](https://github.com/AudreyFelicio/ped/issues/9),
  [10](https://github.com/AudreyFelicio/ped/issues/10))

* **Testing**:
  * Created both unit and integration tests for `update`, `suggestion`, and `sort` features. (Pull requests
  [\#112](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/112),
  [\#188](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/188),
  [\#189](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/189),
  [\#190](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/190),
  [\#192](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/192),
  [\#254](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/254),
  [\#284](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/284))
  * Improved coverage by creating tests for `QuantityAdder` and `ModelManager`. (Pull request [\#264](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/264))
  * Created unit tests for `UpdateStockDescriptor`. (Pull request [\#313](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/313))

* **Documentation**:
  * User Guide:
    * Added prefixes table, command information, command summary table, and invalid prefixes warning. (Pull requests
     [\#252](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/252),
     [\#274](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/274))
    * Added documentation for `update`, `sort`, and `suggestion` features. (Pull requests
    [\#121](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/121),
    [\#145](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/145),
    [\#164](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/164),
    [\#209](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/209),
    [\#274](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/274),
    [\#284](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/284),
    [\#293](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/293))

  * Developer Guide:
    * Added implementation details, UML diagrams, use cases, and manual testing test cases of `update`, `sort`, and `suggestion` features. (Pull requests
    [\#69](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/69),
    [\#145](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/145),
    [\#164](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/164),
    [\#274](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/274),
    [\#284](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/284),
    [\#293](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/293),
    [\#344](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/344))
    * Added sequence and class diagrams for `Logic` API. (Pull request [\#221](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/221))
    * Added user stories in the form of a table. (Pull requests
    [\#51](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/51),
    [\#284](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/284))
    * Added effort appendix to justify project effort and workload. (Pull request [\#293](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/293))

* **Project Management**:
  * Managed releases `v1.1` - `v1.4` (5 releases) on GitHub.

* **UI**:
  * Updated the table in help window and adjusted font. (Pull requests
  [\#219](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/219),
  [\#344](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/344))
  * Gave feedback during the initial design of UI and helped with picking color scheme.
