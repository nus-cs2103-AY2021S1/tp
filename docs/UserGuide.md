---
layout: page
title: User Guide
---

Wishful Shrinking is a **desktop app for managing your diet, keeping track of your on-hand ingredients, recipes, as well as the food you’ve eaten (along with their calories)**. It is optimized for fast and efficient typist as it uses a **Command Line Interface (CLI)** with the added beauty of a Graphical User Interface (GUI).

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure your computer has Java `11` or above installed.

2. Download the latest `wishfulShrinking.jar` from [here](https://github.com/AY2021S1-CS2103T-W10-2/tp).

3. Copy the file to an empty folder you want to use as the _home folder_.

4. **Double-click** the jar file to start the app OR start the app using **CLI** and type `java -jar <jar file name>.jar`.<br>
   The app should look similar to the one shows below: <br>
   ![Ui](images/Ui.png)

5. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`recipe`** : Lists all recipes.

   * **`addR`**` n/salad i/lettuce, carrots, olive oil` : Adds a `salad` recipe to Wishful Shrinking.

   * **`deleteR`**`3` : Deletes the 3rd recipe shown in the current list.

   * **`exit`** : Exits the app.

6. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

# Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/TITLE`, `TITLE` is a parameter which can be used as `addR n/salad`.
</div>

### Viewing help : `help`

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

## Recipe

### Adding a recipe: `add`

Adds a recipe to Recipes Collection.

Format: `addR n/TITLE i/INGREDIENTS`

Examples:
* `addR n/salad i/lettuce, carrots, olive oil`
* `addR n/sandwiches i/breads, cheese`


### Listing all recipes : `list`

Shows a list of all recipes in the Recipes Collection.

Format: `recipe`


### Deleting a recipe : `delete`

Deletes the specified recipe from Recipes Collection.

Format: `deleteR INDEX`

* Deletes the recipe at the specified `INDEX`.
* The index refers to the index number shown in the displayed recipe list.
* The index **must be a positive integer** 1, 2, 3......

Examples:
* `list` followed by `deleteR 2` deletes the 2nd recipe in Recipe Collection.
* `searchR n/salad` followed by `deleteR 1` deletes the 1st recipe in the results of the `search` command.


### Search recipe by recipe title: `search`

Finds recipe with title contain any of the given keywords.

Format: `searchR n/TITLE [MORE_TITLE]`

* The search is case-insensitive. e.g `salad` will match `Salad`
* Only the recipe title is searched.
* The order of the keywords does not matter. e.g. Ham Salad will match Salad with Ham

Examples:
* `searchR n/salad` returns `salad` and `ham salad`


## Fridge
### Adding an ingredient: `add`

Adds an ingredient to fridge.

Format: `addF i/INGREDIENTS`

Examples:
* `addF i/peanut`
* `addF i/banana, green peas, salmon fish`

### Listing all ingredients : `list`

Shows a list of all ingredients in the fridge.

Format: `fridge`

### Deleting an ingredient : `delete`

Deletes the specified ingredient from Fridge.

Format: `deleteF INDEX`

* Deletes the recipe at the specified `INDEX`.
* The index refers to the index number shown in the displayed recipe list.
* The index **must be a positive integer** 1, 2, 3......

Examples:
* `list` followed by `deleteF 2` deletes the 2nd ingredient in the fridge.
* `searchF peanut` followed by `deleteF 1` deletes the 1st ingredient in the results of the `search` command.

### Search Ingredient: `search`

Finds ingredients that contain any of the given keywords.

Format: `searchF KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `peanut` will match `Peanut`
* Only the recipe title is searched.
* The order of the keywords does not matter. e.g. Peanut Butter will match Butter with Peanut

Examples:
* `searchF peanut` returns `peanut`


### Saving the data

Wishful Shrinking data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.


### Remark `[coming in v1.3]`

_{give a remark to the recipe}_

--------------------------------------------------------------------------------------------------------------------

# FAQ

**Q**: Why is my data not saved?<br>
**A**: Remember to copy the jar file to an EMPTY folder before starting the app.
<br>
<br>
**Q**: Why is the app not running?<br>
**A**: Ensure JDK `11` or above is installed.

--------------------------------------------------------------------------------------------------------------------

# Command summary

Features | Format, Examples
--------|------------------
**Add recipe** | `addR n/TITLE i/INGREDIENTS` <br> e.g., `addR n/salad i/lettuce, carrots, olive oil`
**Add Ingredient to the fridge** | `addF i/INGREDIENTS` <br> e.g., `addF i/banana, green peas, salmon fish`
**Delete recipe** | `delete r/INDEX`<br> e.g., `deleteR 3`
**Delete Ingredient from the fridge** | `deleteF INDEX`<br> e.g., `deleteF 3`
**Search recipe** | `searchR n/KEYWORD`<br> e.g., `searchR n/salad`
**Search Ingredient in the fridge** | `searchF KEYWORD`<br> e.g., `searchF avocado`
**List recipe** | `recipe`
**List ingredients in the fridge** | `fridge`
**Help** | `help`
**Exit** | `exit`
