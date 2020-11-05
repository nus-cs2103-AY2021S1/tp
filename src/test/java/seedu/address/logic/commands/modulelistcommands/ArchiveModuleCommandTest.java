package seedu.address.logic.commands.modulelistcommands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showModuleAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_MODULE;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_MODULE;
import static seedu.address.testutil.TypicalModules.getTypicalModuleList;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.ContactList;
import seedu.address.model.EventList;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ModuleList;
import seedu.address.model.TodoList;
import seedu.address.model.UserPrefs;
import seedu.address.model.module.Module;



/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for EditCommand.
 */
public class ArchiveModuleCommandTest {
    private Model model = new ModelManager(getTypicalModuleList(), new ModuleList(), new ContactList(), new TodoList(),
            new EventList(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Module moduleToArchive = model.getModuleList().getModuleList()
                .get(INDEX_FIRST_MODULE.getZeroBased());
        ArchiveModuleCommand archiveModuleCommand = new ArchiveModuleCommand(INDEX_FIRST_MODULE);
        String expectedMessage = String.format(ArchiveModuleCommand.MESSAGE_ARCHIVE_MODULE_SUCCESS,
                moduleToArchive.getName().getName());
        Model expectedModel = new ModelManager(model.getModuleList(), new ModuleList(),
                new ContactList(), new TodoList(), new EventList(), new UserPrefs());
        expectedModel.archiveModule(moduleToArchive);
        assertCommandSuccess(archiveModuleCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredModuleList().size() + 1);
        ArchiveModuleCommand archiveModuleCommand = new ArchiveModuleCommand(outOfBoundIndex);
        assertCommandFailure(archiveModuleCommand, model, Messages.MESSAGE_INVALID_MODULE_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showModuleAtIndex(model, INDEX_FIRST_MODULE);
        Module moduleToArchive = model.getFilteredModuleList().get(INDEX_FIRST_MODULE.getZeroBased());
        ArchiveModuleCommand archiveModuleCommand = new ArchiveModuleCommand(INDEX_FIRST_MODULE);
        String expectedMessage = String.format(ArchiveModuleCommand.MESSAGE_ARCHIVE_MODULE_SUCCESS,
                moduleToArchive.getName());
        Model expectedModel = new ModelManager(model.getModuleList(), new ModuleList(),
                new ContactList(), new TodoList(), new EventList(), new UserPrefs());
        expectedModel.archiveModule(moduleToArchive);
        showNoModule(expectedModel);
        assertCommandSuccess(archiveModuleCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showModuleAtIndex(model, INDEX_FIRST_MODULE);
        Index outOfBoundIndex = INDEX_SECOND_MODULE;
        // ensures that outOfBoundIndex is still in bounds of archived module list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getModuleList().getModuleList().size());
        ArchiveModuleCommand archiveModuleCommand = new ArchiveModuleCommand(outOfBoundIndex);
        assertCommandFailure(archiveModuleCommand, model, Messages.MESSAGE_INVALID_MODULE_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        ArchiveModuleCommand archiveFirstCommand = new ArchiveModuleCommand(INDEX_FIRST_MODULE);
        ArchiveModuleCommand archiveSecondCommand = new ArchiveModuleCommand(INDEX_SECOND_MODULE);

        // same object -> returns true
        assertTrue(archiveFirstCommand.equals(archiveFirstCommand));

        // same values -> returns true
        ArchiveModuleCommand archiveFirstCommandCopy = new ArchiveModuleCommand(INDEX_FIRST_MODULE);
        assertTrue(archiveFirstCommand.equals(archiveFirstCommandCopy));

        // different types -> returns false
        assertFalse(archiveFirstCommand.equals(1));

        // null -> returns false
        assertFalse(archiveFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(archiveFirstCommand.equals(archiveSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoModule(Model model) {
        model.updateFilteredModuleList(p -> false);
        assertTrue(model.getFilteredModuleList().isEmpty());
    }

}
