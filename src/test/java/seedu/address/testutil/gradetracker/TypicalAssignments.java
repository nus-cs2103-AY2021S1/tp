package seedu.address.testutil.gradetracker;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ASSIGNMENT_NAME_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ASSIGNMENT_NAME_2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ASSIGNMENT_PERCENTAGE_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ASSIGNMENT_PERCENTAGE_2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ASSIGNMENT_RESULT_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ASSIGNMENT_RESULT_2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.module.grade.Assignment;
import seedu.address.model.module.grade.GradeTracker;

/**
 * A utility class containing a list of {@code Assignment} objects to be used in tests.
 */
public class TypicalAssignments {

    public static final Assignment QUIZ_2 = new AssignmentBuilder().withAssignmentName("Quiz 1")
            .withAssignmentPercentage(30).withAssignmentResult(80).build();
    public static final Assignment LAB_1 = new AssignmentBuilder().withAssignmentName("Lab 2")
            .withAssignmentPercentage(5).withAssignmentResult(90).build();
    public static final Assignment ORAL_PRESENTATION_3 = new AssignmentBuilder()
            .withAssignmentName("Oral Presentation 3").withAssignmentPercentage(15).withAssignmentResult(95).build();
    public static final Assignment CLASS_PARTICIPATION = new AssignmentBuilder()
            .withAssignmentName("Class Participation").withAssignmentPercentage(10).withAssignmentResult(100).build();
    public static final Assignment FINAL_ASSESSMENT = new AssignmentBuilder().withAssignmentName("Final Assessment")
            .withAssignmentPercentage(50).withAssignmentResult(65).build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Assignment QUIZ_1 = new AssignmentBuilder().withAssignmentName(VALID_ASSIGNMENT_NAME_1)
            .withAssignmentPercentage(VALID_ASSIGNMENT_PERCENTAGE_1).withAssignmentResult(VALID_ASSIGNMENT_RESULT_1)
            .build();
    public static final Assignment ORAL_PRESENTATION_2 = new AssignmentBuilder()
            .withAssignmentName(VALID_ASSIGNMENT_NAME_2).withAssignmentPercentage(VALID_ASSIGNMENT_PERCENTAGE_2)
            .withAssignmentResult(VALID_ASSIGNMENT_RESULT_2).build();

    private TypicalAssignments() {} // prevents instantiation

    /**
     * Returns an {@code } with all the typical Assignments.
     */
    public static GradeTracker getTypicalGradeTracker() {
        GradeTracker gradeTracker = new GradeTracker();
        for (Assignment assignment : getTypicalAssignments()) {
            gradeTracker.addAssignment(assignment);
        }
        return gradeTracker;
    }

    public static List<Assignment> getTypicalAssignments() {
        return new ArrayList<>(Arrays.asList(QUIZ_2, LAB_1, ORAL_PRESENTATION_3, CLASS_PARTICIPATION,
                FINAL_ASSESSMENT));
    }
}
