package seedu.jarvis.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.jarvis.logic.commands.CommandTestUtil.DESC_MC_ONE;
import static seedu.jarvis.logic.commands.CommandTestUtil.DESC_MC_TWO;
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
import seedu.jarvis.logic.commands.edit.EditMasteryCheckCommand;
import seedu.jarvis.logic.commands.exceptions.CommandException;
import seedu.jarvis.model.AddressBook;
import seedu.jarvis.model.Model;
import seedu.jarvis.model.ModelManager;
import seedu.jarvis.model.masterycheck.MasteryCheck;
import seedu.jarvis.testutil.EditMasteryCheckDescriptorBuilder;
import seedu.jarvis.testutil.MasteryCheckBuilder;
import seedu.jarvis.testutil.TypicalManagers;

/**
 * Contains integration tests and unit tests for EditMasteryCheckCommand.
 */
public class EditMasteryCheckCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), TypicalManagers.getUserPrefs(),
            TypicalManagers.getUserLogin());

    @Test
    public void execute_filteredList_success() {
        model.addMasteryCheck(TEST_MASTERY_CHECK_ONE);

        showMasteryCheckAtIndex(model, INDEX_FIRST);

        MasteryCheck masteryCheckInFilteredList = model.getFilteredMasteryChecksList().get(INDEX_FIRST.getZeroBased());
        MasteryCheck editedMasteryCheck =
                new MasteryCheckBuilder(masteryCheckInFilteredList).withHasPassed(false).build();
        EditMasteryCheckCommand editMasteryCheckCommand = new EditMasteryCheckCommand(INDEX_FIRST,
                new EditMasteryCheckDescriptorBuilder().withHasPassed(false).build());

        String expectedMessage = String.format(EditMasteryCheckCommand.MESSAGE_EDIT_MASTERY_CHECK_SUCCESS,
                editedMasteryCheck);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), TypicalManagers.getUserPrefs(),
                TypicalManagers.getUserLogin());
        expectedModel.setMasteryCheck(model.getFilteredMasteryChecksList().get(0), editedMasteryCheck);

        assertCommandSuccess(editMasteryCheckCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        model.addMasteryCheck(TEST_MASTERY_CHECK_ONE);

        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredMasteryChecksList().size() + 1);
        EditMasteryCheckCommand.EditMasteryCheckDescriptor descriptor =
                new EditMasteryCheckDescriptorBuilder().withHasPassed(false).build();
        EditMasteryCheckCommand editMasteryCheckCommand = new EditMasteryCheckCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editMasteryCheckCommand, model, Messages.MESSAGE_INVALID_MASTERY_CHECK_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        model.addMasteryCheck(TEST_MASTERY_CHECK_ONE);

        showMasteryCheckAtIndex(model, INDEX_FIRST);
        Index outOfBoundIndex = INDEX_SECOND;

        EditMasteryCheckCommand editMasteryCheckCommand = new EditMasteryCheckCommand(outOfBoundIndex,
                new EditMasteryCheckDescriptorBuilder().withHasPassed(false).build());

        assertCommandFailure(editMasteryCheckCommand, model, Messages.MESSAGE_INVALID_MASTERY_CHECK_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditMasteryCheckCommand standardCommand = new EditMasteryCheckCommand(INDEX_FIRST, DESC_MC_ONE);

        // same values -> returns true
        EditMasteryCheckCommand.EditMasteryCheckDescriptor copyDescriptor =
                new EditMasteryCheckCommand.EditMasteryCheckDescriptor(DESC_MC_ONE);
        EditMasteryCheckCommand commandWithSameValues = new EditMasteryCheckCommand(INDEX_FIRST, copyDescriptor);

        boolean b = standardCommand.equals(commandWithSameValues);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ExitCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditMasteryCheckCommand(INDEX_SECOND, DESC_MC_ONE)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditMasteryCheckCommand(INDEX_FIRST, DESC_MC_TWO)));
    }

    @Test
    public void execute_filteredListSuccess_commandTargetFeatureAccurate() throws CommandException {
        model.addMasteryCheck(TEST_MASTERY_CHECK_ONE);
        EditMasteryCheckCommand editMasteryCheckCommand = new EditMasteryCheckCommand(INDEX_FIRST,
                new EditMasteryCheckDescriptorBuilder().withHasPassed(false).build());
        CommandResult commandResult = editMasteryCheckCommand.execute(model);
        CommandTargetFeature commandTargetFeature = commandResult.getCommandTargetFeature();

        assertEquals(CommandTargetFeature.MasteryCheck, commandTargetFeature);
    }
}
