package seedu.resireg.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.resireg.logic.commands.CommandTestUtil.VALID_FLOOR_A;
import static seedu.resireg.logic.commands.CommandTestUtil.VALID_FLOOR_B;
import static seedu.resireg.logic.commands.CommandTestUtil.VALID_FLOOR_C;
import static seedu.resireg.logic.commands.CommandTestUtil.VALID_ROOM_NUMBER_A;
import static seedu.resireg.logic.commands.CommandTestUtil.VALID_ROOM_NUMBER_B;
import static seedu.resireg.logic.commands.CommandTestUtil.VALID_ROOM_NUMBER_C;
import static seedu.resireg.logic.commands.CommandTestUtil.VALID_ROOM_TYPE_A;
import static seedu.resireg.logic.commands.CommandTestUtil.VALID_ROOM_TYPE_B;
import static seedu.resireg.logic.commands.CommandTestUtil.VALID_ROOM_TYPE_C;
import static seedu.resireg.logic.commands.CommandTestUtil.assertToggleCommandSuccess;
import static seedu.resireg.testutil.TypicalStudents.ALICE;
import static seedu.resireg.testutil.TypicalStudents.BENSON;
import static seedu.resireg.testutil.TypicalStudents.CARL;

import java.util.Arrays;
import java.util.Collection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.resireg.logic.CommandHistory;
import seedu.resireg.logic.commands.ListRoomsCommand.RoomFilter;
import seedu.resireg.model.Model;
import seedu.resireg.model.ModelManager;
import seedu.resireg.model.ResiReg;
import seedu.resireg.model.UserPrefs;
import seedu.resireg.model.allocation.Allocation;
import seedu.resireg.model.room.Floor;
import seedu.resireg.model.room.Room;
import seedu.resireg.model.room.RoomNumber;
import seedu.resireg.model.room.roomtype.RoomType;
import seedu.resireg.testutil.RoomBuilder;
import seedu.resireg.testutil.RoomFilterBuilder;

public class ListRoomsCommandTest {

