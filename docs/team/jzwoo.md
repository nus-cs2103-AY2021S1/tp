---
layout: page
title: Woo Jian Zhe's Project Portfolio Page
---

## Project: FaculType

FaculType is a desktop app for managing faculty members and their modules, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI) created with JavaFX. It is written in Java, and has about 11 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the ability to delete existing modules.
  * What it does: Allows the user to delete existing modules in the module list.
  * Justification: This feature improves the product because it allows the user to remove modules as part of the module management capability of FaculType.
  * Highlights: This enhancement required an in-depth analysis of design alternatives. The implementation was challenging because it required the use of module codes instead of the index number for deletion.

* **New Feature**: Added a search function for modules.
  * What it does: Allows the user to search for modules by different parameters.
  * Justification: This feature improves the product significantly because it allows for more convenient management when there are a lot of modules involved.
  * Highlights: This enhancement required an in-depth analysis of design alternatives. The implementation was challenging due to the different parameters involved. There was a need to make the search function focused but also flexible enough such that a more general search could be performed.

* **New Feature**: Added the ability to match SubStrings.
  * What it does: Checks if a substring is part of a within a string, while ignoring the case of the strings.
  * Justification: This feature improves the product because it allows for a more flexible search option in `find` and `findmod`.
  * Highlights: This feature requires understanding of how to manipulate strings and the requirements of our search functions. 

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=jzwoo)

* **Enhancements to existing features**:
  * Added icons for contact attributes (Pull requests
   [\#150](https://github.com/AY2021S1-CS2103-T14-1/tp/pull/150))
  * Wrote additional tests for existing features to increase coverage (Pull requests 
  [\#69](https://github.com/AY2021S1-CS2103-T14-1/tp/pull/69), 
  [\#89](https://github.com/AY2021S1-CS2103-T14-1/tp/pull/89), 
  [\#242](https://github.com/AY2021S1-CS2103-T14-1/tp/pull/242))
  * Added a model stub class for testing (Pull requests 
  [\#146](https://github.com/AY2021S1-CS2103-T14-1/tp/pull/146))
  * Added checks for duplicate prefixes of multiple commands (Pull requests 
  [\#222](https://github.com/AY2021S1-CS2103-T14-1/tp/pull/222))

* **Documentation**:
  * User Guide:
    * Added documentation for the features `delmod` and `findmod` 
    [\#27](https://github.com/AY2021S1-CS2103-T14-1/tp/pull/27), 
    [\#228](https://github.com/AY2021S1-CS2103-T14-1/tp/pull/228)
  * Developer Guide:
    * Added implementation details of the `delmod` and `findmod` feature. 
    [\#136](https://github.com/AY2021S1-CS2103-T14-1/tp/pull/136), 
    [\#228](https://github.com/AY2021S1-CS2103-T14-1/tp/pull/228)
    * Added and modified glossary 
    [\#24](https://github.com/AY2021S1-CS2103-T14-1/tp/pull/24), 
    [\#243](https://github.com/AY2021S1-CS2103-T14-1/tp/pull/243)

* **Community**:
  * PRs reviewed (with non-trivial review comments): 
  [\#105](https://github.com/AY2021S1-CS2103-T14-1/tp/pull/105)
  * Reported bugs and suggestions for other teams in the class (examples: 
  [1](https://github.com/jzwoo/ped/issues/1), 
  [2](https://github.com/jzwoo/ped/issues/2), 
  [3](https://github.com/jzwoo/ped/issues/3), 
  [4](https://github.com/jzwoo/ped/issues/4))
