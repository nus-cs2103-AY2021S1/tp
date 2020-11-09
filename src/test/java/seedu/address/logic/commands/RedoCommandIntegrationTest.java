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

public class RedoCommandIntegrationTest {
    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(new ItemList(), new LocationList(),
                new RecipeList(), new UserPrefs());
        Item.setIdCounter(0);
    }

    @Test
    public void execute_redoOnBaseModel_failure() {
        CommandResult expectedResult = new CommandResult(RedoCommand.MESSAGE_FAILURE);

        assertCommandSuccess(new RedoCommand(), model, expectedResult, model);
    }

    @Test
    public void execute_redoAfterOneCommand_failure() {
        try {
            new AddItemCommand(new ItemPrecursorBuilder().build()).execute(model);
        } catch (Exception ignored) {
            // valid add item, won't throw error; ignored
        }

        CommandResult expectedResult = new CommandResult(RedoCommand.MESSAGE_FAILURE);

        assertCommandSuccess(new RedoCommand(), model, expectedResult, model);
    }

    @Test
    public void execute_redoAfterCommandAfterUndo_failure() {
        try {
            new AddItemCommand(new ItemPrecursorBuilder().build()).execute(model);
        } catch (Exception ignored) {
            // valid add item, won't throw error; ignored
        }

        try {
            new UndoCommand().execute(model);
        } catch (Exception ignored) {
            // valid undo, won't throw error; ignored
        }

        try {
            new AddItemCommand(new ItemPrecursorBuilder().build()).execute(model);
        } catch (Exception ignored) {
            // valid add item, won't throw error; ignored
        }

        CommandResult expectedResult = new CommandResult(RedoCommand.MESSAGE_FAILURE);

        assertCommandSuccess(new RedoCommand(), model, expectedResult, model);
    }

    @Test
    public void execute_redoAfterOneUndo_success() {
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

        try {
            new UndoCommand().execute(modifiedModel);
        } catch (Exception ignored) {
            // valid undo, won't throw error; ignored
        }

        CommandResult expectedResult = new CommandResult(RedoCommand.MESSAGE_SUCCESS);

        assertCommandSuccess(new RedoCommand(), modifiedModel, expectedResult, modifiedCopy);

        // compare with base version -> false
        assertNotEquals(modifiedModel, baseModel);
    }
}
