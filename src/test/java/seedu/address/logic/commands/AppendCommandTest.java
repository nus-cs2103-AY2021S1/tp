package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_ARCHIE;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BAILEY;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showAnimalAtIndex;
import static seedu.address.testutil.TypicalAnimals.AHMENG;
import static seedu.address.testutil.TypicalAnimals.BUTTERCUP;
import static seedu.address.testutil.TypicalAnimals.getTypicalZooKeepBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ANIMAL;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_ANIMAL;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.ZooKeepBook;
import seedu.address.model.animal.Animal;
import seedu.address.model.animal.Id;
import seedu.address.testutil.AnimalBuilder;
import seedu.address.testutil.EditAnimalDescriptorBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for AppendCommand.
 */
public class AppendCommandTest {
    private static final String[] FEED_TIMES = new String[]{"0000", "1230", "0059", "1259"};
    private static final String[] MEDICAL_CONDITIONS = new String[]{"COVID", "SARS", "DENGUE"};
    private Model model = new ModelManager(getTypicalZooKeepBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Animal editedAnimal = new AnimalBuilder().withFeedTimes(FEED_TIMES)
                .withMedicalConditions(MEDICAL_CONDITIONS).build();
        Animal appendedAnimal = new AnimalBuilder(AHMENG).withAppendedFeedTimes(FEED_TIMES)
                .withAppendedMedicalConditions(MEDICAL_CONDITIONS).build();
        EditAnimalDescriptor descriptor = new EditAnimalDescriptorBuilder(editedAnimal).build();
        AppendCommand appendCommand = new AppendCommand(AHMENG.getId(), descriptor);

        String expectedMessage = String.format(AppendCommand.MESSAGE_APPEND_ANIMAL_SUCCESS, appendedAnimal);

        Model expectedModel = new ModelManager(new ZooKeepBook(model.getZooKeepBook()), new UserPrefs());
        expectedModel.setAnimal(model.getFilteredAnimalList().get(0), appendedAnimal);

        assertCommandSuccess(appendCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Animal editedAnimal = new AnimalBuilder().withFeedTimes(FEED_TIMES).build();
        Animal appendedAnimal = new AnimalBuilder(AHMENG).withAppendedFeedTimes(FEED_TIMES).build();
        EditAnimalDescriptor descriptor = new EditAnimalDescriptorBuilder(editedAnimal).build();
        AppendCommand appendCommand = new AppendCommand(AHMENG.getId(), descriptor);

        String expectedMessage = String.format(AppendCommand.MESSAGE_APPEND_ANIMAL_SUCCESS, appendedAnimal);

        Model expectedModel = new ModelManager(new ZooKeepBook(model.getZooKeepBook()), new UserPrefs());
        expectedModel.setAnimal(model.getFilteredAnimalList().get(0), appendedAnimal);

        assertCommandSuccess(appendCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        AppendCommand appendCommand = new AppendCommand(AHMENG.getId(), new EditAnimalDescriptor());
        Animal editedAnimal = model.getFilteredAnimalList().get(INDEX_FIRST_ANIMAL.getZeroBased());

        String expectedMessage = String.format(AppendCommand.MESSAGE_APPEND_ANIMAL_SUCCESS, editedAnimal);

        Model expectedModel = new ModelManager(new ZooKeepBook(model.getZooKeepBook()), new UserPrefs());

        assertCommandSuccess(appendCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showAnimalAtIndex(model, INDEX_FIRST_ANIMAL);

        Animal animalInFilteredList = model.getFilteredAnimalList().get(INDEX_FIRST_ANIMAL.getZeroBased());
        Animal editedAnimal = new AnimalBuilder(animalInFilteredList).withFeedTimes(FEED_TIMES).build();
        AppendCommand appendCommand = new AppendCommand(AHMENG.getId(),
            new EditAnimalDescriptorBuilder(editedAnimal).build());
        Animal appendedAnimal = new AnimalBuilder(AHMENG).withAppendedFeedTimes(FEED_TIMES).build();

        String expectedMessage = String.format(AppendCommand.MESSAGE_APPEND_ANIMAL_SUCCESS, appendedAnimal);

        Model expectedModel = new ModelManager(new ZooKeepBook(model.getZooKeepBook()), new UserPrefs());
        expectedModel.setAnimal(model.getFilteredAnimalList().get(0), appendedAnimal);

        assertCommandSuccess(appendCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidAnimalIdUnfilteredList_failure() {
        Id outOfBoundId = new Id("99999999999999");
        assert model.getAnimal(outOfBoundId).equals(Optional.empty());
        EditAnimalDescriptor descriptor = new EditAnimalDescriptorBuilder().withFeedTimes(FEED_TIMES).build();
        AppendCommand appendCommand = new AppendCommand(outOfBoundId, descriptor);

        assertCommandFailure(appendCommand, model, Messages.MESSAGE_INVALID_ANIMAL_DISPLAYED_ID);
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

        AppendCommand appendCommand = new AppendCommand(BUTTERCUP.getId(),
                new EditAnimalDescriptorBuilder().withFeedTimes(FEED_TIMES).build());

        assertCommandFailure(appendCommand, model, Messages.MESSAGE_INVALID_ANIMAL_DISPLAYED_ID);
    }

    @Test
    public void equals() {
        final AppendCommand standardCommand = new AppendCommand(AHMENG.getId(), DESC_ARCHIE);

        // same values -> returns true
        EditAnimalDescriptor copyDescriptor = new EditAnimalDescriptor(DESC_ARCHIE);
        AppendCommand commandWithSameValues = new AppendCommand(AHMENG.getId(), copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new AppendCommand(BUTTERCUP.getId(), DESC_ARCHIE)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new AppendCommand(AHMENG.getId(), DESC_BAILEY)));
    }

}
