package seedu.address.logic.commands;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.item.Item;
import seedu.address.model.item.Quantity;
import seedu.address.model.recipe.Ingredient;
import seedu.address.model.recipe.IngredientList;
import seedu.address.model.recipe.Recipe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.*;

/**
 * DeleteItemCommand represents a delete item command with hidden
 * internal logic and the ability to be executed.
 */
public class CraftItemCommand extends Command {
    public static final String COMMAND_WORD = "craft";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Crafts the item using the materials in the inventory. \n"
            + "Parameters: "
            + PREFIX_ITEM_NAME + "ITEM NAME "
            + PREFIX_ITEM_QUANTITY + "ITEM QUANTITY "
            + PREFIX_RECIPE_ID + "RECIPE INDEX\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_ITEM_NAME + "Iron "
            + PREFIX_ITEM_QUANTITY + "1 "
            + PREFIX_RECIPE_ID + "2";

    public static final String MESSAGE_SUCCESS = "%2$s %1$s crafted";
    public static final String MESSAGE_ITEM_NOT_FOUND = "Item to craft is not found in the item list.";
    public static final String MESSAGE_CRAFTING_NOT_POSSIBLE = "Crafting is not possible.";
    public static final String MESSAGE_INSUFFICIENT_INGREDIENTS = "Crafting failed, insufficient " +
            "ingredients in inventory.";
    private static final String MESSAGE_RECIPE_NOT_FOUND = "Recipe cannot be not found in the recipe list";
    public static final String MESSAGE_INDEX_NOT_FOUND = "Recipe ID is out of range";

    private final String itemName;
    private final int productQuantity;
    private final Index index;
    private int recipeProductQuantity;

    public CraftItemCommand(String itemName, Quantity productQuantity, Index index) {
        this.itemName = itemName;
        this.productQuantity = Integer.parseInt(productQuantity.value);
        this.index = index;
        this.recipeProductQuantity = 1;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        // fetch the item
        List<Item> tempItemList = new ArrayList<>(model.getFilteredItemList());

        // filter to only get matching items
        tempItemList.removeIf(x -> !x.getName().equals(itemName));
        if (tempItemList.isEmpty()) {
            throw new CommandException(MESSAGE_ITEM_NOT_FOUND);
        }
        Item itemToCraft;
        itemToCraft = tempItemList.stream()
                .findFirst()// Get the first (and only) item matching or else throw Error
                .orElseThrow(() -> new CommandException(MESSAGE_ITEM_NOT_FOUND));

        // fetch the recipe
        List<Recipe> tempRecipeList = new ArrayList<>(model.getFilteredRecipeList());
        // filter to only get matching recipes
        tempRecipeList.removeIf(x -> !x.getProductName().equals(itemName));
        if (tempRecipeList.isEmpty()) {
            throw new CommandException(MESSAGE_RECIPE_NOT_FOUND);
        }

        Recipe recipeToUse;
        try {
            recipeToUse = tempRecipeList.get(index.getZeroBased());
        } catch (IndexOutOfBoundsException e) {
            throw new CommandException(MESSAGE_INDEX_NOT_FOUND); //index out of range
        }
        recipeProductQuantity = Integer.parseInt(recipeToUse.getProductQuantity().value);

        // check the product quantity
        if (productQuantity == 0 || productQuantity % recipeProductQuantity != 0) {
            throw new CommandException(MESSAGE_CRAFTING_NOT_POSSIBLE);
        }

        // check if item can be crafted
        IngredientList ingredientList = recipeToUse.getIngredients();
        ArrayList<Item> itemList = new ArrayList<>(model.getFilteredItemList());
        // Storage into hashmap to use for updating
        HashMap<Integer, Integer> requiredIngredients = new HashMap<>();

        // check the ingredients quantity
        if (!checkIngredients(ingredientList, itemList, requiredIngredients)) {
            throw new CommandException(MESSAGE_INSUFFICIENT_INGREDIENTS);
        }
        // update ingredients, decrease by quantity required since ingredients are consumed
        requiredIngredients.forEach((itemId, quantityRequired) ->
        {
            try {
                new AddQuantityToItemCommand(itemList.get(itemId).getName(), -quantityRequired).execute(model);
            } catch (CommandException e) {
                // change of quantity should never fail
                assert false;
            }
        });
        // update the product
        new AddQuantityToItemCommand(itemName, productQuantity).execute(model);

        return new CommandResult(String.format(MESSAGE_SUCCESS, itemToCraft, productQuantity));
    }

    /**
     * Checks if there is sufficient amount of ingredients in the inventory.
     * @param ingredientList The ingredient list to be checked.
     * @param itemList The existing item list in the model to check with.
     * @param requiredIngredients The required amount of ingredients to consume.
     * @return Whether the crafting can be done.
     */
    private boolean checkIngredients(IngredientList ingredientList, ArrayList<Item> itemList,
                                     HashMap<Integer, Integer> requiredIngredients) {
        for (Ingredient ingredient : ingredientList) {
            int itemId = ingredient.getKey();
            int quantityRequired = ingredient.getValue() * productQuantity / recipeProductQuantity;
            int currentQuantity = Integer.parseInt(itemList.get(itemId).getQuantity().value);
            if (quantityRequired > currentQuantity) {
                return false;
            }
            requiredIngredients.put(itemId, quantityRequired);
        }
        return true;
    }
}


