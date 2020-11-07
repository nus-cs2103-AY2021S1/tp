package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedAssignment.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAssignments.CS2103T_TUT;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.task.ModuleCode;
import seedu.address.model.task.Name;
import seedu.address.model.task.Time;

public class JsonAdaptedAssignmentTest {
    private static final String INVALID_NAME = "Tutori@l";
    private static final String INVALID_DEADLINE = "00-00-0000 2400";
    private static final String INVALID_MODULE_CODE = " ";
    private static final String INVALID_SUGGESTED_START_TIME = "00-12-2000 2525";
    private static final String INVALID_SUGGESTED_END_TIME = "12-00-3000 2359";

    private static final String VALID_NAME = CS2103T_TUT.getName().toString();
    private static final String VALID_DEADLINE = CS2103T_TUT.getDeadline().toString();
    private static final String VALID_MODULE_CODE = CS2103T_TUT.getModuleCode().toString();
    private static final boolean VALID_IS_REMINDED = true;
    private static final boolean VALID_IS_SCHEDULED = true;
    private static final boolean VALID_NOT_SCHEDULED = false;
    private static final String VALID_SUGGESTED_START_TIME = "27-03-2000 0505";
    private static final String VALID_SUGGESTED_END_TIME = "12-12-2020 2359";
    private static final boolean VALID_IS_MARKED_DONE = true;

    private static final String NO_PRIORITY = "NONE";

    @Test
    public void toModelType_validAssignmentDetails_returnsAssignment() throws Exception {
        JsonAdaptedAssignment assignment = new JsonAdaptedAssignment(CS2103T_TUT);
        assertEquals(CS2103T_TUT, assignment.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedAssignment assignment =
                new JsonAdaptedAssignment(INVALID_NAME, VALID_DEADLINE, VALID_MODULE_CODE, VALID_IS_REMINDED,
                        VALID_IS_SCHEDULED, VALID_SUGGESTED_START_TIME, VALID_SUGGESTED_END_TIME, NO_PRIORITY,
                        VALID_IS_MARKED_DONE);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, assignment::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedAssignment assignment = new JsonAdaptedAssignment(null, VALID_DEADLINE, VALID_MODULE_CODE,
                VALID_IS_REMINDED, VALID_IS_SCHEDULED, VALID_SUGGESTED_START_TIME, VALID_SUGGESTED_END_TIME,
                NO_PRIORITY, VALID_IS_MARKED_DONE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, assignment::toModelType);
    }

    @Test
    public void toModelType_invalidDeadline_throwsIllegalValueException() {
        JsonAdaptedAssignment assignment = new JsonAdaptedAssignment(VALID_NAME, INVALID_DEADLINE, VALID_MODULE_CODE,
                VALID_IS_REMINDED, VALID_IS_SCHEDULED, VALID_SUGGESTED_START_TIME, VALID_SUGGESTED_END_TIME,
                NO_PRIORITY, VALID_IS_MARKED_DONE);
        String expectedMessage = Time.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, assignment::toModelType);
    }

    @Test
    public void toModelType_nullDeadline_throwsIllegalValueException() {
        JsonAdaptedAssignment assignment = new JsonAdaptedAssignment(VALID_NAME, null, VALID_MODULE_CODE,
                VALID_IS_REMINDED, VALID_IS_SCHEDULED, VALID_SUGGESTED_START_TIME, VALID_SUGGESTED_END_TIME,
                NO_PRIORITY, VALID_IS_MARKED_DONE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Time.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, assignment::toModelType);
    }

    @Test
    public void toModelType_invalidModuleCode_throwsIllegalValueException() {
        JsonAdaptedAssignment assignment = new JsonAdaptedAssignment(VALID_NAME, VALID_DEADLINE, INVALID_MODULE_CODE,
                VALID_IS_REMINDED, VALID_IS_SCHEDULED, VALID_SUGGESTED_START_TIME, VALID_SUGGESTED_END_TIME,
                NO_PRIORITY, VALID_IS_MARKED_DONE);
        String expectedMessage = ModuleCode.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, assignment::toModelType);
    }

    @Test
    public void toModelType_nullModuleCode_throwsIllegalValueException() {
        JsonAdaptedAssignment assignment = new JsonAdaptedAssignment(VALID_NAME, VALID_DEADLINE, null,
                VALID_IS_REMINDED, VALID_IS_SCHEDULED, VALID_SUGGESTED_START_TIME, VALID_SUGGESTED_END_TIME,
                NO_PRIORITY, VALID_IS_MARKED_DONE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, ModuleCode.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, assignment::toModelType);
    }
}
