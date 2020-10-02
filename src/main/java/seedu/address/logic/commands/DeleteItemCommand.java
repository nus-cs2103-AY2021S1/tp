package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.item.Item;
import seedu.address.model.recipe.Recipe;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ITEM_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RECIPE_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RECIPE_PRODUCT_NAME;

public class DeleteItemCommand extends Command {
    public static final String COMMAND_WORD = "deli";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes a item in the item list. "
            + "Parameters: "
            + PREFIX_ITEM_NAME + "ITEM NAME\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_ITEM_NAME + "Iron ";
    public static final String MESSAGE_SUCCESS = "Item and Recipes of the item Deleted: %1$s";
    public static final String MESSAGE_ITEM_NOT_FOUND = "Item is not found in the item list";
    public static final String MESSAGE_ITEM_ALREADY_DELETED = "Item was already previously deleted";
    
    private final String productName;

    /**
     * Creates an DeleteItemCommand to delete the specified {@code Item} and connecting recipes
     * @param productName
     */
    public DeleteItemCommand(String productName) {
        requireNonNull(productName);
        this.productName = productName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Item> itemList = new ArrayList<>(model.getFilteredItemList());
        // filter to only get matching and not deleted recipes
        itemList.removeIf(x -> !x.getName().equals(productName) || x.isDeleted());
        if (itemList.isEmpty()) {
            throw new CommandException(MESSAGE_ITEM_NOT_FOUND);
        }
        Item itemToDelete;
        itemToDelete = itemList.stream()
                .filter(x -> !x.isDeleted())
                .findFirst() // Get the first (and only) item or else throw Error
                .orElseThrow(()-> new CommandException(MESSAGE_ITEM_ALREADY_DELETED));
        model.deleteItem(itemToDelete);
        List<Recipe> recipeList = new ArrayList<>(model.getFilteredRecipeList());
        // remove recipes from consideration that are not deleted, are not the product of a recipe, nor contribute
        // to a recipe.
        recipeList.removeIf(y -> !itemToDelete.getRecipeIds().contains(y.getId())
                || y.isDeleted()
                || y.getIngredients()
                .asUnmodifiableObservableList()
                .stream()
                .noneMatch(z -> z.isItem(itemToDelete.getId())));
        // delete recipes connected to this identified item as a product, or an ingredient
        recipeList.forEach(model::deleteRecipe);
        return new CommandResult(String.format(MESSAGE_SUCCESS, itemToDelete));
    }
}
