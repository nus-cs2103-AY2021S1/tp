package seedu.zookeep.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.zookeep.logic.commands.CommandTestUtil.DESC_ARCHIE;
import static seedu.zookeep.logic.commands.CommandTestUtil.DESC_BAILEY;
import static seedu.zookeep.logic.commands.CommandTestUtil.VALID_ID_PASHA;
import static seedu.zookeep.logic.commands.CommandTestUtil.VALID_MEDICAL_CONDITION_ARTHRITIS;
import static seedu.zookeep.logic.commands.CommandTestUtil.VALID_NAME_BAILEY;
import static seedu.zookeep.logic.commands.CommandTestUtil.VALID_NAME_PASHA;
import static seedu.zookeep.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.zookeep.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.zookeep.logic.commands.CommandTestUtil.showAnimalAtIndex;
import static seedu.zookeep.testutil.TypicalAnimals.AHMENG;
import static seedu.zookeep.testutil.TypicalAnimals.BUTTERCUP;
import static seedu.zookeep.testutil.TypicalAnimals.PASHA;
import static seedu.zookeep.testutil.TypicalAnimals.getTypicalZooKeepBook;
import static seedu.zookeep.testutil.TypicalIndexes.INDEX_FIRST_ANIMAL;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.zookeep.commons.core.Messages;
import seedu.zookeep.commons.core.index.Index;
import seedu.zookeep.model.Model;
import seedu.zookeep.model.ModelManager;
import seedu.zookeep.model.UserPrefs;
import seedu.zookeep.model.ZooKeepBook;
import seedu.zookeep.model.animal.Animal;
import seedu.zookeep.model.animal.Id;
import seedu.zookeep.testutil.AnimalBuilder;
import seedu.zookeep.testutil.EditAnimalDescriptorBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ReplaceCommand.
 */
public class ReplaceCommandTest {

    private Model model = new ModelManager(getTypicalZooKeepBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecified_success() {
        Animal editedAnimal = new AnimalBuilder().build();
        EditAnimalDescriptor descriptor = new EditAnimalDescriptorBuilder(editedAnimal).build();
        ReplaceCommand replaceCommand = new ReplaceCommand(AHMENG.getId(), descriptor);

        String expectedMessage = String.format(ReplaceCommand.MESSAGE_REPLACE_ANIMAL_SUCCESS, editedAnimal);

        Model expectedModel = new ModelManager(new ZooKeepBook(model.getZooKeepBook()), new UserPrefs());
        expectedModel.setAnimal(model.getFilteredAnimalList().get(0), editedAnimal);

        assertCommandSuccess(replaceCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecified_success() {
        Index indexLastAnimal = Index.fromOneBased(model.getFilteredAnimalList().size());
        Animal lastAnimal = model.getFilteredAnimalList().get(indexLastAnimal.getZeroBased());
        AnimalBuilder animalInList = new AnimalBuilder(lastAnimal);
        Animal editedAnimal = animalInList.withName(VALID_NAME_PASHA).withId(VALID_ID_PASHA)
                .withMedicalConditions(VALID_MEDICAL_CONDITION_ARTHRITIS).build();

        EditAnimalDescriptor descriptor = new EditAnimalDescriptorBuilder().withName(VALID_NAME_PASHA)
                .withId(VALID_ID_PASHA).withMedicalConditions(VALID_MEDICAL_CONDITION_ARTHRITIS).build();
        ReplaceCommand replaceCommand = new ReplaceCommand(PASHA.getId(), descriptor);

        String expectedMessage = String.format(ReplaceCommand.MESSAGE_REPLACE_ANIMAL_SUCCESS, editedAnimal);

        Model expectedModel = new ModelManager(new ZooKeepBook(model.getZooKeepBook()), new UserPrefs());
        expectedModel.setAnimal(lastAnimal, editedAnimal);

        assertCommandSuccess(replaceCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecified_failure() {
        ReplaceCommand replaceCommand = new ReplaceCommand(AHMENG.getId(), new EditAnimalDescriptor());
        assertCommandFailure(replaceCommand, model, ReplaceCommand.MESSAGE_FIELDS_UNCHANGED);
    }

    @Test
    public void execute_success() {
        showAnimalAtIndex(model, INDEX_FIRST_ANIMAL);

        Animal animalInFilteredList = model.getFilteredAnimalList().get(INDEX_FIRST_ANIMAL.getZeroBased());
        Animal editedAnimal = new AnimalBuilder(animalInFilteredList).withName(VALID_NAME_BAILEY).build();
        ReplaceCommand replaceCommand = new ReplaceCommand(AHMENG.getId(),
            new EditAnimalDescriptorBuilder().withName(VALID_NAME_BAILEY).build());

        String expectedMessage = String.format(ReplaceCommand.MESSAGE_REPLACE_ANIMAL_SUCCESS, editedAnimal);

        Model expectedModel = new ModelManager(new ZooKeepBook(model.getZooKeepBook()), new UserPrefs());
        expectedModel.setAnimal(model.getFilteredAnimalList().get(0), editedAnimal);

        assertCommandSuccess(replaceCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateAnimal_failure() {
        Animal firstAnimal = model.getFilteredAnimalList().get(INDEX_FIRST_ANIMAL.getZeroBased());
        EditAnimalDescriptor descriptor = new EditAnimalDescriptorBuilder(firstAnimal).build();
        ReplaceCommand replaceCommand = new ReplaceCommand(BUTTERCUP.getId(), descriptor);

        assertCommandFailure(replaceCommand, model, ReplaceCommand.MESSAGE_DUPLICATE_ANIMAL);
    }

    @Test
    public void execute_invalidAnimalId_failure() {
        Id outOfBoundId = new Id("99999999999999");
        assert model.getAnimal(outOfBoundId).equals(Optional.empty());
        EditAnimalDescriptor descriptor = new EditAnimalDescriptorBuilder().withName(VALID_NAME_BAILEY).build();
        ReplaceCommand replaceCommand = new ReplaceCommand(outOfBoundId, descriptor);

        assertCommandFailure(replaceCommand, model, Messages.MESSAGE_INVALID_ANIMAL_DISPLAYED_ID);
    }

    @Test
    public void equals() {
        final ReplaceCommand standardCommand = new ReplaceCommand(AHMENG.getId(), DESC_ARCHIE);

        // same values -> returns true
        EditAnimalDescriptor copyDescriptor = new EditAnimalDescriptor(DESC_ARCHIE);
        ReplaceCommand commandWithSameValues = new ReplaceCommand(AHMENG.getId(), copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new ReplaceCommand(BUTTERCUP.getId(), DESC_ARCHIE)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new ReplaceCommand(AHMENG.getId(), DESC_BAILEY)));
    }

}
