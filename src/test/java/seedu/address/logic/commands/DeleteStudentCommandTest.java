package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showStudentAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalStudents.getTypicalStudentList;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.Trackr;
import seedu.address.model.UserPrefs;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleId;
import seedu.address.model.student.Student;
import seedu.address.model.tutorialgroup.DayOfWeek;
import seedu.address.model.tutorialgroup.TimeOfDay;
import seedu.address.model.tutorialgroup.TutorialGroup;
import seedu.address.model.tutorialgroup.TutorialGroupId;
import seedu.address.testutil.StudentBuilder;

public class DeleteStudentCommandTest {

    public final Module cs2100 = new Module(new ModuleId("CS2100"));
    public final Module cs2103T = new Module(new ModuleId("CS2103T"));
    public final Module cs2040 = new Module(new ModuleId("CS2040"));
    public final Module cs2030 = new Module(new ModuleId("CS2030"));

    public final TutorialGroup t05 = new TutorialGroup(
            new TutorialGroupId("T05"),
            new DayOfWeek("MON"),
            new TimeOfDay("13:00"),
            new TimeOfDay("14:00"),
            getTypicalStudentList()
    );

    public final TutorialGroup b06 = new TutorialGroup(
            new TutorialGroupId("B06"),
            new DayOfWeek("THU"),
            new TimeOfDay("12:00"),
            new TimeOfDay("14:00")

    );

    public final TutorialGroup s12 = new TutorialGroup(
            new TutorialGroupId("S12"),
            new DayOfWeek("FRI"),
            new TimeOfDay("08:00"),
            new TimeOfDay("10:30")
    );

    public final TutorialGroup v04 = new TutorialGroup(
            new TutorialGroupId("V04"),
            new DayOfWeek("TUE"),
            new TimeOfDay("09:00"),
            new TimeOfDay("10:00")
    );

    public final Student alex = new StudentBuilder()
            .withName("Alex Tan")
            .withEmail("alextan@u.nus.edu")
            .withPhone("91234567")
            .withTags("CS2103T")
            .withStudentId("A1234567X")
            .withAttendance("1", "2", "3", "6")
            .withParticipation("96")
            .build();

    public final Student beng = new StudentBuilder()
            .withName("Ah Beng")
            .withEmail("abeng@u.nus.edu")
            .withPhone("81234567")
            .withTags("CS2103T")
            .withStudentId("A7654321B")
            .withAttendance("1", "2", "5", "13")
            .build();

    // don't add attendance to this student
    public final Student charlie = new StudentBuilder()
            .withName("CHARLIE CHEN")
            .withEmail("charlie@hi.com")
            .withPhone("82223333")
            .withTags("CS2103T")
            .withStudentId("A1928835B")
            .build();

    public final Student david = new StudentBuilder()
            .withName("David Ong")
            .withEmail("dong@u.nus.edu")
            .withPhone("81320987")
            .withTags("CS2103T", "UTCP")
            .withStudentId("A1837538N")
            .withAttendance("1", "4", "3", "12")
            .withParticipation("24")
            .build();

    public final Student elizabeth = new StudentBuilder()
            .withName("Elizabeth Teo")
            .withEmail("eteo@u.nus.edu")
            .withPhone("89993333")
            .withTags("CS2103T", "DDP")
            .withStudentId("A1938563M")
            .withParticipation("100")
            .build();

    public final Student fiona = new StudentBuilder()
            .withName("Fiona Chan")
            .withEmail("fionachan@u.nus.edu")
            .withPhone("82938378")
            .withTags("CS1231S")
            .withStudentId("A2038468T")
            .withAttendance("1", "2", "4", "10")
            .build();

    public final Module cs2100Two = new Module(new ModuleId("CS2100"));
    public final Module cs2103TTwo = new Module(new ModuleId("CS2103T"));
    public final Module cs2040Two = new Module(new ModuleId("CS2040"));
    public final Module cs2030Two = new Module(new ModuleId("CS2030"));

    public final TutorialGroup t05Two = new TutorialGroup(
            new TutorialGroupId("T05"),
            new DayOfWeek("MON"),
            new TimeOfDay("13:00"),
            new TimeOfDay("14:00"),
            getTypicalStudentList()
    );

    public final TutorialGroup b06Two = new TutorialGroup(
            new TutorialGroupId("B06"),
            new DayOfWeek("THU"),
            new TimeOfDay("12:00"),
            new TimeOfDay("14:00")

    );

    public final TutorialGroup s12Two = new TutorialGroup(
            new TutorialGroupId("S12"),
            new DayOfWeek("FRI"),
            new TimeOfDay("08:00"),
            new TimeOfDay("10:30")
    );

    public final TutorialGroup v04Two = new TutorialGroup(
            new TutorialGroupId("V04"),
            new DayOfWeek("TUE"),
            new TimeOfDay("09:00"),
            new TimeOfDay("10:00")
    );

    public final Student alexTwo = new StudentBuilder()
            .withName("Alex Tan")
            .withEmail("alextan@u.nus.edu")
            .withPhone("91234567")
            .withTags("CS2103T")
            .withStudentId("A1234567X")
            .withAttendance("1", "2", "3", "6")
            .withParticipation("96")
            .build();

    public final Student bengTwo = new StudentBuilder()
            .withName("Ah Beng")
            .withEmail("abeng@u.nus.edu")
            .withPhone("81234567")
            .withTags("CS2103T")
            .withStudentId("A7654321B")
            .withAttendance("1", "2", "5", "13")
            .build();

    // don't add attendance to this student
    public final Student charlieTwo = new StudentBuilder()
            .withName("CHARLIE CHEN")
            .withEmail("charlie@hi.com")
            .withPhone("82223333")
            .withTags("CS2103T")
            .withStudentId("A1928835B")
            .build();

