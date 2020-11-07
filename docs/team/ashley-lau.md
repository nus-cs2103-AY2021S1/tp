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
  * Refactored ab3 code to suit the classes needed in Warenager. (Pull request [\#64](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/64))
    * Added base classes such as name, quantity, source, serial number and location for stock.
  * Justification: Provided a base platform for teammates to work on their commands,
   without the need to refactor the whole AB3 code before they can start working on the project.

* **Features and Enhancements implemented**
  * **New Feature**: Added the ability to add items in the storage. (Pull request [\#64](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/64), [\#149](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/149))
    * What it does: Allows the user to add new stock into the storage.
    * Justification: This is a necessary feature as it would allow users to add their new stocks into the
    database and subsequently manage their stock from there.
    * Highlights: A unique serial number will be generated for the stock.
     The serial number will contain the source of the stock (eg. company we purchase the stock from)
     so that the serial number will provide a meaningful representation of the stock.
    
  * **New Feature**: Added the ability to convert the stock book into a readable csv file. (Pull request [\#143](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/143))
    * What it does: Allows the user to convert the stock book into a readable csv file. Users can sort 
    the stock in the order of their choice using the sort command before generating the csv file.
    * Justification: This is an enhancement feature as it would allow users have a physical/soft copy of the list of stock
    to refer to for book keeping purposes.
    * Highlights: User scan name the csv generated. The name to the csv file can only be alphanumeric characters.
  
  * **Enhancement Feature**: Added low quality field to the stock. (Pull request [\#184](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/184))
   * What it does: Allows users to add a low quantity field to the stock.
   * Justification: This is an enhancement feature which allows user to be notified when their stock is equal
    or fall below the low quantity field.
    * Highlights: User can set the low quantity field of their stock when adding the stock into the stock book.
     Stocks that are low in quantity will be highlighted in red in the application (Passed on the gui portion to dennis
     as he was in-charge of the gui).

* **Documentation**:
  * User Guide:
    * Added documentation for the feature `add`. (Pull request [\#117](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/117), [\#277](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/277), [\#286](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/286))
    * Added documentation for the feature `print`. (Pull request [\#156](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/156), [\#277](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/277), [\#286](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/286))
    * Checked documentation for any mistakes and updated them accordingly. (Pull request [\#169](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/169/files), [\#170](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/170/files),
     [\#277](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/277), [\#286](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/286))
    * Standardised the format of user guide. (Pull request [\#172](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/172/files), [\#286](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/286))
  * Developer Guide:
    * Added implementation details of the `add` feature. (Pull request [\#162](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/162), [\#269](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/269))
    * Added implementation details of the `print` feature. (Pull request [\#269](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/269))
    * Created sequence and activity diagram for `add` and `print` feature. (Pull request [\#269](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/269))
    * Added documentation for `product scope` section. (Pull request [\#55](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/55))
    * Added use cases for `add` feature. (Pull request [\#67](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/67), [\#117](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/117))
    * Added use cases for `print` feature. (Pull request [\#156](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/156))
    * Improved on the Model diagram (Pull request [\#286](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/286)). 

* **Testing**:
  * User Guide:
    * Added unit test for `add` feature (AddCommandParser). (Pull request [\#107](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/107), [\#149](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/149))
    * Added integration test for `add` feature (AddCommand). (Pull request [\#107](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/107), [\#149](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/149))
    * Added unit test for `print` feature (PrintCommandParser). (Pull request [\#156](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/156))
    * Added integration test for `print` feature (PrintCommand). (Pull request [\#156](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/156))
    * Added testing for bases classes in model package (Stock, Location, Quantity, Source, Name). (Pull request [\#102](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/102))
    * Added test classes for storage package (JsonAdaptedStock, JsonSerializableStockBook,
     JsonStockBookStorage, JsonUserPrefsStorage and StorageManager) (Pull request [\#191](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/191))

* **Tracking of task**
  * Ensures that all assigned tasks completed before deadline and TP task tracker badges updated correctly.

* **Community**
    * **Git & Repository**:
      * Used GitHub Projects feature to create kanban boards to track user stories.
      * Helped team members with git and GitHub functionalities.
    
    * **Reviews & Merging**:
      * Frequently checks, test and provides feedback regarding the pull request of the team members. (Pull request [\#62](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/62), 
       [\#66](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/66), [\#69](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/69), [\#89](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/89), 
       [\#100](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/100), [\#101](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/101), [\#105](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/105),
       [\#112](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/112), [\#147](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/147), [\#150](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/150), [\#151](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/151),
       [\#167](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/167), [\#171](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/171), [\#175](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/175), [\#177](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/177),
       [\#179](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/179), [\#187](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/187), [\#188](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/188), [\#189](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/189),
       [\#190](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/190), [\#208](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/208), [\#213](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/213), [\#221](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/221), 
       [\#222](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/222), [\#252](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/252), [\#254](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/254), [\#274](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/274),
       [\#276](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/276), [\#285](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/285))
      * Found many bugs in the pull requests and informed the respective authors. (Pull request [\#150](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/150), [\#151](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/151),
        [\#213](https://github.com/AY2021S1-CS2103T-T15-3/tp/pull/213)) (Informed the authors offline as well.)
