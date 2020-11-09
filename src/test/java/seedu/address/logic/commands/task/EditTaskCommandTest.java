package seedu.address.logic.commands.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static seedu.address.logic.commands.task.EditTaskCommand.EditTaskDescriptor;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalRooms.ROOM_NO_PATIENT_TASK_RESTOCK_SUPPLY;
import static seedu.address.testutil.TypicalTasks.REMIND_PATIENT;
import static seedu.address.testutil.TypicalTasks.RESTOCK_SUPPLY;
import static seedu.address.testutil.command.RoomCommandTestUtil.VALID_ROOM_NUMBER_EIGHT;
import static seedu.address.testutil.command.RoomCommandTestUtil.VALID_ROOM_NUMBER_SEVEN;
import static seedu.address.testutil.command.TaskCommandTestUtil.VALID_DATETIME_DUE_ORDER_BEDSHEET;
import static seedu.address.testutil.command.TaskCommandTestUtil.VALID_DATETIME_DUE_REMIND_PATIENT;
import static seedu.address.testutil.command.TaskCommandTestUtil.VALID_DESCRIPTION_ORDER_BEDSHEET;
import static seedu.address.testutil.command.TaskCommandTestUtil.VALID_DESCRIPTION_REMIND_PATIENT;
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
import seedu.address.model.task.DateTimeDue;
import seedu.address.model.task.Description;
import seedu.address.model.task.Task;
import seedu.address.testutil.RoomBuilder;
import seedu.address.testutil.TaskBuilder;

//@@author w-yeehong
public class EditTaskCommandTest {

    private EditTaskDescriptor descriptor;
    private Model modelMock;
    private Room validRoom;

    @BeforeEach
    public void setUp() {
        descriptor = new EditTaskDescriptor();
        modelMock = mock(Model.class);
        validRoom = new RoomBuilder(ROOM_NO_PATIENT_TASK_RESTOCK_SUPPLY)
                .withRoomNumber(VALID_ROOM_NUMBER_SEVEN).build();
    }

    @Test
    public void constructor_negativeRoomNumber_throwsAssertionError() {
        int negativeRoomNumber = -1;
        assertThrows(AssertionError.class, () ->
                new EditTaskCommand(negativeRoomNumber, VALID_TASK_INDEX_ONE, descriptor));
    }

