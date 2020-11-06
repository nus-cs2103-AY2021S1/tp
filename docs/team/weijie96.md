---
layout: page
title: Wei Jie's Project Portfolio Page
---

## Project: Insurance4Insurance (I4I)

Insurance4Insurance (I4I) is a desktop app for insurance agents to manage clients.  
It is optimized for use via a CLI, while still having the benefits of a GUI. 
It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the ability to archive clients.
  * What it does: It allows the user to add active clients into the archive. The opposite can be done as well; archived clients can be made active again.
  To support the archiving feature, users can also view the archive, as well as to switch back to view the active clients.
  * Justification: This feature improves the product significantly because a user can add clients which are not currently relevant to the user into the archive.
  The presence of a separate view of the archive prevents the user from getting distracted by archived clients. This is different from deleting a client,
  as it handles for the case when the archived client turning active again in future.
  * Highlights: 
  This enhancement affects existing commands (such as `ListCommand` and `AddCommand`), and commands to be added in future (such as `FindCommand`). 
  It required an in-depth analysis of design alternatives. 
  The implementation too was challenging as it required changes to existing commands.
  The feature is complete, as both archiving and unarchiving are implemented, in addition to viewing the archive.
  
* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&tabAuthor=WeiJie96&tabRepo=AY2021S1-CS2103-T16-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other)

* **Enhancements to existing features**:
  * Made fields in `Person`, such as `Email` and `Address`, optional.
  (Pull requests [\#41](https://github.com/AY2021S1-CS2103-T16-2/tp/pull/41), [\#60](https://github.com/AY2021S1-CS2103-T16-2/tp/pull/60))
  
* **Documentation**:
  * User Guide:
    * Added/Maintained documentation for `add`, `archive`, `clear`, `exit`,  `help`, `list`, `list r/` and `unarchive` commands.
      (Pull requests [\#68](https://github.com/AY2021S1-CS2103-T16-2/tp/pull/68),
      [\#134](https://github.com/AY2021S1-CS2103-T16-2/tp/pull/134), [\#138](https://github.com/AY2021S1-CS2103-T16-2/tp/pull/138))
    * Added an individual section to explain the `archive` feature. [\#159](https://github.com/AY2021S1-CS2103-T16-2/tp/pull/159)
    * Maintained FAQ and Quick Start. [\#159](https://github.com/AY2021S1-CS2103-T16-2/tp/pull/159)
  * Developer Guide:
    * Added implementation details of the `archive` feature.
    (Pull requests [\#83](https://github.com/AY2021S1-CS2103-T16-2/tp/pull/83), [\#131](https://github.com/AY2021S1-CS2103-T16-2/tp/pull/131))
    * Added/Maintained user stories, use cases and test cases for `archive`, `delete` `help`, `list`, `list r/` and `unarchive` commands. 
    (Pull requests [\#23](https://github.com/AY2021S1-CS2103-T16-2/tp/pull/23), 
    [\#69](https://github.com/AY2021S1-CS2103-T16-2/tp/pull/69), [\#160](https://github.com/AY2021S1-CS2103-T16-2/tp/pull/160))

* **Contributions to team-based tasks**:
  * PRs reviewed (examples with non-trivial review comments): 
  [\#50](https://github.com/AY2021S1-CS2103-T16-2/tp/pull/50), [\#97](https://github.com/AY2021S1-CS2103-T16-2/tp/pull/97), [\#124](https://github.com/AY2021S1-CS2103-T16-2/tp/pull/124)
  * Maintained the issue tracker.
  
* **Community**:
  * Asked questions in forums which could potentially help other students. 
  (examples: [\#206](https://github.com/nus-cs2103-AY2021S1/forum/issues/206), [\#358](https://github.com/nus-cs2103-AY2021S1/forum/issues/358))
  * Reported non-trivial bugs and suggestions for other teams in the class. 
  (examples: [\#7](https://github.com/WeiJie96/ped/issues/7), [\#8](https://github.com/WeiJie96/ped/issues/8), [\#11](https://github.com/WeiJie96/ped/issues/11))
  * Reported bugs and suggestions in AB3. (example: [\#220](https://github.com/nus-cs2103-AY2021S1/forum/issues/220))
