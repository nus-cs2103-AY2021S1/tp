package seedu.resireg.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.resireg.storage.JsonAdaptedAllocation.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.resireg.testutil.Assert.assertThrows;
import static seedu.resireg.testutil.TypicalAllocations.ALLOCATION_ONE;
import static seedu.resireg.testutil.TypicalRooms.ROOM_ONE;
import static seedu.resireg.testutil.TypicalStudents.AMY;

import org.junit.jupiter.api.Test;

import seedu.resireg.commons.exceptions.IllegalValueException;
import seedu.resireg.model.room.Floor;
import seedu.resireg.model.room.RoomNumber;
import seedu.resireg.model.student.StudentId;


class JsonAdaptedAllocationTest {
    private static final String INVALID_FLOOR = "08";
    private static final String INVALID_NUMBER = "0110";
    private static final String INVALID_STUDENT_ID = "e0000000";

    private static final String VALID_FLOOR = ROOM_ONE.getFloor().toString();
    private static final String VALID_NUMBER = ROOM_ONE.getRoomNumber().toString();
    private static final String VALID_STUDENT_ID = AMY.getStudentId().toString();

    @Test
    void toModelType_validAllocationDetails_returnsAllocation() throws Exception {
        JsonAdaptedAllocation allocation = new JsonAdaptedAllocation(ALLOCATION_ONE);
        assertEquals(ALLOCATION_ONE, allocation.toModelType());
    }

    @Test
    public void toModelType_invalidFloor_throwsIllegalValueException() {
        JsonAdaptedAllocation allocation =
                new JsonAdaptedAllocation(INVALID_FLOOR, VALID_NUMBER, VALID_STUDENT_ID);
        String expectedMessage = Floor.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, allocation::toModelType);
    }

    @Test
    public void toModelType_nullFloor_throwsIllegalValueException() {
        JsonAdaptedAllocation allocation =
                new JsonAdaptedAllocation(null, VALID_NUMBER, VALID_STUDENT_ID);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Floor.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, allocation::toModelType);
    }

    @Test
    public void toModelType_invalidNumber_throwsIllegalValueException() {
        JsonAdaptedAllocation allocation =
                new JsonAdaptedAllocation(VALID_FLOOR, INVALID_NUMBER, VALID_STUDENT_ID);
        String expectedMessage = RoomNumber.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, allocation::toModelType);
    }

    @Test
    public void toModelType_nullNumber_throwsIllegalValueException() {
        JsonAdaptedAllocation allocation =
                new JsonAdaptedAllocation(VALID_FLOOR, null, VALID_STUDENT_ID);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, RoomNumber.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, allocation::toModelType);
    }

    @Test
    public void toModelType_invalidStudentId_throwsIllegalValueException() {
        JsonAdaptedAllocation allocation =
                new JsonAdaptedAllocation(VALID_FLOOR, VALID_NUMBER, INVALID_STUDENT_ID);
        String expectedMessage = StudentId.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, allocation::toModelType);
    }

    @Test
    public void toModelType_nullStudentId_throwsIllegalValueException() {
        JsonAdaptedAllocation allocation =
                new JsonAdaptedAllocation(VALID_FLOOR, VALID_NUMBER, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, StudentId.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, allocation::toModelType);
    }
}
