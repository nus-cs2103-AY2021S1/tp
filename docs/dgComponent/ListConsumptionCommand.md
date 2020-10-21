### List Consumption feature

List Consumption feature is used to list out all the recipes that user ate. This feature will also calculate 
the total calories. 

#### Implementation
Substitutability is used in Command and Parser:
* `ListConsumptionCommand` extends `Command`

The following sequence diagram shows how eat recipe operation works when `execute(calories)` API call:

![ListConsumptionSequenceDiagram](images/ListConsumptionSequenceDiagram.png)

1. User inputs the list consumption command to add recipe to consumption list.
1. After successful parsing the user inputs, `ListConsumptionCommand#method` method is called.
1. After successfully fetch the consumption list, a `CommandResult` object is instantiated and returned to `LogicManager`.

#### Design consideration:
##### Aspect 1: Concern while adding a new feature
* Workflow must be consistent with other adding commands e.g. list recipe and ingredient.
##### Aspect 2: What are the informations to list from a recipe in consumption list
* **Alternative 1 (current choice):** Listing recipe with images, name and calories.
  * Pros: Cleaner UI.
  * Cons: Other details that is not used become an extra data in memory.

* **Alternative 2:** Listing the whole recipe's information.
  * Pros: All the data saved are being used. 
  * Cons: Showing too much unimportant information.
