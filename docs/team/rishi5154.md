---
layout: page
title: Rishi Ravikumar's Project Portfolio Page
---

## Project: TAsker

TAsker is a desktop address book application used for teaching Software Engineering principles. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Written below are my contributions to the project.
 
- **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=&sort=totalCommits&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&tabAuthor=Rishi5154&tabRepo=AY2021S1-CS2103T-F11-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code)

- **Enhancements implemented**:

 - **Implement the student model**: Update existing Person model to match actual needs of student
 
    - What it does: 
      Includes student specific fields such as their matriculation number, telegram.
 
    - Justification: 
      The given Person model simply lacked the precise fields necessary for basic student administration.
 
    - Highlights:
      Update edit operation in order to edit the student objects, with all new fields added.
 
  - **Unattend command**: Appended a command that enables for the unmarking of student's attendance.
  
  - **Archive command**: A command that copies the current data of TAsker at `/data` directory.
  
  - **Consultation management**:
     - Set-up the whole backend functionality from scratch to display on UI, with **conceptualisation** & **implementation** of Consultation model
     - **Add Consult command**: A command that enables for the addition of a consultation.
        - Regulation of consultation addition with checks like whether personal consults can be added with conflicting consultations
     - **List Consult command**: A command that allows user to list all consultations.
  
  - **Unit tests**: Inserted JUnit tests for all active commands I added & updated existing tests accordingly. 

- **Contributions to documentation**: Updated UG for `unattend`, `archive`, `add-consult` & `list-consult` operations with examples.

- **Contributions to DG**: Updated DG for `list`, `find`, `unattend`, `archive` operations, with UML sequence diagrams for each command.

- **Project management**:

    - Attend and participate proactively in weekly project meetings.
    - Actively test out new features added by teammates and relaying the status of features actively

- **Contributions to team-based tasks**:

    - **Review/mentoring contributions**: 
        - Reviewed and raised non-trivial thoughts for PRs at [#196](https://github.com/AY2021S1-CS2103T-F11-1/tp/pull/196), [#183](https://github.com/AY2021S1-CS2103T-F11-1/tp/pull/183), [#174](https://github.com/AY2021S1-CS2103T-F11-1/tp/pull/174),  [#172](https://github.com/AY2021S1-CS2103T-F11-1/tp/pull/172), [\#156](https://github.com/AY2021S1-CS2103T-F11-1/tp/pull/156), [\#94](https://github.com/AY2021S1-CS2103T-F11-1/tp/pull/94) & many more
    - **Contributions beyond project**:
        
        -  Found crucial bugs [bug1](https://github.com/AY2021S1-CS2103T-W16-3/tp/issues/240) & enhancement [bug2](https://github.com/AY2021S1-CS2103T-W16-3/tp/issues/241)
        in a well-constructed and effort-ladden [TP](https://github.com/AY2021S1-CS2103T-W16-3/tp)
        - Helped to test the working status of another group's project jar file [TP](https://github.com/AY2021S1-CS2103T-T17-3/tp)
        
