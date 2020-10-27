package seedu.pivot.logic.commands;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.pivot.logic.commands.testutil.CommandTestUtil.assertCommandSuccess;
import static seedu.pivot.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import seedu.pivot.commons.core.index.Index;
import seedu.pivot.logic.state.StateManager;
import seedu.pivot.model.Model;
import seedu.pivot.model.ModelManager;

public class ReturnCommandTest {

    private static Index index = Index.fromZeroBased(INDEX_FIRST_PERSON.getZeroBased());
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @BeforeAll
    public static void setStateZero() {
        StateManager.setState(index);
    }

    @Test
    public void execute_return_success() {
        ReturnCommand returnCommand = new ReturnCommand();

        String expectedMessage = ReturnCommand.MESSAGE_RETURN_SUCCESS;

        assertCommandSuccess(returnCommand, model, expectedMessage, expectedModel);
        assertTrue(StateManager.atMainPage()); //check state
    }
}
