package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedAssignment.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAssignments.CS2103T_TUT;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Deadline;
import seedu.address.model.person.ModuleCode;
import seedu.address.model.person.Name;

public class JsonAdaptedAssignmentTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_DEADLINE = "00-00-0000 2400";
    private static final String INVALID_MODULE_CODE = " ";

    private static final String VALID_NAME = CS2103T_TUT.getName().toString();
    private static final String VALID_DEADLINE = CS2103T_TUT.getDeadline().toString();
    private static final String VALID_MODULE_CODE = CS2103T_TUT.getModuleCode().toString();

    @Test
    public void toModelType_validAssignmentDetails_returnsAssignment() throws Exception {
        JsonAdaptedAssignment assignment = new JsonAdaptedAssignment(CS2103T_TUT);
        assertEquals(CS2103T_TUT, assignment.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedAssignment assignment =
                new JsonAdaptedAssignment(INVALID_NAME, VALID_DEADLINE, VALID_MODULE_CODE);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, assignment::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedAssignment assignment = new JsonAdaptedAssignment(null, VALID_DEADLINE,
                VALID_MODULE_CODE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, assignment::toModelType);
    }

    @Test
    public void toModelType_invalidDeadline_throwsIllegalValueException() {
        JsonAdaptedAssignment assignment =
                new JsonAdaptedAssignment(VALID_NAME, INVALID_DEADLINE, VALID_MODULE_CODE);
        String expectedMessage = Deadline.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, assignment::toModelType);
    }

    @Test
    public void toModelType_nullDeadline_throwsIllegalValueException() {
        JsonAdaptedAssignment assignment = new JsonAdaptedAssignment(VALID_NAME, null,
                VALID_MODULE_CODE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Deadline.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, assignment::toModelType);
    }

    @Test
    public void toModelType_invalidModuleCode_throwsIllegalValueException() {
        JsonAdaptedAssignment assignment =
                new JsonAdaptedAssignment(VALID_NAME, VALID_DEADLINE, INVALID_MODULE_CODE);
        String expectedMessage = ModuleCode.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, assignment::toModelType);
    }

    @Test
    public void toModelType_nullModuleCode_throwsIllegalValueException() {
        JsonAdaptedAssignment assignment =
                new JsonAdaptedAssignment(VALID_NAME, VALID_DEADLINE, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, ModuleCode.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, assignment::toModelType);
    }
}
