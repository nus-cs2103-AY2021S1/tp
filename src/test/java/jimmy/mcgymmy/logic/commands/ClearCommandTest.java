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

   //ToDo: Add relevent non empty success that uses predicates

}
