package seedu.address.logic.commands.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalRooms.ROOM_NO_PATIENT_TASK_RESTOCK_SUPPLY;
import static seedu.address.testutil.TypicalTasks.RESTOCK_SUPPLY;
import static seedu.address.testutil.command.RoomCommandTestUtil.VALID_ROOM_NUMBER_EIGHT;
import static seedu.address.testutil.command.RoomCommandTestUtil.VALID_ROOM_NUMBER_SEVEN;
import static seedu.address.testutil.command.TaskCommandTestUtil.VALID_TASK_INDEX_ONE;
import static seedu.address.testutil.command.TaskCommandTestUtil.VALID_TASK_INDEX_TWO;

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
public class DeleteTaskCommandTest {

    private Model modelMock;
    private Room validRoom;

    @BeforeEach
    public void setUp() {
        modelMock = mock(Model.class);
        validRoom = new RoomBuilder(ROOM_NO_PATIENT_TASK_RESTOCK_SUPPLY)
                .withRoomNumber(VALID_ROOM_NUMBER_SEVEN).build();
    }

    @Test
    public void constructor_nullTaskIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DeleteTaskCommand(VALID_ROOM_NUMBER_SEVEN, null));
    }

    @Test
    public void constructor_negativeRoomNumber_throwsAssertionError() {
        int negativeRoomNumber = -1;
        assertThrows(AssertionError.class, () -> new DeleteTaskCommand(negativeRoomNumber, VALID_TASK_INDEX_ONE));
    }

    @Test
    public void execute_getRoomWithRoomNumberReturnsNull_throwsAssertionError() {
        when(modelMock.getRoomWithRoomNumber(anyInt())).thenReturn(null);
        when(modelMock.getTaskFromRoomWithTaskIndex(VALID_TASK_INDEX_ONE, validRoom))
                .thenReturn(Optional.of(RESTOCK_SUPPLY));

        DeleteTaskCommand deleteTaskCommand = new DeleteTaskCommand(VALID_ROOM_NUMBER_SEVEN, VALID_TASK_INDEX_ONE);

        assertThrows(AssertionError.class, () -> deleteTaskCommand.execute(modelMock));
    }

    @Test
    public void execute_getTaskFromRoomWithTaskIndexReturnsNull_throwsAssertionError() {
        when(modelMock.getRoomWithRoomNumber(VALID_ROOM_NUMBER_SEVEN)).thenReturn(Optional.of(validRoom));
        when(modelMock.getTaskFromRoomWithTaskIndex(VALID_TASK_INDEX_ONE, validRoom)).thenReturn(null);

        DeleteTaskCommand deleteTaskCommand = new DeleteTaskCommand(VALID_ROOM_NUMBER_SEVEN, VALID_TASK_INDEX_ONE);

        assertThrows(AssertionError.class, () -> deleteTaskCommand.execute(modelMock));
    }

    @Test
    public void execute_roomWithRoomNumberNotInModel_throwsCommandException() {
        when(modelMock.getRoomWithRoomNumber(anyInt())).thenReturn(Optional.empty());
        when(modelMock.getTaskFromRoomWithTaskIndex(VALID_TASK_INDEX_ONE, validRoom))
                .thenReturn(Optional.of(RESTOCK_SUPPLY));

        DeleteTaskCommand deleteTaskCommand = new DeleteTaskCommand(VALID_ROOM_NUMBER_SEVEN, VALID_TASK_INDEX_ONE);

        assertThrows(CommandException.class, Messages.MESSAGE_INVALID_ROOM_NUMBER, () ->
                deleteTaskCommand.execute(modelMock));
    }

    @Test
    public void execute_taskWithTaskIndexNotInRoom_throwsCommandException() {
        when(modelMock.getRoomWithRoomNumber(VALID_ROOM_NUMBER_SEVEN)).thenReturn(Optional.of(validRoom));
        when(modelMock.getTaskFromRoomWithTaskIndex(VALID_TASK_INDEX_ONE, validRoom))
                .thenReturn(Optional.empty());

        DeleteTaskCommand deleteTaskCommand = new DeleteTaskCommand(VALID_ROOM_NUMBER_SEVEN, VALID_TASK_INDEX_ONE);

        assertThrows(CommandException.class, Messages.MESSAGE_INVALID_TASK_INDEX, () ->
                deleteTaskCommand.execute(modelMock));
    }

    @Test
    public void execute_taskAcceptedByModel_deleteSuccessful() throws Exception {
        when(modelMock.getRoomWithRoomNumber(VALID_ROOM_NUMBER_SEVEN)).thenReturn(Optional.of(validRoom));
        when(modelMock.getTaskFromRoomWithTaskIndex(VALID_TASK_INDEX_ONE, validRoom))
                .thenReturn(Optional.of(RESTOCK_SUPPLY));

        CommandResult commandResult = new DeleteTaskCommand(VALID_ROOM_NUMBER_SEVEN, VALID_TASK_INDEX_ONE)
                .execute(modelMock);

        verify(modelMock).deleteTaskFromRoom(RESTOCK_SUPPLY, validRoom);
        assertEquals(String.format(DeleteTaskCommand.MESSAGE_DELETE_TASK_SUCCESS, VALID_TASK_INDEX_ONE.getOneBased(),
                VALID_ROOM_NUMBER_SEVEN, RESTOCK_SUPPLY), commandResult.getFeedbackToUser());
    }

    @Test
    public void equals() {
        DeleteTaskCommand deleteTaskOneFromRoomSeven = new DeleteTaskCommand(
                VALID_ROOM_NUMBER_SEVEN, VALID_TASK_INDEX_ONE);

        // same object -> returns true
        assertTrue(deleteTaskOneFromRoomSeven.equals(deleteTaskOneFromRoomSeven));

        // same values -> returns true
        DeleteTaskCommand deleteTaskOneFromRoomSevenCopy = new DeleteTaskCommand(
                VALID_ROOM_NUMBER_SEVEN, VALID_TASK_INDEX_ONE);
        assertTrue(deleteTaskOneFromRoomSeven.equals(deleteTaskOneFromRoomSevenCopy));

        // different types -> returns false
        assertFalse(deleteTaskOneFromRoomSeven.equals(1));

        // null -> returns false
        assertFalse(deleteTaskOneFromRoomSeven.equals(null));

        // different room index -> returns false
        DeleteTaskCommand deleteTaskOneFromRoomEight = new DeleteTaskCommand(
                VALID_ROOM_NUMBER_EIGHT, VALID_TASK_INDEX_ONE);
        assertFalse(deleteTaskOneFromRoomSeven.equals(deleteTaskOneFromRoomEight));

        // different task index -> returns false
        DeleteTaskCommand deleteTaskTwoFromRoomSeven = new DeleteTaskCommand(
                VALID_ROOM_NUMBER_SEVEN, VALID_TASK_INDEX_TWO);
        assertFalse(deleteTaskOneFromRoomSeven.equals(deleteTaskTwoFromRoomSeven));
    }
}
