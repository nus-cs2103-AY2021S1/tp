package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_ARCHIE;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BAILEY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ID_BAILEY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MEDICAL_CONDITION_ARTHRITIS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BAILEY;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showAnimalAtIndex;
import static seedu.address.testutil.TypicalAnimals.getTypicalZooKeepBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ANIMAL;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_ANIMAL;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand.EditAnimalDescriptor;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.ZooKeepBook;
import seedu.address.model.animal.Animal;
import seedu.address.testutil.AnimalBuilder;
import seedu.address.testutil.EditAnimalDescriptorBuilder;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for EditCommand.
 */
public class EditCommandTest {

    private Model model = new ModelManager(getTypicalZooKeepBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Animal editedAnimal = new AnimalBuilder().build();
        EditAnimalDescriptor descriptor = new EditAnimalDescriptorBuilder(editedAnimal).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_ANIMAL, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_ANIMAL_SUCCESS, editedAnimal);

        Model expectedModel = new ModelManager(new ZooKeepBook(model.getZooKeepBook()), new UserPrefs());
        expectedModel.setAnimal(model.getFilteredAnimalList().get(0), editedAnimal);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastAnimal = Index.fromOneBased(model.getFilteredAnimalList().size());
        Animal lastAnimal = model.getFilteredAnimalList().get(indexLastAnimal.getZeroBased());

        AnimalBuilder animalInList = new AnimalBuilder(lastAnimal);
        Animal editedAnimal = animalInList.withName(VALID_NAME_BAILEY).withId(VALID_ID_BAILEY)
                .withMedicalConditions(VALID_MEDICAL_CONDITION_ARTHRITIS).build();

        EditAnimalDescriptor descriptor = new EditAnimalDescriptorBuilder().withName(VALID_NAME_BAILEY)
                .withId(VALID_ID_BAILEY).withMedicalConditions(VALID_MEDICAL_CONDITION_ARTHRITIS).build();
        EditCommand editCommand = new EditCommand(indexLastAnimal, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_ANIMAL_SUCCESS, editedAnimal);

        Model expectedModel = new ModelManager(new ZooKeepBook(model.getZooKeepBook()), new UserPrefs());
        expectedModel.setAnimal(lastAnimal, editedAnimal);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditCommand editCommand = new EditCommand(INDEX_FIRST_ANIMAL, new EditAnimalDescriptor());
        Animal editedAnimal = model.getFilteredAnimalList().get(INDEX_FIRST_ANIMAL.getZeroBased());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_ANIMAL_SUCCESS, editedAnimal);

        Model expectedModel = new ModelManager(new ZooKeepBook(model.getZooKeepBook()), new UserPrefs());

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showAnimalAtIndex(model, INDEX_FIRST_ANIMAL);

        Animal animalInFilteredList = model.getFilteredAnimalList().get(INDEX_FIRST_ANIMAL.getZeroBased());
        Animal editedAnimal = new AnimalBuilder(animalInFilteredList).withName(VALID_NAME_BAILEY).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_ANIMAL,
                new EditAnimalDescriptorBuilder().withName(VALID_NAME_BAILEY).build());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_ANIMAL_SUCCESS, editedAnimal);

        Model expectedModel = new ModelManager(new ZooKeepBook(model.getZooKeepBook()), new UserPrefs());
        expectedModel.setAnimal(model.getFilteredAnimalList().get(0), editedAnimal);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateAnimalUnfilteredList_failure() {
        Animal firstAnimal = model.getFilteredAnimalList().get(INDEX_FIRST_ANIMAL.getZeroBased());
        EditAnimalDescriptor descriptor = new EditAnimalDescriptorBuilder(firstAnimal).build();
        EditCommand editCommand = new EditCommand(INDEX_SECOND_ANIMAL, descriptor);

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_ANIMAL);
    }

    @Test
    public void execute_duplicateAnimalFilteredList_failure() {
        showAnimalAtIndex(model, INDEX_FIRST_ANIMAL);

        // edit animal in filtered list into a duplicate in zookeep book
        Animal animalInList = model.getZooKeepBook().getAnimalList().get(INDEX_SECOND_ANIMAL.getZeroBased());
        EditCommand editCommand = new EditCommand(INDEX_FIRST_ANIMAL,
                new EditAnimalDescriptorBuilder(animalInList).build());

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_ANIMAL);
    }

    @Test
    public void execute_invalidAnimalIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredAnimalList().size() + 1);
        EditAnimalDescriptor descriptor = new EditAnimalDescriptorBuilder().withName(VALID_NAME_BAILEY).build();
        EditCommand editCommand = new EditCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_ANIMAL_DISPLAYED_ID);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of zookeep book
     */
    @Test
    public void execute_invalidAnimalIndexFilteredList_failure() {
        showAnimalAtIndex(model, INDEX_FIRST_ANIMAL);
        Index outOfBoundIndex = INDEX_SECOND_ANIMAL;
        // ensures that outOfBoundIndex is still in bounds of zookeep book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getZooKeepBook().getAnimalList().size());

        EditCommand editCommand = new EditCommand(outOfBoundIndex,
                new EditAnimalDescriptorBuilder().withName(VALID_NAME_BAILEY).build());

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_ANIMAL_DISPLAYED_ID);
    }

    @Test
    public void equals() {
        final EditCommand standardCommand = new EditCommand(INDEX_FIRST_ANIMAL, DESC_ARCHIE);

        // same values -> returns true
        EditAnimalDescriptor copyDescriptor = new EditAnimalDescriptor(DESC_ARCHIE);
        EditCommand commandWithSameValues = new EditCommand(INDEX_FIRST_ANIMAL, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_SECOND_ANIMAL, DESC_ARCHIE)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_FIRST_ANIMAL, DESC_BAILEY)));
    }

}