    public final Student davidTwo = new StudentBuilder()
            .withName("David Ong")
            .withEmail("dong@u.nus.edu")
            .withPhone("81320987")
            .withTags("CS2103T", "UTCP")
            .withStudentId("A1837538N")
            .withAttendance("1", "4", "3", "12")
            .withParticipation("24")
            .build();

    public final Student elizabethTwo = new StudentBuilder()
            .withName("Elizabeth Teo")
            .withEmail("eteo@u.nus.edu")
            .withPhone("89993333")
            .withTags("CS2103T", "DDP")
            .withStudentId("A1938563M")
            .withParticipation("100")
            .build();

    public final Student fionaTwo = new StudentBuilder()
            .withName("Fiona Chan")
            .withEmail("fionachan@u.nus.edu")
            .withPhone("82938378")
            .withTags("CS1231S")
            .withStudentId("A2038468T")
            .withAttendance("1", "2", "4", "10")
            .build();

    private Model model = new ModelManager(getFirstTypicalTrackr(), new UserPrefs());
    private Module moduleInView = cs2103T;
    private TutorialGroup tgInView = t05;

    /**
     * Returns an {@code Trackr} with all the typical modules, tutorial groups and students.
     */
    public Trackr getFirstTypicalTrackr() {
        Trackr trackr = new Trackr();
        // populate modules with the same tutorial groups and students
        trackr.addModule(cs2103T);
        for (TutorialGroup tutorialGroup : new ArrayList<>(Arrays.asList(t05, b06, s12, v04))) {
            if (!cs2103T.getUniqueTutorialGroupList().contains(tutorialGroup)) {
                trackr.addTutorialGroup(tutorialGroup, cs2103T);
            }
        }
        for (Student student : Arrays.asList(alex, beng, charlie, david, elizabeth, fiona)) {
            if (!t05.getUniqueStudentList().contains(student)) {
                trackr.addStudent(cs2103T, t05, student);
            }
        }
        return trackr;
    }

    /**
     * Returns an {@code Trackr} with all the typical modules, tutorial groups and students.
     */
    public Trackr getSecondTypicalTrackr() {
        Trackr trackr = new Trackr();
        // populate modules with the same tutorial groups and students
        trackr.addModule(cs2103TTwo);
        for (TutorialGroup tutorialGroup : new ArrayList<>(Arrays.asList(t05Two, b06Two, s12Two, v04Two))) {
            if (!cs2103TTwo.getUniqueTutorialGroupList().contains(tutorialGroup)) {
                trackr.addTutorialGroup(tutorialGroup, cs2103TTwo);
            }
        }
        for (Student student : Arrays.asList(alexTwo, bengTwo, charlieTwo, davidTwo, elizabethTwo, fionaTwo)) {
            if (!t05Two.getUniqueStudentList().contains(student)) {
                trackr.addStudent(cs2103TTwo, t05Two, student);
            }
        }
        return trackr;
    }

    @Test
    public void execute_validIndexUnfilteredList_success() {
        model.setViewToTutorialGroup(cs2103T);
        model.setViewToStudent(t05);
        Student studentToDelete = model.getFilteredStudentList().get(INDEX_FIRST_PERSON.getZeroBased());
        DeleteStudentCommand deleteStudentCommand = new DeleteStudentCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(DeleteStudentCommand.MESSAGE_DELETE_STUDENT_SUCCESS, studentToDelete);
        ModelManager expectedModel = new ModelManager(getSecondTypicalTrackr(), new UserPrefs());
        expectedModel.setViewToTutorialGroup(cs2103T);
        expectedModel.setViewToStudent(t05);
        expectedModel.deleteStudent(studentToDelete);
        assertCommandSuccess(deleteStudentCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        model.setViewToTutorialGroup(moduleInView);
        model.setViewToStudent(tgInView);
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredStudentList().size() + 1);
        DeleteStudentCommand deleteStudentCommand = new DeleteStudentCommand(outOfBoundIndex);

        assertCommandFailure(deleteStudentCommand, model, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showStudentAtIndex(model, INDEX_FIRST_PERSON, moduleInView, tgInView);

        Student studentToDelete = model.getFilteredStudentList().get(INDEX_FIRST_PERSON.getZeroBased());
        DeleteStudentCommand deleteStudentCommand = new DeleteStudentCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(DeleteStudentCommand.MESSAGE_DELETE_STUDENT_SUCCESS,
                studentToDelete);
        ModelManager expectedModel = new ModelManager(model.getModuleList(), new UserPrefs());
        expectedModel.setViewToTutorialGroup(moduleInView);
        expectedModel.setViewToStudent(tgInView);
        showNoStudent(expectedModel);

        assertCommandSuccess(deleteStudentCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showStudentAtIndex(model, INDEX_FIRST_PERSON, moduleInView, tgInView);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures than outOfBoundIndex is still in bounds of student list
        assertTrue(outOfBoundIndex.getZeroBased()
                < model.getModuleList().getStudentList(moduleInView, tgInView).size());
        DeleteStudentCommand deleteStudentCommand = new DeleteStudentCommand(outOfBoundIndex);
        assertCommandFailure(deleteStudentCommand, model, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteStudentCommand deleteFirstCommand = new DeleteStudentCommand(INDEX_FIRST_PERSON);
        DeleteStudentCommand deleteSecondCommand = new DeleteStudentCommand(INDEX_SECOND_PERSON);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        //same values -> returns true
        DeleteStudentCommand deleteFirstCommandCopy = new DeleteStudentCommand(INDEX_FIRST_PERSON);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no student.
     */
    private void showNoStudent(Model model) {
        model.updateFilteredStudentList(student -> false);
        assertTrue(model.getFilteredStudentList().isEmpty());
    }
}
