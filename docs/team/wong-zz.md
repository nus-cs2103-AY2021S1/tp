---
layout: page
title: Wong Zheng Zhi's Project Portfolio Page
---

## Project: MediBook

MediBook is a desktop medical records software targeting doctors and administrative staffs in clinics or hospitals to help manage patient data. It is optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI), implemented using JavaFX. If you can type fast, MediBook can get your patient records management tasks done faster than traditional GUI apps.

MediBook aims to increase efficiency in a clinical setting by providing a convenient interface for clinical staff to record and keep track of patient details whilst also having an intuitive workflow that reduces repetition in order to reduce repeated work. Besides just managing patient data, MediBook is also able to generate and visualize data for the user. For example, MediBook is able to generate charts of a patient's height, weight and BMI, helping doctors to better track the progress of the patient.

Overall, MediBook aims to reduce the inefficiency in other existing patient management software while also being more user-friendly and intuitive.

Given below are my contributions to the project.

* **New Feature**: Added a `Record` class.
  * What it does: Allows the application to store a history of a patient's height and weight.
  * Justification: This feature allows the application to record past height and weight to be used in the Charts UI.
  * Highlights: This enhancement affects existing commands and commands to be added in future. It required an in-depth analysis of design alternatives. The implementation too was challenging as it required changes to existing commands.
  
* **New Feature**: Implemented storage of the `Record` class.
  
* **New Feature**: Implemented storage of medical notes data.
  * What it does: Stores the list of medical notes in `JSON` format for each individual patient.
  * Justification: This feature allows the application to start up without having to load the medical notes of every single patient, reducing start up time and memory used.
  * Highlights: There were many features that had to modified in order to ensure that the application is able to handle the storing of patient's medical notes in separate files. For example, the application has to rename the file if the user chooses to edit the ID of the patient since the file name is determined by the ID of the patient.
  
* **New Feature**: Added a `Context` class.
  * What it does: Stores information related to the command that was just called.
  * Justification: Due to the `Command` design pattern, `LogicManager` is unable to know what command was just executed and the `Context` class addresses this issue, allowing `LogicManager` to perform actions based on the commadn that was most recently executed.
  * Highlights: It took a long to decide on the implementation of the `Context` class in order to prevent any cyclic dependencies between `LogicManager` and `Command`. The decision was made to implement `Context` as an interface in order to prevent the cyclic dependency and also to maintain the `Command` design pattern.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&tabAuthor=Wong-ZZ&tabRepo=AY2021S1-CS2103T-F13-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code)

* **Enhancements to existing features**:
  * Added a new `Height` field for the Patient class.
  * Improved the find function such that the application is able to match substrings and find patients using multiple fields.
  * Modified `NameContainsKeywordsPredicate` to `FieldContainsKeywordsPredicate` such that it is able to handle fields other than `Name`.

* **Team-Based Tasks**:
  * Setting up the GitHub Team Organisation.
  * Setting up the GitHub Team Repo.
  * Created labels for the issue tracker.

* **Project management**:
  * Tagged and labelled all issues created during PE dry run.

* **Documentation**:
  * User Guide:
    * Added documentation for the features `find` and `charts`.
    * Added subsection for editing of save data.
    * Added overview for the user guide.
    * Added screenshots in the `Getting started` section to familiarize new users with the UI.
    * Fixed multiple formatting issues.
  * Developer Guide:
    * Added implementation details for the enhanced `find` command.
    * Added implementation details for the `Context` interface.
    * Added implementation details for the `Record` class.
    * Added some use cases.
    * Updated class diagram for `Storage Component`, added class and sequence diagrams for enhanced `find` command, `Context` interface and `Record` class.

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#39](https://github.com/AY2021S1-CS2103T-F13-3/tp/pull/39), [\#58](https://github.com/AY2021S1-CS2103T-F13-3/tp/pull/58), [\#13](https://github.com/AY2021S1-CS2103T-F13-3/tp/pull/13), [\#77](https://github.com/AY2021S1-CS2103T-F13-3/tp/pull/77)
  * Reported multiple high/medium severity issues for other teams in the class (examples: [1](https://github.com/AY2021S1-CS2103T-W16-3/tp/issues/242), [2](https://github.com/AY2021S1-CS2103T-W16-3/tp/issues/244), [3](https://github.com/AY2021S1-CS2103T-W16-3/tp/issues/245), [4](https://github.com/AY2021S1-CS2103T-W16-3/tp/issues/246))
