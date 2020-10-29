---
layout: page
title: User Guide
---

* Table of Contents
{:toc}

---------------
## 1. Introduction

ChopChop is a food recipe management system, which aims to make it easier for people to manage their recipes and ingredients in an easy and effective manner. It is a *desktop app*, optimised for use through typing textual commands; For fast typists, ChopChop will be able to manage your recipes more efficiently than other applications. Even so, it also features a graphical user interface (GUI) to display ingredients and recipes in an interactive form.

Furthermore, our command interface also features [tab completion](#TabCompletion), which will greatly increase the speed at which you can use ChopChop by reducing the amount of typing required.


### 1.1. Navigating this Document

This user guide provides an in-depth guide about how to use ChopChop. Choose a topic from the Table of Contents to find answers, get step-by-step instructions. In addition, the quick start guide provides an end-to-end setup process to get you started on the ChopChop installation process.

Specifically, this document covers:
1. The components of the user interface
2. The syntax and behaviour of the commands
3. Detailed usage examples, with step-by-step illustrated walkthroughs
4. Other usage notes and features


### 1.2. Document Conventions

In this document, some elements are styled differently for emphasis. These include:
- :bulb: — This a useful piece of information that can make using ChopChop easier
- :information_source: — This indicates something that you should pay attention to
- `code` — Text styled in this font generally indicates commands that you will type into the <i>command box</i> (see below)
- <kbd>enter</kbd> — Text styled in this font indicates keys that you press on your keyboard



--------------
## 2. Quick Start

To start using and experimenting with ChopChop, here are the steps you can follow:

1. Ensure you have Java <b>11</b> or above installed in your computer; it can be obtained from [AdoptOpenJDK](https://adoptopenjdk.net).

2. Download the latest <i>chopchop.jar</i> from [here](https://github.com/AY2021S1-CS2103T-T10-3/tp/releases).

3. Copy the file to the folder you want to use as the _home folder_ for your ChopChop.

4. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. The app starts with some sample data for you to experiment with.<br>

<div style="text-align: center; padding-bottom: 2em">
<img src="images/ug/sample_data.png" width="75%" /> <br />
Figure 1.1: <i>The initial state of ChopChop, including sample data</i>
</div>

Now that you have ChopChop installed, you can start to play around with the sample data — add recipes, ingredients, and more! Some commands you could try include:

- `add ingredient milk /qty 500ml /expiry 2020-11-09` — to add some milk
- `add recipe milkshake /ingredient milk /qty 250ml /step Add milk /step Shake` — to add a recipe

To exit ChopChop, you can either use the `quit` command, or simply close the application window.


-----------
## 3. Overview

ChopChop manages two key components — ingredients and recipes — and they will be the main pieces you will interact with. Common to both are names and tags, the latter of which allow you to quickly group related ingredients or recipes together, or to organise them in any way you desire.

The names for both ingredients and recipes are case insensitive, so <i>pAnCaKeS</i> and <i>Pancakes</i> refer to the same recipe. Note that you cannot have duplicate recipes nor ingredients in ChopChop; items are duplicates if their names are the same.

### 3.1. Ingredients
An ingredient consists of a quantity with an associated unit, and an optional expiry date. Each ingredient can have multiple *sets*, where each set is a given quantity of that ingredient, expiring on a certain date.

For example, you might have <i>500 mL</i> of milk that you bought last week that expires tomorrow, while you have another <i>1.5 L</i> of milk that you bought today, expiring two weeks from now. ChopChop will track both these *sets*, and will intelligently use the earliest-expiring set when doing its accounting.

For a more in-depth look at how ChopChop handles quantities, see [this section](#quantities-and-units).

### 3.2. Recipes
A recipe consists of a list of used ingredients (and their quantities), as well as a list of steps.


--------------------------------
## 4. Navigating the User Interface

ChopChop's UI design allows users to view all of the information you need through mouse input over a few tabs. However, that might lead to a slightly steeper learning curve.
Hence, this section aims to give you a breakdown of the GUI's various components.

Specifically, this section covers:
1. [Command Box](#commandBox)
2. [Command Output](#commandOutput)
3. [Recipe Button](#recipeButton)
4. [Ingredient Button](#ingredientButton)
5. Recommendation Button
6. Favourites Button
7. Statistics Box
8. Recipe Tile
9. Ingredient Tile
10. Recipe Name and Tags
11. Recipe Ingredients
12. Recipe Steps
13. Menu Bar

<div style="text-align: center; padding-bottom: 2em">
<img src="images/ug/recipe_panel_description.png" width="95%" /> <br />
Figure 1.2: <i>The recipe view panel of ChopChop.</i>
</div>

<div style="text-align: center; padding-bottom: 2em">
<img src="images/ug/ingredient_panel_description.png" width="95%" /> <br />
Figure 1.3: <i>The ingredient view panel of ChopChop.</i>
</div>

<a name="commandBox"></a>
### 4.1 Command Box 
ChopChop does your bidding by listening to your commands — the `Command Box` is where you type your textual commands.
After typing your commands, press <kbd>enter</kbd> to input the command.
 
To learn about the commands you can perform, check out our [command summary](#commandSummary) for a quick overview or our [commands](#commands) for a detailed -.
If you have yet to check out ChopChop's [tab completion](#TabCompletion) section, do drop by to learn this handy feature!

<a name="commandOutput"></a>
### 4.2 Command Output
ChopChop will always display textual responses to the commands you input — the `Command Output` is where you can view the responses.

<a name="recipeButton"></a>
### Recipe Button
ChopChop is able to display all of your recipes as [Recipe Tiles]() as shown in Figure 1.2 when you press the `Recipe Button`. Also, the `Recipe Button` will be in a darker shade of orange when the `Recipe Tiles` are in play.




<a name="ingredientButton"></a>
### Ingredient Button
ChopChop is able to display all of your ingredients as [Ingredient Tiles]() as shown in Figure 1.3 when you press the `Ingredient button`. Also, the `Ingredient Button` will be in a darker shade of orange when the `Ingredient Tiles` are in play.



<div style="text-align: center; padding-bottom: 2em">
<img src="images/ug/recipe_display_description.png" width="95%" /> <br />
Figure 1.4: <i>The recipe display panel of ChopChop.</i>
</div>




-----------
<a name="commands"></a>
## 5. Commands

While ChopChop has a graphical user interface, the main method of interaction is through the use of *typed commands*. Using these commands as described below, you can manipulate your recipes and ingredients without ever needing to move your mouse!

Commands should be typed in the <i>Command Box</i> — you can refer to <i>Figure 1.2</i> above if you get lost.

### 5.1. Command Syntax

To succinctly represent the syntax of the various commands, we adopt a simple notation in this User Guide, as shown below:

* Words starting with a slash (`/`) denote named parameters; these names are case sensitive (eg. `/STEP` is not the same as `/step`). All the text following a named parameter *belong* to it, until either the end of the input, or the next named parameter. <br />
For example, in `/param1 lorem ipsum /param2 dolor sit amet`, the parameter <i>param1</i> will have the value &ldquo;<i>lorem ipsum</i>&rdquo;, while the parameter <i>param2</i> will have the value &ldquo;<i>dolor sit amet</i>&rdquo;.

* Words in angle brackets (eg. `<name>`) denote an input that is provided by *you*, the user. <br />
For example, the <i>add ingredient</i> command is specified like this: `add ingredient <name> /qty <quantity> [/expiry <expiry-date>]`; in this case, you would need to provide the <i>name</i>, <i>quantity</i>, and <i>expiry date</i>.

* Portions in square brackets (eg. `[/expiry <expiry-date>]`) denote optional parts of the command. In this example, not all ingredients will expire, so the expiry date is optional.

* Portions with trailing ellipses (eg. `[/step <step>]...`) denote commands accepting one or more of the given parameter. In this example, a recipe can have multiple steps, so you can specify multiple `/step` arguments.

* A `<#REF>` refers to an item reference, and is used to refer to either a recipe or an ingredient. It can either be the (case-insensitive) name of the item, or it can be a number prefixed with '#', eg. `#3` to refer to the third item in the list. In the GUI, displayed items are numbered in the corner.

* In general, the order of arguments is important; for example, the order of `/step` determines the order of the steps in the recipe, while a `/qty` in an <i>add recipe</i> command must only appear after an `/ingredient`.






<a name="HelpCommand"></a>
### 5.2. Getting Help — **`help`**

This command shows a message with a link to this user guide; you can use it to easily access this page from the application. The link can be clicked, and will open this page in your web browser.

Furthermore, you can also use this command to get help for specific commands. In that case, clicking the link will bring you to the corresponding section in the User Guide for that command.

**Usage**: `help [<command-name> [<command-target>]]`

Examples:
- `help` <br />
  This shows the link to bring you to this User Guide, as shown in figure 2.1 below.

  <div style="text-align: center; padding-bottom: 2em">
  <img src="images/ug/help_message_1.png" width="55%" /> <br />
  Figure 2.1: <i>The help message, with a link to this User Guide</i>
  </div>

- `help add recipe` <br />
  This gives a brief description of the command provided, as well as a link to its section.

  <div style="text-align: center; padding-bottom: 2em">
  <img src="images/ug/help_message_2.png" width="55%" /> <br />
  Figure 2.2: <i>The help message for a specific command</i>
  </div>






<a name="QuitCommand"></a>
### 5.3. Quitting ChopChop — **`quit`**
This command quits ChopChop. You can rest assured that your data is automatically saved whenever a command is executed, so you do not need to save it manually before quitting.

**Usage**: `quit`






<a name="ViewCommand"></a>
### 5.4. Viewing Recipes — **`view`**
This command opens the detailed recipe view, allowing you to see the steps, ingredients, and tags of the recipe.

**Usage**: `view <#REF>`

Examples:
- `view #4` <br />
	This displays the fourth recipe currently shown in the GUI's recipe view.
- `view pancakes` <br />
	This displays the recipe named 'pancakes'. Note that the name here is case insensitive.

To illustrate, in the scenario below, both `#4` and `pancakes` will refer to the same recipe:
<div style="text-align: center; padding-bottom: 2em">
<img src="images/ug/view_recipe_1.png" width="75%" /> <br />
Figure 3.1: <i>The recipe list view</i>
</div>

After pressing <kbd>enter</kbd>, you will see this view, showing the recipe you wish to view:

<div style="text-align: center; padding-bottom: 2em">
<img src="images/ug/add_recipe_2.png" width="75%" /> <br />
Figure 3.2: <i>The detailed recipe view</i>
</div>






<a name="ListRecipeCommand"></a>
### 5.5. Listing Recipes — **`list`**`recipes`
This command shows a list of all recipes in ChopChop. You can use this to switch panes (between recipes and ingredients) without using the mouse, as well as to clear any filters that might have been applied due to previous commands (eg. `find` and `filter`).

**Usage**: `list recipes`

<div markdown="span" class="alert alert-primary">
:bulb: **Tip:** For convenience, you can use either `list recipes` or `list recipe`.
</div>

Executing this command simply brings you back to the recipe list view:

<div style="text-align: center; padding-bottom: 2em">
<img src="images/ug/list_recipe.png" width="75%" /> <br />
Figure 4: <i>The recipe list view</i>
</div>





<a name="AddRecipeCommand"></a>
### 5.6. Adding Recipes — **`add`**`recipe`
This command adds a recipe to ChopChop, specifying zero or more ingredients, each with an optional quantity, and zero or more steps. After a recipe is added, you will be able to see it immediately in the application.

**Usage**:
```
add recipe <name>
  [/ingredient <ingredient-name> [/qty <quantity>]]...
  [/step <step>]...
  [/tag <tag-name>]...
```

Constraints:
- Recipe name should not be empty
- Ingredient names should not be empty
- Steps should not be empty
- Tag names should not be empty

If an ingredient is specified without a quantity, it is treated *as if* you used `/qty 1`. This works for counted ingredients (eg. eggs), but it will cause errors for other ingredients (eg. volume of milk).


For example, suppose you wanted to add a recipe for pancakes using flour, eggs, and milk, you would type this:
```
add recipe Pancakes
/ingredient flour /qty 400g
/ingredient egg /qty 3
/ingredient milk /qty 250ml
/step Mix ingredients together
/step Bake for 30 minutes at 400 celsius
/step Pour syrup and serve
```
(note that this is displayed on separate lines for clarity, but you should type this in one go)

<div style="text-align: center; padding-bottom: 2em">
<img src="images/ug/add_recipe_1.png" width="75%" /> <br />
Figure 5.1: <i>The add recipe command</i>
</div>

After pressing <kbd>enter</kbd>, you will see this view, showing your newly created recipe:

<div style="text-align: center; padding-bottom: 2em">
<img src="images/ug/add_recipe_2.png" width="75%" /> <br />
Figure 5.2: <i>The recipe detail view</i>
</div>

If you go back to the main recipe view (either by clicking on the tab at the bottom, or by using `list recipes`, you can see the new recipe in the list:

<div style="text-align: center; padding-bottom: 2em">
<img src="images/ug/add_recipe_3.png" width="75%" /> <br />
Figure 5.3: <i>The newly created recipe in the recipe list</i>
</div>











<a name="EditRecipeCommand"></a>
### 5.7. Editing Recipes — **`edit`**`recipe`
This command edits a specific recipe in ChopChop. The `edit recipe` lets you perform different actions on the name, ingredients, steps, and tags, as specified below.

To accomodate the various different kinds of editing operations, ChopChop has special syntax for editing, known as *edit-arguments*, eg. `/step:add`. The component following the colon is the *ACTION*, which can take these values:

- For ingredients and steps, it can either be `add`, `edit`, or `delete`.
- For tags, it can be either `add` or `delete`.



<h4>Name</h4>
If you want to edit a recipe's name, use `/name`, for example `/name new recipe name`.


<h4>Ingredients</h4>
If you want to edit a recipe's ingredients, use `/ingredient` with the corresponding action (eg. `/ingredient:add`).

When adding or editing ingredients, a `/qty` *must* be specified after the ingredient (similar to an `add recipe` command). Here are some examples:

- `/ingredient:add milk /qty 500ml` <br />
  This makes the recipe require 500ml of milk; if the recipe already used milk, then an error is displayed — here, you should use `/ingredient:edit` instead.

- `/ingredient:edit beef /qty 200g` <br />
  This changes the quantity of beef used in the recipe from its previous value, to 200 grams. If the recipe did not use beef as an ingredient, an error is displayed — here, you should use `/ingredient:add` instead.

- `/ingredient:delete carrot` <br />
  This removes carrots from the recipe entirely. If the recipe did not use carrots, then an error is displayed.


<h4>Tags</h4>
If you want to edit the tags for a recipe, use `/tag` with the corresponding action, which are either `add` or `delete`. For example:

- `/tag:add vegetarian` <br />
  This adds the 'vegetarian' tag to the recipe. It is not an error if the recipe already contains this tag.

- `/tag:delete cold` <br />
  This removes the 'cold' tag from the recipe. If the recipe did not have this tag, an error is displayed.



<h4>Steps</h4>
Since steps have a fixed ordering in a recipe, editing them is slightly more involved; when editing or deleting steps, you are required to provide the step number as an additional component in the *edit-argument*, for example `/step:edit:3` edits the third step in the recipe.

When adding a step, the step number is optional; if not specified, the new step will be added at the end. If it is specified, then the new step will be inserted at the corresponding position, and the following steps will be re-numbered.

For example:

- `/step:add Bake for 80 minutes at 400 C` <br />
  This adds a new step at the end of the existing steps of the recipe.

- `/step:edit:4 Bake for 50 minutes at 250 C` <br />
  This changes the content of step number 4, so the cake does not get burnt.

- `/step:delete:1` <br />
  This deletes the first step of the recipe.


<h4>Usage</h4>

Except `/name` (which can only appear once), all of the edit operations described above can appear multiple times, in any order, in a single `edit recipe` command. Each operation is processed sequentially from left-to-right, so if two operations modify the same item, then the second one will take precedence.

(As an example, `/step:edit:3 Bake ... /step:edit:3 Fry` will cause step 3 to be 'Fry')

**Usage**:
```
edit recipe <#REF>
  [/name <new-recipe-name>]
  [/ingredient:<action> <ingredient-name> [/qty <quantity>]]...
  [/step:<action>[:<index>] <step>]...
  [/tag:<action> <tag-name>]....
```

Examples:
- `edit recipe #4 /name soup` <br />
	This changes the name of the fourth recipe currently shown in the GUI's view to 'soup'.
- `edit recipe pancakes /ingredient:add syrup /qty 500ml` <br />
	This edits the recipe named 'pancakes' by adding 500ml of syrup to the recipe's ingredient list.
- `edit recipe risotto /step:edit:1 In a saucepan, warm the broth over low heat` <br />
    This edits the recipe named 'risotto' by changing the 1st step to the text above.
- `edit recipe beef curry /ingredient:delete apple /step:delete:4` <br />
    This edits the recipe named 'beef curry' to remove both the ingredient 'apple' as well as the 4th step.

To illustrate how to use this powerful command, let us recreate the Pancake recipe from above, but starting from a blank recipe. First, we make the empty recipe using `add recipe Pancakes`:

<!-- to editors: don't mind the image names, i cut out some steps for brevity and i'm lazy to rename the files. -->
<div style="text-align: center; padding-bottom: 2em">
<img src="images/ug/edit_recipe_1.png" width="75%" /> <br />
Figure 6.1: <i>The empty recipe</i>
</div>

Now, let's add our ingredients. First, 400 grams of flour:

<div style="text-align: center; padding-bottom: 2em">
<img src="images/ug/edit_recipe_2.png" width="75%" /> <br />
Figure 6.2: <i>The command to add a new ingredient to the recipe</i>
</div>

Next, adding the eggs and milk in one go:

<div style="text-align: center; padding-bottom: 2em">
<img src="images/ug/edit_recipe_4.png" width="75%" /> <br />
Figure 6.3: <i>The edit command supports multiple operations at once</i>
</div>

Oops, that's too many eggs, so let's edit the quantity:

<div style="text-align: center; padding-bottom: 2em">
<img src="images/ug/edit_recipe_5.png" width="75%" /> <br />
Figure 6.4: <i>Editing an ingredient to change its quantity</i>
</div>

<div style="text-align: center; padding-bottom: 2em">
<img src="images/ug/edit_recipe_6.png" width="75%" /> <br />
Figure 6.5: <i>The recipe now uses only 3 eggs</i>
</div>

Now let's add the steps:

<div style="text-align: center; padding-bottom: 2em">
<img src="images/ug/edit_recipe_7.png" width="75%" /> <br />
Figure 6.6: <i>Adding the first step</i>
</div>

<div style="text-align: center; padding-bottom: 2em">
<img src="images/ug/edit_recipe_8.png" width="75%" /> <br />
Figure 6.7: <i>Adding steps 2 and 3</i>
</div>

Oh no, if we bake the pancakes (are pancakes baked?) like that, they'll get burnt, so let's fix it:

<div style="text-align: center; padding-bottom: 2em">
<img src="images/ug/edit_recipe_10.png" width="75%" /> <br />
Figure 6.8: <i>Editing the second step</i>
</div>

<div style="text-align: center; padding-bottom: 2em">
<img src="images/ug/edit_recipe_11.png" width="75%" /> <br />
Figure 6.9: <i>The actual completed recipe</i>
</div>

And now the pancake recipe is complete!











<a name="DeleteRecipeCommand"></a>
### 5.8. Deleting Recipes — **`delete`**`recipe`
This command deletes a specific recipe from ChopChop. Don't worry if you did this accidentally, because commands can be undone! (see: [undo](#UndoCommand)).

**Usage**: `delete recipe <#REF>`

Examples:
- `delete recipe #4` <br />
	This deletes the fourth recipe currently shown in the GUI's view.
- `delete recipe pancakes` <br />
	This deletes the recipe named 'pancakes'. Note that the name here is case insensitive.





<a name="FindRecipeCommand"></a>
### 5.9. Finding Recipes — **`find`**`recipe`
This command finds all recipes containing the given keywords in the name.

**Usage**: `find recipe <keyword> [<keyword>]...`

Constraints:
- At least one search keyword must be given

Only the recipe name is searched, and only full words are matched, case-insensitively. In the case of multiple search keywords, recipes containing any of those words will be returned.

Examples:
- `find recipe cake` will match **Chocolate Cake** and **Strawberry Cake**, but *not* **Pancakes**.
- `find recipe milk cake` will match **Milk Tea** and **Carrot Cake**.

To illustrate, suppose you want to search for recipes with names containing 'cake', you would use `find recipe cake`:
<div style="text-align: center; padding-bottom: 2em">
<img src="images/ug/find_recipe_1.png" width="75%" /> <br />
Figure 8.1: <i>The starting state of the application</i>
</div>

After executing the command, note how the recipe list has changed, showing only the matching recipes, and that item number in the corners have changed as well. As explained above, the 'Pancakes' recipe was not included in this list:

<div style="text-align: center; padding-bottom: 2em">
<img src="images/ug/find_recipe_2.png" width="75%" /> <br />
Figure 8.2: <i>The recipes containing 'cake'</i>
</div>

To go back to the full recipe view (resetting the search filter), you can either click the Recipes button at the bottom, or run the `list recipes` command:

<div style="text-align: center; padding-bottom: 2em">
<img src="images/ug/find_recipe_3.png" width="75%" /> <br />
Figure 8.3: <i>Back to the main recipe list</i>
</div>








<a name="FilterRecipeCommand"></a>
### 5.10. Filtering Recipes — **`filter`**`recipe`
This command filters all recipes and lists those containing all ingredients and tags specified in the command.

**Usage**:
```
filter recipe
  [/tag <tag-keywords>...]...
  [/ingredient <ingredient-keywords>...]...
```

- Keywords do not have to be complete to match the 'tag' or 'ingredient' names.
- Multiple search terms from the same category are allowed. e.g. `/tag movie /tag family`
- Search terms can be placed in any order.
- The filtering is case-insensitive and allows spaces between keywords in a single search term. e.g. `/tag family favourite` is allowed.

Constraints:
- At least one search term must be given, and they should be either `/tag` `/ingredient`.
- Search terms must not be empty.

Examples:
- `filter recipe /tag family reunion` will match **Spring Rolls** and **Hot Pot**, the only recipes with 'tag' **family reunion**.
- `filter recipe /tag snacks /tag sweet` will match **Chocolate Cookie** and **Gummy Bears**, the only recipes with 'tag' **snacks** and 'tag' **sweet**.
- `filter recipe /ingredient egg` will match **Egg Tart** and **Scrambled Eggs**, the only recipes using the 'ingredient' **egg**.
- `filter recipe /ingredient chicken /ingredient cheese /ingredient pineapple` will match **Chicken Quesadilla**, the only recipe containing 'ingredient' **chicken**, **cheese**, and **pineapple**.
- `filter recipe /tag local dish /ingredient chicken /ingredient white rice /tag family favourite` will match **Chicken Rice**, the only recipe that matches all criteria specified.

To illustrate, suppose you want to search for recipes with 'tags' **Christmas** and **home baked** that use the 'ingredient' **Ginger Root**, **Honey** and **Molasses**, you could use `filter recipe /tag christmas /ingredient ginger root /tag home baked /ingredient honey /ingredient molasses`:
<div style="text-align: center; padding-bottom: 2em">
<img src="images/ug/filter_recipe_1.png" width="75%" /> <br />
Figure 8.1: <i>The starting state of the application</i>
</div>

After executing the command, similar to the effect of **find recipe** command, the recipe list has changed, showing only the matching recipe, **gingerbread man**.

<div style="text-align: center; padding-bottom: 2em">
<img src="images/ug/filter_recipe_2.png" width="75%" /> <br />
Figure 8.2: <i>The recipe matching all criteria provided</i>
</div>

Again, to reset the search filter or go back to the full recipe view, you can click the Recipes button or run the `list recipes` command.








<a name="ListIngredientCommand"></a>
### 5.11. Listing Ingredients — **`list`**`ingredients`
This command shows a list of all recipes in ChopChop. As with the `list recipes` command, you can use this command to switch between panes without clicking, or to reset any filters.

**Usage**: `list ingredients`

<div markdown="span" class="alert alert-primary">
:bulb: **Tip:** For convenience, you can use either `list ingredients` or `list ingredient`.
</div>

<div style="text-align: center; padding-bottom: 2em">
<img src="images/ug/list_ingredient.png" width="75%" /> <br />
Figure 9: <i>The ingredient list view</i>
</div>





<a name="AddIngredientCommand"></a>
### 5.12. Adding Ingredients — **`add`**`ingredient`
This command adds an ingredient to ChopChop, with an optional quantity and expiry date:
- If the quantity is not specified, ChopChop will infer a counted quantity, like eggs.
- If the expiry date is not specified, it is assumed that the ingredient (eg. salt) does not expire.

As mentioned in the overview above, an ingredient can consist of multiple sets; the `add ingredient` command will intelligently *combine* ingredients as appropriate.

<div markdown="span" class="alert alert-primary">
:information_source: **Note:** Ingredients need to have compatible units in order to be combined; see [this section](#quantities-and-units) for how it works.
</div>

If the new ingredient has `/tag` options that are not present in the existing ingredient, then they are added as well.

**Usage**:
```
add ingredient <name>
  [/qty <quantity>]
  [/expiry <expiry-date>]
  [/tag <tag-name>]...
```

Examples:
- `add ingredient milk /qty 1l /expiry 2020-11-09` adds one litre of milk that expires on the 9th of November.
- `add ingredient egg /expiry 2020-12-25` adds one egg that expires on Christmas day.

Suppose you just finished a grocery run, and want to add the items to ChopChop. First, you have 2 cartons of milk:

<div style="text-align: center; padding-bottom: 2em">
<img src="images/ug/add_ingredient_1.png" width="75%" /> <br />
Figure 10.1: <i>Adding 2 litres of milk</i>
</div>

Since ChopChop did not know about 'milk' previously, a new ingredient entry is created for it:

<div style="text-align: center; padding-bottom: 2em">
<img src="images/ug/add_ingredient_2.png" width="75%" /> <br />
Figure 10.2: <i>The newly added milk ingredient</i>
</div>

Next, suppose you also bought 24 blueberries:

<div style="text-align: center; padding-bottom: 2em">
<img src="images/ug/add_ingredient_3.png" width="75%" /> <br />
Figure 10.3: <i>Adding 24 blueberries</i>
</div>

This time, since ChopChop already knew about blueberries, our previous 5 blueberries now become 29:

<div style="text-align: center; padding-bottom: 2em">
<img src="images/ug/add_ingredient_4.png" width="75%" /> <br />
Figure 10.4: <i>You now have 29 blueberries</i>
</div>

If you try to add an ingredient with incompatible quantities (for example, suppose you did not want to count the blueberries individually, and you only know that you bought a 400 gram box), ChopChop will display an error message, and not update the ingredient:

<div style="text-align: center; padding-bottom: 2em">
<img src="images/ug/add_ingredient_5.png" width="75%" /> <br />
Figure 10.5: <i>Ingredients must have compatible units to be combined</i>
</div>








<a name="DeleteIngredientCommand"></a>
### 5.13. Deleting Ingredients — **`delete`**`ingredient`

This command deletes a specific ingredient from ChopChop. Similar to the `add ingredient` command, this command also allows you to delete quantities of ingredients instead of the whole ingredient. In this scenario, ChopChop will intelligently remove the earliest-expiring ingredients first.

If `/qty` is not specified, then the behaviour of this command is to completely remove the ingredient from ChopChop. Worry not: if you accidentally delete something, you can always `undo` it.

<div markdown="span" class="alert alert-primary">
:information_source: **Note:** If specified, the quantity needs to have compatible units with the existing ingredient; see [this section](#quantities-and-units) for how it works.
</div>

**Usage**: `delete ingredient <#REF> [/qty <quantity>]`

Examples:
- `delete ingredient #4` <br />
	This deletes the fourth ingredient currently shown in the GUI's view.
- `delete ingredient milk /qty 500ml` <br />
	This removes 500ml of milk from ChopChop's inventory.

To illustrate, suppose that you poured yourself a glass of cold milk to drink, without making a recipe. To tell ChopChop that there is less milk in the fridge, you would use this command:

<div style="text-align: center; padding-bottom: 2em">
<img src="images/ug/delete_ingredient_1.png" width="75%" /> <br />
Figure 11.1: <i>Removing 250ml of milk</i>
</div>

Notice how the amount of milk decreased from 2 litres to 1.75 litres:

<div style="text-align: center; padding-bottom: 2em">
<img src="images/ug/delete_ingredient_2.png" width="75%" /> <br />
Figure 11.2: <i>You now only have 1.75 litres of milk left</i>
</div>











<a name="FindIngredientCommand"></a>
### 5.14. Finding Ingredients — **`find`**`ingredient`
This command finds all ingredients containing the given keywords in the name, and it works identically to the `find recipe` command [above](#FindRecipeCommand).

Constraints:
- At least one search keyword must be given

**Usage**: `find ingredient <keyword> [<keyword>]...`

For example, suppose you wanted to find all ingredients containing fish (not in the literal sense, but only in their name):

<div style="text-align: center; padding-bottom: 2em">
<img src="images/ug/find_ingredient_1.png" width="75%" /> <br />
Figure 12.1: <i>The complete ingredient list</i>
</div>

Now, only the matching ingredients are shown:

<div style="text-align: center; padding-bottom: 2em">
<img src="images/ug/find_ingredient_2.png" width="75%" /> <br />
Figure 12.2: <i>Only ingredients containing 'fish' in their name are shown</i>
</div>

Again, you can either click the Ingredients button, or use `list ingredients` to clear the search filter.








<a name="FilterIngredientCommand"></a>
### 5.15. Filtering Ingredients — **`filter`**`ingredient`
This command filters all ingredients and lists those that match all the tags and expiry dates specified in the command.

**Usage**: `filter ingredient [/expiry <expiry-date>] [/tag <tag-keywords>]...`

- Keywords following `/tag` do not have to be complete to match the 'tag' name.
- `/expiry <expiry-date>` filters the ingredients and only lists those that expire before the date provided.
- When there are multiple expiry dates specified,, only the earliest one will be considered.
- Except for the changes in the search fields, this feature works identically to the `filter recipe` command [above](#FilterRecipeCommand).

Constraints:
- At least one search term must be given, and they should be either `/expiry` or `/tag`.
- Search terms must not be empty.

Examples:
- `filter ingredient /tag bitter taste` will match **bitter melon** and **dark chocolate**, the only ingredients with the 'tag' **bitter taste**.
- `filter ingredient /tag frequently used /tag sweet` will match **sugar**, the only ingredient with the 'tag' **frequently used** and 'tag' **sweet**.
- `filter ingredient /expiry 2020-12-01` will match **apple**, the only 'ingredient' expiring before **2020-12-01**.
- `filter ingredient /expiry 2022-12-31 /expiry 2020-10-31 /expiry 2023-01-01` will match **chocolate**, the only ingredient expiring before **2020-10-31**.
- `filter ingredient /tag powdery /expiry 2020-12-31 /expiry 2020-12-01 /tag bakery` will match **baking soda**, the only ingredient that matches all criteria specified.

To illustrate, suppose you want to search for ingredients with 'tags' **all time** and **favourite**, and expire earlier than the 'expiry date' **2020-12-31**, you could use `filter ingredient /tag all time /expiry 2020-12-31 /expiry 2021-01-01 /tag favourite`:
<div style="text-align: center; padding-bottom: 2em">
<img src="images/ug/filter_ingredient_1.png" width="75%" /> <br />
Figure 13.1: <i>The starting state of the application</i>
</div>

After executing the command, similar to the effect of **filter recipe** command, the ingredient list has changed, showing only the matching ingredient, **apple**.

<div style="text-align: center; padding-bottom: 2em">
<img src="images/ug/filter_ingredient_2.png" width="75%" /> <br />
Figure 13.2: <i>The ingredient matching all criteria provided</i>
</div>

Again, to reset the search filter or go back to the full ingredient view, you can click the Ingredients button or run the `list ingredients` command:






<a name="UndoCommand"></a>
### 5.16. Undoing Commands — **`undo`**
Undoes the last undoable command. Undoable commands are commands that involve changes to recipes and ingredients stored in ChopChop.

**Usage**: `undo`





<a name="RedoCommand"></a>
### 5.17. Redoing Commands — **`redo`**
Redoes the last redoable command. All undoable commands (as described [above](#UndoCommand)) can be redone.

**Usage**: `redo`



<a name="StatsRecipeTopCommand"></a>
### 5.18. Listing top Recipes -- **`stats recipe top`**
Shows a list of recipes that were made the most. The list is sorted in descending order by the number of times it was made; the first recipe in the list is the recipe that was made the most number of times. The number of usages is calculated from based on current records. So, if you have just cleared your recipe usage records, 
you will see that all recipes were made 0 times.
Even after you delete a recipe is deleted, its past usages are still saved within ChopChop.

Usage: `stats recipe top`

Example:
Let's say you executed `make Singapore Sling` 2 times a day for the past 1 year. Today, you decided to delete the recipe for health reasons. If you enter `stats recipe most made`, you will still see it listed as one of the most made recipes.

<a name="StatsRecipeRecentCommand"></a>
### 5.19. Listing recently made Recipes— **`stats recipe recent`**
Shows a list of most recently made recipes. The list is arranged in descending chronological order; the recipe most recently made is the first item on the list. 
Even after the recipe is deleted, its past usages are still saved within ChopChop, and you will the recipe listed. However, if you have just cleared your recipe usage records, there will be no recipes shown. 

<a name="StatsRecipeMadeCommand"></a>
### 5.20. Listing Recipes made within a given time frame — **`stats recipe made`**
Shows a list of recipes that were made within the given time frame. The list is arranged in descending chronological order.

Even after the recipe is deleted, its past usages are still saved within ChopChop, and you will see the recipe listed. However, if you have just cleared your recipe usage records, there will be no recipes shown. 

**Usage**: `stats recipe [/before <DATE>] [/after <DATE>]`

<div markdown="span" class="alert alert-primary">
:bulb: **Tip:** If you are omit both `[/before DATE]` and `[/after DATE]`, ChopChop will show you a list of recipes that were made today from 00:00 hours to tomorrow 00:00 hours.
</div>

For example:
Let's say you executed `make Rojak` on 23:59 hours yesterday. If you enter `stats recipe` you will not see `Rojak` listed in the statistics box.

If you enter `stats recipe /before 2020-02-13` into the command box, all recipes made prior to 2020-02-13 will be listed in the Statistics box.

If you enter `stats recipe /after 2020-02-13` into the command box, all recipes made after 2020-02-13 will be listed in the Statistics box.

If you enter `stats recipe /before 2020-10-31 /after 2020-02-13` into the command box, all recipes made within the period of 2020-02-13 to 2020-10-31 will be listed in the Statistics box.

If you enter `stats recipe` into the command box without either `[/before <DATE>]` or `[/after <DATE>]`, all recipes made today be listed in the Statistics box. 

<div markdown="span" class="alert alert-primary">
:bulb: **Tip:** If you are only interested in what was cooked for dinner, you can specify the time period to the nearest minute. For example, `stats recipe /before 2020-02-13 20:30 /after 2020-02-13 18:30` will show a list of recipes made within this 2-hour period.
</div>

<a name="StatsIngredientRecentCommand"></a>
### 5.21. Clearing Recipe usage records -- **`stats recipe clear`**
After you execute this command, the records of recipes that were made are deleted from ChopChop. If you run other stats commands such as `stats recipe recent`, you will see "No results found" as all records are deleted.'

**Usage**: `stats recipe clear`

<a name="StatsIngredientRecentCommand"></a>
### 5.22. Listing recently used Ingredients— **`stats ingredient recent`**
Shows a list of ingredients that were used by recipes made recently. The list is arranged in descending chronological order.
Even after the ingredient is deleted, its past usages are still saved within ChopChop, and you will the ingredient listed. However, if you have just cleared your ingredient usage records, there will be no ingredients shown. 

**Usage**: `stats ingredient made`

<a name="StatsIngredientUsedCommand"></a>
### 5.23. Listing Ingredients used within a given time frame — **`stats ingredient used`**
Shows a list of ingredients that were used by recipes made recently within the given time frame.
Even after the ingredient is deleted, its past usages are still saved within ChopChop, and you will the ingredient listed. However, if you have just cleared your ingredient usage records, there will be no ingredients shown. 

**Usage**: Similar to the previous command [above](#StatsRecipeMadeCommand), the only difference is the keyword is now `stats ingredient` instead of `stats recipe`.

<a name="StatsIngredientClearCommand"></a>
### 5.24. Clearing Ingredient usage records -- **`stats ingredient clear`**
After you execute this command, the records of ingredients that were used are deleted from ChopChop. If you run other stats commands such as `stats ingredient recent`, you will see "No results found" as all records are deleted.'

**Usage**: `stats ingredient clear`

<a name="CommandSummary"></a>
### 5.25. Command Summary

For easy reference, here are the commands that ChopChop supports, listed in alphabetical order. You can click on the name of the command to go to its section in the User Guide.


| Command                                      | Description                                                                | Undoable |
|----------------------------------------------|----------------------------------------------------------------------------|----------|
| [add ingredient](#AddIngredientCommand)      | Adds a new ingredient, or increases the quantity of an existing ingredient | **YES**  |
| [add recipe](#AddRecipeCommand)              | Adds a new recipe                                                          | **YES**  |
| [delete ingredient](#DeleteIngredientCommand)| Completely deletes an ingredient, or removes some quantity of it           | **YES**  |
| [delete recipe](#DeleteRecipeCommand)        | Completely deletes a recipe                                                | **YES**  |
| [edit recipe](#EditRecipeCommand)            | Edits an existing recipe                                                   | **YES**  |
| [filter ingredient](#FilterIngredientCommand)| Searches for ingredients by one or more filtering criteria                 | **NO**   |
| [filter recipe](#FilterRecipeCommand)        | Searches for recipes by one or more filtering criteria                     | **NO**   |
| [find ingredient](#FindIngredientCommand)    | Searches for ingredients by their name                                     | **NO**   |
| [find recipe](#FindRecipeCommand)            | Searches for recipes by their name                                         | **NO**   |
| [help](#HelpCommand)                         | Shows help in general, or help for specific commands                       | **NO**   |
| [list ingredient](#ListIngredientCommand)    | Shows the main ingredient list, and clears any search filters              | **NO**   |
| [list recipe](#ListRecipeCommand)            | Shows the main recipe list, and clears any search filters                  | **NO**   |
| [make](#MakeRecipeCommand)                   | Makes a recipe, consuming ingredients and recording statistics             | **YES**  |
| [quit](#QuitCommand)                         | Exits ChopChop                                                             | **NO**   |
| [redo](#RedoCommand)                         | Redoes a command that was previously undone                                | **NO**   |
| [undo](#UndoCommand)                         | Undoes a command that was previously executed                              | **NO**   |
| [view](#ViewCommand)                         | Opens the detailed view for a recipe                                       | **NO**   |
| [stats recipe top] (#StatsRecipeTopCommand)  | Shows the top recipes                                                      | **NO**   |
| [stats recipe recent] (#StatsRecipeRecentCommand) | Shows the recently made recipes                                       | **NO**   |
| [stats recipe made] (#StatsRecipeMadeCommand)| Shows the recipes made within the given time frame                         | **NO**   |
| [stats recipe clear] (#ClearRecipeCommand)   | Clear recipe usage records                                                 | **YES**  |
| [stats ingredient recent] (#StatsIngredientRecentCommand) | Shows the recently used ingredients                           | **NO**   |
| [stats ingredient made] (#StatsIngredientUsedCommand) | Shows the ingredient used within the given time frame             | **NO**   |
| [stats ingredient clear] (#StatsIngredientClearCommand) | Clear ingredient usage records                                  | **YES**  |






-------------------------
## 6. Quantities and Units

In order to keep track of ingredients correctly, ChopChop needs to know about their amounts. Currently, there are 3 'kinds' of units supported; volume, mass (weight), and counts. These are the supported units specifically:

- `ml`, `mL` — millilitres
- `l`, `L` — litres (1000 ml)
- `cup`, `cups` — metric cup (250 ml)
- `tsp` — metric teaspoon (5 ml)
- `tbsp` — metric tablespoon (15 ml)
- `g` — gram
- `mg` — milligram (0.001 g)
- `kg` — kilogram (1000 g)

Additionally, quantities without a unit are assumed to be dimensionless 'counts'; for example, **3 eggs**.


### 6.1. Ingredient Combining

As mentioned above, ChopChop will combine ingredients when you `add` them, provided they have compatible units. Combining works as you would expect, and is rather flexible; adding `3 cups` of milk to an existing stock of `400ml` will yield `1.15l`.

However, you cannot, for example, add `300g` of eggs to `4` eggs, as grams and counts are incompatible units.






--------------------
<a name="TabCompletion"></a>
## 7. Tab Completion

Suppose you wanted to add a recipe for pancakes, and you wanted real, <i>industrial strength</i> pancakes (unlike the simplified recipe we've been using thus far); the list of ingredients would look something like this:

```
add recipe Pancakes
  /ingredient flour /qty 290g
  /ingredient egg /qty 1
  /ingredient sugar /qty 60g
  /ingredient baking powder /qty 4tsp
  /ingredient baking soda /qty 0.25tsp
  /ingredient salt /qty 3g
  /ingredient milk /qty 440ml
  /ingredient butter /qty 60g
  /ingredient vanilla extract /qty 2tsp
  /step ...
```

That certainly seems cumbersome to type out in full, so what if there was a way to speed it up drastically? You can, simply by pressing the <kbd>tab</kbd> key to let ChopChop &ldquo;fill-in-the-blanks&rdquo; for you!

### 7.1. Introduction to Tab Completion

Here's what you can do instead (where <kbd>tab</kbd> represents pressing the tab key):
<pre>
a <kbd>tab</kbd> r <kbd>tab</kbd> Pancakes
  /i <kbd>tab</kbd> f <kbd>tab</kbd> /q <kbd>tab</kbd> 290g
  /i <kbd>tab</kbd> e <kbd>tab</kbd> /q <kbd>tab</kbd> 1
  /i <kbd>tab</kbd> su <kbd>tab</kbd> /q <kbd>tab</kbd> 60g
  /i <kbd>tab</kbd> baking p <kbd>tab</kbd> /q <kbd>tab</kbd> 4tsp
  /i <kbd>tab</kbd> baking s <kbd>tab</kbd> /q <kbd>tab</kbd> 0.25tsp
  /i <kbd>tab</kbd> sa <kbd>tab</kbd> /q <kbd>tab</kbd> 3g
  /i <kbd>tab</kbd> m <kbd>tab</kbd> /q <kbd>tab</kbd> 440ml
  /i <kbd>tab</kbd> bu <kbd>tab</kbd> /q <kbd>tab</kbd> 60g
  /i <kbd>tab</kbd> v <kbd>tab</kbd> /q <kbd>tab</kbd> 2tsp
</pre>

At just 126 compared to 289 keystrokes, that's more than a 50% reduction! ChopChop will intelligently fill in commands, parameter names (eg. `/ingredient`), recipe names, ingredient names, and tag names.


### 7.2. Using Tab Completion

How does it work? ChopChop uses the current text when completing and searches for the <i>appropriate</i> matching items; it knows to look for ingredient names while within an `/ingredient` parameter, and to look for ingredient tags instead of recipe tags when in an `add ingredient` command.

<div markdown="span" class="alert alert-primary">
:information_source: **Note:** For tab completion to work, you must type at least one character before pressing <kdb>tab</kdb>. ChopChop cannot read your mind!
</div>

What if there are multiple items that share a prefix, for example <i>baking powder</i> and <i>baking soda</i> in the pancake recipe above? Worry not; pressing <kbd>tab</kbd> <i>repeatedly</i> will cycle through the available completions, and they are sorted lexicographically (length, followed by alphabetical order) — pressing <kbd>tab</kbd> after `/ingredient b` would give you <i>butter</i>, <i>baking powder</i>, and <i>baking soda</i>, in that order.

The same thing applies to commands; <code>f <kbd>tab</kbd></code> would cycle between `find` and `filter`.

--------------------
<a name="commandSummary"></a>
## 8. Command Summary
