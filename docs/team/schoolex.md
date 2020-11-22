---
layout: page
title: Pang Biao Yi's Project Portfolio Page
---

## Project: Hall-y

Hall-y is a hall residents' contact management application. It helps to keep track and update the residents' record, so the hall admin staff can easily find the residents based on the saved records.

Given below are my contributions to the project.

* **New Feature**: Addition of block and room fields
    - What it does: Allows the user to create a new resident with information about their block and room
    - Justification: This will allow the user to keep track of block and room information. 
    It allows the filter function to filter residents by block and room when required.
    - Highlights: There are many constraints for this field such as restricting range of values allowed and handling the 
    various invalid user inputs. Hence,i had to implement rigorous validation checks to ensure that the app works correctly.
    Block and room are also consolidated together as 1 field in the add/edit command so there were additional steps that had to be
    taken in order to ensure that it integrates seamlessly with Hall-y.
    - Credits: The code uses a similar structure as existing fields such as email which was provided in the original AB3

* **New Feature**: Block and room configurations
    - What it does: Allows the user to modify the allowed range of blocks and room settings by modifying a json settings file.
    - Justification: Users will be managing different halls and the halls may have a different structure. This feature will allow
    the user to modify the configuration as required.
    - Highlights: The code had to be tested rigorously to account for a corrupted settings file so that the application can recover from it
and not crash. There were many possible invalid settings and i had to do multiple checks to cover as many edge cases as possible.
    - Credits: This is built on top of the preferences.json file provided in the original AB3 which made use of the Jackson library.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=schoolex&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)
* **Project management**: 
    - Coordinated and managed tasks related to the User Guide

* **Enhancements to existing features**: UI enhancement
    - What it does: It made the UI more visually pleasant by changing the colour schemes,font and layout.
    [\#165](https://github.com/AY2021S1-CS2103T-T11-2/tp/pull/165)
    - Justification: A visually pleasant UI will be more attractive to users
    - Credits: Library functions provided by JavaFX 

* **Documentation**:
  * User Guide: Added documentation for the features `add`, `edit`, `clear`, `exit` and 
  `Changing the blocks and rooms configuration` [\#247](https://github.com/AY2021S1-CS2103T-T11-2/tp/pull/247)
  * Developer Guide: Added implementation details of the `Changing the blocks and rooms configuration` feature
  [\#111](https://github.com/AY2021S1-CS2103T-T11-2/tp/pull/111)

* **Community**: 
    - PRs reviewed (with non-trivial review comments): [\#117](https://github.com/AY2021S1-CS2103T-T11-2/tp/pull/117), [\#231](https://github.com/AY2021S1-CS2103T-T11-2/tp/pull/231)
    - Reported bugs and suggestions for other teams in the class (examples: [\#6](https://github.com/schoolex/ped/issues/6))


