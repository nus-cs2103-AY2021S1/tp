---
layout: page
title: Seow Alex's Project Portfolio Page
---

## Project: ChopChop

ChopChop is a food recipe and ingredient inventory management system, which aims to make it easier for people to manage their recipes and ingredients in an easy and effective manner. It features a keyboard-driven command interface and a GUI written in JavaFX.

A summary of code contributions can be found here: [reposense](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=seowalex).

### New Features

#### Undo/redo

Added the ability to undo commands, which allows the user to reverse any changes made to the recipes/ingredients in ChopChop.
Preceding undo commands can also be reversed using the redo command.
This feature allows users to rectify any mistakes they may have made, greatly increasing the quality-of-life when using ChopChop.

The undo/redo feature was implemented by saving a history of previously entered undoable commands, such that each command can be undone/redone.
While this forced all model changes to be restricted to each `Command` class, it allowed the feature to be implemented without having to save the entire state of the application after each command.
This allowed the undo/redo feature to be fairly responsive, even when dealing with hundreds or thousands of ingredients/recipes.

PRs: [#41](https://github.com/AY2021S1-CS2103T-T10-3/tp/pull/41), [#96](https://github.com/AY2021S1-CS2103T-T10-3/tp/pull/96)

#### Intelligent ingredient editing

Unlike recipes, it is not as natural to edit ingredients, as one generally thinks about ingredients in terms of ingredients used and ingredients added (e.g. I bought 1L of milk instead of I changed 1L of milk to 2L).
Hence, in lieu of using an `edit ingredient` command to edit quantities, the `add ingredient` and `delete ingredient` commands were changed to allow adding and deleting specific quantites of ingredients.
Since ChopChop supports ingredients with different sets of expiry dates, care had to be taken to ensure that the ingredients that were about to expire first would be deleted first using the `delete ingredient` command.
This feature also helped in the implementation of the `make recipe` command, which at its core is simply deleting the quantities of the ingredients specified in the recipe.

PRs: [#96](https://github.com/AY2021S1-CS2103T-T10-3/tp/pull/96)

#### Editing recipes

For recipe editing, the command parser returns a list of changes needed to be made to a recipe, which then needs to be checked to ensure that the edit operation is successful.
For example, duplicate ingredients/tags should not be able to be added, among other restrictions.
Care was also taken to return appropiate error messages for each problem detected in the edit operation, so that the user can correct the command.

PRs: [#137](https://github.com/AY2021S1-CS2103T-T10-3/tp/pull/137)

### Other Contributions

#### Project Management
Assisted in scheduling and keeping track of work assigned to various team members, including leading weekly team meetings.

#### Enhancements to Existing Features

1. Enhanced the ingredient model to be able to split into two copies for ingredient deleting and recipe making. ([#96](https://github.com/AY2021S1-CS2103T-T10-3/tp/pull/96))

2. Enhanced the GUI to support window resizing and improved the overall design. ([#125](https://github.com/AY2021S1-CS2103T-T10-3/tp/pull/125), [#191](https://github.com/AY2021S1-CS2103T-T10-3/tp/pull/191), [#204](https://github.com/AY2021S1-CS2103T-T10-3/tp/pull/204))

3. Refactored code to make use of generics whenever possible to reduce code duplication. ([#54](https://github.com/AY2021S1-CS2103T-T10-3/tp/pull/54), [#67](https://github.com/AY2021S1-CS2103T-T10-3/tp/pull/67))

#### Documentation
Add implementation details for the undo/redo feature, updated UML diagrams for the Logic, Model and Storage components in the Developer Guide, and added command usage instructions in the User Guide. ([#25](https://github.com/AY2021S1-CS2103T-T10-3/tp/pull/25), [#29](https://github.com/AY2021S1-CS2103T-T10-3/tp/pull/29), [#103](https://github.com/AY2021S1-CS2103T-T10-3/tp/pull/103), [#108](https://github.com/AY2021S1-CS2103T-T10-3/tp/pull/108), [#168](https://github.com/AY2021S1-CS2103T-T10-3/tp/pull/168))

#### Community

Reviewed pull requests: [#4](https://github.com/AY2021S1-CS2103T-T10-3/tp/pull/4), [#126](https://github.com/AY2021S1-CS2103T-T10-3/tp/pull/126).
