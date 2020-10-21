### Eat recipe feature

Eat Recipe feature is used to record the user daily consumption. This feature will work with list consumption 
feature to output the total calories' user ate . 

#### Implementation
Substitutability is used in Command and Parser:
* `EatRecipeCommand` extends `Command`
* `EatRecipeCommandParser` implements `Parser<EatRecipeCommand>`

Given bellow is the simplified step on how eat recipe is done:

Step 1: User input is change into command.
![EatRecipeStep1](images/EatRecipeStep1.png)

Step 2: Execute the command (make a copy of recipe from recipeList).
![EatRecipeStep2](images/EatRecipeStep2.png)

Step 3: Add the copy recipe into consumptionList.
![EatRecipeStep3](images/EatRecipeStep3.png)


The following sequence diagram shows how eat recipe operation works when `execute(eatR 1)` API call:

![EatRecipeSequenceDiagram](images/EatRecipeSequenceDiagram.png)
<div markdown="span" class="alert alert-info">:information_source: 
**Note:** The lifeline for `EatRecipeCommandParser` should end at the destroy marker (X) but due to a limitation of 
PlantUML, the lifeline reaches the end of diagram.
</div>

1. User inputs eat recipe command to add a recipe to consumption list.
1. After successful parsing the user inputs, `EatRecipeCommand#method` method is called.
1. After successfully added recipe into consumption list, a `CommandResult` object is instantiated and returned to `LogicManager`.

<div markdown="span" class="alert alert-info">:information_source: 
**Note:** Delete a recipe in recipeList would not affect the consumptionList
</div>

#### Design consideration:
##### Aspect 1: Concern while adding a new feature
* Workflow must be consistent with other adding commands e.g. add recipe and ingredient.
##### Aspect 2: What are the informations to extract from a recipe and save in consumptionList 
* **Alternative 1 (current choice):** Saves all the informations in recipe.
  * Pros: Easy to implement.
  * Cons: May have performance issues in terms of memory usage.

* **Alternative 2:** Saves the recipe's data that going to use while listing consumption.
  * Pros: Will use less memory (only the information being used is copied into consumptionList).
  * Cons: Not future proof (need to restructure the whole command if wanted to show more information from the recipe)