    private CommandHistory history = new CommandHistory();

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        ResiReg ab = new ResiReg();
        Room room1 = new RoomBuilder()
                .withFloor(VALID_FLOOR_A).withRoomNumber(VALID_ROOM_NUMBER_A).withRoomType(VALID_ROOM_TYPE_A).build();
        Room room2 = new RoomBuilder()
                .withFloor(VALID_FLOOR_A).withRoomNumber(VALID_ROOM_NUMBER_B).withRoomType(VALID_ROOM_TYPE_B).build();
        Room room3 = new RoomBuilder()
                .withFloor(VALID_FLOOR_B).withRoomNumber(VALID_ROOM_NUMBER_A).withRoomType(VALID_ROOM_TYPE_C).build();
        Room room4 = new RoomBuilder()
                .withFloor(VALID_FLOOR_B).withRoomNumber(VALID_ROOM_NUMBER_C).withRoomType(VALID_ROOM_TYPE_A).build();
        Room room5 = new RoomBuilder()
                .withFloor(VALID_FLOOR_C).withRoomNumber(VALID_ROOM_NUMBER_C).withRoomType(VALID_ROOM_TYPE_B).build();
        Room room6 = new RoomBuilder()
                .withFloor(VALID_FLOOR_C).withRoomNumber(VALID_ROOM_NUMBER_B).withRoomType(VALID_ROOM_TYPE_C).build();
        Allocation allocation2 = new Allocation(room2.getFloor(), room2.getRoomNumber(), ALICE.getStudentId());
        Allocation allocation3 = new Allocation(room3.getFloor(), room3.getRoomNumber(), BENSON.getStudentId());
        Allocation allocation5 = new Allocation(room5.getFloor(), room5.getRoomNumber(), CARL.getStudentId());
        for (Room r: Arrays.asList(room1, room2, room3, room4, room5, room6)) {
            ab.addRoom(r);
        }
        for (Allocation a: Arrays.asList(allocation2, allocation3, allocation5)) {
            ab.addAllocation(a);
        }
        model = new ModelManager(ab, new UserPrefs());
        expectedModel = new ModelManager(model.getResiReg(), new UserPrefs());
    }

    @Test
    public void testSetUp() {
        assertEquals(expectedModel, model);
    }

    // RoomFilter tests
    // not sure how to test predicates

    @Test
    public void roomFilterEquals() {
        assertEquals(new RoomFilter(), new RoomFilter());

        assertEquals(new RoomFilter(), new RoomFilterBuilder().build());

        RoomFilter filter = new RoomFilter();
        filter.addFloors(Arrays.asList(new Floor(VALID_FLOOR_A)));
        assertEquals(filter, new RoomFilterBuilder().addFloor(VALID_FLOOR_A).build());
        assertNotEquals(new RoomFilter(), filter);
    }

    @Test
    public void equals() {
        assertEquals(new ListRoomsCommand(new RoomFilter()), new ListRoomsCommand(new RoomFilter()));

        ListRoomsCommand command1 = new ListRoomsCommand(new RoomFilterBuilder().addRoomType(VALID_ROOM_TYPE_A).build());
        ListRoomsCommand command2 = new ListRoomsCommand(new RoomFilterBuilder().addRoomType(VALID_ROOM_TYPE_A).build());
        assertEquals(command1, command2);

        // negative test
        ListRoomsCommand command3 = new ListRoomsCommand(new RoomFilterBuilder().addRoomType(VALID_ROOM_TYPE_B).build());
        assertNotEquals(command1, command3);
    }

    // ListRoomsCommand tests

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertToggleCommandSuccess(
                new ListRoomsCommand(new RoomFilter()),
                model, history, ListRoomsCommand.MESSAGE_SUCCESS, expectedModel, TabView.ROOMS);
    }

    @Test
    void execute_listFilterIsVacant_showsOnlyVacantRooms() {
        expectedModel.updateFilteredRoomList((room, m) -> !m.isAllocated(room));
        var cmd = new ListRoomsCommand(new RoomFilterBuilder().onlyVacant().build());
        assertToggleCommandSuccess(
                cmd,
                model,
                history,
                ListRoomsCommand.MESSAGE_FILTERED_SUCCESS, expectedModel, TabView.ROOMS);
    }

    @Test
    void execute_listFilterIsAllocated_showsOnlyAllocatedRooms() {
        expectedModel.updateFilteredRoomList((room, m) -> m.isAllocated(room));
        var cmd = new ListRoomsCommand(new RoomFilterBuilder().onlyAllocated().build());
        assertToggleCommandSuccess(
                cmd,
                model,
                history,
                ListRoomsCommand.MESSAGE_FILTERED_SUCCESS, expectedModel, TabView.ROOMS);
    }

    @Test
    void execute_listFilterSingleFloor_showsCorrect() {
        Floor floor = new Floor(VALID_FLOOR_A);
        expectedModel.updateFilteredRoomList(room -> room.getFloor().equals(floor));
        var cmd = new ListRoomsCommand(new RoomFilterBuilder().addFloor(floor).build());
        assertToggleCommandSuccess(
                cmd,
                model,
                history,
                ListRoomsCommand.MESSAGE_FILTERED_SUCCESS, expectedModel, TabView.ROOMS);
    }

    @Test
    void execute_listFilterMultipleFloors_showsCorrect() {
        Collection<Floor> floors = Arrays.asList(new Floor(VALID_FLOOR_A), new Floor(VALID_FLOOR_B));
        expectedModel.updateFilteredRoomList(room -> floors.contains(room.getFloor()));
        var cmd = new ListRoomsCommand(new RoomFilterBuilder().addFloors(floors).build());
        assertToggleCommandSuccess(
                cmd,
                model,
                history,
                ListRoomsCommand.MESSAGE_FILTERED_SUCCESS, expectedModel, TabView.ROOMS);
    }

    @Test
    void execute_listFilterSingleRoomType_showsCorrect() {
        RoomType type = new RoomType(VALID_ROOM_TYPE_A);
        expectedModel.updateFilteredRoomList(room -> room.getRoomType().equals(type));
        var cmd = new ListRoomsCommand(new RoomFilterBuilder().addRoomType(type).build());
        assertToggleCommandSuccess(
                cmd,
                model,
                history,
                ListRoomsCommand.MESSAGE_FILTERED_SUCCESS, expectedModel, TabView.ROOMS);
    }

    @Test
    void execute_listFilterMultipleRoomTypes_showsCorrect() {
        Collection<RoomType> types = Arrays.asList(new RoomType(VALID_ROOM_TYPE_A), new RoomType(VALID_ROOM_TYPE_B));
        expectedModel.updateFilteredRoomList(room -> types.contains(room.getRoomType()));
        var cmd = new ListRoomsCommand(new RoomFilterBuilder().addRoomTypes(types).build());
        assertToggleCommandSuccess(
                cmd,
                model,
                history,
                ListRoomsCommand.MESSAGE_FILTERED_SUCCESS, expectedModel, TabView.ROOMS);
    }

    @Test
    void execute_listFilterSingleRoomNumber_showsCorrect() {
        RoomNumber number = new RoomNumber(VALID_ROOM_NUMBER_A);
        expectedModel.updateFilteredRoomList(room -> room.getRoomNumber().equals(number));
        var cmd = new ListRoomsCommand(new RoomFilterBuilder().addRoomNumber(number).build());
        assertToggleCommandSuccess(
                cmd,
                model,
                history,
                ListRoomsCommand.MESSAGE_FILTERED_SUCCESS, expectedModel, TabView.ROOMS);
    }

    @Test
    void execute_listFilterMultipleRoomNumbers_showsCorrect() {
        Collection<RoomNumber> numbers = Arrays.asList(new RoomNumber(VALID_ROOM_NUMBER_A),
                new RoomNumber(VALID_ROOM_NUMBER_B));
        expectedModel.updateFilteredRoomList(room -> numbers.contains(room.getRoomNumber()));
        var cmd = new ListRoomsCommand(new RoomFilterBuilder().addRoomNumbers(numbers).build());
        assertToggleCommandSuccess(
                cmd,
                model,
                history,
                ListRoomsCommand.MESSAGE_FILTERED_SUCCESS, expectedModel, TabView.ROOMS);
    }

    @Test
    void execute_listFilterDifferentAttributes_showsCorrect() {
        RoomType type = new RoomType(VALID_ROOM_TYPE_B);
        Floor floor = new Floor(VALID_FLOOR_A);
        expectedModel.updateFilteredRoomList(room -> room.getFloor().equals(floor) && room.getRoomType().equals(type));
        var cmd = new ListRoomsCommand(new RoomFilterBuilder().addRoomType(type).addFloor(floor).build());
        assertToggleCommandSuccess(
                cmd,
                model,
                history,
                ListRoomsCommand.MESSAGE_FILTERED_SUCCESS, expectedModel, TabView.ROOMS);
    }
}
