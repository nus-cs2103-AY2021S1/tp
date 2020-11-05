package seedu.address.logic.commands.recipe;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalRecipes.getTypicalWishfulShrinking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains unit tests for CloseCommand.
 */
public class CloseCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalWishfulShrinking(), new UserPrefs());
        expectedModel = new ModelManager(model.getWishfulShrinking(), new UserPrefs());
    }

    @Test
    public void execute_recipeSelected_closeDrawer() {
        assertCommandSuccess(new CloseCommand(), model,
                CloseCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
