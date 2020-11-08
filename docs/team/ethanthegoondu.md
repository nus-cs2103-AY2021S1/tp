---
layout: page
title: Ethan Rozario's Project Portfolio Page

---

## Project: Trackr

### Project Overview

Trackr is an application for teaching assistants (TAs) who prefer to use a desktop application for managing their
student records. It is uses a Command Line Interface (CLI), while still retaining the benefits of a Graphical User
Interface (GUI). It is written in Java, and currently has over 10kLoC.

### Summary of Contributions

- Code contributed: [tP Code Dashboard](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&tabAuthor=EthanTheGoondu&tabRepo=AY2021S1-CS2103T-W12-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code)
- Enhancements implemented:
  - Storage system
    - Implemented the internal system of converting objects into the serializable JSON versions that the Jackson class can use.
    - Designed the structure of storage by designing how different objects store other objects and how these objects are to be accessed by other classes.
    - Ensured continued support of updating this system when the corresponding non-JSON counterparts update so saving and loading can always work and be tested for bugs.
    - Wrote testcases to ensure even when JSON file is tinkered with, Trackr can throw the proper exceptions and continue to function without crashing.
  - Class implementation
    - Implementation for fields in the Module, TutorialGroup, Student classes into their own classes i.e. ModuleId, TutorialGroupId, DayOfWeek, TimeOfDay.
    - Helped with implementation of Attendance class that belonged to the Student class.
    - Ensured all new classes would be properly incorporated into other systems that use them e.g. Parsers and commands.
  - Bug catching / fixing
    - [Helped catch bugs](https://github.com/AY2021S1-CS2103T-W12-2/tp/issues?q=is%3Aissue+is%3Aclosed+label%3Atype.Bug+author%3Aethanthegoondu) from my own implementations and implementations of other team members.
    - [Helped fix bugs](https://github.com/AY2021S1-CS2103T-W12-2/tp/pulls?q=is%3Aclosed+is%3Apr+label%3Atype.Bug++author%3Aethanthegoondu) from my own implementations and implementations of other team members.
  - Testing
    - Wrote testcases for storage system as mentioned before.
    - [Provided testcase utilities](https://github.com/AY2021S1-CS2103T-W12-2/tp/pull/196/commits/9785cd81f61feb7992af6f5403ff2235883ea013) for the use of all team members such as sample databases for testing purposes.
  - Others
    - Added checks in ParserUtil to fix bugs regarding exception handling and to ensure command parsing provides the appropriate error messages when invalid parameters are provided.
    - All of my pull requests can be viewed [here](https://github.com/AY2021S1-CS2103T-W12-2/tp/pulls?q=is%3Apr+author%3Aethanthegoondu+is%3Aclosed+).
- Contributions to documentation:
  - Wrote introductory sections of UG and updated table of contents with the correct links to the correct sections in the UG.
  - Ensured formatting consistency across the entire UG, setting conventions of how to present information in our UG and fixing grammatical errors / typos.
  - Wrote section on attendance features, namely commands that involve attendance.
- Contributions to the DG:
  - Wrote / edited design section for DG and updated / created diagrams for them.
  - Ensured formatting consistency across the entire DG, setting conventions of how to present information in our DG and fixing grammatical errors / typos.
- Contributions to team-based-tasks:
  - Helped release [trial version of v1.3](https://github.com/AY2021S1-CS2103T-W12-2/tp/releases/tag/v1.3.trial)
  - Done most of refactoring from the name Addressbook to Trackr, [performed renames of UI elements and elements in code.](https://github.com/AY2021S1-CS2103T-W12-2/tp/pull/39)
  - [Safely deleted classes no longer required for Trackr to use](https://github.com/AY2021S1-CS2103T-W12-2/tp/pull/178) that used to belong to AB3, ensured the removal would not cause bugs or crashes to the application.
  - [Reorganise classes into different packages](https://github.com/AY2021S1-CS2103T-W12-2/tp/pull/63) for ease of navigation and updating the team whenever doing so.
  - [Move blocks of code written by other teammates](https://github.com/AY2021S1-CS2103T-W12-2/tp/pull/182) for consistency purposes and to better adhere to abstraction principles and updating the team whenever doing so.
- Review/mentoring contributions:
  - [Reviewed PRs](https://github.com/AY2021S1-CS2103T-W12-2/tp/pulls?q=is%3Apr+reviewed-by%3Aethanthegoondu)
  - [Commented on PRs](https://github.com/AY2021S1-CS2103T-W12-2/tp/pulls?q=is%3Apr+is%3Aclosed+commenter%3Aethanthegoondu)
  - Advice given for code of other team members through external means of communication i.e. Telegram chat and Google Meet meetings
    - Reported bugs and pointed out inconsistencies in other team member's code
    - Helped with code tracing if team member has difficulty finding the cause of a bug
- Contributions beyond the project team:
  - [Bug reports for other project](https://github.com/EthanTheGoondu/ped/issues)

