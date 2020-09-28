---
layout: page
title: User Guide
---

ChopChop is a **desktop app for managing recipes and ingredients, optimised for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, ChopChop can manage your items faster than traditional GUI apps.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `chopchop.jar` from [here](https://github.com/AY2021S1-CS2103T-T10-3/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your ChopChop.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`list recipes`** : Lists recipes.

   * **`add recipe Milkshake /ingredient Milk /qty 500ml /step Pour Milk /step Shake`** : Adds a recipe for making milkshakes, with one ingredient and two steps.

   * **`exit`** : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:** <br>

* Words in UPPER_CASE are the parameters to be supplied by the user. <br>
  eg. `add recipe NAME /ingredient INGREDIENT`, `NAME` and `INGREDIENT` are parameters supplied by the user.

* Items in [square brackets] are optional. <br>
  eg. `add ingredient NAME [/qty QUANTITY]` can be used as `add ingredient Eggs /qty 4` or as `add ingredient Eggs /qty 4`.

* Items with `...` after them can be used multiple times. <br>

</div>


### Viewing help : `help`

Shows a message explaning how to access the help page.

![help message](images/helpMessage.png)

Format: `help`



### Listing all recipes: **`list`** `recipes`
Shows a list of all recipes in the recipe manager.

Format: `list recipes`



### Adding recipe: **`add`** `recipe`
Adds a recipe to the recipe manager.

Format: `add recipe NAME [/ingredient INGREDIENT [/qty QTY1]...]... (/step STEP)...`



### Deleting recipe: **`delete`** `recipe`
Deletes a specific recipe from the recipe manager.

Format: `delete recipe NAME`




### Finding recipes: **`find`** `recipe`
Finds all recipes containing the given keywords in the name.

Format: `find recipe KEYWORDS [MORE_KEYWORDS]`

The search is case-insensitive. e.g henz will match Henz
Only the name is searched, and only substrings will be matched.




### Listing all ingredients: **`list`** `ingredient`
Shows a list of all ingredients in the recipe manager.

Format: `list ingredient`




### Adding ingredient: **`add`** `ingredient`
Adds an ingredient to the recipe manager

Format: `add ingredient NAME [/qty QUANTITY] [/expiry DATE]`




### Deleting ingredient: **`delete`** `ingredient`
Deletes a specific ingredient from the recipe manager.

Format: `delete ingredient NAME [/qty QUANTITY]`




### Finding ingredients: **`find`** `ingredient`
Finds ingredients containing the given keywords in the name.

Format: `find ingredient KEYWORDS [MORE_KEYWORDS]`

The search is case-insensitive. e.g cheese will match Cheese
Only the name is searched, and only substrings will be matched.




### Exiting the program: **`exit`**
Exits the program.

Format: `exit`
