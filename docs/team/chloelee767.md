---
layout: page
title: Chloe Lee Ke Er's Project Portfolio Page
---

**ResiReg** (**Res**idential **Reg**ulation) is a desktop app designed to help admin staff manage housing in Residential Colleges (RCs) in NUS. ResiReg has a GUI, but users can interact with ResiReg entirely using CLI-style commands. ResiReg is written in Java, with the GUI created using JavaFX.

Given below are a summary of my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=chloelee767&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

* **New Feature**: Extended the `help` command to show the documentation for any command.
  * What it does: The command `help <command_word>` (eg. `help students`), shows the user a help message explaining what the command does and how to use it. If only `help` is entered, a list of each command and its purpose is shown.
  * Justification: Improves the user experience, especially for new users, as users can check what a command does and how to use a command without leaving the application.
  * Highlights: This enhancement affected all existing and subsequently added commands, as the help command requires knowledge of all commands in the application. An in-depth analysis of design alternatives was needed.

* **New Feature**: Added the ability to switch between 2 views modes for the students list and rooms list: (1) students and rooms side by side in a single tab and (2) students and rooms each in separate tabs. 
  * What it does: The user can use the command `toggle-split` to toggle the UI between these 2 views modes. The user can use the same commands (`students`, `rooms`) to navigate to the respective tabs in both view modes.
  * Justification: Different views are suited for different tasks. For example, a side by side view of rooms and students is more convenient when performing room allocations, while a separate for rooms would be more useful when performing tasks related solely to rooms (eg. tagging rooms that require renovation). 
  * Highlights: This feature affects all tabs that would be added in the future. A careful consideration of design alternatives was needed to figure out how to avoid excessive code repetition for the 2 different view modes.

* **New Feature**: Added the ability to filter the list of rooms shown using various criteria.
  * What it does: Allows the user to limit the list of rooms being shown using the following criteria: floor, room number, room type and vacancy. Rooms can be filtered based on multiple criteria and multiple options for each criterion can be specified (eg. vacant rooms on either the 11th or 12th floor).
  * Justification: It is useful for an admin managing a RC with many rooms to limit the list of rooms shown to just those fulfilling a certain criteria based on the task at hand eg. showing only vacant rooms while performing room allocations.
  * Highlights: The `RoomFilter` class that was created was later used as a template for the `StudentFilter` class used to implement filtering of the students list.

* **New Feature**: Added the ability for filters on the room and student list to be persisted after commands.
  * What it does: If the user say, filters the room list and then performs an operation which modifies ResiReg, the updated room list shown will still be filtered (and not reset to show all rooms like in AB3). Eg. if a user filters the room list so that only vacant rooms are shown, and then allocates a room, the updated room list will still show only vacant rooms.
  * Justification: There are many repetitive tasks which are easier to perform with filtered room and student lists. Eg. while performing room allocations, it is easier if only vacant rooms are shown. It would be inconvenient if the user had to re-enter the command to show only vacant rooms after each allocation.
  * Highlights: It was challenging to figure out how to ensure that filters that relied on checking other entities in ResiReg are correctly reapplied when ResiReg updates (eg. ensuring that a filter to show only vacant rooms, which requires checking the allocation list, is correctly re-applied after adding or removing a room allocation). 
  
* **Enhancements to existing features**:
  * Theming of tab bar to match rest of the UI: [#82](https://github.com/AY2021S1-CS2103-T16-3/tp/pull/82)
  * Improved user experience by ensuring the command box is focused upon startup: [#82](https://github.com/AY2021S1-CS2103-T16-3/tp/pull/82)
  * Improved code quality by extracting out several repeated methods and classes:  [#178](https://github.com/AY2021S1-CS2103-T16-3/tp/pull/178), [#105](https://github.com/AY2021S1-CS2103-T16-3/tp/pull/105)

* **Contributions to user guide**:
  * Documentation for the following commands: `help`, `toggle-split`, `rooms`

* **Contributions to developer guide**:
  1. Wrote use cases UC01 to UC06, UC11-15.
  2. Updated diagrams and descriptions for the [logic component](../DeveloperGuide#logic-component)
  3. [Discussion of implementation of the help command](../DeveloperGuide#help-command).
  4. Diagrams: updated class diagram and sequence diagram for logic component (under point 2), added sequence diagram for HelpCommand (under point 3). 
  5. Added manual testing instructions for the following commands: `help`, `add-room`, `edit-room`, `delete-room`

* **Team tasks**:
  * Did part of the initial morphing of AB3's user guide to our product's user guide: [#3](https://github.com/AY2021S1-CS2103-T16-3/tp/pull/3)
  * Enabled assertions for `gradle run`: [#98](https://github.com/AY2021S1-CS2103-T16-3/tp/pull/98)
  * Made release for [v1.3.1](https://github.com/AY2021S1-CS2103-T16-3/tp/releases/tag/v1.3.1) for Practical Exam Dry Run

* **Community**:
  * PRs reviewed (with non-trivial review comments): [#191](https://github.com/AY2021S1-CS2103-T16-3/tp/pull/191)
