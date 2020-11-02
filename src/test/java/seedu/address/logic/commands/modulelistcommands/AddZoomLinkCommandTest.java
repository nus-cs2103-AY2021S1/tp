package seedu.address.logic.commands.modulelistcommands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULELESSONTYPE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULELESSONTYPE_ES2660;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ZOOMLINK_CS2030;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ZOOMLINK_CS2103T;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showModuleAtIndex;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalModules.getTypicalModuleList;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_CONTACT;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_MODULE;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_MODULE;

import org.junit.jupiter.api.Test;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.contactlistcommands.EditContactCommand;
import seedu.address.logic.commands.contactlistcommands.EditContactDescriptor;
import seedu.address.model.ArchivedModuleList;
import seedu.address.model.ContactList;
import seedu.address.model.EventList;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ModuleList;
import seedu.address.model.TodoList;
import seedu.address.model.UserPrefs;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleLesson;
import seedu.address.testutil.AddZoomDescriptorBuilder;
import seedu.address.testutil.EditContactDescriptorBuilder;
import seedu.address.testutil.ModuleBuilder;

public class AddZoomLinkCommandTest {

    private Model model = new ModelManager(getTypicalModuleList(), new ArchivedModuleList(),
            new ContactList(), new TodoList(), new EventList(), new UserPrefs());

    @Test
    public void constructor_nullIndex_throwsNullPointerException() {
        AddZoomDescriptor validDescriptor = new AddZoomDescriptor();
        assertThrows(NullPointerException.class, () -> new AddZoomLinkCommand(null, validDescriptor));
    }

    @Test
    public void constructor_nullDescriptor_throwsNullPointerException() {
        Index validIndex = INDEX_FIRST_MODULE;
        assertThrows(NullPointerException.class, () -> new AddZoomLinkCommand(validIndex, null));
    }

    @Test
    public void execute_nullModel_throwsNullPointerException() {
        Index validIndex = INDEX_FIRST_MODULE;
        AddZoomDescriptor validDescriptor = new AddZoomDescriptor();
        AddZoomLinkCommand command = new AddZoomLinkCommand(validIndex,validDescriptor);
        assertThrows(NullPointerException.class, () -> command.execute(null));
    }

    @Test
    public void execute_validZoomLinkAndLesson_addSuccess() {
        Index index = INDEX_FIRST_MODULE;
        Module firstModule = model.getFilteredModuleList().get(index.getZeroBased());;
        Module editedModule = new ModuleBuilder(firstModule).withZoomLink(new ModuleLesson(VALID_MODULELESSONTYPE),
                VALID_ZOOMLINK_CS2103T).build();

        AddZoomDescriptor descriptor = new AddZoomDescriptorBuilder()
                .withModuleLesson(VALID_MODULELESSONTYPE).withZoomLink(VALID_ZOOMLINK_CS2103T).build();
        AddZoomLinkCommand command = new AddZoomLinkCommand(INDEX_FIRST_MODULE, descriptor);

        String expectedMessage = String.format(AddZoomLinkCommand.MESSAGE_ADD_ZOOM_SUCCESS, VALID_ZOOMLINK_CS2103T);

        Model expectedModel = new ModelManager(new ModuleList(model.getModuleList()), new ArchivedModuleList(),
                new ContactList(), new TodoList(), new EventList(), new UserPrefs());
        expectedModel.setModule(firstModule, editedModule);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidModuleIndexUnfilteredList_addFailure() {
        int outOfBoundIndex = model.getFilteredModuleList().size() + 1;
        Index invalidIndex = Index.fromOneBased(outOfBoundIndex);
        AddZoomDescriptor descriptor = new AddZoomDescriptorBuilder()
                .withModuleLesson(VALID_MODULELESSONTYPE).withZoomLink(VALID_ZOOMLINK_CS2030).build();
        AddZoomLinkCommand addZoomLinkCommand = new AddZoomLinkCommand(invalidIndex, descriptor);

        assertCommandFailure(addZoomLinkCommand, model, Messages.MESSAGE_INVALID_MODULE_DISPLAYED_INDEX);
    }

    /**
     * Add zoom link to module in the filtered module list where index is larger than size of filtered list,
     * but smaller than size of the module list.
     */
    @Test
    public void execute_invalidModuleIndexFilteredList_editFailure() {
        // update module filtered list to contain only a single module
        showModuleAtIndex(model, INDEX_FIRST_MODULE);

        Index outOfBoundIndex = INDEX_SECOND_MODULE;

        // ensures that outOfBoundIndex is still in bounds of contact list
        int moduleListSize = model.getModuleList().getModuleList().size();
        assertTrue(outOfBoundIndex.getZeroBased() < moduleListSize);

        AddZoomDescriptor descriptor = new AddZoomDescriptorBuilder()
                .withModuleLesson(VALID_MODULELESSONTYPE).withZoomLink(VALID_ZOOMLINK_CS2030).build();
        AddZoomLinkCommand addZoomLinkCommand = new AddZoomLinkCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(addZoomLinkCommand, model, Messages.MESSAGE_INVALID_CONTACT_DISPLAYED_INDEX);
    }
}