    @Test
    public void constructor_nullTaskIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new EditTaskCommand(VALID_ROOM_NUMBER_SEVEN, null, descriptor));
    }

    @Test
    public void constructor_nullEditTaskDescriptor_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new EditTaskCommand(VALID_ROOM_NUMBER_SEVEN, VALID_TASK_INDEX_ONE, null));
    }

    @Test
    public void execute_getRoomWithRoomNumberReturnsNull_throwsAssertionError() {
        when(modelMock.getRoomWithRoomNumber(anyInt())).thenReturn(null);
        when(modelMock.getTaskFromRoomWithTaskIndex(VALID_TASK_INDEX_ONE, validRoom))
                .thenReturn(Optional.of(RESTOCK_SUPPLY));

        EditTaskCommand editTaskCommand = new EditTaskCommand(VALID_ROOM_NUMBER_SEVEN,
                VALID_TASK_INDEX_ONE, descriptor);

        assertThrows(AssertionError.class, () -> editTaskCommand.execute(modelMock));
    }

    @Test
    public void execute_getTaskFromRoomWithTaskIndexReturnsNull_throwsAssertionError() {
        when(modelMock.getRoomWithRoomNumber(VALID_ROOM_NUMBER_SEVEN)).thenReturn(Optional.of(validRoom));
        when(modelMock.getTaskFromRoomWithTaskIndex(VALID_TASK_INDEX_ONE, validRoom)).thenReturn(null);

        EditTaskCommand editTaskCommand = new EditTaskCommand(VALID_ROOM_NUMBER_SEVEN,
                VALID_TASK_INDEX_ONE, descriptor);

        assertThrows(AssertionError.class, () -> editTaskCommand.execute(modelMock));
    }

    @Test
    public void execute_roomWithRoomNumberNotInModel_throwsCommandException() {
        when(modelMock.getRoomWithRoomNumber(anyInt())).thenReturn(Optional.empty());
        when(modelMock.getTaskFromRoomWithTaskIndex(VALID_TASK_INDEX_ONE, validRoom))
                .thenReturn(Optional.of(RESTOCK_SUPPLY));

        EditTaskCommand editTaskCommand = new EditTaskCommand(VALID_ROOM_NUMBER_SEVEN,
                VALID_TASK_INDEX_ONE, descriptor);

        assertThrows(CommandException.class, Messages.MESSAGE_INVALID_ROOM_NUMBER, () ->
                editTaskCommand.execute(modelMock));
    }

    @Test
    public void execute_taskWithTaskIndexNotInRoom_throwsCommandException() {
        when(modelMock.getRoomWithRoomNumber(VALID_ROOM_NUMBER_SEVEN)).thenReturn(Optional.of(validRoom));
        when(modelMock.getTaskFromRoomWithTaskIndex(VALID_TASK_INDEX_ONE, validRoom))
                .thenReturn(Optional.empty());

        EditTaskCommand editTaskCommand = new EditTaskCommand(VALID_ROOM_NUMBER_SEVEN,
                VALID_TASK_INDEX_ONE, descriptor);

        assertThrows(CommandException.class, Messages.MESSAGE_INVALID_TASK_INDEX, () ->
                editTaskCommand.execute(modelMock));
    }

    @Test
    public void execute_taskDescriptorHasSameFieldsAsTask_throwsCommandException() {
        when(modelMock.getRoomWithRoomNumber(VALID_ROOM_NUMBER_SEVEN)).thenReturn(Optional.of(validRoom));
        when(modelMock.getTaskFromRoomWithTaskIndex(VALID_TASK_INDEX_ONE, validRoom))
                .thenReturn(Optional.of(RESTOCK_SUPPLY));

        descriptor.setDescription(RESTOCK_SUPPLY.getDescription());
        descriptor.setDateTimeDue(RESTOCK_SUPPLY.getDueAt());

        EditTaskCommand editTaskCommand = new EditTaskCommand(VALID_ROOM_NUMBER_SEVEN,
                VALID_TASK_INDEX_ONE, descriptor);

        assertThrows(CommandException.class, Messages.MESSAGE_TASK_NOT_EDITED, () ->
                editTaskCommand.execute(modelMock));
    }

    @Test
    public void execute_taskDescriptorHasDifferentDescription_editSuccessful() throws Exception {
        when(modelMock.getRoomWithRoomNumber(VALID_ROOM_NUMBER_SEVEN)).thenReturn(Optional.of(validRoom));
        when(modelMock.getTaskFromRoomWithTaskIndex(VALID_TASK_INDEX_ONE, validRoom))
                .thenReturn(Optional.of(REMIND_PATIENT));

        descriptor.setDescription(new Description(VALID_DESCRIPTION_ORDER_BEDSHEET));
        descriptor.setDateTimeDue(REMIND_PATIENT.getDueAt());

        Task editedTask = new TaskBuilder()
                .withDescription(VALID_DESCRIPTION_ORDER_BEDSHEET)
                .withDateTimeDue(Optional.of(VALID_DATETIME_DUE_REMIND_PATIENT)).build();

        CommandResult commandResult = new EditTaskCommand(VALID_ROOM_NUMBER_SEVEN, VALID_TASK_INDEX_ONE, descriptor)
                .execute(modelMock);

        verify(modelMock).setTaskToRoom(REMIND_PATIENT, editedTask, validRoom);
        assertEquals(String.format(EditTaskCommand.MESSAGE_EDIT_TASK_SUCCESS, VALID_TASK_INDEX_ONE.getOneBased(),
                VALID_ROOM_NUMBER_SEVEN, editedTask), commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_taskDescriptionHasDifferentDateTimeDue_editSuccessful() throws Exception {
        when(modelMock.getRoomWithRoomNumber(VALID_ROOM_NUMBER_SEVEN)).thenReturn(Optional.of(validRoom));
        when(modelMock.getTaskFromRoomWithTaskIndex(VALID_TASK_INDEX_ONE, validRoom))
                .thenReturn(Optional.of(REMIND_PATIENT));

        descriptor.setDescription(REMIND_PATIENT.getDescription());
        descriptor.setDateTimeDue(new DateTimeDue(VALID_DATETIME_DUE_ORDER_BEDSHEET));

        Task editedTask = new TaskBuilder()
                .withDescription(VALID_DESCRIPTION_REMIND_PATIENT)
                .withDateTimeDue(Optional.of(VALID_DATETIME_DUE_ORDER_BEDSHEET)).build();

        CommandResult commandResult = new EditTaskCommand(VALID_ROOM_NUMBER_SEVEN, VALID_TASK_INDEX_ONE, descriptor)
                .execute(modelMock);

        verify(modelMock).setTaskToRoom(REMIND_PATIENT, editedTask, validRoom);
        assertEquals(String.format(EditTaskCommand.MESSAGE_EDIT_TASK_SUCCESS, VALID_TASK_INDEX_ONE.getOneBased(),
                VALID_ROOM_NUMBER_SEVEN, editedTask), commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_taskDescriptionHasAllDifferentFields_editSuccessful() throws Exception {
        when(modelMock.getRoomWithRoomNumber(VALID_ROOM_NUMBER_SEVEN)).thenReturn(Optional.of(validRoom));
        when(modelMock.getTaskFromRoomWithTaskIndex(VALID_TASK_INDEX_ONE, validRoom))
                .thenReturn(Optional.of(REMIND_PATIENT));

        descriptor.setDescription(new Description(VALID_DESCRIPTION_ORDER_BEDSHEET));
        descriptor.setDateTimeDue(new DateTimeDue(VALID_DATETIME_DUE_ORDER_BEDSHEET));

        Task editedTask = new TaskBuilder()
                .withDescription(VALID_DESCRIPTION_ORDER_BEDSHEET)
                .withDateTimeDue(Optional.of(VALID_DATETIME_DUE_ORDER_BEDSHEET)).build();

        CommandResult commandResult = new EditTaskCommand(VALID_ROOM_NUMBER_SEVEN, VALID_TASK_INDEX_ONE, descriptor)
                .execute(modelMock);

        verify(modelMock).setTaskToRoom(REMIND_PATIENT, editedTask, validRoom);
        assertEquals(String.format(EditTaskCommand.MESSAGE_EDIT_TASK_SUCCESS, VALID_TASK_INDEX_ONE.getOneBased(),
                VALID_ROOM_NUMBER_SEVEN, editedTask), commandResult.getFeedbackToUser());
    }

    @Test
    public void equals() {
        EditTaskCommand editTaskOneFromRoomSeven = new EditTaskCommand(
                VALID_ROOM_NUMBER_SEVEN, VALID_TASK_INDEX_ONE, descriptor);
        EditTaskCommand editTaskOneFromRoomEight = new EditTaskCommand(
                VALID_ROOM_NUMBER_EIGHT, VALID_TASK_INDEX_ONE, descriptor);
        EditTaskCommand editTaskTwoFromRoomSeven = new EditTaskCommand(
                VALID_ROOM_NUMBER_SEVEN, VALID_TASK_INDEX_TWO, descriptor);

        // same object -> returns true
        assertTrue(editTaskOneFromRoomSeven.equals(editTaskOneFromRoomSeven));

        // same values -> returns true
        EditTaskCommand editTaskOneFromRoomSevenCopy = new EditTaskCommand(
                VALID_ROOM_NUMBER_SEVEN, VALID_TASK_INDEX_ONE, descriptor);
        assertTrue(editTaskOneFromRoomSeven.equals(editTaskOneFromRoomSevenCopy));

        // different types -> returns false
        assertFalse(editTaskOneFromRoomSeven.equals(1));

        // null -> returns false
        assertFalse(editTaskOneFromRoomSeven.equals(null));

        // different room number -> returns false
        assertFalse(editTaskOneFromRoomSeven.equals(editTaskOneFromRoomEight));

        // different task index -> returns false
        assertFalse(editTaskOneFromRoomSeven.equals(editTaskTwoFromRoomSeven));

        // different edit task descriptor -> returns false
        EditTaskDescriptor modifiedDescriptor = new EditTaskDescriptor();
        modifiedDescriptor.setDescription(new Description(VALID_DESCRIPTION_REMIND_PATIENT));
        EditTaskCommand editTaskOneFromRoomSevenModifiedDescriptor = new EditTaskCommand(
                VALID_ROOM_NUMBER_SEVEN, VALID_TASK_INDEX_ONE, modifiedDescriptor);
        assertFalse(editTaskOneFromRoomSeven.equals(editTaskOneFromRoomSevenModifiedDescriptor));
    }
}
