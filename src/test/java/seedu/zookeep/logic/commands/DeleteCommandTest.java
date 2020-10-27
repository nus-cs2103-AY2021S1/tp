package seedu.zookeep.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.zookeep.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.zookeep.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.zookeep.logic.commands.CommandTestUtil.showAnimalAtIndex;
import static seedu.zookeep.testutil.TypicalAnimals.getTypicalZooKeepBook;
import static seedu.zookeep.testutil.TypicalIndexes.INDEX_FIRST_ANIMAL;

import org.junit.jupiter.api.Test;

import seedu.zookeep.commons.core.Messages;
import seedu.zookeep.model.Model;
import seedu.zookeep.model.ModelManager;
import seedu.zookeep.model.UserPrefs;
import seedu.zookeep.model.animal.Animal;
import seedu.zookeep.model.animal.Id;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteCommandTest {

    private Model model = new ModelManager(getTypicalZooKeepBook(), new UserPrefs());
    private Id invalidId = new Id("113"); // No animal in zookeep book has this id
    private Id validId = new Id("123"); // Animal with this id exists in the zookeep book

    @Test
    public void execute_validIdUnfilteredList_success() {
        Animal animalToDelete = model.getFilteredAnimalList().get(0);
        DeleteCommand deleteCommand = new DeleteCommand(animalToDelete.getId());

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_ANIMAL_SUCCESS, animalToDelete);

        ModelManager expectedModel = new ModelManager(model.getZooKeepBook(), new UserPrefs());
        expectedModel.deleteAnimal(animalToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIdUnfilteredList_throwsCommandException() {
        DeleteCommand deleteCommand = new DeleteCommand(invalidId);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_ANIMAL_DISPLAYED_ID);
    }

    // Able to delete an animal even if it is not shown on the UI
    @Test
    public void execute_validIdFilteredList_success() {
        Animal animalToDelete = model.getAnimal(validId).get();
        showNoAnimal(model);
        DeleteCommand deleteCommand = new DeleteCommand(validId);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_ANIMAL_SUCCESS, animalToDelete);
        Model expectedModel = new ModelManager(model.getZooKeepBook(), new UserPrefs());
        expectedModel.deleteAnimal(animalToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIdFilteredList_throwsCommandException() {
        showAnimalAtIndex(model, INDEX_FIRST_ANIMAL);

        DeleteCommand deleteCommand = new DeleteCommand(invalidId);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_ANIMAL_DISPLAYED_ID);
    }

    @Test
    public void equals() {
        DeleteCommand deleteFirstCommand = new DeleteCommand(new Id("123"));
        DeleteCommand deleteSecondCommand = new DeleteCommand(new Id("1234"));

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteCommand deleteFirstCommandCopy = new DeleteCommand(new Id("123"));
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different animal -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoAnimal(Model model) {
        model.updateFilteredAnimalList(p -> false);

        assertTrue(model.getFilteredAnimalList().isEmpty());
    }
}
