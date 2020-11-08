package seedu.address.logic.commands.room;

import static seedu.address.testutil.command.GeneralCommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.command.GeneralCommandTestUtil.assertCommandSuccess;

import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.PatientRecords;
import seedu.address.model.RoomList;
import seedu.address.model.UserPrefs;
import seedu.address.model.room.Room;
import seedu.address.testutil.TypicalRooms;

class FindEmptyRoomCommandTest {

    @Test
    void execute_numberOfRooms_notDefined() {
        Model model = new ModelManager(new PatientRecords(), new RoomList(), new UserPrefs());

        //if there are no rooms -> FindEmptyRoomCommand.NUMBER_OF_ROOMS_UNDEFINED exception is thrown
        assertCommandFailure(new FindEmptyRoomCommand(), model, FindEmptyRoomCommand.NUMBER_OF_ROOMS_UNDEFINED);
    }

    @Test
    void execute_numberOfRooms_success() {
        Model model = new ModelManager(new PatientRecords(), new RoomList(), new UserPrefs());
        Model expectedModel =
                new ModelManager(model.getPatientRecords(), new RoomList(), new UserPrefs());

        //if there is no occupied rooms -> room number one should be given
        Predicate<Room> predicate = getFilterByRoomNumberPredicate(new Room(1));
        expectedModel.updateFilteredRoomList(predicate);
        model.initRooms(100);
        expectedModel.initRooms(100);
        String expectedMessage = String.format(FindEmptyRoomCommand.MESSAGE_SUCCESS, 1);
        assertCommandSuccess(new FindEmptyRoomCommand(), model, expectedMessage, expectedModel);
    }

    @Test
    void execute_numberOfRoomsContainingOccupiedRooms_success() {
        Model model = new ModelManager(new PatientRecords(), new RoomList(), new UserPrefs());
        Model expectedModel =
                new ModelManager(model.getPatientRecords(), new RoomList(), new UserPrefs());

        //if there is occupied room -> room number two should be given
        Predicate<Room> predicate = getFilterByRoomNumberPredicate(new Room(2));
        expectedModel.updateFilteredRoomList(predicate);
        model.initRooms(100);
        expectedModel.initRooms(100);
        model.setRoom(TypicalRooms.ROOM_PATIENT_NO_TASK);
        expectedModel.setRoom(TypicalRooms.ROOM_PATIENT_NO_TASK);
        String expectedMessage = String.format(FindEmptyRoomCommand.MESSAGE_SUCCESS, 2);
        assertCommandSuccess(new FindEmptyRoomCommand(), model, expectedMessage, expectedModel);
    }

    private Predicate<Room> getFilterByRoomNumberPredicate(Room room) {
        Predicate<Room> filterByRoomNumber = room1 -> room1.getRoomNumber() == room.getRoomNumber();
        return filterByRoomNumber;
    }
}
