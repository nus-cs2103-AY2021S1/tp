---
layout: page
title: Pang Wai Kye's Project Portfolio Page
---

## Project: _bookmark_

_bookmark_ is a desktop book management application used for managing user added books to manage book records such as
last read page (bookmark), added goals and notes. A user will interact with bookmark through a GUI created with JavaFx and
invoke commands through a Command Line Interface style.

Given below are my contributions to the project.

* **New Feature**: Added the ability to edit a book in the library.
  * What it does: allows users to edit the details of a book, in any order of details and any number of details.
  * Justification: This feature improves the quality of life for our users as they no longer need to add a new updated book, and delete the
  previous outdated book. This reduces the amount of redundancies that the user has to do in order to modify at least one detail of a book.
  * Highlights: This feature was evolved upon the previous version of edit command in AB3. If additional properties of a book are added,
  this class and its relevant classes will have to be constantly updated to prevent program crashes and loss of information.

* **New Feature**: Added the ability to add and delete notes from a book.
  * What it does: allows the user to create a note and add it to a book. If you would like to remove the note, you can use the delete note command.
  * Justification: This feature improves the product significantly because a user can now add their own thoughts and analysis of a book easily
  without having to rely on other applications. This provides a centralised storage system for their book related matters, increasing the user experience.
  * Highlights: This required the use of the Jackson library and new classes in order to support the adding and deletion of notes into storage.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=pangpuncake&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

* **Project Management**
  * Aided in maintaining of the issue tracker, closing non-issues and ensuring relevant pull requests closed the corresponding issue.

* **Enhancements to existing features**:
  * Wrote additional tests for existing features to increase coverage (Pull request [\#85](https://github.com/AY2021S1-CS2103T-F13-2/tp/pull/85))

* **Documentation**:
  * User Guide:
    * Added documentation for the features `edit`, `note`, and `notedel` [\#59](https://github.com/AY2021S1-CS2103T-F13-2/tp/pull/59), [\#114](https://github.com/AY2021S1-CS2103T-F13-2/tp/pull/114)
    * Organised the examples into tables for clarity.
    * Added a prefix table for users to get a better understanding of the features.
    * Reformat minor details to keep a consistent format in each feature.
  * Developer Guide:
    * Added User stories.
    * Added implementation details of the `note` feature.
    * Added relevant UML diagrams for `note` feature.

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#84](https://github.com/AY2021S1-CS2103T-F13-2/tp/pull/84), [\#94](https://github.com/AY2021S1-CS2103T-F13-2/tp/pull/94), [\#111](https://github.com/AY2021S1-CS2103T-F13-2/tp/pull/111), [\#115](https://github.com/AY2021S1-CS2103T-F13-2/tp/pull/115), [\#119](https://github.com/AY2021S1-CS2103T-F13-2/tp/pull/119)
  * Reported bugs for other project team. (e.g. see [Issues](https://github.com/pangpuncake/ped/issues))
