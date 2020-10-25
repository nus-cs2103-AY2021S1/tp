package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;

import seedu.address.model.ItemList;
import seedu.address.model.LocationList;
import seedu.address.model.Model;
import seedu.address.model.item.Item;
import seedu.address.model.recipe.Recipe;

/**
 * Clears the item list.
 */
public class ClearItemCommand extends Command {

    public static final String COMMAND_WORD = "cleari";
    public static final String MESSAGE_SUCCESS = "Item list has been cleared!";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        // delete related recipes
        List<Recipe> recipeList = new ArrayList<>(model.getFilteredRecipeList());
        List<Item> itemList = new ArrayList<>(model.getFilteredItemList());
        // remove recipes from consideration that do not
        // contain deleted item as product or ingredient
        for (Item item: itemList) {
            recipeList.removeIf(y -> !y.getProductName().equals(item.getName())
                    && y.getIngredients()
                    .asUnmodifiableObservableList()
                    .stream()
                    .noneMatch(z -> z.isItem(item.getId())));
        }
        // delete recipes connected to this identified item as a product, or an ingredient
        recipeList.forEach(model::deleteRecipe);
        model.setItemList(new ItemList());
        // clears locations as well
        model.setLocationList(new LocationList());

        model.commitInventory();

        return new CommandResult(MESSAGE_SUCCESS);
    }
}
