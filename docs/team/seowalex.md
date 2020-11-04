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
This feature allows users to rectify any mistaken commands they may have entered, greatly increasing the quality-of-life when using ChopChop.

The undo/redo feature was implemented by saving a history of previously entered commands, such that each command can be undone/redone.
While this forced all model changes to be restricted to the `Command` class, it allowed the feature to be implemented without having to save the entire state of the application after each command.
This allowed the undo/redo feature to be fairly responsive, even when dealing with hundreds or thousands of ingredients/recipes.

#### Intelligent ingredient editing

Unlike recipes, editing ingredients is not as natural, as one generally thinks about ingredients in terms of ingredients used and ingredients added (e.g. I bought 1L of milk instead of I changed 1L of milk to 2L).
Hence, in lieu of an `edit ingredient` command, the `add ingredient` and `delete ingredient` command was changed to allow adding and deleting specific quantites of ingredients.
Since ChopChop supports different sets of the same ingredient with different expiry dates, care had to be taken to ensure that the ingredients that were about to expire first would be deleted first using the `delete ingredient` command.
This feature also helped in the implementation of the `make recipe` command, which at its core is simply deleting the quantities of ingredients specified in the recipe.


### Other Contributions

#### Project Management
Assisted in scheduling and keeping track of work assigned to various team members, including leading weekly team meetings.

#### Enhancements to Existing Features

1. Enhanced the ingredient model to be able to split into two copies for ingredient deleting and recipe making.

2. Enhanced the GUI to support window resizing and improved the overall design.

#### Documentation
Add implementation details for the undo/redo feature, and updated UML diagrams for the Logic, Model and Storage components in the Developer Guide.

#### Community

Reviewed pull requests: [#4](https://github.com/AY2021S1-CS2103T-T10-3/tp/pull/4), [#126](https://github.com/AY2021S1-CS2103T-T10-3/tp/pull/126).
