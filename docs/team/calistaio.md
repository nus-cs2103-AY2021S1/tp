---
layout: page
title: Phianne Calista Io's Project Portfolio Page
---

## Project: DSAce

DSAce is a desktop flashcard application used for teaching CS2040S concepts. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 4K LoC.

Given below are my contributions to the project.

* **New Feature**: Added the ability to sort flashcards in order of ascending/descending priority
  * What it does: allows the user to sort flashcards in either ascending or descending priority, depending on the user input. If the user does not specify the preferred sorting order in the input,
    the flashcard list will be sorted in ascending order by default.
  * Justification: This feature improves the product significantly because it is quite common for students to prioritise certain flashcards, probably because they have a higher likelihood of being tested in exams. Hence, this feature
  	provides a convenient means for students to quickly sort their flashcards in order of priority.


* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=calistaio&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

* **Project management**:
  * Managed releases `v1.2`, `v1.3.trial` and `v1.3` (3 releases) on GitHub

*  **Enhancements to exitsting features**:
  * Enhanced the model of the flashcard book by adding a performanceBook attribute to the ModelManager class. PerformanceBook is a class that stores
    both a Performance object that keeps track of past quiz attempts and the responses for each attempt, as well as a PerformanceStorage object that performs
	read and write functions on Performance. [\#134](https://github.com/AY2021S1-CS2103-T14-2/tp/pull/134)
  * Created MCQ, T/F, and Question classes to allow for quizzes to be implemented in the flashcard book [\#74](https://github.com/AY2021S1-CS2103-T14-2/tp/pull/74)
  * Create Priority enum class to allow for flashcards to possess differing levels of priority [\#60](https://github.com/AY2021S1-CS2103-T14-2/tp/pull/60)
  * Renamed Person class to Flashcard and removed phone and email fields from Person class to adapt original AB3 project [\#37](https://github.com/AY2021S1-CS2103-T14-2/tp/pull/37)

