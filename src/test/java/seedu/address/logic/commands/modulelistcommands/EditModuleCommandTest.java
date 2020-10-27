package seedu.address.logic.commands.modulelistcommands;

//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static seedu.address.logic.commands.CommandTestUtil.DESC_AMY;
//import static seedu.address.logic.commands.CommandTestUtil.DESC_BOB;
//import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
//import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
//import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
// import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
// import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
// import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.*;
import static seedu.address.testutil.TypicalIndexes.*;
import static seedu.address.testutil.TypicalModules.getTypicalModuleList;
// import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

//import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
// import seedu.address.model.Model;
// import seedu.address.model.ModelManager;
// import seedu.address.model.ModuleList;
// import seedu.address.model.UserPrefs;
import seedu.address.model.*;
//import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.model.module.Module;
import seedu.address.testutil.EditModuleDescriptorBuilder;
import seedu.address.testutil.ModuleBuilder;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for EditCommand.
 */
public class EditModuleCommandTest {

    private Model model = new ModelManager(getTypicalModuleList(), new ContactList(), new TodoList(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Module editedModule = new ModuleBuilder().build();
        EditModuleCommand.EditModuleDescriptor descriptor = new EditModuleDescriptorBuilder(editedModule).build();
        EditModuleCommand editModuleCommand = new EditModuleCommand("CS2030", descriptor);
        String expectedMessage = String.format(EditModuleCommand.MESSAGE_EDIT_MODULE_SUCCESS, editedModule);
        Model expectedModel = new ModelManager(new ModuleList(model.getModuleList()),
                new ContactList(), new TodoList(), new UserPrefs());
        expectedModel.setModule(model.getFilteredModuleList().get(0), editedModule);
        assertCommandSuccess(editModuleCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastModule = Index.fromOneBased(model.getFilteredModuleList().size());
        Module lastModule = model.getFilteredModuleList().get(indexLastModule.getZeroBased());
        ModuleBuilder moduleInList = new ModuleBuilder(lastModule);
        Module editedModule = moduleInList.withName(VALID_MODULENAME_CS2103T).withZoomLink(VALID_ZOOMLINK_CS2103T)
                     .withTags(VALID_TAG_CORE_MODULE).build();
        EditModuleCommand.EditModuleDescriptor descriptor = new EditModuleDescriptorBuilder()
                .withName(VALID_MODULENAME_CS2103T).withZoomLink(VALID_ZOOMLINK_CS2103T)
                .withTags(VALID_TAG_CORE_MODULE).build();
        EditModuleCommand editModuleCommand = new EditModuleCommand(lastModule.getName().fullName, descriptor);
        String expectedMessage = String.format(EditModuleCommand.MESSAGE_EDIT_MODULE_SUCCESS, editedModule);
        Model expectedModel = new ModelManager(new ModuleList(model.getModuleList()), new ContactList(),
                new TodoList(), new UserPrefs());
        expectedModel.setModule(lastModule, editedModule);
        assertCommandSuccess(editModuleCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditModuleCommand editModuleCommand = new EditModuleCommand("CS2030",
                new EditModuleCommand.EditModuleDescriptor());
        Module editedModule = model.getFilteredModuleList().get(INDEX_FIRST_MODULE.getZeroBased());
        String expectedMessage = String.format(EditModuleCommand.MESSAGE_EDIT_MODULE_SUCCESS, editedModule);
        Model expectedModel = new ModelManager(new ModuleList(model.getModuleList()), new ContactList(),
                new TodoList(), new UserPrefs());
        assertCommandSuccess(editModuleCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showModuleAtIndex(model, INDEX_FIRST_MODULE);
        Module moduleInFilteredList = model.getFilteredModuleList().get(INDEX_FIRST_MODULE.getZeroBased());
        Module editedModule = new ModuleBuilder(moduleInFilteredList).withName(VALID_MODULENAME_CS2030)
                .withZoomLink(VALID_ZOOMLINK_CS2030).withModularCredits(4.0).build();
        EditModuleCommand editModuleCommand = new EditModuleCommand("CS2030",
                new EditModuleDescriptorBuilder().withName(VALID_MODULENAME_CS2030).build());
        String expectedMessage = String.format(EditModuleCommand.MESSAGE_EDIT_MODULE_SUCCESS, editedModule);
        Model expectedModel = new ModelManager(new ModuleList(model.getModuleList()), new ContactList(),
                new TodoList(), new UserPrefs());
        expectedModel.setModule(model.getFilteredModuleList().get(0), editedModule);
        assertCommandSuccess(editModuleCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateModuleUnfilteredList_failure() {
        Module firstModule = model.getFilteredModuleList().get(INDEX_FIRST_MODULE.getZeroBased());
        EditModuleCommand.EditModuleDescriptor descriptor = new EditModuleDescriptorBuilder(firstModule).build();
        EditModuleCommand editModuleCommand = new EditModuleCommand("CS2101", descriptor);
        assertCommandFailure(editModuleCommand, model, EditModuleCommand.MESSAGE_DUPLICATE_MODULE);
    }

    @Test
    public void execute_duplicateModuleFilteredList_failure() {
        showModuleAtIndex(model, INDEX_FIRST_MODULE);
        // edit person in filtered list into a duplicate in module list
        Module moduleInList = model.getModuleList().getModuleList().get(INDEX_SECOND_MODULE.getZeroBased());
        EditModuleCommand editModuleCommand = new EditModuleCommand("CS2030",
            new EditModuleDescriptorBuilder(moduleInList).build());
        assertCommandFailure(editModuleCommand, model, EditModuleCommand.MESSAGE_DUPLICATE_MODULE);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        //Wait for editmodulecommand to become index based
        //Index outOfBoundIndex = Index.fromOneBased(model.getFilteredModuleList().size() + 1);
        //EditModuleCommand.EditModuleDescriptor descriptor = new EditModuleDescriptorBuilder()
        //  .withName(VALID_MODULENAME_CS2030).build();
        //EditModuleCommand editModuleCommand = new EditModuleCommand(outOfBoundIndex, descriptor);

        // assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        //Wait for editmodulecommand to become index based
        // showPersonAtIndex(model, INDEX_FIRST_PERSON);
        //Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        // assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        //EditCommand editCommand = new EditCommand(outOfBoundIndex,
        //        new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB).build());

        //assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditModuleCommand standardCommand = new EditModuleCommand("CS2030", DESC_CS2030);

        // same values -> returns true
        EditModuleCommand.EditModuleDescriptor copyDescriptor = new EditModuleCommand.EditModuleDescriptor(DESC_CS2030);
        EditModuleCommand commandWithSameValues = new EditModuleCommand("CS2030", copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new UndoCommand()));

        // different module name -> returns false
        assertFalse(standardCommand.equals(new EditModuleCommand("CS2103T", DESC_CS2030)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditModuleCommand("CS2103T", DESC_CS2103T)));
    }

}
