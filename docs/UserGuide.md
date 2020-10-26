---
layout: page
title: User Guide
---

Welcome to the Wishful Shrinking User Guide! This user guide provides in-depth documentation on the installation process, step-by-step instructions for each feature and troubleshooting recommendations. Wishful Shrinking is available for the Linux, Unix, Windows and Mac OS operating systems.<br><br>


## Introducing Wishful Shrinking
Wishful Shrinking is your desktop diet manager. It is an app that helps you manage your on-hand ingredients, organise personal recipes and track your diet. Wishful Shrinking facilitates a healthier diet in three main ways: 
1. Provide a source of healthy, customizable recipes 
2. Recommend recipes to improve ease of home cooking 
3. Track daily food and calorie intake<br><br>

Wishful Shrinking targets busy office workers who tend to discount healthy eating. Office workers are also more familiar with desktop applications and typing and correspondingly, Wishful Shrinking is optimized for fast and efficient typers as it uses a Command Line Interface (CLI) with the added beauty of a Graphical User Interface (GUI).<br><br>

Choose a topic from the table of contents to get started on your Wishful Shrinking journey. 


* Table of Contents
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
   * List of recipes/ingredients/consumption: The left window will display either a list of recipes, a list of ingredients or the consumption list depending on your input. In the image above, it is displaying the recipe list.
   * Command Result: The Command Result box will show the result of your input into the Command Box.
   * Command Box: Here is where you will type all your commands.

6. Type the command in the command box and press `Enter` to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`recipes`** : Lists all recipes.

   * **`addR`**` n/salad i/lettuce, carrots, olive oil c/40 instr/Prepare the ingredients. Toss the ingredients together. Serve. img/https://www.onceuponachef.com/images/2019/07/Big-Italian-Salad.jpg t/yummy t/healthy` : Adds a `salad` recipe to Wishful Shrinking.

   * **`deleteR`**`3` : Deletes the 3rd recipe shown in the current list.

   * **`exit`** : Exits the app.

