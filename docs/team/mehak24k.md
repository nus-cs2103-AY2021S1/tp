---
layout: page
title: Mehak Virdi's Project Portfolio Page
---

## Project: DSAce

DSAce is a desktop definition book application used for teaching Data Structures and Algorithms principles. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the ability to flip a flashcard.
  * What it does: allows the user to flip a flashcard to either show or hide the definition of the term in the flashcard.
  * Justification: This feature improves the product significantly because a user should be able to view or hide the definition of the term in the flashcard as required to help them memorise and test themselves.
  * Highlights: This enhancement affects the GUI and state of the definition book significantly. It required an in-depth analysis of design alternatives. The implementation too was challenging as it required changes to the properties of a flashcard and the GUI of the application.
  * Credits: *{referred to: https://nus-cs2103-ay2021s1.github.io/tp/tutorials/AddRemark.html}*

* **New Feature**: Implemented GUI for the quiz mode in the application.
    * What it does: Enables the user to have a separate interface to practice quiz questions.
    * Justification: This feature improves the product significantly because a user should be able to practice quiz questions to test their understanding of the subject and be exam-ready.
    * Highlights: This enhancement affects the GUI and state of the definition book significantly. It required an in-depth analysis of design alternatives. The implementation too was challenging as it required changes to the model and logic of the application to maintain the state of the application properly to account for both the flashcard and quiz modes.

* **New Feature**: Implemented the `start attempt` command under the quiz mode.
    * What it does: Enables the user to initiate an attempt in answering the quiz questions.
    * Justification: This feature improves the product significantly because a user should be able to practice quiz questions under specific attempts to simulate a mock-test.
    * Highlights: This enhancement affects the state of the definition book.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&tabAuthor=mehak24k&tabRepo=AY2021S1-CS2103-T14-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code)

* **Project management**:
  * Managed release `v1.3b` on GitHub

* **Documentation**:
  * User Guide:
    * Added introduction and list of features covered in the application. [\#19](https://github.com/AY2021S1-CS2103-T14-2/tp/pull/19)
  * Developer Guide:
    * Added implementation details of the `flip` flashcard feature. (TBC)

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#103](https://github.com/nus-cs2103-AY2021S1/ip/pull/103), [\#129](https://github.com/nus-cs2103-AY2021S1/ip/pull/129)
  * Reported bugs and suggestions for other teams in the class (examples: [1](https://github.com/mehak24k/ped/issues/1), [2](https://github.com/mehak24k/ped/issues/2), [3](https://github.com/mehak24k/ped/issues/3), [4](https://github.com/mehak24k/ped/issues/4), [5](https://github.com/mehak24k/ped/issues/5))
