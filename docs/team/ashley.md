---
layout: page
title: Ashley's Project Portfolio Page
---

## Project: Warenager

Warenager is an **inventory application** to help tech-savvy warehouse managers to keep track of items in their warehouse.
It **optimizes management tasks** for warehouse managers including but not exhaustive of updating,
searching and ordering supplies, via Command Line Interface (CLI).

Given below are my contributions to the project.

* **Initial set-up of code**:
  * Refactored ab3 code to suit the classes needed in Warenager.
    * Added base classes such as name, quantity, source, serial number and location for stock.
  * Provided a base platform for teammates to work on their commands, without the need to refactor the whole AB3 code.

* **New Feature**: Added the ability to add items in the storage.
  * What it does: Allows the user to add new stock into the storage.
  * Justification: This is a necessary feature as it would allow users to add their new stocks into the
  database and subsequently manage their stock from there.
  * Highlights: A unique serial number will be generated for the stock.
   There are plans to incorporate the company name into the serial number after we implement a feature which
   allows first time user to input their company name.

* **New Feature**: Added the ability to convert the stock book into a readable csv file.
  * What it does: Allows the user to convert the stock book into a readable csv file. Users can sort 
  the stock in the order of their choice using the sort command before generating the csv file.
  * Justification: This is an enhancement feature as it would allow users have a physical/soft copy of the list of stock
  to refer to for book keeping purposes.
  * Highlights: User scan name the csv generated. The name to the csv file can only be alphanumeric characters.

* **Git & Repository**:
  * Used GitHub Projects feature to create kanban boards to track user stories.
  * Helped team members with git and GitHub functionalities.
  * Frequently checks, test and provides feedback regarding the pull request of the team members.

* **Documentation**:
  * User Guide:
    * Added documentation for the feature `add`.
    * Added documentation for the feature `print`.
  * Developer Guide:
    * Added implementation details of the `add` feature.
    * Added implementation details of the `print` feature.
    * Added documentation for `product scope` section.

* **Testing**:
  * User Guide:
    * Added test classes for `add` feature.
    * Added test classes for `print` feature.
    * Added testing for bases classes in model package.
    * Added test classes for storage package (except classes related to serial numbers)

* **Tracking of task**
  * Ensures that all assigned tasks completed before deadline and TP task tracker badges updated correctly.

