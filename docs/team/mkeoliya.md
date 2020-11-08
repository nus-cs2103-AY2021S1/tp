---
layout: page
title: Mayank Keoliya's Project Portfolio Page
---

**ResiReg** (**Res**idential **Reg**ulation) is a desktop app designed to help admin staff manage housing in Residential Colleges (RCs) in NUS. ResiReg has a GUI, but users can interact with ResiReg entirely using CLI-style commands. ResiReg is written in Java, with the GUI created using JavaFX.

Given below are a summary of my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=mkeoliya&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

* **New Feature**: Added support for aliasing ([#101](https://github.com/AY2021S1-CS2103-T16-3/tp/pull/101))
  * What it does: The `alias` command adds a user-defined alias for a command word, allowing the (typically shorter) alias to be used in place of the command word 
  * Justification: Optimizes the user's CLI experience, especially for advanced users, as users can define shorter and more succinct aliases that are more intuitive to them. Makes the typing of commands more flexible.
  * Highlights: This feature entailed a heavy refactoring of the way commands were linked to their parsers, choosing a single-source-of-truth approach of using an enum to link them, over AB3's ad-hoc switch statements. Multiple iterations and an in-depth analysis of design alternatives were needed. It was also tested comprehensively to handle several edge-cases (such as when the user tries to define an alias to be a command word). 

* **New Feature**: Added the ability to move and restore items from a recycling bin ([#112](https://github.com/AY2021S1-CS2103-T16-3/tp/pull/112))
  * What it does: Much like common OSes, items (students and rooms) are automatically moved to the recycling bin when deleted. They remain in the bin for a user-specified amount of time, and can be restored via a `restore` command. 
  * Justification: Users deal with constantly changing residency statuses of hundreds of students and rooms, and a high workload. They might erroneously delete a student or room record prematurely, and this bin feature allows a correction. 
  * Highlights: This feature affects all binnable items that can be added in the future. As before, [two design choices were considered](./DeveloperGuide#design-consideration), but practicality and simplicitly of code drove the first choice to be picked.
  
* **Enhancements to existing features**:
  * Modified AB3 to ResiReg, and support a `Student` class : [#82](https://github.com/AY2021S1-CS2103-T16-3/tp/pull/82)
    - Entailed a heavy refactoring, the addition of multiple fields to the Student class, design choice for creating a `FacultyEnum` and extending AB3 to ensure it could support more than one type of item. 

* **Contributions to user guide**:
  * Documentation for the following commands: aliasing (`alias`, `dealias`, `aliases`) and binning (`delete-student`, `bin`, `restore` and `set-bin-expiry`). 

* **Contributions to developer guide**:
  1. Wrote use cases UC02, UC16-18. 
  2. Updated descriptions for the [Ui component](../DeveloperGuide#ui-component)
  3. [Discussion and implementation of the binning feature](../DeveloperGuide#bin-feature).
  4. Diagrams: updated class diagram for UI component (under point 2), added sequence diagrams for Binning (under point 3). 
  5. Added manual testing instructions for the following commands: `delete-student`, `restore`, and the automatic bin expiry feature.

* **Team tasks**:
  * Created team PR and updated README at the outset [#79](https://github.com/nus-cs2103-AY2021S1/tp/pull/79) and [#1](https://github.com/AY2021S1-CS2103-T16-3/tp/pull/1)
  * Added initial draft of value proposition, target user and NFRs : [#57](https://github.com/AY2021S1-CS2103-T16-3/tp/pull/57)
  * Made release for trial run [v1.3.0](https://github.com/AY2021S1-CS2103-T16-3/tp/releases/tag/v1.3.0) for v1.3
  * Added Githooks for the project [#5](https://github.com/AY2021S1-CS2103-T16-3/tp/pull/5), ensuring that CI jobs do not fail for files inside `src`, boosting git flow turnaround time
  * Handled milestone management and issue tracking for [v1.3](https://github.com/AY2021S1-CS2103-T16-3/tp/milestone/3)
  * Enforced testing coverage guidelines, and issue management after completion of PE-D

* **Community**:
  * PRs reviewed (with non-trivial review comments): [#181](https://github.com/AY2021S1-CS2103-T16-3/tp/pull/181), with a [comprehensive list of all PRs](https://github.com/AY2021S1-CS2103-T16-3/tp/pulls?q=is%3Apr+is%3Aclosed+reviewed-by%3Amkeoliya)
  * Bug reports for my team: [#90](https://github.com/AY2021S1-CS2103-T16-3/tp/issues/90) and [#116](https://github.com/AY2021S1-CS2103-T16-3/tp/issues/116)
  * Bug reports made for other teams (9/10 accepted): [ped](https://github.com/mkeoliya/ped/issues)
  * Secured approval for using Jackson-Data-Format-Text for tP projects: [#357](https://github.com/nus-cs2103-AY2021S1/forum/issues/357)
  * Forum participation: [1](https://github.com/nus-cs2103-AY2021S1/forum/issues/24 (thread)), [2 (thread)](https://github.com/nus-cs2103-AY2021S1/forum/issues/351#issuecomment-715725610), [3](https://github.com/nus-cs2103-AY2021S1/forum/issues/389#issuecomment-722804116)
