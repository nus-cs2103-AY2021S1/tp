package seedu.address.logic.commands.hairdresser;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_ALISSA;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BENJAMIN;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BENJAMIN;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BENJAMIN;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SPECIALISATION_PERM;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showHairdresserWithId;
import static seedu.address.testutil.TypicalIds.ID_FIRST_HAIRDRESSER;
import static seedu.address.testutil.TypicalIds.ID_SECOND_HAIRDRESSER;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.hairdresser.EditHairdresserCommand.EditHairdresserDescriptor;
import seedu.address.model.IdCounter;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.hairdresser.Hairdresser;
import seedu.address.model.person.hairdresser.HairdresserId;
import seedu.address.testutil.EditHairdresserDescriptorBuilder;
import seedu.address.testutil.HairdresserBuilder;
import seedu.address.testutil.TypicalHairdressers;

/**
 * Contains integration tests (interaction with the Model)
 * and unit tests for EditHairdresserCommand.
 */
public class EditHairdresserCommandTest {

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        IdCounter.reset();
        Model model = new ModelManager(TypicalHairdressers.getTypicalHairStyleX(), new UserPrefs());
        Hairdresser editedHairdresser = new HairdresserBuilder().build(ID_FIRST_HAIRDRESSER);
        EditHairdresserDescriptor descriptor = new EditHairdresserDescriptorBuilder(editedHairdresser).build();
        EditHairdresserCommand editCommand = new EditHairdresserCommand(ID_FIRST_HAIRDRESSER, descriptor);

        String expectedMessage = String.format(EditHairdresserCommand.MESSAGE_EDIT_HAIRDRESSER_SUCCESS,
                editedHairdresser);

        IdCounter.reset();
        Model expectedModel = new ModelManager(TypicalHairdressers.getTypicalHairStyleX(), new UserPrefs());
        expectedModel.setHairdresser(model.getHairdresserById(ID_FIRST_HAIRDRESSER), editedHairdresser);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        IdCounter.reset();
        Model model = new ModelManager(TypicalHairdressers.getTypicalHairStyleX(), new UserPrefs());
        HairdresserId indexSecondHairdresser = ID_SECOND_HAIRDRESSER;
        Hairdresser secondHairdresser = model.getHairdresserById(indexSecondHairdresser);

        HairdresserBuilder personInList = new HairdresserBuilder(secondHairdresser);
        Hairdresser editedHairdresser = personInList.withName(VALID_NAME_BENJAMIN).withPhone(VALID_PHONE_BENJAMIN)
                .withSpecs(VALID_SPECIALISATION_PERM).build(ID_FIRST_HAIRDRESSER);

        EditHairdresserDescriptor descriptor = new EditHairdresserDescriptorBuilder().withName(VALID_NAME_BENJAMIN)
                .withPhone(VALID_PHONE_BENJAMIN).withSpecs(VALID_SPECIALISATION_PERM).build();
        EditHairdresserCommand editCommand = new EditHairdresserCommand(indexSecondHairdresser, descriptor);

        String expectedMessage = String.format(EditHairdresserCommand.MESSAGE_EDIT_HAIRDRESSER_SUCCESS,
                editedHairdresser);

        IdCounter.reset();
        Model expectedModel = new ModelManager(TypicalHairdressers.getTypicalHairStyleX(), new UserPrefs());
        expectedModel.setHairdresser(secondHairdresser, editedHairdresser);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        IdCounter.reset();
        Model model = new ModelManager(TypicalHairdressers.getTypicalHairStyleX(), new UserPrefs());
        EditHairdresserCommand editCommand = new EditHairdresserCommand(ID_FIRST_HAIRDRESSER,
                new EditHairdresserDescriptor());
        Hairdresser editedHairdresser = model.getHairdresserById(ID_FIRST_HAIRDRESSER);

        String expectedMessage = String.format(EditHairdresserCommand.MESSAGE_EDIT_HAIRDRESSER_SUCCESS,
                editedHairdresser);

