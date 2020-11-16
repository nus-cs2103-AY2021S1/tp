---
layout: page
title: Sun Yitao's Project Portfolio Page
---

## Project: TAsker

 TAsker is a desktop address book application used for teaching Software Engineering principles. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

 Written below are my contributions to the project.

- **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=sun-yitao&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

- **Enhancements implemented**:

  - **Attend command**: Add attend command that enables for the marking of student's attendance. Student's attendance make use of a sorted treeset so prevent duplicate attendances from being added and also to present the attendances in chronological order.

  - **Export attendance command**: A command that exports students' attendance to a csv in `/data`, using Apache Commons CSV library. It has been optimised to run in a better runtime by using HashSet instead of TreeSet.

  - **Update help window** Update the link in help window to point to the correct UG

  - **Improve add command error handling** Improve error message to user to detect the error in the command and show why the error occurred and how to fix it: missing prefix, duplicate prefix or extra preamble.

  - **Unit tests**: Inserted JUnit tests for all active commands, updated existing tests accordingly and added new tests to increase test coverage of existing code.

- **Contributions to documentation**: Created the initial version of the UG

- **Contributions to DG**: Updated DG for `exportatt` command.

- **Project management**:
  - Attend and participate proactively in weekly project meetings.

- **Contributions to team-based tasks**:
  - **Review/mentoring contributions**: [\#47](https://github.com/AY2021S1-CS2103T-F11-1/tp/pull/47), [\#52](https://github.com/AY2021S1-CS2103T-F11-1/tp/pull/52)
  - **Found crucial bugs**: [bug1](https://github.com/AY2021S1-CS2103T-F11-1/tp/issues/49) & [bug2](https://github.com/AY2021S1-CS2103T-F11-1/tp/issues/48)
