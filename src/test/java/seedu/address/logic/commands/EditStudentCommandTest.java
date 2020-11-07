package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_ALEX;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BENG;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BENG;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BENG;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showStudentAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalModulesPopulatedWithTutorialGroups.CS2103T;
import static seedu.address.testutil.TypicalModulesPopulatedWithTutorialGroups.getTypicalTrackr;
import static seedu.address.testutil.TypicalTutorialGroups.T05;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditStudentCommand.EditStudentDescriptor;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.Trackr;
import seedu.address.model.UserPrefs;
import seedu.address.model.module.Module;
import seedu.address.model.student.Student;
import seedu.address.model.tutorialgroup.TutorialGroup;
import seedu.address.testutil.EditStudentDescriptorBuilder;
import seedu.address.testutil.StudentBuilder;

public class EditStudentCommandTest {

    private Model model = new ModelManager(getTypicalTrackr(), new UserPrefs());
    private Module moduleInView = CS2103T;
    private TutorialGroup tgInView = T05;

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        model.setViewToTutorialGroup(moduleInView);
        model.setViewToStudent(tgInView);
        Student editedStudent = new StudentBuilder().withStudentId("A1234567T").build();
        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder(editedStudent).build();
        EditStudentCommand editCommand = new EditStudentCommand(INDEX_FIRST_PERSON, descriptor);

        String expectedMessage = String.format(EditStudentCommand.MESSAGE_EDIT_STUDENT_SUCCESS, editedStudent);

        Model expectedModel = new ModelManager(new Trackr(model.getModuleList()), new UserPrefs());
        expectedModel.setViewToTutorialGroup(moduleInView);
        expectedModel.setViewToStudent(tgInView);
        expectedModel.setStudent(model.getFilteredStudentList().get(0), editedStudent);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        model.setViewToTutorialGroup(moduleInView);
        model.setViewToStudent(tgInView);
        Index indexLastStudent = Index.fromOneBased(model.getFilteredStudentList().size());
        Student lastStudent = model.getFilteredStudentList().get(indexLastStudent.getZeroBased());

        StudentBuilder studentInList = new StudentBuilder(lastStudent);
        Student editedStudent = studentInList.withName(VALID_NAME_BENG).withPhone(VALID_PHONE_BENG).build();
        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder().withName(VALID_NAME_BENG)
                .withPhone(VALID_PHONE_BENG).build();
        EditStudentCommand editStudentCommand = new EditStudentCommand(indexLastStudent, descriptor);

        String expectedMessage = String.format(EditStudentCommand.MESSAGE_EDIT_STUDENT_SUCCESS, editedStudent);

        Model expectedModel = new ModelManager(new Trackr(model.getModuleList()), new UserPrefs());
        expectedModel.setViewToTutorialGroup(moduleInView);
        expectedModel.setViewToStudent(tgInView);
        expectedModel.setStudent(lastStudent, editedStudent);

        assertCommandSuccess(editStudentCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        model.setViewToTutorialGroup(moduleInView);
        model.setViewToStudent(tgInView);
        EditStudentCommand editStudentCommand = new EditStudentCommand(INDEX_FIRST_PERSON, new EditStudentDescriptor());
        Student editedStudent = model.getFilteredStudentList().get(INDEX_FIRST_PERSON.getZeroBased());

        String expectedMessage = String.format(EditStudentCommand.MESSAGE_EDIT_STUDENT_SUCCESS, editedStudent);

        Model expectedModel = new ModelManager(new Trackr(model.getModuleList()), new UserPrefs());
        expectedModel.setViewToTutorialGroup(moduleInView);
        expectedModel.setViewToStudent(tgInView);

        assertCommandSuccess(editStudentCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showStudentAtIndex(model, INDEX_FIRST_PERSON, moduleInView, tgInView);

        Student studentInFilteredList = model.getFilteredStudentList().get(INDEX_FIRST_PERSON.getZeroBased());
        Student editedStudent = new StudentBuilder(studentInFilteredList).withPhone(VALID_PHONE_BENG).build();
        EditStudentCommand editStudentCommand = new EditStudentCommand(INDEX_FIRST_PERSON,
                new EditStudentDescriptorBuilder().withPhone(VALID_PHONE_BENG).build());

        String expectedMessage = String.format(EditStudentCommand.MESSAGE_EDIT_STUDENT_SUCCESS, editedStudent);

        Model expectedModel = new ModelManager(new Trackr(model.getModuleList()), new UserPrefs());
        expectedModel.setViewToTutorialGroup(moduleInView);
        expectedModel.setViewToStudent(tgInView);
        expectedModel.setStudent(model.getFilteredStudentList().get(0), editedStudent);

        assertCommandSuccess(editStudentCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateStudentUnfilteredList_failure() {
        model.setViewToTutorialGroup(moduleInView);
        model.setViewToStudent(tgInView);
        Student firstStudent = model.getFilteredStudentList().get(INDEX_FIRST_PERSON.getZeroBased());
        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder(firstStudent).build();
        EditStudentCommand editCommand = new EditStudentCommand(INDEX_SECOND_PERSON, descriptor);

        assertCommandFailure(editCommand, model, EditStudentCommand.MESSAGE_DUPLICATE_STUDENT);
    }

    @Test
    public void execute_duplicateStudentFilteredList_failure() {
        showStudentAtIndex(model, INDEX_FIRST_PERSON, moduleInView, tgInView);

        // edit student in filtered list into a duplicate in student list
        Student studentInList = model
                .getModuleList()
                .getStudentList(moduleInView, tgInView)
                .get(INDEX_SECOND_PERSON.getZeroBased());
        EditStudentCommand editCommand = new EditStudentCommand(INDEX_FIRST_PERSON,
                new EditStudentDescriptorBuilder(studentInList).build());

        assertCommandFailure(editCommand, model, EditStudentCommand.MESSAGE_DUPLICATE_STUDENT);
    }

    @Test
    public void execute_invalidStudentIndexUnfilteredList_failure() {
        model.setViewToTutorialGroup(moduleInView);
        model.setViewToStudent(tgInView);
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredStudentList().size() + 1);
        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder().withName(VALID_NAME_BENG).build();
        EditStudentCommand editCommand = new EditStudentCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of student list
     */
    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        showStudentAtIndex(model, INDEX_FIRST_PERSON, moduleInView, tgInView);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased()
                < model.getModuleList().getStudentList(moduleInView, tgInView).size());

        EditStudentCommand editCommand = new EditStudentCommand(outOfBoundIndex,
                new EditStudentDescriptorBuilder().withName(VALID_NAME_BENG).build());

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditStudentCommand standardCommand = new EditStudentCommand(INDEX_FIRST_PERSON, DESC_ALEX);

        // same values -> returns true
        EditStudentDescriptor copyDescriptor = new EditStudentDescriptor(DESC_ALEX);
        EditStudentCommand commandWithSameValues = new EditStudentCommand(INDEX_FIRST_PERSON, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditStudentCommand(INDEX_SECOND_PERSON, DESC_ALEX)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditStudentCommand(INDEX_FIRST_PERSON, DESC_BENG)));
    }
}
