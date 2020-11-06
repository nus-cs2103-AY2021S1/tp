---
layout: page
title: Song Guan's Project Portfolio Page
---

## Project: SWEe!

SWEe! is a desktop application used for managing CS2103T learning progress mainly through flashcards. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=sgn00&tabRepo=AY2021S1-CS2103T-T17-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code)

* **New Feature**: Added the ability to review flashcards. [#37](https://github.com/AY2021S1-CS2103T-T17-2/tp/pull/37), [#94](https://github.com/AY2021S1-CS2103T-T17-2/tp/pull/94)
  * What it does: allows the user to enter an interactive review mode where the user can use arrow keys
  to show a flashcard's answer, move to the next flashcard or move to previous flashcard.
  * Justification: This feature allows users to review their flashcards in a sandbox mode, allowing them to study the flashcards
  more easily.
  * Highlights: This enhancement involved designing a completely new UI screen. It also involved UI integration with the logic component of the app and was challenging as 
 no similar feature was present originally in AB3. Also rewrote the review UI code in a fashion such that it could be extended easily by the quiz feature.

* **New Feature**: Added the ability to view flashcards. [#89](https://github.com/AY2021S1-CS2103T-T17-2/tp/pull/89), [#111](https://github.com/AY2021S1-CS2103T-T17-2/tp/pull/111)
  * What it does: allows user to view a flashcard in the view pane with or without the answer shown.
  * Justification: This feature allows users to see the flashcards in detail with the full question, answer, diagrams and notes.
  * Highlights: This enhancement involved mainly designing the UI to ensure that the view pane can accommodate to different
  screen sizes, as well as different lengths of fields in the flashcard.
  
* **New Feature**: Added rating to flashcards. [#74](https://github.com/AY2021S1-CS2103T-T17-2/tp/pull/74)
  * What it does: allows user to specify a star rating attribute to the flashcard.
  * Justification: The star rating is intended to mirror the CS2103 website which provides star rating for different objectives.
  This allows users to map their learning from CS2103 website over to our flashcards better.
  * Highlights: Besides changing the flashcard model, had to modify add, edit commands and the UI to accommodate the flashcard's rating.
  
* **UI Implementation**: Stats feature. [#109](https://github.com/AY2021S1-CS2103T-T17-2/tp/pull/109)
  * Handled the UI portion of the stats command by displaying the relevant information in the view pane.
  * Highlights: Displayed information using a piechart for better visualization.

* **UI Implementation**: General UI of the app. [#56](https://github.com/AY2021S1-CS2103T-T17-2/tp/pull/56), [#89](https://github.com/AY2021S1-CS2103T-T17-2/tp/pull/89), [#111](https://github.com/AY2021S1-CS2103T-T17-2/tp/pull/111), [#109](https://github.com/AY2021S1-CS2103T-T17-2/tp/pull/109)
  * Updated the UI from AB3 to use a 2 column format.
  * Updated the list panel to use rounded borders.
  
* **Refactor**: Refactored add command from AB3. [#28](https://github.com/AY2021S1-CS2103T-T17-2/tp/pull/28)
  * Refactored add command to support flashcards' question and answer.
  * Highlights: Refactored associated tests and wrote new tests.

* **Contribution to UG**:
  * Added documentation for `review` and `view` feature [#101](https://github.com/AY2021S1-CS2103T-T17-2/tp/pull/101), [#86](https://github.com/AY2021S1-CS2103T-T17-2/tp/pull/86)
  * Added additional details about rating in `add` feature
  * Edited grammar and formatting of the document [#168](https://github.com/AY2021S1-CS2103T-T17-2/tp/pull/168)
  * Added common input fields table to UG [#189](https://github.com/AY2021S1-CS2103T-T17-2/tp/pull/189)
  
* **Contribution to DG**:
  * Added review feature implementation and associated diagrams [#190](https://github.com/AY2021S1-CS2103T-T17-2/tp/pull/190), [#78](https://github.com/AY2021S1-CS2103T-T17-2/tp/pull/78)
  * Added Effort section [#193](https://github.com/AY2021S1-CS2103T-T17-2/tp/pull/193)
  
* **Contribution to Team-Based Tasks**:
  * Set up the Github team org/repo
  * Managed milestone creation and tracking on Github
  * Managed release of v1.2 and v1.3 on Github
  * Changed product icon
  

* **Review/mentoring contributions**:
  * PRs reviewed (with non-trivial review comments): [#72](https://github.com/AY2021S1-CS2103T-T17-2/tp/pull/72), [#88](https://github.com/AY2021S1-CS2103T-T17-2/tp/pull/88), [#106](https://github.com/AY2021S1-CS2103T-T17-2/tp/pull/106), [#162](https://github.com/AY2021S1-CS2103T-T17-2/tp/pull/162), [#169](https://github.com/AY2021S1-CS2103T-T17-2/tp/pull/169)
  * Reported bugs to other teams in the class: [#258](https://github.com/AY2021S1-CS2103T-F12-1/tp/issues/258), [#255](https://github.com/AY2021S1-CS2103T-F12-1/tp/issues/255), [#254](https://github.com/AY2021S1-CS2103T-F12-1/tp/issues/254)


