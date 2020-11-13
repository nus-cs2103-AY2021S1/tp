package com.eva.logic.commands;

import static com.eva.logic.commands.CommandTestUtil.DESC_AMY;
import static com.eva.logic.commands.CommandTestUtil.DESC_BOB;
import static com.eva.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static com.eva.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static com.eva.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static com.eva.logic.commands.CommandTestUtil.assertCommandFailure;
import static com.eva.logic.commands.CommandTestUtil.assertCommandSuccess;
import static com.eva.logic.commands.CommandTestUtil.showApplicantAtIndex;
import static com.eva.logic.commands.EditApplicantCommand.MESSAGE_EDIT_APPLICANT_SUCCESS;
import static com.eva.logic.commands.EditApplicantCommand.MESSAGE_WRONG_PANEL;
import static com.eva.logic.parser.CliSyntax.PREFIX_APPLICANT;
import static com.eva.model.Model.PREDICATE_SHOW_ALL_APPLICANTS;
import static com.eva.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static com.eva.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static com.eva.testutil.TypicalPersons.getTypicalApplicantDatabase;
import static com.eva.testutil.TypicalPersons.getTypicalPersonDatabase;
import static com.eva.testutil.TypicalPersons.getTypicalStaffDatabase;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.eva.commons.core.Messages;
import com.eva.commons.core.PanelState;
import com.eva.commons.core.index.Index;
import com.eva.logic.commands.EditCommand.EditPersonDescriptor;
import com.eva.model.EvaDatabase;
import com.eva.model.Model;
import com.eva.model.ModelManager;
import com.eva.model.UserPrefs;
import com.eva.model.person.applicant.Applicant;
import com.eva.testutil.ApplicantBuilder;
import com.eva.testutil.EditPersonDescriptorBuilder;

