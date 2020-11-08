---
layout: page
title: Jeremy Tan's Project Portfolio Page
---

## Project: ZooKeep

ZooKeep is a desktop app for managing animals under a zookeeper’s care, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI), created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=koonweee)

* **New Feature**: Added the ability to append information to existing animal fields ([\#148](https://github.com/AY2021S1-CS2103T-W15-4/tp/pull/148))
  * What it does: Allows the user to add additional information to animal fields that are already populated.
  * Justification: For the fields FeedTime and MedicalCondition, there may be scenarios where additional information may need to be added at a later date.
  * Highlights: This enhancement is branched out from the original EditCommand in AddressBook3. It complements the ReplaceCommand that was also created.

* **New Feature**: Added the ability to replace information in existing animal fields ([\#148](https://github.com/AY2021S1-CS2103T-W15-4/tp/pull/148))
  * What it does: Allows the user to replace information in animal fields that are already populated.
  * Justification: Some fields should only contain a maximum of 1 value at a time (eg. Name, ID). Hence, a replace command makes sense here (instead of append).
  * Highlights: This enhancement is branched out from the original EditCommand in AddressBook3. It complements the AppendCommand that was also created.

* **Enhancements to existing features**:
  * Refactor references to addressbook.json ([\#58](https://github.com/AY2021S1-CS2103T-W15-4/tp/pull/58))
  * Update delete command to take an ID input instead of index ([\#65](https://github.com/AY2021S1-CS2103T-W15-4/tp/pull/65))
  * Add feeding times field to Animal model and AddCommand ([\#96](https://github.com/AY2021S1-CS2103T-W15-4/tp/pull/96))
  * Allow duplicate names for Animals and prevent duplicate IDs ([\#115](https://github.com/AY2021S1-CS2103T-W15-4/tp/pull/115)) and ([\#116](https://github.com/AY2021S1-CS2103T-W15-4/tp/pull/116))
  * Add CommandException for unchanged fields in ReplaceCommand and AppendCommand ([\#285](https://github.com/AY2021S1-CS2103T-W15-4/tp/pull/285)) and ([\#287](https://github.com/AY2021S1-CS2103T-W15-4/tp/pull/287))

* **Documentation**:
  * User Guide:
    * Added documentation for the features `append` and `replace` ([\#155](https://github.com/AY2021S1-CS2103T-W15-4/tp/pull/155))
    * Updated order of commands in summary ([\#155](https://github.com/AY2021S1-CS2103T-W15-4/tp/pull/155))
    * Updated constraints for replace and append commands ([\#289](https://github.com/AY2021S1-CS2103T-W15-4/tp/pull/289))
  * Developer Guide:
    * Added use cases ([\#34](https://github.com/AY2021S1-CS2103T-W15-4/tp/pull/34))
    * Added implementation details for feeding times feature ([\#143](https://github.com/AY2021S1-CS2103T-W15-4/tp/pull/143))
    * Updated storage section description and UML diagram ([\#223](https://github.com/AY2021S1-CS2103T-W15-4/tp/pull/223))
    * Updated logic section description and UML diagram ([\#263](https://github.com/AY2021S1-CS2103T-W15-4/tp/pull/263))
    * Updated model section description and UML diagram ([\#263](https://github.com/AY2021S1-CS2103T-W15-4/tp/pull/263))
    * Add introduction section ([\#264](https://github.com/AY2021S1-CS2103T-W15-4/tp/pull/264))

* **Team-based Tasks**:
  * Update Github site settings ([\#35](https://github.com/AY2021S1-CS2103T-W15-4/tp/pull/35))
  * Update Github site index.md ([\#36](https://github.com/AY2021S1-CS2103T-W15-4/tp/pull/36))