        IdCounter.reset();
        Model expectedModel = new ModelManager(TypicalHairdressers.getTypicalHairStyleX(), new UserPrefs());

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        IdCounter.reset();
        Model model = new ModelManager(TypicalHairdressers.getTypicalHairStyleX(), new UserPrefs());
        showHairdresserWithId(model, ID_FIRST_HAIRDRESSER);

        Hairdresser personInFilteredList = model.getHairdresserById(ID_FIRST_HAIRDRESSER);;
        Hairdresser editedHairdresser = new HairdresserBuilder(personInFilteredList)
                .withName(VALID_NAME_BENJAMIN).build(ID_FIRST_HAIRDRESSER);
        EditHairdresserCommand editCommand = new EditHairdresserCommand(ID_FIRST_HAIRDRESSER,
                new EditHairdresserDescriptorBuilder().withName(VALID_NAME_BENJAMIN).build());

        String expectedMessage = String.format(EditHairdresserCommand.MESSAGE_EDIT_HAIRDRESSER_SUCCESS,
                editedHairdresser);

        IdCounter.reset();
        Model expectedModel = new ModelManager(TypicalHairdressers.getTypicalHairStyleX(), new UserPrefs());
        expectedModel.setHairdresser(model.getHairdresserById(ID_FIRST_HAIRDRESSER), editedHairdresser);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateHairdresserUnfilteredList_failure() {
        IdCounter.reset();
        Model model = new ModelManager(TypicalHairdressers.getTypicalHairStyleX(), new UserPrefs());
        Hairdresser firstHairdresser = model.getHairdresserById(ID_FIRST_HAIRDRESSER);;
        EditHairdresserDescriptor descriptor = new EditHairdresserDescriptorBuilder(firstHairdresser).build();
        EditHairdresserCommand editCommand = new EditHairdresserCommand(ID_SECOND_HAIRDRESSER, descriptor);

        assertCommandFailure(editCommand, model, EditHairdresserCommand.MESSAGE_DUPLICATE_HAIRDRESSER);
    }

    @Test
    public void execute_duplicateHairdresserFilteredList_failure() {
        IdCounter.reset();
        Model model = new ModelManager(TypicalHairdressers.getTypicalHairStyleX(), new UserPrefs());
        showHairdresserWithId(model, ID_FIRST_HAIRDRESSER);

        // edit person in filtered list into a duplicate in address book
        Hairdresser personInList = model.getHairdresserById(ID_SECOND_HAIRDRESSER);;
        EditHairdresserCommand editCommand = new EditHairdresserCommand(ID_FIRST_HAIRDRESSER,
                new EditHairdresserDescriptorBuilder(personInList).build());

        assertCommandFailure(editCommand, model, EditHairdresserCommand.MESSAGE_DUPLICATE_HAIRDRESSER);
    }

    @Test
    public void execute_invalidHairdresserIndexUnfilteredList_failure() {
        IdCounter.reset();
        Model model = new ModelManager(TypicalHairdressers.getTypicalHairStyleX(), new UserPrefs());
        HairdresserId outOfBoundIndex = new HairdresserId(String.valueOf(model
                .getFilteredHairdresserList().size() + 1));
        EditHairdresserDescriptor descriptor = new EditHairdresserDescriptorBuilder()
                .withName(VALID_NAME_BENJAMIN).build();
        EditHairdresserCommand editCommand = new EditHairdresserCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_HAIRDRESSER_DISPLAYED_ID);
    }

    @Test
    public void equals() {
        final EditHairdresserCommand standardCommand = new EditHairdresserCommand(ID_FIRST_HAIRDRESSER, DESC_ALISSA);

        // same values -> returns true
        EditHairdresserDescriptor copyDescriptor = new EditHairdresserDescriptor(DESC_ALISSA);
        EditHairdresserCommand commandWithSameValues = new EditHairdresserCommand(ID_FIRST_HAIRDRESSER, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditHairdresserCommand(ID_SECOND_HAIRDRESSER, DESC_ALISSA)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditHairdresserCommand(ID_FIRST_HAIRDRESSER, DESC_BENJAMIN)));
    }

}
