//package seedu.address.logic.commands;
//
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
//import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
//import static seedu.address.testutil.TypicalModules.getTypicalModuleList;
//
//import org.junit.jupiter.api.Test;
//
//import seedu.address.model.Model;
//import seedu.address.model.ModelManager;
//import seedu.address.model.UserPrefs;
//
///**
// * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for
// * {@code DeleteCommand}.
// */
//public class DeleteCommandTest {
//
//    private Model model = new ModelManager(getTypicalModuleList(), new UserPrefs());
//
//    //    @Test
//    //    public void execute_validIndexUnfilteredList_success() {
//    //        Module moduleToDelete = model.getFilteredModuleList().get(INDEX_FIRST_PERSON.getZeroBased());
//    //        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_PERSON);
//    //
//    //        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_PERSON_SUCCESS, moduleToDelete);
//    //
//    //        ModelManager expectedModel = new ModelManager(model.getModuleList(), new UserPrefs());
//    //        expectedModel.deleteModule(moduleToDelete);
//    //
//    //        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
//    //    }
//    //
//    //    @Test
//    //    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
//    //        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredModuleList().size() + 1);
//    //        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);
//    //
//    //        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
//    //    }
//    //
//    //    @Test
//    //    public void execute_validIndexFilteredList_success() {
//    //        showPersonAtIndex(model, INDEX_FIRST_PERSON);
//    //
//    //        Module moduleToDelete = model.getFilteredModuleList().get(INDEX_FIRST_PERSON.getZeroBased());
//    //        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_PERSON);
//    //
//    //        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_PERSON_SUCCESS, moduleToDelete);
//    //
//    //        Model expectedModel = new ModelManager(model.getModuleList(), new UserPrefs());
//    //        expectedModel.deleteModule(moduleToDelete);
//    //        showNoPerson(expectedModel);
//    //
//    //        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
//    //    }
//    //
//    //    @Test
//    //    public void execute_invalidIndexFilteredList_throwsCommandException() {
//    //        showPersonAtIndex(model, INDEX_FIRST_PERSON);
//    //
//    //        Index outOfBoundIndex = INDEX_SECOND_PERSON;
//    //        // ensures that outOfBoundIndex is still in bounds of address book list
//    //        assertTrue(outOfBoundIndex.getZeroBased() < model.getModuleList().getModuleList().size());
//    //
//    //        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);
//    //
//    //        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
//    //    }
//
//    @Test
//    public void equals() {
//        DeleteCommand deleteFirstCommand = new DeleteCommand(INDEX_FIRST_PERSON);
//        DeleteCommand deleteSecondCommand = new DeleteCommand(INDEX_SECOND_PERSON);
//
//        // same object -> returns true
//        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));
//
//        // same values -> returns true
//        DeleteCommand deleteFirstCommandCopy = new DeleteCommand(INDEX_FIRST_PERSON);
//        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));
//
//        // different types -> returns false
//        assertFalse(deleteFirstCommand.equals(1));
//
//        // null -> returns false
//        assertFalse(deleteFirstCommand.equals(null));
//
//        // different person -> returns false
//        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
//    }
//
//    /**
//     * Updates {@code model}'s filtered list to show no one.
//     */
//    private void showNoPerson(Model model) {
//        model.updateFilteredModuleList(p -> false);
//
//        assertTrue(model.getFilteredModuleList().isEmpty());
//    }
//}
