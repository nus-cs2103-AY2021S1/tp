package seedu.jarvis.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.jarvis.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.jarvis.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.jarvis.logic.commands.CommandTestUtil.showMasteryCheckAtIndex;
import static seedu.jarvis.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.jarvis.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.jarvis.testutil.TypicalMasteryChecks.TEST_MASTERY_CHECK_ONE;
import static seedu.jarvis.testutil.TypicalStudents.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.jarvis.commons.core.Messages;
import seedu.jarvis.commons.core.index.Index;
import seedu.jarvis.logic.commands.delete.DeleteMasteryCheckCommand;
import seedu.jarvis.logic.commands.exceptions.CommandException;
import seedu.jarvis.model.Model;
import seedu.jarvis.model.ModelManager;
import seedu.jarvis.model.masterycheck.MasteryCheck;
import seedu.jarvis.testutil.TypicalManagers;

public class DeleteMasteryCheckCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), TypicalManagers.getUserPrefs(),
            TypicalManagers.getUserLogin());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        model.addMasteryCheck(TEST_MASTERY_CHECK_ONE);

        MasteryCheck masteryCheckToDelete = model.getFilteredMasteryChecksList().get(INDEX_FIRST.getZeroBased());
        DeleteMasteryCheckCommand deleteCommand = new DeleteMasteryCheckCommand(INDEX_FIRST);

        String expectedMessage = String.format(
                DeleteMasteryCheckCommand.MESSAGE_SUCCESS, masteryCheckToDelete);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), TypicalManagers.getUserPrefs(),
                TypicalManagers.getUserLogin());
        expectedModel.deleteMasteryCheck(masteryCheckToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        model.addMasteryCheck(TEST_MASTERY_CHECK_ONE);

        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredMasteryChecksList().size() + 1);
        DeleteMasteryCheckCommand deleteCommand = new DeleteMasteryCheckCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_MASTERY_CHECK_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        model.addMasteryCheck(TEST_MASTERY_CHECK_ONE);

        showMasteryCheckAtIndex(model, INDEX_FIRST);

        MasteryCheck masteryCheckToDelete = model.getFilteredMasteryChecksList().get(INDEX_FIRST.getZeroBased());
        DeleteMasteryCheckCommand deleteCommand = new DeleteMasteryCheckCommand(INDEX_FIRST);

        String expectedMessage =
                String.format(DeleteMasteryCheckCommand.MESSAGE_SUCCESS, masteryCheckToDelete);

        Model expectedModel = new ModelManager(model.getAddressBook(), TypicalManagers.getUserPrefs(),
                TypicalManagers.getUserLogin());
        expectedModel.deleteMasteryCheck(masteryCheckToDelete);
        showNoMasteryChecks(expectedModel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        model.addMasteryCheck(TEST_MASTERY_CHECK_ONE);

        showMasteryCheckAtIndex(model, INDEX_FIRST);
        Index outOfBoundIndex = INDEX_SECOND;

        DeleteMasteryCheckCommand deleteCommand = new DeleteMasteryCheckCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_MASTERY_CHECK_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexMasteryChecksList_commandTargetFeatureAccurate() throws CommandException {
        model.addMasteryCheck(TEST_MASTERY_CHECK_ONE);
        DeleteMasteryCheckCommand deleteCommand = new DeleteMasteryCheckCommand(INDEX_FIRST);
        CommandResult commandResult = deleteCommand.execute(model);
        CommandTargetFeature actualTargetTab = commandResult.getCommandTargetFeature();

        assertEquals(CommandTargetFeature.MasteryCheck, actualTargetTab);
    }

    /**
     * Updates {@code model}'s filtered list to show no mastery checks.
     */
    private void showNoMasteryChecks(Model model) {
        model.updateFilteredMasteryCheckList(p -> false);
        assertTrue(model.getFilteredMasteryChecksList().isEmpty());
    }
}
