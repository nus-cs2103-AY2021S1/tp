package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.logic.commands.EditDetailCommand.MESSAGE_BAD_DETAIL_INDEX;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalStudents.ALICE;
import static seedu.address.testutil.TypicalStudents.BENSON;
import static seedu.address.testutil.TypicalStudents.getTypicalAddressBook;
import static seedu.address.testutil.notes.TypicalNotes.getTypicalNotebook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.student.Student;
import seedu.address.model.student.admin.Detail;
import seedu.address.testutil.StudentBuilder;

public class EditDetailCommandTest {

    //@@author VaishakAnand
    private static final Index TEST_INDEX_FIRST_STUDENT = INDEX_FIRST_PERSON;
    private static final Index TEST_INDEX_SECOND_STUDENT = INDEX_SECOND_PERSON;
    private static final Index TEST_INDEX_FIRST_DETAIL = INDEX_FIRST_PERSON;
    private static final Index TEST_INDEX_SECOND_DETAIL = INDEX_SECOND_PERSON;
    private static final String TEST_CORRECT_DETAIL = "eats flies";
    private static final String TEST_WRONG_DETAIL = "drinks flies";

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalNotebook());

    @Test
    public void constructor_null_throwsNullPointerException() {

        // all 3 arguments null
        assertThrows(NullPointerException.class, () ->
                new EditDetailCommand(null, null, null));

        // 2 arguments null
        assertThrows(NullPointerException.class, () ->
                new EditDetailCommand(TEST_INDEX_FIRST_STUDENT, null, null));
        assertThrows(NullPointerException.class, () ->
                new EditDetailCommand(null, TEST_INDEX_FIRST_DETAIL, null));
        assertThrows(NullPointerException.class, () ->
                new EditDetailCommand(null,
                        null, new Detail(TEST_CORRECT_DETAIL)));

        // 1 argument null
        assertThrows(NullPointerException.class, () ->
                new EditDetailCommand(TEST_INDEX_FIRST_STUDENT, TEST_INDEX_FIRST_DETAIL, null));
        assertThrows(NullPointerException.class, () ->
                new EditDetailCommand(TEST_INDEX_FIRST_STUDENT,
                        null, new Detail(TEST_CORRECT_DETAIL)));
        assertThrows(NullPointerException.class, () ->
                new EditDetailCommand(null,
                        TEST_INDEX_FIRST_DETAIL, new Detail(TEST_CORRECT_DETAIL)));
    }

    @Test
    public void execute_validStudentIndexUnfilteredList_success() {
        Student asker = model.getSortedStudentList().get(INDEX_FIRST_PERSON.getZeroBased());
        Student clone = new StudentBuilder(asker).withDetails(TEST_WRONG_DETAIL).build();
        Detail detail = new Detail(TEST_CORRECT_DETAIL);
        EditDetailCommand editDetailCommand = new EditDetailCommand(
                TEST_INDEX_FIRST_STUDENT, TEST_INDEX_FIRST_DETAIL, detail);
        Student expectedStudent = new StudentBuilder(ALICE).withDetails(TEST_CORRECT_DETAIL).build();
        model.setStudent(asker, clone);

        String expectedMessage = String.format(EditDetailCommand.MESSAGE_SUCCESS,
                clone.getName(), detail);

        ModelManager expectedModel = new ModelManager(model.getReeve(), new UserPrefs(), getTypicalNotebook());
        expectedModel.setStudent(clone, expectedStudent);

        assertCommandSuccess(editDetailCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidStudentIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundsStudentIndex = Index.fromOneBased(model.getSortedStudentList().size() + 1);
        Detail detail = new Detail(TEST_CORRECT_DETAIL);
        EditDetailCommand invalidCommand = new EditDetailCommand(
                outOfBoundsStudentIndex, TEST_INDEX_FIRST_DETAIL, detail);

        assertCommandFailure(invalidCommand, model, MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidDetailIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundsDetailIndex =
                Index.fromOneBased(model.getSortedStudentList().get(0).getDetails().size() + 1);
        Detail detail = new Detail(TEST_CORRECT_DETAIL);
        EditDetailCommand invalidCommand = new EditDetailCommand(
                TEST_INDEX_FIRST_STUDENT, outOfBoundsDetailIndex, detail);

        assertCommandFailure(invalidCommand, model, MESSAGE_BAD_DETAIL_INDEX);
    }

    @Test
    public void execute_validStudentIndexFilteredList_success() {
        showPersonAtIndex(model, INDEX_SECOND_PERSON);

        Student asker = model.getSortedStudentList().get(INDEX_FIRST_PERSON.getZeroBased());
        Student clone = new StudentBuilder(asker).withDetails(TEST_WRONG_DETAIL).build();
        Detail detail = new Detail(TEST_CORRECT_DETAIL);
        EditDetailCommand editDetailCommand = new EditDetailCommand(
                TEST_INDEX_FIRST_STUDENT, TEST_INDEX_FIRST_DETAIL, detail);
        Student expectedStudent = new StudentBuilder(BENSON).withDetails(TEST_CORRECT_DETAIL).build();
        model.setStudent(asker, clone);

        String expectedMessage = String.format(EditDetailCommand.MESSAGE_SUCCESS,
                clone.getName(), detail);

        ModelManager expectedModel = new ModelManager(model.getReeve(), new UserPrefs(), getTypicalNotebook());
        expectedModel.setStudent(clone, expectedStudent);
        showPersonAtIndex(expectedModel, INDEX_SECOND_PERSON);

        assertCommandSuccess(editDetailCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidStudentIndexFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_SECOND_PERSON);

        Index outOfBoundsStudentIndex = Index.fromOneBased(model.getSortedStudentList().size() + 1);
        Detail detail = new Detail(TEST_CORRECT_DETAIL);
        EditDetailCommand invalidCommand = new EditDetailCommand(
                outOfBoundsStudentIndex, TEST_INDEX_FIRST_DETAIL, detail);

        assertCommandFailure(invalidCommand, model, MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidDetailIndexFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_SECOND_PERSON);

        Index outOfBoundsDetailIndex =
                Index.fromOneBased(model.getSortedStudentList().get(0).getDetails().size() + 1);
        Detail detail = new Detail(TEST_CORRECT_DETAIL);
        EditDetailCommand invalidCommand = new EditDetailCommand(
                TEST_INDEX_FIRST_STUDENT, outOfBoundsDetailIndex, detail);

        assertCommandFailure(invalidCommand, model, MESSAGE_BAD_DETAIL_INDEX);
    }

    @Test
    public void equals() {
        Detail testDetail = new Detail(TEST_CORRECT_DETAIL);
        EditDetailCommand editDetailCommand =
                new EditDetailCommand(TEST_INDEX_FIRST_STUDENT, TEST_INDEX_FIRST_DETAIL, testDetail);

        // same object -> return true;
        assertTrue(editDetailCommand.equals(editDetailCommand));

        // different object -> return false;
        assertFalse(editDetailCommand.equals("hello"));

        // same fields -> return true;
        assertTrue(editDetailCommand.equals(new EditDetailCommand(TEST_INDEX_FIRST_STUDENT,
                TEST_INDEX_FIRST_DETAIL, testDetail)));

        // different student index -> return false;
        assertFalse(editDetailCommand.equals(new EditDetailCommand(TEST_INDEX_SECOND_STUDENT,
                TEST_INDEX_FIRST_DETAIL, testDetail)));

        // different detail index -> return false;
        assertFalse(editDetailCommand.equals(new EditDetailCommand(TEST_INDEX_FIRST_STUDENT,
                TEST_INDEX_SECOND_DETAIL, testDetail)));

        // different detail -> return false;
        assertFalse(editDetailCommand.equals(new EditDetailCommand(TEST_INDEX_FIRST_STUDENT,
                TEST_INDEX_FIRST_DETAIL, new Detail(TEST_WRONG_DETAIL))));
    }

}
