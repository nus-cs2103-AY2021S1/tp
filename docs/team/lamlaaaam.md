---
layout: page
title: Lam Zhi Yuan's Project Portfolio Page
---

## Project: ZooKeep

ZooKeep is a desktop app for managing animals under a zookeeper’s care, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI), created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the ability to undo commands ([\#113](https://github.com/AY2021S1-CS2103T-W15-4/tp/pull/113))
  * What it does: allows the user to undo all previous commands one at a time in the event of a mistake.
  * Justification: Managing and tracking sensitive information is prone to human error. This feature allows mistakes to be undone and makes the app more forgiving.
  * Highlights: This enhancement is implemented as an extension, without modifications to current commands. It tracks the states of the app and hence is not dependent on individual commands. This is extensible as future commands will also conform to the undo feature without having to be aware of it.
  * Credits: The undo feature was implemented using a stack, similar to every modern app.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=lamlaaaam)

* **Project management**:
  * Managed releases `v1.3.trial` - `v1.3` (2 releases) on GitHub

* **Enhancements to existing features**:
  * Refactor Person class to Animal ([\#66](https://github.com/AY2021S1-CS2103T-W15-4/tp/pull/66), [\#71](https://github.com/AY2021S1-CS2103T-W15-4/tp/pull/71), [\#72](https://github.com/AY2021S1-CS2103T-W15-4/tp/pull/72))
  * Refactor AB3's phone field to animal ID field ([\#73](https://github.com/AY2021S1-CS2103T-W15-4/tp/pull/73))
  * Refactor AB3's tags field to animal medical conition fields ([\#82](https://github.com/AY2021S1-CS2103T-W15-4/tp/pull/82))
  * Add headers to fields for clarity ([\#81](https://github.com/AY2021S1-CS2103T-W15-4/tp/pull/81))
  * Add feeding times field to Animal model ([\#98](https://github.com/AY2021S1-CS2103T-W15-4/tp/pull/98))
  * Refactor AB3's address package to zookeep ([\#190](https://github.com/AY2021S1-CS2103T-W15-4/tp/pull/190))

* **Documentation**:
  * User Guide:
    * Added documentation for the features `undo`, `redo`, and `sort` ([\#154](https://github.com/AY2021S1-CS2103T-W15-4/tp/pull/154))
    * Added figure labels and centered figures ([\#191](https://github.com/AY2021S1-CS2103T-W15-4/tp/pull/191), [\#211](https://github.com/AY2021S1-CS2103T-W15-4/tp/pull/211))
    * Added preface to all sections ([\#281](https://github.com/AY2021S1-CS2103T-W15-4/tp/pull/281))
    * Split command summary table into basic and advanced ([\#288](https://github.com/AY2021S1-CS2103T-W15-4/tp/pull/288))
    * Added numbering for TOC ([\#292](https://github.com/AY2021S1-CS2103T-W15-4/tp/pull/292))
    * Added a welcome introduction message ([\#283](https://github.com/AY2021S1-CS2103T-W15-4/tp/pull/283))
  * Developer Guide:
    * Added implementation details of the `undo` feature ([\#128](https://github.com/AY2021S1-CS2103T-W15-4/tp/pull/128), [\#149](https://github.com/AY2021S1-CS2103T-W15-4/tp/pull/149), [\#153](https://github.com/AY2021S1-CS2103T-W15-4/tp/pull/153))
    * Added use cases for `undo` and `redo` features ([\#152](https://github.com/AY2021S1-CS2103T-W15-4/tp/pull/152))
    * Added figure labels ([\#193](https://github.com/AY2021S1-CS2103T-W15-4/tp/pull/193))
    * Added preface to all sections ([\#194](https://github.com/AY2021S1-CS2103T-W15-4/tp/pull/194))
    
* **Team-based Tasks**:
  * Enabled assertions in Gradle build ([\#108](https://github.com/AY2021S1-CS2103T-W15-4/tp/pull/108))
  * Update version number and Gradle build on release ([\#130](https://github.com/AY2021S1-CS2103T-W15-4/tp/pull/130))
