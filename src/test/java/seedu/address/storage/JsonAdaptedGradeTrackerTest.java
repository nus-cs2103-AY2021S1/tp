package seedu.address.storage;

import org.junit.jupiter.api.Test;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.module.ModuleName;
import seedu.address.model.module.grade.Assignment;
import seedu.address.model.module.grade.Grade;
import seedu.address.model.module.grade.GradeTracker;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

public class JsonAdaptedGradeTrackerTest {

    public static final JsonAdaptedAssignment INVALID_ASSIGNMENT = new JsonAdaptedAssignment(
            new Assignment("Assignment1", 5.0, -0.1));
    public static final List<JsonAdaptedAssignment> INVALID_ASSIGNMENT_LIST = new ArrayList<>();
    private static final double INVALID_GRADE = -0.1;
    private static final String INVALID_GRADE_POINT = "-0.1";
    public static final JsonAdaptedAssignment VALID_ASSIGNMENT = new JsonAdaptedAssignment(
            new Assignment("Assignment1", 5.0, 0.5));
    public static final List<JsonAdaptedAssignment> VALID_ASSIGNMENT_LIST = new ArrayList<>();
    private static final double VALID_GRADE = 0.5;
    private static final String VALID_GRADE_POINT = "5.0";
    static {
        INVALID_ASSIGNMENT_LIST.add(INVALID_ASSIGNMENT);
        VALID_ASSIGNMENT_LIST.add(VALID_ASSIGNMENT);
    }
    @Test
    public void toModelType_validGradeTrackerDetails_returnsGradeTracker() throws Exception {
        GradeTracker validGradeTracker = new GradeTracker(5.0);
        validGradeTracker.setGrade(new Grade(0.5));
        validGradeTracker.addAssignment(
                new Assignment("Assignment1", 5.0, 0.5));
        JsonAdaptedGradeTracker gradeTracker = new JsonAdaptedGradeTracker(validGradeTracker);
        assertEquals(validGradeTracker, gradeTracker.toModelType());
    }

    @Test
    public void toModelType_invalidAssignment_throwsIllegalValueException() {
        JsonAdaptedGradeTracker gradeTracker =
                new JsonAdaptedGradeTracker(INVALID_ASSIGNMENT_LIST, VALID_GRADE, VALID_GRADE_POINT);
        String expectedMessage = Assignment.MESSAGE_ASSIGNMENT_RESULT_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, gradeTracker::toModelType);
    }

    /*@Test
    public void toModelType_nullAssignments_throwsIllegalValueException() {
        JsonAdaptedGradeTracker gradeTracker =
                new JsonAdaptedGradeTracker(null, VALID_GRADE, VALID_GRADE_POINT);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Assignment.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, gradeTracker::toModelType);
    }*/

    @Test
    public void toModelType_invalidGrade_throwsIllegalValueException() {
        JsonAdaptedGradeTracker gradeTracker =
                new JsonAdaptedGradeTracker(VALID_ASSIGNMENT_LIST, INVALID_GRADE, VALID_GRADE_POINT);
        String expectedMessage = GradeTracker.MESSAGE_INVALID_GRADE;
        assertThrows(IllegalValueException.class, expectedMessage, gradeTracker::toModelType);
    }

    @Test
    public void toModelType_invalidGradePoint_throwsIllegalValueException() {
        JsonAdaptedGradeTracker gradeTracker =
                new JsonAdaptedGradeTracker(VALID_ASSIGNMENT_LIST, VALID_GRADE, INVALID_GRADE_POINT);
        String expectedMessage = GradeTracker.MESSAGE_INVALID_GRADEPOINT;
        assertThrows(IllegalValueException.class, expectedMessage, gradeTracker::toModelType);
    }
}
