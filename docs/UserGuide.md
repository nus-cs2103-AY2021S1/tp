---
layout: page
title: User Guide
---

1. Table of Contents
{:toc}

---------------
## Introduction

ChopChop is a food recipe management system, which aims to make it easier for people to manage their recipes and ingredients in an easy and effective manner. It is a *desktop app*, optimised for use through typing textual commands, rather than with a point-and-click GUI. For fast typists, ChopChop will be able to manage your recipes more efficiently than other applications.

However, ChopChop also features a graphical interface to display ingredients and recipes in an interactive form.

--------------
## Quick start

To start using and experimenting with ChopChop, here are the steps you can follow:

1. Ensure you have Java `11` or above installed in your Computer; it can be obtained from [AdoptOpenJDK](https://adoptopenjdk.net).

2. Download the latest `chopchop.jar` from [here](https://github.com/AY2021S1-CS2103T-T10-3/tp/releases).

3. Copy the file to the folder you want to use as the _home folder_ for your ChopChop.

4. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. The app starts with some sample data for you to experiment with.<br>
   ![Ui](images/Ui.png)

5. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`list recipes`** : Lists recipes.

   * **`add recipe Milkshake /ingredient Milk /qty 500ml /step Pour Milk /step Shake`** : Adds a recipe for making milkshakes, with one ingredient and two steps.

   * **`quit`** : Exits the app.

6. Refer to the [Commands](#commands) below for details of each command.

-----------
## Overview

ChopChop manages two key components — ingredients and recipes, and they will be the main pieces you will interact with in ChopChop. Common to both are names and tags, the latter of which allow you to quickly group related ingredients or recipes together, or to organise them in any way you desire.

Names for both ingredients and recipes are case insensitive, so `pAnCakes` and `Pancakes` refer to the same recipe. Note that you cannot have duplicate recipes nor ingredients in ChopChop; items are duplicates if their names are the same.

### Ingredients
An ingredient consists has a quantity with an associated unit, and an optional expiry date. Each ingredient can have multiple *sets*, where each set is a given quantity of that ingredient, exipring on a certain date.

For example, you might have `500 ml` of milk that you bought last week that expires tomorrow, while you have another `1.5 l` of milk that you bought today, expiring two weeks from now. ChopChop will track both these *sets*, and will intelligently use the earliest-expiring set when doing its accounting.

For a more in-depth look at how ChopChop handles quantities, see [this section](#quantities-and-units).

### Recipes
A recipe consists of a list of used ingredients (and their quantities), as well as a list of steps.


--------------------------------
## Navigating the User Interface

<h1>WE NEED A PROPERLY LABELLED PICTURE</h1>

![Ui](images/Ui.png)

<h3>insert the various parts of the GUI and talk about it.</h3>






-----------
## Commands


### Command Syntax

To succinctly represent the syntax of the various commands, we adopt a simple notation in this User Guide, as shown below:

* Words starting with a slash (`/`) denote named parameters; these names are case sensitive (eg. `/STEP` is not the same as `/step`). All the text following a named parameter *belong* to it, until either the end of the input, or the next named parameter. <br />
For example, in `/param1 lorem ipsum /param2 dolor sit amet`, the parameter `param1` will have the value `lorem ipsum`, while the parameter `param2` will have the value `dolor sit amet`.

* Words in angle brackets (eg. `<name>`) denote an input that is provided by *you*, the user. <br />
For example, the **add ingredient** command is specified like this: `add ingredient <name> /qty <quantity> [/expiry <expiry_date>]`; in this case, `<name>`, `<quantity>` and `<expiry_date>` are values that you provide.

* Portions in square brackets (eg. `[/expiry <expiry_date>]`) denote optional parts of the command. In this example, not all ingredients will expire, so the expiry date is optional.

* Portions with trailing ellipses (eg. `(/step <step>)...`) denote commands accepting one or more of the given argument. In this example, a recipe can have multiple steps, so you can specify multiple `/step` arguments.

* Parentheses are used to group parts of the command together purely for clarity in this document; they must not appear in the typed input. For instance, in `(/step <step>)...`, it is simply used to denote that each step must be preceeded by the `/step` argument.

* A `<#REF>` refers to an item reference, and is used to refer to either a recipe or an ingredient. It can either be the (case-insensitive) name of the item, or it can be a number prefixed with '#', eg. `#3` to refer to the third item in the list. In the GUI, displayed items are numbered in the corner.

* In general, the order of arguments is important; for example, the order of `/step` arguments determines the order of the steps in the recipe, while a `/qty` in an **add recipe** command must only appear after an `/ingredient` argument.


### Viewing Help : **`help`**

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

Usage: `help`


### Quitting ChopChop **`quit`**
Quits the program.

Usage: `quit`





### Listing Recipes — **`list`**`recipes`
Shows a list of all recipes in ChopChop.

<div markdown="span" class="alert alert-primary">
:bulb: **Tip:** For convenience, this command accepts `list recipes` as well as `list recipe`.
</div>

Usage: `list recipes`



### Adding Recipes — **`add`**`recipe`
Adds a recipe to the recipe manager, specifying zero or more ingredients, each with an optional quantity, and at least one step.

Usage: `add recipe <NAME> [/ingredient <INGREDIENT_NAME> [/qty <QUANTITY>]]... /step <STEP_ONE> [/step <STEP_TWO>]...`

Examples (separated into lines for clarity, but you should type them as one line):
```
add recipe Pancakes
/ingredient flour /qty 400g
/ingredient egg /qty 3
/ingredient milk /qty 250ml
/step Mix ingredients together
/step Bake for 30 minutes at 400 celsius
/step Pour syrup and serve
```


 

### Editing Recipes — **`edit`**`recipe`
Edits a specific recipe from ChopChop. The `edit recipe` command allows for different actions on the name, ingredients, steps and tags, as specified below.

#### Name
To edit a recipe's name, use the `/name` parameter (e.g. `/name new recipe name`).

#### Ingredients
To edit a recipe's ingredients, use the `/ingredient` parameter, with an action specified after a colon. Note that ingredient names are case-insensitive.

Possible actions include:

- Adding an ingredient with the `/ingredient:add` parameter (e.g. `/ingredient:add beef /qty 1kg`). Note that specifying a quantity is required.
- Editing an ingredient with the `/ingredient:edit` parameter (e.g.`/ingredient:edit beef /qty 3kg` ). The ingredient must exist for the command to be valid.
- Deleting an ingredient with the `/ingredient:delete` parameter (e.g. `/ingredient:delete beef`).

#### Steps
To edit a recipe's steps, use the `/step` parameter, with an action specified after a colon. Steps are referred to using their indexes (starting from 1), which specified after the action.

Possible actions include:

- Adding a step with the `/step:add` parameter (e.g. `/step:add Add in the vanilla extract and mix well`). If an index is specified, the step will be added at the given index. Otherwise, it will be added after all existing steps.
- Editing a step with the `/step:edit` parameter. An index must be specified for the command to be valid (eg. `/step:edit:4 Beat the eggs thoroughly`).
- Deleting a step with the `/step:delete` parameter (e.g. `/step:delete:4`).

#### Tags
To edit a recipe's steps, use the `/tag` parameter, with an action specified after a colon. Like ingredients, tags are case-insensitive.

Possible actions include:

- Adding a tag with the `/tag:add` parameter (e.g. `/tag:add breakfast`).
- Deleting a tag with the `/tag:delete` parameter (e.g. `/tag:delete breakfast`)

Usage `edit recipe <#REF> [/name <RECIPE_NAME>] [/ingredient:<ACTION> [<INGREDIENT_NAME> /qty <QUANTITY>]]... [/step:<ACTION>[:<INDEX>] [<STEP>]]... [/tag:<ACTION> [<TAG>]]...`

Examples:
- `edit recipe #4 /name soup` <br />
	This changes the name of the fourth recipe currently shown in the GUI's view to 'soup'.
- `edit recipe pancakes /ingredient:add syrup /qty 500ml` <br />
	This edits the recipe named 'pancakes' by adding 500ml of syrup to the recipe's ingredient list.
- `edit recipe risotto /step:edit:1 In a saucepan, warm the broth over low heat` <br />
    This edits the recipe named 'risotto' by changing the 1st step to the text above.
- `edit recipe beef curry /ingredient:delete apple /step:delete:4` <br />
    This edits the recipe named 'beef curry' to remove both the ingredient 'apple' and the 4th step.


### Deleting Recipes — **`delete`**`recipe`
Deletes a specific recipe from ChopChop.

Usage: `delete recipe <#REF>`

Examples:
- `delete recipe #4` <br />
	This deletes the fourth recipe currently shown in the GUI's view.
- `delete recipe pancakes` <br />
	This deletes the recipe named 'pancakes'. Note that the name here is case insensitive.



### Finding Recipes — **`find`**`recipe`
Finds all recipes containing the given keywords in the name; at least one search term must be given.

Usage: `find recipe <KEYWORD_ONE> [<KEYWORD_TWO>]...`

Only the recipe name is searched, and only full words are matched, case-insensitively. In the case of multiple search keywords, recipes containing any of those words will be returned.

Examples:
- `find recipe cake` will match **Chocolate Cake** and **Strawberry Cake**, but *not* **Pancakes**.
- `find recipe milk cake` will match **Milk Tea** and **Carrot Cake**.







### Listing Ingredients — **`list`**`ingredients`
Shows a list of all ingredients in ChopChop.

<div markdown="span" class="alert alert-primary">
:bulb: **Tip:** For convenience, this command accepts `list ingredients` as well as `list ingredient`.
</div>

Usage: `list ingredients`




### Adding Ingredients — **`add`**`ingredient`
Adds an ingredient to the recipe manager, with an optional quantity and expiry date. If the quantity is not specified, ChopChop will infer a single unitless ingredient, like eggs.

As mentioned in the overview above, an ingredient can consist of multiple sets; the `add ingredient` command will intelligently *combine* ingredients as appropriate.

<div markdown="span" class="alert alert-primary">
:information_source: **Note:** Ingredients need to have compatible units in order to be combined; see [this section](#quantities-and-units) for how it works.
</div>

Usage: `add ingredient <NAME> [/qty <QUANTITY>] [/expiry <EXPIRY_DATE>]`

Examples:
- `add ingredient milk /qty 1l /expiry 2020-11-09` adds one litre of milk that expires on the 9th of November.
- `add ingredient egg /expiry 2020-12-25` adds one egg that expires on Christmas day.




### Deleting Ingredients — **`delete`**`ingredient`
Deletes a specific ingredient from ChopChop. If a quantity is specified, the given quantity of the ingredient is removed.

If an ingredient consists of multiple sets, the `delete ingredient` command will intelligently remove the earliest expiring ingredients first.

<div markdown="span" class="alert alert-primary">
:information_source: **Note:** If specified, the quantity needs to have compatible units with the existing ingredient; see [this section](#quantities-and-units) for how it works.
</div>

Usage: `delete ingredient <#REF> [/qty <QUANTITY>]`

Examples:
- `delete ingredient #4` <br />
	This deletes the fourth ingredient currently shown in the GUI's view.
- `delete ingredient milk` <br />
	This deletes the ingredient named 'milk'. Note that the name here is case insensitive.
- `delete ingredient milk /qty 500ml` <br />
    This removes 500ml of the ingredient named 'milk'.




### Finding Ingredients — **`find`**`ingredient`
Finds all ingredients containing the given keywords in the name; at least one search term must be given.

Usage: `find ingredient <KEYWORD_ONE> [<KEYWORD_TWO>]...`

The semantics of this command are the same as `find recipe`, which you can see [above](#finding-recipes--findrecipe).




#### Undoing commands — **`undo`**
Undoes the last undoable command. Undoable commands are commands that involve changes to recipes and ingredients stored in ChopChop.

Usage: `undo`




#### Redoing commands — **`redo`**
Redoes the last redoable command. All undoable commands (as described [above](#undoing-commands--undo)) can be redone.

Usage: `redo`




### Listing most made Recipes — **`stats recipe most made`**
Shows a list of the top 5 most made recipes of all time. The number of times of which each recipe is made is tracked from the start of usage of ChopChop. Even after you delete the recipe is deleted, its past usages are still saved within ChopChop.

Usage: `stats recipe most made`

Example:
Let's say you executed `make Singapore Sling` 2 times a day for the past 1 year. Today, you decided to delete the recipe for health reasons. If you enter `stats recipe most made`, you will still see it listed as one of the most made recipes.




### Listing Recipes made within a certain period — **`stats recipe`**
The time period given can either be:
1. A specific day.
2. A period of time with a start date or an end date or both.
#### 1. Listing recipes made on the specified day. 
The day starts at 00:00 hours and ends at 23:59 hours.

Usage: `stats recipe /on <DATE>` 

For example:
Let's say you executed `make Rojak` on 23:59 hours yesterday. If you enter `stats recipe /on <TODAY'S DATE>` you will not see `Rojak` listed in the statistics box. 

#### 2. Listing recipes made within the specified time period.
Shows a list of recipes made within the specified time period. It should have a start date or an end date or both.

Usage: `stats recipe [/before <DATE>] [/after <DATE>]`
<div markdown="span" class="alert alert-primary">
:warning: 
</div>

**Note:** Either `[/before <DATE>]` or `[/after <DATE>]` has to be specified.


Examples:

If you enter `stats recipe /before 2020-02-13` into the command box, all recipes made prior to 2020-02-13 will be listed in the Statistics box.

If you enter `stats recipe /after 2020-02-13` into the command box, all recipes made after 2020-02-13 will be listed in the Statistics box.

If you enter `stats recipe /before 2020-10-31 /after 2020-02-13` into the command box, all recipes made within the period of 2020-02-13 to 2020-10-31 will be listed in the Statistics box.

If you enter `stats recipe` into the command box without either `[/before <DATE>]` or `[/after <DATE>]`, no recipes will be listed as this is an invalid command.

<div markdown="span" class="alert alert-primary">
:bulb: **Tip:** If you are only interested in what was cooked for dinner, you can specify the time period to the nearest minute. For example, `stats recipe /before 2020-02-13 20:30 /after 2020-02-13 18:30` will show a list of recipes made within this 2-hour period.
</div>




### Listing Ingredients used within a certain period — **`stats ingredient`**
Shows a list of ingredients used within the specified time period.

The time period given can either be:
1. A specific day.
2. A period of time with a start date or an end date or both.

Usage: Similar to the previous command [above](#listing-recipes-made-within-a-certain-period--stats-recipe), the only difference is the keyword is now `stats ingredient` instead of `stats recipe`.







-----------------------
## Quantities and Units

In order to keep track of ingredients correctly, ChopChop needs to know about their amounts. Currently, there are 3 'kinds' of units supported; volume, mass (weight), and counts. These are the supported units specifically:

- `ml` — millilitres
- `l` — litres (1000ml)
- `cup`, `cups` — metric cup (250ml)
- `tsp` — metric teaspoon (5ml)
- `tbsp` — metric tablespoon (15ml)
- `g` — gram
- `mg` — milligram (0.001g)
— `kg` — kilogram (1000g)

Additionally, quantities without a unit are assumed to be dimensionless 'counts'; for example, **3 eggs**.


### Ingredient Combining

As mentioned above, ChopChop will combine ingredients when you `add` them, provided they have compatible units. Combining works as you would expect, and is rather flexible; adding `3 cups` of milk to an existing stock of `400ml` will yield `1.15l`.

However, you cannot, for example, add `300g` of eggs to `4` eggs, as grams and counts are incompatible units.
