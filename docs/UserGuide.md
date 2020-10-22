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

To succintly represent the syntax of the various commands, we adopt a simple notation in this User Guide, as shown below:

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

Shows a message explaning how to access the help page.

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
Deletes a specific ingredient from ChopChop.

Usage: `delete ingredient <#REF>`

Examples:
- `delete ingredient #4` <br />
	This deletes the fourth ingredient currently shown in the GUI's view.
- `delete ingredient milk` <br />
	This deletes the ingredient named 'milk'. Note that the name here is case insensitive.




### Finding Ingredients — **`find`**`ingredient`
Finds all ingredients containing the given keywords in the name; at least one search term must be given.

Usage: `find ingredient <KEYWORD_ONE> [<KEYWORD_TWO>]...`

The semantics of this command are the same as `find recipe`, which you can see [above](#finding-recipes--findrecipe).




### Using Ingredients — **`use`**
Removes the given quantity of the specified ingredient from the internal inventory.

Usage: `use <#REF> /qty <QUANTITY>`

Examples:
- `use milk /qty 500ml` removes 500 ml of milk from the internal inventory.





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
