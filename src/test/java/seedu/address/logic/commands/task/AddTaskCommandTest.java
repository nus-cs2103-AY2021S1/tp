package seedu.address.logic.commands.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTasks.REMIND_PATIENT;
import static seedu.address.testutil.TypicalTasks.RESTOCK_SUPPLY;
import static seedu.address.testutil.command.RoomCommandTestUtil.VALID_ROOM_NUMBER_EIGHT;
import static seedu.address.testutil.command.RoomCommandTestUtil.VALID_ROOM_NUMBER_SEVEN;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.room.Room;
import seedu.address.testutil.RoomBuilder;

//@@author w-yeehong
public class AddTaskCommandTest {

    private Model modelMock;

    @BeforeEach
    public void setUp() {
        modelMock = mock(Model.class);
    }

    @Test
    public void constructor_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddTaskCommand(null, VALID_ROOM_NUMBER_SEVEN));
    }

    @Test
    public void constructor_negativeRoomNumber_throwsAssertionError() {
        int negativeRoomNumber = -1;
        assertThrows(AssertionError.class, () -> new AddTaskCommand(REMIND_PATIENT, negativeRoomNumber));
    }

    @Test
    public void execute_getRoomWithRoomNumberReturnsNull_throwsAssertionError() {
        when(modelMock.getRoomWithRoomNumber(anyInt())).thenReturn(null);

        AddTaskCommand addTaskCommand = new AddTaskCommand(REMIND_PATIENT, VALID_ROOM_NUMBER_SEVEN);

        assertThrows(AssertionError.class, () -> addTaskCommand.execute(modelMock));
    }

    @Test
    public void execute_roomWithRoomNumberNotInModel_throwsCommandException() {
        when(modelMock.getRoomWithRoomNumber(anyInt())).thenReturn(Optional.empty());

        AddTaskCommand addTaskCommand = new AddTaskCommand(REMIND_PATIENT, VALID_ROOM_NUMBER_SEVEN);

        assertThrows(CommandException.class, Messages.MESSAGE_INVALID_ROOM_NUMBER, () ->
                addTaskCommand.execute(modelMock));
    }

    @Test
    public void execute_taskAcceptedByModel_addSuccessful() throws Exception {
        Room validRoom = new RoomBuilder().withRoomNumber(VALID_ROOM_NUMBER_SEVEN).build();
        when(modelMock.getRoomWithRoomNumber(VALID_ROOM_NUMBER_SEVEN)).thenReturn(Optional.of(validRoom));

        CommandResult commandResult = new AddTaskCommand(REMIND_PATIENT, VALID_ROOM_NUMBER_SEVEN).execute(modelMock);

        verify(modelMock).addTaskToRoom(REMIND_PATIENT, validRoom);
        assertEquals(String.format(AddTaskCommand.MESSAGE_ADD_TASK_SUCCESS, VALID_ROOM_NUMBER_SEVEN, REMIND_PATIENT),
                commandResult.getFeedbackToUser());
    }

    @Test
    public void equals() {
        AddTaskCommand addRemindPatientToRoomSeven = new AddTaskCommand(REMIND_PATIENT, VALID_ROOM_NUMBER_SEVEN);

        // same object -> returns true
        assertTrue(addRemindPatientToRoomSeven.equals(addRemindPatientToRoomSeven));

        // same values -> returns true
        AddTaskCommand addRemindPatientToRoomSevenCopy = new AddTaskCommand(REMIND_PATIENT, VALID_ROOM_NUMBER_SEVEN);
        assertTrue(addRemindPatientToRoomSeven.equals(addRemindPatientToRoomSevenCopy));

        // different types -> returns false
        assertFalse(addRemindPatientToRoomSeven.equals(1));

        // null -> returns false
        assertFalse(addRemindPatientToRoomSeven.equals(null));

        // different task -> returns false
        AddTaskCommand addRestockSupplyToRoomSeven = new AddTaskCommand(RESTOCK_SUPPLY, VALID_ROOM_NUMBER_SEVEN);
        assertFalse(addRemindPatientToRoomSeven.equals(addRestockSupplyToRoomSeven));

        // different room number -> returns false
        AddTaskCommand addRemindPatientToRoomEight = new AddTaskCommand(REMIND_PATIENT, VALID_ROOM_NUMBER_EIGHT);
        assertFalse(addRemindPatientToRoomSeven.equals(addRemindPatientToRoomEight));
    }
}
