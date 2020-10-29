package seedu.pivot.logic.commands;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.pivot.logic.commands.testutil.CommandTestUtil.assertCommandFailure;
import static seedu.pivot.logic.commands.testutil.CommandTestUtil.assertCommandSuccess;
import static seedu.pivot.testutil.TypicalCases.getTypicalPivot;
import static seedu.pivot.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.pivot.commons.core.UserMessages;
import seedu.pivot.commons.core.index.Index;
import seedu.pivot.logic.commands.casecommands.OpenCaseCommand;
import seedu.pivot.logic.state.StateManager;
import seedu.pivot.model.Model;
import seedu.pivot.model.ModelManager;
import seedu.pivot.model.UserPrefs;
import seedu.pivot.model.investigationcase.Case;


public class OpenCaseCommandTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalPivot(), new UserPrefs());
        StateManager.resetState();
    }
    @AfterEach
    public void cleanUp() {
        StateManager.resetState();
    }

    @Test
    public void execute_validIndex_success() {
        Case caseToOpen = model.getFilteredCaseList().get(INDEX_FIRST_PERSON.getZeroBased());
        OpenCaseCommand openCaseCommand = new OpenCaseCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(OpenCaseCommand.MESSAGE_OPEN_CASE_SUCCESS, caseToOpen);

        assertCommandSuccess(openCaseCommand, model, expectedMessage, model);
        assertTrue(StateManager.atCasePage()); //check state
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredCaseList().size() + 1);
        OpenCaseCommand openCaseCommand = new OpenCaseCommand(outOfBoundIndex);
        assertCommandFailure(openCaseCommand, model, UserMessages.MESSAGE_INVALID_CASE_DISPLAYED_INDEX);
    }

}
