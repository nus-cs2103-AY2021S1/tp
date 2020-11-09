---
layout: page
title: Melvin's Project Portfolio Page
---

## Project: SWEe!

SWEe! is a desktop application used for managing CS2103T learning progress mainly through flashcards. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.


Given below are my contributions to the project.

**Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=moyj01&tabRepo=AY2021S1-CS2103T-T17-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other)

**Enhancements implemented**: 
* Added the ability to add a diagram to the question of a flashcard. [#80](https://github.com/AY2021S1-CS2103T-T17-2/tp/pull/80), [#249](https://github.com/AY2021S1-CS2103T-T17-2/tp/pull/249)
  * What it does: allows the user to attach a diagram to the question of a flashcard
  * Justification: This feature allows users who are visual learners to attach a diagram to the question of a flashcard to aid them in their learning.
  * Highlights: This enhancement involves incorporating an image into the application, ensuring that the file exists and its file type is supported by the application as well as making sure it has read permission. Modifications were also made to the `add` command and `edit` command to allow the diagram to be added and edited.
* Added the ability to quiz flashcards. [#96](https://github.com/AY2021S1-CS2103T-T17-2/tp/pull/96)
  * What it does: allows the user to enter quiz mode.
  * Justification: This feature allows users to test themselves on their content knowledge and prepare for upcoming examination or quiz.
  * Highlights: This feature involves coding the backend logic of the quiz mode. It also involves monitoring the user attempts in quiz mode to update the flashcard's statistics.  
<div style="page-break-after: always;"></div>

* Added statistics command. [#106](https://github.com/AY2021S1-CS2103T-T17-2/tp/pull/106)
  * What it does: allows the user to check the statistics of the flashcard.
  * Justification: This feature allows users to check the statistics of the flashcard. The statistics of flashcards are updated from the user's attempts in quiz mode. Users can see their number of attempts and the number of times they got the answer correct. This allows them to keep track of how well they did and know whether they have mastered the content properly.
  * Highlights: This feature involves coding the backend logic and passing the information to UI so that the statistics can be displayed to the user.
* Refactor storage to support flashcard storage [#42](https://github.com/AY2021S1-CS2103T-T17-2/tp/pull/42)
* Modified various test cases for classes under the package storage. [#42](https://github.com/AY2021S1-CS2103T-T17-2/tp/pull/42) 
* Modified test cases for `add` and `edit` commands. [#80](https://github.com/AY2021S1-CS2103T-T17-2/tp/pull/80)
* Added test cases for `stats` command [#106](https://github.com/AY2021S1-CS2103T-T17-2/tp/pull/106)

**Contribution to UG**:
* Added documentation for `stats` and `quiz` feature [#104](https://github.com/AY2021S1-CS2103T-T17-2/tp/pull/104), [#105](https://github.com/AY2021S1-CS2103T-T17-2/tp/pull/105), [#166](https://github.com/AY2021S1-CS2103T-T17-2/tp/pull/166)
* Added additional details about diagram in `add` feature [#87](https://github.com/AY2021S1-CS2103T-T17-2/tp/pull/87)
* Edited grammar and language of the document [#166](https://github.com/AY2021S1-CS2103T-T17-2/tp/pull/166), [#169](https://github.com/AY2021S1-CS2103T-T17-2/tp/pull/169)
* Added step by step instructions with images to features and annotated diagrams [#169](https://github.com/AY2021S1-CS2103T-T17-2/tp/pull/169), [#215](https://github.com/AY2021S1-CS2103T-T17-2/tp/pull/215), [#239](https://github.com/AY2021S1-CS2103T-T17-2/tp/pull/239), [#276](https://github.com/AY2021S1-CS2103T-T17-2/tp/pull/276)
  
**Contribution to DG**:
* Added add feature implementation and associated diagrams [#87](https://github.com/AY2021S1-CS2103T-T17-2/tp/pull/87), [#239](https://github.com/AY2021S1-CS2103T-T17-2/tp/pull/239)

**Contribution to Team-Based Tasks**:
* Maintaining the issue-tracker
* Refactor Java code to remove all instances of AB3 [#60](https://github.com/AY2021S1-CS2103T-T17-2/tp/pull/60)
* Populate application with sample data [#211](https://github.com/AY2021S1-CS2103T-T17-2/tp/pull/211)
* Managed release of v1.3 trial on Github [v1.3.trial](https://github.com/AY2021S1-CS2103T-T17-2/tp/releases/tag/v1.3.trial)
 
**Review/mentoring contributions**:
* PRs reviewed (with non-trivial review comments): [#89](https://github.com/AY2021S1-CS2103T-T17-2/tp/pull/89), [#94](https://github.com/AY2021S1-CS2103T-T17-2/tp/pull/94), [#101](https://github.com/AY2021S1-CS2103T-T17-2/tp/pull/101), [#109](https://github.com/AY2021S1-CS2103T-T17-2/tp/pull/109), [#172](https://github.com/AY2021S1-CS2103T-T17-2/tp/pull/172), [#189](https://github.com/AY2021S1-CS2103T-T17-2/tp/pull/189)
* Reported bugs to other teams during PE dry run: [Issue tracker](https://github.com/moyj01/ped/issues)


