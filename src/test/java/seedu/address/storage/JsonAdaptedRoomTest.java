package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPatients.BENSON;
import static seedu.address.testutil.TypicalRooms.ROOM_NO_PATIENT_NO_TASK;
import static seedu.address.testutil.TypicalRooms.ROOM_PATIENT_ALICE_NO_TASK;
import static seedu.address.testutil.TypicalRooms.ROOM_PATIENT_ALICE_TASK_REMIND_PATIENT;
import static seedu.address.testutil.TypicalTasks.REMIND_PATIENT;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.room.Room;
import seedu.address.storage.patient.JsonAdaptedPatient;
import seedu.address.storage.rooms.JsonAdaptedRoom;
import seedu.address.testutil.Assert;
import seedu.address.testutil.RoomBuilder;

/**
 * Test cases for JsonAdaptedRoom
 */
class JsonAdaptedRoomTest {
    private static final String VALID_DESCRIPTION = REMIND_PATIENT.getDescription().value;
    private static final String VALID_DUE_DATE = REMIND_PATIENT.getDueAt().getVal();

    private static final String INVALID_DUE_DATE = "two thirty";

    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_TEMP = "39.p";
    private static final String INVALID_PERIOD = "20200011-20209900";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_AGE = "1000";

    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_TEMP = BENSON.getTemperature().toString();
    private static final String VALID_PERIOD = BENSON.getPeriodOfStay().toString();
    private static final String VALID_PHONE = BENSON.getPhone().toString();
    private static final String VALID_AGE = BENSON.getAge().toString();

    private static final String COMMENT = "Vegan";

    private static final int VALID_ROOM_NUM = ROOM_PATIENT_ALICE_TASK_REMIND_PATIENT.getRoomNumber();
    private static final boolean VALID_IS_OCCUPIED = ROOM_PATIENT_ALICE_TASK_REMIND_PATIENT.isOccupied();

    private static final Room ROOM_INVALID_PATIENT_PRESENT_OCCUPIED_FALSE =
            new RoomBuilder(ROOM_PATIENT_ALICE_NO_TASK).withIsOccupied(false).build();
    private static final Room ROOM_INVALID_PATIENT_ABSENT_OCCUPIED_TRUE =
            new RoomBuilder(ROOM_NO_PATIENT_NO_TASK).withIsOccupied(true).build();
    @Test
    public void toModelType_returnsEmptyRoom() throws Exception {
        JsonAdaptedRoom room1 = new JsonAdaptedRoom(ROOM_NO_PATIENT_NO_TASK);
        assertEquals(ROOM_NO_PATIENT_NO_TASK, room1.toModelType());
    }

    @Test
    public void toModelType_returnsOccupiedRoom_success() throws Exception {
        JsonAdaptedRoom room = new JsonAdaptedRoom(ROOM_PATIENT_ALICE_NO_TASK);
        assertEquals(ROOM_PATIENT_ALICE_NO_TASK, room.toModelType());
    }

    @Test
    public void toModelType_invalidDueDate_throwsIllegalValueException() throws Exception {
        JsonAdaptedTask task = new JsonAdaptedTask(VALID_DESCRIPTION, INVALID_DUE_DATE);
        List<JsonAdaptedTask> tasks = new ArrayList<>();
        tasks.add(task);
        JsonSerializableTaskList taskList = new JsonSerializableTaskList(tasks);
        JsonAdaptedPatient patient =
                new JsonAdaptedPatient(VALID_NAME, VALID_TEMP, VALID_PERIOD, VALID_PHONE, VALID_AGE, COMMENT);
        JsonAdaptedRoom room = new JsonAdaptedRoom(VALID_ROOM_NUM, VALID_IS_OCCUPIED, patient, taskList);
        String expectedMessage = JsonAdaptedRoom.DATE_WRONG_FORMAT_IN_TASKS;
        Assert.assertThrows(IllegalValueException.class, expectedMessage, room::toModelType);
    }

    @Test
    public void toModelType_invalidPatient_throwsIllegalValueException() throws Exception {
        JsonAdaptedTask task = new JsonAdaptedTask(VALID_DESCRIPTION, VALID_DUE_DATE);
        List<JsonAdaptedTask> tasks = new ArrayList<>();
        tasks.add(task);
        JsonSerializableTaskList taskList = new JsonSerializableTaskList(tasks);
        JsonAdaptedPatient patient =
                new JsonAdaptedPatient(INVALID_NAME, INVALID_TEMP, INVALID_PERIOD, INVALID_PHONE, INVALID_AGE, COMMENT);
        JsonAdaptedRoom room = new JsonAdaptedRoom(VALID_ROOM_NUM, VALID_IS_OCCUPIED, patient, taskList);
        String expectedMessage = JsonAdaptedRoom.PATIENT_WRONG_FORMAT;
        Assert.assertThrows(IllegalValueException.class, expectedMessage, room::toModelType);
    }
    @Test
    public void toModelType_returnsOccupiedRoom_error() throws Exception {
        JsonAdaptedRoom room = new JsonAdaptedRoom(ROOM_INVALID_PATIENT_PRESENT_OCCUPIED_FALSE);
        String expectedMessage = JsonAdaptedRoom.PATIENT_PRESENT_IS_OCCUPIED_FALSE;
        assertThrows(IllegalValueException.class, expectedMessage, room::toModelType);
    }

    @Test
    public void toModelType_returnsNotOccupiedRoom_error() throws Exception {
        JsonAdaptedRoom room = new JsonAdaptedRoom(ROOM_INVALID_PATIENT_ABSENT_OCCUPIED_TRUE);
        String expectedMessage = JsonAdaptedRoom.PATIENT_ABSENT_IS_OCCUPIED_TRUE;
        assertThrows(IllegalValueException.class, expectedMessage, room::toModelType);
    }
}
