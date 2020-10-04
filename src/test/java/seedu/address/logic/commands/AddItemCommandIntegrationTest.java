package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.assertItemCommandFailure;
import static seedu.address.testutil.TypicalItems.getTypicalItemList;
import static seedu.address.testutil.TypicalLocations.getTypicalLocationsList;
import static seedu.address.testutil.TypicalRecipes.getTypicalRecipeList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.item.Item;
import seedu.address.model.item.ItemPrecursor;
import seedu.address.testutil.ItemPrecursorBuilder;

public class AddItemCommandIntegrationTest {
    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalItemList(), getTypicalLocationsList(),
                getTypicalRecipeList(), new UserPrefs());
    }

    @Test
    public void execute_newItem_success() {
        ItemPrecursor validItemPrecursor = new ItemPrecursorBuilder().build();
        Item validItem = model.processPrecursor(validItemPrecursor);

        Model expectedModel = new ModelManager(model.getItemList(), model.getLocationList(),
                model.getRecipeList(), new UserPrefs());
        expectedModel.addItem(validItem);

        assertCommandSuccess(new AddItemCommand(validItemPrecursor), model,
                String.format(AddItemCommand.MESSAGE_SUCCESS, validItem), expectedModel);
    }

    @Test
    public void execute_duplicateItem_throwsCommandException() {
        ItemPrecursor validItemPrecursor = new ItemPrecursorBuilder().build();
        Item validItem = model.processPrecursor(validItemPrecursor);

        Model modelWithItem = new ModelManager(model.getItemList(), model.getLocationList(),
                model.getRecipeList(), new UserPrefs());
        modelWithItem.addItem(validItem);

        assertItemCommandFailure(new AddItemCommand(validItemPrecursor), modelWithItem,
                AddItemCommand.MESSAGE_DUPLICATE_ITEM);
    }
}
