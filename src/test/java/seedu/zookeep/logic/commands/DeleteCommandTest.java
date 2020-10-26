package seedu.zookeep.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.zookeep.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.zookeep.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.zookeep.logic.commands.CommandTestUtil.showAnimalAtIndex;
import static seedu.zookeep.testutil.TypicalAnimals.getTypicalZooKeepBook;
import static seedu.zookeep.testutil.TypicalIndexes.INDEX_FIRST_ANIMAL;
import static seedu.zookeep.testutil.TypicalIndexes.INDEX_SECOND_ANIMAL;

import org.junit.jupiter.api.Test;

import seedu.zookeep.commons.core.Messages;
import seedu.zookeep.commons.core.index.Index;
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

    private static final Id OUT_OF_BOUNDS_ID = new Id("99999999999999999999999999999999");

    private Model model = new ModelManager(getTypicalZooKeepBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Animal animalToDelete = model.getFilteredAnimalList().get(0);
        DeleteCommand deleteCommand = new DeleteCommand(animalToDelete.getId());

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_ANIMAL_SUCCESS, animalToDelete);

        ModelManager expectedModel = new ModelManager(model.getZooKeepBook(), new UserPrefs());
        expectedModel.deleteAnimal(animalToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredAnimalList().size() + 1);
        DeleteCommand deleteCommand = new DeleteCommand(OUT_OF_BOUNDS_ID);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_ANIMAL_DISPLAYED_ID);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showAnimalAtIndex(model, INDEX_FIRST_ANIMAL);

        Animal animalToDelete = model.getFilteredAnimalList().get(0);
        DeleteCommand deleteCommand = new DeleteCommand(animalToDelete.getId());

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_ANIMAL_SUCCESS, animalToDelete);

        Model expectedModel = new ModelManager(model.getZooKeepBook(), new UserPrefs());
        expectedModel.deleteAnimal(animalToDelete);
        showNoAnimal(expectedModel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showAnimalAtIndex(model, INDEX_FIRST_ANIMAL);

        Index outOfBoundIndex = INDEX_SECOND_ANIMAL;
        // ensures that outOfBoundIndex is still in bounds of zookeep book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getZooKeepBook().getAnimalList().size());

        DeleteCommand deleteCommand = new DeleteCommand(OUT_OF_BOUNDS_ID);

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
