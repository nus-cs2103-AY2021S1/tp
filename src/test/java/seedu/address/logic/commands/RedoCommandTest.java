package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULENAME_CS2030;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_LESSON_LECTURE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_LESSON_TUTORIAL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_LECTURE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ZOOMLINKS_CS2103T;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ZOOM_LINK_CS2103T;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_MODULE;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ZOOM_LINK;
import static seedu.address.testutil.TypicalModules.CS2030;
import static seedu.address.testutil.TypicalModules.ES2660;
import static seedu.address.testutil.TypicalModules.getTypicalModuleList;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.modulelistcommands.AddCompletedModuleCommand;
import seedu.address.logic.commands.modulelistcommands.AddModuleCommand;
import seedu.address.logic.commands.modulelistcommands.AddZoomLinkCommand;
import seedu.address.logic.commands.modulelistcommands.ArchiveModuleCommand;
import seedu.address.logic.commands.modulelistcommands.ClearModuleCommand;
import seedu.address.logic.commands.modulelistcommands.DeleteModuleCommand;
import seedu.address.logic.commands.modulelistcommands.DeleteZoomLinkCommand;
import seedu.address.logic.commands.modulelistcommands.EditModuleCommand;
import seedu.address.logic.commands.modulelistcommands.EditZoomLinkCommand;
import seedu.address.logic.commands.modulelistcommands.RedoCommand;
import seedu.address.logic.commands.modulelistcommands.UnarchiveModuleCommand;
import seedu.address.logic.commands.modulelistcommands.UndoCommand;
import seedu.address.model.ContactList;
import seedu.address.model.EventList;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ModuleList;
import seedu.address.model.TodoList;
import seedu.address.model.UserPrefs;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleLesson;
import seedu.address.model.module.ZoomLink;
import seedu.address.testutil.EditModuleDescriptorBuilder;
import seedu.address.testutil.ModuleBuilder;
import seedu.address.testutil.ZoomDescriptorBuilder;

public class RedoCommandTest {
    private static String expectedUndoMessage = UndoCommand.MESSAGE_UNDO_COMMAND_SUCCESS;
    private static String expectedRedoMessage = RedoCommand.MESSAGE_REDO_COMMAND_SUCCESS;

