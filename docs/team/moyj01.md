---
layout: page
title:Melvin's Project Portfolio Page
---

## Project: SWEe!

SWEe! is a desktop application used for managing CS2103T learning progress mainly through flashcards. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the ability to add diagram to the question of a flashcard. [#80](https://github.com/AY2021S1-CS2103T-T17-2/tp/pull/80)
  * What it does: allows the user to attach a diagram to the question of a flashcard
  * Justification: This feature allows users who are visual learners to attach diagram to the question of a flashcard to aid them in their learning.
  * Highlights: This enhancement involves incorporating an image into the application, ensuring that the file exist and that the file is of the correct type. Modification were also made to the `add` command and `edit` command to allow diagram to be added and edited.
  
* **New Feature**: Added the ability to quiz flashcard. [#96](https://github.com/AY2021S1-CS2103T-T17-2/tp/pull/96)
  * What it does: allows the user to enter quiz mode.
  * Justification: This feature allows users to test themselves on their content knowledge and prepare for upcoming examination or quiz.
  * Highlights: This feature involves coding the backend logic of the quiz mode. It also involves monitoring the user attempts to update the flashcard's statistics.
  
* **New Feature**: Added statistics command. [#106](https://github.com/AY2021S1-CS2103T-T17-2/tp/pull/106)
  * What it does: allows the user to check the statistics of the flashcard.
  * Justification: This feature allows users to check the statistics of the flashcard. The statistics of the flashcard are updated from the user's attempts in quiz mode. Users can see their number of attempts and the number of times they got the answer correct. This allows them to see which topics they are weaker in.
  * Highlights: This feature involves coding the backend logic and passing the information to UI so that the statistics can be displayed to the user.
  
* **Enhancements implemented**:
    1. Added test cases for `stats` command [#106](https://github.com/AY2021S1-CS2103T-T17-2/tp/pull/106)
    2. Modified test cases for `add` and `edit` commands. [#80](https://github.com/AY2021S1-CS2103T-T17-2/tp/pull/80)
    3. Modified various test cases for classes under the package storage. [#42](https://github.com/AY2021S1-CS2103T-T17-2/tp/pull/42) 
    4. Refactor storage to support flashcard storage [#42](https://github.com/AY2021S1-CS2103T-T17-2/tp/pull/42)

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=moyj01&tabRepo=AY2021S1-CS2103T-T17-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other)

* **Contribution to UG**:
  * Added documentation for `stats` and `quiz` feature [#104](https://github.com/AY2021S1-CS2103T-T17-2/tp/pull/104), [#105](https://github.com/AY2021S1-CS2103T-T17-2/tp/pull/105), [#166](https://github.com/AY2021S1-CS2103T-T17-2/tp/pull/166)
  * Added additional details about diagram in `add` feature [#87]((https://github.com/AY2021S1-CS2103T-T17-2/tp/pull/87))
  * Edited grammar and language of the document [#166](https://github.com/AY2021S1-CS2103T-T17-2/tp/pull/166), [#169](https://github.com/AY2021S1-CS2103T-T17-2/tp/pull/169)
  * Added step by step instructions with images to features [#169](https://github.com/AY2021S1-CS2103T-T17-2/tp/pull/169), [#98](https://github.com/AY2021S1-CS2103T-T17-2/tp/pull/98)
  
* **Contribution to DG**:
  * Added add feature implementation and associated diagrams [#87](https://github.com/AY2021S1-CS2103T-T17-2/tp/pull/87)

* **Contribution to Team-Based Tasks**:
  * Refactor java code to remove all instances of AB3 [#60](https://github.com/AY2021S1-CS2103T-T17-2/tp/pull/60)
  * Managed release of v1.3 trial on Github
  * Populate application with sample data [#211](https://github.com/AY2021S1-CS2103T-T17-2/tp/pull/211)
 
* **Review/mentoring contributions**:
  * PRs reviewed (with non-trivial review comments): [#89](https://github.com/AY2021S1-CS2103T-T17-2/tp/pull/89), [#94](https://github.com/AY2021S1-CS2103T-T17-2/tp/pull/94), [#101](https://github.com/AY2021S1-CS2103T-T17-2/tp/pull/101), [#109](https://github.com/AY2021S1-CS2103T-T17-2/tp/pull/109), [#172](https://github.com/AY2021S1-CS2103T-T17-2/tp/pull/172)
  * Reported bugs to other teams in the class: [#235](https://github.com/AY2021S1-CS2103T-T10-4/tp/issues/235), [#236](https://github.com/AY2021S1-CS2103T-T10-4/tp/issues/236), [#238](https://github.com/AY2021S1-CS2103T-T10-4/tp/issues/238), [#239](https://github.com/AY2021S1-CS2103T-T10-4/tp/issues/239)


