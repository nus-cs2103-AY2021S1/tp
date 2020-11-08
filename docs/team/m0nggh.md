---
layout: page
title: Malcolm Ong's Project Portfolio Page
---

## Project: ZooKeep

ZooKeep is a desktop app for managing animals under a zookeeper’s care, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI), created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the ability to sort animals ([\#127](https://github.com/AY2021S1-CS2103T-W15-4/tp/pull/127), [\#196](https://github.com/AY2021S1-CS2103T-W15-4/tp/pull/196), [\#298](https://github.com/AY2021S1-CS2103T-W15-4/tp/pull/298), [\#300](https://github.com/AY2021S1-CS2103T-W15-4/tp/pull/300))
  * What it does: allows the user to sort all animals according to their names (alphabetical order), ids (ascending order), feed times (chronological order) or number of medical conditions (ascending order).
  * Justification: After adding a large number of animals, the user may find the need to better categorise the animals. This feature allows for the user to sort all animals based on a category they require at his own convenience.
  * Highlights: This enhancement was implemented as an extension, without modifications to current commands. This feature is flexible and can allow for more variations of sorting if necessary in the future e.g. reversed order.
  * Credits: The sort feature was implemented with the help of different animal comparators.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=m0nggh)

* **Enhancements to existing features**:
  * Deleted the email field from AB3 since ZooKeep does not require it ([\#68](https://github.com/AY2021S1-CS2103T-W15-4/tp/pull/68))
  * Refactored human references to animals in ZooKeep ([\#94](https://github.com/AY2021S1-CS2103T-W15-4/tp/pull/94), [\#95](https://github.com/AY2021S1-CS2103T-W15-4/tp/pull/95))
  * Standardised ZooKeep feeding time references to feed time ([\#276](https://github.com/AY2021S1-CS2103T-W15-4/tp/pull/276))
  * Wrote additional tests for existing features to increase coverage from 71% to 76% ([\#127](https://github.com/AY2021S1-CS2103T-W15-4/tp/pull/127), [\#196](https://github.com/AY2021S1-CS2103T-W15-4/tp/pull/196), [\#274](https://github.com/AY2021S1-CS2103T-W15-4/tp/pull/274), [\#320](https://github.com/AY2021S1-CS2103T-W15-4/tp/pull/320))

* **Documentation**:
  * User Guide:
    * Added documentation for the feature `sort` ([\#226](https://github.com/AY2021S1-CS2103T-W15-4/tp/pull/226))
    * Modified the description to be more user-centric ([\#181](https://github.com/AY2021S1-CS2103T-W15-4/tp/pull/181))
    * Included a legend to explain the relevant symbols ([\#181](https://github.com/AY2021S1-CS2103T-W15-4/tp/pull/181))
    * Distinguished the features to basic and advanced features for the user ([\#181](https://github.com/AY2021S1-CS2103T-W15-4/tp/pull/181))
    * Refined the ID constraints for different features ([\#298](https://github.com/AY2021S1-CS2103T-W15-4/tp/pull/298))
    * Added details to the FAQ section ([\#181](https://github.com/AY2021S1-CS2103T-W15-4/tp/pull/181))
  * Developer Guide:
    * Added user profile, value proposition and user stories ([\#33](https://github.com/AY2021S1-CS2103T-W15-4/tp/pull/33))
    * Added implementation details of the `sort` feature ([\#145](https://github.com/AY2021S1-CS2103T-W15-4/tp/pull/145), [\#150](https://github.com/AY2021S1-CS2103T-W15-4/tp/pull/150), [\#326](https://github.com/AY2021S1-CS2103T-W15-4/tp/pull/326))
    * Added numbering for TOC ([\#301](https://github.com/AY2021S1-CS2103T-W15-4/tp/pull/301))
    * Modified formatting and edited details in the appendix ([\#301](https://github.com/AY2021S1-CS2103T-W15-4/tp/pull/301))
    * Added and modified all use cases ([\#151](https://github.com/AY2021S1-CS2103T-W15-4/tp/pull/151), [\#301](https://github.com/AY2021S1-CS2103T-W15-4/tp/pull/301))

* **Community**:
  * PRs reviewed (with non-trivial review comments): ([\#148](https://github.com/AY2021S1-CS2103T-W15-4/tp/pull/148), [\#199](https://github.com/AY2021S1-CS2103T-W15-4/tp/pull/199), [\#222](https://github.com/AY2021S1-CS2103T-W15-4/tp/pull/222))

* **Team-based tasks**:
  * Created necessary issues to the repository for v1.3 & v1.4
