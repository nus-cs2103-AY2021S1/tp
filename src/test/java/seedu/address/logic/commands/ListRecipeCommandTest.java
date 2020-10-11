package seedu.address.logic.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.RecipeList;
import seedu.address.model.UserPrefs;
import seedu.address.testutil.TypicalItems;
import seedu.address.testutil.TypicalRecipes;
import seedu.address.ui.DisplayedInventoryType;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalLocations.getTypicalLocationsList;

public class ListRecipeCommandTest {

    private Model model;
    private ListRecipeCommand listRecipeCommand = new ListRecipeCommand();

    @BeforeEach
    public void setUp() {
        model = new ModelManager(TypicalItems.getTypicalItemList(), getTypicalLocationsList(),
                new RecipeList(), new UserPrefs());
    }

    /**
     * Tests for correct execution and displayed message with non-empty recipe list.
     */
    @Test
    public void execute_success() {
        model.setRecipeList(TypicalRecipes.getTypicalRecipeList());
        CommandResult expectedCommandResult = new CommandResult(ListRecipeCommand.MESSAGE_SUCCESS,
                false, false, DisplayedInventoryType.RECIPES);
        assertCommandSuccess(listRecipeCommand, model, expectedCommandResult, model);
    }

    /**
     * Tests for correct execution and displayed message with empty recipe list.
     */
    @Test
    public void execute_showEmptyItemList() {
        CommandResult expectedCommandResult = new CommandResult(ListRecipeCommand.MESSAGE_NO_RECIPES,
                false, false, DisplayedInventoryType.RECIPES);
        assertCommandSuccess(listRecipeCommand, model, expectedCommandResult, model);
    }
}