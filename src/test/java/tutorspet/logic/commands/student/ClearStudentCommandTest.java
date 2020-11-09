package tutorspet.logic.commands.student;

import static tutorspet.logic.commands.CommandTestUtil.assertCommandSuccess;
import static tutorspet.logic.commands.student.ClearStudentCommand.MESSAGE_SUCCESS;
import static tutorspet.testutil.TypicalTutorsPet.getOnlyModuleClassTutorsPet;
import static tutorspet.testutil.TypicalTutorsPet.getTypicalTutorsPet;

import org.junit.jupiter.api.Test;

import tutorspet.model.Model;
import tutorspet.model.ModelManager;
import tutorspet.model.UserPrefs;

public class ClearStudentCommandTest {

    @Test
    public void execute_emptyTutorsPet_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();
        expectedModel.commit(MESSAGE_SUCCESS);

        assertCommandSuccess(new ClearStudentCommand(), model, MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyTutorsPet_success() {
        Model model = new ModelManager(getTypicalTutorsPet(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalTutorsPet(), new UserPrefs());
        expectedModel.setTutorsPet(getOnlyModuleClassTutorsPet());
        expectedModel.commit(MESSAGE_SUCCESS);

        assertCommandSuccess(new ClearStudentCommand(), model, MESSAGE_SUCCESS, expectedModel);
    }
}
