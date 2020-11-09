package tutorspet.logic.commands.student;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static tutorspet.commons.core.Messages.MESSAGE_DUPLICATE_STUDENT;
import static tutorspet.commons.core.Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX;
import static tutorspet.logic.commands.CommandTestUtil.DESC_AMY;
import static tutorspet.logic.commands.CommandTestUtil.DESC_BOB;
import static tutorspet.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static tutorspet.logic.commands.CommandTestUtil.VALID_TAG_AVERAGE;
import static tutorspet.logic.commands.CommandTestUtil.VALID_TELEGRAM_BOB;
import static tutorspet.logic.commands.CommandTestUtil.assertCommandFailure;
import static tutorspet.logic.commands.CommandTestUtil.assertCommandSuccess;
import static tutorspet.logic.commands.CommandTestUtil.showStudentAtIndex;
import static tutorspet.logic.commands.student.EditStudentCommand.MESSAGE_COMMIT;
import static tutorspet.logic.commands.student.EditStudentCommand.MESSAGE_SUCCESS;
import static tutorspet.testutil.TypicalIndexes.INDEX_FIRST_ITEM;
import static tutorspet.testutil.TypicalIndexes.INDEX_SECOND_ITEM;
import static tutorspet.testutil.TypicalTutorsPet.getTypicalTutorsPet;

import org.junit.jupiter.api.Test;

import tutorspet.commons.core.index.Index;
import tutorspet.logic.commands.student.EditStudentCommand.EditStudentDescriptor;
import tutorspet.model.Model;
import tutorspet.model.ModelManager;
import tutorspet.model.TutorsPet;
import tutorspet.model.UserPrefs;
import tutorspet.model.student.Student;
import tutorspet.testutil.EditStudentDescriptorBuilder;
import tutorspet.testutil.StudentBuilder;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for
 * {@code EditStudentCommand}.
 */
public class EditStudentCommandTest {

