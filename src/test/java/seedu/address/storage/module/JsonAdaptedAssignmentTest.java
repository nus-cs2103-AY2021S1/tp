package seedu.address.storage.module;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedAssignment.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.module.grade.Assignment;
import seedu.address.model.module.grade.AssignmentName;
import seedu.address.model.module.grade.AssignmentPercentage;
import seedu.address.model.module.grade.AssignmentResult;
import seedu.address.storage.JsonAdaptedAssignment;

public class JsonAdaptedAssignmentTest {
    private static final String VALID_ASSIGNMENT_NAME = "ASSIGNMENT1";
    private static final double VALID_ASSIGNMENT_PERCENTAGE = 50;
    private static final double VALID_ASSIGNMENT_RESULT = 0.5;

    private static final String INVALID_ASSIGNMENT_NAME = "@SSIGNMENT1.";
    private static final double INVALID_ASSIGNMENT_PERCENTAGE = 101;
    private static final double INVALID_ASSIGNMENT_RESULT = -0.1;

    @Test
    public void toModelType_validAssignmentDetails_returnsAssignment() throws Exception {

        Assignment validAssignment = new Assignment(new AssignmentName(VALID_ASSIGNMENT_NAME),
                new AssignmentPercentage(VALID_ASSIGNMENT_PERCENTAGE), new AssignmentResult(VALID_ASSIGNMENT_RESULT));
        JsonAdaptedAssignment assignment = new JsonAdaptedAssignment(validAssignment);
        assertEquals(validAssignment, assignment.toModelType());
    }

    @Test
    public void toModelType_invalidAssignmentName_throwsIllegalValueException() {
        JsonAdaptedAssignment assignment = new JsonAdaptedAssignment(INVALID_ASSIGNMENT_NAME,
                VALID_ASSIGNMENT_PERCENTAGE, VALID_ASSIGNMENT_RESULT);
        String expectedMessage = Assignment.MESSAGE_ASSIGNMENT_NAME_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, assignment::toModelType);
    }

    @Test
    public void toModelType_nullAssignmentName_throwsIllegalValueException() {
        JsonAdaptedAssignment assignment = new JsonAdaptedAssignment(null,
                VALID_ASSIGNMENT_PERCENTAGE, VALID_ASSIGNMENT_RESULT);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "assignment name");
        assertThrows(IllegalValueException.class, expectedMessage, assignment::toModelType);
    }

    @Test
    public void toModelType_invalidAssignmentPercentage_throwsIllegalValueException() {
        JsonAdaptedAssignment assignment = new JsonAdaptedAssignment(VALID_ASSIGNMENT_NAME,
                INVALID_ASSIGNMENT_PERCENTAGE, VALID_ASSIGNMENT_RESULT);
        String expectedMessage = Assignment.MESSAGE_ASSIGNMENT_PERCENTAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, assignment::toModelType);
    }

    @Test
    public void toModelType_nullAssignmentPercentage_throwsIllegalValueException() {
        /*JsonAdaptedAssignment assignment = new JsonAdaptedAssignment(VALID_ASSIGNMENT_NAME,
                null, INVALID_ASSIGNMENT_RESULT);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT,
                AssignmentPercentage.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, assignment::toModelType);*/
    }

    @Test
    public void toModelType_invalidAssignmentResult_throwsIllegalValueException() {
        JsonAdaptedAssignment assignment = new JsonAdaptedAssignment(VALID_ASSIGNMENT_NAME,
                VALID_ASSIGNMENT_PERCENTAGE, INVALID_ASSIGNMENT_RESULT);
        String expectedMessage = Assignment.MESSAGE_ASSIGNMENT_RESULT_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, assignment::toModelType);
    }

    @Test
    public void toModelType_nullAssignmentResult_throwsIllegalValueException() {
        /*JsonAdaptedAssignment assignment = new JsonAdaptedAssignment(VALID_ASSIGNMENT_NAME,
                VALID_ASSIGNMENT_PERCENTAGE, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, AssignmentResult.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, assignment::toModelType);*/
    }
}
