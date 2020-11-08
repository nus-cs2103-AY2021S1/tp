---
layout: page
title: Tan Jia Qi's Project Portfolio Page
---

## Project: Wishful Shrinking

Wishful Shrinking is a desktop application for managing your diet, keeping track of your on-hand ingredients, recipes, as well as the food you’ve eaten (along with their calories). While it has a GUI, most of the user interactions happen using a CLI (Command Line Interface).

Given below are my contributions to the project.
  
* **New Feature 1**: Added the ability to `delete recipe` from the recipe list. (Pull request [\#43](https://github.com/AY2021S1-CS2103T-W10-2/tp/pull/43))
  * What it does: Allows the user to delete a recipe that they have in the recipe list.
  * Justification: This feature improves the product significantly because a user can now delete the recipes that they do not want anymore or have mistakenly added from the recipe list.
  * Highlights: This enhancement affects existing commands and commands to be added in future. It required an in-depth analysis of design alternatives. The implementation too was challenging as it required changes to existing commands.
  * Credits: This feature was built off from the `delete contact` feature in AddressBook Level 3.  
  
* **New Feature 2**: Added the ability to `list all recipes` in the recipe list. (Pull request [\#43](https://github.com/AY2021S1-CS2103T-W10-2/tp/pull/43))
  * What it does: Allows the user to view all recipe that they have added previously into recipe list.
  * Justification: This feature improves the product significantly because a user can now delete the recipes that they do not want anymore or have mistakenly added from the consumption list.
  * Highlights: This enhancement affects existing commands and commands to be added in future. It required an in-depth analysis of design alternatives. The implementation too was challenging as it required changes to existing commands.
  * Credits: This feature was built off from the `list contact` feature in AddressBook Level 3. 

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=jiaqi20&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=zoom&zFR=false&zA=jiaqi20&zR=AY2021S1-CS2103T-W10-2%2Ftp%5Bmaster%5D&zACS=265.00439466969095&zS=2020-08-14&zFS=&zU=2020-11-03&zMG=false&zFTF=commit&zFGS=groupByRepos&until=2020-11-07)

* **Project management**:
  * Managed release `v1.4` (1 release) on GitHub
  * Set up group meeting via zoom for discussion
  * Check dashboard to ensure consistent team progress
  * Refactored code to add and support recipe related features (Instruction, RecipeImage field)

* **Enhancements to existing features**:
  * Added support for `instructions` field for `add recipe` and `edit recipe` (Pull request [\#173](https://github.com/AY2021S1-CS2103T-W10-2/tp/pull/173))
    * Justification: Allows users to customise the recipe added by including single or multiple instructions.
    * Highlights: This enhancement affects existing commands, `addR` and `editR`. It required an in-depth analysis of design alternatives. The implementation too was challenging as it required changes to existing commands.

  * Added support for optional field, `recipe image` for `add recipe` and `edit recipe` (Pull request [\#179](https://github.com/AY2021S1-CS2103T-W10-2/tp/pull/179))
    * Justification: Allows users to customise the recipe added by including a recipe image in different formats (local storage and online resource) for different conditions (i.e. presence of WIFI). Default image will be displayed instead if the image input is invalid or not specified.
    * Highlights: This enhancement affects existing commands, `addR` and `editR` It required an in-depth analysis of design alternatives and research on image path The implementation too was challenging as it required changes to existing command and have to handle different cases.
  
  * Update tests for existing features (Pull request [\#262](https://github.com/AY2021S1-CS2103T-W10-2/tp/pull/262))
  
* **Documentation**:
  * User Guide:
    * Added documentation for the `deleteR`, `recipes` features [\#126](https://github.com/AY2021S1-CS2103T-W10-2/tp/pull/126)
    * Added tables for description of recipe image field in `addR` and `editR` [\#237](https://github.com/AY2021S1-CS2103T-W10-2/tp/pull/237)
    
  * Developer Guide:
    * Added implementation details of the `deleteR`, `recipes` features [\#126](https://github.com/AY2021S1-CS2103T-W10-2/tp/pull/126)
    * Update activity diagrams for `add` and `list` features [\#264](https://github.com/AY2021S1-CS2103T-W10-2/tp/pull/264)
    * Added details on Instructions for Manual Testing [\#283](https://github.com/AY2021S1-CS2103T-W10-2/tp/pull/283)

* **Community**:
  * PRs reviewed (with review comments): [\#178](https://github.com/AY2021S1-CS2103T-W10-2/tp/pull/178)
  * Reported bugs and suggestions for other teams in the class (examples: [1](https://github.com/jiaqi20/ped/issues/1), [2](https://github.com/jiaqi20/ped/issues/2), [3](https://github.com/jiaqi20/ped/issues/3))
  * Contribute to forum discussions (example: [1](https://github.com/nus-cs2103-AY2021S1/forum/issues/360))
