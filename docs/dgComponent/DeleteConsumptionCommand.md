### Delete consumption feature

#### Implementation
This feature allows users to delete the recipes they have consumed in the consumption list.

Substitutability is used in Command and Parser:
* `DeleteConsumptionCommand` extends `Command`
* `DeleteConsumptionCommandParser` implements `Parser<DeleteConsumptionCommand>`

Given below is an example usage scenario and how the mechanism behaves at each step.

Step 1:
User inputs the delete consumption command to delete a `Consumption` from the `ConsumptionList`.

Step 2:
After successful parsing of user input, the `DeleteConsumptionCommand#execute(Model model)` method is called.

Step 3:
The `Consumption` that the user has specified by using index will be deleted from the `ConsumptionList`.

Step 4:
After the successful deleting of an `Consumption`, a `CommandResult` object is instantiated and returned to `LogicManager`.

The following sequence diagram shows how eat Consumption operation works when `execute(deleteC 1)` API call:

![DeleteConsumptionSequence](../images/DeleteConsumptionSequence.png)

#### Design consideration:
##### Aspect 1: Concern while adding a new feature
* Workflow must be consistent with other deleting commands e.g. delete recipe and delete ingredient.
