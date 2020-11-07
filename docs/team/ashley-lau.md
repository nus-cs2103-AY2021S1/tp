---
layout: page
title: Ashley's Project Portfolio Page
---

## Project: Warenager

Warenager is an **inventory application** to help tech-savvy warehouse managers to keep track of items in their warehouse.
It **optimizes management tasks** for warehouse managers including but not exhaustive of updating,
searching and ordering supplies, via Command Line Interface (CLI).

Given below are my contributions to the project.

* **Code Contribution**
  * Here is the link to [my code](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=ashley&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&tabAuthor=Ashley-Lau&tabRepo=AY2021S1-CS2103T-T15-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other)
  on the tP Code Dashboard.

* **Initial set-up of code**:
  * Refactored ab3 code to suit the classes needed in Warenager.
    * Added base classes such as name, quantity, source, serial number and location for stock.
  * Justification: Provided a base platform for teammates to work on their commands,
   without the need to refactor the whole AB3 code before they can start working on the project.

* **Enhancements implemented**
  * **New Feature**: Added the ability to add items in the storage.
    * What it does: Allows the user to add new stock into the storage.
    * Justification: This is a necessary feature as it would allow users to add their new stocks into the
    database and subsequently manage their stock from there.
    * Highlights: A unique serial number will be generated for the stock.
     The serial number will contain the source of the stock (eg. company we purchase the stock from)
     so that the serial number will provide a meaningful representation of the stock.
    
  * **New Feature**: Added the ability to convert the stock book into a readable csv file.
    * What it does: Allows the user to convert the stock book into a readable csv file. Users can sort 
    the stock in the order of their choice using the sort command before generating the csv file.
    * Justification: This is an enhancement feature as it would allow users have a physical/soft copy of the list of stock
    to refer to for book keeping purposes.
    * Highlights: User scan name the csv generated. The name to the csv file can only be alphanumeric characters.
  
  * **Enhancement Feature**: Added low quality field to the stock.
   * What it does: Allows users to add a low quantity field to the stock.
   * Justification: This is an enhancement feature which allows user to be notified when their stock is equal
    or fall below the low quantity field.
    * Highlights: User can set the low quantity field of their stock when adding the stock into the stock book.
     Stocks that are low in quantity will be highlighted in red in the application (Passed on the gui portion to dennis
     as he was in-charge of the gui).
  
* **Git & Repository**:
  * Used GitHub Projects feature to create kanban boards to track user stories.
  * Helped team members with git and GitHub functionalities.

* **Reviews & Merging**:
  * Frequently checks, test and provides feedback regarding the pull request of the team members.
  * Found many bugs in the pull requests and informed the respective authors.

* **Documentation**:
  * User Guide:
    * Added documentation for the feature `add`.
    * Added documentation for the feature `print`.
    * Checked documentation for any mistakes and updated them accordingly.
    * Standardised the format of user guide.
  * Developer Guide:
    * Added implementation details of the `add` feature.
    * Added implementation details of the `print` feature.
    * Created sequence and activity diagram for `add` and `print` feature.
    * Added documentation for `product scope` section.
    * Added use cases for `add` feature.
    * Added use cases for `print` feature.

* **Testing**:
  * User Guide:
    * Added unit test for `add` feature (AddCommandParser).
    * Added integration test for `add` feature (AddCommand)
    * Added unit test for `print` feature (PrintCommandParser).
    * Added integration test for `print` feature (PrintCommand).
    * Added testing for bases classes in model package (Stock, Location, Quantity, Source, Name).
    * Added test classes for storage package (JsonAdaptedStock, JsonSerializableStockBook,
     JsonStockBookStorage, JsonUserPrefsStorage and StorageManager)

* **Tracking of task**
  * Ensures that all assigned tasks completed before deadline and TP task tracker badges updated correctly.

