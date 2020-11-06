---
layout: page
title: Marcus's Project Portfolio Page
---

## Project: AddressBook Level 3

Bamboo (v1.4) is a **simple desktop app for managing personal finance, optimized for use via a Command Line Interface (CLI),** and targeted at college students. If you can type fast, Bamboo v1.4 can get your financial management tasks done faster than traditional GUI apps.

Given below are my contributions to the project.

* **New Feature**: Added the ability to add shortcuts (aliases) to each command.
  * What it does: allows users to add up to ONE customised command word for all commands except alias and resetAlias. Restrictions on user input and more specific usage details can be found in the User Guide.
  * Justification: As an experienced user, the user should be able to customise the command word using alias shortcuts for faster workflow. Being experienced also likely means that he is either a long-time user or a frequent user. The alias and resetAlias commands cannot have aliases as a neat fail-safe.
  * Highlights: `find [oldAlias] [newAlias]` will override the old alias with the new alias. If there are no aliases currently for a command, then simply use `find [defaultCommandWord] [newAlias]`. 
  * Additional model classes implemented for this feature: `AliasMap`, `AliasEntry`, and their JSON Storage Formatting Classes
  * Credits: *{AliasMap class design inspired by AddressBook3's AddressBook class}*

* **New Feature**: Added the ability to remove shortcuts (aliases) for commands all at once.
  * What it does: allows users to add up to ONE customised command word for all commands except alias and resetAlias. Restrictions on user input and more specific usage details can be found in the User Guide.
  * Justification: A user may have not used the app for quite some time, and forgot all the aliases he/she have customised. Since aliases will not be visible in user guide, he/she can simply run `resetAlias` and can resume using the app with the command words as shown in the user guide.

* **Updated Feature**: Altered find command to perform partial description keyword or date matching or both.
  * What it does: filters expenses list to only show expenses with description that partially contains one of the keywords (e.g. `find -d pasta` will include in its output an expense with a description containing the word `pastafarian`) and contains one of the dates. 
  * Justification: This feature allows users to filter out expenses for a particular day or expenses whose description contains a particular keyword. These features are important for users to be able to reflect on his total spending on a particular day or on a particular item, and learn from any spending mistakes. 
  * Credits: The current find feature implementation was enhanced from AddressBook3’s find feature which allowed us to do description matching, from which date matching was then extended.
  * Other: Previously implemented and subsequently removed tag matching support due to the introduction of expense book categories in the Bamboo project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=marcus2k&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=false)

* **Project management**:
  * Opened and resolved issues
  * Reviewed and merged PRs

* **Enhancements to existing features**:
  * Updated the find command (Pull requests [\#42]())
  * Wrote additional tests for `alias` and `resetAlias` feature to increase coverage from 62.84% to 65.05% (Pull requests [\#154]())
  * Add feature restrictions or update behaviours with justifications (Pull requests [\#84]())

* **Documentation**:
  * User Guide:
    * Updated documentation for the features `find` and `alias` (Pull requests [\#88](), [\#165]()) 
    * Added documentation for the features `resetAlias` [\#165]()
  * Developer Guide:
    * Added implementation details of the `alias` feature. [\#165]()

* **Community**:
  * Reported bugs: [\#163](), [\#153]()
  * PRs reviewed and merged: [\#37](), [\#14](), [\#66](), [\#89](), [\#95]()
  * Asked question in forum [1](https://github.com/nus-cs2103-AY2021S1/forum/issues/130)
  * Reported bugs and suggestions for other teams in the class (examples: [1](https://github.com/AY2021S1-CS2103-F09-3/tp/issues/176), [2](https://github.com/AY2021S1-CS2103-F09-3/tp/issues/175), [3](https://github.com/AY2021S1-CS2103-F09-3/tp/issues/174), [4](https://github.com/AY2021S1-CS2103-F09-3/tp/issues/172))

* _{you can add/remove categories in the list above}_
