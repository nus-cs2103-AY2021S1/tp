---
layout: page
title: User Guide
---

Welcome to the Wishful Shrinking User Guide! This user guide provides in-depth documentation on the installation process, step-by-step instructions for each feature and troubleshooting recommendations. Wishful Shrinking is available for the Linux, Unix, Windows and Mac OS operating systems.


## Introducing Wishful Shrinking
Wishful Shrinking your desktop diet manager. It is an app that helps you manage your on-hand ingredients, organise personal recipes and track your diet. Wishful Shrinking facilitates a healthier diet in three main ways: 
1. Provide a source of healthy, customizable recipes 
2. Recommend recipes to improve ease of home cooking 
3. Track daily food and calorie intake

Wishful Shrinking targets busy office workers who tend to discount healthy eating. Office workers are also more familiar with desktop applications and typing and correspondingly, Wishful Shrinking is optimized for fast and efficient typers as it uses a Command Line Interface (CLI) with the added beauty of a Graphical User Interface (GUI).

Choose a topic from the table of contents to get started on your Wishful Shrinking journey. 


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

   * **`addR`**` n/salad i/lettuce, carrots, olive oil c/40` : Adds a `salad` recipe to Wishful Shrinking.

   * **`deleteR`**`3` : Deletes the 3rd recipe shown in the current list.

   * **`exit`** : Exits the app.

6. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

# Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `addR n/salad`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/Salad t/healthy` or as `n/Salad`.

* Items with `…`​ after them can be used more than 1 times.<br>
  e.g. `[t/TAG]…​` can be used as `t/healthy`, `t/healthy t/low calories` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME i/INGREDIENTS`, `i/INGREDIENTS n/NAME` is also acceptable.
</div>

### Viewing help : `help`

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

## Recipe

### Adding a recipe: `addR`

Adds a recipe to Recipes Collection.

Format: `addR n/TITLE i/INGREDIENT [ -QUANTITY][, MORE INGREDIENTS [ -QUANTITY]] c/CALORIES img/IMAGE inst
/INSTRUCTION... [t/TAG]...`

* `INGREDIENT` can take in an optional `Quantity` e.g. i/Tomato -2 whole
    * there is a mandatory space before `-` 
* `IMAGE` can be local path e.g. images/healthy1.jpg or url e.g. https://vaya.in/recipes/wp-content/uploads/2018/06/Club-sandwich.jpg

Examples:
* `addR n/salad i/lettuce, tomato, olive oil c/40 img/images/healthy1.jpg instr/1. Cook 2. Eat`
* `addR n/sandwiches i/breads, cheese -2 sclices c/80 img/https://vaya.in/recipes/wp-content/uploads/2018/06/Club-sandwich.jpg instr/1. Cook 2. Eat`


### Listing all recipes : `recipes`

Shows a list of all recipes in the Recipes Collection.

Format: `recipes`


### Deleting a recipe : `deleteR`

Deletes the specified recipe from Recipes Collection.

Format: `deleteR INDEX`

* Deletes the recipe at the specified `INDEX`.
* The index refers to the index number shown in the displayed recipe list.
* The index **must be a positive integer** 1, 2, 3......

Examples:
* `list` followed by `deleteR 2` deletes the 2nd recipe in Recipe Collection.
* `searchR n/salad` followed by `deleteR 1` deletes the 1st recipe in the results of the `search` command.

### Edit a recipe: `editR`

Edits the specified recipe from Recipes Collection.

Format: `editR INDEX [n/TITLE] i/INGREDIENT [ -QUANTITY][, MORE INGREDIENTS [ -QUANTITY]] [c/CALORIES] [img/IMAGE] 
[inst/INSTRUCTION...] [t/TAG]...`

* Edits the recipe at the specified `INDEX`.
* The index refers to the index number shown in the displayed recipe list.
* The index **must be a positive integer** 1, 2, 3......
* Note: All fields are optional
* Note: Not allowed to edit a recipe into another recipe already in the recipe list
* Note: Use carefully as this command will override the existing values with the new values
* Tip: Use `editR INDEX` to directly modify from the existing recipe

Examples:
* `editR 2` followed by `n/Apple salad i/apple` will edit the update the name of this recipe to Apple
 and the ingredients to contain an apple ingredient.

### Get a recipe to edit: `editR`

Set the edit existing recipe command into the command box for editing purposes.

Format: `editR INDEX`

* Get the edit recipe command with the recipe at the specified `INDEX`

Examples:
* `editR 2`

### Searching for a recipe: `searchR`

Finds recipes that contains all the specified ingredients OR whose title OR tags contain any of the given keywords.

Format: `searchR i/INGREDIENT [MORE_INGREDIENT]` OR `searchR n/TITLE [MORE_TITLE]` OR `searchR t/TAG [MORE_TAG]`

* The search is case-insensitive. e.g `salad` will match `Salad`
* Only the recipe ingredient OR recipe title OR recipe tag is searched.
* The order of the keywords does not matter. e.g. Ham Salad will match Salad with Ham

Examples:
* `searchR i/lettuce tomato` returns `salad` that has both ingredients `lettuce` and `tomato`
* `searchR n/salad` returns `salad` and `ham salad`
* `searchR t/healthy` returns `salad` with tag `healthy`


### Recommending recipes : `recommend`

Shows a list of all recipes in the Recipes Collection that can be made with the ingredients in the user's fridge.

