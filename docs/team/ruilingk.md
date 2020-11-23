---
layout: page
title: Koh Rui Ling's Project Portfolio Page
---

## Project: TR4CKER

TR4CKER is a desktop app that helps SoC students track their tasks, meet their deadlines, and stay productive. It is
optimized for Computing students familiar with CLI, who can manage their tasks efficiently by typing in commands.

Given below are my contributions to the project:

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=ruilingk)

* **Morphing of AddressBook:**
  * Refactor Person class to Task class: [\#14](https://github.com/AY2021S1-CS2103T-T10-2/tp/pull/14)
  * Changed AB3 traces to TR4CKER in Developer Guide: [\#186](https://github.com/AY2021S1-CS2103T-T10-2/tp/pull/186)

* **Change to existing feature**: Updated edit command.
  * Change: Edit command now no longer edit tags for tasks as there is a tag command to meet this purpose.

* **New Feature**: Added tag command.
  * What it does: Allows users to add and/or delete tag(s) from an existing task.
  * Justification: This feature makes TR4CKER more intuitive for users to use as it does not override current tags
  (which was what it used to do in AB3). It can add tags to existing tags, and delete specific existing tags without
  having to delete all the tags.
  * Highlights: This new feature can add and/or delete multiple tags at one go without users having to type multiple commands.
  Users will also be provided feedback on their inputs. If users try to add tags which already existed, or users try to
  delete tags which do not exist, TR4CKER will inform users the specific tags in the result box.
  
* **New Feature**: Added planner tab.
  * What it does: Allows users to view their tasks in a calendar view with tasks list side-by-side.
  * Justification: This feature provides users another perspective of their tasks. By viewing their tasks in a monthly
  calendar view with different indicators, they would be able to plan their time ahead and hence be more productive.
  Users can also view their tasks on the specific dates they input, so it will be clearer than just a tasks list sorted
  in chronological order.
  * Highlights: This new feature has a calendar and tasks list. The calendar shows a monthly view, with indicators on the
  dates. For example, no indicator means there are no tasks due on that day. Green indicator means there is less than 2
  tasks due on that day, and red indicator means there is more than 2 tasks due on that day. The tasks list also updates
  accordingly to the date inputs users provide. 

* **Enhancements**:
  * Updated GUI colour scheme for tasks box: [\#68](https://github.com/AY2021S1-CS2103T-T10-2/tp/pull/68)
  * Wrote additional tests for existing features to increase coverage from 73.07% to 75.23%: [\#81](https://github.com/AY2021S1-CS2103T-T10-2/tp/pull/81)
  * Polished GUI colour scheme for Planner tab: [\#104](https://github.com/AY2021S1-CS2103T-T10-2/tp/pull/104)

* **Documentation**:
  * User Guide:
    * Edited documentation for `Quickstart`, `Features` and the feature `edit`: [\#50](https://github.com/AY2021S1-CS2103T-T10-2/tp/pull/50)
    * Added documentation for the feature `tag`: [\#61](https://github.com/AY2021S1-CS2103T-T10-2/tp/pull/61)
    * Added documentation for the feature `planner`: [\#105](https://github.com/AY2021S1-CS2103T-T10-2/tp/pull/105), [\#131](https://github.com/AY2021S1-CS2103T-T10-2/tp/pull/131)
    * Updated formatting and updated documentation for the feature `planner`: [\#184](https://github.com/AY2021S1-CS2103T-T10-2/tp/pull/184)
    * Updated Command Summary and updated documentation for the feature `planner`: [\#195](https://github.com/AY2021S1-CS2103T-T10-2/tp/pull/195)
    * Standardise screenshots for all figures: [\#209](https://github.com/AY2021S1-CS2103T-T10-2/tp/pull/209), [\#215](https://github.com/AY2021S1-CS2103T-T10-2/tp/pull/215)
    * Added labelled GUI: [\#211](https://github.com/AY2021S1-CS2103T-T10-2/tp/pull/211)
    * Updated documentation for the feature `tag` and Commands Section: [\#239](https://github.com/AY2021S1-CS2103T-T10-2/tp/pull/239)
  * Developer Guide:
    * Added Non-Functional Requirements (NFRs) under Appendix: Requirements: [\#13](https://github.com/AY2021S1-CS2103T-T10-2/tp/pull/13)
    * Added documentation for the feature `planner`: [\#88](https://github.com/AY2021S1-CS2103T-T10-2/tp/pull/88), [\#97](https://github.com/AY2021S1-CS2103T-T10-2/tp/pull/97)
    * Updated documentation for the feature `planner`: [\#186](https://github.com/AY2021S1-CS2103T-T10-2/tp/pull/186)
    * Updated User Stories and added instructions for manual testing for the feature `planner`: [\#194](https://github.com/AY2021S1-CS2103T-T10-2/tp/pull/194)
    * Added table of contents: [\#216](https://github.com/AY2021S1-CS2103T-T10-2/tp/pull/216)
    * Updated documentation for the feature `planner`: [\#221](https://github.com/AY2021S1-CS2103T-T10-2/tp/pull/221)
    * Added Use Cases for the feature `planner` and updated figure numbers: [\#226](https://github.com/AY2021S1-CS2103T-T10-2/tp/pull/226)

* **Team-based tasks**:
  * Setting up tools:
    * Update `README.md` with `CI` and `CodeCov`: [\#49](https://github.com/AY2021S1-CS2103T-T10-2/tp/pull/49)
    * Enable assertions in `build.gradle`: [\#88](https://github.com/AY2021S1-CS2103T-T10-2/tp/pull/88)
    * Update `README.md` with latest features: [\#226](https://github.com/AY2021S1-CS2103T-T10-2/tp/pull/226)
  * Project management:
    * Managed release `v1.1` on GitHub
    * Maintained issue tracker on GitHub

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#15](https://github.com/AY2021S1-CS2103T-T10-2/tp/pull/15), [\#101](https://github.com/AY2021S1-CS2103T-T10-2/tp/pull/101),
  [\#112](https://github.com/AY2021S1-CS2103T-T10-2/tp/pull/112), [\#125](https://github.com/AY2021S1-CS2103T-T10-2/tp/pull/125),
  [\#126](https://github.com/AY2021S1-CS2103T-T10-2/tp/pull/126), [\#175](https://github.com/AY2021S1-CS2103T-T10-2/tp/pull/175),
  [\#196](https://github.com/AY2021S1-CS2103T-T10-2/tp/pull/196), [\#205](https://github.com/AY2021S1-CS2103T-T10-2/tp/pull/205),
  [\#219](https://github.com/AY2021S1-CS2103T-T10-2/tp/pull/219)
  * Reported bugs and suggestions for other teams in the class (during Practical Dry Run): [Issues link](https://github.com/ruilingk/ped/issues)
