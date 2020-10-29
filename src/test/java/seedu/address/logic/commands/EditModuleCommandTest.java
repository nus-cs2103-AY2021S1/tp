package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalModules.getTypicalModuleList;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showModuleAtIndex;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.Trackr;
import seedu.address.model.UserPrefs;
import seedu.address.testutil.ModuleBuilder;
import seedu.address.model.module.Module;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for EditCommand.
 */
public class EditModuleCommandTest {

    private Model model = new ModelManager(getTypicalModuleList(), new UserPrefs());

    //    @Test
    //    public void execute_allFieldsSpecifiedUnfilteredList_success() {
    //        Module editedModule = new ModuleBuilder().build();
    //        EditModuleCommand editCommand = new EditModuleCommand(INDEX_FIRST_PERSON, "CS21");
    //
    //        String expectedMessage = String.format(EditModuleCommand.MESSAGE_EDIT_MODULE_SUCCESS, editedModule);
    //
    //        Model expectedModel = new ModelManager(new Trackr(model.getModuleList()), new UserPrefs());
    //        expectedModel.setModule(model.getFilteredModuleList().get(0), "CS21");
    //
    //        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    //    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditModuleCommand editCommand = new EditModuleCommand(INDEX_FIRST_PERSON, "CS21");
        Module editedModule = model.getFilteredModuleList().get(INDEX_FIRST_PERSON.getZeroBased());

        String expectedMessage = String.format(EditModuleCommand.MESSAGE_EDIT_MODULE_SUCCESS, editedModule);

        Model expectedModel = new ModelManager(new Trackr(model.getModuleList()), new UserPrefs());

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showModuleAtIndex(model, INDEX_FIRST_PERSON);

        Module moduleInFilteredList = model.getFilteredModuleList().get(INDEX_FIRST_PERSON.getZeroBased());
        Module editedModule = new ModuleBuilder(moduleInFilteredList).withModuleId("CS21").build();
        EditModuleCommand editCommand = new EditModuleCommand(INDEX_FIRST_PERSON,
                "CS21");

        String expectedMessage = String.format(EditModuleCommand.MESSAGE_EDIT_MODULE_SUCCESS, editedModule);

        Model expectedModel = new ModelManager(new Trackr(model.getModuleList()), new UserPrefs());
        expectedModel.setModule(model.getFilteredModuleList().get(0), "CS21");

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateModuleUnfilteredList_failure() {
        Module firstModule = model.getFilteredModuleList().get(INDEX_FIRST_PERSON.getZeroBased());
        //EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(firstPerson).build();
        EditModuleCommand editCommand = new EditModuleCommand(INDEX_SECOND_PERSON, firstModule.getModuleId().toString());

        assertCommandFailure(editCommand, model, EditModuleCommand.MESSAGE_DUPLICATE_MODULE);
    }

    @Test
    public void execute_duplicateModuleFilteredList_failure() {
        showModuleAtIndex(model, INDEX_FIRST_PERSON);

        // edit module in filtered list into a duplicate in address book
        Module moduleInList = model.getModuleList().getList().get(INDEX_SECOND_PERSON.getZeroBased());
        EditModuleCommand editCommand = new EditModuleCommand(INDEX_FIRST_PERSON,
                moduleInList.getModuleId().toString());

        assertCommandFailure(editCommand, model, EditModuleCommand.MESSAGE_DUPLICATE_MODULE);
    }

    @Test
    public void execute_invalidModuleIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredModuleList().size() + 1);
        EditModuleCommand editCommand = new EditModuleCommand(outOfBoundIndex, "CS21");

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

        EditModuleCommand editCommand = new EditModuleCommand(outOfBoundIndex, "CS21");

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_MODULE_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditModuleCommand standardCommand = new EditModuleCommand(INDEX_FIRST_PERSON, "CS21");

        // same values -> returns true
        EditModuleCommand commandWithSameValues = new EditModuleCommand(INDEX_FIRST_PERSON, "CS21");
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditModuleCommand(INDEX_SECOND_PERSON, "CS21")));

        // different descriptor, same values -> returns true
        assertTrue(standardCommand.equals(new EditModuleCommand(INDEX_FIRST_PERSON, "CS21")));
    }

}
