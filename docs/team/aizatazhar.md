---
layout: page
title: Aizat's Project Portfolio Page
---

## Project: ZooKeep

ZooKeep is a desktop app for managing animals under a zookeeper’s care, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI), created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=aizatazhar&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=zoom&zFR=false&zA=aizatazhar&zR=AY2021S1-CS2103T-W15-4%2Ftp%5Bmaster%5D&zACS=130.23529411764707&zS=2020-08-14&zFS=aizatazhar&zU=2020-11-04&zMG=false&zFTF=commit&zFGS=groupByRepos)

* **New Feature**: Snapshot feature ([\#124](https://github.com/AY2021S1-CS2103T-W15-4/tp/pull/124))
  * What it does: saves a snapshot of the `ZooKeepBook` data at the point in time when the command is executed as a `.json` file
  with the user specified file name.
  * Justification: Zookeepers would need to frequently back up their animal data (e.g. at the end of every
  day) to keep track of the condition of their animals as time passes.
  * Highlights: It creates a separate folder within the `data` directory and ensures that any existing file
  in this directory will not be overridden due to execution of the command.

* **Enhancements to existing features**:
  * Morphed `Address` class to `Species` class ([\#60](https://github.com/AY2021S1-CS2103T-W15-4/tp/pull/60))
  * Renamed `AddressBook` instances in code to `ZooKeepBook` ([\#90](https://github.com/AY2021S1-CS2103T-W15-4/tp/pull/90))
  * Updated the UI ([\#112](https://github.com/AY2021S1-CS2103T-W15-4/tp/pull/112))
  * Update help feature to have a clickable URL ([#228](https://github.com/AY2021S1-CS2103T-W15-4/tp/pull/228))
  
* **Documentation**:
  * User Guide:
      * Added documentation for all our proposed features for milestone 1.2 ([\#26](https://github.com/AY2021S1-CS2103T-W15-4/tp/pull/26))
      * Added additional FAQs ([#207](https://github.com/AY2021S1-CS2103T-W15-4/tp/pull/207), [#267](https://github.com/AY2021S1-CS2103T-W15-4/tp/pull/267/))
      * Added documentation for snapshot feature to UG ([#137](https://github.com/AY2021S1-CS2103T-W15-4/tp/pull/137))
      * Added expected outcomes for every command ([#234](https://github.com/AY2021S1-CS2103T-W15-4/tp/pull/234))
      
  * Developer Guide:
      * Updated links to code within the DG ([#185](https://github.com/AY2021S1-CS2103T-W15-4/tp/pull/185))
      * Updated `AddressBook` documentation and UML diagrams to reflect `ZooKeep` in Architecture, Logic and UI sections ([#186](https://github.com/AY2021S1-CS2103T-W15-4/tp/pull/186), [#198](https://github.com/AY2021S1-CS2103T-W15-4/tp/pull/198), [#271](https://github.com/AY2021S1-CS2103T-W15-4/tp/pull/271))
      * Added documentation for snapshot feature to DG ([#137](https://github.com/AY2021S1-CS2103T-W15-4/tp/pull/137), [#156](https://github.com/AY2021S1-CS2103T-W15-4/tp/pull/156))
      
* **Tools**:
  * Set up Codecov to monitor code coverage ([\#45](https://github.com/AY2021S1-CS2103T-W15-4/tp/pull/45))
  * Set up Codacy to enforce code quality ([\#56](https://github.com/AY2021S1-CS2103T-W15-4/tp/pull/56))

* **Project management**:
  * Created issues detailing the high-level tasks for [milestone 1.2](https://github.com/AY2021S1-CS2103T-W15-4/tp/issues?q=is%3Aissue+is%3Aclosed+author%3Aaizatazhar+milestone%3Av1.2+) 
  * Created issues on UG/DG feedback received for [milestone 1.3](https://github.com/AY2021S1-CS2103T-W15-4/tp/issues?q=is%3Aissue+is%3Aclosed+author%3Aaizatazhar+milestone%3Av1.3)
  * Created issues for some [bugs I found](https://github.com/AY2021S1-CS2103T-W15-4/tp/issues?q=is%3Aissue+is%3Aclosed+author%3Aaizatazhar+label%3Atype.bug)

* **Community**:
  * Reviewed teammates' [PRs](https://github.com/AY2021S1-CS2103T-W15-4/tp/pulls?q=is%3Apr+is%3Aclosed+reviewed-by%3Aaizatazhar)
  * Contributed to forum discussions (examples: [1](https://github.com/nus-cs2103-AY2021S1/forum/issues/219), [2](https://github.com/nus-cs2103-AY2021S1/forum/issues/245))
  * Reported typos found on various parts of module website (examples: [1](https://github.com/se-edu/se-book/issues/94), [2](https://github.com/se-edu/guides/pull/1), [3](https://github.com/nus-cs2103-AY2021S1/website/issues/6), [4](https://github.com/se-edu/addressbook-level3/issues/52))
  * Reported broken link on module website ([1](https://github.com/nus-cs2103-AY2021S1/website/issues/7))
