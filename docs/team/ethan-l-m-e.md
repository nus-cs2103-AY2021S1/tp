---
layout: page
title: Lim Ethan's Project Portfolio Page
---

## Project: TR4CKER

TR4CKER is a desktop app that helps SoC students track their tasks, meet their deadlines, and stay productive. It is
optimized for Computing students familiar with CLI, who can manage their tasks efficiently by typing in commands.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=ethan-l-m-e)

* **Morphing of AddressBook:**
  * Refactor Email class to CompletionStatus class: [\#34](https://github.com/AY2021S1-CS2103T-T10-2/tp/pull/34)

* **Change to existing feature**: Updated `find` command.
  * Change: Find command now returns results even if query only matches partially to task name.
  * Justification: Less strict searching, users do not need to remember exact task names they may have forgotten over time.

* **Change to existing feature**: Updated `edit` & `add` command.
  * Change: Add command can now add task with module, Edit command now can edit module of a task.

* **New Feature**: Added Module tab.
  * What it does: Allows users view tasks according to module code in a separate tab.
  * Justification: This feature provides users with the functionality to create modules and assign tasks to them. This lets
  users view their tasks according to module code instead of according to deadlines. This way users do not have to search
  specifically for particular modules.
  * Highlights: Users create modules in the Module tab, and then assign tasks in the Home page with the particular
  module code. Assigning a task will let it appear automatically in the Module tab, under the same module.

* **Enhancements**:
  * Updated UI appearance of Module tab. [\#111](https://github.com/AY2021S1-CS2103T-T10-2/tp/pull/111)
  * Added tab switching and updated UI for highlighting current tab: [\#69](https://github.com/AY2021S1-CS2103T-T10-2/tp/pull/69)
  * Bug fixes: [\#185](https://github.com/AY2021S1-CS2103T-T10-2/tp/pull/185), [\#224](https://github.com/AY2021S1-CS2103T-T10-2/tp/pull/224)

* **Documentation:**
  * User Guide:
    * Edited documentation for `find` command: [\#53](https://github.com/AY2021S1-CS2103T-T10-2/tp/pull/53)
    * Added documentation for the feature `modules`: [\#134](https://github.com/AY2021S1-CS2103T-T10-2/tp/pull/134)
    * Updated formatting and updated documentation for the feature `modules`: [\#206](https://github.com/AY2021S1-CS2103T-T10-2/tp/pull/206)
  * Developer Guide:
    * Added Use cases (UC04 - UC07): [\#15](https://github.com/AY2021S1-CS2103T-T10-2/tp/pull/15)
    * Added documentation for the feature `modules`: [\#100](https://github.com/AY2021S1-CS2103T-T10-2/tp/pull/100)
    * Updated documentation and diagrams for the feature `modules`: [\#234](https://github.com/AY2021S1-CS2103T-T10-2/tp/pull/234)
    * Added instructions for manual testing: [\#206](https://github.com/AY2021S1-CS2103T-T10-2/tp/pull/206)
    * Added Use cases under Appendix: Requirements for the feature `modules`. [\#234](https://github.com/AY2021S1-CS2103T-T10-2/tp/pull/234)

* **Team-based tasks**:
  * Project management:
    * Maintained issue tracker on GitHub

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#122](https://github.com/AY2021S1-CS2103T-T10-2/tp/pull/122), [\#207](https://github.com/AY2021S1-CS2103T-T10-2/tp/pull/207)
  * Reported bugs and suggestions for other teams in the class (during Practical Dry Run): [Issues link](https://github.com/ethan-l-m-e/ped/issues)
