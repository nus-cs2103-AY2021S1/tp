---
layout: page
title: Ekam Singh's Project Portfolio Page
---

## Project: Modduke

Modduke - Modduke is a desktop app targeted towards NUS students. It allows them to easily manage their contacts, modules and meetings during the semester. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added a module add command that allows users to add modules to the Modduke. (Pull Request: [\#113](https://github.com/AY2021S1-CS2103-F10-2/tp/pull/113))
  * What it does: It is a feature where users are able to create and add Modules to Modduke and store them in the module book.
  * Justification: This feature was implemented for the target audience of NUS students who may want to store contacts of all modules together
  * Highlights: This feature allows users to group contacts together based on shared modules. The implementation required the extensive use of object oriented paradigms as well as a good understanding of the codebase.

* **New Feature**: Added a module list command that allows users to list all the contacts within a selected module.(Pull Request: [\#118](https://github.com/AY2021S1-CS2103-F10-2/tp/pull/118))
  * What it does: Allows the user to list all the contacts within a given module in order to quickly find contacts that are specific to the module.
  * Justification: This feature improved the user experience drastically as it provided users a method to quickly filtered their contacts in a manner that was especially relevant to them. As contact lists could get long, this command made it much easier to identify contacts within a certain module.
  * Highlights: The implementation of this command required a solid understanding of the code base and required refactoring old module commands in order to ensure no duplicity of modules.

* **New Feature**: Added a module delete command that allowed users to delete modules in Modduke.(Pull Request: [\#158](https://github.com/AY2021S1-CS2103-F10-2/tp/pull/158))
  * What it does: Allows users to delete modules from the module book.
  * Justification: This feature was necessary to delete old modules or modules the user did not want in the module book anymore.
  * Highlights: This feature was technically complicated as it also effected meetings and the UI.

* **New Feature**: Added a module edit command that allowed users to edit modules in Modduke. (Pull Request: [\#157](https://github.com/AY2021S1-CS2103-F10-2/tp/pull/157)) 
  * What it does: Allows users to edit modules in order to update contacts stored within the module or to edit any mistakes made during the module add command.
  * Justification: This feature improved the user experience drastically as now users are able to edit modules and are able to add contacts to pre-existing modules.
  * Highlights: This implementation proposed challenges with the design of other features, especially meetings, where editing a module needed to update the relevant meetings as well. It also required design choices for how meetings would be affected when all participants within the meetings were changed.

* **New Feature**: Added a command history feature to the UI.
  * What it does: Allows users to toggle to previous commands using the up and down keys similar to command lines.(Pull Request: [\#254](https://github.com/AY2021S1-CS2103-F10-2/tp/pull/254))
  * Justification: This feature improved the user experience and made adding similar commands much easier as they could toggle to a similar command and simply edit the details.
  * Highlights: This was a complex feature to implement as it required a way to maintain the state of user's commands as well as toggle capability that traversed through the history.

* **Enhancements to existing features**:
  * Updated the UI for modules to show professors and TA's when listing participants in the module
  * Added Classmates Badges to the modules list UI
  * Wrote Tests for module commands
  * Modified the argument multi-mapper to check for duplicate prefixes when not allowed

* **Code contributed**: [RepoSense Link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=ekamsinghpandher&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&tabAuthor=EkamSinghPandher&tabRepo=AY2021S1-CS2103-F10-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code)

* **Project management**:
  * Managed releases `v1.2` - `v1.4` (3 releases) on GitHub

* **Documentation**:
  * User Guide:
    * Added documentation for the commands   `module add`, `module delete`, `module edit`, `module list` and the command history feature
  * Developer Guide:
    * Added documentation for the commands   `module add`, `module delete`, `module edit`, `module list` and the command history feature
