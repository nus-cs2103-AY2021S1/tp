---
layout: page
title: Xingjian's Project Portfolio Page
---

## Xingjian Chen - Project Portfolio for QuickCache (tP)

### 1. Introduction

This is my project portfolio for QuickCache. The document outlines my contributions to the project, including the enhancements and features that I have implemented.

#### 1.1 About the team

Our team consists of five Year 2 Computer Science students from National University of Singapore, who were all taking CS2103T Software Engineering at the time of this project.

#### 1.2 About the project

QuickCache is developed as part of our team project for our module CS2103T Software Engineering. QuickCache is designed to be a desktop application (Windows/macOS/Linux) for managing flashcards, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). The software is adapted and expanded from [AddressBook Level 3](https://github.com/nus-cs2103-AY2021S1/tp).

### 2. Contributions

I served to oversee code integration, resolving conflicts that arise during merging of pull requests in GitHub. My responsibilities also included maintaining QuickCache's storage system, creating features for users to set difficulty levels to their flashcards, and creating a set of starting sample flashcards for users to experiment on.

In the following sections, I will illustrate the above-mentioned enhancements in greater detail, along with the corresponding documentation that I have written for them within the user and developer guides.

#### 2.1 Enhancements and features added

The following describes the enhancements and new features that I added to the project.

**2.1.1 Feature: Difficulty functionality for flashcards**
  * What it does: allows the user to set a specific difficulty level for created flashcards. Difficulty level of a flashcard can be specified during creation through the `add` commands or during editing through the `edit` command. A difficulty tag with specified colouring corresponding to the difficulty level will then be seen under the flashcard in the GUI.
  * Justification: This feature improves the organisation of flashcards for the user as the user can more easily differentiate the flashcards that he/she has more difficulty in.
  * Highlights: This feature affects existing commands and commands to be added in the future. It required an in-depth analysis of design alternatives. The implementation too was challenging as it required changes to existing commands, such as `add`, `edit`. GUI was also adjusted to fit the functionality.

**2.1.2 Feature: Sample flashcards for starting users**
  * What it does: provides a set of sample flashcards containing sample questions and answers for new users who open QuickCache for the first time.
  * Justification: This feature provides a more user-friendly experience for the new user as he/she can play around with the sample flashcards and get familiar with the application.
  * Highlights: This feature checks for the existence of the data file stored in QuickCache. A new data file containing the sample flashcards will be added if there is no present data file.

**2.1.3 Enhancement: Maintaining storage system**
  * What it does: manages QuickCache's storage system by updating the application's data file with the current set of flashcards.
  * Justification: This enhancement ensures that the application's data file is properly updated and checks for corrupted information within the data file.
  * Highlights: This enhancement required an in-depth analysis of design alternatives and how we represent data in the json file. The storage has to be updated as functionalities are added or changed.

**2.1.4 Enhancement: GUI color scheme**
  * What it does: changes the colour of the application's GUI.
  * Justification: Sets a suitable theme for our application that reflects our vision for the project.

#### 2.2 Code contributed

Click on the following links to view the code that I have contributed:

 * [RepoSense](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=josiahkhoo&tabRepo=AY2021S1-CS2103T-T13-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other)
 * [Functional Code](https://github.com/AY2021S1-CS2103T-T13-2/tp/tree/master/src/main/java/quickcache)
 * [Test Code](https://github.com/AY2021S1-CS2103T-T13-2/tp/tree/master/src/test/java/quickcache)

#### 2.3. Other contributions

 The following describes the various other contributions that I have made to the project.

 * **Project management**:
   * Managed 34 issues on GitHub
   * Reviewed 59 pull requests on GitHub
   
 * **Enhancements to existing features**:
   * Wrote additional tests for existing features (Pull request [\#140](https://github.com/AY2021S1-CS2103T-T13-2/tp/pull/140))
   * Wrote tests for difficulty feature (Pull request [\#127](https://github.com/AY2021S1-CS2103T-T13-2/tp/pull/127))
   * Refactored remnant code from AddressBook package into the QuickCache package (Pull request [\#231](https://github.com/AY2021S1-CS2103T-T13-2/tp/pull/231))
   
 * **Documentation**:
   * User Guide:
     * Added documentation for the features `difficulty`, `tag` (Pull requests [\#30](https://github.com/AY2021S1-CS2103T-T13-2/tp/pull/30), [\#159](https://github.com/AY2021S1-CS2103T-T13-2/tp/pull/159)) 
     * Add introduction message and table of contents of the user guide (Pull requests [\#168](https://github.com/AY2021S1-CS2103T-T13-2/tp/pull/168))
   * Developer Guide:
     * Added implementation details of the `difficulty`, `storage` features (Pull requests [\#116](https://github.com/AY2021S1-CS2103T-T13-2/tp/pull/116), [\#132](https://github.com/AY2021S1-CS2103T-T13-2/tp/pull/132), [\#175](https://github.com/AY2021S1-CS2103T-T13-2/tp/pull/175))
 
 * **Community**:
   * PRs reviewed (with non-trivial review comments): [\#35](https://github.com/AY2021S1-CS2103T-T13-2/tp/pull/35), [\#37](https://github.com/AY2021S1-CS2103T-T13-2/tp/pull/37), [\#38](https://github.com/AY2021S1-CS2103T-T13-2/tp/pull/38), [\#50](https://github.com/AY2021S1-CS2103T-T13-2/tp/pull/50), [\#127](https://github.com/AY2021S1-CS2103T-T13-2/tp/pull/127), [\#129](https://github.com/AY2021S1-CS2103T-T13-2/tp/pull/129), [\#130](https://github.com/AY2021S1-CS2103T-T13-2/tp/pull/130), [\#151](https://github.com/AY2021S1-CS2103T-T13-2/tp/pull/151), [\#154](https://github.com/AY2021S1-CS2103T-T13-2/tp/pull/154), [\#169](https://github.com/AY2021S1-CS2103T-T13-2/tp/pull/169), [\#171](https://github.com/AY2021S1-CS2103T-T13-2/tp/pull/171)
   * Reported bugs and suggestions for other teams in the class (examples: [1](https://github.com/AY2021S1-CS2103T-T09-2/tp/issues/145), [2](https://github.com/AY2021S1-CS2103T-T09-2/tp/issues/144), [3](https://github.com/AY2021S1-CS2103T-T09-2/tp/issues/143), [4](https://github.com/josiahkhoo/ped/issues/1))
