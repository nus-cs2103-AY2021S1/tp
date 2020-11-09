---
layout: page
title: Maxx Chan's Project Portfolio Page
---

## Project: Bamboo

Bamboo (v1.4) is a **simple desktop app for managing personal finance, optimized for use via a Command Line Interface (CLI),** and targeted at college students. If you can type fast, Bamboo v1.4 can get your financial management tasks done faster than traditional GUI apps.

Given below are my contributions to the project.

* **New Feature**: Added the ability to sort expenses. (Pull Request [\#86](https://github.com/AY2021S1-CS2103-W14-3/tp/pull/86))
  - What it does: allows the user to sort expenses currently displayed on the application based on Amount, Description (alphabetical), and Date of Expense. Users can use at least, and up to 3 of the sorting criterion - they will be applied with priority in order of appearance. Users also have the option to do a reverse sort for criterion.
  - Justification: This feature improves the product significantly because a user can better organise how expenses are displayed on the application. Specifically:
    - Amount sort allows the user to find out which expenses contribute the most/least to his spending. 
    - Description sort allows the user to organise expenses with similar descriptions together.
    - Date sort allows the user to have a chronological overview of his spending. 
  - Highlights: Priority sort for multiple keywords was an intentional design choice which added additional layers of complexity. Sorting also works together with filtering. While sorting itself is not complicated, this feature was challenging as JavaFX does not support both sorting and filtering at the same time natively. Workarounds had to be used.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=maxxyh&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=false&until=2020-11-09)

* **Project management**:
  * Team leader - delegation of work to members.
  * Managed all releases `v1.2` - `v1.4` (4 releases) on GitHub.
  * Opened, tagged and resolved Issues according to priority, category and person-in-charge.
  * Managed closing of milestones and shifting unfinished tasks to later milestones. 
  * Reviewed and merged PRs.
  * Created v1.2, v1.3, v1.4 demos.
  * Bug reporting (Issues [\#94](https://github.com/AY2021S1-CS2103-W14-3/tp/issues/94), [\#98](https://github.com/AY2021S1-CS2103-W14-3/tp/issues/98), [\#99](https://github.com/AY2021S1-CS2103-W14-3/tp/issues/99), [\#171](https://github.com/AY2021S1-CS2103-W14-3/tp/issues/171))

<div style="page-break-after: always;"></div>

* **Refactoring**
  * Refactored sample data and test classes from AB3 to fit Bamboo's purposes (Pull requests [\#44](https://github.com/AY2021S1-CS2103-W14-3/tp/pull/44))
  * Refactored classes from AB3 to fit Bamboo's needs (Pull requests [\#30](https://github.com/AY2021S1-CS2103-W14-3/tp/pull/30), [\#32](https://github.com/AY2021S1-CS2103-W14-3/tp/pull/32))

* **Enhancements to existing features**:
  * Changed the datatype used in Amount class so that it would not overflow and was more suitable for storing money. (Pull requests [\#150](https://github.com/AY2021S1-CS2103-W14-3/tp/pull/150), [\#157](https://github.com/AY2021S1-CS2103-W14-3/tp/pull/157), [\#171](https://github.com/AY2021S1-CS2103-W14-3/tp/pull/171))
  * Wrote additional tests for `sort` feature (Pull request [\#86](https://github.com/AY2021S1-CS2103-W14-3/tp/pull/86))

* **Documentation**:
  * User Guide:
    * Did overall formatting and proofreading of user guide. (Pull requests [\#100](https://github.com/AY2021S1-CS2103-W14-3/tp/pull/100), [#152](https://github.com/AY2021S1-CS2103-W14-3/tp/pull/152))
    * Updated Command Format Guide, Quick Start, Features section. (Pull requests [\#152](https://github.com/AY2021S1-CS2103-W14-3/tp/pull/152))
    * Updated Glossary section with input restrictions. (Pull request [\#152](https://github.com/AY2021S1-CS2103-W14-3/tp/pull/152))
    * Added documentation for the features `sort`, `clear` and `exit`. (Pull requests [\#89](https://github.com/AY2021S1-CS2103-W14-3/tp/pull/89))
    * Touch up existing documentation for `alias`,  `switch`, `addCat`, `deleteCat`, `find`. (Pull requests [\#100](https://github.com/AY2021S1-CS2103-W14-3/tp/pull/100))
    * Add app screenshots for all commands. 
  * Developer Guide:
    * Added implementation details of the `sort` feature. (Pull requests [\#72](https://github.com/AY2021S1-CS2103-W14-3/tp/pull/72))
    * Updated use cases U7-U12, user stories. (Pull requests [\#97](https://github.com/AY2021S1-CS2103-W14-3/tp/pull/97))
    * Updated diagrams to reflect latest architecture. (Pull requests [\#97](https://github.com/AY2021S1-CS2103-W14-3/tp/pull/97))

* **Community**:
  * PRs reviewed (with non-trivial review comments): (Pull requests [\#140](https://github.com/AY2021S1-CS2103-W14-3/tp/pull/140), [\#156](https://github.com/AY2021S1-CS2103-W14-3/tp/pull/156), [\#155](https://github.com/AY2021S1-CS2103-W14-3/tp/pull/155), [\#162](https://github.com/AY2021S1-CS2103-W14-3/tp/pull/162))
  * Reported bugs and suggestions for other teams (examples: [1](https://github.com/AY2021S1-CS2103-T16-3/tp/issues/155), [2](https://github.com/AY2021S1-CS2103-T16-3/tp/issues/156), [3](https://github.com/AY2021S1-CS2103-T16-3/tp/issues/157), [4](https://github.com/AY2021S1-CS2103-T16-3/tp/issues/158), [5](https://github.com/AY2021S1-CS2103-T16-3/tp/issues/159))

* **Tools**:
  * Introduced the team to Notion to store notes and tasks from our team meetings.