    private Model model = new ModelManager(getTypicalTutorsPet(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Student editedStudent = new StudentBuilder().build();
        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder(editedStudent).build();
        EditStudentCommand editStudentCommand = new EditStudentCommand(INDEX_FIRST_ITEM, descriptor);

        String commitMessage = String.format(MESSAGE_COMMIT, editedStudent.getName());
        String expectedMessage = String.format(MESSAGE_SUCCESS, editedStudent);

        Model expectedModel = new ModelManager(new TutorsPet(model.getTutorsPet()), new UserPrefs());
        expectedModel.setStudent(model.getFilteredStudentList().get(0), editedStudent);
        expectedModel.commit(commitMessage);

        assertCommandSuccess(editStudentCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastStudent = Index.fromOneBased(model.getFilteredStudentList().size());
        Student lastStudent = model.getFilteredStudentList().get(indexLastStudent.getZeroBased());

        StudentBuilder studentInList = new StudentBuilder(lastStudent);
        Student editedStudent = studentInList.withName(VALID_NAME_BOB)
                .withTelegram(VALID_TELEGRAM_BOB)
                .withTags(VALID_TAG_AVERAGE).build();

        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder()
                .withName(VALID_NAME_BOB).withTelegram(VALID_TELEGRAM_BOB)
                .withTags(VALID_TAG_AVERAGE).build();
        EditStudentCommand editStudentCommand = new EditStudentCommand(indexLastStudent, descriptor);

        String commitMessage = String.format(MESSAGE_COMMIT, editedStudent.getName());
        String expectedMessage = String.format(MESSAGE_SUCCESS, editedStudent);

        Model expectedModel = new ModelManager(new TutorsPet(model.getTutorsPet()), new UserPrefs());
        expectedModel.setStudent(lastStudent, editedStudent);
        expectedModel.commit(commitMessage);

        assertCommandSuccess(editStudentCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditStudentCommand editStudentCommand =
                new EditStudentCommand(INDEX_FIRST_ITEM, new EditStudentDescriptor());
        Student editedStudent = model.getFilteredStudentList().get(INDEX_FIRST_ITEM.getZeroBased());

        String commitMessage = String.format(MESSAGE_COMMIT, editedStudent.getName());
        String expectedMessage = String.format(MESSAGE_SUCCESS, editedStudent);

        Model expectedModel = new ModelManager(new TutorsPet(model.getTutorsPet()), new UserPrefs());
        expectedModel.commit(commitMessage);

        assertCommandSuccess(editStudentCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showStudentAtIndex(model, INDEX_FIRST_ITEM);

        Student studentInFilteredList = model.getFilteredStudentList()
                .get(INDEX_FIRST_ITEM.getZeroBased());
        Student editedStudent = new StudentBuilder(studentInFilteredList)
                .withName(VALID_NAME_BOB).build();
        EditStudentCommand editStudentCommand = new EditStudentCommand(INDEX_FIRST_ITEM,
                new EditStudentDescriptorBuilder().withName(VALID_NAME_BOB).build());

        String commitMessage = String.format(MESSAGE_COMMIT, editedStudent.getName());
        String expectedMessage = String.format(MESSAGE_SUCCESS, editedStudent);

        Model expectedModel = new ModelManager(new TutorsPet(model.getTutorsPet()), new UserPrefs());
        expectedModel.setStudent(model.getFilteredStudentList().get(0), editedStudent);
        expectedModel.commit(commitMessage);

        assertCommandSuccess(editStudentCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateStudentUnfilteredList_failure() {
        Student firstStudent = model.getFilteredStudentList().get(INDEX_FIRST_ITEM.getZeroBased());
        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder(firstStudent).build();
        EditStudentCommand editStudentCommand = new EditStudentCommand(INDEX_SECOND_ITEM, descriptor);

        assertCommandFailure(editStudentCommand, model, MESSAGE_DUPLICATE_STUDENT);
    }

    @Test
    public void execute_duplicateStudentFilteredList_failure() {
        showStudentAtIndex(model, INDEX_FIRST_ITEM);

        // edit student in filtered list into a duplicate in Tutor's Pet
        Student studentInList = model.getTutorsPet().getStudentList()
                .get(INDEX_SECOND_ITEM.getZeroBased());
        EditStudentCommand editStudentCommand = new EditStudentCommand(INDEX_FIRST_ITEM,
                new EditStudentDescriptorBuilder(studentInList).build());

        assertCommandFailure(editStudentCommand, model, MESSAGE_DUPLICATE_STUDENT);
    }

    @Test
    public void execute_invalidStudentIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredStudentList().size() + 1);
        EditStudentDescriptor descriptor =
                new EditStudentDescriptorBuilder().withName(VALID_NAME_BOB).build();
        EditStudentCommand editStudentCommand = new EditStudentCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editStudentCommand, model, MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of student list.
     */
    @Test
    public void execute_invalidStudentIndexFilteredList_failure() {
        showStudentAtIndex(model, INDEX_FIRST_ITEM);
        Index outOfBoundIndex = INDEX_SECOND_ITEM;
        // ensures that outOfBoundIndex is still in bounds of student list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getTutorsPet().getStudentList().size());

        EditStudentCommand editStudentCommand = new EditStudentCommand(outOfBoundIndex,
                new EditStudentDescriptorBuilder().withName(VALID_NAME_BOB).build());

        assertCommandFailure(editStudentCommand, model, MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditStudentCommand standardCommand =
                new EditStudentCommand(INDEX_FIRST_ITEM, DESC_AMY);

        // same values -> returns true
        EditStudentDescriptor copyDescriptor =
                new EditStudentDescriptor(DESC_AMY);
        EditStudentCommand commandWithSameValues =
                new EditStudentCommand(INDEX_FIRST_ITEM, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(1));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditStudentCommand(INDEX_SECOND_ITEM, DESC_AMY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditStudentCommand(INDEX_FIRST_ITEM, DESC_BOB)));
    }
}
