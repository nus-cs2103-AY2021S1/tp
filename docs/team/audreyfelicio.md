---
layout: page
title: Audrey Felicio Anwar's Project Portfolio Page
---

## Project: Warenager

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
  * **New Feature**: Added the ability to update existing stocks in the inventory. (Pull request [\#101](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/101))
    * What it does: Allows the user to update the details of the desired stock.
    
    * Justification: This feature improves the user experience as user can easily edit the details of
    existing stocks. Users now do not need to remove the item and then add them back in order to
    edit the details.
    
    * Highlights: Multiple stocks are able to be updated in one step. User can update the name, source, low quantity
    threshold, quantity, and location details of a stock. The implementation was challenging a helper class was
    needed to generate the updated stock without tampering with the original stock in the inventory. Implementing
    the increment quantity in update was the most challenging thing in this feature since a helper class to
    abstract and encapsulate the process of adding value to a quantity was needed.

  * **New Feature**: Added the ability to sort existing stocks in the inventory. (Pull request [\#151](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/151))
    * What it does: Allows the user to sort the stocks in the inventory by the field the user wants.
    
    * Justification: This feature improves the user experience as the user can list the existing stocks by
    the user's order preference. Sorting in alphabetical order also helps for easier viewing of stocks.
    
    * Highlights: User can sort by name, source, quantity, serial number, or location. For each field the user can also
    further specify whether to sort in ascending or descending order. The implementation was quite challenging since
    adaptation to the `ModelManager` was needed in order to sort the internal `FilteredList` inside `ModelManager`.
    Another thing that is challenging is to generate different comparators for different fields.
  
  * **New Feature**: Added the ability to suggest correct commands to the user. (Pull request [\#133](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/133))
    * What it does: Suggests the correct command format to the user if the command user input is in the wrong format.
    
    * Justification: This feature improves the user experience as the user can view and type the correct command form
    easily after entering a faulty command. This makes the user more convenient as every time the user enters a wrong
    command format, the user will not need to refer to the user guide or help and instead just need to type the suggested
    message.
    
    * Highlights: This feature uses the minimum edit distance heuristics to calculate the closest correct command word.
    The suggestion for the fields and prefixes is generated based on the user input itself. The minimum edit distance
    heuristics is implemented using dynamic programming algorithm. The implementation for this feature was the most
    challenging since suggestion feature requires all error message format to be standardized and a lot of research
    regarding the string comparison heuristic and the algorithm was needed. Another thing that made this challenging is
    that to make sure the suggestion generated is correct and hence a lot of parameter checking is needed in generating
    the suggestion message.


* **Git & Repository**:
  * Created the team repository by forking from `nus-cs2103-AY2021S1/tp`.
  * Set up the team repository as specified by the `CS2103T` module requirements.
  * Used GitHub Projects feature to create kanban boards to track user stories. ([Link](https://github.com/AY2021S1-CS2103T-T15-3/tp/projects/1))
  * Manager and maintainer of project repository.
  * Provided help to team members about Git, GitHub, and forking workflow.
  * Setup codecov for the team to track coverage for tests (Pull request [\#69](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/69))
  * Managed the issue tracker: [Link to issues created](https://github.com/AY2021S1-CS2103T-T15-3/tp/issues?q=is%3Aissue+is%3Aclosed+author%3Aaudreyfelicio)

* **Community**
    * **Reviews & Merging**:
      * PRs reviewed (with non-trivial review comments): [\#62](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/62), [\#64](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/64), [\#156](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/156), [\#213](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/213), [\#256](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/256)
      * All PRs reviewed: [Link to PRs reviewed](https://github.com/AY2021S1-CS2103T-T15-3/tp/pulls?page=2&q=is%3Apr+reviewed-by%3Aaudreyfelicio+is%3Aclosed)
    
    * **Bugs & Forums**:
      * Fixed bugs in update feature (Pull request [\#258](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/258))
      * Fixed bugs in suggestion feature (Pull request [\#261](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/261))
      * Reported bugs and suggestions for other teams in the class (examples: [1](https://github.com/AudreyFelicio/ped/issues/1), [2](https://github.com/AudreyFelicio/ped/issues/2), [3](https://github.com/AudreyFelicio/ped/issues/3), [4](https://github.com/AudreyFelicio/ped/issues/4), [5](https://github.com/AudreyFelicio/ped/issues/5), [6](https://github.com/AudreyFelicio/ped/issues/6), [7](https://github.com/AudreyFelicio/ped/issues/7), [8](https://github.com/AudreyFelicio/ped/issues/8), [9](https://github.com/AudreyFelicio/ped/issues/9), [10](https://github.com/AudreyFelicio/ped/issues/10))

* **Testing**:
  * Created unit tests for `update` feature (update command parser, update command). (Pull request [\#112](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/112))
  * Created integration tests for `update` feature (update command). (Pull request [\#112](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/112))
  * Created unit tests for `suggestion` feature (suggestion command parser, suggestion util, suggestion command). (Pull requests [\#190](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/190), [\#192](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/192))
  * Created integration tests for `suggestion` feature (suggestion command). (Pull request [\#192](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/192))
  * Created unit tests for `sort` feature (sort command parser, sort util, sort command). (Pull requests [\#188](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/188), [\#189](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/189))
  * Created integration tests for `sort` feature (sort command). (Pull request [\#189](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/189))
  * Improved coverage by creating tests for `QuantityAdder` and `ModelManager`. (Pull request [\#264](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/264))
  * Reviewed other team members' tests and ensure the tests are adequate and working.
  * Help other team members debug testing when the tests failed and they don't know what causes the tests to fail.

* **Documentation**:
  * User Guide:
    * Added prefixes table. (Pull request [\#274](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/274))
    * Added command information and summary table. (Pull request [\#252](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/252))
    * Added invalid prefixes warning. (Pull request [\#252](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/252))
    * Added documentation for the feature `update`. (Pull request [\#121](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/121))
    * Added documentation for the feature `sort`. (Pull request [\#164](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/164))
    * Added documentation for the feature `suggestion`. (Pull request [\#145](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/145))
    * Fix markdown formatting errors. (Pull request [\#121](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/121))

  * Developer Guide:
    * Added implementation details of the `update` feature. (Pull request [\#164](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/164))
    * Added implementation details of the `sort` feature. (Pull request [\#164](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/164))
    * Added implementation details of the `suggestion` feature. (Pull request [\#145](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/145))
    * Added sequence and class diagrams for `Logic` API. (Pull request [\#221](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/221))
    * Added UML diagrams for `update`, `sort`, and `suggestion` feature. (Pull requests [\#145](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/145), [\#164](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/164), [\#274](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/274))
    * Added user stories in the form of a table. (Pull request [\#51](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/51))
    * Added use cases for `update` feature. (Pull request [\#284](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/284))
    * Added use cases for `sort` feature. (Pull request [\#164](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/164))
    * Added use cases for `suggestion` feature. (Pull request [\#145](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/145))

* **Project Management**:
  * Created meeting rooms via Zoom for regular team meeting every week.
  * Ensured meeting time is chosen such that everyone can attend.
  * Ensured everyone attend the meeting on time.
  * Managed releases `v1.1` - `v1.3` (4 releases) on GitHub.

* **UI**:
  * Updated the table in help window and adjusted font. (Pull request [\#219](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/219))
  * Gave feedback during the initial design of UI and helped with picking color scheme.
