package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ITEM_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ITEM_QUANTITY;

import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.item.Item;
import seedu.address.model.item.Quantity;
import seedu.address.model.recipe.Ingredient;
import seedu.address.model.recipe.Recipe;

/**
 * Checks if there are recipes which may be used to craft the desired quantity of the item.
 */
public class CheckCraftCommand extends Command {
    public static final String COMMAND_WORD = "check";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Checks if the item may be crafted\n"
            + "Parameters: "
            + PREFIX_ITEM_NAME + "ITEM NAME "
            + PREFIX_ITEM_QUANTITY + "ITEM QUANTITY \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_ITEM_NAME + "Bob's blueberry "
            + PREFIX_ITEM_QUANTITY + "20";

    public static final String MESSAGE_SUCCESS_CRAFTABLE = "%2$s %1$s may be crafted with any of the recipes below:";
    public static final String MESSAGE_SUCCESS_UNCRAFTABLE = "%2$s %1$s is not possible to craft with a single recipe.";

    public static final String MESSAGE_INVALID_QUANTITY = "Please enter a valid quantity.";

    private final String itemName;
    private final int quantity;

    /**
     * Creates a CheckCraftCommand to check if the {@code quantity} of the item with {@code itemName} may be crafted.
     */
    public CheckCraftCommand(String itemName, Quantity quantity) {
        requireNonNull(itemName);
        requireNonNull(quantity);

        this.itemName = itemName;
        this.quantity = quantity.getNumber();
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (quantity == 0) {
            throw new CommandException(MESSAGE_INVALID_QUANTITY);
        }

        // fetch the item
        List<Item> tempItemList = new ArrayList<>(model.getFilteredItemList());
        // filter to only get matching items
        tempItemList.removeIf(x -> !x.getName().equals(itemName));
        Item item = tempItemList.stream()
                .findFirst() // Get the first (and only) item matching or else throw Error
                .orElseThrow(() -> new CommandException(String.format(Messages.MESSAGE_NO_ITEM_FOUND, itemName)));

        ArrayList<Recipe> recipeList = new ArrayList<>(model.getFilteredRecipeList());
        // filter to only get matching recipes
        recipeList.removeIf(x -> !x.getProductName().equals(itemName));
        if (recipeList.isEmpty()) {
            throw new CommandException(String.format(Messages.MESSAGE_RECIPE_NOT_FOUND, itemName));
        }

        ArrayList<Item> itemList = new ArrayList<>(model.getFilteredItemList());

        return new CommandResult(findRecipes(quantity, itemList, recipeList));
    }

    /**
     * Extracts all usable recipes and formats them for display.
     */
    private String findRecipes(int quantity, List<Item> itemList, List<Recipe> recipeList) {
        StringBuilder message = new StringBuilder();
        message.append(String.format(MESSAGE_SUCCESS_CRAFTABLE, itemName, quantity)).append("\n");
        boolean isCraftable = false;

        for (Recipe recipe
                : recipeList) {
            assert recipe.getProductName().equals(itemName);

            int productQuantity = recipe.getProductQuantity().getNumber();
            int timesToCraft = quantity / productQuantity;
            if (quantity % productQuantity != 0) {
                // 1 more needed to round up and produce excess
                timesToCraft += 1;
            }
            boolean isUsable = true;
            StringBuilder requiredIngredients = new StringBuilder();

            for (Ingredient ingredient : recipe.getIngredients()) {
                int itemId = ingredient.getKey();
                int quantityRequired = ingredient.getValue() * timesToCraft;
                int currentQuantity = itemList.get(itemId).getQuantity().getNumber();
                if (quantityRequired <= currentQuantity) {
                    // this recipe can be used
                    requiredIngredients.append(itemList.get(itemId).getName())
                            .append("[").append(quantityRequired).append("], ");
                } else {
                    // this recipe cannot be used
                    isUsable = false;
                    break;
                }
            }
            // trim off the last ", "
            if (requiredIngredients.length() > 1) {
                requiredIngredients.setLength(requiredIngredients.length() - 2);
            }

            // add to message if recipe can be used
            if (isUsable) {
                int productQuantityProduced = timesToCraft * productQuantity;
                message.append(recipe.getDescription()).append(": ")
                        .append(requiredIngredients)
                        .append(" -> ")
                        .append(recipe.getProductName())
                        .append("[").append(productQuantityProduced).append("]\n");
                isCraftable = true;
            }
        }
        if (!isCraftable) {
            return String.format(MESSAGE_SUCCESS_UNCRAFTABLE, itemName, quantity);
        }
        return message.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CheckCraftCommand // instanceof handles nulls
                && itemName.equals(((CheckCraftCommand) other).itemName)
                && quantity == (((CheckCraftCommand) other).quantity));
    }
}
