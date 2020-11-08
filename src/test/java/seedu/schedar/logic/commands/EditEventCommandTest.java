//package seedu.schedar.logic.commands;
//
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertNotEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static seedu.schedar.logic.commands.CommandTestUtil.*;
//import static seedu.schedar.testutil.TypicalIndexes.INDEX_FIRST_TASK;
//import static seedu.schedar.testutil.TypicalIndexes.INDEX_SECOND_TASK;
//import static seedu.schedar.testutil.TypicalTASKs.getTypicalTaskManager;
//
//import org.junit.jupiter.api.Test;
//
//import seedu.schedar.commons.core.Messages;
//import seedu.schedar.commons.core.index.Index;
//import seedu.schedar.logic.CommandHistory;
//import seedu.schedar.logic.commands.EditCommand.EditTASKDescriptor;
//import seedu.schedar.model.TaskManager;
//import seedu.schedar.model.Model;
//import seedu.schedar.model.ModelManager;
//import seedu.schedar.model.UserPrefs;
//import seedu.schedar.model.TASK.TASK;
//import seedu.schedar.model.task.Event;
//import seedu.schedar.testutil.EditEventDescriptorBuilder;
//import seedu.schedar.testutil.EditTASKDescriptorBuilder;
//import seedu.schedar.testutil.EventBuilder;
//import seedu.schedar.testutil.TASKBuilder;
//
///**
// * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand)
// * and unit tests for EditCommand.
// */
//public class EditEventCommandTest {
//
//    private Model model = new ModelManager(getTypicalTaskManager(), new UserPrefs());
//    private CommandHistory commandHistory = new CommandHistory();
//
//    @Test
//    public void execute_allFieldsSpecifiedUnfilteredList_success() {
//        TASK editedTASK = new TASKBuilder().build();
//        EditTASKDescriptor descriptor = new EditTASKDescriptorBuilder(editedTASK).build();
//        EditCommand editCommand = new EditCommand(INDEX_FIRST_TASK, descriptor);
//
//        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_TASK_SUCCESS, editedTASK);
//
//        Model expectedModel = new ModelManager(new TaskManager(model.getTaskManager()), new UserPrefs());
//        expectedModel.setTASK(model.getFilteredTaskList().get(0), editedTASK);
//        expectedModel.commitTaskManager();
//
//        assertCommandSuccess(editCommand, model, commandHistory, expectedMessage, expectedModel);
//    }
//
//    @Test
//    public void execute_someFieldsSpecifiedUnfilteredList_success() {
//        Index indexLastTASK = Index.fromOneBased(model.getFilteredTASKList().size());
//        TASK lastTASK = model.getFilteredTASKList().get(indexLastTASK.getZeroBased());
//
//        TASKBuilder TASKInList = new TASKBuilder(lastTASK);
//        TASK editedTASK = TASKInList.withName(VALID_NAME_LECTURE).withPhone(VALID_PHONE_LECTURE)
//                .withTags(VALID_TAG_HUSBAND).build();
//
//        EditTASKDescriptor descriptor = new EditTASKDescriptorBuilder().withName(VALID_NAME_LECTURE)
//                .withPhone(VALID_PHONE_LECTURE).withTags(VALID_TAG_HUSBAND).build();
//        EditCommand editCommand = new EditCommand(indexLastTASK, descriptor);
//
//        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_TASK_SUCCESS, editedTASK);
//
//        Model expectedModel = new ModelManager(new TaskManager(model.getTaskManager()), new UserPrefs());
//        expectedModel.setTASK(lastTASK, editedTASK);
//        expectedModel.commitTaskManager();
//
//        assertCommandSuccess(editCommand, model, commandHistory, expectedMessage, expectedModel);
//    }
//
//    @Test
//    public void execute_noFieldSpecifiedUnfilteredList_success() {
//        EditCommand editCommand = new EditCommand(INDEX_FIRST_TASK, new EditTASKDescriptor());
//        TASK editedTASK = model.getFilteredTASKList().get(INDEX_FIRST_TASK.getZeroBased());
//
//        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_TASK_SUCCESS, editedTASK);
//
//        Model expectedModel = new ModelManager(new TaskManager(model.getTaskManager()), new UserPrefs());
//        expectedModel.commitTaskManager();
//
//        assertCommandSuccess(editCommand, model, commandHistory, expectedMessage, expectedModel);
//    }
//
//    @Test
//    public void execute_filteredList_success() {
//        showTASKAtIndex(model, INDEX_FIRST_TASK);
//
//        TASK TASKInFilteredList = model.getFilteredTASKList().get(INDEX_FIRST_TASK.getZeroBased());
//        TASK editedTASK = new TASKBuilder(TASKInFilteredList).withName(VALID_NAME_LECTURE).build();
//        EditCommand editCommand = new EditCommand(INDEX_FIRST_TASK,
//                new EditTASKDescriptorBuilder().withName(VALID_NAME_LECTURE).build());
//
//        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_TASK_SUCCESS, editedTASK);
//
//        Model expectedModel = new ModelManager(new TaskManager(model.getTaskManager()), new UserPrefs());
//        expectedModel.setTASK(model.getFilteredTASKList().get(0), editedTASK);
//        expectedModel.commitTaskManager();
//
//        assertCommandSuccess(editCommand, model, commandHistory, expectedMessage, expectedModel);
//    }
//
//    @Test
//    public void execute_duplicateTASKUnfilteredList_failure() {
//        TASK firstTASK = model.getFilteredTASKList().get(INDEX_FIRST_TASK.getZeroBased());
//        EditTASKDescriptor descriptor = new EditTASKDescriptorBuilder(firstTASK).build();
//        EditCommand editCommand = new EditCommand(INDEX_SECOND_TASK, descriptor);
//
//        assertCommandFailure(editCommand, model, commandHistory, EditCommand.MESSAGE_DUPLICATE_TASK);
//    }
//
//    @Test
//    public void execute_duplicateTASKFilteredList_failure() {
//        showTASKAtIndex(model, INDEX_FIRST_TASK);
//
//        // edit TASK in filtered list into a duplicate in schedar book
//        TASK TASKInList = model.getTaskManager().getTASKList().get(INDEX_SECOND_TASK.getZeroBased());
//        EditCommand editCommand = new EditCommand(INDEX_FIRST_TASK,
//                new EditTASKDescriptorBuilder(TASKInList).build());
//
//        assertCommandFailure(editCommand, model, commandHistory, EditCommand.MESSAGE_DUPLICATE_TASK);
//    }
//
//    @Test
//    public void execute_invalidTASKIndexUnfilteredList_failure() {
//        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTASKList().size() + 1);
//        EditTASKDescriptor descriptor = new EditTASKDescriptorBuilder().withName(VALID_NAME_LECTURE).build();
//        EditCommand editCommand = new EditCommand(outOfBoundIndex, descriptor);
//
//        assertCommandFailure(editCommand, model, commandHistory, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
//    }
//
//    /**
//     * Edit filtered list where index is larger than size of filtered list,
//     * but smaller than size of schedar book
//     */
//    @Test
//    public void execute_invalidTASKIndexFilteredList_failure() {
//        showTASKAtIndex(model, INDEX_FIRST_TASK);
//        Index outOfBoundIndex = INDEX_SECOND_TASK;
//        // ensures that outOfBoundIndex is still in bounds of schedar book list
//        assertTrue(outOfBoundIndex.getZeroBased() < model.getTaskManager().getTASKList().size());
//
//        EditCommand editCommand = new EditCommand(outOfBoundIndex,
//                new EditTASKDescriptorBuilder().withName(VALID_NAME_LECTURE).build());
//
//        assertCommandFailure(editCommand, model, commandHistory, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
//    }
//
//    @Test
//    public void executeUndoRedo_validIndexUnfilteredList_success() throws Exception {
//        TASK editedTASK = new TASKBuilder().build();
//        TASK TASKToEdit = model.getFilteredTASKList().get(INDEX_FIRST_TASK.getZeroBased());
//        EditTASKDescriptor descriptor = new EditTASKDescriptorBuilder(editedTASK).build();
//        EditCommand editCommand = new EditCommand(INDEX_FIRST_TASK, descriptor);
//        Model expectedModel = new ModelManager(new TaskManager(model.getTaskManager()), new UserPrefs());
//        expectedModel.setTASK(TASKToEdit, editedTASK);
//        expectedModel.commitTaskManager();
//
//        // edit -> first TASK edited
//        editCommand.execute(model, commandHistory);
//
//        // undo -> reverts TaskManager back to previous state and filtered TASK list to show all TASKs
//        expectedModel.undoTaskManager();
//        assertCommandSuccess(new UndoCommand(), model, commandHistory, UndoCommand.MESSAGE_SUCCESS, expectedModel);
//
//        // redo -> same first TASK edited again
//        expectedModel.redoTaskManager();
//        assertCommandSuccess(new RedoCommand(), model, commandHistory, RedoCommand.MESSAGE_SUCCESS, expectedModel);
//    }
//
//    @Test
//    public void executeUndoRedo_invalidIndexUnfilteredList_failure() {
//        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTASKList().size() + 1);
//        EditTASKDescriptor descriptor = new EditTASKDescriptorBuilder().withName(VALID_NAME_LECTURE).build();
//        EditCommand editCommand = new EditCommand(outOfBoundIndex, descriptor);
//
//        // execution failed -> schedar book state not added into model
//        assertCommandFailure(editCommand, model, commandHistory, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
//
//        // single schedar book state in model -> undoCommand and redoCommand fail
//        assertCommandFailure(new UndoCommand(), model, commandHistory, UndoCommand.MESSAGE_FAILURE);
//        assertCommandFailure(new RedoCommand(), model, commandHistory, RedoCommand.MESSAGE_FAILURE);
//    }
//
//    /**
//     * 1. Edits a {@code TASK} from a filtered list.
//     * 2. Undo the edit.
//     * 3. The unfiltered list should be shown now. Verify that the index of the previously edited TASK in the
//     * unfiltered list is different from the index at the filtered list.
//     * 4. Redo the edit. This ensures {@code RedoCommand} edits the TASK object regardless of indexing.
//     */
//    @Test
//    public void executeUndoRedo_validIndexFilteredList_sameTASKEdited() throws Exception {
//        Event editedEvent = new EventBuilder().build();
//        EditEventCommand.EditEventDescriptor descriptor = new EditEventDescriptorBuilder(editedEvent).build();
//        EditEventCommand editEventCommand = new EditEventCommand(INDEX_FIRST_TASK, descriptor);
//        Model expectedModel = new ModelManager(new TaskManager(model.getTaskManager()), new UserPrefs());
//
//        showTaskAtIndex(model, INDEX_SECOND_TASK);
//        Event EventToEdit = model.getFilteredTaskList().get(INDEX_FIRST_TASK.getZeroBased());
//        expectedModel.setTask(EventToEdit, editedEvent);
//        expectedModel.commitTaskManager();
//
//        // edit -> edits second TASK in unfiltered TASK list / first TASK in filtered TASK list
//        editEventCommand.execute(model, commandHistory);
//
//        // undo -> reverts TaskManager back to previous state and filtered TASK list to show all TASKs
//        expectedModel.undoTaskManager();
//        assertCommandSuccess(new UndoCommand(), model, commandHistory, UndoCommand.MESSAGE_SUCCESS, expectedModel);
//
//        assertNotEquals(model.getFilteredTaskList().get(INDEX_FIRST_TASK.getZeroBased()), EventToEdit);
//        // redo -> edits same second TASK in unfiltered TASK list
//        expectedModel.redoTaskManager();
//        assertCommandSuccess(new RedoCommand(), model, commandHistory, RedoCommand.MESSAGE_SUCCESS, expectedModel);
//    }
//
//    @Test
//    public void equals() {
//        final EditEventCommand standardCommand = new EditEventCommand(INDEX_FIRST_TASK, DESC_PROJECT);
//
//        // same values -> returns true
//        EditEventCommand.EditEventDescriptor copyDescriptor = new EditEventCommand.EditEventDescriptor(DESC_PROJECT);
//        EditEventCommand commandWithSameValues = new EditEventCommand(INDEX_FIRST_TASK, copyDescriptor);
//        assertTrue(standardCommand.equals(commandWithSameValues));
//
//        // same object -> returns true
//        assertTrue(standardCommand.equals(standardCommand));
//
//        // null -> returns false
//        assertFalse(standardCommand.equals(null));
//
//        // different types -> returns false
//        assertFalse(standardCommand.equals(new ClearCommand()));
//
//        // different index -> returns false
//        assertFalse(standardCommand.equals(new EditEventCommand(INDEX_SECOND_TASK, DESC_PROJECT)));
//
//        // different descriptor -> returns false
//        assertFalse(standardCommand.equals(new EditEventCommand(INDEX_FIRST_TASK, DESC_LECTURE)));
//    }
//
//}
