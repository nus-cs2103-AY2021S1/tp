---
layout: page
title: Josiah's Project Portfolio Page
---

## Josiah Khoo - Project Portfolio for QuickCache (tP)

### 1. Introduction

This document serves as a project portfolio for QuickCache, and outlines my contributions to the project, including the features that I have implemented.

#### 1.1. About the team

My team of five consists of  five Year 2 Computer Science students, all taking the module CS2103T Software Engineering.

#### 1.2. About the project

This project was developed as part of the module CS2103T Software Engineering. We were tasked to develop a desktop application (Windows/macOS/Linux) with a Command Line Interface (that is, the program operates via text input from the user, called commands). Additionally, we were required to use an existing application, called AddressBook Level 3, as the starting point for building our application.

My team decided to a create an application that helps students manage their flashcards, test themselves, and view their performance over time. To do so, we incorporated the existing people management features of AddressBook and used it as a starting point to build QuickCache. 

In total, QuickCache took a total of 10 weeks to complete.

### 2. Summary of Contributions

I served as Team Lead for the project, and my responsibilities included creating features for users to test their created flashcards and enabling the users to share their flashcards with one another.

In the following sections, I will illustrate the above-mentioned enhancements in greter detail, along with the corresponding documentation that I have written fro them within the user and developer guides.

#### 2.1. Enhancements and new features added

The following describes the enhancements and new features that I added to the project. 

#### 2.1.1 Test Command (Feature)

- What it does: The test command allows the user to test their created flashcards with what they think the answer for the flashcard is.
- Justification: This command is essential to the core of our application in allowing students to revise from the flashcards that they have created.
- Highlights: The test command updates the internal state of the flashcard that is being tested to allow for useful statistics to be generated from it. It also works for any question type (open-ended question and multiple choice questions)

#### 2.1.2 Export Command (Feature)

- What it does: The export command allows the user to export a specified set of flashcards from QuickCache into a file that the user can store or share with others.
- Justification: This commands works in tandem with the import command to facilitate sharing as well as for the user to back his/her flashcards up.
- Highlights: The export command uses the `QuickCacheStorage` interface to write the file into the users storage.

#### 2.1.3 Import Command (Feature)

- What it does: The import command allows the user to import a specified set of flashcards from the user's local storage into QuickCache.
- Justification: This command works in tandem with the export command to facilitate sharing as well as allow for the user to import his/her flashcards from a file in local storage.

* Highlights: The import command uses the `QuickCacheStorage` and checks whether a flashcard has been imported before prior to deciding to import it. This prevents the user from importing duplicate flashcards.

#### 2.1.4 Statistics by Tags Command (Enhancement)

- What it does: The statistics by tags command allows the user to view aggregated statistics of a set of flashcards through the use of tags.
- Justification: This command saves the user time when he/she  wants to view statistics of all questions with a certain tag. Instead of having to manually view statistics for each question, the user can now view them all at a go.
- Highlights: The statistics by tags command were implemented over the existing statistics command by overloading the constructor. This allows for minimal repetition of code and for variations of the command to use similar logic, allowing for ease of debugging.

### 2.2. Code contributed

Click on the following links to view the code that I have contributed:

* [RepoSense](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=josiahkhoo&tabRepo=AY2021S1-CS2103T-T13-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other)
* [Functional Code](https://github.com/AY2021S1-CS2103T-T13-2/tp/tree/master/src/main/java/quickcache)
* [Test Code](https://github.com/AY2021S1-CS2103T-T13-2/tp/tree/master/src/test/java/quickcache)

### 2.3. Other contributions

The following describes the various other contributions that I have made to the project.

* **Project management**:
  * Managed releases `v1.2` - `v1.4` (4 releases) on GitHub
* Managed milestones `v1.1` - `v1.4` (6 milestones) on GitHub
  * Managed 62 issues on GitHub
  * Recorded minutes and deliverables on Google Docs for the functional aspect of the project
* **Enhancements to existing features**:
  * Wrote additional tests for existing features (Pull requests [\#72](https://github.com/AY2021S1-CS2103T-T13-2/tp/pull/72), [\#95](https://github.com/AY2021S1-CS2103T-T13-2/tp/pull/95))
  * Refactored feedback string for command result into its own class (Pull request [\#58](https://github.com/AY2021S1-CS2103T-T13-2/tp/pull/58))
  * Refactored the AddressBook package into the QuickCache package (Pull request [\#85](https://github.com/AY2021S1-CS2103T-T13-2/tp/pull/85))
* **Documentation**:
  * User Guide:
    * Added documentation for the features `export`, `import`, `test` (Pull requests [\#30](https://github.com/AY2021S1-CS2103T-T13-2/tp/pull/30), [\#159](https://github.com/AY2021S1-CS2103T-T13-2/tp/pull/159)) 
    * Added documentation for the extension of the feature `stats` (Pull requests [\#153](https://github.com/AY2021S1-CS2103T-T13-2/tp/pull/153))
    * Add welcome message and aims of the user guide (Pull requests [\#168](https://github.com/AY2021S1-CS2103T-T13-2/tp/pull/168))
  * Developer Guide:
  * Added implementation details of the `export`, `import`, `test` feature (Pull requests [\#116](https://github.com/AY2021S1-CS2103T-T13-2/tp/pull/116), [\#132](https://github.com/AY2021S1-CS2103T-T13-2/tp/pull/132), [\#175](https://github.com/AY2021S1-CS2103T-T13-2/tp/pull/175))
* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#35](https://github.com/AY2021S1-CS2103T-T13-2/tp/pull/35), [\#37](https://github.com/AY2021S1-CS2103T-T13-2/tp/pull/37), [\#38](https://github.com/AY2021S1-CS2103T-T13-2/tp/pull/38), [\#50](https://github.com/AY2021S1-CS2103T-T13-2/tp/pull/50), [\#127](https://github.com/AY2021S1-CS2103T-T13-2/tp/pull/127), [\#129](https://github.com/AY2021S1-CS2103T-T13-2/tp/pull/129), [\#130](https://github.com/AY2021S1-CS2103T-T13-2/tp/pull/130), [\#151](https://github.com/AY2021S1-CS2103T-T13-2/tp/pull/151), [\#154](https://github.com/AY2021S1-CS2103T-T13-2/tp/pull/154), [\#169](https://github.com/AY2021S1-CS2103T-T13-2/tp/pull/169), [\#171](https://github.com/AY2021S1-CS2103T-T13-2/tp/pull/171)
  * Reported bugs and suggestions for other teams in the class (examples: [1](https://github.com/AY2021S1-CS2103T-T09-2/tp/issues/145), [2](https://github.com/AY2021S1-CS2103T-T09-2/tp/issues/144), [3](https://github.com/AY2021S1-CS2103T-T09-2/tp/issues/143), [4](https://github.com/josiahkhoo/ped/issues/1))
