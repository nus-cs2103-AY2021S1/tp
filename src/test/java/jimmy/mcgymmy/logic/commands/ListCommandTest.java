package jimmy.mcgymmy.logic.commands;

import static jimmy.mcgymmy.logic.commands.ListCommand.MESSAGE_SUCCESS;
import static jimmy.mcgymmy.testutil.TypicalIndexes.INDEX_FIRST_FOOD;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jimmy.mcgymmy.model.Model;
import jimmy.mcgymmy.model.ModelManager;
import jimmy.mcgymmy.model.UserPrefs;
import jimmy.mcgymmy.testutil.TypicalFoods;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(TypicalFoods.getTypicalMcGymmy(), new UserPrefs());
        expectedModel = new ModelManager(model.getMcGymmy(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        CommandTestUtil.assertCommandSuccess(new ListCommand(), model, MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        CommandTestUtil.showFoodAtIndex(model, INDEX_FIRST_FOOD);
        CommandTestUtil.assertCommandSuccess(new ListCommand(), model, MESSAGE_SUCCESS, expectedModel);
    }
}
