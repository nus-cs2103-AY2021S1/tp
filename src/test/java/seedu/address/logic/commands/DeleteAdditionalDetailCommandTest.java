package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalStudents.ALICE;
import static seedu.address.testutil.TypicalStudents.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.student.Student;
import seedu.address.model.student.admin.AdditionalDetail;
import seedu.address.testutil.StudentBuilder;

public class DeleteAdditionalDetailCommandTest {

    private static final Index TEST_INDEX_FIRST_STUDENT = INDEX_FIRST_PERSON;
    private static final Index TEST_INDEX_SECOND_STUDENT = INDEX_SECOND_PERSON;
    private static final Index TEST_INDEX_FIRST_DETAIL = INDEX_FIRST_PERSON;
    private static final Index TEST_INDEX_SECOND_DETAIL = INDEX_SECOND_PERSON;
    private static final String TEST_DETAIL = "eats flies";

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void constructor_null_throwsNullPointerException() {

        // both arguments null
        assertThrows(NullPointerException.class, () ->
                new DeleteAdditionalDetailCommand(null, null));

        // one argument null
        assertThrows(NullPointerException.class, () ->
                new DeleteAdditionalDetailCommand(null, TEST_INDEX_FIRST_STUDENT));
        assertThrows(NullPointerException.class, () ->
                new DeleteAdditionalDetailCommand(TEST_INDEX_FIRST_STUDENT, null));
    }

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Student asker = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Student clone = new StudentBuilder(asker).withDetails(TEST_DETAIL).build();
        AdditionalDetail additionalDetail = new AdditionalDetail(TEST_DETAIL);
        DeleteAdditionalDetailCommand deleteAdditionalDetailCommand =
                new DeleteAdditionalDetailCommand(TEST_INDEX_FIRST_STUDENT, TEST_INDEX_FIRST_DETAIL);
        Student expectedStudent = new StudentBuilder(ALICE).withDetails().build();
        model.setPerson(asker, clone);

        String expectedMessage = String.format(DeleteAdditionalDetailCommand.MESSAGE_SUCCESS, additionalDetail);

        ModelManager expectedModel = new ModelManager(model.getReeve(), new UserPrefs());
        expectedModel.setPerson(clone, expectedStudent);

        assertCommandSuccess(deleteAdditionalDetailCommand, model, expectedMessage, expectedModel);
    }
//
//    @Test
//    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
//        Index outOfBounds = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
//        AddAdditionalDetailCommand command = new AddAdditionalDetailCommand(outOfBounds,
//                new AdditionalDetail(TEST_DETAIL));
//
//        assertCommandFailure(command, model, MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
//    }
//
//    @Test
//    public void execute_validIndexFilteredList_throwsCommandException() {
//        showPersonAtIndex(model, INDEX_SECOND_PERSON);
//
//        Student asker = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
//        AdditionalDetail detail = new AdditionalDetail(TEST_DETAIL);
//        Student clone = new StudentBuilder(asker).withDetails().build();
//        model.setPerson(asker, clone);
//
//        AddAdditionalDetailCommand command = new AddAdditionalDetailCommand(INDEX_FIRST_PERSON, detail);
//        Student expectedStudent = new StudentBuilder(BENSON).withDetails(TEST_DETAIL).build();
//
//        String expectedMessage = String.format(AddAdditionalDetailCommand.MESSAGE_SUCCESS, detail);
//
//        ModelManager expectedModel = new ModelManager(model.getReeve(), new UserPrefs());
//        expectedModel.setPerson(clone, expectedStudent);
//
//        assertCommandSuccess(command, model, expectedMessage, expectedModel);
//    }
//
//    @Test
//    public void execute_invalidIndexFilteredList_throwsCommandException() {
//        showPersonAtIndex(model, INDEX_SECOND_PERSON);
//
//        Index outOfBounds = INDEX_SECOND_PERSON;
//        AdditionalDetail detail = new AdditionalDetail(TEST_DETAIL);
//        AddAdditionalDetailCommand invalidCommand = new AddAdditionalDetailCommand(outOfBounds, detail);
//
//        assertCommandFailure(invalidCommand, model, MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
//    }
//
//    @Test
//    public void equals() {
//        AdditionalDetail testDetail = new AdditionalDetail(TEST_DETAIL);
//        AddAdditionalDetailCommand addAdditionalDetailCommand =
//                new AddAdditionalDetailCommand(INDEX_FIRST_PERSON, testDetail);
//
//        // same object -> return true;
//        assertTrue(addAdditionalDetailCommand.equals(addAdditionalDetailCommand));
//
//        // different object -> return false;
//        assertFalse(addAdditionalDetailCommand.equals("hello"));
//
//        // same fields -> return true;
//        assertTrue(addAdditionalDetailCommand.equals(new AddAdditionalDetailCommand(INDEX_FIRST_PERSON, testDetail)));
//
//        // different index -> return false;
//        assertFalse(addAdditionalDetailCommand.equals(new AddAdditionalDetailCommand(INDEX_SECOND_PERSON, testDetail)));
//
//        // different question -> return false;
//        AdditionalDetail altDetail = new AdditionalDetail("he watches birds");
//        assertFalse(addAdditionalDetailCommand.equals(new AddAdditionalDetailCommand(INDEX_FIRST_PERSON, altDetail)));
//    }
}
