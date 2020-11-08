---
layout: page
title: Ge Wai Lok's Project Portfolio Page
---

## Project: Covigent

Covigent is a desktop application that is used to aid hotel staff handle the Covid-19 situation better. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the ability to search for patients based on name or temperature range.
  * What it does: allows user to search for patient based on the input, it can be either a sub-name of the patient's name, or a temperature range. All patients matching the criteria will be displayed.
  * Justification: This feature improves the product by providing the user to find the patients in mind in a more efficient way.

* **New Feature**: Added the ability to search for tasks based on due date.
  * What it does: allows user look for tasks before a given due date(deadline).
  * Justification: This feature improves the product by providing the user to prioritise the tasks. The users may use the search result to decide what task to complete first.
  * Highlights: This feature displays the search result based on the Room number, tasks are grouped to be displayed under room number in the task tab.

* **New Feature**: Added the ability to list all tasks defined by user
  * What it does: allows user to look at all tasks defined by user as the UI can change when adding, searching or deleting tasks.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=raymondge)

* **Enhancements to existing features**:
  * Wrote additional test cases for temperatureRange class.

* **Enhancements Code to aid Feature**:
  * Added most of the implementation of the UI and storage for task.
  * Improved css for task to make the UI for tasks show one scollbar only instead of showing two scollbar, the problem was due to the implementation of task UI using the room UI.

* **Documentation**:
    * User Guide:
      * Added documentation for the features `searchpatient` , `listpatient`, `searchtask` and `listtask`
      * Added documentation for the section `About this Guide`
    * Developer Guide:
      * Added implementation details of the `searchpatient` , `listpatient`, `searchtask` and `listtask` feature.
      * Added documentation and UML diagram for the section `UI`

  * **Community**:
    * PRs reviewed (with non-trivial review comments): [\#72](https://github.com/AY2021S1-CS2103T-W12-1/tp/pull/72), [\#79](https://github.com/AY2021S1-CS2103T-W12-1/tp/pull/79), [\#80](https://github.com/AY2021S1-CS2103T-W12-1/tp/pull/80)
