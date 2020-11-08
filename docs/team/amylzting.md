---
layout: page
title: Amy's Project Portfolio Page
---

## Project: Warenager

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
  * **New Feature**: Added the ability to find existing items in the storage. (Pull request [\#93](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/93))
    * What it does: Allows the user to search for desired stock.
    * Justification: This feature improves the user experience as user can easily search for the details of
    existing items. Users can now search for their desired stocks quickly and conveniently,
    by using any of the fields (name, serial number, location stored, source of stock) to find
    their desired stock. Users have the convenience of searching for a whole list of stocks that are related,
    instead of only being able to search for one stock (via name).
    * Highlights: This enhancement comes with the ability to find stocks from not just the name of the stock
    but also other related fields such as serial number, location stored and source of stock.
    Search is also case-insensitive and any stock that contains the search term will be shown.

  * **New Feature**: Added the ability for advanced find for existing items in the storage. (Pull request [\#115](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/115))
    * What it does: Allows the user to apply additional filters to search for desired stock.
    * Justification: This feature improves the user experience as user can now accurately search
    for stocks using more filters, to narrow down the results.
    * Highlights: This enhancement comes with the ability to apply filters such as name of the stock,
    serial number, location stored and source of stock.
    Search is also case-insensitive and any stock that contains the search term will be shown.

  * **New Feature**: Added the ability to add optional notes to existing items in the storage. (Pull request [\#147](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/147))
    * What it does: Allows the user to add notes to stock.
    * Justification: This feature improves the user experience as user can now add optional notes
    to their stocks, to remind them of important details.
    * Highlights: This enhancement comes with the ability to add multiple notes to the stock.
    Users will be able to add notes to the stock at any time, without erasing their previous notes.

  * **New Feature**: Added the ability to delete one note or all notes from existing a stock in the storage. (Pull request [\#147](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/147))
    * What it does: Allows the user to delete notes from stock.
    * Justification: This feature improves the user experience as user can now delete notes that they
    no longer need from the stock.
    * Highlights: This enhancement comes with the ability to delete one note from the stock by providing
    the index of note, or delete all notes from the stock.
    Users will be able to delete whichever note they want from the stock at any time,
    without needing to delete all notes.

  * **New Feature**: Added the ability to view the details of a stock in a Stock View tab. (Pull request [\#265](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/265), code from [NoteView PR#177](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/177) changed to StockView)
    * What it does: Allows the user to view details of a single stock.
    * Justification: This feature improves the user experience as the user can now view the details of
    a single entire stock on another tab away from the entire stock book.
    * Highlights: The Stock View tab live updates whenever the details of the stock being viewed is changed.
    The details of the stock remain in the tab even when the user clicks away to another tab. This allows the
    user to compare the details of a single stock with other stocks in the Data stockbook tab.
  
* **Git & Repository**:
  * Used GitHub Projects feature to create kanban boards to track [user stories](https://github.com/AY2021S1-CS2103T-T15-3/tp/projects/1).
  * Helped team members with Github functionalities
  * Managed the issue tracker: [Issues created](https://github.com/AY2021S1-CS2103T-T15-3/tp/issues?q=is%3Aissue+is%3Aclosed+author%3Aamylzting)

* **Reviews & Merging**:
  * Frequently reviewed and tested teammates' pull requests.
  * Merged approved pull requests after thorough review and GitHub action checks.
  * Helped team members with any bugs and suggested any possible improvements.
  * Tests code to be merged before reviewing to detect functional flaws.
  * Some pull requests: [#89](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/89), [#100](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/100),
   [#133](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/133), [#136](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/136), [#137](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/137),
   [#143](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/143), [#150](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/150), [#156](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/156)
  * Here is the link to all the PRs reviewed by me: [PRs](https://github.com/AY2021S1-CS2103T-T15-3/tp/pulls?page=1&q=is%3Apr+is%3Aclosed+reviewed-by%3A%40me)
  
* **Documentation**:
  * Responsible for managing documentation quality and content.
  * Ensure that the documentation is informative and follows a relatively standardized format.
  * Checked team members' documentation.
  
  * User Guide:
    * Head writer and maintainer of team aspects of the user guide
      (eg. sections include introduction, quick start, definitions, features, commands) (Pull request [\#276](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/267))
    * Added documentation for the feature `find`. (Pull request [\#111](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/111), [\#276](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/267))
    * Added documentation for the feature `findexact`. (Pull request [\#120](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/120), [\#276](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/267))
    * Added documentation for the feature `note`. (Pull request [\#154](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/154))
    * Added documentation for the feature `notedelete`. (Pull request [\#154](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/154), [\#272](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/272))
    * Added documentation for the feature `stockview`. (Pull request [\#272](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/272))
    
  * Developer Guide:
    * Added implementation and UML diagrams for the Find and FindExact features. (Pull request [\#163](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/163), [\#165](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/165))
    * Added implementation and UML diagrams for the Note feature. (Pull request [\#290](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/290), [\#294](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/294))
    * Added implementation and UML diagrams for the StockView feature. (Pull request [\#294](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/294))
    * Rebased Architecture, UI component, Logic component,
    Model component and Storage component UML diagrams from AB3 to Warenager. (Pull request [\#165](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/165))
    * Added use cases and manual testing test cases for the feature `find`. (Pull request [\#62](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/62))
    * Added use cases and manual testing test cases for the feature `findexact`. (Pull request [\#154](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/154))
    * Added use cases and manual testing test cases for the feature `note`. (Pull request [\#154](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/154))
    * Added use cases and manual testing test cases for the feature `notedelete`. (Pull request [\#154](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/154))
    * Added use cases and manual testing test cases for feature `stockview`. (Pull request [\#290](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/290))
    * Added documentation for `non-functional requirements` and `glossary`sections. (Pull request [\#62](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/62))
    
* **Testing**:
   * Created unit tests for `find` feature (find command parser, find command). (Pull request [\#129](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/129))
   * Created integration tests for `find` feature (find command). (Pull request [\#129](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/129))
   * Created unit tests for `findexact` feature (findexact command parser, findexact command). (Pull request [\#159](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/159))
   * Created integration tests for `findexact` feature (findexact command). (Pull request [\#159](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/159))
   * Created unit tests for `note` feature (note command parser, note command). (Pull request [\#259](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/259))
   * Created integration tests for `note` feature (note command). (Pull request [\#259](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/259))
   * Created unit tests for `notedelete` feature (notedelete command parser, notedelete command). (Pull request [\#259](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/259))
   * Created integration tests for `notedelete` feature (notedelete command). (Pull request [\#259](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/259))
   * Created unit tests for `stockview` feature (stockview command parser, stockview command). (Pull request [\#265](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/265))
   * Created integration tests for `stockview` feature (stockview command). (Pull request [\#265](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/265))
   * Created tests for `FindUtil`. (Pull request [\#259](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/259))
   * Created tests for `Note` and `NoteIndex`. (Pull request [\#259](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/259))
   * Created unit tests for predicates package classes `LocationContainsKeywordsPredicate`,
   `NameContainsKeywordsPredicate`, `SerialNumberContainsKeywordsPredicate` and `SourceContainsKeywordsPredicate`. (Pull request [#\127](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/127))
