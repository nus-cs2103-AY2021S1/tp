---
layout: page
title: Lin Yingqi's Project Portfolio Page
---

## Project: TR4CKER

TR4CKER is a desktop app that helps SoC students track their tasks, meet their deadlines, and stay productive. It is
optimized for Computing students familiar with CLI, who can manage their tasks efficiently by typing in commands.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=yingqi0607)

* **Morphing of AddressBook**:
  * Refactor Address class to TaskDescription class.[#44](https://github.com/AY2021S1-CS2103T-T10-2/tp/pull/44)

* **Change to exiting feature**:
  * Change: List command will now list out names all pending tasks to cooperate with the new todo command.
  * Change: Delete command will now delete a todo task together with the same pending task.
  * Change: Edit command will now edit a todo task together with the same pending task.

* **New Feature: Added Daily tab**
  * What it does: Allows users to add existing tasks to a daily todo list so that users can plan what they aim to complete for the day. 
  Users can delete a todo task in daily todo list without deleting the existing pending task.
  * Justification: This feature helps users to decide what they want to complete for a day so that there is a daily plan 
  for them to keep track for their pending tasks.
  * Highlights: This feature has a daily todo list. Users only need to choose from existing pending tasks and add them into 
  daily todo list by indicating the task indexes. They do not need to create new daily todo task. This saves the trouble of having to add tasks two times.

* **Enhancements**:
  * Updated UI appearance for background and heading: [#85](https://github.com/AY2021S1-CS2103T-T10-2/tp/pull/85)

* **Documentation**:
  * User Guide:
    * Added documentation for the feature `daily` : [#103](https://github.com/AY2021S1-CS2103T-T10-2/tp/pull/103)
    * Edited documentation for `find` and `list` command.
  * Developer Guide:
    * Added User Stories under Appendix: Requirements.
    * Added Documentation for the feature `daily`.
    * Added Use cases under Appendix: Requirements for the feature `daily`.

* **Community**:
  * PRs reviewed (with non-trivial review comments): [#229](https://github.com/AY2021S1-CS2103T-T10-2/tp/pull/229), 
  [#62](https://github.com/AY2021S1-CS2103T-T10-2/tp/pull/62)
  * Reported bugs and suggestions for other teams in the class (during Practical Dry Run): [Issues link](https://github.com/yingqi0607/ped/issues)
