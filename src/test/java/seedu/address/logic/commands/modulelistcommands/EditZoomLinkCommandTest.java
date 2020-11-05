package seedu.address.logic.commands.modulelistcommands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_LESSON_LECTURE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_LESSON_TUTORIAL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ZOOM_LINK_CS2030;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ZOOM_LINK_CS2103T;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showModuleAtIndex;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_MODULE;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_MODULE;
import static seedu.address.testutil.TypicalModules.getTypicalModuleList;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.model.ContactList;
import seedu.address.model.EventList;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ModuleList;
import seedu.address.model.TodoList;
import seedu.address.model.UserPrefs;
import seedu.address.model.module.Module;
import seedu.address.testutil.ModuleBuilder;
import seedu.address.testutil.ZoomDescriptorBuilder;

public class EditZoomLinkCommandTest {

    private Model model = new ModelManager(getTypicalModuleList(), new ModuleList(),
            new ContactList(), new TodoList(), new EventList(), new UserPrefs());

    @Test
    public void constructor_nullIndex_throwsNullPointerException() {
        ZoomDescriptor validDescriptor = new ZoomDescriptor();
        assertThrows(NullPointerException.class, () -> new EditZoomLinkCommand(null, validDescriptor));
    }

    @Test
    public void constructor_nullDescriptor_throwsNullPointerException() {
        Index validIndex = INDEX_FIRST_MODULE;
        assertThrows(NullPointerException.class, () -> new EditZoomLinkCommand(validIndex, null));
    }

    @Test
    public void execute_nullModel_throwsNullPointerException() {
        Index validIndex = INDEX_FIRST_MODULE;
        ZoomDescriptor validDescriptor = new ZoomDescriptor();
        EditZoomLinkCommand command = new EditZoomLinkCommand(validIndex, validDescriptor);
        assertThrows(NullPointerException.class, () -> command.execute(null));
    }

