package jimmy.mcgymmy.logic.commands;

import jimmy.mcgymmy.model.Model;
import jimmy.mcgymmy.model.ModelManager;

import org.junit.jupiter.api.Test;

public class ClearCommandTest {

    @Test
    public void execute_emptyMcGymmy_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        CommandTestUtil.assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    //ToDo: Add relevent non empty success that uses predicates

}
