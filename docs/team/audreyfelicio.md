---
layout: page
title: Audrey's Project Portfolio Page
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
  * Used GitHub Projects feature to create kanban boards to track user stories.
  * Manager and maintainer of project repository.
  * Provided help to team members about Git, GitHub, and forking workflow.

* **Reviews & Merging**:
  * Head reviewer for Pull Requests.
  * Split review tasks to team members.
  * Ensured no pull requests was merged before thorough review was conducted.
  * Ensured every merge pass the GitHub action checks.
  * Head merger for Pull Requests.
  * Tests other team member's code and determine functional flaws if any.

* **Testing**:
  * Created unit tests for `update` feature (update command parser, update command).
  * Created integration tests for `update` feature (update command).
  * Created unit tests for `suggestion` feature (suggestion command parser, suggestion util, suggestion command).
  * Created integration tests for `suggestion` feature (suggestion command).
  * Created unit tests for `sort` feature (sort command parser, sort util, sort command).
  * Created integration tests for `sort` feature (sort command).
  * Improved coverage by creating tests for `QuantityAdder` and `ModelManager`.
  * Reviewed other team members' tests and ensure the tests are adequate and working.
  * Help other team members debug testing when the tests failed and they don't know what causes the tests to fail.

* **Documentation**:
  * User Guide:
    * Added prefixes table.
    * Added command information and summary table.
    * Added invalid prefixes warning.
    * Added documentation for the feature `update`.
    * Added documentation for the feature `sort`.
    * Added documentation for the feature `suggestion`.
    * Fix markdown formatting errors.

  * Developer Guide:
    * Added implementation details of the `update` feature.
    * Added implementation details of the `sort` feature.
    * Added implementation details of the `suggestion` feature.
    * Added sequence and class diagrams for `Logic` API.
    * Added UML diagrams for `update`, `sort`, and `suggestion` feature.
    * Added user stories in the form of a table.
    * Added use cases for `update` feature.
    * Added use cases for `sort` feature.
    * Added use cases for `suggestion` feature.

* **Project Management**:
  * Created meeting rooms via Zoom for regular team meeting every week.
  * Ensured meeting time is chosen such that everyone can attend.
  * Ensured everyone attend the meeting on time.

* **UI**:
  * Updated the table in help window and adjusted font.
  * Gave feedback during the initial design of UI and helped with picking color scheme.
