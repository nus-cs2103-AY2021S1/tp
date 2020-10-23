package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ITEM_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ITEM_QUANTITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RECIPE_ID;
import static seedu.address.logic.parser.ItemParserUtil.DEFAULT_INDEX;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.item.Item;
import seedu.address.model.item.Quantity;
import seedu.address.model.recipe.Ingredient;
import seedu.address.model.recipe.IngredientList;
import seedu.address.model.recipe.Recipe;

/**
 * CraftItemCommand represents a craft item command with hidden
 * internal logic and the ability to be executed.
 */
public class CraftItemCommand extends Command {
    public static final String COMMAND_WORD = "craft";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Crafts the item "
            + "using the materials in the inventory.\n"
            + "Parameters: "
            + PREFIX_ITEM_NAME + "ITEM NAME "
            + PREFIX_ITEM_QUANTITY + "ITEM QUANTITY "
            + PREFIX_RECIPE_ID + "RECIPE INDEX\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_ITEM_NAME + "Iron "
            + PREFIX_ITEM_QUANTITY + "1 "
            + PREFIX_RECIPE_ID + "2";

    public static final String MESSAGE_SUCCESS = "%2$s %1$s crafted";
    public static final String MESSAGE_SUCCESS_EXCESS = "%2$s %1$s crafted instead of %3$s, extras crafted"
            + " due to recipe";
    public static final String MESSAGE_MISSING_RECIPE_INDEX = "First recipe used as recipe index was not provided.\n";
    public static final String MESSAGE_ITEM_NOT_FOUND = "Item to craft is not found in the item list.";
    public static final String MESSAGE_INVALID_PRODUCT_QUANTITY = "Crafting failed, please enter a valid "
            + "product quantity";
    public static final String MESSAGE_INSUFFICIENT_INGREDIENTS = "Crafting failed, insufficient "
            + "ingredients in inventory.";
    public static final String MESSAGE_RECIPE_NOT_FOUND = "No relevant recipes found in the recipe list";
    public static final String MESSAGE_INDEX_OUT_OF_RANGE = "Recipe ID is out of range";

    private final String itemName;
    private int productQuantity;
    private final Index index;

    private boolean hasCraftedExcess;
    private final boolean hasDefaultIndex;

    /**
     * Creates a command to craft the item
     * @param itemName Name of item to be crafted
     * @param productQuantity Quantity of item to be crafted
     * @param index Index of recipe to use for crafting
     */
    public CraftItemCommand(String itemName, Quantity productQuantity, Index index) {
        requireNonNull(itemName);
        requireNonNull(productQuantity);
        requireNonNull(index);
        this.itemName = itemName;
        this.productQuantity = productQuantity.number;
        this.index = index;
        this.hasCraftedExcess = false;
        this.hasDefaultIndex = false;
    }

    /**
     * Constructs a craft item command with a default recipe index as user did not provide one
     */
    public CraftItemCommand(String itemName, Quantity productQuantity) {
        requireNonNull(itemName);
        requireNonNull(productQuantity);
        this.itemName = itemName;
        this.productQuantity = productQuantity.number;
        this.index = DEFAULT_INDEX;
        this.hasCraftedExcess = false;
        this.hasDefaultIndex = true;
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
                .findFirst() // Get the first (and only) item matching or else throw Error
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
            throw new CommandException(MESSAGE_INDEX_OUT_OF_RANGE); //index out of range
        }

        int recipeProductQuantity = recipeToUse.getProductQuantity().number;

        if (productQuantity == 0) {
            throw new CommandException(MESSAGE_INVALID_PRODUCT_QUANTITY);
        }

        // check if quantity to craft is not an exact multiple of recipe product quantity
        int intendedQuantity = productQuantity;
        if (productQuantity % recipeProductQuantity != 0) {
            // round up to craft extra
            productQuantity = ((productQuantity / recipeProductQuantity) + 1) * recipeProductQuantity;
            hasCraftedExcess = true;
        }

        IngredientList ingredientList = recipeToUse.getIngredients();
        ArrayList<Item> itemList = new ArrayList<>(model.getFilteredItemList());
        // Storage into hashmap to use for updating
        HashMap<Integer, Integer> requiredIngredients = new HashMap<>();

        // check if there is enough of each ingredient in inventory
        if (!checkIngredients(ingredientList, itemList, requiredIngredients, recipeProductQuantity)) {
            throw new CommandException(MESSAGE_INSUFFICIENT_INGREDIENTS);
        }

        // update ingredients, decrease by quantity required since ingredients are consumed
        requiredIngredients.forEach((itemId, quantityRequired) -> {
            try {
                new AddQuantityToItemCommand(itemList.get(itemId).getName(), -quantityRequired).execute(model);
            } catch (CommandException e) {
                // change of quantity should never fail
                assert false;
            }
        });

        // update the quantity of the item crafted
        new AddQuantityToItemCommand(itemName, productQuantity).execute(model);

        String displayMessage = "";
        // indicate if user left out the index field
        if (hasDefaultIndex) {
            displayMessage += MESSAGE_MISSING_RECIPE_INDEX;
        }

        // indicate if crafted more than intended due to recipe
        if (hasCraftedExcess) {
            displayMessage += String.format(MESSAGE_SUCCESS_EXCESS, itemToCraft,
                    productQuantity, intendedQuantity);
        } else {
            displayMessage += String.format(MESSAGE_SUCCESS, itemToCraft, productQuantity);
        }

        return new CommandResult(displayMessage);
    }

    /**
     * Checks if there is sufficient amount of ingredients in the inventory to proceed with crafting.
     * @param ingredientList The ingredient list to be checked.
     * @param itemList The existing item list in the model to check with.
     * @param requiredIngredients The required amount of ingredients to consume.
     * @return Whether the crafting can be done.
     */
    private boolean checkIngredients(IngredientList ingredientList, ArrayList<Item> itemList,
                                     HashMap<Integer, Integer> requiredIngredients, int recipeProductQuantity) {
        for (Ingredient ingredient : ingredientList) {
            int itemId = ingredient.getKey();
            int quantityRequired = ingredient.getValue() * productQuantity / recipeProductQuantity;
            int currentQuantity = itemList.get(itemId).getQuantity().number;
            if (quantityRequired > currentQuantity) {
                return false;
            }
            requiredIngredients.put(itemId, quantityRequired);
        }
        return true;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CraftItemCommand // instanceof handles nulls
                && itemName.equals(((CraftItemCommand) other).itemName)
                && productQuantity == (((CraftItemCommand) other).productQuantity)
                && index.equals(((CraftItemCommand) other).index))
                && hasCraftedExcess == (((CraftItemCommand) other).hasCraftedExcess)
                && hasDefaultIndex == (((CraftItemCommand) other).hasDefaultIndex);
    }
}
