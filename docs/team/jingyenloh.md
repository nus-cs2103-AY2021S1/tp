---
layout: page
title: Loh Jing Yen's Project Portfolio Page
---

## Project: ResiReg

**ResiReg** is a desktop residential college management application used for managing a residential college such as Cinnamon College. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 20 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the ability to archive the current Semester.
  * What it does: allows the user to move the current Semester's allocation into an archived file. The application is then set to the next Semester, and future allocations will be set with respect to that Semester.
  * Justification: This feature improves the product significantly because the allocation data the user operates on is semester specific. This allows them to conveniently refresh the data for a new Semester with a single command, while ensuring all data from the previous semester is preserved.
  * Highlights: This enhancement affects existing allocations and models to be added in future, such as Bills. It required an in-depth analysis of design alternatives. The implementation too was challenging as it required the Semester to be made Observable, rather than a POJO like the rest of the AddressBook/ResiReg models.

* **New Feature**: Added `--allocated` and `--vacant` flags to the `rooms` command
  * What it does: allows the user to optionally view only allocated or vacant rooms, versus the original command where it would display all the rooms.
  * Justification: This allows the user more fine grained viewing of the rooms, for when they only want to view rooms with students residing in them (e.g. for maintainence services), or when they only want to view vacant rooms (e.g. to see if last minute allocations are allowed for an Exchange student)
  * Highlights: Unlike most commands, these flags are exclusive, so the user cannot pass in both together.


* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&tabAuthor=JingYenLoh&tabRepo=AY2021S1-CS2103-T16-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~otherhttps://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&tabAuthor=JingYenLoh&tabRepo=AY2021S1-CS2103-T16-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~otherhttps://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&tabAuthor=JingYenLoh&tabRepo=AY2021S1-CS2103-T16-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other)

* **Project management**:
  * Created GitHub Organization and team repository, and configured organization permissions
  * Managed releases `v0.1` - `v1.1` (1 release) on GitHub (although I did the releases for several other milestones, my teammates coordinated those weeks)

* **Enhancements to existing features**:
  * Updated the GUI to use a tabbed interface with a side history panel (Pull requests [\#70](https://github.com/AY2021S1-CS2103-T16-3/tp/pull/70), [\#79](https://github.com/AY2021S1-CS2103-T16-3/tp/pull/79))
  * Combined `find` (adapted from AddressBook 3) and `find-students` into a single command ([\#136](https://github.com/AY2021S1-CS2103-T16-3/tp/pull/136))

* **Documentation**:
  * User Guide:
    * Added documentation for the features `archive` and `students` ([\#108](https://github.com/AY2021S1-CS2103-T16-3/tp/pull/108), [\#137](https://github.com/AY2021S1-CS2103-T16-3/tp/pull/137))
    * Did cosmetic tweaks to existing documentation e.g. [\#76](https://github.com/AY2021S1-CS2103-T16-3/tp/pull/76)
  * Developer Guide:
    * Added initial ResiReg User Stories ([\#61](https://github.com/AY2021S1-CS2103-T16-3/tp/pull/61))
    * Added implementation details of the `archive` feature ([\#134](https://github.com/AY2021S1-CS2103-T16-3/tp/pull/134))
    * Added overview of ResiReg model ([\#134](https://github.com/AY2021S1-CS2103-T16-3/tp/pull/134))

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#5](https://github.com/AY2021S1-CS2103-T16-3/tp/pull/5), [\#67](https://github.com/AY2021S1-CS2103-T16-3/tp/pull/67), [\#89](https://github.com/AY2021S1-CS2103-T16-3/tp/pull/89), [\#122](https://github.com/AY2021S1-CS2103-T16-3/tp/pull/122)
  * Contributed to forum discussions (examples: [1](https://github.com/nus-cs2103-AY2021S1/forum/issues/6#issuecomment-675195748))
  * Made PRs correcting minor issues in TextBook ([\#92](https://github.com/se-edu/se-book/pull/92), [\#93](https://github.com/se-edu/se-book/pull/93))

* **Tools**:
  * Added a PR template to the team repo [\#87](https://github.com/AY2021S1-CS2103-T16-3/tp/pull/87)
