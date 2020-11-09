---
layout: page
title: Project Portfolio Page
---

## Project: FaculType

FaculType is a desktop app for managing faculty members and their modules, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI) created with JavaFX. It is written in Java, and has about 11 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the ability to assign contacts to modules
  * What it does: Allows the user to assign a particular contact to one or more modules in the active semester.
  * Justification: This feature improves the product by helping the user keep track of the assignments of faculty members to the modules that they instruct. This is one of the main value propositions of FaculType, 
  as it allows faculty leaders to manage the relationship between faculty members and modules instead of managing faculty members and faculty modules separately.
  * Highlights: This enhancement required an in-depth understanding of how the `Model` component works. Development could only be done after the module management features were finished.
  
* **New Feature**: Added the ability to switch between semesters
  * What it does: Allows the user to switch to the other semester's module list.
  * Justification: This feature improves the product by allowing the user to switch to another module configuration quickly. This is also one of the main value propositions of FaculType, 
   because a particular semester/term tends to not change a lot from year to year, but the modules offered in Semester 1 might differ greatly from those offered in Semester 2 (and their instructors as well).
   The ability to switch to another configuration is much more convenient than having to erase and re-add module data each semester.
  * Highlights: This enhancement required an in-depth analysis of design alternatives. A change to the `AddressBook` structure was also required. The result of all module management features now depend on which semester is the active semester.

* **New Feature**: Added the ability to add remarks
  * What it does: Allows the user to add additional information or personal remarks to contacts.
  * Justification: This feature improves the product by helping the user keep track of useful additional information about the contact that cannot be stored inside the contact's attributes.
  * Highlights: This enhancement required an understanding of how the application parses and executes commands. This was one of the first enhancements made for FaculType.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=justintzuriel&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&tabAuthor=justintzuriel&tabRepo=AY2021S1-CS2103-T14-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other)

* **Project management**:
  * Managed all team releases (`v1.1` - `v1.4`) on GitHub
  * Managed the issue tracker and created every issue for each iteration
  * Assigned issues and delegated tasks to team members
  * Led the weekly project meetings
  * Recorded all demo videos

* **Enhancements to existing features**:
  * Designed the FaculType icon (Pull requests [\#94](https://github.com/AY2021S1-CS2103-T14-1/tp/pull/94))
  * Modified command messages to be more descriptive (Pull requests [\#181](https://github.com/AY2021S1-CS2103-T14-1/tp/pull/181))
  * Wrote additional tests for model manager to increase coverage (Pull requests [\#227](https://github.com/AY2021S1-CS2103-T14-1/tp/pull/227))
  * Added sample modules (Pull requests [\#178](https://github.com/AY2021S1-CS2103-T14-1/tp/pull/178))
  
* **Documentation**:
  * User Guide:
    * Added documentation for the features `edit`, `remark` and `switch` 
    [\#32](https://github.com/AY2021S1-CS2103-T14-1/tp/pull/32),
    [\#33](https://github.com/AY2021S1-CS2103-T14-1/tp/pull/33),
    [\#161](https://github.com/AY2021S1-CS2103-T14-1/tp/pull/161)
    * Add current bugs and upcoming features [\#263](https://github.com/AY2021S1-CS2103-T14-1/tp/pull/263)
    * Maintained correctness and consistency from time to time:
    [\#25](https://github.com/AY2021S1-CS2103-T14-1/tp/pull/25),
    [\#38](https://github.com/AY2021S1-CS2103-T14-1/tp/pull/38),
    [\#49](https://github.com/AY2021S1-CS2103-T14-1/tp/pull/49),
    [\#122](https://github.com/AY2021S1-CS2103-T14-1/tp/pull/122),
    [\#178](https://github.com/AY2021S1-CS2103-T14-1/tp/pull/178),
    [\#182](https://github.com/AY2021S1-CS2103-T14-1/tp/pull/182),
    [\#184](https://github.com/AY2021S1-CS2103-T14-1/tp/pull/184),
    [\#186](https://github.com/AY2021S1-CS2103-T14-1/tp/pull/186),
    [\#189](https://github.com/AY2021S1-CS2103-T14-1/tp/pull/189),
    [\#225](https://github.com/AY2021S1-CS2103-T14-1/tp/pull/225)
  * Developer Guide:
    * Added implementation details of the `assign` and `switch` features [\#261](https://github.com/AY2021S1-CS2103-T14-1/tp/pull/261), [\#128](https://github.com/AY2021S1-CS2103-T14-1/tp/pull/128)
    * Added manual testing instructions for the `assign` and `switch` features [\#261](https://github.com/AY2021S1-CS2103-T14-1/tp/pull/261)
    * Add current bugs and upcoming features [\#263](https://github.com/AY2021S1-CS2103-T14-1/tp/pull/263)
    * Maintained correctness and consistency from time to time:
    [\#41](https://github.com/AY2021S1-CS2103-T14-1/tp/pull/41),
    [\#47](https://github.com/AY2021S1-CS2103-T14-1/tp/pull/47),
    [\#261](https://github.com/AY2021S1-CS2103-T14-1/tp/pull/261)

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#61](https://github.com/AY2021S1-CS2103-T14-1/tp/pull/61), [\#95](https://github.com/AY2021S1-CS2103-T14-1/tp/pull/95), [\#144](https://github.com/AY2021S1-CS2103-T14-1/tp/pull/114)
  * Reported bugs and suggestions for other teams in the class (examples: [4](https://github.com/justintzuriel/ped/issues/4), [5](https://github.com/justintzuriel/ped/issues/5), [6](https://github.com/justintzuriel/ped/issues/6), [7](https://github.com/justintzuriel/ped/issues/7))