    @Test
    public void execute_validZoomLinkAndLessonUnfilteredList_editSuccess() {
        Module moduleToUpdate = model.getFilteredModuleList().get(INDEX_FIRST_MODULE.getZeroBased());
        Module moduleWithEditedZoom = new ModuleBuilder(moduleToUpdate)
                .withZoomLink(VALID_MODULE_LESSON_LECTURE, VALID_ZOOM_LINK_CS2103T).build();

        ZoomDescriptor descriptor = new ZoomDescriptorBuilder()
                .withModuleLesson(VALID_MODULE_LESSON_LECTURE).withZoomLink(VALID_ZOOM_LINK_CS2103T).build();
        EditZoomLinkCommand editZoomLinkCommand = new EditZoomLinkCommand(INDEX_FIRST_MODULE, descriptor);

        String expectedMessage = String.format(EditZoomLinkCommand.MESSAGE_EDIT_ZOOM_SUCCESS,
                VALID_ZOOM_LINK_CS2103T, VALID_MODULE_LESSON_LECTURE);

        Model expectedModel = new ModelManager(new ModuleList(model.getModuleList()), new ModuleList(),
                new ContactList(), new TodoList(), new EventList(), new UserPrefs());
        expectedModel.setModule(moduleToUpdate, moduleWithEditedZoom);

        assertCommandSuccess(editZoomLinkCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validZoomLinkAndLessonFilteredList_editSuccess() {
        showModuleAtIndex(model, INDEX_FIRST_MODULE);
        Module moduleInFilteredList = model.getFilteredModuleList().get(INDEX_FIRST_MODULE.getZeroBased());
        Module moduleWithEditedZoom = new ModuleBuilder(moduleInFilteredList)
                .withZoomLink(VALID_MODULE_LESSON_LECTURE, VALID_ZOOM_LINK_CS2103T).build();

        ZoomDescriptor descriptor = new ZoomDescriptorBuilder()
                .withModuleLesson(VALID_MODULE_LESSON_LECTURE).withZoomLink(VALID_ZOOM_LINK_CS2103T).build();
        EditZoomLinkCommand editZoomLinkCommand = new EditZoomLinkCommand(INDEX_FIRST_MODULE, descriptor);

        String expectedMessage = String.format(EditZoomLinkCommand.MESSAGE_EDIT_ZOOM_SUCCESS,
                VALID_ZOOM_LINK_CS2103T, VALID_MODULE_LESSON_LECTURE);

        Model expectedModel = new ModelManager(new ModuleList(model.getModuleList()), new ModuleList(),
                new ContactList(), new TodoList(), new EventList(), new UserPrefs());
        showModuleAtIndex(expectedModel, INDEX_FIRST_MODULE);
        expectedModel.setModule(model.getFilteredModuleList().get(0), moduleWithEditedZoom);
        assertCommandSuccess(editZoomLinkCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidModuleIndexUnfilteredList_throwsCommandException() {
        int outOfBoundIndex = model.getFilteredModuleList().size() + 1;
        Index invalidIndex = Index.fromOneBased(outOfBoundIndex);
        ZoomDescriptor descriptor = new ZoomDescriptorBuilder()
                .withModuleLesson(VALID_MODULE_LESSON_LECTURE).withZoomLink(VALID_ZOOM_LINK_CS2030).build();
        EditZoomLinkCommand editZoomLinkCommand = new EditZoomLinkCommand(invalidIndex, descriptor);

        assertCommandFailure(editZoomLinkCommand, model, Messages.MESSAGE_INVALID_MODULE_DISPLAYED_INDEX);
    }

    /**
     * Edit zoom link in module in the filtered module list where index is larger than size of filtered list,
     * but smaller than size of the module list.
     */
    @Test
    public void execute_invalidModuleIndexFilteredList_throwsCommandException() {
        // update module filtered list to contain only a single module
        showModuleAtIndex(model, INDEX_FIRST_MODULE);

        Index outOfBoundIndex = INDEX_SECOND_MODULE;

        // ensures that outOfBoundIndex is still in bounds of module list
        int moduleListSize = model.getModuleList().getModuleList().size();
        assertTrue(outOfBoundIndex.getZeroBased() < moduleListSize);

        ZoomDescriptor descriptor = new ZoomDescriptorBuilder()
                .withModuleLesson(VALID_MODULE_LESSON_LECTURE).withZoomLink(VALID_ZOOM_LINK_CS2030).build();
        EditZoomLinkCommand editZoomLinkCommand = new EditZoomLinkCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editZoomLinkCommand, model, Messages.MESSAGE_INVALID_MODULE_DISPLAYED_INDEX);
    }

    @Test
    public void execute_duplicateZoomLink_throwsCommandException() {
        ZoomDescriptor descriptor = new ZoomDescriptorBuilder()
                .withModuleLesson(VALID_MODULE_LESSON_LECTURE).withZoomLink(VALID_ZOOM_LINK_CS2030).build();
        EditZoomLinkCommand editZoomLinkCommand = new EditZoomLinkCommand(INDEX_FIRST_MODULE, descriptor);

        assertCommandFailure(editZoomLinkCommand, model, EditZoomLinkCommand.MESSAGE_DUPLICATE_ZOOM);
    }

    @Test
    public void execute_invalidLesson_throwsCommandException() {
        ZoomDescriptor descriptor = new ZoomDescriptorBuilder()
                .withModuleLesson(VALID_MODULE_LESSON_TUTORIAL).withZoomLink(VALID_ZOOM_LINK_CS2103T).build();
        EditZoomLinkCommand command = new EditZoomLinkCommand(INDEX_FIRST_MODULE, descriptor);

        assertCommandFailure(command, model, EditZoomLinkCommand.MESSAGE_INVALID_LESSON);
    }

    @Test
    public void equals() {

        ZoomDescriptor descriptor = new ZoomDescriptorBuilder()
                .withModuleLesson(VALID_MODULE_LESSON_LECTURE).withZoomLink(VALID_ZOOM_LINK_CS2030).build();
        final EditZoomLinkCommand standardCommand = new EditZoomLinkCommand(INDEX_FIRST_MODULE, descriptor);

        // same index and descriptor -> returns true
        ZoomDescriptor copyDescriptor = new ZoomDescriptorBuilder()
                .withModuleLesson(VALID_MODULE_LESSON_LECTURE).withZoomLink(VALID_ZOOM_LINK_CS2030).build();
        EditZoomLinkCommand commandWithSameValues = new EditZoomLinkCommand(INDEX_FIRST_MODULE, copyDescriptor);

        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different type -> returns false
        assertFalse(standardCommand.equals(8));

        // different command types -> returns false
        assertFalse(standardCommand.equals(new ExitCommand()));

        // different index, same descriptor -> returns false
        assertFalse(standardCommand.equals(new EditZoomLinkCommand(INDEX_SECOND_MODULE, descriptor)));

        // different descriptor, same index -> returns false
        ZoomDescriptor editedDescriptor = new ZoomDescriptorBuilder()
                .withModuleLesson(VALID_MODULE_LESSON_TUTORIAL).withZoomLink(VALID_ZOOM_LINK_CS2030).build();
        assertFalse(standardCommand.equals(new EditZoomLinkCommand(INDEX_FIRST_MODULE, editedDescriptor)));
    }

}
