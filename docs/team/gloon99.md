---
layout: page
title: Yee Loon's Project Portfolio Page
---

## Project: Modduke

Modduke - Modduke is a desktop app targeted towards NUS students. It allows them to easily manage their contacts, modules and meetings during the semester. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added a meeting add command.
  * What it does: Users are able to create and add new meetings of a module with contacts from that module at a specified date and time.
  * Justification: This feature improves the product significantly for an NUS student with multiple modules that have many group based projects so they are able to easily keep tabs on their schedule.
  * Highlights: This enchancement required a good understanding of the various architectural components of the code as extensive additions were made to the logic and model components.
  It was also extremely challenging because the command is heavily dependent on person and module, and good integration of each part was needed for the feature to function properly.
  * Credits: Code inspiration was taken from AB3's existing add contact feature.

* **New Feature**: Added a meeting delete command
  * What it does: Users are able to delete existing meetings.
  * Justification: This feature improves the product significantly for an NUS student with a changing schedule so that they are able to remove any meetings that have been cancelled.
  Additionally, meetings that have been completed or are overdue can also be removed to facilitate organization in the application.
  * Highlights: This enhancement affects existing commands and commands to be added in future. It required an in-depth analysis of design alternatives. The implementation too was challenging as it required changes to existing commands.
  * Credits: Code inspiration was taken from AB3's existing delete contact feature.

* **New Feature**: Added a meeting edit command
  * What it does: Users are able to edit an existing command.
  * Justification: This feature improves the product significantly for an NUS student with a changing schedule so that they are able to edit important details of their meetings to keep the application updated.
  Additionally, it allows any mistakes made when adding the meeting to be rectified.
  * Highlights: This enhancement affects existing commands and commands to be added in future.
  All the constraints of person and module has to also be accomodated for, and therefore required in-depth understanding of how those features are implemented.
  * Credits: Code inspiration was taken from AB3's existing edit contact feature. 

* **New Feature**: Added a timeline feature so that users can view their meetings in a visual form
  * What it does: Users are able to visualise their meetings in a timeline format.
  * Justification: This feature improves the product significantly because a NUS student with many meetings is able to have a bird eye's view of their meetings and also know when meetings are overdue.
  * Highlights: This enhancement not only required understanding of good UI/UX design, but also comprehensive knowledge of the logic component so that the command to trigger the timeline window can be implemented.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&tabAuthor=gloon99&tabRepo=AY2021S1-CS2103-F10-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code)
  **Note**: Large chunks of code were accidentally deleted and a pull request revert had to be committed. Hence, a large portion of code attributed to this developer was not actually written by them.
  The pull request revert in question is [\#170](https://github.com/AY2021S1-CS2103-F10-2/tp/pull/170)
  The following includes a list of the features and enhancements (including documentation) detected by reposense that are not written by this developer:
  * contact list, clear, delete, find
  * label add, clear, delete
  * copy
  * meeting view
  * autocomplete

* **Project management**:
  * Managed releases `v1.2` - `v1.4` (3 releases) on GitHub

* **Enhancements to existing features**: 
  * Wrote tests for the meeting model, meeting add, meeting delete and meeting edit (Pull requests [\#233](https://github.com/AY2021S1-CS2103-F10-2/tp/pull/233), [\#234](https://github.com/AY2021S1-CS2103-F10-2/tp/pull/234))
  * Made minor tweaks to timeline GUI for light and dark mode to make it more aesthetically pleasing (Pull request [\##260](https://github.com/AY2021S1-CS2103-F10-2/tp/pull/260))

* **Documentation**:
  * User Guide:
    * Added documentation for the features `meeting add`, `meeting delete`, `meeting edit`, `meeting list` and `timeline` (Pull requests [\##181](https://github.com/AY2021S1-CS2103-F10-2/tp/pull/181), [\#276](https://github.com/AY2021S1-CS2103-F10-2/tp/pull/234))
  * Developer Guide:
    * Added implementation details of the `meeting add`, `meeting delete`, `meeting edit`, `meeting list` and `timeline` features (Pull request [\##237](https://github.com/AY2021S1-CS2103-F10-2/tp/pull/237) [\#276](https://github.com/AY2021S1-CS2103-F10-2/tp/pull/276))

