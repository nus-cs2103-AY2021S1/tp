---
layout: page
title: Jerryl Chong's Project Portfolio Page
---

## Project: Modduke

Modduke is a desktop app targeted towards NUS students. It allows them to easily manage their contacts, modules and meetings during the semester. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the feature where users can copy email address or phone numbers of multiple contacts to their system clipboard using the copy command. ([\#155](https://github.com/AY2021S1-CS2103-F10-2/tp/pull/155))
  * What it does: Allows the user to copy email address or phone numbers of multiple contacts to their system clipboard.
  * Justification: This feature improves the product significantly because a user can allow the app copy the information for them. This prevents typing mistakes and helps make copying information from multiple contacts more convenient.
  * Highlights: This enhancement required understanding of how FilteredLists work and understanding of how contacts are stored in meetings and modules. It required analysis of design alternatives.

* **Enhancements to existing features**:
  * Changed `list` command to `contact list` ([\#81](https://github.com/AY2021S1-CS2103-F10-2/tp/pull/81))
  * Changed `clear` command to `contact clear` ([\#82](https://github.com/AY2021S1-CS2103-F10-2/tp/pull/82))
  * Added commands specifically for tagging, such as `tag add`, `tag delete` and `tag clear` for more flexibility when using tags ([\#83](https://github.com/AY2021S1-CS2103-F10-2/tp/pull/83), [\#144](https://github.com/AY2021S1-CS2103-F10-2/tp/pull/144), [\#241](https://github.com/AY2021S1-CS2103-F10-2/tp/pull/241))
  * Changed `find` command to `contact find` ([\#226](https://github.com/AY2021S1-CS2103-F10-2/tp/pull/226))
  * Allowed finding of contacts by tags and initials in addition to keywords ([\#105](https://github.com/AY2021S1-CS2103-F10-2/tp/pull/105), [\#226](https://github.com/AY2021S1-CS2103-F10-2/tp/pull/226))
  * Added ability for user to delete multiple contacts by specifying names, tags or modules ([\#156](https://github.com/AY2021S1-CS2103-F10-2/tp/pull/156))
  * Update `contact clear` to delete all modules and meetings ([\#224](https://github.com/AY2021S1-CS2103-F10-2/tp/pull/224))

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=jerrylchong&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

* **Project management**:
  * Managed releases `v1.2` - `v1.4` (3 releases) on GitHub
  * Regularly updated user guide and test cases upon adding new functional code

* **Documentation**:
  * User Guide:
    * Added documentation for the commands  `contact delete`, `contact clear`, `contact list`, `contact find`, `tag add`, `tag delete`, `tag clear`, `copy email` and `copy phone`.
    * Standardised parameter of contact's name to `CONTACT_NAME`.
  * Developer Guide:
    * Added implementation details of the `contact delete`, `contact clear`, `contact list`, `contact find`, `tag add`, `tag delete`, `tag clear`, `copy email` and `copy phone` features.
