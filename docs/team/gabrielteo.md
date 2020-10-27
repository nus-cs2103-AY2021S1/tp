---
layout: page
title: Gabriel Teo Yu Xiang's Project Portfolio Page
---

## Project: Hospify

Hospify is an application to help maintain medical records.

Given below are my contributions to the project.

* **New Features**:
  * Added the ability to `find` a patient. (Pull request [\#47]())
    * What it does: Allows the user to find a patient.
    * Justification: This feature provides a convenient way to access patients' details.
    * Highlights: The implementation does not require changes to existing commands.
  * Added `Appointment` Class. (Pull requests [\#80](), [\#87](), [\#115](), [\#116]())
    * What it does: The user is now able to schedule and store new appointments for patients.
    * Justification: This feature provides added functionality to keep track of appointment for patients.
    * Highlights: The implementation requires changes to the UI and some classes.
  * Added the ability to `delete` an appointment from a patient. (Pull request [\#125]())
    * What it does: Allows the user to delete the specified appointment from a patient's list of appointments.
    * Justification: This feature allows the user to get rid of old appointments that have passed.
    * Highlights: The implementation does not require changes to existing commands.
  * Added the ability to `edit` an appointment of a patient. (Pull requests [\#128](), [\#158]())
    * What it does: Allows the user to edit the specified appointment of a patient.
    * Justification: This feature allows the user to reschedule a patient's appointment.
    * Highlights: The implementation does not require changes to existing commands.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&tabAuthor=GabrielTeo&tabRepo=AY2021S1-CS2103T-W15-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other)

* **Project management**:
  * Managed releases `v1.3(trial)` and `v1.3` (2 releases) on GitHub

* **Enhancements to existing features**:
  * Edited `find` command to take in both name and `NRIC`, returning all patients that match the keywords and `NRIC` (Pull request [\#47]())
  * Edited `delete` command to be able to delete by `NRIC`, in addition to just deleting by `INDEX` originally (Pull request [\#48]())
  * Updated the GUI color scheme for base colors (Pull request [\#50]())
  * Edited `help` command to display a table of commands and updated the link to Hospify user guide (Pull request [\#68]())
  * Edited `Appointment` class to include description field and more methods to get information needed for UI rendering of appointments (Pull request [\#116]())

* **Contributions to the User Guide**:
  * User Guide:
    * Updated `find` command to include new find by `NRIC` functionality (Pull request [\#71](), [\#170]())
    * Updated `delete` command to include new delete by `NRIC` functionality (Pull request [\#71]())
    * Updated product screenshots and included some new screenshots (Pull requests [\#71](), [\#157](), [\#170]())
    * Added documentation for the feature `addAppt` (Pull request [\#170]())
    * Added documentation for the feature `editAppt` (Pull request [\#170]())
    * Added documentation for the feature `deleteAppt` (Pull request [\#170]())

* **Contributions to the Developer Guide**:
  * Developer Guide: (Pull requests [\#21](), [\#141]())
    * Added Target user profile, value proposition, and user stories.
    * Added Use cases.
    * Added Non-functional requirements.
    * Added Glossary.
    * Added Appointment feature and implementation details. (Pull requests [\#94]())

* **Contributions to team-based tasks**:
  * Set up the product website
  * Set up team issue tracker and manage team issues and milestones
  * Maintaining issue tracker
  * Updating the Developer Guide
    * Target user profile, value proposition, user stories, use cases, non-functional requirements, glossary (Pull requests [\#21](), [\#141]())
  * Renaming the product to Hospify (Pull request [\#70]())
  * Changing the App window's bottom path display (Pull requests [\#70](), [\#124]())
  * Updating product home page and description to Hospify and relevant links to Java CI and codecov (Pull request [\#76]())

* **Community**:

* **Tools**:
