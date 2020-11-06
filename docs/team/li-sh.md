---
layout: page
title: Li Shuo's Project Portfolio Page
---

## Project: HelloFile

HelloFile is a file management application created as an extension to AddressBook - Level 3 (AB3),
specifically made for tech savvy CS2103T CS students.
By using HelloFile, students can tag frequently used files/folders with a short nickname, and open their files
with intuitive commands.

Given below are my contributions to the project.

* **New Feature**: Added the `untag` command
(Pull request [\#84](https://github.com/AY2021S1-CS2103T-F12-1/tp/pull/84))
  * What it does: Allows user to untag an existing tag saved in HelloFile.
  * Justification: This feature allows users to remove unwanted tags saved in HelloFile, so that they can declutter
  their workspace, allowing them to find specific files more easily.
  * Credits: This feature was built from AB3's `delete` method.
  It was refactored to remove tag from the model instead of person.
  
* **New Feature**: Added the `retag` command
(Pull request [\#96](https://github.com/AY2021S1-CS2103T-F12-1/tp/pull/96))
  * What it does: Allows user to retag existing tags in the HelloFile.
  * Justification: This feature allows user to change the tag name of existing tags, in cases where the tag is
  misspelled, or have changed name.
  * Highlights: This feature allows the user to retag the tag name as the same tag name.
  
* **New Feature**: Added the `label` command
(Pull request [\#185](https://github.com/AY2021S1-CS2103T-F12-1/tp/pull/185)))
  * What it does: Allows user to add labels to existing tags.
  * Justification: This feature is the core feature in allowing users to open multiple tagged files with a single `open`
  command.
  * Highlight: This feature allows the user to add multiple unique labels to the tag at once. Duplicate label will simply
  be treated as the same label.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=li-s&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&tabAuthor=li-s&tabRepo=AY2021S1-CS2103T-F12-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code)

* **Project management**:
  * Maintained release for `v1.1`.
  * Maintaining team tasks together with other team members.

* **Enhancements to existing features**:
  * Modify UntagCommand to use tag instead of index.
  (Pull requests [\#99](https://github.com/AY2021S1-CS2103T-F12-1/tp/pull/99))
  * Update error/help messages.
  (Pull request [\#259](https://github.com/AY2021S1-CS2103T-F12-1/tp/pull/259),
  [\#260](https://github.com/AY2021S1-CS2103T-F12-1/tp/pull/260))
  * Write additional tests to increase test coverage.
  (Pull requests [\#187](https://github.com/AY2021S1-CS2103T-F12-1/tp/pull/187))

* **Documentation**:
  * User Guide (UG):
    * Add documentation for command summary. [\#152](https://github.com/AY2021S1-CS2103T-F12-1/tp/pull/27)
    * Update FAQ. [\#261](https://github.com/AY2021S1-CS2103T-F12-1/tp/pull/261)
    * Update command usage. [\#262](https://github.com/AY2021S1-CS2103T-F12-1/tp/pull/262)
    * Indicated which actions can be undone, redone, and added screenshots. [\#269](https://github.com/AY2021S1-CS2103T-F12-1/tp/pull/269)
    * Update find command guide. [\#273](https://github.com/AY2021S1-CS2103T-F12-1/tp/pull/273)
    
  * Developer Guide (DG):
    * Update DG and include `UntagCommand`, `RetagCommand` implementation details. [\#167](https://github.com/AY2021S1-CS2103T-F12-1/tp/pull/167)

* **Community**:
  * PRs reviewed (with non-trivial review comments):
  [\#83](https://github.com/AY2021S1-CS2103T-F12-1/tp/pull/83)
  * Contributed to forum discussions
  (examples: [1](https://github.com/nus-cs2103-AY2021S1/forum/issues/129),
  [2](https://github.com/nus-cs2103-AY2021S1/forum/issues/156))
  * Reported bugs and suggestions for other teams in the class
  (examples: [1](https://github.com/li-s/ped/issues/7),
  [2](https://github.com/li-s/ped/issues/10),
  [3](https://github.com/li-s/ped/issues/11))
  
  
  
  
  
  
  
  
