package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FEE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SCHOOL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalStudents.getTypicalAddressBook;
import static seedu.address.testutil.notes.TypicalNotes.getTypicalNotebook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand.EditAdminDescriptor;
import seedu.address.logic.commands.EditCommand.EditStudentDescriptor;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.Reeve;
import seedu.address.model.UserPrefs;
import seedu.address.model.student.Student;
import seedu.address.testutil.EditAdminDescriptorBuilder;
import seedu.address.testutil.EditStudentDescriptorBuilder;
import seedu.address.testutil.StudentBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalNotebook());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Student editedStudent = new StudentBuilder().build();
        EditStudentDescriptor editStudentDescriptor = new EditStudentDescriptorBuilder(editedStudent).build();
        EditAdminDescriptor editAdminDescriptor = new EditAdminDescriptorBuilder(editedStudent).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_PERSON, editStudentDescriptor, editAdminDescriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedStudent);

        Model expectedModel = new ModelManager(new Reeve(model.getReeve()), new UserPrefs(), getTypicalNotebook());
        expectedModel.setStudent(model.getFilteredStudentList().get(INDEX_FIRST_PERSON.getZeroBased()), editedStudent);


        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastPerson = Index.fromOneBased(model.getSortedStudentList().size());
        Student lastStudent = model.getSortedStudentList().get(indexLastPerson.getZeroBased());

        StudentBuilder personInList = new StudentBuilder(lastStudent);
        Student editedStudent = personInList.withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB).withFee(VALID_FEE_BOB)
                .build();

        EditStudentDescriptor editStudentDescriptor = new EditStudentDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).build();
        EditAdminDescriptor editAdminDescriptor = new EditAdminDescriptorBuilder(editedStudent).withFee(VALID_FEE_BOB)
                .build();
        EditCommand editCommand = new EditCommand(indexLastPerson, editStudentDescriptor, editAdminDescriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedStudent);

        Model expectedModel = new ModelManager(new Reeve(model.getReeve()), new UserPrefs(), getTypicalNotebook());
        expectedModel.setStudent(lastStudent, editedStudent);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditCommand editCommand = new EditCommand(INDEX_FIRST_PERSON, new EditStudentDescriptor(),
                new EditAdminDescriptor());
        Student editedStudent = model.getSortedStudentList().get(INDEX_FIRST_PERSON.getZeroBased());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedStudent);

        Model expectedModel = new ModelManager(new Reeve(model.getReeve()), new UserPrefs(), getTypicalNotebook());

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Student studentInSortedList = model.getSortedStudentList().get(INDEX_FIRST_PERSON.getZeroBased());
        Student editedStudent = new StudentBuilder(studentInSortedList).withSchool(VALID_SCHOOL_BOB)
                .withFee(VALID_FEE_BOB).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_PERSON,
                new EditStudentDescriptorBuilder().withSchool(VALID_SCHOOL_BOB).build(),
                new EditAdminDescriptorBuilder().withFee(VALID_FEE_BOB).build());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedStudent);

        Model expectedModel = new ModelManager(new Reeve(model.getReeve()), new UserPrefs(), getTypicalNotebook());
        expectedModel.setStudent(studentInSortedList, editedStudent);
        showPersonAtIndex(expectedModel, INDEX_FIRST_PERSON);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicatePersonUnfilteredList_failure() {
        Student firstStudent = model.getSortedStudentList().get(INDEX_FIRST_PERSON.getZeroBased());
        EditStudentDescriptor editStudentDescriptor = new EditStudentDescriptorBuilder(firstStudent).build();
        EditAdminDescriptor editAdminDescriptor = new EditAdminDescriptorBuilder(firstStudent).build();
        EditCommand editCommand = new EditCommand(INDEX_SECOND_PERSON, editStudentDescriptor, editAdminDescriptor);

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_PERSON);
    }

    @Test
    public void execute_duplicatePersonFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        // edit person in filtered list into a duplicate in address book
        Student studentInList = model.getReeve().getStudentList().get(INDEX_SECOND_PERSON.getZeroBased());
        EditCommand editCommand = new EditCommand(INDEX_FIRST_PERSON,
                new EditStudentDescriptorBuilder(studentInList).build(),
                new EditAdminDescriptorBuilder(studentInList).build());

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_PERSON);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getSortedStudentList().size() + 1);
        EditStudentDescriptor editStudentDescriptor = new EditStudentDescriptorBuilder()
                .withName(VALID_NAME_BOB).build();
        EditAdminDescriptor editAdminDescriptor = new EditAdminDescriptorBuilder().build();
        EditCommand editCommand = new EditCommand(outOfBoundIndex, editStudentDescriptor, editAdminDescriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getReeve().getStudentList().size());

        EditCommand editCommand = new EditCommand(outOfBoundIndex,
                new EditStudentDescriptorBuilder().withName(VALID_NAME_BOB).build(),
                new EditAdminDescriptorBuilder().build());

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_clashingClassTimeUnfilteredList_failure() {
        String clash = getClassTimeInputString();
        assertEditClassTimeFailure(clash);
    }

    @Test
    public void execute_clashingClassTimeFilteredList_failure() {
        String clash = getClassTimeInputString();
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        assertEditClassTimeFailure(clash);
    }

    @Test
    public void equals() {
        final EditCommand standardCommand = new EditCommand(INDEX_FIRST_PERSON, DESC_AMY,
                new EditAdminDescriptorBuilder().build());

        // same values -> returns true
        EditStudentDescriptor copyStudentDescriptor = new EditStudentDescriptor(DESC_AMY);
        EditCommand commandWithSameValues = new EditCommand(INDEX_FIRST_PERSON, copyStudentDescriptor,
                new EditAdminDescriptorBuilder().build());
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_SECOND_PERSON, DESC_AMY,
                new EditAdminDescriptorBuilder().build())));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_FIRST_PERSON, DESC_BOB,
                new EditAdminDescriptorBuilder().build())));
    }

    /**
     * Tests that a class time clash error is thrown.
     */
    private void assertEditClassTimeFailure(String classTimeInput) {
        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder().build();
        EditAdminDescriptor adminDescriptor = new EditAdminDescriptorBuilder().withTime(classTimeInput).build();
        EditCommand command = new EditCommand(INDEX_FIRST_PERSON, descriptor, adminDescriptor);

        assertCommandFailure(command, model, Messages.MESSAGE_CLASHING_LESSON);
    }

    private String getClassTimeInputString() {
        return model.getSortedStudentList().get(INDEX_SECOND_PERSON.getZeroBased())
                .getClassTime()
                .convertClassTimeToUserInputString();
    }

}
