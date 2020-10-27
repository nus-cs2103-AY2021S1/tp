---
layout: page
title: User Guide
---

Welcome to the Wishful Shrinking User Guide! This user guide provides in-depth documentation on the
 **installation process, step-by-step instructions** for each feature and **troubleshooting recommendations**. Wishful Shrinking is available for the Linux, Unix, Windows and Mac OS operating systems.<br><br>


## Introducing Wishful Shrinking
Wishful Shrinking is your desktop diet manager. It is an app that helps you **manage your on-hand ingredients
, organise personal recipes and track your diet**. Wishful Shrinking facilitates a **healthier diet** in three main
 ways: 
1. Provide a **source of healthy, customizable recipes** 
2. **Recommend recipes** to improve ease of home cooking 
3. **Track daily food and calorie** intake<br><br>

Wishful Shrinking targets **busy office workers** who tend to discount healthy eating. Office workers are also more
 familiar with desktop applications and typing and correspondingly, Wishful Shrinking is optimized for fast and efficient typers as it uses a Command Line Interface (CLI) with the added beauty of a Graphical User Interface (GUI).<br><br>

Choose a topic from the table of contents to get started on your **Wishful Shrinking journey**. 


* Table of Contents <a id="toc"></a>
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure your computer has Java `11` or above installed.

