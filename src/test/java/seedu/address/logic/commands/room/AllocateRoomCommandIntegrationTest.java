package seedu.address.logic.commands.room;

//@author LeeMingDe

import static seedu.address.commons.core.Messages.MESSAGE_PATIENT_ALREADY_ASSIGNED;
import static seedu.address.testutil.TypicalPatients.ALICE;
import static seedu.address.testutil.TypicalPatients.CARL;
import static seedu.address.testutil.TypicalPatients.getTypicalPatientRecords;
import static seedu.address.testutil.TypicalRooms.getTypicalRoomList;
import static seedu.address.testutil.command.GeneralCommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.command.GeneralCommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.command.RoomCommandTestUtil.VALID_ROOM_NUMBER_ONE;
import static seedu.address.testutil.command.RoomCommandTestUtil.VALID_ROOM_NUMBER_TWO;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.RoomList;
import seedu.address.model.UserPrefs;
import seedu.address.model.room.Room;
import seedu.address.testutil.AllocateRoomDescriptorBuilder;
import seedu.address.testutil.RoomBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AllocateRoomCommand}.
 */
public class AllocateRoomCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalPatientRecords(), new RoomList(), new UserPrefs());
    }

    @Test
    public void execute_allocatePatientToRoom_success() {
        Room editedRoom = new RoomBuilder()
            .withRoomNumber(Integer.parseInt(VALID_ROOM_NUMBER_ONE))
            .withPatient(CARL)
            .build();
        model.addRooms(1);
        Room room = model.getFilteredRoomList().get(0);

        AllocateRoomCommand.AllocateRoomDescriptor descriptor = new AllocateRoomDescriptorBuilder(editedRoom).build();
        AllocateRoomCommand allocateRoomCommand = new AllocateRoomCommand(Integer.parseInt(VALID_ROOM_NUMBER_ONE),
            descriptor, false);
        String expectedMessage = String.format(AllocateRoomCommand.MESSAGE_ALLOCATE_ROOM_SUCCESS, editedRoom);

        ModelManager expectedModel =
            new ModelManager(getTypicalPatientRecords(), model.getModifiableRoomList(), new UserPrefs());
        expectedModel.setSingleRoom(room, editedRoom);
        assertCommandSuccess(allocateRoomCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_removePatientFromRoom_success() {
        Room editedRoom = new RoomBuilder()
            .withRoomNumber(Integer.parseInt(VALID_ROOM_NUMBER_ONE))
            .withPatient(null)
            .withIsOccupied(false)
            .build();
        model.addRooms(1);
        Room room = model.getFilteredRoomList().get(0);
        room.setPatient(ALICE);

        AllocateRoomCommand.AllocateRoomDescriptor descriptor = new AllocateRoomDescriptorBuilder(editedRoom).build();
        AllocateRoomCommand allocateRoomCommand = new AllocateRoomCommand(Integer.parseInt(VALID_ROOM_NUMBER_ONE),
            descriptor, true);
        String expectedMessage = String.format(AllocateRoomCommand.MESSAGE_ALLOCATE_ROOM_SUCCESS, editedRoom);

        ModelManager expectedModel =
            new ModelManager(getTypicalPatientRecords(), model.getModifiableRoomList(), new UserPrefs());
        expectedModel.setSingleRoom(room, editedRoom);
        assertCommandSuccess(allocateRoomCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_patientAlreadyInRoom_throwsCommandException() {
        Room editedRoom = new RoomBuilder()
            .withRoomNumber(Integer.parseInt(VALID_ROOM_NUMBER_TWO))
            .withPatient(ALICE)
            .withIsOccupied(false)
            .build();
        model.addRooms(2);
        Room roomWithAlice = model.getFilteredRoomList().get(0);
        Room emptyRoom = model.getFilteredRoomList().get(1);
        roomWithAlice.setPatient(ALICE);

        AllocateRoomCommand.AllocateRoomDescriptor descriptor = new AllocateRoomDescriptorBuilder(editedRoom).build();
        AllocateRoomCommand allocateRoomCommand = new AllocateRoomCommand(Integer.parseInt(VALID_ROOM_NUMBER_TWO),
            descriptor, false);

        ModelManager expectedModel =
            new ModelManager(getTypicalPatientRecords(), getTypicalRoomList(), new UserPrefs());
        expectedModel.setSingleRoom(emptyRoom, editedRoom);
        assertCommandFailure(allocateRoomCommand, model, MESSAGE_PATIENT_ALREADY_ASSIGNED);
    }
}
