---
layout: page
title: Noel Kwan's Project Portfolio Page
---

## Project: TAsker

TAsker is a desktop address book application used for teaching Software Engineering principles. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

- **Implement the student model**: Update Person model to student

  - What it does: 
  
    Includes student specific fields such as their matriculation number, telegram.
    
  - Justification: 
  
    Existing models were missing critical fields which TAs needed for administration.
    
    Including these fields in the model would mean we can extend existing commands
    to add these in as parameters.
    
  - Highlights:
  
    Implementing regex was difficult as both matriculation number and telegram handle
    had different constraints.
    
    Refactoring tests was painful but made easier thanks to the `Builder` pattern we used for
    `Person` tests.

- **Show attendance command**: Added a command that allows the user view attendance of a student at a specific date

  - What it does:
  
    Allows students to display their attendance,

- **Delete consultation command**: Added a command that allows the user to delete a consultation slot.

- **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=&sort=groupTitle&sortWithin=title&since=2020-08-14&until=2020-11-09&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&tabAuthor=kwannoel&tabRepo=AY2021S1-CS2103T-F11-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code)

- **Project management**:

  - Organize weekly project meetings
  - Distribute weekly tasks for first sprint (v1.1 - v1.2).

- **Enhancements to existing features**:

- **Documentation**:

  - User Guide:
    - Added documentation for the features `delete` and `find` [\#72]()
    - Did cosmetic tweaks to existing documentation of features `clear`, `exit`: [\#74]()
    - Fix linking for the help guide to our user guide
  - Developer Guide:
    - Added implementation details of the `show attendance` feature.
    - Added implementation details of the ``

- **Community**:

  TODO

  - PRs reviewed (with non-trivial review comments): [\#12](), [\#32](), [\#19](), [\#42]()
  - PRs reviewed

