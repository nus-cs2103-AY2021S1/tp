---
layout: page
title: Amy's Project Portfolio Page
---
### Project: Warenager

Warenager is an **inventory management application** to help warehouse managers
of small scale companies keep track of stocks in their warehouse.
It **optimizes inventory management tasks** for warehouse managers including but not
exhaustive of **updating, searching and sorting stocks** via Command Line Interface (CLI),
while still having the benefits of a Graphical User Interface (GUI).

Given below are my contributions to the project.

* **Code Contribution**
  * Here is the link to [my code](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=amylzting&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)
  on the tP Code Dashboard.

* **Features and Enhancements implemented**
  * **New Feature**: Added the ability to find existing items in the storage. (PR [\#93](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/93))
    * What it does: Allows the user to search for desired stock.
    * Justification: Users can easily find stocks using the stock's fields (name, serial number, location stored, source). 
    * Highlights: This enhancement brings ability to find stocks from not just the name of the stock but also other related fields
    of the stock. Any stock that matches any field searched will be shown. Search is also case-insensitive. 

  * **New Feature**: Added the ability for advanced find for existing items in the storage. (PR [\#115](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/115))
    * What it does: Allows the user to apply additional filters to search for desired stock.
    * Justification: Users can accurately find stocks using more filters, to narrow down the results.
    * Highlights: This enhancement comes with the ability to apply filters such as name of the stock,
    serial number, location stored and source of stock. Any stock that matches all fields searched will be shown.
    Search is also case-insensitive.

  * **New Feature**: Added the ability to add optional notes to existing items in the storage. (PR [\#147](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/147))
    * What it does: Allows the user to add notes to stock.
    * Justification: User can now add notes to their stocks, to remind them of important details.
    * Highlights: This enhancement comes with the ability to add multiple notes to the stock.
    Users are able to add notes to the stock at any time, without erasing their previous notes.

  * **New Feature**: Added the ability to delete one or all notes from an existing stock. (PR [\#147](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/147))
    * What it does: Allows the user to delete notes from stock.
    * Justification: Users can now delete notes that they no longer need from the stock.
    * Highlights: This enhancement comes with the ability to delete any one note from the stock by providing
    the index of note, or delete all notes from the stock.

  * **New Feature**: Added the ability to view the details of a stock in a Stock View tab. (PR [\#265](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/265), code from [NoteView PR#177](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/177) changed to StockView)
    * What it does: Allows the user to view details of a single stock.
    * Justification: Users can view a stock on a tab away from the entire stockbook.
    * Highlights: The Stock View tab live updates whenever the details of the stock being viewed is changed.
    The details of the stock remain in the tab even when the user clicks away to another tab. Users can compare the details of a single stock with other stocks in the Data stockbook tab.
  
* **Git & Repository**:
  * Used GitHub Projects feature to create kanban boards to track [user stories](https://github.com/AY2021S1-CS2103T-T15-3/tp/projects/1).
  * Helped team members with Github functionalities
  * Managed the issue tracker: [Issues created](https://github.com/AY2021S1-CS2103T-T15-3/tp/issues?q=is%3Aissue+is%3Aclosed+author%3Aamylzting)

* **Reviews & Merging**:
  * Frequently reviewed, tested (to detect functional flaws), and merged teammates' PRs after thorough review and Github action checks.
  * Helped team members with any bugs and suggested any possible improvements.
  * Here are some pull requests: [#89](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/89), [#100](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/100),
   [#133](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/133), [#136](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/136), [#137](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/137),
   [#143](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/143), [#150](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/150), [#156](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/156)
  
* **Documentation**:
  * Responsible for managing documentation quality and content.
  * Ensure that the documentation is informative and follows a relatively standardized format.
  * Checked team members' documentation.
  
  * User Guide:
    * Head writer and maintainer of team aspects of the user guide
      (eg. sections include introduction, quick start, definitions, features, commands) (PR [\#276](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/267))
    * Added documentation for the features `find` (PR [\#111](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/111), [\#276](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/267)),
    `findexact` (PR [\#120](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/120), [\#276](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/267)),
    `note` (PR [\#154](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/154)),
    `notedelete` (PR [\#154](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/154), [\#272](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/272)),
    and `stockview` (PR [\#272](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/272))
    
  * Developer Guide:
    * Added implementation and UML diagrams for Find and FindExact features (PR [\#163](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/163), [\#165](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/165)),
    the Note feature (PR [\#290](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/290), [\#294](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/294)),
    and the StockView feature (PR [\#294](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/294))
    * Rebased Architecture, UI component, Logic component,
    Model component and Storage component UML diagrams from AB3 to Warenager. (PR [\#165](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/165))
    * Added use cases and instructions manual testing test cases for the features `find` (PR [\#62](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/62)),
    `findexact` (PR [\#154](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/154)),
    `note` (PR [\#154](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/154)),
    `notedelete` (PR [\#154](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/154)),
    and `stockview` (PR [\#290](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/290)).
    * Added documentation for `non-functional requirements` and `glossary`sections. (PR [\#62](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/62))
    
* **Testing**:
   * Created unit and integration tests for `find` feature (find command parser, find command, PR [\#129](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/129)),
   `findexact` feature (findexact command parser, findexact command, PR [\#159](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/159)),
   `note` feature (note command parser, note command, PR [\#259](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/259)),
   `notedelete` feature (notedelete command parser, notedelete command, PR [\#259](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/259)),
   and `stockview` feature (stockview command parser, stockview command, PR [\#265](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/265)).
   * Created tests for `FindUtil`. (PR [\#259](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/259))
   * Created tests for `Note` and `NoteIndex`. (PR [\#259](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/259))
   * Created unit tests for predicates package classes `LocationContainsKeywordsPredicate`,
   `NameContainsKeywordsPredicate`, `SerialNumberContainsKeywordsPredicate` and `SourceContainsKeywordsPredicate`. (PR [\#127](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/127))
   * Created tests for `ParserUtil` to increase code coverage. (PR [\#343](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/343))
