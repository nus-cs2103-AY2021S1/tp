---
layout: page
title: Aung Thuya Oo's Project Portfolio Page
---

## Project: Hall-y

Hall-y is a hall residents' contact management application. It helps to keep track and update the residents' record, so the hall admin staff can easily find the residents based on the saved records.

Given below are my contributions to the project.

* **New Feature**: Find residents by various characteristics
  * What it does: Allows the user to find residents by their characteristics such as their
    name, matriculation number, floor number, room number, gender and block.
  * Justification: This allows the user to find a particular resident quickly,
    especially if the user knows the particular resident's characteristics.
  * Highlights: The find command changes so that when executed, several polymorphic predicates 
  will be combined and used in the `updateFilterPerson` function. 
  * Credits: Worked off existing `FindCommand` implementation.
  
* **New Feature**: Adding gender to residents' characteristics
  * What it does: Allows the user to view, and edit residents' gender.
  * Justification: This allows the user to know whether a particular resident is a male or female, which is useful information in hall.
  * Highlights: Discussion was done whether to add an unknown gender.
  
* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=athuyaoo)

* **Project management**: 
    * Managed release `v1.2.1 Trial JAR release`, on GitHub.
    * Write issues to github (regarding user stories, 
    for example:  [\#121](https://github.com/AY2021S1-CS2103T-T11-2/tp/issues/121),
    [\#122](https://github.com/AY2021S1-CS2103T-T11-2/tp/issues/122),
    [\#123](https://github.com/AY2021S1-CS2103T-T11-2/tp/issues/123)).

* **Enhancements to existing features**: 
    * Wrote additional tests for added features to increase coverage from 69% to 72%:
    [\#250](https://github.com/AY2021S1-CS2103T-T11-2/tp/pull/250).
    * Restricted phone length to 15 digits prevent user error:
     [\#228](https://github.com/AY2021S1-CS2103T-T11-2/tp/pull/250).
    * Updated error messages for duplicate residents so that user mistakes are more clear:
     [\#287](https://github.com/AY2021S1-CS2103T-T11-2/tp/pull/287).
    * Wrote additional validation to ensured that all residents have unique matriculation number and rooms when 
    doing add and edit commands: [\#226](https://github.com/AY2021S1-CS2103T-T11-2/tp/pull/226).
    * Improved usage instruction for user in delete and edit commands: 
    [\#229](https://github.com/AY2021S1-CS2103T-T11-2/tp/pull/229).

* **Documentation**:
  * User Guide: 
    * Added missing documentation for the feature "Find Command":
    [\#257](https://github.com/AY2021S1-CS2103T-T11-2/tp/pull/257).
    * Added more information to the frequently asked questions (FAQ) section.
    * Add matriculation year, number to glossary: [\#24](https://github.com/AY2021S1-CS2103T-T11-2/tp/pull/24).
  * Developer Guide: 
    * Add non-functional requirements: [\#25](https://github.com/AY2021S1-CS2103T-T11-2/tp/pull/25).
    * Added missing documentation for the changed feature "Find Command": [\#304](https://github.com/AY2021S1-CS2103T-T11-2/tp/pull/304).
    
* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#63](https://github.com/AY2021S1-CS2103T-T11-2/tp/pull/63),
  [\#100](https://github.com/AY2021S1-CS2103T-T11-2/tp/pull/100),
  [\#142](https://github.com/AY2021S1-CS2103T-T11-2/tp/pull/142),
  [\#147](https://github.com/AY2021S1-CS2103T-T11-2/tp/pull/147).
  * Reported bugs and gave suggestions for other teams in the class
  (example: [PED issues for T10-3](https://github.com/athuyaoo/ped/issues)).
