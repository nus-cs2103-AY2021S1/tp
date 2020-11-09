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
  
    Allows users to view attendance of a student at a specified date.
    
  - Justification:
  
    Teaching assistants may need to know whether a student is present or absent
    on during tutorial dates.
    
    This feature allows them to perform that check.
    
- **Delete consultation command**: Added a command that allows the user to delete a consultation slot.

  - What it does:
  
    Allows users to delete consultations.
    
  - Justification:
  
    In the event users have keyed in an incorrect consultation slot
    or want to remove a consultation slot, this feature allows them to do so.

- **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=&sort=groupTitle&sortWithin=title&since=2020-08-14&until=2020-11-09&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&tabAuthor=kwannoel&tabRepo=AY2021S1-CS2103T-F11-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code)

- **Project management**:

  - Organize weekly project meetings
  - Distribute weekly tasks for first sprint (v1.1 - v1.2).
  - Check on team members to see if they need help for tasks.

- **Enhancements to existing features**:

  - Bugfixes:
    - [\#196]()
      Prevent group consultations with differing locations
    - [\#186]()
      Fix duplicate arguments to commands
    - [\#160]()
      Fix attendance date parsing
    - [\#157]()
      Prevent duplicate matric and telegram handle
    - [\#156]()
      Declare behaviour of invalid parameter tags
    - [\#154]()
      Fix show attendance error message
    

- **Documentation**:

  - User Guide:
    - Remove address field from examples [\#101]()
    - Fix linking for the help guide to our user guide
  - Developer Guide:
    - Added implementation details of the `show attendance` feature.
    - Added implementation details of the updated `Person` model.

- **Community**:

  - PRs reviewed (with non-trivial review comments): [\#12](), [\#32](), [\#19](), [\#42]()

