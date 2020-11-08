package seedu.address.logic.commands.room;

import static seedu.address.logic.commands.room.InitRoomCommand.MESSAGE_NEGATIVE_VALUES_CANNOT_BE_INPUT;
import static seedu.address.logic.commands.room.InitRoomCommand.MESSAGE_SUCCESS;
import static seedu.address.logic.commands.room.InitRoomCommand.MESSAGE_ZERO_CANNOT_BE_AN_INPUT;
import static seedu.address.testutil.command.GeneralCommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.command.GeneralCommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.PatientRecords;
import seedu.address.model.RoomList;
import seedu.address.model.UserPrefs;

class AddRoomsCommandTest {

    @Test
    void execute_addRooms_success() {
        Model model = new ModelManager(new PatientRecords(), new RoomList(), new UserPrefs());
        Model expectedModel =
                new ModelManager(model.getPatientRecords(), new RoomList(), new UserPrefs());
        expectedModel.addRooms(100);
        String expectedResult = String.format(MESSAGE_SUCCESS, 100);
        assertCommandSuccess(new InitRoomCommand(100), model, expectedResult, expectedModel);
    }

    @Test
    void execute_addRooms_throwsCommandException() {
        Model model = new ModelManager(new PatientRecords(), new RoomList(), new UserPrefs());
        model.addRooms(0);
        assertCommandFailure(new InitRoomCommand(0), model, MESSAGE_ZERO_CANNOT_BE_AN_INPUT);
        model.addRooms(-100);
        assertCommandFailure(new InitRoomCommand(-100), model, MESSAGE_NEGATIVE_VALUES_CANNOT_BE_INPUT);
    }
}