public class EditApplicantCommandTest {
    private Model model = new ModelManager(getTypicalPersonDatabase(), getTypicalStaffDatabase(),
            getTypicalApplicantDatabase(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Applicant editedApplicant = new ApplicantBuilder().build();
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(editedApplicant).build();
        EditApplicantCommand editApplicantCommand = new EditApplicantCommand(INDEX_FIRST_PERSON, descriptor);

        String expectedMessage = String.format(MESSAGE_EDIT_APPLICANT_SUCCESS, editedApplicant);
        CommandResult expectedResult = new CommandResult(expectedMessage, false, false, true);

        Model expectedModel = new ModelManager(
                new EvaDatabase<>(model.getPersonDatabase()),
                new EvaDatabase<>(model.getStaffDatabase()),
                new EvaDatabase<>(model.getApplicantDatabase()),
                new UserPrefs());
        expectedModel.setApplicant(model.getFilteredApplicantList().get(0), editedApplicant);
        model.setPanelState(PanelState.APPLICANT_LIST);
        expectedModel.setPanelState(PanelState.APPLICANT_LIST);
        assertCommandSuccess(editApplicantCommand, model, expectedResult, expectedModel);
    }


    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastApplicant = Index.fromOneBased(model.getFilteredApplicantList().size());
        Applicant lastApplicant = model.getFilteredApplicantList().get(indexLastApplicant.getZeroBased());

        ApplicantBuilder applicantToEdit = new ApplicantBuilder(lastApplicant);
        Applicant editedApplicant = applicantToEdit.withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
                .withTags(VALID_TAG_FRIEND).build();

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withTags(VALID_TAG_FRIEND).build();
        EditApplicantCommand editApplicantCommand = new EditApplicantCommand(indexLastApplicant, descriptor);

        String expectedMessage = String.format(MESSAGE_EDIT_APPLICANT_SUCCESS, editedApplicant);
        CommandResult expectedResult = new CommandResult(expectedMessage, false, false, true);

        Model expectedModel = new ModelManager(
                new EvaDatabase<>(model.getPersonDatabase()),
                new EvaDatabase<>(model.getStaffDatabase()),
                new EvaDatabase<>(model.getApplicantDatabase()),
                new UserPrefs());
        model.setPanelState(PanelState.APPLICANT_LIST);
        expectedModel.setPanelState(PanelState.APPLICANT_LIST);
        expectedModel.setApplicant(lastApplicant, editedApplicant);

        assertCommandSuccess(editApplicantCommand, model, expectedResult, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditApplicantCommand editApplicantCommand = new EditApplicantCommand(INDEX_FIRST_PERSON,
                new EditPersonDescriptor());
        Applicant editedApplicant = model.getFilteredApplicantList().get(INDEX_FIRST_PERSON.getZeroBased());

        String expectedMessage = String.format(MESSAGE_EDIT_APPLICANT_SUCCESS, editedApplicant);
        CommandResult expectedResult = new CommandResult(expectedMessage, false, false, true);

        Model expectedModel = new ModelManager(
                new EvaDatabase<>(model.getPersonDatabase()),
                new EvaDatabase<>(model.getStaffDatabase()),
                new EvaDatabase<>(model.getApplicantDatabase()),
                new UserPrefs());

        model.setPanelState(PanelState.APPLICANT_LIST);
        expectedModel.setPanelState(PanelState.APPLICANT_LIST);
        assertCommandSuccess(editApplicantCommand, model, expectedResult, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showApplicantAtIndex(model, INDEX_FIRST_PERSON);

        Applicant applicantInFilteredList = model.getFilteredApplicantList().get(INDEX_FIRST_PERSON.getZeroBased());
        Applicant editedApplicant = new ApplicantBuilder(applicantInFilteredList).withName(VALID_NAME_BOB).build();
        EditApplicantCommand editApplicantCommand = new EditApplicantCommand(INDEX_FIRST_PERSON,
                new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB).build());

        String expectedMessage = String.format(MESSAGE_EDIT_APPLICANT_SUCCESS, editedApplicant);
        CommandResult expectedResult = new CommandResult(expectedMessage, false, false, true);

        Model expectedModel = new ModelManager(
                new EvaDatabase<>(model.getPersonDatabase()),
                new EvaDatabase<>(model.getStaffDatabase()),
                new EvaDatabase<>(model.getApplicantDatabase()),
                new UserPrefs());

        model.setPanelState(PanelState.APPLICANT_LIST);
        expectedModel.setPanelState(PanelState.APPLICANT_LIST);
        expectedModel.setApplicant(model.getFilteredApplicantList().get(0), editedApplicant);

        assertCommandSuccess(editApplicantCommand, model, expectedResult, expectedModel);
    }

    @Test
    public void execute_duplicatePersonUnfilteredList_failure() {
        Applicant firstApplicant = model.getFilteredApplicantList().get(INDEX_FIRST_PERSON.getZeroBased());
        EditPersonDescriptor applicantDescriptor = new EditPersonDescriptorBuilder(firstApplicant).build();
        EditApplicantCommand editApplicantCommand = new EditApplicantCommand(INDEX_SECOND_PERSON, applicantDescriptor);

        model.setPanelState(PanelState.APPLICANT_LIST);
        assertCommandFailure(editApplicantCommand, model, EditCommand.MESSAGE_DUPLICATE_PERSON);
    }

    @Test
    public void execute_duplicatePersonFilteredList_failure() {
        showApplicantAtIndex(model, INDEX_FIRST_PERSON);

        // edit Applicant in filtered list into a duplicate in eva database
        Applicant applicantInList = model.getApplicantDatabase().getPersonList().get(
                INDEX_SECOND_PERSON.getZeroBased());
        EditApplicantCommand editApplicantCommand = new EditApplicantCommand(INDEX_FIRST_PERSON,
                new EditPersonDescriptorBuilder(applicantInList).build());

        model.setPanelState(PanelState.APPLICANT_LIST);
        assertCommandFailure(editApplicantCommand, model, EditCommand.MESSAGE_DUPLICATE_PERSON);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredApplicantList().size() + 1);
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB).build();
        EditApplicantCommand editApplicantCommand = new EditApplicantCommand(outOfBoundIndex, descriptor);

        model.setPanelState(PanelState.APPLICANT_LIST);
        assertCommandFailure(editApplicantCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of eva database
     */
    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        showApplicantAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of eva database list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getApplicantDatabase().getPersonList().size());

        EditApplicantCommand editApplicantCommand = new EditApplicantCommand(outOfBoundIndex,
                new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB).build());

        model.setPanelState(PanelState.APPLICANT_LIST);

        assertCommandFailure(editApplicantCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where user is seeing incorrect panel,
     * but smaller than size of eva database
     */
    @Test
    public void execute_invalidPanel_failure() {
        model.updateFilteredApplicantList(PREDICATE_SHOW_ALL_APPLICANTS);
        EditApplicantCommand editApplicantCommand = new EditApplicantCommand(INDEX_FIRST_PERSON,
                new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB).build());
        String expectedMessage = String.format(Messages.MESSAGE_INVALID_COMMAND_AT_PANEL,
                MESSAGE_WRONG_PANEL);

        model.setPanelState(PanelState.STAFF_LIST);

        assertCommandFailure(editApplicantCommand, model, expectedMessage);
    }


    @Test
    public void equals() {
        final EditApplicantCommand standardCommand = new EditApplicantCommand(INDEX_FIRST_PERSON, DESC_AMY);

        // same values -> returns true
        EditPersonDescriptor copyDescriptor = new EditPersonDescriptor(DESC_AMY);
        EditApplicantCommand commandWithSameValues = new EditApplicantCommand(INDEX_FIRST_PERSON, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand(PREFIX_APPLICANT)));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditApplicantCommand(INDEX_SECOND_PERSON, DESC_AMY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditApplicantCommand(INDEX_FIRST_PERSON, DESC_BOB)));
    }
}
