package com.eva.logic.commands;

import static com.eva.logic.commands.CommandTestUtil.DESC_AMY;
import static com.eva.logic.commands.CommandTestUtil.DESC_BOB;
import static com.eva.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static com.eva.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static com.eva.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static com.eva.logic.commands.CommandTestUtil.assertCommandFailure;
import static com.eva.logic.commands.CommandTestUtil.assertCommandSuccess;
import static com.eva.logic.commands.CommandTestUtil.showStaffAtIndex;
import static com.eva.logic.parser.CliSyntax.PREFIX_STAFF;
import static com.eva.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static com.eva.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static com.eva.testutil.TypicalPersons.getTypicalApplicantDatabase;
import static com.eva.testutil.TypicalPersons.getTypicalPersonDatabase;
import static com.eva.testutil.TypicalPersons.getTypicalStaffDatabase;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.eva.commons.core.Messages;
import com.eva.commons.core.index.Index;
import com.eva.logic.commands.EditCommand.EditPersonDescriptor;
import com.eva.model.EvaDatabase;
import com.eva.model.Model;
import com.eva.model.ModelManager;
import com.eva.model.UserPrefs;
import com.eva.model.person.staff.Staff;
import com.eva.testutil.EditPersonDescriptorBuilder;
import com.eva.testutil.staff.StaffBuilder;

public class EditStaffTest {
    private Model model = new ModelManager(getTypicalPersonDatabase(), getTypicalStaffDatabase(),
            getTypicalApplicantDatabase(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Staff editedStaff = new StaffBuilder().build();
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(editedStaff).build();
        EditStaffCommand editStaffCommand = new EditStaffCommand(INDEX_FIRST_PERSON, descriptor);

        String expectedMessage = String.format(EditStaffCommand.MESSAGE_EDIT_STAFF_SUCCESS, editedStaff);
        CommandResult expectedResult = new CommandResult(expectedMessage, false, false, true);

        Model expectedModel = new ModelManager(
                new EvaDatabase<>(model.getPersonDatabase()),
                new EvaDatabase<>(model.getStaffDatabase()),
                new EvaDatabase<>(model.getApplicantDatabase()),
                new UserPrefs());
        expectedModel.setStaff(model.getFilteredStaffList().get(0), editedStaff);

        assertCommandSuccess(editStaffCommand, model, expectedResult, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastStaff = Index.fromOneBased(model.getFilteredStaffList().size());
        Staff lastStaff = model.getFilteredStaffList().get(indexLastStaff.getZeroBased());

        StaffBuilder staffInList = new StaffBuilder(lastStaff);
        Staff editedStaff = staffInList.withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
                .withTags(VALID_TAG_HUSBAND).build();

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withTags(VALID_TAG_HUSBAND).build();
        EditStaffCommand editStaffCommand = new EditStaffCommand(indexLastStaff, descriptor);

        String expectedMessage = String.format(EditStaffCommand.MESSAGE_EDIT_STAFF_SUCCESS, editedStaff);
        CommandResult expectedResult = new CommandResult(expectedMessage, false, false, true);

        Model expectedModel = new ModelManager(
                new EvaDatabase<>(model.getPersonDatabase()),
                new EvaDatabase<>(model.getStaffDatabase()),
                new EvaDatabase<>(model.getApplicantDatabase()),
                new UserPrefs());
        expectedModel.setStaff(lastStaff, editedStaff);

        assertCommandSuccess(editStaffCommand, model, expectedResult, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditStaffCommand editStaffCommand = new EditStaffCommand(INDEX_FIRST_PERSON, new EditPersonDescriptor());
        Staff editedStaff = model.getFilteredStaffList().get(INDEX_FIRST_PERSON.getZeroBased());

        String expectedMessage = String.format(EditStaffCommand.MESSAGE_EDIT_STAFF_SUCCESS, editedStaff);
        CommandResult expectedResult = new CommandResult(expectedMessage, false, false, true);

        Model expectedModel = new ModelManager(
                new EvaDatabase<>(model.getPersonDatabase()),
                new EvaDatabase<>(model.getStaffDatabase()),
                new EvaDatabase<>(model.getApplicantDatabase()),
                new UserPrefs());

        assertCommandSuccess(editStaffCommand, model, expectedResult, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showStaffAtIndex(model, INDEX_FIRST_PERSON);

        Staff staffInFilteredList = model.getFilteredStaffList().get(INDEX_FIRST_PERSON.getZeroBased());
        Staff editedStaff = new StaffBuilder(staffInFilteredList).withName(VALID_NAME_BOB).build();
        EditStaffCommand editStaffCommand = new EditStaffCommand(INDEX_FIRST_PERSON,
                new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB).build());

        String expectedMessage = String.format(EditStaffCommand.MESSAGE_EDIT_STAFF_SUCCESS, editedStaff);
        CommandResult expectedResult = new CommandResult(expectedMessage, false, false, true);

        Model expectedModel = new ModelManager(
                new EvaDatabase<>(model.getPersonDatabase()),
                new EvaDatabase<>(model.getStaffDatabase()),
                new EvaDatabase<>(model.getApplicantDatabase()),
                new UserPrefs());
        expectedModel.setStaff(model.getFilteredStaffList().get(0), editedStaff);

        assertCommandSuccess(editStaffCommand, model, expectedResult, expectedModel);
    }

    @Test
    public void execute_duplicatePersonUnfilteredList_failure() {
        Staff firstStaff = model.getFilteredStaffList().get(INDEX_FIRST_PERSON.getZeroBased());
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(firstStaff).build();
        EditStaffCommand editStaffCommand = new EditStaffCommand(INDEX_SECOND_PERSON, descriptor);

        assertCommandFailure(editStaffCommand, model, EditCommand.MESSAGE_DUPLICATE_PERSON);
    }

    @Test
    public void execute_duplicatePersonFilteredList_failure() {
        showStaffAtIndex(model, INDEX_FIRST_PERSON);

        // edit staff in filtered list into a duplicate in eva database
        Staff staffInList = model.getStaffDatabase().getPersonList().get(INDEX_SECOND_PERSON.getZeroBased());
        EditStaffCommand editStaffCommand = new EditStaffCommand(INDEX_FIRST_PERSON,
                new EditPersonDescriptorBuilder(staffInList).build());

        assertCommandFailure(editStaffCommand, model, EditCommand.MESSAGE_DUPLICATE_PERSON);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredStaffList().size() + 1);
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB).build();
        EditStaffCommand editStaffCommand = new EditStaffCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editStaffCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of eva database
     */
    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        showStaffAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of eva database list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getStaffDatabase().getPersonList().size());

        EditStaffCommand editStaffCommand = new EditStaffCommand(outOfBoundIndex,
                new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB).build());

        assertCommandFailure(editStaffCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditStaffCommand standardCommand = new EditStaffCommand(INDEX_FIRST_PERSON, DESC_AMY);

        // same values -> returns true
        EditPersonDescriptor copyDescriptor = new EditPersonDescriptor(DESC_AMY);
        EditStaffCommand commandWithSameValues = new EditStaffCommand(INDEX_FIRST_PERSON, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand(PREFIX_STAFF)));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditStaffCommand(INDEX_SECOND_PERSON, DESC_AMY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditStaffCommand(INDEX_FIRST_PERSON, DESC_BOB)));
    }
}
