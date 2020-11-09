---
layout: page
title: Lim Jun Cheng's Project Portfolio Page
---

## Project: ZooKeep

ZooKeep is a desktop app for managing animals under a zookeeper’s care, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI), created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the ability to redo commands that were undone by the undo command: [\#147](https://github.com/AY2021S1-CS2103T-W15-4/tp/pull/147)
  * What it does: allows the user to reverse all the preceding undo commands one at a time.
  * Justification: If there exists an undo function that rectifies mistakes made by users, then there should also be a redo function in case users make mistakes again, accidentally undoing too many commands and want a convenient way to restore them.
  * Highlights: This enhancement, similar to undo, was added as an extension and did not require any modifications to be made to existing commands (other than undo) since redo only needs to track the changes in the state of the ZooKeepBook following an undo command.
  It was difficult to decide on an appropriate way for redo to keep track of commands that change the ZooKeepBook state since commands go through several processes from user to execution.
  * Credits: Similar to the undo feature, the redo command was implemented using a stack (which stores states of the ZooKeepBook that were undone by the undo command).
  

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=JunCheng98)

* **Enhancements to existing features**:
  * Morphed the Phone field in the original AB3 to ID field in the current ZooKeep: [\#62](https://github.com/AY2021S1-CS2103T-W15-4/tp/pull/62), [\#64](https://github.com/AY2021S1-CS2103T-W15-4/tp/pull/64)
  * Enhanced the find command to enable searching in all fields: [\#114](https://github.com/AY2021S1-CS2103T-W15-4/tp/pull/114)
  * Enhanced the find command to allow substring matching: [\#269](https://github.com/AY2021S1-CS2103T-W15-4/tp/pull/269)
  * Added logging and assertions to raise code quality: [\#305](https://github.com/AY2021S1-CS2103T-W15-4/tp/pull/305)

* **Documentation**:
  * User Guide:
    * Added information on data saving in FAQ: [\#199](https://github.com/AY2021S1-CS2103T-W15-4/tp/pull/199/files)
    * Added documentation to clarify constraints for ID entries: [\#199](https://github.com/AY2021S1-CS2103T-W15-4/tp/pull/199/files)
    * Changed 'feeding times' to 'feed times' to be consistent with code base: [\#199](https://github.com/AY2021S1-CS2103T-W15-4/tp/pull/199/files)
    * Clarified format for feed time and addressed issues about leading zeroes in ID: [\#270](https://github.com/AY2021S1-CS2103T-W15-4/tp/pull/270)
    * Confirmed that find command supports both substring matching and full word matching: [\#304](https://github.com/AY2021S1-CS2103T-W15-4/tp/pull/304)
  * Developer Guide:
    * Added implementation details of the `redo` feature: [\#144](https://github.com/AY2021S1-CS2103T-W15-4/tp/pull/144)
    * Added relevant UML diagrams to complement the implementation details of `redo`: [\#144](https://github.com/AY2021S1-CS2103T-W15-4/tp/pull/144)
    * Changed some instances of 'person' in various sections to 'animal': [\#199](https://github.com/AY2021S1-CS2103T-W15-4/tp/pull/199/files)
    * Modified the 'Deleting an animal' section of the Appendix: [\#199](https://github.com/AY2021S1-CS2103T-W15-4/tp/pull/199/files)
    * Appended 2 new scenarios to the 'Saving Data' section of the Appendix: [\#199](https://github.com/AY2021S1-CS2103T-W15-4/tp/pull/199/files)

* **Team-based tasks**:
  * [DG] Added relevant Non-Functional Requirements (NFRs) and Glossary: [\#20](https://github.com/AY2021S1-CS2103T-W15-4/tp/pull/20)
  * [UG] Modified the descriptions of all available features to be more user-centric: [\#222](https://github.com/AY2021S1-CS2103T-W15-4/tp/pull/222)
  * [UG] Included different screenshots of ZooKeep to complement the examples given for all the features: [\#268](https://github.com/AY2021S1-CS2103T-W15-4/tp/pull/268), [\#313](https://github.com/AY2021S1-CS2103T-W15-4/tp/pull/313)
  * [DG] Modified entries in NFRs and added some new Glossary entries: [\#305](https://github.com/AY2021S1-CS2103T-W15-4/tp/pull/305)
  * Added relevant documentation issues to the repository: ([\#217](https://github.com/AY2021S1-CS2103T-W15-4/tp/issues/217), [\#218](https://github.com/AY2021S1-CS2103T-W15-4/tp/issues/218), [\#219](https://github.com/AY2021S1-CS2103T-W15-4/tp/issues/219))
