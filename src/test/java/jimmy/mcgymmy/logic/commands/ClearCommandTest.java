package jimmy.mcgymmy.logic.commands;

import org.junit.jupiter.api.Test;

import jimmy.mcgymmy.model.McGymmy;
import jimmy.mcgymmy.model.Model;
import jimmy.mcgymmy.model.ModelManager;
import jimmy.mcgymmy.model.UserPrefs;
import jimmy.mcgymmy.testutil.TypicalFoods;

public class ClearCommandTest {

    @Test
    public void execute_emptyMcGymmy_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        CommandTestUtil.assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyMcGymmy_success() {
        Model model = new ModelManager(TypicalFoods.getTypicalMcGymmy(), new UserPrefs());
        Model expectedModel = new ModelManager(TypicalFoods.getTypicalMcGymmy(), new UserPrefs());
        expectedModel.setMcGymmy(new McGymmy());

        CommandTestUtil.assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
