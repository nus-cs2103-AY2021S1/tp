### Search Ingredient feature
This feature allows users search ingredient in the ingredient list based on the name.

#### Implementation

Substitutability is used in Command and Parser:
* `SearchIngredientCommand` extends `Command`
* `SearchIngredientCommandParser` implements `Parser<SearchIngredientCommand>`

The following sequence diagram shows how eat recipe operation works when `execute(searchF avocado)` API call:

![SearchIngredientSequence](images/SearchIngredientSequence.png)
<div markdown="span" class="alert alert-info">:information_source: 
**Note:** The lifeline for `EatRecipeCommandParser` should end at the destroy marker (X) but due to a limitation of 
PlantUML, the lifeline reaches the end of diagram.
</div>

1. User inputs the search ingredient command to search for the ingredient from the ingredient list.
1. After successful parsing of user input, the `SearchIngredientCommand#execute(Model model)` method is called.
1. The list of ingredient that fit the user's search will be returned to the user.
1. After the successful searching of the recipes, a `CommandResult` object is instantiated and returned to `LogicManager`.

#### Design Considerations
##### Aspect 1: Concern while adding a new feature
* Workflow must be consistent with other searching commands e.g. search recipe.
