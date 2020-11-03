---
layout: page
title: Song Guan's Project Portfolio Page
---

## Project: SWEe!

SWEe! is a desktop application used for managing CS2103T learning progress mainly through flashcards. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the ability to review flashcards.
  * What it does: allows the user to enter an interactive review mode where the user can use arrow keys
  to show a flashcard's answer, move to the next flashcard or move to previous flashcard.
  * Justification: This features allows users to review their flashcards in a sandbox mode, allowing them to study the flashcards
  more easily.
  * Highlights: This enhancement involved designing a completely new UI screen. It also involved much UI integration with the logic component of the app and was challenging as 
  no similar feature was present originally in AB3. Also rewrote the review UI code in a fashion such that it could be extended easily by the quiz feature.

* **New Feature**: Added the ability to view flashcards.
  * What it does: allows user to view a flashcard in the view pane with or without the answer shown.
  * Justification: This features allows users to see the flashcards in detail with the full question, answer, diagrams and notes.
  * Highlights: This enhancement involved mainly designing the UI to ensure that the view pane can accommodate to different
  screen sizes, as well as different lengths of fields in the flashcard.
  
* **New Feature**: Added rating to flashcards.
  * What it does: allows user to specify a star rating attribute to the flashcard.
  * Justification: The star rating is intended to mirror the CS2103 website which provides star rating for different objectives.
  This allows user to map their learning from CS2103 website over to our flashcards better.
  * Highlights: Besides changing the flashcard model, had to modify add, edit commands and the UI to accommodate the flashcard's rating.
  
* **UI Implementation**: Stats feature.
  * Handled the UI portion of the stats command by displaying the relevant information in the view pane.
  * Highlights: Displayed information using a piechart for better visualization.

* **UI Implementation**: General UI of the app.
  * Updated the UI from AB3 to use a 2 column format.
  * Updated the list panel to use rounded borders.
  
* **Refactor**: Refactored add command from AB3.
  * Refactored add command to support flashcards' question and answer.
  * Highlights: Refactored associated tests and wrote new tests.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=sgn00&tabRepo=AY2021S1-CS2103T-T17-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code)

* **Contribution to UG**:
  * Added documentation for `review`, `quiz` and `view` feature
  * Added additional details about rating in `add` feature
  * Edited grammar of the document
  
* **Contribution to DG**:
  * Added review feature implementation and associated diagrams
  
* **Contribution to Team-Based Tasks**:
  * Set up the Github team org/repo
  * Managed milestone creation and tracking on Github
  * Managed release of v1.2 and v1.3 on Github
  * Changed product icon
  

* **Review/mentoring contributions**:
  * PRs reviewed (with non-trivial review comments): [#72](https://github.com/AY2021S1-CS2103T-T17-2/tp/pull/72), [#88](https://github.com/AY2021S1-CS2103T-T17-2/tp/pull/88), [#106](https://github.com/AY2021S1-CS2103T-T17-2/tp/pull/106), [#162](https://github.com/AY2021S1-CS2103T-T17-2/tp/pull/162), [#169](https://github.com/AY2021S1-CS2103T-T17-2/tp/pull/169)
  * Reported bugs to other teams in the class: [#258](https://github.com/AY2021S1-CS2103T-F12-1/tp/issues/258), [#255](https://github.com/AY2021S1-CS2103T-F12-1/tp/issues/255), [#254](https://github.com/AY2021S1-CS2103T-F12-1/tp/issues/254)


