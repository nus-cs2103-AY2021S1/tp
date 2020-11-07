---
layout: page
title: Lee Jian Hui's Project Portfolio Page
---

## Project: Bamboo

Bamboo (v1.4) is a **simple desktop app for managing personal finance, optimized for use via a Command Line Interface (CLI),** and targeted at college students. If you can type fast, Bamboo v1.4 can get your financial management tasks done faster than traditional GUI apps.

Given below are my contributions to the project.

* **New Feature**: Added the ability to switch category in the expense book.
  * What it does: allows the user to switch into another category with the given category input. Once switched, the list displayed will only show the expenses with the corresponding category and uses the specified budget assigned to this category.
  * Justification: This feature improves the product significantly because a user can focus on the individual categories of expenses to look into and manage instead of having to do their own separate calculation manually to see where they overspent.
  * Highlights: This enhancement affects existing commands and commands to be added in future. It required an in-depth analysis of design alternatives. The implementation too was challenging as it required changes to existing commands and some architecture designs.
  * Credits: N.A

* **New Feature**: Added a delete category command that allows the user to remove unwanted category.
  * What it does: allows the user to delete a specified category from the expense book. Once deleted, the budget assigned to that category will be removed amd expenses associated with that category will automatically be reset to Default category.
  * Justification: This feature improves the product significantly because a user can recover from adding the wrong category into the expense book and better manage their expenses if there is a certain category that they are no longer interested in or have no more need for.
  * Highlights: This enhancement affects existing commands and commands to be added in future. It required an in-depth analysis of design alternatives. The designing process too was challenging as there are many variables to be concerned with as budget and expense are both intricately linked with category.
  * Credits: N.A
  
* **New Component**: Added a class containing a list of unique tag.
  * What it does: contains a list of unique tags and allows tag list specified function.
  * Justification: This component improves the execution significantly because categories can now be parsed and loaded to be used by the expensebook.
  * Highlights: This enhancement affects existing commands and commands to be added in future. It required an in-depth analysis of design alternatives.
  * Credits: N.A

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=jaylenlee&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=false)

* **Project management**:
  * Assisted in assigning PRs to corresponding issues and milestones
  * Reviewed and merged PRs

* **Enhancements to existing features**:
  * Refactoring of names of existing classes to more suitable names (Pull requests [\#37](https://github.com/AY2021S1-CS2103-W14-3/tp/pull/37), [\#47](https://github.com/AY2021S1-CS2103-W14-3/tp/pull/47))
  * Modifying classes to encourage more abstraction barriers (Pull request [\#77](https://github.com/AY2021S1-CS2103-W14-3/tp/pull/77))
  * Fixing of bugs that causes unideal results (Pull request [\#155](https://github.com/AY2021S1-CS2103-W14-3/tp/pull/155), [\#156](https://github.com/AY2021S1-CS2103-W14-3/tp/pull/156))
  * Setting character limits to textfield input to prevent excessive amount of input to lag the application (Pull request [\#158](https://github.com/AY2021S1-CS2103-W14-3/tp/pull/158))
  
* **Documentation**:
  * User Guide:
    * Added initial documentation for basic features. [\#11](https://github.com/AY2021S1-CS2103-W14-3/tp/pull/11)
    * Updated documentation to v1.2 [\#48](https://github.com/AY2021S1-CS2103-W14-3/tp/pull/48)
    * Added documentation for the features `help`, `deleteCat` and `switch` [\#91](https://github.com/AY2021S1-CS2103-W14-3/tp/pull/91)
    * Added example screenshots to features in Usage 
  * Developer Guide:
    * Added implementation details of the `switch` feature. [\#66](https://github.com/AY2021S1-CS2103-W14-3/tp/pull/66)
    * Updated `switch` feature, user stories, spacing and use case 13 [\#159](https://github.com/AY2021S1-CS2103-W14-3/tp/pull/159)

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#9](https://github.com/AY2021S1-CS2103-W14-3/tp/pull/9), [\#150](https://github.com/AY2021S1-CS2103-W14-3/tp/pull/150), [\#151](https://github.com/AY2021S1-CS2103-W14-3/tp/pull/151)
  * Posted in forum discussions (examples: [\#77](https://github.com/nus-cs2103-AY2021S1/forum/issues/77))