2. Download the latest `wishfulShrinking.jar` from [here](https://github.com/AY2021S1-CS2103T-W10-2/tp/releases).

3. Copy the file to an empty folder you want to use as the _home folder_.

4. **Double-click** the jar file to start the app OR start the app using **CLI** and type `java -jar wishfulShrinking.jar`.<br>
   The app should look similar to the one shown below:<br><br>
   <img src="images/Ui.png" width="550" height="300">
   <br><br><br>

5. The image below is a labeled diagram of each of Wishful Shrinking's components.<br><br>
   <img src="images/UiExplained.png" width="550" height="300">
   <br><br><br>
   
   Below is a brief explanation on each of the components:
   * **List of recipes/ingredients/consumption**: The left window will display either a list of recipes, a list of
    ingredients or the consumption list depending on your input. In the image above, it is displaying the Recipe
     List.
   * **Command Result**: The Command Result box will show the result of your input into the Command Box.
   * **Command Box**: Here is where you will type all your commands.

6. Type the command in the command box and press `Enter` to execute it.<br>
   Some example commands you can try:

   * **`recipes`** : Lists all recipes.

   * **`addR`**` n/salad i/lettuce, carrots, olive oil c/40 instr/Prepare the ingredients. Toss the ingredients together. Serve. img/https://www.onceuponachef.com/images/2019/07/Big-Italian-Salad.jpg t/yummy t/healthy` : Adds a `salad` recipe to Wishful Shrinking.

   * **`deleteR`**`3` : Deletes the 3rd recipe shown in the current recipe list.

   * **`exit`** : Exits the app.
   
   * **`help`** : Opens the help window.

7. Refer to the [Features](#features) below for details of each command.
<br><br>

--------------------------------------------------------------------------------------------------------------------

# Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* All prefixes must be preceded by a space.<br>
   e.g ` t/` or ` i/`

* All commands are **case-sensitive**.<br>
  e.g. in `addR`, `add` is in small letters while `R` is in capital letters.

* Words in `UPPER_CASE` are the parameters to be **supplied by you**.<br>
  e.g. in `addR n/NAME`, `NAME` is a parameter which can be used as `addR n/salad`.

* Items in **square brackets are optional**.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/Salad` or as `n/Salad t/healthy`.

* Items with `…` after means that there can be **more than 1** item. It is necessary to specify the prefix
 before every item.<br>
  e.g. `[t/TAG]…` can be used as `t/healthy` or `t/healthy t/low calories`.

* Items in the more format `[<character> MORE ITEM]` means there can be **more than 1** item, separated by the
 specified character. It is not necessary to specify the prefix before each item. <br> 
 e.g `instr/INSTRUCTION[. MORE INSTRUCTION]` can be used as `instr/Open baked beans and serve` or `instr/Boil pasta
 . Fry pasta with meat sauce. Serve meat pasta.`.
 e.g `n/[ MORE NAMES]` can be used as `n/salad` or `n/salad pie`.

* `INDEX` **must be a positive integer** e.g. 1, 2, 3...<br>
  e.g. the `INDEX` in `deleteR INDEX` and `editF INDEX` must be a positive integer that is present in the
   corresponding lists e.g. `deleteR 1` `editF 2`.

* Parameters can be in **any order**.<br>
  e.g. if the command specifies `n/NAME i/INGREDIENTS`, `i/INGREDIENTS n/NAME` is also acceptable.
</div>
<br><br>


## Recipe-related Commands

The Recipe-related commands include [`addR`](#add-recipe), [`recipes`](#list-recipe), [`deleteR`](#delete-recipe
), [`editR`](#edit-recipe), [`selectR`](#select-recipe), [`close`](#close-recipe), [`searchR`](#search-recipe), 
[`recommend`](#recommend-recipe) and [`clearR`](#clear-recipe). These are the commands in Wishful Shrinking that
are relevant only to Recipes.
<br><br><br>

### Adding a recipe: `addR` <a id="add-recipe"></a>

Adds a recipe to Recipe List.

<br>  The image below is what Wishful Shrinking looks like after you have added a recipe. <br><br>
<img src="images/AddRecipeImage.png" width="550" height="300">
<br><br><br>

Format: `addR n/NAME i/INGREDIENT [ -QUANTITY][, MORE INGREDIENTS [ -QUANTITY]] c/CALORIES [img/IMAGE] inst
/INSTRUCTION[. MORE INSTRUCTIONS] [t/TAG]...`

* `INGREDIENT` can take in an optional `Quantity` e.g. i/Tomato -2 whole.
<div markdown="span" class="alert alert-primary">:memo: **Note:**
`QUANTITY` is separated with a mandatory space before `-` and after the dash, only accepts alphanumeric characters
, forward slashes and full stops.
</div>
<div markdown="span" class="alert alert-primary">:memo: **Note:**
Multiple ingredients are separated by `,`.
</div> 

* `CALORIES` **must be a positive integer** e.g. 150, 200...
* `IMAGE` can be local path e.g. images/healthy1.jpg or url e.g. https://vaya.in/recipes/wp-content/uploads/2018/06/Club-sandwich.jpg
    * If you don't input the image field, Wishful Shrinking will add a default image to the recipe.
    * If you want to use an image that has been saved in your computer, you have to specify the **absolute path** of the image, and add file:// in front of the file path. Then, replace IMAGE in img/IMAGE with the file path of your image, e.g. img/file:///D:/myimages/wishful/data/myimage.png.
    * If you want to use a url as the image, make sure that you are connected to the internet on your computer, and that the url is the **image address** (starting with https:// and ending with .jpg/jpeg/png). The image will only be downloaded into Wishful Shrinking's data folder if there is internet connection.
    * If you input an invalid file path, input an invalid url, or input a url while there is no internet,  Wishful Shrinking will replace the image with a default image. 
    * Wishful Shrinking also has a few sample images for you to use if you don't have any images on hand. Below is the table of sample images and their file paths. To use the sample images, replace IMAGE in img/IMAGE with the file path, e.g. img/images/healthy1.jpg.<br>
   
      Image | File Path | Image | File Path
      --------|------------------|---------|----------------
      <img src="../src/main/resources/images/healthy1.jpg" width="100" height="100"> | images/healthy1.jpg | <img src="../src/main/resources/images/healthy4.jpg" width="100" height="100"> | images/healthy4.jpg
      <img src="../src/main/resources/images/healthy2.jpg" width="100" height="100"> | images/healthy2.jpg | <img src="../src/main/resources/images/healthy5.jpg" width="100" height="100"> | images/healthy5.jpg
      <img src="../src/main/resources/images/healthy3.jpg" width="100" height="100"> | images/healthy3.jpg | <img src="../src/main/resources/images/healthy6.jpg" width="100" height="100"> | images/healthy6.jpg
   
* `INSTRUCTION` will take in a series of instruction text and Wishful Shrinking will automatically separate each
 step of the instruction based on the end of a sentence, indicated by a `.`.
<div markdown="span" class="alert alert-success">:bulb: **Tip:**
   Wishful Shrinking numbers each instruction step after separating the input instruction by the position(s) of
    `.`.
</div> 

Examples:
* `addR n/salad i/lettuce, tomato, olive oil c/40 img/images/healthy1.jpg instr/Cook pasta. Serve immediately. t/fast t/easy`
* `addR n/sandwiches i/breads, cheese -2 slices c/80 img/https://vaya.in/recipes/wp-content/uploads/2018/06/Club-sandwich.jpg instr/Cook. Eat.`
<br><br><br>

### Listing all recipes : `recipes` <a id="list-recipe"></a>

Shows a list of all recipes in the Recipe List.

<br>  The image below is what Wishful Shrinking looks like after you have listed all recipes. <br><br>
<img src="images/ListRecipesImage.png" width="550" height="300">
<br><br><br>

Format: `recipes`
<br><br><br>

### Deleting a recipe : `deleteR` <a id="delete-recipe"></a>

Deletes the specified recipe from the Recipe List.

<br>  The image below is what Wishful Shrinking looks like after you have deleted a recipe. <br><br>
<img src="images/DeleteRecipeImage.png" width="550" height="300">
<br><br><br>

Format: `deleteR INDEX`

* Deletes the recipe at the specified `INDEX`.
* The index refers to the index number shown in the displayed Recipe List.
<div markdown="span" class="alert alert-primary">:memo: **Note:**
    Deleting a recipe **will not** affect the recipes that have been eaten in the consumption list.
</div>

Examples:
* `recipes` followed by `deleteR 2` deletes the 2nd recipe in Recipe List.
* `searchR n/salad` followed by `deleteR 1` deletes the 1st recipe in the result of the `searchR` command.
<br><br><br>

### Editing a recipe: `editR` <a id="edit-recipe"></a>

Edits the specified recipe from the Recipe List.

<br>  The image below is what Wishful Shrinking looks like after you have edited a recipe. <br><br>
<img src="images/EditRecipeImage.png" width="550" height="300">
<br><br><br>

Format: `editR INDEX [n/NAME] [i/INGREDIENT [ -QUANTITY][, MORE INGREDIENTS [ -QUANTITY]]] [c/CALORIES] [img/IMAGE] 
[inst/INSTRUCTION[. MORE INSTRUCTIONS]] [t/TAG]...`

* Edits the recipe at the specified `INDEX`.
* The index refers to the index number shown in the displayed Recipe List.
* `INGREDIENT` can take in an optional `Quantity` e.g. i/Tomato -2 whole.
<div markdown="span" class="alert alert-primary">:memo: **Note:**
`QUANTITY` is separated with a mandatory space before `-` and after the dash, only accepts alphanumeric characters
, forward slashes and full stops.
</div>
<div markdown="span" class="alert alert-primary">:memo: **Note:**
Multiple ingredients are separated by `,`.
</div> 

* `CALORIES` **must be a positive integer** e.g. 150, 200...
* `IMAGE` can be local path e.g. images/healthy1.jpg or url e.g. https://vaya.in/recipes/wp-content/uploads/2018/06/Club-sandwich.jpg
    * If you don't input the image field, Wishful Shrinking will add a default image to the recipe.
    * If you want to use an image that has been saved in your computer, you have to specify the **absolute path** of the image, and add file:// in front of the file path. Then, replace IMAGE in img/IMAGE with the file path of your image, e.g. img/file:///D:/myimages/wishful/data/myimage.png.
    * If you want to use a url as the image, make sure that you are connected to the internet on your computer, and that the url is the **image address** (starting with https:// and ending with .jpg/jpeg/png). The image will only be downloaded into Wishful Shrinking's data folder if there is internet connection.
    * If you input an invalid file path, input an invalid url, or input a url while there is no internet,  Wishful Shrinking will replace the image with a default image. 
<div markdown="span" class="alert alert-primary">:memo: **Note:**
Multiple ingredients are separated by `,`.
</div> 
* All fields are optional, but **at least** the recipe index and one of the fields must be present to edit
     a recipe.
* You are not allowed to edit a recipe into an already existing recipe in the Recipe List.
<div markdown="span" class="alert alert-primary">:memo: **Note:**
    Modifying a recipe **will not** affect the recipes that have been eaten in the consumption list.
</div>
<div markdown="span" class="alert alert-success">:bulb: **Tip:**
    Specified fields will permanently override existing values with the new values- the edit is not cumulative. Typing `editR INDEX` and then hitting `Enter` will insert the information of the recipe at the specified `INDEX` into the command box, letting you directly modify the existing recipe.
</div> 

Examples:
* `editR 2 n/Apple salad i/apple` will update the name of the 2nd recipe in the displayed Recipe List to Apple
 salad and the ingredients to contain an apple ingredient.
<br><br><br>

### Getting a recipe to edit: `editR` <a id="get-edit-recipe"></a>

Insert the information of the specified recipe to the edit into the command box for editing purposes.

<br>  The image below is what Wishful Shrinking looks like after you have gotten a recipe to edit. <br><br>
<img src="images/EditRecipeGetImage.png" width="550" height="300">
<br><br><br>

Format: `editR INDEX`

* Gets the information of the recipe at the specified `INDEX` and adds it behind the edit recipe command in the command box.
* The index refers to the index number shown in the displayed Recipe List.

Examples:
* `editR 2` followed by `Enter` will insert the information of the 2nd recipe in the displayed Recipe List into the
 command box.
<br><br><br>

### Selecting a single recipe : `selectR` <a id="select-recipe"></a>

Displays the information of a single recipe in full view.

<br>  The image below is what Wishful Shrinking looks like after you have selected a specific recipe. <br><br>
<img src="images/SelectRecipeImage.png" width="550" height="300">
<br><br><br>

Format: `selectR INDEX`

* Selects the recipe at the specified `INDEX` to show its full information.
* The index refers to the index number shown in the displayed Recipe List.

Examples:
* `selectR 1` shows the 1st recipe in full view in the left drawer.
<br><br><br>

### Closing the recipe drawer : `close` <a id="close-recipe"></a>

Closes the left drawer if it has been opened by the select command.

<br>  The image below is what Wishful Shrinking looks like after you have closed the left drawer. It should look similar to before you selected a recipe. <br><br>
<img src="images/CloseRecipeImage.png" width="550" height="300">
<br><br><br>

Format: `close`

* This command closes the left drawer that is opened after you select a recipe.
* Nothing will happen if you use this command when the left drawer is not open.
<br><br><br>

### Searching for a recipe: `searchR` <a id="search-recipe"></a>

Finds recipes in the Recipe List that contain all the specified ingredient(s), name or tag(s).

<br>  The image below is what Wishful Shrinking looks like after you have searched for a recipe. In this case, the recipe is being searched by ingredients. <br><br>
<img src="images/SearchRecipeImage.png" width="550" height="300">
<br><br><br>

Format: `searchR [i/INGREDIENT [ MORE INGREDIENTS]] [n/NAME] [t/TAG [ MORE TAGS]]`

* The search is case-insensitive. e.g `salad` will match `Salad`.
* The order of the keywords does not matter. e.g. Ham Salad will match Salad with Ham.
* All fields are optional, but **at least one** of the fields must be present to search by recipe ingredient(s
), recipe name or recipe tag(s).
<div markdown="span" class="alert alert-primary">:memo: **Note:**
    Multiple ingredients and tags are separated by a space and not a comma
</div>

Examples:
* `searchR i/lettuce tomato` returns `salad` that has both ingredients `lettuce` and `tomato`.
* `searchR n/salad` returns `salad` and `ham salad`.
* `searchR t/healthy` returns `salad` with tag `healthy`.
<br><br><br>

### Recommending recipes : `recommend` <a id="recommend-recipe"></a>

Shows a list of all recipes in the Recipe List that can be made with the ingredients in your Fridge.

<br>  The image below is what Wishful Shrinking looks like after you have gotten the recommended recipes. In
 this case, `recommend` returns the recipe `salad` with ingredients `lettuce`, `olive oil` and `tomato` since
  the example user has all the ingredients: `lettuce`, `olive oil` and `tomato` in their Fridge.<br><br>
<img src="images/RecommendImage.png" width="550" height="300">
<br><br><br>

Format: `recommend`

* Recipes are only recommended if you your Fridge contains all of a recipe ingredients.
<div markdown="span" class="alert alert-primary">:memo: **Note:**
    Ingredient quantity is not taken into account when determining whether an ingredient is present in your Fridge
</div>

Examples:
* `recommend` returns the recipe `salad` with ingredients `lettuce`, `onion` and `tomato` **only if** you have
 all `lettuce`, `onion` and `tomato` in your Fridge.
<br><br><br>


### Clearing all recipes : `clearR` <a id="clear-recipe"></a>

Clears all the recipes in the Recipe List.

<br>  The image below is what Wishful Shrinking looks like after you have cleared all recipes. <br><br>
<img src="images/ClearRecipeImage.png" width="550" height="300">
<br><br><br>

Format: `clearR`
<br><br><br>

## Fridge-related Commands

The Fridge-related commands include [`addF`](#add-ingredient), [`Fridge`](#list-ingredient), [`deleteF`](#delete-ingredient), 
[`editF`](#edit-ingredient), [`searchF`](#search-ingredient) and [`clearF`](#clear-ingredient). These are the
 commands in Wishful Shrinking that are relevant only to the Fridge.
<br><br><br>

### Adding an ingredient: `addF` <a id="add-ingredient"></a>

Adds an ingredient to the Fridge.

<br>  The image below is what Wishful Shrinking looks like after you have added an ingredient to the Fridge. <br
><br>
<img src="images/AddIngredientImage.png" width="550" height="300">
<br><br><br>

Format: `addF i/INGREDIENT [ -QUANTITY][, MORE INGREDIENTS [ -QUANTITY]]`

* `INGREDIENT` can take in an optional `Quantity` e.g. i/Tomato -2 whole.
<div markdown="span" class="alert alert-primary">:memo: **Note:**
`QUANTITY` is separated with a mandatory space before `-` and after, only accepts alphanumeric characters, forward
 slashes and full stops.
</div>
<div markdown="span" class="alert alert-primary">:memo: **Note:**
Multiple ingredients are separated by `,`.
</div> 

Examples:
* `addF i/peanut`
* `addF i/tomato -1 kg`
* `addF i/banana -3/4 cups, green peas -200g, salmon fish`
<br><br><br>

### Listing all ingredients : `fridge` <a id="list-ingredient"></a>

Shows a list of all ingredients in the Fridge.

<br>  The image below is what Wishful Shrinking looks like after you have listed all ingredients in the Fridge. <br
><br>
<img src="images/ListIngredientsImage.png" width="550" height="300">
<br><br><br>

Format: `fridge`
<br><br><br>

### Deleting an ingredient : `deleteF` <a id="delete-ingredient"></a>

Deletes the specified ingredient from the Fridge.

<br>  The image below is what Wishful Shrinking looks like after you have deleted an ingredient from the Fridge. <br><br>
<img src="images/DeleteIngredientImage.png" width="550" height="300">
<br><br><br>

Format: `deleteF INDEX`

* Deletes the ingredient at the specified `INDEX`.
* The index refers to the index number shown in the displayed Ingredient List.

Examples:
* `fridge` followed by `deleteF 4` deletes the 4th ingredient in the Fridge.
* `searchF peanut` followed by `deleteF 1` deletes the 1st ingredient in the results of the `searchF` command.
<br><br><br>

### Editing an ingredient: `editF` <a id="edit-ingredient"></a>

Edits the specified ingredient from Fridge.

<br>  The image below is what Wishful Shrinking looks like after you have edited an ingredient in the Fridge. <br
><br>
<img src="images/EditIngredientImage.png" width="550" height="300">
<br><br><br>

Format: `editF INDEX i/INGREDIENT [ -QUANTITY]`

* Edits the ingredient at the specified `INDEX`.
* The index refers to the index number shown in the displayed Ingredient List.
* `INGREDIENT` can take in an optional `Quantity` e.g. i/Tomato -2 whole.
<div markdown="span" class="alert alert-primary">:memo: **Note:**
`QUANTITY` is separated with a mandatory space before `-` and after, only accepts alphanumeric characters, forward
 slashes and full stops.
</div>
<div markdown="span" class="alert alert-primary">:memo: **Note:**
Multiple ingredients are separated by `,`.
</div> 

* All fields are optional, but **at least** the ingredient index and one of the fields must be present to edit
 an ingredient.
* You are not allowed to edit an ingredient into an already existing ingredient in the Fridge.
<div markdown="span" class="alert alert-success">:bulb: **Tip:**
    Specified fields will permanently override existing values with the new values- the edit is not cumulative. Typing `editF INDEX` and then hitting `Enter` will insert the information of the ingredient at the specified `INDEX` into the command box, letting you directly modify the existing ingredient.
</div> 

Examples:
* `editF 2 i/apple` will update the value of the 2nd recipe in the displayed Ingredient List to apple.
<br><br><br>

### Getting an ingredient to edit: `editF` <a id="get-edit-ingredient"></a>

Adds the information of the specified ingredient to the edit command into the command box for editing purposes.

<br>  The image below is what Wishful Shrinking looks like after you have gotten an ingredient in the Fridge to
 edit
. <br><br>
<img src="images/EditIngredientGetImage.png" width="550" height="300">
<br><br><br>

Format: `editF INDEX`

* Gets the information of the ingredient at the specified `INDEX` and adds it behind the edit ingredient command in the command box.
* The index refers to the index number shown in the displayed Ingredient List.

Examples:
* `editF 1` followed by `Enter` will insert the information of the 1st ingredient in the displayed Ingredient List
 into
 the
 command box.
<br><br><br>

### Searching for an Ingredient: `searchF` <a id="search-ingredient"></a>

Finds ingredients in the Fridge that contain any of the given keywords.

<br>  The image below is what Wishful Shrinking looks like after you have searched for ingredients in the Fridge
. <br
><br>
<img src="images/SearchIngredientImage.png" width="550" height="300">
<br><br><br>

Format: `searchF KEYWORD [ MORE KEYWORDS]`

* Input keywords are only compared against the ingredient name.
* The search is case-insensitive. e.g `peanut` will match `Peanut`.
* The order of the keywords does not matter. e.g. Peanut Butter will match Butter with Peanut.
<div markdown="span" class="alert alert-primary">:memo: **Note:**
    Ingredient quantity is not taken into account when determining whether two ingredients matches
</div>
<div markdown="span" class="alert alert-primary">:memo: **Note:**
Multiple kewyords are separated by a space.
</div> 

Examples:
* `searchF peanut` returns `peanut` and `peanut butter`.
<br><br><br>

### Clearing all ingredients : `clearF` <a id="clear-ingredient"></a>

Clears all the ingredients in the Fridge.

<br>  The image below is what Wishful Shrinking looks like after you have cleared all ingredients in the Fridge
. <br
><br>
<img src="images/ClearFridgeImage.png" width="550" height="300">
<br><br><br>

Format: `clearF`
<br><br><br>

## Consumption-related Commands

The Consumption-related commands include [`eatR`](#eat-consumption), [`calories`](#list-consumption), [`deleteC`](#delete-consumption), 
and [`clearC`](#clear-consumption). These are the commands in Wishful Shrinking that are relevant only to the
 Consumption list.
<br><br><br>

### Eating a recipe : `eatR` <a id="eat-consumption"></a>

Adds the specified recipe in the Recipe List into the Consumption list.

<br>  The image below is what Wishful Shrinking looks like after you have eaten a recipe. <br><br>
<img src="images/EatRecipeImage.png" width="550" height="300">
<br><br><br>

Format: `eatR INDEX`

* Adds the recipe at the specified `INDEX` into the Consumption list.
* The index refers to the index number shown in the displayed Recipe List.

Examples:
* `recipes` followed by `eatR 2` adds the 2nd recipe in the displayed Recipe List into the Consumption list.
* `searchR n/salad` followed by `eatR 1` adds the 1st recipe in the results of the `searchR` command into the Consumption list.
<br><br><br>

### Listing all recipes eaten : `calories` <a id="list-consumption"></a>

Shows the list of recipes that you have eaten, including the recipe's name and calorie. The total calories
 consumed so far is displayed in the command result.
<br>  The image below is what Wishful Shrinking looks like after you have listed all recipes that you have eaten. <br><br>
<img src="images/CaloriesImage.png" width="550" height="300">
<br><br><br>

Format: `calories`

<div markdown="span" class="alert alert-primary">:memo: **Note:**
    Deleting a recipe **will not** affect the recipes that have been eaten in the consumption list.
</div>

<br><br><br>

### Deleting a recipe eaten: `deleteC` <a id="delete-consumption"></a>

Deletes the specified recipe from consumption list.

<br>  The image below is what Wishful Shrinking looks like after you have deleted a recipe from the consumption list. <br><br>
<img src="images/DeleteCalorieImage.png" width="550" height="300">
<br><br><br>

Format: `deleteC INDEX`

* Deletes the recipe at the specified `INDEX`.
* The index refers to the index number shown in the displayed consumption list.

Examples:
* `calories` followed by `deleteC 2` deletes the 2nd recipe in the Consumption List.
<br><br><br>

### Clearing all consumed recipes : `clearC` <a id="clear-consumption"></a>

Clears all the recipes that have been eaten in the consumption list.

<br>  The image below is what Wishful Shrinking looks like after you have cleared all recipes eaten from the consumption list. <br><br>
<img src="images/ClearConsumptionImage.png" width="550" height="300">
<br><br><br>

Format: `clearC`
<br><br><br>

## Miscellaneous Commands

The miscellaneous commands include `help` and `exit`. They are the commands that you can use in Wishful Shrinking
 that are of a separate implementation from the Recipes, Fridge and Consumption-related commands.
<br><br><br>

### Viewing help : `help`

Shows a message explaining how to access the help page.

<br>The image below is Wishful Shrinking's help window. <br><br>
![help message](images/helpMessage.png)
<br><br><br>

Format: `help`
<br><br><br>

### Exiting the program : `exit`

Exits the program.

Format: `exit`
<br><br><br>


## Saving the data

Wishful Shrinking's data is saved in the hard disk automatically after any command that changes the data. There is no need to save manually.
<br><br>

[Back to table of contents](#toc)

--------------------------------------------------------------------------------------------------------------------

# FAQ

**Q**: Why is my data not saved?<br>
**A**: Remember to copy the jar file to an EMPTY folder before starting the app.
<br>
<br>
**Q**: Why is the app not running?<br>
**A**: Ensure JDK `11` or above is installed. 
<br>
<br>
**Q**: Why can't I add/edit images?<br>
**A**: Refer to the section on image path restrictions under [add recipe.](#add-recipe)

--------------------------------------------------------------------------------------------------------------------

# Glossary

Term | Explanation
--------|------------------
**Wishful Shrinking** | Can refer to name of the application as a whole or to the app’s storage file.<br>
**Fridge** | A personalised storage that contains all the ingredients that you have.<br>
**Recipe** | A set of cooking instructions that describes how to prepare a meal and the ingredients required.<br>
**Ingredient** | Food that can be used to prepare a particular dish according to a recipe.<br>
**Consumption** | A tracker which calculates and displays your calorie intake based on the recipes you have consumed as well as a list of recipes consumed.<br>
**Drawer** | Page that layers above left section of the window when the `selectR` command runs and closes when the `close` command runs.
**CLI** | A Command Line Interface (CLI) is a text-based user interface that allows users to type text commands instructing the program to do specific tasks.
**GUI** | A Graphical User Interface (GUI) is a form of user interface that allows users to interact with the program through graphical icons instead of text-based user interfaces.

--------------------------------------------------------------------------------------------------------------------

# Command summary

Features | Format, Examples
--------|------------------
**Add recipe** | `addR n/NAME i/INGREDIENT [ -QUANTITY][, MORE INGREDIENTS [ -QUANTITY]] c/CALORIES [img/IMAGE] inst/INSTRUCTION[. MORE INSTRUCTIONS] [t/TAG]...` <br> e.g. `addR n/salad i/lettuce, tomato, olive oil c/40 img/images/healthy1.jpg instr/Cook. Eat. t/fast t/easy`
**List recipes** | `recipes`
**Delete recipe** | `deleteR INDEX`<br> e.g. `deleteR 3`
**Edit recipe** | `editR INDEX [n/NAME] [i/INGREDIENT [ -QUANTITY][, MORE INGREDIENTS [ -QUANTITY]]] [c/CALORIES] [img/IMAGE] [inst/INSTRUCTION[. MORE INSTRUCTIONS]] [t/TAG]...` <br> e.g. `editR 2 n/Apple salad i/apple`
**Get edit recipe info** | `editR INDEX` <br> e.g. `editR 2`
**Select recipe**| `selectR INDEX` <br> e.g. `selectR 3`
**Close recipe drawer**| `close`
**Search for recipe** | `searchR [i/INGREDIENT [MORE_INGREDIENT]] [n/NAME [MORE_NAME]] [t/TAG [MORE_TAG]]` <br> e.g. `searchR i/lettuce tomato`, `searchR n/salad`, `searchR t/healthy`
**Recommend recipe** | `recommend`
**Clear all recipes** | `clearR`
**Add ingredient to the Fridge** | `addF i/INGREDIENT [ -QUANTITY][, MORE INGREDIENTS [ -QUANTITY]]` <br> e.g. `addF i/banana -3/4 cups, green peas -200g, salmon fish`
**List ingredients in the Fridge** | `fridge`
**Delete ingredient from the Fridge** | `deleteF INDEX`<br> e.g. `deleteF 3`
**Edit ingredient in the Fridge** | `editF INDEX i/INGREDIENTS [ -QUANTITY]` <br> e.g. `editF 3 i/apple sauce -20.0g`
**Get edit ingredient info** | `editF INDEX` <br> e.g. `editF 2`
**Search for ingredient in the Fridge** | `searchF KEYWORD [MORE_KEYWORDS]` <br> e.g. `searchF avocado`
**Clear all ingredients from the Fridge** | `clearF`
**Eat recipe**| `eatR INDEX` <br> e.g. `eatR 3`
**List recipes eaten** | `calories`
**Delete recipe eaten**| `deleteC INDEX` <br> e.g. `deleteC 3`
**Clear all consumed recipes** | `clearC`
**Help** | `help`
**Exit** | `exit`
