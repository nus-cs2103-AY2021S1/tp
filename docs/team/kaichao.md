---
layout: page
title: Kai Chao's Project Portfolio Page
---

## Project: Warenager

**Warenager** is an **inventory management application** to help warehouse managers
of small scale companies keep track of stocks in their warehouse.
It **optimizes inventory management tasks** for warehouse managers including but not
exhaustive of **updating, searching and sorting stocks** via Command Line Interface (CLI),
while still having the benefits of a Graphical User Interface (GUI).

Given below are my contributions to the project.

* **Code Contribution**
  * Here is the link to [my code](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=kc-98&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)
  on the tP Code Dashboard.

* **Enhancements implemented**
    * **New Feature**: Added the ability to delete existing stock(s) from the inventory.
      * What it does: Allows the user to delete the chosen stock(s).
      * Justification: This feature enables the user to delete any unwanted or unused stock, so that
        the storage will not be clogged up with outdated stock.
      * Highlights: Multiple stocks are able to be deleted in one input for faster usage. Status of the deletion will be
        shown to the user, informing them which stocks are being successfully deleted and which are not.
      
    * **New Feature**: Added the ability to generate a unique serial number for each newly added stock.
      * What it does: Allows the user to perform commands using serial numbers.
      * Justification: This feature eliminates any confusion that can arise when users reuse field attributes when
       adding a new stock. Users can then differentiate field inputs clearly when using Warenager commands.
      * Difficulty: This feature is hard to implement due to the unfamiliarity of Jackson and JSON formats, and the
        deeply abstracted storage classes to replicate. 
    
    * **New Feature**: Added the ability to display various statistics through a pie chart.
      * What it does: Allows the user to view relevant statistics for their entire inventory.
      * Justification: Often statistics are needed for inventory evaluation. The quick overview that this feature
        provides will help the user obtain the required data for analysis.
      * Highlights: Statistics are being updated after each command is executed, making it faster to view
        statistics instead of having the input the command lines again.

* **Reviews & Merging**:
  * Ensured code quality when reviewing pull requests, before approving merge.

* **Project management**:
  * Ensured team meetings cover the requirements for the week and everyone attends the meeting.
  
* **UI**: 
  * Created base light theme for Warenager.
  * Added tabbing feature to toggle between tabs easily through CLI.
  
* **Testing**:
  * Created unit tests for `delete`, `stats`, `clear` and `tab` parsers.
  * Created unit tests for `delete`, `stats` and `clear` features.
  * Created utility classes for testutil (TypicalSerialNumberSets, SerialNumberSetBuilder, SerialNumberSetsBookBuilder, StockUtil)
  * Created unit tests for most classes in **model** (including ModelManagerTest, StockBookTest, UserPrefsTest, SerialNumberTest, SerialNumberSetTest, UniqueSerialNumberSetListTest)
  * Reviewed other team members' tests and ensure the tests are adequate and working.  

* **Documentation**:
  * User Guide:
    * Converted the draft user guide content to markdown file format.
    * Updated the documentation for `delete`, `stats`, `clear`, `tab` features.
    * Added definitions and label notations guide, and FAQ section.
  * Developer Guide:
    * Updated NFR and Appendix section for Warenager.
    * Added MSS for `delete`, `stats`, `clear`, `tab` features.
    * Added use cases for `delete`, `stats`, `clear`, `tab` features.
    * Added implementation details of the `stats` feature.
    * Added UML diagrams for storage and `stats` feature.
