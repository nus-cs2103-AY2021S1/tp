package seedu.address.testutil;

import static seedu.address.testutil.TypicalPatients.ALICE;
import static seedu.address.testutil.TypicalPatients.BENSON;
import static seedu.address.testutil.TypicalTasks.REMIND_PATIENT;
import static seedu.address.testutil.TypicalTasks.RESTOCK_SUPPLY;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.RoomList;
import seedu.address.model.room.Room;

/**
 * A utility class containing a list of {@code Room} objects to be used in tests.
 * The patients present in getTypicalRooms() of TypicalRooms class must be present
 * in getTypicalPatientRecords() of TypicalPatients class.
 */
public class TypicalRooms {

    public static final Integer ROOM_NUMBER_7 = 7;
    public static final Integer ROOM_NUMBER_8 = 8;
    public static final Integer ROOM_NUMBER_11 = 11;
    public static final Integer ROOM_NUMBER_16 = 16;
    public static final Integer ROOM_NUMBER_17 = 17;
    public static final Room ROOM_NO_PATIENT_NO_TASK_ROOM_CORRECT_ORDER_1 = new RoomBuilder()
            .withIsOccupied(false).withPatient(null).withRoomNumber(ROOM_NUMBER_16).build();
    public static final Room ROOM_NO_PATIENT_NO_TASK_ROOM_CORRECT_ORDER_2 = new RoomBuilder()
            .withIsOccupied(false).withPatient(null).withRoomNumber(ROOM_NUMBER_17).build();
    public static final Room ROOM_NO_PATIENT_NO_TASK = new RoomBuilder()
            .withIsOccupied(false).withPatient(null).build();
    public static final Room ROOM_NO_PATIENT_TASK_RESTOCK_SUPPLY = new RoomBuilder()
            .withIsOccupied(false).withPatient(null).withRoomNumber(ROOM_NUMBER_11)
            .withTasks(RESTOCK_SUPPLY).build();
    public static final Room ROOM_PATIENT_ALICE_NO_TASK = new RoomBuilder()
            .withIsOccupied(true).withPatient(ALICE).build();
    public static final Room ROOM_PATIENT_ALICE_TASK_REMIND_PATIENT = new RoomBuilder()
            .withIsOccupied(true).withPatient(ALICE).withTasks(REMIND_PATIENT).build();
    public static final Room ROOM7_PATIENT_ALICE_NO_TASK = new RoomBuilder()
            .withIsOccupied(true).withPatient(ALICE).withRoomNumber(ROOM_NUMBER_7).build();
    public static final Room ROOM7_PATIENT_BENSON_NO_TASK = new RoomBuilder()
        .withIsOccupied(true).withPatient(BENSON).withRoomNumber(ROOM_NUMBER_7).build();
    public static final Room ROOM8_PATIENT_BENSON_NO_TASK = new RoomBuilder()
            .withIsOccupied(true).withPatient(BENSON).withRoomNumber(ROOM_NUMBER_8).build();

    private TypicalRooms() {
    } // prevents instantiation

    public static RoomList getTypicalRoomList() {
        RoomList roomList = new RoomList();
        roomList.initRooms(15);
        for (Room room : getTypicalRooms()) {
            roomList.setRoom(room);
        }
        return roomList;
    }

    public static List<Room> getTypicalRooms() {
        //do not change, used in integration tests
        return new ArrayList<>(Arrays.asList(ROOM7_PATIENT_ALICE_NO_TASK, ROOM8_PATIENT_BENSON_NO_TASK,
                ROOM_NO_PATIENT_NO_TASK, ROOM_NO_PATIENT_TASK_RESTOCK_SUPPLY));
    }

}
