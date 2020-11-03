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

* **Enhancements implemented**
  * **New Feature**: Added the ability to find existing items in the storage.
    * What it does: Allows the user to search for desired stock.
    * Justification: This feature improves the user experience as user can easily search for the details of
    existing items. Users can now search for their desired stocks quickly and conveniently,
    by using any of the fields (name, serial number, location stored, source of stock) to find
    their desired stock. Users have the convenience of searching for a whole list of stocks that are related,
    instead of only being able to search for one stock (via name).
    * Highlights: This enhancement comes with the ability to find stocks from not just the name of the stock
    but also other related fields such as serial number, location stored and source of stock.
    Search is also case-insensitive and any stock that contains the search term will be shown.

  * **New Feature**: Added the ability for advanced find for existing items in the storage.
    * What it does: Allows the user to apply additional filters to search for desired stock.
    * Justification: This feature improves the user experience as user can now accurately search
    for stocks using more filters, to narrow down the results.
    * Highlights: This enhancement comes with the ability to apply filters such as name of the stock,
    serial number, location stored and source of stock.
    Search is also case-insensitive and any stock that contains the search term will be shown.

  * **New Feature**: Added the ability to add optional notes to existing items in the storage.
    * What it does: Allows the user to add notes to stock.
    * Justification: This feature improves the user experience as user can now add optional notes
    to their stocks, to remind them of important details.
    * Highlights: This enhancement comes with the ability to add multiple notes to the stock.
    Users will be able to add notes to the stock at any time, without erasing their previous notes.

  * **New Feature**: Added the ability to delete one note or all notes from existing a stock in the storage.
    * What it does: Allows the user to delete notes from stock.
    * Justification: This feature improves the user experience as user can now delete notes that they
    no longer need from the stock.
    * Highlights: This enhancement comes with the ability to delete one note from the stock by providing
    the index of note, or delete all notes from the stock.
    Users will be able to delete whichever note they want from the stock at any time,
    without needing to delete all notes.

  * **New Feature**: Added the ability to view the details of a stock in a Stock View tab.
    * What it does: Allows the user to view details of a single stock.
    * Justification: This feature improves the user experience as the user can now view the details of
    a single entire stock on another tab away from the entire stock book.
    * Highlights: The Stock View tab live updates whenever the details of the stock being viewed is changed.
    The details of the stock remain in the tab even when the user clicks away to another tab. This allows the
    user to compare the details of a single stock with other stocks in the Data stockbook tab.
  
* **Git & Repository**:
  * Used GitHub Projects feature to create kanban boards to track user stories.

* **Reviews & Merging**:
  * Reviewed and tested teammates' pull requests.
  * Merged approved pull requests after thorough review and GitHub action checks.
  * Tests code to be merged before reviewing to detect functional flaws.
  * Thorough review includes checking if the code to be merged is standardized with
  teammates' codes (eg. error messages used).

* **Documentation**:
  * Responsible for managing documentation quality and content.
  * Read Me:
    * Header writer of README document.
  * User Guide:
    * Head writer and maintainer of team aspects of the user guide
      (eg. sections include introduction, quick start, definitions, features, commands)
    * Added documentation for the feature [`find`](https://ay2021s1-cs2103t-t15-3.github.io/tp/UserGuide.html#find-stocks-from-inventory-find).
    * Added documentation for the feature [`findexact`](https://ay2021s1-cs2103t-t15-3.github.io/tp/UserGuide.html#find-exact-stocks-from-inventory-findexact).
    * Added documentation for the feature [`note`](https://ay2021s1-cs2103t-t15-3.github.io/tp/UserGuide.html#adding-notes-to-stock-note).
    * Added documentation for the feature [`notedelete`](https://ay2021s1-cs2103t-t15-3.github.io/tp/UserGuide.html#deleting-notes-from-stock-notedelete).
    * Added documentation for the feature [`stockview`](https://ay2021s1-cs2103t-t15-3.github.io/tp/UserGuide.html#viewing-details-of-a-stock-stockview).
    * Checked team members' documentation.
    * Ensures that the documentation is informative and follows a relatively standardized format.
  * Developer Guide:
    * Added implementation for the Find and FindExact features, and the Note feature.
    This includes description of the feature, mechanism, example usage scenarios,
    sequence diagrams, activity diagrams, and design considerations.
    * Rebased Architecture, UI component, Logic component,
    Model component and Storage component UML diagrams from AB3 to Warenager.
    * In charge of UML diagrams for Model component.
    * Added documentation for the feature `find`.
    * Added documentation for the feature `findexact`.
    * Added documentation for the feature `note`.
    * Added documentation for the feature `notedelete`.
    * Added use cases for feature `find`.
    * Added use cases for the feature `findexact`.
    * Added documentation for `non-functional requirements` and `glossary`sections.
    * Added documentation for `instructions for manual testing`.
    * Checked team members' documentation.