    @Test
    public void execute_noFutureRedo_throwsCommandException() {
        Model moduleModel = new ModelManager(getTypicalModuleList() , new ModuleList(), new ContactList(),
                new TodoList(), new EventList(), new UserPrefs());
        assertThrows(CommandException.class, ModelManager.MESSAGE_NO_REDO_HISTORY, () ->
                new RedoCommand().execute(moduleModel));
    }
    @Test
    public void execute_addModuleRedo_success() {
        Model moduleModel = new ModelManager(getTypicalModuleList() , new ModuleList(), new ContactList(),
                new TodoList(), new EventList(), new UserPrefs());
        Model expectedModel = new ModelManager(new ModuleList(moduleModel.getModuleList()), new ModuleList(),
                new ContactList(), new TodoList(), new EventList(), new UserPrefs());
        Model commitCommandModel = new ModelManager(new ModuleList(moduleModel.getModuleList()), new ModuleList(),
                new ContactList(), new TodoList(), new EventList(), new UserPrefs());
        commitCommandModel.addModule(ES2660);
        AddModuleCommand addModuleCommand = new AddModuleCommand(ES2660);
        String expectedCommitMessage = String.format(AddModuleCommand.MESSAGE_SUCCESS, ES2660);
        //Make sure Commit Command is successful
        assertCommandSuccess(addModuleCommand, moduleModel, expectedCommitMessage, commitCommandModel);
        //Make sure Undo Command is successful
        assertCommandSuccess(new UndoCommand(), moduleModel, expectedUndoMessage, expectedModel);
        assertCommandSuccess(new RedoCommand(), moduleModel, expectedRedoMessage, commitCommandModel);
    }
    @Test
    public void execute_addCompletedModuleRedo_success() {
        Model moduleModel = new ModelManager(getTypicalModuleList() , new ModuleList(), new ContactList(),
                new TodoList(), new EventList(), new UserPrefs());
        Module completedModule = new ModuleBuilder(ES2660).withTag("completed").build();
        Model expectedModel = new ModelManager(new ModuleList(moduleModel.getModuleList()), new ModuleList(),
                new ContactList(), new TodoList(), new EventList(), new UserPrefs());
        Model commitCommandModel = new ModelManager(new ModuleList(moduleModel.getModuleList()), new ModuleList(),
                new ContactList(), new TodoList(), new EventList(), new UserPrefs());
        commitCommandModel.addModule(completedModule);
        AddCompletedModuleCommand addCompletedModuleCommand = new AddCompletedModuleCommand(completedModule);
        String expectedCommitMessage = String.format(AddCompletedModuleCommand.MESSAGE_SUCCESS, completedModule);
        //Make sure Commit Command is successful
        assertCommandSuccess(addCompletedModuleCommand, moduleModel, expectedCommitMessage, commitCommandModel);
        //Make sure Undo Command is successful
        assertCommandSuccess(new UndoCommand(), moduleModel, expectedUndoMessage, expectedModel);
        assertCommandSuccess(new RedoCommand(), moduleModel, expectedRedoMessage, commitCommandModel);
    }
    @Test
    public void execute_addZoomLinkRedo_success() {
        Model moduleModel = new ModelManager(getTypicalModuleList() , new ModuleList(), new ContactList(),
                new TodoList(), new EventList(), new UserPrefs());
        HashMap<ModuleLesson, ZoomLink> validZoomLink = new HashMap<>();
        validZoomLink.put(new ModuleLesson(VALID_MODULE_LESSON_TUTORIAL), new ZoomLink(VALID_ZOOM_LINK_CS2103T));
        Module editedModule = new ModuleBuilder(CS2030)
                .withZoomLink(VALID_MODULE_LESSON_TUTORIAL, VALID_ZOOM_LINK_CS2103T).build();
        Model expectedModel = new ModelManager(new ModuleList(moduleModel.getModuleList()), new ModuleList(),
                new ContactList(), new TodoList(), new EventList(), new UserPrefs());
        Model commitCommandModel = new ModelManager(new ModuleList(moduleModel.getModuleList()), new ModuleList(),
                new ContactList(), new TodoList(), new EventList(), new UserPrefs());
        commitCommandModel.setModule(CS2030, editedModule);
        AddZoomLinkCommand addZoomLinkCommand = new AddZoomLinkCommand(INDEX_FIRST_MODULE,
                new ZoomDescriptorBuilder().withModuleLesson(VALID_MODULE_LESSON_TUTORIAL)
                        .withZoomLink(VALID_ZOOM_LINK_CS2103T).build());

        String expectedCommitMessage = String.format(AddZoomLinkCommand.MESSAGE_ADD_ZOOM_SUCCESS,
                VALID_ZOOM_LINK_CS2103T);
        //Make sure Commit Command is successful
        assertCommandSuccess(addZoomLinkCommand, moduleModel, expectedCommitMessage, commitCommandModel);
        //Make sure Undo Command is successful
        assertCommandSuccess(new UndoCommand(), moduleModel, expectedUndoMessage, expectedModel);
        assertCommandSuccess(new RedoCommand(), moduleModel, expectedRedoMessage, commitCommandModel);
    }
    @Test
    public void execute_archiveModuleRedo_success() {
        Model moduleModel = new ModelManager(getTypicalModuleList() , new ModuleList(), new ContactList(),
                new TodoList(), new EventList(), new UserPrefs());
        Model expectedModel = new ModelManager(new ModuleList(moduleModel.getModuleList()), new ModuleList(),
                new ContactList(), new TodoList(), new EventList(), new UserPrefs());
        Model commitCommandModel = new ModelManager(new ModuleList(moduleModel.getModuleList()), new ModuleList(),
                new ContactList(), new TodoList(), new EventList(), new UserPrefs());
        commitCommandModel.archiveModule(CS2030);
        ArchiveModuleCommand archiveModuleCommand = new ArchiveModuleCommand(INDEX_FIRST_MODULE);
        String expectedCommitMessage = String.format(ArchiveModuleCommand.MESSAGE_ARCHIVE_MODULE_SUCCESS, CS2030);
        //Make sure Commit Command is successful
        assertCommandSuccess(archiveModuleCommand, moduleModel, expectedCommitMessage, commitCommandModel);
        //Make sure Undo Command is successful
        assertCommandSuccess(new UndoCommand(), moduleModel, expectedUndoMessage, expectedModel);
        assertCommandSuccess(new RedoCommand(), moduleModel, expectedRedoMessage, commitCommandModel);
    }
    @Test
    public void execute_clearModuleRedo_success() {
        Model moduleModel = new ModelManager(getTypicalModuleList() , new ModuleList(), new ContactList(),
                new TodoList(), new EventList(), new UserPrefs());
        Model expectedModel = new ModelManager(new ModuleList(moduleModel.getModuleList()), new ModuleList(),
                new ContactList(), new TodoList(), new EventList(), new UserPrefs());
        Model commitCommandModel = new ModelManager(new ModuleList(moduleModel.getModuleList()), new ModuleList(),
                new ContactList(), new TodoList(), new EventList(), new UserPrefs());
        commitCommandModel.setModuleList(new ModuleList());
        ClearModuleCommand clearModuleCommand = new ClearModuleCommand();
        String expectedCommitMessage = ClearModuleCommand.MESSAGE_SUCCESS;
        //Make sure Commit Command is successful
        assertCommandSuccess(clearModuleCommand, moduleModel, expectedCommitMessage, commitCommandModel);
        //Make sure Undo Command is successful
        assertCommandSuccess(new UndoCommand(), moduleModel, expectedUndoMessage, expectedModel);
        assertCommandSuccess(new RedoCommand(), moduleModel, expectedRedoMessage, commitCommandModel);
    }
    @Test
    public void execute_deleteModuleRedo_success() {
        Model moduleModel = new ModelManager(getTypicalModuleList() , new ModuleList(), new ContactList(),
                new TodoList(), new EventList(), new UserPrefs());
        Model expectedModel = new ModelManager(new ModuleList(moduleModel.getModuleList()), new ModuleList(),
                new ContactList(), new TodoList(), new EventList(), new UserPrefs());
        Model commitCommandModel = new ModelManager(new ModuleList(moduleModel.getModuleList()), new ModuleList(),
                new ContactList(), new TodoList(), new EventList(), new UserPrefs());
        commitCommandModel.deleteModule(CS2030);
        DeleteModuleCommand deleteModuleCommand = new DeleteModuleCommand(INDEX_FIRST_MODULE);
        String expectedCommitMessage = String.format(DeleteModuleCommand.MESSAGE_DELETE_MODULE_SUCCESS, CS2030);
        //Make sure Commit Command is successful
        assertCommandSuccess(deleteModuleCommand, moduleModel, expectedCommitMessage, commitCommandModel);
        //Make sure Undo Command is successful
        assertCommandSuccess(new UndoCommand(), moduleModel, expectedUndoMessage, expectedModel);
        assertCommandSuccess(new RedoCommand(), moduleModel, expectedRedoMessage, commitCommandModel);
    }
    @Test
    public void execute_deleteZoomLinkUndo_success() {
        Model moduleModel = new ModelManager(getTypicalModuleList() , new ModuleList(), new ContactList(),
                new TodoList(), new EventList(), new UserPrefs());
        ModuleLesson lecture = new ModuleLesson(VALID_MODULE_LESSON_LECTURE);
        Module editedModule = CS2030.deleteZoomLink(lecture);
        Model expectedModel = new ModelManager(new ModuleList(moduleModel.getModuleList()), new ModuleList(),
                new ContactList(), new TodoList(), new EventList(), new UserPrefs());
        Model commitCommandModel = new ModelManager(new ModuleList(moduleModel.getModuleList()), new ModuleList(),
                new ContactList(), new TodoList(), new EventList(), new UserPrefs());
        commitCommandModel.setModule(CS2030, editedModule);
        DeleteZoomLinkCommand deleteZoomLinkCommand =
                new DeleteZoomLinkCommand(INDEX_FIRST_ZOOM_LINK, new ModuleLesson(VALID_MODULE_LESSON_LECTURE));
        String expectedCommitMessage = String.format(DeleteZoomLinkCommand.MESSAGE_DELETE_ZOOM_SUCCESS,
                VALID_MODULE_LESSON_LECTURE, CS2030.getName());
        //Make sure Commit Command is successful
        assertCommandSuccess(deleteZoomLinkCommand, moduleModel, expectedCommitMessage, commitCommandModel);
        //Make sure Undo Command is successful
        assertCommandSuccess(new UndoCommand(), moduleModel, expectedUndoMessage, expectedModel);
        assertCommandSuccess(new RedoCommand(), moduleModel, expectedRedoMessage, commitCommandModel);
    }
    @Test
    public void execute_editModuleRedo_success() {
        Model moduleModel = new ModelManager(getTypicalModuleList() , new ModuleList(), new ContactList(),
                new TodoList(), new EventList(), new UserPrefs());
        Module editedModule = new ModuleBuilder(CS2030).withName(VALID_MODULENAME_CS2030)
                .withZoomLink(VALID_TAG_LECTURE, VALID_ZOOM_LINK_CS2103T)
                .withModularCredits(4.0).build();
        Model expectedModel = new ModelManager(new ModuleList(moduleModel.getModuleList()), new ModuleList(),
                new ContactList(), new TodoList(), new EventList(), new UserPrefs());
        Model commitCommandModel = new ModelManager(new ModuleList(moduleModel.getModuleList()), new ModuleList(),
                new ContactList(), new TodoList(), new EventList(), new UserPrefs());
        commitCommandModel.setModule(CS2030, editedModule);
        EditModuleCommand editModuleCommand = new EditModuleCommand(INDEX_FIRST_MODULE,
                new EditModuleDescriptorBuilder().withZoomLinks(VALID_ZOOMLINKS_CS2103T)
                        .withName(VALID_MODULENAME_CS2030).withMc(4.0).build());
        String expectedCommitMessage = String.format(EditModuleCommand.MESSAGE_EDIT_MODULE_SUCCESS, editedModule);
        //Make sure Commit Command is successful
        assertCommandSuccess(editModuleCommand, moduleModel, expectedCommitMessage, commitCommandModel);
        //Make sure Undo Command is successful
        assertCommandSuccess(new UndoCommand(), moduleModel, expectedUndoMessage, expectedModel);
        assertCommandSuccess(new RedoCommand(), moduleModel, expectedRedoMessage, commitCommandModel);
    }
    @Test
    public void execute_editZoomLinkRedo_success() {
        Model moduleModel = new ModelManager(getTypicalModuleList() , new ModuleList(), new ContactList(),
                new TodoList(), new EventList(), new UserPrefs());
        Module editedModule = new ModuleBuilder(CS2030)
                .withZoomLink(VALID_MODULE_LESSON_TUTORIAL, VALID_ZOOM_LINK_CS2103T).build();
        Model expectedModel = new ModelManager(new ModuleList(moduleModel.getModuleList()), new ModuleList(),
                new ContactList(), new TodoList(), new EventList(), new UserPrefs());
        Model commitCommandModel = new ModelManager(new ModuleList(moduleModel.getModuleList()), new ModuleList(),
                new ContactList(), new TodoList(), new EventList(), new UserPrefs());
        commitCommandModel.setModule(CS2030, editedModule);
        EditZoomLinkCommand editZoomLinkCommand = new EditZoomLinkCommand(INDEX_FIRST_ZOOM_LINK,
                new ZoomDescriptorBuilder().withModuleLesson(VALID_MODULE_LESSON_LECTURE)
                        .withZoomLink(VALID_ZOOM_LINK_CS2103T).build());

        String expectedCommitMessage = String.format(EditZoomLinkCommand.MESSAGE_EDIT_ZOOM_SUCCESS,
                VALID_ZOOM_LINK_CS2103T, VALID_MODULE_LESSON_LECTURE);
        //Make sure Commit Command is successful
        assertCommandSuccess(editZoomLinkCommand, moduleModel, expectedCommitMessage, commitCommandModel);
        assertCommandSuccess(new UndoCommand(), moduleModel, expectedUndoMessage, expectedModel);
        assertCommandSuccess(new RedoCommand(), moduleModel, expectedRedoMessage, commitCommandModel);
    }
    @Test
    public void execute_unarchiveModuleRedo_success() {
        Model moduleModel = new ModelManager(getTypicalModuleList() , new ModuleList(), new ContactList(),
                new TodoList(), new EventList(), new UserPrefs());
        Model archiveModel = new ModelManager(new ModuleList(), new ModuleList(moduleModel.getModuleList()),
                new ContactList(), new TodoList(), new EventList(), new UserPrefs());
        Model expectedModel = new ModelManager(new ModuleList(), new ModuleList(moduleModel.getModuleList()),
                new ContactList(), new TodoList(), new EventList(), new UserPrefs());
        Model commitCommandModel = new ModelManager(new ModuleList(), new ModuleList(moduleModel.getModuleList()),
                new ContactList(), new TodoList(), new EventList(), new UserPrefs());
        archiveModel.displayArchivedModules();
        expectedModel.displayArchivedModules();
        commitCommandModel.displayArchivedModules();
        commitCommandModel.unarchiveModule(CS2030);
        UnarchiveModuleCommand unarchiveModuleCommand = new UnarchiveModuleCommand(INDEX_FIRST_MODULE);
        String expectedCommitMessage = String.format(UnarchiveModuleCommand.MESSAGE_UNARCHIVE_MODULE_SUCCESS, CS2030);
        //Make sure Commit Command is successful
        assertCommandSuccess(unarchiveModuleCommand, archiveModel, expectedCommitMessage, commitCommandModel);
        //Make sure Undo Command is successful
        assertCommandSuccess(new UndoCommand(), archiveModel, expectedUndoMessage, expectedModel);
        assertCommandSuccess(new RedoCommand(), archiveModel, expectedRedoMessage, commitCommandModel);
    }
}
