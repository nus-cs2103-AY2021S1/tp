package seedu.medibook.logic.commands;

import static seedu.medibook.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.medibook.logic.commands.CommandTestUtil.showPatientAtIndex;
import static seedu.medibook.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.medibook.testutil.TypicalPatients.getTypicalMediBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.medibook.model.Model;
import seedu.medibook.model.ModelManager;
import seedu.medibook.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalMediBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getMediBook(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        expectedModel.resetAccessedPatient();
        CommandResult expectedCommandResult = new CommandResult(String.format(ListCommand.MESSAGE_SUCCESS),
                false, false, false, true);
        assertCommandSuccess(new ListCommand(), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showPatientAtIndex(model, INDEX_FIRST);
        expectedModel.resetAccessedPatient();
        CommandResult expectedCommandResult = new CommandResult(String.format(ListCommand.MESSAGE_SUCCESS),
                false, false, false, true);
        assertCommandSuccess(new ListCommand(), model, expectedCommandResult, expectedModel);
    }
}
