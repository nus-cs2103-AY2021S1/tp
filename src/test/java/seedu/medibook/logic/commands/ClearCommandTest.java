package seedu.medibook.logic.commands;

import static seedu.medibook.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.medibook.testutil.TypicalPatients.getTypicalMediBook;

import org.junit.jupiter.api.Test;

import seedu.medibook.model.MediBook;
import seedu.medibook.model.Model;
import seedu.medibook.model.ModelManager;
import seedu.medibook.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptyMediBook_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();
        expectedModel.setShouldDeleteAllMedicalNotes(true);

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyMediBook_success() {
        Model model = new ModelManager(getTypicalMediBook(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalMediBook(), new UserPrefs());
        expectedModel.setMediBook(new MediBook());
        expectedModel.setShouldDeleteAllMedicalNotes(true);

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
