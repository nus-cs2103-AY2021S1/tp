---
layout: page
title: Caitlin Jee's Project Portfolio Page
---

## Project: Wishful Shrinking

Wishful Shrinking is a desktop application for managing your diet, keeping track of your on-hand ingredients, recipes, as well as the food you’ve eaten (along with their calories). While it has a GUI, most of the user interactions happen using a CLI (Command Line Interface).

Given below are my contributions to the project.

* **New Feature 1**: Added the ability to get recommended recipes. (Pull requests [\#109](https://github.com/AY2021S1-CS2103T-W10-2/tp/pull/109))
  * What it does: allows users to get recipes whose ingredients are all present in their fridge.
  * Justification: This feature improves the product significantly because a user can easily search for what recipes they are able to make, with the ingredients that are already present in their fridge.
  * Highlights: This enhancement required an in-depth analysis of design alternatives. The implementation too was challenging as it was a new command that was not very similar to existing commands.

* **New Feature 2**: Added the ability to add ingredients to users' fridge. (Pull requests [\#41](https://github.com/AY2021S1-CS2103T-W10-2/tp/pull/41))
  * What it does: allows users to add ingredients to their fridge.
  * Justification: This feature improves the product significantly because users can save ingredients that they have into the fridge, which is essential when using the `recommend` feature.
  * Highlights: This enhancement affects existing commands and commands to be added in future. It required an in-depth analysis of design alternatives. The implementation too was challenging as it required changes to existing commands.
  * Credits: This feature was refactored from the `add` feature in AddressBook Level 3.

* **New Feature 3**: Added the ability to search for ingredients in the fridge. (Pull requests [\#47](https://github.com/AY2021S1-CS2103T-W10-2/tp/pull/47))
  * What it does: allows users to search for ingredients in the fridge by name.
  * Justification: This feature improves the product significantly because a user can now easily search for an ingredient by its name to see whether it is present in the fridge.
  * Highlights: This enhancement affects existing commands and commands to be added in future. It required an in-depth analysis of design alternatives. The implementation too was challenging as it required changes to existing commands.
  * Credits: This feature was refactored from the `find` feature in AddressBook Level 3.

* **New Feature 4**: Added the ability to delete a consumed recipe in the consumption list. (Pull requests [\#78](https://github.com/AY2021S1-CS2103T-W10-2/tp/pull/78)) <br><br>
  * What it does: allows users to delete a consumed recipe from the consumption list.
  * Justification: This feature improves the product significantly because a user can now delete the recipes that they do not want anymore from the consumption list.
  * Highlights: This enhancement affects existing commands and commands to be added in future. It required an in-depth analysis of design alternatives. The implementation too was challenging as it required changes to existing commands.
  * Credits: This feature was refactored from the `delete` feature in AddressBook Level 3.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=caitlinjee&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

* **Project management**:
  * Managed release `v1.2` (1 release) on GitHub
  * Refactored code to add and support the Ingredient class when work began on the fridge-related features
  * Updated the links and architecture sequence diagram, Logic, Model and Storage class diagrams in the Developer Guide to match Wishful Shrinking
  * Reminded team members to review and approve pull requests in a timely manner

* **Enhancements to existing features**:
  * Added support for tags in recipes (Pull requests [\#85](https://github.com/AY2021S1-CS2103T-W10-2/tp/pull/85))
    * Justification: This feature, which is refactored from the existing tag feature in Address Book 3, allows users to easily tag their favourite recipes.
  * Added the ability to search for a recipe in the recipe list by its ingredients or tags, in addition to name. (Pull requests [\#109](https://github.com/AY2021S1-CS2103T-W10-2/tp/pull/109))
    * Justification: This feature improves on the original `find` feature in AddressBook Level 3 as it now allows users to search for recipes not only by name, but also by ingredients and tags, making it easier for them to filter their recipes.
  * Wrote additional tests for existing features to increase test coverage from 63.5% to 67.5% (Pull requests [\#246](https://github.com/AY2021S1-CS2103T-W10-2/tp/pull/246)) 

* **Documentation**:
  * User Guide:
    * Added documentation for the features `searchR`, `recommend` and `deleteC`: [\#110](https://github.com/AY2021S1-CS2103T-W10-2/tp/pull/110)
    * Added UI images and labelled and explained the components [\#178](https://github.com/AY2021S1-CS2103T-W10-2/tp/pull/178) [\#259](https://github.com/AY2021S1-CS2103T-W10-2/tp/pull/259)
  * Developer Guide:
    * Added implementation details and diagrams for the features `addF`, `deleteC`, `recommend` and `searchR`: [\#118](https://github.com/AY2021S1-CS2103T-W10-2/tp/pull/118) [\#266](https://github.com/AY2021S1-CS2103T-W10-2/tp/pull/266)
    * Added Instructions for Manual Testing section in Developer Guide: [\#282](https://github.com/AY2021S1-CS2103T-W10-2/tp/pull/282)
  * Did cosmetic tweaks to User Guide and Developer Guide and ensured phrasing used was consistent and accurate: [\#178](https://github.com/AY2021S1-CS2103T-W10-2/tp/pull/178) [\#259](https://github.com/AY2021S1-CS2103T-W10-2/tp/pull/259)

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#117](https://github.com/AY2021S1-CS2103T-W10-2/tp/pull/117), [\#115](https://github.com/AY2021S1-CS2103T-W10-2/tp/pull/115), [\#175](https://github.com/AY2021S1-CS2103T-W10-2/tp/pull/175), [\#173](https://github.com/AY2021S1-CS2103T-W10-2/tp/pull/173)
  * Reported bugs and suggestions for other teams in the class (examples: [1](https://github.com/caitlinjee/ped/issues/1), [2](https://github.com/caitlinjee/ped/issues/2), [3](https://github.com/caitlinjee/ped/issues/3), [4](https://github.com/caitlinjee/ped/issues/4))

