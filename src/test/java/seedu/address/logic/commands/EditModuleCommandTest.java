package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showModuleAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalModules.getTypicalModuleList;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditModuleCommand.EditModuleDescriptor;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.Trackr;
import seedu.address.model.UserPrefs;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleId;
import seedu.address.testutil.ModuleBuilder;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for EditCommand.
 */
public class EditModuleCommandTest {

    private Model model = new ModelManager(getTypicalModuleList(), new UserPrefs());

    /*
        @Test
        public void execute_allFieldsSpecifiedUnfilteredList_success() {
            Module editedModule = new ModuleBuilder().build();
            EditModuleCommand editCommand = new EditModuleCommand(INDEX_FIRST_PERSON, "CS21");

            String expectedMessage = String.format(EditModuleCommand.MESSAGE_EDIT_MODULE_SUCCESS, editedModule);

            Model expectedModel = new ModelManager(new Trackr(model.getModuleList()), new UserPrefs());
            expectedModel.setModule(model.getFilteredModuleList().get(0), "CS21");

            assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
        }
     */

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditModuleDescriptor editModuleDescriptor = new EditModuleDescriptor();
        EditModuleCommand editCommand = new EditModuleCommand(INDEX_FIRST_PERSON, editModuleDescriptor);
        Module editedModule = model.getFilteredModuleList().get(INDEX_FIRST_PERSON.getZeroBased());

        String expectedMessage = String.format(EditModuleCommand.MESSAGE_EDIT_MODULE_SUCCESS, editedModule);

        Model expectedModel = new ModelManager(new Trackr(model.getModuleList()), new UserPrefs());

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showModuleAtIndex(model, INDEX_FIRST_PERSON);
        EditModuleDescriptor editModuleDescriptor = new EditModuleDescriptor();
        editModuleDescriptor.setModuleId(new ModuleId("CS22"));
        Module moduleInFilteredList = model.getFilteredModuleList().get(INDEX_FIRST_PERSON.getZeroBased());
        Module editedModule = new ModuleBuilder(moduleInFilteredList).withModuleId("CS22").build();
        EditModuleCommand editCommand = new EditModuleCommand(INDEX_FIRST_PERSON,
                editModuleDescriptor);

        String expectedMessage = String.format(EditModuleCommand.MESSAGE_EDIT_MODULE_SUCCESS, editedModule);

        Model expectedModel = new ModelManager(new Trackr(model.getModuleList()), new UserPrefs());
        Module target = model.getFilteredModuleList().get(0);
        expectedModel.setModule(target, new Module(new ModuleId("CS22"), target.getUniqueTutorialGroupList()));

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateModuleUnfilteredList_failure() {
        Module firstModule = model.getFilteredModuleList().get(INDEX_FIRST_PERSON.getZeroBased());
        EditModuleDescriptor editModuleDescriptor = new EditModuleDescriptor();
        editModuleDescriptor.setModuleId(firstModule.getModuleId());
        EditModuleCommand editCommand = new EditModuleCommand(INDEX_SECOND_PERSON, editModuleDescriptor);

        assertCommandFailure(editCommand, model, EditModuleCommand.MESSAGE_DUPLICATE_MODULE);
    }

    @Test
    public void execute_duplicateModuleFilteredList_failure() {
        showModuleAtIndex(model, INDEX_FIRST_PERSON);

        // edit module in filtered list into a duplicate in address book
        Module moduleInList = model.getModuleList().getList().get(INDEX_SECOND_PERSON.getZeroBased());
        EditModuleDescriptor editModuleDescriptor = new EditModuleDescriptor();
        editModuleDescriptor.setModuleId(moduleInList.getModuleId());
        EditModuleCommand editCommand = new EditModuleCommand(INDEX_FIRST_PERSON,
                editModuleDescriptor);

        assertCommandFailure(editCommand, model, EditModuleCommand.MESSAGE_DUPLICATE_MODULE);
    }

    @Test
    public void execute_invalidModuleIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredModuleList().size() + 1);
        EditModuleDescriptor editModuleDescriptor = new EditModuleDescriptor();
        editModuleDescriptor.setModuleId(new ModuleId("CS22"));
        EditModuleCommand editCommand = new EditModuleCommand(outOfBoundIndex, editModuleDescriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_MODULE_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidModuleIndexFilteredList_failure() {
        showModuleAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getModuleList().getList().size());

        EditModuleDescriptor editModuleDescriptor = new EditModuleDescriptor();
        editModuleDescriptor.setModuleId(new ModuleId("CS22"));
        EditModuleCommand editCommand = new EditModuleCommand(outOfBoundIndex, editModuleDescriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_MODULE_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        EditModuleDescriptor editModuleDescriptor = new EditModuleDescriptor();
        editModuleDescriptor.setModuleId(new ModuleId("CS22"));
        final EditModuleCommand standardCommand = new EditModuleCommand(INDEX_FIRST_PERSON, editModuleDescriptor);

        // same values -> returns true
        EditModuleCommand commandWithSameValues = new EditModuleCommand(INDEX_FIRST_PERSON, editModuleDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditModuleCommand(INDEX_SECOND_PERSON, editModuleDescriptor)));

        // different descriptor, same values -> returns true
        assertTrue(standardCommand.equals(new EditModuleCommand(INDEX_FIRST_PERSON, editModuleDescriptor)));
    }

}
