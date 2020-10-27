package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.ItemList;
import seedu.address.model.LocationList;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.RecipeList;
import seedu.address.model.UserPrefs;
import seedu.address.model.item.Item;
import seedu.address.testutil.ItemPrecursorBuilder;

public class UndoCommandIntegrationTest {
    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(new ItemList(), new LocationList(),
                new RecipeList(), new UserPrefs());
        Item.setIdCounter(0);
    }

    @Test
    public void execute_undoOnBaseModel_failure() {
        CommandResult expectedResult = new CommandResult(UndoCommand.MESSAGE_FAILURE);

        assertCommandSuccess(new UndoCommand(), model, expectedResult, model);
    }

    @Test
    public void execute_undoAfterOneCommand_success() {
        Model baseModel = model;
        Model modifiedModel = new ModelManager(new ItemList(), new LocationList(),
                new RecipeList(), new UserPrefs());

        try {
            new AddItemCommand(new ItemPrecursorBuilder().build()).execute(modifiedModel);
        } catch (Exception ignored) {
            // valid add item, won't throw error; ignored
        }

        Model modifiedCopy = new ModelManager(modifiedModel.getItemList(), new LocationList(),
                new RecipeList(), new UserPrefs());

        CommandResult expectedResult = new CommandResult(UndoCommand.MESSAGE_SUCCESS);

        assertCommandSuccess(new UndoCommand(), modifiedModel, expectedResult, baseModel);

        // compare with un-undoed version -> false
        assertNotEquals(modifiedModel, modifiedCopy);
    }
}
