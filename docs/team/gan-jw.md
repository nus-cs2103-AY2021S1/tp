---
layout: page
title: Gan Jing Wen's Project Portfolio Page
---

## Project: DSAce

DSAce is a desktop flashcard application used for teaching CS2040S concepts. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 4K LoC.

Given below are my contributions to the project.

* **New Feature**: Added the ability to answer quiz questions.
  * What it does: allows the user answer an intended question in quiz mode. The question can be of type either True
  -False or MCQ.
  * Justification: This feature improves the product significantly because a user may be unsure of whether they had
   mastered the concepts in CS2040S. Hence, this feature provides the user an avenue to test their understanding of
    CS2040S concepts and optimise their learning by verifying their knowledge.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=&sort=groupTitle&sortWithin=title&since=2020-08-14&until=2020-11-09&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&tabAuthor=gan-jw&tabRepo=AY2021S1-CS2103-T14-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code)

* **Project management**:
  * Setting up the GitHub team org/repo settings
  * Set up GitHub page

* **Enhancements to existing features**:
  * Implement quiz parser class and edit logic manager to switch between flashcard mode and quiz mode [\#81](https://github.com/AY2021S1-CS2103-T14-2/tp/pull/81)
  * Implement Response, Attempt, Performance classes for quiz model [\#94](https://github.com/AY2021S1-CS2103-T14-2/tp/pull/94)
  * Add exit command in Quiz mode [\#139](https://github.com/AY2021S1-CS2103-T14-2/tp/pull/139)
  * Implement Answer Command in Quiz mode [\#143](https://github.com/AY2021S1-CS2103-T14-2/tp/pull/143)
  * Wrote additional tests for existing features (AnswerCommand and AnswerCommandParser) to increase coverage [\#162
  ](https://github.com/AY2021S1-CS2103-T14-2/tp/pull/162)

* **Documentation**:
  * User Guide:
    * Added documentation for the features `sort`, `edit` and `find` [\#80](https://github.com/AY2021S1-CS2103-T14-2/tp/pull/80)
    * Added documentation for the features `enter quiz`, `leave quiz` [\#95](https://github.com/AY2021S1-CS2103-T14-2/tp/pull/95)
    * Edited documentation for the `answer` feature [\#165](https://github.com/AY2021S1-CS2103-T14-2/tp/pull/165)
  * Developer Guide:
    * Added implementation details of Logic component and quiz components of quiz feature [\#165](https://github.com/AY2021S1-CS2103-T14-2/tp/pull/165)
    * Added activity diagram for workflow of quiz feature [\#171](https://github.com/AY2021S1-CS2103-T14-2/tp/pull/171)
    * Added Instructions for Manual Testing for `answer` command [\#166](https://github.com/AY2021S1-CS2103-T14-2/tp/pull/166)
    * Added Instructions for Manual Testing for `start attempt` and `end attempt` command [\#171](https://github.com/AY2021S1-CS2103-T14-2/tp/pull/171)

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#12](), [\#32](), [\#19](), [\#42]()
  * Contributed to forum discussions (examples: [1](), [2](), [3](), [4]())
  * Reported bugs and suggestions for other teams in the class (examples: [1](), [2](), [3]())
  * Some parts of the history feature I added was adopted by several other class mates ([1](), [2]())
