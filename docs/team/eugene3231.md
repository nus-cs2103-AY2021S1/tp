---
layout: page
title: Eugene's Project Portfolio Page
---

## Project: CliniCal

CliniCal is a desktop app that allows doctors to manage patient records and schedule upcoming appointments. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 20 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the ability to `undo`/`redo` previous commands.
  * What it does: Allows the user to undo all previous commands that modify the patient database, one at a time. Preceding undo commands can be reversed by using the redo command.
  * Justification: This feature improves the product significantly because a user can make mistakes in commands and the app should provide a convenient way to rectify them.
  * Highlights: The implementation was demanding as it required changes to existing commands and continuous updates for new commands added at each iteration. This included new commands such as adding, editing, deleting of visits and appointments added in `v1.3`.
  * Credits:
    1. Existing AB3 codebase: [AddressBook Level-3](https://github.com/se-edu/addressbook-level3)
    2. Design pattern consideration: [Best Practice for Undo Redo implementation](https://stackoverflow.com/questions/1915907/best-practice-for-undo-redo-implementation)
<br/><br/>
* **New Feature**: Added `editvisit` and `deletevisit` commands to edit and delete an existing visitation log.
  * What it does: Allows the user to modify or remove the existing visitation logs seen from the profile window.
  * Justification: This feature allows the user to amend visitation log details when the user makes a mistake. Also allows the user to organize the visitation logs and remove redundant visitations.
  * Highlights: Also implemented the functionality of a new popup window to modify visitation details, as stated below. And integrated undo and redo functions for the visitation commands.
  * Credits: 
    1. Implementation of `editvisit` and `deletevisit`: [AddressBook Level-3](https://github.com/se-edu/addressbook-level3)
<br/><br/>
* **New Feature**: Implemented a pop-up window to add and edit a visitation log.
  * What it does: Allows the user to add or modify visit details such as diagnosis, prescription and comments in a user-friendly interface.
  * Justification: This feature improves the product significantly as there is a new interface for the user to modify visitation details other than the main GUI, improving user experience.
  * Highlights: Created UI mockups for the team and adapted this pop-up window for use in `addvisit` and `editvisit` commands.
  * Credits: 
    1. Creating a new pop-up window: [How to create a popup window in javafx](https://stackoverflow.com/questions/22166610/how-to-create-a-popup-windows-in-javafx), [Adding a button in a new scene](http://tutorials.jenkov.com/javafx/button.html), [Generating a new window](https://www.tutorialspoint.com/javafx/javafx_application.htm), [VISIT](https://github.com/AY1920S1-CS2103T-F12-2/main)
    1. Using text fields in a pop-up window: [Building pop-up window with text fields](https://docs.oracle.com/javafx/2/ui_controls/text-field.htm), [Creating a Form](https://docs.oracle.com/javafx/2/get_started/form.htm), [Handle text input on form submit](https://www.callicoder.com/javafx-registration-form-gui-tutorial/), [VISIT](https://github.com/AY1920S1-CS2103T-F12-2/main)
    1. Setting keyboard shortcuts for shifting focus to next text box: [Keyboard Navigation](https://wiki.openjdk.java.net/display/OpenJFX/Keyboard+Navigation) 
<br/><br/>
* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=eugene3231)
<br/><br/>
* **Project management**:
  * Managed release `v1.3` on GitHub 
<br/><br/>
* **Enhancements to existing features**:
  * Improved the GUI for the main application window. ([\#104](https://github.com/AY2021S1-CS2103T-W11-4/tp/pull/104), [\#252](https://github.com/AY2021S1-CS2103T-W11-4/tp/pull/252))
  * Updated the GUI for the Profile Window. ([\#149](https://github.com/AY2021S1-CS2103T-W11-4/tp/pull/149))
<br/><br/>
* **Enhancements to new features**:
  * Improved the GUI for the Visitation Log Window. ([\#149](https://github.com/AY2021S1-CS2103T-W11-4/tp/pull/149))
  * Wrote additional tests for `undo`, `redo`, `AddVisitParser`, `ProfileCommandParser`, 
  `DeleteVisitCommand`, `DeleteVisitCommandParser`, `EditVisitCommand`, `EditVisitCommandParser`classes. ([\#160](https://github.com/AY2021S1-CS2103T-W11-4/tp/pull/160), [\#166](https://github.com/AY2021S1-CS2103T-W11-4/tp/pull/166))
<br/><br/>
* **Documentation**:
  * User Guide:
    * Did cosmetic tweaks to overall table of contents and existing documentation of feature: `Retrieving past commands using arrow keys`. ([\#112](https://github.com/AY2021S1-CS2103T-W11-4/tp/pull/112))
    * Did overall formatting tweaks for the document by reorganizing content into respective categories. ([\#118](https://github.com/AY2021S1-CS2103T-W11-4/tp/pull/118), [\#119](https://github.com/AY2021S1-CS2103T-W11-4/tp/pull/119))
    * Added documentation for the features `undo`, `redo`, `editvisit` and `deletevisit` ([\#160](https://github.com/AY2021S1-CS2103T-W11-4/tp/pull/160))
    * Added formatting guidelines for the user to follow. ([\#173](https://github.com/AY2021S1-CS2103T-W11-4/tp/pull/173))
    * Added a section on user interface to introduce user to the GUI. ([\#240](https://github.com/AY2021S1-CS2103T-W11-4/tp/pull/240))
  * Developer Guide:
    * Updated implementation details and UML diagrams for `undo` and `redo` features. ([\#55](https://github.com/AY2021S1-CS2103T-W11-4/tp/pull/55), [\#101](https://github.com/AY2021S1-CS2103T-W11-4/tp/pull/101)).
    * Improved labelling of headings and subheadings for better user readability. ([\#75](https://github.com/AY2021S1-CS2103T-W11-4/tp/pull/75))
    * Added labelling of all figures used in the developer guide. ([\#101](https://github.com/AY2021S1-CS2103T-W11-4/tp/pull/101))
    * Added implementation details for visitation log features and added UML diagrams for `addvisit`. ([\#261](https://github.com/AY2021S1-CS2103T-W11-4/tp/pull/261))
    * Added use cases, manual testing instructions for `editvisit` and `deletevisit` features. ([\#277](https://github.com/AY2021S1-CS2103T-W11-4/tp/pull/277))
<br/><br/>
* **Contributions to team-based tasks** :
  * Helped to track issues discussed in team meetings and transcribe them onto GitHub for ease of reference. ([\#93](https://github.com/AY2021S1-CS2103T-W11-4/tp/issues/93), [\#123](https://github.com/AY2021S1-CS2103T-W11-4/tp/issues/123), [\#257](https://github.com/AY2021S1-CS2103T-W11-4/tp/issues/257))
<br/><br/>
* **Community**:
  * Contributed to forum discussions: ([\#159](https://github.com/nus-cs2103-AY2021S1/forum/issues/159))
  * Reported bugs and suggestions for other teams in the class (during Practical Exam - Dry Run): [Issues Link](https://github.com/eugene3231/ped/issues)
<br/><br/>
* **Tools**:
  * Integrated a third party library (JFoenix) to the project improving the aesthetics of tabs in GUI ([\#252](https://github.com/AY2021S1-CS2103T-W11-4/tp/pull/252))


