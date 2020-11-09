package seedu.address.logic.commands.room;

import static seedu.address.testutil.command.GeneralCommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.command.GeneralCommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.PatientRecords;
import seedu.address.model.RoomList;
import seedu.address.model.UserPrefs;
import seedu.address.testutil.TypicalRooms;

//@@author itssodium
public class InitRoomCommandTest {

    @Test
    void execute_numberOfRooms_notDefined() {
        Model model = new ModelManager(new PatientRecords(), new RoomList(), new UserPrefs());

        assertCommandFailure(new InitRoomCommand(-100), model,
                InitRoomCommand.MESSAGE_NEGATIVE_VALUES_CANNOT_BE_INPUT);
    }

    @Test
    void execute_middleRangeNumberOfRooms_success() {
        Model model = new ModelManager(new PatientRecords(), new RoomList(), new UserPrefs());
        Model expectedModel =
                new ModelManager(model.getPatientRecords(), new RoomList(), new UserPrefs());

        //initRoom to 250 rooms -> middle value for acceptable range from 1 - 500
        String expectedMessage = String.format(InitRoomCommand.MESSAGE_SUCCESS, 250);
        expectedModel.initRooms(250);
        assertCommandSuccess(new InitRoomCommand(250), model, expectedMessage, expectedModel);
    }

    @Test
    void execute_minRangeNumberOfRooms_success() {
        Model model = new ModelManager(new PatientRecords(), new RoomList(), new UserPrefs());
        Model expectedModel =
                new ModelManager(model.getPatientRecords(), new RoomList(), new UserPrefs());

        //initRoom to 1 rooms -> start of acceptable range
        String expectedMessage = String.format(InitRoomCommand.MESSAGE_SUCCESS, 1);
        expectedModel.initRooms(1);
        assertCommandSuccess(new InitRoomCommand(1), model, expectedMessage, expectedModel);
    }

    @Test
    void execute_maxNumberOfRooms_success() {
        Model model = new ModelManager(new PatientRecords(), new RoomList(), new UserPrefs());
        Model expectedModel =
                new ModelManager(model.getPatientRecords(), new RoomList(), new UserPrefs());

        //initRoom to 500 rooms -> end of acceptable range
        String expectedMessage = String.format(InitRoomCommand.MESSAGE_SUCCESS, 500);
        expectedModel.initRooms(500);
        assertCommandSuccess(new InitRoomCommand(500), model, expectedMessage, expectedModel);
    }

    @Test
    void execute_increaseNumberOfRooms_success() {
        Model model = new ModelManager(new PatientRecords(), new RoomList(), new UserPrefs());
        Model expectedModel =
                new ModelManager(model.getPatientRecords(), new RoomList(), new UserPrefs());

        //initRoom to 10 rooms first
        String expectedMessage = String.format(InitRoomCommand.MESSAGE_SUCCESS, 10);
        expectedModel.initRooms(10);
        assertCommandSuccess(new InitRoomCommand(10), model, expectedMessage, expectedModel);

        //initRoom to 50 rooms -> increase number of rooms
        String expectedMessage2 = String.format(InitRoomCommand.MESSAGE_SUCCESS, 50);
        expectedModel.initRooms(50);
        assertCommandSuccess(new InitRoomCommand(50), model, expectedMessage2, expectedModel);
    }

    @Test
    void execute_decreaseNumberOfRooms_success() {
        Model model = new ModelManager(new PatientRecords(), new RoomList(), new UserPrefs());
        Model expectedModel =
                new ModelManager(model.getPatientRecords(), new RoomList(), new UserPrefs());

        //initRoom to 50 rooms first
        String expectedMessage = String.format(InitRoomCommand.MESSAGE_SUCCESS, 50);
        expectedModel.initRooms(50);
        assertCommandSuccess(new InitRoomCommand(50), model, expectedMessage, expectedModel);

        //initRoom to 10 rooms -> decrease number of rooms
        String expectedMessage2 = String.format(InitRoomCommand.MESSAGE_SUCCESS, 10);
        expectedModel.initRooms(10);
        assertCommandSuccess(new InitRoomCommand(10), model, expectedMessage2, expectedModel);
    }

    @Test
    void execute_increaseNumberOfOccupiedRooms_success() {
        RoomList roomList = TypicalRooms.getTypicalRoomList();
        RoomList expectedRoomList = TypicalRooms.getTypicalRoomList();

        Model model = new ModelManager(new PatientRecords(), roomList, new UserPrefs());
        Model expectedModel = new ModelManager(model.getPatientRecords(), expectedRoomList, new UserPrefs());

        //initRoom to 50 rooms -> increase number of rooms
        String expectedMessage2 = String.format(InitRoomCommand.MESSAGE_SUCCESS, 50);
        expectedModel.initRooms(50);
        assertCommandSuccess(new InitRoomCommand(50), model, expectedMessage2, expectedModel);
    }

    @Test
    void execute_decreaseNumberOfOccupiedRooms_success() throws CommandException {
        RoomList roomList = TypicalRooms.getTypicalRoomList();
        RoomList expectedRoomList = TypicalRooms.getTypicalRoomList();

        Model model = new ModelManager(new PatientRecords(), roomList, new UserPrefs());
        Model expectedModel = new ModelManager(model.getPatientRecords(), expectedRoomList, new UserPrefs());

        //initRoom to 5 rooms -> decrease number of rooms
        String expectedMessage2 = String.format(InitRoomCommand.MESSAGE_SUCCESS, 5);
        expectedModel.initRooms(5);

        assertCommandSuccess(new InitRoomCommand(5), model, expectedMessage2, expectedModel);
    }

    @Test
    void execute_decreaseNumberOfOccupiedRooms_failure() {
        RoomList roomList = TypicalRooms.getTypicalRoomList();

        Model model = new ModelManager(new PatientRecords(), roomList, new UserPrefs());

        //initRoom to 2 rooms -> decrease number of rooms
        String expectedMessage = String.format(InitRoomCommand.MESSAGE_INSUFFICIENT_ROOMS, 2);
        assertCommandFailure(new InitRoomCommand(1), model,
                expectedMessage);
    }

    @Test
    void execute_aboveMaxNumberOfRooms_failure() {
        Model model = new ModelManager(new PatientRecords(), new RoomList(), new UserPrefs());

        //initRoom to 5001 rooms -> too many rooms initialised
        String expectedMessage = InitRoomCommand.MESSAGE_LARGE_NUMBER_OF_ROOMS_INPUT;
        assertCommandFailure(new InitRoomCommand(501), model, expectedMessage);
    }

    @Test
    void execute_zeroNumberOfRooms_failure() {
        Model model = new ModelManager(new PatientRecords(), new RoomList(), new UserPrefs());

        //initRoom to 0 rooms -> zero rooms initialised
        String expectedMessage = InitRoomCommand.MESSAGE_ZERO_CANNOT_BE_AN_INPUT;
        assertCommandFailure(new InitRoomCommand(0), model, expectedMessage);
    }

    @Test
    void execute_belowMinNumberOfRooms_failure() {
        Model model = new ModelManager(new PatientRecords(), new RoomList(), new UserPrefs());

        //initRoom to -1 rooms -> negative number of rooms initialised
        String expectedMessage = InitRoomCommand.MESSAGE_NEGATIVE_VALUES_CANNOT_BE_INPUT;
        assertCommandFailure(new InitRoomCommand(-1), model, expectedMessage);
    }
}
//@@author itssodium
