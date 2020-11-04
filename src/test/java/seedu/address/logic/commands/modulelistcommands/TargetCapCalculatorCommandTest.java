package seedu.address.logic.commands.modulelistcommands;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.modulelistparsers.TargetCapCalculatorParser;
import seedu.address.model.*;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.*;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_MODULE;
import static seedu.address.testutil.TypicalModules.getTypicalModuleList;

public class TargetCapCalculatorCommandTest {
    private static double VALID_TARGET_CAP = 5.0;
    private static double INVALID_TARGET_CAP = 6.0;
    private Model model = new ModelManager(getTypicalModuleList(), new ModuleList(),
            new ContactList(), new TodoList(), new EventList(), new UserPrefs());
    @Test
    public void execute_noCompletedModules_throwsCommandException() {
        Model blankModel = new ModelManager(new ModuleList(), new ModuleList(),
                new ContactList(), new TodoList(), new EventList(), new UserPrefs());
        assertThrows(CommandException.class, () -> new TargetCapCalculatorCommand(VALID_TARGET_CAP)
                        .execute(blankModel));
    }

    @Test
    public void execute_nullModel_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TargetCapCalculatorCommand(VALID_TARGET_CAP)
                .execute(null));
    }

    @Test
    public void execute_invalidTargetCap_throwsCommandException() {
        TargetCapCalculatorCommand targetCapCalculatorCommand = new TargetCapCalculatorCommand(INVALID_TARGET_CAP);
        assertCommandFailure(targetCapCalculatorCommand, model, TargetCapCalculatorCommand.MESSAGE_IMPOSSIBLE_TARGET);
    }

    @Test
    public void execute_filteredListSameAsUnfilteredList_calculateCapSuccess() {
        showModuleAtIndex(model, INDEX_FIRST_MODULE);
        String expectedMessage;
        try {
            expectedMessage = new TargetCapCalculatorCommand(VALID_TARGET_CAP).execute(model).getFeedbackToUser();
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
        ModelManager expectedModel = new ModelManager(model.getModuleList(), model.getArchivedModuleList(),
                model.getContactList(), model.getTodoList(), model.getEventList(), new UserPrefs());
        showModuleAtIndex(expectedModel, INDEX_FIRST_MODULE);
        assertCommandSuccess(new TargetCapCalculatorCommand(VALID_TARGET_CAP), model, expectedMessage, expectedModel);
    }

}
