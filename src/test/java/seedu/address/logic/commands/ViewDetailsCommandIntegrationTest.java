package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_RECIPES;
import static seedu.address.testutil.TypicalItems.APPLE;
import static seedu.address.testutil.TypicalItems.APPLE_PIE_ITEM;
import static seedu.address.testutil.TypicalRecipes.BANANA_PIE;
import static seedu.address.testutil.TypicalRecipes.FOUND_APPLE;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.Messages;
import seedu.address.model.ItemList;
import seedu.address.model.LocationList;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.RecipeList;
import seedu.address.model.UserPrefs;
import seedu.address.model.item.Item;
import seedu.address.model.item.NameIsExactlyPredicate;
import seedu.address.model.recipe.Recipe;
import seedu.address.testutil.TypicalRecipes;
import seedu.address.ui.DisplayedInventoryType;

/**
 * Contains integration tests (interaction with the Model) for {@code ViewDetailsCommand}.
 */
public class ViewDetailsCommandIntegrationTest {

    private Model model;
    private ItemList itemList;
    private RecipeList recipeList;

    @BeforeEach
    public void setUp() {
        itemList = new ItemList();
        itemList.addItem(APPLE);
        itemList.addItem(APPLE_PIE_ITEM);
        recipeList = TypicalRecipes.getTypicalRecipeList();
        model = new ModelManager(itemList, new LocationList(), recipeList, new UserPrefs());
    }

    /**
     * Tests for successful extraction of item and recipes which craft it.
     */
    @Test
    public void execute_success() {
        String expectedMessage = ViewDetailsCommand.MESSAGE_SUCCESS;
        NameIsExactlyPredicate predicate = new NameIsExactlyPredicate(
                Collections.singletonList(APPLE.getName()));
        ViewDetailsCommand command = new ViewDetailsCommand(predicate);

        assertEquals(command.execute(model), new CommandResult(expectedMessage, false,
                false, DisplayedInventoryType.DETAILED_ITEM));

        // check the matching item is extracted
        List<Item> expectedItemList = Collections.singletonList(APPLE);
        ObservableList<Item> exObservableItemList = FXCollections.observableList(expectedItemList);
        FilteredList<Item> exFilteredItemList = exObservableItemList.filtered(predicate);
        assertEquals(exFilteredItemList, model.getFilteredItemList());

        // check the relevant recipes are extracted
        List<Recipe> expectedRecipeList = new ArrayList<>();
        expectedRecipeList.add(BANANA_PIE);
        expectedRecipeList.add(FOUND_APPLE);
        ObservableList<Recipe> exObservableRecipeList = FXCollections.observableList(expectedRecipeList);
        FilteredList<Recipe> exFilteredRecipeList = exObservableRecipeList.filtered(PREDICATE_SHOW_ALL_RECIPES);
        assertEquals(exFilteredRecipeList, model.getFilteredRecipeList());
    }

    /**
     * Tests for success when the item name specified in predicate cannot be found.
     */
    @Test
    public void execute_noItemFound() {
        NameIsExactlyPredicate predicate = new NameIsExactlyPredicate(Collections.singletonList("test"));
        ViewDetailsCommand vdc = new ViewDetailsCommand(predicate);

        assertEquals(vdc.execute(model), new CommandResult(String.format(Messages.MESSAGE_NO_ITEM_FOUND,
                predicate.getKeyword()), false, false, DisplayedInventoryType.UNCHANGED));
        assertTrue(model.getFilteredItemList().isEmpty());
    }
}
