package seedu.address.logic.commands;

import org.junit.jupiter.api.Test;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.recipe.Recipe;
import seedu.address.ui.DisplayedInventoryType;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalItems.getTypicalItemList;
import static seedu.address.testutil.TypicalLocations.getTypicalLocationsList;
import static seedu.address.testutil.TypicalRecipes.getTypicalRecipeList;

public class DeleteRecipeCommandTest {

    private final Model model = new ModelManager(getTypicalItemList(), getTypicalLocationsList(),
            getTypicalRecipeList(), new UserPrefs());

    //TODO
//    @Test
//    public void execute_success() {
//        DeleteRecipeCommand drc = new DeleteRecipeCommand("Apple", Index.fromOneBased(1));
//        try {
//            drc.execute(model);
//            Model expectedmodel = new ModelManager(getTypicalItemList(), getTypicalLocationsList(),
//                    getTypicalRecipeList(), new UserPrefs());
//            Recipe recipeToDelete = expectedmodel.getFilteredRecipeList().get(1);
//            expectedmodel.deleteRecipe(recipeToDelete);
//            String expectedMessage = String.format(DeleteItemCommand.MESSAGE_SUCCESS, recipeToDelete);
//            assertCommandSuccess(drc, model, expectedMessage, expectedmodel);
//        } catch (CommandException e) {
//            fail();
//        }
//    }

//    @Test
//    public void execute_recipe_not_found() {
//    }
//
//    @Test
//    public void execute_index_out_of_range() throws CommandException {
//        DeleteRecipeCommand drc = new DeleteRecipeCommand("Apple", Index.fromOneBased(2));
//        // index out of range since only 1 recipe for apple
//        drc.execute(model);
//        assertCommandFailure(drc, model, DeleteRecipeCommand.MESSAGE_INDEX_NOT_FOUND);
//    }

    @Test
    void testEquals() {
    }
}