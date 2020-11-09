package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalModules.getTypicalGradeBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.GradeBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.module.GoalTarget;

public class ClearCommandTest {

    @Test
    public void execute_emptyGradeBook_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyGradeBook_success() {
        Model model = new ModelManager(getTypicalGradeBook(), new UserPrefs(), new GoalTarget());
        Model expectedModel = new ModelManager(getTypicalGradeBook(), new UserPrefs(), new GoalTarget());
        expectedModel.setGradeBook(new GradeBook());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
