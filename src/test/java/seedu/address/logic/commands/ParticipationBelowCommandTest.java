package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalStudents.getTypicalStudentList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.Trackr;
import seedu.address.model.UserPrefs;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleId;
import seedu.address.model.student.ParticipationBelowSpecifiedScorePredicate;
import seedu.address.model.student.Student;
import seedu.address.model.tutorialgroup.DayOfWeek;
import seedu.address.model.tutorialgroup.TimeOfDay;
import seedu.address.model.tutorialgroup.TutorialGroup;
import seedu.address.model.tutorialgroup.TutorialGroupId;
import seedu.address.testutil.StudentBuilder;

public class ParticipationBelowCommandTest {
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

    private Model model = new ModelManager(getTypicalTrackr(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalTrackr(), new UserPrefs());

    /**
     * Returns an {@code Trackr} with all the typical modules, tutorial groups and students.
     */
    public Trackr getTypicalTrackr() {
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

    @Test
    public void equals() {
        int firstUpperBound = 1;
        int secondUpperBound = 101;
        ParticipationBelowSpecifiedScorePredicate firstPredicate =
                new ParticipationBelowSpecifiedScorePredicate(firstUpperBound);
        ParticipationBelowSpecifiedScorePredicate secondPredicate =
                new ParticipationBelowSpecifiedScorePredicate(secondUpperBound);

        ParticipationBelowCommand firstParticipationBelow =
                new ParticipationBelowCommand(firstPredicate, firstUpperBound);
        ParticipationBelowCommand secondParticipationBelow =
                new ParticipationBelowCommand(secondPredicate, secondUpperBound);

        // same object -> returns true
        assertTrue(firstParticipationBelow.equals(firstParticipationBelow));

        // same values -> returns true
        ParticipationBelowCommand firstParticipationBelowCommandCopy =
                new ParticipationBelowCommand(firstPredicate, firstUpperBound);
        assertTrue(firstParticipationBelow.equals(firstParticipationBelowCommandCopy));

        // different types -> returns false
        assertFalse(firstParticipationBelow.equals(1));

        // null -> returns false
        assertFalse(firstParticipationBelow.equals(null));

        // different command -> returns false
        assertFalse(firstParticipationBelow.equals(secondParticipationBelow));
    }

    @Test
    public void execute_zeroUpperBound_noModuleFound() {
        model.setViewToTutorialGroup(cs2103T);
        model.setViewToStudent(t05);
        expectedModel.setViewToTutorialGroup(cs2103T);
        expectedModel.setViewToStudent(t05);
        String expectedMessage = String.format(ParticipationBelowCommand.MESSAGE_PARTICIPATION_BELOW_SUCCESS, 0);
        ParticipationBelowSpecifiedScorePredicate predicate = preparePredicate(0);
        ParticipationBelowCommand command = new ParticipationBelowCommand(predicate, 0);
        expectedModel.updateFilteredStudentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredStudentList());
    }

    @Test
    public void execute_validUpperBound_multipleModulesFound() {
        model.setViewToTutorialGroup(cs2103T);
        model.setViewToStudent(t05);
        expectedModel.setViewToTutorialGroup(cs2103T);
        expectedModel.setViewToStudent(t05);
        String expectedMessage = String.format(ParticipationBelowCommand.MESSAGE_PARTICIPATION_BELOW_SUCCESS, 50);
        ParticipationBelowSpecifiedScorePredicate predicate = preparePredicate(50);
        ParticipationBelowCommand command = new ParticipationBelowCommand(predicate, 50);
        expectedModel.updateFilteredStudentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(beng, charlie, david, fiona), model.getFilteredStudentList());
    }


    @Test
    public void execute_topUpperBound_multipleModulesFound() {
        model.setViewToTutorialGroup(cs2103T);
        model.setViewToStudent(t05);
        expectedModel.setViewToTutorialGroup(cs2103T);
        expectedModel.setViewToStudent(t05);
        String expectedMessage = String.format(ParticipationBelowCommand.MESSAGE_PARTICIPATION_BELOW_SUCCESS,
                101);
        ParticipationBelowSpecifiedScorePredicate predicate = preparePredicate(101);
        ParticipationBelowCommand command = new ParticipationBelowCommand(predicate, 101);
        expectedModel.updateFilteredStudentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(alex, beng, charlie, david, elizabeth, fiona), model.getFilteredStudentList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private ParticipationBelowSpecifiedScorePredicate preparePredicate(int upperBound) {
        return new ParticipationBelowSpecifiedScorePredicate(upperBound);
    }
}