Format: `recommend`

Examples:
* `recommend` returns `salad` with ingredients `lettuce`, `onion` and `tomato` only if the user has all `lettuce`, `onion` and `tomato` in the fridge

### Clearing all recipes : `clearR'

Clear all the recipes in the Recipe Collection.

Format: `clearR`

## Fridge

### Adding an ingredient: `addF`

Adds an ingredient to fridge.

Format: `addF i/INGREDIENT [ -QUANTITY][, MORE INGREDIENTS [ -QUANTITY]]`

Examples:
* `addF i/peanut`
* `addF i/banana -3/4 cups, green peas -200g, salmon fish`


### Listing all ingredients : `fridge`

Shows a list of all ingredients in the fridge.

Format: `fridge`


### Deleting an ingredient : `deleteF`

Deletes the specified ingredient from Fridge.

Format: `deleteF INDEX`

* Deletes the recipe at the specified `INDEX`.
* The index refers to the index number shown in the displayed recipe list.
* The index **must be a positive integer** 1, 2, 3......

Examples:
* `list` followed by `deleteF 2` deletes the 2nd ingredient in the fridge.
* `searchF peanut` followed by `deleteF 1` deletes the 1st ingredient in the results of the `search` command.

### Edit an ingredient: `editF`

Edits the specified ingredient from Ingredient Collection.

Format: `editF INDEX i/INGREDIENT [ -QUANTITY]`

* Edits the ingredient at the specified `INDEX`.
* The index refers to the index number shown in the displayed ingredient list.
* The index **must be a positive integer** 1, 2, 3......
* Note: All fields are optional
* Note: Not allowed to edit an ingredient into another ingredient already in the ingredient list
* Note: Use carefully as this command will override the existing values with the new values
* Tip: Use `editF INDEX` to directly modify the existing ingredient

Examples:
* `editF 2` followed by `i/apple` will edit the update the ingredient value to apple.

### Get an ingredient to edit: `editF`

Set the edit existing ingredient command into the command box for editing purposes.

Format: `editF INDEX`

* Get the edit ingredient command with the ingredient at the specified `INDEX`

Examples:
* `editF 2`

### Searching for an Ingredient: `searchF`

Finds ingredients that contain any of the given keywords.

Format: `searchF KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `peanut` will match `Peanut`
* Only the recipe title is searched.
* The order of the keywords does not matter. e.g. Peanut Butter will match Butter with Peanut

Examples:
* `searchF peanut` returns `peanut`


### Clearing all ingredients : `clearF'

Clear all the recipes in the Fridge.

Format: `clearF`

## Consumption

### Eating a recipe : `eatR`

Add the specified recipe to the Consumption Collection from Recipes Collection.

Format: `eatR INDEX`

* Add the recipe at the specified `INDEX` to the Consumption Collection.
* The index refers to the index number shown in the displayed recipe list.
* The index **must be a positive integer** 1, 2, 3......

Examples:
* `list` followed by `eatR 2` deletes the 2nd recipe in Recipe Collection.
* `searchR n/salad` followed by `eatR 1` deletes the 1st recipe in the results of the `search` command.


### Listing all recipes eaten : `calories`

Shows a list of all recipes eat by the user.

Format: `calories`


### Deleting a recipe eaten: `deleteC`

Deletes the specified recipe from consumption list.

Format: `deleteC INDEX`

* Deletes the recipe at the specified `INDEX`.
* The index refers to the index number shown in the displayed consumption list.
* The index **must be a positive integer** 1, 2, 3......

Examples:
* `calories` followed by `deleteC 2` deletes the 2nd recipe in Consumption List.

### Clearing all consumption : `clearC'

Clear all the recipe ate in the daily consumption list.

Format: `clearC`

### Saving the data

Wishful Shrinking data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.


### Remark `[coming in v1.4]`

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

# Glossary
**Wishful Shrinking**: can refer to name of the application as a whole or to app’s storage file

--------------------------------------------------------------------------------------------------------------------

# Command summary

Features | Format, Examples
--------|------------------
**Add recipe** | `addR n/TITLE i/INGREDIENT[, MORE INGREDIENT] c/CALORIES img/IMAGE inst/INSTRUCTION... [t/TAG]...` <br> e.g., `addR n/salad i/lettuce, tomato, olive oil c/40 inst/mix everything img/image/Salad.png`
**Add Ingredient to the fridge** | `addF i/INGREDIENTS` <br> e.g., `addF i/banana, green peas, salmon fish`
**Delete recipe** | `deleteR INDEX`<br> e.g., `deleteR 3`
**Delete Ingredient from the fridge** | `deleteF INDEX`<br> e.g., `deleteF 3`
**Delete recipe eaten**| `deleteC INDEX` <br> e.g., `deleteC 3`
**Search recipe** | `searchR i/INGREDIENT` OR `searchR n/TITLE` OR `searchR t/TAG` <br> e.g. `searchR i/lettuce`, `searchR n/salad`, `searchR t/healthy`
**Search Ingredient in the fridge** | `searchF KEYWORD`<br> e.g., `searchF avocado`
**List recipe** | `recipeS`
**List ingredients in the fridge** | `fridge`
**List recipes eaten** | `calories`
**Recommend recipe** | `recommend`
**Eat recipe**| `eatR INDEX` <br> e.g., `eatR 3`
**Help** | `help`
**Exit** | `exit`
