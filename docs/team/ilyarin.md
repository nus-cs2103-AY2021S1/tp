---
layout: page
title: Melanie Ng's Project Portfolio Page
---

## Project: Green Tea

Green Tea is a desktop app for learning a new language, optimized for use via a Command Line Interface (CLI)
while still having the benefits of a Graphical User Interface (GUI) created with JavaFX.
It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Implemented the deck system along with new commands (NewDeckCommand, RemoveDeckCommand, SelectDeckCommand).

  Pull requests: [\#56](https://github.com/AY2021S1-CS2103T-T09-4/tp/pull/56)
  Commit: [\AddSelectDeckCommand](https://github.com/AY2021S1-CS2103T-T09-4/tp/commit/bd036d7fc33d5fea091aeae658ab7967e1d8e010#diff-d126ae081f932363c3d9e4b8117d275191d41cee659ff81474b4d917c8530533)

  * What it does: Allows the user to create decks and add entries to the deck.
  * Justification: Users can organize their entries better. Also allows the quizzing system to be implemented more easily.
  * Highlights: This feature was difficult to implement since it required entries to be embedded within decks. This means that
                on top of adding new commands, original commands also had to be altered to accomodate the deck system.
                There was also many bugs that had to be fixed during the implementation of this feature such as existing commands
                not working like they should (edit command).
                This feature required modifications to the ui component, model component, logic component and storage component.
                Most of my contributions for this feature was in the model and logic components and planning how to implement this feature.
                The ui component for this feature was handled by my teammate, Gabriel. The storage component was handled by my teammate, Vignesh

  * Credits: *{I utilized the existing command format in Address Book 3 to write the command classes for new commands. I also utilized the person class in Address Book 3 to design the deck system}*

<div style="page-break-after: always;"></div>

* **New Feature**: Create tab panels in the GUI which will display the appropriate information to the user depending on the command given.

  Pull requests: [\#114](https://github.com/AY2021S1-CS2103T-T09-4/tp/pull/114) [\#119](https://github.com/AY2021S1-CS2103T-T09-4/tp/pull/119)

  * What it does: Allows users to see only what is necessary depending on the command given
  * Justification: Cleaner UI. More flexibility in how to display the quiz and statistics to the user.
  * Highlights: Created a package in model called view. Commands will change the view which will in turn change the tabs.
                There are 5 panels which are visible to the user (start, entry list, quiz, score and statistics).
                I created and designed the start, quiz and score panels. My teammate Georgie was in charge of the statistics panel.
                This feature required changes to be made to the logic, model and ui components.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=IlyaRin&tabRepo=AY2021S1-CS2103T-T09-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code)

* **Project management**:
  * Managed releases `v1.3` - `v1,4` (2 releases) on GitHub

* **Enhancements to existing features**:
  * Change GUI to current form (Pull requests [\#114](https://github.com/AY2021S1-CS2103T-T09-4/tp/pull/114), [\#131](https://github.com/AY2021S1-CS2103T-T09-4/tp/pull/131))
  * Wrote additional tests for existing features to increase code coverage (Pull requests [\#182](https://github.com/AY2021S1-CS2103T-T09-4/tp/pull/182), [\#60](https://github.com/AY2021S1-CS2103T-T09-4/tp/pull/60))
  * Fixed several bugs (Pull requests [\#181](https://github.com/AY2021S1-CS2103T-T09-4/tp/pull/181) [\#177](https://github.com/AY2021S1-CS2103T-T09-4/tp/pull/177) [\#138](https://github.com/AY2021S1-CS2103T-T09-4/tp/pull/138) [\#128](https://github.com/AY2021S1-CS2103T-T09-4/tp/pull/128) [\#88](https://github.com/AY2021S1-CS2103T-T09-4/tp/pull/88))

* **Documentation**:
  * User Guide:
    * Added documentation for section 4 (Using this guide) of user guide [\#133](https://github.com/AY2021S1-CS2103T-T09-4/tp/pull/133)
    * Added images and labelling for user guide [\#144](https://github.com/AY2021S1-CS2103T-T09-4/tp/pull/144)
    * Added documentation for section 1 (What is GreenTea) [\#141](https://github.com/AY2021S1-CS2103T-T09-4/tp/pull/141)
    * Added documentation for section 2 (About this document) [\#141](https://github.com/AY2021S1-CS2103T-T09-4/tp/pull/141)
    * Added documentation for section 5 (Features), deck commands and statistics section

  * Developer Guide:
    * Added prefaces to each section for the various components. [\#200](https://github.com/AY2021S1-CS2103T-T09-4/tp/pull/200)
    * Added NFRs [\#200](https://github.com/AY2021S1-CS2103T-T09-4/tp/pull/200) [\#12](https://github.com/AY2021S1-CS2103T-T09-4/tp/pull/12)
    * Added documentation for Manual Testing [\#200](https://github.com/AY2021S1-CS2103T-T09-4/tp/pull/200)
    * Added implementation details of the deck feature.

* **Community**:
* PRs reviewed (with non-trivial review comments): [\#52](https://github.com/AY2021S1-CS2103T-T09-4/tp/pull/52) [\#94](https://github.com/AY2021S1-CS2103T-T09-4/tp/pull/94)