7. Refer to the [Features](#features) below for details of each command.
<br><br>

--------------------------------------------------------------------------------------------------------------------

# Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* All commands are case-sensitive.<br>
  e.g. in `addR`, `add` is in small letters while `R` is in capital letters.

* Words in `UPPER_CASE` are the parameters to be supplied by you.<br>
  e.g. in `addR n/NAME`, `NAME` is a parameter which can be used as `addR n/salad`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/Salad t/healthy` or as `n/Salad`.

* Items with `…`​ after them can be used more than 1 times.<br>
  e.g. `[t/TAG]…​` can be used as `t/healthy` or `t/healthy t/low calories`.

* `INDEX` **must be a positive integer** e.g. 1, 2, 3...<br>
  e.g. the `INDEX` in `deleteR INDEX` and `editF INDEX` must be a positive integer that is present in the corresponding lists e.g. `deleteR 1` `editF 2`

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME i/INGREDIENTS`, `i/INGREDIENTS n/NAME` is also acceptable.
</div>
<br><br>


## Recipe-related Commands

The Recipe-related commands include `addR`, `recipes`, `deleteR`, `editR`, `selectR`, `close`, `searchR`, `recommend` and `clearR`. These are the commands in Wishful Shrinking that are relevant only to the Recipes.
<br><br><br>

### Adding a recipe: `addR`

Adds a recipe to Recipe list.

<br>  The image below is what Wishful Shrinking looks like after you have added a recipe. <br><br>
<img src="images/AddRecipeImage.png" width="550" height="300">
<br><br><br>

Format: `addR n/NAME i/INGREDIENT [ -QUANTITY][, MORE INGREDIENTS [ -QUANTITY]] c/CALORIES [img/IMAGE] inst/INSTRUCTION... [t/TAG]...`

* `INGREDIENT` can take in an optional `Quantity` e.g. i/Tomato -2 whole.
    * Note: there is a mandatory space before `-`.
    * Note: `QUANTITY` only accepts alphanumeric characters, forward slashes and full stops.
* `CALORIES` **must be a positive integer** e.g. 100, 200, 300...
* `IMAGE` can be local path e.g. images/healthy1.jpg or url e.g. https://vaya.in/recipes/wp-content/uploads/2018/06/Club-sandwich.jpg
   * Note: If you don't input the image field, Wishful Shrinking will add a default image to the recipe.
   * Note: If you want to use an image that has been saved in your computer, you have to specify the **absolute path** of the image, and add file:// in front of the file path. Then, replace IMAGE in img/IMAGE with the file path of your image, e.g. img/file:///D:/myimages/wishful/data/myimage.png.
   * Note: If you want to use a url as the image, make sure that you are connected to the internet on your computer, and that the url is the **image address** (starting with https:// and ending with .jpg/jpeg/png). The image will only be downloaded into Wishful Shrinking's data folder if there is internet connection.
   * Note: If you input an invalid file path, input an invalid url, or input a url while there is no internet,  Wishful Shrinking will replace the image with a default image. 
   * Wishful Shrinking also has a few sample images for you to use if you don't have any images on hand. Below is the table of sample images and their file paths. To use the sample images, replace IMAGE in img/IMAGE with the file path, e.g. img/images/healthy1.jpg.<br>
   
      Image | File Path | Image | File Path
      --------|------------------|---------|----------------
      <img src="../src/main/resources/images/healthy1.jpg" width="100" height="100"> | images/healthy1.jpg | <img src="../src/main/resources/images/healthy4.jpg" width="100" height="100"> | images/healthy4.jpg
      <img src="../src/main/resources/images/healthy2.jpg" width="100" height="100"> | images/healthy2.jpg | <img src="../src/main/resources/images/healthy5.jpg" width="100" height="100"> | images/healthy5.jpg
      <img src="../src/main/resources/images/healthy3.jpg" width="100" height="100"> | images/healthy3.jpg | <img src="../src/main/resources/images/healthy6.jpg" width="100" height="100"> | images/healthy6.jpg
   
* `INSTRUCTION` will take in a series of instruction text and Wishful Shrinking will automatically separate each step of the instruction based on the end of a sentence, indicated by a `.` e.g. instr/Cook pasta. Serve immediately.
   * Tip: You don't have to number the steps. Wishful Shrinking will number each instruction after separating by `.`

Examples:
* `addR n/salad i/lettuce, tomato, olive oil c/40 img/images/healthy1.jpg instr/Cook. Eat. t/fast t/easy`
* `addR n/sandwiches i/breads, cheese -2 slices c/80 img/https://vaya.in/recipes/wp-content/uploads/2018/06/Club-sandwich.jpg instr/Cook. Eat.`
<br><br><br>

### Listing all recipes : `recipes`

Shows a list of all recipes in the Recipe list.

<br>  The image below is what Wishful Shrinking looks like after you have listed all recipes. <br><br>
<img src="images/ListRecipesImage.png" width="550" height="300">
<br><br><br>

Format: `recipes`
<br><br><br>

### Deleting a recipe : `deleteR`

Deletes the specified recipe from the Recipe list.

<br>  The image below is what Wishful Shrinking looks like after you have deleted a recipe. <br><br>
<img src="images/DeleteRecipeImage.png" width="550" height="300">
<br><br><br>

Format: `deleteR INDEX`

* Deletes the recipe at the specified `INDEX`.
* Note: The index refers to the index number shown in the displayed recipe list.
* Note: Deleting a recipe **will not** affect the recipes that have been eaten in the consumption list.

Examples:
* `recipes` followed by `deleteR 2` deletes the 2nd recipe in Recipe list.
* `searchR n/salad` followed by `deleteR 1` deletes the 1st recipe in the result of the `searchR` command.
<br><br><br>

### Editing a recipe: `editR`

Edits the specified recipe from the Recipe list.

<br>  The image below is what Wishful Shrinking looks like after you have edited a recipe. <br><br>
<img src="images/EditRecipeImage.png" width="550" height="300">
<br><br><br>

Format: `editR INDEX [n/NAME] [i/INGREDIENT [ -QUANTITY][, MORE INGREDIENTS [ -QUANTITY]]] [c/CALORIES] [img/IMAGE] 
[inst/INSTRUCTION...] [t/TAG]...`

* Edits the recipe at the specified `INDEX`.
* Note: The index refers to the index number shown in the displayed recipe list.
* Note: All fields are optional, but **at least one** of the fields must be present. There can be any number of fields to edit. If you have not specified a field to edit, then it will simply become a get edit command elaborated below.
* Note: You are not allowed to edit a recipe into another recipe that is already in the recipe list.
* Note: Specified fields will permanently override existing values with the new values.
* Tip: Typing `editR INDEX` and then hitting `Enter` will insert the information of the recipe at the specified `INDEX` into the command box, letting you directly modify the existing recipe.

Examples:
* `editR 2 n/Apple salad i/apple` will update the name of the 2nd recipe in the displayed recipe list to Apple salad and the ingredients to contain an apple ingredient.
<br><br><br>

### Getting a recipe to edit: `editR`

Adds the information of the specified recipe to the edit command into the command box for editing purposes.

<br>  The image below is what Wishful Shrinking looks like after you have gotten a recipe to edit. <br><br>
<img src="images/EditRecipeGetImage.png" width="550" height="300">
<br><br><br>

Format: `editR INDEX`

* Gets the information of the recipe at the specified `INDEX` and adds it behind the edit recipe command in the command box.
* Note: The index refers to the index number shown in the displayed recipe list.

Examples:
* `editR 2` followed by `Enter` will insert the information of the 2nd recipe in the displayed recipe list into the command box.
<br><br><br>

### Selecting a single recipe : `selectR`

Displays the information of a single recipe in full view.

<br>  The image below is what Wishful Shrinking looks like after you have selected a specific recipe. <br><br>
<img src="images/SelectRecipeImage.png" width="550" height="300">
<br><br><br>

Format: `selectR INDEX`

* Selects the recipe at the specified `INDEX` to show its full information.
* Note: The index refers to the index number shown in the displayed recipe list.

Examples:
* `selectR 1` shows the 1st recipe in full view (left drawer), as well as in the command result box on the right.
<br><br><br>

### Closing the recipe drawer : `close`

Closes the left drawer (if opened after selecting a recipe).

<br>  The image below is what Wishful Shrinking looks like after you have closed the left drawer. It should look similar to before you selected a recipe. <br><br>
<img src="images/CloseRecipeImage.png" width="550" height="300">
<br><br><br>

Format: `close`

* This command closes the left drawer that is opened after you have selected a recipe.
* Note: Nothing will happen if you use this command when the left drawer is not opened.
<br><br><br>

### Searching for a recipe: `searchR`

Finds recipes in the Recipe list that contains all the specified ingredients OR whose name OR tags contain any of the given keywords.

<br>  The image below is what Wishful Shrinking looks like after you have searched for a recipe. In this case, the recipe is being searched by ingredients. <br><br>
<img src="images/SearchRecipeImage.png" width="550" height="300">
<br><br><br>

Format: `searchR [i/INGREDIENT [MORE_INGREDIENT]] [n/NAME [MORE_NAME]] [t/TAG [MORE_TAG]]`

* The search is case-insensitive. e.g `salad` will match `Salad`.
* The order of the keywords does not matter. e.g. Ham Salad will match Salad with Ham.
* Ingredients are separated by a space.
* Note: All fields are optional, but **only one** of the fields must be present. You can only search by the recipe ingredient(s) OR recipe name OR recipe tag(s).

Examples:
* `searchR i/lettuce tomato` returns `salad` that has both ingredients `lettuce` and `tomato`.
* `searchR n/salad` returns `salad` and `ham salad`.
* `searchR t/healthy` returns `salad` with tag `healthy`.
<br><br><br>

### Recommending recipes : `recommend`

Shows a list of all recipes in the Recipe list that can be made with the ingredients in your fridge.

<br>  The image below is what Wishful Shrinking looks like after you have gotten the recommended recipes. In this case, `recommend` returns the recipe `salad` with ingredients `lettuce`, `olive oil` and `tomato` since the example user has all the ingredients: `lettuce`, `olive oil` and `tomato` in their fridge.<br><br>
<img src="images/RecommendImage.png" width="550" height="300">
<br><br><br>

Format: `recommend`

* Note: Recipes will only be recommend if you have all the ingredients in the recipe with the exact same name in your fridge.
* Note: Ingredient quantity is not taken into account when determining whether an ingredient is present in your fridge.

Examples:
* `recommend` returns the recipe `salad` with ingredients `lettuce`, `onion` and `tomato` **only if** you have all `lettuce`, `onion` and `tomato` in your fridge.
<br><br><br>


### Clearing all recipes : `clearR`

Clears all the recipes in the Recipe list.

<br>  The image below is what Wishful Shrinking looks like after you have cleared all recipes. <br><br>
<img src="images/ClearRecipeImage.png" width="550" height="300">
<br><br><br>

Format: `clearR`
<br><br><br>

## Fridge-related Commands

The Fridge-related commands include `addF`, `fridge`, `deleteF`, `editF`, `searchF` and `clearF`. These are the commands in Wishful Shrinking that are relevant only to the Fridge.
<br><br><br>

### Adding an ingredient: `addF`

Adds an ingredient to the Fridge.

<br>  The image below is what Wishful Shrinking looks like after you have added an ingredient to the fridge. <br><br>
<img src="images/AddIngredientImage.png" width="550" height="300">
<br><br><br>

Format: `addF i/INGREDIENT [ -QUANTITY][, MORE INGREDIENTS [ -QUANTITY]]`

* `INGREDIENT` can take in an optional `Quantity` e.g. i/Tomato -2 whole.
    * Note: there is a mandatory space before `-`.
    * Note: `QUANTITY` only accepts alphanumeric characters, forward slashes and full stops.

Examples:
* `addF i/peanut`
* `addF i/tomato -1 kg`
* `addF i/banana -3/4 cups, green peas -200g, salmon fish`
<br><br><br>

### Listing all ingredients : `fridge`

Shows a list of all ingredients in the Fridge.

<br>  The image below is what Wishful Shrinking looks like after you have listed all ingredients in the fridge. <br><br>
<img src="images/ListIngredientsImage.png" width="550" height="300">
<br><br><br>

Format: `fridge`
<br><br><br>

### Deleting an ingredient : `deleteF`

Deletes the specified ingredient from the Fridge.

<br>  The image below is what Wishful Shrinking looks like after you have deleted an ingredient from the fridge. <br><br>
<img src="images/DeleteIngredientImage.png" width="550" height="300">
<br><br><br>

Format: `deleteF INDEX`

* Deletes the ingredient at the specified `INDEX`.
* Note: The index refers to the index number shown in the displayed ingredient list.

Examples:
* `fridge` followed by `deleteF 4` deletes the 4th ingredient in the fridge.
* `searchF peanut` followed by `deleteF 1` deletes the 1st ingredient in the results of the `searchF` command.
<br><br><br>

### Editing an ingredient: `editF`

Edits the specified ingredient from Fridge.

<br>  The image below is what Wishful Shrinking looks like after you have edited an ingredient in the fridge. <br><br>
<img src="images/EditIngredientImage.png" width="550" height="300">
<br><br><br>

Format: `editF INDEX i/INGREDIENT [ -QUANTITY]`

* Edits the ingredient at the specified `INDEX`.
* Note: The index refers to the index number shown in the displayed ingredient list.
* Note: All fields are optional, but **at least one** of the fields must be present.
* Note: You are not allowed to edit an ingredient into another ingredient that is already in the ingredient list.
* Note: Specified fields will permanently override existing values with the new values.
* Tip: Typing `editF INDEX` and then hitting `Enter` will insert the information of the ingredient at the specified `INDEX` into the command box, letting you directly modify the existing ingredient.

Examples:
* `editF 2 i/apple` will update the value of the 2nd recipe in the displayed ingredient list to apple.
<br><br><br>

### Getting an ingredient to edit: `editF`

Adds the information of the specified ingredient to the edit command into the command box for editing purposes.

<br>  The image below is what Wishful Shrinking looks like after you have gotten an ingredient in the fridge to edit. <br><br>
<img src="images/EditIngredientGetImage.png" width="550" height="300">
<br><br><br>

Format: `editF INDEX`

* Gets the information of the ingredient at the specified `INDEX` and adds it behind the edit ingredient command in the command box.
* Note: The index refers to the index number shown in the displayed ingredient list.

Examples:
* `editF 1` followed by `Enter` will insert the information of the 1st ingredient in the displayed ingredient list into the command box.
<br><br><br>

### Searching for an Ingredient: `searchF`

Finds ingredients in the Fridge that contain any of the given keywords.

<br>  The image below is what Wishful Shrinking looks like after you have searched for ingredients in the fridge. <br><br>
<img src="images/SearchIngredientImage.png" width="550" height="300">
<br><br><br>

Format: `searchF KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `peanut` will match `Peanut`.
* Only the ingredient name is searched.
* The order of the keywords does not matter. e.g. Peanut Butter will match Butter with Peanut.

Examples:
* `searchF peanut` returns `peanut` and `peanut butter`.
<br><br><br>

### Clearing all ingredients : `clearF`

Clears all the ingredients in the Fridge.

<br>  The image below is what Wishful Shrinking looks like after you have cleared all ingredients in the fridge. <br><br>
<img src="images/ClearFridgeImage.png" width="550" height="300">
<br><br><br>

Format: `clearF`
<br><br><br>

## Consumption-related Commands

The Consumption-related commands include `eatR`, `calories`, `deleteC`, and `clearC`. These are the commands in Wishful Shrinking that are relevant only to the Consumption list.
<br><br><br>

### Eating a recipe : `eatR`

Adds the specified recipe in the Recipe list into the Consumption list.

<br>  The image below is what Wishful Shrinking looks like after you have eaten a recipe. <br><br>
<img src="images/EatRecipeImage.png" width="550" height="300">
<br><br><br>

Format: `eatR INDEX`

* Adds the recipe at the specified `INDEX` into the Consumption list.
* Note: The index refers to the index number shown in the displayed recipe list.

Examples:
* `recipes` followed by `eatR 2` adds the 2nd recipe in the displayed recipe list into the Consumption list.
* `searchR n/salad` followed by `eatR 1` adds the 1st recipe in the results of the `searchR` command into the Consumption list.
<br><br><br>

### Listing all recipes eaten : `calories`

Shows a list of all recipes that you have eaten, as well as each recipe's calories and the total calories consumed.

<br>  The image below is what Wishful Shrinking looks like after you have listed all recipes that you have eaten. <br><br>
<img src="images/CaloriesImage.png" width="550" height="300">
<br><br><br>

Format: `calories`
<br><br><br>

### Deleting a recipe eaten: `deleteC`

Deletes the specified recipe from consumption list.

<br>  The image below is what Wishful Shrinking looks like after you have deleted a recipe from the consumption list. <br><br>
<img src="images/DeleteCalorieImage.png" width="550" height="300">
<br><br><br>

Format: `deleteC INDEX`

* Deletes the recipe at the specified `INDEX`.
* Note: The index refers to the index number shown in the displayed consumption list.

Examples:
* `calories` followed by `deleteC 2` deletes the 2nd recipe in the Consumption List.
<br><br><br>

### Clearing all consumed recipes : `clearC`

Clears all the recipes that have been eaten in the daily consumption list.

<br>  The image below is what Wishful Shrinking looks like after you have cleared all recipes eaten from the consumption list. <br><br>
<img src="images/ClearConsumptionImage.png" width="550" height="300">
<br><br><br>

Format: `clearC`
<br><br><br>

## Miscellaneous Commands

The miscellaneous commands include `help` and `exit`. They are the commands you can use in Wishful Shrinking that are separate from the Recipes, Fridge and Consumption list.
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

--------------------------------------------------------------------------------------------------------------------

# FAQ

**Q**: Why is my data not saved?<br>
**A**: Remember to copy the jar file to an EMPTY folder before starting the app.
<br>
<br>
**Q**: Why is the app not running?<br>
**A**: Ensure JDK `11` or above is installed.<br><br>

--------------------------------------------------------------------------------------------------------------------

# Glossary

Term | Explanation
--------|------------------
**Wishful Shrinking** | Can refer to name of the application as a whole or to the app’s storage file.<br>
**Fridge** | A personalised storage that contains all the ingredients that you have.<br>
**Recipe** | A set of cooking instructions that describes how to prepare a meal and the ingredients required.<br>
**Ingredient** | Food that can be used to prepare a particular dish according to a recipe.<br>
**Consumption** | A counter which calculates and displays your calorie intake based on the recipes you have consumed as well as a list of recipes consumed.<br>
**Drawer** | Page that layers above left section of the window when the `selectR` command runs and closes when the `close` command runs.
**CLI** | A Command Line Interface (CLI) is a text-based user interface that allows users to type text commands instructing the program to do specific tasks.
**GUI** | A Graphical User Interface (GUI) is a form of user interface that allows users to interact with the program through graphical icons instead of text-based user interfaces.

--------------------------------------------------------------------------------------------------------------------

# Command summary

Features | Format, Examples
--------|------------------
**Add recipe** | `addR n/NAME i/INGREDIENT [ -QUANTITY][, MORE INGREDIENTS [ -QUANTITY]] c/CALORIES [img/IMAGE] inst/INSTRUCTION... [t/TAG]...` <br> e.g. `addR n/salad i/lettuce, tomato, olive oil c/40 img/images/healthy1.jpg instr/Cook. Eat. t/fast t/easy`
**List recipes** | `recipes`
**Delete recipe** | `deleteR INDEX`<br> e.g. `deleteR 3`
**Edit recipe** | `editR INDEX [n/NAME] [i/INGREDIENT [ -QUANTITY][, MORE INGREDIENTS [ -QUANTITY]]] [c/CALORIES] [img/IMAGE] [inst/INSTRUCTION...] [t/TAG]...` <br> e.g. `editR 2 n/Apple salad i/apple`
**Get edit recipe info** | `editR INDEX` <br> e.g. `editR 2`
**Select recipe**| `selectR INDEX` <br> e.g. `selectR 3`
**Close recipe drawer**| `close`
**Search for recipe** | `searchR [i/INGREDIENT [MORE_INGREDIENT]] [n/NAME [MORE_NAME]] [t/TAG [MORE_TAG]]` <br> e.g. `searchR i/lettuce tomato`, `searchR n/salad`, `searchR t/healthy`
**Recommend recipe** | `recommend`
**Clear all recipes** | `clearR`
**Add ingredient to the fridge** | `addF i/INGREDIENT [ -QUANTITY][, MORE INGREDIENTS [ -QUANTITY]]` <br> e.g. `addF i/banana -3/4 cups, green peas -200g, salmon fish`
**List ingredients in the fridge** | `fridge`
**Delete ingredient from the fridge** | `deleteF INDEX`<br> e.g. `deleteF 3`
**Edit ingredient in the fridge** | `editF INDEX i/INGREDIENTS [ -QUANTITY]` <br> e.g. `editF 3 i/apple sauce -20.0g`
**Get edit ingredient info** | `editF INDEX` <br> e.g. `editF 2`
**Search for ingredient in the fridge** | `searchF KEYWORD [MORE_KEYWORDS]` <br> e.g. `searchF avocado`
**Clear all ingredients from the fridge** | `clearF`
**Eat recipe**| `eatR INDEX` <br> e.g. `eatR 3`
**List recipes eaten** | `calories`
**Delete recipe eaten**| `deleteC INDEX` <br> e.g. `deleteC 3`
**Clear all consumed recipes** | `clearC`
**Help** | `help`
**Exit** | `exit`
